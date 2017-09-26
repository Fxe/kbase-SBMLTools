package pt.uminho.sysbio.biosynthframework.kbase.report;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PropagationReport {
  
  private Map<Double, Set<String>> genomeMatchData = new TreeMap<> ();
  
  public String matchMethod = "DNA Polymerase";
  private final int msize;
  private int mindex = 0;
  private Map<String, Integer> modelIndex = new HashMap<>();
  private Map<String, double[]> genePropagation = new HashMap<>();;
  
  public Map<Double, Set<String>> getGenomeMatchData() { return genomeMatchData;}
  public int getMsize() { return msize;}
  public int getMindex() { return mindex;}
  public Map<String, Integer> getModelIndex() { return modelIndex;}
  public Map<String, double[]> getGenePropagation() { return genePropagation;}

  public PropagationReport(int size) {
    this.msize = size;
  }
  
  public void add(String g, Double v) {
    if (g != null && v != null) {
      if (!genomeMatchData.containsKey(v)) {
        genomeMatchData.put(v, new HashSet<String>());
      }
      
      genomeMatchData.get(v).add(g);
    }
  }
  
  public void addGene(String gene, String model) {
    if (gene != null && model != null) {
      if (!modelIndex.containsKey(model)) {
        modelIndex.put(model, mindex++);
      }
      
      int index = modelIndex.get(model);
      
      if (!genePropagation.containsKey(gene)) {
        genePropagation.put(gene, new double[msize]);
      }
      
      genePropagation.get(gene)[index] = 1;
    }
  }
}
