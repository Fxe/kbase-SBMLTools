
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
 * <p>Original spec-file type: NewTemplateBiomassComponent</p>
 * <pre>
 * TemplateBiomassComponent object holds data on a compound of biomass in template
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "class",
    "templatecompcompound_ref",
    "coefficient_type",
    "coefficient",
    "linked_compound_refs",
    "link_coefficients"
})
public class NewTemplateBiomassComponent {

    @JsonProperty("class")
    private java.lang.String _class;
    @JsonProperty("templatecompcompound_ref")
    private java.lang.String templatecompcompoundRef;
    @JsonProperty("coefficient_type")
    private java.lang.String coefficientType;
    @JsonProperty("coefficient")
    private java.lang.Double coefficient;
    @JsonProperty("linked_compound_refs")
    private List<String> linkedCompoundRefs;
    @JsonProperty("link_coefficients")
    private List<Double> linkCoefficients;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("class")
    public java.lang.String getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(java.lang.String _class) {
        this._class = _class;
    }

    public NewTemplateBiomassComponent withClass(java.lang.String _class) {
        this._class = _class;
        return this;
    }

    @JsonProperty("templatecompcompound_ref")
    public java.lang.String getTemplatecompcompoundRef() {
        return templatecompcompoundRef;
    }

    @JsonProperty("templatecompcompound_ref")
    public void setTemplatecompcompoundRef(java.lang.String templatecompcompoundRef) {
        this.templatecompcompoundRef = templatecompcompoundRef;
    }

    public NewTemplateBiomassComponent withTemplatecompcompoundRef(java.lang.String templatecompcompoundRef) {
        this.templatecompcompoundRef = templatecompcompoundRef;
        return this;
    }

    @JsonProperty("coefficient_type")
    public java.lang.String getCoefficientType() {
        return coefficientType;
    }

    @JsonProperty("coefficient_type")
    public void setCoefficientType(java.lang.String coefficientType) {
        this.coefficientType = coefficientType;
    }

    public NewTemplateBiomassComponent withCoefficientType(java.lang.String coefficientType) {
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

    public NewTemplateBiomassComponent withCoefficient(java.lang.Double coefficient) {
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

    public NewTemplateBiomassComponent withLinkedCompoundRefs(List<String> linkedCompoundRefs) {
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

    public NewTemplateBiomassComponent withLinkCoefficients(List<Double> linkCoefficients) {
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
        return ((((((((((((((("NewTemplateBiomassComponent"+" [_class=")+ _class)+", templatecompcompoundRef=")+ templatecompcompoundRef)+", coefficientType=")+ coefficientType)+", coefficient=")+ coefficient)+", linkedCompoundRefs=")+ linkedCompoundRefs)+", linkCoefficients=")+ linkCoefficients)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
