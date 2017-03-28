
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
 * <p>Original spec-file type: FBABiomassVariable</p>
 * <pre>
 * FBABiomassVariable object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "biomass_ref",
    "variableType",
    "upperBound",
    "lowerBound",
    "class",
    "min",
    "max",
    "value"
})
public class FBABiomassVariable {

    @JsonProperty("biomass_ref")
    private String biomassRef;
    @JsonProperty("variableType")
    private String variableType;
    @JsonProperty("upperBound")
    private Double upperBound;
    @JsonProperty("lowerBound")
    private Double lowerBound;
    @JsonProperty("class")
    private String _class;
    @JsonProperty("min")
    private Double min;
    @JsonProperty("max")
    private Double max;
    @JsonProperty("value")
    private Double value;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("biomass_ref")
    public String getBiomassRef() {
        return biomassRef;
    }

    @JsonProperty("biomass_ref")
    public void setBiomassRef(String biomassRef) {
        this.biomassRef = biomassRef;
    }

    public FBABiomassVariable withBiomassRef(String biomassRef) {
        this.biomassRef = biomassRef;
        return this;
    }

    @JsonProperty("variableType")
    public String getVariableType() {
        return variableType;
    }

    @JsonProperty("variableType")
    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }

    public FBABiomassVariable withVariableType(String variableType) {
        this.variableType = variableType;
        return this;
    }

    @JsonProperty("upperBound")
    public Double getUpperBound() {
        return upperBound;
    }

    @JsonProperty("upperBound")
    public void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
    }

    public FBABiomassVariable withUpperBound(Double upperBound) {
        this.upperBound = upperBound;
        return this;
    }

    @JsonProperty("lowerBound")
    public Double getLowerBound() {
        return lowerBound;
    }

    @JsonProperty("lowerBound")
    public void setLowerBound(Double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public FBABiomassVariable withLowerBound(Double lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    public FBABiomassVariable withClass(String _class) {
        this._class = _class;
        return this;
    }

    @JsonProperty("min")
    public Double getMin() {
        return min;
    }

    @JsonProperty("min")
    public void setMin(Double min) {
        this.min = min;
    }

    public FBABiomassVariable withMin(Double min) {
        this.min = min;
        return this;
    }

    @JsonProperty("max")
    public Double getMax() {
        return max;
    }

    @JsonProperty("max")
    public void setMax(Double max) {
        this.max = max;
    }

    public FBABiomassVariable withMax(Double max) {
        this.max = max;
        return this;
    }

    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Double value) {
        this.value = value;
    }

    public FBABiomassVariable withValue(Double value) {
        this.value = value;
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
        return ((((((((((((((((((("FBABiomassVariable"+" [biomassRef=")+ biomassRef)+", variableType=")+ variableType)+", upperBound=")+ upperBound)+", lowerBound=")+ lowerBound)+", _class=")+ _class)+", min=")+ min)+", max=")+ max)+", value=")+ value)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
