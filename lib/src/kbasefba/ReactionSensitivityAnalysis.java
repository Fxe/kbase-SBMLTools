
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
 * <p>Original spec-file type: ReactionSensitivityAnalysis</p>
 * <pre>
 * Object for holding reaction knockout sensitivity results
 *         kb_id kbid - KBase ID of reaction sensitivity object
 *         ws_id model_wsid - Workspace reference to associated model
 *         string type - type of reaction KO sensitivity object
 *         bool deleted_noncontributing_reactions - boolean indicating if noncontributing reactions were deleted
 *         bool integrated_deletions_in_model - boolean indicating if deleted reactions were implemented in the model
 *         list<ReactionSensitivityAnalysisReaction> reactions - list of sensitivity data for tested reactions
 *         list<ReactionSensitivityAnalysisCorrectedReaction> corrected_reactions - list of reactions dependant upon tested reactions
 *         
 *         @searchable ws_subset id fbamodel_ref type deleted_noncontributing_reactions integrated_deletions_in_model
 *         @optional
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "fbamodel_ref",
    "type",
    "deleted_noncontributing_reactions",
    "integrated_deletions_in_model",
    "reactions",
    "corrected_reactions"
})
public class ReactionSensitivityAnalysis {

    @JsonProperty("id")
    private String id;
    @JsonProperty("fbamodel_ref")
    private String fbamodelRef;
    @JsonProperty("type")
    private String type;
    @JsonProperty("deleted_noncontributing_reactions")
    private Long deletedNoncontributingReactions;
    @JsonProperty("integrated_deletions_in_model")
    private Long integratedDeletionsInModel;
    @JsonProperty("reactions")
    private List<ReactionSensitivityAnalysisReaction> reactions;
    @JsonProperty("corrected_reactions")
    private List<ReactionSensitivityAnalysisCorrectedReaction> correctedReactions;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ReactionSensitivityAnalysis withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("fbamodel_ref")
    public String getFbamodelRef() {
        return fbamodelRef;
    }

    @JsonProperty("fbamodel_ref")
    public void setFbamodelRef(String fbamodelRef) {
        this.fbamodelRef = fbamodelRef;
    }

    public ReactionSensitivityAnalysis withFbamodelRef(String fbamodelRef) {
        this.fbamodelRef = fbamodelRef;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public ReactionSensitivityAnalysis withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("deleted_noncontributing_reactions")
    public Long getDeletedNoncontributingReactions() {
        return deletedNoncontributingReactions;
    }

    @JsonProperty("deleted_noncontributing_reactions")
    public void setDeletedNoncontributingReactions(Long deletedNoncontributingReactions) {
        this.deletedNoncontributingReactions = deletedNoncontributingReactions;
    }

    public ReactionSensitivityAnalysis withDeletedNoncontributingReactions(Long deletedNoncontributingReactions) {
        this.deletedNoncontributingReactions = deletedNoncontributingReactions;
        return this;
    }

    @JsonProperty("integrated_deletions_in_model")
    public Long getIntegratedDeletionsInModel() {
        return integratedDeletionsInModel;
    }

    @JsonProperty("integrated_deletions_in_model")
    public void setIntegratedDeletionsInModel(Long integratedDeletionsInModel) {
        this.integratedDeletionsInModel = integratedDeletionsInModel;
    }

    public ReactionSensitivityAnalysis withIntegratedDeletionsInModel(Long integratedDeletionsInModel) {
        this.integratedDeletionsInModel = integratedDeletionsInModel;
        return this;
    }

    @JsonProperty("reactions")
    public List<ReactionSensitivityAnalysisReaction> getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(List<ReactionSensitivityAnalysisReaction> reactions) {
        this.reactions = reactions;
    }

    public ReactionSensitivityAnalysis withReactions(List<ReactionSensitivityAnalysisReaction> reactions) {
        this.reactions = reactions;
        return this;
    }

    @JsonProperty("corrected_reactions")
    public List<ReactionSensitivityAnalysisCorrectedReaction> getCorrectedReactions() {
        return correctedReactions;
    }

    @JsonProperty("corrected_reactions")
    public void setCorrectedReactions(List<ReactionSensitivityAnalysisCorrectedReaction> correctedReactions) {
        this.correctedReactions = correctedReactions;
    }

    public ReactionSensitivityAnalysis withCorrectedReactions(List<ReactionSensitivityAnalysisCorrectedReaction> correctedReactions) {
        this.correctedReactions = correctedReactions;
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
        return ((((((((((((((((("ReactionSensitivityAnalysis"+" [id=")+ id)+", fbamodelRef=")+ fbamodelRef)+", type=")+ type)+", deletedNoncontributingReactions=")+ deletedNoncontributingReactions)+", integratedDeletionsInModel=")+ integratedDeletionsInModel)+", reactions=")+ reactions)+", correctedReactions=")+ correctedReactions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
