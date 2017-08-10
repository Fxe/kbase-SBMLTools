package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import datafileutil.DataFileUtilClient;
import kbasereport.CreateParams;
import kbasereport.KBaseReportClient;
import kbasereport.Report;
import kbasereport.ReportInfo;
import kbasereport.WorkspaceObject;
import sbmltools.CompartmentMapping;
import sbmltools.IntegrateModelParams;
import sbmltools.SbmlImporterResults;
import us.kbase.workspace.WorkspaceClient;

public class KBaseModelIntegrationFacade {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseModelIntegrationFacade.class);
  
  private final WorkspaceClient wspClient;
  private final DataFileUtilClient dfuClient;
  private final KBaseReportClient kbrClient;
  
  public KBaseModelIntegrationFacade(WorkspaceClient    wspClient,
                                     DataFileUtilClient dfuClient, 
                                     KBaseReportClient  kbrClient) {
    this.wspClient = wspClient;
    this.dfuClient = dfuClient;
    this.kbrClient = kbrClient;
  }
  
  public SbmlImporterResults kbaseIntegrate(IntegrateModelParams params, String workspaceName) throws Exception {

    List<WorkspaceObject> wsObjects = new ArrayList<> ();
    
    final ReportInfo reportInfo = kbrClient.create(
        new CreateParams().withWorkspaceName(workspaceName)
                          .withReport(new Report()
                              .withObjectsCreated(wsObjects)
                              .withTextMessage(String.format("%s", params))));
    
    List<CompartmentMapping> compartmentMapping_ = params.getCompartmentTranslation();
    
    String fbaModelName = params.getModelName();
    Map<String, String> compartmentMapping = null;
    
    KBaseIOUtils.getFBAModel(fbaModelName, workspaceName, null, wspClient);
    KBaseIOUtils.getFBAModel(params.getGenomeId(), workspaceName, null, wspClient);
    
    SbmlImporterResults returnVal = new SbmlImporterResults().withFbamodelId(fbaModelName)
                                         .withReportName(reportInfo.getName())
                                         .withReportRef(reportInfo.getRef());
    
    return returnVal;
  }
}

