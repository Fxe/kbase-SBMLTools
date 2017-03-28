
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
import us.kbase.common.service.Tuple2;
import us.kbase.common.service.Tuple3;


/**
 * <p>Original spec-file type: QuantOptSolution</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "integrated",
    "ReactionMaxBounds",
    "UptakeMaxBounds",
    "BiomassChanges",
    "ATPSynthase",
    "ATPMaintenance"
})
public class QuantOptSolution {

    @JsonProperty("integrated")
    private java.lang.Long integrated;
    @JsonProperty("ReactionMaxBounds")
    private List<Tuple3 <String, Double, Long>> ReactionMaxBounds;
    @JsonProperty("UptakeMaxBounds")
    private List<Tuple2 <String, Double>> UptakeMaxBounds;
    @JsonProperty("BiomassChanges")
    private List<Tuple3 <String, String, Double>> BiomassChanges;
    @JsonProperty("ATPSynthase")
    private java.lang.Double ATPSynthase;
    @JsonProperty("ATPMaintenance")
    private java.lang.Double ATPMaintenance;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("integrated")
    public java.lang.Long getIntegrated() {
        return integrated;
    }

    @JsonProperty("integrated")
    public void setIntegrated(java.lang.Long integrated) {
        this.integrated = integrated;
    }

    public QuantOptSolution withIntegrated(java.lang.Long integrated) {
        this.integrated = integrated;
        return this;
    }

    @JsonProperty("ReactionMaxBounds")
    public List<Tuple3 <String, Double, Long>> getReactionMaxBounds() {
        return ReactionMaxBounds;
    }

    @JsonProperty("ReactionMaxBounds")
    public void setReactionMaxBounds(List<Tuple3 <String, Double, Long>> ReactionMaxBounds) {
        this.ReactionMaxBounds = ReactionMaxBounds;
    }

    public QuantOptSolution withReactionMaxBounds(List<Tuple3 <String, Double, Long>> ReactionMaxBounds) {
        this.ReactionMaxBounds = ReactionMaxBounds;
        return this;
    }

    @JsonProperty("UptakeMaxBounds")
    public List<Tuple2 <String, Double>> getUptakeMaxBounds() {
        return UptakeMaxBounds;
    }

    @JsonProperty("UptakeMaxBounds")
    public void setUptakeMaxBounds(List<Tuple2 <String, Double>> UptakeMaxBounds) {
        this.UptakeMaxBounds = UptakeMaxBounds;
    }

    public QuantOptSolution withUptakeMaxBounds(List<Tuple2 <String, Double>> UptakeMaxBounds) {
        this.UptakeMaxBounds = UptakeMaxBounds;
        return this;
    }

    @JsonProperty("BiomassChanges")
    public List<Tuple3 <String, String, Double>> getBiomassChanges() {
        return BiomassChanges;
    }

    @JsonProperty("BiomassChanges")
    public void setBiomassChanges(List<Tuple3 <String, String, Double>> BiomassChanges) {
        this.BiomassChanges = BiomassChanges;
    }

    public QuantOptSolution withBiomassChanges(List<Tuple3 <String, String, Double>> BiomassChanges) {
        this.BiomassChanges = BiomassChanges;
        return this;
    }

    @JsonProperty("ATPSynthase")
    public java.lang.Double getATPSynthase() {
        return ATPSynthase;
    }

    @JsonProperty("ATPSynthase")
    public void setATPSynthase(java.lang.Double ATPSynthase) {
        this.ATPSynthase = ATPSynthase;
    }

    public QuantOptSolution withATPSynthase(java.lang.Double ATPSynthase) {
        this.ATPSynthase = ATPSynthase;
        return this;
    }

    @JsonProperty("ATPMaintenance")
    public java.lang.Double getATPMaintenance() {
        return ATPMaintenance;
    }

    @JsonProperty("ATPMaintenance")
    public void setATPMaintenance(java.lang.Double ATPMaintenance) {
        this.ATPMaintenance = ATPMaintenance;
    }

    public QuantOptSolution withATPMaintenance(java.lang.Double ATPMaintenance) {
        this.ATPMaintenance = ATPMaintenance;
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
        return ((((((((((((((("QuantOptSolution"+" [integrated=")+ integrated)+", ReactionMaxBounds=")+ ReactionMaxBounds)+", UptakeMaxBounds=")+ UptakeMaxBounds)+", BiomassChanges=")+ BiomassChanges)+", ATPSynthase=")+ ATPSynthase)+", ATPMaintenance=")+ ATPMaintenance)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
