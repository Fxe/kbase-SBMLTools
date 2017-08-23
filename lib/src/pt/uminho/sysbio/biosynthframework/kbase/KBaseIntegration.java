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
import sbmltools.MockData;

public class KBaseIntegration {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseIntegration.class);
  
  public FBAModel fbaModel;
  public Map<String, String> compartmentMapping = new HashMap<> ();
  public String rename = null;
  public boolean autoIntegration = false;
  public boolean fillMetadata = false;
  public String mediaName = null;
  public KBaseBiodbContainer biodbContainer;
  public Object defaultMedia = null;
  public String genomeRef = null;
  
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
  public static final String REACTION_PRE = "R";
  
  public String buildIdentifier(String id, String cmp) {
    return String.format("%s_%s_%s", COMPOUND_PRE, id, cmp);
  }
  
  public String buildRxnIdentifier(String id) {
    return String.format("%s_%s", REACTION_PRE, id);
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
  
  public void renameReactionEntry(String from, String to) {
    for (ModelReaction krxn : fbaModel.getModelreactions()) {
      String rxnEntry = krxn.getId();
      if (rxnEntry.equals(from)) {
        logger.info("{} -> {}", krxn.getId(), to);
//        to = buildIdentifier(to, getModelCompoundCompartmentEntry(kcpd));
//        to = buildRxnIdentifier(rxnEntry);
      }
    }
  }
  
  public void integrate() {
    if (this.genomeRef != null) {
      fbaModel.setGenomeRef(this.genomeRef);
    }
    
    for (String cmpOld : compartmentMapping.keySet()) {
      String cmpSwap = compartmentMapping.get(cmpOld);
      renameComparmentEntry(cmpOld, cmpSwap);
    }
    
    //integration
    //BiGG, BiGG2, HMDB, LigandCompound, MetaCyc, ModelSeed, Seed
    if (rename != null) {
      
      logger.info("rename: {}", this.rename);
      
      for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
        List<String> dbRefs = kcpd.getDblinks().get(rename);
        if (dbRefs != null && !dbRefs.isEmpty()) {
          renameMetaboliteEntry(kcpd.getId(), dbRefs.iterator().next());
        }
      }
      
      for (ModelReaction krxn : fbaModel.getModelreactions()) {
        List<String> dbRefs = krxn.getDblinks().get(rename);
        if (dbRefs != null && !dbRefs.isEmpty()) {
          renameReactionEntry(krxn.getId(), dbRefs.iterator().next());
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
//            System.out.println(name + " " + formula + " " + smiles);
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
    
    if (this.mediaName != null) {
      Object media = MockData.mockMedia();
      defaultMedia = media;
    }
  }
}
