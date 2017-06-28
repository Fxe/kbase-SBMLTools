
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
 * <p>Original spec-file type: ReactionItem</p>
 * <pre>
 * description of a role missing in the contigs
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "reaction_id",
    "reaction_name"
})
public class ReactionItem {

    @JsonProperty("reaction_id")
    private String reactionId;
    @JsonProperty("reaction_name")
    private String reactionName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("reaction_id")
    public String getReactionId() {
        return reactionId;
    }

    @JsonProperty("reaction_id")
    public void setReactionId(String reactionId) {
        this.reactionId = reactionId;
    }

    public ReactionItem withReactionId(String reactionId) {
        this.reactionId = reactionId;
        return this;
    }

    @JsonProperty("reaction_name")
    public String getReactionName() {
        return reactionName;
    }

    @JsonProperty("reaction_name")
    public void setReactionName(String reactionName) {
        this.reactionName = reactionName;
    }

    public ReactionItem withReactionName(String reactionName) {
        this.reactionName = reactionName;
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
        return ((((((("ReactionItem"+" [reactionId=")+ reactionId)+", reactionName=")+ reactionName)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
