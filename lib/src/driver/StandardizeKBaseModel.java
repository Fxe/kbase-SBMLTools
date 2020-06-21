package driver;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import kbasebiochem.Media;
import kbasefba.FBAModel;
import kbasegenomes.Genome;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseBiodbContainer;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIntegrationReport;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;

public class StandardizeKBaseModel {
  
  public static<T> T readJsonFile(File file, Class<T> clazz) {
    ObjectMapper om = new ObjectMapper();
    T fbaModel = om.convertValue(file, clazz);
    return fbaModel;
  }
  
  public static void main(String[] args) {
//    String outpath = ".";
    //boolean removeBoundary = false;
    //boolean runIntegration = false;
    Set<String> biomassNames = new HashSet<>();
    String mediaId = null;
    String genomeFilename = null;
    String translate = "modelseed";
//    String curationPath = KBaseConfig.CURATION_DATA;
//    String dataPath = KBaseConfig.DATA_EXPORT_PATH;
//    String modelSeedPath = KBaseConfig.dbPath;
    
    String modelOutputFilename  = "./integrated.json";
    String mediaOutputFilename  = "./media.json";
    String reportOutputFilename = "./data.json";
    
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (arg.equals("--mediaFilename")) {
        i++;
        mediaOutputFilename = args[i];
      } else if (arg.equals("--reportFilename")) {
        i++;
        reportOutputFilename = args[i];
      } else if (arg.equals("--data")) {
        i++;
        KBaseConfig.DATA_EXPORT_PATH = args[i];
      } else if (arg.equals("--curation")) {
        i++;
        KBaseConfig.CURATION_DATA = args[i];
      } else if (arg.equals("--modelseed")) {
        i++;
        KBaseConfig.dbPath = args[i];
      } else if (arg.equals("--translate")) {
        i++;
        translate = args[i];
      } else if (arg.equals("--media")) {
        i++;
        mediaId = args[i];
      } else if (arg.equals("--genomeFilename")) {
        i++;
        genomeFilename = args[i];
      } else if (arg.equals("--biomass")) {
        i++;
        for (String str : args[i].split(";")) {
          biomassNames.add(str);
        }
      } else if (arg.equals("-O")) {
        i++;
        modelOutputFilename = args[i];
      }
    }
    
    if (args.length == 0) {
      System.err.println("<FBAModel.json>");
      System.exit(1);
    }
    
    String path = args[args.length - 1];

    //read FBAModel from path
    FBAModel kmodel = readJsonFile(new File(path), FBAModel.class);
    
    //read Genome from genomeFilename
    Genome genome = null;
    if (!DataUtils.empty(genomeFilename)) {
      genome = readJsonFile(new File(genomeFilename), Genome.class);
    }
    Map<String, String> gprOverride = new HashMap<>();
    Map<String, String> cpdOverride = new HashMap<>();
    Map<String, String> compartmentMapping = new HashMap<>();
    
    KBaseBiodbContainer biodbContainer = new KBaseBiodbContainer(KBaseConfig.DATA_EXPORT_PATH);
    
    IntegrationOutput output = kbaseIntegrate(kmodel, gprOverride, cpdOverride, compartmentMapping, biomassNames, mediaId, translate, genome, biodbContainer);
    
    if (output.outputMedia != null) {
      KBaseIOUtils.writeStringFile(KBaseIOUtils.toJson(output.outputMedia), mediaOutputFilename);
    }
    if (output.outputModel != null) {
      KBaseIOUtils.writeStringFile(KBaseIOUtils.toJson(output.outputModel), modelOutputFilename);
    }
    if (output.report != null) {
      KBaseIOUtils.writeStringFile(KBaseIOUtils.toJson(output.report), reportOutputFilename);
    }
  }
  
  public static class IntegrationOutput{
    public FBAModel outputModel;
    public Media outputMedia;
    public KBaseIntegrationReport report;
  }
  
  public static IntegrationOutput kbaseIntegrate(FBAModel fbaModel, 
                                                 Map<String, String> gprOverride, 
                                                 Map<String, String> cpdOverride, 
                                                 Map<String, String> compartmentMapping, 
                                                 Set<String> biomassReactions, 
                                                 String mediaId, 
                                                 String translateDatabase,
                                                 Genome genome,
                                                 KBaseBiodbContainer biodbContainer) {
    
    KBaseIntegrationReport kir = new KBaseIntegrationReport();
    kir.model = fbaModel.getId();
    kir.objName = fbaModel.getName();
    
    boolean allowNumberId = true;
    
    KBaseIntegration integration = new KBaseIntegration(fbaModel);
    integration.report = kir;
    integration.biomassSet.addAll(biomassReactions);
    integration.compartmentMapping = compartmentMapping;
    integration.rename = translateDatabase;
    integration.fillMetadata = true;
    integration.mediaName = mediaId;
    integration.biodbContainer = biodbContainer;
    integration.gprOverride = gprOverride;
    integration.cpdOverride = cpdOverride;
    integration.allowNumberId = allowNumberId;
    integration.fixIdToKBase = true;
    
    if (genome != null) {
      integration.genome = genome;
    }
    
    integration.integrate();
    
    kir.genomeReport.status = "auto_genome_get_fail";
    if (genome == null) {
      kir.genomeReport.status = "no genome assigned";
    } else {
      kir.fillGenomeData(integration.greport);
      kir.genomeReport.status = "user";
    }
    
    Media defaultMedia = null;
    if (!DataUtils.empty(mediaId)&&
        integration.defaultMedia != null) {
      defaultMedia = integration.defaultMedia;
      //outputObjects.put(mediaKid.reference, "detected media");
    }
    
    FBAModel integratedModel = fbaModel;
    kir.fillModelData(integration.adapter);
    //outputObjects.put(integratedModelKid.reference, "integrated model");
//    KBaseIOUtils.writeStringFile(KBaseIOUtils.toJson(kir), "/kb/module/data/data.json");
    
    IntegrationOutput output = new IntegrationOutput();
    output.outputMedia = defaultMedia;
    output.outputModel = integratedModel;
    output.report = kir;
    return output;
  }
}
