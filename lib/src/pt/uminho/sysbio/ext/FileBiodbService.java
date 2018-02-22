package pt.uminho.sysbio.ext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

import pt.uminho.sysbio.biosynth.integration.BiodbService;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.GlobalLabel;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetabolicModelLabel;
import pt.uminho.sysbio.biosynthframework.BHashMap;
import pt.uminho.sysbio.biosynthframework.BMap;
import pt.uminho.sysbio.biosynthframework.EntityType;
import pt.uminho.sysbio.biosynthframework.SubcellularCompartment;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;

public class FileBiodbService implements BiodbService{
  
  private static final Logger logger = LoggerFactory.getLogger(FileBiodbService.class);
  
  public Map<Long, String> idToEntry = new HashMap<> ();
  public Map<Long, Boolean> idToProxy = new HashMap<> ();
  public BMap<Long, String> idToAlias = new BHashMap<> ();
  private Map<String, Set<Long>> entryToIds = new HashMap<> ();
  public Map<Long, String> idToDatabase = new HashMap<> ();
  public Map<String, Set<Long>> databaseToId = new HashMap<> ();
  private Map<Long, String> idToName = new HashMap<> ();
  private Map<Long, String> idToFormula = new HashMap<> ();
  private Map<Long, Long> idToCmpId = new HashMap<> ();
  public Map<Long, String> cmpIdToAnnotation = new HashMap<> ();
  private Map<Long, Map<Long, Double>> idToStoich = new HashMap<> ();
  
  public Map<Long, List<Pair<Long, Double>>> idToLStoich = new HashMap<>();
  
  private Map<Map<Long, Double>, Set<Long>> stoichToIds = new HashMap<> ();
  private Map<Long, Set<Long>> idToReferences = new HashMap<> ();
  public Map<Long, Long> idToCtrId = new HashMap<> ();
  public Map<Long, Set<Long>> ctrIdToIds = new HashMap<> ();
  public Map<Long, Label> idToType = new HashMap<> ();
  private Map<Long, String> idToRxnType = new HashMap<> ();
  public Map<String, Set<Long>> rxnTypeToRxnIds = new HashMap<> ();
  private Map<Label, Set<Long>> typeToIds = new HashMap<> ();
  public Map<Long, Double[]> idToBounds = new HashMap<> ();
  private Map<Long, String> metaIdToEntry = new HashMap<> ();
  public Map<Long, Set<Long>> stoichToDegree = new HashMap<> ();
  public Map<Long, String> rxnIdToPathwayAlias = new HashMap<> ();
  public Map<Pair<String, String>, Long> entryDbToId = new HashMap<> ();
  public Map<Long, Set<Long>> mcpdIdToSpiIdSet = new HashMap<> ();
  public Map<Long, Boolean> spiIdIsBondary = new HashMap<> ();
  public Map<Long, Boolean> idToReversible = new HashMap<> ();
  
  public Map<Long, Long> modelIdToTxId = new HashMap<> ();
  public Map<Long, Long> idToParent = new HashMap<> ();
  
  public BMap<Long, Long> protIdToTxId = new BHashMap<> ();
  
  private Map<Long, Map<String, Set<Long>>> idToPropertyIds = new HashMap<> ();
  private Map<Long, String> idToKey = new HashMap<> ();
  
  private long itgId = -1;
  
  public FileBiodbService() { }
  
  public FileBiodbService(Map<Long, String> idToEntry, 
      BMap<Long, String> idToAlias,
      Map<Long, String> idToName, 
      Map<Long, String> idToFormula,
      Map<Long, Long> idToCmpId,
      Map<Long, String> cmpIdToAnnotation,
      Map<Long, Set<Long>> idToReferences,
      Map<Long, Map<Long, Double>> idToStoich,
      Map<Long, Long> idToCtrId,
      Map<Long, Label> idToType,
      Map<Long, String> idToDatabase,
      Map<Long, String> idToRxnType,
      Map<Long, Double[]> idToBounds,
      Map<Long, String> metaIdToEntry,
      Map<Long, String> rxnIdToPathwayAlias,
      Map<Long, Set<Long>> mcpdIdToSpiIdSet,
      Map<Long, Boolean> spiIdIsBondary,
      Map<Long, Boolean> idToReversible,
      Map<Long, Long> modelIdToTxId,
      Map<Long, Long> idToParent,
      BMap<Long, Long> protIdToTxId,
      Map<Long, Boolean> idToProxy,
      Map<Long, String> idToKey,
      Map<Long, Map<String, Set<Long>>> idToPropertyIds) { 


    this.idToEntry = idToEntry;
    this.idToAlias = idToAlias;
    this.idToProxy = idToProxy;
    this.entryToIds = CollectionUtils.reverseMap(idToEntry);
    this.idToName = idToName;
    this.idToFormula = idToFormula;
    this.idToReferences = idToReferences;
    this.idToStoich = idToStoich;
    this.stoichToIds = CollectionUtils.reverseMap(idToStoich);
    this.idToCtrId = idToCtrId;
    this.ctrIdToIds = CollectionUtils.reverseMap(idToCtrId);
    this.idToType = idToType;
    this.typeToIds = CollectionUtils.reverseMap(idToType);
    this.idToDatabase = idToDatabase;
    this.databaseToId = CollectionUtils.reverseMap(idToDatabase);
    this.idToRxnType = idToRxnType;
    this.rxnTypeToRxnIds = CollectionUtils.reverseMap(idToRxnType);
    this.idToBounds = idToBounds;
    this.idToCmpId = idToCmpId;
    this.cmpIdToAnnotation = cmpIdToAnnotation;
    this.mcpdIdToSpiIdSet = mcpdIdToSpiIdSet;
    this.metaIdToEntry = metaIdToEntry;
    this.rxnIdToPathwayAlias = rxnIdToPathwayAlias;
    this.spiIdIsBondary = spiIdIsBondary;
    this.idToReversible = idToReversible;
    this.modelIdToTxId = modelIdToTxId;
    this.idToParent = idToParent;
    this.protIdToTxId = protIdToTxId;
    this.idToKey = idToKey;
    this.idToPropertyIds = idToPropertyIds;
  }
  
  
  /**
   * Generic method {@link FileBiodbService#getIdsByDatabaseAndType getIdsByDatabaseAndType}
   * <br>
   * e.g., model -> iMM904, type -> MetabolicModelLabel.MetaboliteSpecie.toString()
   * @param model
   * @return set of specie unique ids
   */
  public Set<Long> getSpecieIdsByModel(String model) {
    return getIdsByDatabaseAndType(model, MetabolicModelLabel.MetaboliteSpecie);
  }
  
  public Set<Long> getReactionIdsByModel(String model) {
    return getIdsByDatabaseAndType(model, MetabolicModelLabel.ModelReaction);
  }
  
  @Override
  public Set<Long> getIdsByDatabaseAndType(String database, String type) {
    return getIdsByDatabaseAndType(database, DynamicLabel.label(type));
  }
  
  /**
   * Filter IDs by database and entity type
   * @param database Database [LigandCompound, LigandReaction, iMM904]
   * @param type [Metabolite, Reaction, MetaboliteSpecie]
   * @return
   */
  public Set<Long> getIdsByDatabaseAndType(String database, Label type) {
    Set<Long> result = new HashSet<> ();
    for (long id : idToEntry.keySet()) {
      String d = idToDatabase.get(id);
//      System.out.println(d);
      Label t = idToType.get(id);
//      if (t == null || d == null) {
//        System.out.println(idToEntry.get(id));
//      }
      if (d != null && d.equals(database) && t.name().equals(type.name())) {
        result.add(id);
      }
    }
    return result;
  }
  
  public void buildStoichDegreeMap() {
    this.stoichToDegree.clear();
    for (long id : this.idToStoich.keySet()) {
      for (long spiId : this.idToStoich.get(id).keySet()) {
        if (!this.stoichToDegree.containsKey(spiId)) {
          this.stoichToDegree.put(spiId, new HashSet<Long> ());
        }
        this.stoichToDegree.get(spiId).add(id);
      }
    }
  }

  @Override
  public String getEntryById(long id) {
    return idToEntry.get(id);
  }
  
  public Set<Long> getIdsByEntry(String entry) {
    return entryToIds.get(entry);
  }

  @Override
  public String getNamePropertyById(long id) {
    return idToName.get(id);
  }

  @Override
  public String getFormulaPropertyById(long id) {
    return idToFormula.get(id);
  }

  @Override
  public Map<Long, Double> getStoichiometry(long id) {
    return idToStoich.get(id);
  }
  
  public String getIdDatabase(long id) {
    return this.idToDatabase.get(id);
  }

  @Override
  public List<Pair<Long, Double>> getRawStoichiometry(long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<Long> getIdsByStoichiometry(Map<Long, Double> s) {
    return stoichToIds.get(s);
  }

  @Override
  public Set<Long> getReferencesBy(long id) {
    return idToReferences.get(id);
  }

  @Override
  public Pair<Set<Long>, Set<Long>> getReactionsByCpd(long cpdId) {
    //slow version !
    Pair<Set<Long>, Set<Long>> result = new ImmutablePair<Set<Long>, Set<Long>>(
        new HashSet<Long> (), new HashSet<Long> ());
    for (long rxnId : idToStoich.keySet()) {
      Map<Long, Double> s = idToStoich.get(rxnId);
      if (s.containsKey(cpdId)) {
        double v = s.get(cpdId);
        if (v < 0) {
          result.getLeft().add(rxnId);
        } else {
          result.getRight().add(rxnId);
        }
      }
    }
    return result;
  }

  @Override
  public boolean isIntegrated(long id) {
    return this.idToCtrId.containsKey(id);
  }

  @Override
  public Long getIntegratedId(long id) {
    return this.idToCtrId.get(id);
  }

  @Override
  public Set<Long> getMembersByCtrId(long id) {
    return this.ctrIdToIds.get(id);
  }

  @Override
  public void setIntegrationId(long itgId) {
    this.itgId = itgId;
  }

  @Override
  public long getIntegrationId() {
    return itgId;
  }

  
  public Set<Long> getUnification(long cpdId) {
    if (idToCtrId.containsKey(cpdId)) {
      long ctrId = idToCtrId.get(cpdId);
      return ctrIdToIds.get(ctrId);
    }
    return new HashSet<> ();
  }
  

  public Set<Long> expandReferences(Set<Long> cpdIdSet) {
    Set<Long> expanded = new HashSet<> ();
    for (long cpdId : cpdIdSet) {
      expanded.addAll(this.getUnification(cpdId));
    }
    return expanded;
  }
  
  private Set<Long> getUnification(long cpdId, long itgId) {
    Long ctrId = this.getIntegratedId(cpdId);
    if (ctrId != null) {
      return ctrIdToIds.get(ctrId);
    }
    return new HashSet<> ();
  }
  
  @Override
  public Map<Set<Long>, Set<Long>> expandReferences(Set<Long> cpdIdSet, long itgId) {
    Map<Set<Long>, Set<Long>> expanded = new HashMap<> ();
    Map<Set<Long>, Set<Long>> uniToCpdId = new HashMap<> ();
    for (long cpdId : cpdIdSet) {
      Set<Long> uni = this.getUnification(cpdId, itgId);
      if (!uniToCpdId.containsKey(uni)) {
        uniToCpdId.put(uni, new HashSet<Long> ());
      }
      uniToCpdId.get(uni).add(cpdId);
    }
    
    for (Set<Long> uni : uniToCpdId.keySet()) {
      Set<Long> cpdIdSet_ = uniToCpdId.get(uni);
      expanded.put(cpdIdSet_, uni);
    }
    return expanded; //expandReferences(cpdIdSet);
  }
  
  public Map<Set<Long>, Double> integrateStoichiometry(
      Map<Set<Long>, Double> cpdStoich, Set<Long> exclude) {
    Map<Set<Long>, Double> integratedStoichiometry = new HashMap<> ();
    
    for (Set<Long> ids : cpdStoich.keySet()) {
      double value = cpdStoich.get(ids);
      Set<Long> expanded = new HashSet<> ();
      expanded.addAll(expandReferences(ids));
      
      //if species are not integrated
      if (expanded.isEmpty()) {
        expanded.addAll(ids);
      }
      
      if (exclude == null 
          || Sets.intersection(expanded, exclude).isEmpty()) {
        integratedStoichiometry.put(expanded, value);
      }
    }
    
    return integratedStoichiometry;
  }
  
  public Map<Set<Long>, Double> spiToCpd(Map<Long, Double> stoich) {
    Map<Set<Long>, Double> a = new HashMap<> ();
    for (long spiId : stoich.keySet()) {
      double value = stoich.get(spiId);
      Set<Long> ids = new HashSet<> ();
      if (idToReferences.containsKey(spiId)
          && !idToReferences.get(spiId).isEmpty()) {
        ids.addAll(idToReferences.get(spiId));
      } else {
        ids.add(spiId);
      }
      a.put(ids, value);
    }
    return a;
  }

  public Pair<Set<Long>, Set<Long>> sortByType(Collection<Long> rxnIds) {
    Set<Long> mrxnIds = new HashSet<> ();
    Set<Long> drxnIds = new HashSet<> ();
    for (long rxnId : rxnIds) {
      Label type = idToType.get(rxnId);
      if (type.equals(GlobalLabel.Reaction)) {
        drxnIds.add(rxnId);
      } else if (type.equals(MetabolicModelLabel.ModelReaction)) {
        mrxnIds.add(rxnId);
      }
    }
    return new ImmutablePair<Set<Long>, Set<Long>>(drxnIds, mrxnIds);
  }
  
  public Set<Long> getAllStoichiometryIds() {
    return new HashSet<> (this.idToStoich.keySet());
  }
  
  public Set<Long> listReactions() {
    return this.idToStoich.keySet();
  }
  
  public Set<Long> listReactionsByDatabase(String db) {
    
    return Sets.intersection(this.typeToIds.get(MetabolicModelLabel.ModelReaction), 
                             Sets.intersection(this.idToEntry.keySet(), 
                                               this.databaseToId.get(db)));
  }
  
  @Override
  public String getSubTypeById(long id) {
    return getSubType(id);
  }
  
  public String getSubType(long rxnId) {
    return this.idToRxnType.get(rxnId);
  }

  public Long getSpecieCompartmentId(long spiId) {
    return this.idToCmpId.get(spiId);
  }
  
  public String getMetaIdEntry(long metaId) {
    return this.metaIdToEntry.get(metaId);
  }
  
  public SubcellularCompartment getCompartmentSubcellularLocation(long cmpId) {
    if (cmpIdToAnnotation.containsKey(cmpId)) {
      return SubcellularCompartment.valueOf(cmpIdToAnnotation.get(cmpId));
    }
    
    return SubcellularCompartment.UNKNOWN;
  }

  @Deprecated
  public Set<String> getAliasFromIdsOld(Set<Long> cpdIds) {    
    return new HashSet<> (getAliasFromIds(cpdIds));
  }
  
  public Set<String> getSpecieAlias(long spiId) {
    Label type = idToType.get(spiId);
    if (!type.equals(MetabolicModelLabel.MetaboliteSpecie)) {
      return null;
    }
    Set<Long> refs = getReferencesBy(spiId);
    Set<Long> expRefs = expandReferences(refs);
    Set<String> aliases = new HashSet<> ();
    for (long refId : expRefs) {
      Long ctrId = idToCtrId.get(refId);
      String alias = metaIdToEntry.get(ctrId);
//      System.out.println(spiId + ", " + alias + ", " + ctrId + ", " + refId);
      aliases.add(alias);
    }
    
    return aliases;
  }

  @Override
  public List<String> getAliasFromIds(Collection<Long> ids) {
    List<String> aliases = new ArrayList<> ();
    for (Long id : ids) {
      String alias = null;
      Label type = idToType.get(id);
      if (type.equals(MetabolicModelLabel.MetaboliteSpecie)) {
        Set<String> a = getSpecieAlias(id);
        if (a.size() != 1) {
          logger.warn("invalid alias {}:{} -> {}", type, id, a);
        }
        alias = StringUtils.join(a, ' ');
      } else {
        Long ctrId = idToCtrId.get(id);
        alias = metaIdToEntry.get(ctrId);
//        System.out.println(type + " " + alias);
      }
      aliases.add(alias);
    }
    
    return aliases;
  }

  @Override
  public Map<Long, Set<Long>> getSpeciesFromReferences(Set<Long> cpdIdSet) {
    System.err.println("Not implemented !!!!");
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<Long> getSpecieUnification(long spiId, long itgId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getDatabaseById(long id) {
    return this.idToDatabase.get(id);
  }

  @Override
  public SubcellularCompartment getSpecieSubcellularLocation(long spiId) {
    //check if spiId overrides cmpId
    
    Long cmpId = this.getSpecieCompartmentId(spiId);
    if (cmpId != null) {
      return this.getCompartmentSubcellularLocation(cmpId);
    }
    return null;
  }

  @Override
  public Map<Long, Long> getMetaboliteUnificationMap() {
    return this.idToCtrId;
  }

  @Override
  public Map<Long, Long> getReactionUnificationMap() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<Long, Long> getModelSpecieUnificationMap() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<Long, Long> getModelReactionUnificationMap() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Long getIdByEntryAndDatabase(String entry, String database) {
    Set<Long> ids = this.getIdsByEntry(entry);
    
    if (ids == null) {
      return null;
    }
    
    for (long id : ids) {
      String db = this.getDatabaseById(id);
      if (db == null) {
        logger.info("db not found for {} {}", id, idToEntry.get(id));
      }
      if (this.getDatabaseById(id).equals(database)) {
        return id;
      }
    }
    return null;
  }

  @Override
  public String getTypeById(long id) {
    Label l = this.idToType.get(id);
    
    if (l != null) {
      return l.toString();
    }
    
    return null;
  }

  public Set<Long> getIdsByAlias(String entry) {
    return idToAlias.bget(entry);
  }

  @Override
  public Long getModelTaxonomyId(long modelId) {
    return modelIdToTxId.get(modelId);
  }

  @Override
  public Long getTaxonomyParentId(long txId) {
    return idToParent.get(txId);
  }

  @Override
  public Set<Long> getAllTaxonomyIds() {
    Set<Long> a = new HashSet<> ();
    for (long id : idToEntry.keySet()) {
      if (idToDatabase.containsKey(id) && 
          "EntrezTaxonomy".equals(idToDatabase.get(id))) {
        a.add(id);
      }
    }
    return a;
  }

  @Override
  public Set<Long> getAllTaxonomyProteins(long txId) {
    Set<Long> result = protIdToTxId.bget(txId);
    if (result == null) {
      result = new HashSet<> ();
    }
    return result;
  }

  @Override
  public Double[] getReactionBounds(long rxnId) {
    return this.idToBounds.get(rxnId);
  }

  @Override
  public EntityType getEntityType(long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> T getAttribute(long id, String attribute, Class<T> clazz) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isProxy(long id) {
    return idToProxy.get(id);
  }

  @Override
  public String getEntityProperty(long id, String propertyType) {
    Map<String, Set<Long>> props = idToPropertyIds.get(id);
    switch (propertyType) {
      case "alias": return idToAlias.get(id);
      case "formula":
        if (props != null && props.containsKey("formula")) {
          Set<Long> fids = props.get("formula");
          if (fids != null && !fids.isEmpty()) {
            return idToKey.get(fids.iterator().next());
          }
        }
        break;
      case "name": return idToName.get(id);
      case "smiles":
        if (props != null && props.containsKey("smiles")) {
          Set<Long> fids = props.get("smiles");
          if (fids != null && !fids.isEmpty()) {
            return idToKey.get(fids.iterator().next());
          }
        }
        break;
      case "inchikey":
        if (props != null && props.containsKey("inchikey")) {
          Set<Long> fids = props.get("inchikey");
          if (fids != null && !fids.isEmpty()) {
            return idToKey.get(fids.iterator().next());
          }
        }
        break;
      default: logger.warn("unknown property [{}]", propertyType); break;
    }
    return null;
  }

  @Override
  public Set<Long> getIdByProperty(String property, String propertyType) {
    switch (propertyType) {
      case "alias":
        if (idToAlias.bget(property) == null) {
          return new HashSet<>();
        }
        return new HashSet<> (idToAlias.bget(property));
      default: logger.warn("unknown property [{}]", propertyType); break;
    }
    return new HashSet<> ();
  }
}
