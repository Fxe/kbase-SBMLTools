package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fbatools.FbaToolsClient;
import fbatools.PropagateModelToNewGenomeParams;
import fbatools.PropagateModelToNewGenomeResults;
import genomeproteomecomparison.BlastProteomesParams;
import genomeproteomecomparison.GenomeProteomeComparisonClient;
import kbsolrutil.KBaseAPI;
import rastsdk.AnnotateGenomeParams;
import rastsdk.AnnotateGenomeResults;
import rastsdk.RASTSDKClient;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UnauthorizedException;

public class EasyKBase {
  
  private static final Logger logger = LoggerFactory.getLogger(EasyKBase.class);
  
  private final URL callback;
  private final AuthToken token;
  public RASTSDKClient rastClient = null;
  private GenomeProteomeComparisonClient gpcClient = null;
  private FbaToolsClient fbaClient = null;
  
  public EasyKBase(URL callback, AuthToken auth) {
    this.callback = callback;
    this.token = auth;
  }
  
  public EasyKBase(KBaseAPI api) {
    this(api.callbackURL, api.authToken);
  }
  
  public FbaToolsClient getFbaClient() throws IOException, UnauthorizedException {
    if (this.fbaClient == null) {
      logger.info("[INIT] building FbaToolsClient ...");
      this.fbaClient = new FbaToolsClient(callback, token);
      this.fbaClient.setIsInsecureHttpConnectionAllowed(true);
    }
    return this.fbaClient;
  }

  public RASTSDKClient getRastClient() throws IOException, UnauthorizedException {
    if (this.rastClient == null) {
      this.rastClient = new RASTSDKClient(callback, token);
      this.rastClient.setIsInsecureHttpConnectionAllowed(true);
    }
    return this.rastClient;
  }
  
  public GenomeProteomeComparisonClient getGpcClient() throws IOException, UnauthorizedException {
    if (this.gpcClient == null) {
      this.gpcClient = new GenomeProteomeComparisonClient(callback, token);
      this.gpcClient.setIsInsecureHttpConnectionAllowed(true);
    }
    return this.gpcClient;
  }

  public void initRastClient() throws IOException, UnauthorizedException {
    getRastClient();
  }
  
  public String compareProteomes(KBaseId kid1, KBaseId kid2, KBaseId kidOut) throws IOException {
    try {
      GenomeProteomeComparisonClient client = getGpcClient();
      BlastProteomesParams bparams = new BlastProteomesParams()
          .withGenome1id(kid1.name).withGenome1ws(kid1.workspace)
          .withGenome2id(kid2.name).withGenome2ws(kid2.workspace)
          .withMaxEvalue("1e-9")
          .withSubBbhPercent(90.0)
          .withOutputId(kidOut.name)
          .withOutputWs(kidOut.workspace);
      String res = client.blastProteomes(bparams);
      return res;
    } catch (Exception e) {
      throw new IOException(e);
    }
  }
  
  public String propagateModelToNewGenome(String modelName, String workspace, String pcompName, String pcompWs, String outModel, String outWs) throws IOException {
    logger.info("model: {} -> {}", modelName, outModel);
    try {
//      "translation_policy": "translate_only" ?????????????????????????
      FbaToolsClient client = getFbaClient();
      PropagateModelToNewGenomeParams params = new PropagateModelToNewGenomeParams()
          .withFbamodelId(modelName)               //"fbamodel_id": "Ec_core_flux1",
          .withProteincomparisonId(pcompName)      //"proteincomparison_id": "ecoli.comp",
          .withMediaId("None")                     //"media_id": None,
          .withFbamodelOutputId(outModel)          //"fbamodel_output_id": "EcBw_core_flux1",
          .withKeepNogeneRxn(0L)                   //"keep_nogene_rxn": 0,
          .withGapfillModel(0L)                    //"gapfill_model": 0,
          .withCustomBoundList(new ArrayList<String> ())     //"custom_bound_list": [],
          .withMediaSupplementList(new ArrayList<String> ()) //"media_supplement_list": [],
          .withMinimumTargetFlux(0.1d)                       //"minimum_target_flux": 0.1,
          .withFbamodelWorkspace(workspace)
          .withWorkspace(outWs)
          .withProteincomparisonWorkspace(pcompWs);
      PropagateModelToNewGenomeResults results = client.propagateModelToNewGenome(params);
      
      logger.info("model created {}, fba {}", results.getNewFbamodelRef(), results.getNewFbaRef());
      
      return results.getNewFbamodelRef();
    } catch (Exception e) {
      throw new IOException(e);
    }
  }
  
  public void annotateGenome(String workspace, String genomeIn, String genomeOut) throws IOException {
    try {
      if (rastClient == null) {
        initRastClient();
      }
      AnnotateGenomeResults r = EasyKBase.annotateGenome(workspace, genomeIn, genomeOut, rastClient);
      System.out.println(r);
    } catch (Exception e) {
      throw new IOException(e);
    }
  }
  
  public static AnnotateGenomeResults annotateGenome(String workspace, 
      String genomeIn, String genomeOut, RASTSDKClient rastClient) throws IOException {

    try {
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
      return rastClient.annotateGenome(params);
    } catch (JsonClientException e) {
      throw new IOException(e);
    }
  }
}
