
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
import us.kbase.common.service.Tuple2;


/**
 * <p>Original spec-file type: ModelComparisonFamily</p>
 * <pre>
 * ModelComparisonFamily object: this object holds information about a protein family across a set of models
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "family_id",
    "function",
    "number_models",
    "fraction_models",
    "core",
    "family_model_data"
})
public class ModelComparisonFamily {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("family_id")
    private java.lang.String familyId;
    @JsonProperty("function")
    private java.lang.String function;
    @JsonProperty("number_models")
    private java.lang.Long numberModels;
    @JsonProperty("fraction_models")
    private Double fractionModels;
    @JsonProperty("core")
    private java.lang.Long core;
    @JsonProperty("family_model_data")
    private Map<String, Tuple2 <Long, List<String>>> familyModelData;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ModelComparisonFamily withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("family_id")
    public java.lang.String getFamilyId() {
        return familyId;
    }

    @JsonProperty("family_id")
    public void setFamilyId(java.lang.String familyId) {
        this.familyId = familyId;
    }

    public ModelComparisonFamily withFamilyId(java.lang.String familyId) {
        this.familyId = familyId;
        return this;
    }

    @JsonProperty("function")
    public java.lang.String getFunction() {
        return function;
    }

    @JsonProperty("function")
    public void setFunction(java.lang.String function) {
        this.function = function;
    }

    public ModelComparisonFamily withFunction(java.lang.String function) {
        this.function = function;
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

    public ModelComparisonFamily withNumberModels(java.lang.Long numberModels) {
        this.numberModels = numberModels;
        return this;
    }

    @JsonProperty("fraction_models")
    public Double getFractionModels() {
        return fractionModels;
    }

    @JsonProperty("fraction_models")
    public void setFractionModels(Double fractionModels) {
        this.fractionModels = fractionModels;
    }

    public ModelComparisonFamily withFractionModels(Double fractionModels) {
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

    public ModelComparisonFamily withCore(java.lang.Long core) {
        this.core = core;
        return this;
    }

    @JsonProperty("family_model_data")
    public Map<String, Tuple2 <Long, List<String>>> getFamilyModelData() {
        return familyModelData;
    }

    @JsonProperty("family_model_data")
    public void setFamilyModelData(Map<String, Tuple2 <Long, List<String>>> familyModelData) {
        this.familyModelData = familyModelData;
    }

    public ModelComparisonFamily withFamilyModelData(Map<String, Tuple2 <Long, List<String>>> familyModelData) {
        this.familyModelData = familyModelData;
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
        return ((((((((((((((((("ModelComparisonFamily"+" [id=")+ id)+", familyId=")+ familyId)+", function=")+ function)+", numberModels=")+ numberModels)+", fractionModels=")+ fractionModels)+", core=")+ core)+", familyModelData=")+ familyModelData)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
