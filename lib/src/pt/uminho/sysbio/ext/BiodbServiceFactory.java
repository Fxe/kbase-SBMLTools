package pt.uminho.sysbio.ext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

import pt.uminho.sysbio.biosynth.integration.BiodbService;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.GlobalLabel;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetabolicModelLabel;
import pt.uminho.sysbio.biosynthframework.BHashMap;
import pt.uminho.sysbio.biosynthframework.BMap;
import pt.uminho.sysbio.biosynthframework.Reaction;
import pt.uminho.sysbio.biosynthframework.io.FileImportKb;

public class BiodbServiceFactory {
  
  private static final Logger logger = LoggerFactory.getLogger(BiodbServiceFactory.class);
  
  private Map<Long, Map<String, Set<Long>>> idToPropertyIds = new HashMap<> ();
  private Map<Long, String> idToKey = new HashMap<> ();
  private Map<Long, String> idToEntry = new HashMap<> ();
  private Map<Long, Boolean> idToProxy = new HashMap<> ();
  private BMap<Long, String> idToAlias = new BHashMap<> ();
  private Map<Long, String> idToName = new HashMap<> ();
  private Map<Long, String> idToFormula = new HashMap<> ();
  private Map<Long, String> idToRxnType = new HashMap<> ();
  private Map<Long, Map<Long, Double>> idToStoich = new HashMap<> ();
  private Map<Long, Double[]> idToBounds = new HashMap<> ();
//  private Map<Map<Long, Double>, Set<Long>> stoichToIds = new HashMap<> ();
  private Map<Long, Set<Long>> idToReferences = new HashMap<> ();
  private Map<Long, Long> uniMap = new HashMap<> ();
  private Map<Long, Label> idToType = new HashMap<> ();
  private Map<Long, String> idToDatabase = new HashMap<> ();
  private Map<Long, Long> idToCmpId = new HashMap<> ();
  private Map<Long, Set<Long>> mcpdIdToSpiIdSet = new HashMap<> ();
  private Map<Long, String> cmpIdToAnnotation = new HashMap<> ();
  private Map<Long, String> metaIdToAlias = new HashMap<> ();
  private Map<Long, String> rxnIdToPathwayAlias = new HashMap<> ();
  private Map<Long, Boolean> spiIdIsBondary = new HashMap<> ();
  private Map<Long, Boolean> idToReversible = new HashMap<> ();
  private Map<Long, Long> modelIdToTxId = new HashMap<> ();
  private Map<Long, Long> idToParent = new HashMap<> ();
  private BMap<Long, Long> protIdToTxId = new BHashMap<> ();
  
//  private GraphDatabaseService graphDatabaseService;
  
  private final long AUTO_ID = -1;
  
  public BiodbService build(GraphDatabaseService service) {
    //Neo4jBiodbDataServiceImpl a;
    return null;
  }
  
  public BiodbServiceFactory withReaction(Reaction rxn) {
    //check if is AUTO_ID
    if (rxn.getId() == AUTO_ID) {
      
    }
//    rxn.getEntry();
    return this;
  }
  
//  public BiodbServiceFactory withSBML(String path, String modelId) {
//    logger.info("load model {}", modelId);
//    
//    
//    
//    return this;
//  }
  
  public BiodbServiceFactory withModel(String modelEntry) {
    
    logger.info("load model {}", modelEntry);
    Map<Long, String> cmpIdToAnnotation_ = new HashMap<> ();
    Map<Long, String> cpdIdToEntry = new HashMap<> ();
    
    for (long id : FileImportKb.importModelSpi(modelEntry, idToEntry, 
                                             idToName, idToFormula, 
                                             idToReferences, 
                                             idToCmpId, 
                                             idToEntry,
                                             cmpIdToAnnotation_,
                                             cpdIdToEntry, mcpdIdToSpiIdSet,
                                             idToRxnType, spiIdIsBondary
                                             )) {
      idToType.put(id, MetabolicModelLabel.MetaboliteSpecie);
      idToDatabase.put(id, modelEntry);
    }
    for (long cpdId : cpdIdToEntry.keySet()) {
      idToType.put(cpdId, MetabolicModelLabel.ModelMetabolite);
      idToDatabase.put(cpdId, modelEntry);
    }
    this.idToEntry.putAll(cpdIdToEntry);
    cmpIdToAnnotation.putAll(cmpIdToAnnotation_);
    for (long id : cmpIdToAnnotation_.keySet()) {
      idToType.put(id, GlobalLabel.SubcellularCompartment);
      idToDatabase.put(id, modelEntry);
    }
    for (long id : FileImportKb.importModelRxn(modelEntry, idToEntry, 
                                             idToName, rxnIdToPathwayAlias,
                                             idToStoich, idToRxnType, 
                                             idToBounds, idToReversible)) {
      idToType.put(id, MetabolicModelLabel.ModelReaction);
      idToDatabase.put(id, modelEntry);
    }
    
    logger.info("loaded model {}", modelEntry);
    
    return this;
  }
  
  public BiodbServiceFactory withMetaboliteDatabases() {
    Map<String, Map<String, Map<String, Object>>> cpdData = new HashMap<> ();
    for (long cpdId : FileImportKb.importDatabaseCpd(cpdData, idToAlias)) {
      idToType.put(cpdId, GlobalLabel.Metabolite);
    }
    for (String k : cpdData.keySet()) {
      for (String cpdEntry : cpdData.get(k).keySet()) {
        //        Pair<String, String> entry = new ImmutablePair<>(k, cpdEntry);
        Long cpdId = (long) cpdData.get(k).get(cpdEntry).get("id");
        String cpdName = (String) cpdData.get(k).get(cpdEntry).get("name");
        String referencesStr = (String) cpdData.get(k).get(cpdEntry).get("references");
        boolean proxy = (boolean) cpdData.get(k).get(cpdEntry).get("proxy");
//        idToAlias.put(cpdId, value)
        idToReferences.put(cpdId, new HashSet<Long> ());
        if (referencesStr !=  null && !referencesStr.trim().isEmpty()) {
          for (String refId : referencesStr.split(" ")) {
            idToReferences.get(cpdId).add(Long.parseLong(refId));
          }
        }
        idToProxy.put(cpdId, proxy);
        idToEntry.put(cpdId, cpdEntry);
        idToDatabase.put(cpdId, k);
        idToName.put(cpdId, cpdName);
//        idToReferences.put(cpdId, value)
        //        cpdIdToCpdEntry.put(cpdId, entry);
      }
    }
    
    return this;
  }
  
  public BiodbServiceFactory withReactionDatabase(String database) {
//    Set<Long> ids = FileImportKb.importDatabaseRxn(
//        database, idToEntry, idToStoich, idToName);
//    for (long id : ids) {
//      idToType.put(id, GlobalLabel.Reaction);
//      idToDatabase.put(id, database);
//    }
    return this;
  }
  
  public BiodbServiceFactory usingIntegration(long itgId) {
    Map<Long, Long> umap =FileImportKb.importUnificationMap(itgId);
    uniMap.putAll(umap);
//    Map<Long, String> metaIdToAlias = new HashMap<> ();
    Map<Long, String> dummy = new HashMap<> ();
    FileImportKb.importIntegrationMetadata(itgId, metaIdToAlias, metaIdToAlias, dummy);
    return this;
  }
  
  public BiodbServiceFactory usingModelIntegration(long itgId) {
    Map<Long, Long> umap = FileImportKb.importModelUnificationMap(itgId);
    uniMap.putAll(umap);
    return this;
  }
  
  public long getNextId() {
    long n = -1;
    for (long id : idToEntry.keySet()) {
      if (id > n) {
        n = id;
      }
    }
    return n + 1;
  }
  
  /**
   * 
   * @param swapPairs
   * @param delete Delete folded reaction
   * @return
   */
  public BiodbServiceFactory foldReactions(Long[][] swapPairs, boolean delete) {
    logger.info("folding reactions ...");
    Map<Long, Long> swapPairMap = new HashMap<> ();
    Map<Long, List<Long>> swapMap = new HashMap<> ();
    for (int i = 0; i < swapPairs.length; i+=4) {
      long a = swapPairs[i][0];
      long b = swapPairs[i + 1][0];
      List<Long> aSwap = Arrays.asList(swapPairs[i + 2]);
      List<Long> bSwap = Arrays.asList(swapPairs[i + 3]);
      swapPairMap.put(a, b);
      swapMap.put(a, aSwap);
      swapMap.put(b, bSwap);
    }
    Set<Long> toDelete = new HashSet<> ();
    Set<Long> stoichIds = new HashSet<> (this.idToStoich.keySet());
    for (long id : stoichIds) {
      Map<Long, Double> stoich = this.idToStoich.get(id);
      //check for possible swaps
      Set<Long> swaps = Sets.intersection(stoich.keySet(), swapPairMap.keySet());
      if (!swaps.isEmpty()) {
        Map<Long, Double> lhs = new HashMap<> ();
        Map<Long, Double> rhs = new HashMap<> ();
        for (long compId : stoich.keySet()) {
          double v = stoich.get(compId);
          if (v > 0) {
            rhs.put(compId, v);
          } else if (v < 0) {
            lhs.put(compId, v);
          } else {
            logger.warn("zero value stoich");
          }
        }
        String e = idToEntry.get(id);
        String d = idToDatabase.get(id);
        String n = idToName.get(id);
        String subtype = idToRxnType.get(id);
        Label tt = idToType.get(id);
//        System.out.println(id + " " + idToEntry.get(id));
//        System.out.println(ReactionPrinter.mapToString(stoich, idToEntry));
        boolean hasValidSwaps = false;
        for (long swapId : swaps) {
//          System.out.println(swapId + " " + idToEntry.get(swapId));
          long swapIdOp = swapPairMap.get(swapId);
          if (lhs.containsKey(swapId) && rhs.containsKey(swapIdOp) || 
              rhs.containsKey(swapId) && lhs.containsKey(swapIdOp)) {
            for (int i = 0; i < swapMap.get(swapId).size(); i++) {
              Map<Long, Double> stoichSwaped = new HashMap<> ();
              stoichSwaped.putAll(stoich);
              double aValue = stoichSwaped.remove(swapId);
              double bValue = stoichSwaped.remove(swapIdOp);
              long aId = swapMap.get(swapId).get(i);
              long bId = swapMap.get(swapIdOp).get(i);
              stoichSwaped.put(aId, aValue);
              stoichSwaped.put(bId, bValue);
              
              //gen new id
              long id_ = getNextId();
              //gen entry
              String rxnEntry = e.concat("_").concat(String.format("%d_%d", aId, bId));
              idToEntry.put(id_, rxnEntry);
              idToDatabase.put(id_, d);
              idToName.put(id_, n);
              idToRxnType.put(id_, "UNFOLD");
              idToType.put(id_, tt);
              idToStoich.put(id_, stoichSwaped);
              logger.debug("Generated reaction [{}]{}@{}, {}, {}, {}, {}", id_, rxnEntry, d, n, subtype, tt, stoichSwaped);
//              idToEntry.put(key, value)
//              System.out.println(ReactionPrinter.mapToString(stoichSwaped, idToEntry));
              hasValidSwaps = true;
            }
          }
        }
        if (hasValidSwaps && delete){
          toDelete.add(id);
        }
      }
    }
    for (long id : toDelete) {
      String e = idToEntry.remove(id);
      Map<Long, Double> s = idToStoich.remove(id);
      String d = idToDatabase.remove(id);
      String n = idToName.remove(id);
      String t = idToRxnType.remove(id);
      Label tt = idToType.remove(id);
      logger.debug("DELETE [{}]{}@{} {}, {}, {}, {}", id, e, d, n, t, tt, s);
    }
    return this;
  }
  
  public BiodbServiceFactory withNCBITaxonomy() {
    for (long id : FileImportKb.importNCBITaxonomy(
        modelIdToTxId, idToEntry, idToName, idToParent)) {
      this.idToDatabase.put(id, GlobalLabel.EntrezTaxonomy.toString());
    }
    return this;
  }
  
  public BiodbServiceFactory withMetaboliteFormulas() {
    Map<Long, Set<Long>> idToFormulas = new HashMap<> ();
    FileImportKb.importMetaboliteFormulas(idToFormulas, idToKey);
    for (long id : idToFormulas.keySet()) {
      if (!idToPropertyIds.containsKey(id)) {
        idToPropertyIds.put(id, new HashMap<String, Set<Long>> ());
        
      }
      if (!idToPropertyIds.get(id).containsKey("formula")) {
        idToPropertyIds.get(id).put("formula", new HashSet<Long> ());
      }
      idToPropertyIds.get(id).get("formula").addAll(idToFormulas.get(id));
    }
    
    return this;
  }

  public BiodbServiceFactory withMetaboliteSmiles() {
    Map<Long, Set<Long>> idToPropIds = new HashMap<> ();
    FileImportKb.importMetaboliteSmiles(idToPropIds, idToKey);
    for (long id : idToPropIds.keySet()) {
      if (!idToPropertyIds.containsKey(id)) {
        idToPropertyIds.put(id, new HashMap<String, Set<Long>> ());
        
      }
      if (!idToPropertyIds.get(id).containsKey("smiles")) {
        idToPropertyIds.get(id).put("smiles", new HashSet<Long> ());
      }
      idToPropertyIds.get(id).get("smiles").addAll(idToPropIds.get(id));
    }
    
    return this;
  }
  
  public BiodbServiceFactory withMetaboliteInchiKeys() {
    Map<Long, Set<Long>> idToPropIds = new HashMap<> ();
    FileImportKb.importMetaboliteInchiKeys(idToPropIds, idToKey);
    for (long id : idToPropIds.keySet()) {
      if (!idToPropertyIds.containsKey(id)) {
        idToPropertyIds.put(id, new HashMap<String, Set<Long>> ());
        
      }
      if (!idToPropertyIds.get(id).containsKey("inchikey")) {
        idToPropertyIds.get(id).put("inchikey", new HashSet<Long> ());
      }
      idToPropertyIds.get(id).get("inchikey").addAll(idToPropIds.get(id));
    }
    
    return this;
  }
  
  public BiodbServiceFactory withTaxonomyProteins() {
    for (long id : FileImportKb.importTaxonomyGenes(idToEntry, protIdToTxId)) {
      this.idToDatabase.put(id, GlobalLabel.Protein.toString());
    }
//    for (long id : FileImport.importNCBITaxonomy(
//        modelIdToTxId, idToEntry, idToName, idToParent)) {
//      this.idToType.put(id, GlobalLabel.EntrezTaxonomy);
//    }
    return this;
  }
  
  public FileBiodbService build() {
//  System.out.println(cmpIdToAnnotation);
  return new FileBiodbService(idToEntry, idToAlias, idToName, idToFormula, 
                              idToCmpId, cmpIdToAnnotation,
                              idToReferences, idToStoich, uniMap, idToType, 
                              idToDatabase, idToRxnType, idToBounds, 
                              metaIdToAlias, rxnIdToPathwayAlias, mcpdIdToSpiIdSet,
                              spiIdIsBondary, idToReversible, modelIdToTxId, 
                              idToParent, protIdToTxId, idToProxy, 
                              idToKey, idToPropertyIds);
}









}
