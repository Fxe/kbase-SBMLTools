package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kbasereport.CreateExtendedReportParams;
import kbasereport.KBaseReportClient;
import kbasereport.ReportInfo;
import kbasereport.WorkspaceObject;
import us.kbase.common.service.JsonClientException;

public class KBaseReporter {
  
  private final KBaseReportClient client;
  private final String workspaceName;
  
  public KBaseReporter(final KBaseReportClient client, String workspace) {
    this.client = client;
    this.workspaceName = workspace;
  }
  
  public void extendedReport() throws IOException, JsonClientException {
    List<WorkspaceObject> objectsCreated = new ArrayList<> ();
    CreateExtendedReportParams params = new CreateExtendedReportParams()
        .withObjectsCreated(objectsCreated)
        .withDirectHtml("<p>report!</p>");
//    params.withFileLinks(null);
//    params.withHtmlLinks(null);
    params.withWorkspaceName(workspaceName);
    params.withReportObjectName("fliu_test_report_" + UUID.randomUUID().toString());
    ReportInfo reportInfo = client.createExtendedReport(params);
    
    reportInfo.getName();
    reportInfo.getRef();
  }
}
