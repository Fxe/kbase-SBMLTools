package pt.uminho.sysbio.biosynthframework.kbase.genome;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.biojava.nbio.alignment.template.PairwiseSequenceAligner;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynthframework.genome.Aligner;

public class AlignmentKernel {
  
  private static final Logger logger = LoggerFactory.getLogger(AlignmentKernel.class);
  
  public List<Pair<String, String>> aaa = new ArrayList<> ();
  Iterator<Pair<String, String>> jobIt;
  
  private final int t;
  private final Aligner aligner;
  
  public synchronized Pair<String, String> getJob() {
    if (jobIt.hasNext()) {
      return jobIt.next();
    }
    return null;
  }
  
  public List<Runnable> runners = new ArrayList<> ();
  
  public static class AlignWorker implements Runnable {
    
    private static final Logger logger = LoggerFactory.getLogger(AlignWorker.class);
    
    private final int workerId;
    private final AlignmentKernel ma;
    
    public AlignWorker(AlignmentKernel ma, int workerId) {
      logger.info("");
      this.workerId = workerId;
      this.ma = ma;
      logger.trace("created workder [{}]", this.workerId);
    }

    public int getWorkerId() { return workerId;}

    @Override
    public void run() {
      Pair<String, String> p = null;
      while ((p = ma.getJob()) != null) {
        logger.trace("[{}] worker running job ..", this.workerId);
        String seq1 = p.getLeft();
        String seq2 = p.getRight();
        Object res = ma.aligner.localAlignment(seq1, seq2);
        @SuppressWarnings("unchecked")
        PairwiseSequenceAligner<DNASequence, NucleotideCompound> psa = PairwiseSequenceAligner.class.cast(res);
        
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
    long start = System.currentTimeMillis();
    
    logger.info("running ... jobs: {}", aaa.size());
    jobIt = aaa.iterator();
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
  }
}
