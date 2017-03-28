package sbmltools;

import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;

import assemblyutil.AssemblyUtilClient;
import assemblyutil.FastaAssemblyFile;
import assemblyutil.GetAssemblyParams;
import assemblyutil.SaveAssemblyParams;
import kbasereport.CreateParams;
import kbasereport.KBaseReportClient;
import kbasereport.Report;
import kbasereport.ReportInfo;
import kbasereport.WorkspaceObject;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import us.kbase.auth.AuthToken;

public class FilterContigs {
  
  URL callbackURL;
  AuthToken authPart;
  String assyRef;
  String workspaceName;
  Path scratch;
  
  public FilterContigs(URL callbackURL) {
    // TODO Auto-generated constructor stub
  }
  
  public FilterContigsResults run() throws Exception {
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
//    InputStream is = url.openStream();
    XmlStreamSbmlReader reader = new XmlStreamSbmlReader("");
//    if (is != null) {
//      is.close();
//    }
    /* Step 3 - Actually perform the filter operation, saving the good contigs to a new
     * fasta file.
     */
    final Path out = scratch.resolve("filtered.fasta");
    long total = 0;
    long remaining = 0;
//    try (final FASTAFileReader fastaRead = new FASTAFileReaderImpl(
//                new File(fileobj.getPath()));
//            final FASTAFileWriter fastaWrite = new FASTAFileWriter(out.toFile())) {
//        final FASTAElementIterator iter = fastaRead.getIterator();
//        while (iter.hasNext()) {
//            total++;
//            final FASTAElement fe = iter.next();
//            if (fe.getSequenceLength() >= minLength) {
//                remaining++;
//                fastaWrite.write(fe);
//            }
//        }
//    }
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
    
//    returnVal = 
//
//    System.out.println("returning:\n" + returnVal);
    
    return new FilterContigsResults().withAssemblyOutput(newAssyRef)
                                     .withNInitialContigs(total)
                                    .withNContigsRemaining(remaining)
                                    .withNContigsRemoved(total - remaining)
                                    .withReportName(report.getName())
                                    .withReportRef(report.getRef());
  }
}
