package us.kbase;

import java.util.List;
import java.util.Map;

public class KBaseTemplate {
  public String id;
  public String name;
  public String type;
  public String domain;
  public String biochemistry_ref;
  
  public Object biomasses, pathways, roles;
  public Object compartments;
  public Object compcompounds;
  public Object complexes;

//  public List<Map<String, Object>> compounds;
//  public List<Map<String, Object>> reactions;
  public List<KBaseTemplateCompound> compounds;
  public List<KBaseTemplateReaction> reactions;
  
  public class KBaseTemplateReaction {
    public String id;
    public String name;
    
    public Double base_cost;
    public Double maxrevflux;
    public Double maxforflux;
    public Double forward_penalty;
    public Double reverse_penalty;
    public String status;
    public String type;
    public String reversibility;
    public String direction;
    public String GapfillDirection;
    public String templatecompartment;
    
    public List<Map<String, Object>> templateReactionReagents;
    public List<Object> templatecomplex_refs;
    
    public Object deltaG, deltaGErr;
  }
  
  public class KBaseTemplateCompound {
    public String id;
    public String name;
    public String abbreviation;
    public String formula;
    
    public Object aliases, isCofactor, mass, defaultCharge, deltaG, deltaGErr;
  }
}
