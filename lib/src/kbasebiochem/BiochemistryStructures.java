
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
 * <p>Original spec-file type: BiochemistryStructures</p>
 * <pre>
 * BiochemistryStructures object
 * @optional description name
 * @searchable ws_subset id name structures
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "description",
    "structures"
})
public class BiochemistryStructures {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("structures")
    private List<CompoundStructure> structures;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public BiochemistryStructures withId(String id) {
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

    public BiochemistryStructures withName(String name) {
        this.name = name;
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

    public BiochemistryStructures withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("structures")
    public List<CompoundStructure> getStructures() {
        return structures;
    }

    @JsonProperty("structures")
    public void setStructures(List<CompoundStructure> structures) {
        this.structures = structures;
    }

    public BiochemistryStructures withStructures(List<CompoundStructure> structures) {
        this.structures = structures;
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
        return ((((((((((("BiochemistryStructures"+" [id=")+ id)+", name=")+ name)+", description=")+ description)+", structures=")+ structures)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
