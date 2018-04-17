package sbmltools.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import datafileutil.DataFileUtilClient;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.ReactionMajorLabel;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseBiodbContainer;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseHtmlReport;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelSeedIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseHtmlReport.ReportFiles;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelSeedIntegration.KBaseMappingResult;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter.ImportModelResult;
import pt.uminho.sysbio.biosynthframework.report.IntegrationByDatabase;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReport;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlReaction;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlSpecie;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.workspace.WorkspaceClient;

public class TestIntegration {
  
  public static XmlSbmlModel testReadFixModel(String path) {
    XmlSbmlModel xmodel = null;
    
    try {
      XmlStreamSbmlReader reader = new XmlStreamSbmlReader(path);
      xmodel = reader.parse();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return xmodel;
  }
  
  public static void testGenerateDatabaseReferences(XmlSbmlModel xmodel, String modelEntry) {
    KBaseBiodbContainer biodbContainer = new KBaseBiodbContainer(KBaseConfig.DATA_EXPORT_PATH);
    KBaseModelSeedIntegration integration = new KBaseModelSeedIntegration(
        KBaseConfig.DATA_EXPORT_PATH, KBaseConfig.CURATION_DATA, biodbContainer);
    
    KBaseMappingResult result = integration.generateDatabaseReferences(xmodel, modelEntry, null);
    
    Map<String, Map<MetaboliteMajorLabel, String>> cpdDbLinks = result.species;
    Map<String, Map<ReactionMajorLabel, String>> rxnDbLinks   = result.reactions;
    
    Map<String, String> spiToModelSeedReference = 
        KBaseModelSeedIntegration.filter(cpdDbLinks, MetaboliteMajorLabel.ModelSeed);
    Map<String, String> rxnToModelSeedReference = 
        KBaseModelSeedIntegration.filter(rxnDbLinks, ReactionMajorLabel.ModelSeedReaction);
    
    for (XmlSbmlSpecie xspi : xmodel.getSpecies()) {
      String id = xspi.getAttributes().get("id");
      System.out.println(id + " " + spiToModelSeedReference.get(id));
    }
    for (XmlSbmlReaction xrxn : xmodel.getReactions()) {
      String id = xrxn.getAttributes().get("id");
      System.out.println(id + " " + rxnToModelSeedReference.get(id));
    }
  }
  
  public static void testImportModel(String path) {
    String workspace = null;
    DataFileUtilClient dfuClient = null;
    WorkspaceClient wsClient = null;
    try {
      KBaseSbmlImporter importer = new KBaseSbmlImporter(workspace, dfuClient, wsClient);
      FileInputStream fis = new FileInputStream(path);
      ImportModelResult result = new ImportModelResult();
      IntegrationByDatabase spiIntegrationAll = new IntegrationByDatabase();
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
      
      IntegrationReport jsonResult = new IntegrationReport();
      importer.importModel(
          //InputStream to read the model
          fis, 
          //ImportModelResult
          result, 
          //ID to name the model
          "", 
          //URL to detect ID
          "https://raw.githubusercontent.com/Fxe/biomodels/master/kbase/PlantSEED_Cre.mdl.imported.xml",
          true,
          //Run Integration
          true, 
          new HashSet<String>(), 
          null, 
          spiIntegrationAll, 
          jsonResult, 
          true);
      
      jsonResult.setSpeciesIntegrationSummary(spiIntegrationAll);
      IntegrationReport.write(jsonResult, "D:\\opt\\nginx-1.9.6\\html\\biosynth-web-biobase\\exports\\kbase\\sbml-import/readerData.json");
      
    } catch (UnauthorizedException | IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    Path p = new File("/tmp/argonne/report").toPath();
    KBaseHtmlReport htmlReport = new KBaseHtmlReport(p);
    Map<String, String> files = new HashMap<>();
    File aaaaaaa = new File(
        "/home/fliu/workspace/java/kbase-SBMLTools-auth2/data/report/sbml-import/index.html");
    System.out.println(aaaaaaa.getAbsolutePath());
//    files.put("index.html", KBaseIOUtils.getResource("report/sbml-import/index.html"));
//    ReportFiles reportFiles = htmlReport.makeStaticReport(files);
    
    KBaseConfig.wut();
    String path = "/var/biomodels/kbase/PlantSEED_Cre.mdl.xml";
    XmlSbmlModel xmodel = testReadFixModel(path);
//    testGenerateDatabaseReferences(xmodel, "test");
    testImportModel(path);
  }
}
