
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
 * <p>Original spec-file type: Cue</p>
 * <pre>
 * Cue object
 * @optional mass deltaGErr deltaG defaultCharge structure_key structure_data structure_type
 *     @searchable ws_subset structure_key id name abbreviation formula unchargedFormula mass defaultCharge deltaG smallMolecule priority structure_key structure_data
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "abbreviation",
    "formula",
    "unchargedFormula",
    "mass",
    "defaultCharge",
    "deltaG",
    "deltaGErr",
    "smallMolecule",
    "priority",
    "structure_key",
    "structure_data",
    "structure_type"
})
public class Cue {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("abbreviation")
    private String abbreviation;
    @JsonProperty("formula")
    private String formula;
    @JsonProperty("unchargedFormula")
    private String unchargedFormula;
    @JsonProperty("mass")
    private Double mass;
    @JsonProperty("defaultCharge")
    private Double defaultCharge;
    @JsonProperty("deltaG")
    private Double deltaG;
    @JsonProperty("deltaGErr")
    private Double deltaGErr;
    @JsonProperty("smallMolecule")
    private Long smallMolecule;
    @JsonProperty("priority")
    private Long priority;
    @JsonProperty("structure_key")
    private String structureKey;
    @JsonProperty("structure_data")
    private String structureData;
    @JsonProperty("structure_type")
    private String structureType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Cue withId(String id) {
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

    public Cue withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    @JsonProperty("abbreviation")
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Cue withAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
        return this;
    }

    @JsonProperty("formula")
    public String getFormula() {
        return formula;
    }

    @JsonProperty("formula")
    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Cue withFormula(String formula) {
        this.formula = formula;
        return this;
    }

    @JsonProperty("unchargedFormula")
    public String getUnchargedFormula() {
        return unchargedFormula;
    }

    @JsonProperty("unchargedFormula")
    public void setUnchargedFormula(String unchargedFormula) {
        this.unchargedFormula = unchargedFormula;
    }

    public Cue withUnchargedFormula(String unchargedFormula) {
        this.unchargedFormula = unchargedFormula;
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

    public Cue withMass(Double mass) {
        this.mass = mass;
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

    public Cue withDefaultCharge(Double defaultCharge) {
        this.defaultCharge = defaultCharge;
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

    public Cue withDeltaG(Double deltaG) {
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

    public Cue withDeltaGErr(Double deltaGErr) {
        this.deltaGErr = deltaGErr;
        return this;
    }

    @JsonProperty("smallMolecule")
    public Long getSmallMolecule() {
        return smallMolecule;
    }

    @JsonProperty("smallMolecule")
    public void setSmallMolecule(Long smallMolecule) {
        this.smallMolecule = smallMolecule;
    }

    public Cue withSmallMolecule(Long smallMolecule) {
        this.smallMolecule = smallMolecule;
        return this;
    }

    @JsonProperty("priority")
    public Long getPriority() {
        return priority;
    }

    @JsonProperty("priority")
    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Cue withPriority(Long priority) {
        this.priority = priority;
        return this;
    }

    @JsonProperty("structure_key")
    public String getStructureKey() {
        return structureKey;
    }

    @JsonProperty("structure_key")
    public void setStructureKey(String structureKey) {
        this.structureKey = structureKey;
    }

    public Cue withStructureKey(String structureKey) {
        this.structureKey = structureKey;
        return this;
    }

    @JsonProperty("structure_data")
    public String getStructureData() {
        return structureData;
    }

    @JsonProperty("structure_data")
    public void setStructureData(String structureData) {
        this.structureData = structureData;
    }

    public Cue withStructureData(String structureData) {
        this.structureData = structureData;
        return this;
    }

    @JsonProperty("structure_type")
    public String getStructureType() {
        return structureType;
    }

    @JsonProperty("structure_type")
    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public Cue withStructureType(String structureType) {
        this.structureType = structureType;
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
        return ((((((((((((((((((((((((((((((("Cue"+" [id=")+ id)+", name=")+ name)+", abbreviation=")+ abbreviation)+", formula=")+ formula)+", unchargedFormula=")+ unchargedFormula)+", mass=")+ mass)+", defaultCharge=")+ defaultCharge)+", deltaG=")+ deltaG)+", deltaGErr=")+ deltaGErr)+", smallMolecule=")+ smallMolecule)+", priority=")+ priority)+", structureKey=")+ structureKey)+", structureData=")+ structureData)+", structureType=")+ structureType)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
