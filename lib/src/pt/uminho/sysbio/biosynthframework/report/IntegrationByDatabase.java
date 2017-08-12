package pt.uminho.sysbio.biosynthframework.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.Dataset;
import pt.uminho.sysbio.biosynthframework.integration.model.IntegrationMap;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;

public class IntegrationByDatabase {
  public Map<String, Integer> modelTotal = new HashMap<> ();
  public List<String> databases = new ArrayList<> ();
  public Dataset<String, String, Integer> dataAbsolute = new Dataset<>();
  public Map<String, List<Double>> values = new HashMap<> ();
  public Map<String, List<Integer>> valuesAbsolute = new HashMap<> ();
  
  public void addIntegrationMap(String model, 
      IntegrationMap<String, ?> imap) {
    if (modelTotal.containsKey(model)) {
      Map<String, Integer> count = new HashMap<> ();
      
      for (String k : imap.keySet()) {
        for (Object db : imap.get(k).keySet()) {
          if (databases.contains(db.toString())) {
            int size = imap.get(k).get(db).size();
            if (size > 0) {
              CollectionUtils.increaseCount(count, db.toString(), 1);
            }
          }
        }
      }
      
      for (String db : databases) {
        Integer i = count.get(db);
        if (i == null) {
          i = 0;
        }
        dataAbsolute.add(model, db, i);
      }
    }
  }
  
  public void addIntegrationMap(String model, 
      Map<String, Map<MetaboliteMajorLabel, String>> imap) {
    if (modelTotal.containsKey(model)) {
      Map<String, Integer> count = new HashMap<> ();
      
      for (String k : imap.keySet()) {
        for (Object db : imap.get(k).keySet()) {
          if (databases.contains(db.toString())) {
            String ref = imap.get(k).get(db);
            if (ref != null) {
              CollectionUtils.increaseCount(count, db.toString(), 1);
            }
          }
        }
      }
      
      for (String db : databases) {
        Integer i = count.get(db);
        if (i == null) {
          i = 0;
        }
        dataAbsolute.add(model, db, i);
      }
    }
  }
  
  @Override
  public String toString() {
    return String.format(
        "modelTotal: %s, databases: %s, dataAbsolute: %s, values: %s, valuesAbsolute: %s", 
        modelTotal, databases, dataAbsolute, values, valuesAbsolute);
  }
}
