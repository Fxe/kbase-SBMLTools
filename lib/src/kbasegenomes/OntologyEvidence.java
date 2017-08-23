
package kbasegenomes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple3;
import us.kbase.common.service.Tuple4;


/**
 * <p>Original spec-file type: OntologyEvidence</p>
 * <pre>
 * @optional translation_provenance alignment_evidence
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "method",
    "method_version",
    "timestamp",
    "translation_provenance",
    "alignment_evidence"
})
public class OntologyEvidence {

    @JsonProperty("method")
    private java.lang.String method;
    @JsonProperty("method_version")
    private java.lang.String methodVersion;
    @JsonProperty("timestamp")
    private java.lang.String timestamp;
    @JsonProperty("translation_provenance")
    private Tuple3 <String, String, String> translationProvenance;
    @JsonProperty("alignment_evidence")
    private List<Tuple4 <Long, Long, Long, Double>> alignmentEvidence;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("method")
    public java.lang.String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(java.lang.String method) {
        this.method = method;
    }

    public OntologyEvidence withMethod(java.lang.String method) {
        this.method = method;
        return this;
    }

    @JsonProperty("method_version")
    public java.lang.String getMethodVersion() {
        return methodVersion;
    }

    @JsonProperty("method_version")
    public void setMethodVersion(java.lang.String methodVersion) {
        this.methodVersion = methodVersion;
    }

    public OntologyEvidence withMethodVersion(java.lang.String methodVersion) {
        this.methodVersion = methodVersion;
        return this;
    }

    @JsonProperty("timestamp")
    public java.lang.String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(java.lang.String timestamp) {
        this.timestamp = timestamp;
    }

    public OntologyEvidence withTimestamp(java.lang.String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @JsonProperty("translation_provenance")
    public Tuple3 <String, String, String> getTranslationProvenance() {
        return translationProvenance;
    }

    @JsonProperty("translation_provenance")
    public void setTranslationProvenance(Tuple3 <String, String, String> translationProvenance) {
        this.translationProvenance = translationProvenance;
    }

    public OntologyEvidence withTranslationProvenance(Tuple3 <String, String, String> translationProvenance) {
        this.translationProvenance = translationProvenance;
        return this;
    }

    @JsonProperty("alignment_evidence")
    public List<Tuple4 <Long, Long, Long, Double>> getAlignmentEvidence() {
        return alignmentEvidence;
    }

    @JsonProperty("alignment_evidence")
    public void setAlignmentEvidence(List<Tuple4 <Long, Long, Long, Double>> alignmentEvidence) {
        this.alignmentEvidence = alignmentEvidence;
    }

    public OntologyEvidence withAlignmentEvidence(List<Tuple4 <Long, Long, Long, Double>> alignmentEvidence) {
        this.alignmentEvidence = alignmentEvidence;
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
        return ((((((((((((("OntologyEvidence"+" [method=")+ method)+", methodVersion=")+ methodVersion)+", timestamp=")+ timestamp)+", translationProvenance=")+ translationProvenance)+", alignmentEvidence=")+ alignmentEvidence)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
