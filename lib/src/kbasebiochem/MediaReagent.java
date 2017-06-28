
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
 * <p>Original spec-file type: MediaReagent</p>
 * <pre>
 * MediaReagent object
 * @optional molecular_weight concentration_units concentration associated_compounds
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "concentration",
    "concentration_units",
    "molecular_weight",
    "associated_compounds"
})
public class MediaReagent {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("concentration")
    private java.lang.Double concentration;
    @JsonProperty("concentration_units")
    private java.lang.String concentrationUnits;
    @JsonProperty("molecular_weight")
    private java.lang.Double molecularWeight;
    @JsonProperty("associated_compounds")
    private Map<String, Double> associatedCompounds;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public MediaReagent withId(java.lang.String id) {
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

    public MediaReagent withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("concentration")
    public java.lang.Double getConcentration() {
        return concentration;
    }

    @JsonProperty("concentration")
    public void setConcentration(java.lang.Double concentration) {
        this.concentration = concentration;
    }

    public MediaReagent withConcentration(java.lang.Double concentration) {
        this.concentration = concentration;
        return this;
    }

    @JsonProperty("concentration_units")
    public java.lang.String getConcentrationUnits() {
        return concentrationUnits;
    }

    @JsonProperty("concentration_units")
    public void setConcentrationUnits(java.lang.String concentrationUnits) {
        this.concentrationUnits = concentrationUnits;
    }

    public MediaReagent withConcentrationUnits(java.lang.String concentrationUnits) {
        this.concentrationUnits = concentrationUnits;
        return this;
    }

    @JsonProperty("molecular_weight")
    public java.lang.Double getMolecularWeight() {
        return molecularWeight;
    }

    @JsonProperty("molecular_weight")
    public void setMolecularWeight(java.lang.Double molecularWeight) {
        this.molecularWeight = molecularWeight;
    }

    public MediaReagent withMolecularWeight(java.lang.Double molecularWeight) {
        this.molecularWeight = molecularWeight;
        return this;
    }

    @JsonProperty("associated_compounds")
    public Map<String, Double> getAssociatedCompounds() {
        return associatedCompounds;
    }

    @JsonProperty("associated_compounds")
    public void setAssociatedCompounds(Map<String, Double> associatedCompounds) {
        this.associatedCompounds = associatedCompounds;
    }

    public MediaReagent withAssociatedCompounds(Map<String, Double> associatedCompounds) {
        this.associatedCompounds = associatedCompounds;
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
        return ((((((((((((((("MediaReagent"+" [id=")+ id)+", name=")+ name)+", concentration=")+ concentration)+", concentrationUnits=")+ concentrationUnits)+", molecularWeight=")+ molecularWeight)+", associatedCompounds=")+ associatedCompounds)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
