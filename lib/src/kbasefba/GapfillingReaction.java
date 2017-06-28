
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
 * <p>Original spec-file type: GapfillingReaction</p>
 * <pre>
 * GapFillingReaction object holds data on a reaction added by gapfilling analysis
 * @optional compartmentIndex round
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "round",
    "reaction_ref",
    "compartment_ref",
    "direction",
    "compartmentIndex",
    "candidateFeature_refs"
})
public class GapfillingReaction {

    @JsonProperty("round")
    private Long round;
    @JsonProperty("reaction_ref")
    private java.lang.String reactionRef;
    @JsonProperty("compartment_ref")
    private java.lang.String compartmentRef;
    @JsonProperty("direction")
    private java.lang.String direction;
    @JsonProperty("compartmentIndex")
    private Long compartmentIndex;
    @JsonProperty("candidateFeature_refs")
    private List<String> candidateFeatureRefs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("round")
    public Long getRound() {
        return round;
    }

    @JsonProperty("round")
    public void setRound(Long round) {
        this.round = round;
    }

    public GapfillingReaction withRound(Long round) {
        this.round = round;
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

    public GapfillingReaction withReactionRef(java.lang.String reactionRef) {
        this.reactionRef = reactionRef;
        return this;
    }

    @JsonProperty("compartment_ref")
    public java.lang.String getCompartmentRef() {
        return compartmentRef;
    }

    @JsonProperty("compartment_ref")
    public void setCompartmentRef(java.lang.String compartmentRef) {
        this.compartmentRef = compartmentRef;
    }

    public GapfillingReaction withCompartmentRef(java.lang.String compartmentRef) {
        this.compartmentRef = compartmentRef;
        return this;
    }

    @JsonProperty("direction")
    public java.lang.String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(java.lang.String direction) {
        this.direction = direction;
    }

    public GapfillingReaction withDirection(java.lang.String direction) {
        this.direction = direction;
        return this;
    }

    @JsonProperty("compartmentIndex")
    public Long getCompartmentIndex() {
        return compartmentIndex;
    }

    @JsonProperty("compartmentIndex")
    public void setCompartmentIndex(Long compartmentIndex) {
        this.compartmentIndex = compartmentIndex;
    }

    public GapfillingReaction withCompartmentIndex(Long compartmentIndex) {
        this.compartmentIndex = compartmentIndex;
        return this;
    }

    @JsonProperty("candidateFeature_refs")
    public List<String> getCandidateFeatureRefs() {
        return candidateFeatureRefs;
    }

    @JsonProperty("candidateFeature_refs")
    public void setCandidateFeatureRefs(List<String> candidateFeatureRefs) {
        this.candidateFeatureRefs = candidateFeatureRefs;
    }

    public GapfillingReaction withCandidateFeatureRefs(List<String> candidateFeatureRefs) {
        this.candidateFeatureRefs = candidateFeatureRefs;
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
        return ((((((((((((((("GapfillingReaction"+" [round=")+ round)+", reactionRef=")+ reactionRef)+", compartmentRef=")+ compartmentRef)+", direction=")+ direction)+", compartmentIndex=")+ compartmentIndex)+", candidateFeatureRefs=")+ candidateFeatureRefs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
