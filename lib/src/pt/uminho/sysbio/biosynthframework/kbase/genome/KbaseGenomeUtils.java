package pt.uminho.sysbio.biosynthframework.kbase.genome;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbasefba.FBAModel;
import kbasefba.ModelReaction;
import kbasefba.ModelReactionProtein;
import kbasegenomes.Feature;
import kbasegenomes.Genome;
import kbsolrutil.KBaseAPI;
import pt.uminho.sysbio.biosynthframework.BFunction;
import pt.uminho.sysbio.biosynthframework.biodb.seed.ModelSeedRole;
import pt.uminho.sysbio.biosynthframework.io.biodb.JsonModelSeedRoleDao;
import pt.uminho.sysbio.biosynthframework.kbase.FBAModelFactory;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseId;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseUtils;
import sbmltools.KBaseType;

public class KbaseGenomeUtils {
  
  private static final Logger logger = LoggerFactory.getLogger(KbaseGenomeUtils.class);
  
  public static String NUC44 = 
      "#\n" +
      "# This matrix was created by Todd Lowe   12/10/92\n"+ 
      "#\n" + 
      "# Uses ambiguous nucleotide codes, probabilities rounded to\n" + 
      "#  nearest integer\n" +
      "#\n" +
      "# Lowest score = -4, Highest score = 5\n" +
      "#\n" +
      "    A   T   G   C   S   W   R   Y   K   M   B   V   H   D   N\n" +
      "A   5  -4  -4  -4  -4   1   1  -4  -4   1  -4  -1  -1  -1  -2\n" +
      "T  -4   5  -4  -4  -4   1  -4   1   1  -4  -1  -4  -1  -1  -2\n" +
      "G  -4  -4   5  -4   1  -4   1  -4   1  -4  -1  -1  -4  -1  -2\n" +
      "C  -4  -4  -4   5   1  -4  -4   1  -4   1  -1  -1  -1  -4  -2\n" +
      "S  -4  -4   1   1  -1  -4  -2  -2  -2  -2  -1  -1  -3  -3  -1\n" +
      "W   1   1  -4  -4  -4  -1  -2  -2  -2  -2  -3  -3  -1  -1  -1\n" +
      "R   1  -4   1  -4  -2  -2  -1  -4  -2  -2  -3  -1  -3  -1  -1\n" +
      "Y  -4   1  -4   1  -2  -2  -4  -1  -2  -2  -1  -3  -1  -3  -1\n" +
      "K  -4   1   1  -4  -2  -2  -2  -2  -1  -4  -1  -3  -3  -1  -1\n" +
      "M   1  -4  -4   1  -2  -2  -2  -2  -4  -1  -3  -1  -1  -3  -1\n" +
      "B  -4  -1  -1  -1  -1  -3  -3  -1  -1  -3  -1  -2  -2  -2  -1\n" +
      "V  -1  -4  -1  -1  -1  -3  -1  -3  -3  -1  -2  -1  -2  -2  -1\n" +
      "H  -1  -1  -4  -1  -3  -1  -3  -1  -3  -1  -2  -2  -1  -2  -1\n" +
      "D  -1  -1  -1  -4  -3  -1  -1  -3  -1  -3  -2  -2  -2  -1  -1\n" +
      "N  -2  -2  -2  -2  -1  -1  -1  -1  -1  -1  -1  -1  -1  -1  -1\n";
  
  public static void buildPolymeraseGenomes(String loginToken, String workspace, OutputStream os) {
    try {
      KBaseAPI prodAPI = new KBaseAPI(loginToken, KBaseAPI.getConfigProd(), true);
      List<KBaseId> kids = prodAPI.listNarrative(workspace, KBaseType.Genome);
      Map<String, Map<String, String>> db = new HashMap<> ();
      for (KBaseId kid : kids) {
        if (kid.name.endsWith(".rast")) {
          Genome genome = prodAPI.getGenome(kid.name, kid.workspace);
          db.put(kid.name, new HashMap<String, String> ());
          Map<String, String> data = db.get(kid.name);
          data.put("scientific_name", genome.getScientificName());
          Feature polymeraseBetaUnit = KbaseGenomeUtils.findRnaPolymeraseBetaUnit(genome);
          if (polymeraseBetaUnit != null) {
            data.put("feature", polymeraseBetaUnit.getId());
            data.put("function", polymeraseBetaUnit.getFunction());
            data.put("size", Long.toString(polymeraseBetaUnit.getDnaSequenceLength()));
            data.put("seq", polymeraseBetaUnit.getDnaSequence());
          }
        }
      }
      
      for (String g : db.keySet()) {
        Map<String, String> data = db.get(g);
        if (data != null && data.get("seq") == null) {
          logger.warn("[{}]{}", g, data.get("scientific_name"));
        } else {
          String h = String.format(">%s|%s|%s\n", g, data.get("function"), data.get("scientific_name"));
          os.write(h.getBytes());
          os.write(data.get("seq").getBytes());
          os.write("\n".getBytes());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void buildBlastDb(String path, String loginToken, String workspace) {
    OutputStream os = null;
    try {
      os = new FileOutputStream(path);
      buildPolymeraseGenomes(loginToken, workspace, os);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(os);
    }
  }
  
  public static Map<String, Set<String>> getRolesMap() {
    Map<String, Set<String>> result = new HashMap<> ();
//    Resource roleJson = new FileSystemResource("/var/biodb/modelseed/Roles.json");
    JsonModelSeedRoleDao roleDao = null; //new JsonModelSeedRoleDao(roleJson);
    Map<String, Set<String>> aa = new HashMap<> ();
    for (String k : roleDao.data.keySet()) {
      ModelSeedRole role = roleDao.data.get(k);
      //  System.out.println(k + " " + role.name);
      if (!aa.containsKey(role.name.trim().toLowerCase())) {
        aa.put(role.name.trim().toLowerCase(), new HashSet<String> ());
      }
      aa.get(role.name.trim().toLowerCase()).add(k);
    }
    int good = 0;
    int bad = 0;
    for (String k : aa.keySet()) {

      if (aa.get(k).size() > 1) {
        //    System.out.println(k + " " + aa.get(k));
        result.put(k.trim().toLowerCase(), aa.get(k));
        bad++;
      } else {
        result.put(k.trim().toLowerCase(), aa.get(k));
        good++;
      }
    }
    logger.debug("roles good: {}, bad: {}", good, bad);
    return result;
  }
  
  public static void integrateGenes(
      FBAModel kmodel, 
      Genome genome,
      BFunction<ModelReaction, String> gprTransform,
      BFunction<String, String> geneTransform) {
    for (ModelReaction o : kmodel.getModelreactions()) {
      String gpr = o.getImportedGpr();
      if (gprTransform != null) {
        gpr = gprTransform.apply(o);
      }
      Set<String> genes = KBaseUtils.getGenes(gpr, geneTransform);
      if (genes != null && !genes.isEmpty()) {
        List<ModelReactionProtein> mrpList = FBAModelFactory.setupModelReactionProteins(genes, genome, kmodel.getGenomeRef());
        o.setModelReactionProteins(mrpList);
      } else {
        o.setModelReactionProteins(new ArrayList<ModelReactionProtein> ());
      }
    }

  }
  
  public static Feature findRnaPolymeraseBetaUnit(Genome genome) {
    Feature result = null;
    Map<String, Long> sizes = new HashMap<> ();
    Map<String, Feature> gfeatures = new HashMap<> ();
    Map<String, String> gseq = new HashMap<> ();
    
    for (Feature f : genome.getFeatures()) {
      String function = f.getFunction();
      gfeatures.put(f.getId(), f);
      gseq.put(f.getId(), f.getDnaSequence());
      if (function != null && 
          (function.toUpperCase().contains("2.7.7.6") && function.toUpperCase().contains("BETA"))
          ) {
        sizes.put(f.getId(), f.getDnaSequenceLength());
      }
    }
    
    long high = 0;
    String best = null;
    for (String k : sizes.keySet()) {
      long s = sizes.get(k);
      if (s > high) {
        high = s;
        best = k;
      }
    }
    if (best != null) {
      logger.info("{} [{}]: {}", best, high, gfeatures.get(best).getFunction());
//      data.put("feature", best);
//      data.put("function", gfunctions.get(best));
//      data.put("size", Long.toString(high));
//      data.put("seq", gseq.get(best));
      result = gfeatures.get(best);
    }
    
    return result;
  }
}
