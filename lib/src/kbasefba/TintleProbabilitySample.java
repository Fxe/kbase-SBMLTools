
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
 * <p>Original spec-file type: TintleProbabilitySample</p>
 * <pre>
 * collection of tintle probability scores for each feature in a genome,
 * representing a single gene probability sample
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "tintle_probability",
    "expression_sample_ref"
})
public class TintleProbabilitySample {

    @JsonProperty("tintle_probability")
    private Map<String, Double> tintleProbability;
    @JsonProperty("expression_sample_ref")
    private java.lang.String expressionSampleRef;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("tintle_probability")
    public Map<String, Double> getTintleProbability() {
        return tintleProbability;
    }

    @JsonProperty("tintle_probability")
    public void setTintleProbability(Map<String, Double> tintleProbability) {
        this.tintleProbability = tintleProbability;
    }

    public TintleProbabilitySample withTintleProbability(Map<String, Double> tintleProbability) {
        this.tintleProbability = tintleProbability;
        return this;
    }

    @JsonProperty("expression_sample_ref")
    public java.lang.String getExpressionSampleRef() {
        return expressionSampleRef;
    }

    @JsonProperty("expression_sample_ref")
    public void setExpressionSampleRef(java.lang.String expressionSampleRef) {
        this.expressionSampleRef = expressionSampleRef;
    }

    public TintleProbabilitySample withExpressionSampleRef(java.lang.String expressionSampleRef) {
        this.expressionSampleRef = expressionSampleRef;
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
        return ((((((("TintleProbabilitySample"+" [tintleProbability=")+ tintleProbability)+", expressionSampleRef=")+ expressionSampleRef)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
