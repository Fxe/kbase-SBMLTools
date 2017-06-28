
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
 * <p>Original spec-file type: ReactionSensitivityAnalysisReaction</p>
 * <pre>
 * Object for holding reaction knockout sensitivity reaction data
 * kb_sub_id kbid - KBase ID for reaction knockout sensitivity reaction
 * ws_sub_id model_reaction_wsid - ID of model reaction
 * bool delete - indicates if reaction is to be deleted
 * bool deleted - indicates if the reaction has been deleted
 * float growth_fraction - Fraction of wild-type growth after knockout
 * float normalized_activated_reaction_count - Normalized number of activated reactions
 * list<ws_sub_id> biomass_compounds  - List of biomass compounds that depend on the reaction
 * list<ws_sub_id> new_inactive_rxns - List of new reactions dependant upon reaction KO
 * list<ws_sub_id> new_essentials - List of new essential genes with reaction knockout
 *         
 * @optional direction
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "modelreaction_ref",
    "growth_fraction",
    "delete",
    "deleted",
    "direction",
    "normalized_activated_reaction_count",
    "biomass_compounds",
    "new_inactive_rxns",
    "new_essentials"
})
public class ReactionSensitivityAnalysisReaction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("modelreaction_ref")
    private java.lang.String modelreactionRef;
    @JsonProperty("growth_fraction")
    private Double growthFraction;
    @JsonProperty("delete")
    private Long delete;
    @JsonProperty("deleted")
    private Long deleted;
    @JsonProperty("direction")
    private java.lang.String direction;
    @JsonProperty("normalized_activated_reaction_count")
    private Double normalizedActivatedReactionCount;
    @JsonProperty("biomass_compounds")
    private List<String> biomassCompounds;
    @JsonProperty("new_inactive_rxns")
    private List<String> newInactiveRxns;
    @JsonProperty("new_essentials")
    private List<String> newEssentials;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ReactionSensitivityAnalysisReaction withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("modelreaction_ref")
    public java.lang.String getModelreactionRef() {
        return modelreactionRef;
    }

    @JsonProperty("modelreaction_ref")
    public void setModelreactionRef(java.lang.String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
    }

    public ReactionSensitivityAnalysisReaction withModelreactionRef(java.lang.String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
        return this;
    }

    @JsonProperty("growth_fraction")
    public Double getGrowthFraction() {
        return growthFraction;
    }

    @JsonProperty("growth_fraction")
    public void setGrowthFraction(Double growthFraction) {
        this.growthFraction = growthFraction;
    }

    public ReactionSensitivityAnalysisReaction withGrowthFraction(Double growthFraction) {
        this.growthFraction = growthFraction;
        return this;
    }

    @JsonProperty("delete")
    public Long getDelete() {
        return delete;
    }

    @JsonProperty("delete")
    public void setDelete(Long delete) {
        this.delete = delete;
    }

    public ReactionSensitivityAnalysisReaction withDelete(Long delete) {
        this.delete = delete;
        return this;
    }

    @JsonProperty("deleted")
    public Long getDeleted() {
        return deleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(Long deleted) {
        this.deleted = deleted;
    }

    public ReactionSensitivityAnalysisReaction withDeleted(Long deleted) {
        this.deleted = deleted;
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

    public ReactionSensitivityAnalysisReaction withDirection(java.lang.String direction) {
        this.direction = direction;
        return this;
    }

    @JsonProperty("normalized_activated_reaction_count")
    public Double getNormalizedActivatedReactionCount() {
        return normalizedActivatedReactionCount;
    }

    @JsonProperty("normalized_activated_reaction_count")
    public void setNormalizedActivatedReactionCount(Double normalizedActivatedReactionCount) {
        this.normalizedActivatedReactionCount = normalizedActivatedReactionCount;
    }

    public ReactionSensitivityAnalysisReaction withNormalizedActivatedReactionCount(Double normalizedActivatedReactionCount) {
        this.normalizedActivatedReactionCount = normalizedActivatedReactionCount;
        return this;
    }

    @JsonProperty("biomass_compounds")
    public List<String> getBiomassCompounds() {
        return biomassCompounds;
    }

    @JsonProperty("biomass_compounds")
    public void setBiomassCompounds(List<String> biomassCompounds) {
        this.biomassCompounds = biomassCompounds;
    }

    public ReactionSensitivityAnalysisReaction withBiomassCompounds(List<String> biomassCompounds) {
        this.biomassCompounds = biomassCompounds;
        return this;
    }

    @JsonProperty("new_inactive_rxns")
    public List<String> getNewInactiveRxns() {
        return newInactiveRxns;
    }

    @JsonProperty("new_inactive_rxns")
    public void setNewInactiveRxns(List<String> newInactiveRxns) {
        this.newInactiveRxns = newInactiveRxns;
    }

    public ReactionSensitivityAnalysisReaction withNewInactiveRxns(List<String> newInactiveRxns) {
        this.newInactiveRxns = newInactiveRxns;
        return this;
    }

    @JsonProperty("new_essentials")
    public List<String> getNewEssentials() {
        return newEssentials;
    }

    @JsonProperty("new_essentials")
    public void setNewEssentials(List<String> newEssentials) {
        this.newEssentials = newEssentials;
    }

    public ReactionSensitivityAnalysisReaction withNewEssentials(List<String> newEssentials) {
        this.newEssentials = newEssentials;
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
        return ((((((((((((((((((((((("ReactionSensitivityAnalysisReaction"+" [id=")+ id)+", modelreactionRef=")+ modelreactionRef)+", growthFraction=")+ growthFraction)+", delete=")+ delete)+", deleted=")+ deleted)+", direction=")+ direction)+", normalizedActivatedReactionCount=")+ normalizedActivatedReactionCount)+", biomassCompounds=")+ biomassCompounds)+", newInactiveRxns=")+ newInactiveRxns)+", newEssentials=")+ newEssentials)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
