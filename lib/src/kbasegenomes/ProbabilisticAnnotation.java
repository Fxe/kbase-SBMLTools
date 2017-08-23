
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


/**
 * <p>Original spec-file type: ProbabilisticAnnotation</p>
 * <pre>
 * Object to carry alternative functions and probabilities for genes in a genome
 *         probanno_id id - ID of the probabilistic annotation object
 *         Genome_ref genome_ref - reference to genome probabilistic annotation was built for
 *         mapping<Feature_id, list<function_probability>> roleset_probabilities - mapping of features to list of alternative function_probability objects
 *         list<Feature_id> skipped_features - list of features in genome with no probability
 *         
 *             @searchable ws_subset id genome_ref skipped_features
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "genome_ref",
    "roleset_probabilities",
    "skipped_features"
})
public class ProbabilisticAnnotation {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("genome_ref")
    private java.lang.String genomeRef;
    @JsonProperty("roleset_probabilities")
    private Map<String, List<Tuple2 <String, Double>>> rolesetProbabilities;
    @JsonProperty("skipped_features")
    private List<String> skippedFeatures;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ProbabilisticAnnotation withId(java.lang.String id) {
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

    public ProbabilisticAnnotation withGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("roleset_probabilities")
    public Map<String, List<Tuple2 <String, Double>>> getRolesetProbabilities() {
        return rolesetProbabilities;
    }

    @JsonProperty("roleset_probabilities")
    public void setRolesetProbabilities(Map<String, List<Tuple2 <String, Double>>> rolesetProbabilities) {
        this.rolesetProbabilities = rolesetProbabilities;
    }

    public ProbabilisticAnnotation withRolesetProbabilities(Map<String, List<Tuple2 <String, Double>>> rolesetProbabilities) {
        this.rolesetProbabilities = rolesetProbabilities;
        return this;
    }

    @JsonProperty("skipped_features")
    public List<String> getSkippedFeatures() {
        return skippedFeatures;
    }

    @JsonProperty("skipped_features")
    public void setSkippedFeatures(List<String> skippedFeatures) {
        this.skippedFeatures = skippedFeatures;
    }

    public ProbabilisticAnnotation withSkippedFeatures(List<String> skippedFeatures) {
        this.skippedFeatures = skippedFeatures;
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
        return ((((((((((("ProbabilisticAnnotation"+" [id=")+ id)+", genomeRef=")+ genomeRef)+", rolesetProbabilities=")+ rolesetProbabilities)+", skippedFeatures=")+ skippedFeatures)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
