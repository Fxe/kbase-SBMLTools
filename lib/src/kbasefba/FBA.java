
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
 * <p>Original spec-file type: FBA</p>
 * <pre>
 * FBA object holds the formulation and results of a flux balance analysis study
 * @optional other_objectives mediaset_ref media_list_refs MFALog maximizeActiveReactions calculateReactionKnockoutSensitivity biomassRemovals ExpressionKappa ExpressionOmega ExpressionAlpha expression_matrix_ref expression_matrix_column jobnode gapfillingSolutions QuantitativeOptimizationSolutions quantitativeOptimization minimize_reactions minimize_reaction_costs FBATintleResults FBAMinimalReactionsResults PROMKappa phenotypesimulationset_ref objectiveValue phenotypeset_ref promconstraint_ref regulome_ref tintleW tintleKappa massbalance
 * @metadata ws maximizeObjective as Maximized
 *     @metadata ws comboDeletions as Combination deletions
 *     @metadata ws minimize_reactions as Minimize reactions
 *     @metadata ws regulome_ref as Regulome
 *     @metadata ws fbamodel_ref as Model
 *     @metadata ws promconstraint_ref as PromConstraint
 *     @metadata ws media_ref as Media
 *     @metadata ws objectiveValue as Objective
 *     @metadata ws expression_matrix_ref as ExpressionMatrix
 *     @metadata ws expression_matrix_column as ExpressionMatrixColumn
 *     @metadata ws length(biomassflux_objterms) as Number biomass objectives
 *     @metadata ws length(geneKO_refs) as Number gene KO
 *     @metadata ws length(reactionKO_refs) as Number reaction KO
 *     @metadata ws length(additionalCpd_refs) as Number additional compounds
 *     @metadata ws length(FBAConstraints) as Number constraints
 *     @metadata ws length(FBAReactionBounds) as Number reaction bounds
 *     @metadata ws length(FBACompoundBounds) as Number compound bounds
 *     @metadata ws length(FBACompoundVariables) as Number compound variables
 *     @metadata ws length(FBAReactionVariables) as Number reaction variables
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "fva",
    "fluxMinimization",
    "findMinimalMedia",
    "allReversible",
    "simpleThermoConstraints",
    "thermodynamicConstraints",
    "noErrorThermodynamicConstraints",
    "minimizeErrorThermodynamicConstraints",
    "quantitativeOptimization",
    "maximizeObjective",
    "compoundflux_objterms",
    "reactionflux_objterms",
    "biomassflux_objterms",
    "comboDeletions",
    "numberOfSolutions",
    "objectiveConstraintFraction",
    "defaultMaxFlux",
    "defaultMaxDrainFlux",
    "defaultMinDrainFlux",
    "PROMKappa",
    "tintleW",
    "tintleKappa",
    "ExpressionAlpha",
    "ExpressionOmega",
    "ExpressionKappa",
    "decomposeReversibleFlux",
    "decomposeReversibleDrainFlux",
    "fluxUseVariables",
    "drainfluxUseVariables",
    "minimize_reactions",
    "calculateReactionKnockoutSensitivity",
    "maximizeActiveReactions",
    "jobnode",
    "regulome_ref",
    "fbamodel_ref",
    "promconstraint_ref",
    "expression_matrix_ref",
    "expression_matrix_column",
    "media_ref",
    "media_list_refs",
    "mediaset_ref",
    "phenotypeset_ref",
    "geneKO_refs",
    "reactionKO_refs",
    "additionalCpd_refs",
    "uptakeLimits",
    "minimize_reaction_costs",
    "massbalance",
    "parameters",
    "inputfiles",
    "FBAConstraints",
    "FBAReactionBounds",
    "FBACompoundBounds",
    "objectiveValue",
    "other_objectives",
    "outputfiles",
    "MFALog",
    "phenotypesimulationset_ref",
    "biomassRemovals",
    "FBACompoundVariables",
    "FBAReactionVariables",
    "FBABiomassVariables",
    "FBAPromResults",
    "FBATintleResults",
    "FBADeletionResults",
    "FBAMinimalMediaResults",
    "FBAMetaboliteProductionResults",
    "FBAMinimalReactionsResults",
    "QuantitativeOptimizationSolutions",
    "gapfillingSolutions"
})
public class FBA {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("fva")
    private Long fva;
    @JsonProperty("fluxMinimization")
    private Long fluxMinimization;
    @JsonProperty("findMinimalMedia")
    private Long findMinimalMedia;
    @JsonProperty("allReversible")
    private Long allReversible;
    @JsonProperty("simpleThermoConstraints")
    private Long simpleThermoConstraints;
    @JsonProperty("thermodynamicConstraints")
    private Long thermodynamicConstraints;
    @JsonProperty("noErrorThermodynamicConstraints")
    private Long noErrorThermodynamicConstraints;
    @JsonProperty("minimizeErrorThermodynamicConstraints")
    private Long minimizeErrorThermodynamicConstraints;
    @JsonProperty("quantitativeOptimization")
    private Long quantitativeOptimization;
    @JsonProperty("maximizeObjective")
    private Long maximizeObjective;
    @JsonProperty("compoundflux_objterms")
    private Map<String, Double> compoundfluxObjterms;
    @JsonProperty("reactionflux_objterms")
    private Map<String, Double> reactionfluxObjterms;
    @JsonProperty("biomassflux_objterms")
    private Map<String, Double> biomassfluxObjterms;
    @JsonProperty("comboDeletions")
    private Long comboDeletions;
    @JsonProperty("numberOfSolutions")
    private Long numberOfSolutions;
    @JsonProperty("objectiveConstraintFraction")
    private java.lang.Double objectiveConstraintFraction;
    @JsonProperty("defaultMaxFlux")
    private java.lang.Double defaultMaxFlux;
    @JsonProperty("defaultMaxDrainFlux")
    private java.lang.Double defaultMaxDrainFlux;
    @JsonProperty("defaultMinDrainFlux")
    private java.lang.Double defaultMinDrainFlux;
    @JsonProperty("PROMKappa")
    private java.lang.Double PROMKappa;
    @JsonProperty("tintleW")
    private java.lang.Double tintleW;
    @JsonProperty("tintleKappa")
    private java.lang.Double tintleKappa;
    @JsonProperty("ExpressionAlpha")
    private java.lang.Double ExpressionAlpha;
    @JsonProperty("ExpressionOmega")
    private java.lang.Double ExpressionOmega;
    @JsonProperty("ExpressionKappa")
    private java.lang.Double ExpressionKappa;
    @JsonProperty("decomposeReversibleFlux")
    private Long decomposeReversibleFlux;
    @JsonProperty("decomposeReversibleDrainFlux")
    private Long decomposeReversibleDrainFlux;
    @JsonProperty("fluxUseVariables")
    private Long fluxUseVariables;
    @JsonProperty("drainfluxUseVariables")
    private Long drainfluxUseVariables;
    @JsonProperty("minimize_reactions")
    private Long minimizeReactions;
    @JsonProperty("calculateReactionKnockoutSensitivity")
    private Long calculateReactionKnockoutSensitivity;
    @JsonProperty("maximizeActiveReactions")
    private Long maximizeActiveReactions;
    @JsonProperty("jobnode")
    private java.lang.String jobnode;
    @JsonProperty("regulome_ref")
    private java.lang.String regulomeRef;
    @JsonProperty("fbamodel_ref")
    private java.lang.String fbamodelRef;
    @JsonProperty("promconstraint_ref")
    private java.lang.String promconstraintRef;
    @JsonProperty("expression_matrix_ref")
    private java.lang.String expressionMatrixRef;
    @JsonProperty("expression_matrix_column")
    private java.lang.String expressionMatrixColumn;
    @JsonProperty("media_ref")
    private java.lang.String mediaRef;
    @JsonProperty("media_list_refs")
    private List<String> mediaListRefs;
    @JsonProperty("mediaset_ref")
    private java.lang.String mediasetRef;
    @JsonProperty("phenotypeset_ref")
    private java.lang.String phenotypesetRef;
    @JsonProperty("geneKO_refs")
    private List<String> geneKORefs;
    @JsonProperty("reactionKO_refs")
    private List<String> reactionKORefs;
    @JsonProperty("additionalCpd_refs")
    private List<String> additionalCpdRefs;
    @JsonProperty("uptakeLimits")
    private Map<String, Double> uptakeLimits;
    @JsonProperty("minimize_reaction_costs")
    private Map<String, Double> minimizeReactionCosts;
    @JsonProperty("massbalance")
    private java.lang.String massbalance;
    @JsonProperty("parameters")
    private Map<String, String> parameters;
    @JsonProperty("inputfiles")
    private Map<String, List<String>> inputfiles;
    @JsonProperty("FBAConstraints")
    private List<FBAConstraint> FBAConstraints;
    @JsonProperty("FBAReactionBounds")
    private List<FBAReactionBound> FBAReactionBounds;
    @JsonProperty("FBACompoundBounds")
    private List<FBACompoundBound> FBACompoundBounds;
    @JsonProperty("objectiveValue")
    private java.lang.Double objectiveValue;
    @JsonProperty("other_objectives")
    private List<Double> otherObjectives;
    @JsonProperty("outputfiles")
    private Map<String, List<String>> outputfiles;
    @JsonProperty("MFALog")
    private java.lang.String MFALog;
    @JsonProperty("phenotypesimulationset_ref")
    private java.lang.String phenotypesimulationsetRef;
    @JsonProperty("biomassRemovals")
    private Map<String, List<String>> biomassRemovals;
    @JsonProperty("FBACompoundVariables")
    private List<FBACompoundVariable> FBACompoundVariables;
    @JsonProperty("FBAReactionVariables")
    private List<FBAReactionVariable> FBAReactionVariables;
    @JsonProperty("FBABiomassVariables")
    private List<FBABiomassVariable> FBABiomassVariables;
    @JsonProperty("FBAPromResults")
    private List<FBAPromResult> FBAPromResults;
    @JsonProperty("FBATintleResults")
    private List<FBATintleResult> FBATintleResults;
    @JsonProperty("FBADeletionResults")
    private List<FBADeletionResult> FBADeletionResults;
    @JsonProperty("FBAMinimalMediaResults")
    private List<FBAMinimalMediaResult> FBAMinimalMediaResults;
    @JsonProperty("FBAMetaboliteProductionResults")
    private List<FBAMetaboliteProductionResult> FBAMetaboliteProductionResults;
    @JsonProperty("FBAMinimalReactionsResults")
    private List<FBAMinimalReactionsResult> FBAMinimalReactionsResults;
    @JsonProperty("QuantitativeOptimizationSolutions")
    private List<QuantitativeOptimizationSolution> QuantitativeOptimizationSolutions;
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

    public FBA withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("fva")
    public Long getFva() {
        return fva;
    }

    @JsonProperty("fva")
    public void setFva(Long fva) {
        this.fva = fva;
    }

    public FBA withFva(Long fva) {
        this.fva = fva;
        return this;
    }

    @JsonProperty("fluxMinimization")
    public Long getFluxMinimization() {
        return fluxMinimization;
    }

    @JsonProperty("fluxMinimization")
    public void setFluxMinimization(Long fluxMinimization) {
        this.fluxMinimization = fluxMinimization;
    }

    public FBA withFluxMinimization(Long fluxMinimization) {
        this.fluxMinimization = fluxMinimization;
        return this;
    }

    @JsonProperty("findMinimalMedia")
    public Long getFindMinimalMedia() {
        return findMinimalMedia;
    }

    @JsonProperty("findMinimalMedia")
    public void setFindMinimalMedia(Long findMinimalMedia) {
        this.findMinimalMedia = findMinimalMedia;
    }

    public FBA withFindMinimalMedia(Long findMinimalMedia) {
        this.findMinimalMedia = findMinimalMedia;
        return this;
    }

    @JsonProperty("allReversible")
    public Long getAllReversible() {
        return allReversible;
    }

    @JsonProperty("allReversible")
    public void setAllReversible(Long allReversible) {
        this.allReversible = allReversible;
    }

    public FBA withAllReversible(Long allReversible) {
        this.allReversible = allReversible;
        return this;
    }

    @JsonProperty("simpleThermoConstraints")
    public Long getSimpleThermoConstraints() {
        return simpleThermoConstraints;
    }

    @JsonProperty("simpleThermoConstraints")
    public void setSimpleThermoConstraints(Long simpleThermoConstraints) {
        this.simpleThermoConstraints = simpleThermoConstraints;
    }

    public FBA withSimpleThermoConstraints(Long simpleThermoConstraints) {
        this.simpleThermoConstraints = simpleThermoConstraints;
        return this;
    }

    @JsonProperty("thermodynamicConstraints")
    public Long getThermodynamicConstraints() {
        return thermodynamicConstraints;
    }

    @JsonProperty("thermodynamicConstraints")
    public void setThermodynamicConstraints(Long thermodynamicConstraints) {
        this.thermodynamicConstraints = thermodynamicConstraints;
    }

    public FBA withThermodynamicConstraints(Long thermodynamicConstraints) {
        this.thermodynamicConstraints = thermodynamicConstraints;
        return this;
    }

    @JsonProperty("noErrorThermodynamicConstraints")
    public Long getNoErrorThermodynamicConstraints() {
        return noErrorThermodynamicConstraints;
    }

    @JsonProperty("noErrorThermodynamicConstraints")
    public void setNoErrorThermodynamicConstraints(Long noErrorThermodynamicConstraints) {
        this.noErrorThermodynamicConstraints = noErrorThermodynamicConstraints;
    }

    public FBA withNoErrorThermodynamicConstraints(Long noErrorThermodynamicConstraints) {
        this.noErrorThermodynamicConstraints = noErrorThermodynamicConstraints;
        return this;
    }

    @JsonProperty("minimizeErrorThermodynamicConstraints")
    public Long getMinimizeErrorThermodynamicConstraints() {
        return minimizeErrorThermodynamicConstraints;
    }

    @JsonProperty("minimizeErrorThermodynamicConstraints")
    public void setMinimizeErrorThermodynamicConstraints(Long minimizeErrorThermodynamicConstraints) {
        this.minimizeErrorThermodynamicConstraints = minimizeErrorThermodynamicConstraints;
    }

    public FBA withMinimizeErrorThermodynamicConstraints(Long minimizeErrorThermodynamicConstraints) {
        this.minimizeErrorThermodynamicConstraints = minimizeErrorThermodynamicConstraints;
        return this;
    }

    @JsonProperty("quantitativeOptimization")
    public Long getQuantitativeOptimization() {
        return quantitativeOptimization;
    }

    @JsonProperty("quantitativeOptimization")
    public void setQuantitativeOptimization(Long quantitativeOptimization) {
        this.quantitativeOptimization = quantitativeOptimization;
    }

    public FBA withQuantitativeOptimization(Long quantitativeOptimization) {
        this.quantitativeOptimization = quantitativeOptimization;
        return this;
    }

    @JsonProperty("maximizeObjective")
    public Long getMaximizeObjective() {
        return maximizeObjective;
    }

    @JsonProperty("maximizeObjective")
    public void setMaximizeObjective(Long maximizeObjective) {
        this.maximizeObjective = maximizeObjective;
    }

    public FBA withMaximizeObjective(Long maximizeObjective) {
        this.maximizeObjective = maximizeObjective;
        return this;
    }

    @JsonProperty("compoundflux_objterms")
    public Map<String, Double> getCompoundfluxObjterms() {
        return compoundfluxObjterms;
    }

    @JsonProperty("compoundflux_objterms")
    public void setCompoundfluxObjterms(Map<String, Double> compoundfluxObjterms) {
        this.compoundfluxObjterms = compoundfluxObjterms;
    }

    public FBA withCompoundfluxObjterms(Map<String, Double> compoundfluxObjterms) {
        this.compoundfluxObjterms = compoundfluxObjterms;
        return this;
    }

    @JsonProperty("reactionflux_objterms")
    public Map<String, Double> getReactionfluxObjterms() {
        return reactionfluxObjterms;
    }

    @JsonProperty("reactionflux_objterms")
    public void setReactionfluxObjterms(Map<String, Double> reactionfluxObjterms) {
        this.reactionfluxObjterms = reactionfluxObjterms;
    }

    public FBA withReactionfluxObjterms(Map<String, Double> reactionfluxObjterms) {
        this.reactionfluxObjterms = reactionfluxObjterms;
        return this;
    }

    @JsonProperty("biomassflux_objterms")
    public Map<String, Double> getBiomassfluxObjterms() {
        return biomassfluxObjterms;
    }

    @JsonProperty("biomassflux_objterms")
    public void setBiomassfluxObjterms(Map<String, Double> biomassfluxObjterms) {
        this.biomassfluxObjterms = biomassfluxObjterms;
    }

    public FBA withBiomassfluxObjterms(Map<String, Double> biomassfluxObjterms) {
        this.biomassfluxObjterms = biomassfluxObjterms;
        return this;
    }

    @JsonProperty("comboDeletions")
    public Long getComboDeletions() {
        return comboDeletions;
    }

    @JsonProperty("comboDeletions")
    public void setComboDeletions(Long comboDeletions) {
        this.comboDeletions = comboDeletions;
    }

    public FBA withComboDeletions(Long comboDeletions) {
        this.comboDeletions = comboDeletions;
        return this;
    }

    @JsonProperty("numberOfSolutions")
    public Long getNumberOfSolutions() {
        return numberOfSolutions;
    }

    @JsonProperty("numberOfSolutions")
    public void setNumberOfSolutions(Long numberOfSolutions) {
        this.numberOfSolutions = numberOfSolutions;
    }

    public FBA withNumberOfSolutions(Long numberOfSolutions) {
        this.numberOfSolutions = numberOfSolutions;
        return this;
    }

    @JsonProperty("objectiveConstraintFraction")
    public java.lang.Double getObjectiveConstraintFraction() {
        return objectiveConstraintFraction;
    }

    @JsonProperty("objectiveConstraintFraction")
    public void setObjectiveConstraintFraction(java.lang.Double objectiveConstraintFraction) {
        this.objectiveConstraintFraction = objectiveConstraintFraction;
    }

    public FBA withObjectiveConstraintFraction(java.lang.Double objectiveConstraintFraction) {
        this.objectiveConstraintFraction = objectiveConstraintFraction;
        return this;
    }

    @JsonProperty("defaultMaxFlux")
    public java.lang.Double getDefaultMaxFlux() {
        return defaultMaxFlux;
    }

    @JsonProperty("defaultMaxFlux")
    public void setDefaultMaxFlux(java.lang.Double defaultMaxFlux) {
        this.defaultMaxFlux = defaultMaxFlux;
    }

    public FBA withDefaultMaxFlux(java.lang.Double defaultMaxFlux) {
        this.defaultMaxFlux = defaultMaxFlux;
        return this;
    }

    @JsonProperty("defaultMaxDrainFlux")
    public java.lang.Double getDefaultMaxDrainFlux() {
        return defaultMaxDrainFlux;
    }

    @JsonProperty("defaultMaxDrainFlux")
    public void setDefaultMaxDrainFlux(java.lang.Double defaultMaxDrainFlux) {
        this.defaultMaxDrainFlux = defaultMaxDrainFlux;
    }

    public FBA withDefaultMaxDrainFlux(java.lang.Double defaultMaxDrainFlux) {
        this.defaultMaxDrainFlux = defaultMaxDrainFlux;
        return this;
    }

    @JsonProperty("defaultMinDrainFlux")
    public java.lang.Double getDefaultMinDrainFlux() {
        return defaultMinDrainFlux;
    }

    @JsonProperty("defaultMinDrainFlux")
    public void setDefaultMinDrainFlux(java.lang.Double defaultMinDrainFlux) {
        this.defaultMinDrainFlux = defaultMinDrainFlux;
    }

    public FBA withDefaultMinDrainFlux(java.lang.Double defaultMinDrainFlux) {
        this.defaultMinDrainFlux = defaultMinDrainFlux;
        return this;
    }

    @JsonProperty("PROMKappa")
    public java.lang.Double getPROMKappa() {
        return PROMKappa;
    }

    @JsonProperty("PROMKappa")
    public void setPROMKappa(java.lang.Double PROMKappa) {
        this.PROMKappa = PROMKappa;
    }

    public FBA withPROMKappa(java.lang.Double PROMKappa) {
        this.PROMKappa = PROMKappa;
        return this;
    }

    @JsonProperty("tintleW")
    public java.lang.Double getTintleW() {
        return tintleW;
    }

    @JsonProperty("tintleW")
    public void setTintleW(java.lang.Double tintleW) {
        this.tintleW = tintleW;
    }

    public FBA withTintleW(java.lang.Double tintleW) {
        this.tintleW = tintleW;
        return this;
    }

    @JsonProperty("tintleKappa")
    public java.lang.Double getTintleKappa() {
        return tintleKappa;
    }

    @JsonProperty("tintleKappa")
    public void setTintleKappa(java.lang.Double tintleKappa) {
        this.tintleKappa = tintleKappa;
    }

    public FBA withTintleKappa(java.lang.Double tintleKappa) {
        this.tintleKappa = tintleKappa;
        return this;
    }

    @JsonProperty("ExpressionAlpha")
    public java.lang.Double getExpressionAlpha() {
        return ExpressionAlpha;
    }

    @JsonProperty("ExpressionAlpha")
    public void setExpressionAlpha(java.lang.Double ExpressionAlpha) {
        this.ExpressionAlpha = ExpressionAlpha;
    }

    public FBA withExpressionAlpha(java.lang.Double ExpressionAlpha) {
        this.ExpressionAlpha = ExpressionAlpha;
        return this;
    }

    @JsonProperty("ExpressionOmega")
    public java.lang.Double getExpressionOmega() {
        return ExpressionOmega;
    }

    @JsonProperty("ExpressionOmega")
    public void setExpressionOmega(java.lang.Double ExpressionOmega) {
        this.ExpressionOmega = ExpressionOmega;
    }

    public FBA withExpressionOmega(java.lang.Double ExpressionOmega) {
        this.ExpressionOmega = ExpressionOmega;
        return this;
    }

    @JsonProperty("ExpressionKappa")
    public java.lang.Double getExpressionKappa() {
        return ExpressionKappa;
    }

    @JsonProperty("ExpressionKappa")
    public void setExpressionKappa(java.lang.Double ExpressionKappa) {
        this.ExpressionKappa = ExpressionKappa;
    }

    public FBA withExpressionKappa(java.lang.Double ExpressionKappa) {
        this.ExpressionKappa = ExpressionKappa;
        return this;
    }

    @JsonProperty("decomposeReversibleFlux")
    public Long getDecomposeReversibleFlux() {
        return decomposeReversibleFlux;
    }

    @JsonProperty("decomposeReversibleFlux")
    public void setDecomposeReversibleFlux(Long decomposeReversibleFlux) {
        this.decomposeReversibleFlux = decomposeReversibleFlux;
    }

    public FBA withDecomposeReversibleFlux(Long decomposeReversibleFlux) {
        this.decomposeReversibleFlux = decomposeReversibleFlux;
        return this;
    }

    @JsonProperty("decomposeReversibleDrainFlux")
    public Long getDecomposeReversibleDrainFlux() {
        return decomposeReversibleDrainFlux;
    }

    @JsonProperty("decomposeReversibleDrainFlux")
    public void setDecomposeReversibleDrainFlux(Long decomposeReversibleDrainFlux) {
        this.decomposeReversibleDrainFlux = decomposeReversibleDrainFlux;
    }

    public FBA withDecomposeReversibleDrainFlux(Long decomposeReversibleDrainFlux) {
        this.decomposeReversibleDrainFlux = decomposeReversibleDrainFlux;
        return this;
    }

    @JsonProperty("fluxUseVariables")
    public Long getFluxUseVariables() {
        return fluxUseVariables;
    }

    @JsonProperty("fluxUseVariables")
    public void setFluxUseVariables(Long fluxUseVariables) {
        this.fluxUseVariables = fluxUseVariables;
    }

    public FBA withFluxUseVariables(Long fluxUseVariables) {
        this.fluxUseVariables = fluxUseVariables;
        return this;
    }

    @JsonProperty("drainfluxUseVariables")
    public Long getDrainfluxUseVariables() {
        return drainfluxUseVariables;
    }

    @JsonProperty("drainfluxUseVariables")
    public void setDrainfluxUseVariables(Long drainfluxUseVariables) {
        this.drainfluxUseVariables = drainfluxUseVariables;
    }

    public FBA withDrainfluxUseVariables(Long drainfluxUseVariables) {
        this.drainfluxUseVariables = drainfluxUseVariables;
        return this;
    }

    @JsonProperty("minimize_reactions")
    public Long getMinimizeReactions() {
        return minimizeReactions;
    }

    @JsonProperty("minimize_reactions")
    public void setMinimizeReactions(Long minimizeReactions) {
        this.minimizeReactions = minimizeReactions;
    }

    public FBA withMinimizeReactions(Long minimizeReactions) {
        this.minimizeReactions = minimizeReactions;
        return this;
    }

    @JsonProperty("calculateReactionKnockoutSensitivity")
    public Long getCalculateReactionKnockoutSensitivity() {
        return calculateReactionKnockoutSensitivity;
    }

    @JsonProperty("calculateReactionKnockoutSensitivity")
    public void setCalculateReactionKnockoutSensitivity(Long calculateReactionKnockoutSensitivity) {
        this.calculateReactionKnockoutSensitivity = calculateReactionKnockoutSensitivity;
    }

    public FBA withCalculateReactionKnockoutSensitivity(Long calculateReactionKnockoutSensitivity) {
        this.calculateReactionKnockoutSensitivity = calculateReactionKnockoutSensitivity;
        return this;
    }

    @JsonProperty("maximizeActiveReactions")
    public Long getMaximizeActiveReactions() {
        return maximizeActiveReactions;
    }

    @JsonProperty("maximizeActiveReactions")
    public void setMaximizeActiveReactions(Long maximizeActiveReactions) {
        this.maximizeActiveReactions = maximizeActiveReactions;
    }

    public FBA withMaximizeActiveReactions(Long maximizeActiveReactions) {
        this.maximizeActiveReactions = maximizeActiveReactions;
        return this;
    }

    @JsonProperty("jobnode")
    public java.lang.String getJobnode() {
        return jobnode;
    }

    @JsonProperty("jobnode")
    public void setJobnode(java.lang.String jobnode) {
        this.jobnode = jobnode;
    }

    public FBA withJobnode(java.lang.String jobnode) {
        this.jobnode = jobnode;
        return this;
    }

    @JsonProperty("regulome_ref")
    public java.lang.String getRegulomeRef() {
        return regulomeRef;
    }

    @JsonProperty("regulome_ref")
    public void setRegulomeRef(java.lang.String regulomeRef) {
        this.regulomeRef = regulomeRef;
    }

    public FBA withRegulomeRef(java.lang.String regulomeRef) {
        this.regulomeRef = regulomeRef;
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

    public FBA withFbamodelRef(java.lang.String fbamodelRef) {
        this.fbamodelRef = fbamodelRef;
        return this;
    }

    @JsonProperty("promconstraint_ref")
    public java.lang.String getPromconstraintRef() {
        return promconstraintRef;
    }

    @JsonProperty("promconstraint_ref")
    public void setPromconstraintRef(java.lang.String promconstraintRef) {
        this.promconstraintRef = promconstraintRef;
    }

    public FBA withPromconstraintRef(java.lang.String promconstraintRef) {
        this.promconstraintRef = promconstraintRef;
        return this;
    }

    @JsonProperty("expression_matrix_ref")
    public java.lang.String getExpressionMatrixRef() {
        return expressionMatrixRef;
    }

    @JsonProperty("expression_matrix_ref")
    public void setExpressionMatrixRef(java.lang.String expressionMatrixRef) {
        this.expressionMatrixRef = expressionMatrixRef;
    }

    public FBA withExpressionMatrixRef(java.lang.String expressionMatrixRef) {
        this.expressionMatrixRef = expressionMatrixRef;
        return this;
    }

    @JsonProperty("expression_matrix_column")
    public java.lang.String getExpressionMatrixColumn() {
        return expressionMatrixColumn;
    }

    @JsonProperty("expression_matrix_column")
    public void setExpressionMatrixColumn(java.lang.String expressionMatrixColumn) {
        this.expressionMatrixColumn = expressionMatrixColumn;
    }

    public FBA withExpressionMatrixColumn(java.lang.String expressionMatrixColumn) {
        this.expressionMatrixColumn = expressionMatrixColumn;
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

    public FBA withMediaRef(java.lang.String mediaRef) {
        this.mediaRef = mediaRef;
        return this;
    }

    @JsonProperty("media_list_refs")
    public List<String> getMediaListRefs() {
        return mediaListRefs;
    }

    @JsonProperty("media_list_refs")
    public void setMediaListRefs(List<String> mediaListRefs) {
        this.mediaListRefs = mediaListRefs;
    }

    public FBA withMediaListRefs(List<String> mediaListRefs) {
        this.mediaListRefs = mediaListRefs;
        return this;
    }

    @JsonProperty("mediaset_ref")
    public java.lang.String getMediasetRef() {
        return mediasetRef;
    }

    @JsonProperty("mediaset_ref")
    public void setMediasetRef(java.lang.String mediasetRef) {
        this.mediasetRef = mediasetRef;
    }

    public FBA withMediasetRef(java.lang.String mediasetRef) {
        this.mediasetRef = mediasetRef;
        return this;
    }

    @JsonProperty("phenotypeset_ref")
    public java.lang.String getPhenotypesetRef() {
        return phenotypesetRef;
    }

    @JsonProperty("phenotypeset_ref")
    public void setPhenotypesetRef(java.lang.String phenotypesetRef) {
        this.phenotypesetRef = phenotypesetRef;
    }

    public FBA withPhenotypesetRef(java.lang.String phenotypesetRef) {
        this.phenotypesetRef = phenotypesetRef;
        return this;
    }

    @JsonProperty("geneKO_refs")
    public List<String> getGeneKORefs() {
        return geneKORefs;
    }

    @JsonProperty("geneKO_refs")
    public void setGeneKORefs(List<String> geneKORefs) {
        this.geneKORefs = geneKORefs;
    }

    public FBA withGeneKORefs(List<String> geneKORefs) {
        this.geneKORefs = geneKORefs;
        return this;
    }

    @JsonProperty("reactionKO_refs")
    public List<String> getReactionKORefs() {
        return reactionKORefs;
    }

    @JsonProperty("reactionKO_refs")
    public void setReactionKORefs(List<String> reactionKORefs) {
        this.reactionKORefs = reactionKORefs;
    }

    public FBA withReactionKORefs(List<String> reactionKORefs) {
        this.reactionKORefs = reactionKORefs;
        return this;
    }

    @JsonProperty("additionalCpd_refs")
    public List<String> getAdditionalCpdRefs() {
        return additionalCpdRefs;
    }

    @JsonProperty("additionalCpd_refs")
    public void setAdditionalCpdRefs(List<String> additionalCpdRefs) {
        this.additionalCpdRefs = additionalCpdRefs;
    }

    public FBA withAdditionalCpdRefs(List<String> additionalCpdRefs) {
        this.additionalCpdRefs = additionalCpdRefs;
        return this;
    }

    @JsonProperty("uptakeLimits")
    public Map<String, Double> getUptakeLimits() {
        return uptakeLimits;
    }

    @JsonProperty("uptakeLimits")
    public void setUptakeLimits(Map<String, Double> uptakeLimits) {
        this.uptakeLimits = uptakeLimits;
    }

    public FBA withUptakeLimits(Map<String, Double> uptakeLimits) {
        this.uptakeLimits = uptakeLimits;
        return this;
    }

    @JsonProperty("minimize_reaction_costs")
    public Map<String, Double> getMinimizeReactionCosts() {
        return minimizeReactionCosts;
    }

    @JsonProperty("minimize_reaction_costs")
    public void setMinimizeReactionCosts(Map<String, Double> minimizeReactionCosts) {
        this.minimizeReactionCosts = minimizeReactionCosts;
    }

    public FBA withMinimizeReactionCosts(Map<String, Double> minimizeReactionCosts) {
        this.minimizeReactionCosts = minimizeReactionCosts;
        return this;
    }

    @JsonProperty("massbalance")
    public java.lang.String getMassbalance() {
        return massbalance;
    }

    @JsonProperty("massbalance")
    public void setMassbalance(java.lang.String massbalance) {
        this.massbalance = massbalance;
    }

    public FBA withMassbalance(java.lang.String massbalance) {
        this.massbalance = massbalance;
        return this;
    }

    @JsonProperty("parameters")
    public Map<String, String> getParameters() {
        return parameters;
    }

    @JsonProperty("parameters")
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public FBA withParameters(Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    @JsonProperty("inputfiles")
    public Map<String, List<String>> getInputfiles() {
        return inputfiles;
    }

    @JsonProperty("inputfiles")
    public void setInputfiles(Map<String, List<String>> inputfiles) {
        this.inputfiles = inputfiles;
    }

    public FBA withInputfiles(Map<String, List<String>> inputfiles) {
        this.inputfiles = inputfiles;
        return this;
    }

    @JsonProperty("FBAConstraints")
    public List<FBAConstraint> getFBAConstraints() {
        return FBAConstraints;
    }

    @JsonProperty("FBAConstraints")
    public void setFBAConstraints(List<FBAConstraint> FBAConstraints) {
        this.FBAConstraints = FBAConstraints;
    }

    public FBA withFBAConstraints(List<FBAConstraint> FBAConstraints) {
        this.FBAConstraints = FBAConstraints;
        return this;
    }

    @JsonProperty("FBAReactionBounds")
    public List<FBAReactionBound> getFBAReactionBounds() {
        return FBAReactionBounds;
    }

    @JsonProperty("FBAReactionBounds")
    public void setFBAReactionBounds(List<FBAReactionBound> FBAReactionBounds) {
        this.FBAReactionBounds = FBAReactionBounds;
    }

    public FBA withFBAReactionBounds(List<FBAReactionBound> FBAReactionBounds) {
        this.FBAReactionBounds = FBAReactionBounds;
        return this;
    }

    @JsonProperty("FBACompoundBounds")
    public List<FBACompoundBound> getFBACompoundBounds() {
        return FBACompoundBounds;
    }

    @JsonProperty("FBACompoundBounds")
    public void setFBACompoundBounds(List<FBACompoundBound> FBACompoundBounds) {
        this.FBACompoundBounds = FBACompoundBounds;
    }

    public FBA withFBACompoundBounds(List<FBACompoundBound> FBACompoundBounds) {
        this.FBACompoundBounds = FBACompoundBounds;
        return this;
    }

    @JsonProperty("objectiveValue")
    public java.lang.Double getObjectiveValue() {
        return objectiveValue;
    }

    @JsonProperty("objectiveValue")
    public void setObjectiveValue(java.lang.Double objectiveValue) {
        this.objectiveValue = objectiveValue;
    }

    public FBA withObjectiveValue(java.lang.Double objectiveValue) {
        this.objectiveValue = objectiveValue;
        return this;
    }

    @JsonProperty("other_objectives")
    public List<Double> getOtherObjectives() {
        return otherObjectives;
    }

    @JsonProperty("other_objectives")
    public void setOtherObjectives(List<Double> otherObjectives) {
        this.otherObjectives = otherObjectives;
    }

    public FBA withOtherObjectives(List<Double> otherObjectives) {
        this.otherObjectives = otherObjectives;
        return this;
    }

    @JsonProperty("outputfiles")
    public Map<String, List<String>> getOutputfiles() {
        return outputfiles;
    }

    @JsonProperty("outputfiles")
    public void setOutputfiles(Map<String, List<String>> outputfiles) {
        this.outputfiles = outputfiles;
    }

    public FBA withOutputfiles(Map<String, List<String>> outputfiles) {
        this.outputfiles = outputfiles;
        return this;
    }

    @JsonProperty("MFALog")
    public java.lang.String getMFALog() {
        return MFALog;
    }

    @JsonProperty("MFALog")
    public void setMFALog(java.lang.String MFALog) {
        this.MFALog = MFALog;
    }

    public FBA withMFALog(java.lang.String MFALog) {
        this.MFALog = MFALog;
        return this;
    }

    @JsonProperty("phenotypesimulationset_ref")
    public java.lang.String getPhenotypesimulationsetRef() {
        return phenotypesimulationsetRef;
    }

    @JsonProperty("phenotypesimulationset_ref")
    public void setPhenotypesimulationsetRef(java.lang.String phenotypesimulationsetRef) {
        this.phenotypesimulationsetRef = phenotypesimulationsetRef;
    }

    public FBA withPhenotypesimulationsetRef(java.lang.String phenotypesimulationsetRef) {
        this.phenotypesimulationsetRef = phenotypesimulationsetRef;
        return this;
    }

    @JsonProperty("biomassRemovals")
    public Map<String, List<String>> getBiomassRemovals() {
        return biomassRemovals;
    }

    @JsonProperty("biomassRemovals")
    public void setBiomassRemovals(Map<String, List<String>> biomassRemovals) {
        this.biomassRemovals = biomassRemovals;
    }

    public FBA withBiomassRemovals(Map<String, List<String>> biomassRemovals) {
        this.biomassRemovals = biomassRemovals;
        return this;
    }

    @JsonProperty("FBACompoundVariables")
    public List<FBACompoundVariable> getFBACompoundVariables() {
        return FBACompoundVariables;
    }

    @JsonProperty("FBACompoundVariables")
    public void setFBACompoundVariables(List<FBACompoundVariable> FBACompoundVariables) {
        this.FBACompoundVariables = FBACompoundVariables;
    }

    public FBA withFBACompoundVariables(List<FBACompoundVariable> FBACompoundVariables) {
        this.FBACompoundVariables = FBACompoundVariables;
        return this;
    }

    @JsonProperty("FBAReactionVariables")
    public List<FBAReactionVariable> getFBAReactionVariables() {
        return FBAReactionVariables;
    }

    @JsonProperty("FBAReactionVariables")
    public void setFBAReactionVariables(List<FBAReactionVariable> FBAReactionVariables) {
        this.FBAReactionVariables = FBAReactionVariables;
    }

    public FBA withFBAReactionVariables(List<FBAReactionVariable> FBAReactionVariables) {
        this.FBAReactionVariables = FBAReactionVariables;
        return this;
    }

    @JsonProperty("FBABiomassVariables")
    public List<FBABiomassVariable> getFBABiomassVariables() {
        return FBABiomassVariables;
    }

    @JsonProperty("FBABiomassVariables")
    public void setFBABiomassVariables(List<FBABiomassVariable> FBABiomassVariables) {
        this.FBABiomassVariables = FBABiomassVariables;
    }

    public FBA withFBABiomassVariables(List<FBABiomassVariable> FBABiomassVariables) {
        this.FBABiomassVariables = FBABiomassVariables;
        return this;
    }

    @JsonProperty("FBAPromResults")
    public List<FBAPromResult> getFBAPromResults() {
        return FBAPromResults;
    }

    @JsonProperty("FBAPromResults")
    public void setFBAPromResults(List<FBAPromResult> FBAPromResults) {
        this.FBAPromResults = FBAPromResults;
    }

    public FBA withFBAPromResults(List<FBAPromResult> FBAPromResults) {
        this.FBAPromResults = FBAPromResults;
        return this;
    }

    @JsonProperty("FBATintleResults")
    public List<FBATintleResult> getFBATintleResults() {
        return FBATintleResults;
    }

    @JsonProperty("FBATintleResults")
    public void setFBATintleResults(List<FBATintleResult> FBATintleResults) {
        this.FBATintleResults = FBATintleResults;
    }

    public FBA withFBATintleResults(List<FBATintleResult> FBATintleResults) {
        this.FBATintleResults = FBATintleResults;
        return this;
    }

    @JsonProperty("FBADeletionResults")
    public List<FBADeletionResult> getFBADeletionResults() {
        return FBADeletionResults;
    }

    @JsonProperty("FBADeletionResults")
    public void setFBADeletionResults(List<FBADeletionResult> FBADeletionResults) {
        this.FBADeletionResults = FBADeletionResults;
    }

    public FBA withFBADeletionResults(List<FBADeletionResult> FBADeletionResults) {
        this.FBADeletionResults = FBADeletionResults;
        return this;
    }

    @JsonProperty("FBAMinimalMediaResults")
    public List<FBAMinimalMediaResult> getFBAMinimalMediaResults() {
        return FBAMinimalMediaResults;
    }

    @JsonProperty("FBAMinimalMediaResults")
    public void setFBAMinimalMediaResults(List<FBAMinimalMediaResult> FBAMinimalMediaResults) {
        this.FBAMinimalMediaResults = FBAMinimalMediaResults;
    }

    public FBA withFBAMinimalMediaResults(List<FBAMinimalMediaResult> FBAMinimalMediaResults) {
        this.FBAMinimalMediaResults = FBAMinimalMediaResults;
        return this;
    }

    @JsonProperty("FBAMetaboliteProductionResults")
    public List<FBAMetaboliteProductionResult> getFBAMetaboliteProductionResults() {
        return FBAMetaboliteProductionResults;
    }

    @JsonProperty("FBAMetaboliteProductionResults")
    public void setFBAMetaboliteProductionResults(List<FBAMetaboliteProductionResult> FBAMetaboliteProductionResults) {
        this.FBAMetaboliteProductionResults = FBAMetaboliteProductionResults;
    }

    public FBA withFBAMetaboliteProductionResults(List<FBAMetaboliteProductionResult> FBAMetaboliteProductionResults) {
        this.FBAMetaboliteProductionResults = FBAMetaboliteProductionResults;
        return this;
    }

    @JsonProperty("FBAMinimalReactionsResults")
    public List<FBAMinimalReactionsResult> getFBAMinimalReactionsResults() {
        return FBAMinimalReactionsResults;
    }

    @JsonProperty("FBAMinimalReactionsResults")
    public void setFBAMinimalReactionsResults(List<FBAMinimalReactionsResult> FBAMinimalReactionsResults) {
        this.FBAMinimalReactionsResults = FBAMinimalReactionsResults;
    }

    public FBA withFBAMinimalReactionsResults(List<FBAMinimalReactionsResult> FBAMinimalReactionsResults) {
        this.FBAMinimalReactionsResults = FBAMinimalReactionsResults;
        return this;
    }

    @JsonProperty("QuantitativeOptimizationSolutions")
    public List<QuantitativeOptimizationSolution> getQuantitativeOptimizationSolutions() {
        return QuantitativeOptimizationSolutions;
    }

    @JsonProperty("QuantitativeOptimizationSolutions")
    public void setQuantitativeOptimizationSolutions(List<QuantitativeOptimizationSolution> QuantitativeOptimizationSolutions) {
        this.QuantitativeOptimizationSolutions = QuantitativeOptimizationSolutions;
    }

    public FBA withQuantitativeOptimizationSolutions(List<QuantitativeOptimizationSolution> QuantitativeOptimizationSolutions) {
        this.QuantitativeOptimizationSolutions = QuantitativeOptimizationSolutions;
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

    public FBA withGapfillingSolutions(List<GapfillingSolution> gapfillingSolutions) {
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
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((("FBA"+" [id=")+ id)+", fva=")+ fva)+", fluxMinimization=")+ fluxMinimization)+", findMinimalMedia=")+ findMinimalMedia)+", allReversible=")+ allReversible)+", simpleThermoConstraints=")+ simpleThermoConstraints)+", thermodynamicConstraints=")+ thermodynamicConstraints)+", noErrorThermodynamicConstraints=")+ noErrorThermodynamicConstraints)+", minimizeErrorThermodynamicConstraints=")+ minimizeErrorThermodynamicConstraints)+", quantitativeOptimization=")+ quantitativeOptimization)+", maximizeObjective=")+ maximizeObjective)+", compoundfluxObjterms=")+ compoundfluxObjterms)+", reactionfluxObjterms=")+ reactionfluxObjterms)+", biomassfluxObjterms=")+ biomassfluxObjterms)+", comboDeletions=")+ comboDeletions)+", numberOfSolutions=")+ numberOfSolutions)+", objectiveConstraintFraction=")+ objectiveConstraintFraction)+", defaultMaxFlux=")+ defaultMaxFlux)+", defaultMaxDrainFlux=")+ defaultMaxDrainFlux)+", defaultMinDrainFlux=")+ defaultMinDrainFlux)+", PROMKappa=")+ PROMKappa)+", tintleW=")+ tintleW)+", tintleKappa=")+ tintleKappa)+", ExpressionAlpha=")+ ExpressionAlpha)+", ExpressionOmega=")+ ExpressionOmega)+", ExpressionKappa=")+ ExpressionKappa)+", decomposeReversibleFlux=")+ decomposeReversibleFlux)+", decomposeReversibleDrainFlux=")+ decomposeReversibleDrainFlux)+", fluxUseVariables=")+ fluxUseVariables)+", drainfluxUseVariables=")+ drainfluxUseVariables)+", minimizeReactions=")+ minimizeReactions)+", calculateReactionKnockoutSensitivity=")+ calculateReactionKnockoutSensitivity)+", maximizeActiveReactions=")+ maximizeActiveReactions)+", jobnode=")+ jobnode)+", regulomeRef=")+ regulomeRef)+", fbamodelRef=")+ fbamodelRef)+", promconstraintRef=")+ promconstraintRef)+", expressionMatrixRef=")+ expressionMatrixRef)+", expressionMatrixColumn=")+ expressionMatrixColumn)+", mediaRef=")+ mediaRef)+", mediaListRefs=")+ mediaListRefs)+", mediasetRef=")+ mediasetRef)+", phenotypesetRef=")+ phenotypesetRef)+", geneKORefs=")+ geneKORefs)+", reactionKORefs=")+ reactionKORefs)+", additionalCpdRefs=")+ additionalCpdRefs)+", uptakeLimits=")+ uptakeLimits)+", minimizeReactionCosts=")+ minimizeReactionCosts)+", massbalance=")+ massbalance)+", parameters=")+ parameters)+", inputfiles=")+ inputfiles)+", FBAConstraints=")+ FBAConstraints)+", FBAReactionBounds=")+ FBAReactionBounds)+", FBACompoundBounds=")+ FBACompoundBounds)+", objectiveValue=")+ objectiveValue)+", otherObjectives=")+ otherObjectives)+", outputfiles=")+ outputfiles)+", MFALog=")+ MFALog)+", phenotypesimulationsetRef=")+ phenotypesimulationsetRef)+", biomassRemovals=")+ biomassRemovals)+", FBACompoundVariables=")+ FBACompoundVariables)+", FBAReactionVariables=")+ FBAReactionVariables)+", FBABiomassVariables=")+ FBABiomassVariables)+", FBAPromResults=")+ FBAPromResults)+", FBATintleResults=")+ FBATintleResults)+", FBADeletionResults=")+ FBADeletionResults)+", FBAMinimalMediaResults=")+ FBAMinimalMediaResults)+", FBAMetaboliteProductionResults=")+ FBAMetaboliteProductionResults)+", FBAMinimalReactionsResults=")+ FBAMinimalReactionsResults)+", QuantitativeOptimizationSolutions=")+ QuantitativeOptimizationSolutions)+", gapfillingSolutions=")+ gapfillingSolutions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
