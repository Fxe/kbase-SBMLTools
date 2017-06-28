
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
 * <p>Original spec-file type: FBABiomassVariable</p>
 * <pre>
 * FBABiomassVariable object
 * @optional other_values other_max other_min
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
    "value",
    "other_values",
    "other_max",
    "other_min"
})
public class FBABiomassVariable {

    @JsonProperty("biomass_ref")
    private String biomassRef;
    @JsonProperty("variableType")
    private String variableType;
    @JsonProperty("upperBound")
    private java.lang.Double upperBound;
    @JsonProperty("lowerBound")
    private java.lang.Double lowerBound;
    @JsonProperty("class")
    private String _class;
    @JsonProperty("min")
    private java.lang.Double min;
    @JsonProperty("max")
    private java.lang.Double max;
    @JsonProperty("value")
    private java.lang.Double value;
    @JsonProperty("other_values")
    private List<Double> otherValues;
    @JsonProperty("other_max")
    private List<Double> otherMax;
    @JsonProperty("other_min")
    private List<Double> otherMin;
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
    public java.lang.Double getUpperBound() {
        return upperBound;
    }

    @JsonProperty("upperBound")
    public void setUpperBound(java.lang.Double upperBound) {
        this.upperBound = upperBound;
    }

    public FBABiomassVariable withUpperBound(java.lang.Double upperBound) {
        this.upperBound = upperBound;
        return this;
    }

    @JsonProperty("lowerBound")
    public java.lang.Double getLowerBound() {
        return lowerBound;
    }

    @JsonProperty("lowerBound")
    public void setLowerBound(java.lang.Double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public FBABiomassVariable withLowerBound(java.lang.Double lowerBound) {
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
    public java.lang.Double getMin() {
        return min;
    }

    @JsonProperty("min")
    public void setMin(java.lang.Double min) {
        this.min = min;
    }

    public FBABiomassVariable withMin(java.lang.Double min) {
        this.min = min;
        return this;
    }

    @JsonProperty("max")
    public java.lang.Double getMax() {
        return max;
    }

    @JsonProperty("max")
    public void setMax(java.lang.Double max) {
        this.max = max;
    }

    public FBABiomassVariable withMax(java.lang.Double max) {
        this.max = max;
        return this;
    }

    @JsonProperty("value")
    public java.lang.Double getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(java.lang.Double value) {
        this.value = value;
    }

    public FBABiomassVariable withValue(java.lang.Double value) {
        this.value = value;
        return this;
    }

    @JsonProperty("other_values")
    public List<Double> getOtherValues() {
        return otherValues;
    }

    @JsonProperty("other_values")
    public void setOtherValues(List<Double> otherValues) {
        this.otherValues = otherValues;
    }

    public FBABiomassVariable withOtherValues(List<Double> otherValues) {
        this.otherValues = otherValues;
        return this;
    }

    @JsonProperty("other_max")
    public List<Double> getOtherMax() {
        return otherMax;
    }

    @JsonProperty("other_max")
    public void setOtherMax(List<Double> otherMax) {
        this.otherMax = otherMax;
    }

    public FBABiomassVariable withOtherMax(List<Double> otherMax) {
        this.otherMax = otherMax;
        return this;
    }

    @JsonProperty("other_min")
    public List<Double> getOtherMin() {
        return otherMin;
    }

    @JsonProperty("other_min")
    public void setOtherMin(List<Double> otherMin) {
        this.otherMin = otherMin;
    }

    public FBABiomassVariable withOtherMin(List<Double> otherMin) {
        this.otherMin = otherMin;
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
        return ((((((((((((((((((((((((("FBABiomassVariable"+" [biomassRef=")+ biomassRef)+", variableType=")+ variableType)+", upperBound=")+ upperBound)+", lowerBound=")+ lowerBound)+", _class=")+ _class)+", min=")+ min)+", max=")+ max)+", value=")+ value)+", otherValues=")+ otherValues)+", otherMax=")+ otherMax)+", otherMin=")+ otherMin)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
