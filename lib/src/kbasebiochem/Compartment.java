
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
 * <p>Original spec-file type: Compartment</p>
 * <pre>
 * Compartment object
 *     @searchable ws_subset id name hierarchy
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "hierarchy"
})
public class Compartment {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("hierarchy")
    private Long hierarchy;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Compartment withId(String id) {
        this.id = id;
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

    public Compartment withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("hierarchy")
    public Long getHierarchy() {
        return hierarchy;
    }

    @JsonProperty("hierarchy")
    public void setHierarchy(Long hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Compartment withHierarchy(Long hierarchy) {
        this.hierarchy = hierarchy;
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
        return ((((((((("Compartment"+" [id=")+ id)+", name=")+ name)+", hierarchy=")+ hierarchy)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
