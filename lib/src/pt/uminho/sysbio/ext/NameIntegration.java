package pt.uminho.sysbio.ext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynthframework.io.FileImportKb;

public class NameIntegration {
  
  private static final Logger logger = LoggerFactory.getLogger(NameIntegration.class);
  
  /**
   * 
   * @return Map Compound[Set] -> Name
   */
  public static Map<Set<String>, String> buildNameDictionary() {
    logger.info("build name dictionary ...");
    Map<Long, String> nameIdToName = new HashMap<> ();
    Map<Long, Set<Long>> nameIdToCpdSet = new HashMap<> ();
    FileImportKb.importNameData(nameIdToName, nameIdToCpdSet);
    Map<Set<String>, String> nameDictionary = new HashMap<> ();
    for (long nameId : nameIdToName.keySet()) {
      Set<String> g = new HashSet<> ();
      for (long cpdId : nameIdToCpdSet.get(nameId)) {
        g.add(Long.toString(cpdId));
      }
      nameDictionary.put(g, nameIdToName.get(nameId).trim().toLowerCase());
    }
    
    return nameDictionary;
  }
}
