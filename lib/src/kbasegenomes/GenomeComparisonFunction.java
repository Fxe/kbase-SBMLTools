
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
import us.kbase.common.service.Tuple2;
import us.kbase.common.service.Tuple3;


/**
 * <p>Original spec-file type: GenomeComparisonFunction</p>
 * <pre>
 * GenomeComparisonFunction object: this object holds information about a genome in a function across all genomes
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "core",
    "genome_features",
    "id",
    "reactions",
    "subsystem",
    "primclass",
    "subclass",
    "number_genomes",
    "fraction_genomes",
    "fraction_consistent_families",
    "most_consistent_family"
})
public class GenomeComparisonFunction {

    @JsonProperty("core")
    private java.lang.Long core;
    @JsonProperty("genome_features")
    private Map<String, List<Tuple3 <String, Long, Double>>> genomeFeatures;
    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("reactions")
    private List<Tuple2 <String, String>> reactions;
    @JsonProperty("subsystem")
    private java.lang.String subsystem;
    @JsonProperty("primclass")
    private java.lang.String primclass;
    @JsonProperty("subclass")
    private java.lang.String subclass;
    @JsonProperty("number_genomes")
    private java.lang.Long numberGenomes;
    @JsonProperty("fraction_genomes")
    private java.lang.Double fractionGenomes;
    @JsonProperty("fraction_consistent_families")
    private java.lang.Double fractionConsistentFamilies;
    @JsonProperty("most_consistent_family")
    private java.lang.String mostConsistentFamily;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("core")
    public java.lang.Long getCore() {
        return core;
    }

    @JsonProperty("core")
    public void setCore(java.lang.Long core) {
        this.core = core;
    }

    public GenomeComparisonFunction withCore(java.lang.Long core) {
        this.core = core;
        return this;
    }

    @JsonProperty("genome_features")
    public Map<String, List<Tuple3 <String, Long, Double>>> getGenomeFeatures() {
        return genomeFeatures;
    }

    @JsonProperty("genome_features")
    public void setGenomeFeatures(Map<String, List<Tuple3 <String, Long, Double>>> genomeFeatures) {
        this.genomeFeatures = genomeFeatures;
    }

    public GenomeComparisonFunction withGenomeFeatures(Map<String, List<Tuple3 <String, Long, Double>>> genomeFeatures) {
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

    public GenomeComparisonFunction withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("reactions")
    public List<Tuple2 <String, String>> getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(List<Tuple2 <String, String>> reactions) {
        this.reactions = reactions;
    }

    public GenomeComparisonFunction withReactions(List<Tuple2 <String, String>> reactions) {
        this.reactions = reactions;
        return this;
    }

    @JsonProperty("subsystem")
    public java.lang.String getSubsystem() {
        return subsystem;
    }

    @JsonProperty("subsystem")
    public void setSubsystem(java.lang.String subsystem) {
        this.subsystem = subsystem;
    }

    public GenomeComparisonFunction withSubsystem(java.lang.String subsystem) {
        this.subsystem = subsystem;
        return this;
    }

    @JsonProperty("primclass")
    public java.lang.String getPrimclass() {
        return primclass;
    }

    @JsonProperty("primclass")
    public void setPrimclass(java.lang.String primclass) {
        this.primclass = primclass;
    }

    public GenomeComparisonFunction withPrimclass(java.lang.String primclass) {
        this.primclass = primclass;
        return this;
    }

    @JsonProperty("subclass")
    public java.lang.String getSubclass() {
        return subclass;
    }

    @JsonProperty("subclass")
    public void setSubclass(java.lang.String subclass) {
        this.subclass = subclass;
    }

    public GenomeComparisonFunction withSubclass(java.lang.String subclass) {
        this.subclass = subclass;
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

    public GenomeComparisonFunction withNumberGenomes(java.lang.Long numberGenomes) {
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

    public GenomeComparisonFunction withFractionGenomes(java.lang.Double fractionGenomes) {
        this.fractionGenomes = fractionGenomes;
        return this;
    }

    @JsonProperty("fraction_consistent_families")
    public java.lang.Double getFractionConsistentFamilies() {
        return fractionConsistentFamilies;
    }

    @JsonProperty("fraction_consistent_families")
    public void setFractionConsistentFamilies(java.lang.Double fractionConsistentFamilies) {
        this.fractionConsistentFamilies = fractionConsistentFamilies;
    }

    public GenomeComparisonFunction withFractionConsistentFamilies(java.lang.Double fractionConsistentFamilies) {
        this.fractionConsistentFamilies = fractionConsistentFamilies;
        return this;
    }

    @JsonProperty("most_consistent_family")
    public java.lang.String getMostConsistentFamily() {
        return mostConsistentFamily;
    }

    @JsonProperty("most_consistent_family")
    public void setMostConsistentFamily(java.lang.String mostConsistentFamily) {
        this.mostConsistentFamily = mostConsistentFamily;
    }

    public GenomeComparisonFunction withMostConsistentFamily(java.lang.String mostConsistentFamily) {
        this.mostConsistentFamily = mostConsistentFamily;
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
        return ((((((((((((((((((((((((("GenomeComparisonFunction"+" [core=")+ core)+", genomeFeatures=")+ genomeFeatures)+", id=")+ id)+", reactions=")+ reactions)+", subsystem=")+ subsystem)+", primclass=")+ primclass)+", subclass=")+ subclass)+", numberGenomes=")+ numberGenomes)+", fractionGenomes=")+ fractionGenomes)+", fractionConsistentFamilies=")+ fractionConsistentFamilies)+", mostConsistentFamily=")+ mostConsistentFamily)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
