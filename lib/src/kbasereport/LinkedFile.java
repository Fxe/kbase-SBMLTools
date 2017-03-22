
package kbasereport;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: LinkedFile</p>
 * <pre>
 * Represents a file or html archive that the report should like to
 * @optional description label
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "handle",
    "description",
    "name",
    "label",
    "URL"
})
public class LinkedFile {

    @JsonProperty("handle")
    private String handle;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("label")
    private String label;
    @JsonProperty("URL")
    private String URL;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("handle")
    public String getHandle() {
        return handle;
    }

    @JsonProperty("handle")
    public void setHandle(String handle) {
        this.handle = handle;
    }

    public LinkedFile withHandle(String handle) {
        this.handle = handle;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedFile withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public LinkedFile withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    public LinkedFile withLabel(String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("URL")
    public String getURL() {
        return URL;
    }

    @JsonProperty("URL")
    public void setURL(String URL) {
        this.URL = URL;
    }

    public LinkedFile withURL(String URL) {
        this.URL = URL;
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
        return ((((((((((((("LinkedFile"+" [handle=")+ handle)+", description=")+ description)+", name=")+ name)+", label=")+ label)+", URL=")+ URL)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
