
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
 * <p>Original spec-file type: ETCStep</p>
 * <pre>
 * ETCStep object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "reactions"
})
public class ETCStep {

    @JsonProperty("reactions")
    private List<String> reactions;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("reactions")
    public List<String> getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(List<String> reactions) {
        this.reactions = reactions;
    }

    public ETCStep withReactions(List<String> reactions) {
        this.reactions = reactions;
        return this;
    }

    @JsonAnyGetter
    public Map<java.lang.String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(java.lang.String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public java.lang.String toString() {
        return ((((("ETCStep"+" [reactions=")+ reactions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
