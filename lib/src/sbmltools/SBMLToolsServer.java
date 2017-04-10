package sbmltools;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;

//BEGIN_HEADER
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
    private static final String gitCommitHash = "0ceba67f078d29200c621cbcc259f6c608f7ae7e";

    //BEGIN_CLASS_HEADER
    //END_CLASS_HEADER

    public SBMLToolsServer() throws Exception {
        super("SBMLTools");
        //BEGIN_CONSTRUCTOR
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
