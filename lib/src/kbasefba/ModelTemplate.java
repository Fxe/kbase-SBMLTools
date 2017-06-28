
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
 * <p>Original spec-file type: ModelTemplate</p>
 * <pre>
 * ModelTemplate object holds data on how a model is constructed from an annotation
 *             
 * @optional name
 * @searchable ws_subset id name modelType domain mapping_ref
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "modelType",
    "domain",
    "mapping_ref",
    "biochemistry_ref",
    "templateReactions",
    "templateBiomasses"
})
public class ModelTemplate {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("modelType")
    private String modelType;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("mapping_ref")
    private String mappingRef;
    @JsonProperty("biochemistry_ref")
    private String biochemistryRef;
    @JsonProperty("templateReactions")
    private List<TemplateReaction> templateReactions;
    @JsonProperty("templateBiomasses")
    private List<TemplateBiomass> templateBiomasses;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ModelTemplate withId(String id) {
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

    public ModelTemplate withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("modelType")
    public String getModelType() {
        return modelType;
    }

    @JsonProperty("modelType")
    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public ModelTemplate withModelType(String modelType) {
        this.modelType = modelType;
        return this;
    }

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public ModelTemplate withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    @JsonProperty("mapping_ref")
    public String getMappingRef() {
        return mappingRef;
    }

    @JsonProperty("mapping_ref")
    public void setMappingRef(String mappingRef) {
        this.mappingRef = mappingRef;
    }

    public ModelTemplate withMappingRef(String mappingRef) {
        this.mappingRef = mappingRef;
        return this;
    }

    @JsonProperty("biochemistry_ref")
    public String getBiochemistryRef() {
        return biochemistryRef;
    }

    @JsonProperty("biochemistry_ref")
    public void setBiochemistryRef(String biochemistryRef) {
        this.biochemistryRef = biochemistryRef;
    }

    public ModelTemplate withBiochemistryRef(String biochemistryRef) {
        this.biochemistryRef = biochemistryRef;
        return this;
    }

    @JsonProperty("templateReactions")
    public List<TemplateReaction> getTemplateReactions() {
        return templateReactions;
    }

    @JsonProperty("templateReactions")
    public void setTemplateReactions(List<TemplateReaction> templateReactions) {
        this.templateReactions = templateReactions;
    }

    public ModelTemplate withTemplateReactions(List<TemplateReaction> templateReactions) {
        this.templateReactions = templateReactions;
        return this;
    }

    @JsonProperty("templateBiomasses")
    public List<TemplateBiomass> getTemplateBiomasses() {
        return templateBiomasses;
    }

    @JsonProperty("templateBiomasses")
    public void setTemplateBiomasses(List<TemplateBiomass> templateBiomasses) {
        this.templateBiomasses = templateBiomasses;
    }

    public ModelTemplate withTemplateBiomasses(List<TemplateBiomass> templateBiomasses) {
        this.templateBiomasses = templateBiomasses;
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
        return ((((((((((((((((((("ModelTemplate"+" [id=")+ id)+", name=")+ name)+", modelType=")+ modelType)+", domain=")+ domain)+", mappingRef=")+ mappingRef)+", biochemistryRef=")+ biochemistryRef)+", templateReactions=")+ templateReactions)+", templateBiomasses=")+ templateBiomasses)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
