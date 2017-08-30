package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.IOException;
import java.net.URL;

import kbsolrutil.KBaseAPI;
import rastsdk.AnnotateGenomeParams;
import rastsdk.AnnotateGenomeResults;
import rastsdk.RASTSDKClient;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.UnauthorizedException;

public class EasyKBase {
  
  private final URL callback;
  private final AuthToken token;
  public RASTSDKClient rastClient = null;
  
  public EasyKBase(URL callback, AuthToken auth) {
    this.callback = callback;
    this.token = auth;
  }
  
  public EasyKBase(KBaseAPI api) {
    this(api.callbackURL, api.authToken);
  }
  
  public void initRastClient() throws IOException, UnauthorizedException {
    rastClient = new RASTSDKClient(callback, token);
  }
  
  public void annotateGenome(String workspace, String genomeIn, String genomeOut) throws IOException {
    try {
      if (rastClient == null) {
        initRastClient();
      }

      AnnotateGenomeParams params = new AnnotateGenomeParams()
          .withInputGenome(genomeIn)               //"input_genome": "GCF_000005845.2",
          .withOutputGenome(genomeOut)             //"output_genome": "GCF_000005845.2.rast",
          .withWorkspace(workspace)                //HIDDEN
          .withCallFeaturesRRNASEED(0L)            //"call_features_rRNA_SEED": 0,
          .withCallFeaturesTRNATrnascan(0L)        //"call_features_tRNA_trnascan": 0,
          .withCallSelenoproteins(0L)              //"call_selenoproteins": 0,
          .withCallPyrrolysoproteins(0L)           //"call_pyrrolysoproteins": 0,
          .withCallFeaturesRepeatRegionSEED(0L)    //"call_features_repeat_region_SEED": 0,
          .withCallFeaturesInsertionSequences(0L)  //"call_features_insertion_sequences": 0,
          .withCallFeaturesStrepSuisRepeat(0L)     //"call_features_strep_suis_repeat": 0,
          .withCallFeaturesStrepPneumoRepeat(0L)   //"call_features_strep_pneumo_repeat": 0,
          .withCallFeaturesCrispr(0L)              //"call_features_crispr": 0,
          .withCallFeaturesCDSGlimmer3(0L)         //"call_features_CDS_glimmer3": 0,
          .withCallFeaturesCDSProdigal(0L)         //"call_features_CDS_prodigal": 0,
          .withAnnotateProteinsKmerV2(1L)          //"annotate_proteins_kmer_v2": 1,
          .withKmerV1Parameters(1L)                //"kmer_v1_parameters": 1,
          .withAnnotateProteinsSimilarity(1L)      //"annotate_proteins_similarity": 1,
          .withRetainOldAnnoForHypotheticals(0L)   //"retain_old_anno_for_hypotheticals": 0,
          .withResolveOverlappingFeatures(0L)      //"resolve_overlapping_features": 0,
          .withFindCloseNeighbors(1L)              //"find_close_neighbors": 1,
          .withCallFeaturesProphagePhispy(0L);     //"call_features_prophage_phispy": 0
      AnnotateGenomeResults r = rastClient.annotateGenome(params);
      System.out.println(r);
    } catch (Exception e) {
      throw new IOException(e);
    }
  }
}
