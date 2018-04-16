package sbmltools.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import kbasegenomes.Genome;
import pt.uminho.sysbio.biosynthframework.kbase.FBAModelFactory;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseGenomeAdapter;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.util.GprUtils;
import pt.uminho.sysbio.biosynthframework.util.math.MathUtils;

public class TestTools {
  
  public static void loadZipGenome(String path) {
    try {
      Genome genome = KBaseIOUtils.loadJsonGenomeFromZip(path);
      System.out.println(genome != null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void testBuildReactionProteinFromGprString(String gpr) {
    Set<Set<String>> prots = GprUtils.getProteins(gpr);
    System.out.println(prots);
  }
  
  public static void testBuildReactionProteinFromGprString(String gpr, String path) {
    Set<Set<String>> prots = GprUtils.getProteins(gpr);
    Set<Set<String>> prots2 = new HashSet<>();
    
    try {
      Genome genomeo = KBaseIOUtils.loadJsonGenomeFromZip(path);
      KBaseGenomeAdapter genome = new KBaseGenomeAdapter(genomeo);
      for (Set<String> p : prots) {
        Set<String> pp = new HashSet<>();
        for (String g : p) {
          String np = genome.findUniqueFeature(g, new Predicate<String>() {
            @Override
            public boolean test(String s) {
              return s.startsWith("NP_");
            }
          });
          pp.add(np);
          
        }
        prots2.add(pp);
      }
      List<?> o = FBAModelFactory.setupModelReactionProteins(prots2, genomeo, false);
      for (Object oo : o) {
        System.out.println(oo);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(prots);
    System.out.println(prots2);
//    FBAModelFactory.setupModelReactionProteins(genes, genome, genomeRef)
  }
  
  public static void main(String[] args) {
//    loadZipGenome("/var/biomodels/kbase/PlantSEED_Cre.rast.json.zip");
    testBuildReactionProteinFromGprString("(A)");
    testBuildReactionProteinFromGprString("(A) and (B)");
    testBuildReactionProteinFromGprString("2 and 1");
    testBuildReactionProteinFromGprString("(NP_745666.1 or NP_744890.1) and WUT.1");
    testBuildReactionProteinFromGprString("((PP_0240 and PP_0239 and (PP_3528 or PP_0237)) or (PP_3217 or PP_3229 or PP_3228) or (PP_0232 or PP_0171) or (PP_0233 or PP_0170) or (PP_0172 or PP_0231))");
//    testBuildReactionProteinFromGprString("((PP_0240 and PP_0239 and (PP_3528 or PP_0237)) or (PP_3217 or PP_3229 or PP_3228) or (PP_0232 or PP_0171) or (PP_0233 or PP_0170) or (PP_0172 or PP_0231))",
//        "/var/biomodels/kbase/GCF_000007565.2.rast.json.zip");
  }
}
