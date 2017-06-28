
package kbasebiochem;

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
 * <p>Original spec-file type: MediaSet</p>
 * <pre>
 * Media set object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "description",
    "elements"
})
public class MediaSet {

    @JsonProperty("description")
    private String description;
    @JsonProperty("elements")
    private List<MediaSetElement> elements;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public MediaSet withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("elements")
    public List<MediaSetElement> getElements() {
        return elements;
    }

    @JsonProperty("elements")
    public void setElements(List<MediaSetElement> elements) {
        this.elements = elements;
    }

    public MediaSet withElements(List<MediaSetElement> elements) {
        this.elements = elements;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return ((((((("MediaSet"+" [description=")+ description)+", elements=")+ elements)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
