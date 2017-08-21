package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import kbasefba.FBAModel;

public class FBAModelAdapter {
  
  public final FBAModel fbaModel;
  
  public Map<String, String> geneIdSwap = new HashMap<> ();
  
  public FBAModelAdapter(FBAModel fbaModel) {
    this.fbaModel = fbaModel;
  }
  
  public Set<String> getGenes(String rxnEntry) {
    return null;
  }
}
