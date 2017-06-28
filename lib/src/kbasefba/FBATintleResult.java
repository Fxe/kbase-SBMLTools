
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
 * <p>Original spec-file type: FBATintleResult</p>
 * <pre>
 * FBATintleResult object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "originalGrowth",
    "growth",
    "originalObjective",
    "objective",
    "conflicts"
})
public class FBATintleResult {

    @JsonProperty("originalGrowth")
    private Double originalGrowth;
    @JsonProperty("growth")
    private Double growth;
    @JsonProperty("originalObjective")
    private Double originalObjective;
    @JsonProperty("objective")
    private Double objective;
    @JsonProperty("conflicts")
    private Map<String, String> conflicts;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("originalGrowth")
    public Double getOriginalGrowth() {
        return originalGrowth;
    }

    @JsonProperty("originalGrowth")
    public void setOriginalGrowth(Double originalGrowth) {
        this.originalGrowth = originalGrowth;
    }

    public FBATintleResult withOriginalGrowth(Double originalGrowth) {
        this.originalGrowth = originalGrowth;
        return this;
    }

    @JsonProperty("growth")
    public Double getGrowth() {
        return growth;
    }

    @JsonProperty("growth")
    public void setGrowth(Double growth) {
        this.growth = growth;
    }

    public FBATintleResult withGrowth(Double growth) {
        this.growth = growth;
        return this;
    }

    @JsonProperty("originalObjective")
    public Double getOriginalObjective() {
        return originalObjective;
    }

    @JsonProperty("originalObjective")
    public void setOriginalObjective(Double originalObjective) {
        this.originalObjective = originalObjective;
    }

    public FBATintleResult withOriginalObjective(Double originalObjective) {
        this.originalObjective = originalObjective;
        return this;
    }

    @JsonProperty("objective")
    public Double getObjective() {
        return objective;
    }

    @JsonProperty("objective")
    public void setObjective(Double objective) {
        this.objective = objective;
    }

    public FBATintleResult withObjective(Double objective) {
        this.objective = objective;
        return this;
    }

    @JsonProperty("conflicts")
    public Map<String, String> getConflicts() {
        return conflicts;
    }

    @JsonProperty("conflicts")
    public void setConflicts(Map<String, String> conflicts) {
        this.conflicts = conflicts;
    }

    public FBATintleResult withConflicts(Map<String, String> conflicts) {
        this.conflicts = conflicts;
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
        return ((((((((((((("FBATintleResult"+" [originalGrowth=")+ originalGrowth)+", growth=")+ growth)+", originalObjective=")+ originalObjective)+", objective=")+ objective)+", conflicts=")+ conflicts)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
