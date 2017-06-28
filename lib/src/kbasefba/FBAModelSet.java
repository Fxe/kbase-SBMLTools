
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
 * <p>Original spec-file type: FBAModelSet</p>
 * <pre>
 * A type describing a set of FBAModels, where each element of the set 
 * is an FBAModel object reference.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "description",
    "elements"
})
public class FBAModelSet {

    @JsonProperty("description")
    private java.lang.String description;
    @JsonProperty("elements")
    private Map<String, FBAModelSetElement> elements;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("description")
    public java.lang.String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public FBAModelSet withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("elements")
    public Map<String, FBAModelSetElement> getElements() {
        return elements;
    }

    @JsonProperty("elements")
    public void setElements(Map<String, FBAModelSetElement> elements) {
        this.elements = elements;
    }

    public FBAModelSet withElements(Map<String, FBAModelSetElement> elements) {
        this.elements = elements;
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
        return ((((((("FBAModelSet"+" [description=")+ description)+", elements=")+ elements)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
