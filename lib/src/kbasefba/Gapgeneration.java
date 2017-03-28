
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
 * <p>Original spec-file type: Gapgeneration</p>
 * <pre>
 * GapGeneration object holds data on formulation and solutions from gapgen analysis
 * @optional fba_ref totalTimeLimit timePerSolution media_ref referenceMedia_ref gprHypothesis reactionRemovalHypothesis biomassHypothesis mediaHypothesis
 *     @metadata ws fba_ref as FBA
 *     @metadata ws fbamodel_ref as Model
 *     @metadata ws length(gapgenSolutions) as Number solutions
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "fba_ref",
    "fbamodel_ref",
    "mediaHypothesis",
    "biomassHypothesis",
    "gprHypothesis",
    "reactionRemovalHypothesis",
    "media_ref",
    "referenceMedia_ref",
    "timePerSolution",
    "totalTimeLimit",
    "gapgenSolutions"
})
public class Gapgeneration {

    @JsonProperty("id")
    private String id;
    @JsonProperty("fba_ref")
    private String fbaRef;
    @JsonProperty("fbamodel_ref")
    private String fbamodelRef;
    @JsonProperty("mediaHypothesis")
    private Long mediaHypothesis;
    @JsonProperty("biomassHypothesis")
    private Long biomassHypothesis;
    @JsonProperty("gprHypothesis")
    private Long gprHypothesis;
    @JsonProperty("reactionRemovalHypothesis")
    private Long reactionRemovalHypothesis;
    @JsonProperty("media_ref")
    private String mediaRef;
    @JsonProperty("referenceMedia_ref")
    private String referenceMediaRef;
    @JsonProperty("timePerSolution")
    private Long timePerSolution;
    @JsonProperty("totalTimeLimit")
    private Long totalTimeLimit;
    @JsonProperty("gapgenSolutions")
    private List<GapgenerationSolution> gapgenSolutions;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Gapgeneration withId(String id) {
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

    public Gapgeneration withFbaRef(String fbaRef) {
        this.fbaRef = fbaRef;
        return this;
    }

    @JsonProperty("fbamodel_ref")
    public String getFbamodelRef() {
        return fbamodelRef;
    }

    @JsonProperty("fbamodel_ref")
    public void setFbamodelRef(String fbamodelRef) {
        this.fbamodelRef = fbamodelRef;
    }

    public Gapgeneration withFbamodelRef(String fbamodelRef) {
        this.fbamodelRef = fbamodelRef;
        return this;
    }

    @JsonProperty("mediaHypothesis")
    public Long getMediaHypothesis() {
        return mediaHypothesis;
    }

    @JsonProperty("mediaHypothesis")
    public void setMediaHypothesis(Long mediaHypothesis) {
        this.mediaHypothesis = mediaHypothesis;
    }

    public Gapgeneration withMediaHypothesis(Long mediaHypothesis) {
        this.mediaHypothesis = mediaHypothesis;
        return this;
    }

    @JsonProperty("biomassHypothesis")
    public Long getBiomassHypothesis() {
        return biomassHypothesis;
    }

    @JsonProperty("biomassHypothesis")
    public void setBiomassHypothesis(Long biomassHypothesis) {
        this.biomassHypothesis = biomassHypothesis;
    }

    public Gapgeneration withBiomassHypothesis(Long biomassHypothesis) {
        this.biomassHypothesis = biomassHypothesis;
        return this;
    }

    @JsonProperty("gprHypothesis")
    public Long getGprHypothesis() {
        return gprHypothesis;
    }

    @JsonProperty("gprHypothesis")
    public void setGprHypothesis(Long gprHypothesis) {
        this.gprHypothesis = gprHypothesis;
    }

    public Gapgeneration withGprHypothesis(Long gprHypothesis) {
        this.gprHypothesis = gprHypothesis;
        return this;
    }

    @JsonProperty("reactionRemovalHypothesis")
    public Long getReactionRemovalHypothesis() {
        return reactionRemovalHypothesis;
    }

    @JsonProperty("reactionRemovalHypothesis")
    public void setReactionRemovalHypothesis(Long reactionRemovalHypothesis) {
        this.reactionRemovalHypothesis = reactionRemovalHypothesis;
    }

    public Gapgeneration withReactionRemovalHypothesis(Long reactionRemovalHypothesis) {
        this.reactionRemovalHypothesis = reactionRemovalHypothesis;
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

    public Gapgeneration withMediaRef(String mediaRef) {
        this.mediaRef = mediaRef;
        return this;
    }

    @JsonProperty("referenceMedia_ref")
    public String getReferenceMediaRef() {
        return referenceMediaRef;
    }

    @JsonProperty("referenceMedia_ref")
    public void setReferenceMediaRef(String referenceMediaRef) {
        this.referenceMediaRef = referenceMediaRef;
    }

    public Gapgeneration withReferenceMediaRef(String referenceMediaRef) {
        this.referenceMediaRef = referenceMediaRef;
        return this;
    }

    @JsonProperty("timePerSolution")
    public Long getTimePerSolution() {
        return timePerSolution;
    }

    @JsonProperty("timePerSolution")
    public void setTimePerSolution(Long timePerSolution) {
        this.timePerSolution = timePerSolution;
    }

    public Gapgeneration withTimePerSolution(Long timePerSolution) {
        this.timePerSolution = timePerSolution;
        return this;
    }

    @JsonProperty("totalTimeLimit")
    public Long getTotalTimeLimit() {
        return totalTimeLimit;
    }

    @JsonProperty("totalTimeLimit")
    public void setTotalTimeLimit(Long totalTimeLimit) {
        this.totalTimeLimit = totalTimeLimit;
    }

    public Gapgeneration withTotalTimeLimit(Long totalTimeLimit) {
        this.totalTimeLimit = totalTimeLimit;
        return this;
    }

    @JsonProperty("gapgenSolutions")
    public List<GapgenerationSolution> getGapgenSolutions() {
        return gapgenSolutions;
    }

    @JsonProperty("gapgenSolutions")
    public void setGapgenSolutions(List<GapgenerationSolution> gapgenSolutions) {
        this.gapgenSolutions = gapgenSolutions;
    }

    public Gapgeneration withGapgenSolutions(List<GapgenerationSolution> gapgenSolutions) {
        this.gapgenSolutions = gapgenSolutions;
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
        return ((((((((((((((((((((((((((("Gapgeneration"+" [id=")+ id)+", fbaRef=")+ fbaRef)+", fbamodelRef=")+ fbamodelRef)+", mediaHypothesis=")+ mediaHypothesis)+", biomassHypothesis=")+ biomassHypothesis)+", gprHypothesis=")+ gprHypothesis)+", reactionRemovalHypothesis=")+ reactionRemovalHypothesis)+", mediaRef=")+ mediaRef)+", referenceMediaRef=")+ referenceMediaRef)+", timePerSolution=")+ timePerSolution)+", totalTimeLimit=")+ totalTimeLimit)+", gapgenSolutions=")+ gapgenSolutions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
