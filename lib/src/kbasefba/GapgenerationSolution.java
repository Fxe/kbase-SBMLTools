
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
 * <p>Original spec-file type: GapgenerationSolution</p>
 * <pre>
 * GapGenerationSolution object holds data on a solution proposed by the gapgeneration command
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "solutionCost",
    "biomassSuppplement_refs",
    "mediaRemoval_refs",
    "additionalKO_refs",
    "integrated",
    "suboptimal",
    "gapgenSolutionReactions"
})
public class GapgenerationSolution {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("solutionCost")
    private Double solutionCost;
    @JsonProperty("biomassSuppplement_refs")
    private List<String> biomassSuppplementRefs;
    @JsonProperty("mediaRemoval_refs")
    private List<String> mediaRemovalRefs;
    @JsonProperty("additionalKO_refs")
    private List<String> additionalKORefs;
    @JsonProperty("integrated")
    private Long integrated;
    @JsonProperty("suboptimal")
    private Long suboptimal;
    @JsonProperty("gapgenSolutionReactions")
    private List<GapgenerationSolutionReaction> gapgenSolutionReactions;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public GapgenerationSolution withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("solutionCost")
    public Double getSolutionCost() {
        return solutionCost;
    }

    @JsonProperty("solutionCost")
    public void setSolutionCost(Double solutionCost) {
        this.solutionCost = solutionCost;
    }

    public GapgenerationSolution withSolutionCost(Double solutionCost) {
        this.solutionCost = solutionCost;
        return this;
    }

    @JsonProperty("biomassSuppplement_refs")
    public List<String> getBiomassSuppplementRefs() {
        return biomassSuppplementRefs;
    }

    @JsonProperty("biomassSuppplement_refs")
    public void setBiomassSuppplementRefs(List<String> biomassSuppplementRefs) {
        this.biomassSuppplementRefs = biomassSuppplementRefs;
    }

    public GapgenerationSolution withBiomassSuppplementRefs(List<String> biomassSuppplementRefs) {
        this.biomassSuppplementRefs = biomassSuppplementRefs;
        return this;
    }

    @JsonProperty("mediaRemoval_refs")
    public List<String> getMediaRemovalRefs() {
        return mediaRemovalRefs;
    }

    @JsonProperty("mediaRemoval_refs")
    public void setMediaRemovalRefs(List<String> mediaRemovalRefs) {
        this.mediaRemovalRefs = mediaRemovalRefs;
    }

    public GapgenerationSolution withMediaRemovalRefs(List<String> mediaRemovalRefs) {
        this.mediaRemovalRefs = mediaRemovalRefs;
        return this;
    }

    @JsonProperty("additionalKO_refs")
    public List<String> getAdditionalKORefs() {
        return additionalKORefs;
    }

    @JsonProperty("additionalKO_refs")
    public void setAdditionalKORefs(List<String> additionalKORefs) {
        this.additionalKORefs = additionalKORefs;
    }

    public GapgenerationSolution withAdditionalKORefs(List<String> additionalKORefs) {
        this.additionalKORefs = additionalKORefs;
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

    public GapgenerationSolution withIntegrated(Long integrated) {
        this.integrated = integrated;
        return this;
    }

    @JsonProperty("suboptimal")
    public Long getSuboptimal() {
        return suboptimal;
    }

    @JsonProperty("suboptimal")
    public void setSuboptimal(Long suboptimal) {
        this.suboptimal = suboptimal;
    }

    public GapgenerationSolution withSuboptimal(Long suboptimal) {
        this.suboptimal = suboptimal;
        return this;
    }

    @JsonProperty("gapgenSolutionReactions")
    public List<GapgenerationSolutionReaction> getGapgenSolutionReactions() {
        return gapgenSolutionReactions;
    }

    @JsonProperty("gapgenSolutionReactions")
    public void setGapgenSolutionReactions(List<GapgenerationSolutionReaction> gapgenSolutionReactions) {
        this.gapgenSolutionReactions = gapgenSolutionReactions;
    }

    public GapgenerationSolution withGapgenSolutionReactions(List<GapgenerationSolutionReaction> gapgenSolutionReactions) {
        this.gapgenSolutionReactions = gapgenSolutionReactions;
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
        return ((((((((((((((((((("GapgenerationSolution"+" [id=")+ id)+", solutionCost=")+ solutionCost)+", biomassSuppplementRefs=")+ biomassSuppplementRefs)+", mediaRemovalRefs=")+ mediaRemovalRefs)+", additionalKORefs=")+ additionalKORefs)+", integrated=")+ integrated)+", suboptimal=")+ suboptimal)+", gapgenSolutionReactions=")+ gapgenSolutionReactions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
