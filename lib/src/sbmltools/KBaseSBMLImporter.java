package sbmltools;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

import datafileutil.DataFileUtilClient;
import kbasereport.CreateParams;
import kbasereport.KBaseReportClient;
import kbasereport.Report;
import kbasereport.ReportInfo;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.WorkspaceClient;

public class KBaseSBMLImporter {

  private AuthToken token;
  //  private File tempDir;

  public KBaseSBMLImporter(AuthToken token, Map<String, String> config) throws Exception {
    this.token = token;
    //      this.tempDir = new File(config.get("scratch"));
  }

  public ReadSBMLResults run(ReadSBMLParams params) throws Exception {
    URL callbackUrl = new URL(System.getenv("SDK_CALLBACK_URL"));
    System.out.println("printo !");
    System.err.println("printe !");
    System.out.println(params.getWorkspaceName());
    System.out.println(params.getTestUrl());
    System.out.println(params.getRandomInt());
//    logger.info("is this working !?");
    
//    final DataFileUtilClient dfuClient = new DataFileUtilClient(callbackURL, authPart);
//    dfuClient.setIsInsecureHttpConnectionAllowed(true);
//    Long wsId = dfuClient.wsNameToId(ws);
//    return Utils.getRefFromObjectInfo(dfuClient.saveObjects(
//            new datafileutil.SaveObjectsParams().withId(wsId)
//            .withObjects(Arrays.asList(new datafileutil.ObjectSaveData()
//            .withName(id).withType("GenomeComparison.ProteomeComparison")
//            .withData(new UObject(res))))).get(0));
//    scratch.re
    

    
    final KBaseReportClient reportClient = new KBaseReportClient(callbackUrl, token);
    reportClient.setIsInsecureHttpConnectionAllowed(true);
    final CreateParams reportParams = new CreateParams()
        .withWorkspaceName(params.getWorkspaceName())
        .withReport(new Report().withTextMessage(params.getWorkspaceName()));
    final ReportInfo report = reportClient.create(reportParams);
    
    final WorkspaceClient client = new WorkspaceClient(callbackUrl, token);
//  client.
  client.saveObjects(new SaveObjectsParams()
      .withWorkspace(params.getWorkspaceName())
      .withObjects(Arrays.asList(new ObjectSaveData().withData(
          new UObject(report)).withName("wut"))));
    //BEGIN read_sbml_model
    //END read_sbml_model
    ReadSBMLResults returnVal = new ReadSBMLResults();
    returnVal.setJustAInt(params.getRandomInt() + 10);
    returnVal.setReportName(report.getName());
    returnVal.setReportRef(report.getRef());
    
    return returnVal;
  }
  
  public static String getRefFromObjectInfo(Tuple11<Long, String, String, String, 
      Long, String, Long, String, String, Long, Map<String,String>> info) {
    return info.getE7() + "/" + info.getE1() + "/" + info.getE5();
  }
  
//  private String saveResult(String ws, String id, ProteomeComparison res) throws Exception {
//    URL callbackUrl = new URL(System.getenv("SDK_CALLBACK_URL"));
//    DataFileUtilClient dfu = new DataFileUtilClient(callbackUrl, token);
//    dfu.setIsInsecureHttpConnectionAllowed(true);
//    Long wsId = dfu.wsNameToId(ws);
//    return getRefFromObjectInfo(dfu.saveObjects(
//        new datafileutil.SaveObjectsParams().withId(wsId)
//        .withObjects(Arrays.asList(new datafileutil.ObjectSaveData()
//            .withName(id).withType("GenomeComparison.ProteomeComparison")
//            .withData(new UObject(res))))).get(0));
//  }
}
