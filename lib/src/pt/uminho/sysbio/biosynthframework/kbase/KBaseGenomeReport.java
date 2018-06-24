package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

import pt.uminho.sysbio.biosynthframework.BHashMap;
import pt.uminho.sysbio.biosynthframework.BMap;

public class KBaseGenomeReport {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseGenomeReport.class);
  
  public Map<String, Double> matchScores = new HashMap<> ();
  public Map<String, String> matchOrganism = new HashMap<> ();
  public List<String> bestGenomeKID = new ArrayList<> ();
  public String bestMatch;
  public double bestScore;
  public int features;
  public Map<String, Object> mgenes = new HashMap<> ();
  public Set<String> mgenesMapped = new HashSet<> ();
  public Map<String, String> mgeneToFeature = new HashMap<> ();
  
  public BMap<String, String> geneFunction = new BHashMap<>();
  public Map<String, Set<String>> geneReactions = new HashMap<>();
  
  public String getGenomeId() {
    if (bestGenomeKID.size() == 1) {
      return bestGenomeKID.iterator().next();
    } else if (bestGenomeKID.size() > 1) {
      logger.warn("multiple genomes");
    }
    return null;
  }
  
  public void mapGeneToFeature(String gene, String feature) {
    if (geneFunction.containsKey(feature)) {
      this.mgeneToFeature.put(gene, feature);
    } else {
      logger.warn("Genome Feature [{}] not found", feature);
    }
  }
  
  public Set<String> getUnmappedFeatures() {
    return Sets.difference(geneFunction.keySet(), new HashSet<> (mgeneToFeature.values()));
  }
  
  public Set<String> getUnmappedGenes() {
    return Sets.difference(mgenes.keySet(), mgeneToFeature.keySet());
  }
  
  public void mapGeneToFeature(Set<String> gene, String feature) {
    for (String g : gene) {
      mapGeneToFeature(g, feature);
    }
  }
  
  public void addMatchGenome(String genomeId, String org, double match) {
    matchScores.put(genomeId, match);
    matchOrganism.put(genomeId, org);
  }
  
  //missing features ? and roles !
  //complete miss feature + role + reactions not in model
  //gpr miss feature not in model but reaction is ! +GPR update
}
