package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbasebiochem.Media;
import kbasefba.Biomass;
import kbasefba.BiomassCompound;
import kbasefba.FBAModel;
import kbasefba.ModelCompartment;
import kbasefba.ModelCompound;
import kbasefba.ModelReaction;
import kbasefba.ModelReactionProtein;
import kbasefba.ModelReactionProteinSubunit;
import kbasefba.ModelReactionReagent;
import kbasegenomes.Genome;
import pt.uminho.ceb.biosystems.mew.biocomponents.container.components.GeneReactionRuleCI;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.parser.ParseException;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.parser.TokenMgrError;
import pt.uminho.sysbio.biosynthframework.BHashMap;
import pt.uminho.sysbio.biosynthframework.BMap;
import pt.uminho.sysbio.biosynthframework.CompartmentalizedStoichiometry;
import pt.uminho.sysbio.biosynthframework.EntityType;
import pt.uminho.sysbio.biosynthframework.Metabolite;
import pt.uminho.sysbio.biosynthframework.ModelAdapter;
import pt.uminho.sysbio.biosynthframework.Range;
import pt.uminho.sysbio.biosynthframework.SimpleCompartment;
import pt.uminho.sysbio.biosynthframework.SimpleModelReaction;
import pt.uminho.sysbio.biosynthframework.SimpleModelSpecie;
import pt.uminho.sysbio.biosynthframework.biodb.modelseed.ModelSeedMetaboliteEntity;
import pt.uminho.sysbio.biosynthframework.biodb.modelseed.ModelSeedReactionEntity;
import pt.uminho.sysbio.biosynthframework.io.MetaboliteDao;
import pt.uminho.sysbio.biosynthframework.io.ReactionDao;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;
import pt.uminho.sysbio.biosynthframework.util.GprUtils;
import us.kbase.common.service.Tuple11;
import us.kbase.workspace.GetObjectInfo3Params;
import us.kbase.workspace.GetObjectInfo3Results;
import us.kbase.workspace.ObjectSpecification;
import us.kbase.workspace.WorkspaceClient;

@Deprecated
public class FBAModelAdapter implements ModelAdapter {
  
  private static final Logger logger = LoggerFactory.getLogger(FBAModelAdapter.class);
  
  public static String PROD_KBASE_TEMPLATES = "NewKBaseModelTemplates";
  
  public final FBAModel fbaModel;
  
  public Map<String, String> geneIdSwap = new HashMap<> ();
  public Map<String, ModelCompartment> kcmpMap = new HashMap<> ();
  public Map<String, ModelCompound>    kspiMap = new HashMap<> ();
  public Map<String, ModelReaction>    rxnMap = new HashMap<> ();
  public BMap<String, EntityType> krxnType = new BHashMap<> ();
  public BMap<String, EntityType> kspiType = new BHashMap<> ();
  public BMap<String, String> kspiCmp = new BHashMap<> ();
  public Map<String, Biomass> brxnMap = new HashMap<> ();
  protected Set<String> boundarySpecies = new HashSet<> ();
  protected BMap<String, Integer> kspiDegreeMap = new BHashMap<> ();
  
  public Map<String, Object> deleted = new HashMap<> ();
  
  Map<Long, Double> lbMap = new HashMap<> (); //??
  Map<Long, Double> ubMap = new HashMap<> (); //??
  public static double defaultLB = -1000;
  public static double defaultUB =  1000;
  
  public boolean drainBoundaryCondition = true;
  
  public FBAModelAdapter(FBAModel fbaModel) {
    this.fbaModel = fbaModel;
    for (ModelCompartment kcmp : fbaModel.getModelcompartments()) {
//      System.out.println(kcmp);
      String id = kcmp.getId();
      kcmpMap.put(id, kcmp);
    }
    for (ModelCompound mc : fbaModel.getModelcompounds()) {
      String kspiEntry = mc.getId();
      kspiCmp.put(kspiEntry, getEntryFromRef(mc.getModelcompartmentRef()));
      if (kspiMap.put(kspiEntry, mc) != null) {
        logger.warn("duplicate reacton id {}", kspiEntry);
      }
    }
    for (ModelReaction krxn : fbaModel.getModelreactions()) {
      String krxnEntry = krxn.getId();
      if (rxnMap.put(krxnEntry, krxn) != null) {
        logger.warn("duplicate reacton id {}", krxnEntry);
      }
    }
    for (Biomass kbmrxn : fbaModel.getBiomasses()) {
      krxnType.put(kbmrxn.getId(), EntityType.BIOMASS);
      if (brxnMap.put(kbmrxn.getId(), kbmrxn) != null) {
        logger.warn("duplicate reacton id {}", kbmrxn.getId());
      }
    }
    for (String kspiEntry : kspiMap.keySet()) {
      int degree = getSpecieDegree(kspiEntry);
      if (degree == 0) {
        kspiType.put(kspiEntry, EntityType.ERROR);
      } else if (isBoundarySpecie(kspiEntry)) {
//        logger.info("SPECIE_BOUNDARY" + kspiEntry);
        kspiType.put(kspiEntry, EntityType.SPECIE_BOUNDARY);
        boundarySpecies.add(kspiEntry);
      } else {
        kspiType.put(kspiEntry, EntityType.SPECIE);
      }
    }
    
    for (String krxnEntry : rxnMap.keySet()) {
      if (isDrain(krxnEntry)) {
        krxnType.put(krxnEntry, EntityType.DRAIN);
      } else if (isTranslocation(krxnEntry)) {
        krxnType.put(krxnEntry, EntityType.TRANSLOCATION);
      } else {
        krxnType.put(krxnEntry, EntityType.REACTION);
      }
    }
    

  }
  
  public void setGpr(String rxnEntry, String gprString) {
    if (DataUtils.empty(gprString)) {
      gprString = "";
    }
    ModelReaction krxn = this.rxnMap.get(rxnEntry);
    if (krxn == null) {
      krxn = this.rxnMap.get("R_" + rxnEntry);
      if (krxn != null) {
        logger.warn("Reaction[{}] not found. Found alternative match for [R_{}]", rxnEntry, rxnEntry);
      }
    }
    if (krxn == null) {
      krxn = this.rxnMap.get("o" + rxnEntry);
      if (krxn != null) {
        logger.warn("Reaction[{}] not found. Found alternative match for [o{}]", rxnEntry, rxnEntry);
      }
    }
    if (krxn != null) {
      krxn.setImportedGpr(gprString);
    } else {
      logger.warn("Reaction[{}] not found.", rxnEntry);
    }
  }
  
  public void setGpr(Map<String, String> rxnToGrpStringMap) {
    for (String r : rxnToGrpStringMap.keySet()) {
      setGpr(r, rxnToGrpStringMap.get(r));
    }
  }
  
  public void setupModelReactionProteinsFromGpr(String mrxnEntry, String gpr, String genomeRef) {
    ModelReaction mrxn = rxnMap.get(mrxnEntry);
    try {

      GeneReactionRuleCI grrci = new GeneReactionRuleCI(gpr);
      Set<String> genes = KBaseUtils.getGenes(grrci);

      List<ModelReactionProtein> mrpList = new ArrayList<> ();
      if (genes != null) {
        List<ModelReactionProteinSubunit> mrpsLists = new ArrayList<> (); 
        List<String> features = new ArrayList<> ();
        for (String g : genes) {
          features.add(String.format("%s/features/id/%s", genomeRef, g));
        }

        //1985/8/4/features/id/kb|g.220339.CDS.100
        ModelReactionProteinSubunit mrps = new ModelReactionProteinSubunit()
            .withFeatureRefs(features)
            .withTriggering(0L)
            .withRole("")
            .withNote("Imported GPR")
            .withOptionalSubunit(0L);
        mrpsLists.add(mrps);
        ModelReactionProtein mrp = new ModelReactionProtein()
            .withComplexRef("")
            .withModelReactionProteinSubunits(mrpsLists)
            .withNote("").withSource("SBML");
        mrpList.add(mrp);
        
        mrxn.setModelReactionProteins(mrpList);
      }
    } catch (TokenMgrError | ParseException e) {
      logger.warn("invalid imported gpr: {}", gpr);
    }
  }
  
  public static final String COMPOUND_PRE = null;
  public static final String REACTION_PRE = null;
  
  public String buildSpiIdentifier(String id, String cmp) {
    if (COMPOUND_PRE == null) {
      return String.format("%s_%s", id, cmp);
    }
    return String.format("%s_%s_%s", COMPOUND_PRE, id, cmp);
  }
  
  public String buildRxnIdentifier(String id, String cmp) {
//    if (DataUtils.empty(id) || DataUtils.empty(cmp)) {
//      return id;
//    }
    if (REACTION_PRE == null) {
      return String.format("%s_%s", id, cmp);
    }
    return String.format("%s_%s_%s", REACTION_PRE, id, cmp);
  }
  
  public String getModelCompoundCompartmentEntry(ModelCompound kcpd) {
    String[] tokens = kcpd.getModelcompartmentRef().split("/");
    return tokens[tokens.length - 1];
  }
  
  public String getModelReactionCompartmentEntry(ModelReaction krxn) {
    Map<String, Integer> cmpCount = new HashMap<>();
    
    int high = 0;
    
    for (ModelReactionReagent reagent : krxn.getModelReactionReagents()) {
      String ref = reagent.getModelcompoundRef();
      String entry = getEntryFromRef(ref);
      ModelCompound kspi = kspiMap.get(entry);
      if (kspi == null) {
        return null;
      }
      
      String cmp = getModelCompoundCompartmentEntry(kspi);
      CollectionUtils.increaseCount(cmpCount, cmp, 1);
      int v = cmpCount.get(cmp);
      if (high < v) {
        high = v;
      }
    }
    
    BHashMap<String, Integer> bmap = new BHashMap<>(cmpCount);
    Set<String> cmps = bmap.bget(high);
    
    if (cmps == null) {
      return null;
    }
    
    //worst possible solution, fix it later works for now
    if (cmps.contains("c0")) {
      return "c0";
    } else if (cmps.contains("e0")){
      return "e0";
    } else {
      return cmps.iterator().next();
    }
  }
  
  public boolean renameReactionEntry(String from, String to) {
    ModelReaction krxn = rxnMap.get(from);
    
    String rxnCmp = getModelReactionCompartmentEntry(krxn);
    
    if (DataUtils.empty(rxnCmp)) {
      return false;
    }
    
    to = buildRxnIdentifier(to, rxnCmp);
    
    if (!krxn.getId().equals(to) && !rxnMap.containsKey(to)) {
      logger.info("{} -> {}", krxn.getId(), to);
      krxn.setId(to);
      rxnMap.put(to, rxnMap.remove(from));
      krxnType.put(to, krxnType.remove(from));
      return true;
    }
    return false;
  }
  
  public boolean renameMetaboliteEntry(String from, String to) {
    ModelCompound kcpd = kspiMap.get(from);
    to = buildSpiIdentifier(to, getModelCompoundCompartmentEntry(kcpd));
    
    if (!kcpd.getId().equals(to) && !kspiMap.containsKey(to)) {
      logger.info("{} -> {}", kcpd.getId(), to);
      kcpd.setId(to);
      for (ModelReaction krxn : fbaModel.getModelreactions()) {
        for (ModelReactionReagent r : krxn.getModelReactionReagents()) {
          if (r.getModelcompoundRef().endsWith("/" + from)) {
            r.setModelcompoundRef("~/modelcompounds/id/" + to);
          }
        }
      }
      
      for (Biomass kbrxn : fbaModel.getBiomasses()) {
        for (BiomassCompound r : kbrxn.getBiomasscompounds()) {
          if (r.getModelcompoundRef().endsWith("/" + from)) {
            r.setModelcompoundRef("~/modelcompounds/id/" + to);
          }
        }
      }
      
//      kspiDegreeMap.put(to, kspiDegreeMap.get(from));
      kspiCmp.put(to, kspiCmp.remove(from));
      kspiMap.put(to, kspiMap.remove(from));
      
      return true;
    }
    
    return false;
  }
  
  public void renameSpecie(String spiEntry, String rename) {
    
  }
  
  public Set<String> getGenes(String rxnEntry) {
    return null;
  }
  
  public String integrateCompartment(String cmp, String kcmp) {
    return renameComparmentEntry(cmp, kcmp);
  }
  
  public String getReagentCompartmentRef(ModelReactionReagent reagent) {
    String kspiEntry = getEntryFromRef(reagent.getModelcompoundRef());
    ModelCompound kspi = kspiMap.get(kspiEntry);
    
    return kspi.getModelcompartmentRef();
  }
  
  public String decideCompartment(Set<String> cmp) {
    return cmp.iterator().next();
  }
  
  public String renameComparmentEntry(String from, String to) {
    String indexedCmp = null;
    for (ModelCompartment kcmp : fbaModel.getModelcompartments()) {
      String cmpEntry = kcmp.getId();
      if (cmpEntry.equals(from)) {
        logger.debug("BEFOR: {}", kcmp);
        long index = kcmp.getCompartmentIndex();
        if (from.startsWith("z")) {
          index = 0;
          kcmp.setCompartmentIndex(index);
        }
        
        indexedCmp = to + index;
        
        
        
        kcmp.setId(indexedCmp);
        kcmp.setCompartmentRef("~/template/compartments/id/" + to);
        
        
        
        logger.debug("AFTER: {}", kcmp);
        
        for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
          String cmpRef = kcpd.getModelcompartmentRef();
          if (cmpRef.endsWith("/" + from)) {
            logger.trace("BEFOR: {}", kcpd);
            kcpd.setModelcompartmentRef("~/modelcompartments/id/" + indexedCmp);
            logger.trace("AFTER: {}", kcpd);
            kspiCmp.put(kcpd.getId(), indexedCmp);
          }
        }
      }
    }
    
    for (String krxnEntry : rxnMap.keySet()) {
      ModelReaction krxn = rxnMap.get(krxnEntry);
      Set<String> kcmpRefs = new HashSet<> ();
      if (!krxn.getModelReactionReagents().isEmpty()) {
        for (ModelReactionReagent reagent : krxn.getModelReactionReagents()) {
          kcmpRefs.add(getReagentCompartmentRef(reagent));
        }
        krxn.setModelcompartmentRef(decideCompartment(kcmpRefs));        
      }
    }
    
    return indexedCmp;
  }
  
  public Set<String> getBiomasses() {
    Set<String> result = new HashSet<> ();
    for (Biomass kbmrxn : fbaModel.getBiomasses()) {
      result.add(kbmrxn.getName());
    }
    
    return result;
  }
  
  public String convertToBiomass(String rxnEntry) {
    ModelReaction krxn = this.rxnMap.get(rxnEntry);
    if (krxn != null) {
      logger.info("rxn: {}, bio: {}", fbaModel.getModelreactions().size(), fbaModel.getBiomasses().size()); 
      Biomass bm = modelReactionToBiomass(krxn);
      int bioCounter = 1;
      String bioEntry = "bio" + bioCounter++;
      while (brxnMap.containsKey(bioEntry)) {
        bioEntry = "bio" + bioCounter++;
      }
      bm.setId(bioEntry);
      brxnMap.put(bioEntry, bm);
      deleted.put(rxnEntry, krxn);
      rxnMap.remove(rxnEntry);
      fbaModel.getModelreactions().remove(krxn);
      fbaModel.getBiomasses().add(bm);
      
      logger.info("rxn: {}, bio: {}", fbaModel.getModelreactions().size(), fbaModel.getBiomasses().size());
      
      return bioEntry;
    } else {
      logger.warn("rxn {} not found", rxnEntry);
      return null;
    }
  }
  
  public static Biomass modelReactionToBiomass(ModelReaction rxn) {
    List<BiomassCompound> compounds = new ArrayList<> ();
    
    for (ModelReactionReagent r : rxn.getModelReactionReagents()) {
      compounds.add(modelReactionReagentToBiomassCompound(r));
    }
//    other, dna, rna, protein, cellwall, lipid, cofactor, energy
    return new Biomass().withId(rxn.getId())
                        .withOther(0.0)
                        .withDna(0.0)
                        .withRna(0.0)
                        .withProtein(0.0)
                        .withCellwall(0.0)
                        .withLipid(0.0)
                        .withCofactor(0.0)
                        .withEnergy(0.0)
                        .withName(String.format("%s (%s)", rxn.getId(), rxn.getName()))
                        .withBiomasscompounds(compounds);
  }
  
  public static ModelReaction biomassToModelReaction() {
    return new ModelReaction().withAliases(null)
                              .withDblinks(null)
                              .withDirection("")
                              .withImportedGpr("")
                              .withName("")
                              .withModelReactionReagents(null);
  }
  
  public static BiomassCompound modelReactionReagentToBiomassCompound(ModelReactionReagent r) {
    return new BiomassCompound().withCoefficient(r.getCoefficient())
                                .withModelcompoundRef(r.getModelcompoundRef());
  }

  public Media convertToMedia(String mediaId, Map<String, Pair<Double, Double>> drains) {
    Media media = MediaAdapter.buildDefaultEmptyMedia(mediaId);
    MediaAdapter ma = new MediaAdapter(media);
    for (String k : drains.keySet()) {
      if (kspiMap.containsKey(k) && 
          kspiMap.get(k).getDblinks() != null && 
          kspiMap.get(k).getDblinks().containsKey("ModelSeed")) {
        //addModelSeedMetabolite
        List<String> id_ = kspiMap.get(k).getDblinks().get("ModelSeed");
        if (id_ != null && !id_.isEmpty()) {
          String id = id_.iterator().next();
          Pair<Double, Double> b = drains.get(k);
          ma.addModelSeedMetabolite(id, b.getLeft(), b.getRight());
        }
      } else {
        //addCustomMetabolite
        Pair<Double, Double> b = drains.get(k);
        ma.addCustomMetabolite(k, b.getLeft(), b.getRight());
      }
    }
    return media;
  }
  
  public Set<String> removeDrainReactions() {
    Set<String> drains = krxnType.bget(EntityType.DRAIN);
    if (drains == null) {
      drains = new HashSet<> ();
    }
    
    for (String krxnEntry : drains) {
      ModelReaction krxn = this.rxnMap.remove(krxnEntry);
      if (krxn != null) {
        deleted.put(krxnEntry, krxn);
      }
    }
    
    List<ModelReaction> update = new ArrayList<> (this.rxnMap.values());
    this.fbaModel.setModelreactions(update);
    return drains;
  }
  
  public Map<String, Pair<Double, Double>> getDefaultDrains(String extracell) {
    Map<String, Pair<Double, Double>> result = new HashMap<> ();
    Set<String> drains = krxnType.bget(EntityType.DRAIN);
    
    if (drains != null) {
      Map<String, Set<String>> specieToDrain = new HashMap<> ();
      for (String drxnEntry : drains) {
        Map<String, ?> stoich = getStoichiometry(drxnEntry);
        //normalize value / bounds
        for (String spiEntry : stoich.keySet()) {
          CollectionUtils.insertHS(spiEntry, drxnEntry, specieToDrain);
        }
      }
      
      for (String spiEntry : specieToDrain.keySet()) {
        String kcmp = this.kspiCmp.get(spiEntry);
        System.out.println(kcmp + " " + spiEntry);
        if (extracell == null || extracell.equals(kcmp)) {
          int degree = getSpecieDegree(spiEntry);
          logger.trace("[{}] degree: {}", spiEntry, degree);
          if (degree < 2) {
            logger.trace("WARN: discard specie [{}]", spiEntry);
          } else {
            List<Range> b = new ArrayList<> ();
            for (String drxnEntry : specieToDrain.get(spiEntry)) {
              Range bounds = getBounds(drxnEntry);
              Map<String, ?> dstoich = getStoichiometry(drxnEntry);
              
              Double dstoichValue = Double.parseDouble(dstoich.get(spiEntry).toString());
//              logger.trace("[{}] - {} {}: [{}, {}] {}", spiEntry, getSpecieAttribute(spiEntry, "name"), drxnEntry, bounds[0], bounds[1], dstoich);
              double lb = bounds.lb;
              double ub = bounds.ub;
              if (dstoichValue != null) {
                //specie is a product flip bonds
                if (dstoichValue > 0) {
                  double lb_ = ub * -1;
                  double ub_ = lb * -1;
                  lb = lb_;
                  ub = ub_;
                  logger.trace("[{}] FIX: flip bounds => [{}, {}]", spiEntry, lbMap, ubMap);
                }
              } 

              if (bounds.lb < -1000000000) {
                logger.debug("[{}] lb value to high reducing to default", spiEntry);
                lb = defaultLB;
              }
              if (bounds.ub > 1000000000) {
                logger.debug("[{}] ub value to high reducing to default", spiEntry);
                ub = defaultUB;
              }
              
              b.add(new Range(lb, ub));
            }
            
            double lb = 0;
            double ub = 0;
            
            for (Range p : b) {
              lb += p.lb;
              ub += p.ub;
            }
            
            logger.trace("[{}]: [{}, {}]", spiEntry, lb, ub);
            result.put(spiEntry, new ImmutablePair<Double, Double>(lb, ub));
          }
        }
      }
    }

    
    return result;
  }
  
  public static String getEntryFromRef(String ref) {
    String[] tks = ref.split("/");
    String s = tks[tks.length - 1];
    return s;
  }
  
  public Map<String, Double> getStoichiometry(String mrxnEntry) {
    Map<String, Double> result = new HashMap<>();
    ModelReaction mr = this.rxnMap.get(mrxnEntry);
    for (ModelReactionReagent mrr : mr.getModelReactionReagents()) {
      
      result.put(getEntryFromRef(mrr.getModelcompoundRef()), mrr.getCoefficient());
    }
    return result;
  }
  
  public Set<String> products(Map<String, Double> s) {
    Set<String> result = new HashSet<> ();
    
    for (String k : s.keySet()) {
      if (s.get(k) > 0.0) {
        result.add(k);
      }
    }
    
    return result;
  }
  
  public Set<String> reactants(Map<String, Double> s) {
    Set<String> result = new HashSet<> ();
    
    for (String k : s.keySet()) {
      if (s.get(k) < 0.0) {
        result.add(k);
      }
    }
    
    return result;
  }
  
  @Override
  public Range getBounds(String mrxnEntry) {
    ModelReaction mr = this.rxnMap.get(mrxnEntry);
    Double ub = mr.getMaxforflux();
    Double lb = mr.getMaxrevflux();
    if (ub == null) {
      ub = defaultUB;
    }
    if (lb == null) {
      lb = defaultLB;
    } else {
      lb = -1 * lb;
    }
    
    Range result = null;
    try {
      result = new Range(lb, ub);
    } catch (IllegalArgumentException e) {
      logger.warn("Invalid range [{}, {}]", lb, ub);
      result = new Range(-1000.0, 1000.0);
    }
    
    return result;
  }

  @Override
  public String getGpr(String arg0) {
    return rxnMap.get(arg0).getImportedGpr();
  }
  
  public Set<String> getGeneReactions(String gene) {
    Set<String> result = new HashSet<> ();
    for (String r : this.rxnMap.keySet()) {
      List<ModelReactionProtein> mrps = this.rxnMap.get(r).getModelReactionProteins();
      for (ModelReactionProtein mrp : mrps) {
        for (ModelReactionProteinSubunit mrpu : mrp.getModelReactionProteinSubunits()) {
          for (String f : mrpu.getFeatureRefs()) {
            String[] p = f.split("/");
            if (gene.equals(p[p.length - 1])) {
              result.add(r);
            }
          }
        }
      }
    }
    return result;
  }
  
  public Set<String> getGeneReactionsImportedGpr(String gene) {
    Set<String> result = new HashSet<> ();
    for (String r : this.rxnMap.keySet()) {
      String igpr = this.rxnMap.get(r).getImportedGpr();
      if (igpr.contains(gene)) {
        result.add(r);
      }
    }
    return result;
  }

  @Override
  public int getReactionSize(String mrxnEntry) {
    ModelReaction mr = this.rxnMap.get(mrxnEntry);
    return mr.getModelReactionReagents().size();
  }

  @Override
  public String getSpecieCompartment(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer getSpecieDegree(String spiEntry) {
    if (kspiMap.containsKey(spiEntry)) {
      if (!kspiDegreeMap.containsKey(spiEntry)) {
        int deg = 0;
        for (ModelReaction xrxn : rxnMap.values()) {
          for (ModelReactionReagent o : xrxn.getModelReactionReagents()) {
            String s = getEntryFromRef(o.getModelcompoundRef());
            if (spiEntry.equals(s)) {
              deg++;
            }
          }
        }
        kspiDegreeMap.put(spiEntry, deg);
      }
      return kspiDegreeMap.get(spiEntry);
    }
    
    return null;
  }

  @Override
  public boolean isBoundarySpecie(String spiEntry) {
    if (kspiType.containsKey(spiEntry) && 
        EntityType.SPECIE_BOUNDARY.equals(kspiType.get(spiEntry))) {
      return true;
    }
    
    ModelCompound mc = this.kspiMap.get(spiEntry);
    
    
    if (mc.getStringAttributes() != null) {
      String bc = mc.getStringAttributes().get(KBAModelStringAttributes.boundary_condition.toString());
      if (bc != null) {
        return Boolean.parseBoolean(bc);
      }
    }

    
    return false;
  }

  @Override
  public boolean isDrain(String mrxnEntry) {
    if (krxnType.containsKey(mrxnEntry) && 
        EntityType.DRAIN.equals(krxnType.get(mrxnEntry))) {
      return true;
    }
    
    int rsize = getReactionSize(mrxnEntry);
    if (rsize == 1) {
      return true;
    }
    
    Map<String, Double> stoich = getStoichiometry(mrxnEntry);
    
    if (drainBoundaryCondition) {
//      System.out.println(drainBoundaryCondition);
//      System.out.println(stoich.size() + " " + boundarySpecies.size());
      stoich.keySet().removeAll(boundarySpecies);
//      System.out.println(stoich.size());
    }
    
    if (stoich.size() == 1) {
      return true;
    }
    
//    if (products(stoich).size() == 1 && reactants(stoich).size() == 1) {
//      boolean maybeDrain = false;
//      for (String s : stoich.keySet()) {
//        Integer deg = getSpecieDegree(s);
//        if (deg != null && deg == 1) {
//          maybeDrain = true;
//        }
//      }
//      if (maybeDrain && drainDeadEnds) {
//        return true;
//      }
//    }
    
    return false;
  }

  @Override
  public boolean isTranslocation(String mrxnEntry) {
    if (krxnType.containsKey(mrxnEntry) && 
        EntityType.TRANSLOCATION.equals(krxnType.get(mrxnEntry))) {
      return true;
    }
    
    Map<String, Double> stoich = getStoichiometry(mrxnEntry);
    Set<String> cmpSet = new HashSet<> ();
    for (String k : stoich.keySet()) {
      if (this.kspiMap.containsKey(k)) {
        String cmp = this.kspiMap.get(k).getModelcompartmentRef();
        cmpSet.add(cmp);
      }
      
      
    }
    
    return cmpSet.size() != 1;
  }
  
  public static String getTemplateRef(String t, String ws, WorkspaceClient wsClient) throws IOException {
    try {
      List<ObjectSpecification> oparams = new ArrayList<> ();
      oparams.add(new ObjectSpecification().withName(t).withWorkspace(ws));
      GetObjectInfo3Params params = new GetObjectInfo3Params().withObjects(oparams);
      GetObjectInfo3Results res = wsClient.getObjectInfo3(params);
      String ref = null;
      for (Tuple11<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> i : res.getInfos()) {
        ref = KBaseIOUtils.getRefFromObjectInfo(i);
      }
      return ref;
    } catch (Exception e) {
      throw new IOException(e);
    }
  }
  
  public boolean setTemplate(String template, WorkspaceClient wsClient) {
    String templateRef = null;
    try {
      switch (template) {
        case "GramNegModelTemplate":
        case "gramneg": template = "GramNegModelTemplate"; break;
        case "GramPosModelTemplate":
        case "grampos": template = "GramPosModelTemplate"; break;
        case "PlantModelTemplate":
        case "plant": template = "PlantModelTemplate"; break;
        case "CoreModelTemplate":
        case "core": template = "CoreModelTemplate"; break;
        default:  
          logger.warn("unknown template assume core: {}", template);
          template = "CoreModelTemplate"; break;
      }
      templateRef = getTemplateRef(template, PROD_KBASE_TEMPLATES, wsClient);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    if (templateRef != null) {
      this.fbaModel.setTemplateRef(templateRef);
      return true;
    } else {
      return false;
    }
  }

  public Set<String> getReactionGenes(String mrxnEntry) {
    Set<String> result = new HashSet<>();
    
    ModelReaction krxn = this.rxnMap.get(mrxnEntry);
    List<ModelReactionProtein> mrpList = krxn.getModelReactionProteins();
    for (ModelReactionProtein mrp : mrpList) {
//      System.out.println(mrp.getModelReactionProteinSubunits());
      for (ModelReactionProteinSubunit mrps : mrp.getModelReactionProteinSubunits()) {
        for (String refs : mrps.getFeatureRefs()) {
          String gene = FBAModelAdapter.getEntryFromRef(refs);
          result.add(gene);
        }
      }
    }
    
    return result;
  }
  
  @Override
  public SimpleModelReaction<String> getReaction(String mrxnEntry) {
    throw new RuntimeException("not implemented yet!");
//    return null;
  }

  @Override
  public SimpleModelSpecie<String> getSpecies(String spiEntry) {
    ModelCompound kspi = this.kspiMap.get(spiEntry);
    String id = kspi.getId();
    String name = kspi.getName();
    String cmpId = kspi.getModelcompartmentRef();
    SimpleModelSpecie<String> spi = new SimpleModelSpecie<>(id, name, cmpId);
    return spi;
  }
  
  @Override
  public SimpleCompartment<String> getCompartment(String cmpId) {
    SimpleCompartment<String> cmp = null;
    ModelCompartment kcmp = this.kcmpMap.get(cmpId);
    if (kcmp != null) {
      cmp = new SimpleCompartment<String>(cmpId);
      cmp.name = kcmp.getLabel();
    }

    return cmp;
  }

  @Override
  public Set<String> getReactionIds() {
    return new HashSet<>(this.rxnMap.keySet());
  }

  @Override
  public Set<String> getSpeciesIds() {
    return new HashSet<>(this.kspiMap.keySet());
  }
  
  @Override
  public Set<String> getCompartmentIds() {
    return new HashSet<>(this.kcmpMap.keySet());
  }
  
  public<M extends ModelSeedMetaboliteEntity, R extends ModelSeedReactionEntity> void updateMetadata(
      MetaboliteDao<M> cpdDao, ReactionDao<R> rxnDao) {
    for (String id : this.kspiMap.keySet()) {
      ModelCompound kspi = this.kspiMap.get(id);
      String cpdEntry = getEntryFromRef(kspi.getCompoundRef());
      ModelSeedMetaboliteEntity cpd = cpdDao.getMetaboliteByEntry(cpdEntry);
      if (cpd != null) {
        String name = cpd.getName();
        String cmpId = getEntryFromRef(kspi.getModelcompartmentRef());
        String formula = cpd.getFormula();
        String smiles = null;
        String inchikey = null; //cpd.get
        if (name != null) {
          kspi.setName(name + "_" + cmpId);
        }
        if (formula != null) {
          kspi.setFormula(formula);
        }
        if (smiles != null) {
          kspi.setSmiles(smiles);
        }
        if (inchikey != null) {
          kspi.setInchikey(inchikey);
        }
      }
    }
    
    for (String id : this.rxnMap.keySet()) {
      ModelReaction krxn = this.rxnMap.get(id);
      logger.info("{} -> {} {} [{}]", id, krxn.getReactionRef(), krxn.getModelcompartmentRef(), krxn.getDblinks());
      String rxnEntry = getEntryFromRef(krxn.getReactionRef());
      rxnEntry = rxnEntry.substring(0, 8);
      ModelSeedReactionEntity rxn = rxnDao.getReactionByEntry(rxnEntry);
      if (rxn != null) {
        String name = rxn.getName();
        if (name != null) {
          String cmpId = getEntryFromRef(krxn.getModelcompartmentRef());
          krxn.setName(name + "_" + cmpId);
        }
      }
    }
  }

  public void attachGenome(Genome genome, boolean allowNumberLocus) {
    for (String krxnId : this.rxnMap.keySet()) {
      ModelReaction krxn = this.rxnMap.get(krxnId);
      String gprString = krxn.getImportedGpr();
      if (!DataUtils.empty(gprString)) {
        try {
          Set<Set<String>> proteins = GprUtils.getProteins(gprString);
          //        Set<String> genes = KBaseUtils.getGenes(gprString, null, allowNumberLocus);
          if (proteins != null && !proteins.isEmpty()) {
            List<ModelReactionProtein> mrpList = FBAModelFactory.setupModelReactionProteins(proteins, genome, true);
            krxn.setModelReactionProteins(mrpList);
          } else {
            logger.warn("[{}] invalid gpr: {}", krxnId, gprString);
          }
        } catch (Exception e) {
          logger.error("[{}]{} - {}", krxnId, gprString, e.getMessage());
        }
      }
    }
  }

  @Override
  public CompartmentalizedStoichiometry<String, String> getCompartmentalizedStoichiometry(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<String> getReactionGeneIds(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }
}
