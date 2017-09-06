package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;

import genomeproteomecomparison.GenomeProteomeComparisonClient;
import kbasegenomes.Feature;
import kbasegenomes.Genome;
import pt.uminho.sysbio.biosynthframework.genome.NAlignTool;
import pt.uminho.sysbio.biosynthframework.kbase.genome.AlignmentKernel;
import pt.uminho.sysbio.biosynthframework.kbase.genome.KbaseGenomeUtils;
import sbmltools.AutoPropagateModelParams;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.workspace.WorkspaceClient;

public class AutoPropagateGenomeFacade {
  
  private final NAlignTool alignTool;
//  /kb/module/data/
  public static String BLAST_DB_PATH = "/kb/module/data/blast_db.faa";
  
  private String genomeId;
  private String workspace;
  private final WorkspaceClient wsClient;
  private final GenomeProteomeComparisonClient gpcClient;
  
  public AutoPropagateGenomeFacade(AutoPropagateModelParams params, 
      URL callbackUrl, AuthToken token) throws IOException, UnauthorizedException {
    this.alignTool = new NAlignTool(KbaseGenomeUtils.NUC44);
    this.genomeId = params.getGenomeId();
    this.workspace = params.getWorkspaceName();
    this.wsClient = new WorkspaceClient(callbackUrl, token);
    this.gpcClient = new GenomeProteomeComparisonClient(callbackUrl, token);
    
    this.wsClient.setIsInsecureHttpConnectionAllowed(true);
    this.gpcClient.setIsInsecureHttpConnectionAllowed(true);
  }
  
  public void run() {
    try {
      InputStream is = new FileInputStream(BLAST_DB_PATH);
      Map<String, DNASequence> seqs = FastaReaderHelper.readFastaDNASequence(is);
      Map<String, Genome> seqsGenome = new HashMap<> ();
      for (String k : seqs.keySet()) {
        seqsGenome.put(k, null);
      }
      
      Pair<KBaseId, Object> data = KBaseIOUtils.getObject2(genomeId, workspace, null, wsClient);
      Genome genome = KBaseUtils.convert(data.getRight(), Genome.class);
      
      Feature poly = null;
      if (genome != null && 
          (poly = KbaseGenomeUtils.findRnaPolymeraseBetaUnit(genome)) != null) {
        AlignmentKernel ma = new AlignmentKernel(alignTool, 2);
        String dnaA = poly.getDnaSequence();
        
        for (String k : seqs.keySet()) {
          ma.aaa.add(new ImmutablePair<String, String>(dnaA, seqs.get(k).getSequenceAsString()));
        }
        
        ma.run();
        
        //collect the best genomes
        
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
