package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;
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
import pt.uminho.sysbio.biosynthframework.util.DataUtils;
import sbmltools.MockData;

public class KBaseIntegration {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseIntegration.class);
  
  public final FBAModel fbaModel;
  public final FBAModelAdapter adapter;
  public Map<String, String> compartmentMapping = new HashMap<> ();
  public Set<String> biomassSet = new HashSet<> ();
  public String rename = null;
  public boolean autoIntegration = false;
  public boolean fillMetadata = false;
  public String mediaName = null;
  public KBaseBiodbContainer biodbContainer;
  public Object defaultMedia = null;
  public String genomeRef = null;
  public KBaseGenome genome = null;
  
  public KBaseIntegration(final FBAModel fbaModel) {
    this.fbaModel = fbaModel;
    this.adapter = new FBAModelAdapter(fbaModel);
  }
  
  public static final String REACTION_PRE = "R";
  
  public String buildRxnIdentifier(String id) {
    return String.format("%s_%s", REACTION_PRE, id);
  }
  
  public String getModelCompoundCompartmentEntry(ModelCompound kcpd) {
    String[] tokens = kcpd.getModelcompartmentRef().split("/");
    return tokens[tokens.length - 1];
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
      if (genome != null) {
        for (String mrxnEntry : adapter.rxnMap.keySet()) {
          ModelReaction mrxn = adapter.rxnMap.get(mrxnEntry);
          String gpr = mrxn.getImportedGpr();
          if (!DataUtils.empty(gpr)) {
            adapter.setupModelReactionProteinsFromGpr(mrxnEntry, gpr, genomeRef);            
          }
        }
      }
    }
    
    for (String b : biomassSet) {
      adapter.convertToBiomass(b);
    }
    
    for (String cmpOld : compartmentMapping.keySet()) {
      String cmpSwap = compartmentMapping.get(cmpOld);
      adapter.integrateCompartment(cmpOld, cmpSwap);
    }
    
    //integration
    //BiGG, BiGG2, HMDB, LigandCompound, MetaCyc, ModelSeed, Seed
    if (rename != null) {
      logger.info("rename: {}", this.rename);
      
      for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
        List<String> dbRefs = kcpd.getDblinks().get(rename);
        if (dbRefs != null && !dbRefs.isEmpty()) {
          System.out.println(kcpd.getId() + " " + dbRefs.iterator().next());
          adapter.renameMetaboliteEntry(kcpd.getId(), dbRefs.iterator().next());
//          renameMetaboliteEntry(kcpd.getId(), dbRefs.iterator().next());
        }
      }
      
      for (ModelReaction krxn : fbaModel.getModelreactions()) {
        List<String> dbRefs = krxn.getDblinks().get(rename);
        if (dbRefs != null && !dbRefs.isEmpty()) {
          System.out.println(krxn.getId() + " " + dbRefs.iterator().next());
//          renameReactionEntry(krxn.getId(), dbRefs.iterator().next());
        }
      }
    }
    
    if (fillMetadata && biodbContainer.biodbService != null) {
      BiodbService biodbService = biodbContainer.biodbService;
      for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
        List<String> dbRefs = kcpd.getDblinks().get("ModelSeed");
        if (dbRefs != null && !dbRefs.isEmpty()) {
          String cpdEntry = dbRefs.iterator().next();
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
    
    mediaName = fbaModel.getId() + ".media";
    if (this.mediaName != null) {
      Map<String, Pair<Double, Double>> dfDrains = adapter.getDefaultDrains();
      Object media = adapter.convertToMedia(mediaName, dfDrains);
      defaultMedia = media;
    }
  }
}
