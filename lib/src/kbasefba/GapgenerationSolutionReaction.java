
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
 * <p>Original spec-file type: GapgenerationSolutionReaction</p>
 * <pre>
 * GapGenerationSolutionReaction object holds data a reaction proposed to be removed from the model
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "modelreaction_ref",
    "direction"
})
public class GapgenerationSolutionReaction {

    @JsonProperty("modelreaction_ref")
    private String modelreactionRef;
    @JsonProperty("direction")
    private String direction;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("modelreaction_ref")
    public String getModelreactionRef() {
        return modelreactionRef;
    }

    @JsonProperty("modelreaction_ref")
    public void setModelreactionRef(String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
    }

    public GapgenerationSolutionReaction withModelreactionRef(String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
        return this;
    }

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public GapgenerationSolutionReaction withDirection(String direction) {
        this.direction = direction;
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
        return ((((((("GapgenerationSolutionReaction"+" [modelreactionRef=")+ modelreactionRef)+", direction=")+ direction)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
