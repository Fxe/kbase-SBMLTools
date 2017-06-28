
package kbasebiochem;

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
 * <p>Original spec-file type: ReactionSet</p>
 * <pre>
 * ReactionSet object
 * @searchable ws_subset id name class reaction_refs type
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "class",
    "type",
    "reaction_refs"
})
public class ReactionSet {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("class")
    private java.lang.String _class;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("reaction_refs")
    private List<String> reactionRefs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ReactionSet withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public ReactionSet withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("class")
    public java.lang.String getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(java.lang.String _class) {
        this._class = _class;
    }

    public ReactionSet withClass(java.lang.String _class) {
        this._class = _class;
        return this;
    }

    @JsonProperty("type")
    public java.lang.String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(java.lang.String type) {
        this.type = type;
    }

    public ReactionSet withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("reaction_refs")
    public List<String> getReactionRefs() {
        return reactionRefs;
    }

    @JsonProperty("reaction_refs")
    public void setReactionRefs(List<String> reactionRefs) {
        this.reactionRefs = reactionRefs;
    }

    public ReactionSet withReactionRefs(List<String> reactionRefs) {
        this.reactionRefs = reactionRefs;
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
        return ((((((((((((("ReactionSet"+" [id=")+ id)+", name=")+ name)+", _class=")+ _class)+", type=")+ type)+", reactionRefs=")+ reactionRefs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
