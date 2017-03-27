package pt.uminho.sysbio.biosynthframework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionUtils {

  private static final Logger logger = LoggerFactory.getLogger(CollectionUtils.class);
  
  public static<K> void increaseCount(Map<K, Integer> map, K key, int amount) {
    if (!map.containsKey(key)) {
      map.put(key, 0);
    }

    Integer total = map.get(key);
    total += amount;
    map.put(key, total);
  }
  
  public static<K> void increaseCount(Map<K, Double> map, K key, double amount) {
    if (!map.containsKey(key)) {
      map.put(key, 0d);
    }

    Double total = map.get(key);
    total += amount;
    map.put(key, total);
  }

  public static<C extends Collection<T>, T> Collection<T> fromArray(T[] array, Class<C> clazz) {
    try {
      Collection<T> collection = clazz.newInstance();
      for (T o : array) {
        collection.add(o);
      }
      return collection;
    } catch (Exception e) {
      return null;	
    }
  }

  //	public static inters

  public static<C extends Set<T>, T> Set<T> toSet(T[] array, Class<C> clazz) {
    try {
      Set<T> set = clazz.newInstance();
      for (T o : array) {
        set.add(o);
      }
      return set;
    } catch (Exception e) {
      return null;	
    }
  }
  
  public static<K> void mmul(Map<K, Double> map, Double val) {
    for (K o : map.keySet()) {
      double v = map.get(o); 
      map.put(o, v * val);
    }
  }
  
  public static<K, V> Map<V, Set<K>> reverseMap(Map<K, V> map) {
    Map<V, Set<K>> rev = new HashMap<> ();
    for (K key : map.keySet()) {
      V value = map.get(key);
      if (!rev.containsKey(value)) {
        rev.put(value, new HashSet<K> ());
      }
      rev.get(value).add(key);
    }
    return rev;
  }
  
  public static<E> double jaccard(Collection<E> a, Collection<E> b) {
    if (a.isEmpty() && b.isEmpty()) return 1.0;

    Set<E> A_union_B = new HashSet<> (a);
    A_union_B.addAll(b);
    Set<E> A_intersect_B = new HashSet<> (a);
    A_intersect_B.retainAll(b);

    return A_intersect_B.size() / (double)A_union_B.size();
  }
  

  /**
   * [{A -> j}, {B -> j}, {C -> j}]
   * [ [A, B, C] ]
   * @param mapList
   * @return
   */
  public static<K, V> List<List<Set<K>>> sortMaps(List<Map<K, V>> mapList) {
    int index = 0;
    int total = 0;
    
    //index each value to a array position
    Map<V, Integer> indexMap = new HashMap<> ();
    List<List<Set<K>>> allocation = new ArrayList<> ();
    for (int columnIndex = 0; columnIndex < mapList.size(); columnIndex++) {
      Map<K, V> map = mapList.get(columnIndex);
      for (K id : map.keySet()) {
        total++;
        V v = map.get(id);
        
        if (!indexMap.containsKey(v)) {
          indexMap.put(v, index++);
          List<Set<K>> slot = new ArrayList<> (mapList.size());
          for (int k = 0; k < mapList.size(); k++) {
            slot.add(new HashSet<K> ());
          }
          allocation.add(slot);
          logger.debug("slot {} -> {}", index, v);
        }
        
        int vIndex = indexMap.get(v);
        List<Set<K>> slot = allocation.get(vIndex);
        slot.get(columnIndex).add(id);
       
        logger.debug("slot {} -> {}", index, slot);
      }
    }
    
    logger.debug("{} -> {}", total, index);
    
    return allocation;
  }
}
