package pt.uminho.sysbio.biosynthframework.kbase;

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
import pt.uminho.ceb.biosystems.mew.biocomponents.container.components.GeneReactionRuleCI;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.parser.ParseException;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.parser.TokenMgrError;
import pt.uminho.sysbio.biosynthframework.BHashMap;
import pt.uminho.sysbio.biosynthframework.BMap;
import pt.uminho.sysbio.biosynthframework.EntityType;
import pt.uminho.sysbio.biosynthframework.ModelAdapter;
import pt.uminho.sysbio.biosynthframework.sbml.XmlObject;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlReaction;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;

public class FBAModelAdapter implements ModelAdapter {
  
  private static final Logger logger = LoggerFactory.getLogger(FBAModelAdapter.class);
  
  public final FBAModel fbaModel;
  
  public Map<String, String> geneIdSwap = new HashMap<> ();
  public Map<String, ModelCompound> kspiMap = new HashMap<> ();
  public Map<String, ModelReaction> rxnMap = new HashMap<> ();
  public BMap<String, EntityType> krxnType = new BHashMap<> ();
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
    for (ModelCompound mc : fbaModel.getModelcompounds()) {
      String kspiEntry = mc.getId();
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
    
    for (String krxnEntry : rxnMap.keySet()) {
      if (isDrain(krxnEntry)) {
        krxnType.put(krxnEntry, EntityType.DRAIN);
      } else {
        krxnType.put(krxnEntry, EntityType.REACTION);
      }
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
    if (REACTION_PRE == null) {
      return String.format("%s_%s", id, cmp);
    }
    return String.format("%s_%s_%s", REACTION_PRE, id, cmp);
  }
  
  public String getModelCompoundCompartmentEntry(ModelCompound kcpd) {
    String[] tokens = kcpd.getModelcompartmentRef().split("/");
    return tokens[tokens.length - 1];
  }
  
  public void renameMetaboliteEntry(String from, String to) {
    for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
      String cpdEntry = kcpd.getId();
      if (cpdEntry.equals(from)) {
        to = buildSpiIdentifier(to, getModelCompoundCompartmentEntry(kcpd));
        
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
      }
    }
  }
  
  public void renameSpecie(String spiEntry, String rename) {
    
  }
  
  public Set<String> getGenes(String rxnEntry) {
    return null;
  }
  
  public void integrateCompartment(String cmp, String kcmp) {
    renameComparmentEntry(cmp, kcmp);
  }
  
  public void renameComparmentEntry(String from, String to) {
    for (ModelCompartment kcmp : fbaModel.getModelcompartments()) {
      String cmpEntry = kcmp.getId();
      if (cmpEntry.equals(from)) {
        logger.debug("BEFOR: {}", kcmp);
        long index = kcmp.getCompartmentIndex();
        if (from.startsWith("z")) {
          index = 0;
          kcmp.setCompartmentIndex(index);
        }
        
        kcmp.setId(to + index);
        kcmp.setCompartmentRef("~/template/compartments/id/" + to);
        
        logger.debug("AFTER: {}", kcmp);
        
        for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
          String cmpRef = kcpd.getModelcompartmentRef();
          if (cmpRef.endsWith("/" + from)) {
            logger.trace("BEFOR: {}", kcpd);
            kcpd.setModelcompartmentRef("~/modelcompartments/id/" + to + index);
            logger.trace("AFTER: {}", kcpd);
          }
        }
      }
    }
  }
  
  public Set<String> getBiomasses() {
    Set<String> result = new HashSet<> ();
    for (Biomass kbmrxn : fbaModel.getBiomasses()) {
      result.add(kbmrxn.getName());
    }
    
    return result;
  }
  
  public void convertToBiomass(String rxnEntry) {
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
    } else {
      logger.warn("rxn {} not found", rxnEntry);
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
        List<String> id_ = kspiMap.get(k).getDblinks().get("ModelSeed");
        if (id_ != null && !id_.isEmpty()) {
          String id = id_.iterator().next();
          Pair<Double, Double> b = drains.get(k);
          ma.addModelSeedMetabolite(id, b.getLeft(), b.getRight());
        }
      }
    }
    return media;
  }
  
  public Map<String, Pair<Double, Double>> getDefaultDrains() {
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
        int degree = getSpecieDegree(spiEntry);
        logger.trace("[{}] degree: {}", spiEntry, degree);
        if (degree < 2) {
          logger.trace("WARN: discard specie [{}]", spiEntry);
        } else {
          List<double[]> b = new ArrayList<> ();
          for (String drxnEntry : specieToDrain.get(spiEntry)) {
            double[] bounds = getBounds(drxnEntry);
            Map<String, ?> dstoich = getStoichiometry(drxnEntry);
            
            Double dstoichValue = Double.parseDouble(dstoich.get(spiEntry).toString());
//            logger.trace("[{}] - {} {}: [{}, {}] {}", spiEntry, getSpecieAttribute(spiEntry, "name"), drxnEntry, bounds[0], bounds[1], dstoich);
            
            if (dstoichValue != null) {
              //specie is a product flip bonds
              if (dstoichValue > 0) {
                double lb_ = bounds[1] * -1;
                double ub_ = bounds[0] * -1;
                bounds[0] = lb_;
                bounds[1] = ub_;
                logger.trace("[{}] FIX: flip bounds => [{}, {}]", spiEntry, lbMap, ubMap);
              }
            } 

            if (bounds[0] < -1000000000) {
              logger.debug("[{}] lb value to high reducing to default", spiEntry);
              bounds[0] = defaultLB;
            }
            if (bounds[1] > 1000000000) {
              logger.debug("[{}] ub value to high reducing to default", spiEntry);
              bounds[1] = defaultUB;
            }
            
            b.add(bounds);
          }
          
          double lb = 0;
          double ub = 0;
          
          for (double[] p : b) {
            lb += p[0];
            ub += p[1];
          }
          
          logger.trace("[{}]: [{}, {}]", spiEntry, lb, ub);
          result.put(spiEntry, new ImmutablePair<Double, Double>(lb, ub));
        }
      }
      
    }

    
    return result;
  }
  
  public String getEntryFromRef(String ref) {
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
  public double[] getBounds(String mrxnEntry) {
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
    return new double[]{lb , ub};
  }

  @Override
  public String getGpr(String arg0) {
    // TODO Auto-generated method stub
    return null;
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
  public boolean isBoundarySpecie(String arg0) {
    // TODO Auto-generated method stub
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
      stoich.keySet().removeAll(boundarySpecies);
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
  public boolean isTranslocation(String arg0) {
    // TODO Auto-generated method stub
    return false;
  }
}
