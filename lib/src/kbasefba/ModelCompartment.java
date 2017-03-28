
package kbasefba;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: ModelCompartment</p>
 * <pre>
 * ModelCompartment object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "compartment_ref",
    "compartmentIndex",
    "label",
    "pH",
    "potential"
})
public class ModelCompartment {

    @JsonProperty("id")
    private String id;
    @JsonProperty("compartment_ref")
    private String compartmentRef;
    @JsonProperty("compartmentIndex")
    private Long compartmentIndex;
    @JsonProperty("label")
    private String label;
    @JsonProperty("pH")
    private Double pH;
    @JsonProperty("potential")
    private Double potential;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ModelCompartment withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("compartment_ref")
    public String getCompartmentRef() {
        return compartmentRef;
    }

    @JsonProperty("compartment_ref")
    public void setCompartmentRef(String compartmentRef) {
        this.compartmentRef = compartmentRef;
    }

    public ModelCompartment withCompartmentRef(String compartmentRef) {
        this.compartmentRef = compartmentRef;
        return this;
    }

    @JsonProperty("compartmentIndex")
    public Long getCompartmentIndex() {
        return compartmentIndex;
    }

    @JsonProperty("compartmentIndex")
    public void setCompartmentIndex(Long compartmentIndex) {
        this.compartmentIndex = compartmentIndex;
    }

    public ModelCompartment withCompartmentIndex(Long compartmentIndex) {
        this.compartmentIndex = compartmentIndex;
        return this;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    public ModelCompartment withLabel(String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("pH")
    public Double getPH() {
        return pH;
    }

    @JsonProperty("pH")
    public void setPH(Double pH) {
        this.pH = pH;
    }

    public ModelCompartment withPH(Double pH) {
        this.pH = pH;
        return this;
    }

    @JsonProperty("potential")
    public Double getPotential() {
        return potential;
    }

    @JsonProperty("potential")
    public void setPotential(Double potential) {
        this.potential = potential;
    }

    public ModelCompartment withPotential(Double potential) {
        this.potential = potential;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return ((((((((((((((("ModelCompartment"+" [id=")+ id)+", compartmentRef=")+ compartmentRef)+", compartmentIndex=")+ compartmentIndex)+", label=")+ label)+", pH=")+ pH)+", potential=")+ potential)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
