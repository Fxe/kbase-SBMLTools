
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
 * <p>Original spec-file type: TemplateCompound</p>
 * <pre>
 * TemplateCompound parallel to compound object in biochemistry compound_ref
 * @optional compound_ref md5
 * Z25 4437
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "compound_ref",
    "name",
    "abbreviation",
    "md5",
    "isCofactor",
    "aliases",
    "defaultCharge",
    "mass",
    "deltaG",
    "deltaGErr",
    "formula"
})
public class TemplateCompound {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("compound_ref")
    private java.lang.String compoundRef;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("abbreviation")
    private java.lang.String abbreviation;
    @JsonProperty("md5")
    private java.lang.String md5;
    @JsonProperty("isCofactor")
    private Long isCofactor;
    @JsonProperty("aliases")
    private List<String> aliases;
    @JsonProperty("defaultCharge")
    private Double defaultCharge;
    @JsonProperty("mass")
    private Double mass;
    @JsonProperty("deltaG")
    private Double deltaG;
    @JsonProperty("deltaGErr")
    private Double deltaGErr;
    @JsonProperty("formula")
    private java.lang.String formula;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public TemplateCompound withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("compound_ref")
    public java.lang.String getCompoundRef() {
        return compoundRef;
    }

    @JsonProperty("compound_ref")
    public void setCompoundRef(java.lang.String compoundRef) {
        this.compoundRef = compoundRef;
    }

    public TemplateCompound withCompoundRef(java.lang.String compoundRef) {
        this.compoundRef = compoundRef;
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

    public TemplateCompound withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("abbreviation")
    public java.lang.String getAbbreviation() {
        return abbreviation;
    }

    @JsonProperty("abbreviation")
    public void setAbbreviation(java.lang.String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public TemplateCompound withAbbreviation(java.lang.String abbreviation) {
        this.abbreviation = abbreviation;
        return this;
    }

    @JsonProperty("md5")
    public java.lang.String getMd5() {
        return md5;
    }

    @JsonProperty("md5")
    public void setMd5(java.lang.String md5) {
        this.md5 = md5;
    }

    public TemplateCompound withMd5(java.lang.String md5) {
        this.md5 = md5;
        return this;
    }

    @JsonProperty("isCofactor")
    public Long getIsCofactor() {
        return isCofactor;
    }

    @JsonProperty("isCofactor")
    public void setIsCofactor(Long isCofactor) {
        this.isCofactor = isCofactor;
    }

    public TemplateCompound withIsCofactor(Long isCofactor) {
        this.isCofactor = isCofactor;
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

    public TemplateCompound withAliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }

    @JsonProperty("defaultCharge")
    public Double getDefaultCharge() {
        return defaultCharge;
    }

    @JsonProperty("defaultCharge")
    public void setDefaultCharge(Double defaultCharge) {
        this.defaultCharge = defaultCharge;
    }

    public TemplateCompound withDefaultCharge(Double defaultCharge) {
        this.defaultCharge = defaultCharge;
        return this;
    }

    @JsonProperty("mass")
    public Double getMass() {
        return mass;
    }

    @JsonProperty("mass")
    public void setMass(Double mass) {
        this.mass = mass;
    }

    public TemplateCompound withMass(Double mass) {
        this.mass = mass;
        return this;
    }

    @JsonProperty("deltaG")
    public Double getDeltaG() {
        return deltaG;
    }

    @JsonProperty("deltaG")
    public void setDeltaG(Double deltaG) {
        this.deltaG = deltaG;
    }

    public TemplateCompound withDeltaG(Double deltaG) {
        this.deltaG = deltaG;
        return this;
    }

    @JsonProperty("deltaGErr")
    public Double getDeltaGErr() {
        return deltaGErr;
    }

    @JsonProperty("deltaGErr")
    public void setDeltaGErr(Double deltaGErr) {
        this.deltaGErr = deltaGErr;
    }

    public TemplateCompound withDeltaGErr(Double deltaGErr) {
        this.deltaGErr = deltaGErr;
        return this;
    }

    @JsonProperty("formula")
    public java.lang.String getFormula() {
        return formula;
    }

    @JsonProperty("formula")
    public void setFormula(java.lang.String formula) {
        this.formula = formula;
    }

    public TemplateCompound withFormula(java.lang.String formula) {
        this.formula = formula;
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
        return ((((((((((((((((((((((((((("TemplateCompound"+" [id=")+ id)+", compoundRef=")+ compoundRef)+", name=")+ name)+", abbreviation=")+ abbreviation)+", md5=")+ md5)+", isCofactor=")+ isCofactor)+", aliases=")+ aliases)+", defaultCharge=")+ defaultCharge)+", mass=")+ mass)+", deltaG=")+ deltaG)+", deltaGErr=")+ deltaGErr)+", formula=")+ formula)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
