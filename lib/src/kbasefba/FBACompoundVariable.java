
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
 * <p>Original spec-file type: FBACompoundVariable</p>
 * <pre>
 * FBACompoundVariable object
 * @optional other_values other_max other_min
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "modelcompound_ref",
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
public class FBACompoundVariable {

    @JsonProperty("modelcompound_ref")
    private String modelcompoundRef;
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

    @JsonProperty("modelcompound_ref")
    public String getModelcompoundRef() {
        return modelcompoundRef;
    }

    @JsonProperty("modelcompound_ref")
    public void setModelcompoundRef(String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
    }

    public FBACompoundVariable withModelcompoundRef(String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
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

    public FBACompoundVariable withVariableType(String variableType) {
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

    public FBACompoundVariable withUpperBound(java.lang.Double upperBound) {
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

    public FBACompoundVariable withLowerBound(java.lang.Double lowerBound) {
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

    public FBACompoundVariable withClass(String _class) {
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

    public FBACompoundVariable withMin(java.lang.Double min) {
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

    public FBACompoundVariable withMax(java.lang.Double max) {
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

    public FBACompoundVariable withValue(java.lang.Double value) {
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

    public FBACompoundVariable withOtherValues(List<Double> otherValues) {
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

    public FBACompoundVariable withOtherMax(List<Double> otherMax) {
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

    public FBACompoundVariable withOtherMin(List<Double> otherMin) {
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
        return ((((((((((((((((((((((((("FBACompoundVariable"+" [modelcompoundRef=")+ modelcompoundRef)+", variableType=")+ variableType)+", upperBound=")+ upperBound)+", lowerBound=")+ lowerBound)+", _class=")+ _class)+", min=")+ min)+", max=")+ max)+", value=")+ value)+", otherValues=")+ otherValues)+", otherMax=")+ otherMax)+", otherMin=")+ otherMin)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
