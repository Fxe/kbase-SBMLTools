
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
 * <p>Original spec-file type: BiomassCompound</p>
 * <pre>
 * BiomassCompound object
 *     @searchable ws_subset modelcompound_ref coefficient
 *     @optional gapfill_data
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "modelcompound_ref",
    "coefficient",
    "gapfill_data"
})
public class BiomassCompound {

    @JsonProperty("modelcompound_ref")
    private java.lang.String modelcompoundRef;
    @JsonProperty("coefficient")
    private Double coefficient;
    @JsonProperty("gapfill_data")
    private Map<String, Long> gapfillData;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("modelcompound_ref")
    public java.lang.String getModelcompoundRef() {
        return modelcompoundRef;
    }

    @JsonProperty("modelcompound_ref")
    public void setModelcompoundRef(java.lang.String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
    }

    public BiomassCompound withModelcompoundRef(java.lang.String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
        return this;
    }

    @JsonProperty("coefficient")
    public Double getCoefficient() {
        return coefficient;
    }

    @JsonProperty("coefficient")
    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public BiomassCompound withCoefficient(Double coefficient) {
        this.coefficient = coefficient;
        return this;
    }

    @JsonProperty("gapfill_data")
    public Map<String, Long> getGapfillData() {
        return gapfillData;
    }

    @JsonProperty("gapfill_data")
    public void setGapfillData(Map<String, Long> gapfillData) {
        this.gapfillData = gapfillData;
    }

    public BiomassCompound withGapfillData(Map<String, Long> gapfillData) {
        this.gapfillData = gapfillData;
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
        return ((((((((("BiomassCompound"+" [modelcompoundRef=")+ modelcompoundRef)+", coefficient=")+ coefficient)+", gapfillData=")+ gapfillData)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
