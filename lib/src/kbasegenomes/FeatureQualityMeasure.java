
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
 * <p>Original spec-file type: Feature_quality_measure</p>
 * <pre>
 * @optional weighted_hit_count hit_count existence_priority overlap_rules pyrrolysylprotein truncated_begin truncated_end existence_confidence frameshifted selenoprotein
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "truncated_begin",
    "truncated_end",
    "existence_confidence",
    "frameshifted",
    "selenoprotein",
    "pyrrolysylprotein",
    "overlap_rules",
    "existence_priority",
    "hit_count",
    "weighted_hit_count"
})
public class FeatureQualityMeasure {

    @JsonProperty("truncated_begin")
    private Long truncatedBegin;
    @JsonProperty("truncated_end")
    private Long truncatedEnd;
    @JsonProperty("existence_confidence")
    private Double existenceConfidence;
    @JsonProperty("frameshifted")
    private Long frameshifted;
    @JsonProperty("selenoprotein")
    private Long selenoprotein;
    @JsonProperty("pyrrolysylprotein")
    private Long pyrrolysylprotein;
    @JsonProperty("overlap_rules")
    private List<String> overlapRules;
    @JsonProperty("existence_priority")
    private Double existencePriority;
    @JsonProperty("hit_count")
    private Double hitCount;
    @JsonProperty("weighted_hit_count")
    private Double weightedHitCount;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("truncated_begin")
    public Long getTruncatedBegin() {
        return truncatedBegin;
    }

    @JsonProperty("truncated_begin")
    public void setTruncatedBegin(Long truncatedBegin) {
        this.truncatedBegin = truncatedBegin;
    }

    public FeatureQualityMeasure withTruncatedBegin(Long truncatedBegin) {
        this.truncatedBegin = truncatedBegin;
        return this;
    }

    @JsonProperty("truncated_end")
    public Long getTruncatedEnd() {
        return truncatedEnd;
    }

    @JsonProperty("truncated_end")
    public void setTruncatedEnd(Long truncatedEnd) {
        this.truncatedEnd = truncatedEnd;
    }

    public FeatureQualityMeasure withTruncatedEnd(Long truncatedEnd) {
        this.truncatedEnd = truncatedEnd;
        return this;
    }

    @JsonProperty("existence_confidence")
    public Double getExistenceConfidence() {
        return existenceConfidence;
    }

    @JsonProperty("existence_confidence")
    public void setExistenceConfidence(Double existenceConfidence) {
        this.existenceConfidence = existenceConfidence;
    }

    public FeatureQualityMeasure withExistenceConfidence(Double existenceConfidence) {
        this.existenceConfidence = existenceConfidence;
        return this;
    }

    @JsonProperty("frameshifted")
    public Long getFrameshifted() {
        return frameshifted;
    }

    @JsonProperty("frameshifted")
    public void setFrameshifted(Long frameshifted) {
        this.frameshifted = frameshifted;
    }

    public FeatureQualityMeasure withFrameshifted(Long frameshifted) {
        this.frameshifted = frameshifted;
        return this;
    }

    @JsonProperty("selenoprotein")
    public Long getSelenoprotein() {
        return selenoprotein;
    }

    @JsonProperty("selenoprotein")
    public void setSelenoprotein(Long selenoprotein) {
        this.selenoprotein = selenoprotein;
    }

    public FeatureQualityMeasure withSelenoprotein(Long selenoprotein) {
        this.selenoprotein = selenoprotein;
        return this;
    }

    @JsonProperty("pyrrolysylprotein")
    public Long getPyrrolysylprotein() {
        return pyrrolysylprotein;
    }

    @JsonProperty("pyrrolysylprotein")
    public void setPyrrolysylprotein(Long pyrrolysylprotein) {
        this.pyrrolysylprotein = pyrrolysylprotein;
    }

    public FeatureQualityMeasure withPyrrolysylprotein(Long pyrrolysylprotein) {
        this.pyrrolysylprotein = pyrrolysylprotein;
        return this;
    }

    @JsonProperty("overlap_rules")
    public List<String> getOverlapRules() {
        return overlapRules;
    }

    @JsonProperty("overlap_rules")
    public void setOverlapRules(List<String> overlapRules) {
        this.overlapRules = overlapRules;
    }

    public FeatureQualityMeasure withOverlapRules(List<String> overlapRules) {
        this.overlapRules = overlapRules;
        return this;
    }

    @JsonProperty("existence_priority")
    public Double getExistencePriority() {
        return existencePriority;
    }

    @JsonProperty("existence_priority")
    public void setExistencePriority(Double existencePriority) {
        this.existencePriority = existencePriority;
    }

    public FeatureQualityMeasure withExistencePriority(Double existencePriority) {
        this.existencePriority = existencePriority;
        return this;
    }

    @JsonProperty("hit_count")
    public Double getHitCount() {
        return hitCount;
    }

    @JsonProperty("hit_count")
    public void setHitCount(Double hitCount) {
        this.hitCount = hitCount;
    }

    public FeatureQualityMeasure withHitCount(Double hitCount) {
        this.hitCount = hitCount;
        return this;
    }

    @JsonProperty("weighted_hit_count")
    public Double getWeightedHitCount() {
        return weightedHitCount;
    }

    @JsonProperty("weighted_hit_count")
    public void setWeightedHitCount(Double weightedHitCount) {
        this.weightedHitCount = weightedHitCount;
    }

    public FeatureQualityMeasure withWeightedHitCount(Double weightedHitCount) {
        this.weightedHitCount = weightedHitCount;
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
        return ((((((((((((((((((((((("FeatureQualityMeasure"+" [truncatedBegin=")+ truncatedBegin)+", truncatedEnd=")+ truncatedEnd)+", existenceConfidence=")+ existenceConfidence)+", frameshifted=")+ frameshifted)+", selenoprotein=")+ selenoprotein)+", pyrrolysylprotein=")+ pyrrolysylprotein)+", overlapRules=")+ overlapRules)+", existencePriority=")+ existencePriority)+", hitCount=")+ hitCount)+", weightedHitCount=")+ weightedHitCount)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
