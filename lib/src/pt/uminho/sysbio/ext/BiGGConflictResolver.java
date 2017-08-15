package pt.uminho.sysbio.ext;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import pt.uminho.sysbio.biosynthframework.integration.model.ConflictResolver;

public class BiGGConflictResolver implements ConflictResolver {
  
  public boolean randomSelectOnTie = false;

  @Override
  public Set<String> resolve(String spi, Set<String> spiSet) {
//    System.out.println(spi + "" + spiSet);
    int low = Integer.MAX_VALUE;
    Set<String> lowSet = new HashSet<> ();
    for (String candidate : spiSet) {
      int dist = StringUtils.getLevenshteinDistance(spi, candidate);
      
      if (dist < low) {
        lowSet = new HashSet<> ();
        low = dist;
      }
      if (dist == low) {
        lowSet.add(candidate);
      }
      
//      System.out.println(low + " " + lowSet);
    }
    
    if (randomSelectOnTie && lowSet.size() > 1) {
      Set<String> single = new HashSet<> ();
      single.add(lowSet.iterator().next());
      return single;
    }
    
    return lowSet;
  }

}
