
package kbasegenomes;

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
 * <p>Original spec-file type: Analysis_event</p>
 * <pre>
 * @optional tool_name execution_time parameters hostname
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "tool_name",
    "execution_time",
    "parameters",
    "hostname"
})
public class AnalysisEvent {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("tool_name")
    private java.lang.String toolName;
    @JsonProperty("execution_time")
    private Double executionTime;
    @JsonProperty("parameters")
    private List<String> parameters;
    @JsonProperty("hostname")
    private java.lang.String hostname;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public AnalysisEvent withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("tool_name")
    public java.lang.String getToolName() {
        return toolName;
    }

    @JsonProperty("tool_name")
    public void setToolName(java.lang.String toolName) {
        this.toolName = toolName;
    }

    public AnalysisEvent withToolName(java.lang.String toolName) {
        this.toolName = toolName;
        return this;
    }

    @JsonProperty("execution_time")
    public Double getExecutionTime() {
        return executionTime;
    }

    @JsonProperty("execution_time")
    public void setExecutionTime(Double executionTime) {
        this.executionTime = executionTime;
    }

    public AnalysisEvent withExecutionTime(Double executionTime) {
        this.executionTime = executionTime;
        return this;
    }

    @JsonProperty("parameters")
    public List<String> getParameters() {
        return parameters;
    }

    @JsonProperty("parameters")
    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public AnalysisEvent withParameters(List<String> parameters) {
        this.parameters = parameters;
        return this;
    }

    @JsonProperty("hostname")
    public java.lang.String getHostname() {
        return hostname;
    }

    @JsonProperty("hostname")
    public void setHostname(java.lang.String hostname) {
        this.hostname = hostname;
    }

    public AnalysisEvent withHostname(java.lang.String hostname) {
        this.hostname = hostname;
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
        return ((((((((((((("AnalysisEvent"+" [id=")+ id)+", toolName=")+ toolName)+", executionTime=")+ executionTime)+", parameters=")+ parameters)+", hostname=")+ hostname)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
