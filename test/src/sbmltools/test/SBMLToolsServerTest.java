package sbmltools.test;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ini4j.Ini;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assemblyutil.AssemblyUtilClient;
import assemblyutil.FastaAssemblyFile;
import assemblyutil.SaveAssemblyParams;
import fbatools.FbaToolsClient;
import fbatools.RunFluxBalanceAnalysisParams;
import kbasefba.FBAModel;
import kbasefba.ModelReaction;
import kbsolrutil.KBSolrUtilClient;
import kbsolrutil.SearchSolrParams;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseBiodbContainer;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseGeneIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseSbmlImporter;
import sbmltools.SBMLToolsServer;
import sbmltools.SbmlImporterParams;
import us.kbase.auth.AuthConfig;
import us.kbase.auth.AuthToken;
import us.kbase.auth.ConfigurableAuthService;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.workspace.CreateWorkspaceParams;
import us.kbase.workspace.ListAllTypesParams;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.ProvenanceAction;
import us.kbase.workspace.WorkspaceClient;
import us.kbase.workspace.WorkspaceIdentity;

public class SBMLToolsServerTest {
  
  private static final Logger logger = LoggerFactory.getLogger(SBMLToolsServerTest.class);
  
  private static AuthToken token = null;
  private static Map<String, String> config = null;
  private static WorkspaceClient wsClient = null;
  private static String wsName = null;
  private static SBMLToolsServer impl = null;
  private static Path scratch;
  private static URL callbackURL;

  private static String AUTH_SERVICE_URL = 
      "https://kbase.us/services/auth/api/legacy/KBase/Sessions/Login";
//  private static String AUTH_SERVICE_URL = 
//      "https://kbase.us/services/authorization/Sessions/Login";
  private static String AUTH_SERVICE_URL_ALLOW_I = "false";
  private static String KBASE_ENDPOINT = "https://appdev.kbase.us/services";
  private static String WORKSPACE_URL = KBASE_ENDPOINT + "/ws";
  //http://172.17.0.11:34767
//  private static String SDK_CALLBACK_URL = "http://172.17.0.9:48411";
  private static String SDK_CALLBACK_URL = "http://172.17.0.9:48411";
  
  
  public static void setupConfigAppdev(Map<String, String> config) {
    /* 
     * kbase-endpoint=[https://appdev.kbase.us/services], 
     * job-service-url=[https://appdev.kbase.us/services/userandjobstate/], 
     * workspace-url=[https://appdev.kbase.us/services/ws/], 
     * shock-url=[https://appdev.kbase.us/services/shock-api], 
     * handle-service-url=[https://appdev.kbase.us/services/handle_service], 
     * srv-wiz-url=[https://appdev.kbase.us/services/service_wizard], 
     * njsw-url=[https://appdev.kbase.us/services/njs_wrapper], 
     * auth-service-url=[https://appdev.kbase.us/services/auth/api/legacy/KBase/Sessions/Login], 
     * auth-service-url-allow-insecure=[false], 
     * scratch=[/kb/module/work/tmp]}
     */
    
    if (!config.containsKey("kbase-endpoint")) {
      config.put("kbase-endpoint", "https://appdev.kbase.us/services");
    }
    if (!config.containsKey("job-service-url")) {
      config.put("job-service-url", "https://appdev.kbase.us/services/userandjobstate/");
    }
    if (!config.containsKey("workspace-url")) {
      config.put("workspace-url", "https://appdev.kbase.us/services/ws/");
    }
    if (!config.containsKey("shock-url")) {
      config.put("shock-url", "https://appdev.kbase.us/services/shock-api");
    }
    if (!config.containsKey("auth-service-url")) {
      config.put("auth-service-url", "https://appdev.kbase.us/services/auth/api/legacy/KBase/Sessions/Login");
    }
  }
  
  public static void setupConfigProd(Map<String, String> config) {
    if (!config.containsKey("kbase-endpoint")) {
      config.put("kbase-endpoint", "https://kbase.us/services");
    }
    if (!config.containsKey("job-service-url")) {
      config.put("job-service-url", "https://kbase.us/services/userandjobstate/");
    }
    if (!config.containsKey("workspace-url")) {
      config.put("workspace-url", "https://kbase.us/services/ws/");
    }
    if (!config.containsKey("shock-url")) {
      config.put("shock-url", "https://kbase.us/services/shock-api");
    }
    if (!config.containsKey("auth-service-url")) {
      config.put("auth-service-url", "https://kbase.us/services/auth/api/legacy/KBase/Sessions/Login");
    }
  }
  
  @BeforeClass
  public static void init() throws Exception {
    
    System.out.println("MY TOKEN!: " + System.getenv("KB_AUTH_TOKEN"));
    // Config loading
    String configFilePath = System.getenv("KB_DEPLOYMENT_CONFIG");
//    configFilePath = "/home/fliu/workspace/java/kbase-SBMLTools-auth2/deploy.cfg";
    if (configFilePath != null) {
      File deploy = new File(configFilePath);
      Ini ini = new Ini(deploy);
      config = ini.get("SBMLTools");
      WORKSPACE_URL = config.get("workspace-url");
      AUTH_SERVICE_URL = config.get("auth-service-url");
      AUTH_SERVICE_URL_ALLOW_I = config.get("auth-service-url-allow-insecure");
      scratch = Paths.get(config.get("scratch"));
    } else {
      
      config = new HashMap<>();
      setupConfigAppdev(config);
//      setupConfigProd(config);
    }
    
    config.put("scratch", "/tmp/trash");
    scratch = new File("/tmp/trash").toPath();
    System.out.println(config);
//    SDK_CALLBACK_URL = System.getenv("SDK_CALLBACK_URL");

    // Token validation
    String authUrl = config.get("auth-service-url");
    String authUrlInsecure = AUTH_SERVICE_URL_ALLOW_I; //config.get("auth-service-url-allow-insecure");
    ConfigurableAuthService authService = new ConfigurableAuthService(
        new AuthConfig().withKBaseAuthServerURL(new URL(authUrl))
        .withAllowInsecureURLs("true".equals(authUrlInsecure)));
    
//    AuthUser user = authService.login("filipeliu", "+");
    token = authService.validateToken("Y72TU4D34RICQI3MFVU3SXZ7HZQRVNDC");

//    Map<String, UserDetail> r = authService.fetchUserDetail(Arrays.asList(new String[]{"filipeliu"}));
//    System.out.println(r);
    
    
//    token = user.getToken();//authService.validateToken(System.getenv("KB_AUTH_TOKEN"));
    System.out.println(token);
    // Reading URLs from config
    wsClient = new WorkspaceClient(new URL(config.get("workspace-url")), token);
    wsClient.setIsInsecureHttpConnectionAllowed(false); // do we need this?
    callbackURL = new URL(SDK_CALLBACK_URL);
    
    // These lines are necessary because we don't want to start linux syslog bridge service
    JsonServerSyslog.setStaticUseSyslog(false);
    JsonServerSyslog.setStaticMlogFile("/tmp/test.log");

    System.out.println(SDK_CALLBACK_URL);
    
    impl = new SBMLToolsServer();
  }

  private static String getWsName() throws Exception {
    if (wsName == null) {
      long suffix = System.currentTimeMillis();
      wsName = "test_SBMLTools_" + suffix;
      wsClient.createWorkspace(new CreateWorkspaceParams().withWorkspace(wsName));
    }
    return wsName;
  }

  private static RpcContext getContext() {
    return new RpcContext().withProvenance(Arrays.asList(new ProvenanceAction()
        .withService("SBMLTools").withMethod("please_never_use_it_in_production")
        .withMethodParams(new ArrayList<UObject>())));
  }

  @AfterClass
  public static void cleanup() {
    if (wsName != null) {
      try {
        wsClient.deleteWorkspace(new WorkspaceIdentity().withWorkspace(wsName));
        System.out.println("Test workspace was deleted");
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  private String loadFASTA(
      final Path filename,
      final String objectName,
      final String filecontents)
          throws Exception {
    Files.write(filename, filecontents.getBytes(StandardCharsets.UTF_8));
    final AssemblyUtilClient assyUtil = new AssemblyUtilClient(callbackURL, token);
    /* since the callback server is plain http need this line.
     * CBS is on the same machine as the docker container
     */
    assyUtil.setIsInsecureHttpConnectionAllowed(true);
    return assyUtil.saveAssemblyFromFasta(new SaveAssemblyParams()
        .withAssemblyName(objectName)
        .withWorkspaceName(getWsName())
        .withFile(new FastaAssemblyFile().withPath(filename.toString())));

  }

  @Test
  public void testFilterContigsOk() throws Exception {
    // First load a test FASTA file as an KBase Assembly
    System.out.println("test");
    String url = "http://darwin.di.uminho.pt/fliu/kbase/kbase_published_models.zip";
//    url = "http://193.137.11.210/models/biomodels/sbml/msb201165-sup-0003.xml";
    SbmlImporterParams params = new SbmlImporterParams()
        .withWorkspaceName(getWsName())
        .withAutomaticallyIntegrate(1L)
        .withBiomass(Arrays.asList(new String[]{"bio1"}))
        .withSbmlUrl(url);
    
    KBaseConfig.DATA_EXPORT_PATH = "/var/biobase/export";;
    KBaseConfig.CURATION_DATA = "/var/biobase/integration/cc/cpd_curation.tsv";
    KBaseSbmlImporter.LOCAL_CACHE = "/tmp/argonne/data";
    KBaseConfig.REPORT_OUTPUT_FILE = "/opt/nginx-1.9.6/html/biosynth-web-biobase/exports/model-integration-report/readerData.json";
    impl.sbmlImporter(params, token, getContext());
    
    System.out.println(getWsName());

    /*
        Assert.assertEquals(3L, (long)ret.getNInitialContigs());
        Assert.assertEquals(1L, (long)ret.getNContigsRemoved());
        Assert.assertEquals(2L, (long)ret.getNContigsRemaining());
     */
  }
  
  @Test
  public void test_workspace() throws Exception {
    System.out.println(wsClient.listAllTypes(new ListAllTypesParams().withWithEmptyModules(0L)));
    //KBaseCollections/FBAModel
    List<String> workspaces = new ArrayList<> ();
    workspaces.add("filipeliu:narrative_1492697501369");
    List<Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>>> o = 
        wsClient.listObjects(
            new ListObjectsParams().withType("KBaseFBA.FBAModel").withWorkspaces(workspaces));
    for (Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>> t : o) {
      System.out.println(t);
    }
  }

  @Test
  public void test_get_model_and_integrate() throws Exception {
    /*
     * IntegrateModelParams [
     * modelName=iJO1366, 
     * workspaceName=filipeliu:narrative_1492697501369, 
     * outputModelName=iJO1366, 
     * templateId=gramneg, 
     * genomeId=Shewanella_oneidensis_MR-1, 
     * compartmentTranslation=[
     * CompartmentMapping [kbaseCompartmentId=c, modelCompartmentId=[z0], additionalProperties={}], 
     * CompartmentMapping [kbaseCompartmentId=e, modelCompartmentId=[z1], additionalProperties={}]], 
     * biomassReactions=R_12DGR120tipp,R_12DGR140tipp, 
     * compoundMappings=null, 
     * geneMappings=null, createExtracellular=0, additionalProperties={}]
     */
//    KBaseIOUtils.getFBAModel("iJO1366", "filipeliu:narrative_1492697501369", null, wsClient);
    
    FBAModel fbaModel = KBaseIOUtils.getObject("Ec_core_flux1", "filipeliu:narrative_1492697501369", null, FBAModel.class, wsClient);
    
//    Object o = KBaseIOUtils.getObject("Shewanella_oneidensis_MR-1", "filipeliu:narrative_1492697501369", null, wsClient);
//    System.out.println(fbaModel.getId());
//    Map<String, ?> om = (HashMap) o;
//    System.out.println(om.get("id"));
//    System.out.println(om.keySet());
//    for (String k : om.keySet()) {
//      Object pp = om.get(k);
//      if (pp instanceof String) {
//        System.out.println(k + "\t" + pp);
//      }
//    }
    
    KBaseIntegration integration = new KBaseIntegration(fbaModel);
    integration.biodbContainer = new KBaseBiodbContainer("/var/biobase/export");
    integration.compartmentMapping.put("z0", "c");
    integration.compartmentMapping.put("z1", "e");
    integration.compartmentMapping.put("z2", "p");
    integration.rename = "ModelSeed";
    integration.fillMetadata = true;
    integration.integrate();
    
    System.out.println(KBaseIOUtils.toJson(integration.fbaModel));
    
    
//    System.out.println(KBaseIOUtils.toJson(o));
  }

  @Test
  public void test_filter_contigs_err2() throws Exception {
    /*
     * "fbamodel_id": "kb.iJO1366",
        "media_id": "Sulfate-L-Arabitol",
        "target_reaction": ["bio1"],
        "fba_output_id": "kb.iJO1366.fba",
        "fva": 1,
        "minimize_flux": 1,
        "simulate_ko": 0,
        "feature_ko_list": [],
        "reaction_ko_list": [""],
        "custom_bound_list": [],
        "media_supplement_list": [],
        "expseries_id": None,
        "expression_condition": [""],
        "exp_threshold_percentile": 0.5,
        "exp_threshold_margin": 0.1,
        "activation_coefficient": 0.5,
        "max_c_uptake": None,
        "max_n_uptake": None,
        "max_p_uptake": None,
        "max_s_uptake": None,
        "max_o_uptake": None
     */
    System.out.println(callbackURL);
    FbaToolsClient fbaClient = new FbaToolsClient(callbackURL, token);
    RunFluxBalanceAnalysisParams params = new RunFluxBalanceAnalysisParams()
        .withFbamodelId("iJO1366")
        .withMediaId("Sulfate-L-Arabitol")
        .withTargetReaction("bio1")
        .withFbaOutputId("iJO1366.fba")
        .withFva(1L)
        .withMinimizeFlux(1L)
        .withSimulateKo(0L)
        .withFeatureKoList(new ArrayList<String> ())
        .withReactionKoList(new ArrayList<String> ())
        .withCustomBoundList(new ArrayList<String> ())
        .withMediaSupplementList(null)
        .withExpseriesId(null)
        .withExpThresholdPercentile(0.6)
        .withExpThresholdMargin(0.1)
        .withActivationCoefficient(0.5);
    fbaClient.runFluxBalanceAnalysis(params);
  }

  @Test
  public void test_filter_contigs_err3() throws Exception {
    /*
     *      "search_core": "GenomeFeatures_prod",
        "search_query": "{\"q\":\"ECW_m3682\"}",
        "search_param": "{\"fl\":\"*\",\"start\":0,\"rows\":10}",
        "result_format": "json",
        "group_option": ""
     */
//    Map<String, String> searchQuery = new HashMap<> ();
//    searchQuery.put("q", "ECW_m3682");
//    Map<String, String> searchParam = new HashMap<> ();
//    searchParam.put("fl", "*");
//    searchParam.put("start", "0");
//    searchParam.put("rows", "10");
//    KBSolrUtilClient solrClient = new KBSolrUtilClient(callbackURL, token);
//    SearchSolrParams params = new SearchSolrParams()
//        .withSearchCore("GenomeFeatures_prod")
//        .withResultFormat("json")
//        .withSearchQuery(searchQuery)
//        .withSearchParam(searchParam)
//        .withGroupOption("");
//    
//    solrClient.searchKbaseSolr(params);
//    IntegrateModelParams [modelName=iJO1366_bigg2, workspaceName=filipeliu:narrative_1502474753893, outputModelName=test, templateId=gramneg, genomeId=GCF_000022605.2_ASM2260v1_genomic, compartmentTranslation=[], biomassReactions=, compoundMappings=null, geneMappings=null, createExtracellular=0, additionalProperties={}]
    FBAModel fbaModel = KBaseIOUtils.getObject("iJO1366_bigg2", "filipeliu:narrative_1502474753893", null, FBAModel.class, wsClient);
    KBaseGeneIntegration geneIntegration = new KBaseGeneIntegration(null, null, null);
    geneIntegration.searchGenome(fbaModel);
  }
}
