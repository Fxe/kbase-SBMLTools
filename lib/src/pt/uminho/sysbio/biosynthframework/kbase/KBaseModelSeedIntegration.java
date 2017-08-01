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
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import pt.uminho.sysbio.biosynth.integration.BiodbService;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.BHashMap;
import pt.uminho.sysbio.biosynthframework.BMap;
import pt.uminho.sysbio.biosynthframework.integration.model.ConnectedComponents;
import pt.uminho.sysbio.biosynthframework.integration.model.ConnectedComponentsIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.FirstDegreeReferences;
import pt.uminho.sysbio.biosynthframework.integration.model.IdBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.IntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.NameBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.PrefixNumberSequenceLookupMethod;
import pt.uminho.sysbio.biosynthframework.integration.model.SearchTable;
import pt.uminho.sysbio.biosynthframework.integration.model.SearchTableFactory;
import pt.uminho.sysbio.biosynthframework.integration.model.SpecieIntegrationFacade;
import pt.uminho.sysbio.biosynthframework.integration.model.TokenSwapLookupMethod;
import pt.uminho.sysbio.biosynthframework.integration.model.TrieIdBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.io.BiodbServiceFactory;
import pt.uminho.sysbio.biosynthframework.io.FileImportKb;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlSpecie;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;
import pt.uminho.sysbio.biosynthframework.util.IntegrationMapUtils;
import pt.uminho.sysbio.ext.NameIntegration;

public class KBaseModelSeedIntegration {
  
  public String biodbDataPath;
  public String curationFilePath;
  public Map<String, String> spiToModelSeedReference = new HashMap<> ();
  
  public BiodbService biodbService = null;
  public SearchTable<MetaboliteMajorLabel, String> searchTable = null;
  public ConnectedComponents<String> ccs = null;
  
  public KBaseModelSeedIntegration(String biodbDataPath, String curationPath) {
    this.biodbDataPath = biodbDataPath;
    this.curationFilePath = curationPath;
    
    // /data/biobase/
    FileImportKb.EXPORT_PATH = biodbDataPath;
    setup();
  }
  
  public void setup() {
    biodbService = new BiodbServiceFactory()
        .withMetaboliteDatabases()
        .build();
    
    searchTable = new SearchTableFactory(biodbService)
        .withDatabase(MetaboliteMajorLabel.BiGG)
        .withDatabase(MetaboliteMajorLabel.BiGG2)
        .withDatabase(MetaboliteMajorLabel.ModelSeed)
        .withDatabase(MetaboliteMajorLabel.LigandCompound)
        .build();
    ccs = KBaseModelSeedIntegration.loadConnectedComponents(curationFilePath);
  }
  
//  public static Function<List<Set<String>>, List<Set<String>>> buildReduceBigg2(final Function<String, String> entryToUniversalEntry) {
//    return new Function<List<Set<String>>, List<Set<String>>>() {
//      
//      @Override
//      public List<Set<String>> apply(List<Set<String>> t) {
////        Map<String, String> entryToAbbr = new HashMap<> ();
//        Map<String, String> reduceToRealEntry = new HashMap<> ();
//        List<Set<String>> reduceSets = new ArrayList<> ();
//        for (Set<String> set : t) {
//          Set<String> reducedSet = new HashSet<> ();
//          for (String e : set) {
//            String abbr = entryToUniversalEntry.apply(e);
//            if (abbr != null) {
//              reducedSet.add(abbr);
//              reduceToRealEntry.put(abbr, e);
//            } else {
//              reducedSet.add(e);
//            }
//          }
//          reduceSets.add(reducedSet);
//        }
//        
//        List<Set<String>> result = new ArrayList<> ();
//        for (Set<String> rset : reduceSets) {
//          Set<String> bigg2set = new HashSet<> ();
//          for (String e : rset) {
//            String bigg2entry = reduceToRealEntry.get(e);
//            bigg2set.add(bigg2entry);
//          }
//          result.add(bigg2set);
//        }
//        
////        System.out.println(result);
//        return result;
//      }
//    };
//  }
  
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
  
  public Map<String, Map<MetaboliteMajorLabel, String>> generateDatabaseReferences(XmlSbmlModel xmodel, String modelEntry) {

    
//    BiodbService service = new File
    
    SpecieIntegrationFacade integration = new SpecieIntegrationFacade();
    
    BMap<String, String> spiEntryToName = new BHashMap<> ();
    Set<String> spiEntrySet = new HashSet<> ();
    for (XmlSbmlSpecie xspi : xmodel.getSpecies()) {
      String id = xspi.getAttributes().get("id");
      String cmpId = xspi.getAttributes().get("compartment");
      integration.addSpecie(id, cmpId);
//      integration.patterns.put(, null);
//      integration.spiToCompartment.put(
//          xspi.getAttributes().get("id"), 
//          xspi.getAttributes().get("compartment"));
      String sname = xspi.getAttributes().get("name");
      String spiEntry = xspi.getAttributes().get("id");
      if (spiEntry != null && !spiEntry.trim().isEmpty()) {
        spiEntrySet.add(spiEntry);
      }
      if (sname != null && !sname.trim().isEmpty()) {
        String t = sname.trim().toLowerCase();
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
    TrieIdBaseIntegrationEngine be2 = new TrieIdBaseIntegrationEngine();
    BiodbService biodbService = new BiodbServiceFactory().withMetaboliteDatabases().build();
    Set<MetaboliteMajorLabel> dbs = new HashSet<> ();
    dbs.add(MetaboliteMajorLabel.Seed);
    dbs.add(MetaboliteMajorLabel.ModelSeed);
    dbs.add(MetaboliteMajorLabel.BiGG);
    dbs.add(MetaboliteMajorLabel.BiGG2);
    dbs.add(MetaboliteMajorLabel.LigandCompound);
    dbs.add(MetaboliteMajorLabel.LigandGlycan);
    dbs.add(MetaboliteMajorLabel.LigandDrug);
    
    for (MetaboliteMajorLabel db : dbs) {
      Set<String> entries = new HashSet<> ();
      for (long id : biodbService.getIdsByDatabaseAndType(db.toString(), "Metabolite")) {
        entries.add(biodbService.getEntryById(id));
      }
      be2.setup(db, entries);
    }
    be2.ids.addAll(integration.getPatterns().keySet());
    
    IntegrationEngine ie1 = new ConnectedComponentsIntegrationEngine(ccs);
    IntegrationEngine ie2 = new FirstDegreeReferences(biodbService);
    
    TokenSwapLookupMethod tkLookupMethod = new TokenSwapLookupMethod();
    tkLookupMethod.acceptedTokens.add("_DASH");
    tkLookupMethod.acceptedTokens.add("_L");
    tkLookupMethod.acceptedTokens.add("_D");
    tkLookupMethod.acceptedTokens.add("_R");
    tkLookupMethod.acceptedTokens.add("_S");
    tkLookupMethod.acceptedTokens.add("_bD");
    tkLookupMethod.addSwap("_DASH_", "_");
    tkLookupMethod.addSwap("_D", "-D");
    tkLookupMethod.addSwap("_R", "-R");
    tkLookupMethod.addSwap("_S", "-S");
    tkLookupMethod.addSwap("_bD", "-bD");
    tkLookupMethod.addSwap("_", "-");
    
    TokenSwapLookupMethod tkLookupMethod2 = new TokenSwapLookupMethod();
    tkLookupMethod2.acceptedTokens.add("_DASH");
    tkLookupMethod2.acceptedTokens.add("_L");
    tkLookupMethod2.acceptedTokens.add("_D");
    tkLookupMethod2.acceptedTokens.add("_R");
    tkLookupMethod2.acceptedTokens.add("_S");
    tkLookupMethod2.acceptedTokens.add("_bD");
    tkLookupMethod2.addSwap("_DASH_", "__");
    tkLookupMethod2.addSwap("_L", "__L");
    tkLookupMethod2.addSwap("_D", "__D");
    tkLookupMethod2.addSwap("_R", "__R");
    tkLookupMethod2.addSwap("_S", "__S");
    tkLookupMethod2.addSwap("_bD", "__bD");
    tkLookupMethod2.addSwap("_", "__");
    
    IdBaseIntegrationEngine be1 = new IdBaseIntegrationEngine(searchTable);
    be1.lookupMethods.put(MetaboliteMajorLabel.Seed, 
        new PrefixNumberSequenceLookupMethod("cpd"));
    be1.lookupMethods.put(MetaboliteMajorLabel.ModelSeed, 
        new PrefixNumberSequenceLookupMethod("cpd"));

    be1.lookupMethods.put(MetaboliteMajorLabel.LigandCompound, 
        new PrefixNumberSequenceLookupMethod("C"));
    be1.lookupMethods.put(MetaboliteMajorLabel.LigandDrug, 
        new PrefixNumberSequenceLookupMethod("D"));
    be1.lookupMethods.put(MetaboliteMajorLabel.LigandGlycan, 
        new PrefixNumberSequenceLookupMethod("G"));
    be1.lookupMethods.put(MetaboliteMajorLabel.BiGG, 
        tkLookupMethod);
    be1.lookupMethods.put(MetaboliteMajorLabel.BiGG2, 
        tkLookupMethod2);
    
//    be1.lo
//    be2.setup(MetaboliteMajorLabel.BiGG, words);
    Map<Set<String>, String> nameData = NameIntegration.buildNameDictionary();
    Map<String, Set<Set<String>>> nameToCpd = CollectionUtils.reverseMap(nameData);
    Map<Long, Pair<String, MetaboliteMajorLabel>> data = new HashMap<> ();
    for (Set<String> ids : nameData.keySet()) {
      for (String id : ids) {
        long cpdId = Long.parseLong(id);
        String cpdEntry = biodbService.getEntryById(cpdId);
        String databaseStr = biodbService.getDatabaseById(cpdId);
        Pair<String, MetaboliteMajorLabel> p = 
            new ImmutablePair<>(cpdEntry, MetaboliteMajorLabel.valueOf(databaseStr));
        data.put(cpdId, p);
      }
    }
    NameBaseIntegrationEngine be3 = new NameBaseIntegrationEngine(nameToCpd, data);
    be3.spiEntryToName = spiEntryToName;
    be1.patterns = integration.getPatterns();
    integration.baseEngines.add(be1);
    integration.baseEngines.add(be2);
    integration.baseEngines.add(be3);
//    List<IntegrationEngine> l1 = new ArrayList<> ();
//    l1.add(ie1);
//    l1.add(ie2);
//    
//    List<IntegrationEngine> l2 = new ArrayList<> ();
//    l2.add(ie2);
//    l2.add(new DummyIntegrationEngine());
//    integration.engines.add(l1);
//    integration.engines.add(l2);
    integration.run();
    
    final Map<String, String> aa = new HashMap<> ();
//    IntegrationMapUtils.reduceBigg = buildReduceBigg2(
//        new Function<String, String>() {
//
//          @Override
//          public String apply(String t) {
//            return aa.get(t);
//          }
//        });
    
    Map<String, Map<MetaboliteMajorLabel, String>> imap = integration.build();
    
    for (String id : imap.keySet()) {
      String ref = imap.get(id).get(MetaboliteMajorLabel.ModelSeed);
      if (ref != null) {
        spiToModelSeedReference.put(id, ref);
      }
    }
    
    return imap;

//    ObjectMapper om = new ObjectMapper();
//    System.out.println(om.writeValueAsString(fbaModel));
//    System.out.println("model" + fbaModel);
  }
}
