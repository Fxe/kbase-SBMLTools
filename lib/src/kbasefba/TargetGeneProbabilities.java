
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
 * <p>Original spec-file type: TargetGeneProbabilities</p>
 * <pre>
 * Object required by the prom_constraints object which defines the computed probabilities for a target gene.  The
 * TF regulating this target can be deduced based on the TFtoTGmap object.
 *     string target_gene_ref           - reference to the target gene
 *     float probTGonGivenTFoff    - PROB(target=ON|TF=OFF)
 *                                 the probability that the target gene is ON, given that the
 *                                 transcription factor is not expressed.  Set to null or empty if
 *                                 this probability has not been calculated yet.
 *     float probTGonGivenTFon   - PROB(target=ON|TF=ON)
 *                                 the probability that the transcriptional target is ON, given that the
 *                                 transcription factor is expressed.    Set to null or empty if
 *                                 this probability has not been calculated yet.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "target_gene_ref",
    "probTGonGivenTFoff",
    "probTGonGivenTFon"
})
public class TargetGeneProbabilities {

    @JsonProperty("target_gene_ref")
    private String targetGeneRef;
    @JsonProperty("probTGonGivenTFoff")
    private Double probTGonGivenTFoff;
    @JsonProperty("probTGonGivenTFon")
    private Double probTGonGivenTFon;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("target_gene_ref")
    public String getTargetGeneRef() {
        return targetGeneRef;
    }

    @JsonProperty("target_gene_ref")
    public void setTargetGeneRef(String targetGeneRef) {
        this.targetGeneRef = targetGeneRef;
    }

    public TargetGeneProbabilities withTargetGeneRef(String targetGeneRef) {
        this.targetGeneRef = targetGeneRef;
        return this;
    }

    @JsonProperty("probTGonGivenTFoff")
    public Double getProbTGonGivenTFoff() {
        return probTGonGivenTFoff;
    }

    @JsonProperty("probTGonGivenTFoff")
    public void setProbTGonGivenTFoff(Double probTGonGivenTFoff) {
        this.probTGonGivenTFoff = probTGonGivenTFoff;
    }

    public TargetGeneProbabilities withProbTGonGivenTFoff(Double probTGonGivenTFoff) {
        this.probTGonGivenTFoff = probTGonGivenTFoff;
        return this;
    }

    @JsonProperty("probTGonGivenTFon")
    public Double getProbTGonGivenTFon() {
        return probTGonGivenTFon;
    }

    @JsonProperty("probTGonGivenTFon")
    public void setProbTGonGivenTFon(Double probTGonGivenTFon) {
        this.probTGonGivenTFon = probTGonGivenTFon;
    }

    public TargetGeneProbabilities withProbTGonGivenTFon(Double probTGonGivenTFon) {
        this.probTGonGivenTFon = probTGonGivenTFon;
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
        return ((((((((("TargetGeneProbabilities"+" [targetGeneRef=")+ targetGeneRef)+", probTGonGivenTFoff=")+ probTGonGivenTFoff)+", probTGonGivenTFon=")+ probTGonGivenTFon)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
