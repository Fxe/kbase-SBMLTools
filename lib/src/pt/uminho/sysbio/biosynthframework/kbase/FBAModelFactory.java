package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynthframework.SimpleModelReaction;
import pt.uminho.sysbio.biosynthframework.SimpleModelSpecie;
import pt.uminho.sysbio.biosynthframework.sbml.XmlObject;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlCompartment;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlReaction;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlSpecie;
import sbmltools.Biomass;
import sbmltools.FBAModel;
import sbmltools.ModelCompartment;
import sbmltools.ModelCompound;
import sbmltools.ModelGapfill;
import sbmltools.ModelGapgen;
import sbmltools.ModelReaction;
import sbmltools.ModelReactionProtein;
import sbmltools.ModelReactionReagent;

public class FBAModelFactory {

  private static final Logger logger = LoggerFactory.getLogger(FBAModelFactory.class);
  
//  private static final String DEFAULT_COMPOUND_REF = "~/template/compounds/id/cpd00000";
  private static final String MODEL_SEED_COMPOUND_REF_PATTERN = "~/template/compounds/id/%s";
  
  private XmlSbmlModel xmodel;
  private String modelId;
  private String modelName;
  
  private Map<String, String> spiToModelSeedReference = new HashMap<> ();
  private List<ModelCompound> modelCompounds = new ArrayList<> ();
  private List<ModelCompartment> modelCompartments = new ArrayList<> ();
  private List<ModelReaction> modelReactions = new ArrayList<> ();

  private Map<String, String> cmpMap = new HashMap<> ();
  
  public FBAModelFactory withModelId(String modelId) {
    this.modelId = modelId;
    return this;
  }
  
  public FBAModelFactory withModelName(String modelName) {
    this.modelName = modelName;
    return this;
  }
  
  public FBAModelFactory withModelSeedReference(Map<String, String> spiToModelSeedReference) {
    this.spiToModelSeedReference.putAll(spiToModelSeedReference);
    return this;
  }
  
  public FBAModelFactory withSimpleModelReaction(SimpleModelReaction mrxn) {
    
    return this;
  }
  
  public FBAModelFactory withSimpleModelSpecie(SimpleModelSpecie mspi) {
    return withSimpleModelSpecie(mspi, "cpd00000");
  }
  
  public FBAModelFactory withSimpleModelSpecie(SimpleModelSpecie mspi, String modelSeedRef) {
    String spiEntry = mspi.id;
    String cmpEntry = mspi.compartment;
    String spiName = mspi.name;
    if (spiName == null || spiName.trim().isEmpty()) {
      spiName = "undefined";
    }
    
    ModelCompound cpd = new ModelCompound().withId(spiEntry)
        .withCompoundRef(String.format(MODEL_SEED_COMPOUND_REF_PATTERN, modelSeedRef))
        .withModelcompartmentRef(
            String.format("~/modelcompartments/id/%s", cmpMap.get(cmpEntry)))
        .withFormula("R")
        .withCharge(1.0)
        .withName(spiName);
    
    this.modelCompounds.add(cpd);
    return this;
  }

  public FBAModelFactory withXmlSbmlModel(XmlSbmlModel xmodel) {
    this.xmodel = xmodel;
    
    List<String> cmpArray = new ArrayList<> ();

    cmpArray.add("c");
    //    cmpArray.add("d");
    cmpArray.add("e");
    cmpArray.add("f");
    cmpArray.add("g");
    cmpArray.add("a");
    cmpArray.add("b");
    Iterator<String> cmpIt = cmpArray.iterator();

    
    for (XmlSbmlCompartment xcmp : xmodel.getCompartments()) {
      String cmpEntry = xcmp.getAttributes().get("id");
      String cmpName = xcmp.getAttributes().get("name");
      if (cmpName == null || cmpName.trim().isEmpty()) {
        cmpName = "undefined";
      }
      String cmpId = cmpIt.next();
      long cmpIndex = 0;
      String cmpIdAndIndex = cmpId + cmpIndex;
      ModelCompartment cmp = new ModelCompartment().withId(cmpIdAndIndex)
          .withLabel(cmpName)
          .withPH(7.3)
          .withPotential(1.0)
          .withCompartmentIndex(cmpIndex)
          .withCompartmentRef("~/template/compartments/id/" + cmpId);
      modelCompartments.add(cmp);
      cmpMap.put(cmpEntry, cmpIdAndIndex);
    }
    
    for (XmlSbmlSpecie xspi : xmodel.getSpecies()) {
      String spiEntry = xspi.getAttributes().get("id");
      String cmpEntry = xspi.getAttributes().get("compartment");
      String spiName = xspi.getAttributes().get("name");
      if (spiName == null || spiName.trim().isEmpty()) {
        spiName = "undefined";
      }
      
      String ref = "cpd00000";
      if (spiToModelSeedReference.containsKey(spiEntry)) {
        ref = spiToModelSeedReference.get(spiEntry);
      }
      ModelCompound cpd = new ModelCompound().withId(spiEntry)
          .withCompoundRef(String.format(MODEL_SEED_COMPOUND_REF_PATTERN, ref))
          .withModelcompartmentRef(
              String.format("~/modelcompartments/id/%s", cmpMap.get(cmpEntry)))
          .withFormula("R")
          .withCharge(1.0)
          .withName(spiName);

      modelCompounds.add(cpd);
    }
    
    return this;
  }

  public FBAModel build() {

    FBAModel model = new FBAModel();
    model.setId(modelId);
    model.setName(modelId); //get from xml if exists
    model.setGenomeRef("4345/2/1");
    model.setSource("External");
    model.setSourceId(modelId);
    model.setType("SBML Model");
    model.setTemplateRef("50/1/1");
    model.setGapfillings(new ArrayList<ModelGapfill> ());
    model.setGapgens(new ArrayList<ModelGapgen> ());
    model.setBiomasses(new ArrayList<Biomass> ());
    model.setModelcompounds(modelCompounds);
    model.setModelcompartments(modelCompartments);
    model.setModelreactions(new ArrayList<ModelReaction> ());


    for (XmlSbmlReaction xrxn : xmodel.getReactions()) {
      String rxnEntry = xrxn.getAttributes().get("id");
      String rxnName = xrxn.getAttributes().get("name");
      if (rxnName == null || rxnName.trim().isEmpty()) {
        rxnName = "undefined";
      }
      List<ModelReactionReagent> reagents = new ArrayList<> ();

      for (XmlObject o : xrxn.getListOfReactants()) {
        String species = o.getAttributes().get("species");
        //small cheat to test the ecoli model (should be decided by the integration)
        if (!species.endsWith("_b")) {
          String stoich = o.getAttributes().get("stoichiometry");
          if (stoich == null) {
            stoich = "1";
          }
          double stoichiometry = Double.parseDouble(stoich);
          ModelReactionReagent r = new ModelReactionReagent()
              .withCoefficient(-1 * stoichiometry)
              .withModelcompoundRef(String.format("~/modelcompounds/id/%s", species));
          reagents.add(r);
        }
      }

      for (XmlObject o : xrxn.getListOfProducts()) {
        String species = o.getAttributes().get("species");
        //small cheat to test the ecoli model (should be decided by the integration)
        if (!species.endsWith("_b")) {
          String stoich = o.getAttributes().get("stoichiometry");
          if (stoich == null) {
            stoich = "1";
          }
          double stoichiometry = Double.parseDouble(stoich);
          ModelReactionReagent r = new ModelReactionReagent()
              .withCoefficient(stoichiometry)
              .withModelcompoundRef(String.format("~/modelcompounds/id/%s", species));
          reagents.add(r);
        }
      }

      String rxnCmpRef = String.format("~/modelcompartments/id/%s", cmpMap.values().iterator().next());
      //      System.out.println(rxnCmpRef);
      //      System.out.println(reagents);
      ModelReaction rxn = new ModelReaction().withId(rxnEntry)
          .withAliases(new ArrayList<String> ())
          .withName(rxnName)
          .withDirection("=")
          .withProtons(1.0)
          .withReactionRef("50/1/1/reactions/id/rxn00000_c")
          .withModelReactionProteins(new ArrayList<ModelReactionProtein> ())
          .withProbability(1.0)
          .withPathway("entire model")
          .withModelcompartmentRef(rxnCmpRef);
      rxn.setModelReactionReagents(reagents);

      //      ModelReactionProtein protein = new ModelReactionProtein();
      //      protein.setModelReactionProteinSubunits(null);
      //      protein.setComplexRef("");
      //      protein.setSource("");
      //      protein.setNote("");
      //      ModelReactionProteinSubunit proteinSubunit = new ModelReactionProteinSubunit();
      ////      proteinSubunit.setFeatureRefs(featureRefs);
      ////      proteinSubunit.setOptionalSubunit(optionalSubunit);
      //      proteinSubunit.setRole("");
      //      proteinSubunit.setNote("");
      //      proteinSubunit.setTriggering(0L);
      //      
      //      List<ModelReactionProtein> proteins = new ArrayList<> ();
      //      proteins.add(protein);
      //      rxn.setModelReactionProteins(proteins);
      //      
      //      //??
      //      rxn.setDirection("=");
      //      //LB, UB
      //      xrxn.get
      String[] boundStr = validateReactionContraint(rxnEntry, xmodel, xrxn, xrxn.getListOfParameters());

      double lb = -1000;
      double ub =  1000;
      try {
        lb = Double.parseDouble(boundStr[0]);
      } catch (Exception e) {

      }
      try {
        ub = Double.parseDouble(boundStr[1]);
      } catch (Exception e) {

      }
      rxn.setMaxrevflux(lb);
      rxn.setMaxforflux(ub);
//      System.out.println(rxnEntry + " " + lb + " " + ub);

      model.getModelreactions().add(rxn);

    }

    return model;
  }

  /**
   * Determine reaction reversibility
   */
  public static String[] validateReactionContraint(String rxnEntry, XmlSbmlModel xmodel, XmlSbmlReaction xrxn, List<XmlObject> rxnParameters) {
    //    System.out.println(rxnAttributes);

    String lb = null;
    String ub = null;

    //    System.out.println(xrxn.getAttributes());

    for (XmlObject xo : rxnParameters) {
      //      System.out.println(xo.getAttributes());
      String id = xo.getAttributes().get("id");
      if (id == null) {
        //        this.messages.add(new XmlMessage(xo, MessageType.WARN, "parameter without ID (rxn: %s)", rxnEntry));
        logger.debug("parameter without ID {}", xo.getAttributes());
      } else {
        String value = xo.getAttributes().get("value");
        //      String units = xo.getAttributes().get("units");
        //      System.out.println("\t\t:\t" + xo.getAttributes());
        switch (id.toUpperCase()) {
        case "OBJECTIVE_COEFFICIENT":
          //          this.defaultObjective.put(rxnEntry, value);
          break;
        case "LOWER_BOUND":
          lb = value;
          break;
        case "UPPER_BOUND":
          ub = value;
          break;
        default:
          //          CollectionUtils.increaseCount(ignoredParameter, id, 1);
          logger.debug("ignored {}", id);
          break;
        }
      }
    }

    if (xrxn.getAttributes().containsKey("upperFluxBound")) {
      String ufbcId = xrxn.getAttributes().get("upperFluxBound");
      for (XmlObject xo : xmodel.getListOfParameters()) {
        String id = xo.getAttributes().get("id");
        if (id != null && id.equals(ufbcId)) {
          String valueStr = xo.getAttributes().get("value");
          if (ub != null && !ub.equals(valueStr)) {
            logger.warn("fbc conflict with reaction parameters [{}] -> [{}]", valueStr, ub);
          } else {
            ub = valueStr;
          }
        }
      }
    }

    if (xrxn.getAttributes().containsKey("lowerFluxBound")) {
      String ufbcId = xrxn.getAttributes().get("lowerFluxBound");
      for (XmlObject xo : xmodel.getListOfParameters()) {
        String id = xo.getAttributes().get("id");
        if (id != null && id.equals(ufbcId)) {
          String valueStr = xo.getAttributes().get("value");
          if (lb != null && !lb.equals(valueStr)) {
            logger.warn("fbc conflict with reaction parameters [{}] -> [{}]", valueStr, lb);
          } else {
            lb = valueStr;
          }
        }
      }
    }

    Orientation parametersOrientation = Orientation.Unknown;
    Orientation attributesOrientation = Orientation.Unknown;
    //    if (lb == null || ub == null) {
    ////      logger.warn("{}, {}", lb, ub);
    //    } else {
    //      if (NumberUtils.isNumber(ub.trim()) && NumberUtils.isNumber(lb.trim())) {
    //        double ub_ = Double.parseDouble(ub.trim());
    //        double lb_ = Double.parseDouble(lb.trim());
    //        if (ub_ > 0.0 && lb_ < 0.0) {
    //          parametersOrientation = Orientation.Reversible;
    //        } else if (lb_ == 0.0 && ub_ > 0.0){
    //          parametersOrientation = Orientation.LeftToRight;
    //        } else if (lb_ < 0.0 && ub_ == 0.0) {
    //          parametersOrientation = Orientation.RightToLeft;
    //        } else if (lb_ == 0.0 && ub_ == 0.0) {
    //          logger.debug("zero [{}, {}]", lb, ub);
    //          parametersOrientation = Orientation.Zero;
    //        } else if (lb_ == ub_) {
    //          logger.debug("fixed bounds [{}, {}]", lb, ub);
    //          parametersOrientation = Orientation.Fixed;
    //        } else {
    //          parametersOrientation = Orientation.Range;
    //          //logger.error("strange bounds [{}, {}]", lb, ub);
    //        }
    //
    //      } else {
    //        logger.error("non numeric bounds [{}, {}]", lb, ub);
    //      }
    //    }

    String rev = xrxn.getAttributes().get("reversible");
    if (rev != null) {
      boolean reversible = Boolean.parseBoolean(rev);
      if (reversible) {
        attributesOrientation = Orientation.Reversible;
      } else {
        attributesOrientation = Orientation.LeftToRight;
      }
    }


    //    logger.debug("{} - type:{}, Attribute: {}, Parameters: {}", rxnEntry, xrxnTypeMap.get(rxnEntry), attributesOrientation, parametersOrientation);
    //
    //    //drains are special case we need to have lower and upper bounds !
    //    if (xrxnTypeMap.get(rxnEntry).equals(EntityType.DRAIN)) {
    //      if (lb == null || ub == null) {
    //        logger.debug("drain reaction with undefined bounds [{}, {}] !", lb, ub);
    //        this.messages.add(new XmlMessage(xrxn, MessageType.WARN, "%s drain reaction with undefined bounds [%s, %s] !", rxnEntry, lb, ub));
    //      }
    //      
    //      //if drain assume parameters or attributes (its up to the user to define uptakes)
    //      if (!parametersOrientation.equals(Orientation.Unknown)) {
    //        this.rxnDirection.get(parametersOrientation).add(rxnEntry);
    //      } else {
    //        this.rxnDirection.get(attributesOrientation).add(rxnEntry);
    //      }
    //    } else if (parametersOrientation.equals(Orientation.Unknown) && attributesOrientation.equals(Orientation.Unknown)) {
    //      this.rxnDirection.get(Orientation.Unknown).add(rxnEntry);
    //      this.messages.add(new XmlMessage(xrxn, MessageType.WARN, "no reversiblity information assume reversible (reversible:%s, #parameters %d)", rev, rxnParameters.size()));
    //      logger.debug("unable to determine orientation reversible:{}, #parameters {}", rev, rxnParameters.size());
    //    } else if (parametersOrientation.equals(attributesOrientation)) {
    //      logger.debug("orientation match ! {}", attributesOrientation);
    //      this.rxnDirection.get(attributesOrientation).add(rxnEntry);
    //    } else if (parametersOrientation.equals(Orientation.Unknown) && 
    //        !attributesOrientation.equals(Orientation.Unknown)) {
    //      logger.debug("using attribute orientation reversible:{} -> {}, #parameters {}", rev, attributesOrientation, rxnParameters.size());
    //      this.rxnDirection.get(attributesOrientation).add(rxnEntry);
    //    } else if (!parametersOrientation.equals(Orientation.Unknown) && 
    //        attributesOrientation.equals(Orientation.Unknown)) {
    //      logger.debug("using parameter orientation [{}, {}] -> {}, reversible:{}", lb, ub, parametersOrientation, rev);
    //      this.rxnDirection.get(parametersOrientation).add(rxnEntry);
    //    } else {
    //      this.messages.add(new XmlMessage(xrxn, MessageType.ERROR, "invalid orientation [%s, %s] -> %s", lb, ub, xrxnTypeMap.get(rxnEntry)));
    //      logger.debug("invalid orientation {}, [{}, {}] -> {}", xrxn.getAttributes(), lb, ub, xrxnTypeMap.get(rxnEntry));
    //      this.rxnDirection.get(Orientation.Unknown).add(rxnEntry);
    //    }
    return new String[]{lb, ub};
  }

  public static enum Orientation {
    LeftToRight, RightToLeft, Reversible, Unknown, 
    Fixed, //fixed constant non zero 
    Zero, //zero constant
    Range, //zero not included
  }
}
