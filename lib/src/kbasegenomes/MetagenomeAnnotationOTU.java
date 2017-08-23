
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
 * <p>Original spec-file type: MetagenomeAnnotationOTU</p>
 * <pre>
 * Structure for the "MetagenomeAnnotationOTU" object
 *         string name - name of metagenome OTU
 *         string kbid - KBase ID of OTU of metagenome object
 *         string source_id - ID used for OTU in metagenome source
 *         string source - source OTU ID
 *         list<MetagenomeAnnotationOTUFunction> functions - list of functions in OTU
 *     @searchable ws_subset id name source_id source functions.[*].(id,abundance,confidence,functional_role)
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ave_confidence",
    "ave_coverage",
    "id",
    "name",
    "source_id",
    "source",
    "functions"
})
public class MetagenomeAnnotationOTU {

    @JsonProperty("ave_confidence")
    private Double aveConfidence;
    @JsonProperty("ave_coverage")
    private Double aveCoverage;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("source")
    private String source;
    @JsonProperty("functions")
    private List<MetagenomeAnnotationOTUFunction> functions;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ave_confidence")
    public Double getAveConfidence() {
        return aveConfidence;
    }

    @JsonProperty("ave_confidence")
    public void setAveConfidence(Double aveConfidence) {
        this.aveConfidence = aveConfidence;
    }

    public MetagenomeAnnotationOTU withAveConfidence(Double aveConfidence) {
        this.aveConfidence = aveConfidence;
        return this;
    }

    @JsonProperty("ave_coverage")
    public Double getAveCoverage() {
        return aveCoverage;
    }

    @JsonProperty("ave_coverage")
    public void setAveCoverage(Double aveCoverage) {
        this.aveCoverage = aveCoverage;
    }

    public MetagenomeAnnotationOTU withAveCoverage(Double aveCoverage) {
        this.aveCoverage = aveCoverage;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public MetagenomeAnnotationOTU withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public MetagenomeAnnotationOTU withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("source_id")
    public String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public MetagenomeAnnotationOTU withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    public MetagenomeAnnotationOTU withSource(String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("functions")
    public List<MetagenomeAnnotationOTUFunction> getFunctions() {
        return functions;
    }

    @JsonProperty("functions")
    public void setFunctions(List<MetagenomeAnnotationOTUFunction> functions) {
        this.functions = functions;
    }

    public MetagenomeAnnotationOTU withFunctions(List<MetagenomeAnnotationOTUFunction> functions) {
        this.functions = functions;
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
        return ((((((((((((((((("MetagenomeAnnotationOTU"+" [aveConfidence=")+ aveConfidence)+", aveCoverage=")+ aveCoverage)+", id=")+ id)+", name=")+ name)+", sourceId=")+ sourceId)+", source=")+ source)+", functions=")+ functions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
