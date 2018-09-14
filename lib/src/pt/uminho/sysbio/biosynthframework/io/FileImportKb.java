package pt.uminho.sysbio.biosynthframework.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynth.integration.BiodbService;
//import pt.uminho.sysbio.biosynth.configuration.BiosynthConfiguration;
import pt.uminho.sysbio.biosynthframework.BHashMap;
import pt.uminho.sysbio.biosynthframework.BMap;
//import pt.uminho.sysbio.biosynthframework.NameMappingResult;
import pt.uminho.sysbio.biosynthframework.SubcellularCompartment;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;
//import pt.uminho.sysbio.biosynthframework.web.service.DefaultBiobaseImportServiceImpl;

public class FileImportKb {

  private static final Logger logger = LoggerFactory.getLogger(FileImportKb.class);

  public static String EXPORT_PATH = "/var/biobase/export";
  public static String SEP = "\t";
  
  public static Set<Pair<Long, Long>> importMetaboliteReferenceEdges() {
    Set<Pair<Long, Long>> edges = new HashSet<> ();
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/metabolite_ref_edges.tsv");
      List<String> lines = IOUtils.readLines(is);
      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i).concat(SEP + "!");
        String[] col = line.split(SEP);
        
        edges.add(new ImmutablePair<>(
            Long.parseLong(col[0]), Long.parseLong(col[1])));
        
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return edges;
  }
  
  public static void importMetaboliteFormulas(Map<Long, Set<Long>> idToFormulas, Map<Long, String> idToKey) {
    importMetaboliteProperties(idToFormulas, idToKey, "cpd_formulas.tsv");
  }
  
  public static void importMetaboliteInchiKeys(Map<Long, Set<Long>> idToInchiKeys, Map<Long, String> idToKey) {
    importMetaboliteProperties(idToInchiKeys, idToKey, "cpd_inchikey.tsv");
  }
  
  public static void importMetaboliteSmiles(Map<Long, Set<Long>> idToSmiles, Map<Long, String> idToKey) {
    importMetaboliteProperties(idToSmiles, idToKey, "cpd_smiles.tsv");
  }
  
  public static void importMetaboliteProperties(
      Map<Long, Set<Long>> idToPropId, Map<Long, String> idToKey, String file) {
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/" + file);
      List<String> lines = IOUtils.readLines(is);
      for (int i = 1; i < lines.size(); i++) {
        try {
          String line = lines.get(i);
          String[] col = line.concat(SEP).concat("!").split(SEP);
          long id = Long.parseLong(col[0]);
          String key = col[1];
          String[] ids = getOrNull(col, 2, "").split(" ");
          idToKey.put(id, key);
          for (String cpdIdStr : ids) {
            long cpiId = Long.parseLong(cpdIdStr);
            if (!idToPropId.containsKey(cpiId)) {
              idToPropId.put(cpiId, new HashSet<Long>());
            }
            idToPropId.get(cpiId).add(id);
          }
        } catch (Exception e) {
          logger.warn("Parse error line[{}] - {}", i, lines.get(i));
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
  }
  
  public static Map<Pair<Pair<String, Integer>, String>, 
                    Set<Pair<String, Map<String, Double>>>> readAlignData(String path) {
    Map<Pair<Pair<String, Integer>, String>, 
        Set<Pair<String, Map<String, Double>>>> alignData = new HashMap<> ();
    
    
  //ko modelentry modeltax dist taxid taxentry taxname kegggene modelprotentry ident sim len %iden %sim
    InputStream is = null;
    try {
//      String path = EXPORT_PATH + "/ctr_meta_" + itgId + ".tsv";
      logger.info("Reading Alignment Data {} ...", path);
      is = new FileInputStream(path);
      List<String> lines = IOUtils.readLines(is);

      for (int i = 0; i < lines.size(); i++) {
        String line = lines.get(i).concat(SEP + "!");
        String[] col = line.split(SEP);
        String koEntry = col[0];
        String modelEntry = col[1];
//        String modelTaxEntry = col[2];
        int distance = Integer.parseInt(col[2]);
        long modelTaxId = Long.parseLong(col[3]);
        String targetTaxEntry = col[4];
        String targetTaxName = col[5];
        String targetTaxKeggGeneEntry = col[6];
        String modelTaxUniprotProtEntry = col[7];
        double nident = Double.parseDouble(col[8]);
        double nsim = Double.parseDouble(col[9]);
        double len = Double.parseDouble(col[10]);
        //can calculate by myself !
//        double pident = Double.parseDouble(col[11]);
//        double psim = Double.parseDouble(col[12]);
        
        Pair<Pair<String, Integer>, String> taxGenePair = new ImmutablePair<Pair<String, Integer>, String>(
            new ImmutablePair<String, Integer>(targetTaxEntry, distance), targetTaxKeggGeneEntry);
        if (!alignData.containsKey(taxGenePair)) {
          alignData.put(taxGenePair, new HashSet<Pair<String, Map<String, Double>>> ());
        }
        
        Map<String, Double> data = new HashMap<> ();
        data.put("ident", nident);
        data.put("sim", nsim);
        data.put("len", len);
        
        Pair<String, Map<String, Double>> hitData = new ImmutablePair<>(
            modelTaxUniprotProtEntry, data);
        alignData.get(taxGenePair).add(hitData);
//        System.out.println(taxGenePair + " " + hitData);
//        long id = Long.parseLong(col[0]);
//        String entry = col[1];
//        String alias = col[2];
//        String description = col[3];
//        idToEntry.put(id, entry);
//        if (!alias.trim().isEmpty()) {
//          idToAlias.put(id, alias);
//        }
//        if (!description.trim().isEmpty()) {
//          idToDescription.put(id, description);
//        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return alignData;
  }
  
  public static Set<Long> importIntegrationMetadata(long itgId,
      Map<Long, String> idToEntry,
      Map<Long, String> idToAlias,
      Map<Long, String> idToDescription) {
    
    
    
    Set<Long> ids = new HashSet<> ();
    InputStream is = null;
    try {
      String path = EXPORT_PATH + "/ctr_meta_" + itgId + ".tsv";
      logger.info("Reading Integration Set Metadata {} ...", path);
      is = new FileInputStream(path);
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i).concat(SEP + "!");
        String[] col = line.split(SEP);
        long id = Long.parseLong(col[0]);
        String entry = col[1];
        String alias = col[2];
        String description = col[3];
        idToEntry.put(id, entry);
        if (!alias.trim().isEmpty()) {
          idToAlias.put(id, alias);
        }
        if (!description.trim().isEmpty()) {
          idToDescription.put(id, description);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ids;
  }
  
  public static Map<Long, Long> importModelUnificationMap(long itgId) {
    InputStream is = null;
    Map<Long, Long> umap = new HashMap<> ();
    //ModelEntityId_EntityType_ClusterEntry_ClusterId
    try {
      is = new FileInputStream(
          EXPORT_PATH + "/mumap_" + itgId + ".tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i);
        String[] col = line.split(SEP);
        long refId = Long.parseLong(col[0]);
        long ctrId = Long.parseLong(col[3]);
        Long prevId = null;
        if ((prevId = umap.put(refId, ctrId)) != null) {
          logger.warn("Reference {} conflict map {} -> {}", refId, prevId, ctrId);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return umap;
  }
  
  public static Map<Long, Long> importModelSpecieUnificationMap(long itgId) {
    InputStream is = null;
    Map<Long, Long> umap = new HashMap<> ();
    //ModelEntityId_EntityType_ClusterEntry_ClusterId
    try {
      is = new FileInputStream(
          EXPORT_PATH + "/mspiumap_" + itgId + ".tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i);
        String[] col = line.split(SEP);
        long refId = Long.parseLong(col[0]);
        long ctrId = Long.parseLong(col[3]);
        Long prevId = null;
        if ((prevId = umap.put(refId, ctrId)) != null) {
          logger.warn("Reference {} conflict map {} -> {}", refId, prevId, ctrId);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return umap;
  }
  
  
  public static Map<Long, Long> importUnificationMap(long itgId) {
    InputStream is = null;
    Map<Long, Long> umap = new HashMap<> ();

    try {
      is = new FileInputStream(
          EXPORT_PATH + "/umap_" + itgId + ".tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i);
        String[] col = line.split(SEP);
        umap.put(Long.parseLong(col[0]), 
            Long.parseLong(col[1]));
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    return umap;
  }

  public static void importInchiNameData(Map<Long, String> idToIupac,
      Map<Long, String> idToTraditional,
      Map<Long, String> idToFormula) {
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/property/inchi_name.tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i);
        try {
          String[] col = line.split(SEP);
          long id = Long.parseLong(col[0]);
          String iupac = col[2];
          String trad = col[3];
          String formula = col[4];
//          int charge = Integer.parseInt(col[5]);
          idToIupac.put(id, iupac);
          idToTraditional.put(id, trad);
          idToFormula.put(id, formula);
        } catch (Exception e) {
          logger.error("Error parse line({}): {}", i, line);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
  }

  public static void importMolNameData(Map<Long, String> idToIupac,
      Map<Long, String> idToTraditional,
      Map<Long, String> idToFormula) {
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/property/mol_name.tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i);
        try {
          String[] col = line.split(SEP);
          long id = Long.parseLong(col[0]);
          String iupac = col[1].equals("null") ? null : col[1];
          String trad = col[2].equals("null") ? null : col[2];
          String formula = col[3].equals("null") ? null : col[3];
          //Integer charge = Integer.parseInt(col[5].equals("null") ? null : col[5]);
          idToIupac.put(id, iupac);
          idToTraditional.put(id, trad);
          idToFormula.put(id, formula);
        } catch (Exception e) {
          logger.error("Error parse line({}): {}", i, line);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
  }

  public static void importSmilesNameData(Map<Long, String> idToIupac,
      Map<Long, String> idToTraditional,
      Map<Long, String> idToFormula) {
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/property/smiles_name.tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i);
        try {
          String[] col = line.split(SEP);
          long id = Long.parseLong(col[0]);
          String iupac = col[2].equals("null") ? null : col[2];
          String trad = col[3].equals("null") ? null : col[3];
          String formula = col[4].equals("null") ? null : col[4];
          //          Integer charge = Integer.parseInt(col[5].equals("null") ? null : col[5]);
          idToIupac.put(id, iupac);
          idToTraditional.put(id, trad);
          idToFormula.put(id, formula);
        } catch (Exception e) {
          logger.error("Error parse line({}): {}", i, line);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
  }
  
  public static void importNameData(Map<Long, String> idToName, 
      Map<Long, Set<Long>> nameIdToCpdSet, InputStream is) throws IOException {
    
    List<String> lines = IOUtils.readLines(is);

    for (int i = 1; i < lines.size(); i++) {
      //add ! at end to guarantee split
      try {
        String line = lines.get(i).concat(SEP + "!");
        String[] col = line.split(SEP);
        long id = Long.parseLong(col[0]);
        String name = col[1];
        String cpdArrayStr = col[2];
        Set<Long> cpdSet = new HashSet<> ();
        if (!cpdArrayStr.trim().isEmpty()) {
          for (String s : cpdArrayStr.split(" ")) {
            if (!s.trim().isEmpty()) {
              cpdSet.add(Long.parseLong(s));
            }
          }
        }
        idToName.put(id, name);
        nameIdToCpdSet.put(id, cpdSet);
      } catch (Exception e) {
        logger.warn("invalid line [{}] - {} : {}", i, lines.get(i), e.getMessage());
      }
    }
  }

  public static void importNameData(Map<Long, String> idToName, 
      Map<Long, Set<Long>> nameIdToCpdSet) {
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/property/name.tsv");
      importNameData(idToName, nameIdToCpdSet, is);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
  }

  public static void importInchi(Map<Long, String> idToInchi) {
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/property/inchi.tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i);
        String[] col = line.split(SEP);
        long id = Long.parseLong(col[0]);
        String name = col[1];
        idToInchi.put(id, name);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
  }

  public static void importSmiles(Map<Long, String> idToSmiles) {
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/property/smiles.tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i);
        String[] col = line.split(SEP);
        long id = Long.parseLong(col[0]);
        String smiles = col[1];
        idToSmiles.put(id, smiles);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
  }

  public static<V> V getOrNull(V[] array, int index, V defaultValue) {
    if (array != null && array.length > index) {
      return array[index];
    }

    return defaultValue;
  }

  public static<V> V getOrNull(V[] array, int index) {
    return getOrNull(array, index, null);
  }
  
  public static Set<Long> importDatabaseRxn(String database, 
      Map<Long, String> rxnIdToEntry,
      Map<Long, Map<Long, Double>> rxnStoichMap,
      Map<Long, String> idToName) {
    Set<Long> ids = new HashSet<> ();
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/" + database + "_rxn.tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        try {
          String line = lines.get(i);
          String[] col = line.split(SEP);
          String[] lstr = getOrNull(col, 2, "").split(" ");
          String[] rstr = getOrNull(col, 3, "").split(" ");
          String[] lvstr = getOrNull(col, 4, "").split(" ");
          String rvstr_ = getOrNull(col, 5, "");
          String name = getOrNull(col, 6, null);
          String[] rvstr = rvstr_.split(" ");
          long rxnId = Long.parseLong(col[0]);
          String rxnEntry = col[1];
          Map<Long, Double> stoichiometry = new HashMap<> ();
          boolean error = false;
          for (int k = 0; k < lstr.length; k++) {
            if (!lstr[k].trim().isEmpty()) {
              long spiId = Long.parseLong(lstr[k]);
              double value = -1 * Double.parseDouble(lvstr[k]);
              if (stoichiometry.put(spiId, value) != null) {
                error = true;
              }
            }
          }
          for (int k = 0; k < rstr.length; k++) {
            if (!rstr[k].trim().isEmpty()) {
              long spiId = Long.parseLong(rstr[k]);
              double value = Double.parseDouble(rvstr[k]);
              if (stoichiometry.put(spiId, value) != null) {
                error = true;
              }
            }
          }
          if (!error && !stoichiometry.isEmpty()) {
            ids.add(rxnId);
            rxnIdToEntry.put(rxnId, rxnEntry);
            rxnStoichMap.put(rxnId, stoichiometry);
          } else {
            logger.trace("Invalid: {}", line);
          }
          
          if (name != null && !name.trim().isEmpty()) {
            idToName.put(rxnId, name);
          }
        } catch (Exception e) {
          System.err.println(lines.get(i));
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ids;
  }

  public static Set<Long> importDatabaseRxn(String database, 
      Map<Long, String> rxnIdToEntry,
      Map<Long, Map<Long, Double>> rxnStoichMap) {
    Set<Long> ids = new HashSet<> ();
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/" + database + "_rxn.tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        try {
          String line = lines.get(i);
          String[] col = line.split(SEP);
          String[] lstr = getOrNull(col, 2, "").split(" ");
          String[] rstr = getOrNull(col, 3, "").split(" ");
          String[] lvstr = getOrNull(col, 4, "").split(" ");
          String rvstr_ = getOrNull(col, 5, "");
          String[] rvstr = rvstr_.split(" ");
          long rxnId = Long.parseLong(col[0]);
          String rxnEntry = col[1];
          Map<Long, Double> stoichiometry = new HashMap<> ();
          boolean error = false;
          for (int k = 0; k < lstr.length; k++) {
            if (!lstr[k].trim().isEmpty()) {
              long spiId = Long.parseLong(lstr[k]);
              double value = -1 * Double.parseDouble(lvstr[k]);
              if (stoichiometry.put(spiId, value) != null) {
                error = true;
              }
            }
          }
          for (int k = 0; k < rstr.length; k++) {
            if (!rstr[k].trim().isEmpty()) {
              long spiId = Long.parseLong(rstr[k]);
              double value = Double.parseDouble(rvstr[k]);
              if (stoichiometry.put(spiId, value) != null) {
                error = true;
              }
            }
          }
          if (!error && !stoichiometry.isEmpty()) {
            ids.add(rxnId);
            rxnIdToEntry.put(rxnId, rxnEntry);
            rxnStoichMap.put(rxnId, stoichiometry);
          } else {
            logger.debug("Invalid: {}", line);
          }
        } catch (Exception e) {
          System.err.println(lines.get(i));
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ids;
  }

  public static Set<Long> importDatabaseCpd(
      Map<String, Map<String, Map<String, Object>>> data, Map<Long, String> aliasMap) {

    Set<Long> ids = new HashSet<> ();
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/global_cpd.tsv");
      List<String> lines = IOUtils.readLines(is);
      for (int i = 1; i < lines.size(); i++) {
        try {
          String line = lines.get(i);
          String[] col = line.concat(SEP + "!").split(SEP);
          long id = Long.parseLong(col[0]);
          String database = col[1];
          String entry = col[2];
          String name = col[3];
          boolean proxy = Boolean.parseBoolean(col[4]);
          String alias = col[5];
          String refs = col[6];
          Map<String, Object> prop = new HashMap<> ();
          prop.put("entry", entry);
          prop.put("name", name);
          prop.put("proxy", proxy);
          prop.put("id", id);
          prop.put("references", refs);
          if (aliasMap != null && alias != null && !alias.isEmpty()) {
            aliasMap.put(id, alias);
          }
          
          if (!data.containsKey(database)) {
            data.put(database, new HashMap<String, Map<String, Object>> ());
          }
          data.get(database).put(entry, prop);
          ids.add(id);
        } catch (Exception e) {
          System.err.println(lines.get(i));
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }

    return ids;
  }
  
  public static Double getDouble(String str) {
    
    if (str == null || str.trim().isEmpty()) {
      return null;
    }
    
    try {
      return Double.parseDouble(str);
    } catch (Exception e) {
      logger.warn("parse double [{}] - {}", str, e.getMessage());
      return null;
    }
  }
  
  public static Set<Long> importModelRxn(String mmdEntry, 
      Map<Long, String> rxnIdToEntry,
      Map<Long, String> rxnIdToName,
      Map<Long, String> rxnIdToPathwayAlias,
      Map<Long, Map<Long, Double>> rxnStoichMap,
      Map<Long, String> rxnType,
      Map<Long, Double[]> rxnBounds,
      Map<Long, Boolean> rxnReversible) {
    Set<Long> ids = new HashSet<> ();
    InputStream is = null;
    
    boolean oldFile = false;
    
    try {
      is = new FileInputStream(EXPORT_PATH + "/" + mmdEntry + "_rxn.tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        try {
          String line = lines.get(i).concat("\t!");
          String[] col = line.split(SEP);
          long rxnId = Long.parseLong(col[0]);
          String rxnEntry = col[1];
          String[] lstr = col[2].split(" ");
          String[] rstr = col[3].split(" ");
          String[] lvstr = col[4].split(" ");
          String[] rvstr = col[5].split(" ");
          String type = "UNKNOWN";
          Double lb = Double.MIN_VALUE;
          Double ub = Double.MIN_VALUE;
          String name = rxnEntry;
          String pathway = "";
          String rev = "";
          if (col.length < 7) {
            oldFile = true;
          } else {
            type = col[6];
            lb = getDouble(col[7]);
            ub = getDouble(col[8]);
            //Double.parseDouble(col[8]);
            name = col[9];
            pathway = col[10];
            rev = col[12];
          }
//          String rvstr_ = getOrNull(col, 5);
//          String[] rvstr = rvstr_ == null ? new String[0] : rvstr_.split(" ");
          
          rxnReversible.put(rxnId, null);
          if (rev != null && !rev.isEmpty()) {
            rxnReversible.put(rxnId, Boolean.parseBoolean(rev));
          }
          
          rxnType.put(rxnId, type);
          rxnBounds.put(rxnId, new Double[]{lb, ub});
          Map<Long, Double> stoichiometry = new HashMap<> ();
          for (int k = 0; k < lstr.length; k++) {
            if (!lstr[k].trim().isEmpty()) {
              long spiId = Long.parseLong(lstr[k]);
              double value = -1 * Double.parseDouble(lvstr[k]);
              Double prev = stoichiometry.put(spiId, value);
              if (prev != null) {
                logger.warn("{} {} LHS: [{}] {}", mmdEntry, rxnEntry, spiId, prev);
                stoichiometry.put(spiId, value + prev);
              }
            }
          }
          for (int k = 0; k < rstr.length; k++) {
            if (!rstr[k].trim().isEmpty()) {
              long spiId = Long.parseLong(rstr[k]);
              double value = Double.parseDouble(rvstr[k]);
              Double prev = stoichiometry.put(spiId, value);
              if (prev != null) {
                logger.warn("{} {} LHS: [{}] {}", mmdEntry, rxnEntry, spiId, prev);
                stoichiometry.put(spiId, value + prev);
              }
            }
          }
          ids.add(rxnId);
          rxnIdToEntry.put(rxnId, rxnEntry);
          rxnStoichMap.put(rxnId, stoichiometry);
          rxnIdToName.put(rxnId, name);
          rxnIdToPathwayAlias.put(rxnId, pathway);
        } catch (Exception e) {
          System.err.println(lines.get(i));
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    if (oldFile) {
      logger.warn("Old export file. Missing reaction type and bounds");
    }

    return ids;
  }

  @Deprecated
  public static Set<Long> importModelRxn(String mmdEntry, 
      Map<Long, String> rxnIdToEntry,
      Map<Long, Map<Long, Double>> rxnStoichMap) {
    Set<Long> ids = new HashSet<> ();
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/" + mmdEntry + "_rxn.tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        try {
          String line = lines.get(i);
          String[] col = line.split(SEP);
          String[] lstr = col[2].split(" ");
          String[] rstr = col[3].split(" ");
          String[] lvstr = col[4].split(" ");
          String rvstr_ = getOrNull(col, 5);
          String[] rvstr = rvstr_ == null ? new String[0] : rvstr_.split(" ");
          long rxnId = Long.parseLong(col[0]);
          String rxnEntry = col[1];
          Map<Long, Double> stoichiometry = new HashMap<> ();
          for (int k = 0; k < lstr.length; k++) {
            if (!lstr[k].trim().isEmpty()) {
              long spiId = Long.parseLong(lstr[k]);
              double value = -1 * Double.parseDouble(lvstr[k]);
              stoichiometry.put(spiId, value);
            }
          }
          for (int k = 0; k < rstr.length; k++) {
            if (!rstr[k].trim().isEmpty()) {
              long spiId = Long.parseLong(rstr[k]);
              double value = Double.parseDouble(rvstr[k]);
              stoichiometry.put(spiId, value);
            }
          }
          ids.add(rxnId);
          rxnIdToEntry.put(rxnId, rxnEntry);
          rxnStoichMap.put(rxnId, stoichiometry);
        } catch (Exception e) {
          System.err.println(lines.get(i));
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ids;
  }

  
  
  public static Set<Long> importModelSpi(String mmdEntry,
      Map<Long, String> spiIdToEntry,
      Map<Long, String> spiIdToName,
      Map<Long, String> spiIdToFormula,
      Map<Long, Set<Long>> spiIdToCpdRefSet,
      Map<Long, Long> spiIdToCmpId,
      Map<Long, String> cmpIdToEntry,
      Map<Long, String> cmpIdToAnnotation,
      Map<Long, String> mcpdToEntry,
      Map<Long, Set<Long>> mcpdToSpiSet,
      Map<Long, String> spiToType,
      Map<Long, Boolean> spiToBondary) {
    
    Map<SubcellularCompartment, Long> scmpIdMap = new HashMap<> ();
    scmpIdMap.put(SubcellularCompartment.CYTOSOL, -10L);
    scmpIdMap.put(SubcellularCompartment.PLASMA_MEMBRANE, -11L);
    scmpIdMap.put(SubcellularCompartment.PERIPLASM, -12L);
    
    scmpIdMap.put(SubcellularCompartment.VACUOLE, -14L);
    scmpIdMap.put(SubcellularCompartment.RETICULUM, -15L);
    scmpIdMap.put(SubcellularCompartment.GOLGI, -16L);
    scmpIdMap.put(SubcellularCompartment.MITOCHONDRIA, -17L);
    scmpIdMap.put(SubcellularCompartment.NUCLEUS, -18L);
    
    scmpIdMap.put(SubcellularCompartment.VACUOLAR_MEMBRANE, -114L);
    scmpIdMap.put(SubcellularCompartment.RETICULUM_MEMBRANE, -115L);
    scmpIdMap.put(SubcellularCompartment.GOLGI_MEMBRANE, -116L);
    scmpIdMap.put(SubcellularCompartment.MITOCHONDRIA_MEMBRANE, -117L);

    scmpIdMap.put(SubcellularCompartment.LIPID, -61L);
    scmpIdMap.put(SubcellularCompartment.CELL_ENVELOPE, -62L);
    
    scmpIdMap.put(SubcellularCompartment.PEROXISOME, -30L);
    scmpIdMap.put(SubcellularCompartment.CARBOXYSOME, -31L);
    scmpIdMap.put(SubcellularCompartment.THYLAKOID_LUMEN, -35L);
    
    scmpIdMap.put(SubcellularCompartment.THYLAKOID_MEMBRANE, -45L);
    
    scmpIdMap.put(SubcellularCompartment.EXTRACELLULAR, -20L);
    scmpIdMap.put(SubcellularCompartment.BOUNDARY, -21L);
    
    
    Set<Long> ids = new HashSet<> ();
    InputStream is = null;
    try {
      is = new FileInputStream(EXPORT_PATH + "/" + mmdEntry + "_spi.tsv");
      List<String> lines = IOUtils.readLines(is);

      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i).concat(SEP + "!");
        String[] col = line.split(SEP);
        long spiId = Long.parseLong(col[0]);
        String spiEntry = col[1];
        String spiName = col[2];
        String spiFormula = col[4];
        Long cmpId = null;
        if (!(col[5] == null || col[5].trim().isEmpty() || col[5].equals("null"))) {
          cmpId = Long.parseLong(col[5]);
        }
        String cmpEntry = col[6];
        String cmpAnnotation = col[7];
        
//        logger.info("[{} {} {} {}]", spiEntry, cmpId, cmpEntry, cmpAnnotation);
        
        if (cmpAnnotation != null && !cmpAnnotation.trim().isEmpty()) {
          SubcellularCompartment scmp = SubcellularCompartment.valueOf(cmpAnnotation);
          cmpId = scmpIdMap.get(scmp);
          if (cmpId == null) {
            logger.error("NO DEFAULT CMP ID FOR {}", scmp);
          }
        }
        if (!col[8].trim().isEmpty()) {
          long mcpdId = Long.parseLong(col[8]);
          String mcpdEntry = col[9];
          mcpdToEntry.put(mcpdId, mcpdEntry);
          if (!mcpdToSpiSet.containsKey(mcpdId)) {
            mcpdToSpiSet.put(mcpdId, new HashSet<Long> ());
          }
          mcpdToSpiSet.get(mcpdId).add(spiId);
        }
        String spiType = col[10];
        String isBondary = col[11];
        if (isBondary.toLowerCase().equals("true") || 
            isBondary.toLowerCase().equals("false")) {
          spiToBondary.put(spiId, Boolean.parseBoolean(isBondary));
        }
        
        Set<Long> cpdRefSet = new HashSet<> ();
        if (!col[3].trim().isEmpty()) {
          for (String ref : col[3].split(" ")) {
            cpdRefSet.add(Long.parseLong(ref));
          }
        }
        ids.add(spiId);
        spiIdToCpdRefSet.put(spiId, cpdRefSet);
        spiIdToEntry.put(spiId, spiEntry);
        spiIdToName.put(spiId, spiName);
        spiIdToFormula.put(spiId, spiFormula);
        if (cmpId != null) {
          spiIdToCmpId.put(spiId, cmpId);
          cmpIdToEntry.put(cmpId, cmpEntry);
          if (cmpAnnotation != null && !cmpAnnotation.trim().isEmpty()) {
            cmpIdToAnnotation.put(cmpId, cmpAnnotation);          
          } else {
            cmpIdToAnnotation.put(cmpId, SubcellularCompartment.UNKNOWN.toString());
          }
        }

        spiToType.put(spiId, spiType);

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ids;
  }


  public static void smiles(Map<String, ? > nameMap, Map<String, ?> nameLcMap) {
    Map<Long, String> idToSmiles = new HashMap<> ();
    Map<Long, String> idToIupac = new HashMap<> ();
    Map<Long, String> idToTraditional = new HashMap<> ();
    Map<Long, String> idToFormula = new HashMap<> ();
    importSmilesNameData(idToIupac, idToTraditional, idToFormula);
    importSmiles(idToSmiles);

    //duplicate smiles
    Map<String, Set<Long>> smilesToId = CollectionUtils.reverseMap(idToSmiles);
    hmm(smilesToId, idToSmiles);

    //does a iupac name has two distinct smiles ?
    //OK: naming convention
    System.out.println("IUPAC duplicates");
    Map<String, Set<Long>> iupacToSmiles = CollectionUtils.reverseMap(idToIupac);
    hmm(iupacToSmiles, idToSmiles);
    System.out.println("-----------");
    //does a trad name has two distinct smiles ?
    //OK: naming convention
    System.out.println("Traditional duplicates");
    Map<String, Set<Long>> traditionalToSmiles = CollectionUtils.reverseMap(idToTraditional);
    hmm(traditionalToSmiles, idToSmiles);
    System.out.println("-----------");

    int totalIupac = iupacToSmiles.size();
    int foundIupac = 0;
    int foundIupacLc = 0;
    for (String n : iupacToSmiles.keySet()) {
      if (n != null) {
        foundIupac += nameMap.containsKey(n) ? 1 : 0;
        foundIupacLc += nameLcMap.containsKey(n.toLowerCase()) ? 1 : 0;
      }
    }
    System.out.println(totalIupac + "\t" + foundIupac + "\t" + foundIupacLc);
    System.out.println("-----------");
    //how many traditional names found ?
    int totalTrad = traditionalToSmiles.size();
    int foundTrad = 0;
    int foundTradLc = 0;
    for (String n : traditionalToSmiles.keySet()) {
      if (n != null) {
        foundTrad += nameMap.containsKey(n) ? 1 : 0;
        foundTradLc += nameLcMap.containsKey(n.toLowerCase()) ? 1 : 0;
      }
    }
    System.out.println(totalTrad + "\t" + foundTrad + "\t" + foundTradLc);
    System.out.println("-----------");
  }
  
  
  /**
   * this should not be here
   */
  public static void someAnalysis() {
    Map<Long, String> idToInchi = new HashMap<> ();
    BMap<Long, String> idToName = new BHashMap<> ();
    Map<Long, String> idToNameLc = new HashMap<> ();
    Map<Long, String> idToIupac = new HashMap<> ();
    Map<Long, String> idToTraditional = new HashMap<> ();
    Map<Long, String> idToFormula = new HashMap<> ();
    importInchiNameData(idToIupac, idToTraditional, idToFormula);
    //    importNameData(idToName);
    importInchi(idToInchi);
    for (long id : idToName.keySet()) {
      idToNameLc.put(id, idToName.get(id).toLowerCase());
    }

    //do we have duplicates ? 
    //CRITIAL: constrain violation
    System.out.println("Duplicates:");
    Map<String, Set<Long>> omg = idToName.getReverse();
    hmm(omg, idToName);
    System.out.println("-----------");
    //do we have duplicates if all LowerCase ?
    //WARN: should normalize names
    System.out.println("Duplicates (LC):");
    Map<String, Set<Long>> omg_ = CollectionUtils.reverseMap(idToNameLc);
    hmm(omg_, idToName);
    System.out.println("-----------");

    //does a iupac name has two distinct inchis ?
    //OK: naming convention
    System.out.println("IUPAC duplicates");
    Map<String, Set<Long>> iupacToInchi = CollectionUtils.reverseMap(idToIupac);
    hmm(iupacToInchi, idToInchi);
    System.out.println("-----------");
    //does a trad name has two distinct inchis ?
    //OK: naming convention
    System.out.println("Traditional duplicates");
    Map<String, Set<Long>> traditionalToInchi = CollectionUtils.reverseMap(idToTraditional);
    hmm(traditionalToInchi, idToInchi);
    System.out.println("-----------");

    //how many iupac names found ?

    int totalIupac = iupacToInchi.size();
    int foundIupac = 0;
    int foundIupacLc = 0;
    for (String n : iupacToInchi.keySet()) {
      foundIupac += omg.containsKey(n) ? 1 : 0;
      foundIupacLc += omg_.containsKey(n.toLowerCase()) ? 1 : 0;
    }
    System.out.println(totalIupac + "\t" + foundIupac + "\t" + foundIupacLc);
    System.out.println("-----------");
    //how many traditional names found ?
    int totalTrad = traditionalToInchi.size();
    int foundTrad = 0;
    int foundTradLc = 0;
    for (String n : traditionalToInchi.keySet()) {
      foundTrad += omg.containsKey(n) ? 1 : 0;
      foundTradLc += omg_.containsKey(n.toLowerCase()) ? 1 : 0;
    }
    System.out.println(totalTrad + "\t" + foundTrad + "\t" + foundTradLc);
    System.out.println("-----------");

    smiles(omg, omg_);
  }

  public static void main(String[] args) {
    //    public static Set<Long> proton = new HashSet<> ();
    //    Map<Long, String> nodeIdToEntry = new HashMap<> ();
    //    public static Map<Long, String> rxnIdToType = new HashMap<> ();
    //    public static Map<Long, String> rxnIdToDatabase = new HashMap<> ();
    //    Map<Long, Set<Long>> spiIdToCpdRefSet = new HashMap<> ();
    //    Map<Long, Map<Long, Double>> rxnMap = new HashMap<> ();
    //    importModelSpi("iMM904", nodeIdToEntry, spiIdToCpdRefSet);
    //    importModelRxn("iMM904", nodeIdToEntry, rxnMap);
    //    System.out.println(nodeIdToEntry);
    //    System.out.println(spiIdToCpdRefSet);

    //    for (String[] mmdData : ModelInformation.models) {
    //      String mmdEntry = mmdData[3];
    //      importModelSpi(mmdEntry, nodeIdToEntry, spiIdToCpdRefSet);
    //      importModelRxn(mmdEntry, nodeIdToEntry, rxnMap);
    //    }
    //    
    //    importDatabaseRxn(ReactionMajorLabel.BiGG.toString(), nodeIdToEntry, rxnMap);
    //    importDatabaseRxn(ReactionMajorLabel.LigandReaction.toString(), nodeIdToEntry, rxnMap);
    //    System.out.println(nodeIdToEntry.size());

    //    for (Map<Long, Double> s : rxnMap.values()) {
    //      System.out.println(s.size());
    //    }
    //    printMap(nodeIdToEntry);
//    Map<Long, Long> spiUmap = FileImport.importModelSpecieUnificationMap(BiosynthConfiguration.INTEGRATION_SET);
//    System.out.println(spiUmap.size());
  }

  public static void hmm(Map<String, Set<Long>> omg, Map<Long, String> map) {
    for (String k : omg.keySet()) {
      if (omg.get(k).size() != 1) {
        System.out.println(k + " " + omg.get(k));
        for (long id : omg.get(k)) {
          System.out.println("\t" + map.get(id));
        }
      }
    }
  }

  public static<K, V> void printMap(Map<K, V> map) {
    for (K k : map.keySet()) {
      System.out.println(k + " " + map.get(k));
    }
  }

  public static void parseCollectionString(String string, Collection<Long> a) {
    for (String e : string.replace("[", "").replace("]", "").split(", ")) {
      if (!e.trim().isEmpty()) {
        a.add(Long.parseLong(e));
      }
    }
  }

//  public static NameMappingResult<Pair<String, Set<Long>>> importModelSpiNameMappping(String path) {
////    final int RANK_OFFSET = 4;
//    InputStream is = null;
//    NameMappingResult<Pair<String, Set<Long>>> result = null;
//    try { 
//      is = new FileInputStream(path);
//      result = DefaultBiobaseImportServiceImpl.importModelSpiNameMappping(is);
////      List<String> lines = IOUtils.readLines(is);
////      for (int i = 1; i < lines.size(); i++) {
////        String[] col = lines.get(i).concat(SEP + "!").split(SEP);
////        List<Long> spiIds = new ArrayList<> ();
////        for (String id : col[0].split(" ")) {
////          spiIds.add(Long.parseLong(id));
////        }
////
////        List<Set<Long>> spiReferences = new ArrayList<> ();
////        for (String refStr : Arrays.asList(col[1].split(" "))) {
////          Set<Long> spiRef = new HashSet<> ();
////          parseCollectionString(refStr, spiRef);
////          spiReferences.add(spiRef);
////        }
////        String spiName = col[2];
////        List<String> spiFormula = Arrays.asList(col[3].split(" "));
////        List<ScoredEntity<Pair<String, Set<Long>>>> scoreList = new ArrayList<> ();
////        for (int j = RANK_OFFSET; j < col.length - 1; j+=3) {
////          //          System.out.println(Arrays.toString(col));
////          Set<Long> sref = new HashSet<> ();
////          parseCollectionString(col[j], sref);
////          double svalue = Double.parseDouble(col[j + 1]);
////          String sname = col[j + 2];
////
////          Pair<String, Set<Long>> spair = new ImmutablePair<>(sname, sref);
////          ScoredEntity<Pair<String, Set<Long>>> score = 
////              new ScoredEntity<>(spair, svalue);
////          scoreList.add(score);
////        }
////        result.addScore(spiName, spiIds, spiReferences, scoreList);
//
////      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    } finally {
//      IOUtils.closeQuietly(is);
//    }
//
//    return result;
//  }

  public static Map<Set<Long>, SubcellularCompartment> importSpecieIntegration(
      String path, BiodbService biodbService) {
    
    InputStream is = null;
    Map<Set<Long>, SubcellularCompartment> integration = null;
    try {
      integration = new HashMap<>(); 
      is = new FileInputStream(path);
      List<String> lines = IOUtils.readLines(is);
      
      for (String line : lines) {
        String[] col = line.split("\t");
        if (col.length > 1) {
          Set<Long> ctr = new HashSet<> ();
          Set<SubcellularCompartment> ctrScmp = new HashSet<> ();
          boolean valid = true;
          
          try {
            for (String c : col) {
              long spiId = Long.parseLong(c);
              ctr.add(spiId);
              Long cmpId = biodbService.getSpecieCompartmentId(spiId);
//              System.out.println(spiId + " " + biodbService.getEntryById(cmpId));
              if (cmpId != null) {
                SubcellularCompartment scmp = 
                    biodbService.getCompartmentSubcellularLocation(cmpId);
                ctrScmp.add(scmp);
              } else {
                valid = false;
              }
            }
          } catch (Exception e) {
            logger.error("{}: {}", line, e.getMessage());
            valid = false;
          }
          
          if (valid) {
            //Specie cluster if single scmp and not UNKNOWN
            if (ctrScmp.size() == 1 && 
                !ctrScmp.iterator()
                        .next()
                        .equals(SubcellularCompartment.UNKNOWN)) {
              integration.put(ctr, ctrScmp.iterator().next());
            }
          } else {
            logger.debug("Invalid set {}", ctr);
          }
        } else {
          logger.debug("SKIP {}", line);
        }
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
    return integration;
  }

  public static Set<Set<Long>> importIntegration(String path) {
    InputStream is = null;
    Set<Set<Long>> integration = null;
    try {
      integration = new HashSet<>(); 
      is = new FileInputStream(path);
      List<String> lines = IOUtils.readLines(is);
      
      for (String line : lines) {
        String[] col = line.split("\t");
        if (col.length > 1 && !line.startsWith("#")) {
          Set<Long> ctr = new HashSet<> ();
          for (String c : col) {
            long spiId = Long.parseLong(c);
            ctr.add(spiId);
          }
          integration.add(ctr);
        } else {
          logger.debug("SKIP {}", line);
        }
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
    
    return integration;
  }

  public static Set<String> importExtraSubstrates(String path) {
    InputStream is = null;
    Set<String> extra = null;
    try {
      extra = new HashSet<>(); 
      is = new FileInputStream(path);
      List<String> lines = IOUtils.readLines(is);
      
      for (String line : lines) {
        String[] col = line.split("#");
        String id = col[0].trim();
        extra.add(id);
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
    return extra;
  }

  public static Set<Long> importTaxonomyGenes(Map<Long, String> idToEntry, Map<Long, Long> protToTaxa) {
    Set<Long> result = new HashSet<> ();
    InputStream is = null;
    try {
      
      is = new FileInputStream(EXPORT_PATH + "/taxonomy_proteins.tsv");
      List<String> lines = IOUtils.readLines(is);
      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i).concat(SEP + "!");
        String[] col = line.split(SEP);
        long txId = Long.parseLong(col[0]);
        long protId = Long.parseLong(col[1]);
//        String database = col[2];
        String protEntry = col[3];
        idToEntry.put(protId, protEntry);
        protToTaxa.put(protId, txId);
        result.add(protId);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
//    result.clear();
    return result;
  }
  
  public static Set<Long> importNCBITaxonomy(
      Map<Long, Long> modelIdToTaxId,
      Map<Long, String> idToEntry,
      Map<Long, String> idToName,
      Map<Long, Long> idToParent) {
    Set<Long> result = new HashSet<> ();
    InputStream is = null;
    try {
      
      is = new FileInputStream(EXPORT_PATH + "/ncbi_taxonomy.tsv");
      List<String> lines = IOUtils.readLines(is);
      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i).concat(SEP + "!");
        String[] col = line.split(SEP);
        long txId = Long.parseLong(col[0]);
        String entry = col[1];
        String name = col[2];
        String parent = col[3];
        if (parent != null && !parent.trim().isEmpty()) {
          long parentId = Long.parseLong(col[3].trim());
          idToParent.put(txId, parentId);
        }
        String modelStr = col[4];
        for (String model : modelStr.trim().split(" ")) {
          if (!model.trim().isEmpty()) {
            modelIdToTaxId.put(Long.parseLong(model.trim()), txId);
          }
        }
        idToEntry.put(txId, entry);
        idToName.put(txId, name);
        
        result.add(txId);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
    
    return result;
  }
}