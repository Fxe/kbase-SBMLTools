package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;

import datafileutil.DataFileUtilClient;
import datafileutil.ObjectSaveData;
import datafileutil.SaveObjectsParams;
import kbasefba.Biomass;
import kbasefba.FBAModel;
import kbasereport.WorkspaceObject;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.integration.model.IntegrationMap;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils.KBaseObject;
import pt.uminho.sysbio.biosynthframework.report.IntegrationByDatabase;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReportResult;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReportResultAdapter;
import pt.uminho.sysbio.biosynthframework.sbml.MessageType;
import pt.uminho.sysbio.biosynthframework.sbml.XmlMessage;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelValidator;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;
import sbmltools.KBaseType;
import sbmltools.SbmlImportParams;
import sbmltools.SbmlImporterParams;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.common.service.UnauthorizedException;

public class KBaseSbmlTools {

  private static final Logger logger = LoggerFactory.getLogger(KBaseSbmlTools.class);

  public static String DATA_EXPORT_PATH = "/data/integration/export";
  public static String CURATION_DATA = "/data/integration/cc/cpd_curation.tsv";
  public static String LOCAL_CACHE = "./cache";
  
//  public final AuthToken authPart;
//  public final RpcContext jsonRpcContext;
//  public final URL callbackURL;
  public final String workspace;
  private final DataFileUtilClient dfuClient;
  public KBaseModelSeedIntegration modelSeedIntegration = null;
  
  
  public KBaseSbmlTools(
      String workspace, DataFileUtilClient dfuClient) throws UnauthorizedException, IOException {
    this.workspace = workspace;
    this.modelSeedIntegration = new KBaseModelSeedIntegration(DATA_EXPORT_PATH, CURATION_DATA);
    this.dfuClient = dfuClient;
  }

  public static void validateSbmlImportParams(SbmlImportParams params) {
    /* Step 1 - Parse/examine the parameters and catch any errors
     * It is important to check that parameters exist and are defined, and that nice error
     * messages are returned to users.  Parameter values go through basic validation when
     * defined in a Narrative App, but advanced users or other SDK developers can call
     * this function directly, so validation is still important.
     */
    final String workspaceName = params.getWorkspaceName();
    if (workspaceName == null || workspaceName.isEmpty()) {
      throw new IllegalArgumentException(
          "Parameter workspace_name is not set in input arguments");
    }
    final String assyRef = params.getAssemblyInputRef();
    if (assyRef == null || assyRef.isEmpty()) {
      throw new IllegalArgumentException(
          "Parameter assembly_input_ref is not set in input arguments");
    }
    if (params.getMinLength() == null) {
      throw new IllegalArgumentException(
          "Parameter min_length is not set in input arguments");
    }
    final long minLength = params.getMinLength();
    if (minLength < 0) {
      throw new IllegalArgumentException("min_length parameter cannot be negative (" +
          minLength + ")");
    }
  }

  public static class ImportModelResult {
    public String message = "";
    public String modelRef = "";
    public String modelName = "";
    public List<WorkspaceObject> objects = new ArrayList<> ();
    
    @Override
    public String toString() {
      return String.format("%s, %s, %s, %s", message, modelRef, modelName, objects);
    }
  }

  public static void xrxnAttributes(XmlSbmlModelValidator validator) {
    String[] xrxnAttr = new String[] {
        "upperFluxBound", "fast", "metaid", "reversible", "sboTerm", "name", "lowerFluxBound", "id"
    };
    String[] xrxnSpecieAttr = new String[] {
        "stoichiometry", "constant", "species", "metaid", "sboTerm"
    };
    validator.xrxnAttr.addAll(Arrays.asList(xrxnAttr));
    validator.xrxnStoichAttr.addAll(Arrays.asList(xrxnSpecieAttr));
  }

  public static Map<String, MessageType> knownSpecieAttributes() {
    String[] attr = new String[] {
        "speciesType", "NONE",
        "charge", "NONE",
        "constant", "NONE",
        "metaid", "NONE",
        "hasOnlySubstanceUnits", "NONE",
        "sboTerm", "NONE",
        "boundaryCondition", "NONE",
        "chemicalFormula", "NONE",
        "initialAmount", "NONE",
        "name", "NONE",
        "compartment", "WARN",
        "id", "CRITICAL",
        "initialConcentration", "NONE",
    };
    Map<String, MessageType> fields = new HashMap<>();
    for (int i = 0; i < attr.length; i+=2) {
      fields.put(attr[i], MessageType.valueOf(attr[i + 1]));
    }
    return fields;
  }
  
  
  
  public static String fetchAndCache(String urlString) {
    File file2 = null;
    URLConnection connection = null;
    OutputStream os = null;
    try {
      URL url = new URL(urlString);
      connection = url.openConnection();
      
      String uuid = UUID.randomUUID().toString();
      File file = new File(String.format("%s/%s", LOCAL_CACHE, uuid));
      if (file.mkdirs()) {
        logger.info("created {}", file.getAbsolutePath());
      }
      file2 = new File(String.format("%s/%s", file.getAbsolutePath(), uuid));
      os = new FileOutputStream(file2);
      logger.info("copy {} -> {}", urlString, file2.getAbsolutePath());
      IOUtils.copy(connection.getInputStream(), os);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.close(connection);
      IOUtils.closeQuietly(os);
    }
    
    if (file2 != null) {
      return file2.getAbsolutePath();
    }
    
    return null;
  }
  
  public static String status2(Map<String, Map<MetaboliteMajorLabel, String>> imap, int total) {
    Map<Object, Integer> counter = new HashMap<> ();
    Map<Object, Double> cover = new HashMap<> ();
    
    for (String e : imap.keySet()) {
      Map<MetaboliteMajorLabel, String> a = imap.get(e);
      boolean any = false;
      for (MetaboliteMajorLabel db : a.keySet()) {
        String refs = a.get(db);
        if (refs != null && !refs.isEmpty()) {
          any = true;
          CollectionUtils.increaseCount(counter, db, 1);
        }
      }
      if (any) {
        CollectionUtils.increaseCount(counter, "has_reference", 1);
      }
    }
    
    for (Object k : counter.keySet()) {
      int count = counter.get(k);
      cover.put(k, (double) count / total);
    }
    return Joiner.on(", ").withKeyValueSeparator(": ").join(cover);
  }
  
  public FBAModel importModel(InputStream is, 
      ImportModelResult result, String modelId, String url, 
      boolean runIntegration, Collection<String> biomassIds, 
      IntegrationByDatabase spiIntegrationAll,
      Map<String, Map<String, Object>> jsonResult) throws Exception {
    //import
    FBAModel model = null;
    XmlStreamSbmlReader reader = new XmlStreamSbmlReader(is);
    XmlSbmlModel xmodel = reader.parse();
    
    if (modelId == null || modelId.trim().isEmpty()) {
      modelId = getNameFromUrl(url);
      logger.info("auto model id: {}", modelId);
    }
    
    IntegrationReportResult reportData = new IntegrationReportResult();
    IntegrationReportResultAdapter resultAdapter = 
        new IntegrationReportResultAdapter(reportData);
    reportData.name = modelId;
    reportData.epochTime = System.currentTimeMillis();
    reportData.date = new GregorianCalendar().getTime().toString();
    resultAdapter.fillImportData(xmodel);
    spiIntegrationAll.modelTotal.put(modelId, xmodel.getSpecies().size());
    

    
    logger.info("validate");
    XmlSbmlModelValidator validator = new XmlSbmlModelValidator(xmodel, knownSpecieAttributes());
    xrxnAttributes(validator);
    
    List<XmlMessage> msgs = validator.validate();
    resultAdapter.fillValidationData(msgs);
    
    result.message += "\n" + String.format("Species %d, Reactions %s, %s", 
        xmodel.getSpecies().size(), 
        xmodel.getReactions().size(), 
        url);
    //      String txt = "";
    Map<MessageType, Integer> typeCounterMap = new HashMap<> ();
    for (XmlMessage m : msgs) {
      CollectionUtils.increaseCount(typeCounterMap, m.type, 1);
    }
    
    if (!typeCounterMap.isEmpty()) {
      result.message +="\n" + modelId + " "+ String.format("%s", Joiner.on(' ').withKeyValueSeparator(": ").join(typeCounterMap));
    }
    
    

    
    modelId = modelId.trim();
    
    Map<String, String> spiToModelSeedReference = new HashMap<> ();
    IntegrationMap<String, String> integration = new IntegrationMap<>();
    //check if integrate
    if (runIntegration) {
      modelSeedIntegration.spiToModelSeedReference.clear();
//      String imodelEntry = "i" + modelId;
//      KBaseModelSeedIntegration integration = new KBaseModelSeedIntegration(DATA_EXPORT_PATH, CURATION_DATA);
      Map<String, Map<MetaboliteMajorLabel, String>> imap = 
          modelSeedIntegration.generateDatabaseReferences(xmodel, modelId, resultAdapter);
      for (String spi : imap.keySet()) {
        for (MetaboliteMajorLabel db : imap.get(spi).keySet()) {
          integration.addIntegration(spi, db.toString(), imap.get(spi).get(db));
        }
      }
      //get stats
      result.message +="\n" + modelId + " " + status2(imap, xmodel.getSpecies().size());
      spiToModelSeedReference = modelSeedIntegration.spiToModelSeedReference;
      result.message += String.format("\ni: %d", spiToModelSeedReference.size());
    }
    //order matters ! fix this ... it is a factory ...
    model = new FBAModelFactory()
        .withIntegration(integration)
        .withBiomassIds(biomassIds)
        .withModelSeedReference(spiToModelSeedReference)
        .withModelId(modelId)
        .withModelName(modelId)
        .withXmlSbmlModel(xmodel)
        .build();
    
    if (!model.getBiomasses().isEmpty()) {
      Set<String> biomass = new HashSet<> ();
      for (Biomass b : model.getBiomasses()) {
        biomass.add(b.getId());
      }
      result.message +="\n" + modelId + " biomass: " + biomass;
    }
    
    jsonResult.get("models").put(modelId, reportData);
    
//    KBaseIOUtils.toJson(model);
    if (model != null) {
      String fbaModelRef = KBaseIOUtils.saveDataSafe(modelId, 
          KBaseType.FBAModel.value(), model, workspace, dfuClient);
      if (fbaModelRef != null) {
        result.objects.add(new WorkspaceObject().withDescription(url)
            .withRef(fbaModelRef));
      } else {
        logger.warn("unable to save {}", modelId);
      }
    }
    
    return model;
  }
  
  public static class ZipContainer implements Closeable {
    
    private InputStream is = null; //file input stream
    private ZipInputStream zis = null; //zip file manipulator
    private ZipFile zf = null; //zip file pointer
//    InputStream rfis = null; //file within zip file pointer
    
    public ZipContainer(String path) throws IOException {
      this.is = new FileInputStream(path);
      this.zis = new ZipInputStream(is);
      this.zf = new ZipFile(path);
    }
    
    public List<Map<String, Object>> getInputStreams() throws IOException {
      List<Map<String, Object>> streams = new ArrayList<> ();
      ZipEntry ze = null;
      while ((ze = zis.getNextEntry()) != null) {
        Map<String, Object> record = new HashMap<> ();
        record.put("name", ze.getName());
        record.put("size", ze.getSize());
        record.put("compressed_size", ze.getCompressedSize());
        record.put("method", ze.getMethod());
        record.put("is", zf.getInputStream(ze));
        streams.add(record);
      }
      
      return streams;
    }
    
    @Override
    public void close() throws IOException {
      IOUtils.closeQuietly(this.zf);
      IOUtils.closeQuietly(this.zis);
      IOUtils.closeQuietly(this.is);
    }
    
  }

  public ImportModelResult importModel(SbmlImporterParams params) {
    ImportModelResult result = new ImportModelResult();

    logger.debug("SbmlImporterParams:{} ", params);

    result.message = String.format("%s %s %d %s",
        params.getSbmlUrl(),
        params.getBiomass(),
        params.getAutomaticallyIntegrate(),
        params.getModelName());
    ZipContainer container = null;
    
    try {

      String urlPath = params.getSbmlUrl();
      //check url type
      String localPath = fetchAndCache(params.getSbmlUrl());
      if (localPath == null) {
        throw new IOException("unable to create temp file");
      }
      boolean runIntegration = params.getAutomaticallyIntegrate() == 1;
      String modelId = params.getModelName();
      List<String> biomass = params.getBiomass();
      if (biomass == null) {
        biomass = new ArrayList<> ();
      }
      Map<String, InputStream> inputStreams = new HashMap<> ();
      
      if (urlPath.endsWith(".zip")) {
        container = new ZipContainer(localPath);
        List<Map<String, Object>> streams = container.getInputStreams();
        for (Map<String, Object> d : streams) {
          InputStream is = (InputStream) d.get("is");
          String u = params.getSbmlUrl() + "/" + (String) d.get("name");
          inputStreams.put(u, is);
        }
        //ignore modelId when multiple models
        if (inputStreams.size() > 1) {
          modelId = null;
        }
      } else {
        inputStreams.put(params.getSbmlUrl(), new FileInputStream(localPath));
      }
      
      Map<String, Map<String, Object>> jsonResult = new HashMap<> ();
      jsonResult.put("models", new HashMap<String, Object> ());
      jsonResult.put("all", new HashMap<String, Object> ());
      IntegrationByDatabase spiIntegrationAll = new IntegrationByDatabase();
      List<MetaboliteMajorLabel> dbs = new ArrayList<> ();
      dbs.add(MetaboliteMajorLabel.BiGG);
      dbs.add(MetaboliteMajorLabel.BiGG2);
      dbs.add(MetaboliteMajorLabel.LigandCompound);
      dbs.add(MetaboliteMajorLabel.LigandGlycan);
      dbs.add(MetaboliteMajorLabel.LigandDrug);
      dbs.add(MetaboliteMajorLabel.Seed);
      dbs.add(MetaboliteMajorLabel.ModelSeed);
      dbs.add(MetaboliteMajorLabel.LipidMAPS);
      dbs.add(MetaboliteMajorLabel.ChEBI);
//      dbs.add(MetaboliteMajorLabel.)~
      
      for (MetaboliteMajorLabel db : dbs) {
        spiIntegrationAll.databases.add(db.toString());
      }
      
      for (String u : inputStreams.keySet()) {
        InputStream is = inputStreams.get(u);
        try {
          FBAModel fbaModel = importModel(is, result, modelId, u, runIntegration, biomass, spiIntegrationAll, jsonResult);
          
          if (fbaModel != null) {
            KBaseObject o = new KBaseObject();
            o.dataType = KBaseType.FBAModel;
            o.nameId = fbaModel.getId();
            o.o = fbaModel;
            if (result.modelName == null || result.modelName.isEmpty()) {
              result.modelName = fbaModel.getId();
            }
          }
//          KBaseIOUtils.saveData(objects, workspace, dfuClient);
        } catch (Exception e) {
          result.message += "\nERROR: " + u + " " + e.getMessage();
        }
      }
      
      jsonResult.get("all").put("species", spiIntegrationAll);
      System.out.println(jsonResult);
      
      System.out.println(spiIntegrationAll);
      
      String jsonData = KBaseIOUtils.toJson(jsonResult);
      logger.info("written {}", jsonData.length());
      
      OutputStream os = null;
      try {
        os = new FileOutputStream("/kb/module/data/readerData.json");
        IOUtils.write(jsonData, os);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(os);
      }
      
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("{}", e.getMessage());
    } finally {
      IOUtils.closeQuietly(container);
    }

    return result;
  }

  public ImportModelResult debugThings(SbmlImportParams params) {

    logger.info("run");
    ImportModelResult result = new ImportModelResult();
    String reportText = params.toString();
    result.message = reportText;
    
    try {
      KBaseBiodbContainer biodbContainer = new KBaseBiodbContainer(DATA_EXPORT_PATH);
      int nsize = biodbContainer.nameMap.size();
      result.message += String.format("\nName Dictionary: %d", nsize);
      Set<String> databases = new HashSet<> ();
      databases.add("ModelSeed");
      databases.add("BiGG");
      databases.add("BiGG2");
      databases.add("LigandCompound");
      databases.add("MetaCyc");
      
      for (String db : databases) {
        Set<Long> ids = biodbContainer.biodbService.getIdsByDatabaseAndType(db, "Metabolite");
        result.message += String.format("\n%s: %d", db, ids.size());
      }
      
    } catch (Exception e) {
      result.message += "\n ERROR " + e.getMessage();
    }
    
    return result;
  }




  @Deprecated
  public String saveData(String nameId, String dataType, Object o) throws Exception {
    //  Object o = null;
    //  String nameId = "";
    //  String dataType = "";
//    final DataFileUtilClient dfuClient = new DataFileUtilClient(callbackURL, authPart);
//    dfuClient.setIsInsecureHttpConnectionAllowed(true);
    long wsId = dfuClient.wsNameToId(workspace);

    SaveObjectsParams params = new SaveObjectsParams()
        .withId(wsId)
        .withObjects(Arrays.asList(
            new ObjectSaveData().withName(nameId)
            .withType(dataType)
            .withData(new UObject(o))));
    ////  params.setId(wsId);
    ////  List<ObjectSaveData> saveData = new ArrayList<> ();
    ////  ObjectSaveData odata = new ObjectSaveData();
    ////  odata.set
    ////  
    ////  params.setObjects(saveData);
    ////  ;
    String ref = KBaseIOUtils.getRefFromObjectInfo(dfuClient.saveObjects(params).get(0));

    return ref;
  }
  


  public static String getNameFromUrl(String urlStr) {
    String[] strs = urlStr.split("/");
    String last = strs[strs.length - 1];
    if (last.contains(".")) {
      last = last.substring(0, last.indexOf('.'));
    }
    return last;
  }




}
