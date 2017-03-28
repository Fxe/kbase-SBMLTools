
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


/**
 * <p>Original spec-file type: ModelCompound</p>
 * <pre>
 * ModelCompound object
 * @optional aliases maxuptake
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "compound_ref",
    "aliases",
    "name",
    "charge",
    "maxuptake",
    "formula",
    "modelcompartment_ref"
})
public class ModelCompound {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("compound_ref")
    private java.lang.String compoundRef;
    @JsonProperty("aliases")
    private List<String> aliases;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("charge")
    private Double charge;
    @JsonProperty("maxuptake")
    private Double maxuptake;
    @JsonProperty("formula")
    private java.lang.String formula;
    @JsonProperty("modelcompartment_ref")
    private java.lang.String modelcompartmentRef;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ModelCompound withId(java.lang.String id) {
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

    public ModelCompound withCompoundRef(java.lang.String compoundRef) {
        this.compoundRef = compoundRef;
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

    public ModelCompound withAliases(List<String> aliases) {
        this.aliases = aliases;
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

    public ModelCompound withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("charge")
    public Double getCharge() {
        return charge;
    }

    @JsonProperty("charge")
    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public ModelCompound withCharge(Double charge) {
        this.charge = charge;
        return this;
    }

    @JsonProperty("maxuptake")
    public Double getMaxuptake() {
        return maxuptake;
    }

    @JsonProperty("maxuptake")
    public void setMaxuptake(Double maxuptake) {
        this.maxuptake = maxuptake;
    }

    public ModelCompound withMaxuptake(Double maxuptake) {
        this.maxuptake = maxuptake;
        return this;
    }

    @JsonProperty("formula")
    public java.lang.String getFormula() {
        return formula;
    }

    @JsonProperty("formula")
    public void setFormula(java.lang.String formula) {
        this.formula = formula;
    }

    public ModelCompound withFormula(java.lang.String formula) {
        this.formula = formula;
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

    public ModelCompound withModelcompartmentRef(java.lang.String modelcompartmentRef) {
        this.modelcompartmentRef = modelcompartmentRef;
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
        return ((((((((((((((((((("ModelCompound"+" [id=")+ id)+", compoundRef=")+ compoundRef)+", aliases=")+ aliases)+", name=")+ name)+", charge=")+ charge)+", maxuptake=")+ maxuptake)+", formula=")+ formula)+", modelcompartmentRef=")+ modelcompartmentRef)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
