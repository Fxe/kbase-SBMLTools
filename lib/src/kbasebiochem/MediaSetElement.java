
package kbasebiochem;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: MediaSetElement</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "string_metadata",
    "numerial_metadata",
    "ref"
})
public class MediaSetElement {

    @JsonProperty("string_metadata")
    private Map<String, String> stringMetadata;
    @JsonProperty("numerial_metadata")
    private Map<String, Double> numerialMetadata;
    @JsonProperty("ref")
    private java.lang.String ref;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("string_metadata")
    public Map<String, String> getStringMetadata() {
        return stringMetadata;
    }

    @JsonProperty("string_metadata")
    public void setStringMetadata(Map<String, String> stringMetadata) {
        this.stringMetadata = stringMetadata;
    }

    public MediaSetElement withStringMetadata(Map<String, String> stringMetadata) {
        this.stringMetadata = stringMetadata;
        return this;
    }

    @JsonProperty("numerial_metadata")
    public Map<String, Double> getNumerialMetadata() {
        return numerialMetadata;
    }

    @JsonProperty("numerial_metadata")
    public void setNumerialMetadata(Map<String, Double> numerialMetadata) {
        this.numerialMetadata = numerialMetadata;
    }

    public MediaSetElement withNumerialMetadata(Map<String, Double> numerialMetadata) {
        this.numerialMetadata = numerialMetadata;
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

    public MediaSetElement withRef(java.lang.String ref) {
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
        return ((((((((("MediaSetElement"+" [stringMetadata=")+ stringMetadata)+", numerialMetadata=")+ numerialMetadata)+", ref=")+ ref)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
