package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbasefba.Biomass;
import kbasefba.BiomassCompound;
import kbasefba.FBAModel;
import kbasefba.ModelCompartment;
import kbasefba.ModelCompound;
import kbasefba.ModelReaction;
import kbasefba.ModelReactionReagent;
import pt.uminho.sysbio.biosynth.integration.BiodbService;

public class KBaseIntegration {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseIntegration.class);
  
  public FBAModel fbaModel;
  public Map<String, String> compartmentMapping = new HashMap<> ();
  public String rename = null;
  public boolean autoIntegration = false;
  public boolean fillMetadata = false;
  public KBaseBiodbContainer biodbContainer;
  
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
  
  public static final String COMPOUND_PRE = "M";
  
  public String buildIdentifier(String id, String cmp) {
    return String.format("%s_%s_%s", COMPOUND_PRE, id, cmp);
  }
  
  public String getModelCompoundCompartmentEntry(ModelCompound kcpd) {
    String[] tokens = kcpd.getModelcompartmentRef().split("/");
    return tokens[tokens.length - 1];
  }
  
  public void renameMetaboliteEntry(String from, String to) {
    for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
      String cpdEntry = kcpd.getId();
      if (cpdEntry.equals(from)) {
        to = buildIdentifier(to, getModelCompoundCompartmentEntry(kcpd));
        
        logger.trace("{} -> {}", kcpd.getId(), to);
        
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
  
  public void integrate() {
    for (String cmpOld : compartmentMapping.keySet()) {
      String cmpSwap = compartmentMapping.get(cmpOld);
      renameComparmentEntry(cmpOld, cmpSwap);
    }
    
    //integration
    //BiGG, BiGG2, HMDB, LigandCompound, MetaCyc, ModelSeed, Seed
    if (rename != null) {
      for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
        List<String> dbRefs = kcpd.getDblinks().get(rename);
        if (dbRefs != null && !dbRefs.isEmpty()) {
          renameMetaboliteEntry(kcpd.getId(), dbRefs.iterator().next());
        }
      }
    }
    
    if (fillMetadata && biodbContainer.biodbService != null) {
      BiodbService biodbService = biodbContainer.biodbService;
      for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
        List<String> dbRefs = kcpd.getDblinks().get("ModelSeed");
        if (dbRefs != null && !dbRefs.isEmpty()) {
          String cpdEntry = dbRefs.iterator().next();
          System.out.println(cpdEntry);
          Long cpdId = biodbService.getIdByEntryAndDatabase(cpdEntry, "ModelSeed");
          if (cpdId != null) {
            String name = biodbService.getNamePropertyById(cpdId);
            String formula = biodbService.getEntityProperty(cpdId, "formula");
            String smiles = biodbService.getEntityProperty(cpdId, "smiles");
            String inchikey = biodbService.getEntityProperty(cpdId, "inchikey");
            System.out.println(name + " " + formula + " " + smiles);
            if (name != null) {
              kcpd.setName(name);
            }
            if (formula != null) {
              kcpd.setFormula(formula);
            }
            if (smiles != null) {
              kcpd.setSmiles(smiles);
            }
            if (inchikey != null) {
              kcpd.setInchikey(inchikey);
            }
          }
//          renameMetaboliteEntry(kcpd.getId(), dbRefs.iterator().next());
        }
      }
//      biodbService.getNamePropertyById(id)
    }
  }
}
