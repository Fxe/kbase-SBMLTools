
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
    "workspace_name",
    "biomass",
    "model_name",
    "automatically_integrate"
})
public class SbmlImporterParams {

    @JsonProperty("sbml_url")
    private java.lang.String sbmlUrl;
    @JsonProperty("workspace_name")
    private java.lang.String workspaceName;
    @JsonProperty("biomass")
    private List<String> biomass;
    @JsonProperty("model_name")
    private java.lang.String modelName;
    @JsonProperty("automatically_integrate")
    private Long automaticallyIntegrate;
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
        return ((((((((((((("SbmlImporterParams"+" [sbmlUrl=")+ sbmlUrl)+", workspaceName=")+ workspaceName)+", biomass=")+ biomass)+", modelName=")+ modelName)+", automaticallyIntegrate=")+ automaticallyIntegrate)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
