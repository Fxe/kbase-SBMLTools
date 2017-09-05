
package genomeproteomecomparison;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: blast_proteomes_params</p>
 * <pre>
 * string genome1ws - workspace of genome1
 * string genome1id - id of genome1
 * string genome2ws - workspace of genome2
 * string genome2id - id of genome2
 * float sub_bbh_percent - optional parameter, minimum percent of bit score compared to best bit score, default is 90
 * string max_evalue -  optional parameter, maximum evalue, default is 1e-10
 * string output_ws - workspace of output object
 * string output_id - future id of output object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "genome1ws",
    "genome1id",
    "genome2ws",
    "genome2id",
    "sub_bbh_percent",
    "max_evalue",
    "output_ws",
    "output_id"
})
public class BlastProteomesParams {

    @JsonProperty("genome1ws")
    private String genome1ws;
    @JsonProperty("genome1id")
    private String genome1id;
    @JsonProperty("genome2ws")
    private String genome2ws;
    @JsonProperty("genome2id")
    private String genome2id;
    @JsonProperty("sub_bbh_percent")
    private Double subBbhPercent;
    @JsonProperty("max_evalue")
    private String maxEvalue;
    @JsonProperty("output_ws")
    private String outputWs;
    @JsonProperty("output_id")
    private String outputId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("genome1ws")
    public String getGenome1ws() {
        return genome1ws;
    }

    @JsonProperty("genome1ws")
    public void setGenome1ws(String genome1ws) {
        this.genome1ws = genome1ws;
    }

    public BlastProteomesParams withGenome1ws(String genome1ws) {
        this.genome1ws = genome1ws;
        return this;
    }

    @JsonProperty("genome1id")
    public String getGenome1id() {
        return genome1id;
    }

    @JsonProperty("genome1id")
    public void setGenome1id(String genome1id) {
        this.genome1id = genome1id;
    }

    public BlastProteomesParams withGenome1id(String genome1id) {
        this.genome1id = genome1id;
        return this;
    }

    @JsonProperty("genome2ws")
    public String getGenome2ws() {
        return genome2ws;
    }

    @JsonProperty("genome2ws")
    public void setGenome2ws(String genome2ws) {
        this.genome2ws = genome2ws;
    }

    public BlastProteomesParams withGenome2ws(String genome2ws) {
        this.genome2ws = genome2ws;
        return this;
    }

    @JsonProperty("genome2id")
    public String getGenome2id() {
        return genome2id;
    }

    @JsonProperty("genome2id")
    public void setGenome2id(String genome2id) {
        this.genome2id = genome2id;
    }

    public BlastProteomesParams withGenome2id(String genome2id) {
        this.genome2id = genome2id;
        return this;
    }

    @JsonProperty("sub_bbh_percent")
    public Double getSubBbhPercent() {
        return subBbhPercent;
    }

    @JsonProperty("sub_bbh_percent")
    public void setSubBbhPercent(Double subBbhPercent) {
        this.subBbhPercent = subBbhPercent;
    }

    public BlastProteomesParams withSubBbhPercent(Double subBbhPercent) {
        this.subBbhPercent = subBbhPercent;
        return this;
    }

    @JsonProperty("max_evalue")
    public String getMaxEvalue() {
        return maxEvalue;
    }

    @JsonProperty("max_evalue")
    public void setMaxEvalue(String maxEvalue) {
        this.maxEvalue = maxEvalue;
    }

    public BlastProteomesParams withMaxEvalue(String maxEvalue) {
        this.maxEvalue = maxEvalue;
        return this;
    }

    @JsonProperty("output_ws")
    public String getOutputWs() {
        return outputWs;
    }

    @JsonProperty("output_ws")
    public void setOutputWs(String outputWs) {
        this.outputWs = outputWs;
    }

    public BlastProteomesParams withOutputWs(String outputWs) {
        this.outputWs = outputWs;
        return this;
    }

    @JsonProperty("output_id")
    public String getOutputId() {
        return outputId;
    }

    @JsonProperty("output_id")
    public void setOutputId(String outputId) {
        this.outputId = outputId;
    }

    public BlastProteomesParams withOutputId(String outputId) {
        this.outputId = outputId;
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
        return ((((((((((((((((((("BlastProteomesParams"+" [genome1ws=")+ genome1ws)+", genome1id=")+ genome1id)+", genome2ws=")+ genome2ws)+", genome2id=")+ genome2id)+", subBbhPercent=")+ subBbhPercent)+", maxEvalue=")+ maxEvalue)+", outputWs=")+ outputWs)+", outputId=")+ outputId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
