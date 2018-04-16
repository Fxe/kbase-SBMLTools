package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynth.integration.BiodbService;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.ReactionMajorLabel;
import pt.uminho.sysbio.biosynthframework.BHashMap;
import pt.uminho.sysbio.biosynthframework.BMap;
import pt.uminho.sysbio.biosynthframework.integration.ReferencePropagation;
import pt.uminho.sysbio.biosynthframework.integration.model.BiGG2AliasMultiMatchResolver;
import pt.uminho.sysbio.biosynthframework.integration.model.BoundaryConflictResolver;
import pt.uminho.sysbio.biosynthframework.integration.model.ConnectedComponents;
import pt.uminho.sysbio.biosynthframework.integration.model.ConnectedComponentsIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.DictionaryBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.FirstDegreeReferences;
import pt.uminho.sysbio.biosynthframework.integration.model.IdBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.IntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.IntegrationMap;
import pt.uminho.sysbio.biosynthframework.integration.model.KBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.ModelSeedMultiMatchResolver;
import pt.uminho.sysbio.biosynthframework.integration.model.NameBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.SearchTable;
import pt.uminho.sysbio.biosynthframework.integration.model.SearchTableFactory;
import pt.uminho.sysbio.biosynthframework.integration.model.SimpleStringMatchEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.SpecieIntegrationFacade;
import pt.uminho.sysbio.biosynthframework.integration.model.TrieIdBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.XmlReferencesBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.io.FileImportKb;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReportResultAdapter;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlReaction;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlSpecie;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;
import pt.uminho.sysbio.ext.BiGGConflictResolver;
import pt.uminho.sysbio.ext.MethoBuilder;
import pt.uminho.sysbio.ext.ModelSeedReactionConflictResolver;
import pt.uminho.sysbio.ext.ReactionIntegration;

public class KBaseModelSeedIntegration {
  
  public static class KBaseMappingResult {
    public Map<String, Map<MetaboliteMajorLabel, String>> species = new HashMap<> ();
    public Map<String, Map<ReactionMajorLabel, String>> reactions = new HashMap<> ();
  }
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseModelSeedIntegration.class);
  
  public String biodbDataPath;
  public String curationFilePath;
  public Map<String, String> spiToModelSeedReference = new HashMap<> ();
  public Map<String, String> rxnToModelSeedReference = new HashMap<> ();
  
  public SearchTable<MetaboliteMajorLabel, String> searchTable = null;
  private ConnectedComponents<String> curation = null;
  private MethoBuilder builder;
  private final ReactionIntegration reactionIntegration;
  
  private final KBaseBiodbContainer biodbContainer;
  
  public KBaseModelSeedIntegration(String biodbDataPath, String curationPath, final KBaseBiodbContainer biodbContainer) {
    this.biodbDataPath = biodbDataPath;
    this.curationFilePath = curationPath;
    this.biodbContainer = biodbContainer;
    // /data/biobase/
    FileImportKb.EXPORT_PATH = biodbDataPath;
    this.reactionIntegration = new ReactionIntegration(this.biodbContainer.biodbService);
    setup();
  }
  
  @SuppressWarnings("deprecation")
  public void setup() {
    BiodbService biodbService = biodbContainer.biodbService;
    
    searchTable = new SearchTableFactory(biodbService)
        .withDatabase(MetaboliteMajorLabel.BiGG)
        .withDatabase(MetaboliteMajorLabel.BiGG2)
        .withDatabase(MetaboliteMajorLabel.ModelSeed)
        .withDatabase(MetaboliteMajorLabel.LigandCompound)
        .build();
    curation = KBaseModelSeedIntegration.loadConnectedComponents(curationFilePath);
    
    builder = new MethoBuilder(biodbService);
    
  //SETUP REACTION INTEGRATION DEFAULT EXCLUSIONS
    reactionIntegration.exclude(ReactionMajorLabel.BiGG2Reaction, "h");
    reactionIntegration.exclude(ReactionMajorLabel.BiGG2Reaction, "h2o");
    reactionIntegration.exclude(ReactionMajorLabel.BiGGReaction, "h");
    reactionIntegration.exclude(ReactionMajorLabel.BiGGReaction, "h2o");
    reactionIntegration.exclude(ReactionMajorLabel.LigandReaction, "C00001");
    reactionIntegration.exclude(ReactionMajorLabel.LigandReaction, "C00080");
    reactionIntegration.exclude(ReactionMajorLabel.BiGG, "h");
    reactionIntegration.exclude(ReactionMajorLabel.BiGG, "h2o");
    reactionIntegration.exclude(ReactionMajorLabel.Seed, "cpd00001");
    reactionIntegration.exclude(ReactionMajorLabel.Seed, "cpd00067");
    reactionIntegration.exclude(ReactionMajorLabel.ModelSeedReaction, "cpd00001");
    reactionIntegration.exclude(ReactionMajorLabel.ModelSeedReaction, "cpd00067");
    
    Set<ReactionMajorLabel> rxnDbs = new HashSet<> ();
    rxnDbs.add(ReactionMajorLabel.BiGG);
    rxnDbs.add(ReactionMajorLabel.LigandReaction);
    rxnDbs.add(ReactionMajorLabel.MetaCyc);
    rxnDbs.add(ReactionMajorLabel.ModelSeedReaction);
    rxnDbs.add(ReactionMajorLabel.Seed);

    for (ReactionMajorLabel db : rxnDbs) {
      reactionIntegration.setupStoichDictionary(db);
    }
    
    reactionIntegration.rMap.put(ReactionMajorLabel.BiGG, new BiGGConflictResolver());
    reactionIntegration.rMap.put(ReactionMajorLabel.ModelSeedReaction, new ModelSeedReactionConflictResolver());
    reactionIntegration.rMap.put(ReactionMajorLabel.Seed, new ModelSeedReactionConflictResolver());
  }
  
  public static ConnectedComponents<String> loadConnectedComponents(String path) {
    ConnectedComponents<String> ccs = new ConnectedComponents<>();
    InputStream is = null;
    try {
      is = new FileInputStream(path);
      for (String l : IOUtils.readLines(is)) {
        String[] s = l.split("\t");
        ccs.add(new HashSet<> (Arrays.asList(s)));
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
    
    return ccs;
  }
  
  public KBaseMappingResult generateDatabaseReferences(
      XmlSbmlModel xmodel, 
      String modelEntry, 
      IntegrationReportResultAdapter resultAdapter) {
    BiodbService biodbService = biodbContainer.biodbService;
    
//    BiodbService service = new File
    
    SpecieIntegrationFacade integration = new SpecieIntegrationFacade();
    
    BMap<String, String> spiEntryToName = new BHashMap<> ();
    //names with split _
    BMap<String, String> spiEntryToName2 = new BHashMap<> ();
    Set<String> spiEntrySet = new HashSet<> ();
    BoundaryConflictResolver r1 = new BoundaryConflictResolver();
    ModelSeedMultiMatchResolver r2 = new ModelSeedMultiMatchResolver();
    BiGG2AliasMultiMatchResolver r3 = new BiGG2AliasMultiMatchResolver(biodbService);
    integration.matchResolver.put(MetaboliteMajorLabel.ModelSeed, r2);
    integration.matchResolver.put(MetaboliteMajorLabel.Seed, r2);
    integration.matchResolver.put(MetaboliteMajorLabel.BiGG2, r3);
    integration.specieConflictResolve = r1;
    Map<String, String> spiToCmp = new HashMap<>();
    for (XmlSbmlSpecie xspi : xmodel.getSpecies()) {
//      String id = xspi.getAttributes().get("id");
      String sname = xspi.getAttributes().get("name");
      String spiEntry = xspi.getAttributes().get("id");
      String cmpId = xspi.getAttributes().get("compartment");
      String bcondition = xspi.getAttributes().get("boundaryCondition");
      
      try {
        if (spiEntry != null && bcondition != null && Boolean.parseBoolean(bcondition)) {
          r1.boundary.add(spiEntry);
        }
      } catch (Exception e) {
        logger.warn("{}", e.getMessage());
      }
      integration.addSpecie(spiEntry, cmpId);
      spiToCmp.put(spiEntry, cmpId);
//      integration.patterns.put(, null);
//      integration.spiToCompartment.put(
//          xspi.getAttributes().get("id"), 
//          xspi.getAttributes().get("compartment"));

      if (!DataUtils.empty(spiEntry)) {
        spiEntrySet.add(spiEntry);
      }
      
      if (!DataUtils.empty(sname)) {
        String t = sname.trim().toLowerCase();

        int sepIndex = t.lastIndexOf('_');
        if (sepIndex > -1) {
          String part1 = t.substring(0, sepIndex);
          String part2 = t.substring(sepIndex + 1);
          if (!DataUtils.empty(part1)) {
//            System.out.println(spiEntry + " " + part1.trim());
            spiEntryToName2.put(spiEntry, part1.trim());
          }
        }
        
        if (t.startsWith("m_")) {
          if (t.contains("_")) {
            t = t.substring(0, t.lastIndexOf('_'));
          }
          if (t.startsWith("m_")) {
            t = t.substring(2);
          }
          t = t.replaceAll("_", "-");
        }
        spiEntryToName.put(spiEntry, t);
      }
    }
    
    
    
    integration.generatePatterns();
    
    // /data/biobase/export
    IdBaseIntegrationEngine be1 = builder.buildIdBaseIntegrationEngine();
    TrieIdBaseIntegrationEngine be2 = builder.buildTrieIdBaseIntegrationEngine();
    be2.ids = new HashSet<> (spiEntrySet);
    NameBaseIntegrationEngine be3 = builder.buildNameBaseIntegrationEngine();
    NameBaseIntegrationEngine be3split = builder.buildNameBaseIntegrationEngine();
    XmlReferencesBaseIntegrationEngine be0 = builder.buildXmlReferencesBaseIntegrationEngine();
    KBaseIntegrationEngine beK = builder.buildKBaseIntegrationEngine();
    beK.ids.addAll(spiEntrySet);
    be0.xmlSpecies = new ArrayList<> (xmodel.getSpecies());
    DictionaryBaseIntegrationEngine beX = builder.buildDictionaryBaseIntegrationEngine();
    beX.ids = new HashSet<> (spiEntrySet);
    IntegrationEngine ie1 = new ConnectedComponentsIntegrationEngine(curation);
    IntegrationEngine ie2 = new FirstDegreeReferences(biodbService);
    
    be3.spiEntryToName = spiEntryToName;
    be3split.spiEntryToName = spiEntryToName2;
    be1.patterns = integration.getPatterns();
    integration.baseEngines.put("ms", beK);
    integration.baseEngines.put("dict", beX);
    integration.baseEngines.put("refs1", be0);
    integration.baseEngines.put("refs2", be0);
    integration.baseEngines.put("refs3", be0);
    integration.baseEngines.put("pattern", be1);
    integration.baseEngines.put("trie", be2);
    integration.baseEngines.put("name", be3);
    integration.baseEngines.put("nameSplit", be3split);
    List<IntegrationEngine> l1 = new ArrayList<> ();
    l1.add(ie1);
    l1.add(ie2);
//    
//    List<IntegrationEngine> l2 = new ArrayList<> ();
//    l2.add(ie2);
//    l2.add(new DummyIntegrationEngine());
    integration.engines.add(l1);
//    integration.engines.add(l2);
    integration.run();
    
//    Dataset<String, String, String> ds = new Dataset<>();
//    for (IntegrationMap<String, MetaboliteMajorLabel> a : integration.isets) {
//      for (String k : a.keySet()) {
//        System.out.println(k + "\t" + a.get(k));
//      }
//    }

    
    Map<String, Map<MetaboliteMajorLabel, String>> imap = integration.build();
    
    
    
//    System.out.println(imap);
    imap = integration.clean;
    
    logger.info("propagate curation ... ");
//    ReferencePropagation propagation = new ReferencePropagation();
//    for (String k : imap.keySet()) {
//      Map<MetaboliteMajorLabel, String> references = imap.get(k);
//      for (MetaboliteMajorLabel db : references.keySet()) {
//        propagation.addReference(k, spiToCmp.get(k), db, references.get(db));
//      }
//    }
    logger.info("propagate curation ... done !");
    
    integration.status2(imap);
    
//    IntegrationMap<String, MetaboliteMajorLabel> pmap = 
//        propagation.propagate(true, curation);
//    
//    for (String k : pmap.keySet()) {
//      Map<MetaboliteMajorLabel, Set<String>> references = pmap.get(k);
//      for (MetaboliteMajorLabel database : references.keySet()) {
//        for (String dbEntry : references.get(database)) {
//          imap.get(k).put(database, dbEntry);
//        }
//      }
//    }
    
    //force modelseed identifiers pattern
    IntegrationMap<String, MetaboliteMajorLabel> msCpdMap = beK.integrate();
    for (String spiId : msCpdMap.keySet()) {
      //XXX: this condition is always true ?
      if (msCpdMap.get(spiId).containsKey(MetaboliteMajorLabel.ModelSeed)) {
        Set<String> s = msCpdMap.get(spiId).get(MetaboliteMajorLabel.ModelSeed);
        if (s != null && s.size() == 1) {
          if (!imap.containsKey(spiId)) {
            imap.put(spiId, new HashMap<MetaboliteMajorLabel, String>());
          }
          imap.get(spiId).put(MetaboliteMajorLabel.ModelSeed, s.iterator().next());
        }
      }
    }
    
    for (String k : imap.keySet()) {
//      System.out.println(k + "\t" + imap.get(k));
    }
    
    integration.status2(imap);
    
    
    for (String id : imap.keySet()) {
      String ref = imap.get(id).get(MetaboliteMajorLabel.ModelSeed);
      if (ref != null) {
        spiToModelSeedReference.put(id, ref);
      }
    }
    
    
    
    reactionIntegration.imap.clear();
    
    reactionIntegration.integrate(ReactionMajorLabel.LigandReaction, xmodel, integration.clean);
    reactionIntegration.integrate(ReactionMajorLabel.Seed, xmodel, integration.clean);
    reactionIntegration.integrate(ReactionMajorLabel.BiGG, xmodel, integration.clean);
    reactionIntegration.integrate(ReactionMajorLabel.MetaCyc, xmodel, integration.clean);
    reactionIntegration.integrate(ReactionMajorLabel.ModelSeedReaction, xmodel, integration.clean);

    if (resultAdapter != null) {
      resultAdapter.fillSpeciesIntegrationData(integration);
      resultAdapter.fillReactionIntegrationData(reactionIntegration.imap);
    }

    Set<String> rxnIds = new HashSet<>();
    for (XmlSbmlReaction xrxn : xmodel.getReactions()) {
      rxnIds.add(xrxn.getAttributes().get("id"));
    }
    SimpleStringMatchEngine modelseed = new SimpleStringMatchEngine("rxn", "R_");
    modelseed.ids.addAll(rxnIds);
    for (String rxnEntry : KBaseConfig.getModelSeedRxnDao().getAllReactionEntries()) {
      modelseed.validIds.add(rxnEntry);
    }
    Map<String, String> rmap = modelseed.match();
    for (String rxnId : rmap.keySet()) {
      if (!reactionIntegration.imap.containsKey(rxnId)) {
        reactionIntegration.imap.put(rxnId, new HashMap<ReactionMajorLabel, String>());
      }
      reactionIntegration.imap.get(rxnId).put(ReactionMajorLabel.ModelSeedReaction, rmap.get(rxnId));
    }
    
    KBaseMappingResult result = new KBaseMappingResult();
    result.species = imap;
    result.reactions = reactionIntegration.imap;
    
    for (String id : reactionIntegration.imap.keySet()) {
      String ref = reactionIntegration.imap.get(id).get(ReactionMajorLabel.ModelSeedReaction);
      if (ref != null) {
        rxnToModelSeedReference.put(id, ref);
      }
    }
    
    return result;

//    ObjectMapper om = new ObjectMapper();
//    System.out.println(om.writeValueAsString(fbaModel));
//    System.out.println("model" + fbaModel);
  }
}
