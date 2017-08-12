package sbmltools;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.Tuple11;

//BEGIN_HEADER
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.kbase.workspace.ListAllTypesParams;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.WorkspaceClient;
import datafileutil.DataFileUtilClient;
import kbasereport.CreateParams;
import kbasereport.KBaseReportClient;
import kbasereport.Report;
import kbasereport.ReportInfo;
import kbasereport.WorkspaceObject;
import kbsolrutil.KBSolrUtilClient;
import kbsolrutil.SearchSolrParams;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseGeneIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseHtmlReport;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelIntegrationFacade;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseReporter;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseSbmlTools;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseHtmlReport.ReportFiles;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseSbmlTools.ImportModelResult;
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
    private static final String gitCommitHash = "032c47da3969637ffb44e4bec4b1bfce482a1034";

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
        
        KBaseSbmlTools.validateSbmlImportParams(params);
        
        final String workspaceName = params.getWorkspaceName();
        final String assyRef = params.getAssemblyInputRef();
        ImportModelResult importModelResult = new ImportModelResult();
        KBaseSbmlTools tools = new KBaseSbmlTools(workspaceName, null);
        
        String newAssyRef = assyRef;
        
        try {
          importModelResult = tools.debugThings(params);
        } catch (Exception e) {
          importModelResult.message = e.getMessage();
        }
        
//        final String resultText = "No changes\n" + importModelResult.message;
        
        final KBaseReportClient kbr = new KBaseReportClient(callbackURL, authPart);
        // see note above about bad practice
        kbr.setIsInsecureHttpConnectionAllowed(true);
        KBaseReporter reporter = new KBaseReporter(kbr, workspaceName);
        reporter.addWsObject("Replicate 1", newAssyRef);
        reporter.addWsObject("Replicate 2", newAssyRef);
        
        KBaseHtmlReport htmlReport = new KBaseHtmlReport(scratch);
//        File indexFile = htmlReport.makeStaticReport("index.html", 
//            KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/index.html"));
//        reporter.addHtmlFile("example", "name 1", indexFile.getAbsolutePath());
//        reporter.addFile("example file 1", "fname 1", indexFile.getAbsolutePath());
        
        List<String> files = new ArrayList<> ();
        files.add("index.html");
        files.add("css/bootstrap.min.css");
        files.add("js/jquery-2.2.2.min.js");
        files.add("js/underscore-min.js");
        files.add("js/plotly-1.28.3.min.js");
        files.add("ids.json");
        files.add("names.json");
        files.add("refs.json");
        List<String> datas = new ArrayList<> ();
        
        for (String f : files) {
          datas.add(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/" + f));
        }
        
        DataFileUtilClient dfuClient = new DataFileUtilClient(callbackURL, authPart);
        dfuClient.setIsInsecureHttpConnectionAllowed(true);
        
        ReportFiles reportFiles = htmlReport.makeStaticReport(files, datas);
//        KBaseIOUtils.folderToShock(reportFiles.baseFolder, dfuClient);
        reporter.addHtmlFolderShock("report", "index.html", reportFiles.baseFolder, dfuClient);
        for (int i = 0; i < files.size(); i++) {
          File f = reportFiles.files.get(i);
//          reporter.addHtmlFile(f.getName(), f.getName(), f.getAbsolutePath());
          reporter.addFile(f.getName(), f.getName(), f.getAbsolutePath());
        }
        
//        WorkspaceClient wsClient = new WorkspaceClient(callbackURL, authPart);
//        wsClient.lis
        
        final ReportInfo report = reporter.extendedReport();
        
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
        String workspaceName = params.getUrl();
        WorkspaceClient wspClient = new WorkspaceClient(callbackURL, authPart);
        System.out.println(wspClient.listAllTypes(new ListAllTypesParams().withWithEmptyModules(0L)));
        //KBaseCollections/FBAModel
        List<String> workspaces = new ArrayList<> ();
        workspaces.add(workspaceName);
        List<?> o = 
            wspClient.listObjects(
                new ListObjectsParams().withType("KBaseFBA.FBAModel").withWorkspaces(workspaces));
        for (Object t : o) {
          System.out.println(t);
        }
        List<WorkspaceObject> objectsCreated = new ArrayList<> ();
        final KBaseReportClient kbr = new KBaseReportClient(callbackURL, authPart);
        // see note above about bad practice
        kbr.setIsInsecureHttpConnectionAllowed(true);
        ReportInfo ri = kbr.create(new CreateParams().withWorkspaceName(params.getWorkspaceName())
            .withReport(new Report()
                .withTextMessage("")
                .withObjectsCreated(objectsCreated)));
        
        returnVal = new FilterContigsResults()
            .withReportName(ri.getName())
            .withReportRef(ri.getRef());
        
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

        
        final DataFileUtilClient dfuClient = new DataFileUtilClient(callbackURL, authPart);
        final KBaseReportClient  kbrClient = new KBaseReportClient(callbackURL, authPart);
     // see note above about bad practice
        if (dfuClient != null) {
          dfuClient.setIsInsecureHttpConnectionAllowed(true);
        }
        if (kbrClient != null) {
          kbrClient.setIsInsecureHttpConnectionAllowed(true);
        }
        
        
        KBaseSbmlTools sbmlTools = new KBaseSbmlTools(workspaceName, dfuClient);
        
        ImportModelResult result = sbmlTools.importModel(params);
        List<WorkspaceObject> objs = new ArrayList<WorkspaceObject> (result.objects);
//        Report kbaseReport = new Report().withTextMessage(result.message)
//                                         .withObjectsCreated(objs);
        
        
        KBaseHtmlReport htmlReport = new KBaseHtmlReport(scratch);
        
        List<String> files = new ArrayList<> ();
        files.add("index.html");
        files.add("css/bootstrap.min.css");
        files.add("js/jquery-2.2.2.min.js");
        files.add("js/underscore-min.js");
        files.add("js/plotly-1.28.3.min.js");
        files.add("ids.json");
        files.add("names.json");
        files.add("refs.json");
        List<String> datas = new ArrayList<> ();
        
        for (String f : files) {
          datas.add(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/" + f));
        }
        

        
        ReportFiles reportFiles = htmlReport.makeStaticReport(files, datas);
        
        File f = new File("/kb/module/data/readerData.json");
        if (f.exists()) {
          logger.info("copy {} -> {}", f.getAbsolutePath(), reportFiles.baseFolder);
          KBaseIOUtils.copy(f.getAbsolutePath(), reportFiles.baseFolder + "/");
        }
        
        KBaseReporter reporter = new KBaseReporter(kbrClient, workspaceName);
        reporter.addWsObjects(objs);
        reporter.addHtmlFolderShock("importer report", "index.html", reportFiles.baseFolder, dfuClient);

        
        final ReportInfo report = reporter.extendedReport();
//        final ReportInfo report = kbr.create(
//            new CreateParams().withWorkspaceName(workspaceName)
//                .withReport(kbaseReport));
        // Step 6: contruct the output to send back
        
        logger.info("{}", callbackURL.getPath());
        
        returnVal = new SbmlImporterResults()
                .withReportName(report.getName())
                .withReportRef(report.getRef());

//        System.out.println(result);
//        System.out.println(result.modelName);
        if (objs.size() > 0) {
          //name instead of ref!
          returnVal.withFbamodelId(result.modelName);
        }
        
        //END sbml_importer
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: integrate_model</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link sbmltools.IntegrateModelParams IntegrateModelParams}
     * @return   parameter "output" of type {@link sbmltools.SbmlImporterResults SbmlImporterResults}
     */
    @JsonServerMethod(rpc = "SBMLTools.integrate_model", async=true)
    public SbmlImporterResults integrateModel(IntegrateModelParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        SbmlImporterResults returnVal = null;
        //BEGIN integrate_model
        
        System.out.println(config);
        System.out.println(callbackURL);
        
        final String workspaceName = params.getWorkspaceName();
        
        final DataFileUtilClient dfuClient = new DataFileUtilClient(callbackURL, authPart);
        final KBaseReportClient  kbrClient = new KBaseReportClient(callbackURL, authPart);
        final WorkspaceClient    wspClient = new WorkspaceClient(new URL(config.get("workspace-url")), authPart);
        final KBSolrUtilClient  solrClient = new KBSolrUtilClient(callbackURL, authPart);
//        wspClient.setServiceVersion("beta");
        dfuClient.setIsInsecureHttpConnectionAllowed(true);
        kbrClient.setIsInsecureHttpConnectionAllowed(true);
        wspClient.setIsInsecureHttpConnectionAllowed(true);
        solrClient.setServiceVersion("beta");
        solrClient.setIsInsecureHttpConnectionAllowed(true);
        

        KBaseGeneIntegration geneIntegration = new KBaseGeneIntegration(solrClient);
        
//        KBaseIOUtils.getFBAModel2(params.getModelName(), workspaceName, null, wspClient);
        returnVal = new KBaseModelIntegrationFacade(wspClient, 
                                                    dfuClient, 
                                                    kbrClient,
                                                    geneIntegration,
                                                    "/data/integration/export").kbaseIntegrate(params, workspaceName);
        
        //END integrate_model
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
