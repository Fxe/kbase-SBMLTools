
package sbmltools;

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
 * <p>Original spec-file type: CompartmentMapping</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "kbase_compartment_id",
    "model_compartment_id"
})
public class CompartmentMapping {

    @JsonProperty("kbase_compartment_id")
    private java.lang.String kbaseCompartmentId;
    @JsonProperty("model_compartment_id")
    private List<String> modelCompartmentId;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("kbase_compartment_id")
    public java.lang.String getKbaseCompartmentId() {
        return kbaseCompartmentId;
    }

    @JsonProperty("kbase_compartment_id")
    public void setKbaseCompartmentId(java.lang.String kbaseCompartmentId) {
        this.kbaseCompartmentId = kbaseCompartmentId;
    }

    public CompartmentMapping withKbaseCompartmentId(java.lang.String kbaseCompartmentId) {
        this.kbaseCompartmentId = kbaseCompartmentId;
        return this;
    }

    @JsonProperty("model_compartment_id")
    public List<String> getModelCompartmentId() {
        return modelCompartmentId;
    }

    @JsonProperty("model_compartment_id")
    public void setModelCompartmentId(List<String> modelCompartmentId) {
        this.modelCompartmentId = modelCompartmentId;
    }

    public CompartmentMapping withModelCompartmentId(List<String> modelCompartmentId) {
        this.modelCompartmentId = modelCompartmentId;
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
        return ((((((("CompartmentMapping"+" [kbaseCompartmentId=")+ kbaseCompartmentId)+", modelCompartmentId=")+ modelCompartmentId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
