
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
 * <p>Original spec-file type: FBAPromResult</p>
 * <pre>
 * FBAPromResult object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "objectFraction",
    "alpha",
    "beta"
})
public class FBAPromResult {

    @JsonProperty("objectFraction")
    private Double objectFraction;
    @JsonProperty("alpha")
    private Double alpha;
    @JsonProperty("beta")
    private Double beta;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("objectFraction")
    public Double getObjectFraction() {
        return objectFraction;
    }

    @JsonProperty("objectFraction")
    public void setObjectFraction(Double objectFraction) {
        this.objectFraction = objectFraction;
    }

    public FBAPromResult withObjectFraction(Double objectFraction) {
        this.objectFraction = objectFraction;
        return this;
    }

    @JsonProperty("alpha")
    public Double getAlpha() {
        return alpha;
    }

    @JsonProperty("alpha")
    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public FBAPromResult withAlpha(Double alpha) {
        this.alpha = alpha;
        return this;
    }

    @JsonProperty("beta")
    public Double getBeta() {
        return beta;
    }

    @JsonProperty("beta")
    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public FBAPromResult withBeta(Double beta) {
        this.beta = beta;
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
        return ((((((((("FBAPromResult"+" [objectFraction=")+ objectFraction)+", alpha=")+ alpha)+", beta=")+ beta)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
