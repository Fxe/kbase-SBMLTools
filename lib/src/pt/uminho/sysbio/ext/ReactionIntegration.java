package pt.uminho.sysbio.ext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbasefba.FBAModel;
import kbasefba.ModelCompound;
import kbasefba.ModelReaction;
import kbasefba.ModelReactionReagent;
import pt.uminho.sysbio.biosynth.integration.BiodbService;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.GlobalLabel;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.ReactionMajorLabel;
import pt.uminho.sysbio.biosynthframework.integration.model.ConflictResolver;
import pt.uminho.sysbio.biosynthframework.kbase.FBAModelAdapter;
import pt.uminho.sysbio.biosynthframework.sbml.XmlObject;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlReaction;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlSpecie;

public class ReactionIntegration {
  
  private static final Logger logger = LoggerFactory.getLogger(ReactionIntegration.class);
  
  private final BiodbService biodbService;
  
  public Map<ReactionMajorLabel, Set<String>> exclusion = new HashMap<> ();
  public Map<ReactionMajorLabel, Map<Map<String, Double>, Set<String>>> dictMap = new HashMap<> ();
  public Map<ReactionMajorLabel, MetaboliteMajorLabel> rxnToCpdDbMatchMap = new HashMap<> ();
  public Map<ReactionMajorLabel, ConflictResolver> rMap = new HashMap<> ();
  
  public Map<String, Map<ReactionMajorLabel, String>> imap = new HashMap<> ();
  
  public ReactionIntegration(final BiodbService biodbService) {
    rxnToCpdDbMatchMap.put(ReactionMajorLabel.BiGG2Reaction, MetaboliteMajorLabel.BiGG2);
    rxnToCpdDbMatchMap.put(ReactionMajorLabel.LigandReaction, MetaboliteMajorLabel.LigandCompound);
    rxnToCpdDbMatchMap.put(ReactionMajorLabel.BiGG, MetaboliteMajorLabel.BiGG);
    rxnToCpdDbMatchMap.put(ReactionMajorLabel.ModelSeedReaction, MetaboliteMajorLabel.ModelSeed);
    rxnToCpdDbMatchMap.put(ReactionMajorLabel.Seed, MetaboliteMajorLabel.Seed);
    rxnToCpdDbMatchMap.put(ReactionMajorLabel.MetaCyc, MetaboliteMajorLabel.MetaCyc);
    this.biodbService = biodbService;
  }
  
  public void map(String mrxnEntry, ReactionMajorLabel db, String dbEntry) {
    if (!this.imap.containsKey(mrxnEntry)) {
      this.imap.put(mrxnEntry, new HashMap<ReactionMajorLabel, String> ());
    }
    this.imap.get(mrxnEntry).put(db, dbEntry);
  }
  
  public void exclude(ReactionMajorLabel db, String entry) {
    if (!exclusion.containsKey(db)) {
      exclusion.put(db, new HashSet<String> ());
    }
    exclusion.get(db).add(entry);
  }
  
  public void setupStoichDictionary(ReactionMajorLabel db) {
    logger.info("setup {}", db);
    Map<Long, String> idToEntry = new HashMap<> ();
    Map<Map<String, Double>, Set<String>> stoichDictionary = new HashMap<> ();
    for (long id : biodbService.getIdsByDatabaseAndType(
        db.toString(), GlobalLabel.Reaction.toString())) {
      String rxnEntry = biodbService.getEntryById(id);
      Map<Long, Double> stoich = biodbService.getStoichiometry(id);
      Map<String, Double> estoich = new HashMap<> ();
      for (long id_ : stoich.keySet()) {
        if (!idToEntry.containsKey(id_)) {
          idToEntry.put(id_, biodbService.getEntryById(id_));
        }
        String cpdEntry = idToEntry.get(id_);
        if (cpdEntry == null) {
          cpdEntry = Long.toString(id_);
        }
        if (!this.exclusion.containsKey(db) || !this.exclusion.get(db).contains(cpdEntry)) {
          estoich.put(cpdEntry, stoich.get(id_));
        }
      }
      if (!stoichDictionary.containsKey(estoich)) {
        stoichDictionary.put(estoich, new HashSet<String> ());
      }
      stoichDictionary.get(estoich).add(rxnEntry);
    }
    
    this.dictMap.put(db, stoichDictionary);
  }
  
  public String getSwap(MetaboliteMajorLabel db, String entry, Map<String, Map<MetaboliteMajorLabel, String>> imap) {
    Map<MetaboliteMajorLabel, String> swapMap = imap.get(entry);
    if (swapMap != null && swapMap.get(db) != null) {
      return swapMap.get(db);
    }
    return null;
  }
  
  public void integrate(ReactionMajorLabel db, 
      FBAModel kmodel, Map<String, Map<MetaboliteMajorLabel, String>> imap) {
    MetaboliteMajorLabel cpdDb = rxnToCpdDbMatchMap.get(db);
    if (dictMap.containsKey(db) && cpdDb != null) {
      ConflictResolver resolver = rMap.get(db);
      Set<String> exclude = this.exclusion.get(db);
      if (exclude == null) {
        exclude = new HashSet<> ();
      }
      Map<String, Map<String, Double>> rxnEntries = new HashMap<> ();
      Map<Map<String, Double>, Set<String>> mstoichDictionary = new HashMap<>();
      Map<String, Map<String, Double>> reactions = new HashMap<> ();
      Map<String, Map<String, Double>> treactions = new HashMap<> ();
//      Map<String, Map<String, Double>> xreactions = new HashMap<> ();
      Map<String, String> spiToCmpMap = new HashMap<> ();
      for (ModelCompound spi : kmodel.getModelcompounds()) {
        String spiEntry = spi.getId();
        String spiCmp = spi.getModelcompartmentRef();
        if (spiEntry != null && spiCmp != null) {
          spiToCmpMap.put(spiEntry, spiCmp);
        }
      }
      for (ModelReaction krxn : kmodel.getModelreactions()) {
        Set<String> cmpSet = new HashSet<> ();
        String rxnEntry = krxn.getId();
        Map<String, Double> stoich = new HashMap<> ();
        for (ModelReactionReagent o : krxn.getModelReactionReagents()) {
          String id = FBAModelAdapter.getEntryFromRef(o.getModelcompoundRef());
          if (spiToCmpMap.containsKey(id)) {
            cmpSet.add(spiToCmpMap.get(id));
          }
          double val = o.getCoefficient();
          stoich.put(id, val);
        }

        if (cmpSet.size() == 1) {
          reactions.put(rxnEntry, stoich);
        } else {
          treactions.put(rxnEntry, stoich);
        }
      }
      
      for (String rxnEntry : reactions.keySet()) {
        Map<String, Double> stoichOriginal = reactions.get(rxnEntry);
        Map<String, Double> stoichTranslate = new HashMap<> ();
        for (String id : stoichOriginal.keySet()) {
          double val = stoichOriginal.get(id);
          //do swap
          String swap = getSwap(cpdDb, id, imap);
          if (swap != null) {
            logger.trace("swap {} -> {}", id, swap);
            id = swap;
          }
          if (!exclude.contains(id)) {            
            stoichTranslate.put(id, val);
          }
        }
        
        rxnEntries.put(rxnEntry, stoichTranslate);
        if (!mstoichDictionary.containsKey(stoichTranslate)) {
          mstoichDictionary.put(stoichTranslate, new HashSet<String>());
        }
        mstoichDictionary.get(stoichTranslate).add(rxnEntry);
      }
      
      Map<Map<String, Double>, Set<String>> dbtoichDictionary = dictMap.get(db);
      for (Map<String, Double> stoich : mstoichDictionary.keySet()) {
        Set<String> mrxnSet = mstoichDictionary.get(stoich);
        if (dbtoichDictionary.containsKey(stoich)) {
          Set<String> dbRxns = dbtoichDictionary.get(stoich);
//          System.out.println("yes! " + dbtoichDictionary.get(s));
          for (String mrxnEntry : mrxnSet) {
//            System.out.println("[F] " + mrxnEntry + " " + dbtoichDictionary.get(stoich));
//            System.out.println(dbRxns + " " + resolver + " " + mrxnEntry );
            if (dbRxns != null && dbRxns.size() > 1 && resolver != null) {
              dbRxns = resolver.resolve(mrxnEntry, dbRxns);
            }
            if (dbRxns != null && dbRxns.size() == 1) {
              this.map(mrxnEntry, db, dbRxns.iterator().next());
            }
          }
          rxnEntries.keySet().removeAll(mrxnSet);
        } else {
          Map<String, Double> rstoich = scale(stoich, -1);
          if (dbtoichDictionary.containsKey(rstoich)) {
            Set<String> dbRxns = dbtoichDictionary.get(rstoich);

            for (String mrxnEntry : mrxnSet) {
//              System.out.println("[R] " + mrxnEntry + " " + dbRxns);
              if (dbRxns != null && dbRxns.size() > 1 && resolver != null) {
                dbRxns = resolver.resolve(mrxnEntry, dbRxns);
              }
              if (dbRxns != null && dbRxns.size() == 1) {
                this.map(mrxnEntry, db, dbRxns.iterator().next());
              }
            }
            rxnEntries.keySet().removeAll(mrxnSet);
          }
        }
      }
//      for (String o : rxnEntries.keySet()) {
//        System.out.println(o + " " +  rxnEntries.get(o) + " ?");
//      }
    } else {
      logger.warn("stoichiometry dictionary for {} not found", db);
    }
    

//    System.out.println(mstoichDictionary);
  }
  
  public void integrate(ReactionMajorLabel db, 
      XmlSbmlModel xmodel, Map<String, Map<MetaboliteMajorLabel, String>> imap) {
    MetaboliteMajorLabel cpdDb = rxnToCpdDbMatchMap.get(db);
    if (dictMap.containsKey(db) && cpdDb != null) {
      ConflictResolver resolver = rMap.get(db);
      Set<String> exclude = this.exclusion.get(db);
      if (exclude == null) {
        exclude = new HashSet<> ();
      }
      Map<String, Map<String, Double>> rxnEntries = new HashMap<> ();
      Map<Map<String, Double>, Set<String>> mstoichDictionary = new HashMap<>();
      Map<String, Map<String, Double>> reactions = new HashMap<> ();
      Map<String, Map<String, Double>> treactions = new HashMap<> ();
//      Map<String, Map<String, Double>> xreactions = new HashMap<> ();
      Map<String, String> spiToCmpMap = new HashMap<> ();
      for (XmlSbmlSpecie spi : xmodel.getSpecies()) {
        String spiEntry = spi.getAttributes().get("id");
        String spiCmp = spi.getAttributes().get("compartment");
        if (spiEntry != null && spiCmp != null) {
          spiToCmpMap.put(spiEntry, spiCmp);
        }
      }
      for (XmlSbmlReaction xrxn : xmodel.getReactions()) {
        Set<String> cmpSet = new HashSet<> ();
        String rxnEntry = xrxn.getAttributes().get("id");
        Map<String, Double> stoich = new HashMap<> ();
        for (XmlObject o : xrxn.getListOfReactants()) {
          String id = o.getAttributes().get("species");
          if (spiToCmpMap.containsKey(id)) {
            cmpSet.add(spiToCmpMap.get(id));
          }
          String vStr = o.getAttributes().get("stoichiometry");
          if (vStr == null || !NumberUtils.isParsable(vStr)) {
            vStr = "1.0";
          }
          double val = Double.parseDouble(vStr);
          stoich.put(id, -1 * val);
        }
        for (XmlObject o : xrxn.getListOfProducts()) {
          String id = o.getAttributes().get("species");
          if (spiToCmpMap.containsKey(id)) {
            cmpSet.add(spiToCmpMap.get(id));
          }
          String vStr = o.getAttributes().get("stoichiometry");
          if (vStr == null || !NumberUtils.isParsable(vStr)) {
            vStr = "1.0";
          }
          double val = Double.parseDouble(vStr);
          stoich.put(id, val);
        }
        if (cmpSet.size() == 1) {
          reactions.put(rxnEntry, stoich);
        } else {
          treactions.put(rxnEntry, stoich);
        }
      }
      
      for (String rxnEntry : reactions.keySet()) {
        Map<String, Double> stoichOriginal = reactions.get(rxnEntry);
        Map<String, Double> stoichTranslate = new HashMap<> ();
        for (String id : stoichOriginal.keySet()) {
          double val = stoichOriginal.get(id);
          //do swap
          String swap = getSwap(cpdDb, id, imap);
          if (swap != null) {
            logger.trace("swap {} -> {}", id, swap);
            id = swap;
          }
          if (!exclude.contains(id)) {            
            stoichTranslate.put(id, val);
          }
        }
        
        rxnEntries.put(rxnEntry, stoichTranslate);
        if (!mstoichDictionary.containsKey(stoichTranslate)) {
          mstoichDictionary.put(stoichTranslate, new HashSet<String>());
        }
        mstoichDictionary.get(stoichTranslate).add(rxnEntry);
      }
      
      Map<Map<String, Double>, Set<String>> dbtoichDictionary = dictMap.get(db);
      for (Map<String, Double> stoich : mstoichDictionary.keySet()) {
        Set<String> mrxnSet = mstoichDictionary.get(stoich);
        if (dbtoichDictionary.containsKey(stoich)) {
          Set<String> dbRxns = dbtoichDictionary.get(stoich);
//          System.out.println("yes! " + dbtoichDictionary.get(s));
          for (String mrxnEntry : mrxnSet) {
//            System.out.println("[F] " + mrxnEntry + " " + dbtoichDictionary.get(stoich));
//            System.out.println(dbRxns + " " + resolver + " " + mrxnEntry );
            if (dbRxns != null && dbRxns.size() > 1 && resolver != null) {
              dbRxns = resolver.resolve(mrxnEntry, dbRxns);
            }
            if (dbRxns != null && dbRxns.size() == 1) {
              this.map(mrxnEntry, db, dbRxns.iterator().next());
            }
          }
          rxnEntries.keySet().removeAll(mrxnSet);
        } else {
          Map<String, Double> rstoich = scale(stoich, -1);
          if (dbtoichDictionary.containsKey(rstoich)) {
            Set<String> dbRxns = dbtoichDictionary.get(rstoich);

            for (String mrxnEntry : mrxnSet) {
//              System.out.println("[R] " + mrxnEntry + " " + dbRxns);
              if (dbRxns != null && dbRxns.size() > 1 && resolver != null) {
                dbRxns = resolver.resolve(mrxnEntry, dbRxns);
              }
              if (dbRxns != null && dbRxns.size() == 1) {
                this.map(mrxnEntry, db, dbRxns.iterator().next());
              }
            }
            rxnEntries.keySet().removeAll(mrxnSet);
          }
        }
      }
//      for (String o : rxnEntries.keySet()) {
//        System.out.println(o + " " +  rxnEntries.get(o) + " ?");
//      }
    } else {
      logger.warn("stoichiometry dictionary for {} not found", db);
    }
    

//    System.out.println(mstoichDictionary);
  }
  
  public static<K> Map<K, Double> scale(Map<K, Double> s, double v) {
    Map<K, Double> s_ = new HashMap<> ();
    for (K k : s.keySet()) {
      s_.put(k, s.get(k) * v);
    }
    return s_;
  }
}

