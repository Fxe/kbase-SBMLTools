
package kbasefba;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: TemplateCompCompound</p>
 * <pre>
 * TemplateCompCompound object parallel to compound in model
 * @optional formula
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "templatecompound_ref",
    "charge",
    "maxuptake",
    "formula",
    "templatecompartment_ref"
})
public class TemplateCompCompound {

    @JsonProperty("id")
    private String id;
    @JsonProperty("templatecompound_ref")
    private String templatecompoundRef;
    @JsonProperty("charge")
    private Double charge;
    @JsonProperty("maxuptake")
    private Double maxuptake;
    @JsonProperty("formula")
    private String formula;
    @JsonProperty("templatecompartment_ref")
    private String templatecompartmentRef;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public TemplateCompCompound withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("templatecompound_ref")
    public String getTemplatecompoundRef() {
        return templatecompoundRef;
    }

    @JsonProperty("templatecompound_ref")
    public void setTemplatecompoundRef(String templatecompoundRef) {
        this.templatecompoundRef = templatecompoundRef;
    }

    public TemplateCompCompound withTemplatecompoundRef(String templatecompoundRef) {
        this.templatecompoundRef = templatecompoundRef;
        return this;
    }

    @JsonProperty("charge")
    public Double getCharge() {
        return charge;
    }

    @JsonProperty("charge")
    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public TemplateCompCompound withCharge(Double charge) {
        this.charge = charge;
        return this;
    }

    @JsonProperty("maxuptake")
    public Double getMaxuptake() {
        return maxuptake;
    }

    @JsonProperty("maxuptake")
    public void setMaxuptake(Double maxuptake) {
        this.maxuptake = maxuptake;
    }

    public TemplateCompCompound withMaxuptake(Double maxuptake) {
        this.maxuptake = maxuptake;
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

    public TemplateCompCompound withFormula(String formula) {
        this.formula = formula;
        return this;
    }

    @JsonProperty("templatecompartment_ref")
    public String getTemplatecompartmentRef() {
        return templatecompartmentRef;
    }

    @JsonProperty("templatecompartment_ref")
    public void setTemplatecompartmentRef(String templatecompartmentRef) {
        this.templatecompartmentRef = templatecompartmentRef;
    }

    public TemplateCompCompound withTemplatecompartmentRef(String templatecompartmentRef) {
        this.templatecompartmentRef = templatecompartmentRef;
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
        return ((((((((((((((("TemplateCompCompound"+" [id=")+ id)+", templatecompoundRef=")+ templatecompoundRef)+", charge=")+ charge)+", maxuptake=")+ maxuptake)+", formula=")+ formula)+", templatecompartmentRef=")+ templatecompartmentRef)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
