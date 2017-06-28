
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
 * <p>Original spec-file type: ModelReactionProteinSubunit</p>
 * <pre>
 * ModelReactionProteinSubunit object
 *     @searchable ws_subset role triggering optionalSubunit feature_refs
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "role",
    "triggering",
    "optionalSubunit",
    "note",
    "feature_refs"
})
public class ModelReactionProteinSubunit {

    @JsonProperty("role")
    private java.lang.String role;
    @JsonProperty("triggering")
    private Long triggering;
    @JsonProperty("optionalSubunit")
    private Long optionalSubunit;
    @JsonProperty("note")
    private java.lang.String note;
    @JsonProperty("feature_refs")
    private List<String> featureRefs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("role")
    public java.lang.String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(java.lang.String role) {
        this.role = role;
    }

    public ModelReactionProteinSubunit withRole(java.lang.String role) {
        this.role = role;
        return this;
    }

    @JsonProperty("triggering")
    public Long getTriggering() {
        return triggering;
    }

    @JsonProperty("triggering")
    public void setTriggering(Long triggering) {
        this.triggering = triggering;
    }

    public ModelReactionProteinSubunit withTriggering(Long triggering) {
        this.triggering = triggering;
        return this;
    }

    @JsonProperty("optionalSubunit")
    public Long getOptionalSubunit() {
        return optionalSubunit;
    }

    @JsonProperty("optionalSubunit")
    public void setOptionalSubunit(Long optionalSubunit) {
        this.optionalSubunit = optionalSubunit;
    }

    public ModelReactionProteinSubunit withOptionalSubunit(Long optionalSubunit) {
        this.optionalSubunit = optionalSubunit;
        return this;
    }

    @JsonProperty("note")
    public java.lang.String getNote() {
        return note;
    }

    @JsonProperty("note")
    public void setNote(java.lang.String note) {
        this.note = note;
    }

    public ModelReactionProteinSubunit withNote(java.lang.String note) {
        this.note = note;
        return this;
    }

    @JsonProperty("feature_refs")
    public List<String> getFeatureRefs() {
        return featureRefs;
    }

    @JsonProperty("feature_refs")
    public void setFeatureRefs(List<String> featureRefs) {
        this.featureRefs = featureRefs;
    }

    public ModelReactionProteinSubunit withFeatureRefs(List<String> featureRefs) {
        this.featureRefs = featureRefs;
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
        return ((((((((((((("ModelReactionProteinSubunit"+" [role=")+ role)+", triggering=")+ triggering)+", optionalSubunit=")+ optionalSubunit)+", note=")+ note)+", featureRefs=")+ featureRefs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
