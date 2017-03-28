
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
 * <p>Original spec-file type: FBAMinimalMediaResult</p>
 * <pre>
 * FBAMinimalMediaResult object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "essentialNutrient_refs",
    "optionalNutrient_refs"
})
public class FBAMinimalMediaResult {

    @JsonProperty("essentialNutrient_refs")
    private List<String> essentialNutrientRefs;
    @JsonProperty("optionalNutrient_refs")
    private List<String> optionalNutrientRefs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("essentialNutrient_refs")
    public List<String> getEssentialNutrientRefs() {
        return essentialNutrientRefs;
    }

    @JsonProperty("essentialNutrient_refs")
    public void setEssentialNutrientRefs(List<String> essentialNutrientRefs) {
        this.essentialNutrientRefs = essentialNutrientRefs;
    }

    public FBAMinimalMediaResult withEssentialNutrientRefs(List<String> essentialNutrientRefs) {
        this.essentialNutrientRefs = essentialNutrientRefs;
        return this;
    }

    @JsonProperty("optionalNutrient_refs")
    public List<String> getOptionalNutrientRefs() {
        return optionalNutrientRefs;
    }

    @JsonProperty("optionalNutrient_refs")
    public void setOptionalNutrientRefs(List<String> optionalNutrientRefs) {
        this.optionalNutrientRefs = optionalNutrientRefs;
    }

    public FBAMinimalMediaResult withOptionalNutrientRefs(List<String> optionalNutrientRefs) {
        this.optionalNutrientRefs = optionalNutrientRefs;
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
        return ((((((("FBAMinimalMediaResult"+" [essentialNutrientRefs=")+ essentialNutrientRefs)+", optionalNutrientRefs=")+ optionalNutrientRefs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
