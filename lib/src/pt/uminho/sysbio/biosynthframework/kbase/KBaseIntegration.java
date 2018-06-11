package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

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
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.EntityType;
import pt.uminho.sysbio.biosynthframework.kbase.genome.KbaseGenomeUtils;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;
import sbmltools.MockData;

public class KBaseIntegration {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseIntegration.class);
  
  public KBaseIntegrationReport report = null;
  
  public final FBAModel fbaModel;
  public final FBAModelAdapter adapter;
  public Map<String, String> compartmentMapping = new HashMap<> ();
  public Map<String, String> manualMapping = new HashMap<> ();
  public Set<String> biomassSet = new HashSet<> ();
  public String rename = null;
  public String template = null;
  public boolean autoIntegration = false;
  public boolean fillMetadata = false;
  public boolean fixIdToKBase = false;
  public boolean allowNumberId = true;
  public String mediaName = null;
  
  public KBaseBiodbContainer biodbContainer;
  public Media defaultMedia= null;
  public String genomeRef = null;
  public Genome genome = null;
  
  public KBaseGenomeReport greport = null;
  /**
   * rxn -> new gpr <br> <h1>Example</h1><br> "rxn1000" : "b1000 and b1002"
   */
  public Map<String, String> gprOverride = new HashMap<> ();
  
  
  public Map<String, String> cpdOverride = new HashMap<> ();
  
  /**  
   * gene -> new gene gpr <br> <h1>Example</h1><br> "peg.1" : "Bsu0001"
   */
  public Map<String, String> geneSwap = new HashMap<> ();
  

  
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
  
  public KBaseGenomeReport integrateGprGenes(boolean allowNumberLocus) {
    KBaseGenomeReport report = null;
    
    if (this.genomeRef != null) {
      fbaModel.setGenomeRef(this.genomeRef);
      if (genome != null) {
        report = new KBaseGenomeReport();
        report.bestMatch = genome.getScientificName();
        report.bestScore = -1;
        report.addMatchGenome(genome.getId(), genome.getScientificName(), -1);
        
        Set<String> deleted = new HashSet<> ();
        Set<String> added = new HashSet<> ();
        Set<String> model = new HashSet<> ();
        for (String mrxnEntry : adapter.rxnMap.keySet()) {
          ModelReaction mr = adapter.rxnMap.get(mrxnEntry);
          String gprString = mr.getImportedGpr();
          if (!DataUtils.empty(gprString)) {
            Set<String> genes = KBaseUtils.getGenes(gprString, null, allowNumberLocus);
            if (genes != null && !genes.isEmpty()) {
              List<ModelReactionProtein> mrpList = FBAModelFactory.setupModelReactionProteins(genes, genome, fbaModel.getGenomeRef());
              mr.setModelReactionProteins(mrpList);
              
              Set<String> kgenes = KBaseUtils.getGenes(mrpList);
              
              for (String g : kgenes) {
                report.mgeneToFeature.put(g, null);
              }
              for (String g : genes) {
                report.mgenes.put(g, null);
              }
              added.addAll(kgenes);
              model.addAll(genes);
            } else {
              logger.warn("[{}] invalid gpr: {}", mrxnEntry, gprString);
            }
          }
        }
        
//        deleted.addAll(Sets.difference(model, added));
//        
//        report.mgeneToFeature.addAll(added);
      }
    }
    
    return report;
  }

  public static String fix(String id) {
    if (id == null) {
      return null;
    }

    id = id.trim();
    while (!Character.isLetter(id.charAt(0)) && 
           !Character.isDigit(id.charAt(0))) {
      id = id.substring(1);
    }
    while (!Character.isLetter(id.charAt(id.length() - 1)) && 
           !Character.isDigit(id.charAt(id.length() - 1))) {
      id = id.substring(0, id.length() - 1);
    }
    while (id.contains("__")) {
      id = id.replace("__", "_");
    }
    id = id.replaceAll("_", "-");

    return id;
  }
  
  public void integrate() {
    
    logger.info("[GPR Override]");
    adapter.setGpr(gprOverride);
//    for (String rxn : gprOverride.keySet()) {
//      ModelReaction krxn = adapter.rxnMap.get(rxn);
//      if (krxn == null) {
//        krxn = adapter.rxnMap.get("R_" + rxn);
//        if (krxn != null) {
//          logger.warn("Reaction[{}] not found. Found alternative match for [R_{}]", rxn, rxn);
//        }
//      }
//      if (krxn != null && gprOverride.get(rxn) != null) {
//        krxn.setImportedGpr(gprOverride.get(rxn));
//      } else {
//        logger.warn("Reaction[{}] not found.", rxn);
//      }
//    }
//    KBaseSearchApiGenomeIntegration a;
    
    for (String cpdId : cpdOverride.keySet()) {
      String msId = cpdOverride.get(cpdId);
      if (!DataUtils.empty(cpdId) && !DataUtils.empty(msId)) {
        for (ModelCompound kspi : this.fbaModel.getModelcompounds()) {
          if (kspi.getId().equals(cpdId.trim())) {
            if (kspi.getDblinks().containsKey(MetaboliteMajorLabel.ModelSeed.toString())) {
              kspi.getDblinks().get(MetaboliteMajorLabel.ModelSeed.toString()).clear();
            }
            List<String> single = new ArrayList<>();
            single.add(msId);
            kspi.getDblinks().put(MetaboliteMajorLabel.ModelSeed.toString(), single);
            kspi.setCompoundRef(String.format("~/template/compounds/id/%s", msId));
          }
        }
      }
    }
    
    logger.info("[Biomass Convertion]");
    for (String b : biomassSet) {
      String brxnEntry = adapter.convertToBiomass(b);
      if (brxnEntry != null) {
//        logger.warn("Reaction[{}] not found.", rxn);
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
    

    Set<ModelCompound> renamedSpi = new HashSet<>();
    Set<ModelReaction> renamedRxn = new HashSet<>();
    
    //integration
    //BiGG, BiGG2, HMDB, LigandCompound, MetaCyc, ModelSeed, Seed
    if (rename != null && !rename.equals("none")) {
      //needs a better db resolver
      String cpdDatabase = rename;
      String rxnDatabase = rename;
      if (rename.equals("modelseed")) {
        cpdDatabase = "ModelSeed";
        rxnDatabase = "ModelSeedReaction";
      }
      if (rename.equals("BiGG") || rename.equals("bigg")) {
        cpdDatabase = "BiGG2";
        rxnDatabase = "BiGG";
      }
      if (rename.equals("KEGG") || rename.equals("kegg")) {
        cpdDatabase = "LigandCompound";
        rxnDatabase = "LigandReaction";
      }
      
      logger.info("rename: {}", this.rename);
      
      for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
        List<String> dbRefs = kcpd.getDblinks().get(cpdDatabase);
        if (dbRefs != null && !dbRefs.isEmpty()) {
          String ref = dbRefs.iterator().next();
          String prev = kcpd.getId();
          if (adapter.renameMetaboliteEntry(prev ,ref)) {
            renamedSpi.add(kcpd);
            report.spiTranslationReport.translationMap.put(prev, kcpd.getId());
          }
        }
      }
      
      for (ModelReaction krxn : fbaModel.getModelreactions()) {
        List<String> dbRefs = krxn.getDblinks().get(rxnDatabase);
        if (dbRefs != null && !dbRefs.isEmpty()) {
          String ref = dbRefs.iterator().next();
          String prev = krxn.getId();
          if (adapter.renameReactionEntry(prev, ref)) {
            renamedRxn.add(krxn);
            report.rxnTranslationReport.translationMap.put(prev, krxn.getId());
          }
          
        }
      }
    }
    
    if (rename != null && !rename.equals("none") && fixIdToKBase) {
      for (ModelCompound kcpd : fbaModel.getModelcompounds()) {
        if (!renamedSpi.contains(kcpd)) {
          String prev = kcpd.getId();
          String id = fix(kcpd.getId());
          if (adapter.renameMetaboliteEntry(prev, id)) {
            report.spiTranslationReport.translationMap.put(prev, id);
          }
        }
      }
      for (ModelReaction krxn : fbaModel.getModelreactions()) {
        if (!renamedRxn.contains(krxn)) {
          String prev = krxn.getId();
          String id = fix(krxn.getId());
          if (adapter.renameReactionEntry(prev, id)) {
            report.rxnTranslationReport.translationMap.put(prev, id);
          }
        }
      }
    }
    
    if (fillMetadata) {
      adapter.updateMetadata(KBaseConfig.getModelSeedCpdDao(), 
                             KBaseConfig.getModelSeedRxnDao());
    }
    
//    mediaName = fbaModel.getId() + ".media";
    
    if (this.mediaName != null) {
      String extracell = null;
      if (compartmentMapping.values().contains("e")) {
        extracell = "e0";
      }
      
      Map<String, Pair<Double, Double>> dfDrains = adapter.getDefaultDrains(extracell);
      
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
    
    logger.info("[GPR Gene Integration]");
    greport = integrateGprGenes(allowNumberId);
  }
}
