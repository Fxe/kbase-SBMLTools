package sbmltools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sbmltools.Media.MediaCompound;
import sbmltools.Media.MediaReagent;

public class MockData {
  
  public static ModelCompound mockCompound(String ref, String cmp) {
    return new ModelCompound().withId(ref)
        .withCompoundRef(ref)
        .withModelcompartmentRef(cmp)
        .withFormula("R")
        .withCharge(1.0)
        .withName(ref + "@" + cmp);
  }
  
  public static Object mockMedia() {
    Media media = new Media();
    media.id = "mockmodel";
    media.name = "The Mock Model";
    media.atmosphere = "earth";
    media.atmosphere_addition = "just that";
    media.isAerobic = 1;
    media.isDefined = 1;
    media.isMinimal = 1;
    media.mediacompounds = new ArrayList<> ();
    media.reagents = new ArrayList<> ();
    MediaCompound cpd1 = new MediaCompound();
    cpd1.compound_ref = "cpd00001";
    cpd1.concentration = 100.0;
    cpd1.maxFlux =  10.0;
    cpd1.minFlux = -10.0;
    MediaCompound cpd2 = new MediaCompound();
    cpd2.compound_ref = "cpd00002";
    cpd2.concentration = 100.0;
    cpd2.maxFlux =  10.0;
    cpd2.minFlux = -10.0;
    media.mediacompounds.add(cpd1);
    media.mediacompounds.add(cpd2);
//    MediaReagent rcpd1 = new MediaReagent();
//    rcpd1.associated_compounds.put(key, value)
//    media.reagents.add(rcpd1);
    return media;
  }
  
  public static Object mockModel() {
    String cmp = "default";
    FBAModel model = new FBAModel();
    model.setId("mockmodel");
    model.setName("The Mock Model");
    model.setATPMaintenance(10.0);
    List<ModelReaction> modelreactions = new ArrayList<> ();
    modelreactions.add(new ModelReaction()
        .withId("rxn00148")
        .withName("ATP:pyruvate O2-phosphotransferase")
        .withDirection("=")
        .withProtons(1.0)
        .withReactionRef("rxn00148")
        .withModelReactionProteins(new ArrayList<ModelReactionProtein> ())
        .withProbability(1.0)
        .withModelcompartmentRef(cmp)
        .withModelReactionReagents(Arrays.asList(
            new ModelReactionReagent().withCoefficient(-1d).withModelcompoundRef("cpd00002"),
            new ModelReactionReagent().withCoefficient(-1d).withModelcompoundRef("cpd00020"),
            new ModelReactionReagent().withCoefficient(1d).withModelcompoundRef("cpd00008"),
            new ModelReactionReagent().withCoefficient(1d).withModelcompoundRef("cpd00061"),
            new ModelReactionReagent().withCoefficient(1d).withModelcompoundRef("cpd00067"))));
    
    List<ModelCompound> modelcompounds = new ArrayList<> ();
    modelcompounds.add(mockCompound("cpd00002", cmp));
    modelcompounds.add(mockCompound("cpd00008", cmp));
    modelcompounds.add(mockCompound("cpd00020", cmp));
    modelcompounds.add(mockCompound("cpd00061", cmp));
    modelcompounds.add(mockCompound("cpd00067", cmp));
    
    List<ModelCompartment> modelcompartments = new ArrayList<> ();
    modelcompartments.add(
        new ModelCompartment().withId(cmp)
        .withLabel("default")
        .withPH(7.3)
        .withPotential(1.0)
        .withCompartmentIndex(1L)
        .withCompartmentRef(cmp));
    
    
    model.setModelreactions(modelreactions);
    model.setModelcompounds(modelcompounds);
    model.setModelcompartments(modelcompartments);
    model.setGenomeRef("4345/2/1");
    model.setSource("");
    model.setSourceId("");
    model.setType("");
    model.setTemplateRef("");
    model.setGapfillings(new ArrayList<ModelGapfill> ());
    model.setGapgens(new ArrayList<ModelGapgen> ());
    model.setBiomasses(new ArrayList<Biomass> ());
    return model;
  }
}
