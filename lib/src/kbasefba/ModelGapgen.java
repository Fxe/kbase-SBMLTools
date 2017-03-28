
package kbasefba;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: ModelGapgen</p>
 * <pre>
 * ModelGapgen object
 * @optional integrated_solution
 * @optional fba_ref
 * @optional gapgen_ref jobnode
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "gapgen_id",
    "gapgen_ref",
    "fba_ref",
    "integrated",
    "integrated_solution",
    "media_ref",
    "jobnode"
})
public class ModelGapgen {

    @JsonProperty("id")
    private String id;
    @JsonProperty("gapgen_id")
    private String gapgenId;
    @JsonProperty("gapgen_ref")
    private String gapgenRef;
    @JsonProperty("fba_ref")
    private String fbaRef;
    @JsonProperty("integrated")
    private Long integrated;
    @JsonProperty("integrated_solution")
    private String integratedSolution;
    @JsonProperty("media_ref")
    private String mediaRef;
    @JsonProperty("jobnode")
    private String jobnode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ModelGapgen withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("gapgen_id")
    public String getGapgenId() {
        return gapgenId;
    }

    @JsonProperty("gapgen_id")
    public void setGapgenId(String gapgenId) {
        this.gapgenId = gapgenId;
    }

    public ModelGapgen withGapgenId(String gapgenId) {
        this.gapgenId = gapgenId;
        return this;
    }

    @JsonProperty("gapgen_ref")
    public String getGapgenRef() {
        return gapgenRef;
    }

    @JsonProperty("gapgen_ref")
    public void setGapgenRef(String gapgenRef) {
        this.gapgenRef = gapgenRef;
    }

    public ModelGapgen withGapgenRef(String gapgenRef) {
        this.gapgenRef = gapgenRef;
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

    public ModelGapgen withFbaRef(String fbaRef) {
        this.fbaRef = fbaRef;
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

    public ModelGapgen withIntegrated(Long integrated) {
        this.integrated = integrated;
        return this;
    }

    @JsonProperty("integrated_solution")
    public String getIntegratedSolution() {
        return integratedSolution;
    }

    @JsonProperty("integrated_solution")
    public void setIntegratedSolution(String integratedSolution) {
        this.integratedSolution = integratedSolution;
    }

    public ModelGapgen withIntegratedSolution(String integratedSolution) {
        this.integratedSolution = integratedSolution;
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

    public ModelGapgen withMediaRef(String mediaRef) {
        this.mediaRef = mediaRef;
        return this;
    }

    @JsonProperty("jobnode")
    public String getJobnode() {
        return jobnode;
    }

    @JsonProperty("jobnode")
    public void setJobnode(String jobnode) {
        this.jobnode = jobnode;
    }

    public ModelGapgen withJobnode(String jobnode) {
        this.jobnode = jobnode;
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
        return ((((((((((((((((((("ModelGapgen"+" [id=")+ id)+", gapgenId=")+ gapgenId)+", gapgenRef=")+ gapgenRef)+", fbaRef=")+ fbaRef)+", integrated=")+ integrated)+", integratedSolution=")+ integratedSolution)+", mediaRef=")+ mediaRef)+", jobnode=")+ jobnode)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
