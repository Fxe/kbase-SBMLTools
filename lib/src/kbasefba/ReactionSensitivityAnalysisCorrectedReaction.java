
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
 * <p>Original spec-file type: ReactionSensitivityAnalysisCorrectedReaction</p>
 * <pre>
 * ReactionSensitivityAnalysisCorrectedReaction object
 * kb_sub_id kbid - KBase ID for reaction knockout corrected reaction
 * ws_sub_id model_reaction_wsid - ID of model reaction
 * float normalized_required_reaction_count - Normalized count of reactions required for this reaction to function
 * list<ws_sub_id> required_reactions - list of reactions required for this reaction to function
 * @optional
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "modelreaction_ref",
    "normalized_required_reaction_count",
    "required_reactions"
})
public class ReactionSensitivityAnalysisCorrectedReaction {

    @JsonProperty("modelreaction_ref")
    private java.lang.String modelreactionRef;
    @JsonProperty("normalized_required_reaction_count")
    private Double normalizedRequiredReactionCount;
    @JsonProperty("required_reactions")
    private List<String> requiredReactions;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("modelreaction_ref")
    public java.lang.String getModelreactionRef() {
        return modelreactionRef;
    }

    @JsonProperty("modelreaction_ref")
    public void setModelreactionRef(java.lang.String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
    }

    public ReactionSensitivityAnalysisCorrectedReaction withModelreactionRef(java.lang.String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
        return this;
    }

    @JsonProperty("normalized_required_reaction_count")
    public Double getNormalizedRequiredReactionCount() {
        return normalizedRequiredReactionCount;
    }

    @JsonProperty("normalized_required_reaction_count")
    public void setNormalizedRequiredReactionCount(Double normalizedRequiredReactionCount) {
        this.normalizedRequiredReactionCount = normalizedRequiredReactionCount;
    }

    public ReactionSensitivityAnalysisCorrectedReaction withNormalizedRequiredReactionCount(Double normalizedRequiredReactionCount) {
        this.normalizedRequiredReactionCount = normalizedRequiredReactionCount;
        return this;
    }

    @JsonProperty("required_reactions")
    public List<String> getRequiredReactions() {
        return requiredReactions;
    }

    @JsonProperty("required_reactions")
    public void setRequiredReactions(List<String> requiredReactions) {
        this.requiredReactions = requiredReactions;
    }

    public ReactionSensitivityAnalysisCorrectedReaction withRequiredReactions(List<String> requiredReactions) {
        this.requiredReactions = requiredReactions;
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
        return ((((((((("ReactionSensitivityAnalysisCorrectedReaction"+" [modelreactionRef=")+ modelreactionRef)+", normalizedRequiredReactionCount=")+ normalizedRequiredReactionCount)+", requiredReactions=")+ requiredReactions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
