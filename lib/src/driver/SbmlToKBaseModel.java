package driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kbasefba.FBAModel;
import me.fxe.kbase.KBaseFBAModelFactory;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.ReactionMajorLabel;
import pt.uminho.sysbio.biosynthframework.SubcellularCompartment;
import pt.uminho.sysbio.biosynthframework.integration.model.CompartmentDetectorFlatString;
import pt.uminho.sysbio.biosynthframework.integration.model.CompartmentDetectorKBase;
import pt.uminho.sysbio.biosynthframework.integration.model.CompartmentIntegration;
import pt.uminho.sysbio.biosynthframework.integration.model.IntegrationMap;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseBiodbContainer;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelSeedIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelSeedIntegration.KBaseMappingResult;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseUtils;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter.ImportModelResult;
import pt.uminho.sysbio.biosynthframework.report.IntegrationByDatabase;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReport;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReportResult;
import pt.uminho.sysbio.biosynthframework.report.IntegrationReportResultAdapter;
import pt.uminho.sysbio.biosynthframework.sbml.MessageCategory;
import pt.uminho.sysbio.biosynthframework.sbml.MessageType;
import pt.uminho.sysbio.biosynthframework.sbml.XmlMessage;
import pt.uminho.sysbio.biosynthframework.sbml.XmlMessageGroup;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelAdapter;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelAutofix;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelValidator;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import pt.uminho.sysbio.biosynthframework.util.AutoFileReader;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;
import pt.uminho.sysbio.biosynthframework.util.FileType;
import pt.uminho.sysbio.ext.MethoBuilder;

public class SbmlToKBaseModel {
  
  private static final Logger logger = LoggerFactory.getLogger(SbmlToKBaseModel.class);
  
  public static KBaseModelSeedIntegration modelSeedIntegration = null;
  public static KBaseBiodbContainer biodbContainer = null;
  
  public static void main2(String[] args) {
    {
      Object o = getStreams("D:\\var\\sofia\\iJO1366.xml");
      System.out.println(o);
    }
    {
      Object o = getStreams("D:\\var\\biomodels\\fungis.zip");
      System.out.println(o);
    }
    
    {
      KBaseConfig.dbPath = "D:/home/fliu/workspace/java/kbase-SBMLTools-auth2/data/modelseed";
      MethoBuilder.buildKBaseIntegrationEngine();
    }
    
    
  }
  
  
  public static void main(String[] args) {
    String outpath = ".";
    boolean removeBoundary = false;
    boolean runIntegration = false;
    List<String> biomassNames = new ArrayList<>();
    String modelName = null;
    String curationPath = KBaseConfig.CURATION_DATA;
    String dataPath = KBaseConfig.DATA_EXPORT_PATH;
    String modelSeedPath = KBaseConfig.dbPath;
    

    
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (arg.equals("--integrate")) {
        runIntegration = true;
      } else if (arg.equals("--removeb")) {
        removeBoundary = true;
      } else if (arg.equals("--data")) {
        i++;
        dataPath = args[i];
      } else if (arg.equals("--curation")) {
        i++;
        curationPath = args[i];
      } else if (arg.equals("--modelseed")) {
        i++;
        modelSeedPath = args[i];
      } else if (arg.equals("--name")) {
        i++;
        modelName = args[i];
      } else if (arg.equals("--biomass")) {
        i++;
        for (String str : args[i].split(";")) {
          biomassNames.add(str);
        }
      } else if (arg.equals("-O")) {
        i++;
        outpath = args[i];
      }
    }
    
    if (args.length == 0) {
      System.err.println("<sbml>");
      System.exit(1);
    }
    
    KBaseConfig.dbPath = modelSeedPath;
    
    String path = args[args.length - 1];
    
    logger.info("data path: {}", dataPath);
    logger.info("curation path: {}", curationPath);
    logger.info("modelseed path: {}", modelSeedPath);
    logger.info("remove boundary: {}", removeBoundary);
    logger.info("integration: {}", runIntegration);
    logger.info("biomass names: {}", biomassNames);
    logger.info("modelname: {}", modelName);
    logger.info("inpath: {}", path);
    logger.info("outpath: {}", outpath);
    
    
    Map<String, InputStream> streams = getStreams(path);
    
    logger.info("Streams {}", streams);
    
    biodbContainer = new KBaseBiodbContainer(dataPath);
    modelSeedIntegration = new KBaseModelSeedIntegration(dataPath, curationPath, biodbContainer);
    
    if (streams.size() > 1) {
      modelName = null;
    }
    
    run(streams, modelName, biomassNames, runIntegration, removeBoundary, outpath);
  }
  
  public static Map<String, InputStream> getStreams(String localPath) {
    
    Map<String, InputStream> result = new HashMap<>();
    
    FileType fileType = FileType.AUTO;
    AutoFileReader freader = new AutoFileReader(localPath, fileType);
    
    try {
      
      fileType = AutoFileReader.getFileType(localPath);
      //freader = new AutoFileReader(localPath, fileType);

      Map<String, InputStream> inputStreams = freader.getStreams().streams;
      if (FileType.XML.equals(fileType)) {
        result.put(localPath, new FileInputStream(localPath));
//        System.out.println("!!");
//        String prev = inputStreams.keySet().iterator().next();
//        inputStreams.put(localPath, inputStreams.get(prev));
//        inputStreams.remove(prev);
      } else {
        result = inputStreams;
      }
      
    } catch(IOException e) {
      e.printStackTrace();
    }
    
    return result;
  }
  
  public static FBAModel importModel(InputStream is, 
      ImportModelResult result, String modelId, String url,
      boolean runCompartmentIntegration,
      boolean runIntegration, 
      Collection<String> biomassIds, 
      String genomeRef,
      IntegrationByDatabase spiIntegrationAll,
      IntegrationReport jsonResult, boolean allowBoundary) {
    
    FBAModel kmodel = null;
    try {
      //import
      XmlStreamSbmlReader reader = new XmlStreamSbmlReader(is);
      XmlSbmlModel xmodel = reader.parse();
      XmlSbmlModelAdapter model = new XmlSbmlModelAdapter(xmodel);
      jsonResult.addIntegrationReport(modelId, new IntegrationReportResult());

      IntegrationReportResult reportData = new IntegrationReportResult();
      IntegrationReportResultAdapter resultAdapter = 
          new IntegrationReportResultAdapter(reportData);
      reportData.name = modelId;
      reportData.epochTime = System.currentTimeMillis();
      reportData.date = new GregorianCalendar().getTime().toString();
      resultAdapter.fillImportData(xmodel);
      spiIntegrationAll.modelTotal.put(modelId, xmodel.getSpecies().size());

      logger.info("validate");
      
      List<XmlMessage> vmsg = new ArrayList<> ();
      
      //VALIDATION
      XmlSbmlModelValidator validator = new XmlSbmlModelValidator(xmodel);
      XmlSbmlModelValidator.initializeDefaults(validator);

      List<XmlMessage> msgs = validator.validate();
      
      //CORRECTION
      XmlSbmlModelAutofix autofix = new XmlSbmlModelAutofix();
      autofix.group.put(MessageCategory.STOICH_NO_VALUE, 
          new XmlMessageGroup(MessageCategory.STOICH_NO_VALUE, MessageType.WARN, "assume value 1"));
      autofix.fix(xmodel, msgs);

      List<XmlMessage> fmsgs = autofix.messages;
      vmsg.addAll(fmsgs);
//      resultAdapter.fillValidationData(fmsgs);

      //VALIDATION step 2
      XmlSbmlModelValidator validator2 = new XmlSbmlModelValidator(xmodel);
      XmlSbmlModelValidator.initializeDefaults(validator2);
      List<XmlMessage> msgs2 = validator2.validate();
      vmsg.addAll(msgs2);
//      resultAdapter.fillValidationData(msgs2);

      if (resultAdapter.getMessageTypeCount(MessageType.CRITICAL) > 0) {
        result.message += "\n" + String.format("Unable to import model %s", url);
        IntegrationReportResult iresult = new IntegrationReportResult();
        iresult.error = String.format("Unable to import model %s", url);
        jsonResult.addIntegrationReport(modelId, iresult);
        return null;
      }

      result.message += "\n" + String.format("Species %d, Reactions %s, %s", 
          xmodel.getSpecies().size(), 
          xmodel.getReactions().size(), 
          url);
      //      String txt = "";
      for (XmlMessage m : vmsg) {
        CollectionUtils.increaseCount(reportData.validationCount, m.type, 1);
      }
      
      //write message json for model
      if (!vmsg.isEmpty()) {
        reportData.validationData = modelId + "_msg.json";
        String jsonData = KBaseIOUtils.toJson(vmsg, true);
        Integer i = KBaseIOUtils.writeStringFile(jsonData, KBaseConfig.REPORT_OUTPUT_PATH + "/" + reportData.validationData);
        logger.info("written {} bytes", i);
      }
      
      Map<String, SubcellularCompartment> cmap = new HashMap<>();
      Map<String, String> spiToModelSeedReference = new HashMap<> ();
      Map<String, String> rxnToModelSeedReference = new HashMap<> ();
      IntegrationMap<String, String> sintegration = new IntegrationMap<>();
      IntegrationMap<String, String> rintegration = new IntegrationMap<>();
      
      if (runCompartmentIntegration) {
        CompartmentIntegration cintegration = 
            new CompartmentIntegration();
        cintegration.singleCompartmentAsCytosol = true;
        cintegration.createBoundaryCompartment = false;
        cintegration.detectors.add(new CompartmentDetectorKBase());
        cintegration.detectors.add(new CompartmentDetectorFlatString(false));
        cintegration.detectors.add(new CompartmentDetectorFlatString(true));
        cmap = cintegration.generateCompartmentMapping(model);
      }

      //check if integrate
      if (runIntegration) {
//        modelSeedIntegration.spiToModelSeedReference.clear();

        KBaseMappingResult mapping = modelSeedIntegration.generateDatabaseReferences(xmodel, modelId, resultAdapter);

        Map<String, Map<MetaboliteMajorLabel, String>> imap = mapping.species;
        Map<String, Map<ReactionMajorLabel, String>> rimap = mapping.reactions;

        spiIntegrationAll.addIntegrationMap(modelId, imap);
        for (String spi : imap.keySet()) {
          for (MetaboliteMajorLabel db : imap.get(spi).keySet()) {
            sintegration.addIntegration(spi, db.toString(), imap.get(spi).get(db));
          }
        }

        for (String mrxn : rimap.keySet()) {
          for (ReactionMajorLabel db : rimap.get(mrxn).keySet()) {
            rintegration.addIntegration(mrxn, db.toString(), rimap.get(mrxn).get(db));
          }
        }

        spiToModelSeedReference = 
            KBaseModelSeedIntegration.filter(imap, MetaboliteMajorLabel.ModelSeed);
        rxnToModelSeedReference = 
            KBaseModelSeedIntegration.filter(rimap, ReactionMajorLabel.ModelSeedReaction);
//        result.message += String.format("\ni: %d", spiToModelSeedReference.size());
      }

      //order matters ! fix this ... it is a factory ...
      kmodel = new KBaseFBAModelFactory()
          .withCompartmentMapping(cmap)
          .withGenomeRef(genomeRef)
          .withSpecieIntegration(sintegration)
          .withReactionIntegration(rintegration)
          .withBiomassIds(biomassIds)
          .withMetaboliteModelSeedReference(spiToModelSeedReference)
          .withReactionModelSeedReference(rxnToModelSeedReference)
          .withModelId(modelId)
          .withModelName(modelId)
          .withXmlSbmlModel(xmodel, allowBoundary)
          .build();
      
//      if (genomeRef != null && 
//          !genomeRef.trim().isEmpty() && 
//          wsClient != null) {
//        KBaseModelAdapter mAdapter = new KBaseModelAdapter(kmodel);
//        Pair<KBaseId, Object> kdata = KBaseIOUtils.getObject2(genomeRef, workspace, null, wsClient);
//        Genome genome = KBaseUtils.convert(kdata.getRight(), Genome.class);
//        mAdapter.attachGenome(genome, true);
//      }

//      if (!kmodel.getBiomasses().isEmpty()) {
//        Set<String> biomass = new HashSet<> ();
//        for (Biomass b : kmodel.getBiomasses()) {
//          biomass.add(b.getId());
//        }
//        result.message +="\n" + modelId + " biomass: " + biomass;
//      }

      jsonResult.addIntegrationReport(modelId, reportData);

      //    KBaseIOUtils.toJson(model);
    } catch (Exception e) {
      e.printStackTrace();
      IntegrationReportResult iresult = new IntegrationReportResult();
      iresult.error = KBaseUtils.toString(e);
      jsonResult.addIntegrationReport(modelId, iresult);
    }
    
    return kmodel;
  }
  
  public static void write(FBAModel fbaModel, String outpath) {
    File f = new File(String.format("%s/%s.json", outpath, fbaModel.getId()));
    
    logger.info("write: {}", f);
    
    ObjectMapper om = new ObjectMapper();
    try {
      om.writeValue(f, fbaModel);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void run(Map<String, InputStream> inputStreams, String modelName, List<String> biomassNames, 
      boolean runIntegration, boolean removeBoundary, String outpath) {
    
    ImportModelResult result = new ImportModelResult();
    IntegrationReport jsonResult = new IntegrationReport();
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
    
    for (String u : inputStreams.keySet()) {
      logger.info("convert: {}", u);
      InputStream is = inputStreams.get(u);
      try {
        
        if (modelName == null || modelName.trim().isEmpty()) {
          modelName = KBaseUtils.getNameFromUrl(u);
          logger.info("auto model id: {}", modelName);
        }
        modelName = modelName.trim();
        
        FBAModel fbaModel = importModel(
            is, result, modelName, u, 
            true, 
            runIntegration, biomassNames, null, spiIntegrationAll, jsonResult, !removeBoundary);

        if (fbaModel != null) {
          write(fbaModel, outpath);          
        } else {
          logger.warn("failed to convert: {}", u);
        }
        
      } catch (Exception e) {
        result.message += "\nERROR: " + u + " " + e.getMessage();
      }
      modelName = null;
    }
  }
}
