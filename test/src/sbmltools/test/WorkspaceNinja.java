package sbmltools.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbasefba.FBAModel;
import kbasegenomes.Feature;
import kbasegenomes.Genome;
import kbsolrutil.KBaseAPI;
import pt.uminho.sysbio.biosynthframework.BFunction;
import pt.uminho.sysbio.biosynthframework.DataUtils;
import pt.uminho.sysbio.biosynthframework.Dataset;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseGeneIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseGenomeReport;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseId;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;
import sbmltools.KBaseType;
import us.kbase.auth.AuthConfig;
import us.kbase.auth.AuthException;
import us.kbase.auth.AuthToken;
import us.kbase.auth.ConfigurableAuthService;
import us.kbase.common.service.JsonClientException;
import us.kbase.workspace.ObjectSpecification;

public class WorkspaceNinja {

  private static final Logger logger = LoggerFactory.getLogger(WorkspaceNinja.class);
  
  public static void test1() {
    Map<String, String> config = KBaseAPI.getConfigDev();

    //  KBaseAPI.setupConfigProd(config);

    String authUrl = config.get("auth-service-url");

    try {
      ConfigurableAuthService authService =  new ConfigurableAuthService(
          new AuthConfig().withKBaseAuthServerURL(new URL(authUrl))
          .withAllowInsecureURLs("true".equals(config.get("auth-service-url-allow-insecure"))));
      AuthToken token = authService.validateToken("Y72TU4D34RICQI3MFVU3SXZ7HZQRVNDC");
      //    WorkspaceClient wsClient = new WorkspaceClient(new URL(config.get("workspace-url")), token);
      WSCLIENT wsClient = new WSCLIENT(new URL(config.get("workspace-url")), token, null);
      wsClient.setIsInsecureHttpConnectionAllowed(false);

      List<String> workspaces = new ArrayList<> ();
      workspaces.add("ReferenceDataManager");
      String objectType = "KBaseFBA.FBAModel";
      objectType = "KBaseGenomes.Genome";
      //    workspaces.add("filipeliu:narrative_1492697501369");

      //    List<Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>>> o = 
      //        wsClient.listObjects(
      //            new ListObjectsParams().withType(objectType).withWorkspaces(workspaces));
      //    for (Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>> t : o) {
      //      System.out.println(t);
      //    }
      //    System.out.println(wsClient.listAllTypes(new ListAllTypesParams().withWithEmptyModules(0L)));
      ObjectSpecification ospec = new ObjectSpecification();
      //pull genome
      //map genome -> model reactions
      //list genes with modelseed rxn not in model
      //list genes without modelseed rxn but in model
      //get model completness
      //awesome job !
      //    ospec.withWorkspace(workspace)
      //KBaseGenomes.Genome
      //GCF_000184185.1|feature--ECW_RS18855
      //    FBAModel fbaModel = KBaseIOUtils.getObject("Ec_core_flux1", "filipeliu:narrative_1492697501369", null, FBAModel.class, wsClient);
      //    Object genome = KBaseIOUtils.getObject("GCF_000184185.1", "ReferenceDataManager", null, wsClient);
      //    Map<String, ?> om = (HashMap) genome;
      //    System.out.println(om.get("id"));
      //    System.out.println(om.keySet());
      //    for (String k : om.keySet()) {
      //      Object pp = om.get(k);
      //      if (pp instanceof String) {
      //        System.out.println(k + "\t" + pp);
      //      }
      //    }
      FBAModel a;
      URL callbackURL = new URL("https://kbase.us/services/njs_wrapper");
      MockKBSolrUtilClient solr = new MockKBSolrUtilClient(callbackURL, token);
      //    KBSolrUtilClient solr = new KBSolrUtilClient(callbackURL, token);
      solr.setIsInsecureHttpConnectionAllowed(true);
      solr.setServiceVersion("beta");

      //    solr.
      //    Map<String, String> sq = new HashMap<> ();
      //    sq.put("q", "*");
      //    ExistsInputParams eparam = new ExistsInputParams()
      //        .withSearchCore("GenomeFeatures_prod").withSearchQuery(sq);
      //    System.out.println("existsInSolr");
      //    System.out.println(solr.existsInSolr(eparam));

      Set<String> genes = new HashSet<> ();
      //    genes.add("ECW_m3682");
      genes.add("PMM1297");
      genes.add("PMM1577");
      genes.add("PMM0591");
      genes.add("PMM0415");
      genes.add("PMM1315");
      //    genes.add("b0010");
      //    genes.add("b1000");

      //    CacheEngine cengine = new CacheEngine();
      //    KBaseGenome genome = cengine.getCacheData("GCF_000011465.1", "ReferenceDataManager", KBaseGenome.class);
      //    System.out.println(jsonStr.length());
      //    ObjectMapper om = new ObjectMapper();
      //    KBaseGenome genome = om.readValue(jsonStr, KBaseGenome.class);
      //    System.out.println(genome.assembly_ref);

      KBaseGeneIntegration geneIntegration = new KBaseGeneIntegration(wsClient, null, solr);
      System.out.println(geneIntegration.searchGenome(genes));
      System.out.println("close the stream !");
      //    InputStream is = callbackURL.openStream();
      //    BufferedReader br = new BufferedReader(new InputStreamReader(is));
      //    String l = null;
      //    while ((l = br.readLine()) != null) {
      //      System.out.println(l);
      //    }
      //    geneIntegration.aaa(kmodel);
      //    FbaToolsClient fbaClient = new FbaToolsClient(callbackURL, token);
      //    RunFluxBalanceAnalysisParams params = new RunFluxBalanceAnalysisParams()
      //        .withFbamodelId("iJO1366")
      //        .withMediaId("Sulfate-L-Arabitol")
      //        .withTargetReaction("bio1")
      //        .withFbaOutputId("iJO1366.fba")
      //        .withFva(1L)
      //        .withMinimizeFlux(1L)
      //        .withSimulateKo(0L)
      //        .withFeatureKoList(new ArrayList<String> ())
      //        .withReactionKoList(new ArrayList<String> ())
      //        .withCustomBoundList(new ArrayList<String> ())
      //        .withMediaSupplementList(null)
      //        .withExpseriesId(null)
      //        .withExpThresholdPercentile(0.6)
      //        .withExpThresholdMargin(0.1)
      //        .withActivationCoefficient(0.5);
      //    fbaClient.runFluxBalanceAnalysis(params);
    } catch (IOException | URISyntaxException | AuthException | JsonClientException e) {
      e.printStackTrace();
    }
  }
  
//  public static Map<String, Set<String>> getRolesMap() {
//    Map<String, Set<String>> result = new HashMap<> ();
//    Resource roleJson = new FileSystemResource("/var/biodb/modelseed/Roles.json");
//    JsonModelSeedRoleDao roleDao = new JsonModelSeedRoleDao(roleJson);
//    Map<String, Set<String>> aa = new HashMap<> ();
//    for (String k : roleDao.data.keySet()) {
//      ModelSeedRole role = roleDao.data.get(k);
////      System.out.println(k + " " + role.name);
//      if (!aa.containsKey(role.name.trim().toLowerCase())) {
//        aa.put(role.name.trim().toLowerCase(), new HashSet<String> ());
//      }
//      aa.get(role.name.trim().toLowerCase()).add(k);
//    }
//    int good = 0;
//    int bad = 0;
//    for (String k : aa.keySet()) {
//      
//      if (aa.get(k).size() > 1) {
////        System.out.println(k + " " + aa.get(k));
//        result.put(k.trim().toLowerCase(), aa.get(k));
//        bad++;
//      } else {
//        result.put(k.trim().toLowerCase(), aa.get(k));
//        good++;
//      }
//    }
//    logger.debug("roles good: {}, bad: {}", good, bad);
//    return result;
//  }
  
//  public static Map<String, Set<String>> getReactionRoles() {
//    Map<String, Set<String>> result = new HashMap<> ();
//    Resource roleJson = new FileSystemResource("/var/biodb/modelseed/Roles.json");
//    JsonModelSeedRoleDao roleDao = new JsonModelSeedRoleDao(roleJson);
//    for (String k : roleDao.data.keySet()) {
//      ModelSeedRole role = roleDao.data.get(k);
//      for (String rxn : role.reactions) {
//        rxn = rxn.split(";")[0].trim();
////        System.out.println(rxn + " " + k); 
//        if (!result.containsKey(rxn)) {
//          result.put(rxn, new HashSet<String> ());
//        }
//        result.get(rxn).add(k);
//      }
////      System.out.println(k + " " + role.reactions.size());
//
//    }
//    return result;
//  }
  
  public static String[] gotit = new String[] {
//      "iGT196",
//      "iGB555_fixed",
      "iBsu1103",
//      "iOG654",
//      "iJC568",
//      
//      //must have underscore !
//      "iJP962",  //PP_
//      "iJP815",  //PP_
//      "iCac802", //CA_
//      "iCAC490", //CA_
//      "iPB890",  //PST_
//      "iJH728",  //A -> SYNPCC7002_A
  };
  
  public static Map<String, String> getTsv(String path) {
    Map<String, String> result = new HashMap<>();
    
    InputStream is = null;
    try {
      is = new FileInputStream(path);
      List<String> lines = IOUtils.readLines(is);
      for (int i = 0; i < lines.size(); i++) {
        String[] cols = lines.get(i).split("\t");
        result.put(cols[0], cols[1]);
      }
    }  catch (IOException e) {
      e.printStackTrace();
    }finally {
      IOUtils.closeQuietly(is);
    }
    return result;
  }
  
  public static enum GeneType {
    METABOLIC_IN_GENOME,
    NON_METABOLIC_IN_GENOME,
    METABOLIC_IN_MODEL,
    NON_METABOLIC_IN_MODEL,
    METABOLIC,
    
  }
  
  public static Map<GeneType, Integer> geneCategory(
      Set<String> modelGenes,
      Genome genome, Map<String, Set<String>> functionToRxn) {
    Map<GeneType, Integer> result = new HashMap<> ();
    
    Map<String, GeneType> gclass = new HashMap<> ();
    Set<String> gmetabolic = IntegrationLocalRun.getMetabolic(genome, functionToRxn);
    
    for (Feature f : genome.getFeatures()) {
//      String function = f.getFunction();
//      if (function != null && function.toUpperCase().contains("16S")) {
//        System.out.println(function);
//      }
      gclass.put(f.getId(), GeneType.NON_METABOLIC_IN_GENOME);
    }
    
    for (String g : gmetabolic) {
      gclass.put(g, GeneType.METABOLIC_IN_GENOME);
    }
    
    for (String mg : modelGenes) {
      if (GeneType.METABOLIC_IN_GENOME.equals(gclass.get(mg))) {
        gclass.put(mg, GeneType.METABOLIC);
      } else if (GeneType.NON_METABOLIC_IN_GENOME.equals(gclass.get(mg))) {
        gclass.put(mg, GeneType.METABOLIC_IN_MODEL);
      }
    }
    
    result = CollectionUtils.count(gclass);
//    logger.info("Model: {}, Genome: {}, Both: {}", mmetabolic.size(), gmetabolic.size(), shared.size());
    
    return result;
  }

  public static void main(String[] args) {
    Map<String, BFunction<String, String>> modelGeneTransformers = new HashMap<>();
    modelGeneTransformers.put("iCac802", ManualFixes.get_iCAC());
    modelGeneTransformers.put("iCAC490", ManualFixes.get_iCAC());
    modelGeneTransformers.put("iCac802_V_cobragitsengerpapou", ManualFixes.get_iCAC());
    modelGeneTransformers.put("iJH728", ManualFixes.get_iJH728());
    modelGeneTransformers.put("iLCBaumannia", ManualFixes.get_iLCBaumannia());
    modelGeneTransformers.put("iJP962", ManualFixes.get_iJP962_iJP815());
    modelGeneTransformers.put("iJP815", ManualFixes.get_iJP962_iJP815());
    modelGeneTransformers.put("iPB890", ManualFixes.get_iPB890());
    modelGeneTransformers.put("iOD907", ManualFixes.get_iOD907());
    
    Map<String, String> swap = getTsv("/var/argonne/model_gpr/iBsu1103_swap.tsv");
    
    String genomeAnnotationWorkspace = "filipeliu:narrative_1502913563238";
    Map<String, Set<String>> functionToRxn = IntegrationLocalRun.getFunctionToRxn();
    Map<String, Set<String>> rmap = new HashMap<>(); //getRolesMap();
    Map<String, Set<String>> roles = new HashMap<>(); //getReactionRoles();
    Map<String, Set<String>> roleToRxn = new HashMap<> ();
    for (String rxn : roles.keySet()) {
      for (String r : roles.get(rxn)) {
        if (!roleToRxn.containsKey(r)) {
          roleToRxn.put(r, new HashSet<String> ());
        }
        roleToRxn.get(r).add(rxn);
      }
    }
//    System.out.println(roles.keySet());
//    System.out.println(rmap.get("chloroplast"));
//    System.out.println(roles.get("rxn06244"));
    String LOGIN_TOKEN = "Q5DLF7PMWWPLZPCO66KN55MJ3KTA5JYI";
    Dataset<String, String, Object> data = new Dataset<>();
    try {
      KBaseAPI prodAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigProd(), true);
      KBaseAPI devAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigDev(), true);
      KBaseGeneIntegration geneIntegration = new KBaseGeneIntegration(prodAPI.getWorkspaceClient(), prodAPI.dfuClient, prodAPI.getKBSolrUtilClient());
      //      prodAPI.listNarrative("ReferenceDataManager", "KBaseGenomes.Genome");
      String modelWorkspace = "filipeliu:narrative_1492697501369"; //small
      modelWorkspace = "filipeliu:narrative_1502428739293"; //repo
      //      devAPI.find("KBaseFBA.FBAModel", "HMRdatabase2_00_Cobra", "filipeliu");
      List<KBaseId> qGenomes = prodAPI.listNarrative(IntegrationLocalRun.PROD_RAST_GENOME, KBaseType.Genome);
      Set<String> rastGenomes = new HashSet<> ();
      for (KBaseId id : qGenomes) {
        if (id.name.endsWith(".rast")) {
          rastGenomes.add(id.name.replace(".rast", ""));
        }
      }
//      System.out.println(rastGenomes);
      List<KBaseId> fbaModelQuery = devAPI.listNarrative(modelWorkspace, "KBaseFBA.FBAModel");
//      List<KBaseId> fbaModelQuery = new ArrayList<>();
//      fbaModelQuery.add(new KBaseId("iJO1366_bigg2", modelWorkspace, null));
//      fbaModelQuery.add(new KBaseId("iAF1260", modelWorkspace, null));
//      fbaModelQuery.add(new KBaseId("iJR904", modelWorkspace, null));
//      fbaModelQuery.add(new KBaseId("iLCBaumannia", modelWorkspace, null));
      Set<String> exclude = new HashSet<> ();
      exclude.add("iJN678");
      exclude.add("iAI549");
      for (KBaseId kid : fbaModelQuery) {
        FBAModel kmodel = devAPI.getWorkspaceObject(kid.name, modelWorkspace, FBAModel.class);
        kmodel.setGenomeRef("23373/5/2");
        
        if (!kmodel.getId().toLowerCase().contains("neuron") && !exclude.contains(kmodel.getId())) {
          geneIntegration.geneTransformer = modelGeneTransformers.get(kmodel.getId());
          geneIntegration.searchGenome(kmodel, false);
          
          KBaseGenomeReport report = geneIntegration.report;
          System.out.println(report.bestMatch);
          System.out.println(report.bestScore);
          System.out.println(report.matchScores);
          System.out.println(report.matchOrganism);
          int metab = 0;
          int nmetab = 0;
          for (String fid : report.getUnmappedFeatures()) {
            String function = report.geneFunction.get(fid);
            if (function != null) {
              function = function.toLowerCase().trim();
              Set<String> froles = rmap.get(function);
              Set<String> rxn = new HashSet<> ();
              if (froles != null) {
                for (String r : froles) {
                  Set<String> rxns = roleToRxn.get(r);
                  if (rxns != null && !rxns.isEmpty()) {
                    rxn.addAll(rxns);
                  }
                }
              }
//              System.out.println(function + " " + rxn);
              if (!rxn.isEmpty()) {
                metab++;
              } else {
                nmetab++;
              }
            }
          }
          
          if (report.bestMatch == null && report.mgenes.size() == 0) {
            report.bestMatch = "missing gpr";
          }
          
          if (report.bestMatch == null && report.mgenes.size() > 0) {
            report.bestMatch = "?";
          }
          
          if (report.bestMatch != null) {
            Map<GeneType, Integer> geneCategory = new HashMap<>();
            
            
            KBaseId genomeKid = report.getGenomeId();
            String genomeName = "";
            if (genomeKid!= null && genomeKid.reference != null) {
              try {
                genomeName = genomeKid.name;
                if (rastGenomes.contains(genomeName)) {
                  logger.warn("RAST GENOME FOUND !");
                  Genome rastG = prodAPI.getGenome(genomeName + ".rast", IntegrationLocalRun.PROD_RAST_GENOME);
                  geneCategory = geneCategory(report.mgenesMapped, rastG, functionToRxn);
//                  Set<String> gmetabolic = IntegrationLocalRun.getMetabolic(rastG, functionToRxn);
//                  Set<String> mmetabolic = new HashSet<> (report.mgenesMapped);
//                  Set<String> shared = Sets.intersection(mmetabolic, gmetabolic);
                  
                  
                  //model metabolic
                  
                  //rast metabolic
                  //intersection
                  //
                }
//                Genome genome = prodAPI.getGenome(id, ws)
                
                kmodel.setGenomeRef(genomeKid.reference);
                KBaseIOUtils.saveData(kmodel.getId(), KBaseType.FBAModel.value(), kmodel, "filipeliu:narrative_1502913538729", prodAPI.wsClient);
              } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }

//            System.out.println(genomeKid);
//            kmodel.setGenomeRef(genomeKid.reference);
//            String ref = KBaseIOUtils.saveDataSafe(kid.name, KBaseType.FBAModel, kmodel, "filipeliu:narrative_1502913538729", prodAPI.dfuClient);
//            System.out.println(ref);
            
            data.add(kid.name, "org", report.bestMatch);
            data.add(kid.name, "genome", genomeName);
            data.add(kid.name, "features", report.geneFunction.size());
            data.add(kid.name, "fmiss", report.getUnmappedFeatures().size());
            data.add(kid.name, "fmetabmiss", metab);
            data.add(kid.name, "fnetabmiss", nmetab);
            data.add(kid.name, "gprGenes", report.mgenes.size());
            data.add(kid.name, "gprGenesIntegrated", report.mgenesMapped.size());
            data.add(kid.name, "model", kmodel.getId());
            
            data.add(kid.name, "g_type_rast_m", geneCategory.get(GeneType.METABOLIC_IN_GENOME));
            data.add(kid.name, "g_type_non_m", geneCategory.get(GeneType.NON_METABOLIC_IN_GENOME));
            data.add(kid.name, "g_type_both_m", geneCategory.get(GeneType.METABOLIC));
            data.add(kid.name, "g_type_model_m", geneCategory.get(GeneType.METABOLIC_IN_MODEL));
//            data.add(kid.name, "model", kmodel.getId());
          }
        } else {
          System.out.println("SKIP " + kmodel. getId());
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    
    DataUtils.printData(data.dataset, 
        "id", "features", "fmiss", "org", "gprGenes", "gprGenesIntegrated", "model", 
        "g_type_non_m", "g_type_rast_m", "g_type_model_m", "g_type_both_m");
  }
}

