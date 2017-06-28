
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
 * <p>Original spec-file type: ActivatedReaction</p>
 * <pre>
 * ActivatedReaction object holds data on a reaction activated by gapfilling analysis
 * @optional round
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "round",
    "modelreaction_ref"
})
public class ActivatedReaction {

    @JsonProperty("round")
    private Long round;
    @JsonProperty("modelreaction_ref")
    private String modelreactionRef;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("round")
    public Long getRound() {
        return round;
    }

    @JsonProperty("round")
    public void setRound(Long round) {
        this.round = round;
    }

    public ActivatedReaction withRound(Long round) {
        this.round = round;
        return this;
    }

    @JsonProperty("modelreaction_ref")
    public String getModelreactionRef() {
        return modelreactionRef;
    }

    @JsonProperty("modelreaction_ref")
    public void setModelreactionRef(String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
    }

    public ActivatedReaction withModelreactionRef(String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
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
        return ((((((("ActivatedReaction"+" [round=")+ round)+", modelreactionRef=")+ modelreactionRef)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
