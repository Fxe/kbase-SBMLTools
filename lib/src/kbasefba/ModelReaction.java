
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
 * @optional gapfill_data name pathway reference aliases displayID dblinks maxforflux maxrevflux imported_gpr string_attributes numerical_attributes
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "displayID",
    "reaction_ref",
    "name",
    "dblinks",
    "aliases",
    "pathway",
    "reference",
    "direction",
    "protons",
    "maxforflux",
    "maxrevflux",
    "imported_gpr",
    "modelcompartment_ref",
    "probability",
    "modelReactionReagents",
    "modelReactionProteins",
    "string_attributes",
    "numerical_attributes",
    "gapfill_data"
})
public class ModelReaction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("displayID")
    private java.lang.String displayID;
    @JsonProperty("reaction_ref")
    private java.lang.String reactionRef;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("dblinks")
    private Map<String, List<String>> dblinks;
    @JsonProperty("aliases")
    private List<String> aliases;
    @JsonProperty("pathway")
    private java.lang.String pathway;
    @JsonProperty("reference")
    private java.lang.String reference;
    @JsonProperty("direction")
    private java.lang.String direction;
    @JsonProperty("protons")
    private java.lang.Double protons;
    @JsonProperty("maxforflux")
    private java.lang.Double maxforflux;
    @JsonProperty("maxrevflux")
    private java.lang.Double maxrevflux;
    @JsonProperty("imported_gpr")
    private java.lang.String importedGpr;
    @JsonProperty("modelcompartment_ref")
    private java.lang.String modelcompartmentRef;
    @JsonProperty("probability")
    private java.lang.Double probability;
    @JsonProperty("modelReactionReagents")
    private List<ModelReactionReagent> modelReactionReagents;
    @JsonProperty("modelReactionProteins")
    private List<kbasefba.ModelReactionProtein> modelReactionProteins;
    @JsonProperty("string_attributes")
    private Map<String, String> stringAttributes;
    @JsonProperty("numerical_attributes")
    private Map<String, Double> numericalAttributes;
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

    @JsonProperty("displayID")
    public java.lang.String getDisplayID() {
        return displayID;
    }

    @JsonProperty("displayID")
    public void setDisplayID(java.lang.String displayID) {
        this.displayID = displayID;
    }

    public ModelReaction withDisplayID(java.lang.String displayID) {
        this.displayID = displayID;
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

    @JsonProperty("dblinks")
    public Map<String, List<String>> getDblinks() {
        return dblinks;
    }

    @JsonProperty("dblinks")
    public void setDblinks(Map<String, List<String>> dblinks) {
        this.dblinks = dblinks;
    }

    public ModelReaction withDblinks(Map<String, List<String>> dblinks) {
        this.dblinks = dblinks;
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
    public java.lang.Double getProtons() {
        return protons;
    }

    @JsonProperty("protons")
    public void setProtons(java.lang.Double protons) {
        this.protons = protons;
    }

    public ModelReaction withProtons(java.lang.Double protons) {
        this.protons = protons;
        return this;
    }

    @JsonProperty("maxforflux")
    public java.lang.Double getMaxforflux() {
        return maxforflux;
    }

    @JsonProperty("maxforflux")
    public void setMaxforflux(java.lang.Double maxforflux) {
        this.maxforflux = maxforflux;
    }

    public ModelReaction withMaxforflux(java.lang.Double maxforflux) {
        this.maxforflux = maxforflux;
        return this;
    }

    @JsonProperty("maxrevflux")
    public java.lang.Double getMaxrevflux() {
        return maxrevflux;
    }

    @JsonProperty("maxrevflux")
    public void setMaxrevflux(java.lang.Double maxrevflux) {
        this.maxrevflux = maxrevflux;
    }

    public ModelReaction withMaxrevflux(java.lang.Double maxrevflux) {
        this.maxrevflux = maxrevflux;
        return this;
    }

    @JsonProperty("imported_gpr")
    public java.lang.String getImportedGpr() {
        return importedGpr;
    }

    @JsonProperty("imported_gpr")
    public void setImportedGpr(java.lang.String importedGpr) {
        this.importedGpr = importedGpr;
    }

    public ModelReaction withImportedGpr(java.lang.String importedGpr) {
        this.importedGpr = importedGpr;
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
    public java.lang.Double getProbability() {
        return probability;
    }

    @JsonProperty("probability")
    public void setProbability(java.lang.Double probability) {
        this.probability = probability;
    }

    public ModelReaction withProbability(java.lang.Double probability) {
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

    @JsonProperty("string_attributes")
    public Map<String, String> getStringAttributes() {
        return stringAttributes;
    }

    @JsonProperty("string_attributes")
    public void setStringAttributes(Map<String, String> stringAttributes) {
        this.stringAttributes = stringAttributes;
    }

    public ModelReaction withStringAttributes(Map<String, String> stringAttributes) {
        this.stringAttributes = stringAttributes;
        return this;
    }

    @JsonProperty("numerical_attributes")
    public Map<String, Double> getNumericalAttributes() {
        return numericalAttributes;
    }

    @JsonProperty("numerical_attributes")
    public void setNumericalAttributes(Map<String, Double> numericalAttributes) {
        this.numericalAttributes = numericalAttributes;
    }

    public ModelReaction withNumericalAttributes(Map<String, Double> numericalAttributes) {
        this.numericalAttributes = numericalAttributes;
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
        return ((((((((((((((((((((((((((((((((((((((((((("ModelReaction"+" [id=")+ id)+", displayID=")+ displayID)+", reactionRef=")+ reactionRef)+", name=")+ name)+", dblinks=")+ dblinks)+", aliases=")+ aliases)+", pathway=")+ pathway)+", reference=")+ reference)+", direction=")+ direction)+", protons=")+ protons)+", maxforflux=")+ maxforflux)+", maxrevflux=")+ maxrevflux)+", importedGpr=")+ importedGpr)+", modelcompartmentRef=")+ modelcompartmentRef)+", probability=")+ probability)+", modelReactionReagents=")+ modelReactionReagents)+", modelReactionProteins=")+ modelReactionProteins)+", stringAttributes=")+ stringAttributes)+", numericalAttributes=")+ numericalAttributes)+", gapfillData=")+ gapfillData)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
