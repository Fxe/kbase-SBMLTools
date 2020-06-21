
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
 * <p>Original spec-file type: SbmlImporterParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "sbml_url",
    "sbml_local_path",
    "input_staging_file_path",
    "file_type",
    "workspace_name",
    "genome_ref",
    "biomass",
    "model_name",
    "automatically_integrate",
    "remove_boundary",
    "conflict_resolution"
})
public class SbmlImporterParams {

    @JsonProperty("sbml_url")
    private java.lang.String sbmlUrl;
    @JsonProperty("sbml_local_path")
    private java.lang.String sbmlLocalPath;
    @JsonProperty("input_staging_file_path")
    private java.lang.String inputStagingFilePath;
    @JsonProperty("file_type")
    private java.lang.String fileType;
    @JsonProperty("workspace_name")
    private java.lang.String workspaceName;
    @JsonProperty("genome_ref")
    private java.lang.String genomeRef;
    @JsonProperty("biomass")
    private List<String> biomass;
    @JsonProperty("model_name")
    private java.lang.String modelName;
    @JsonProperty("automatically_integrate")
    private Long automaticallyIntegrate;
    @JsonProperty("remove_boundary")
    private Long removeBoundary;
    @JsonProperty("conflict_resolution")
    private java.lang.String conflictResolution;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("sbml_url")
    public java.lang.String getSbmlUrl() {
        return sbmlUrl;
    }

    @JsonProperty("sbml_url")
    public void setSbmlUrl(java.lang.String sbmlUrl) {
        this.sbmlUrl = sbmlUrl;
    }

    public SbmlImporterParams withSbmlUrl(java.lang.String sbmlUrl) {
        this.sbmlUrl = sbmlUrl;
        return this;
    }

    @JsonProperty("sbml_local_path")
    public java.lang.String getSbmlLocalPath() {
        return sbmlLocalPath;
    }

    @JsonProperty("sbml_local_path")
    public void setSbmlLocalPath(java.lang.String sbmlLocalPath) {
        this.sbmlLocalPath = sbmlLocalPath;
    }

    public SbmlImporterParams withSbmlLocalPath(java.lang.String sbmlLocalPath) {
        this.sbmlLocalPath = sbmlLocalPath;
        return this;
    }

    @JsonProperty("input_staging_file_path")
    public java.lang.String getInputStagingFilePath() {
        return inputStagingFilePath;
    }

    @JsonProperty("input_staging_file_path")
    public void setInputStagingFilePath(java.lang.String inputStagingFilePath) {
        this.inputStagingFilePath = inputStagingFilePath;
    }

    public SbmlImporterParams withInputStagingFilePath(java.lang.String inputStagingFilePath) {
        this.inputStagingFilePath = inputStagingFilePath;
        return this;
    }

    @JsonProperty("file_type")
    public java.lang.String getFileType() {
        return fileType;
    }

    @JsonProperty("file_type")
    public void setFileType(java.lang.String fileType) {
        this.fileType = fileType;
    }

    public SbmlImporterParams withFileType(java.lang.String fileType) {
        this.fileType = fileType;
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

    public SbmlImporterParams withWorkspaceName(java.lang.String workspaceName) {
        this.workspaceName = workspaceName;
        return this;
    }

    @JsonProperty("genome_ref")
    public java.lang.String getGenomeRef() {
        return genomeRef;
    }

    @JsonProperty("genome_ref")
    public void setGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
    }

    public SbmlImporterParams withGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("biomass")
    public List<String> getBiomass() {
        return biomass;
    }

    @JsonProperty("biomass")
    public void setBiomass(List<String> biomass) {
        this.biomass = biomass;
    }

    public SbmlImporterParams withBiomass(List<String> biomass) {
        this.biomass = biomass;
        return this;
    }

    @JsonProperty("model_name")
    public java.lang.String getModelName() {
        return modelName;
    }

    @JsonProperty("model_name")
    public void setModelName(java.lang.String modelName) {
        this.modelName = modelName;
    }

    public SbmlImporterParams withModelName(java.lang.String modelName) {
        this.modelName = modelName;
        return this;
    }

    @JsonProperty("automatically_integrate")
    public Long getAutomaticallyIntegrate() {
        return automaticallyIntegrate;
    }

    @JsonProperty("automatically_integrate")
    public void setAutomaticallyIntegrate(Long automaticallyIntegrate) {
        this.automaticallyIntegrate = automaticallyIntegrate;
    }

    public SbmlImporterParams withAutomaticallyIntegrate(Long automaticallyIntegrate) {
        this.automaticallyIntegrate = automaticallyIntegrate;
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

    public SbmlImporterParams withRemoveBoundary(Long removeBoundary) {
        this.removeBoundary = removeBoundary;
        return this;
    }

    @JsonProperty("conflict_resolution")
    public java.lang.String getConflictResolution() {
        return conflictResolution;
    }

    @JsonProperty("conflict_resolution")
    public void setConflictResolution(java.lang.String conflictResolution) {
        this.conflictResolution = conflictResolution;
    }

    public SbmlImporterParams withConflictResolution(java.lang.String conflictResolution) {
        this.conflictResolution = conflictResolution;
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
        return ((((((((((((((((((((((((("SbmlImporterParams"+" [sbmlUrl=")+ sbmlUrl)+", sbmlLocalPath=")+ sbmlLocalPath)+", inputStagingFilePath=")+ inputStagingFilePath)+", fileType=")+ fileType)+", workspaceName=")+ workspaceName)+", genomeRef=")+ genomeRef)+", biomass=")+ biomass)+", modelName=")+ modelName)+", automaticallyIntegrate=")+ automaticallyIntegrate)+", removeBoundary=")+ removeBoundary)+", conflictResolution=")+ conflictResolution)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
