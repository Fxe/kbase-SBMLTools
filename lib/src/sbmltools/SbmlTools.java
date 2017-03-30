package sbmltools;

import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import assemblyutil.AssemblyUtilClient;
import assemblyutil.FastaAssemblyFile;
import assemblyutil.GetAssemblyParams;
import assemblyutil.SaveAssemblyParams;
import pt.uminho.sysbio.biosynthframework.sbml.MessageType;
import pt.uminho.sysbio.biosynthframework.sbml.XmlMessage;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelValidator;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.RpcContext;

public class SbmlTools {
  
  public final AuthToken authPart;
  public final RpcContext jsonRpcContext;
  public final URL callbackURL;
  
  public SbmlTools(AuthToken authPart, URL callbackURL, RpcContext jsonRpcContext) {
    this.authPart = authPart;
    this.jsonRpcContext = jsonRpcContext;
    this.callbackURL = callbackURL;
  }
  
  public String filterContigs(String assyRef, String workspaceName, Path scratch) throws Exception {
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
    
    /* Step 3 - Actually perform the filter operation, saving the good contigs to a new
     * fasta file.
     */
    final Path out = scratch.resolve("filtered.fasta");
//    long total = 0;
//    long remaining = 0;
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
    
    // Step 4 - Save the new Assembly back to the system
    final String newAssyRef = assyUtil.saveAssemblyFromFasta(new SaveAssemblyParams()
        .withAssemblyName(fileobj.getAssemblyName() + "_new")
        .withWorkspaceName(workspaceName)
        .withFile(new FastaAssemblyFile().withPath(fileobj.getPath())));
    
    return newAssyRef;
  }
  
  public static Map<String, MessageType> knownSpecieAttributes() {
    String[] attr = new String[] {
        "speciesType", "NONE",
        "charge", "NONE",
        "constant", "NONE",
        "metaid", "NONE",
        "hasOnlySubstanceUnits", "NONE",
        "sboTerm", "NONE",
        "boundaryCondition", "NONE",
        "chemicalFormula", "NONE",
        "initialAmount", "NONE",
        "name", "NONE",
        "compartment", "WARN",
        "id", "CRITICAL",
        "initialConcentration", "NONE",
    };
    Map<String, MessageType> fields = new HashMap<>();
    for (int i = 0; i < attr.length; i+=2) {
      fields.put(attr[i], MessageType.valueOf(attr[i + 1]));
    }
    return fields;
  }
  
  public static void xrxnAttributes(XmlSbmlModelValidator validator) {
    String[] xrxnAttr = new String[] {
        "upperFluxBound", "fast", "metaid", "reversible", "sboTerm", "name", "lowerFluxBound", "id"
    };
    String[] xrxnSpecieAttr = new String[] {
        "stoichiometry", "constant", "species", "metaid", "sboTerm"
    };
    validator.xrxnAttr.addAll(Arrays.asList(xrxnAttr));
    validator.xrxnStoichAttr.addAll(Arrays.asList(xrxnSpecieAttr));
  }
  
  public String importModel(SbmlImportParams params) {
    String reportText = "";
    try {
      URL url = new URL(params.getUrl());
      XmlStreamSbmlReader reader = new XmlStreamSbmlReader(url.openStream());
      XmlSbmlModel model = reader.parse();
//      msg = model.getAttributes().toString();
      XmlSbmlModelValidator validator = new XmlSbmlModelValidator(model, knownSpecieAttributes());
      xrxnAttributes(validator);
      
      List<XmlMessage> msgs = validator.validate();
      reportText = String.format("Species %d, Reactions %s, %s", model.getSpecies().size(), model.getReactions().size(), params.getUrl());
//      reportText += SbmlTools.aaa(validator.validate());
//      reportText = String.format("Species %d, Reactions %s, %s", model.getSpecies().size(), model.getReactions().size(), params.getUrl());
    } catch (Exception e) {
      e.printStackTrace();
      reportText = e.getMessage();
    }
    
    return reportText;
  }
  
  public static String aaa(List<XmlMessage> msgs) {
    String txt = "";
    for (XmlMessage m : msgs) {
      txt +="\n" + String.format("%s", m);
    }
    
    return txt;
  }
}
