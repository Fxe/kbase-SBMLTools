
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
 * <p>Original spec-file type: inputs_get_mrna_by_cds</p>
 * <pre>
 * @optional cds_id_list
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "cds_id_list"
})
public class InputsGetMrnaByCds {

    @JsonProperty("ref")
    private java.lang.String ref;
    @JsonProperty("cds_id_list")
    private List<String> cdsIdList;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public InputsGetMrnaByCds withRef(java.lang.String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("cds_id_list")
    public List<String> getCdsIdList() {
        return cdsIdList;
    }

    @JsonProperty("cds_id_list")
    public void setCdsIdList(List<String> cdsIdList) {
        this.cdsIdList = cdsIdList;
    }

    public InputsGetMrnaByCds withCdsIdList(List<String> cdsIdList) {
        this.cdsIdList = cdsIdList;
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
        return ((((((("InputsGetMrnaByCds"+" [ref=")+ ref)+", cdsIdList=")+ cdsIdList)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
