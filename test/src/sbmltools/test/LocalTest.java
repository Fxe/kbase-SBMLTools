package sbmltools.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;

import kbasefba.FBAModel;
import kbasegenomes.Feature;
import kbasegenomes.Genome;
import kbasereport.WorkspaceObject;
import kbsolrutil.KBaseAPI;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.ReactionMajorLabel;
import pt.uminho.sysbio.biosynthframework.integration.model.CompartmentIntegration;
import pt.uminho.sysbio.biosynthframework.integration.model.IntegrationMap;
import pt.uminho.sysbio.biosynthframework.kbase.FBAModelFactory;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseBiodbContainer;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseGeneIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseGenomeAdapter;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseId;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIntegrationReport;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelIntegrationFacade;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelSeedIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelSeedIntegration.KBaseMappingResult;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseUtils;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter.ImportModelResult;
import pt.uminho.sysbio.biosynthframework.kbase.report.ReportFBAModel;
import pt.uminho.sysbio.biosynthframework.sbml.MessageCategory;
import pt.uminho.sysbio.biosynthframework.sbml.MessageType;
import pt.uminho.sysbio.biosynthframework.sbml.XmlMessage;
import pt.uminho.sysbio.biosynthframework.sbml.XmlMessageGroup;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelAutofix;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelValidator;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;
import sbmltools.IntegrateModelParams;
import sbmltools.KBaseType;
import sbmltools.SbmlImportParams;
import sbmltools.SbmlImporterParams;
import us.kbase.common.service.UnauthorizedException;

public class LocalTest {
  
  private static final Logger logger = LoggerFactory.getLogger(LocalTest.class);
  
  public static List<XmlMessage> validate(XmlSbmlModel xmodel) {
    XmlSbmlModelValidator validator = new XmlSbmlModelValidator(xmodel);
    XmlSbmlModelValidator.initializeDefaults(validator);
    return validator.validate();
  }
  
  public static List<XmlMessage> autoFix(XmlSbmlModel xmodel, List<XmlMessage> msgs) {
    XmlSbmlModelAutofix autofix = new XmlSbmlModelAutofix();
    autofix.group.put(MessageCategory.STOICH_NO_VALUE, 
        new XmlMessageGroup(MessageCategory.STOICH_NO_VALUE, MessageType.WARN, "assume value 1"));
    autofix.fix(xmodel, msgs);
    
    return autofix.messages;
  }
  
  public static void test(String path) throws IOException {
    FileInputStream fis = new FileInputStream(path);
    test(fis);
    fis.close();
  }
  
  public static void test(InputStream is) throws IOException {
    XmlStreamSbmlReader reader = new XmlStreamSbmlReader(is);
    XmlSbmlModel xmodel = reader.parse();
    
    CompartmentIntegration cintegration = 
        new CompartmentIntegration();
    cintegration.generateCompartmentMapping(xmodel);
    
    List<XmlMessage> vmsg = new ArrayList<> ();
    
    //VALIDATION 1
    List<XmlMessage> msgs1 = validate(xmodel);
    
    List<XmlMessage> msgsf = autoFix(xmodel, msgs1);
    vmsg.addAll(msgsf);
    
    //VALIDATION 2
    List<XmlMessage> msgs2 = validate(xmodel);
    vmsg.addAll(msgs2);
    
    System.out.println(vmsg);
    
    KBaseBiodbContainer biodbContainer = new KBaseBiodbContainer(KBaseConfig.DATA_EXPORT_PATH);
    KBaseModelSeedIntegration integration = new KBaseModelSeedIntegration(
        KBaseConfig.DATA_EXPORT_PATH, KBaseConfig.CURATION_DATA, biodbContainer);
    KBaseMappingResult mapping = integration.generateDatabaseReferences(xmodel, "test", null);
    IntegrationMap<String, String> sintegration = new IntegrationMap<>();
    IntegrationMap<String, String> rintegration = new IntegrationMap<>();
    
    Map<String, Map<MetaboliteMajorLabel, String>> imap = mapping.species;
    Map<String, Map<ReactionMajorLabel, String>> rimap = mapping.reactions;
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

    String genomeRef = "";
    String modelId = "test";
    Collection<String> biomassIds = new HashSet<>();
    Map<String, String> spiToModelSeedReference = KBaseModelSeedIntegration.filter(imap, MetaboliteMajorLabel.ModelSeed);
    Map<String, String> rxnToModelSeedReference = KBaseModelSeedIntegration.filter(rimap, ReactionMajorLabel.ModelSeedReaction);
    FBAModel model = new FBAModelFactory()
        .withGenomeRef(genomeRef)
        .withSpecieIntegration(sintegration)
        .withReactionIntegration(rintegration)
        .withBiomassIds(biomassIds)
        .withMetaboliteModelSeedReference(spiToModelSeedReference)
        .withReactionModelSeedReference(rxnToModelSeedReference)
        .withModelId(modelId)
        .withModelName(modelId)
        .withXmlSbmlModel(xmodel, true)
        .build();
    

    try {
      ReportFBAModel report = new ReportFBAModel();
      FileOutputStream fos = new FileOutputStream(
          "/opt/nginx-1.9.6/html/biosynth-web-biobase/exports/model-report/fbamodel.json");
      String data = KBaseIOUtils.toJson(report.report(model), false);
      fos.write(data.getBytes());
      fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
//    Map<String, String> spiToModelSeedReference = integration.spiToModelSeedReference;
    
//    FBAModel fbaModel = new FBAModelFactory()
////        .withModelSeedReference(spiToModelSeedReference)
//        .withXmlSbmlModel(xmodel, false)
//        .build();
//    System.out.println(fbaModel.getModelcompartments());

//    ObjectMapper om = new ObjectMapper();
//    om.enable(SerializationFeature.INDENT_OUTPUT);
//    System.out.println(om.writeValueAsString(fbaModel));
//    System.out.println("model" + fbaModel);
  }
  
  public static void test1() {


    String sbmlPath = "/var/biomodels/joana_bigg_old/iJO1366_bigg2.xml";
    sbmlPath = "/var/biomodels/joana_bigg_old/iSB619_bigg2.xml";
    sbmlPath = "/var/biomodels/2batch/iJB785.xml";
    sbmlPath = "/tmp/joana_model/test_models.zip";
//    sbmlPath = "http://193.137.11.210/models/biomodels/sbml/msb201165-sup-0003.xml";
    sbmlPath = "http://193.137.11.210/models/biomodels/test_models.zip";
//    sbmlPath = "/var/biomodels/sbml/Ec_core_flux1.xml";
    sbmlPath = "http://darwin.di.uminho.pt/fliu/kbase/kbase_published_models.zip";
    sbmlPath = "/var/biomodels/sbml/Ec_core_flux1.xml";
    
    String urlPath = sbmlPath;
    InputStream is = null; //file input stream
    ZipInputStream zis = null; //zip file manipulator
    ZipFile zf = null; //zip file pointer
    InputStream rfis = null; //file within zip file pointer
    String local = KBaseIOUtils.fetchAndCache(sbmlPath, KBaseSbmlImporter.LOCAL_CACHE);
    
    
    if (urlPath.endsWith(".zip") && local != null) {
      try {
//        URL url = new URL(urlPath);
//        URI uri = new URI(urlPath);
//        URLConnection connection = url.openConnection();
//        is = connection.getInputStream();
        is = new FileInputStream(local);
//        File zipFile = new File(uri);
//        FileUtils.copyURLToFile(url, zipFile);
        zis = new ZipInputStream(is);
        zf = new ZipFile(local);
        
        ZipEntry ze = null;
//        zf = new 
        while ((ze = zis.getNextEntry()) != null) {
          Map<String, Object> record = new HashMap<> ();
          record.put("name", ze.getName());
          record.put("size", ze.getSize());
          record.put("compressed_size", ze.getCompressedSize());
          record.put("method", ze.getMethod());
          System.out.println(record);
          try {
            rfis = zf.getInputStream(ze);
//            System.out.println(IOUtils.readLines(rfis).size());
            System.out.println(record.get("name"));
            test(rfis);
          } catch (Exception e) {
            e.printStackTrace();
          } finally {
            IOUtils.closeQuietly(rfis);
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        rfis = new FileInputStream(urlPath);
        test(rfis);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(rfis);
      }
    }
    
//    try {
////      URL url = new URL("http://193.137.11.210/models/biomodels/sbml/msb201165-sup-0003.xml");
////      URLConnection connection = url.openConnection();
//
//      
//      
//      
//
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
    
  }
  
  
  public static void integrationTest2() {
    
    KBaseConfig.setupLocalPaths();
    try (InputStream modelJson = new FileInputStream("/tmp/argonne/data/885ba65f-0526-47ed-8e9e-896d7044733f/202.json")) {
      KBaseAPI api =  new KBaseAPI("AIQEX4L6EU4WCWWHX7Q7VNPKJILYCZYO", KBaseAPI.getConfigDev(), true);
      FBAModel kmodel = KBaseIOUtils.getObject(Joiner.on('\n').join(IOUtils.readLines(modelJson)), FBAModel.class);
      
      KBaseIntegrationReport kir = new KBaseIntegrationReport();
      kir.model = kmodel.getId();
      kir.objName = kmodel.getName();
      
      KBaseBiodbContainer biodbContainer = new KBaseBiodbContainer(KBaseConfig.DATA_EXPORT_PATH);
      KBaseIntegration integration = new KBaseIntegration(kmodel);
      integration.report = kir;
//      integration.biomassSet.addAll(biomassReactions);
//      integration.compartmentMapping = compartmentMapping;
      integration.rename = "modelseed";
      integration.fillMetadata = true;
      integration.mediaName = "media";
      integration.biodbContainer = biodbContainer;
//      integration.gprOverride = gprOverride;
//      integration.cpdOverride = cpdOverride;
      integration.allowNumberId = false;
      integration.fixIdToKBase = true;
      integration.integrate();
      
      KBaseId mediaKid = null;
      if (integration.defaultMedia != null) {
        api.saveObject("test_media", "filipeliu:narrative_1505405117321", KBaseType.KBaseBiochemMedia, integration.defaultMedia);
//        mediaKid = KBaseIOUtils.saveData(params.getOutputMediaName(), KBaseType.KBaseBiochemMedia.value(), integration.defaultMedia, workspaceName, wspClient);
//        outputObjects.put(mediaKid.reference, "detected media");
      }
      
//      KBaseId integratedModelKid = KBaseIOUtils.saveData(outputName, KBaseType.FBAModel.value(), fbaModel, workspaceName, wspClient);
//      outputObjects.put(integratedModelKid.reference, "integrated model");
//      String ref = KBaseIOUtils.saveDataSafe(outputName, KBaseType.FBAModel, fbaModel, workspaceName, dfuClient);
      
//      String uuString = UUID.randomUUID().toString();
      
      kir.fillModelData(integration.adapter);
      KBaseIOUtils.writeStringFile(KBaseIOUtils.toJson(kir), "/kb/module/data/data.json");
    } catch (IOException e) {
      e.printStackTrace();
    }
    
//    KBaseGeneIntegration geneIntegration = new KBaseGeneIntegration(null, null, null);
//    KBaseModelIntegrationFacade a = new KBaseModelIntegrationFacade(null, null, null, null, geneIntegration, "", null);
//    IntegrateModelParams params = new IntegrateModelParams();
//    try {
//      a.kbaseIntegrate(params, "");
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
  }
  
  public static void integrationTest() {
    KBaseConfig.setupLocalPaths();
    String sbmlPath = "http://193.137.11.210/models/biomodels/sbml/msb201165-sup-0003.xml";
    sbmlPath = "http://193.137.11.210/models/biomodels/test_models.zip";
    sbmlPath = "http://193.137.11.210/models/biomodels/test_models_with_invalid_sbml.zip";
//    sbmlPath = "http://193.137.11.210/models/biomodels/joana/iSH335.xml";
    sbmlPath = "http://193.137.11.210/models/biomodels/sbml/Ec_core_flux1.xml";
    sbmlPath = "http://193.137.11.210/models/biomodels/iOD907_bqbiol.xml";
    sbmlPath = "http://bioseed.mcs.anl.gov/~jplfaria/models/yeast_7.6.xml";
    sbmlPath = "https://raw.githubusercontent.com/Fxe/biomodels/master/sbml/Ec_core_flux1.xml";
//    sbmlPath = "https://raw.githubusercontent.com/Fxe/biomodels/master/sbml/iJDZ836/iJDZ836.xml";
//    sbmlPath = "https://raw.githubusercontent.com/Fxe/biomodels/master/fungis/iNX804.xml";
//    sbmlPath = "https://raw.githubusercontent.com/Fxe/biomodels/master/fbc3/yeast_7.xml";
//    sbmlPath = "http://127.0.0.1/core.xml";
//    sbmlPath = "http://127.0.0.1/models/biomodels/sbml/hsa/MODEL1109130000.xml";
//    sbmlPath = "http://darwin.di.uminho.pt/fliu/kbase/kbase_published_models.zip";
    
    try {
      KBaseSbmlImporter sbmlTools = new KBaseSbmlImporter(null, null, null);
      List<String> biomass = new ArrayList<> ();
      biomass.add("R_R07230_B");
      biomass.add("R_R374");
      SbmlImporterParams params = new SbmlImporterParams()
          .withAutomaticallyIntegrate(1L)
          .withRemoveBoundary(1L)
          .withModelName(null)
          .withSbmlUrl(sbmlPath)
          .withBiomass(biomass);
      ImportModelResult r = sbmlTools.importModel(params);
      System.out.println("Message: " + r.message);
      System.out.println("Objects: " + r.objects);
      for (WorkspaceObject wso : r.objects) {
        System.out.println("Workspace Object: " + wso);
      }
    } catch (UnauthorizedException | IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void dataTest() {
    try {
      KBaseSbmlImporter sbmlTools = new KBaseSbmlImporter(null, null, null);
      SbmlImportParams params = new SbmlImportParams()
          .withAssemblyInputRef("111")
          .withMinLength(10L)
          .withUrl("url");
      ImportModelResult result = sbmlTools.debugThings(params);
      System.out.println(result.message);
    } catch (UnauthorizedException | IOException e) {
      e.printStackTrace();
    }
  }
  

  
  public static void main(String[] args) {
    integrationTest2();
    System.exit(0);
    try {
      //    fixModel26590126("/var/biomodels/sbml/26590126/tpj13081-sup-0003-TableS3_fix.xml");
      Genome genome = KBaseIOUtils.loadJsonGenomeFromZip("D:\\var\\biomodels\\sbml\\26590126/Phaeodactylum_tricornutum.ASM15095v2.38.gff3_genome.json.zip");
      KBaseGenomeAdapter adapter = new KBaseGenomeAdapter(genome);
      for (Feature f: genome.getFeatures()) {
        String featureId = f.getId();
//        List<String> aliases = generateAliases(featureId);
//        System.out.println(f.getId() + " " + f.getAliases() + " " + f.getFunction() + " " + aliases);
//        adapter.addUniqueAliases(f.getId(), aliases);
      }
      for (Feature f: genome.getFeatures()) {
//        System.out.println(f.getId() + " " + f.getAliases() + " " + f.getFunction());
      }
      KBaseIOUtils.writeStringFile(KBaseIOUtils.toJson(genome), 
          "/var/biomodels/sbml/26590126/Phaeodactylum_tricornutum_aliases.json");
      

      KBaseAPI api = new KBaseAPI("FFM4T3AU3XBWAD2UXYE5USIGJIWNYYNW", KBaseAPI.getConfigProd(), false);
//      api.saveGenome("Phaeodactylum_tricornutum_aliases", "filipeliu:1452618747692", genome);
////      
//      RpcContext rpc = null;
//      
//      SaveOneGenomeParamsV1 gparams = new SaveOneGenomeParamsV1()
//          .withData(genome)
//          .withWorkspace("filipeliu:1452618747692")
//          .withName("Phaeodactylum_tricornutum_aliases");
//          api.getGenomeClient().saveOneGenomeV1(gparams);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    KBaseConfig.setupLocalPaths();
    //integrationTest();
//    try {
//      test("/tmp/argonne/data/fba834db-9ed7-433f-86d3-874c4f28bb68/fba834db-9ed7-433f-86d3-874c4f28bb68_2");
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    dataTest();
//    test1();
    
//    System.out.println(KBaseUtils.getGenes("(a1 and a2 )"));
//    System.out.println(KBaseUtils.getGenes("(a1 a2 )"));
    
//    try {
//      //iMO1056.xml
//      //
//      test(new FileInputStream("/var/biomodels/sbml/Ec_core_flux1.xml"));
//      test(new FileInputStream("/var/biomodels/joana/iMO1056.xml"));
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
  }
}
