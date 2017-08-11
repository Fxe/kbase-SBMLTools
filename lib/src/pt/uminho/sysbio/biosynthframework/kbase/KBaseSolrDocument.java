package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.List;

public class KBaseSolrDocument {
  public List<String> aliases = new ArrayList<> ();
  public List<String> functions = new ArrayList<> ();
  public String scientific_name;
  public String genome_id;
  public String taxonomy_ref;
  public String workspace_name;
  public String save_date;
}
