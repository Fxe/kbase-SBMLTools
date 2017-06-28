
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
 * <p>Original spec-file type: QuantOptBoundMod</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "modelreaction_ref",
    "modelcompound_ref",
    "reaction",
    "mod_upperbound"
})
public class QuantOptBoundMod {

    @JsonProperty("modelreaction_ref")
    private String modelreactionRef;
    @JsonProperty("modelcompound_ref")
    private String modelcompoundRef;
    @JsonProperty("reaction")
    private Long reaction;
    @JsonProperty("mod_upperbound")
    private Double modUpperbound;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("modelreaction_ref")
    public String getModelreactionRef() {
        return modelreactionRef;
    }

    @JsonProperty("modelreaction_ref")
    public void setModelreactionRef(String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
    }

    public QuantOptBoundMod withModelreactionRef(String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
        return this;
    }

    @JsonProperty("modelcompound_ref")
    public String getModelcompoundRef() {
        return modelcompoundRef;
    }

    @JsonProperty("modelcompound_ref")
    public void setModelcompoundRef(String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
    }

    public QuantOptBoundMod withModelcompoundRef(String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
        return this;
    }

    @JsonProperty("reaction")
    public Long getReaction() {
        return reaction;
    }

    @JsonProperty("reaction")
    public void setReaction(Long reaction) {
        this.reaction = reaction;
    }

    public QuantOptBoundMod withReaction(Long reaction) {
        this.reaction = reaction;
        return this;
    }

    @JsonProperty("mod_upperbound")
    public Double getModUpperbound() {
        return modUpperbound;
    }

    @JsonProperty("mod_upperbound")
    public void setModUpperbound(Double modUpperbound) {
        this.modUpperbound = modUpperbound;
    }

    public QuantOptBoundMod withModUpperbound(Double modUpperbound) {
        this.modUpperbound = modUpperbound;
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
        return ((((((((((("QuantOptBoundMod"+" [modelreactionRef=")+ modelreactionRef)+", modelcompoundRef=")+ modelcompoundRef)+", reaction=")+ reaction)+", modUpperbound=")+ modUpperbound)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
