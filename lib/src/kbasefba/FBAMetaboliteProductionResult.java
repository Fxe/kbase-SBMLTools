
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
 * <p>Original spec-file type: FBAMetaboliteProductionResult</p>
 * <pre>
 * FBAMetaboliteProductionResult object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "modelcompound_ref",
    "maximumProduction"
})
public class FBAMetaboliteProductionResult {

    @JsonProperty("modelcompound_ref")
    private String modelcompoundRef;
    @JsonProperty("maximumProduction")
    private Double maximumProduction;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("modelcompound_ref")
    public String getModelcompoundRef() {
        return modelcompoundRef;
    }

    @JsonProperty("modelcompound_ref")
    public void setModelcompoundRef(String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
    }

    public FBAMetaboliteProductionResult withModelcompoundRef(String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
        return this;
    }

    @JsonProperty("maximumProduction")
    public Double getMaximumProduction() {
        return maximumProduction;
    }

    @JsonProperty("maximumProduction")
    public void setMaximumProduction(Double maximumProduction) {
        this.maximumProduction = maximumProduction;
    }

    public FBAMetaboliteProductionResult withMaximumProduction(Double maximumProduction) {
        this.maximumProduction = maximumProduction;
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
        return ((((((("FBAMetaboliteProductionResult"+" [modelcompoundRef=")+ modelcompoundRef)+", maximumProduction=")+ maximumProduction)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
