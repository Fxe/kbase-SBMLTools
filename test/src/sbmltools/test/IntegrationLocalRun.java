package sbmltools.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.Resource;

import kbasebiochem.Media;
import kbasefba.FBAModel;
import kbasefba.ModelReaction;
import kbasefba.ModelReactionProtein;
import kbasefba.ModelReactionProteinSubunit;
import kbasegenomes.Feature;
import kbasegenomes.Genome;
import kbsolrutil.KBaseAPI;
import pt.uminho.ceb.biosystems.mew.biocomponents.container.components.GeneReactionRuleCI;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTree;
import pt.uminho.sysbio.biosynthframework.BFunction;
import pt.uminho.sysbio.biosynthframework.BMap;
import pt.uminho.sysbio.biosynthframework.Dataset;
import pt.uminho.sysbio.biosynthframework.biodb.seed.ModelSeedRole;
import pt.uminho.sysbio.biosynthframework.genome.NAlignTool;
import pt.uminho.sysbio.biosynthframework.io.FileImportKb;
import pt.uminho.sysbio.biosynthframework.io.biodb.JsonModelSeedRoleDao;
import pt.uminho.sysbio.biosynthframework.kbase.EasyKBase;
import pt.uminho.sysbio.biosynthframework.kbase.FBAModelAdapter;
import pt.uminho.sysbio.biosynthframework.kbase.FBAModelFactory;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseGeneIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseId;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIntegration;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIntegrationReport;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelIntegrationFacade;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseSbmlImporter;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseUtils;
import pt.uminho.sysbio.biosynthframework.kbase.genome.AlignmentKernel;
import pt.uminho.sysbio.biosynthframework.kbase.genome.AlignmentKernel.AlignmentJob;
import pt.uminho.sysbio.biosynthframework.kbase.genome.KbaseGenomeUtils;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;
import sbmltools.CompartmentMapping;
import sbmltools.IntegrateModelParams;
import sbmltools.KBaseType;

public class IntegrationLocalRun {
  
  private static final Logger logger = LoggerFactory.getLogger(IntegrationLocalRun.class);
  
  public static String LOGIN_TOKEN = "3OJVW5P6KWFG7AJCNLN7VFUHHIRVOELP";
  
  public static String  DEV_PUBLISHED_MODEL_REPO = "filipeliu:narrative_1502428739293";
  public static String PROD_PUBLISHED_MODEL_REPO = "filipeliu:narrative_1502913538729";
  public static String PROD_KBASE_TEMPLATES = "NewKBaseModelTemplates";
  public static String PROD_QZ_GENOMES = "ReferenceDataManager";
  public static String PROD_RAST_GENOME = "filipeliu:narrative_1502913563238";
  public static String PROD_GENOME = "filipeliu:narrative_1502913538729";
  public static String PROD_1000_MODELS = "chenry:narrative_1493799658132";
  public static String PROD_1000_RAST_GENOMES = "jplfaria:narrative_1492808527866";
  public static String DEV_APP_TEST = "filipeliu:narrative_1503501857164";
  public static String DEV_SOME_REPO = "filipeliu:narrative_1502474753893";
  public static String PROD_ECOLI_CORES = "filipeliu:narrative_1504192868437";
  
  public static void localIntegraiton() {
    String model = "Ec_core_flux1";
    model = "iJW145";
    try {
      KBaseAPI devAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigDev(), true);
      String wsName = DEV_APP_TEST;
      List<KBaseId> fbaModelQuery = devAPI.listNarrative(wsName, "KBaseFBA.FBAModel");
      KBaseId kid = new KBaseId(model, wsName, "7427/6/15");
//      FBAModel kmodel = devAPI.getWorkspaceObject(kid.name, modelWorkspace, FBAModel.class);
      
      List<KBaseId> fbaModelQuery2 = devAPI.listNarrative(wsName, "KBaseGenomes.Genome");
      //"   filipeliu:narrative_1502474753893"
      for (KBaseId kid_ : fbaModelQuery2) {
        System.out.println(kid_);
      }
      List<CompartmentMapping> cmap = new ArrayList<CompartmentMapping>();
      cmap.add(new CompartmentMapping().withKbaseCompartmentId("c").withModelCompartmentId(Arrays.asList(new String[]{"z1"})));
      cmap.add(new CompartmentMapping().withKbaseCompartmentId("e").withModelCompartmentId(Arrays.asList(new String[]{"z0"})));
      KBaseModelIntegrationFacade integration = 
          new KBaseModelIntegrationFacade(devAPI.wsClient, devAPI.dfuClient, null, null, null, "/var/biobase/export", null);
      integration.kbaseIntegrate(
          new IntegrateModelParams().withModelName(kid.name)
          .withCompartmentTranslation(cmap)
          .withGenomeId("GCF_000027345.1")
          .withWorkspaceName(wsName)
          .withOutputModelName("kb." + kid.name)
          .withFillMetadata(1L)
          .withTranslateDatabase("ModelSeed")
          .withRemoveBoundary(1L)
          .withBiomassReactions("IR09955")
          .withCreateExtracellular(1L)
          .withGeneMappings("")
          .withCompoundMappings("")
          .withTemplateId(""),
          wsName);
      
      
      KBaseSbmlImporter.CURATION_DATA = "/var/biobase/integration/cc/cpd_curation.tsv";
      KBaseSbmlImporter.LOCAL_CACHE = "/tmp/argonne";
      KBaseSbmlImporter.REPORT_OUTPUT_PATH = "/opt/nginx-1.9.6/html/biosynth-web-biobase/exports/model-integration-report/readerData.json";
      KBaseSbmlImporter.DATA_EXPORT_PATH = "/var/biobase/export";
//      KBaseSbmlImporter sbmlTools = new KBaseSbmlImporter(modelWorkspace, devAPI.dfuClient);
//      sbmlTools.
//      RASTSDKClient rastClient = new RASTSDKClient(devAPI.callbackURL, devAPI.authToken);
//      rastClient.annotateGenome(new AnnotateGenomeParams());
////      rastClient.get
//      SBMLToolsClient stools = new SBMLToolsClient(devAPI.callbackURL, devAPI.authToken);
////      stools.
//      System.out.println(stools.isStreamingModeOn());
//      stools.setStreamingModeOn(true);
//      stools.setIsInsecureHttpConnectionAllowed(true);
////      stools.
//      stools.integrateModel(
//          new IntegrateModelParams().withModelName(kid.name));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  

  
  public static void updateGpr(FBAModel kmodel) throws Exception {
    for (ModelReaction krxn : kmodel.getModelreactions()) {
      GeneReactionRuleCI grrci = new GeneReactionRuleCI(krxn.getImportedGpr());
      AbstractSyntaxTree<?, ?> rule = grrci.getRule();
      Set<String> genes = KBaseUtils.getGenes(krxn.getImportedGpr());
      if (genes != null && genes.size() == 1) {
        System.out.println(genes);
        List<ModelReactionProteinSubunit> mrpsLists = new ArrayList<> (); 
        List<String> features = new ArrayList<> ();
        features.add(String.format("%s/features/id/%s", kmodel.getGenomeRef(), genes.iterator().next()));
        //1985/8/4/features/id/kb|g.220339.CDS.100
        ModelReactionProteinSubunit mrps = new ModelReactionProteinSubunit()
            .withFeatureRefs(features)
            .withTriggering(0L)
            .withRole("")
            .withNote("Imported GPR")
            .withOptionalSubunit(0L);
        mrpsLists.add(mrps);
        ModelReactionProtein mrp = new ModelReactionProtein()
            .withComplexRef("")
            .withModelReactionProteinSubunits(mrpsLists)
            .withNote(krxn.getImportedGpr()).withSource("SBML");
        krxn.getModelReactionProteins().add(mrp);
      }
      
//      System.out.println(krxn.getModelReactionProteins());
    }
  }
  
  public static void updateGpr2(FBAModel kmodel, Genome genome, BFunction<String, String> geneTransform) throws Exception {
    Map<String, String> badGpr = new HashMap<> ();
    Map<String, String> fix = getGprFix();
    System.out.println(kmodel.getGenomeRef());
    for (ModelReaction krxn : kmodel.getModelreactions()) {
      String gprString = krxn.getImportedGpr();
      if (fix.containsKey(gprString)) {
        gprString = fix.get(gprString);
        logger.debug("FIX: {} -> {}", krxn.getImportedGpr(), gprString);
        krxn.setImportedGpr(gprString);
      }
      if (!DataUtils.empty(gprString)) {
        Set<String> genes = KBaseUtils.getGenes(gprString, geneTransform);
        if (genes != null && !genes.isEmpty()) {
          List<ModelReactionProtein> mrpList = FBAModelFactory.setupModelReactionProteins(genes, genome, kmodel.getGenomeRef());
          krxn.setModelReactionProteins(mrpList);
        }
      }
    }
    for (String k : badGpr.keySet()) {
      logger.warn("[{}] Invalid GPR: {}", k, badGpr.get(k));
    }
  }
  
  public static Map<String, String> getGprFix() {
    Map<String, String> fix = new HashMap<> ();
    fix.put("?", "");
    fix.put("Cyan7425_4501)", "Cyan7425_4501");
    fix.put("Cyan8802_0780 and Cyan8802_2576 and Cyan8802_0781 and Cyan8802_2577 and (Cyan8802_2038 or Cyan8802_3891)and Cyan8802_0433 and Cyan8802_2537", 
            "Cyan8802_0780 and Cyan8802_2576 and Cyan8802_0781 and Cyan8802_2577 and (Cyan8802_2038 or Cyan8802_3891) and Cyan8802_0433 and Cyan8802_2537");
    fix.put("Mbar_A0818 or Mbar_A1889 )", "Mbar_A0818 or Mbar_A1889");
    fix.put("PCC8801_3195 and PCC8801_1209 and PCC8801_3577 and (PCC8801_2016 or PCC8801_3216 or PCC8801_3850 orPCC8801_2016) and PCC8801_3069 and PCC8801_0055 and PCC8801_1960 and PCC8801_4147 and PCC8801_1645 and PCC8801_3277 and PCC8801_0533 and PCC8801_4328 and PCC8801_2531 and PCC8801_3070 and PCC8801_3827 and PCC8801_4309 and PCC8801_3099 and PCC8801_2473", 
            "PCC8801_3195 and PCC8801_1209 and PCC8801_3577 and (PCC8801_2016 or PCC8801_3216 or PCC8801_3850 or PCC8801_2016) and PCC8801_3069 and PCC8801_0055 and PCC8801_1960 and PCC8801_4147 and PCC8801_1645 and PCC8801_3277 and PCC8801_0533 and PCC8801_4328 and PCC8801_2531 and PCC8801_3070 and PCC8801_3827 and PCC8801_4309 and PCC8801_3099 and PCC8801_2473");
    fix.put("( Cbei_0661??Cbei_2182 )", "Cbei_2182");
    fix.put("( Cbei_1559??or Cbei_2856??or Cbei_4512 )", "Cbei_4512");
    fix.put("( MA0455  or  MA4392  or  ( MA1616  and  MA0456 )  or  ( MA4391 )  or  MA1617  ana  MA4379 )", 
            "( MA0455  or  MA4392  or  ( MA1616  and  MA0456 )  or  ( MA4391 )  or  MA1617  and  MA4379 )");
    
    fix.put("RSP_2827 and RSP_1977and RSP_1976", "RSP_2827 and RSP_1977 and RSP_1976");
    fix.put("(RSP_4327 or RSP_4346) and ?", "RSP_4327 or RSP_4346");
    fix.put("RSP_1763 and RSP_1761 and RSP_4333)", "RSP_1763 and RSP_1761 and RSP_4333");
    fix.put("RSP_4299 and ?", "RSP_4299");
    return fix;
  }
  


  public static Map<String, Set<String>> getReactionRoles() {
    Map<String, Set<String>> result = new HashMap<> ();
//    Resource roleJson = new FileSystemResource("/var/biodb/modelseed/Roles.json");
    JsonModelSeedRoleDao roleDao = null; //new JsonModelSeedRoleDao(roleJson);
    for (String k : roleDao.data.keySet()) {
      ModelSeedRole role = roleDao.data.get(k);
      for (String rxn : role.reactions) {
        rxn = rxn.split(";")[0].trim();
        //    System.out.println(rxn + " " + k); 
        if (!result.containsKey(rxn)) {
          result.put(rxn, new HashSet<String> ());
        }
        result.get(rxn).add(k);
      }
      //  System.out.println(k + " " + role.reactions.size());

    }
    return result;
  }
  
  public static Set<String> annotationSplit(String str) {
    Set<String> result = new HashSet<> ();
    for (String s : str.split(" @ |; | / ")) {
      result.add(s.trim());
    }
    
    if (result.size() != 1) {
      logger.trace("split! {} - {}", result, str);
    }
    
    return result;
  };
  
  public static Map<String, Set<String>> getFunctionToRxn() {
    Map<String, Set<String>> rmap = KbaseGenomeUtils.getRolesMap();
    Map<String, Set<String>> roles = getReactionRoles();
    Map<String, Set<String>> roleToRxn = new HashMap<> ();
    Map<String, Set<String>> functionToRxn = new HashMap<> ();
    for (String rxn : roles.keySet()) {
      for (String r : roles.get(rxn)) {
        if (!roleToRxn.containsKey(r)) {
          roleToRxn.put(r, new HashSet<String> ());
        }
        roleToRxn.get(r).add(rxn);
      }
    }
    
    for (String function : rmap.keySet()) {
      Set<String> rs = rmap.get(function);
      Set<String> frxns = new HashSet<> ();
      for (String r : rs) {
        Set<String> mseedRxns = roleToRxn.get(r);
        if (mseedRxns != null && !mseedRxns.isEmpty()) {
          frxns.addAll(mseedRxns);
        }
      }
      if (!frxns.isEmpty()) {
        functionToRxn.put(function, frxns);
        logger.debug("{}: {}", function, frxns);
      } else {
        logger.debug("no reactions assigned to: {}", function);
      }
    }
    logger.info("loaded {} functions!", functionToRxn.size());
    
    return functionToRxn;
  }
  

  
  public static Set<String> getMetabolic(Genome a, Map<String, Set<String>> functionToRxn) {
    Set<String> metabolic = new HashSet<> ();
    
    for (Feature f : a.getFeatures()) {
      String function = f.getFunction();
      if (function == null) {
        function = "hypothetical protein";
      }
      Set<String> s1 = functionToRxn.get(function);
      String f_ = function.trim().toLowerCase();
      Set<String> fs = annotationSplit(f_);
      Set<String> s2 = new HashSet<> ();
      for (String ff : fs) {
        Set<String> s2_ = functionToRxn.get(ff);
        if (s2_ != null) {
          s2.addAll(s2_);
        }
      }
      
      if (s1 == null && s2.isEmpty()) {
        logger.trace(function);
      } else {
        metabolic.add(f.getId());
      }
    }
    
    logger.info("loaded {}/{} metabolic funcions!", metabolic.size(), a.getFeatures().size());
    return metabolic;
  }
  
  public static void list16SGenomes() {
    try {
      KBaseAPI prodAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigProd(), true);
      List<KBaseId> kids = prodAPI.listNarrative(PROD_RAST_GENOME, KBaseType.Genome);
      Map<String, Map<String, Object>> db = new HashMap<> ();
      for (KBaseId kid : kids) {
        if (kid.name.endsWith(".rast")) {
          Genome genome = prodAPI.getGenome(kid.name, kid.workspace);
          db.put(kid.name, new HashMap<String, Object> ());
          Map<String, Object> data = db.get(kid.name);
          data.put("scientific_name", genome.getScientificName());
          Map<String, Long> sizes = new HashMap<> ();
          Map<String, String> gfunctions = new HashMap<> ();
          Map<String, String> gseq = new HashMap<> ();
          System.out.println(genome.getScientificName());
          Object cs = genome.getCloseGenomes();
          System.out.println(cs);
          if (cs != null) {
            
//            for (Contig c : genome.getContigs()) {
//              System.out.println(c);
//            }
          }

          for (Feature f : genome.getFeatures()) {
          }
          long high = 0;
          String best = null;
          for (String k : sizes.keySet()) {
            long s = sizes.get(k);
            if (s > high) {
              high = s;
              best = k;
            }
          }
          if (best != null) {
            logger.info("{} [{}]: {}", best, high, gfunctions.get(best));

            data.put("feature", best);
            data.put("function", gfunctions.get(best));
            data.put("size", high);
            data.put("seq", gseq.get(best));
          }
//          break;
        }
      }
      
      for (String g : db.keySet()) {
        Map<String, Object> data = db.get(g);
        if (data != null && data.get("seq") == null) {
          logger.warn("[{}]{}", g, data.get("scientific_name"));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  


  
  public static void listRefGenomes() {
    try {
      KBaseAPI prodAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigProd(), true);
      List<KBaseId> kids = prodAPI.listNarrative(PROD_RAST_GENOME, KBaseType.Genome);
      Set<String> rast = new HashSet<> ();
      Set<String> genomes = new HashSet<> ();
      for (KBaseId kid : kids) {
        if (kid.name.endsWith(".rast")) {
          rast.add(kid.name);
        } else {
          genomes.add(kid.name);
        }
      }

      EasyKBase kBase = new EasyKBase(prodAPI);
      for (String g : genomes) {
        String rastG = g.concat(".rast");
        if (!rast.contains(rastG)) {
          Genome genome = prodAPI.getGenome(g, PROD_RAST_GENOME);
          kBase.annotateGenome(PROD_RAST_GENOME, g, rastG);
        } else {
          logger.warn("skip: {}", g);
        }
      }
//      Object genome = KBaseIOUtils.getObject("GCF_000005845.2", PROD_RAST_GENOME, null, prodAPI.getWorkspaceClient());
//      Genome a = prodAPI.getGenome("GCF_000005845.2.rast2", PROD_RAST_GENOME);
//
//      Map<String, Set<String>> functionToRxn = getFunctionToRxn();
//      Set<String> metabolic = getMetabolic(a, functionToRxn);

//      
//      kBase.annotateGenome(PROD_RAST_GENOME, "GCF_000005845.2", "GCF_000005845.2.rast2");
//      System.out.println(r);

//      GenomeAnnotationAPIClient gaClient = new GenomeAnnotationAPIClient(prodAPI.callbackURL, prodAPI.authToken);
//      SaveOneGenomeParamsV1 params = new SaveOneGenomeParamsV1().withData(a).withWorkspace(PROD_RAST_GENOME).withName("genome");
////      gaClient.sav
//      gaClient.saveOneGenomeV1(params);
//      Genome genome = null; //prodAPI.getGenome("GCF_000008805.1", "ReferenceDataManager");
//      genome = prodAPI.getGenome("GCF_000005845.2", PROD_RAST_GENOME);
      
//      System.out.println(genome.getTaxonRef());
//      System.out.println(genome.getAssemblyRef());
//      System.out.println(genome.getGenbankHandleRef());
      
//      {
//        Object o = KBaseIOUtils.getObject(null, null, genome.getTaxonRef(), prodAPI.getWorkspaceClient());
//        try {
//          KBaseId kid = KBaseIOUtils.saveData("taxon", "KBaseGenomeAnnotations.Taxon", o, PROD_RAST_GENOME, prodAPI.getWorkspaceClient());
//          System.out.println(kid);
//        } catch (Exception e) {
//          throw new IOException(e);
//        }
//      }
//      {
//        Object o = KBaseIOUtils.getObject(null, null, genome.assembly_ref, prodAPI.getWorkspaceClient());
//        try {
//          KBaseId kid = KBaseIOUtils.saveData("assembly", "KBaseGenomeAnnotations.Assembly", o, PROD_RAST_GENOME, prodAPI.getWorkspaceClient());
//          System.out.println(kid);
//        } catch (Exception e) {
//          throw new IOException(e);
//        }
//      }
      
//      genome.set = "23373/9/1";
//      genome.taxon_ref = "23373/8/1";
      
//      KBaseIOUtils.getObject(null, null, genome.assembly_ref, prodAPI.getWorkspaceClient());
//      KBaseIOUtils.getObject(null, null, genome.genbank_handle_ref, prodAPI.getWorkspaceClient());
      
//      for (KBaseGenome.Feature f : genome.features) {
//        System.out.println(f);
//      }
//      
//      for (KBaseGenome.Cds cds : genome.cdss) {
//        System.out.println(cds.ontology_terms);
//      }
      
//      prodAPI.saveObject("GCF_000005845.2", PROD_RAST_GENOME, KBaseType.Genome.value(), genome);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void updateModelGpr() {
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
    
    Map<String, String> defaultBiomass = JoanaConstants.getModelDefaultBiomassReaction();
    try {
      KBaseAPI devAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigDev(), true);
      KBaseAPI prodAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigProd(), false);
      
      List<KBaseId> qrast = prodAPI.listNarrative(PROD_RAST_GENOME, KBaseType.Genome);
      Map<String, KBaseId> rastGenomes = new HashMap<> ();
      for (KBaseId kid : qrast) {
        if (kid.name.endsWith(".rast")) {
          rastGenomes.put(kid.name.replace(".rast", ""), kid);
        }
      }
      
      List<KBaseId> q1 = devAPI.listNarrative(DEV_PUBLISHED_MODEL_REPO, "KBaseFBA.FBAModel");
      List<KBaseId> q2 = prodAPI.listNarrative(PROD_PUBLISHED_MODEL_REPO, "KBaseFBA.FBAModel");
//      q2.clear();
//      q2.add(new KBaseId("iCM925", PROD_PUBLISHED_MODEL_REPO, null));
      for (KBaseId kid : q2) {
        System.out.println(kid);
        try {
          FBAModel kmodel = KBaseIOUtils.getObject(kid.name, PROD_PUBLISHED_MODEL_REPO, null, FBAModel.class, prodAPI.wsClient);
          if (kmodel.getGenomeRef() != null) {
            KBaseId gkid = new KBaseId(null, null, kmodel.getGenomeRef());
            Genome genome = prodAPI.getGenome(gkid);
            System.out.println(genome.getId() + " " + kmodel.getGenomeRef());
            if (rastGenomes.containsKey(genome.getId()) && 
                rastGenomes.get(genome.getId()).reference != null) {
              logger.info("swap to rast genome ...");
              kmodel.setGenomeRef(rastGenomes.get(genome.getId()).reference);
            }
            
            FBAModelAdapter adapter = new FBAModelAdapter(kmodel);
            if (defaultBiomass.containsKey(kmodel.getId())) {
              String biomassEntry = defaultBiomass.get(kmodel.getId());
              logger.info("default biomass: {}", biomassEntry);
              adapter.convertToBiomass(biomassEntry);
            }
            BFunction<String, String> gtrans = modelGeneTransformers.get(kmodel.getId());
            updateGpr2(kmodel, genome, gtrans);
            System.out.println(adapter.getBiomasses());
            KBaseIOUtils.saveData(kid.name, KBaseType.FBAModel.value(), kmodel, PROD_PUBLISHED_MODEL_REPO, prodAPI.wsClient);
          }
          
        } catch (Exception e) {
          e.printStackTrace();
        }
//        break;
      }
      
//      FBAModel kmodel = KBaseIOUtils.getObject("Ec_core_flux1", DEV_PUBLISHED_MODEL_REPO, null, FBAModel.class, devAPI.wsClient);
//      kmodel.setGenomeRef("23373/5/2");

      
//      KBaseIOUtils.saveData("Ec_core_flux1", KBaseType.FBAModel.value(), kmodel, PROD_PUBLISHED_MODEL_REPO, prodAPI.wsClient);
//      prodAPI.wsClient.sa
//      System.out.println(kmodel);
//      KBaseIOUtils.getObject("Ec_core_flux1", ws, ref, devAPI.wsClient);
      List<KBaseId> q3 = prodAPI.listNarrative(PROD_RAST_GENOME, "KBaseGenomes.Genome");
      for (KBaseId kid : q3) {
//        System.out.println(kid);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void getModel(KBaseAPI prodAPI) throws Exception {
    KBaseSbmlImporter.DATA_EXPORT_PATH = FileImportKb.EXPORT_PATH;
    
    Map<String, BFunction<String, String>> modelGeneTransformers = 
        ManualFixes.getModelGeneTransformers();
    Map<String, Map<String, String>> gswap = 
        ManualFixes.getGprSwap();
    
    
    String model = "Ec_core_flux1";
//    model = "iCM925";
//    model = "iJO1366_bigg2";
    FBAModel kmodel = KBaseIOUtils.getObject(model, PROD_PUBLISHED_MODEL_REPO, null, FBAModel.class, prodAPI.wsClient);
//    FBAModel kmode2 = KBaseIOUtils.getObject("Ec_core_flux1.kbase", PROD_ECOLI_CORES, null, FBAModel.class, prodAPI.wsClient);
    FBAModelAdapter ma = new FBAModelAdapter(kmodel);
    KBaseGeneIntegration geneIntegration = new KBaseGeneIntegration(prodAPI.getWorkspaceClient(), prodAPI.dfuClient, prodAPI.getKBSolrUtilClient());
    geneIntegration.geneTransformer = modelGeneTransformers.get(kmodel.getId());
    geneIntegration.searchGenome(kmodel);
    
    KBaseIntegrationReport kir = new KBaseIntegrationReport();
    kir.model = kmodel.getId();
    kir.objName = kmodel.getName();
    
    kir.fillGenomeData(geneIntegration);
    
//    System.out.println(geneIntegration.report.getUnmappedGenes());
//    System.out.println(geneIntegration.report.bestScore);
//    System.out.println(geneIntegration.report.features);
//    System.out.println(geneIntegration.report.matchScores);
//    System.out.println(geneIntegration.report.matchOrganism);
//    System.out.println(geneIntegration.report.getUnmappedFeatures());
    
//    CompareFbaModel compareFbaModel = new CompareFbaModel(kmodel, kmode2);
//    compareFbaModel.compare();
    
    KBaseIntegration integration = new KBaseIntegration(kmodel);
    integration.report = kir;
//    integration.biodbContainer = new KBaseBiodbContainer(KBaseSbmlImporter.DATA_EXPORT_PATH);
//    integration.
    integration.compartmentMapping.put("z0", "e");
    integration.compartmentMapping.put("z1", "c");
    integration.rename = "modelseed";
    integration.fillMetadata = true;
    integration.integrate();
    
    ma.setTemplate("gramneg", prodAPI.getWorkspaceClient());
//    Media media = ma.convertToMedia(kmodel.getId() + ".media", ma.getDefaultDrains());
    Media media = integration.defaultMedia;
    KBaseIOUtils.saveData(kmodel.getId() + ".media", KBaseType.KBaseBiochemMedia, media, PROD_ECOLI_CORES, prodAPI.getWorkspaceClient());
    
    
//    KBaseGenomeReport report = geneIntegration.report;
//    System.out.println(report.bestMatch);
//    System.out.println(report.bestScore);
//    System.out.println(report.matchScores);
//    System.out.println(report.matchOrganism);
    for (ModelReaction o : kmodel.getModelreactions()) {
      
      if (o.getReactionRef().startsWith("50/1/1")) {
        String rxnEntry = FBAModelAdapter.getEntryFromRef(o.getReactionRef());
        o.setReactionRef("~/template/reactions/id/" + rxnEntry);
      }
      
//      System.out.println(o);
//      System.out.println(o.getReactionRef());
//
//      System.out.println(o.getModelcompartmentRef());
//      System.out.println(o.getImportedGpr());

      
//      ma.getGpr(arg0)
//      for (ModelReactionReagent rg : o.getModelReactionReagents()) {
//        System.out.println("\t" + rg);
//      }
    }
    
//    System.out.println(kir);
    
    kir.fillModelData(integration.adapter);
    KBaseIOUtils.writeStringFile(KBaseIOUtils.toJson(kir), "/opt/nginx-1.9.6/html/biosynth-web-biobase/exports/model-report/data.json");
    
//    if (report.bestGenomeKID.size() == 1) {
//      KBaseId genomeKid = report.bestGenomeKID.iterator().next();
//      Genome g = prodAPI.getGenome(genomeKid);
////      KbaseGenomeUtils.integrateGenes(kmodel, g, null, geneIntegration.geneTransformer);
//
//    }
    
    //      for (ModelCompartment cmp : kmodel.getModelcompartments()) {
    //        System.out.println(cmp);
    //      }
    



//
//    for (ModelCompartment o : kmodel.getModelcompartments()) {
//      System.out.println(o);
//    }
//    for (ModelCompound o : kmodel.getModelcompounds()) {
//      System.out.println(o);
//    }



    //      long id = integration.biodbContainer.biodbService.getIdByEntryAndDatabase("cpd02557", "ModelSeed");
    //      System.out.println(id + " " + integration.biodbContainer.biodbService.getFormulaPropertyById(id));
    //      System.out.println(id + " " + integration.biodbContainer.biodbService.getEntityProperty(id, "name"));
    //      System.out.println(id + " " + integration.biodbContainer.biodbService.getEntityProperty(id, "formula"));
    //      System.out.println(id + " " + integration.biodbContainer.biodbService.getEntityProperty(id, "smiles"));
    //      System.out.println(id + " " + integration.biodbContainer.biodbService.getEntityProperty(id, "inchikey"));

//    KBaseIOUtils.saveData(kmodel.getId() + ".integrated", KBaseType.FBAModel, kmodel, PROD_ECOLI_CORES, prodAPI.wsClient);
    //      FBAModelAdapter madapter = new FBAModelAdapter(kmodel);

    //      System.out.println(media);
    //      


    //      System.out.println(CollectionUtils.print(madapter.getDefaultDrains()));
    //      System.out.println(madapter.kspiMap.get("M_for_e").getDblinks());

    //      Media media = KBaseIOUtils.getObject("Carbon-D-Glucose", "filipeliu:narrative_1503957402064", null, Media.class, prodAPI.wsClient);
    //      {
    //        Media m5 = MediaAdapter.buildDefaultEmptyMedia("Carbon-D-Glucose_5");
    //        for (MediaCompound mc : media.getMediacompounds()) {
    //          MediaAdapter ma = new MediaAdapter(m5);
    //          ma.addMediaCompound(mc);
    //        }
    //        KBaseIOUtils.saveData("Carbon-D-Glucose_5", KBaseType.KBaseBiochemMedia, m5, "filipeliu:narrative_1503957402064", prodAPI.getWorkspaceClient());
    //      }
    //      {
    //        Media m10 = MediaAdapter.buildDefaultEmptyMedia("Carbon-D-Glucose_10");
    //        MediaAdapter ma = new MediaAdapter(m10);
    //        for (MediaCompound mc : media.getMediacompounds()) {
    //          ma.addMediaCompound(mc);
    //        }
    //        ma.setUptake("cpd00027", 10);
    //        KBaseIOUtils.saveData("Carbon-D-Glucose_10", KBaseType.KBaseBiochemMedia, m10, "filipeliu:narrative_1503957402064", prodAPI.getWorkspaceClient());
    //      }
    //      {
    //        Media m20 = MediaAdapter.buildDefaultEmptyMedia("Carbon-D-Glucose_20");
    //        MediaAdapter ma = new MediaAdapter(m20);
    //        for (MediaCompound mc : media.getMediacompounds()) {
    //          ma.addMediaCompound(mc);
    //        }
    //        ma.setUptake("cpd00027", 20);
    //        KBaseIOUtils.saveData("Carbon-D-Glucose_20", KBaseType.KBaseBiochemMedia, m20, "filipeliu:narrative_1503957402064", prodAPI.getWorkspaceClient());
    //      }

    //      System.out.println(media);
    //templateRef=12998/2/1 iCac802.rast.nofill
    //      FBAModel kmodel2 = KBaseIOUtils.getObject("iCac802.rast.nofill", "filipeliu:narrative_1503957402064", null, FBAModel.class, prodAPI.wsClient);
    //      System.out.println(kmodel2);
  }
  
  public static void pmodel(KBaseAPI prodAPI) {
    EasyKBase kbase = new EasyKBase(prodAPI);
    String ws = "filipeliu:narrative_1504192868437";
    //
    try {
//      kbase.annotateGenome(ws, "GCF_000010485.1", "GCF_000010485.1.rast");
      kbase.propagateModelToNewGenome("Ec_core_flux1.kbase", ws, "ECOLI_SE15", ws, "EcSE15_core_flux1.prop", ws);
      kbase.propagateModelToNewGenome("Ec_core_flux1.kbase", ws, "ECOLI_DH10B", ws, "EcDH10B_core_flux1.prop", ws);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void propagationKernel(KBaseAPI prodAPI) throws IOException {
    NAlignTool tool = new NAlignTool(KbaseGenomeUtils.NUC44);
    InputStream is = new FileInputStream("/home/fliu/workspace/java/kbase-SBMLTools-auth2/data/blast_db_latest.faa");
    Map<String, DNASequence> seqs = FastaReaderHelper.readFastaDNASequence(is);
    Map<String, Genome> seqsGenome = new HashMap<> ();
    Map<String, Set<String>> genomeToModels = new HashMap<> ();
    for (String k : seqs.keySet()) {
      String h[] = seqs.get(k).getOriginalHeader().split("\\|");
      logger.info("OriginalHeader: {}", Arrays.toString(h));
      Set<String> models = new HashSet<> (Arrays.asList(h[3].split(";")));
      genomeToModels.put(k, models);
//      Genome rgenome = prodAPI.getGenome(k, PROD_RAST_GENOME);
//      seqsGenome.put(k, rgenome);
    }
    
    Map<String, String> genomeToRast = new HashMap<> ();
    for (KBaseId kid : prodAPI.listNarrative(PROD_1000_RAST_GENOMES, KBaseType.Genome)) {
      if (kid.name.contains(".RAST")) {
        String base = kid.name.substring(0, kid.name.length() - ".RAST".length());
        genomeToRast.put(base, kid.name);
      }
    }
    List<KBaseId> q = prodAPI.listNarrative(PROD_1000_MODELS, KBaseType.FBAModel);
    
    Dataset<String, String, Object> gdata = new Dataset<>();
    
    for (KBaseId kid : q) {
      String genomeName = kid.name.replace(".mdl", "");
      if (genomeToRast.containsKey(genomeName)) {
        logger.info("found RAST genome: {}", genomeToRast.get(genomeName));
        Genome kgenome = prodAPI.getGenome(genomeToRast.get(genomeName), PROD_1000_RAST_GENOMES);
        gdata.add(genomeName, "sn", kgenome.getScientificName());
        gdata.add(genomeName, "lineage", kgenome.getTaxonomy());
        System.out.println(kgenome.getScientificName());
        Feature poly = null;
        if (kgenome != null && 
            (poly = KbaseGenomeUtils.findRnaPolymeraseBetaUnit(kgenome)) != null) {
          logger.info("yes !");
          String dnaA = poly.getDnaSequence();
          gdata.add(genomeName, "seqL", poly.getDnaSequenceLength());
          gdata.add(genomeName, "function", poly.getFunction());
          gdata.add(genomeName, "locus", poly.getId());

            
          AlignmentKernel ma = new AlignmentKernel(tool);
            
//            DNASequence seqA = new DNASequence(dnaA);
            List<String> o = new ArrayList<> ();
            for (String k : seqs.keySet()) {
//              Genome rgenome = seqsGenome.get(k);
//              @SuppressWarnings("unchecked")
//              PairwiseSequenceAligner<DNASequence, NucleotideCompound> psa = (PairwiseSequenceAligner<DNASequence, NucleotideCompound>) tool.localAlignment(dnaA, seqs.get(k).getSequenceAsString());

//              o.add(StringUtils.join(data, '\t'));
              DNASequence s = seqs.get(k);
              System.out.println(s.getOriginalHeader());
              AlignmentJob job = new AlignmentJob();
              job.dna1 = dnaA;
              job.dna2 = s.getSequenceAsString();
              job.targetOrganism = seqs.get(k).getOriginalHeader().split("\\|")[2];
              System.out.println(job.targetOrganism);
              ma.jobs.add(job);
            }
            ma.run();
            
            Map<Double, Set<AlignmentJob>> sortedResults = ma.getSortedResults();
            for (Double score : sortedResults.keySet()) {
              for (AlignmentJob job : sortedResults.get(score)) {
                System.out.println(score + ", " + job.genome2);
              }
            }
//              
//              DNASequence seqB = seqs.get(k);
//              PairwiseSequenceAligner<DNASequence, NucleotideCompound> psa = tool.lalign(seqA, seqB);
//              List<Object> data = new ArrayList<> ();
//              int length = psa.getPair().getLength();
//              

//              
//            }
            
            
            
//            System.out.println("G!");
//            o = new ArrayList<> ();
//            for (String k : seqs.keySet()) {
//              Genome rgenome = prodAPI.getGenome(k, PROD_RAST_GENOME);
//              DNASequence seqB = seqs.get(k);
//              PairwiseSequenceAligner<DNASequence, NucleotideCompound> psa = tool.galign(seqA, seqB);
//              List<Object> data = new ArrayList<> ();
//              int length = psa.getPair().getLength();
//              
//              data.add(k);
//              data.add(rgenome.getScientificName());
////              data.add(modelTaxaEntry);
////              data.add(distance);
////              data.add(txId);
////              data.add(biodbService.getEntryById(txId));
////              data.add(bb);
////              data.add(aaSeqEntry);
////              data.add(modelSeqId);
//              data.add(psa.getPair().getNumIdenticals());
//              data.add(psa.getPair().getNumSimilars());
//              data.add(psa.getPair().getLength());
//              data.add((double) psa.getPair().getNumIdenticals() / length);
//              data.add((double) psa.getPair().getNumSimilars() / length);
//              data.add(psa.getSimilarity());
//              data.add(psa.getDistance());
//              o.add(StringUtils.join(data, '\t'));
//            }
//            System.out.println(StringUtils.join(o, '\n'));
//            DNASequence seqB = new DNASequence(dnaA);

//            double psa.get
//            psa.getProfile().get

//          tool.galign(seqs);
        } else {
          logger.warn("polymerase not found {}", poly);
        }
      } else {
        logger.warn("no RAST genome: {}", genomeName);
      }
//      Genome kgenome = prodAPI.getGenome(genomeName, PROD_QZ_GENOMES);

      //get genome
      //get seq method
      //local align
      //best hit
      //proteome compare
      //propragate
      //get genes ! (pmodel)
      //get genes ! (kmodel)
    }
    
    pt.uminho.sysbio.biosynthframework.DataUtils.printData(gdata.dataset, "gid");

    logger.info("FBAModels: {}", q.size());
  }
  

  
  public static void main(String[] args) {
    try {
      KBaseAPI prodAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigProd(), true);
      
//      pmodel(prodAPI);
//      BMap<String, String> modelToGenome = KbaseGenomeUtils.getModelGenomeAssignment("/home/fliu/workspace/java/kbase-SBMLTools-auth2/data/sbml_genome_map.tsv");
//      KbaseGenomeUtils.buildBlastDb("/home/fliu/workspace/java/kbase-SBMLTools-auth2/data/blast_db_latest.faa", modelToGenome, LOGIN_TOKEN, PROD_RAST_GENOME);
//      propagationKernel(prodAPI);
//      localIntegraiton();
//      updateModelGpr();
//      getModel(prodAPI);
    } catch (Exception e) {
      e.printStackTrace();
    }
    


//    listRefGenomes();
    

//    list16SGenomes();

  }



}

