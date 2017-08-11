package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KBaseSolrResponse {
  
  public static class Response {
    public int numFound;
    public int start;
    public List<KBaseSolrDocument> docs = new ArrayList<> ();
  }
  
  public Map<String, ?> responseHeader = new HashMap<> ();
  public Response response;
}
