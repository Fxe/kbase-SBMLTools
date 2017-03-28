
package kbasefba;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: FBAModelSetElement</p>
 * <pre>
 * This type represents an element of a FBAModelSet.
 * @optional metadata
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "metadata",
    "ref"
})
public class FBAModelSetElement {

    @JsonProperty("metadata")
    private Map<String, String> metadata;
    @JsonProperty("ref")
    private java.lang.String ref;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("metadata")
    public Map<String, String> getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public FBAModelSetElement withMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public FBAModelSetElement withRef(java.lang.String ref) {
        this.ref = ref;
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
        return ((((((("FBAModelSetElement"+" [metadata=")+ metadata)+", ref=")+ ref)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
