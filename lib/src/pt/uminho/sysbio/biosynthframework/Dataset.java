package pt.uminho.sysbio.biosynthframework;

import java.util.HashMap;
import java.util.Map;

public class Dataset<A, B, D> {
  public Map<A, Map<B, D>> dataset = new HashMap<> ();
  
  public void add(A a, B b, D data) {
    if (!dataset.containsKey(a)) {
      dataset.put(a, new HashMap<B, D> ());
    }
    dataset.get(a).put(b, data);
  }
  
  public void initIfNull(A a, B b, D data) {
    if (!dataset.containsKey(a)) {
      dataset.put(a, new HashMap<B, D> ());
    }
    if (!dataset.get(a).containsKey(b)) {
      dataset.get(a).put(b, data);
    }
  }
  
  public void renameColumn(B a, B b) {
    for (A r : dataset.keySet()) {
      Map<B, D> attributes = dataset.get(r);
      D data = attributes.remove(a);
      if (data != null) {
        attributes.put(b, data);
      }
    }
  }
}
