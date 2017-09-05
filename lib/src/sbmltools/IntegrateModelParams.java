
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
 * <pre>
 * list<mapping<string, string>> compartment_translation;
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "model_name",
    "workspace_name",
    "output_model_name",
    "output_media_name",
    "template_id",
    "genome_id",
    "compartment_translation",
    "biomass_reactions",
    "compound_mappings",
    "gene_mappings",
    "create_extracellular",
    "remove_boundary",
    "fill_metadata",
    "translate_database"
})
public class IntegrateModelParams {

    @JsonProperty("model_name")
    private String modelName;
    @JsonProperty("workspace_name")
    private String workspaceName;
    @JsonProperty("output_model_name")
    private String outputModelName;
    @JsonProperty("output_media_name")
    private String outputMediaName;
    @JsonProperty("template_id")
    private String templateId;
    @JsonProperty("genome_id")
    private String genomeId;
    @JsonProperty("compartment_translation")
    private List<CompartmentMapping> compartmentTranslation;
    @JsonProperty("biomass_reactions")
    private String biomassReactions;
    @JsonProperty("compound_mappings")
    private String compoundMappings;
    @JsonProperty("gene_mappings")
    private String geneMappings;
    @JsonProperty("create_extracellular")
    private Long createExtracellular;
    @JsonProperty("remove_boundary")
    private Long removeBoundary;
    @JsonProperty("fill_metadata")
    private Long fillMetadata;
    @JsonProperty("translate_database")
    private String translateDatabase;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("model_name")
    public String getModelName() {
        return modelName;
    }

    @JsonProperty("model_name")
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public IntegrateModelParams withModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    @JsonProperty("workspace_name")
    public String getWorkspaceName() {
        return workspaceName;
    }

    @JsonProperty("workspace_name")
    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public IntegrateModelParams withWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
        return this;
    }

    @JsonProperty("output_model_name")
    public String getOutputModelName() {
        return outputModelName;
    }

    @JsonProperty("output_model_name")
    public void setOutputModelName(String outputModelName) {
        this.outputModelName = outputModelName;
    }

    public IntegrateModelParams withOutputModelName(String outputModelName) {
        this.outputModelName = outputModelName;
        return this;
    }

    @JsonProperty("output_media_name")
    public String getOutputMediaName() {
        return outputMediaName;
    }

    @JsonProperty("output_media_name")
    public void setOutputMediaName(String outputMediaName) {
        this.outputMediaName = outputMediaName;
    }

    public IntegrateModelParams withOutputMediaName(String outputMediaName) {
        this.outputMediaName = outputMediaName;
        return this;
    }

    @JsonProperty("template_id")
    public String getTemplateId() {
        return templateId;
    }

    @JsonProperty("template_id")
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public IntegrateModelParams withTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    @JsonProperty("genome_id")
    public String getGenomeId() {
        return genomeId;
    }

    @JsonProperty("genome_id")
    public void setGenomeId(String genomeId) {
        this.genomeId = genomeId;
    }

    public IntegrateModelParams withGenomeId(String genomeId) {
        this.genomeId = genomeId;
        return this;
    }

    @JsonProperty("compartment_translation")
    public List<CompartmentMapping> getCompartmentTranslation() {
        return compartmentTranslation;
    }

    @JsonProperty("compartment_translation")
    public void setCompartmentTranslation(List<CompartmentMapping> compartmentTranslation) {
        this.compartmentTranslation = compartmentTranslation;
    }

    public IntegrateModelParams withCompartmentTranslation(List<CompartmentMapping> compartmentTranslation) {
        this.compartmentTranslation = compartmentTranslation;
        return this;
    }

    @JsonProperty("biomass_reactions")
    public String getBiomassReactions() {
        return biomassReactions;
    }

    @JsonProperty("biomass_reactions")
    public void setBiomassReactions(String biomassReactions) {
        this.biomassReactions = biomassReactions;
    }

    public IntegrateModelParams withBiomassReactions(String biomassReactions) {
        this.biomassReactions = biomassReactions;
        return this;
    }

    @JsonProperty("compound_mappings")
    public String getCompoundMappings() {
        return compoundMappings;
    }

    @JsonProperty("compound_mappings")
    public void setCompoundMappings(String compoundMappings) {
        this.compoundMappings = compoundMappings;
    }

    public IntegrateModelParams withCompoundMappings(String compoundMappings) {
        this.compoundMappings = compoundMappings;
        return this;
    }

    @JsonProperty("gene_mappings")
    public String getGeneMappings() {
        return geneMappings;
    }

    @JsonProperty("gene_mappings")
    public void setGeneMappings(String geneMappings) {
        this.geneMappings = geneMappings;
    }

    public IntegrateModelParams withGeneMappings(String geneMappings) {
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

    @JsonProperty("remove_boundary")
    public Long getRemoveBoundary() {
        return removeBoundary;
    }

    @JsonProperty("remove_boundary")
    public void setRemoveBoundary(Long removeBoundary) {
        this.removeBoundary = removeBoundary;
    }

    public IntegrateModelParams withRemoveBoundary(Long removeBoundary) {
        this.removeBoundary = removeBoundary;
        return this;
    }

    @JsonProperty("fill_metadata")
    public Long getFillMetadata() {
        return fillMetadata;
    }

    @JsonProperty("fill_metadata")
    public void setFillMetadata(Long fillMetadata) {
        this.fillMetadata = fillMetadata;
    }

    public IntegrateModelParams withFillMetadata(Long fillMetadata) {
        this.fillMetadata = fillMetadata;
        return this;
    }

    @JsonProperty("translate_database")
    public String getTranslateDatabase() {
        return translateDatabase;
    }

    @JsonProperty("translate_database")
    public void setTranslateDatabase(String translateDatabase) {
        this.translateDatabase = translateDatabase;
    }

    public IntegrateModelParams withTranslateDatabase(String translateDatabase) {
        this.translateDatabase = translateDatabase;
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
        return ((((((((((((((((((((((((((((((("IntegrateModelParams"+" [modelName=")+ modelName)+", workspaceName=")+ workspaceName)+", outputModelName=")+ outputModelName)+", outputMediaName=")+ outputMediaName)+", templateId=")+ templateId)+", genomeId=")+ genomeId)+", compartmentTranslation=")+ compartmentTranslation)+", biomassReactions=")+ biomassReactions)+", compoundMappings=")+ compoundMappings)+", geneMappings=")+ geneMappings)+", createExtracellular=")+ createExtracellular)+", removeBoundary=")+ removeBoundary)+", fillMetadata=")+ fillMetadata)+", translateDatabase=")+ translateDatabase)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
