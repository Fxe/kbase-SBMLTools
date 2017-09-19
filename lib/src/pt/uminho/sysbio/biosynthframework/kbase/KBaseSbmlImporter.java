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
import org.apache.commons.io.output.ByteArrayOutputStream;
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
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.ReactionMajorLabel;
import pt.uminho.sysbio.biosynthframework.integration.model.IntegrationMap;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils.KBaseObject;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelSeedIntegration.KBaseMappingResult;
import pt.uminho.sysbio.biosynthframework.report.IntegrationByDatabase;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReport;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReportResult;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReportResultAdapter;
import pt.uminho.sysbio.biosynthframework.sbml.MessageType;
import pt.uminho.sysbio.biosynthframework.sbml.XmlMessage;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelAutofix;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelMetabolicNetworkFactory;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelValidator;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;
import pt.uminho.sysbio.biosynthframework.util.ZipContainer;
import pt.uminho.sysbio.biosynthframework.util.ZipContainer.ZipRecord;
import pt.uminho.sysbio.ext.BiGGConflictResolver;
import pt.uminho.sysbio.ext.ModelSeedReactionConflictResolver;
import pt.uminho.sysbio.ext.ReactionIntegration;
import sbmltools.KBaseType;
import sbmltools.SbmlImportParams;
import sbmltools.SbmlImporterParams;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.workspace.WorkspaceClient;

public class KBaseSbmlImporter {

  private static final Logger logger = LoggerFactory.getLogger(KBaseSbmlImporter.class);


  public static String LOCAL_CACHE = "./cache";


  //  public final AuthToken authPart;
  //  public final RpcContext jsonRpcContext;
  //  public final URL callbackURL;
  public final String workspace;
  private final DataFileUtilClient dfuClient;
  private final WorkspaceClient wsClient;
  public KBaseModelSeedIntegration modelSeedIntegration = null;
  public final ReactionIntegration reactionIntegration;
  public final KBaseBiodbContainer biodbContainer;

  public KBaseSbmlImporter(
      String workspace, DataFileUtilClient dfuClient, WorkspaceClient wsClient) throws UnauthorizedException, IOException {
    this.workspace = workspace;
    this.dfuClient = dfuClient;
    this.wsClient = wsClient;
    this.biodbContainer = new KBaseBiodbContainer(KBaseConfig.DATA_EXPORT_PATH);
    this.modelSeedIntegration = new KBaseModelSeedIntegration(
        KBaseConfig.DATA_EXPORT_PATH, KBaseConfig.CURATION_DATA, biodbContainer);

    reactionIntegration = new ReactionIntegration(biodbContainer.biodbService);
    reactionIntegration.exclude(ReactionMajorLabel.BiGG2Reaction, "h");
    reactionIntegration.exclude(ReactionMajorLabel.BiGG2Reaction, "h2o");
    reactionIntegration.exclude(ReactionMajorLabel.LigandReaction, "C00001");
    reactionIntegration.exclude(ReactionMajorLabel.LigandReaction, "C00080");
    reactionIntegration.exclude(ReactionMajorLabel.BiGG, "h");
    reactionIntegration.exclude(ReactionMajorLabel.BiGG, "h2o");
    reactionIntegration.exclude(ReactionMajorLabel.Seed, "cpd00001");
    reactionIntegration.exclude(ReactionMajorLabel.Seed, "cpd00067");
    reactionIntegration.exclude(ReactionMajorLabel.ModelSeedReaction, "cpd00001");
    reactionIntegration.exclude(ReactionMajorLabel.ModelSeedReaction, "cpd00067");

    Set<ReactionMajorLabel> rxnDbs = new HashSet<> ();
    rxnDbs.add(ReactionMajorLabel.BiGG);
    rxnDbs.add(ReactionMajorLabel.LigandReaction);
    rxnDbs.add(ReactionMajorLabel.MetaCyc);
    rxnDbs.add(ReactionMajorLabel.ModelSeedReaction);
    rxnDbs.add(ReactionMajorLabel.Seed);

    for (ReactionMajorLabel db : rxnDbs) {
      reactionIntegration.setupStoichDictionary(db);
    }

    reactionIntegration.rMap.put(ReactionMajorLabel.BiGG, new BiGGConflictResolver());
    reactionIntegration.rMap.put(ReactionMajorLabel.ModelSeedReaction, new ModelSeedReactionConflictResolver());
    reactionIntegration.rMap.put(ReactionMajorLabel.Seed, new ModelSeedReactionConflictResolver());
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
    public List<File> jsonDataFiles = new ArrayList<> ();

    @Override
    public String toString() {
      return String.format("%s, %s, %s, %s", message, modelRef, modelName, objects);
    }
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
      IntegrationReport jsonResult, boolean allowBoundary) {
    
    if (modelId == null || modelId.trim().isEmpty()) {
      modelId = KBaseUtils.getNameFromUrl(url);
      logger.info("auto model id: {}", modelId);
    }
    modelId = modelId.trim();
    
    FBAModel model = null;
    try {
      //import
      XmlStreamSbmlReader reader = new XmlStreamSbmlReader(is);
      XmlSbmlModel xmodel = reader.parse();

      jsonResult.addIntegrationReport(modelId, new IntegrationReportResult());

      IntegrationReportResult reportData = new IntegrationReportResult();
      IntegrationReportResultAdapter resultAdapter = 
          new IntegrationReportResultAdapter(reportData);
      reportData.name = modelId;
      reportData.epochTime = System.currentTimeMillis();
      reportData.date = new GregorianCalendar().getTime().toString();
      resultAdapter.fillImportData(xmodel);
      spiIntegrationAll.modelTotal.put(modelId, xmodel.getSpecies().size());

      logger.info("validate");
      
      List<XmlMessage> vmsg = new ArrayList<> ();
      //    pt.uminho.sysbio.biosynthframework.sbml.
      XmlSbmlModelValidator validator = new XmlSbmlModelValidator(xmodel);
      XmlSbmlModelValidator.initializeDefaults(validator);

      List<XmlMessage> msgs = validator.validate();
      XmlSbmlModelAutofix autofix = new XmlSbmlModelAutofix();
      autofix.fix(xmodel, msgs);

      List<XmlMessage> fmsgs = autofix.messages;
      vmsg.addAll(fmsgs);
//      resultAdapter.fillValidationData(fmsgs);

      XmlSbmlModelValidator validator2 = new XmlSbmlModelValidator(xmodel);
      XmlSbmlModelValidator.initializeDefaults(validator2);
      List<XmlMessage> msgs2 = validator2.validate();
      vmsg.addAll(msgs2);
//      resultAdapter.fillValidationData(msgs2);

      if (resultAdapter.getMessageTypeCount(MessageType.CRITICAL) > 0) {
        result.message += "\n" + String.format("Unable to import model %s", url);
        IntegrationReportResult iresult = new IntegrationReportResult();
        iresult.error = String.format("Unable to import model %s", url);
        jsonResult.addIntegrationReport(modelId, iresult);
        return null;
      }

      result.message += "\n" + String.format("Species %d, Reactions %s, %s", 
          xmodel.getSpecies().size(), 
          xmodel.getReactions().size(), 
          url);
      //      String txt = "";
      for (XmlMessage m : vmsg) {
        CollectionUtils.increaseCount(reportData.validationCount, m.type, 1);
      }
      
      //write message json for model
      if (!vmsg.isEmpty()) {
        reportData.validationData = modelId + "_msg.json";
        String jsonData = KBaseIOUtils.toJson(vmsg, true);
        Integer i = KBaseIOUtils.writeStringFile(jsonData, KBaseConfig.REPORT_OUTPUT_PATH + "/" + reportData.validationData);
        logger.info("written {} bytes", i);
      }

      Map<String, String> spiToModelSeedReference = new HashMap<> ();
      IntegrationMap<String, String> sintegration = new IntegrationMap<>();
      IntegrationMap<String, String> rintegration = new IntegrationMap<>();
      //check if integrate
      if (runIntegration) {
        modelSeedIntegration.spiToModelSeedReference.clear();

        KBaseMappingResult mapping = modelSeedIntegration.generateDatabaseReferences(xmodel, modelId, resultAdapter, reactionIntegration);

        Map<String, Map<MetaboliteMajorLabel, String>> imap = mapping.species;
        Map<String, Map<ReactionMajorLabel, String>> rimap = mapping.reactions;

        spiIntegrationAll.addIntegrationMap(modelId, imap);
        for (String spi : imap.keySet()) {
          for (MetaboliteMajorLabel db : imap.get(spi).keySet()) {
            sintegration.addIntegration(spi, db.toString(), imap.get(spi).get(db));
          }
        }

        for (String mrxn : rimap.keySet()) {
          for (ReactionMajorLabel db : rimap.get(mrxn).keySet()) {
            rintegration.addIntegration(mrxn, db.toString(), rimap.get(mrxn).get(db));
          }
        }

        //get stats
        result.message +="\n" + modelId + " " + status2(imap, xmodel.getSpecies().size());
        spiToModelSeedReference = modelSeedIntegration.spiToModelSeedReference;
        result.message += String.format("\ni: %d", spiToModelSeedReference.size());


      }
      //order matters ! fix this ... it is a factory ...
      model = new FBAModelFactory()
          .withSpecieIntegration(sintegration)
          .withReactionIntegration(rintegration)
          .withBiomassIds(biomassIds)
          .withMetaboliteModelSeedReference(spiToModelSeedReference)
          .withModelId(modelId)
          .withModelName(modelId)
          .withXmlSbmlModel(xmodel, allowBoundary)
          .build();

      if (!model.getBiomasses().isEmpty()) {
        Set<String> biomass = new HashSet<> ();
        for (Biomass b : model.getBiomasses()) {
          biomass.add(b.getId());
        }
        result.message +="\n" + modelId + " biomass: " + biomass;
      }

      jsonResult.addIntegrationReport(modelId, reportData);

      //    KBaseIOUtils.toJson(model);
    } catch (Exception e) {
      e.printStackTrace();
      IntegrationReportResult iresult = new IntegrationReportResult();
      iresult.error = KBaseUtils.toString(e);
      jsonResult.addIntegrationReport(modelId, iresult);
    }
    
    return model;
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
      String localPath = KBaseIOUtils.fetchAndCache(params.getSbmlUrl(), LOCAL_CACHE);
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
        List<ZipRecord> streams = container.getInputStreams();
        for (ZipRecord zr : streams) {
          InputStream is = zr.is;
          String u = params.getSbmlUrl() + "/" + zr.name;
          inputStreams.put(u, is);
        }
        //ignore modelId when multiple models
        if (inputStreams.size() > 1) {
          modelId = null;
        }
      } else {
        inputStreams.put(params.getSbmlUrl(), new FileInputStream(localPath));
      }

      IntegrationReport jsonResult = new IntegrationReport();

      //      Map<String, Map<String, Object>> jsonResult = new HashMap<> ();
      //      jsonResult.put("models", new HashMap<String, Object> ());
      //      jsonResult.put("all", new HashMap<String, Object> ());
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
      dbs.add(MetaboliteMajorLabel.MetaCyc);
      //      dbs.add(MetaboliteMajorLabel.)~

      for (MetaboliteMajorLabel db : dbs) {
        spiIntegrationAll.databases.add(db.toString());
      }

      boolean removeBoundary = params.getRemoveBoundary() == 1;

      for (String u : inputStreams.keySet()) {
        InputStream is = inputStreams.get(u);
        try {
          FBAModel fbaModel = importModel(is, result, modelId, u, runIntegration, biomass, spiIntegrationAll, jsonResult, !removeBoundary);

          if (fbaModel != null) {
            KBaseObject o = new KBaseObject();
            o.dataType = KBaseType.FBAModel;
            o.nameId = fbaModel.getId();
            o.o = fbaModel;
            if (result.modelName == null || result.modelName.isEmpty()) {
              result.modelName = fbaModel.getId();
            }

            try {
              KBaseId kid = KBaseIOUtils.saveData(fbaModel.getId(), KBaseType.FBAModel, fbaModel, workspace, wsClient);
              result.objects.add(new WorkspaceObject()
                  .withDescription(u)
                  .withRef(kid.toString()));
            } catch (Exception e) {
              result.message += "\nERROR: " + u + " " + e.getMessage();
            }
            //            if (model != null) {
            //              String fbaModelRef = KBaseIOUtils.saveDataSafe(modelId, 
            //                  KBaseType.FBAModel.value(), model, workspace, dfuClient);
            //              if (fbaModelRef != null) {

            //              } else {
            //                logger.warn("unable to save {}", modelId);
            //              }
            //            }
          }
          //          KBaseIOUtils.saveData(objects, workspace, dfuClient);
        } catch (Exception e) {
          result.message += "\nERROR: " + u + " " + e.getMessage();
        }
      }

      jsonResult.setSpeciesIntegrationSummary(spiIntegrationAll);

      String jsonData = KBaseIOUtils.toJson(jsonResult, true);
      logger.info("written {}", jsonData.length());

      OutputStream os = null;
      try {
        os = new FileOutputStream(KBaseConfig.REPORT_OUTPUT_FILE);
        IOUtils.write(jsonData, os);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(os);
      }
      
      for (String m : jsonResult.models.keySet()) {
        IntegrationReportResult irr = jsonResult.models.get(m);
        String vr = irr.validationData;
        System.out.println(vr);
        if (vr != null) {
          File jsFile = new File(KBaseConfig.REPORT_OUTPUT_PATH + "/" + vr);
          if (jsFile.exists()) {
            result.jsonDataFiles.add(new File(vr));
          } else {
            logger.warn("file not found: {}", jsFile.getAbsolutePath());
          }
        }
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
      KBaseBiodbContainer biodbContainer = new KBaseBiodbContainer(KBaseConfig.DATA_EXPORT_PATH);
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






}
