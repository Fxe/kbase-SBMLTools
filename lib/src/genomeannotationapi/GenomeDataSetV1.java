
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
 * <p>Original spec-file type: GenomeDataSetV1</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "genomes"
})
public class GenomeDataSetV1 {

    @JsonProperty("genomes")
    private List<GenomeDataV1> genomes;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("genomes")
    public List<GenomeDataV1> getGenomes() {
        return genomes;
    }

    @JsonProperty("genomes")
    public void setGenomes(List<GenomeDataV1> genomes) {
        this.genomes = genomes;
    }

    public GenomeDataSetV1 withGenomes(List<GenomeDataV1> genomes) {
        this.genomes = genomes;
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
        return ((((("GenomeDataSetV1"+" [genomes=")+ genomes)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
