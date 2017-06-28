
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
 * <p>Original spec-file type: Compound</p>
 * <pre>
 * Compound object
 * @optional md5 formula unchargedFormula mass deltaG deltaGErr abstractCompound_ref comprisedOfCompound_refs structure_ref cues pkas pkbs
 *     @searchable ws_subset id isCofactor name abbreviation md5 formula unchargedFormula mass defaultCharge deltaG abstractCompound_ref comprisedOfCompound_refs structure_ref cues
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "isCofactor",
    "name",
    "abbreviation",
    "md5",
    "formula",
    "unchargedFormula",
    "mass",
    "defaultCharge",
    "deltaG",
    "deltaGErr",
    "abstractCompound_ref",
    "comprisedOfCompound_refs",
    "structure_ref",
    "cues",
    "pkas",
    "pkbs"
})
public class Compound {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("isCofactor")
    private java.lang.Long isCofactor;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("abbreviation")
    private java.lang.String abbreviation;
    @JsonProperty("md5")
    private java.lang.String md5;
    @JsonProperty("formula")
    private java.lang.String formula;
    @JsonProperty("unchargedFormula")
    private java.lang.String unchargedFormula;
    @JsonProperty("mass")
    private java.lang.Double mass;
    @JsonProperty("defaultCharge")
    private java.lang.Double defaultCharge;
    @JsonProperty("deltaG")
    private java.lang.Double deltaG;
    @JsonProperty("deltaGErr")
    private java.lang.Double deltaGErr;
    @JsonProperty("abstractCompound_ref")
    private java.lang.String abstractCompoundRef;
    @JsonProperty("comprisedOfCompound_refs")
    private List<String> comprisedOfCompoundRefs;
    @JsonProperty("structure_ref")
    private java.lang.String structureRef;
    @JsonProperty("cues")
    private Map<String, Double> cues;
    @JsonProperty("pkas")
    private Map<Long, List<Double>> pkas;
    @JsonProperty("pkbs")
    private Map<Long, List<Double>> pkbs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Compound withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("isCofactor")
    public java.lang.Long getIsCofactor() {
        return isCofactor;
    }

    @JsonProperty("isCofactor")
    public void setIsCofactor(java.lang.Long isCofactor) {
        this.isCofactor = isCofactor;
    }

    public Compound withIsCofactor(java.lang.Long isCofactor) {
        this.isCofactor = isCofactor;
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

    public Compound withName(java.lang.String name) {
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

    public Compound withAbbreviation(java.lang.String abbreviation) {
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

    public Compound withMd5(java.lang.String md5) {
        this.md5 = md5;
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

    public Compound withFormula(java.lang.String formula) {
        this.formula = formula;
        return this;
    }

    @JsonProperty("unchargedFormula")
    public java.lang.String getUnchargedFormula() {
        return unchargedFormula;
    }

    @JsonProperty("unchargedFormula")
    public void setUnchargedFormula(java.lang.String unchargedFormula) {
        this.unchargedFormula = unchargedFormula;
    }

    public Compound withUnchargedFormula(java.lang.String unchargedFormula) {
        this.unchargedFormula = unchargedFormula;
        return this;
    }

    @JsonProperty("mass")
    public java.lang.Double getMass() {
        return mass;
    }

    @JsonProperty("mass")
    public void setMass(java.lang.Double mass) {
        this.mass = mass;
    }

    public Compound withMass(java.lang.Double mass) {
        this.mass = mass;
        return this;
    }

    @JsonProperty("defaultCharge")
    public java.lang.Double getDefaultCharge() {
        return defaultCharge;
    }

    @JsonProperty("defaultCharge")
    public void setDefaultCharge(java.lang.Double defaultCharge) {
        this.defaultCharge = defaultCharge;
    }

    public Compound withDefaultCharge(java.lang.Double defaultCharge) {
        this.defaultCharge = defaultCharge;
        return this;
    }

    @JsonProperty("deltaG")
    public java.lang.Double getDeltaG() {
        return deltaG;
    }

    @JsonProperty("deltaG")
    public void setDeltaG(java.lang.Double deltaG) {
        this.deltaG = deltaG;
    }

    public Compound withDeltaG(java.lang.Double deltaG) {
        this.deltaG = deltaG;
        return this;
    }

    @JsonProperty("deltaGErr")
    public java.lang.Double getDeltaGErr() {
        return deltaGErr;
    }

    @JsonProperty("deltaGErr")
    public void setDeltaGErr(java.lang.Double deltaGErr) {
        this.deltaGErr = deltaGErr;
    }

    public Compound withDeltaGErr(java.lang.Double deltaGErr) {
        this.deltaGErr = deltaGErr;
        return this;
    }

    @JsonProperty("abstractCompound_ref")
    public java.lang.String getAbstractCompoundRef() {
        return abstractCompoundRef;
    }

    @JsonProperty("abstractCompound_ref")
    public void setAbstractCompoundRef(java.lang.String abstractCompoundRef) {
        this.abstractCompoundRef = abstractCompoundRef;
    }

    public Compound withAbstractCompoundRef(java.lang.String abstractCompoundRef) {
        this.abstractCompoundRef = abstractCompoundRef;
        return this;
    }

    @JsonProperty("comprisedOfCompound_refs")
    public List<String> getComprisedOfCompoundRefs() {
        return comprisedOfCompoundRefs;
    }

    @JsonProperty("comprisedOfCompound_refs")
    public void setComprisedOfCompoundRefs(List<String> comprisedOfCompoundRefs) {
        this.comprisedOfCompoundRefs = comprisedOfCompoundRefs;
    }

    public Compound withComprisedOfCompoundRefs(List<String> comprisedOfCompoundRefs) {
        this.comprisedOfCompoundRefs = comprisedOfCompoundRefs;
        return this;
    }

    @JsonProperty("structure_ref")
    public java.lang.String getStructureRef() {
        return structureRef;
    }

    @JsonProperty("structure_ref")
    public void setStructureRef(java.lang.String structureRef) {
        this.structureRef = structureRef;
    }

    public Compound withStructureRef(java.lang.String structureRef) {
        this.structureRef = structureRef;
        return this;
    }

    @JsonProperty("cues")
    public Map<String, Double> getCues() {
        return cues;
    }

    @JsonProperty("cues")
    public void setCues(Map<String, Double> cues) {
        this.cues = cues;
    }

    public Compound withCues(Map<String, Double> cues) {
        this.cues = cues;
        return this;
    }

    @JsonProperty("pkas")
    public Map<Long, List<Double>> getPkas() {
        return pkas;
    }

    @JsonProperty("pkas")
    public void setPkas(Map<Long, List<Double>> pkas) {
        this.pkas = pkas;
    }

    public Compound withPkas(Map<Long, List<Double>> pkas) {
        this.pkas = pkas;
        return this;
    }

    @JsonProperty("pkbs")
    public Map<Long, List<Double>> getPkbs() {
        return pkbs;
    }

    @JsonProperty("pkbs")
    public void setPkbs(Map<Long, List<Double>> pkbs) {
        this.pkbs = pkbs;
    }

    public Compound withPkbs(Map<Long, List<Double>> pkbs) {
        this.pkbs = pkbs;
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
        return ((((((((((((((((((((((((((((((((((((("Compound"+" [id=")+ id)+", isCofactor=")+ isCofactor)+", name=")+ name)+", abbreviation=")+ abbreviation)+", md5=")+ md5)+", formula=")+ formula)+", unchargedFormula=")+ unchargedFormula)+", mass=")+ mass)+", defaultCharge=")+ defaultCharge)+", deltaG=")+ deltaG)+", deltaGErr=")+ deltaGErr)+", abstractCompoundRef=")+ abstractCompoundRef)+", comprisedOfCompoundRefs=")+ comprisedOfCompoundRefs)+", structureRef=")+ structureRef)+", cues=")+ cues)+", pkas=")+ pkas)+", pkbs=")+ pkbs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
