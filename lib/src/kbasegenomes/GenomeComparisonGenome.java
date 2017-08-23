
package kbasegenomes;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple2;


/**
 * <p>Original spec-file type: GenomeComparisonGenome</p>
 * <pre>
 * GenomeComparisonGenome object: this object holds information about a genome in a genome comparison
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "genome_ref",
    "genome_similarity",
    "name",
    "taxonomy",
    "features",
    "families",
    "functions"
})
public class GenomeComparisonGenome {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("genome_ref")
    private java.lang.String genomeRef;
    @JsonProperty("genome_similarity")
    private Map<String, Tuple2 <Long, Long>> genomeSimilarity;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("taxonomy")
    private java.lang.String taxonomy;
    @JsonProperty("features")
    private java.lang.Long features;
    @JsonProperty("families")
    private java.lang.Long families;
    @JsonProperty("functions")
    private java.lang.Long functions;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public GenomeComparisonGenome withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("genome_ref")
    public java.lang.String getGenomeRef() {
        return genomeRef;
    }

    @JsonProperty("genome_ref")
    public void setGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
    }

    public GenomeComparisonGenome withGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("genome_similarity")
    public Map<String, Tuple2 <Long, Long>> getGenomeSimilarity() {
        return genomeSimilarity;
    }

    @JsonProperty("genome_similarity")
    public void setGenomeSimilarity(Map<String, Tuple2 <Long, Long>> genomeSimilarity) {
        this.genomeSimilarity = genomeSimilarity;
    }

    public GenomeComparisonGenome withGenomeSimilarity(Map<String, Tuple2 <Long, Long>> genomeSimilarity) {
        this.genomeSimilarity = genomeSimilarity;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public GenomeComparisonGenome withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("taxonomy")
    public java.lang.String getTaxonomy() {
        return taxonomy;
    }

    @JsonProperty("taxonomy")
    public void setTaxonomy(java.lang.String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public GenomeComparisonGenome withTaxonomy(java.lang.String taxonomy) {
        this.taxonomy = taxonomy;
        return this;
    }

    @JsonProperty("features")
    public java.lang.Long getFeatures() {
        return features;
    }

    @JsonProperty("features")
    public void setFeatures(java.lang.Long features) {
        this.features = features;
    }

    public GenomeComparisonGenome withFeatures(java.lang.Long features) {
        this.features = features;
        return this;
    }

    @JsonProperty("families")
    public java.lang.Long getFamilies() {
        return families;
    }

    @JsonProperty("families")
    public void setFamilies(java.lang.Long families) {
        this.families = families;
    }

    public GenomeComparisonGenome withFamilies(java.lang.Long families) {
        this.families = families;
        return this;
    }

    @JsonProperty("functions")
    public java.lang.Long getFunctions() {
        return functions;
    }

    @JsonProperty("functions")
    public void setFunctions(java.lang.Long functions) {
        this.functions = functions;
    }

    public GenomeComparisonGenome withFunctions(java.lang.Long functions) {
        this.functions = functions;
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
        return ((((((((((((((((((("GenomeComparisonGenome"+" [id=")+ id)+", genomeRef=")+ genomeRef)+", genomeSimilarity=")+ genomeSimilarity)+", name=")+ name)+", taxonomy=")+ taxonomy)+", features=")+ features)+", families=")+ families)+", functions=")+ functions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
