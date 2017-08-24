package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class FBAModelAdapter {
  
  private static final Logger logger = LoggerFactory.getLogger(FBAModelAdapter.class);
  
  public final FBAModel fbaModel;
  
  public Map<String, String> geneIdSwap = new HashMap<> ();
  public Map<String, ModelReaction> rxnMap = new HashMap<> ();
  public Map<String, Biomass> brxnMap = new HashMap<> ();
  
  public Map<String, Object> deleted = new HashMap<> ();
  
  public FBAModelAdapter(FBAModel fbaModel) {
    this.fbaModel = fbaModel;
    for (ModelReaction krxn : fbaModel.getModelreactions()) {
      if (rxnMap.put(krxn.getId(), krxn) != null) {
        logger.warn("duplicate reacton id {}", krxn.getId());
      }
    }
    for (Biomass kbmrxn : fbaModel.getBiomasses()) {
      if (brxnMap.put(kbmrxn.getId(), kbmrxn) != null) {
        logger.warn("duplicate reacton id {}", kbmrxn.getId());
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
}
