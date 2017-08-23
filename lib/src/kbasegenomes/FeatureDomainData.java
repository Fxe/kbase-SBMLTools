
package kbasegenomes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple11;


/**
 * <p>Original spec-file type: FeatureDomainData</p>
 * <pre>
 * FeatureDomain - a subobject holding information on how a domain appears in a gene
 * string id - numerical ID assigned by KBase
 * string source_id - assession ID from CDD database;
 * string type - type of CDD, possible values are cd, pfam, smart, COG, PRK, CHL
 * string name - name of CDD
 * string description - description of CDD
 * @optional feature_ref domains
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "feature_id",
    "feature_ref",
    "function",
    "feature_length",
    "domains"
})
public class FeatureDomainData {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("feature_id")
    private java.lang.String featureId;
    @JsonProperty("feature_ref")
    private java.lang.String featureRef;
    @JsonProperty("function")
    private java.lang.String function;
    @JsonProperty("feature_length")
    private java.lang.Long featureLength;
    @JsonProperty("domains")
    private List<Tuple11 <String, Long, Long, Long, Long, Double, Double, Double, Double, Double, Double>> domains;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FeatureDomainData withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("feature_id")
    public java.lang.String getFeatureId() {
        return featureId;
    }

    @JsonProperty("feature_id")
    public void setFeatureId(java.lang.String featureId) {
        this.featureId = featureId;
    }

    public FeatureDomainData withFeatureId(java.lang.String featureId) {
        this.featureId = featureId;
        return this;
    }

    @JsonProperty("feature_ref")
    public java.lang.String getFeatureRef() {
        return featureRef;
    }

    @JsonProperty("feature_ref")
    public void setFeatureRef(java.lang.String featureRef) {
        this.featureRef = featureRef;
    }

    public FeatureDomainData withFeatureRef(java.lang.String featureRef) {
        this.featureRef = featureRef;
        return this;
    }

    @JsonProperty("function")
    public java.lang.String getFunction() {
        return function;
    }

    @JsonProperty("function")
    public void setFunction(java.lang.String function) {
        this.function = function;
    }

    public FeatureDomainData withFunction(java.lang.String function) {
        this.function = function;
        return this;
    }

    @JsonProperty("feature_length")
    public java.lang.Long getFeatureLength() {
        return featureLength;
    }

    @JsonProperty("feature_length")
    public void setFeatureLength(java.lang.Long featureLength) {
        this.featureLength = featureLength;
    }

    public FeatureDomainData withFeatureLength(java.lang.Long featureLength) {
        this.featureLength = featureLength;
        return this;
    }

    @JsonProperty("domains")
    public List<Tuple11 <String, Long, Long, Long, Long, Double, Double, Double, Double, Double, Double>> getDomains() {
        return domains;
    }

    @JsonProperty("domains")
    public void setDomains(List<Tuple11 <String, Long, Long, Long, Long, Double, Double, Double, Double, Double, Double>> domains) {
        this.domains = domains;
    }

    public FeatureDomainData withDomains(List<Tuple11 <String, Long, Long, Long, Long, Double, Double, Double, Double, Double, Double>> domains) {
        this.domains = domains;
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
        return ((((((((((((((("FeatureDomainData"+" [id=")+ id)+", featureId=")+ featureId)+", featureRef=")+ featureRef)+", function=")+ function)+", featureLength=")+ featureLength)+", domains=")+ domains)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
