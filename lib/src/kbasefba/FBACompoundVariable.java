
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
 * <p>Original spec-file type: FBACompoundVariable</p>
 * <pre>
 * FBACompoundVariable object
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
    "value"
})
public class FBACompoundVariable {

    @JsonProperty("modelcompound_ref")
    private String modelcompoundRef;
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
    public Double getUpperBound() {
        return upperBound;
    }

    @JsonProperty("upperBound")
    public void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
    }

    public FBACompoundVariable withUpperBound(Double upperBound) {
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

    public FBACompoundVariable withLowerBound(Double lowerBound) {
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
    public Double getMin() {
        return min;
    }

    @JsonProperty("min")
    public void setMin(Double min) {
        this.min = min;
    }

    public FBACompoundVariable withMin(Double min) {
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

    public FBACompoundVariable withMax(Double max) {
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

    public FBACompoundVariable withValue(Double value) {
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
        return ((((((((((((((((((("FBACompoundVariable"+" [modelcompoundRef=")+ modelcompoundRef)+", variableType=")+ variableType)+", upperBound=")+ upperBound)+", lowerBound=")+ lowerBound)+", _class=")+ _class)+", min=")+ min)+", max=")+ max)+", value=")+ value)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
