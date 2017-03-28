
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
 * <p>Original spec-file type: ModelReactionReagent</p>
 * <pre>
 * ModelReactionReagent object
 *     @searchable ws_subset modelcompound_ref coefficient
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "modelcompound_ref",
    "coefficient"
})
public class ModelReactionReagent {

    @JsonProperty("modelcompound_ref")
    private String modelcompoundRef;
    @JsonProperty("coefficient")
    private Double coefficient;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("modelcompound_ref")
    public String getModelcompoundRef() {
        return modelcompoundRef;
    }

    @JsonProperty("modelcompound_ref")
    public void setModelcompoundRef(String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
    }

    public ModelReactionReagent withModelcompoundRef(String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
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

    public ModelReactionReagent withCoefficient(Double coefficient) {
        this.coefficient = coefficient;
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
        return ((((((("ModelReactionReagent"+" [modelcompoundRef=")+ modelcompoundRef)+", coefficient=")+ coefficient)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
