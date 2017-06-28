
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
 * <p>Original spec-file type: FBAMinimalReactionsResult</p>
 * <pre>
 * FBAMinimalReactionsResult object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "suboptimal",
    "totalcost",
    "reaction_refs",
    "reaction_directions"
})
public class FBAMinimalReactionsResult {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("suboptimal")
    private Long suboptimal;
    @JsonProperty("totalcost")
    private Double totalcost;
    @JsonProperty("reaction_refs")
    private List<String> reactionRefs;
    @JsonProperty("reaction_directions")
    private List<String> reactionDirections;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FBAMinimalReactionsResult withId(java.lang.String id) {
        this.id = id;
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

    public FBAMinimalReactionsResult withSuboptimal(Long suboptimal) {
        this.suboptimal = suboptimal;
        return this;
    }

    @JsonProperty("totalcost")
    public Double getTotalcost() {
        return totalcost;
    }

    @JsonProperty("totalcost")
    public void setTotalcost(Double totalcost) {
        this.totalcost = totalcost;
    }

    public FBAMinimalReactionsResult withTotalcost(Double totalcost) {
        this.totalcost = totalcost;
        return this;
    }

    @JsonProperty("reaction_refs")
    public List<String> getReactionRefs() {
        return reactionRefs;
    }

    @JsonProperty("reaction_refs")
    public void setReactionRefs(List<String> reactionRefs) {
        this.reactionRefs = reactionRefs;
    }

    public FBAMinimalReactionsResult withReactionRefs(List<String> reactionRefs) {
        this.reactionRefs = reactionRefs;
        return this;
    }

    @JsonProperty("reaction_directions")
    public List<String> getReactionDirections() {
        return reactionDirections;
    }

    @JsonProperty("reaction_directions")
    public void setReactionDirections(List<String> reactionDirections) {
        this.reactionDirections = reactionDirections;
    }

    public FBAMinimalReactionsResult withReactionDirections(List<String> reactionDirections) {
        this.reactionDirections = reactionDirections;
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
        return ((((((((((((("FBAMinimalReactionsResult"+" [id=")+ id)+", suboptimal=")+ suboptimal)+", totalcost=")+ totalcost)+", reactionRefs=")+ reactionRefs)+", reactionDirections=")+ reactionDirections)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
