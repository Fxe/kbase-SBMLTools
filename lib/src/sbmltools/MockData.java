package sbmltools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {
  public static ModelCompound mockCompound(String ref, String cmp) {
    return new ModelCompound().withId(ref)
        .withCompoundRef(ref)
        .withModelcompartmentRef(cmp)
        .withFormula("R")
        .withCharge(1.0)
        .withName(ref + "@" + cmp);
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
