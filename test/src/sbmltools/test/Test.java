package sbmltools.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import genomeannotationapi.SaveGenomeResultV1;
import genomeannotationapi.SaveOneGenomeParamsV1;
import kbasefba.FBAModel;
import kbasefba.ModelCompound;
import kbasefba.ModelReaction;
import kbasegenomes.Genome;
import kbsolrutil.KBaseAPI;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseBiodbContainer;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseGeneIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseId;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIntegrationReport;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelIntegrationFacade;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseUtils;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter.ImportModelResult;
import pt.uminho.sysbio.biosynthframework.report.IntegrationByDatabase;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReport;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReportResult;
import pt.uminho.sysbio.biosynthframework.util.ZipContainer;
import pt.uminho.sysbio.biosynthframework.util.ZipContainer.ZipRecord;
import sbmltools.KBaseType;
import us.kbase.common.service.UnauthorizedException;

public class Test {
  
  private static final Logger logger = LoggerFactory.getLogger(Test.class);
  
  public static Map<String, InputStream> zip(String urlPath) throws IOException {
    ZipContainer container = null;
    Map<String, InputStream> inputStreams = new HashMap<> ();
    if (urlPath.endsWith(".zip")) {
      container = new ZipContainer(urlPath);
      List<ZipRecord> streams = container.getInputStreams();
      for (ZipRecord zr : streams) {
        InputStream is = zr.is;
        String u = urlPath + "/" + zr.name;
        inputStreams.put(u, is);
      }
      //ignore modelId when multiple models
      if (inputStreams.size() > 1) {
//        modelId = null;
      }
    } else {
      inputStreams.put(urlPath, new FileInputStream(urlPath));
    }
    
    return inputStreams;
  }
  
  public static void test() {
    boolean allowBoundary = true;
    boolean runIntegration = true;
    Set<String> biomassIds = new HashSet<> ();
    ImportModelResult result = new ImportModelResult();
    String modelId = null; //null if streams > 1
    
//    String sbmlPath = "/var/biomodels/joana_bigg_old/iJO1366_bigg2.xml";
//    sbmlPath = "/var/biomodels/joana_bigg_old/iSB619_bigg2.xml";
//    sbmlPath = "/var/biomodels/2batch/iJB785.xml";
//    sbmlPath = "/tmp/joana_model/test_models.zip";
////    sbmlPath = "http://193.137.11.210/models/biomodels/sbml/msb201165-sup-0003.xml";
//    sbmlPath = "http://193.137.11.210/models/biomodels/test_models.zip";
////    sbmlPath = "/var/biomodels/sbml/Ec_core_flux1.xml";
//    sbmlPath = "http://darwin.di.uminho.pt/fliu/kbase/kbase_published_models.zip";
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
    for (MetaboliteMajorLabel db : dbs) {
      spiIntegrationAll.databases.add(db.toString());
    }
    
    IntegrationReport jsonResult = new IntegrationReport();
    
    try {

      KBaseSbmlImporter importer = new KBaseSbmlImporter(null, null, null);
//      kbase_published_models
      Map<String, InputStream> z = zip("/var/argonne/importer_test_input/test.zip");
//      Map<String, InputStream> z = zip("/var/argonne/kbase_published_models.zip");
      Map<String, String> read = new HashMap<> ();
      for (String k : z.keySet()) {
        InputStream is = z.get(k);

          String url = k;
//          is = new FileInputStream("/var/argonne/importer_test_input/fix.xml");
          FBAModel fbaModel = importer.importModel(is, 
                                                   result, 
                                                   modelId, 
                                                   url, 
                                                   runIntegration, 
                                                   biomassIds, null,
                                                   spiIntegrationAll, 
                                                   jsonResult, 
                                                   allowBoundary);
          if (fbaModel != null) {
            KBaseAPI prodAPI = new KBaseAPI(IntegrationLocalRun.LOGIN_TOKEN, KBaseAPI.getConfigDev(), true);
            KBaseId kid = prodAPI.saveObject(fbaModel.getId(), IntegrationLocalRun.DEV_JUNK_REPO, KBaseType.FBAModel, fbaModel);
            read.put(k, kid.toString());
          }
      }
      
      System.out.println(read);
      
      jsonResult.setSpeciesIntegrationSummary(spiIntegrationAll);
      
      String jsonData = KBaseIOUtils.toJson(jsonResult, true);
      
      KBaseIOUtils.writeStringFile(jsonData, KBaseConfig.REPORT_OUTPUT_FILE);
      
      logger.info("written {}", jsonData.length());
      
    } catch (UnauthorizedException | IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void runIntegration(
      FBAModel fbaModel, 
      Set<String> biomassReactions, 
      Map<String, String> compartmentMapping, 
      String translate,
      boolean fillMetadata,
      String mediaOuput,
      KBaseBiodbContainer biodbContainer,
      KBaseAPI api) {
    KBaseIntegrationReport kir = new KBaseIntegrationReport();
    kir.model = fbaModel.getId();
    kir.objName = fbaModel.getName();
    
    Genome genome = null;
    
    KBaseIntegration integration = new KBaseIntegration(fbaModel);
    integration.report = kir;
    integration.biomassSet.addAll(biomassReactions);
    integration.compartmentMapping = compartmentMapping;
    integration.rename = translate;
    integration.fillMetadata = fillMetadata;
    integration.mediaName = mediaOuput;
    integration.biodbContainer = biodbContainer;
    
    integration.integrate();
    
    KBaseGeneIntegration geneIntegration = new KBaseGeneIntegration(
        api.getWorkspaceClient(), api.dfuClient, api.solrClient);
    
    kir.genomeReport.status = "auto_genome_get_fail";
    if (genome == null) {
      logger.info("auto detect genome...");
      String geneData = "";
      if (geneIntegration != null) {
        geneData = geneIntegration.searchGenome(fbaModel, false);
        System.out.println(geneData);
        kir.fillGenomeData(geneIntegration.report);
        if (geneIntegration.report != null && 
            geneIntegration.report.bestGenomeKID != null && 
            geneIntegration.report.bestGenomeKID.size() >= 1) {
          KBaseId matchGenome = geneIntegration.report.bestGenomeKID.iterator().next();
          integration.genomeRef = matchGenome.reference;
          try {
            Pair<KBaseId, Object> kdata = KBaseIOUtils.getObject2(matchGenome.name, matchGenome.workspace, null, api.getWorkspaceClient());
            genome = KBaseUtils.convert(kdata.getRight(), Genome.class);
//            SaveOneGenomeParamsV1 gparams = new SaveOneGenomeParamsV1()
//                .withData(genome)
//                .withWorkspace(workspaceName)
//                .withName(matchGenome.name);
//            SaveGenomeResultV1 gresults = gaClient.saveOneGenomeV1(gparams);
//            String ref = KBaseIOUtils.getRefFromObjectInfo(gresults.getInfo());
//            outputObjects.put(ref, "detected genome");
            integration.genome = genome;
//            integration.genomeRef = ref;
            kir.genomeReport.status = "auto";
          } catch (Exception e) {
            kir.genomeReport.status = "auto_genome_get_fail";
          }
          integration.integrateGprGenes(true);
        }
      }
    } else {
      kir.genomeReport.status = "user";
    }
    
    kir.fillModelData(integration.adapter);
    KBaseIOUtils.writeStringFile(KBaseIOUtils.toJson(kir), "/opt/nginx-1.9.6/html/biosynth-web-biobase/exports/model-report/data.json");
    
  }
  
  public static void test2() {
    
    try {
      KBaseAPI prodAPI = new KBaseAPI(IntegrationLocalRun.LOGIN_TOKEN, KBaseAPI.getConfigDev(), true);
      KBaseAPI prodAPI2 = new KBaseAPI(IntegrationLocalRun.LOGIN_TOKEN, KBaseAPI.getConfigProd(), true);
      FBAModel kmodel = KBaseIOUtils.getObject("ok", IntegrationLocalRun.DEV_JUNK_REPO, null, FBAModel.class, prodAPI.wsClient);
      
      for (ModelCompound mc : kmodel.getModelcompounds()) {
        System.out.println(mc.getId() + " " + mc.getStringAttributes());
      }
      for (ModelReaction mr : kmodel.getModelreactions()) {
        System.out.println(mr.getId() + " " + mr.getStringAttributes());
      }
      
      System.out.println(kmodel.getId());
      System.out.println(kmodel.getSource());
      System.out.println(kmodel.getName());
      System.out.println(kmodel.getSourceId());
      System.out.println(kmodel.getType());
//      
////      KBaseModelIntegrationFacade facade = new KBaseModelIntegrationFacade(
////          prodAPI2.getWorkspaceClient(), prodAPI2.dfuClient, null, null, null, KBaseConfig.DATA_EXPORT_PATH, null);
////      
////      facade.kbaseIntegrate(null, null);
      Set<String> biomassReactions = new HashSet<> ();
      Map<String, String> compartmentMapping = new HashMap<> ();
      compartmentMapping.put("z0", "e");
      compartmentMapping.put("z1", "c");
      KBaseBiodbContainer biodbContainer = new KBaseBiodbContainer(KBaseConfig.DATA_EXPORT_PATH);
      runIntegration(kmodel, biomassReactions, compartmentMapping, "KEGG", true, "media", biodbContainer, prodAPI2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    KBaseConfig.DATA_EXPORT_PATH = "/var/biobase/export";
    KBaseConfig.CURATION_DATA = "/var/biobase/integration/cc/cpd_curation.tsv";
    KBaseConfig.REPORT_OUTPUT_FILE = "/opt/nginx-1.9.6/html/biosynth-web-biobase/exports/model-integration-report/readerData.json";
    KBaseConfig.REPORT_OUTPUT_PATH = "/opt/nginx-1.9.6/html/biosynth-web-biobase/exports/model-integration-report/";
    
//    test();
    test2();
  }
}
