package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import pt.uminho.sysbio.biosynthframework.SubcellularCompartment;

public class KBaseCompartment {
  public static String toSymbol(SubcellularCompartment scmp) {
    if (scmp == null) {
      return "z";
    }
    switch (scmp) {
      case BOUNDARY: return "b";
      case EXTRACELLULAR: return "e";
      case CYTOSOL: return "c";
      case PERIPLASM: return "p";
      case MITOCHONDRIA: return "m";
      case NUCLEUS: return "n";
      case VACUOLE: return "v";
      case GOLGI: return "g";
      case RETICULUM: return "r";
      case MITOCHONDRIA_MEMBRANE: return "j";
      case PEROXISOME: return "x";
      case CARBOXYSOME: return "a";
      case LYSOSOME: return "l";
      case PLASTID: return "d";
      case CELL_WALL: return "w";
      default: return "z";
    }
  }
  
  public static<T> Set<T> singleton(T e) {
    Set<T> set = new HashSet<>();
    set.add(e);
    return set;
  }

  public static Set<String> decideCompartment(Set<String> cmps) {
    if (cmps.size() == 2) {
      Iterator<String> it = cmps.iterator();
      String c1 = it.next();
      String c2 = it.next();
      String s1 = c1.substring(0, 1);
      String s2 = c2.substring(0, 1);
      Map<String, String> s = new HashMap<>();
      s.put(s1, c1);
      s.put(s2, c2);
      if (s.containsKey("e") && s.containsKey("c")) {
        return singleton(s.get("c"));
      } else if (s.containsKey("e") && s.containsKey("c")) {
        return singleton(s.get("c"));
      } else if (s.containsKey("m") && s.containsKey("c")) {
        return singleton(s.get("m"));
      } else if (s.containsKey("d") && s.containsKey("c")) {
        return singleton(s.get("d"));
      } else if (s.containsKey("x") && s.containsKey("c")) {
        return singleton(s.get("x"));
      } else if (s.containsKey("v") && s.containsKey("c")) {
        return singleton(s.get("v"));
      } else if (s.containsKey("e") && s.containsKey("d")) {
        return singleton(s.get("d"));
      } else if (s.containsKey("m") && s.containsKey("j")) {
        return singleton(s.get("j"));
      }
      System.out.println(s1 + " " + s2);
    } else {
      return new HashSet<>(cmps);
    }
    return new HashSet<>(cmps);
  }
}
