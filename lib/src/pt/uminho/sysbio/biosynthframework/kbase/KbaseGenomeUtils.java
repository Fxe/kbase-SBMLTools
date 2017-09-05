package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbasefba.FBAModel;
import kbasefba.ModelReaction;
import kbasefba.ModelReactionProtein;
import kbasegenomes.Feature;
import kbasegenomes.Genome;
import pt.uminho.sysbio.biosynthframework.BFunction;

public class KbaseGenomeUtils {
  
  private static final Logger logger = LoggerFactory.getLogger(KbaseGenomeUtils.class);
  
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
