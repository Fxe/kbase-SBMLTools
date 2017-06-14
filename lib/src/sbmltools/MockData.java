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
  
  public static Object mockMedia() {
    Media media = new Media();
    media.id = "mock_media2";
    media.name = "The Mock Media 2";
    media.sourceId = "mock_media2";
    media.source = "mock_media2";
    media.atmosphere = "earth";
    media.atmosphere_addition = "no";
    media.temperature = 70.0;
    media.pH_data = "7.3";
    media.isAerobic = 1;
    media.isDefined = 1;
    media.isMinimal = 1;
    media.protocol_link = "prot";
    media.type = "some media";
    media.mediacompounds = new ArrayList<> ();
    media.reagents = new ArrayList<> ();

    media.addMediaCompound("cpd00001", 0.1, -10, 10);
    media.addMediaCompound("cpd00002", 0.1, -10, 10);
    media.addMediaCompound("cpd00139", 0.1, -10, 10);
    media.addMediaCompound("cpd00159", 0.1, -10, 10);
    media.addMediaCompound("cpd00029", 0.1, -10, 10);
    media.addMediaCompound("cpd00011", 0.1, -10, 10);
    media.addMediaCompound("cpd00239", 0.1, -10, 10);
    
    media.addMediaCompound("cpd00048", 0.1, -10, 10);
    media.addMediaCompound("cpd00363", 0.1, -10, 10);
    media.addMediaCompound("cpd00013", 0.1, -10, 10);
    media.addMediaCompound("cpd00067", 0.1, -10, 10);
    
    media.addMediaCompound("cpd00009", 0.1, -10, 10);
    media.addMediaCompound("cpd11640", 0.1, -10, 10);
    media.addMediaCompound("cpd00047", 0.1, -10, 10);
    media.addMediaCompound("cpd00100", 0.1, -10, 10);
    
    media.addMediaCompound("cpd11416", 0.1, -10, 10);
    
//    media.addMediaCompound("mockcompound", 0.1, -10, 10);
    
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
