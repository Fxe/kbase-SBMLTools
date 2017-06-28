
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
 * <p>Original spec-file type: Biochemistry</p>
 * <pre>
 * Biochemistry object
 * @optional description name
 * @searchable ws_subset compartments.[*].(id,name)
 * @searchable ws_subset compounds.[*].(id,name)
 * @searchable ws_subset reactions.[*].(id)
 * @searchable ws_subset cues.[*].(id,name,smallMolecule)
 * @searchable ws_subset reactionSets.[*].(id,name,class,reaction_refs,type)
 * @searchable ws_subset compoundSets.[*].(id,name,class,compound_refs,type)
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "description",
    "compartments",
    "compounds",
    "reactions",
    "reactionSets",
    "compoundSets",
    "cues",
    "compound_aliases",
    "reaction_aliases"
})
public class Biochemistry {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("description")
    private java.lang.String description;
    @JsonProperty("compartments")
    private List<Compartment> compartments;
    @JsonProperty("compounds")
    private List<Compound> compounds;
    @JsonProperty("reactions")
    private List<Reaction> reactions;
    @JsonProperty("reactionSets")
    private List<ReactionSet> reactionSets;
    @JsonProperty("compoundSets")
    private List<CompoundSet> compoundSets;
    @JsonProperty("cues")
    private List<Cue> cues;
    @JsonProperty("compound_aliases")
    private Map<String, Map<String, List<String>>> compoundAliases;
    @JsonProperty("reaction_aliases")
    private Map<String, Map<String, List<String>>> reactionAliases;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Biochemistry withId(java.lang.String id) {
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

    public Biochemistry withName(java.lang.String name) {
        this.name = name;
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

    public Biochemistry withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("compartments")
    public List<Compartment> getCompartments() {
        return compartments;
    }

    @JsonProperty("compartments")
    public void setCompartments(List<Compartment> compartments) {
        this.compartments = compartments;
    }

    public Biochemistry withCompartments(List<Compartment> compartments) {
        this.compartments = compartments;
        return this;
    }

    @JsonProperty("compounds")
    public List<Compound> getCompounds() {
        return compounds;
    }

    @JsonProperty("compounds")
    public void setCompounds(List<Compound> compounds) {
        this.compounds = compounds;
    }

    public Biochemistry withCompounds(List<Compound> compounds) {
        this.compounds = compounds;
        return this;
    }

    @JsonProperty("reactions")
    public List<Reaction> getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Biochemistry withReactions(List<Reaction> reactions) {
        this.reactions = reactions;
        return this;
    }

    @JsonProperty("reactionSets")
    public List<ReactionSet> getReactionSets() {
        return reactionSets;
    }

    @JsonProperty("reactionSets")
    public void setReactionSets(List<ReactionSet> reactionSets) {
        this.reactionSets = reactionSets;
    }

    public Biochemistry withReactionSets(List<ReactionSet> reactionSets) {
        this.reactionSets = reactionSets;
        return this;
    }

    @JsonProperty("compoundSets")
    public List<CompoundSet> getCompoundSets() {
        return compoundSets;
    }

    @JsonProperty("compoundSets")
    public void setCompoundSets(List<CompoundSet> compoundSets) {
        this.compoundSets = compoundSets;
    }

    public Biochemistry withCompoundSets(List<CompoundSet> compoundSets) {
        this.compoundSets = compoundSets;
        return this;
    }

    @JsonProperty("cues")
    public List<Cue> getCues() {
        return cues;
    }

    @JsonProperty("cues")
    public void setCues(List<Cue> cues) {
        this.cues = cues;
    }

    public Biochemistry withCues(List<Cue> cues) {
        this.cues = cues;
        return this;
    }

    @JsonProperty("compound_aliases")
    public Map<String, Map<String, List<String>>> getCompoundAliases() {
        return compoundAliases;
    }

    @JsonProperty("compound_aliases")
    public void setCompoundAliases(Map<String, Map<String, List<String>>> compoundAliases) {
        this.compoundAliases = compoundAliases;
    }

    public Biochemistry withCompoundAliases(Map<String, Map<String, List<String>>> compoundAliases) {
        this.compoundAliases = compoundAliases;
        return this;
    }

    @JsonProperty("reaction_aliases")
    public Map<String, Map<String, List<String>>> getReactionAliases() {
        return reactionAliases;
    }

    @JsonProperty("reaction_aliases")
    public void setReactionAliases(Map<String, Map<String, List<String>>> reactionAliases) {
        this.reactionAliases = reactionAliases;
    }

    public Biochemistry withReactionAliases(Map<String, Map<String, List<String>>> reactionAliases) {
        this.reactionAliases = reactionAliases;
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
        return ((((((((((((((((((((((((("Biochemistry"+" [id=")+ id)+", name=")+ name)+", description=")+ description)+", compartments=")+ compartments)+", compounds=")+ compounds)+", reactions=")+ reactions)+", reactionSets=")+ reactionSets)+", compoundSets=")+ compoundSets)+", cues=")+ cues)+", compoundAliases=")+ compoundAliases)+", reactionAliases=")+ reactionAliases)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
