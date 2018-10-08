package sbmltools.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

import kbasefba.FBAModel;
import kbasefba.ModelCompartment;
import kbasefba.ModelCompound;
import kbasefba.ModelReaction;
import kbasefba.ModelReactionProtein;
import kbasefba.ModelReactionProteinSubunit;
import kbasefba.ModelReactionReagent;
import kbasegenomes.Genome;
import me.fxe.kbase.KBaseFBAModelFactory;
import me.fxe.kbase.KBaseModelAdapter;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.ReactionMajorLabel;
import pt.uminho.sysbio.biosynthframework.Metabolite;
import pt.uminho.sysbio.biosynthframework.ModelAdapter;
import pt.uminho.sysbio.biosynthframework.SubcellularCompartment;
import pt.uminho.sysbio.biosynthframework.integration.model.BaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.CompartmentDetectorKBase;
import pt.uminho.sysbio.biosynthframework.integration.model.CompartmentIntegration;
import pt.uminho.sysbio.biosynthframework.integration.model.IntegrationMap;
import pt.uminho.sysbio.biosynthframework.integration.model.KBaseIntegrationEngine;
import pt.uminho.sysbio.biosynthframework.integration.model.SimpleStringMatchEngine;
import pt.uminho.sysbio.biosynthframework.io.MetaboliteDao;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelIntegrationFacade;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseUtils;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelAdapter;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlReaction;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlSpecie;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import pt.uminho.sysbio.biosynthframework.util.IOUtils;
import pt.uminho.sysbio.ext.MethoBuilder;

public class JsonObjectTest {
  
  private static final Logger logger = LoggerFactory.getLogger(JsonObjectTest.class);
  
  public static void equals(String s1, String s2, String f) {
    if (s1 == null && s2 == null) {
      logger.debug("[{}] NULL", f);
    } else if (s1 == null && s2 != null) {
      logger.warn("[{}] {}, {}", f, s1, s2);
    } else {
      if (!s1.equals(s2)) {
        logger.warn("[{}] {}, {}", f, s1, s2);
      } else {
        logger.info("[{}] {}, {}", f, s1, s2);
      }
    }
  }
  
  public static<T> void equals(Collection<T> s1, Collection<T> s2, String f) {
    if (s1 == null && s2 == null) {
      logger.debug("[{}] NULL", f);
    } else if (s1 == null && s2 != null) {
      logger.warn("[{}] {}, {}", f, s1, s2);
    } else {
      if (s1.containsAll(s2) && s2.containsAll(s1) && s1.size() == s2.size()) {
        logger.info("[{}] {}, {}", f, s1, s2);
      } else {
        logger.warn("[{}] {}/{} {}, {}", f, s1.size(), s2.size(), s1, s2);
      }
    }
  }
  
  public static void equals(Double d1, Double d2, String f) {
    if (d1 == null && d2 == null) {
      logger.debug("[{}] NULL", f);
    } else {
      if (!d1.equals(d2)) {
        logger.warn("[{}] {}, {}", f, d1, d2);
      } else {
        logger.debug("[{}] {}, {}", f, d1, d2);
      }
    }
  }
  
  public static void equals(Long l1, Long l2, String f) {
    if (l1 == null && l2 == null) {
      logger.debug("[{}] NULL", f);
    } else {
      if (!l1.equals(l2)) {
        logger.warn("[{}] {}, {}", f, l1, l2);
      } else {
        logger.debug("[{}] {}, {}", f, l1, l2);
      }
    }
  }
  
  public static void equalsCompartments(FBAModel kmodel1, FBAModel kmodel2) {
    Map<String, ModelCompartment> m1 = new HashMap<>();
    Map<String, ModelCompartment> m2 = new HashMap<>();
    for (ModelCompartment kcmp : kmodel1.getModelcompartments()) {
      m1.put(kcmp.getId(), kcmp);
    }
    for (ModelCompartment kcmp : kmodel2.getModelcompartments()) {
      m2.put(kcmp.getId(), kcmp);
    }
    
    KBaseModelAdapter model1 = new KBaseModelAdapter(kmodel1);
    KBaseModelAdapter model2 = new KBaseModelAdapter(kmodel2);
    if (m1.size() != m2.size()) {
      logger.warn("[COMPARTMENTS] {}, {}", m1.size(), m2.size());
    } else {
      Set<String> ids1 = model1.getCompartmentIds();
      Set<String> ids2 = model2.getCompartmentIds();
      Set<String> both = Sets.intersection(ids1, ids2);
      if (both.size() != ids1.size()) {
        for (String u1 : Sets.difference(ids1, both)) {
          logger.warn("[COMPARTMENTS UNIQUE MODEL1] {} - {}", u1, m1.get(u1));
        }
        for (String u2 : Sets.difference(ids2, both)) {
          logger.warn("[COMPARTMENTS UNIQUE MODEL2] {} - {}", u2, m2.get(u2));
        }
      }
      for (String cmpId : both) {
        ModelCompartment kcmp1 = m1.get(cmpId);
        ModelCompartment kcmp2 = m2.get(cmpId);
        equals(kcmp1.getId(), kcmp2.getId(), "id");
        equals(kcmp1.getCompartmentRef(), kcmp2.getCompartmentRef(), "CompartmentRef");
        equals(kcmp1.getLabel(), kcmp2.getLabel(), "Label");
        equals(kcmp1.getPH(), kcmp2.getPH(), "PH");
        equals(kcmp1.getPotential(), kcmp2.getPotential(), "Potential");
        equals(kcmp1.getCompartmentIndex(), kcmp2.getCompartmentIndex(), "CompartmentIndex");
      }
    }
  }
  
  public static void equalsSpecies(FBAModel kmodel1, FBAModel kmodel2) {
    Map<String, ModelCompound> m1 = new HashMap<>();
    Map<String, ModelCompound> m2 = new HashMap<>();
    for (ModelCompound kspi : kmodel1.getModelcompounds()) {
      m1.put(kspi.getId(), kspi);
    }
    for (ModelCompound kspi : kmodel2.getModelcompounds()) {
      m2.put(kspi.getId(), kspi);
    }
    
    if (m1.size() != m2.size()) {
      logger.warn("[SPECIES] {}, {}", m1.size(), m2.size());
    } else {
      KBaseModelAdapter model1 = new KBaseModelAdapter(kmodel1);
      KBaseModelAdapter model2 = new KBaseModelAdapter(kmodel2);
      Set<String> ids1 = model1.getSpeciesIds();
      Set<String> ids2 = model2.getSpeciesIds();
      Set<String> both = Sets.intersection(ids1, ids2);
      if (both.size() != ids1.size()) {
        for (String u1 : Sets.difference(ids1, both)) {
          logger.warn("[SPECIES UNIQUE MODEL1] {} - {}", u1, m1.get(u1));
        }
        for (String u2 : Sets.difference(ids2, both)) {
          logger.warn("[SPECIES UNIQUE MODEL2] {} - {}", u2, m2.get(u2));
        }
      }
      for (String spiId : both) {
        ModelCompound kspi1 = m1.get(spiId);
        ModelCompound kspi2 = m2.get(spiId);
        equals(kspi1.getId(), kspi2.getId(), "id");
        equals(kspi1.getCompoundRef(), kspi2.getCompoundRef(), "CompoundRef");
        equals(kspi1.getDisplayID(), kspi2.getDisplayID(), "DisplayID");
        equals(kspi1.getFormula(), kspi2.getFormula(), "Formula");
        equals(kspi1.getInchikey(), kspi2.getInchikey(), "Inchikey");
        equals(kspi1.getModelcompartmentRef(), kspi2.getModelcompartmentRef(), "ModelcompartmentRef");
        equals(kspi1.getName(), kspi2.getName(), "Name");
        equals(kspi1.getSmiles(), kspi2.getSmiles(), "Smiles");
      }
    }
  }
  
  public static void equalsReactionProteinSubunit(ModelReactionProteinSubunit s1, ModelReactionProteinSubunit s2) {
    equals(s1.getTriggering(), s2.getTriggering(), "Triggering");
    equals(s1.getOptionalSubunit(), s1.getOptionalSubunit(), "OptionalSubunit");
    equals(s1.getRole(), s2.getRole(), "Role");
    equals(s1.getNote(), s2.getNote(), "Note");
    equals(s1.getFeatureRefs(), s2.getFeatureRefs(), "FeatureRefs");
  }
  public static void equalsReactionProtein(ModelReactionProtein p1, ModelReactionProtein p2) {
    List<ModelReactionProteinSubunit> l1 = new ArrayList<>();
    List<ModelReactionProteinSubunit> l2 = new ArrayList<>();
    for (ModelReactionProteinSubunit s : p1.getModelReactionProteinSubunits()) {
      l1.add(s);
    }
    for (ModelReactionProteinSubunit s : p2.getModelReactionProteinSubunits()) {
      l2.add(s);
    }
    if (l1.size() != l2.size()) {
      logger.warn("[REACTION PROTEIN SUBUNIT] {}, {}", l1.size(), l2.size());
    }
    equals(p1.getComplexRef(), p2.getComplexRef(), "ComplexRef");
    equals(p1.getNote(), p2.getNote(), "Note");
    equals(p1.getSource(), p2.getSource(), "Source");
    
    if (l1.size() == 1 && l1.size() == l2.size()) {
      equalsReactionProteinSubunit(l1.get(0), l2.get(0));
    } else {
      for (ModelReactionProteinSubunit s : l1) {
        System.out.println("S1: " + s);
      }
      for (ModelReactionProteinSubunit s : l2) {
        System.out.println("S2: " + s);
      }
    }
  }
  
  public static void equalsReactionProteins(ModelReaction r1, ModelReaction r2) {
//    Map<String, ModelReactionProtein> m1 = new HashMap<>();
//    Map<String, ModelReactionProtein> m2 = new HashMap<>();
    List<ModelReactionProtein> l1 = new ArrayList<>();
    List<ModelReactionProtein> l2 = new ArrayList<>();
    //remove empty ModelReactionProtein
    for (ModelReactionProtein r : r1.getModelReactionProteins()) {
      l1.add(r);
    }
    for (ModelReactionProtein r : r2.getModelReactionProteins()) {
      l2.add(r);
    }
    if (l1.size() != l2.size()) {
      logger.warn("[REACTION PROTEIN] {}, {}", l1.size(), l2.size());
    }
    
    if (l1.size() == 1 && l1.size() == l2.size()) {
      equalsReactionProtein(l1.get(0), l2.get(0));
    } else {
      for (ModelReactionProtein r : l1) {
        System.out.println(r1.getId() + " 1: " + r);
      }
      for (ModelReactionProtein r : l2) {
        System.out.println(r2.getId() + " 2: " + r);
      }
    }
  }
  
  public static void equalsReagents(ModelReaction r1, ModelReaction r2) {
    Map<String, ModelReactionReagent> m1 = new HashMap<>();
    Map<String, ModelReactionReagent> m2 = new HashMap<>();
    for (ModelReactionReagent r : r1.getModelReactionReagents()) {
      m1.put(r.getModelcompoundRef(), r);
    }
    for (ModelReactionReagent r : r2.getModelReactionReagents()) {
      m2.put(r.getModelcompoundRef(), r);
    }
    if (m1.size() != m2.size()) {
      logger.warn("[REAGENTS] {}, {}", m1.size(), m2.size());
    }
    Set<String> ids1 = m1.keySet();
    Set<String> ids2 = m2.keySet();
    Set<String> both = Sets.intersection(ids1, ids2);
    if (both.size() != ids1.size() || 
        both.size() != ids2.size()) {
      for (String u1 : Sets.difference(ids1, both)) {
        logger.warn("[REAGENTS UNIQUE MODEL1] {} - {}", u1, m1.get(u1));
      }
      for (String u2 : Sets.difference(ids2, both)) {
        logger.warn("[REAGENTS UNIQUE MODEL2] {} - {}", u2, m2.get(u2));
      }
    }
    for (String id : both) {
      ModelReactionReagent o1 = m1.get(id);
      ModelReactionReagent o2 = m2.get(id);
      equals(o1.getCoefficient(), o2.getCoefficient(), "Coefficient");
      equals(o1.getModelcompoundRef(), o2.getModelcompoundRef(), "ModelcompoundRef");
    }
  }
  
  public static void equalsReactions(FBAModel kmodel1, FBAModel kmodel2) {
    Map<String, ModelReaction> m1 = new HashMap<>();
    Map<String, ModelReaction> m2 = new HashMap<>();
    for (ModelReaction krxn : kmodel1.getModelreactions()) {
      m1.put(krxn.getId(), krxn);
    }
    for (ModelReaction krxn : kmodel2.getModelreactions()) {
      m2.put(krxn.getId(), krxn);
    }
    
    if (m1.size() != m2.size()) {
      logger.warn("[REACTIONS] {}, {}", m1.size(), m2.size());
    } 
    KBaseModelAdapter model1 = new KBaseModelAdapter(kmodel1);
    KBaseModelAdapter model2 = new KBaseModelAdapter(kmodel2);
    Set<String> ids1 = model1.getReactionIds();
    Set<String> ids2 = model2.getReactionIds();
    Set<String> both = Sets.intersection(ids1, ids2);
    if (both.size() != ids1.size() || 
        both.size() != ids2.size()) {
      for (String u1 : Sets.difference(ids1, both)) {
        logger.warn("[REACTIONS UNIQUE MODEL1] {} - {}", u1, m1.get(u1));
      }
      for (String u2 : Sets.difference(ids2, both)) {
        logger.warn("[REACTIONS UNIQUE MODEL2] {} - {}", u2, m2.get(u2));
      }
    }
    for (String rxnId : both) {
      System.out.println(rxnId);
      ModelReaction krxn1 = m1.get(rxnId);
      ModelReaction krxn2 = m2.get(rxnId);
      equals(krxn1.getId(), krxn1.getId(), "id");
      equals(krxn1.getReactionRef(), krxn2.getReactionRef(), "ReactionRef");
      equals(krxn1.getDisplayID(), krxn2.getDisplayID(), "DisplayID");
      equals(krxn1.getDirection(), krxn2.getDirection(), "Direction");
//      equals(krxn1.getImportedGpr(), krxn2.getImportedGpr(), "ImportedGpr");
      equals(krxn1.getModelcompartmentRef(), krxn2.getModelcompartmentRef(), "ModelcompartmentRef");
      equals(krxn1.getName(), krxn2.getName(), "Name");
      equals(krxn1.getPathway(), krxn2.getPathway(), "Pathway");
//      equals(krxn1.getMaxforflux(), krxn2.getMaxforflux(), "Maxforflux");
//      equals(krxn1.getMaxrevflux(), krxn2.getMaxrevflux(), "Maxrevflux");
      equals(krxn1.getProbability(), krxn2.getProbability(), "Probability");
      equals(krxn1.getProtons(), krxn2.getProtons(), "Protons");
      equalsReagents(krxn1, krxn2);
      equalsReactionProteins(krxn1, krxn2);
    }
    
  }
  
  public static void compare(FBAModel kmodel1, FBAModel kmodel2) {
    equals(kmodel1.getId(), kmodel2.getId(), "Id");
    equals(kmodel1.getTemplateRef(), kmodel2.getTemplateRef(), "TemplateRef");
    equals(kmodel1.getMetagenomeRef(), kmodel2.getMetagenomeRef(), "MetagenomeRef");
    equals(kmodel1.getMetagenomeOtuRef(), kmodel2.getMetagenomeOtuRef(), "MetagenomeOtuRef");
    equals(kmodel1.getGenomeRef(), kmodel2.getGenomeRef(), "GenomeRef");
    equals(kmodel1.getSourceId(), kmodel2.getSourceId(), "SourceId");
    equals(kmodel1.getName(), kmodel2.getName(), "Name");
    equals(kmodel1.getType(), kmodel2.getType(), "Type");
    equalsCompartments(kmodel1, kmodel2);
    equalsSpecies(kmodel1, kmodel2);
    equalsReactions(kmodel1, kmodel2);
  }
  
  public static FBAModel convert(XmlSbmlModel xmodel, 
      Map<String, SubcellularCompartment> scmpMap,
      Map<String, String> spiToModelSeedReference,
      Map<String, String> rxnToModelSeedReference) {
    FBAModel kmodel = null;
    
    kmodel = new KBaseFBAModelFactory()
         .withCompartmentMapping(scmpMap)
//        .withGenomeRef(genomeRef)
//        .withSpecieIntegration(sintegration)
//        .withReactionIntegration(rintegration)
//        .withBiomassIds(biomassIds)
        .withMetaboliteModelSeedReference(spiToModelSeedReference)
        .withReactionModelSeedReference(rxnToModelSeedReference)
//        .withModelId(modelId)
//        .withModelName(modelId)
        .withRemovePrefix("M_")
        .withReactionIdRemovePrefix("R_")
        .withXmlSbmlModel(xmodel, false)
        .build();
    
    return kmodel;
  }
  
  public static void main(String[] args) {
    KBaseConfig.production = false;
    CompartmentIntegration cintegration = 
        new CompartmentIntegration();
    cintegration.detectors.add(new CompartmentDetectorKBase());
    try {
//      String json = IOUtils.readFromFile(
//          "/var/biomodels/kbase/PlantSEED_Cre.mdl.json");
//      FBAModel kmodel = KBaseIOUtils.getObject(json, FBAModel.class);
//      FBAModelAdapter fbamodel = new FBAModelAdapter(kmodel);
//      ModelAdapter model = fbamodel;
      
      
//      KBaseSbmlImporter importer = new KBaseSbmlImporter(workspace, dfuClient, wsClient);
//      String xmlModel = "/var/biomodels/kbase/PlantSEED_Cre.mdl.xml";
      String xmlModel = "/var/biomodels/kbase/GCF_000007565.2.mdl.xml";
      XmlStreamSbmlReader reader = new XmlStreamSbmlReader(xmlModel);
      XmlSbmlModel xmodel = reader.parse();
      Set<String> ids = new HashSet<>();
      Set<String> rxnIds = new HashSet<>();
      for (XmlSbmlSpecie xspi : xmodel.getSpecies()) {
        ids.add(xspi.getAttributes().get("id"));
      }
      for (XmlSbmlReaction xrxn : xmodel.getReactions()) {
        rxnIds.add(xrxn.getAttributes().get("id"));
      }
      
      Map<String, SubcellularCompartment> cmap = cintegration.generateCompartmentMapping(new XmlSbmlModelAdapter(xmodel));
      System.out.println(cmap);
      
      KBaseIntegrationEngine e = new MethoBuilder(null).buildKBaseIntegrationEngine();
      e.ids.addAll(ids);
      SimpleStringMatchEngine r = new SimpleStringMatchEngine("rxn", "R_");
      r.ids.addAll(rxnIds);
      for (String rxnEntry : KBaseConfig.getModelSeedRxnDao().getAllReactionEntries()) {
        r.validIds.add(rxnEntry);
      }
      
      IntegrationMap<String, MetaboliteMajorLabel> spiMap = e.integrate();
      
      Map<String, String> smap = new HashMap<>();
      Map<String, String> rmap = r.match();
      for (String spiId : spiMap.keySet()) {
        if (spiMap.get(spiId).containsKey(MetaboliteMajorLabel.ModelSeed)) {
          Set<String> s = spiMap.get(spiId).get(MetaboliteMajorLabel.ModelSeed);
          if (s != null && s.size() == 1) {
            smap.put(spiId, s.iterator().next());
          }
        }
      }
      KBaseModelIntegrationFacade integrationFacade = null; 
//      integrationFacade.kbaseIntegrate(params, workspaceName)
//          new KBaseModelIntegrationFacade(wspClient, dfuClient, gaClient, kbrClient, geneIntegration, biodbPath, scratch);
      FBAModel kmodelMine = convert(xmodel, cmap, smap, rmap);
      KBaseModelAdapter kmodelMineA = new KBaseModelAdapter(kmodelMine);
      kmodelMineA.updateMetadata(KBaseConfig.getModelSeedCpdDao(), 
                                 KBaseConfig.getModelSeedRxnDao());
//      Genome genome = KBaseIOUtils.loadJsonGenomeFromZip("/var/biomodels/kbase/PlantSEED_Cre.rast.json.zip");
      Genome genome = KBaseIOUtils.loadJsonGenomeFromZip("/var/biomodels/kbase/GCF_000007565.2.rast.json.zip");
      
      kmodelMineA.attachGenome(genome, true);
      //      
//      kmodelMineA.integrateCompartment("z0", "e");
      String model1 = "/var/biomodels/kbase/PlantSEED_Cre.mdl.json";
//      String model2 = "/var/biomodels/kbase/PlantSEED_Cre.mdl.imported.json";
      String model2 = "/var/biomodels/kbase/GCF_000007565.2.mdl.imported.json";
//      FBAModel kmodel2 = KBaseIOUtils.getObject(
//          IOUtils.readFromFile(model2), 
//          FBAModel.class);
      FBAModel kmodel2 = kmodelMine;
//      
      
      compare(
          KBaseIOUtils.getObject(
              IOUtils.readFromFile(model2), 
              FBAModel.class), kmodel2);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
