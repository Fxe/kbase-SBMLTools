
package genomeannotationapi;

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
 * <p>Original spec-file type: Feature_data</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "feature_id",
    "feature_type",
    "feature_function",
    "feature_aliases",
    "feature_dna_sequence_length",
    "feature_dna_sequence",
    "feature_md5",
    "feature_locations",
    "feature_publications",
    "feature_quality_warnings",
    "feature_quality_score",
    "feature_notes",
    "feature_inference"
})
public class FeatureData {

    @JsonProperty("feature_id")
    private java.lang.String featureId;
    @JsonProperty("feature_type")
    private java.lang.String featureType;
    @JsonProperty("feature_function")
    private java.lang.String featureFunction;
    @JsonProperty("feature_aliases")
    private Map<String, List<String>> featureAliases;
    @JsonProperty("feature_dna_sequence_length")
    private Long featureDnaSequenceLength;
    @JsonProperty("feature_dna_sequence")
    private java.lang.String featureDnaSequence;
    @JsonProperty("feature_md5")
    private java.lang.String featureMd5;
    @JsonProperty("feature_locations")
    private List<Region> featureLocations;
    @JsonProperty("feature_publications")
    private List<String> featurePublications;
    @JsonProperty("feature_quality_warnings")
    private List<String> featureQualityWarnings;
    @JsonProperty("feature_quality_score")
    private List<String> featureQualityScore;
    @JsonProperty("feature_notes")
    private java.lang.String featureNotes;
    @JsonProperty("feature_inference")
    private java.lang.String featureInference;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("feature_id")
    public java.lang.String getFeatureId() {
        return featureId;
    }

    @JsonProperty("feature_id")
    public void setFeatureId(java.lang.String featureId) {
        this.featureId = featureId;
    }

    public FeatureData withFeatureId(java.lang.String featureId) {
        this.featureId = featureId;
        return this;
    }

    @JsonProperty("feature_type")
    public java.lang.String getFeatureType() {
        return featureType;
    }

    @JsonProperty("feature_type")
    public void setFeatureType(java.lang.String featureType) {
        this.featureType = featureType;
    }

    public FeatureData withFeatureType(java.lang.String featureType) {
        this.featureType = featureType;
        return this;
    }

    @JsonProperty("feature_function")
    public java.lang.String getFeatureFunction() {
        return featureFunction;
    }

    @JsonProperty("feature_function")
    public void setFeatureFunction(java.lang.String featureFunction) {
        this.featureFunction = featureFunction;
    }

    public FeatureData withFeatureFunction(java.lang.String featureFunction) {
        this.featureFunction = featureFunction;
        return this;
    }

    @JsonProperty("feature_aliases")
    public Map<String, List<String>> getFeatureAliases() {
        return featureAliases;
    }

    @JsonProperty("feature_aliases")
    public void setFeatureAliases(Map<String, List<String>> featureAliases) {
        this.featureAliases = featureAliases;
    }

    public FeatureData withFeatureAliases(Map<String, List<String>> featureAliases) {
        this.featureAliases = featureAliases;
        return this;
    }

    @JsonProperty("feature_dna_sequence_length")
    public Long getFeatureDnaSequenceLength() {
        return featureDnaSequenceLength;
    }

    @JsonProperty("feature_dna_sequence_length")
    public void setFeatureDnaSequenceLength(Long featureDnaSequenceLength) {
        this.featureDnaSequenceLength = featureDnaSequenceLength;
    }

    public FeatureData withFeatureDnaSequenceLength(Long featureDnaSequenceLength) {
        this.featureDnaSequenceLength = featureDnaSequenceLength;
        return this;
    }

    @JsonProperty("feature_dna_sequence")
    public java.lang.String getFeatureDnaSequence() {
        return featureDnaSequence;
    }

    @JsonProperty("feature_dna_sequence")
    public void setFeatureDnaSequence(java.lang.String featureDnaSequence) {
        this.featureDnaSequence = featureDnaSequence;
    }

    public FeatureData withFeatureDnaSequence(java.lang.String featureDnaSequence) {
        this.featureDnaSequence = featureDnaSequence;
        return this;
    }

    @JsonProperty("feature_md5")
    public java.lang.String getFeatureMd5() {
        return featureMd5;
    }

    @JsonProperty("feature_md5")
    public void setFeatureMd5(java.lang.String featureMd5) {
        this.featureMd5 = featureMd5;
    }

    public FeatureData withFeatureMd5(java.lang.String featureMd5) {
        this.featureMd5 = featureMd5;
        return this;
    }

    @JsonProperty("feature_locations")
    public List<Region> getFeatureLocations() {
        return featureLocations;
    }

    @JsonProperty("feature_locations")
    public void setFeatureLocations(List<Region> featureLocations) {
        this.featureLocations = featureLocations;
    }

    public FeatureData withFeatureLocations(List<Region> featureLocations) {
        this.featureLocations = featureLocations;
        return this;
    }

    @JsonProperty("feature_publications")
    public List<String> getFeaturePublications() {
        return featurePublications;
    }

    @JsonProperty("feature_publications")
    public void setFeaturePublications(List<String> featurePublications) {
        this.featurePublications = featurePublications;
    }

    public FeatureData withFeaturePublications(List<String> featurePublications) {
        this.featurePublications = featurePublications;
        return this;
    }

    @JsonProperty("feature_quality_warnings")
    public List<String> getFeatureQualityWarnings() {
        return featureQualityWarnings;
    }

    @JsonProperty("feature_quality_warnings")
    public void setFeatureQualityWarnings(List<String> featureQualityWarnings) {
        this.featureQualityWarnings = featureQualityWarnings;
    }

    public FeatureData withFeatureQualityWarnings(List<String> featureQualityWarnings) {
        this.featureQualityWarnings = featureQualityWarnings;
        return this;
    }

    @JsonProperty("feature_quality_score")
    public List<String> getFeatureQualityScore() {
        return featureQualityScore;
    }

    @JsonProperty("feature_quality_score")
    public void setFeatureQualityScore(List<String> featureQualityScore) {
        this.featureQualityScore = featureQualityScore;
    }

    public FeatureData withFeatureQualityScore(List<String> featureQualityScore) {
        this.featureQualityScore = featureQualityScore;
        return this;
    }

    @JsonProperty("feature_notes")
    public java.lang.String getFeatureNotes() {
        return featureNotes;
    }

    @JsonProperty("feature_notes")
    public void setFeatureNotes(java.lang.String featureNotes) {
        this.featureNotes = featureNotes;
    }

    public FeatureData withFeatureNotes(java.lang.String featureNotes) {
        this.featureNotes = featureNotes;
        return this;
    }

    @JsonProperty("feature_inference")
    public java.lang.String getFeatureInference() {
        return featureInference;
    }

    @JsonProperty("feature_inference")
    public void setFeatureInference(java.lang.String featureInference) {
        this.featureInference = featureInference;
    }

    public FeatureData withFeatureInference(java.lang.String featureInference) {
        this.featureInference = featureInference;
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
        return ((((((((((((((((((((((((((((("FeatureData"+" [featureId=")+ featureId)+", featureType=")+ featureType)+", featureFunction=")+ featureFunction)+", featureAliases=")+ featureAliases)+", featureDnaSequenceLength=")+ featureDnaSequenceLength)+", featureDnaSequence=")+ featureDnaSequence)+", featureMd5=")+ featureMd5)+", featureLocations=")+ featureLocations)+", featurePublications=")+ featurePublications)+", featureQualityWarnings=")+ featureQualityWarnings)+", featureQualityScore=")+ featureQualityScore)+", featureNotes=")+ featureNotes)+", featureInference=")+ featureInference)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
