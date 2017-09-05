package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;

import kbasefba.ModelCompound;
import kbasefba.ModelReaction;
import pt.uminho.sysbio.biosynthframework.EntityType;

public class KBaseIntegrationReport {

  public class ModelDataReport {
    public Map<String, String> specieData = new HashMap<> ();
    public Map<String, String> reactionData = new HashMap<> ();
  }
  
  public class CompartmentReport {
    public Map<String, String> rename = new HashMap<> ();
    public Map<String, String> cmpName = new HashMap<> ();
  }

  public class GenomeReport {
    public Map<String, String> scName = new HashMap<> ();
    public Map<Double, TreeSet<String>> hits = new TreeMap<>();
    public Set<String> miss = new HashSet<> ();
    public Double best;

    @Override
    public String toString() {
      List<Object> lines = new ArrayList<> ();
      lines.add(String.format("BEST HIT: %f, %s", best, hits.get(best)));
      for (double score : hits.keySet()) {
        for (String g : hits.get(score)) {
          lines.add(String.format("%f, %s, %s", score, g, scName.get(g)));
        }
      }
      return StringUtils.join(lines, '\n');
    }
  }

  public class DrainReport {
    //    public Map<Double, TreeSet<String>> hits = new TreeMap<>();
    public Map<String, Map<String, Double>> deletedReactions = new HashMap<> ();
    public Map<String, double[]> media = new HashMap<> ();
  }

  public class TranslationReport {
    public Map<String, String> translationMap = new TreeMap<>();
  }

  public String model;
  public String objName;
  public TranslationReport spiTranslationReport = new TranslationReport();
  public DrainReport drainReport = new DrainReport();
  public GenomeReport genomeReport = new GenomeReport();
  public ModelDataReport modelDataReport = new ModelDataReport();
  public CompartmentReport compartmentReport = new CompartmentReport();

  public Map<EntityType, Integer> spi = new HashMap<> ();
  public Map<String, Integer> cmp = new HashMap<> ();
  public Map<EntityType, Integer> rxn = new HashMap<> ();


  public void fillModelData(FBAModelAdapter ma) {
    for (ModelCompound mc : ma.fbaModel.getModelcompounds()) {
      this.modelDataReport.specieData.put(mc.getId(), mc.getName());
    }
    for (ModelReaction mr : ma.fbaModel.getModelreactions()) {
      this.modelDataReport.reactionData.put(mr.getId(), mr.getName());
    }

    for (EntityType et : ma.krxnType.bkeySet()) {
      Set<String> krxnEntrySet = ma.krxnType.bget(et);
      if (krxnEntrySet != null) {
        rxn.put(et, krxnEntrySet.size());
      }
    }
    
    for (EntityType et : ma.kspiType.bkeySet()) {
      Set<String> kspiEntrySet = ma.kspiType.bget(et);
      if (kspiEntrySet != null) {
        spi.put(et, kspiEntrySet.size());
      }
    }
    
    for (String cmpEntry : ma.kspiCmp.bkeySet()) {
      Set<String> kspiEntrySet = ma.kspiCmp.bget(cmpEntry);
      if (kspiEntrySet != null) {
        cmp.put(cmpEntry, kspiEntrySet.size());
      }
    }


    //   for (EntityType et : ma..bkeySet()) {
    //     Set<String> krxnEntrySet = ma.krxnType.bget(et);
    //     if (krxnEntrySet != null) {
    //       rxn.put(et, krxnEntrySet.size());
    //     }
    //   }
  }

  public void fillGenomeData(KBaseGeneIntegration geneIntegration) {
    this.genomeReport.scName.putAll(geneIntegration.report.matchOrganism);
    this.genomeReport.best = geneIntegration.report.bestScore;
    for (String k : geneIntegration.report.matchScores.keySet()) {
      double score = geneIntegration.report.matchScores.get(k);
      if (!genomeReport.hits.containsKey(score)) {
        this.genomeReport.hits.put(score, new TreeSet<String>());
      }
      this.genomeReport.hits.get(score).add(k);
    }
    this.genomeReport.miss.addAll(geneIntegration.report.getUnmappedGenes());
  }

  @Override
  public String toString() {
    List<Object> lines = new ArrayList<> ();
    //    lines.add(Joiner.on('\n').withKeyValueSeparator("\t").join(spi));
    lines.add(Joiner.on('\n').withKeyValueSeparator("\t").join(rxn));

    lines.add(Joiner.on('\n').withKeyValueSeparator("\t").join(this.drainReport.deletedReactions));

    lines.add(Joiner.on('\n').withKeyValueSeparator("\t").join(this.drainReport.media));

    lines.add(this.genomeReport);

    return StringUtils.join(lines, '\n');
  }


}
