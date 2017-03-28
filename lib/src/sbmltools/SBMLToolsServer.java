package sbmltools;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import us.kbase.auth.AuthToken;
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
import net.sf.jfasta.FASTAElement;
import net.sf.jfasta.FASTAFileReader;
import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import net.sf.jfasta.impl.FASTAFileWriter;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
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
    private static final String gitCommitHash = "1ab91759ef489e6f5108a960dcbc8cb76fdba839";

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
        
        /* Step 2 - Download the input data as a Fasta file
         * We can use the AssemblyUtils module to download a FASTA file from our Assembly data
         * object. The return object gives us the path to the file that was created.
         */
        System.out.println("Downloading assembly data as FASTA file.");
        final AssemblyUtilClient assyUtil = new AssemblyUtilClient(callbackURL, authPart);
        /* Normally this is bad practice, but the callback server (which runs on the same machine
         * as the docker container running the method) is http only
         * TODO Should allow the clients to not require a token, even for auth required methods,
         * since the callback server ignores the incoming token. No need to transmit the token
         * here.
         */
        assyUtil.setIsInsecureHttpConnectionAllowed(true);
        final FastaAssemblyFile fileobj = assyUtil.getAssemblyAsFasta(new GetAssemblyParams()
                .withRef(assyRef));
        
//        final DataFileUtilClient fileUtilClient = new DataFileUtilClient(callbackURL, authPart);
//        fileUtilClient.setIsInsecureHttpConnectionAllowed(true);
//        
//        DownloadWebFileOutput webFileOutput = fileUtilClient.downloadWebFile(
//            new DownloadWebFileParams().withFileUrl(params.getUrl()));
//        final Path webFilePath = scratch.resolve(webFileOutput.getCopyFilePath());
        
        
//        InputStream is = url.openStream();
        String msg = "empty";
        
        try {
          URL url = new URL(params.getUrl());
          XmlStreamSbmlReader reader = new XmlStreamSbmlReader(url.openStream());
          XmlSbmlModel model = reader.parse();
//          msg = model.getAttributes().toString();
          msg = String.format("Species %d, Reactions %s, %s", model.getSpecies().size(), model.getReactions().size(), params.getUrl());
        } catch (Exception e) {
          e.printStackTrace();
          msg = e.getMessage();
        }
//        if (url != null) {
//          url.c
//        }
//        if (is != null) {
//          is.close();
//        }
        /* Step 3 - Actually perform the filter operation, saving the good contigs to a new
         * fasta file.
         */
        final Path out = scratch.resolve("filtered.fasta");
        long total = 0;
        long remaining = 0;
//        try (final FASTAFileReader fastaRead = new FASTAFileReaderImpl(
//                    new File(fileobj.getPath()));
//                final FASTAFileWriter fastaWrite = new FASTAFileWriter(out.toFile())) {
//            final FASTAElementIterator iter = fastaRead.getIterator();
//            while (iter.hasNext()) {
//                total++;
//                final FASTAElement fe = iter.next();
//                if (fe.getSequenceLength() >= minLength) {
//                    remaining++;
//                    fastaWrite.write(fe);
//                }
//            }
//        }
        final String resultText = "No changes " + params.getUrl() + " " + msg;
        System.out.println(resultText);
        
        // Step 4 - Save the new Assembly back to the system
        
        final String newAssyRef = assyUtil.saveAssemblyFromFasta(new SaveAssemblyParams()
                .withAssemblyName(fileobj.getAssemblyName() + "_new")
                .withWorkspaceName(workspaceName)
                .withFile(new FastaAssemblyFile().withPath(fileobj.getPath())));
        
        // Step 5 - Build a Report and return
        
        final KBaseReportClient kbr = new KBaseReportClient(callbackURL, authPart);
        // see note above about bad practice
        kbr.setIsInsecureHttpConnectionAllowed(true);
        final ReportInfo report = kbr.create(new CreateParams().withWorkspaceName(workspaceName)
                .withReport(new Report().withTextMessage(resultText)
                        .withObjectsCreated(Arrays.asList(new WorkspaceObject()
                                .withDescription("Filtered contigs")
                                .withRef(newAssyRef)))));
        // Step 6: contruct the output to send back
        
        returnVal = new FilterContigsResults()
                .withAssemblyOutput(newAssyRef)
                .withNInitialContigs(total)
                .withNContigsRemaining(remaining)
                .withNContigsRemoved(total - remaining)
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
      //BEGIN filter_contigs
      
      // Print statements to stdout/stderr are captured and available as the App log
      System.out.println("Starting filter contigs. Parameters:");
      System.out.println(params);
      
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
      
      /* Step 2 - Download the input data as a Fasta file
       * We can use the AssemblyUtils module to download a FASTA file from our Assembly data
       * object. The return object gives us the path to the file that was created.
       */
      System.out.println("Downloading assembly data as FASTA file.");
      final AssemblyUtilClient assyUtil = new AssemblyUtilClient(callbackURL, authPart);
      /* Normally this is bad practice, but the callback server (which runs on the same machine
       * as the docker container running the method) is http only
       * TODO Should allow the clients to not require a token, even for auth required methods,
       * since the callback server ignores the incoming token. No need to transmit the token
       * here.
       */
      assyUtil.setIsInsecureHttpConnectionAllowed(true);
      final FastaAssemblyFile fileobj = assyUtil.getAssemblyAsFasta(new GetAssemblyParams()
              .withRef(assyRef));
      
      URL url = new URL("http://193.137.11.210/models/biomodels/iBROKEN.xml");
//      InputStream is = url.openStream();
      XmlStreamSbmlReader reader = new XmlStreamSbmlReader("");
//      if (is != null) {
//        is.close();
//      }
      /* Step 3 - Actually perform the filter operation, saving the good contigs to a new
       * fasta file.
       */
      final Path out = scratch.resolve("filtered.fasta");
      long total = 0;
      long remaining = 0;
//      try (final FASTAFileReader fastaRead = new FASTAFileReaderImpl(
//                  new File(fileobj.getPath()));
//              final FASTAFileWriter fastaWrite = new FASTAFileWriter(out.toFile())) {
//          final FASTAElementIterator iter = fastaRead.getIterator();
//          while (iter.hasNext()) {
//              total++;
//              final FASTAElement fe = iter.next();
//              if (fe.getSequenceLength() >= minLength) {
//                  remaining++;
//                  fastaWrite.write(fe);
//              }
//          }
//      }
      final String resultText = "No changes";
      System.out.println(resultText);
      
      // Step 4 - Save the new Assembly back to the system
      
      final String newAssyRef = assyUtil.saveAssemblyFromFasta(new SaveAssemblyParams()
              .withAssemblyName(fileobj.getAssemblyName() + "_new")
              .withWorkspaceName(workspaceName)
              .withFile(new FastaAssemblyFile().withPath(fileobj.getPath())));
      
      // Step 5 - Build a Report and return
      
      final KBaseReportClient kbr = new KBaseReportClient(callbackURL, authPart);
      // see note above about bad practice
      kbr.setIsInsecureHttpConnectionAllowed(true);
      final ReportInfo report = kbr.create(new CreateParams().withWorkspaceName(workspaceName)
              .withReport(new Report().withTextMessage(resultText)
                      .withObjectsCreated(Arrays.asList(new WorkspaceObject()
                              .withDescription("Filtered contigs")
                              .withRef(newAssyRef)))));
      // Step 6: contruct the output to send back
      
      returnVal = new FilterContigsResults()
              .withAssemblyOutput(newAssyRef)
              .withNInitialContigs(total)
              .withNContigsRemaining(remaining)
              .withNContigsRemoved(total - remaining)
              .withReportName(report.getName())
              .withReportRef(report.getRef());

      System.out.println("returning:\n" + returnVal);
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
