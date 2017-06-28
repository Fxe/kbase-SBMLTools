
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
import us.kbase.common.service.Tuple4;


/**
 * <p>Original spec-file type: ModelComparisonReaction</p>
 * <pre>
 * ModelComparisonReaction object: this object holds information about a reaction across all compared models
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "reaction_ref",
    "name",
    "equation",
    "number_models",
    "fraction_models",
    "core",
    "reaction_model_data"
})
public class ModelComparisonReaction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("reaction_ref")
    private java.lang.String reactionRef;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("equation")
    private java.lang.String equation;
    @JsonProperty("number_models")
    private java.lang.Long numberModels;
    @JsonProperty("fraction_models")
    private java.lang.Double fractionModels;
    @JsonProperty("core")
    private java.lang.Long core;
    @JsonProperty("reaction_model_data")
    private Map<String, Tuple4 <Long, String, List<Tuple4 <String, String, Double, Long>> , String>> reactionModelData;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ModelComparisonReaction withId(java.lang.String id) {
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

    public ModelComparisonReaction withReactionRef(java.lang.String reactionRef) {
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

    public ModelComparisonReaction withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("equation")
    public java.lang.String getEquation() {
        return equation;
    }

    @JsonProperty("equation")
    public void setEquation(java.lang.String equation) {
        this.equation = equation;
    }

    public ModelComparisonReaction withEquation(java.lang.String equation) {
        this.equation = equation;
        return this;
    }

    @JsonProperty("number_models")
    public java.lang.Long getNumberModels() {
        return numberModels;
    }

    @JsonProperty("number_models")
    public void setNumberModels(java.lang.Long numberModels) {
        this.numberModels = numberModels;
    }

    public ModelComparisonReaction withNumberModels(java.lang.Long numberModels) {
        this.numberModels = numberModels;
        return this;
    }

    @JsonProperty("fraction_models")
    public java.lang.Double getFractionModels() {
        return fractionModels;
    }

    @JsonProperty("fraction_models")
    public void setFractionModels(java.lang.Double fractionModels) {
        this.fractionModels = fractionModels;
    }

    public ModelComparisonReaction withFractionModels(java.lang.Double fractionModels) {
        this.fractionModels = fractionModels;
        return this;
    }

    @JsonProperty("core")
    public java.lang.Long getCore() {
        return core;
    }

    @JsonProperty("core")
    public void setCore(java.lang.Long core) {
        this.core = core;
    }

    public ModelComparisonReaction withCore(java.lang.Long core) {
        this.core = core;
        return this;
    }

    @JsonProperty("reaction_model_data")
    public Map<String, Tuple4 <Long, String, List<Tuple4 <String, String, Double, Long>> , String>> getReactionModelData() {
        return reactionModelData;
    }

    @JsonProperty("reaction_model_data")
    public void setReactionModelData(Map<String, Tuple4 <Long, String, List<Tuple4 <String, String, Double, Long>> , String>> reactionModelData) {
        this.reactionModelData = reactionModelData;
    }

    public ModelComparisonReaction withReactionModelData(Map<String, Tuple4 <Long, String, List<Tuple4 <String, String, Double, Long>> , String>> reactionModelData) {
        this.reactionModelData = reactionModelData;
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
        return ((((((((((((((((((("ModelComparisonReaction"+" [id=")+ id)+", reactionRef=")+ reactionRef)+", name=")+ name)+", equation=")+ equation)+", numberModels=")+ numberModels)+", fractionModels=")+ fractionModels)+", core=")+ core)+", reactionModelData=")+ reactionModelData)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
