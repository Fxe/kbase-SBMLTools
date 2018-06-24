
package genomefileutil;

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
 * <p>Original spec-file type: GenomeToGenbankParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "genome_ref",
    "ref_path_to_genome"
})
public class GenomeToGenbankParams {

    @JsonProperty("genome_ref")
    private java.lang.String genomeRef;
    @JsonProperty("ref_path_to_genome")
    private List<String> refPathToGenome;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("genome_ref")
    public java.lang.String getGenomeRef() {
        return genomeRef;
    }

    @JsonProperty("genome_ref")
    public void setGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
    }

    public GenomeToGenbankParams withGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("ref_path_to_genome")
    public List<String> getRefPathToGenome() {
        return refPathToGenome;
    }

    @JsonProperty("ref_path_to_genome")
    public void setRefPathToGenome(List<String> refPathToGenome) {
        this.refPathToGenome = refPathToGenome;
    }

    public GenomeToGenbankParams withRefPathToGenome(List<String> refPathToGenome) {
        this.refPathToGenome = refPathToGenome;
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
        return ((((((("GenomeToGenbankParams"+" [genomeRef=")+ genomeRef)+", refPathToGenome=")+ refPathToGenome)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
