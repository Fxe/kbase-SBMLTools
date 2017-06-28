
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
 * <p>Original spec-file type: NewModelTemplate</p>
 * <pre>
 * ModelTemplate object holds data on how a model is constructed from an annotation
 *             
 * @optional name
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "type",
    "domain",
    "biochemistry_ref",
    "roles",
    "complexes",
    "compounds",
    "compcompounds",
    "compartments",
    "reactions",
    "biomasses",
    "pathways"
})
public class NewModelTemplate {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("biochemistry_ref")
    private String biochemistryRef;
    @JsonProperty("roles")
    private List<TemplateRole> roles;
    @JsonProperty("complexes")
    private List<TemplateComplex> complexes;
    @JsonProperty("compounds")
    private List<TemplateCompound> compounds;
    @JsonProperty("compcompounds")
    private List<TemplateCompCompound> compcompounds;
    @JsonProperty("compartments")
    private List<TemplateCompartment> compartments;
    @JsonProperty("reactions")
    private List<NewTemplateReaction> reactions;
    @JsonProperty("biomasses")
    private List<NewTemplateBiomass> biomasses;
    @JsonProperty("pathways")
    private List<TemplatePathway> pathways;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public NewModelTemplate withId(String id) {
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

    public NewModelTemplate withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public NewModelTemplate withType(String type) {
        this.type = type;
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

    public NewModelTemplate withDomain(String domain) {
        this.domain = domain;
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

    public NewModelTemplate withBiochemistryRef(String biochemistryRef) {
        this.biochemistryRef = biochemistryRef;
        return this;
    }

    @JsonProperty("roles")
    public List<TemplateRole> getRoles() {
        return roles;
    }

    @JsonProperty("roles")
    public void setRoles(List<TemplateRole> roles) {
        this.roles = roles;
    }

    public NewModelTemplate withRoles(List<TemplateRole> roles) {
        this.roles = roles;
        return this;
    }

    @JsonProperty("complexes")
    public List<TemplateComplex> getComplexes() {
        return complexes;
    }

    @JsonProperty("complexes")
    public void setComplexes(List<TemplateComplex> complexes) {
        this.complexes = complexes;
    }

    public NewModelTemplate withComplexes(List<TemplateComplex> complexes) {
        this.complexes = complexes;
        return this;
    }

    @JsonProperty("compounds")
    public List<TemplateCompound> getCompounds() {
        return compounds;
    }

    @JsonProperty("compounds")
    public void setCompounds(List<TemplateCompound> compounds) {
        this.compounds = compounds;
    }

    public NewModelTemplate withCompounds(List<TemplateCompound> compounds) {
        this.compounds = compounds;
        return this;
    }

    @JsonProperty("compcompounds")
    public List<TemplateCompCompound> getCompcompounds() {
        return compcompounds;
    }

    @JsonProperty("compcompounds")
    public void setCompcompounds(List<TemplateCompCompound> compcompounds) {
        this.compcompounds = compcompounds;
    }

    public NewModelTemplate withCompcompounds(List<TemplateCompCompound> compcompounds) {
        this.compcompounds = compcompounds;
        return this;
    }

    @JsonProperty("compartments")
    public List<TemplateCompartment> getCompartments() {
        return compartments;
    }

    @JsonProperty("compartments")
    public void setCompartments(List<TemplateCompartment> compartments) {
        this.compartments = compartments;
    }

    public NewModelTemplate withCompartments(List<TemplateCompartment> compartments) {
        this.compartments = compartments;
        return this;
    }

    @JsonProperty("reactions")
    public List<NewTemplateReaction> getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(List<NewTemplateReaction> reactions) {
        this.reactions = reactions;
    }

    public NewModelTemplate withReactions(List<NewTemplateReaction> reactions) {
        this.reactions = reactions;
        return this;
    }

    @JsonProperty("biomasses")
    public List<NewTemplateBiomass> getBiomasses() {
        return biomasses;
    }

    @JsonProperty("biomasses")
    public void setBiomasses(List<NewTemplateBiomass> biomasses) {
        this.biomasses = biomasses;
    }

    public NewModelTemplate withBiomasses(List<NewTemplateBiomass> biomasses) {
        this.biomasses = biomasses;
        return this;
    }

    @JsonProperty("pathways")
    public List<TemplatePathway> getPathways() {
        return pathways;
    }

    @JsonProperty("pathways")
    public void setPathways(List<TemplatePathway> pathways) {
        this.pathways = pathways;
    }

    public NewModelTemplate withPathways(List<TemplatePathway> pathways) {
        this.pathways = pathways;
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
        return ((((((((((((((((((((((((((((("NewModelTemplate"+" [id=")+ id)+", name=")+ name)+", type=")+ type)+", domain=")+ domain)+", biochemistryRef=")+ biochemistryRef)+", roles=")+ roles)+", complexes=")+ complexes)+", compounds=")+ compounds)+", compcompounds=")+ compcompounds)+", compartments=")+ compartments)+", reactions=")+ reactions)+", biomasses=")+ biomasses)+", pathways=")+ pathways)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
