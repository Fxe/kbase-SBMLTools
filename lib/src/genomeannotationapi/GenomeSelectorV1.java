
package genomeannotationapi;

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
 * <p>Original spec-file type: GenomeSelectorV1</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "included_feature_position_index",
    "ref_path_to_genome"
})
public class GenomeSelectorV1 {

    @JsonProperty("ref")
    private java.lang.String ref;
    @JsonProperty("included_feature_position_index")
    private List<Long> includedFeaturePositionIndex;
    @JsonProperty("ref_path_to_genome")
    private List<String> refPathToGenome;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public GenomeSelectorV1 withRef(java.lang.String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("included_feature_position_index")
    public List<Long> getIncludedFeaturePositionIndex() {
        return includedFeaturePositionIndex;
    }

    @JsonProperty("included_feature_position_index")
    public void setIncludedFeaturePositionIndex(List<Long> includedFeaturePositionIndex) {
        this.includedFeaturePositionIndex = includedFeaturePositionIndex;
    }

    public GenomeSelectorV1 withIncludedFeaturePositionIndex(List<Long> includedFeaturePositionIndex) {
        this.includedFeaturePositionIndex = includedFeaturePositionIndex;
        return this;
    }

    @JsonProperty("ref_path_to_genome")
    public List<String> getRefPathToGenome() {
        return refPathToGenome;
    }

    @JsonProperty("ref_path_to_genome")
    public void setRefPathToGenome(List<String> refPathToGenome) {
        this.refPathToGenome = refPathToGenome;
    }

    public GenomeSelectorV1 withRefPathToGenome(List<String> refPathToGenome) {
        this.refPathToGenome = refPathToGenome;
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
        return ((((((((("GenomeSelectorV1"+" [ref=")+ ref)+", includedFeaturePositionIndex=")+ includedFeaturePositionIndex)+", refPathToGenome=")+ refPathToGenome)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
