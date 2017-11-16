package pt.uminho.sysbio.biosynthframework.kbase.genome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.biojava.nbio.alignment.template.PairwiseSequenceAligner;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynthframework.BFunction;
import pt.uminho.sysbio.biosynthframework.genome.Aligner;

public class AlignmentKernel {
  
  private static final Logger logger = LoggerFactory.getLogger(AlignmentKernel.class);
  
  public List<AlignmentJob> jobs = new ArrayList<> ();
  
  public BFunction<PairwiseSequenceAligner<?, ?>, Double> scoringMethod = null;
  
  private boolean running = false;
  private Iterator<AlignmentJob> jobIt;
  private Map<AlignmentJob, Double> results = new HashMap<> ();
  public boolean global = false;
  public boolean protein = false;
  
  private final int t;
  private final Aligner aligner;
  
  public static class AlignmentJob {
    public String genome1;
    public String genome2;
    public String dna1;
    public String dna2;
    public String targetOrganism;
    
    @Override
    public String toString() {
      return String.format("%s / %s (%s)", genome1, genome2, targetOrganism);
    }
  }
  
  public synchronized AlignmentJob getJob() {
    if (jobIt.hasNext()) {
      return jobIt.next();
    }
    return null;
  }
  
  public Map<AlignmentJob, Double> getResults() {
    if (!running) {
      return this.results;
    }
    return null;
  }
  
  public Map<Double, Set<AlignmentJob>> getSortedResults() {
    if (!running) {
      Map<AlignmentJob, Double> results = this.getResults();
      Map<Double, Set<AlignmentJob>> sortedResults = 
          new TreeMap<> (Collections.reverseOrder());
      for (AlignmentJob job : results.keySet()) {
        Double score = results.get(job);
        if (!sortedResults.containsKey(score)) {
          sortedResults.put(score, new HashSet<AlignmentJob>());
        }
        sortedResults.get(score).add(job);
      }
      return sortedResults;
    }
    return null;
  }
  
  public synchronized void saveResult(AlignmentJob job, Double result) {
    this.results.put(job, result);
  }
  
  public List<Runnable> runners = new ArrayList<> ();
  
  public static class AlignWorker implements Runnable {
    
    private static final Logger logger = LoggerFactory.getLogger(AlignWorker.class);
    
    private final int workerId;
    private final AlignmentKernel ma;
    
    public AlignWorker(AlignmentKernel ma, int workerId) {
      logger.info("[{}] worker created.", workerId);
      this.workerId = workerId;
      this.ma = ma;
    }

    public int getWorkerId() { return workerId;}

    @Override
    public void run() {
      AlignmentJob p = null;
      while ((p = ma.getJob()) != null) {
        logger.trace("[{}] worker running job ..", this.workerId);
        String seq1 = p.dna1;
        String seq2 = p.dna2;
        Object res = null;
        if (ma.global) {
          res = ma.aligner.globalAlignment(seq1, seq2);
        } else {
          res = ma.aligner.localAlignment(seq1, seq2);
        }
        
        PairwiseSequenceAligner<?, ?> psa = PairwiseSequenceAligner.class.cast(res);
        
//        if (ma.protein) {
//          
//        } else {
//          psa = 
////          psa = psa_;
//        }

//        @SuppressWarnings("unchecked")
//        PairwiseSequenceAligner<ProteinSequence, AminoAcidCompound> psa = PairwiseSequenceAligner.class.cast(res);
        
        List<Object> data = new ArrayList<> ();
        int length = psa.getPair().getLength();
//        data.add(k);
//        data.add(rgenome.getScientificName());
        data.add(psa.getPair().getNumIdenticals());
        data.add(psa.getPair().getNumSimilars());
        data.add(psa.getPair().getLength());
        data.add((double) psa.getPair().getNumIdenticals() / length);
        data.add((double) psa.getPair().getNumSimilars() / length);
        data.add(psa.getSimilarity());
        data.add(psa.getDistance());
        logger.trace("[{}] worker done job ..", this.workerId);
        if (ma.scoringMethod == null) {
          ma.saveResult(p, (double) psa.getPair().getNumIdenticals() / length);
        } else {
          double score = ma.scoringMethod.apply(psa);
          ma.saveResult(p, score);
        }
        
      }
      logger.info("[{}] done!", this.workerId);
    }
  }
  
  public AlignmentKernel(final Aligner aligner) {
    this(aligner, 4);
  }
  
  public AlignmentKernel(final Aligner aligner, int threads) {
    this.t = threads;
    this.aligner = aligner;
    int id = 0;
    for (int i = 0; i < t; i++) {
      runners.add(new AlignWorker(this, id++));
    }
  }
  
  public void run() {
    this.running = true;
    this.results.clear();
    this.jobIt = jobs.iterator();
    
    logger.info("running ... jobs: {}", jobs.size());
    long start = System.currentTimeMillis();
    
    List<Thread> threads = new ArrayList<> ();
    for (Runnable r : runners) {
      Thread t = new Thread(r);
      t.start();
      threads.add(t);
    }
    for (Thread t : threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
    long end = System.currentTimeMillis();
    logger.info("Time: {}", (end - start) / 1000);
    this.running = false;
  }
}
