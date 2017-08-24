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
import kbasefba.ModelReactionReagent;

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
