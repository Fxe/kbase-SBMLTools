
package kbasefba;

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
 * <p>Original spec-file type: TemplateCompartment</p>
 * <pre>
 * TemplateCompartment parallel to compartment object in biochemistry
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "aliases",
    "hierarchy",
    "pH"
})
public class TemplateCompartment {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("aliases")
    private List<String> aliases;
    @JsonProperty("hierarchy")
    private Long hierarchy;
    @JsonProperty("pH")
    private Double pH;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public TemplateCompartment withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public TemplateCompartment withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("aliases")
    public List<String> getAliases() {
        return aliases;
    }

    @JsonProperty("aliases")
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public TemplateCompartment withAliases(List<String> aliases) {
        this.aliases = aliases;
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

    public TemplateCompartment withHierarchy(Long hierarchy) {
        this.hierarchy = hierarchy;
        return this;
    }

    @JsonProperty("pH")
    public Double getPH() {
        return pH;
    }

    @JsonProperty("pH")
    public void setPH(Double pH) {
        this.pH = pH;
    }

    public TemplateCompartment withPH(Double pH) {
        this.pH = pH;
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
        return ((((((((((((("TemplateCompartment"+" [id=")+ id)+", name=")+ name)+", aliases=")+ aliases)+", hierarchy=")+ hierarchy)+", pH=")+ pH)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
