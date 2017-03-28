
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
 * <p>Original spec-file type: ModelQuantOpt</p>
 * <pre>
 * ModelQuantOpt object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "fba_ref",
    "media_ref",
    "integrated",
    "integrated_solution",
    "solutions"
})
public class ModelQuantOpt {

    @JsonProperty("id")
    private String id;
    @JsonProperty("fba_ref")
    private String fbaRef;
    @JsonProperty("media_ref")
    private String mediaRef;
    @JsonProperty("integrated")
    private Long integrated;
    @JsonProperty("integrated_solution")
    private Long integratedSolution;
    @JsonProperty("solutions")
    private List<QuantOptSolution> solutions;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ModelQuantOpt withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("fba_ref")
    public String getFbaRef() {
        return fbaRef;
    }

    @JsonProperty("fba_ref")
    public void setFbaRef(String fbaRef) {
        this.fbaRef = fbaRef;
    }

    public ModelQuantOpt withFbaRef(String fbaRef) {
        this.fbaRef = fbaRef;
        return this;
    }

    @JsonProperty("media_ref")
    public String getMediaRef() {
        return mediaRef;
    }

    @JsonProperty("media_ref")
    public void setMediaRef(String mediaRef) {
        this.mediaRef = mediaRef;
    }

    public ModelQuantOpt withMediaRef(String mediaRef) {
        this.mediaRef = mediaRef;
        return this;
    }

    @JsonProperty("integrated")
    public Long getIntegrated() {
        return integrated;
    }

    @JsonProperty("integrated")
    public void setIntegrated(Long integrated) {
        this.integrated = integrated;
    }

    public ModelQuantOpt withIntegrated(Long integrated) {
        this.integrated = integrated;
        return this;
    }

    @JsonProperty("integrated_solution")
    public Long getIntegratedSolution() {
        return integratedSolution;
    }

    @JsonProperty("integrated_solution")
    public void setIntegratedSolution(Long integratedSolution) {
        this.integratedSolution = integratedSolution;
    }

    public ModelQuantOpt withIntegratedSolution(Long integratedSolution) {
        this.integratedSolution = integratedSolution;
        return this;
    }

    @JsonProperty("solutions")
    public List<QuantOptSolution> getSolutions() {
        return solutions;
    }

    @JsonProperty("solutions")
    public void setSolutions(List<QuantOptSolution> solutions) {
        this.solutions = solutions;
    }

    public ModelQuantOpt withSolutions(List<QuantOptSolution> solutions) {
        this.solutions = solutions;
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
        return ((((((((((((((("ModelQuantOpt"+" [id=")+ id)+", fbaRef=")+ fbaRef)+", mediaRef=")+ mediaRef)+", integrated=")+ integrated)+", integratedSolution=")+ integratedSolution)+", solutions=")+ solutions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
