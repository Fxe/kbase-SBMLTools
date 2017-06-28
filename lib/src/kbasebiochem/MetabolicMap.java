
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
 * <p>Original spec-file type: MetabolicMap</p>
 * <pre>
 * MetabolicMap object
 * @optional description link
 * @metadata ws source_id as Source ID
 *     @metadata ws source as Source
 *     @metadata ws name as Name
 *     @metadata ws length(reactions) as Number reactions
 *     @metadata ws length(compounds) as Number compounds
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "source_id",
    "source",
    "link",
    "description",
    "reaction_ids",
    "compound_ids",
    "groups",
    "reactions",
    "compounds",
    "linkedmaps"
})
public class MetabolicMap {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    @JsonProperty("source")
    private java.lang.String source;
    @JsonProperty("link")
    private java.lang.String link;
    @JsonProperty("description")
    private java.lang.String description;
    @JsonProperty("reaction_ids")
    private List<String> reactionIds;
    @JsonProperty("compound_ids")
    private List<String> compoundIds;
    @JsonProperty("groups")
    private List<ReactionGroup> groups;
    @JsonProperty("reactions")
    private List<MapReaction> reactions;
    @JsonProperty("compounds")
    private List<MapCompound> compounds;
    @JsonProperty("linkedmaps")
    private List<MapLink> linkedmaps;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public MetabolicMap withId(java.lang.String id) {
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

    public MetabolicMap withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("source_id")
    public java.lang.String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
    }

    public MetabolicMap withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("source")
    public java.lang.String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(java.lang.String source) {
        this.source = source;
    }

    public MetabolicMap withSource(java.lang.String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("link")
    public java.lang.String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(java.lang.String link) {
        this.link = link;
    }

    public MetabolicMap withLink(java.lang.String link) {
        this.link = link;
        return this;
    }

    @JsonProperty("description")
    public java.lang.String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public MetabolicMap withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("reaction_ids")
    public List<String> getReactionIds() {
        return reactionIds;
    }

    @JsonProperty("reaction_ids")
    public void setReactionIds(List<String> reactionIds) {
        this.reactionIds = reactionIds;
    }

    public MetabolicMap withReactionIds(List<String> reactionIds) {
        this.reactionIds = reactionIds;
        return this;
    }

    @JsonProperty("compound_ids")
    public List<String> getCompoundIds() {
        return compoundIds;
    }

    @JsonProperty("compound_ids")
    public void setCompoundIds(List<String> compoundIds) {
        this.compoundIds = compoundIds;
    }

    public MetabolicMap withCompoundIds(List<String> compoundIds) {
        this.compoundIds = compoundIds;
        return this;
    }

    @JsonProperty("groups")
    public List<ReactionGroup> getGroups() {
        return groups;
    }

    @JsonProperty("groups")
    public void setGroups(List<ReactionGroup> groups) {
        this.groups = groups;
    }

    public MetabolicMap withGroups(List<ReactionGroup> groups) {
        this.groups = groups;
        return this;
    }

    @JsonProperty("reactions")
    public List<MapReaction> getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(List<MapReaction> reactions) {
        this.reactions = reactions;
    }

    public MetabolicMap withReactions(List<MapReaction> reactions) {
        this.reactions = reactions;
        return this;
    }

    @JsonProperty("compounds")
    public List<MapCompound> getCompounds() {
        return compounds;
    }

    @JsonProperty("compounds")
    public void setCompounds(List<MapCompound> compounds) {
        this.compounds = compounds;
    }

    public MetabolicMap withCompounds(List<MapCompound> compounds) {
        this.compounds = compounds;
        return this;
    }

    @JsonProperty("linkedmaps")
    public List<MapLink> getLinkedmaps() {
        return linkedmaps;
    }

    @JsonProperty("linkedmaps")
    public void setLinkedmaps(List<MapLink> linkedmaps) {
        this.linkedmaps = linkedmaps;
    }

    public MetabolicMap withLinkedmaps(List<MapLink> linkedmaps) {
        this.linkedmaps = linkedmaps;
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
        return ((((((((((((((((((((((((((("MetabolicMap"+" [id=")+ id)+", name=")+ name)+", sourceId=")+ sourceId)+", source=")+ source)+", link=")+ link)+", description=")+ description)+", reactionIds=")+ reactionIds)+", compoundIds=")+ compoundIds)+", groups=")+ groups)+", reactions=")+ reactions)+", compounds=")+ compounds)+", linkedmaps=")+ linkedmaps)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
