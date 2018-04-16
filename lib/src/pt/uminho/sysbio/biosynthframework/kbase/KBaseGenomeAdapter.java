package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import kbasegenomes.Feature;
import kbasegenomes.Genome;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;

public class KBaseGenomeAdapter {
  
  public final Genome genome;
  public Map<String, Set<Feature>> featureMap = new HashMap<> ();
  public Map<String, String> aliasToFeatureId = new HashMap<> ();
  
  public KBaseGenomeAdapter(Genome genome) {
    this.genome = genome;
    
    for (Feature f: genome.getFeatures()) {
      addFeature(f.getId(), f);
      for (String a : f.getAliases()) {
        if (!DataUtils.empty(a)) {
          addFeature(a, f);
        }
      }
    }
  }
  
  private void addFeature(String alias, Feature f) {
    if (!featureMap.containsKey(alias)) {
      featureMap.put(alias, new HashSet<Feature>());
    }
    if (!aliasToFeatureId.containsKey(alias)) {
      aliasToFeatureId.put(alias, f.getId());
    }
    featureMap.get(alias).add(f);
  }
  
  public Feature findUniqueFeature(String alias) {
    Feature f = null;
    if (featureMap.containsKey(alias)) {
      if (featureMap.get(alias).size() == 1) {
        f = featureMap.get(alias).iterator().next();
      } else {
        throw new IllegalArgumentException(String.format("Alias not unique: [%s]", alias));
      }
    }
    
    return f;
  }
  
  public String findUniqueFeature(String alias, Predicate<String> filter) {
    Feature f = findUniqueFeature(alias);
    
    String id = null;
    
    if (f != null) {
      Set<String> aliases = new HashSet<>(f.getAliases());
      aliases.add(f.getId());
      for (String a : aliases) {
        if (filter.test(a)) {
          id = a;
        }
      }
    }
    
    return id;
  }
  
//  public Feature findFeature(String alias) {
//    
//  }
}
