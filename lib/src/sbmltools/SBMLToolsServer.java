package sbmltools;


//BEGIN_HEADER
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbasereport.CreateExtendedReportParams;
import kbasereport.CreateParams;
import kbasereport.KBaseReportClient;
import kbasereport.Report;
import kbasereport.ReportInfo;
import kbasereport.WorkspaceObject;
import sbmltools.SbmlTools.ImportModelResult;
//END_HEADER
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;

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
    private static final String gitCommitHash = "7657e029b7ff29f2cff34eb4832381c5a9bb2457";

    //BEGIN_CLASS_HEADER
    private static final Logger logger = LoggerFactory.getLogger(SBMLToolsServer.class);
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
//        System.out.println(super.config.get("scratch"));
        if (super.config.get("scratch") == null) {
          scratch = null;
        } else {
          scratch = Paths.get(super.config.get("scratch"));
        }
        
        //END_CONSTRUCTOR
    }

    /**
     * <p>Original spec-file function name: filter_contigs</p>
     * <pre>
     * The actual function is declared using 'funcdef' to specify the name
     * and input/return arguments to the function.  For all typical KBase
     * Apps that run in the Narrative, your function should have the 
     * 'authentication required' modifier.
     * </pre>
     * @param   params   instance of type {@link sbmltools.SbmlImportParams SbmlImportParams}
     * @return   parameter "output" of type {@link sbmltools.FilterContigsResults FilterContigsResults}
     */
    @JsonServerMethod(rpc = "SBMLTools.filter_contigs", async=true)
    public FilterContigsResults filterContigs(SbmlImportParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        FilterContigsResults returnVal = null;
        //BEGIN filter_contigs
        
        // Print statements to stdout/stderr are captured and available as the App log
        System.out.println("Starting filter contigs. Parameters:");
        System.out.println(params);
        
        SbmlTools.validateSbmlImportParams(params);
        
        final String workspaceName = params.getWorkspaceName();
        final String assyRef = params.getAssemblyInputRef();
        ImportModelResult importModelResult = new ImportModelResult();
        SbmlTools tools = new SbmlTools(workspaceName, authPart, callbackURL, jsonRpcContext);
        
        String newAssyRef = assyRef;
        
        try {
          importModelResult = tools.importModel(params);
        } catch (Exception e) {
          importModelResult.message = e.getMessage();
        }
        
        final String resultText = "No changes\n" + importModelResult.message;
        System.out.println(resultText);
        
        newAssyRef = importModelResult.modelRef;
        
        final KBaseReportClient kbr = new KBaseReportClient(callbackURL, authPart);
        // see note above about bad practice
        kbr.setIsInsecureHttpConnectionAllowed(true);
        final ReportInfo report = kbr.create(new CreateParams().withWorkspaceName(workspaceName)
                .withReport(new Report().withTextMessage(resultText)
                        .withObjectsCreated(Arrays.asList(
                            new WorkspaceObject()
                                .withDescription("Filtered contigs")
                                .withRef(newAssyRef),
                              new WorkspaceObject()
                                .withDescription("Filtered contigs")
                                .withRef(newAssyRef)
                                ))));
        // Step 6: contruct the output to send back
        
        returnVal = new FilterContigsResults()
                .withAssemblyOutput(newAssyRef)
                .withNInitialContigs(0L)
                .withNContigsRemaining(0L)
                .withNContigsRemoved(-1L)
                .withReportName(report.getName())
                .withReportRef(report.getRef());

        System.out.println("returning:\n" + returnVal);
        
        //END filter_contigs
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: import_model_xml</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link sbmltools.SbmlImportParams SbmlImportParams}
     * @return   parameter "output" of type {@link sbmltools.FilterContigsResults FilterContigsResults}
     */
    @JsonServerMethod(rpc = "SBMLTools.import_model_xml", async=true)
    public FilterContigsResults importModelXml(SbmlImportParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        FilterContigsResults returnVal = null;
        //BEGIN import_model_xml
        //END import_model_xml
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: sbml_importer</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link sbmltools.SbmlImporterParams SbmlImporterParams}
     * @return   parameter "output" of type {@link sbmltools.SbmlImporterResults SbmlImporterResults}
     */
    @JsonServerMethod(rpc = "SBMLTools.sbml_importer", async=true)
    public SbmlImporterResults sbmlImporter(SbmlImporterParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        SbmlImporterResults returnVal = null;
        //BEGIN sbml_importer
        final String workspaceName = params.getWorkspaceName();
        SbmlTools sbmlTools = new SbmlTools(
            workspaceName, authPart, callbackURL, jsonRpcContext);
        ImportModelResult result = sbmlTools.importModel(params);
        final KBaseReportClient kbr = new KBaseReportClient(callbackURL, authPart);
        // see note above about bad practice
        kbr.setIsInsecureHttpConnectionAllowed(true);
        List<WorkspaceObject> objs = new ArrayList<WorkspaceObject> (result.objects);
        Report kbaseReport = new Report().withTextMessage(result.message)
                                         .withObjectsCreated(objs);
//        .withObjectsCreated();
//        List<WorkspaceObject> wsObjs = new ArrayList<> ();
//        List<kbasereport.File> htmlLinks = new ArrayList<> ();
//        htmlLinks.add(new kbasereport.File()
//            .withShockId("shock")
//            .withDescription("desc")
//            .withName("rep.html"));
//        final ReportInfo ereport = kbr.createExtendedReport(
//            new CreateExtendedReportParams()
//                .withMessage("report")
//                .withDirectHtmlLinkIndex(0L)
//                .withObjectsCreated(wsObjs)
//                .withHtmlLinks(htmlLinks)
//                .withWorkspaceName(workspaceName));
        final ReportInfo report = kbr.create(
            new CreateParams().withWorkspaceName(workspaceName)
                .withReport(kbaseReport));
        // Step 6: contruct the output to send back
        
        logger.info("{}", callbackURL.getPath());
        
        returnVal = new SbmlImporterResults()
                .withReportName(report.getName())
                .withReportRef(report.getRef());
        
        if (objs.size() > 0) {
          returnVal.withFbamodelId(objs.get(0).getRef());
        }
        
        //END sbml_importer
        return returnVal;
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
