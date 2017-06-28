
package kbasefba;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple4;
import us.kbase.common.service.Tuple7;


/**
 * <p>Original spec-file type: FBAComparisonCompound</p>
 * <pre>
 * FBAComparisonCompound object: this object holds information about a compound across a set of FBA simulations
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "charge",
    "formula",
    "state_conservation",
    "most_common_state",
    "exchanges"
})
public class FBAComparisonCompound {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("charge")
    private java.lang.Double charge;
    @JsonProperty("formula")
    private java.lang.String formula;
    @JsonProperty("state_conservation")
    private Map<String, Tuple4 <Long, Double, Double, Double>> stateConservation;
    @JsonProperty("most_common_state")
    private java.lang.String mostCommonState;
    @JsonProperty("exchanges")
    private Map<String, Tuple7 <String, Double, Double, Double, Double, Double, String>> exchanges;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FBAComparisonCompound withId(java.lang.String id) {
        this.id = id;
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

    public FBAComparisonCompound withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("charge")
    public java.lang.Double getCharge() {
        return charge;
    }

    @JsonProperty("charge")
    public void setCharge(java.lang.Double charge) {
        this.charge = charge;
    }

    public FBAComparisonCompound withCharge(java.lang.Double charge) {
        this.charge = charge;
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

    public FBAComparisonCompound withFormula(java.lang.String formula) {
        this.formula = formula;
        return this;
    }

    @JsonProperty("state_conservation")
    public Map<String, Tuple4 <Long, Double, Double, Double>> getStateConservation() {
        return stateConservation;
    }

    @JsonProperty("state_conservation")
    public void setStateConservation(Map<String, Tuple4 <Long, Double, Double, Double>> stateConservation) {
        this.stateConservation = stateConservation;
    }

    public FBAComparisonCompound withStateConservation(Map<String, Tuple4 <Long, Double, Double, Double>> stateConservation) {
        this.stateConservation = stateConservation;
        return this;
    }

    @JsonProperty("most_common_state")
    public java.lang.String getMostCommonState() {
        return mostCommonState;
    }

    @JsonProperty("most_common_state")
    public void setMostCommonState(java.lang.String mostCommonState) {
        this.mostCommonState = mostCommonState;
    }

    public FBAComparisonCompound withMostCommonState(java.lang.String mostCommonState) {
        this.mostCommonState = mostCommonState;
        return this;
    }

    @JsonProperty("exchanges")
    public Map<String, Tuple7 <String, Double, Double, Double, Double, Double, String>> getExchanges() {
        return exchanges;
    }

    @JsonProperty("exchanges")
    public void setExchanges(Map<String, Tuple7 <String, Double, Double, Double, Double, Double, String>> exchanges) {
        this.exchanges = exchanges;
    }

    public FBAComparisonCompound withExchanges(Map<String, Tuple7 <String, Double, Double, Double, Double, Double, String>> exchanges) {
        this.exchanges = exchanges;
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
        return ((((((((((((((((("FBAComparisonCompound"+" [id=")+ id)+", name=")+ name)+", charge=")+ charge)+", formula=")+ formula)+", stateConservation=")+ stateConservation)+", mostCommonState=")+ mostCommonState)+", exchanges=")+ exchanges)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
