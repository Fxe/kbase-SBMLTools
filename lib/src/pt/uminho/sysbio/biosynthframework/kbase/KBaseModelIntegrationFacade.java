package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import datafileutil.DataFileUtilClient;
import genomeannotationapi.GenomeAnnotationAPIClient;
import genomeannotationapi.SaveGenomeResultV1;
import genomeannotationapi.SaveOneGenomeParamsV1;
import kbasefba.FBAModel;
import kbasefba.ModelReaction;
import kbasegenomes.Genome;
import kbasereport.CreateParams;
import kbasereport.KBaseReportClient;
import kbasereport.Report;
import kbasereport.ReportInfo;
import kbasereport.WorkspaceObject;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseHtmlReport.ReportFiles;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;
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
  private final GenomeAnnotationAPIClient gaClient;
  private final KBaseBiodbContainer biodbContainer;
  private final KBaseGeneIntegration geneIntegration;
  private final Path scratch;
  
  public KBaseModelIntegrationFacade(WorkspaceClient    wspClient,
                                     DataFileUtilClient dfuClient, 
                                     GenomeAnnotationAPIClient gaClient,
                                     KBaseReportClient  kbrClient,
                                     KBaseGeneIntegration geneIntegration,
                                     String biodbPath,
                                     Path scratch) {
    
    this.wspClient = wspClient;
    this.dfuClient = dfuClient;
    this.kbrClient = kbrClient;
    this.gaClient = gaClient;
    this.geneIntegration = geneIntegration;
    this.biodbContainer = new KBaseBiodbContainer(biodbPath);
    this.scratch = scratch;
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
  
  
  public Map<String, String> geneOverride(String geneMapping) {
    Map<String, String> geneOverride = new HashMap<> ();
    if (!DataUtils.empty(geneMapping)) {
      for (String l : geneMapping.split("\\n")) {
        if (!DataUtils.empty(l) && l.contains(";")) {
          String[] p = l.split(";");
          if (p.length >= 2) {
            String reaction = p[0].trim();
            String gprString = p[1].trim();
            geneOverride.put(reaction, gprString);
          }
        }
      }
    }
    return geneOverride;
  }
  
  public SbmlImporterResults kbaseIntegrate(IntegrateModelParams params, String workspaceName) throws Exception {
    //validate params
//    System.out.println(params);
    Map<String, String> gprOverride = new HashMap<> ();
    try {
      gprOverride = geneOverride(params.getGeneMappings());
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    
    
    Map<String, String> outputObjects = new HashMap<> ();
    String fbaModelName = params.getModelName();
    String outputName = params.getOutputModelName();
//    Long workspaceId = dfuClient.wsNameToId(workspaceName);
//    System.out.println(workspaceId);
    Map<String, String> compartmentMapping = getCompartmentMapping(params.getCompartmentTranslation());
    
    //get model
    FBAModel fbaModel = KBaseIOUtils.getObject(fbaModelName, workspaceName, null, FBAModel.class, wspClient);
    //get genome ref
    KBaseIntegrationReport kir = new KBaseIntegrationReport();
    kir.model = fbaModel.getId();
    kir.objName = fbaModel.getName();
    
    
    Genome genome = null;
    
    Set<String> biomassReactions = new HashSet<> ();
    if (params.getBiomassReactions() != null) {
      for (String s : params.getBiomassReactions().split(",")) {
        if (s.trim() != null) {
          biomassReactions.add(s.trim());
        }
      }
    }
    
    boolean allowNumberId = true;
    
    //integrate
    KBaseIntegration integration = new KBaseIntegration(fbaModel);
    integration.report = kir;
    integration.biomassSet.addAll(biomassReactions);
    integration.compartmentMapping = compartmentMapping;
    integration.rename = params.getTranslateDatabase();
    integration.fillMetadata = params.getFillMetadata() == 1L;
    integration.mediaName = params.getOutputMediaName();
    integration.biodbContainer = this.biodbContainer;
    integration.gprOverride = gprOverride;
    integration.allowNumberId = allowNumberId;
    integration.fixIdToKBase = true;
    
    if (!DataUtils.empty(params.getGenomeId())) {
      Pair<KBaseId, Object> kdata = KBaseIOUtils.getObject2(params.getGenomeId(), workspaceName, null, wspClient);
      integration.genomeRef = kdata.getLeft().reference;
      genome = KBaseUtils.convert(kdata.getRight(), Genome.class);
      integration.genome = genome;
    }
    
    
    
    integration.integrate();
    
    kir.genomeReport.status = "auto_genome_get_fail";
    if (genome == null) {
      logger.info("auto detect genome...");
      String geneData = "";
      if (geneIntegration != null) {
        geneData = geneIntegration.searchGenome(fbaModel, false);
        System.out.println(geneData);
        kir.fillGenomeData(geneIntegration.report);
        if (geneIntegration.report != null && 
            geneIntegration.report.bestGenomeKID != null && 
            geneIntegration.report.bestGenomeKID.size() >= 1) {
          KBaseId matchGenome = geneIntegration.report.bestGenomeKID.iterator().next();
          integration.genomeRef = matchGenome.reference;
          try {
            Pair<KBaseId, Object> kdata = KBaseIOUtils.getObject2(matchGenome.name, matchGenome.workspace, null, wspClient);
            genome = KBaseUtils.convert(kdata.getRight(), Genome.class);
            SaveOneGenomeParamsV1 gparams = new SaveOneGenomeParamsV1()
                .withData(genome)
                .withWorkspace(workspaceName)
                .withName(matchGenome.name);
            SaveGenomeResultV1 gresults = gaClient.saveOneGenomeV1(gparams);
            String ref = KBaseIOUtils.getRefFromObjectInfo(gresults.getInfo());
            outputObjects.put(ref, "detected genome");
            integration.genome = genome;
            integration.genomeRef = ref;
            kir.genomeReport.status = "auto";
          } catch (IOException e) {
            kir.genomeReport.status = "auto_genome_get_fail";
          }
          logger.info("[GPR Gene Integration]");
          integration.integrateGprGenes(allowNumberId);
        }
      }
    } else {
      kir.fillGenomeData(integration.greport);
      kir.genomeReport.status = "user";
    }
    
    try {
      if (params.getTemplateId() != null) {
        integration.adapter.setTemplate(params.getTemplateId(), wspClient);
      }
    } catch (Exception e) {
      logger.error("Set Template: [{}] - {}", params.getTemplateId(), e.getMessage());
    }
    

    

    
    
    
    KBaseId mediaKid = null;
    if (!DataUtils.empty(params.getOutputMediaName())&&
        integration.defaultMedia != null) {
      mediaKid = KBaseIOUtils.saveData(params.getOutputMediaName(), KBaseType.KBaseBiochemMedia.value(), integration.defaultMedia, workspaceName, wspClient);
      outputObjects.put(mediaKid.reference, "detected media");
    }
    
    KBaseId integratedModelKid = KBaseIOUtils.saveData(outputName, KBaseType.FBAModel.value(), fbaModel, workspaceName, wspClient);
    outputObjects.put(integratedModelKid.reference, "integrated model");
//    String ref = KBaseIOUtils.saveDataSafe(outputName, KBaseType.FBAModel, fbaModel, workspaceName, dfuClient);
    
//    String uuString = UUID.randomUUID().toString();
    
    kir.fillModelData(integration.adapter);
    KBaseIOUtils.writeStringFile(KBaseIOUtils.toJson(kir), "/kb/module/data/data.json");
    
    List<WorkspaceObject> wsObjects = new ArrayList<> ();
    for (String ref : outputObjects.keySet()) {
      String def = outputObjects.get(ref);
      wsObjects.add(new WorkspaceObject().withDescription(def)
                                         .withRef(ref));
    }
    
//    wsObjects.add(new WorkspaceObject().withDescription("model").withRef(kid.reference));
//    if (mediaKid != null) {
//      wsObjects.add(new WorkspaceObject().withDescription("media").withRef(mediaKid.reference));
//    }
    
    KBaseHtmlReport htmlReport = new KBaseHtmlReport(scratch);
    
    Map<String, String> files = new HashMap<>();
    files.put("index.html", KBaseIOUtils.getResource("report/integration/index.html"));
    ReportFiles reportFiles = htmlReport.makeStaticReport(files);
    
    File f = new File("/kb/module/data/data.json");
    if (f.exists()) {
      logger.info("copy {} -> {}", f.getAbsolutePath(), reportFiles.baseFolder);
      KBaseIOUtils.copy(f.getAbsolutePath(), reportFiles.baseFolder + "/");
    }
    
    if (kbrClient != null) {
      KBaseReporter reporter = new KBaseReporter(kbrClient, workspaceName);
      reporter.addWsObjects(wsObjects);
      reporter.addHtmlFolderShock("Integration report", "index.html", reportFiles.baseFolder, dfuClient);
      final ReportInfo reportInfo = reporter.extendedReport();
      
//      final ReportInfo reportInfo = kbrClient.create(
//          new CreateParams().withWorkspaceName(workspaceName)
//                            .withReport(new Report()
//                                .withObjectsCreated(wsObjects)
//                                .withTextMessage(String.format("%s\n%s", params, geneData))));
      
      SbmlImporterResults returnVal = new SbmlImporterResults().withFbamodelId(outputName)
                                                               .withReportName(reportInfo.getName())
                                                               .withReportRef(reportInfo.getRef());
      
      return returnVal;
    }
    
    return null;
  }
}

