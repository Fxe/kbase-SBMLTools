package pt.uminho.sysbio.ext;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import pt.uminho.sysbio.biosynthframework.integration.model.ConflictResolver;

public class ModelSeedReactionConflictResolver implements ConflictResolver {

  @Override
  public Set<String> resolve(String spi, Set<String> spiSet) {
    Map<Integer, String> tmap = new TreeMap<>();
    for (String id : spiSet) {
      int nseq = Integer.parseInt(id.replace("rxn", "0"));
      tmap.put(nseq, id);
    }
    int low = tmap.keySet().iterator().next();
    Set<String> single = new HashSet<> ();
    single.add(tmap.get(low));
    
    return single;
  }

}
