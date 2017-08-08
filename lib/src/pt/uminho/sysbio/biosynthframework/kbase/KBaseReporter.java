package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import datafileutil.DataFileUtilClient;
import kbasereport.CreateExtendedReportParams;
import kbasereport.File;
import kbasereport.KBaseReportClient;
import kbasereport.ReportInfo;
import kbasereport.WorkspaceObject;
import us.kbase.common.service.JsonClientException;
import us.kbase.workspace.WorkspaceClient;

public class KBaseReporter {
  
  private final KBaseReportClient client;
  private final String workspaceName;
  
  private List<File> htmlLinks = new ArrayList<> ();
  private List<File> fileLinks = new ArrayList<> ();
  private List<WorkspaceObject> wsObjects = new ArrayList<> ();
  
  public KBaseReporter(final KBaseReportClient client, String workspace) {
    this.client = client;
    this.workspaceName = workspace;
  }
  
  public void addHtmlFile(String desc, String name, String path) {
    File kfile = new File()
        .withDescription(desc)
        .withName(name)
        .withPath(path);
    htmlLinks.add(kfile);
  }
  
  public void addHtmlFolderShock(String desc, String name, String path, 
      final DataFileUtilClient dfuClient) throws IOException {
    String shockId = KBaseIOUtils.folderToShock(path, dfuClient);
    File kfile = new File()
        .withDescription(desc)
        .withName(name)
        .withShockId(shockId);
    htmlLinks.add(kfile);
  }
  
  public void addFile(String desc, String name, String path) {
    File kfile = new File()
        .withDescription(desc)
        .withName(name)
        .withPath(path);
    fileLinks.add(kfile);
  }
  
  public void addWsObject(String desc, String ref) {
    WorkspaceObject wsObject = new WorkspaceObject().withDescription(desc)
                                                    .withRef(ref);
    wsObjects.add(wsObject);
  }
  
  public void addWsObjects(List<WorkspaceObject> os) {
    wsObjects.addAll(os);
  }
  
  public ReportInfo extendedReport() throws IOException, JsonClientException {
    
    CreateExtendedReportParams params = new CreateExtendedReportParams()
//        .withDirectHtml("<div> <iframe src= "????")
//        .withDirectHtmlLinkIndex(directHtmlLinkIndex)
        .withDirectHtmlLinkIndex(0L)
        .withWorkspaceName(workspaceName)
        .withReportObjectName("fliu_test_report_" + UUID.randomUUID().toString());
    if (!wsObjects.isEmpty()) {
      params.withObjectsCreated(wsObjects);
    }
    if (!htmlLinks.isEmpty()) {
      params.withHtmlLinks(htmlLinks);
    }
    if (!fileLinks.isEmpty()) {
      params.withFileLinks(fileLinks);
    }
    
    
    
    ReportInfo reportInfo = client.createExtendedReport(params);
//    
//    reportInfo.getName();
//    reportInfo.getRef();
    return reportInfo;
  }
}
