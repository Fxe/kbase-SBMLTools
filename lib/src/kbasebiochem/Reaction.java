
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
 * <p>Original spec-file type: Reaction</p>
 * <pre>
 * Reaction object
 * @optional md5 deltaG deltaGErr abstractReaction_ref cues
 *     @searchable ws_subset id name abbreviation md5 direction thermoReversibility status defaultProtons deltaG abstractReaction_ref cues
 *     @searchable ws_subset reagents.[*].(compound_ref,compartment_ref,coefficient,isCofactor)
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "abbreviation",
    "md5",
    "direction",
    "thermoReversibility",
    "status",
    "defaultProtons",
    "deltaG",
    "deltaGErr",
    "abstractReaction_ref",
    "cues",
    "reagents"
})
public class Reaction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("abbreviation")
    private java.lang.String abbreviation;
    @JsonProperty("md5")
    private java.lang.String md5;
    @JsonProperty("direction")
    private java.lang.String direction;
    @JsonProperty("thermoReversibility")
    private java.lang.String thermoReversibility;
    @JsonProperty("status")
    private java.lang.String status;
    @JsonProperty("defaultProtons")
    private java.lang.Double defaultProtons;
    @JsonProperty("deltaG")
    private java.lang.Double deltaG;
    @JsonProperty("deltaGErr")
    private java.lang.Double deltaGErr;
    @JsonProperty("abstractReaction_ref")
    private java.lang.String abstractReactionRef;
    @JsonProperty("cues")
    private Map<String, Double> cues;
    @JsonProperty("reagents")
    private List<Reagent> reagents;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Reaction withId(java.lang.String id) {
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

    public Reaction withName(java.lang.String name) {
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

    public Reaction withAbbreviation(java.lang.String abbreviation) {
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

    public Reaction withMd5(java.lang.String md5) {
        this.md5 = md5;
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

    public Reaction withDirection(java.lang.String direction) {
        this.direction = direction;
        return this;
    }

    @JsonProperty("thermoReversibility")
    public java.lang.String getThermoReversibility() {
        return thermoReversibility;
    }

    @JsonProperty("thermoReversibility")
    public void setThermoReversibility(java.lang.String thermoReversibility) {
        this.thermoReversibility = thermoReversibility;
    }

    public Reaction withThermoReversibility(java.lang.String thermoReversibility) {
        this.thermoReversibility = thermoReversibility;
        return this;
    }

    @JsonProperty("status")
    public java.lang.String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public Reaction withStatus(java.lang.String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("defaultProtons")
    public java.lang.Double getDefaultProtons() {
        return defaultProtons;
    }

    @JsonProperty("defaultProtons")
    public void setDefaultProtons(java.lang.Double defaultProtons) {
        this.defaultProtons = defaultProtons;
    }

    public Reaction withDefaultProtons(java.lang.Double defaultProtons) {
        this.defaultProtons = defaultProtons;
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

    public Reaction withDeltaG(java.lang.Double deltaG) {
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

    public Reaction withDeltaGErr(java.lang.Double deltaGErr) {
        this.deltaGErr = deltaGErr;
        return this;
    }

    @JsonProperty("abstractReaction_ref")
    public java.lang.String getAbstractReactionRef() {
        return abstractReactionRef;
    }

    @JsonProperty("abstractReaction_ref")
    public void setAbstractReactionRef(java.lang.String abstractReactionRef) {
        this.abstractReactionRef = abstractReactionRef;
    }

    public Reaction withAbstractReactionRef(java.lang.String abstractReactionRef) {
        this.abstractReactionRef = abstractReactionRef;
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

    public Reaction withCues(Map<String, Double> cues) {
        this.cues = cues;
        return this;
    }

    @JsonProperty("reagents")
    public List<Reagent> getReagents() {
        return reagents;
    }

    @JsonProperty("reagents")
    public void setReagents(List<Reagent> reagents) {
        this.reagents = reagents;
    }

    public Reaction withReagents(List<Reagent> reagents) {
        this.reagents = reagents;
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
        return ((((((((((((((((((((((((((((("Reaction"+" [id=")+ id)+", name=")+ name)+", abbreviation=")+ abbreviation)+", md5=")+ md5)+", direction=")+ direction)+", thermoReversibility=")+ thermoReversibility)+", status=")+ status)+", defaultProtons=")+ defaultProtons)+", deltaG=")+ deltaG)+", deltaGErr=")+ deltaGErr)+", abstractReactionRef=")+ abstractReactionRef)+", cues=")+ cues)+", reagents=")+ reagents)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
