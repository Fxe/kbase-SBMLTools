
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


/**
 * <p>Original spec-file type: GenomeDomainData</p>
 * <pre>
 * GenomeDomainData object: this object holds all data regarding protein domains in a genome in KBase
 *     @optional genome_ref
 * @searchable ws_subset id genome_id scientific_name genome_ref num_domains num_features
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "genome_id",
    "scientific_name",
    "genome_ref",
    "num_domains",
    "num_features",
    "domains",
    "featuredomains"
})
public class GenomeDomainData {

    @JsonProperty("id")
    private String id;
    @JsonProperty("genome_id")
    private String genomeId;
    @JsonProperty("scientific_name")
    private String scientificName;
    @JsonProperty("genome_ref")
    private String genomeRef;
    @JsonProperty("num_domains")
    private Long numDomains;
    @JsonProperty("num_features")
    private Long numFeatures;
    @JsonProperty("domains")
    private List<Domain> domains;
    @JsonProperty("featuredomains")
    private List<FeatureDomainData> featuredomains;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public GenomeDomainData withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("genome_id")
    public String getGenomeId() {
        return genomeId;
    }

    @JsonProperty("genome_id")
    public void setGenomeId(String genomeId) {
        this.genomeId = genomeId;
    }

    public GenomeDomainData withGenomeId(String genomeId) {
        this.genomeId = genomeId;
        return this;
    }

    @JsonProperty("scientific_name")
    public String getScientificName() {
        return scientificName;
    }

    @JsonProperty("scientific_name")
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public GenomeDomainData withScientificName(String scientificName) {
        this.scientificName = scientificName;
        return this;
    }

    @JsonProperty("genome_ref")
    public String getGenomeRef() {
        return genomeRef;
    }

    @JsonProperty("genome_ref")
    public void setGenomeRef(String genomeRef) {
        this.genomeRef = genomeRef;
    }

    public GenomeDomainData withGenomeRef(String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("num_domains")
    public Long getNumDomains() {
        return numDomains;
    }

    @JsonProperty("num_domains")
    public void setNumDomains(Long numDomains) {
        this.numDomains = numDomains;
    }

    public GenomeDomainData withNumDomains(Long numDomains) {
        this.numDomains = numDomains;
        return this;
    }

    @JsonProperty("num_features")
    public Long getNumFeatures() {
        return numFeatures;
    }

    @JsonProperty("num_features")
    public void setNumFeatures(Long numFeatures) {
        this.numFeatures = numFeatures;
    }

    public GenomeDomainData withNumFeatures(Long numFeatures) {
        this.numFeatures = numFeatures;
        return this;
    }

    @JsonProperty("domains")
    public List<Domain> getDomains() {
        return domains;
    }

    @JsonProperty("domains")
    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public GenomeDomainData withDomains(List<Domain> domains) {
        this.domains = domains;
        return this;
    }

    @JsonProperty("featuredomains")
    public List<FeatureDomainData> getFeaturedomains() {
        return featuredomains;
    }

    @JsonProperty("featuredomains")
    public void setFeaturedomains(List<FeatureDomainData> featuredomains) {
        this.featuredomains = featuredomains;
    }

    public GenomeDomainData withFeaturedomains(List<FeatureDomainData> featuredomains) {
        this.featuredomains = featuredomains;
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
        return ((((((((((((((((((("GenomeDomainData"+" [id=")+ id)+", genomeId=")+ genomeId)+", scientificName=")+ scientificName)+", genomeRef=")+ genomeRef)+", numDomains=")+ numDomains)+", numFeatures=")+ numFeatures)+", domains=")+ domains)+", featuredomains=")+ featuredomains)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
