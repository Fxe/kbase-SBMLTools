
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
 * <p>Original spec-file type: FBAConstraint</p>
 * <pre>
 * FBAConstraint object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "name",
    "rhs",
    "sign",
    "compound_terms",
    "reaction_terms",
    "biomass_terms"
})
public class FBAConstraint {

    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("rhs")
    private java.lang.Double rhs;
    @JsonProperty("sign")
    private java.lang.String sign;
    @JsonProperty("compound_terms")
    private Map<String, Double> compoundTerms;
    @JsonProperty("reaction_terms")
    private Map<String, Double> reactionTerms;
    @JsonProperty("biomass_terms")
    private Map<String, Double> biomassTerms;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public FBAConstraint withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("rhs")
    public java.lang.Double getRhs() {
        return rhs;
    }

    @JsonProperty("rhs")
    public void setRhs(java.lang.Double rhs) {
        this.rhs = rhs;
    }

    public FBAConstraint withRhs(java.lang.Double rhs) {
        this.rhs = rhs;
        return this;
    }

    @JsonProperty("sign")
    public java.lang.String getSign() {
        return sign;
    }

    @JsonProperty("sign")
    public void setSign(java.lang.String sign) {
        this.sign = sign;
    }

    public FBAConstraint withSign(java.lang.String sign) {
        this.sign = sign;
        return this;
    }

    @JsonProperty("compound_terms")
    public Map<String, Double> getCompoundTerms() {
        return compoundTerms;
    }

    @JsonProperty("compound_terms")
    public void setCompoundTerms(Map<String, Double> compoundTerms) {
        this.compoundTerms = compoundTerms;
    }

    public FBAConstraint withCompoundTerms(Map<String, Double> compoundTerms) {
        this.compoundTerms = compoundTerms;
        return this;
    }

    @JsonProperty("reaction_terms")
    public Map<String, Double> getReactionTerms() {
        return reactionTerms;
    }

    @JsonProperty("reaction_terms")
    public void setReactionTerms(Map<String, Double> reactionTerms) {
        this.reactionTerms = reactionTerms;
    }

    public FBAConstraint withReactionTerms(Map<String, Double> reactionTerms) {
        this.reactionTerms = reactionTerms;
        return this;
    }

    @JsonProperty("biomass_terms")
    public Map<String, Double> getBiomassTerms() {
        return biomassTerms;
    }

    @JsonProperty("biomass_terms")
    public void setBiomassTerms(Map<String, Double> biomassTerms) {
        this.biomassTerms = biomassTerms;
    }

    public FBAConstraint withBiomassTerms(Map<String, Double> biomassTerms) {
        this.biomassTerms = biomassTerms;
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
        return ((((((((((((((("FBAConstraint"+" [name=")+ name)+", rhs=")+ rhs)+", sign=")+ sign)+", compoundTerms=")+ compoundTerms)+", reactionTerms=")+ reactionTerms)+", biomassTerms=")+ biomassTerms)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
