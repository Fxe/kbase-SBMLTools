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
import pt.uminho.sysbio.ext.MethoBuilder;
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

    MethoBuilder builder = new MethoBuilder(biodbService);
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
    IdBaseIntegrationEngine be1 = builder.buildIdBaseIntegrationEngine();
    TrieIdBaseIntegrationEngine be2 = builder.buildTrieIdBaseIntegrationEngine();
    NameBaseIntegrationEngine be3 = builder.buildNameBaseIntegrationEngine();
    
    IntegrationEngine ie1 = new ConnectedComponentsIntegrationEngine(ccs);
    IntegrationEngine ie2 = new FirstDegreeReferences(biodbService);
    
    be3.spiEntryToName = spiEntryToName;
    be1.patterns = integration.getPatterns();
    integration.baseEngines.add(be1);
    integration.baseEngines.add(be2);
    integration.baseEngines.add(be3);
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
