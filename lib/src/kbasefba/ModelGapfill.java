
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
 * <p>Original spec-file type: ModelGapfill</p>
 * <pre>
 * ModelGapfill object
 *  
 * @optional integrated_solution
 * @optional fba_ref
 * @optional gapfill_ref jobnode
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "gapfill_id",
    "gapfill_ref",
    "fba_ref",
    "integrated",
    "integrated_solution",
    "media_ref",
    "jobnode"
})
public class ModelGapfill {

    @JsonProperty("id")
    private String id;
    @JsonProperty("gapfill_id")
    private String gapfillId;
    @JsonProperty("gapfill_ref")
    private String gapfillRef;
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

    public ModelGapfill withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("gapfill_id")
    public String getGapfillId() {
        return gapfillId;
    }

    @JsonProperty("gapfill_id")
    public void setGapfillId(String gapfillId) {
        this.gapfillId = gapfillId;
    }

    public ModelGapfill withGapfillId(String gapfillId) {
        this.gapfillId = gapfillId;
        return this;
    }

    @JsonProperty("gapfill_ref")
    public String getGapfillRef() {
        return gapfillRef;
    }

    @JsonProperty("gapfill_ref")
    public void setGapfillRef(String gapfillRef) {
        this.gapfillRef = gapfillRef;
    }

    public ModelGapfill withGapfillRef(String gapfillRef) {
        this.gapfillRef = gapfillRef;
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

    public ModelGapfill withFbaRef(String fbaRef) {
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

    public ModelGapfill withIntegrated(Long integrated) {
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

    public ModelGapfill withIntegratedSolution(String integratedSolution) {
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

    public ModelGapfill withMediaRef(String mediaRef) {
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

    public ModelGapfill withJobnode(String jobnode) {
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
        return ((((((((((((((((((("ModelGapfill"+" [id=")+ id)+", gapfillId=")+ gapfillId)+", gapfillRef=")+ gapfillRef)+", fbaRef=")+ fbaRef)+", integrated=")+ integrated)+", integratedSolution=")+ integratedSolution)+", mediaRef=")+ mediaRef)+", jobnode=")+ jobnode)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
