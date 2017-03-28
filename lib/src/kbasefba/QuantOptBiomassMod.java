
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
 * <p>Original spec-file type: QuantOptBiomassMod</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "biomass_component",
    "mod_coefficient"
})
public class QuantOptBiomassMod {

    @JsonProperty("biomass_component")
    private String biomassComponent;
    @JsonProperty("mod_coefficient")
    private Double modCoefficient;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("biomass_component")
    public String getBiomassComponent() {
        return biomassComponent;
    }

    @JsonProperty("biomass_component")
    public void setBiomassComponent(String biomassComponent) {
        this.biomassComponent = biomassComponent;
    }

    public QuantOptBiomassMod withBiomassComponent(String biomassComponent) {
        this.biomassComponent = biomassComponent;
        return this;
    }

    @JsonProperty("mod_coefficient")
    public Double getModCoefficient() {
        return modCoefficient;
    }

    @JsonProperty("mod_coefficient")
    public void setModCoefficient(Double modCoefficient) {
        this.modCoefficient = modCoefficient;
    }

    public QuantOptBiomassMod withModCoefficient(Double modCoefficient) {
        this.modCoefficient = modCoefficient;
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
        return ((((((("QuantOptBiomassMod"+" [biomassComponent=")+ biomassComponent)+", modCoefficient=")+ modCoefficient)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
