
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
 * <p>Original spec-file type: FBACompoundBound</p>
 * <pre>
 * FBACompoundBound object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "modelcompound_ref",
    "variableType",
    "upperBound",
    "lowerBound"
})
public class FBACompoundBound {

    @JsonProperty("modelcompound_ref")
    private String modelcompoundRef;
    @JsonProperty("variableType")
    private String variableType;
    @JsonProperty("upperBound")
    private Double upperBound;
    @JsonProperty("lowerBound")
    private Double lowerBound;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("modelcompound_ref")
    public String getModelcompoundRef() {
        return modelcompoundRef;
    }

    @JsonProperty("modelcompound_ref")
    public void setModelcompoundRef(String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
    }

    public FBACompoundBound withModelcompoundRef(String modelcompoundRef) {
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

    public FBACompoundBound withVariableType(String variableType) {
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

    public FBACompoundBound withUpperBound(Double upperBound) {
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

    public FBACompoundBound withLowerBound(Double lowerBound) {
        this.lowerBound = lowerBound;
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
        return ((((((((((("FBACompoundBound"+" [modelcompoundRef=")+ modelcompoundRef)+", variableType=")+ variableType)+", upperBound=")+ upperBound)+", lowerBound=")+ lowerBound)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
