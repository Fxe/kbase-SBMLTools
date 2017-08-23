
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


/**
 * <p>Original spec-file type: MetagenomeAnnotationOTUFunction</p>
 * <pre>
 * Structure for the "MetagenomeAnnotationOTUFunction" object
 * list<string> reference_genes - list of genes associated with hit
 * string functional_role - annotated function
 * string kbid - kbase ID of OTU function in metagenome
 * int abundance - number of hits with associated role and OTU
 * float confidence - confidence of functional role hit
 * string confidence_type - type of functional role hit
 *             @searchable ws_subset id abundance confidence functional_role
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "reference_genes",
    "functional_role",
    "abundance",
    "confidence"
})
public class MetagenomeAnnotationOTUFunction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("reference_genes")
    private List<String> referenceGenes;
    @JsonProperty("functional_role")
    private java.lang.String functionalRole;
    @JsonProperty("abundance")
    private Long abundance;
    @JsonProperty("confidence")
    private Double confidence;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public MetagenomeAnnotationOTUFunction withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("reference_genes")
    public List<String> getReferenceGenes() {
        return referenceGenes;
    }

    @JsonProperty("reference_genes")
    public void setReferenceGenes(List<String> referenceGenes) {
        this.referenceGenes = referenceGenes;
    }

    public MetagenomeAnnotationOTUFunction withReferenceGenes(List<String> referenceGenes) {
        this.referenceGenes = referenceGenes;
        return this;
    }

    @JsonProperty("functional_role")
    public java.lang.String getFunctionalRole() {
        return functionalRole;
    }

    @JsonProperty("functional_role")
    public void setFunctionalRole(java.lang.String functionalRole) {
        this.functionalRole = functionalRole;
    }

    public MetagenomeAnnotationOTUFunction withFunctionalRole(java.lang.String functionalRole) {
        this.functionalRole = functionalRole;
        return this;
    }

    @JsonProperty("abundance")
    public Long getAbundance() {
        return abundance;
    }

    @JsonProperty("abundance")
    public void setAbundance(Long abundance) {
        this.abundance = abundance;
    }

    public MetagenomeAnnotationOTUFunction withAbundance(Long abundance) {
        this.abundance = abundance;
        return this;
    }

    @JsonProperty("confidence")
    public Double getConfidence() {
        return confidence;
    }

    @JsonProperty("confidence")
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public MetagenomeAnnotationOTUFunction withConfidence(Double confidence) {
        this.confidence = confidence;
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
        return ((((((((((((("MetagenomeAnnotationOTUFunction"+" [id=")+ id)+", referenceGenes=")+ referenceGenes)+", functionalRole=")+ functionalRole)+", abundance=")+ abundance)+", confidence=")+ confidence)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
