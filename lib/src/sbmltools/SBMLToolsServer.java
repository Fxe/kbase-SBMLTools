package sbmltools;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;

//BEGIN_HEADER
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.net.MalformedURLException;

import assemblyutil.AssemblyUtilClient;
import assemblyutil.FastaAssemblyFile;
import assemblyutil.GetAssemblyParams;
import assemblyutil.SaveAssemblyParams;
import datafileutil.DataFileUtilClient;
import datafileutil.DownloadWebFileOutput;
import datafileutil.DownloadWebFileParams;
import kbasereport.CreateParams;
import kbasereport.KBaseReportClient;
import kbasereport.Report;
import kbasereport.ReportInfo;
import kbasereport.WorkspaceObject;
//import net.sf.jfasta.FASTAElement;
//import net.sf.jfasta.FASTAFileReader;
//import net.sf.jfasta.impl.FASTAElementIterator;
//import net.sf.jfasta.impl.FASTAFileReaderImpl;
//import net.sf.jfasta.impl.FASTAFileWriter;
//END_HEADER

/**
 * <p>Original spec-file module name: SBMLTools</p>
 * <pre>
 * A KBase module: SBMLTools
 * This sample module contains one small method - filter_contigs.
 * </pre>
 */
public class SBMLToolsServer extends JsonServerServlet {
  private static final long serialVersionUID = 1L;
  private static final String version = "0.0.1";
  private static final String gitUrl = "https://fxe@github.com/Fxe/kbase-SBMLTools.git";
  private static final String gitCommitHash = "97afd9e5b69c872696c4e121a4f9f7fba098410c";
  
  private static final Logger logger = LoggerFactory.getLogger(SBMLToolsServer.class);

  //BEGIN_CLASS_HEADER
  private final URL callbackURL;
  private final Path scratch;
  //END_CLASS_HEADER

  public SBMLToolsServer() throws Exception {
    super("SBMLTools");
    //BEGIN_CONSTRUCTOR
    final String sdkURL = System.getenv("SDK_CALLBACK_URL");
    try {
      callbackURL = new URL(sdkURL);
      System.out.println("Got SDK_CALLBACK_URL " + callbackURL);
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException("Invalid SDK callback url: " + sdkURL, e);
    }
    scratch = Paths.get(super.config.get("scratch"));
    //END_CONSTRUCTOR
  }

  /**
   * <p>Original spec-file function name: filter_contigs_changed</p>
   * <pre>
   * The actual function is declared using 'funcdef' to specify the name
   * and input/return arguments to the function.  For all typical KBase
   * Apps that run in the Narrative, your function should have the 
   * 'authentication required' modifier.
   * </pre>
   * @param   params   instance of type {@link sbmltools.FilterContigsParams FilterContigsParams}
   * @return   parameter "output" of type {@link sbmltools.FilterContigsResults FilterContigsResults}
   */
  @JsonServerMethod(rpc = "SBMLTools.filter_contigs_changed", async=true)
  public FilterContigsResults filterContigsChanged(FilterContigsParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
    FilterContigsResults returnVal = null;
    //BEGIN filter_contigs_changed
    //END filter_contigs_changed
    final String workspaceName = params.getWorkspaceName();
    
    final AssemblyUtilClient assemblyUtilClient = new AssemblyUtilClient(callbackURL, authPart);
    assemblyUtilClient.setIsInsecureHttpConnectionAllowed(true);
    final FastaAssemblyFile fastaAssemblyFile = assemblyUtilClient.getAssemblyAsFasta(new GetAssemblyParams().withRef(params.getAssemblyInputRef()));
    
    final String newRef = assemblyUtilClient.saveAssemblyFromFasta(new SaveAssemblyParams().withAssemblyName(
        fastaAssemblyFile.getAssemblyName())
        .withWorkspaceName(workspaceName).withFile(fastaAssemblyFile));
//    JsonClientCaller caller = new JsonClientCaller(callbackURL, authPart);
//    caller.ca
    final KBaseReportClient reportClient = new KBaseReportClient(callbackURL, authPart);
    final ReportInfo reportInfo = reportClient.create(new CreateParams().withWorkspaceName(workspaceName)
        .withReport(new Report()
            .withTextMessage("message")
            .withObjectsCreated(Arrays.asList(new WorkspaceObject()
                .withDescription("the object").withRef(newRef)))));
    
    
    returnVal = new FilterContigsResults()
        .withAssemblyOutput(newRef)
        .withNContigsRemaining(10L)
        .withNContigsRemoved(5L)
        .withNInitialContigs(15L)
        .withReportName(reportInfo.getName())
        .withReportRef(reportInfo.getRef());
    
    return returnVal;
  }

  /**
   * <p>Original spec-file function name: do_nothing</p>
   * <pre>
   * </pre>
   */
  @JsonServerMethod(rpc = "SBMLTools.do_nothing", async=true)
  public void doNothing(RpcContext jsonRpcContext) throws Exception {
    //BEGIN do_nothing
    //END do_nothing
  }

  /**
   * <p>Original spec-file function name: do_nothing_but_auth</p>
   * <pre>
   * </pre>
   */
  @JsonServerMethod(rpc = "SBMLTools.do_nothing_but_auth", async=true)
  public void doNothingButAuth(AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
    //BEGIN do_nothing_but_auth
    final DataFileUtilClient dataFileUtilClient = new DataFileUtilClient(callbackURL, authPart);
    dataFileUtilClient.setIsInsecureHttpConnectionAllowed(true);
    //      dataFileUtilClient.
    DownloadWebFileParams webFileParams = new DownloadWebFileParams();
    webFileParams.setFileUrl("http://some_url_for_test.html");
    DownloadWebFileOutput webFileOutput = 
        dataFileUtilClient.downloadWebFile(webFileParams, jsonRpcContext);

    webFileOutput.getCopyFilePath();
    //END do_nothing_but_auth
  }

  /**
   * <p>Original spec-file function name: read_sbml_model</p>
   * <pre>
   * </pre>
   * @param   params   instance of type {@link sbmltools.ReadSBMLParams ReadSBMLParams}
   * @return   parameter "result" of type {@link sbmltools.ReadSBMLResults ReadSBMLResults}
   */
  @JsonServerMethod(rpc = "SBMLTools.read_sbml_model", async=true)
  public ReadSBMLResults readSbmlModel(ReadSBMLParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
    
    if (params.getRandomInt() == null) {
      throw new IllegalArgumentException("getRandomInt null !");
    }
    final long min_random_int = params.getRandomInt();
    if (min_random_int < -10) {
      throw new IllegalArgumentException("< -10 !! " + min_random_int);
    }
    return new KBaseSBMLImporter(authPart, config).run(params);

//    return returnVal;
  }
  @JsonServerMethod(rpc = "SBMLTools.status")
  public Map<String, Object> status() {
    Map<String, Object> returnVal = null;
    //BEGIN_STATUS
    returnVal = new LinkedHashMap<String, Object>();
    returnVal.put("state", "OK");
    returnVal.put("message", "");
    returnVal.put("version", version);
    returnVal.put("git_url", gitUrl);
    returnVal.put("git_commit_hash", gitCommitHash);
    //END_STATUS
    return returnVal;
  }

  public static void main(String[] args) throws Exception {
    if (args.length == 1) {
      new SBMLToolsServer().startupServer(Integer.parseInt(args[0]));
    } else if (args.length == 3) {
      JsonServerSyslog.setStaticUseSyslog(false);
      JsonServerSyslog.setStaticMlogFile(args[1] + ".log");
      new SBMLToolsServer().processRpcCall(new File(args[0]), new File(args[1]), args[2]);
    } else {
      System.out.println("Usage: <program> <server_port>");
      System.out.println("   or: <program> <context_json_file> <output_json_file> <token>");
      return;
    }
  }
}
