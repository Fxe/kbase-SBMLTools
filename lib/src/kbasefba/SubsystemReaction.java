
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
 * <p>Original spec-file type: SubsystemReaction</p>
 * <pre>
 * SubsystemReaction object: this object holds information about individual reactions in a subsystems
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "reaction_ref",
    "roles",
    "tooltip"
})
public class SubsystemReaction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("reaction_ref")
    private java.lang.String reactionRef;
    @JsonProperty("roles")
    private List<String> roles;
    @JsonProperty("tooltip")
    private java.lang.String tooltip;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public SubsystemReaction withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("reaction_ref")
    public java.lang.String getReactionRef() {
        return reactionRef;
    }

    @JsonProperty("reaction_ref")
    public void setReactionRef(java.lang.String reactionRef) {
        this.reactionRef = reactionRef;
    }

    public SubsystemReaction withReactionRef(java.lang.String reactionRef) {
        this.reactionRef = reactionRef;
        return this;
    }

    @JsonProperty("roles")
    public List<String> getRoles() {
        return roles;
    }

    @JsonProperty("roles")
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public SubsystemReaction withRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    @JsonProperty("tooltip")
    public java.lang.String getTooltip() {
        return tooltip;
    }

    @JsonProperty("tooltip")
    public void setTooltip(java.lang.String tooltip) {
        this.tooltip = tooltip;
    }

    public SubsystemReaction withTooltip(java.lang.String tooltip) {
        this.tooltip = tooltip;
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
        return ((((((((((("SubsystemReaction"+" [id=")+ id)+", reactionRef=")+ reactionRef)+", roles=")+ roles)+", tooltip=")+ tooltip)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
