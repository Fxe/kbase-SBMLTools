
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
 * <p>Original spec-file type: TemplateReaction</p>
 * <pre>
 * TemplateReaction object holds data on reaction in template
 * @optional base_cost forward_penalty reverse_penalty GapfillDirection
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "reaction_ref",
    "compartment_ref",
    "complex_refs",
    "direction",
    "GapfillDirection",
    "type",
    "base_cost",
    "forward_penalty",
    "reverse_penalty"
})
public class TemplateReaction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("reaction_ref")
    private java.lang.String reactionRef;
    @JsonProperty("compartment_ref")
    private java.lang.String compartmentRef;
    @JsonProperty("complex_refs")
    private List<String> complexRefs;
    @JsonProperty("direction")
    private java.lang.String direction;
    @JsonProperty("GapfillDirection")
    private java.lang.String GapfillDirection;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("base_cost")
    private Double baseCost;
    @JsonProperty("forward_penalty")
    private Double forwardPenalty;
    @JsonProperty("reverse_penalty")
    private Double reversePenalty;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public TemplateReaction withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("reaction_ref")
    public java.lang.String getReactionRef() {
        return reactionRef;
    }

    @JsonProperty("reaction_ref")
    public void setReactionRef(java.lang.String reactionRef) {
        this.reactionRef = reactionRef;
    }

    public TemplateReaction withReactionRef(java.lang.String reactionRef) {
        this.reactionRef = reactionRef;
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

    public TemplateReaction withCompartmentRef(java.lang.String compartmentRef) {
        this.compartmentRef = compartmentRef;
        return this;
    }

    @JsonProperty("complex_refs")
    public List<String> getComplexRefs() {
        return complexRefs;
    }

    @JsonProperty("complex_refs")
    public void setComplexRefs(List<String> complexRefs) {
        this.complexRefs = complexRefs;
    }

    public TemplateReaction withComplexRefs(List<String> complexRefs) {
        this.complexRefs = complexRefs;
        return this;
    }

    @JsonProperty("direction")
    public java.lang.String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(java.lang.String direction) {
        this.direction = direction;
    }

    public TemplateReaction withDirection(java.lang.String direction) {
        this.direction = direction;
        return this;
    }

    @JsonProperty("GapfillDirection")
    public java.lang.String getGapfillDirection() {
        return GapfillDirection;
    }

    @JsonProperty("GapfillDirection")
    public void setGapfillDirection(java.lang.String GapfillDirection) {
        this.GapfillDirection = GapfillDirection;
    }

    public TemplateReaction withGapfillDirection(java.lang.String GapfillDirection) {
        this.GapfillDirection = GapfillDirection;
        return this;
    }

    @JsonProperty("type")
    public java.lang.String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(java.lang.String type) {
        this.type = type;
    }

    public TemplateReaction withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("base_cost")
    public Double getBaseCost() {
        return baseCost;
    }

    @JsonProperty("base_cost")
    public void setBaseCost(Double baseCost) {
        this.baseCost = baseCost;
    }

    public TemplateReaction withBaseCost(Double baseCost) {
        this.baseCost = baseCost;
        return this;
    }

    @JsonProperty("forward_penalty")
    public Double getForwardPenalty() {
        return forwardPenalty;
    }

    @JsonProperty("forward_penalty")
    public void setForwardPenalty(Double forwardPenalty) {
        this.forwardPenalty = forwardPenalty;
    }

    public TemplateReaction withForwardPenalty(Double forwardPenalty) {
        this.forwardPenalty = forwardPenalty;
        return this;
    }

    @JsonProperty("reverse_penalty")
    public Double getReversePenalty() {
        return reversePenalty;
    }

    @JsonProperty("reverse_penalty")
    public void setReversePenalty(Double reversePenalty) {
        this.reversePenalty = reversePenalty;
    }

    public TemplateReaction withReversePenalty(Double reversePenalty) {
        this.reversePenalty = reversePenalty;
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
        return ((((((((((((((((((((((("TemplateReaction"+" [id=")+ id)+", reactionRef=")+ reactionRef)+", compartmentRef=")+ compartmentRef)+", complexRefs=")+ complexRefs)+", direction=")+ direction)+", GapfillDirection=")+ GapfillDirection)+", type=")+ type)+", baseCost=")+ baseCost)+", forwardPenalty=")+ forwardPenalty)+", reversePenalty=")+ reversePenalty)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
