
package rastsdk;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: GenomeParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "input_contigset",
    "input_genome",
    "output_genome",
    "genetic_code",
    "domain",
    "scientific_name"
})
public class GenomeParams {

    @JsonProperty("input_contigset")
    private String inputContigset;
    @JsonProperty("input_genome")
    private String inputGenome;
    @JsonProperty("output_genome")
    private String outputGenome;
    @JsonProperty("genetic_code")
    private Long geneticCode;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("scientific_name")
    private String scientificName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("input_contigset")
    public String getInputContigset() {
        return inputContigset;
    }

    @JsonProperty("input_contigset")
    public void setInputContigset(String inputContigset) {
        this.inputContigset = inputContigset;
    }

    public GenomeParams withInputContigset(String inputContigset) {
        this.inputContigset = inputContigset;
        return this;
    }

    @JsonProperty("input_genome")
    public String getInputGenome() {
        return inputGenome;
    }

    @JsonProperty("input_genome")
    public void setInputGenome(String inputGenome) {
        this.inputGenome = inputGenome;
    }

    public GenomeParams withInputGenome(String inputGenome) {
        this.inputGenome = inputGenome;
        return this;
    }

    @JsonProperty("output_genome")
    public String getOutputGenome() {
        return outputGenome;
    }

    @JsonProperty("output_genome")
    public void setOutputGenome(String outputGenome) {
        this.outputGenome = outputGenome;
    }

    public GenomeParams withOutputGenome(String outputGenome) {
        this.outputGenome = outputGenome;
        return this;
    }

    @JsonProperty("genetic_code")
    public Long getGeneticCode() {
        return geneticCode;
    }

    @JsonProperty("genetic_code")
    public void setGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
    }

    public GenomeParams withGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
        return this;
    }

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public GenomeParams withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    @JsonProperty("scientific_name")
    public String getScientificName() {
        return scientificName;
    }

    @JsonProperty("scientific_name")
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public GenomeParams withScientificName(String scientificName) {
        this.scientificName = scientificName;
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
        return ((((((((((((((("GenomeParams"+" [inputContigset=")+ inputContigset)+", inputGenome=")+ inputGenome)+", outputGenome=")+ outputGenome)+", geneticCode=")+ geneticCode)+", domain=")+ domain)+", scientificName=")+ scientificName)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
