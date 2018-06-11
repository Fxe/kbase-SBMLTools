package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;

import kbasegenomes.Feature;
import kbasegenomes.Genome;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;

public class KBaseGenomeAdapter {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseGenomeAdapter.class);
  
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
  
  public void addUniqueAliases(String id, List<String> aliases) {
    Set<Feature> features = featureMap.get(id);
    if (features.size() == 1) {
      Feature f = features.iterator().next();
      for (String a : aliases) {
        if (!featureMap.containsKey(a)) {
          addFeature(a, f);
          f.getAliases().add(a);
        } else {
          logger.warn("duplicate alias: {}", a);
        }
      }
    } else {
      logger.warn("invalid feature ID: {}", id);
    }
//    System.out.println(features.size());
//    System.out.println(featureMap.get(id));
//    System.out.println(aliases);
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
        if (filter.apply(a)) {
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

