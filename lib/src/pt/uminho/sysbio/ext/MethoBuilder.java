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
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.GlobalLabel;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.ExternalReference;
import pt.uminho.sysbio.biosynthframework.biodb.modelseed.ModelSeedMetaboliteEntity;
import pt.uminho.sysbio.biosynthframework.integration.model.Dictionary;
import pt.uminho.sysbio.biosynthframework.integration.model.DictionaryBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.FirstDegreeReferences;
import pt.uminho.sysbio.biosynthframework.integration.model.IdBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.KBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.NameBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.PrefixNumberSequenceLookupMethod;
import pt.uminho.sysbio.biosynthframework.integration.model.SearchTable;
import pt.uminho.sysbio.biosynthframework.integration.model.SearchTableFactory;
import pt.uminho.sysbio.biosynthframework.integration.model.TokenSwapLookupMethod;
import pt.uminho.sysbio.biosynthframework.integration.model.TrieIdBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.XmlReferencesBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.io.biodb.modelseed.GithubModelSeedMetaboliteDaoImpl;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.sbml.SbmlNotesParser;
//import pt.uminho.sysbio.biosynthframework.integration.model.XmlReferencesBaseIntegrationEngine;
//import pt.uminho.sysbio.biosynthframework.sbml.SbmlNotesParser;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;

public class MethoBuilder {
  
  private static final Logger logger = LoggerFactory.getLogger(MethoBuilder.class);
  
  public BiodbService biodbService;
  
  private Map<String, Dictionary> dictionaries = null;
  
  private Map<Long, Pair<String, MetaboliteMajorLabel>> idToDbPair;
  private Map<String, Set<Set<String>>> nameToCpd;
  private Map<MetaboliteMajorLabel, Set<String>> entries = new HashMap<>();
  
  public MethoBuilder(BiodbService biodbService) {
    this.biodbService = biodbService;
  }
  
  public static void aaa(SbmlNotesParser parser) {
    parser.fields.put("INCHI:", "inchi");
    parser.fields.put("EHMN_ABBREVIATION:", "trash");
    parser.fields.put("HEPATONET_1.0_ABBREVIATION:", "trash");
    parser.fields.put("DRUGBANK_INDUCER:", "trash");
    parser.fields.put("DRUGBANK_SUBSTRATE:", "trash");
    parser.fields.put("DRUGBANK_UNKNOWN:", "trash");
    parser.fields.put("DRUGBANK_COFACTOR:", "trash");
    parser.fields.put("DRUGBANK_LIGAND:", "trash");
    parser.fields.put("DRUGBANK_BINDER:", "trash");
    parser.fields.put("DRUGBANK_INHIBITOR:", "trash");
    parser.fields.put("DRUGBANK_ACTIVATOR:", "trash");
    parser.fields.put("DRUGBANK_AGONIST:", "trash");
    parser.fields.put("DRUGBANK_ANTAGONIST:", "trash");
    parser.fields.put("DRUGBANK_OTHER:", "trash");
    parser.fields.put("DRUGBANK_PRODUCT_OF:", "trash");
    parser.fields.put("DRUGBANK_OTHER_UNKNOWN:", "trash");
    parser.fields.put("DRUGBANK_POSITIVE_ALLOSTERIC_MODULATOR:", "trash");
    parser.fields.put("DRUGBANK_POTENTIATOR:", "trash");
    parser.fields.put("DRUGBANK_CONVERSION_INHIBITOR:", "trash");
    
    
    parser.fields.put("GENE:", "trash");
    parser.fields.put("SHORT NAME:", "trash");
    
    parser.fields.put("KEGG:", "kegg");
    parser.fields.put("BIOCYC:", "metacyc");
    parser.fields.put("CARBONS:", "trash");
  }
  
  public static String[] ndict = new String[] {
      "ZYMST", "zymst",
      "XUL", "xylu-D",
      "XTSINE", "xtsn",
      "XMP", "xmp",
      "XAN", "xan",
      "VAL", "val-L",
      "UTP", "utp",
      "URI", "uri",
      "UREA", "urea",
      "UGC", "urdglyc",
      "URA", "ura",
      "UPRG", "uppg3",
      "UMP", "ump",
      "UDPGAL", "udpgal",
      "UDPG", "udpg",
      "UDPNAG", "udpacgal",
      "UDP", "udp",
      "TYR", "tyr-L",
      "TRP", "trp-L",
      "TRE6P", "tre6p",
      "TRE", "tre",
      "RTHIO", "trdrd",
      "DT", "thymd",
      "THY", "thym",
      "THR", "thr-L",
      "TPPP", "thmtp",
      "TPP", "thmpp",
      "THMP", "thmmp",
      "THIAMIN", "thm",
      "THFG", "thfglu",
      "THF", "thfglu",
      "SUCCSAL", "sucsal",
      "SUC", "sucr",
      "SUCC", "succ",
      "S23E", "Ssq23epx",
      "SQL", "sql",
      "SPRMD", "spmd",
      "SPH", "sphgn",
      "DHSP", "sph1p",
      "SLF", "so4",
      "SME5P", "skm5p",
      "SME", "skm",
      "SER", "ser-L",
      "SACP", "saccrp-L",
      "S7P", "s7p",
      "RIBFLAV", "ribflv",
      "RIB", "rib-D",
      "RAF", "raffin",
      "R5P", "r5p",
      "R1P", "r1p",
      "QA", "quln",
      "PYR", "pyr",
      "PYRDX", "pydxn",
      "PL", "pydx",
      "PTRSC", "ptrc",
      "3PSER", "pser-L",
      "PRO", "pro-L",
      "PRBATP", "prbatp",
      "PRBAMP", "prbamp",
      "PPI", "ppi",
      "PHEN", "pphn",
      "PNTO", "pnto-R",
      "PI", "pi",
      "PHPYR", "phpyr",
      "PHSER", "phom",
      "PHE", "phe-L",
      "PEP", "pep",
      "PAPS", "paps",
      "PAP", "pap",
      "PANT", "pant-R",
      "OMP", "orot5p",
      "ORN", "orn",
      "OA", "oaa",
      "O2", "o2",
      "NH3", "nh4",
      "NADPH", "nadph",
      "NADP", "nadp",
      "NADH", "nadh",
      "NAD", "nad",
      "NAC", "nac",
      "MTHGXL", "mthgxl",
      "METTHF", "mlthf",
      "TPI", "mi145p",
      "MVL", "mev-R",
      "MET", "met-L",
      "METHF", "methf",
      "MAN6P", "man6p",
      "MAN1P", "man1p",
      "MAN", "man",
      "MLT", "malt",
      "MALCOA", "malcoa",
      "LYS", "lys-L",
      "LGT", "lgt-S",
      "LEU", "leu-L",
      "ITP", "itp",
      "IPPP", "ipdp",
      "INS", "ins",
      "MYOI", "inost",
      "IMP", "imp",
      "ILE", "ile-L",
      "IDP", "idp",
      "ICIT", "icit",
      "HISOL", "histd",
      "HISOLP", "hisp",
      "HIS", "his-L",
      "HCYS", "hcys-L",
      "HCIT", "hcit",
      "H2S", "h2s",
      "H2O2", "h2o2",
      "GTP", "gtp",
      "RGT", "gthrd",
      "GSN", "gsn",
      "GMP", "gmp",
      "GLYCOGEN", "glycogen",
      "GL3P", "glyc3p",
      "GL", "glyc",
      "GLY", "gly",
      "GLX", "glx",
      "GLU", "glu-L",
      "GLN", "gln-L",
      "GLC", "glc-D",
      "GLAC", "glac",
      "GGL", "ggl",
      "GDPMAN", "gdpmann",
      "GDP", "gdp",
      "GLAL", "gcald",
      "GAR", "gar",
      "GA6P", "gam6p",
      "GAL1P", "gal1p",
      "G6P", "g6p",
      "T3P1", "g3p",
      "G1P", "g1p",
      "FUM", "fum",
      "PRFICA", "fprica",
      "FOR", "for",
      "FMN", "fmn",
      "FGAM", "fgam",
      "FDP", "fdp",
      "FALD", "fald",
      "FAD", "fad",
      "F6P", "f6p",
      "ETH", "etoh",
      "ETHM", "etha",
      "ERTROL", "ergtrol",
      "ERTEOL", "ergtetrol",
      "ERGOST", "ergst",
      "EPM", "epm",
      "EPST", "epist",
      "E4P", "e4p",
      "DUTP", "dutp",
      "DU", "duri",
      "DUMP", "dump",
      "DUDP", "dudp",
      "DTTP", "dttp",
      "DTMP", "dtmp",
      "DTDP", "dtdp",
      "DTB", "dtbt",
      "DPCOA", "dpcoa",
      "DOLP", "dolp",
      "DHPT", "dhpt",
      "DHPP", "dhpmp",
      "DHP", "dhnpt",
      "DHF", "dhf",
      "T3P2", "dhap",
      "GLYN", "dha",
      "DGTP", "dgtp",
      "DG", "dgsn",
      "DGMP", "dgmp",
      "DGDP", "dgdp",
      "DCTP", "dctp",
      "DCMP", "dcmp",
      "DCDP", "dcdp",
      "DATP", "datp",
      "DADP", "dadp",
      "CYTD", "cytd",
      "CYS", "cys-L",
      "CTP", "ctp",
      "CYTS", "csn",
      "COA", "coa",
      "CO2", "co2",
      "CMP", "cmp",
      "CIT", "cit",
      "CHIT", "chtn",
      "CHOR", "chor",
      "CHO", "chol",
      "CDP", "cdp",
      "CAP", "cbp",
      "CAASP", "cbasp",
      "cAMP", "camp",
      "ATP", "atp",
      "ASP", "asp-L",
      "ASN", "asn-L",
      "ARG", "arg-L",
      "3AP", "aproa",
      "AMP", "amp",
      "SAMOB", "amob",
      "DSAM", "ametam",
      "SAM", "amet",
      "UREAC", "allphn",
      "ALA", "ala-L",
      "AKG", "akg",
      "AIR", "air",
      "SAH", "ahcys",
      "ADP", "adp",
      "ADN", "adn",
      "AD", "ad",
      "ASER", "acser",
      "ACP", "ACP",
      "ACCOA", "accoa",
      "ACAL", "acald",
      "AC", "ac",
      "AACCOA", "aacoa",
      "MTHF", "5mthf",
      "PPMVL", "5dpmev",
      "CAIR", "5aizc",
      "RADP", "4r5au",
      "PABA", "4abz",
      "GABA", "4abut",
      "3PG", "3pg",
      "OIVAL", "3mob",
      "DHSPH", "3dsphgn",
      "DHSK", "3dhsk",
      "DQT", "3dhq",
      "OICAP", "3c4mop",
      "4HPP", "34hpp",
      "2PG", "2pg",
      "AKA", "2oxoadp",
      "AKP", "2dhp",
      "SAICAR", "25aics",
      "13PDG", "13dpg",
      "13GLUCAN", "13BDglcn",
//    "GGPP", "pendp",
//    "DTP", "dtp",
  };
  
  private void setupDictionaries() {
    this.dictionaries = new HashMap<> ();
    Dictionary nDictionary = new Dictionary();
    for (int i = 0; i < ndict.length; i+=2) {
      nDictionary.add(ndict[i], new ExternalReference(ndict[i+1], "BiGG"));
    }
    
    Dictionary biggDictionary = new Dictionary();
    for (long id : biodbService.getIdsByDatabaseAndType(
        MetaboliteMajorLabel.BiGG.toString(), 
        GlobalLabel.Metabolite.toString())) {
      String cpdEntry = biodbService.getEntryById(id);
      biggDictionary.add(cpdEntry, new ExternalReference(cpdEntry, "BiGG"));
     
      {
        String cpdReplace = cpdEntry;
        if (cpdReplace.contains("-")) {
          cpdReplace = cpdReplace.replace("-", "_DASH_");
        }
        if (cpdReplace.contains("(")) {
          cpdReplace = cpdReplace.replace("(", "_LSQBKT_");
        }
        if (cpdReplace.contains(")")) {
          cpdReplace = cpdReplace.replace(")", "_RSQBKT_");
        }
        
        if (!cpdReplace.equals(cpdEntry)) {
          biggDictionary.add(cpdReplace, new ExternalReference(cpdEntry, "BiGG"));
        }
      }
      {
        String cpdReplace = cpdEntry;
        if (cpdReplace.contains("-")) {
          cpdReplace = cpdReplace.replace("-", "_");
        }
        if (!cpdReplace.equals(cpdEntry)) {
          biggDictionary.add(cpdReplace, new ExternalReference(cpdEntry, "BiGG"));
        }
      }
      {
        String cpdReplace = cpdEntry;
        if (cpdReplace.contains("-")) {
          cpdReplace = cpdReplace.replace("-", "__");
        }
        if (!cpdReplace.equals(cpdEntry)) {
          biggDictionary.add(cpdReplace, new ExternalReference(cpdEntry, "BiGG"));
        }
      }
    }
    
    Dictionary bigg2Dictionary = new Dictionary();
    for (long id : biodbService.getIdsByDatabaseAndType(
        MetaboliteMajorLabel.BiGG2.toString(), 
        GlobalLabel.Metabolite.toString())) {
      String cpdEntry = biodbService.getEntityProperty(id, "alias");
      if (cpdEntry != null) {
        bigg2Dictionary.add(cpdEntry, new ExternalReference(cpdEntry, "BiGG2"));
        
        {
          String cpdReplace = cpdEntry;
          if (cpdReplace.contains("__")) {
            cpdReplace = cpdReplace.replace("__", "_DASH_");
          }
          if (!cpdReplace.equals(cpdEntry)) {
            bigg2Dictionary.add(cpdReplace, new ExternalReference(cpdEntry, "BiGG2"));
          }
        }
        {
          String cpdReplace = cpdEntry;
          if (cpdReplace.contains("__")) {
            cpdReplace = cpdReplace.replace("__", "-");
          }
          if (!cpdReplace.equals(cpdEntry)) {
            bigg2Dictionary.add(cpdReplace, new ExternalReference(cpdEntry, "BiGG2"));
          }
        }
        {
          String cpdReplace = cpdEntry;
          if (cpdReplace.contains("__")) {
            cpdReplace = cpdReplace.replace("__", "_");
          }
          if (!cpdReplace.equals(cpdEntry)) {
            bigg2Dictionary.add(cpdReplace, new ExternalReference(cpdEntry, "BiGG2"));
          }
        }
        {
          String cpdReplace = cpdEntry;
          if (cpdReplace.contains("_")) {
            cpdReplace = cpdReplace.replace("_", "_DASH_");
          }
          if (!cpdReplace.equals(cpdEntry)) {
            bigg2Dictionary.add(cpdReplace, new ExternalReference(cpdEntry, "BiGG2"));
          }
        }
      } else {
        logger.warn("no alias for {}", id);
      }
    }
    
    Dictionary metaCycDictionary = new Dictionary();
    for (long id : biodbService.getIdsByDatabaseAndType(
        MetaboliteMajorLabel.MetaCyc.toString(), 
        GlobalLabel.Metabolite.toString())) {
      String cpdEntry = biodbService.getEntryById(id);
      String cpdReplace = cpdEntry.replaceAll("-", "_");
      metaCycDictionary.add(cpdEntry, new ExternalReference(cpdEntry, "MetaCyc"));
      metaCycDictionary.add(cpdReplace, new ExternalReference(cpdEntry, "MetaCyc"));
//      metaCycDictionary.add(cpdReplace, new ExternalReference(cpdEntry, "MetaCyc"));
    }
    
    this.dictionaries.put("ndict", nDictionary);
    this.dictionaries.put("bigg", biggDictionary);
    this.dictionaries.put("bigg2", bigg2Dictionary);
    this.dictionaries.put("meta", metaCycDictionary);
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
      if (!entries.containsKey(db)) {
        Set<String> dict = new HashSet<> ();
        for (long id : biodbService.getIdsByDatabaseAndType(db.toString(), "Metabolite")) {
          if (MetaboliteMajorLabel.BiGG2.equals(db)) {
            String alias = biodbService.getEntityProperty(id, "alias");
            if (alias != null) {
              dict.add(alias);
            }
          } else {
            dict.add(biodbService.getEntryById(id));
          }
        }
        entries.put(db, dict);
      }
      e.setup(db, entries.get(db));
    }
    
    return e;
  }
  
  public IdBaseIntegrationEngine buildIdBaseIntegrationEngine() {
    SearchTable<MetaboliteMajorLabel, String> searchTable = new SearchTableFactory(biodbService)
        .withDatabase(MetaboliteMajorLabel.BiGG)
//        .withDatabase(MetaboliteMajorLabel.BiGG2)
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
    
    
    IdBaseIntegrationEngine be1 = new IdBaseIntegrationEngine(searchTable);
    be1.lookupMethods.put(MetaboliteMajorLabel.BiGG, tkLookupMethod);
//    be1.lookupMethods.put(MetaboliteMajorLabel.BiGG2, tkLookupMethod2);
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
  
  public XmlReferencesBaseIntegrationEngine buildXmlReferencesBaseIntegrationEngine() {
    SbmlNotesParser notesParser = new SbmlNotesParser();
    aaa(notesParser);
    XmlReferencesBaseIntegrationEngine e = new XmlReferencesBaseIntegrationEngine(notesParser);
    
    return e;
  }

  
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
  
  public DictionaryBaseIntegrationEngine buildDictionaryBaseIntegrationEngine() {    
    DictionaryBaseIntegrationEngine e = new DictionaryBaseIntegrationEngine();
    if (dictionaries == null) {
      setupDictionaries();
    }
    for (String dict : dictionaries.keySet()) {
      e.dictionaryMap.put(dict, dictionaries.get(dict));
    }
    e.setup();
    return e;
  }
  
  
  public static void loadModelSeedCompoundData(
      Set<String> ids,
      Map<String, Boolean> obsolete, Map<String, String> remap
//      Map<String, String> nmap, Map<String, String> fmap
      ) {
    GithubModelSeedMetaboliteDaoImpl dao = KBaseConfig.getModelSeedCpdDao();

    for (String cpdEntry : dao.getAllMetaboliteEntries()) {
      ModelSeedMetaboliteEntity cpd = dao.getMetaboliteByEntry(cpdEntry);
      ids.add(cpdEntry);
//      nmap.put(cpdEntry, cpd.getName());
//      fmap.put(cpdEntry, cpd.getFormula());
      obsolete.put(cpd.getEntry(), cpd.getObsolete());
      if (cpd.getLinkedCompound() != null) {
        remap.put(cpd.getEntry(), cpd.getLinkedCompound());
      }
    }
  }
  
  public static KBaseIntegrationEngine buildKBaseIntegrationEngine() {
    KBaseIntegrationEngine e = new KBaseIntegrationEngine();
    
    Set<String> ids = new HashSet<>();
    Map<String, Boolean> obsolete = new HashMap<>();
    Map<String, String> remap = new HashMap<>();
    loadModelSeedCompoundData(ids, obsolete, remap);
    
    e.validIds.addAll(ids);
    e.obsolete.putAll(obsolete);
    e.remap.putAll(remap);
    
    return e;
  }
  
  public NameBaseIntegrationEngine buildNameBaseIntegrationEngine() {
    if (idToDbPair == null || nameToCpd == null) {
      setupNameData();
    }
    
    NameBaseIntegrationEngine e = new NameBaseIntegrationEngine(nameToCpd, idToDbPair);
    return e;
  }
  
  public FirstDegreeReferences buildFirstDegreeReferences() {
    FirstDegreeReferences e = new FirstDegreeReferences(biodbService);
    return e;
  }
}
