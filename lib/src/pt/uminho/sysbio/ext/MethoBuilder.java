package pt.uminho.sysbio.ext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynth.integration.BiodbService;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.integration.model.IdBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.NameBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.PrefixNumberSequenceLookupMethod;
import pt.uminho.sysbio.biosynthframework.integration.model.SearchTable;
import pt.uminho.sysbio.biosynthframework.integration.model.SearchTableFactory;
import pt.uminho.sysbio.biosynthframework.integration.model.TokenSwapLookupMethod;
import pt.uminho.sysbio.biosynthframework.integration.model.TrieIdBaseIntegrationEngine;
//import pt.uminho.sysbio.biosynthframework.integration.model.XmlReferencesBaseIntegrationEngine;
//import pt.uminho.sysbio.biosynthframework.sbml.SbmlNotesParser;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;

public class MethoBuilder {
  
  private static final Logger logger = LoggerFactory.getLogger(MethoBuilder.class);
  
  public BiodbService biodbService;
  
  private Map<Long, Pair<String, MetaboliteMajorLabel>> idToDbPair;
  private Map<String, Set<Set<String>>> nameToCpd;
  
  public MethoBuilder(BiodbService biodbService) {
    this.biodbService = biodbService;
  }
  
//  public static void aaa(SbmlNotesParser parser) {
//    parser.fields.put("INCHI:", "inchi");
//    parser.fields.put("EHMN_ABBREVIATION:", "trash");
//    parser.fields.put("HEPATONET_1.0_ABBREVIATION:", "trash");
//    parser.fields.put("DRUGBANK_INDUCER:", "trash");
//    parser.fields.put("DRUGBANK_SUBSTRATE:", "trash");
//    parser.fields.put("DRUGBANK_UNKNOWN:", "trash");
//    parser.fields.put("DRUGBANK_COFACTOR:", "trash");
//    parser.fields.put("DRUGBANK_LIGAND:", "trash");
//    parser.fields.put("DRUGBANK_BINDER:", "trash");
//    parser.fields.put("DRUGBANK_INHIBITOR:", "trash");
//    parser.fields.put("DRUGBANK_ACTIVATOR:", "trash");
//    parser.fields.put("DRUGBANK_AGONIST:", "trash");
//    parser.fields.put("DRUGBANK_ANTAGONIST:", "trash");
//    parser.fields.put("DRUGBANK_OTHER:", "trash");
//    parser.fields.put("DRUGBANK_PRODUCT_OF:", "trash");
//    parser.fields.put("DRUGBANK_OTHER_UNKNOWN:", "trash");
//    parser.fields.put("DRUGBANK_POSITIVE_ALLOSTERIC_MODULATOR:", "trash");
//    parser.fields.put("DRUGBANK_POTENTIATOR:", "trash");
//    parser.fields.put("DRUGBANK_CONVERSION_INHIBITOR:", "trash");
//    
//    
//    parser.fields.put("GENE:", "trash");
//    parser.fields.put("SHORT NAME:", "trash");
//    
//    parser.fields.put("KEGG:", "kegg");
//    parser.fields.put("BIOCYC:", "metacyc");
//    parser.fields.put("CARBONS:", "trash");
//  }
  
  public void aa() {
    
  }
  
  public TrieIdBaseIntegrationEngine buildTrieIdBaseIntegrationEngine() {
    TrieIdBaseIntegrationEngine e = new TrieIdBaseIntegrationEngine();
    e.ignoreCase = false;
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
      e.setup(db, entries);
    }
    
    return e;
  }
  
  public IdBaseIntegrationEngine buildIdBaseIntegrationEngine() {
    SearchTable<MetaboliteMajorLabel, String> searchTable = new SearchTableFactory(biodbService)
        .withDatabase(MetaboliteMajorLabel.BiGG)
        .withDatabase(MetaboliteMajorLabel.BiGG2)
        .withDatabase(MetaboliteMajorLabel.ModelSeed)
        .withDatabase(MetaboliteMajorLabel.Seed)
        .withDatabase(MetaboliteMajorLabel.LigandCompound)
        .withDatabase(MetaboliteMajorLabel.LigandGlycan)
        .build();
    
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
    be1.lookupMethods.put(MetaboliteMajorLabel.BiGG, tkLookupMethod);
    be1.lookupMethods.put(MetaboliteMajorLabel.BiGG2, tkLookupMethod2);
    be1.lookupMethods.put(MetaboliteMajorLabel.Seed, 
        new PrefixNumberSequenceLookupMethod("cpd"));
    be1.lookupMethods.put(MetaboliteMajorLabel.ModelSeed, 
        new PrefixNumberSequenceLookupMethod("cpd"));
    be1.lookupMethods.put(MetaboliteMajorLabel.LigandCompound, 
        new PrefixNumberSequenceLookupMethod("C"));
    be1.lookupMethods.put(MetaboliteMajorLabel.LigandGlycan, 
        new PrefixNumberSequenceLookupMethod("G"));
    be1.lookupMethods.put(MetaboliteMajorLabel.LigandDrug, 
        new PrefixNumberSequenceLookupMethod("D"));
    
    return be1;
  }
  
//  public XmlReferencesBaseIntegrationEngine buildXmlReferencesBaseIntegrationEngine() {
//    SbmlNotesParser notesParser = new SbmlNotesParser();
//    aaa(notesParser);
//    XmlReferencesBaseIntegrationEngine e = new XmlReferencesBaseIntegrationEngine(notesParser);
//    
//    return e;
//  }
  

  
  public void setupNameData() {
    
    logger.info("loading information for name matching ...");
    
    Map<Set<String>, String> nameData = NameIntegration.buildNameDictionary();
    nameToCpd = CollectionUtils.reverseMap(nameData);
    
    idToDbPair = new HashMap<> ();
    for (Set<String> ids : nameData.keySet()) {
      for (String id : ids) {
        long cpdId = Long.parseLong(id);
        String cpdEntry = biodbService.getEntryById(cpdId);
        String databaseStr = biodbService.getDatabaseById(cpdId);
        Pair<String, MetaboliteMajorLabel> p = 
            new ImmutablePair<>(cpdEntry, MetaboliteMajorLabel.valueOf(databaseStr));
        idToDbPair.put(cpdId, p);
      }
    }
  }
  
  public NameBaseIntegrationEngine buildNameBaseIntegrationEngine() {
    if (idToDbPair == null || nameToCpd == null) {
      setupNameData();
    }
    
    NameBaseIntegrationEngine e = new NameBaseIntegrationEngine(nameToCpd, idToDbPair);
    return e;
  }
  
  
}
