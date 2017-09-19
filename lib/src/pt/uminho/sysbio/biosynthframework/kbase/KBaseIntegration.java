package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import kbasefba.ModelReactionReagent;
import kbasegenomes.Genome;
import pt.uminho.sysbio.biosynth.integration.BiodbService;
import pt.uminho.sysbio.biosynthframework.EntityType;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;
import sbmltools.MockData;

public class KBaseIntegration {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseIntegration.class);
  
  public KBaseIntegrationReport report = null;
  
  public final FBAModel fbaModel;
  public final FBAModelAdapter adapter;
  public Map<String, String> compartmentMapping = new HashMap<> ();
  public Set<String> biomassSet = new HashSet<> ();
  public String rename = null;
  public String template = null;
  public boolean autoIntegration = false;
  public boolean fillMetadata = false;
  public String mediaName = null;
  /**
   * rxn -> new gpr <br> <h1>Example</h1><br> "rxn1000" : "b1000 and b1002"
   */
  public Map<String, String> gprOverride = new HashMap<> ();
  
  /**  
   * gene -> new gene gpr <br> <h1>Example</h1><br> "peg.1" : "Bsu0001"
   */
  public Map<String, String> geneSwap = new HashMap<> ();
  
  public KBaseBiodbContainer biodbContainer;
  public Media defaultMedia = null;
  public String genomeRef = null;
  public Genome genome = null;
  
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
  
  public void integrateGprGenes() {
    if (this.genomeRef != null) {
      fbaModel.setGenomeRef(this.genomeRef);
      if (genome != null) {
        for (String mrxnEntry : adapter.rxnMap.keySet()) {
          ModelReaction mr = adapter.rxnMap.get(mrxnEntry);
          String gprString = mr.getImportedGpr();
          if (!DataUtils.empty(gprString)) {
            Set<String> genes = KBaseUtils.getGenes(gprString, null);
            if (genes != null && !genes.isEmpty()) {
              List<ModelReactionProtein> mrpList = FBAModelFactory.setupModelReactionProteins(genes, genome, fbaModel.getGenomeRef());
              mr.setModelReactionProteins(mrpList);
            }
//            adapter.setupModelReactionProteinsFromGpr(mrxnEntry, gpr, genomeRef);            
          }
        }
      }
    }
  }
  
  public void integrate() {
    
    logger.info("[GPR Override]");
    for (String rxn : gprOverride.keySet()) {
      ModelReaction krxn = adapter.rxnMap.get(rxn);
      if (krxn != null && gprOverride.get(rxn) != null) {
        krxn.setImportedGpr(gprOverride.get(rxn));
      }
    }
    
    logger.info("[Biomass Convertion]");
    for (String b : biomassSet) {
      String brxnEntry = adapter.convertToBiomass(b);
      if (brxnEntry != null) {
        
      }
    }
    
    logger.info("[Compartment Mapping]");
    for (String cmpOld : compartmentMapping.keySet()) {
      String cmpSwap = compartmentMapping.get(cmpOld);
      
      
      
      this.report.compartmentReport.cmpName.put(cmpOld, "");
      
      
      for (ModelCompartment mcmp : this.fbaModel.getModelcompartments()) {
        if (mcmp.getId().equals(cmpOld)) {
          this.report.compartmentReport.cmpName.put(cmpOld, mcmp.getLabel());
        }
      }
      
      String indexedCmp =  adapter.integrateCompartment(cmpOld, cmpSwap);
      
      this.report.compartmentReport.cmpName.put(indexedCmp, "");
      this.report.compartmentReport.rename.put(cmpOld, indexedCmp);
    }
    
    //integration
    //BiGG, BiGG2, HMDB, LigandCompound, MetaCyc, ModelSeed, Seed
    if (rename != null && !rename.equals("none")) {
      //needs a better db resolver
      if (rename.equals("modelseed")) {
        rename = "ModelSeed";
      }
      if (rename.equals("BiGG") || rename.equals("bigg")) {
        rename = "BiGG2";
      }
      if (rename.equals("KEGG") || rename.equals("kegg")) {
        rename = "LigandCompound";
      }
      
      logger.info("rename: {}", this.rename);
      
      for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
        List<String> dbRefs = kcpd.getDblinks().get(rename);
        if (dbRefs != null && !dbRefs.isEmpty()) {
          String ref = dbRefs.iterator().next();
          String prev = kcpd.getId();
          if (adapter.renameMetaboliteEntry(prev, dbRefs.iterator().next())) {
            report.spiTranslationReport.translationMap.put(prev, kcpd.getId());
          }
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
    
    if (fillMetadata && biodbContainer != null && biodbContainer.biodbService != null) {
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
      
      if (dfDrains != null && !dfDrains.isEmpty()) {
        Set<String> drains = adapter.removeDrainReactions();
        logger.trace("[DRAINS] Removed reactions: {}", drains);
        Media media = adapter.convertToMedia(mediaName, dfDrains);
        defaultMedia = media;
        
        for (String d : drains) {
          report.drainReport.deletedReactions.put(d, new HashMap<String, Double> ());
        }
        
        for (String d : dfDrains.keySet()) {
          Pair<Double, Double> p = dfDrains.get(d);
          report.drainReport.media.put(d, new double[]{p.getLeft(), p.getRight()});
        }  
      } else {
        logger.warn("unable to detect default media");
      }
      
      
    }
    
    integrateGprGenes();
  }
}
