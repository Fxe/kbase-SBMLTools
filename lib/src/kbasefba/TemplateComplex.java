
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
 * <p>Original spec-file type: TemplateComplex</p>
 * <pre>
 * TemplateComplex object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "reference",
    "source",
    "confidence",
    "complexroles"
})
public class TemplateComplex {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("source")
    private String source;
    @JsonProperty("confidence")
    private Double confidence;
    @JsonProperty("complexroles")
    private List<TemplateComplexRole> complexroles;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public TemplateComplex withId(String id) {
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

    public TemplateComplex withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    public TemplateComplex withReference(String reference) {
        this.reference = reference;
        return this;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    public TemplateComplex withSource(String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("confidence")
    public Double getConfidence() {
        return confidence;
    }

    @JsonProperty("confidence")
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public TemplateComplex withConfidence(Double confidence) {
        this.confidence = confidence;
        return this;
    }

    @JsonProperty("complexroles")
    public List<TemplateComplexRole> getComplexroles() {
        return complexroles;
    }

    @JsonProperty("complexroles")
    public void setComplexroles(List<TemplateComplexRole> complexroles) {
        this.complexroles = complexroles;
    }

    public TemplateComplex withComplexroles(List<TemplateComplexRole> complexroles) {
        this.complexroles = complexroles;
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
        return ((((((((((((((("TemplateComplex"+" [id=")+ id)+", name=")+ name)+", reference=")+ reference)+", source=")+ source)+", confidence=")+ confidence)+", complexroles=")+ complexroles)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
