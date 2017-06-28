
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
 * <p>Original spec-file type: QuantitativeOptimizationSolution</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "atp_synthase",
    "atp_maintenance",
    "QuantOptBiomassMods",
    "QuantOptBoundMods"
})
public class QuantitativeOptimizationSolution {

    @JsonProperty("atp_synthase")
    private Double atpSynthase;
    @JsonProperty("atp_maintenance")
    private Double atpMaintenance;
    @JsonProperty("QuantOptBiomassMods")
    private List<QuantOptBiomassMod> QuantOptBiomassMods;
    @JsonProperty("QuantOptBoundMods")
    private List<QuantOptBoundMod> QuantOptBoundMods;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("atp_synthase")
    public Double getAtpSynthase() {
        return atpSynthase;
    }

    @JsonProperty("atp_synthase")
    public void setAtpSynthase(Double atpSynthase) {
        this.atpSynthase = atpSynthase;
    }

    public QuantitativeOptimizationSolution withAtpSynthase(Double atpSynthase) {
        this.atpSynthase = atpSynthase;
        return this;
    }

    @JsonProperty("atp_maintenance")
    public Double getAtpMaintenance() {
        return atpMaintenance;
    }

    @JsonProperty("atp_maintenance")
    public void setAtpMaintenance(Double atpMaintenance) {
        this.atpMaintenance = atpMaintenance;
    }

    public QuantitativeOptimizationSolution withAtpMaintenance(Double atpMaintenance) {
        this.atpMaintenance = atpMaintenance;
        return this;
    }

    @JsonProperty("QuantOptBiomassMods")
    public List<QuantOptBiomassMod> getQuantOptBiomassMods() {
        return QuantOptBiomassMods;
    }

    @JsonProperty("QuantOptBiomassMods")
    public void setQuantOptBiomassMods(List<QuantOptBiomassMod> QuantOptBiomassMods) {
        this.QuantOptBiomassMods = QuantOptBiomassMods;
    }

    public QuantitativeOptimizationSolution withQuantOptBiomassMods(List<QuantOptBiomassMod> QuantOptBiomassMods) {
        this.QuantOptBiomassMods = QuantOptBiomassMods;
        return this;
    }

    @JsonProperty("QuantOptBoundMods")
    public List<QuantOptBoundMod> getQuantOptBoundMods() {
        return QuantOptBoundMods;
    }

    @JsonProperty("QuantOptBoundMods")
    public void setQuantOptBoundMods(List<QuantOptBoundMod> QuantOptBoundMods) {
        this.QuantOptBoundMods = QuantOptBoundMods;
    }

    public QuantitativeOptimizationSolution withQuantOptBoundMods(List<QuantOptBoundMod> QuantOptBoundMods) {
        this.QuantOptBoundMods = QuantOptBoundMods;
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
        return ((((((((((("QuantitativeOptimizationSolution"+" [atpSynthase=")+ atpSynthase)+", atpMaintenance=")+ atpMaintenance)+", QuantOptBiomassMods=")+ QuantOptBiomassMods)+", QuantOptBoundMods=")+ QuantOptBoundMods)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
