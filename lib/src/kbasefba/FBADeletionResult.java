
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
 * <p>Original spec-file type: FBADeletionResult</p>
 * <pre>
 * FBADeletionResult object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "feature_refs",
    "growthFraction"
})
public class FBADeletionResult {

    @JsonProperty("feature_refs")
    private List<String> featureRefs;
    @JsonProperty("growthFraction")
    private Double growthFraction;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("feature_refs")
    public List<String> getFeatureRefs() {
        return featureRefs;
    }

    @JsonProperty("feature_refs")
    public void setFeatureRefs(List<String> featureRefs) {
        this.featureRefs = featureRefs;
    }

    public FBADeletionResult withFeatureRefs(List<String> featureRefs) {
        this.featureRefs = featureRefs;
        return this;
    }

    @JsonProperty("growthFraction")
    public Double getGrowthFraction() {
        return growthFraction;
    }

    @JsonProperty("growthFraction")
    public void setGrowthFraction(Double growthFraction) {
        this.growthFraction = growthFraction;
    }

    public FBADeletionResult withGrowthFraction(Double growthFraction) {
        this.growthFraction = growthFraction;
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
        return ((((((("FBADeletionResult"+" [featureRefs=")+ featureRefs)+", growthFraction=")+ growthFraction)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
