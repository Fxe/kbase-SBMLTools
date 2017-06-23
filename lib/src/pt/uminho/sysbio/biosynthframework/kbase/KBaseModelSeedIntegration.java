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

import pt.uminho.sysbio.biosynth.integration.BiodbService;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.integration.model.ConnectedComponents;
import pt.uminho.sysbio.biosynthframework.integration.model.ConnectedComponentsIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.FirstDegreeReferences;
import pt.uminho.sysbio.biosynthframework.integration.model.IdBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.IntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.SearchTable;
import pt.uminho.sysbio.biosynthframework.integration.model.SearchTableFactory;
import pt.uminho.sysbio.biosynthframework.integration.model.SpecieIntegrationFacade;
import pt.uminho.sysbio.biosynthframework.io.BiodbServiceFactory;
import pt.uminho.sysbio.biosynthframework.io.FileImport;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlSpecie;
import pt.uminho.sysbio.biosynthframework.util.IntegrationMapUtils;

public class KBaseModelSeedIntegration {
  
  protected final String biodbDataPath;
  protected final String curationPath;
  
  public KBaseModelSeedIntegration(String biodbDataPath, String curationPath) {
    this.biodbDataPath = biodbDataPath;
    this.curationPath = curationPath;
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
    BiodbService biodbService = new BiodbServiceFactory()
        .withMetaboliteDatabases()
        .build();
    
    SpecieIntegrationFacade integration = new SpecieIntegrationFacade();
    for (XmlSbmlSpecie xspi : xmodel.getSpecies()) {
      integration.patterns.put(xspi.getAttributes().get("id"), null);
      integration.spiToCompartment.put(
          xspi.getAttributes().get("id"), 
          xspi.getAttributes().get("compartment"));
    }
    
    integration.generatePatterns();
    
    SearchTable<MetaboliteMajorLabel, String> searchTable = new SearchTableFactory(biodbService)
        .withDatabase(MetaboliteMajorLabel.BiGG)
        .withDatabase(MetaboliteMajorLabel.BiGG2)
        .withDatabase(MetaboliteMajorLabel.ModelSeed)
        .withDatabase(MetaboliteMajorLabel.LigandCompound)
        .build();
    
    // /data/biobase/export
    // /data/biobase/
    FileImport.EXPORT_PATH = "/var/biobase/export";
    
    ConnectedComponents<String> ccs = loadConnectedComponents("/var/biobase/integration/cc/cpd_curation.tsv");
    IntegrationEngine ie1 = new ConnectedComponentsIntegrationEngine(ccs);
    IntegrationEngine ie2 = new FirstDegreeReferences(biodbService);
    
    IdBaseIntegrationEngine be1 = new IdBaseIntegrationEngine(searchTable);
    be1.patterns = integration.patterns;
    integration.baseEngines.add(be1);
//    integration.baseEngines.add(be1);
//    integration.baseEngines.add(be1);
    List<IntegrationEngine> l1 = new ArrayList<> ();
    l1.add(ie1);
    l1.add(ie2);
    
    List<IntegrationEngine> l2 = new ArrayList<> ();
    l2.add(ie2);
//    l2.add(new DummyIntegrationEngine());
    integration.engines.add(l1);
    integration.engines.add(l2);
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
    return imap;

//    ObjectMapper om = new ObjectMapper();
//    System.out.println(om.writeValueAsString(fbaModel));
//    System.out.println("model" + fbaModel);
  }
}
