
package genomeannotationapi;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: Exon_data</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "exon_location",
    "exon_dna_sequence",
    "exon_ordinal"
})
public class ExonData {

    /**
     * <p>Original spec-file type: Region</p>
     * 
     * 
     */
    @JsonProperty("exon_location")
    private Region exonLocation;
    @JsonProperty("exon_dna_sequence")
    private String exonDnaSequence;
    @JsonProperty("exon_ordinal")
    private Long exonOrdinal;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * <p>Original spec-file type: Region</p>
     * 
     * 
     */
    @JsonProperty("exon_location")
    public Region getExonLocation() {
        return exonLocation;
    }

    /**
     * <p>Original spec-file type: Region</p>
     * 
     * 
     */
    @JsonProperty("exon_location")
    public void setExonLocation(Region exonLocation) {
        this.exonLocation = exonLocation;
    }

    public ExonData withExonLocation(Region exonLocation) {
        this.exonLocation = exonLocation;
        return this;
    }

    @JsonProperty("exon_dna_sequence")
    public String getExonDnaSequence() {
        return exonDnaSequence;
    }

    @JsonProperty("exon_dna_sequence")
    public void setExonDnaSequence(String exonDnaSequence) {
        this.exonDnaSequence = exonDnaSequence;
    }

    public ExonData withExonDnaSequence(String exonDnaSequence) {
        this.exonDnaSequence = exonDnaSequence;
        return this;
    }

    @JsonProperty("exon_ordinal")
    public Long getExonOrdinal() {
        return exonOrdinal;
    }

    @JsonProperty("exon_ordinal")
    public void setExonOrdinal(Long exonOrdinal) {
        this.exonOrdinal = exonOrdinal;
    }

    public ExonData withExonOrdinal(Long exonOrdinal) {
        this.exonOrdinal = exonOrdinal;
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
        return ((((((((("ExonData"+" [exonLocation=")+ exonLocation)+", exonDnaSequence=")+ exonDnaSequence)+", exonOrdinal=")+ exonOrdinal)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
