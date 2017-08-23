
package kbasegenomes;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: Genome_quality_measure</p>
 * <pre>
 * @optional frameshift_error_rate sequence_error_rate
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "frameshift_error_rate",
    "sequence_error_rate"
})
public class GenomeQualityMeasure {

    @JsonProperty("frameshift_error_rate")
    private Double frameshiftErrorRate;
    @JsonProperty("sequence_error_rate")
    private Double sequenceErrorRate;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("frameshift_error_rate")
    public Double getFrameshiftErrorRate() {
        return frameshiftErrorRate;
    }

    @JsonProperty("frameshift_error_rate")
    public void setFrameshiftErrorRate(Double frameshiftErrorRate) {
        this.frameshiftErrorRate = frameshiftErrorRate;
    }

    public GenomeQualityMeasure withFrameshiftErrorRate(Double frameshiftErrorRate) {
        this.frameshiftErrorRate = frameshiftErrorRate;
        return this;
    }

    @JsonProperty("sequence_error_rate")
    public Double getSequenceErrorRate() {
        return sequenceErrorRate;
    }

    @JsonProperty("sequence_error_rate")
    public void setSequenceErrorRate(Double sequenceErrorRate) {
        this.sequenceErrorRate = sequenceErrorRate;
    }

    public GenomeQualityMeasure withSequenceErrorRate(Double sequenceErrorRate) {
        this.sequenceErrorRate = sequenceErrorRate;
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
        return ((((((("GenomeQualityMeasure"+" [frameshiftErrorRate=")+ frameshiftErrorRate)+", sequenceErrorRate=")+ sequenceErrorRate)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
