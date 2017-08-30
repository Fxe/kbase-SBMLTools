package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import datafileutil.DataFileUtilClient;
import kbasefba.FBAModel;
import kbasereport.CreateParams;
import kbasereport.KBaseReportClient;
import kbasereport.Report;
import kbasereport.ReportInfo;
import kbasereport.WorkspaceObject;
import sbmltools.CompartmentMapping;
import sbmltools.IntegrateModelParams;
import sbmltools.KBaseType;
import sbmltools.SbmlImporterResults;
import us.kbase.workspace.WorkspaceClient;

public class KBaseModelIntegrationFacade {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseModelIntegrationFacade.class);
  
  private final WorkspaceClient wspClient;
  private final DataFileUtilClient dfuClient;
  private final KBaseReportClient kbrClient;
  private final KBaseBiodbContainer biodbContainer;
  private final KBaseGeneIntegration geneIntegration;
  
  public KBaseModelIntegrationFacade(WorkspaceClient    wspClient,
                                     DataFileUtilClient dfuClient, 
                                     KBaseReportClient  kbrClient,
                                     KBaseGeneIntegration geneIntegration,
                                     String biodbPath) {
    this.wspClient = wspClient;
    this.dfuClient = dfuClient;
    this.kbrClient = kbrClient;
    this.geneIntegration = geneIntegration;
    this.biodbContainer = new KBaseBiodbContainer(biodbPath);
  }
  
  public static Map<String, String> getCompartmentMapping(List<CompartmentMapping> compartmentMappings) {
    Map<String, String> result = new HashMap<> ();

    for (CompartmentMapping cmap : compartmentMappings) {
      if (cmap != null && cmap.getKbaseCompartmentId() != null && 
          cmap.getModelCompartmentId() != null && 
          !cmap.getModelCompartmentId().isEmpty()) {
        String to = cmap.getKbaseCompartmentId();
        String from = cmap.getModelCompartmentId().iterator().next();
        result.put(from, to);
      }
    }

    return result;
  }
  
//  public SbmlImporterResults kbaseIntegrate(IntegrateModelParams params, Long workspaceId) throws Exception {
//    //validate params
//    String fbaModelName = params.getModelName();
//    String outputName = params.getOutputModelName();
//    Long workspaceId = dfuClient.wsNameToId(workspaceName);
//    System.out.println(workspaceId);
//    Map<String, String> compartmentMapping = getCompartmentMapping(params.getCompartmentTranslation());
//    
//    //get model
//    FBAModel fbaModel = KBaseIOUtils.getObject(fbaModelName, workspaceName, null, FBAModel.class, wspClient);
//    //get genome ref
//    KBaseIOUtils.getObject(params.getGenomeId(), workspaceName, null, wspClient);
//    
//    
//    
//    //integrate
//    KBaseIntegration integration = new KBaseIntegration();
//    integration.fbaModel = fbaModel;
//    integration.compartmentMapping = compartmentMapping;
//    integration.rename = "ModelSeed";
//    integration.fillMetadata = true;
//    integration.biodbContainer = this.biodbContainer;
//    
//    integration.integrate();
//    
//    
//    String geneData = "";
//    if (geneIntegration != null) {
//      geneData = geneIntegration.searchGenome(fbaModel);
//    }
//    
//    
//    String ref = KBaseIOUtils.saveDataSafe(outputName, KBaseType.FBAModel, fbaModel, workspaceName, dfuClient);
//    
//    List<WorkspaceObject> wsObjects = new ArrayList<> ();
//    wsObjects.add(new WorkspaceObject().withDescription("model").withRef(ref));
//    final ReportInfo reportInfo = kbrClient.create(
//        new CreateParams().withWorkspaceName(workspaceName)
//                          .withReport(new Report()
//                              .withObjectsCreated(wsObjects)
//                              .withTextMessage(String.format("%s\n%s", params, geneData))));
//    
//    SbmlImporterResults returnVal = new SbmlImporterResults().withFbamodelId(outputName)
//                                                             .withReportName(reportInfo.getName())
//                                                             .withReportRef(reportInfo.getRef());
//    
//    return returnVal;
//  }
  
  public SbmlImporterResults kbaseIntegrate(IntegrateModelParams params, String workspaceName) throws Exception {
    //validate params
    System.out.println(params);
    String fbaModelName = params.getModelName();
    String outputName = params.getOutputModelName();
//    Long workspaceId = dfuClient.wsNameToId(workspaceName);
//    System.out.println(workspaceId);
    Map<String, String> compartmentMapping = getCompartmentMapping(params.getCompartmentTranslation());
    
    //get model
    FBAModel fbaModel = KBaseIOUtils.getObject(fbaModelName, workspaceName, null, FBAModel.class, wspClient);
    //get genome ref
    
    Pair<KBaseId, Object> kdata = KBaseIOUtils.getObject2(params.getGenomeId(), workspaceName, null, wspClient);
    
    Set<String> biomassReactions = new HashSet<> ();
    if (params.getBiomassReactions() != null) {
      for (String s : params.getBiomassReactions().split(",")) {
        if (s.trim() != null) {
          biomassReactions.add(s.trim());
        }
      }
    }
    
    
    //integrate
    KBaseIntegration integration = new KBaseIntegration(fbaModel);
    integration.biomassSet.addAll(biomassReactions);
    integration.genomeRef = kdata.getLeft().reference;
    integration.genome = KBaseUtils.convert(kdata.getRight(), KBaseGenome.class);
    integration.compartmentMapping = compartmentMapping;
    integration.rename = params.getTranslateDatabase();
    integration.fillMetadata = params.getFillMetadata() == 1L;
    integration.mediaName = "importer.media";
    integration.biodbContainer = this.biodbContainer;
    
    integration.integrate();
    
    String geneData = "";
    if (geneIntegration != null) {
      geneData = geneIntegration.searchGenome(fbaModel);
    }
    
    KBaseId mediaKid = null;
    if (integration.defaultMedia != null) {
      mediaKid = KBaseIOUtils.saveData(outputName + ".media", KBaseType.KBaseBiochemMedia.value(), integration.defaultMedia, workspaceName, wspClient);
    }
    
    KBaseId kid = KBaseIOUtils.saveData(outputName, KBaseType.FBAModel.value(), fbaModel, workspaceName, wspClient);
    System.out.println(kid);
//    String ref = KBaseIOUtils.saveDataSafe(outputName, KBaseType.FBAModel, fbaModel, workspaceName, dfuClient);
    
    List<WorkspaceObject> wsObjects = new ArrayList<> ();
    wsObjects.add(new WorkspaceObject().withDescription("model").withRef(kid.reference));
    
    if (mediaKid != null) {
      wsObjects.add(new WorkspaceObject().withDescription("media").withRef(mediaKid.reference));
    }
    
    
    if (kbrClient != null) {
      final ReportInfo reportInfo = kbrClient.create(
          new CreateParams().withWorkspaceName(workspaceName)
                            .withReport(new Report()
                                .withObjectsCreated(wsObjects)
                                .withTextMessage(String.format("%s\n%s", params, geneData))));
      
      SbmlImporterResults returnVal = new SbmlImporterResults().withFbamodelId(outputName)
                                                               .withReportName(reportInfo.getName())
                                                               .withReportRef(reportInfo.getRef());
      
      return returnVal;
    }
    
    return null;
  }
}

