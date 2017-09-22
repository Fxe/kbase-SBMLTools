package pt.uminho.sysbio.biosynthframework.kbase.report;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PropagationReport {
  
  private Map<Double, Set<String>> genomeMatchData = new TreeMap<> ();
  
  private int size;
  private int i = 0;
  private Map<String, Integer> modelIndex = new HashMap<>();
  private Map<String, double[]> genePropagation = new HashMap<>();;
  
  public PropagationReport(int size) {
    this.size = size;
  }
  
  public void add(String g, Double v) {
    if (!genomeMatchData.containsKey(v)) {
      genomeMatchData.put(v, new HashSet<String>());
    }
    
    genomeMatchData.get(v).add(g);
  }
  
  public void addGene(String gene, String model) {
    if (!modelIndex.containsKey(model)) {
      modelIndex.put(model, i++);
    }
    
    int index = modelIndex.get(model);
    
    if (!genePropagation.containsKey(model)) {
      genePropagation.put(gene, new double[size]);
    }
    
    genePropagation.get(model)[index] = 1;
  }
}
