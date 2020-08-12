package sbmltools.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kbasefba.FBAModel;
import kbasereport.WorkspaceObject;
import me.fxe.kbase.KBaseAPI;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseId;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils.KBaseObject;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter.ImportModelResult;
import pt.uminho.sysbio.biosynthframework.report.IntegrationByDatabase;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReport;
import pt.uminho.sysbio.biosynthframework.util.AutoFileReader;
import pt.uminho.sysbio.biosynthframework.util.FileType;
import sbmltools.KBaseType;
import us.kbase.common.service.UnauthorizedException;

public class TestImport {
  public static void main(String[] args) {
    KBaseConfig.setupLocalPaths();
//    String localPath = "/var/biomodels/sbml/Ecoli_K12_MG1655.xml";
    String localPath = "/var/biomodels/joana_set.zip";
    
    FileType fileType = FileType.AUTO;
    fileType = AutoFileReader.getFileType(localPath);
    try (AutoFileReader freader = new AutoFileReader(localPath, fileType)) {
      
      Map<String, InputStream> inputStreams = freader.getStreams().streams;
      if (FileType.XML.equals(fileType)) {
        String prev = inputStreams.keySet().iterator().next();
        inputStreams.put("model.xml", inputStreams.get(prev));
        inputStreams.remove(prev);
      }
      
      KBaseAPI api = new KBaseAPI(TestData.TOKEN, KBaseAPI.getConfigProd(), true);
      KBaseSbmlImporter importer = new KBaseSbmlImporter("", null, null, api.wsClient);
      
      for (String u : inputStreams.keySet()) {
        InputStream is = inputStreams.get(u);
        System.out.println(u);
        try {
          
          ImportModelResult result = new ImportModelResult();
          String url = "iAF1260";
          List<String> biomassIds = new ArrayList<> ();

          IntegrationByDatabase spiIntegrationAll = new IntegrationByDatabase();
          IntegrationReport jsonResult = new IntegrationReport();
          List<MetaboliteMajorLabel> dbs = new ArrayList<> ();
          dbs.add(MetaboliteMajorLabel.BiGG);
          dbs.add(MetaboliteMajorLabel.BiGG2);
          dbs.add(MetaboliteMajorLabel.LigandCompound);
          dbs.add(MetaboliteMajorLabel.LigandGlycan);
          dbs.add(MetaboliteMajorLabel.LigandDrug);
          dbs.add(MetaboliteMajorLabel.Seed);
          dbs.add(MetaboliteMajorLabel.ModelSeed);
          dbs.add(MetaboliteMajorLabel.LipidMAPS);
          dbs.add(MetaboliteMajorLabel.ChEBI);
          dbs.add(MetaboliteMajorLabel.MetaCyc);

          for (MetaboliteMajorLabel db : dbs) {
            spiIntegrationAll.databases.add(db.toString());
          }
          
          FBAModel model = importer.importModel(
              is, 
              result, 
              u, 
              url, 
              true, 
              true, 
              biomassIds, 
              null, 
              spiIntegrationAll, 
              jsonResult,
              true);
          //          KBaseIOUtils.saveData(objects, workspace, dfuClient);
        } catch (Exception e) {
          e.printStackTrace();
//          result.message += "\nERROR: " + u + " " + e.getMessage();
        }
      }
    } catch (IOException | UnauthorizedException e) {
      e.printStackTrace();
    }
    
//    try (InputStream is = new FileInputStream("/var/biomodels/sbml/Ecoli_K12_MG1655.xml")) {
//      KBaseAPI api = new KBaseAPI(TestData.TOKEN, KBaseAPI.getConfigProd(), true);
//      KBaseSbmlImporter importer = new KBaseSbmlImporter("", null, api.wsClient);
////      InputStream is = null;
//      ImportModelResult result = new ImportModelResult();
//      String url = "iAF1260";
//      List<String> biomassIds = new ArrayList<> ();
//      
//      IntegrationByDatabase spiIntegrationAll = new IntegrationByDatabase();
//      IntegrationReport jsonResult = new IntegrationReport();
//      List<MetaboliteMajorLabel> dbs = new ArrayList<> ();
//      dbs.add(MetaboliteMajorLabel.BiGG);
//      dbs.add(MetaboliteMajorLabel.BiGG2);
//      dbs.add(MetaboliteMajorLabel.LigandCompound);
//      dbs.add(MetaboliteMajorLabel.LigandGlycan);
//      dbs.add(MetaboliteMajorLabel.LigandDrug);
//      dbs.add(MetaboliteMajorLabel.Seed);
//      dbs.add(MetaboliteMajorLabel.ModelSeed);
//      dbs.add(MetaboliteMajorLabel.LipidMAPS);
//      dbs.add(MetaboliteMajorLabel.ChEBI);
//      dbs.add(MetaboliteMajorLabel.MetaCyc);
//
//      for (MetaboliteMajorLabel db : dbs) {
//        spiIntegrationAll.databases.add(db.toString());
//      }
//      
//      
//      
//      FBAModel model = importer.importModel(
//            is, 
//            result, 
//            "iAF1260", 
//            url, 
//            true, 
//            true, 
//            biomassIds, 
//            null, 
//            spiIntegrationAll, 
//            jsonResult,
//            true);
//      
//      api.save(model, model.getId(), "filipeliu:narrative_1505344826555");
//    } catch (IOException | UnauthorizedException e) {
//      e.printStackTrace();
//    }
  }
}
