
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
 * <p>Original spec-file type: inputs_get_mrna_utrs</p>
 * <pre>
 * @optional mrna_id_list
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "mrna_id_list"
})
public class InputsGetMrnaUtrs {

    @JsonProperty("ref")
    private java.lang.String ref;
    @JsonProperty("mrna_id_list")
    private List<String> mrnaIdList;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public InputsGetMrnaUtrs withRef(java.lang.String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("mrna_id_list")
    public List<String> getMrnaIdList() {
        return mrnaIdList;
    }

    @JsonProperty("mrna_id_list")
    public void setMrnaIdList(List<String> mrnaIdList) {
        this.mrnaIdList = mrnaIdList;
    }

    public InputsGetMrnaUtrs withMrnaIdList(List<String> mrnaIdList) {
        this.mrnaIdList = mrnaIdList;
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
        return ((((((("InputsGetMrnaUtrs"+" [ref=")+ ref)+", mrnaIdList=")+ mrnaIdList)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
