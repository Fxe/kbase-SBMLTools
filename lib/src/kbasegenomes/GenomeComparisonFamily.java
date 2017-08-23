
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


/**
 * <p>Original spec-file type: GenomeComparisonFamily</p>
 * <pre>
 * GenomeComparisonFamily object: this object holds information about a protein family across a set of genomes
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "core",
    "genome_features",
    "id",
    "type",
    "protein_translation",
    "number_genomes",
    "fraction_genomes",
    "fraction_consistent_annotations",
    "most_consistent_role"
})
public class GenomeComparisonFamily {

    @JsonProperty("core")
    private java.lang.Long core;
    @JsonProperty("genome_features")
    private Map<String, List<Tuple3 <String, List<Long> , Double>>> genomeFeatures;
    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("protein_translation")
    private java.lang.String proteinTranslation;
    @JsonProperty("number_genomes")
    private java.lang.Long numberGenomes;
    @JsonProperty("fraction_genomes")
    private java.lang.Double fractionGenomes;
    @JsonProperty("fraction_consistent_annotations")
    private java.lang.Double fractionConsistentAnnotations;
    @JsonProperty("most_consistent_role")
    private java.lang.String mostConsistentRole;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("core")
    public java.lang.Long getCore() {
        return core;
    }

    @JsonProperty("core")
    public void setCore(java.lang.Long core) {
        this.core = core;
    }

    public GenomeComparisonFamily withCore(java.lang.Long core) {
        this.core = core;
        return this;
    }

    @JsonProperty("genome_features")
    public Map<String, List<Tuple3 <String, List<Long> , Double>>> getGenomeFeatures() {
        return genomeFeatures;
    }

    @JsonProperty("genome_features")
    public void setGenomeFeatures(Map<String, List<Tuple3 <String, List<Long> , Double>>> genomeFeatures) {
        this.genomeFeatures = genomeFeatures;
    }

    public GenomeComparisonFamily withGenomeFeatures(Map<String, List<Tuple3 <String, List<Long> , Double>>> genomeFeatures) {
        this.genomeFeatures = genomeFeatures;
        return this;
    }

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public GenomeComparisonFamily withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("type")
    public java.lang.String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(java.lang.String type) {
        this.type = type;
    }

    public GenomeComparisonFamily withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("protein_translation")
    public java.lang.String getProteinTranslation() {
        return proteinTranslation;
    }

    @JsonProperty("protein_translation")
    public void setProteinTranslation(java.lang.String proteinTranslation) {
        this.proteinTranslation = proteinTranslation;
    }

    public GenomeComparisonFamily withProteinTranslation(java.lang.String proteinTranslation) {
        this.proteinTranslation = proteinTranslation;
        return this;
    }

    @JsonProperty("number_genomes")
    public java.lang.Long getNumberGenomes() {
        return numberGenomes;
    }

    @JsonProperty("number_genomes")
    public void setNumberGenomes(java.lang.Long numberGenomes) {
        this.numberGenomes = numberGenomes;
    }

    public GenomeComparisonFamily withNumberGenomes(java.lang.Long numberGenomes) {
        this.numberGenomes = numberGenomes;
        return this;
    }

    @JsonProperty("fraction_genomes")
    public java.lang.Double getFractionGenomes() {
        return fractionGenomes;
    }

    @JsonProperty("fraction_genomes")
    public void setFractionGenomes(java.lang.Double fractionGenomes) {
        this.fractionGenomes = fractionGenomes;
    }

    public GenomeComparisonFamily withFractionGenomes(java.lang.Double fractionGenomes) {
        this.fractionGenomes = fractionGenomes;
        return this;
    }

    @JsonProperty("fraction_consistent_annotations")
    public java.lang.Double getFractionConsistentAnnotations() {
        return fractionConsistentAnnotations;
    }

    @JsonProperty("fraction_consistent_annotations")
    public void setFractionConsistentAnnotations(java.lang.Double fractionConsistentAnnotations) {
        this.fractionConsistentAnnotations = fractionConsistentAnnotations;
    }

    public GenomeComparisonFamily withFractionConsistentAnnotations(java.lang.Double fractionConsistentAnnotations) {
        this.fractionConsistentAnnotations = fractionConsistentAnnotations;
        return this;
    }

    @JsonProperty("most_consistent_role")
    public java.lang.String getMostConsistentRole() {
        return mostConsistentRole;
    }

    @JsonProperty("most_consistent_role")
    public void setMostConsistentRole(java.lang.String mostConsistentRole) {
        this.mostConsistentRole = mostConsistentRole;
    }

    public GenomeComparisonFamily withMostConsistentRole(java.lang.String mostConsistentRole) {
        this.mostConsistentRole = mostConsistentRole;
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
        return ((((((((((((((((((((("GenomeComparisonFamily"+" [core=")+ core)+", genomeFeatures=")+ genomeFeatures)+", id=")+ id)+", type=")+ type)+", proteinTranslation=")+ proteinTranslation)+", numberGenomes=")+ numberGenomes)+", fractionGenomes=")+ fractionGenomes)+", fractionConsistentAnnotations=")+ fractionConsistentAnnotations)+", mostConsistentRole=")+ mostConsistentRole)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
