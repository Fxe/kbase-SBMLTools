
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
 * <p>Original spec-file type: GetFeatures2Params</p>
 * <pre>
 * exclude_sequence = set to 1 (true) or 0 (false) to indicate if sequences
 * should be included.  Defautl is false.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "feature_id_list",
    "exclude_sequence"
})
public class GetFeatures2Params {

    @JsonProperty("ref")
    private java.lang.String ref;
    @JsonProperty("feature_id_list")
    private List<String> featureIdList;
    @JsonProperty("exclude_sequence")
    private Long excludeSequence;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public GetFeatures2Params withRef(java.lang.String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("feature_id_list")
    public List<String> getFeatureIdList() {
        return featureIdList;
    }

    @JsonProperty("feature_id_list")
    public void setFeatureIdList(List<String> featureIdList) {
        this.featureIdList = featureIdList;
    }

    public GetFeatures2Params withFeatureIdList(List<String> featureIdList) {
        this.featureIdList = featureIdList;
        return this;
    }

    @JsonProperty("exclude_sequence")
    public Long getExcludeSequence() {
        return excludeSequence;
    }

    @JsonProperty("exclude_sequence")
    public void setExcludeSequence(Long excludeSequence) {
        this.excludeSequence = excludeSequence;
    }

    public GetFeatures2Params withExcludeSequence(Long excludeSequence) {
        this.excludeSequence = excludeSequence;
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
        return ((((((((("GetFeatures2Params"+" [ref=")+ ref)+", featureIdList=")+ featureIdList)+", excludeSequence=")+ excludeSequence)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
