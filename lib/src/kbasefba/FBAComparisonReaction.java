
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
import us.kbase.common.service.Tuple3;
import us.kbase.common.service.Tuple4;
import us.kbase.common.service.Tuple9;


/**
 * <p>Original spec-file type: FBAComparisonReaction</p>
 * <pre>
 * FBAComparisonReaction object: this object holds information about a reaction across all compared models
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "stoichiometry",
    "direction",
    "state_conservation",
    "most_common_state",
    "reaction_fluxes"
})
public class FBAComparisonReaction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("stoichiometry")
    private List<Tuple3 <Double, String, String>> stoichiometry;
    @JsonProperty("direction")
    private java.lang.String direction;
    @JsonProperty("state_conservation")
    private Map<String, Tuple4 <Long, Double, Double, Double>> stateConservation;
    @JsonProperty("most_common_state")
    private java.lang.String mostCommonState;
    @JsonProperty("reaction_fluxes")
    private Map<String, Tuple9 <String, Double, Double, Double, Double, Double, Double, String, String>> reactionFluxes;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FBAComparisonReaction withId(java.lang.String id) {
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

    public FBAComparisonReaction withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("stoichiometry")
    public List<Tuple3 <Double, String, String>> getStoichiometry() {
        return stoichiometry;
    }

    @JsonProperty("stoichiometry")
    public void setStoichiometry(List<Tuple3 <Double, String, String>> stoichiometry) {
        this.stoichiometry = stoichiometry;
    }

    public FBAComparisonReaction withStoichiometry(List<Tuple3 <Double, String, String>> stoichiometry) {
        this.stoichiometry = stoichiometry;
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

    public FBAComparisonReaction withDirection(java.lang.String direction) {
        this.direction = direction;
        return this;
    }

    @JsonProperty("state_conservation")
    public Map<String, Tuple4 <Long, Double, Double, Double>> getStateConservation() {
        return stateConservation;
    }

    @JsonProperty("state_conservation")
    public void setStateConservation(Map<String, Tuple4 <Long, Double, Double, Double>> stateConservation) {
        this.stateConservation = stateConservation;
    }

    public FBAComparisonReaction withStateConservation(Map<String, Tuple4 <Long, Double, Double, Double>> stateConservation) {
        this.stateConservation = stateConservation;
        return this;
    }

    @JsonProperty("most_common_state")
    public java.lang.String getMostCommonState() {
        return mostCommonState;
    }

    @JsonProperty("most_common_state")
    public void setMostCommonState(java.lang.String mostCommonState) {
        this.mostCommonState = mostCommonState;
    }

    public FBAComparisonReaction withMostCommonState(java.lang.String mostCommonState) {
        this.mostCommonState = mostCommonState;
        return this;
    }

    @JsonProperty("reaction_fluxes")
    public Map<String, Tuple9 <String, Double, Double, Double, Double, Double, Double, String, String>> getReactionFluxes() {
        return reactionFluxes;
    }

    @JsonProperty("reaction_fluxes")
    public void setReactionFluxes(Map<String, Tuple9 <String, Double, Double, Double, Double, Double, Double, String, String>> reactionFluxes) {
        this.reactionFluxes = reactionFluxes;
    }

    public FBAComparisonReaction withReactionFluxes(Map<String, Tuple9 <String, Double, Double, Double, Double, Double, Double, String, String>> reactionFluxes) {
        this.reactionFluxes = reactionFluxes;
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
        return ((((((((((((((((("FBAComparisonReaction"+" [id=")+ id)+", name=")+ name)+", stoichiometry=")+ stoichiometry)+", direction=")+ direction)+", stateConservation=")+ stateConservation)+", mostCommonState=")+ mostCommonState)+", reactionFluxes=")+ reactionFluxes)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
