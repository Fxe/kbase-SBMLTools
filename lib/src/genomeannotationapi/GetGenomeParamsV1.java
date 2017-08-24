
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
 * <p>Original spec-file type: GetGenomeParamsV1</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "genomes",
    "included_fields",
    "included_feature_fields",
    "ignore_errors",
    "no_data",
    "no_metadata"
})
public class GetGenomeParamsV1 {

    @JsonProperty("genomes")
    private List<GenomeSelectorV1> genomes;
    @JsonProperty("included_fields")
    private List<String> includedFields;
    @JsonProperty("included_feature_fields")
    private List<String> includedFeatureFields;
    @JsonProperty("ignore_errors")
    private Long ignoreErrors;
    @JsonProperty("no_data")
    private Long noData;
    @JsonProperty("no_metadata")
    private Long noMetadata;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("genomes")
    public List<GenomeSelectorV1> getGenomes() {
        return genomes;
    }

    @JsonProperty("genomes")
    public void setGenomes(List<GenomeSelectorV1> genomes) {
        this.genomes = genomes;
    }

    public GetGenomeParamsV1 withGenomes(List<GenomeSelectorV1> genomes) {
        this.genomes = genomes;
        return this;
    }

    @JsonProperty("included_fields")
    public List<String> getIncludedFields() {
        return includedFields;
    }

    @JsonProperty("included_fields")
    public void setIncludedFields(List<String> includedFields) {
        this.includedFields = includedFields;
    }

    public GetGenomeParamsV1 withIncludedFields(List<String> includedFields) {
        this.includedFields = includedFields;
        return this;
    }

    @JsonProperty("included_feature_fields")
    public List<String> getIncludedFeatureFields() {
        return includedFeatureFields;
    }

    @JsonProperty("included_feature_fields")
    public void setIncludedFeatureFields(List<String> includedFeatureFields) {
        this.includedFeatureFields = includedFeatureFields;
    }

    public GetGenomeParamsV1 withIncludedFeatureFields(List<String> includedFeatureFields) {
        this.includedFeatureFields = includedFeatureFields;
        return this;
    }

    @JsonProperty("ignore_errors")
    public Long getIgnoreErrors() {
        return ignoreErrors;
    }

    @JsonProperty("ignore_errors")
    public void setIgnoreErrors(Long ignoreErrors) {
        this.ignoreErrors = ignoreErrors;
    }

    public GetGenomeParamsV1 withIgnoreErrors(Long ignoreErrors) {
        this.ignoreErrors = ignoreErrors;
        return this;
    }

    @JsonProperty("no_data")
    public Long getNoData() {
        return noData;
    }

    @JsonProperty("no_data")
    public void setNoData(Long noData) {
        this.noData = noData;
    }

    public GetGenomeParamsV1 withNoData(Long noData) {
        this.noData = noData;
        return this;
    }

    @JsonProperty("no_metadata")
    public Long getNoMetadata() {
        return noMetadata;
    }

    @JsonProperty("no_metadata")
    public void setNoMetadata(Long noMetadata) {
        this.noMetadata = noMetadata;
    }

    public GetGenomeParamsV1 withNoMetadata(Long noMetadata) {
        this.noMetadata = noMetadata;
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
        return ((((((((((((((("GetGenomeParamsV1"+" [genomes=")+ genomes)+", includedFields=")+ includedFields)+", includedFeatureFields=")+ includedFeatureFields)+", ignoreErrors=")+ ignoreErrors)+", noData=")+ noData)+", noMetadata=")+ noMetadata)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
