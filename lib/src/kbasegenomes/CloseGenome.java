
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
 * <p>Original spec-file type: Close_genome</p>
 * <pre>
 * @optional genome closeness_measure
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "genome",
    "closeness_measure"
})
public class CloseGenome {

    @JsonProperty("genome")
    private String genome;
    @JsonProperty("closeness_measure")
    private Double closenessMeasure;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("genome")
    public String getGenome() {
        return genome;
    }

    @JsonProperty("genome")
    public void setGenome(String genome) {
        this.genome = genome;
    }

    public CloseGenome withGenome(String genome) {
        this.genome = genome;
        return this;
    }

    @JsonProperty("closeness_measure")
    public Double getClosenessMeasure() {
        return closenessMeasure;
    }

    @JsonProperty("closeness_measure")
    public void setClosenessMeasure(Double closenessMeasure) {
        this.closenessMeasure = closenessMeasure;
    }

    public CloseGenome withClosenessMeasure(Double closenessMeasure) {
        this.closenessMeasure = closenessMeasure;
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
        return ((((((("CloseGenome"+" [genome=")+ genome)+", closenessMeasure=")+ closenessMeasure)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
