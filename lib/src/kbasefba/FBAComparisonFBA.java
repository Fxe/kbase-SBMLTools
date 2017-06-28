
package kbasefba;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple8;


/**
 * <p>Original spec-file type: FBAComparisonFBA</p>
 * <pre>
 * FBAComparisonFBA object: this object holds information about an FBA in a FBA comparison
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "fba_ref",
    "fbamodel_ref",
    "fba_similarity",
    "objective",
    "media_ref",
    "reactions",
    "compounds",
    "forward_reactions",
    "reverse_reactions",
    "uptake_compounds",
    "excretion_compounds"
})
public class FBAComparisonFBA {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("fba_ref")
    private java.lang.String fbaRef;
    @JsonProperty("fbamodel_ref")
    private java.lang.String fbamodelRef;
    @JsonProperty("fba_similarity")
    private Map<String, Tuple8 <Long, Long, Long, Long, Long, Long, Long, Long>> fbaSimilarity;
    @JsonProperty("objective")
    private Double objective;
    @JsonProperty("media_ref")
    private java.lang.String mediaRef;
    @JsonProperty("reactions")
    private java.lang.Long reactions;
    @JsonProperty("compounds")
    private java.lang.Long compounds;
    @JsonProperty("forward_reactions")
    private java.lang.Long forwardReactions;
    @JsonProperty("reverse_reactions")
    private java.lang.Long reverseReactions;
    @JsonProperty("uptake_compounds")
    private java.lang.Long uptakeCompounds;
    @JsonProperty("excretion_compounds")
    private java.lang.Long excretionCompounds;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FBAComparisonFBA withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("fba_ref")
    public java.lang.String getFbaRef() {
        return fbaRef;
    }

    @JsonProperty("fba_ref")
    public void setFbaRef(java.lang.String fbaRef) {
        this.fbaRef = fbaRef;
    }

    public FBAComparisonFBA withFbaRef(java.lang.String fbaRef) {
        this.fbaRef = fbaRef;
        return this;
    }

    @JsonProperty("fbamodel_ref")
    public java.lang.String getFbamodelRef() {
        return fbamodelRef;
    }

    @JsonProperty("fbamodel_ref")
    public void setFbamodelRef(java.lang.String fbamodelRef) {
        this.fbamodelRef = fbamodelRef;
    }

    public FBAComparisonFBA withFbamodelRef(java.lang.String fbamodelRef) {
        this.fbamodelRef = fbamodelRef;
        return this;
    }

    @JsonProperty("fba_similarity")
    public Map<String, Tuple8 <Long, Long, Long, Long, Long, Long, Long, Long>> getFbaSimilarity() {
        return fbaSimilarity;
    }

    @JsonProperty("fba_similarity")
    public void setFbaSimilarity(Map<String, Tuple8 <Long, Long, Long, Long, Long, Long, Long, Long>> fbaSimilarity) {
        this.fbaSimilarity = fbaSimilarity;
    }

    public FBAComparisonFBA withFbaSimilarity(Map<String, Tuple8 <Long, Long, Long, Long, Long, Long, Long, Long>> fbaSimilarity) {
        this.fbaSimilarity = fbaSimilarity;
        return this;
    }

    @JsonProperty("objective")
    public Double getObjective() {
        return objective;
    }

    @JsonProperty("objective")
    public void setObjective(Double objective) {
        this.objective = objective;
    }

    public FBAComparisonFBA withObjective(Double objective) {
        this.objective = objective;
        return this;
    }

    @JsonProperty("media_ref")
    public java.lang.String getMediaRef() {
        return mediaRef;
    }

    @JsonProperty("media_ref")
    public void setMediaRef(java.lang.String mediaRef) {
        this.mediaRef = mediaRef;
    }

    public FBAComparisonFBA withMediaRef(java.lang.String mediaRef) {
        this.mediaRef = mediaRef;
        return this;
    }

    @JsonProperty("reactions")
    public java.lang.Long getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(java.lang.Long reactions) {
        this.reactions = reactions;
    }

    public FBAComparisonFBA withReactions(java.lang.Long reactions) {
        this.reactions = reactions;
        return this;
    }

    @JsonProperty("compounds")
    public java.lang.Long getCompounds() {
        return compounds;
    }

    @JsonProperty("compounds")
    public void setCompounds(java.lang.Long compounds) {
        this.compounds = compounds;
    }

    public FBAComparisonFBA withCompounds(java.lang.Long compounds) {
        this.compounds = compounds;
        return this;
    }

    @JsonProperty("forward_reactions")
    public java.lang.Long getForwardReactions() {
        return forwardReactions;
    }

    @JsonProperty("forward_reactions")
    public void setForwardReactions(java.lang.Long forwardReactions) {
        this.forwardReactions = forwardReactions;
    }

    public FBAComparisonFBA withForwardReactions(java.lang.Long forwardReactions) {
        this.forwardReactions = forwardReactions;
        return this;
    }

    @JsonProperty("reverse_reactions")
    public java.lang.Long getReverseReactions() {
        return reverseReactions;
    }

    @JsonProperty("reverse_reactions")
    public void setReverseReactions(java.lang.Long reverseReactions) {
        this.reverseReactions = reverseReactions;
    }

    public FBAComparisonFBA withReverseReactions(java.lang.Long reverseReactions) {
        this.reverseReactions = reverseReactions;
        return this;
    }

    @JsonProperty("uptake_compounds")
    public java.lang.Long getUptakeCompounds() {
        return uptakeCompounds;
    }

    @JsonProperty("uptake_compounds")
    public void setUptakeCompounds(java.lang.Long uptakeCompounds) {
        this.uptakeCompounds = uptakeCompounds;
    }

    public FBAComparisonFBA withUptakeCompounds(java.lang.Long uptakeCompounds) {
        this.uptakeCompounds = uptakeCompounds;
        return this;
    }

    @JsonProperty("excretion_compounds")
    public java.lang.Long getExcretionCompounds() {
        return excretionCompounds;
    }

    @JsonProperty("excretion_compounds")
    public void setExcretionCompounds(java.lang.Long excretionCompounds) {
        this.excretionCompounds = excretionCompounds;
    }

    public FBAComparisonFBA withExcretionCompounds(java.lang.Long excretionCompounds) {
        this.excretionCompounds = excretionCompounds;
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
        return ((((((((((((((((((((((((((("FBAComparisonFBA"+" [id=")+ id)+", fbaRef=")+ fbaRef)+", fbamodelRef=")+ fbamodelRef)+", fbaSimilarity=")+ fbaSimilarity)+", objective=")+ objective)+", mediaRef=")+ mediaRef)+", reactions=")+ reactions)+", compounds=")+ compounds)+", forwardReactions=")+ forwardReactions)+", reverseReactions=")+ reverseReactions)+", uptakeCompounds=")+ uptakeCompounds)+", excretionCompounds=")+ excretionCompounds)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
