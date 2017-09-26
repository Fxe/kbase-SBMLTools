
package sbmltools;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: AutoPropagateModelParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "genome_id",
    "workspace_name",
    "output_model_name"
})
public class AutoPropagateModelParams {

    @JsonProperty("genome_id")
    private String genomeId;
    @JsonProperty("workspace_name")
    private String workspaceName;
    @JsonProperty("output_model_name")
    private String outputModelName;
    @JsonProperty("num_models_propagate")
    private Long numModelsPropagate;
    
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("genome_id")
    public String getGenomeId() {
        return genomeId;
    }

    @JsonProperty("genome_id")
    public void setGenomeId(String genomeId) {
        this.genomeId = genomeId;
    }
    
    @JsonProperty("num_models_propagate")
    public Long getNumModelsPropagate() {
      return numModelsPropagate;
    }

    @JsonProperty("num_models_propagate")
    public void setNumModelsPropagate(Long numModelsPropagate) {
      this.numModelsPropagate = numModelsPropagate;
    }

    public AutoPropagateModelParams withGenomeId(String genomeId) {
        this.genomeId = genomeId;
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

    public AutoPropagateModelParams withWorkspaceName(String workspaceName) {
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

    public AutoPropagateModelParams withOutputModelName(String outputModelName) {
        this.outputModelName = outputModelName;
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
        return ((((((((("AutoPropagateModelParams"+" [genomeId=")+ genomeId)+", workspaceName=")+ workspaceName)+", outputModelName=")+ outputModelName)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
