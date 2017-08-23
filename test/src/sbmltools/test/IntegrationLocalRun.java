package sbmltools.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbasefba.FBAModel;
import kbasefba.ModelReaction;
import kbasefba.ModelReactionProtein;
import kbasefba.ModelReactionProteinSubunit;
import kbsolrutil.KBaseAPI;
import pt.uminho.ceb.biosystems.mew.biocomponents.container.components.GeneReactionRuleCI;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTree;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.parser.TokenMgrError;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseGenome;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseId;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelIntegrationFacade;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseSbmlImporter;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseUtils;
import sbmltools.CompartmentMapping;
import sbmltools.IntegrateModelParams;
import sbmltools.KBaseType;
import sbmltools.SBMLToolsClient;
import us.kbase.workspace.WorkspaceIdentity;

public class IntegrationLocalRun {
  
  private static final Logger logger = LoggerFactory.getLogger(IntegrationLocalRun.class);
  
  public static void localIntegraiton() {
    String LOGIN_TOKEN = "MO2FCAGI3TLEM3PPRZZ4KH4ZKBJEMRGO";
    try {
      KBaseAPI devAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigDev(), true);
      String modelWorkspace = "filipeliu:narrative_1502474753893"; //repo
      List<KBaseId> fbaModelQuery = devAPI.listNarrative(modelWorkspace, "KBaseFBA.FBAModel");
      KBaseId kid = new KBaseId("Ec_core_flux1", "filipeliu:narrative_1502474753893", "7427/6/15");
//      FBAModel kmodel = devAPI.getWorkspaceObject(kid.name, modelWorkspace, FBAModel.class);
      
      List<KBaseId> fbaModelQuery2 = devAPI.listNarrative("filipeliu:narrative_1502474753893", "KBaseGenomes.Genome");
      //"   filipeliu:narrative_1502474753893"
      for (KBaseId kid_ : fbaModelQuery2) {
        System.out.println(kid_);
      }
      List<CompartmentMapping> cmap = new ArrayList<CompartmentMapping>();
      cmap.add(new CompartmentMapping().withKbaseCompartmentId("c").withModelCompartmentId(Arrays.asList(new String[]{"z1"})));
      cmap.add(new CompartmentMapping().withKbaseCompartmentId("e").withModelCompartmentId(Arrays.asList(new String[]{"z0"})));
      KBaseModelIntegrationFacade integration = 
          new KBaseModelIntegrationFacade(devAPI.wsClient, devAPI.dfuClient, null, null, "/var/biobase/export");
      integration.kbaseIntegrate(
          new IntegrateModelParams().withModelName(kid.name)
          .withCompartmentTranslation(cmap)
          .withGenomeId("GCF_000022605.2_ASM2260v1_genomic")
          .withWorkspaceName(modelWorkspace)
          .withOutputModelName("kb." + kid.name)
          .withFillMetadata(1L)
          .withTranslateDatabase("KEGG")
          .withRemoveBoundary(1L)
          .withBiomassReactions("")
          .withCreateExtracellular(1L)
          .withGeneMappings("")
          .withCompoundMappings("")
          .withTemplateId(""),
          modelWorkspace);
      
      
      KBaseSbmlImporter.CURATION_DATA = "/var/biobase/integration/cc/cpd_curation.tsv";
      KBaseSbmlImporter.LOCAL_CACHE = "/tmp/argonne";
      KBaseSbmlImporter.REPORT_OUTPUT_PATH = "/opt/nginx-1.9.6/html/biosynth-web-biobase/exports/model-integration-report/readerData.json";
      KBaseSbmlImporter.DATA_EXPORT_PATH = "/var/biobase/export";
      KBaseSbmlImporter sbmlTools = new KBaseSbmlImporter(modelWorkspace, devAPI.dfuClient);
//      sbmlTools.
//      RASTSDKClient rastClient = new RASTSDKClient(devAPI.callbackURL, devAPI.authToken);
//      rastClient.annotateGenome(new AnnotateGenomeParams());
////      rastClient.get
      SBMLToolsClient stools = new SBMLToolsClient(devAPI.callbackURL, devAPI.authToken);
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
  
  public static String  DEV_PUBLISHED_MODEL_REPO = "filipeliu:narrative_1502428739293";
  public static String PROD_PUBLISHED_MODEL_REPO = "filipeliu:narrative_1502913538729";
  public static String PROD_RAST_GENOME = "filipeliu:narrative_1502913563238";
  public static String PROD_GENOME = "filipeliu:narrative_1502913538729";
  
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
  
  public static void updateGpr2(FBAModel kmodel) throws Exception {
    Map<String, String> badGpr = new HashMap<> ();
    Map<String, String> fix = getGprFix();
    for (ModelReaction krxn : kmodel.getModelreactions()) {
      String gprString = krxn.getImportedGpr();
      if (fix.containsKey(gprString)) {
        gprString = fix.get(gprString);
        logger.debug("FIX: {} -> {}", krxn.getImportedGpr(), gprString);
        krxn.setImportedGpr(gprString);
      }
      try {
        GeneReactionRuleCI grrci = new GeneReactionRuleCI(gprString);
        AbstractSyntaxTree<?, ?> rule = grrci.getRule();
        Set<String> genes = KBaseUtils.getGenes(gprString);
        if (genes != null) {
          krxn.getModelReactionProteins().clear();
          List<ModelReactionProteinSubunit> mrpsLists = new ArrayList<> (); 
          List<String> features = new ArrayList<> ();
          for (String g : genes) {
            features.add(String.format("%s/features/id/%s", kmodel.getGenomeRef(), g));
          }

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
      } catch (Exception | TokenMgrError e) {
        badGpr.put(krxn.getId(), gprString);
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
  
  public static void listRefGenomes() {
    String LOGIN_TOKEN = "MO2FCAGI3TLEM3PPRZZ4KH4ZKBJEMRGO";
    try {
      KBaseAPI prodAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigProd(), false);

      KBaseGenome genome = null; //prodAPI.getGenome("GCF_000008805.1", "ReferenceDataManager");
      genome = prodAPI.getGenome("GCF_000005845.2", PROD_RAST_GENOME);
      
      System.out.println(genome.taxon_ref);
      System.out.println(genome.assembly_ref);
      System.out.println(genome.genbank_handle_ref);
      
      {
        Object o = KBaseIOUtils.getObject(null, null, genome.taxon_ref, prodAPI.getWorkspaceClient());
        try {
          KBaseId kid = KBaseIOUtils.saveData("taxon", "KBaseGenomeAnnotations.Taxon", o, PROD_RAST_GENOME, prodAPI.getWorkspaceClient());
          System.out.println(kid);
        } catch (Exception e) {
          throw new IOException(e);
        }
      }
//      {
//        Object o = KBaseIOUtils.getObject(null, null, genome.assembly_ref, prodAPI.getWorkspaceClient());
//        try {
//          KBaseId kid = KBaseIOUtils.saveData("assembly", "KBaseGenomeAnnotations.Assembly", o, PROD_RAST_GENOME, prodAPI.getWorkspaceClient());
//          System.out.println(kid);
//        } catch (Exception e) {
//          throw new IOException(e);
//        }
//      }
      
      genome.assembly_ref = "23373/9/1";
//      genome.taxon_ref = "23373/8/1";
      
      KBaseIOUtils.getObject(null, null, genome.assembly_ref, prodAPI.getWorkspaceClient());
//      KBaseIOUtils.getObject(null, null, genome.genbank_handle_ref, prodAPI.getWorkspaceClient());
      
//      for (KBaseGenome.Feature f : genome.features) {
//        System.out.println(f);
//      }
//      
//      for (KBaseGenome.Cds cds : genome.cdss) {
//        System.out.println(cds.ontology_terms);
//      }
      prodAPI.saveGenome(genome.id, PROD_RAST_GENOME, genome);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void list() {
    String LOGIN_TOKEN = "MO2FCAGI3TLEM3PPRZZ4KH4ZKBJEMRGO";
    try {
      KBaseAPI devAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigDev(), false);
      KBaseAPI prodAPI = new KBaseAPI(LOGIN_TOKEN, KBaseAPI.getConfigProd(), false);
      
      List<KBaseId> q1 = devAPI.listNarrative(DEV_PUBLISHED_MODEL_REPO, "KBaseFBA.FBAModel");
      List<KBaseId> q2 = prodAPI.listNarrative(PROD_PUBLISHED_MODEL_REPO, "KBaseFBA.FBAModel");
      for (KBaseId kid : q2) {
        System.out.println(kid);
        try {
          FBAModel kmodel = KBaseIOUtils.getObject(kid.name, PROD_PUBLISHED_MODEL_REPO, null, FBAModel.class, prodAPI.wsClient);
          updateGpr2(kmodel);
//          KBaseIOUtils.saveData(kid.name, KBaseType.FBAModel.value(), kmodel, PROD_PUBLISHED_MODEL_REPO, prodAPI.wsClient);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      
      FBAModel kmodel = KBaseIOUtils.getObject("Ec_core_flux1", DEV_PUBLISHED_MODEL_REPO, null, FBAModel.class, devAPI.wsClient);
//      kmodel.setGenomeRef("23373/5/2");

      
//      KBaseIOUtils.saveData("Ec_core_flux1", KBaseType.FBAModel.value(), kmodel, PROD_PUBLISHED_MODEL_REPO, prodAPI.wsClient);
//      prodAPI.wsClient.sa
      System.out.println(kmodel);
//      KBaseIOUtils.getObject("Ec_core_flux1", ws, ref, devAPI.wsClient);
      List<KBaseId> q3 = prodAPI.listNarrative(PROD_RAST_GENOME, "KBaseGenomes.Genome");
      for (KBaseId kid : q3) {
        System.out.println(kid);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
//    localIntegraiton();
//    list();
    listRefGenomes();
  }
}
