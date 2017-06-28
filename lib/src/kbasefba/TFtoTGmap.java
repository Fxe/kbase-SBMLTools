
package kbasefba;

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
 * <p>Original spec-file type: TFtoTGmap</p>
 * <pre>
 * Object required by the prom_constraints object, this maps a transcription factor 
 *  to a group of regulatory target genes.
 *     string transcriptionFactor_ref                       - reference to the transcription factor
 *     list<TargetGeneProbabilities> targetGeneProbs        - collection of target genes for the TF
 *                                                             along with associated joint probabilities for each
 *                                                             target to be on given that the TF is on or off.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "transcriptionFactor_ref",
    "targetGeneProbs"
})
public class TFtoTGmap {

    @JsonProperty("transcriptionFactor_ref")
    private String transcriptionFactorRef;
    @JsonProperty("targetGeneProbs")
    private List<TargetGeneProbabilities> targetGeneProbs;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("transcriptionFactor_ref")
    public String getTranscriptionFactorRef() {
        return transcriptionFactorRef;
    }

    @JsonProperty("transcriptionFactor_ref")
    public void setTranscriptionFactorRef(String transcriptionFactorRef) {
        this.transcriptionFactorRef = transcriptionFactorRef;
    }

    public TFtoTGmap withTranscriptionFactorRef(String transcriptionFactorRef) {
        this.transcriptionFactorRef = transcriptionFactorRef;
        return this;
    }

    @JsonProperty("targetGeneProbs")
    public List<TargetGeneProbabilities> getTargetGeneProbs() {
        return targetGeneProbs;
    }

    @JsonProperty("targetGeneProbs")
    public void setTargetGeneProbs(List<TargetGeneProbabilities> targetGeneProbs) {
        this.targetGeneProbs = targetGeneProbs;
    }

    public TFtoTGmap withTargetGeneProbs(List<TargetGeneProbabilities> targetGeneProbs) {
        this.targetGeneProbs = targetGeneProbs;
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
        return ((((((("TFtoTGmap"+" [transcriptionFactorRef=")+ transcriptionFactorRef)+", targetGeneProbs=")+ targetGeneProbs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
