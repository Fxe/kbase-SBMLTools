package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.IOException;
import java.util.ArrayList;

import fbatools.FbaToolsClient;
import fbatools.PropagateModelToNewGenomeParams;
import kbasefba.FBAModel;
import kbasegenomes.Feature;
import kbasegenomes.Genome;
import pt.uminho.sysbio.biosynthframework.genome.AlignTool;
import pt.uminho.sysbio.biosynthframework.genome.NAlignTool;
import pt.uminho.sysbio.biosynthframework.kbase.genome.KbaseGenomeUtils;
import rastsdk.RASTSDKClient;
import us.kbase.common.service.JsonClientException;

public class KBasePropagateModel {
  
  private Genome genome;
  private RASTSDKClient rastClient;
  private FbaToolsClient fbaClient;
  private FBAModel kmodel;
  
  public KBasePropagateModel() {
    // TODO Auto-generated constructor stub
    //
  }
  
  public void run() {
    
    //get genome
    Feature sigSeq = KbaseGenomeUtils.findRnaPolymeraseBetaUnit(genome);
  //rast genome
    if (sigSeq == null) {
//      rastClient.annotateGenome(params, jsonRpcContext)
      sigSeq = KbaseGenomeUtils.findRnaPolymeraseBetaUnit(genome);
//      EasyKBase.annotateGenome(workspace, genomeIn, genomeOut, rastClient);
    }
    
    //
    if (sigSeq != null) {
      String dnaA = sigSeq.getDnaSequence();
      //
      //super alignment
      
//        "fbamodel_id": "iCM925.rast.nofill",
//        "proteincomparison_id": "iCac802_iCM925",
//        "media_id": None,
//        "fbamodel_output_id": "iCac802.iCM925.nofill",
//        "keep_nogene_rxn": 1,
//        "gapfill_model": 0,
//        "custom_bound_list": [],
//        "media_supplement_list": [],
//        "minimum_target_flux": 0.1,
//        "translation_policy": "add_reactions_for_unique_genes"
    
      PropagateModelToNewGenomeParams params = new PropagateModelToNewGenomeParams()
          .withFbamodelId(kmodel.getId())
          .withFbamodelOutputId("")
          .withProteincomparisonWorkspace("")
          .withProteincomparisonId("")
          .withMediaId("")
          .withKeepNogeneRxn(1L)
          .withGapfillModel(0L)
          .withCustomBoundList(new ArrayList<String> ())
          .withMinimumTargetFlux(0.1)
          .withWorkspace("");
          
      try {
        fbaClient.propagateModelToNewGenome(params);
      } catch (IOException | JsonClientException e) {
        e.printStackTrace();
      }
    }
  }
}
