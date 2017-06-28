
package kbasebiochem;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: Reagent</p>
 * <pre>
 * Reactant object
 *     @searchable ws_subset compound_ref compartment_ref coefficient isCofactor
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "compound_ref",
    "compartment_ref",
    "coefficient",
    "isCofactor"
})
public class Reagent {

    @JsonProperty("compound_ref")
    private String compoundRef;
    @JsonProperty("compartment_ref")
    private String compartmentRef;
    @JsonProperty("coefficient")
    private Double coefficient;
    @JsonProperty("isCofactor")
    private Long isCofactor;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("compound_ref")
    public String getCompoundRef() {
        return compoundRef;
    }

    @JsonProperty("compound_ref")
    public void setCompoundRef(String compoundRef) {
        this.compoundRef = compoundRef;
    }

    public Reagent withCompoundRef(String compoundRef) {
        this.compoundRef = compoundRef;
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

    public Reagent withCompartmentRef(String compartmentRef) {
        this.compartmentRef = compartmentRef;
        return this;
    }

    @JsonProperty("coefficient")
    public Double getCoefficient() {
        return coefficient;
    }

    @JsonProperty("coefficient")
    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Reagent withCoefficient(Double coefficient) {
        this.coefficient = coefficient;
        return this;
    }

    @JsonProperty("isCofactor")
    public Long getIsCofactor() {
        return isCofactor;
    }

    @JsonProperty("isCofactor")
    public void setIsCofactor(Long isCofactor) {
        this.isCofactor = isCofactor;
    }

    public Reagent withIsCofactor(Long isCofactor) {
        this.isCofactor = isCofactor;
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
        return ((((((((((("Reagent"+" [compoundRef=")+ compoundRef)+", compartmentRef=")+ compartmentRef)+", coefficient=")+ coefficient)+", isCofactor=")+ isCofactor)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
