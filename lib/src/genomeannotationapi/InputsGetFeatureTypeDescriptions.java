
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
 * <p>Original spec-file type: inputs_get_feature_type_descriptions</p>
 * <pre>
 * optional feature_type_list
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "feature_type_list"
})
public class InputsGetFeatureTypeDescriptions {

    @JsonProperty("ref")
    private java.lang.String ref;
    @JsonProperty("feature_type_list")
    private List<String> featureTypeList;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public InputsGetFeatureTypeDescriptions withRef(java.lang.String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("feature_type_list")
    public List<String> getFeatureTypeList() {
        return featureTypeList;
    }

    @JsonProperty("feature_type_list")
    public void setFeatureTypeList(List<String> featureTypeList) {
        this.featureTypeList = featureTypeList;
    }

    public InputsGetFeatureTypeDescriptions withFeatureTypeList(List<String> featureTypeList) {
        this.featureTypeList = featureTypeList;
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
        return ((((((("InputsGetFeatureTypeDescriptions"+" [ref=")+ ref)+", featureTypeList=")+ featureTypeList)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
