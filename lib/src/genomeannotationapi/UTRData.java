
package genomeannotationapi;

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
 * <p>Original spec-file type: UTR_data</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "utr_locations",
    "utr_dna_sequence"
})
public class UTRData {

    @JsonProperty("utr_locations")
    private List<Region> utrLocations;
    @JsonProperty("utr_dna_sequence")
    private String utrDnaSequence;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("utr_locations")
    public List<Region> getUtrLocations() {
        return utrLocations;
    }

    @JsonProperty("utr_locations")
    public void setUtrLocations(List<Region> utrLocations) {
        this.utrLocations = utrLocations;
    }

    public UTRData withUtrLocations(List<Region> utrLocations) {
        this.utrLocations = utrLocations;
        return this;
    }

    @JsonProperty("utr_dna_sequence")
    public String getUtrDnaSequence() {
        return utrDnaSequence;
    }

    @JsonProperty("utr_dna_sequence")
    public void setUtrDnaSequence(String utrDnaSequence) {
        this.utrDnaSequence = utrDnaSequence;
    }

    public UTRData withUtrDnaSequence(String utrDnaSequence) {
        this.utrDnaSequence = utrDnaSequence;
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
        return ((((((("UTRData"+" [utrLocations=")+ utrLocations)+", utrDnaSequence=")+ utrDnaSequence)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
