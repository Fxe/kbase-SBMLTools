package sbmltools.test;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;

import junit.framework.Assert;

import org.ini4j.Ini;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import us.kbase.common.service.ServerException;
import assemblyutil.AssemblyUtilClient;
import assemblyutil.FastaAssemblyFile;
import assemblyutil.SaveAssemblyParams;
import sbmltools.FilterContigsParams;
import sbmltools.FilterContigsResults;
import sbmltools.SBMLToolsServer;
import sbmltools.SbmlImportParams;
import us.kbase.auth.AuthConfig;
import us.kbase.auth.AuthToken;
import us.kbase.auth.AuthUser;
import us.kbase.auth.ConfigurableAuthService;
import us.kbase.auth.UserDetail;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.UObject;
import us.kbase.workspace.CreateWorkspaceParams;
import us.kbase.workspace.ProvenanceAction;
import us.kbase.workspace.WorkspaceClient;
import us.kbase.workspace.WorkspaceIdentity;

public class SBMLToolsServerTest {
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
  private static String SDK_CALLBACK_URL = KBASE_ENDPOINT;
  
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
    }
    

    SDK_CALLBACK_URL = System.getenv("SDK_CALLBACK_URL");

    // Token validation
    String authUrl = AUTH_SERVICE_URL ; //config.get("auth-service-url");
    String authUrlInsecure = AUTH_SERVICE_URL_ALLOW_I; //config.get("auth-service-url-allow-insecure");
    ConfigurableAuthService authService = new ConfigurableAuthService(
        new AuthConfig().withKBaseAuthServerURL(new URL(authUrl))
        .withAllowInsecureURLs("true".equals(authUrlInsecure)));
    
//    AuthUser user = authService.login("filipeliu", "+");
    token = authService.validateToken("HGPTQEG474SETX5KEGWOT4RDLGSSCLIN");
//    Map<String, UserDetail> r = authService.fetchUserDetail(Arrays.asList(new String[]{"filipeliu"}));
//    System.out.println(r);
    
    
//    token = user.getToken();//authService.validateToken(System.getenv("KB_AUTH_TOKEN"));
    System.out.println(token);
    // Reading URLs from config
    wsClient = new WorkspaceClient(new URL(WORKSPACE_URL), token);
    wsClient.setIsInsecureHttpConnectionAllowed(false); // do we need this?
    callbackURL = new URL(SDK_CALLBACK_URL);
    
    // These lines are necessary because we don't want to start linux syslog bridge service
    JsonServerSyslog.setStaticUseSyslog(false);
    JsonServerSyslog.setStaticMlogFile("/tmp/test.log");
//    JsonServerSyslog.setStaticMlogFile(new File(config.get("scratch"), "test.log")
//        .getAbsolutePath());
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
    final String fastaContent = ">seq1 something something asdf\n" +
        "agcttttcat\n" +
        ">seq2\n" +
        "agctt\n" +
        ">seq3\n" +
        "agcttttcatgg";
    SbmlImportParams params = new SbmlImportParams()
        .withWorkspaceName(getWsName())
        .withMinLength(100000L);
    System.out.println(getWsName());
//    impl.filterContigs(params, token, getContext());
    /*
        final String ref = loadFASTA(scratch.resolve("test1.fasta"), "TestAssembly", fastaContent);

        // second, call the implementation
        final FilterContigsResults ret = impl.filterContigs(new FilterContigsParams()
                .withWorkspaceName(getWsName())
                .withAssemblyInputRef(ref)
                .withMinLength(10L),
                token, getContext());

        // validate the returned data
        Assert.assertEquals(3L, (long)ret.getNInitialContigs());
        Assert.assertEquals(1L, (long)ret.getNContigsRemoved());
        Assert.assertEquals(2L, (long)ret.getNContigsRemaining());
     */
  }

  @Test
  public void test_filter_contigs_err1() throws Exception {
    /*
        try {
            impl.filterContigs(new FilterContigsParams().withWorkspaceName(getWsName())
                .withAssemblyInputRef("fake/fake/1"), token, getContext());
            Assert.fail("Error is expected above");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("Parameter min_length is not set in input arguments",
                    ex.getMessage());
        }
     */
  }

  @Test
  public void test_filter_contigs_err2() throws Exception {
    /*
        try {
            impl.filterContigs(new FilterContigsParams().withWorkspaceName(getWsName())
                .withAssemblyInputRef("fake/fake/1").withMinLength(-10L), token, getContext());
            Assert.fail("Error is expected above");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("min_length parameter cannot be negative (-10)", ex.getMessage());
        }
     */
  }

  @Test
  public void test_filter_contigs_err3() throws Exception {
    /*
        try {
            impl.filterContigs(new FilterContigsParams().withWorkspaceName(getWsName())
                .withAssemblyInputRef("fake").withMinLength(10L), token, getContext());
            Assert.fail("Error is expected above");
        } catch (ServerException ex) {
            Assert.assertEquals("Invalid workspace reference string! Found fake", ex.getMessage());
        }
     */
  }
}
