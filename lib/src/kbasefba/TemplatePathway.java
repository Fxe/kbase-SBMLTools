
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
 * <p>Original spec-file type: TemplatePathway</p>
 * <pre>
 * TemplatePathway object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "source",
    "source_id",
    "broadClassification",
    "midClassification",
    "templatereaction_refs"
})
public class TemplatePathway {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("source")
    private java.lang.String source;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    @JsonProperty("broadClassification")
    private java.lang.String broadClassification;
    @JsonProperty("midClassification")
    private java.lang.String midClassification;
    @JsonProperty("templatereaction_refs")
    private List<String> templatereactionRefs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public TemplatePathway withId(java.lang.String id) {
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

    public TemplatePathway withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("source")
    public java.lang.String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(java.lang.String source) {
        this.source = source;
    }

    public TemplatePathway withSource(java.lang.String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("source_id")
    public java.lang.String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
    }

    public TemplatePathway withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("broadClassification")
    public java.lang.String getBroadClassification() {
        return broadClassification;
    }

    @JsonProperty("broadClassification")
    public void setBroadClassification(java.lang.String broadClassification) {
        this.broadClassification = broadClassification;
    }

    public TemplatePathway withBroadClassification(java.lang.String broadClassification) {
        this.broadClassification = broadClassification;
        return this;
    }

    @JsonProperty("midClassification")
    public java.lang.String getMidClassification() {
        return midClassification;
    }

    @JsonProperty("midClassification")
    public void setMidClassification(java.lang.String midClassification) {
        this.midClassification = midClassification;
    }

    public TemplatePathway withMidClassification(java.lang.String midClassification) {
        this.midClassification = midClassification;
        return this;
    }

    @JsonProperty("templatereaction_refs")
    public List<String> getTemplatereactionRefs() {
        return templatereactionRefs;
    }

    @JsonProperty("templatereaction_refs")
    public void setTemplatereactionRefs(List<String> templatereactionRefs) {
        this.templatereactionRefs = templatereactionRefs;
    }

    public TemplatePathway withTemplatereactionRefs(List<String> templatereactionRefs) {
        this.templatereactionRefs = templatereactionRefs;
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
        return ((((((((((((((((("TemplatePathway"+" [id=")+ id)+", name=")+ name)+", source=")+ source)+", sourceId=")+ sourceId)+", broadClassification=")+ broadClassification)+", midClassification=")+ midClassification)+", templatereactionRefs=")+ templatereactionRefs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
