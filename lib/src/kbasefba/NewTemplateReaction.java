
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
 * <p>Original spec-file type: NewTemplateReaction</p>
 * <pre>
 * TemplateReaction object holds data on reaction in template
 * @optional reference base_cost forward_penalty reverse_penalty GapfillDirection reaction_ref
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "reaction_ref",
    "name",
    "type",
    "reference",
    "direction",
    "GapfillDirection",
    "maxforflux",
    "maxrevflux",
    "templatecompartment_ref",
    "base_cost",
    "forward_penalty",
    "reverse_penalty",
    "templateReactionReagents",
    "templatecomplex_refs"
})
public class NewTemplateReaction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("reaction_ref")
    private java.lang.String reactionRef;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("reference")
    private java.lang.String reference;
    @JsonProperty("direction")
    private java.lang.String direction;
    @JsonProperty("GapfillDirection")
    private java.lang.String GapfillDirection;
    @JsonProperty("maxforflux")
    private Double maxforflux;
    @JsonProperty("maxrevflux")
    private Double maxrevflux;
    @JsonProperty("templatecompartment_ref")
    private java.lang.String templatecompartmentRef;
    @JsonProperty("base_cost")
    private Double baseCost;
    @JsonProperty("forward_penalty")
    private Double forwardPenalty;
    @JsonProperty("reverse_penalty")
    private Double reversePenalty;
    @JsonProperty("templateReactionReagents")
    private List<TemplateReactionReagent> templateReactionReagents;
    @JsonProperty("templatecomplex_refs")
    private List<String> templatecomplexRefs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public NewTemplateReaction withId(java.lang.String id) {
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

    public NewTemplateReaction withReactionRef(java.lang.String reactionRef) {
        this.reactionRef = reactionRef;
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

    public NewTemplateReaction withName(java.lang.String name) {
        this.name = name;
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

    public NewTemplateReaction withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("reference")
    public java.lang.String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(java.lang.String reference) {
        this.reference = reference;
    }

    public NewTemplateReaction withReference(java.lang.String reference) {
        this.reference = reference;
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

    public NewTemplateReaction withDirection(java.lang.String direction) {
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

    public NewTemplateReaction withGapfillDirection(java.lang.String GapfillDirection) {
        this.GapfillDirection = GapfillDirection;
        return this;
    }

    @JsonProperty("maxforflux")
    public Double getMaxforflux() {
        return maxforflux;
    }

    @JsonProperty("maxforflux")
    public void setMaxforflux(Double maxforflux) {
        this.maxforflux = maxforflux;
    }

    public NewTemplateReaction withMaxforflux(Double maxforflux) {
        this.maxforflux = maxforflux;
        return this;
    }

    @JsonProperty("maxrevflux")
    public Double getMaxrevflux() {
        return maxrevflux;
    }

    @JsonProperty("maxrevflux")
    public void setMaxrevflux(Double maxrevflux) {
        this.maxrevflux = maxrevflux;
    }

    public NewTemplateReaction withMaxrevflux(Double maxrevflux) {
        this.maxrevflux = maxrevflux;
        return this;
    }

    @JsonProperty("templatecompartment_ref")
    public java.lang.String getTemplatecompartmentRef() {
        return templatecompartmentRef;
    }

    @JsonProperty("templatecompartment_ref")
    public void setTemplatecompartmentRef(java.lang.String templatecompartmentRef) {
        this.templatecompartmentRef = templatecompartmentRef;
    }

    public NewTemplateReaction withTemplatecompartmentRef(java.lang.String templatecompartmentRef) {
        this.templatecompartmentRef = templatecompartmentRef;
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

    public NewTemplateReaction withBaseCost(Double baseCost) {
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

    public NewTemplateReaction withForwardPenalty(Double forwardPenalty) {
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

    public NewTemplateReaction withReversePenalty(Double reversePenalty) {
        this.reversePenalty = reversePenalty;
        return this;
    }

    @JsonProperty("templateReactionReagents")
    public List<TemplateReactionReagent> getTemplateReactionReagents() {
        return templateReactionReagents;
    }

    @JsonProperty("templateReactionReagents")
    public void setTemplateReactionReagents(List<TemplateReactionReagent> templateReactionReagents) {
        this.templateReactionReagents = templateReactionReagents;
    }

    public NewTemplateReaction withTemplateReactionReagents(List<TemplateReactionReagent> templateReactionReagents) {
        this.templateReactionReagents = templateReactionReagents;
        return this;
    }

    @JsonProperty("templatecomplex_refs")
    public List<String> getTemplatecomplexRefs() {
        return templatecomplexRefs;
    }

    @JsonProperty("templatecomplex_refs")
    public void setTemplatecomplexRefs(List<String> templatecomplexRefs) {
        this.templatecomplexRefs = templatecomplexRefs;
    }

    public NewTemplateReaction withTemplatecomplexRefs(List<String> templatecomplexRefs) {
        this.templatecomplexRefs = templatecomplexRefs;
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
        return ((((((((((((((((((((((((((((((((("NewTemplateReaction"+" [id=")+ id)+", reactionRef=")+ reactionRef)+", name=")+ name)+", type=")+ type)+", reference=")+ reference)+", direction=")+ direction)+", GapfillDirection=")+ GapfillDirection)+", maxforflux=")+ maxforflux)+", maxrevflux=")+ maxrevflux)+", templatecompartmentRef=")+ templatecompartmentRef)+", baseCost=")+ baseCost)+", forwardPenalty=")+ forwardPenalty)+", reversePenalty=")+ reversePenalty)+", templateReactionReagents=")+ templateReactionReagents)+", templatecomplexRefs=")+ templatecomplexRefs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
