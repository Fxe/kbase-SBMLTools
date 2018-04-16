package sbmltools.test;

import java.util.HashSet;
import java.util.Set;

import pt.uminho.sysbio.biosynthframework.io.MetaboliteDao;
import pt.uminho.sysbio.biosynthframework.io.ReactionDao;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;

public class TestModelSeedDao {
  
  public static void testCpds() {
    MetaboliteDao<?> cpdDao = KBaseConfig.getModelSeedCpdDao();
    Set<Object> cpds = new HashSet<>();
    for (String e : cpdDao.getAllMetaboliteEntries()) {
      Object o = cpdDao.getMetaboliteByEntry(e);
      cpds.add(o);
    }
    
    System.out.println("Compounds: " + cpds.size());
  }
  
  public static void testRxns() {
    ReactionDao<?> rxnDao = KBaseConfig.getModelSeedRxnDao();
    Set<Object> rxns = new HashSet<>();
    for (String e : rxnDao.getAllReactionEntries()) {
      Object o = rxnDao.getReactionByEntry(e);
      rxns.add(o);
    }
    
    System.out.println("Reactions: " + rxns.size());
  }
  
  public static void main(String[] args) {
    testCpds();
    testRxns();
  }
}
