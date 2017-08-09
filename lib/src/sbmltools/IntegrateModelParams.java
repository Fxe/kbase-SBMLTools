
package sbmltools;

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
 * <p>Original spec-file type: IntegrateModelParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "model_name",
    "workspace_name",
    "output_model_name",
    "template_id",
    "genome_id",
    "compartment_translation",
    "biomass_reactions",
    "compound_mappings",
    "gene_mappings",
    "create_extracellular"
})
public class IntegrateModelParams {

    @JsonProperty("model_name")
    private java.lang.String modelName;
    @JsonProperty("workspace_name")
    private java.lang.String workspaceName;
    @JsonProperty("output_model_name")
    private java.lang.String outputModelName;
    @JsonProperty("template_id")
    private java.lang.String templateId;
    @JsonProperty("genome_id")
    private java.lang.String genomeId;
    @JsonProperty("compartment_translation")
    private Object compartmentTranslation;
    @JsonProperty("biomass_reactions")
    private java.lang.String biomassReactions;
    @JsonProperty("compound_mappings")
    private java.lang.String compoundMappings;
    @JsonProperty("gene_mappings")
    private java.lang.String geneMappings;
    @JsonProperty("create_extracellular")
    private Long createExtracellular;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("model_name")
    public java.lang.String getModelName() {
        return modelName;
    }

    @JsonProperty("model_name")
    public void setModelName(java.lang.String modelName) {
        this.modelName = modelName;
    }

    public IntegrateModelParams withModelName(java.lang.String modelName) {
        this.modelName = modelName;
        return this;
    }

    @JsonProperty("workspace_name")
    public java.lang.String getWorkspaceName() {
        return workspaceName;
    }

    @JsonProperty("workspace_name")
    public void setWorkspaceName(java.lang.String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public IntegrateModelParams withWorkspaceName(java.lang.String workspaceName) {
        this.workspaceName = workspaceName;
        return this;
    }

    @JsonProperty("output_model_name")
    public java.lang.String getOutputModelName() {
        return outputModelName;
    }

    @JsonProperty("output_model_name")
    public void setOutputModelName(java.lang.String outputModelName) {
        this.outputModelName = outputModelName;
    }

    public IntegrateModelParams withOutputModelName(java.lang.String outputModelName) {
        this.outputModelName = outputModelName;
        return this;
    }

    @JsonProperty("template_id")
    public java.lang.String getTemplateId() {
        return templateId;
    }

    @JsonProperty("template_id")
    public void setTemplateId(java.lang.String templateId) {
        this.templateId = templateId;
    }

    public IntegrateModelParams withTemplateId(java.lang.String templateId) {
        this.templateId = templateId;
        return this;
    }

    @JsonProperty("genome_id")
    public java.lang.String getGenomeId() {
        return genomeId;
    }

    @JsonProperty("genome_id")
    public void setGenomeId(java.lang.String genomeId) {
        this.genomeId = genomeId;
    }

    public IntegrateModelParams withGenomeId(java.lang.String genomeId) {
        this.genomeId = genomeId;
        return this;
    }

    @JsonProperty("compartment_translation")
    public Object getCompartmentTranslation() {
        return compartmentTranslation;
    }

    @JsonProperty("compartment_translation")
    public void setCompartmentTranslation(Object compartmentTranslation) {
        this.compartmentTranslation = compartmentTranslation;
    }

    public IntegrateModelParams withCompartmentTranslation(Object compartmentTranslation) {
        this.compartmentTranslation = compartmentTranslation;
        return this;
    }

    @JsonProperty("biomass_reactions")
    public java.lang.String getBiomassReactions() {
        return biomassReactions;
    }

    @JsonProperty("biomass_reactions")
    public void setBiomassReactions(java.lang.String biomassReactions) {
        this.biomassReactions = biomassReactions;
    }

    public IntegrateModelParams withBiomassReactions(java.lang.String biomassReactions) {
        this.biomassReactions = biomassReactions;
        return this;
    }

    @JsonProperty("compound_mappings")
    public java.lang.String getCompoundMappings() {
        return compoundMappings;
    }

    @JsonProperty("compound_mappings")
    public void setCompoundMappings(java.lang.String compoundMappings) {
        this.compoundMappings = compoundMappings;
    }

    public IntegrateModelParams withCompoundMappings(java.lang.String compoundMappings) {
        this.compoundMappings = compoundMappings;
        return this;
    }

    @JsonProperty("gene_mappings")
    public java.lang.String getGeneMappings() {
        return geneMappings;
    }

    @JsonProperty("gene_mappings")
    public void setGeneMappings(java.lang.String geneMappings) {
        this.geneMappings = geneMappings;
    }

    public IntegrateModelParams withGeneMappings(java.lang.String geneMappings) {
        this.geneMappings = geneMappings;
        return this;
    }

    @JsonProperty("create_extracellular")
    public Long getCreateExtracellular() {
        return createExtracellular;
    }

    @JsonProperty("create_extracellular")
    public void setCreateExtracellular(Long createExtracellular) {
        this.createExtracellular = createExtracellular;
    }

    public IntegrateModelParams withCreateExtracellular(Long createExtracellular) {
        this.createExtracellular = createExtracellular;
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
        return ((((((((((((((((((((((("IntegrateModelParams"+" [modelName=")+ modelName)+", workspaceName=")+ workspaceName)+", outputModelName=")+ outputModelName)+", templateId=")+ templateId)+", genomeId=")+ genomeId)+", compartmentTranslation=")+ compartmentTranslation)+", biomassReactions=")+ biomassReactions)+", compoundMappings=")+ compoundMappings)+", geneMappings=")+ geneMappings)+", createExtracellular=")+ createExtracellular)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
