
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
 * <p>Original spec-file type: FBAComparison</p>
 * <pre>
 * FBAComparison object: this object holds information about a comparison of multiple FBA simulations
 * @metadata ws id as ID
 * @metadata ws common_reactions as Common reactions
 * @metadata ws common_compounds as Common compounds
 * @metadata ws length(fbas) as Number FBAs
 * @metadata ws length(reactions) as Number reactions
 * @metadata ws length(compounds) as Number compounds
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "common_reactions",
    "common_compounds",
    "fbas",
    "reactions",
    "compounds"
})
public class FBAComparison {

    @JsonProperty("id")
    private String id;
    @JsonProperty("common_reactions")
    private Long commonReactions;
    @JsonProperty("common_compounds")
    private Long commonCompounds;
    @JsonProperty("fbas")
    private List<FBAComparisonFBA> fbas;
    @JsonProperty("reactions")
    private List<FBAComparisonReaction> reactions;
    @JsonProperty("compounds")
    private List<FBAComparisonCompound> compounds;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FBAComparison withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("common_reactions")
    public Long getCommonReactions() {
        return commonReactions;
    }

    @JsonProperty("common_reactions")
    public void setCommonReactions(Long commonReactions) {
        this.commonReactions = commonReactions;
    }

    public FBAComparison withCommonReactions(Long commonReactions) {
        this.commonReactions = commonReactions;
        return this;
    }

    @JsonProperty("common_compounds")
    public Long getCommonCompounds() {
        return commonCompounds;
    }

    @JsonProperty("common_compounds")
    public void setCommonCompounds(Long commonCompounds) {
        this.commonCompounds = commonCompounds;
    }

    public FBAComparison withCommonCompounds(Long commonCompounds) {
        this.commonCompounds = commonCompounds;
        return this;
    }

    @JsonProperty("fbas")
    public List<FBAComparisonFBA> getFbas() {
        return fbas;
    }

    @JsonProperty("fbas")
    public void setFbas(List<FBAComparisonFBA> fbas) {
        this.fbas = fbas;
    }

    public FBAComparison withFbas(List<FBAComparisonFBA> fbas) {
        this.fbas = fbas;
        return this;
    }

    @JsonProperty("reactions")
    public List<FBAComparisonReaction> getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(List<FBAComparisonReaction> reactions) {
        this.reactions = reactions;
    }

    public FBAComparison withReactions(List<FBAComparisonReaction> reactions) {
        this.reactions = reactions;
        return this;
    }

    @JsonProperty("compounds")
    public List<FBAComparisonCompound> getCompounds() {
        return compounds;
    }

    @JsonProperty("compounds")
    public void setCompounds(List<FBAComparisonCompound> compounds) {
        this.compounds = compounds;
    }

    public FBAComparison withCompounds(List<FBAComparisonCompound> compounds) {
        this.compounds = compounds;
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
        return ((((((((((((((("FBAComparison"+" [id=")+ id)+", commonReactions=")+ commonReactions)+", commonCompounds=")+ commonCompounds)+", fbas=")+ fbas)+", reactions=")+ reactions)+", compounds=")+ compounds)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
