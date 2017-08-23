package kbasegenomes;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.JsonServerSyslog;

//BEGIN_HEADER
//END_HEADER

/**
 * <p>Original spec-file module name: KBaseGenomes</p>
 * <pre>
 * @author chenry,kkeller
 * </pre>
 */
public class KBaseGenomesServer extends JsonServerServlet {
    private static final long serialVersionUID = 1L;
    private static final String version = "0.0.1";
    private static final String gitUrl = "https://fxe@github.com/Fxe/kbase-SBMLTools.git";
    private static final String gitCommitHash = "c5502733aa11b35689ce7eb1942afec5b5d2d6a4";

    //BEGIN_CLASS_HEADER
    //END_CLASS_HEADER

    public KBaseGenomesServer() throws Exception {
        super("KBaseGenomes");
        //BEGIN_CONSTRUCTOR
        //END_CONSTRUCTOR
    }
    @JsonServerMethod(rpc = "KBaseGenomes.status")
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
            new KBaseGenomesServer().startupServer(Integer.parseInt(args[0]));
        } else if (args.length == 3) {
            JsonServerSyslog.setStaticUseSyslog(false);
            JsonServerSyslog.setStaticMlogFile(args[1] + ".log");
            new KBaseGenomesServer().processRpcCall(new File(args[0]), new File(args[1]), args[2]);
        } else {
            System.out.println("Usage: <program> <server_port>");
            System.out.println("   or: <program> <context_json_file> <output_json_file> <token>");
            return;
        }
    }
}
