
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
 * <p>Original spec-file type: ModelComparisonCompound</p>
 * <pre>
 * ModelComparisonCompound object: this object holds information about a compound across a set of models
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "compound_ref",
    "name",
    "number_models",
    "fraction_models",
    "core",
    "model_compound_compartments"
})
public class ModelComparisonCompound {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("compound_ref")
    private java.lang.String compoundRef;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("number_models")
    private Long numberModels;
    @JsonProperty("fraction_models")
    private java.lang.Double fractionModels;
    @JsonProperty("core")
    private Long core;
    @JsonProperty("model_compound_compartments")
    private Map<String, List<Tuple2 <String, Double>>> modelCompoundCompartments;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ModelComparisonCompound withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("compound_ref")
    public java.lang.String getCompoundRef() {
        return compoundRef;
    }

    @JsonProperty("compound_ref")
    public void setCompoundRef(java.lang.String compoundRef) {
        this.compoundRef = compoundRef;
    }

    public ModelComparisonCompound withCompoundRef(java.lang.String compoundRef) {
        this.compoundRef = compoundRef;
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

    public ModelComparisonCompound withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("number_models")
    public Long getNumberModels() {
        return numberModels;
    }

    @JsonProperty("number_models")
    public void setNumberModels(Long numberModels) {
        this.numberModels = numberModels;
    }

    public ModelComparisonCompound withNumberModels(Long numberModels) {
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

    public ModelComparisonCompound withFractionModels(java.lang.Double fractionModels) {
        this.fractionModels = fractionModels;
        return this;
    }

    @JsonProperty("core")
    public Long getCore() {
        return core;
    }

    @JsonProperty("core")
    public void setCore(Long core) {
        this.core = core;
    }

    public ModelComparisonCompound withCore(Long core) {
        this.core = core;
        return this;
    }

    @JsonProperty("model_compound_compartments")
    public Map<String, List<Tuple2 <String, Double>>> getModelCompoundCompartments() {
        return modelCompoundCompartments;
    }

    @JsonProperty("model_compound_compartments")
    public void setModelCompoundCompartments(Map<String, List<Tuple2 <String, Double>>> modelCompoundCompartments) {
        this.modelCompoundCompartments = modelCompoundCompartments;
    }

    public ModelComparisonCompound withModelCompoundCompartments(Map<String, List<Tuple2 <String, Double>>> modelCompoundCompartments) {
        this.modelCompoundCompartments = modelCompoundCompartments;
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
        return ((((((((((((((((("ModelComparisonCompound"+" [id=")+ id)+", compoundRef=")+ compoundRef)+", name=")+ name)+", numberModels=")+ numberModels)+", fractionModels=")+ fractionModels)+", core=")+ core)+", modelCompoundCompartments=")+ modelCompoundCompartments)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
