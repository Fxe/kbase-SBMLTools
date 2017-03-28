
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
 * <p>Original spec-file type: TemplateBiomassComponent</p>
 * <pre>
 * TemplateBiomassComponent object holds data on a compound of biomass in template
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "class",
    "compound_ref",
    "compartment_ref",
    "coefficientType",
    "coefficient",
    "linked_compound_refs",
    "link_coefficients"
})
public class TemplateBiomassComponent {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("class")
    private java.lang.String _class;
    @JsonProperty("compound_ref")
    private java.lang.String compoundRef;
    @JsonProperty("compartment_ref")
    private java.lang.String compartmentRef;
    @JsonProperty("coefficientType")
    private java.lang.String coefficientType;
    @JsonProperty("coefficient")
    private java.lang.Double coefficient;
    @JsonProperty("linked_compound_refs")
    private List<String> linkedCompoundRefs;
    @JsonProperty("link_coefficients")
    private List<Double> linkCoefficients;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public TemplateBiomassComponent withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("class")
    public java.lang.String getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(java.lang.String _class) {
        this._class = _class;
    }

    public TemplateBiomassComponent withClass(java.lang.String _class) {
        this._class = _class;
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

    public TemplateBiomassComponent withCompoundRef(java.lang.String compoundRef) {
        this.compoundRef = compoundRef;
        return this;
    }

    @JsonProperty("compartment_ref")
    public java.lang.String getCompartmentRef() {
        return compartmentRef;
    }

    @JsonProperty("compartment_ref")
    public void setCompartmentRef(java.lang.String compartmentRef) {
        this.compartmentRef = compartmentRef;
    }

    public TemplateBiomassComponent withCompartmentRef(java.lang.String compartmentRef) {
        this.compartmentRef = compartmentRef;
        return this;
    }

    @JsonProperty("coefficientType")
    public java.lang.String getCoefficientType() {
        return coefficientType;
    }

    @JsonProperty("coefficientType")
    public void setCoefficientType(java.lang.String coefficientType) {
        this.coefficientType = coefficientType;
    }

    public TemplateBiomassComponent withCoefficientType(java.lang.String coefficientType) {
        this.coefficientType = coefficientType;
        return this;
    }

    @JsonProperty("coefficient")
    public java.lang.Double getCoefficient() {
        return coefficient;
    }

    @JsonProperty("coefficient")
    public void setCoefficient(java.lang.Double coefficient) {
        this.coefficient = coefficient;
    }

    public TemplateBiomassComponent withCoefficient(java.lang.Double coefficient) {
        this.coefficient = coefficient;
        return this;
    }

    @JsonProperty("linked_compound_refs")
    public List<String> getLinkedCompoundRefs() {
        return linkedCompoundRefs;
    }

    @JsonProperty("linked_compound_refs")
    public void setLinkedCompoundRefs(List<String> linkedCompoundRefs) {
        this.linkedCompoundRefs = linkedCompoundRefs;
    }

    public TemplateBiomassComponent withLinkedCompoundRefs(List<String> linkedCompoundRefs) {
        this.linkedCompoundRefs = linkedCompoundRefs;
        return this;
    }

    @JsonProperty("link_coefficients")
    public List<Double> getLinkCoefficients() {
        return linkCoefficients;
    }

    @JsonProperty("link_coefficients")
    public void setLinkCoefficients(List<Double> linkCoefficients) {
        this.linkCoefficients = linkCoefficients;
    }

    public TemplateBiomassComponent withLinkCoefficients(List<Double> linkCoefficients) {
        this.linkCoefficients = linkCoefficients;
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
        return ((((((((((((((((((("TemplateBiomassComponent"+" [id=")+ id)+", _class=")+ _class)+", compoundRef=")+ compoundRef)+", compartmentRef=")+ compartmentRef)+", coefficientType=")+ coefficientType)+", coefficient=")+ coefficient)+", linkedCompoundRefs=")+ linkedCompoundRefs)+", linkCoefficients=")+ linkCoefficients)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
