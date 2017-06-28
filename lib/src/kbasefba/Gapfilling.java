
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
 * <p>Original spec-file type: Gapfilling</p>
 * <pre>
 * GapFilling object holds data on the formulations and solutions of a gapfilling analysis
 * @optional simultaneousGapfill totalTimeLimit timePerSolution transporterMultiplier singleTransporterMultiplier biomassTransporterMultiplier noDeltaGMultiplier noStructureMultiplier deltaGMultiplier directionalityMultiplier drainFluxMultiplier reactionActivationBonus allowableCompartment_refs blacklistedReaction_refs targetedreaction_refs guaranteedReaction_refs completeGapfill balancedReactionsOnly reactionAdditionHypothesis gprHypothesis biomassHypothesis mediaHypothesis fba_ref media_ref probanno_ref
 * @metadata ws fba_ref as FBA
 *     @metadata ws fbamodel_ref as Model
 *     @metadata ws media_ref as Media
 *     @metadata ws length(gapfillingSolutions) as Number solutions
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "fba_ref",
    "media_ref",
    "fbamodel_ref",
    "probanno_ref",
    "mediaHypothesis",
    "biomassHypothesis",
    "gprHypothesis",
    "reactionAdditionHypothesis",
    "balancedReactionsOnly",
    "completeGapfill",
    "simultaneousGapfill",
    "guaranteedReaction_refs",
    "targetedreaction_refs",
    "blacklistedReaction_refs",
    "allowableCompartment_refs",
    "reactionActivationBonus",
    "drainFluxMultiplier",
    "directionalityMultiplier",
    "deltaGMultiplier",
    "noStructureMultiplier",
    "noDeltaGMultiplier",
    "biomassTransporterMultiplier",
    "singleTransporterMultiplier",
    "transporterMultiplier",
    "timePerSolution",
    "totalTimeLimit",
    "reactionMultipliers",
    "gapfillingSolutions"
})
public class Gapfilling {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("fba_ref")
    private java.lang.String fbaRef;
    @JsonProperty("media_ref")
    private java.lang.String mediaRef;
    @JsonProperty("fbamodel_ref")
    private java.lang.String fbamodelRef;
    @JsonProperty("probanno_ref")
    private java.lang.String probannoRef;
    @JsonProperty("mediaHypothesis")
    private Long mediaHypothesis;
    @JsonProperty("biomassHypothesis")
    private Long biomassHypothesis;
    @JsonProperty("gprHypothesis")
    private Long gprHypothesis;
    @JsonProperty("reactionAdditionHypothesis")
    private Long reactionAdditionHypothesis;
    @JsonProperty("balancedReactionsOnly")
    private Long balancedReactionsOnly;
    @JsonProperty("completeGapfill")
    private Long completeGapfill;
    @JsonProperty("simultaneousGapfill")
    private Long simultaneousGapfill;
    @JsonProperty("guaranteedReaction_refs")
    private List<String> guaranteedReactionRefs;
    @JsonProperty("targetedreaction_refs")
    private List<String> targetedreactionRefs;
    @JsonProperty("blacklistedReaction_refs")
    private List<String> blacklistedReactionRefs;
    @JsonProperty("allowableCompartment_refs")
    private List<String> allowableCompartmentRefs;
    @JsonProperty("reactionActivationBonus")
    private java.lang.Double reactionActivationBonus;
    @JsonProperty("drainFluxMultiplier")
    private java.lang.Double drainFluxMultiplier;
    @JsonProperty("directionalityMultiplier")
    private java.lang.Double directionalityMultiplier;
    @JsonProperty("deltaGMultiplier")
    private java.lang.Double deltaGMultiplier;
    @JsonProperty("noStructureMultiplier")
    private java.lang.Double noStructureMultiplier;
    @JsonProperty("noDeltaGMultiplier")
    private java.lang.Double noDeltaGMultiplier;
    @JsonProperty("biomassTransporterMultiplier")
    private java.lang.Double biomassTransporterMultiplier;
    @JsonProperty("singleTransporterMultiplier")
    private java.lang.Double singleTransporterMultiplier;
    @JsonProperty("transporterMultiplier")
    private java.lang.Double transporterMultiplier;
    @JsonProperty("timePerSolution")
    private Long timePerSolution;
    @JsonProperty("totalTimeLimit")
    private Long totalTimeLimit;
    @JsonProperty("reactionMultipliers")
    private Map<String, Double> reactionMultipliers;
    @JsonProperty("gapfillingSolutions")
    private List<GapfillingSolution> gapfillingSolutions;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Gapfilling withId(java.lang.String id) {
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

    public Gapfilling withFbaRef(java.lang.String fbaRef) {
        this.fbaRef = fbaRef;
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

    public Gapfilling withMediaRef(java.lang.String mediaRef) {
        this.mediaRef = mediaRef;
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

    public Gapfilling withFbamodelRef(java.lang.String fbamodelRef) {
        this.fbamodelRef = fbamodelRef;
        return this;
    }

    @JsonProperty("probanno_ref")
    public java.lang.String getProbannoRef() {
        return probannoRef;
    }

    @JsonProperty("probanno_ref")
    public void setProbannoRef(java.lang.String probannoRef) {
        this.probannoRef = probannoRef;
    }

    public Gapfilling withProbannoRef(java.lang.String probannoRef) {
        this.probannoRef = probannoRef;
        return this;
    }

    @JsonProperty("mediaHypothesis")
    public Long getMediaHypothesis() {
        return mediaHypothesis;
    }

    @JsonProperty("mediaHypothesis")
    public void setMediaHypothesis(Long mediaHypothesis) {
        this.mediaHypothesis = mediaHypothesis;
    }

    public Gapfilling withMediaHypothesis(Long mediaHypothesis) {
        this.mediaHypothesis = mediaHypothesis;
        return this;
    }

    @JsonProperty("biomassHypothesis")
    public Long getBiomassHypothesis() {
        return biomassHypothesis;
    }

    @JsonProperty("biomassHypothesis")
    public void setBiomassHypothesis(Long biomassHypothesis) {
        this.biomassHypothesis = biomassHypothesis;
    }

    public Gapfilling withBiomassHypothesis(Long biomassHypothesis) {
        this.biomassHypothesis = biomassHypothesis;
        return this;
    }

    @JsonProperty("gprHypothesis")
    public Long getGprHypothesis() {
        return gprHypothesis;
    }

    @JsonProperty("gprHypothesis")
    public void setGprHypothesis(Long gprHypothesis) {
        this.gprHypothesis = gprHypothesis;
    }

    public Gapfilling withGprHypothesis(Long gprHypothesis) {
        this.gprHypothesis = gprHypothesis;
        return this;
    }

    @JsonProperty("reactionAdditionHypothesis")
    public Long getReactionAdditionHypothesis() {
        return reactionAdditionHypothesis;
    }

    @JsonProperty("reactionAdditionHypothesis")
    public void setReactionAdditionHypothesis(Long reactionAdditionHypothesis) {
        this.reactionAdditionHypothesis = reactionAdditionHypothesis;
    }

    public Gapfilling withReactionAdditionHypothesis(Long reactionAdditionHypothesis) {
        this.reactionAdditionHypothesis = reactionAdditionHypothesis;
        return this;
    }

    @JsonProperty("balancedReactionsOnly")
    public Long getBalancedReactionsOnly() {
        return balancedReactionsOnly;
    }

    @JsonProperty("balancedReactionsOnly")
    public void setBalancedReactionsOnly(Long balancedReactionsOnly) {
        this.balancedReactionsOnly = balancedReactionsOnly;
    }

    public Gapfilling withBalancedReactionsOnly(Long balancedReactionsOnly) {
        this.balancedReactionsOnly = balancedReactionsOnly;
        return this;
    }

    @JsonProperty("completeGapfill")
    public Long getCompleteGapfill() {
        return completeGapfill;
    }

    @JsonProperty("completeGapfill")
    public void setCompleteGapfill(Long completeGapfill) {
        this.completeGapfill = completeGapfill;
    }

    public Gapfilling withCompleteGapfill(Long completeGapfill) {
        this.completeGapfill = completeGapfill;
        return this;
    }

    @JsonProperty("simultaneousGapfill")
    public Long getSimultaneousGapfill() {
        return simultaneousGapfill;
    }

    @JsonProperty("simultaneousGapfill")
    public void setSimultaneousGapfill(Long simultaneousGapfill) {
        this.simultaneousGapfill = simultaneousGapfill;
    }

    public Gapfilling withSimultaneousGapfill(Long simultaneousGapfill) {
        this.simultaneousGapfill = simultaneousGapfill;
        return this;
    }

    @JsonProperty("guaranteedReaction_refs")
    public List<String> getGuaranteedReactionRefs() {
        return guaranteedReactionRefs;
    }

    @JsonProperty("guaranteedReaction_refs")
    public void setGuaranteedReactionRefs(List<String> guaranteedReactionRefs) {
        this.guaranteedReactionRefs = guaranteedReactionRefs;
    }

    public Gapfilling withGuaranteedReactionRefs(List<String> guaranteedReactionRefs) {
        this.guaranteedReactionRefs = guaranteedReactionRefs;
        return this;
    }

    @JsonProperty("targetedreaction_refs")
    public List<String> getTargetedreactionRefs() {
        return targetedreactionRefs;
    }

    @JsonProperty("targetedreaction_refs")
    public void setTargetedreactionRefs(List<String> targetedreactionRefs) {
        this.targetedreactionRefs = targetedreactionRefs;
    }

    public Gapfilling withTargetedreactionRefs(List<String> targetedreactionRefs) {
        this.targetedreactionRefs = targetedreactionRefs;
        return this;
    }

    @JsonProperty("blacklistedReaction_refs")
    public List<String> getBlacklistedReactionRefs() {
        return blacklistedReactionRefs;
    }

    @JsonProperty("blacklistedReaction_refs")
    public void setBlacklistedReactionRefs(List<String> blacklistedReactionRefs) {
        this.blacklistedReactionRefs = blacklistedReactionRefs;
    }

    public Gapfilling withBlacklistedReactionRefs(List<String> blacklistedReactionRefs) {
        this.blacklistedReactionRefs = blacklistedReactionRefs;
        return this;
    }

    @JsonProperty("allowableCompartment_refs")
    public List<String> getAllowableCompartmentRefs() {
        return allowableCompartmentRefs;
    }

    @JsonProperty("allowableCompartment_refs")
    public void setAllowableCompartmentRefs(List<String> allowableCompartmentRefs) {
        this.allowableCompartmentRefs = allowableCompartmentRefs;
    }

    public Gapfilling withAllowableCompartmentRefs(List<String> allowableCompartmentRefs) {
        this.allowableCompartmentRefs = allowableCompartmentRefs;
        return this;
    }

    @JsonProperty("reactionActivationBonus")
    public java.lang.Double getReactionActivationBonus() {
        return reactionActivationBonus;
    }

    @JsonProperty("reactionActivationBonus")
    public void setReactionActivationBonus(java.lang.Double reactionActivationBonus) {
        this.reactionActivationBonus = reactionActivationBonus;
    }

    public Gapfilling withReactionActivationBonus(java.lang.Double reactionActivationBonus) {
        this.reactionActivationBonus = reactionActivationBonus;
        return this;
    }

    @JsonProperty("drainFluxMultiplier")
    public java.lang.Double getDrainFluxMultiplier() {
        return drainFluxMultiplier;
    }

    @JsonProperty("drainFluxMultiplier")
    public void setDrainFluxMultiplier(java.lang.Double drainFluxMultiplier) {
        this.drainFluxMultiplier = drainFluxMultiplier;
    }

    public Gapfilling withDrainFluxMultiplier(java.lang.Double drainFluxMultiplier) {
        this.drainFluxMultiplier = drainFluxMultiplier;
        return this;
    }

    @JsonProperty("directionalityMultiplier")
    public java.lang.Double getDirectionalityMultiplier() {
        return directionalityMultiplier;
    }

    @JsonProperty("directionalityMultiplier")
    public void setDirectionalityMultiplier(java.lang.Double directionalityMultiplier) {
        this.directionalityMultiplier = directionalityMultiplier;
    }

    public Gapfilling withDirectionalityMultiplier(java.lang.Double directionalityMultiplier) {
        this.directionalityMultiplier = directionalityMultiplier;
        return this;
    }

    @JsonProperty("deltaGMultiplier")
    public java.lang.Double getDeltaGMultiplier() {
        return deltaGMultiplier;
    }

    @JsonProperty("deltaGMultiplier")
    public void setDeltaGMultiplier(java.lang.Double deltaGMultiplier) {
        this.deltaGMultiplier = deltaGMultiplier;
    }

    public Gapfilling withDeltaGMultiplier(java.lang.Double deltaGMultiplier) {
        this.deltaGMultiplier = deltaGMultiplier;
        return this;
    }

    @JsonProperty("noStructureMultiplier")
    public java.lang.Double getNoStructureMultiplier() {
        return noStructureMultiplier;
    }

    @JsonProperty("noStructureMultiplier")
    public void setNoStructureMultiplier(java.lang.Double noStructureMultiplier) {
        this.noStructureMultiplier = noStructureMultiplier;
    }

    public Gapfilling withNoStructureMultiplier(java.lang.Double noStructureMultiplier) {
        this.noStructureMultiplier = noStructureMultiplier;
        return this;
    }

    @JsonProperty("noDeltaGMultiplier")
    public java.lang.Double getNoDeltaGMultiplier() {
        return noDeltaGMultiplier;
    }

    @JsonProperty("noDeltaGMultiplier")
    public void setNoDeltaGMultiplier(java.lang.Double noDeltaGMultiplier) {
        this.noDeltaGMultiplier = noDeltaGMultiplier;
    }

    public Gapfilling withNoDeltaGMultiplier(java.lang.Double noDeltaGMultiplier) {
        this.noDeltaGMultiplier = noDeltaGMultiplier;
        return this;
    }

    @JsonProperty("biomassTransporterMultiplier")
    public java.lang.Double getBiomassTransporterMultiplier() {
        return biomassTransporterMultiplier;
    }

    @JsonProperty("biomassTransporterMultiplier")
    public void setBiomassTransporterMultiplier(java.lang.Double biomassTransporterMultiplier) {
        this.biomassTransporterMultiplier = biomassTransporterMultiplier;
    }

    public Gapfilling withBiomassTransporterMultiplier(java.lang.Double biomassTransporterMultiplier) {
        this.biomassTransporterMultiplier = biomassTransporterMultiplier;
        return this;
    }

    @JsonProperty("singleTransporterMultiplier")
    public java.lang.Double getSingleTransporterMultiplier() {
        return singleTransporterMultiplier;
    }

    @JsonProperty("singleTransporterMultiplier")
    public void setSingleTransporterMultiplier(java.lang.Double singleTransporterMultiplier) {
        this.singleTransporterMultiplier = singleTransporterMultiplier;
    }

    public Gapfilling withSingleTransporterMultiplier(java.lang.Double singleTransporterMultiplier) {
        this.singleTransporterMultiplier = singleTransporterMultiplier;
        return this;
    }

    @JsonProperty("transporterMultiplier")
    public java.lang.Double getTransporterMultiplier() {
        return transporterMultiplier;
    }

    @JsonProperty("transporterMultiplier")
    public void setTransporterMultiplier(java.lang.Double transporterMultiplier) {
        this.transporterMultiplier = transporterMultiplier;
    }

    public Gapfilling withTransporterMultiplier(java.lang.Double transporterMultiplier) {
        this.transporterMultiplier = transporterMultiplier;
        return this;
    }

    @JsonProperty("timePerSolution")
    public Long getTimePerSolution() {
        return timePerSolution;
    }

    @JsonProperty("timePerSolution")
    public void setTimePerSolution(Long timePerSolution) {
        this.timePerSolution = timePerSolution;
    }

    public Gapfilling withTimePerSolution(Long timePerSolution) {
        this.timePerSolution = timePerSolution;
        return this;
    }

    @JsonProperty("totalTimeLimit")
    public Long getTotalTimeLimit() {
        return totalTimeLimit;
    }

    @JsonProperty("totalTimeLimit")
    public void setTotalTimeLimit(Long totalTimeLimit) {
        this.totalTimeLimit = totalTimeLimit;
    }

    public Gapfilling withTotalTimeLimit(Long totalTimeLimit) {
        this.totalTimeLimit = totalTimeLimit;
        return this;
    }

    @JsonProperty("reactionMultipliers")
    public Map<String, Double> getReactionMultipliers() {
        return reactionMultipliers;
    }

    @JsonProperty("reactionMultipliers")
    public void setReactionMultipliers(Map<String, Double> reactionMultipliers) {
        this.reactionMultipliers = reactionMultipliers;
    }

    public Gapfilling withReactionMultipliers(Map<String, Double> reactionMultipliers) {
        this.reactionMultipliers = reactionMultipliers;
        return this;
    }

    @JsonProperty("gapfillingSolutions")
    public List<GapfillingSolution> getGapfillingSolutions() {
        return gapfillingSolutions;
    }

    @JsonProperty("gapfillingSolutions")
    public void setGapfillingSolutions(List<GapfillingSolution> gapfillingSolutions) {
        this.gapfillingSolutions = gapfillingSolutions;
    }

    public Gapfilling withGapfillingSolutions(List<GapfillingSolution> gapfillingSolutions) {
        this.gapfillingSolutions = gapfillingSolutions;
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
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((("Gapfilling"+" [id=")+ id)+", fbaRef=")+ fbaRef)+", mediaRef=")+ mediaRef)+", fbamodelRef=")+ fbamodelRef)+", probannoRef=")+ probannoRef)+", mediaHypothesis=")+ mediaHypothesis)+", biomassHypothesis=")+ biomassHypothesis)+", gprHypothesis=")+ gprHypothesis)+", reactionAdditionHypothesis=")+ reactionAdditionHypothesis)+", balancedReactionsOnly=")+ balancedReactionsOnly)+", completeGapfill=")+ completeGapfill)+", simultaneousGapfill=")+ simultaneousGapfill)+", guaranteedReactionRefs=")+ guaranteedReactionRefs)+", targetedreactionRefs=")+ targetedreactionRefs)+", blacklistedReactionRefs=")+ blacklistedReactionRefs)+", allowableCompartmentRefs=")+ allowableCompartmentRefs)+", reactionActivationBonus=")+ reactionActivationBonus)+", drainFluxMultiplier=")+ drainFluxMultiplier)+", directionalityMultiplier=")+ directionalityMultiplier)+", deltaGMultiplier=")+ deltaGMultiplier)+", noStructureMultiplier=")+ noStructureMultiplier)+", noDeltaGMultiplier=")+ noDeltaGMultiplier)+", biomassTransporterMultiplier=")+ biomassTransporterMultiplier)+", singleTransporterMultiplier=")+ singleTransporterMultiplier)+", transporterMultiplier=")+ transporterMultiplier)+", timePerSolution=")+ timePerSolution)+", totalTimeLimit=")+ totalTimeLimit)+", reactionMultipliers=")+ reactionMultipliers)+", gapfillingSolutions=")+ gapfillingSolutions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
