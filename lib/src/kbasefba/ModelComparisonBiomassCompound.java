
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
 * <p>Original spec-file type: ModelComparisonBiomassCompound</p>
 * <pre>
 * ModelComparisonBiomassCompound object: this object holds information about a biomass compound across a set of models
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
    "model_biomass_compounds"
})
public class ModelComparisonBiomassCompound {

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
    @JsonProperty("model_biomass_compounds")
    private Map<String, List<Tuple2 <String, Double>>> modelBiomassCompounds;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ModelComparisonBiomassCompound withId(java.lang.String id) {
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

    public ModelComparisonBiomassCompound withCompoundRef(java.lang.String compoundRef) {
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

    public ModelComparisonBiomassCompound withName(java.lang.String name) {
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

    public ModelComparisonBiomassCompound withNumberModels(Long numberModels) {
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

    public ModelComparisonBiomassCompound withFractionModels(java.lang.Double fractionModels) {
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

    public ModelComparisonBiomassCompound withCore(Long core) {
        this.core = core;
        return this;
    }

    @JsonProperty("model_biomass_compounds")
    public Map<String, List<Tuple2 <String, Double>>> getModelBiomassCompounds() {
        return modelBiomassCompounds;
    }

    @JsonProperty("model_biomass_compounds")
    public void setModelBiomassCompounds(Map<String, List<Tuple2 <String, Double>>> modelBiomassCompounds) {
        this.modelBiomassCompounds = modelBiomassCompounds;
    }

    public ModelComparisonBiomassCompound withModelBiomassCompounds(Map<String, List<Tuple2 <String, Double>>> modelBiomassCompounds) {
        this.modelBiomassCompounds = modelBiomassCompounds;
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
        return ((((((((((((((((("ModelComparisonBiomassCompound"+" [id=")+ id)+", compoundRef=")+ compoundRef)+", name=")+ name)+", numberModels=")+ numberModels)+", fractionModels=")+ fractionModels)+", core=")+ core)+", modelBiomassCompounds=")+ modelBiomassCompounds)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
