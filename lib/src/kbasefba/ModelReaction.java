
package kbasefba;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple3;


/**
 * <p>Original spec-file type: ModelReaction</p>
 * <pre>
 * ModelReaction object
 * @optional gapfill_data name pathway reference aliases maxforflux maxrevflux
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "reaction_ref",
    "name",
    "aliases",
    "pathway",
    "reference",
    "direction",
    "protons",
    "maxforflux",
    "maxrevflux",
    "modelcompartment_ref",
    "probability",
    "modelReactionReagents",
    "modelReactionProteins",
    "gapfill_data"
})
public class ModelReaction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("reaction_ref")
    private java.lang.String reactionRef;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("aliases")
    private List<String> aliases;
    @JsonProperty("pathway")
    private java.lang.String pathway;
    @JsonProperty("reference")
    private java.lang.String reference;
    @JsonProperty("direction")
    private java.lang.String direction;
    @JsonProperty("protons")
    private Double protons;
    @JsonProperty("maxforflux")
    private Double maxforflux;
    @JsonProperty("maxrevflux")
    private Double maxrevflux;
    @JsonProperty("modelcompartment_ref")
    private java.lang.String modelcompartmentRef;
    @JsonProperty("probability")
    private Double probability;
    @JsonProperty("modelReactionReagents")
    private List<ModelReactionReagent> modelReactionReagents;
    @JsonProperty("modelReactionProteins")
    private List<kbasefba.ModelReactionProtein> modelReactionProteins;
    @JsonProperty("gapfill_data")
    private Map<String, Map<Long, Tuple3 <String, Long, List<kbasefba.ModelReactionProtein>>>> gapfillData;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ModelReaction withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("reaction_ref")
    public java.lang.String getReactionRef() {
        return reactionRef;
    }

    @JsonProperty("reaction_ref")
    public void setReactionRef(java.lang.String reactionRef) {
        this.reactionRef = reactionRef;
    }

    public ModelReaction withReactionRef(java.lang.String reactionRef) {
        this.reactionRef = reactionRef;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public ModelReaction withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("aliases")
    public List<String> getAliases() {
        return aliases;
    }

    @JsonProperty("aliases")
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public ModelReaction withAliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }

    @JsonProperty("pathway")
    public java.lang.String getPathway() {
        return pathway;
    }

    @JsonProperty("pathway")
    public void setPathway(java.lang.String pathway) {
        this.pathway = pathway;
    }

    public ModelReaction withPathway(java.lang.String pathway) {
        this.pathway = pathway;
        return this;
    }

    @JsonProperty("reference")
    public java.lang.String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(java.lang.String reference) {
        this.reference = reference;
    }

    public ModelReaction withReference(java.lang.String reference) {
        this.reference = reference;
        return this;
    }

    @JsonProperty("direction")
    public java.lang.String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(java.lang.String direction) {
        this.direction = direction;
    }

    public ModelReaction withDirection(java.lang.String direction) {
        this.direction = direction;
        return this;
    }

    @JsonProperty("protons")
    public Double getProtons() {
        return protons;
    }

    @JsonProperty("protons")
    public void setProtons(Double protons) {
        this.protons = protons;
    }

    public ModelReaction withProtons(Double protons) {
        this.protons = protons;
        return this;
    }

    @JsonProperty("maxforflux")
    public Double getMaxforflux() {
        return maxforflux;
    }

    @JsonProperty("maxforflux")
    public void setMaxforflux(Double maxforflux) {
        this.maxforflux = maxforflux;
    }

    public ModelReaction withMaxforflux(Double maxforflux) {
        this.maxforflux = maxforflux;
        return this;
    }

    @JsonProperty("maxrevflux")
    public Double getMaxrevflux() {
        return maxrevflux;
    }

    @JsonProperty("maxrevflux")
    public void setMaxrevflux(Double maxrevflux) {
        this.maxrevflux = maxrevflux;
    }

    public ModelReaction withMaxrevflux(Double maxrevflux) {
        this.maxrevflux = maxrevflux;
        return this;
    }

    @JsonProperty("modelcompartment_ref")
    public java.lang.String getModelcompartmentRef() {
        return modelcompartmentRef;
    }

    @JsonProperty("modelcompartment_ref")
    public void setModelcompartmentRef(java.lang.String modelcompartmentRef) {
        this.modelcompartmentRef = modelcompartmentRef;
    }

    public ModelReaction withModelcompartmentRef(java.lang.String modelcompartmentRef) {
        this.modelcompartmentRef = modelcompartmentRef;
        return this;
    }

    @JsonProperty("probability")
    public Double getProbability() {
        return probability;
    }

    @JsonProperty("probability")
    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public ModelReaction withProbability(Double probability) {
        this.probability = probability;
        return this;
    }

    @JsonProperty("modelReactionReagents")
    public List<ModelReactionReagent> getModelReactionReagents() {
        return modelReactionReagents;
    }

    @JsonProperty("modelReactionReagents")
    public void setModelReactionReagents(List<ModelReactionReagent> modelReactionReagents) {
        this.modelReactionReagents = modelReactionReagents;
    }

    public ModelReaction withModelReactionReagents(List<ModelReactionReagent> modelReactionReagents) {
        this.modelReactionReagents = modelReactionReagents;
        return this;
    }

    @JsonProperty("modelReactionProteins")
    public List<kbasefba.ModelReactionProtein> getModelReactionProteins() {
        return modelReactionProteins;
    }

    @JsonProperty("modelReactionProteins")
    public void setModelReactionProteins(List<kbasefba.ModelReactionProtein> modelReactionProteins) {
        this.modelReactionProteins = modelReactionProteins;
    }

    public ModelReaction withModelReactionProteins(List<kbasefba.ModelReactionProtein> modelReactionProteins) {
        this.modelReactionProteins = modelReactionProteins;
        return this;
    }

    @JsonProperty("gapfill_data")
    public Map<String, Map<Long, Tuple3 <String, Long, List<kbasefba.ModelReactionProtein>>>> getGapfillData() {
        return gapfillData;
    }

    @JsonProperty("gapfill_data")
    public void setGapfillData(Map<String, Map<Long, Tuple3 <String, Long, List<kbasefba.ModelReactionProtein>>>> gapfillData) {
        this.gapfillData = gapfillData;
    }

    public ModelReaction withGapfillData(Map<String, Map<Long, Tuple3 <String, Long, List<kbasefba.ModelReactionProtein>>>> gapfillData) {
        this.gapfillData = gapfillData;
        return this;
    }

    @JsonAnyGetter
    public Map<java.lang.String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(java.lang.String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public java.lang.String toString() {
        return ((((((((((((((((((((((((((((((((("ModelReaction"+" [id=")+ id)+", reactionRef=")+ reactionRef)+", name=")+ name)+", aliases=")+ aliases)+", pathway=")+ pathway)+", reference=")+ reference)+", direction=")+ direction)+", protons=")+ protons)+", maxforflux=")+ maxforflux)+", maxrevflux=")+ maxrevflux)+", modelcompartmentRef=")+ modelcompartmentRef)+", probability=")+ probability)+", modelReactionReagents=")+ modelReactionReagents)+", modelReactionProteins=")+ modelReactionProteins)+", gapfillData=")+ gapfillData)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
