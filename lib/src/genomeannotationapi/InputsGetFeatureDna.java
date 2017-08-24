
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
 * <p>Original spec-file type: inputs_get_feature_dna</p>
 * <pre>
 * *
 * * Retrieve Feature DNA sequences.
 * *
 * * @param feature_id_list List of Feature IDs for which to retrieve sequences.
 * *     If empty, returns data for all features.
 * * @return Mapping of Feature IDs to their DNA sequence.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "feature_id_list"
})
public class InputsGetFeatureDna {

    @JsonProperty("ref")
    private java.lang.String ref;
    @JsonProperty("feature_id_list")
    private List<String> featureIdList;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public InputsGetFeatureDna withRef(java.lang.String ref) {
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

    public InputsGetFeatureDna withFeatureIdList(List<String> featureIdList) {
        this.featureIdList = featureIdList;
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
        return ((((((("InputsGetFeatureDna"+" [ref=")+ ref)+", featureIdList=")+ featureIdList)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
