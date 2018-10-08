package sbmltools.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kbasefba.FBAModel;
import me.fxe.kbase.KBaseAPI;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter.ImportModelResult;
import pt.uminho.sysbio.biosynthframework.report.IntegrationByDatabase;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReport;
import us.kbase.common.service.UnauthorizedException;

public class TestImport {
  public static void main(String[] args) {
    KBaseConfig.setupLocalPaths();
    
    try (InputStream is = new FileInputStream("/var/biomodels/sbml/iAF1260.xml")) {
      KBaseAPI api = new KBaseAPI(TestData.TOKEN, KBaseAPI.getConfigProd(), true);
      KBaseSbmlImporter importer = new KBaseSbmlImporter("", null, api.wsClient);
//      InputStream is = null;
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
            "iAF1260", 
            url, 
            true, 
            true, 
            biomassIds, 
            null, 
            spiIntegrationAll, 
            jsonResult, 
            true);
      
      api.save(model, model.getId(), "filipeliu:narrative_1505344826555");
    } catch (IOException | UnauthorizedException e) {
      e.printStackTrace();
    }
  }
}
