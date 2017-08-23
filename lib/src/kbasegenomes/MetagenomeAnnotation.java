
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
 * <p>Original spec-file type: MetagenomeAnnotation</p>
 * <pre>
 * Structure for the "MetagenomeAnnotation" object
 *         string type - type of metagenome object
 *         string name - name of metagenome object
 *         string kbid - KBase ID of metagenome object
 *         string source_id - ID used in metagenome source
 *         string source - source of metagenome data
 *         string confidence_type - type of confidence score
 *         list<MetagenomeAnnotationOTU> otus - list of otus in metagenome
 *         
 *     @searchable ws_subset type name id source_id source confidence_type otus.[*].(id,name,source_id,source,functions.[*].(id,abundance,confidence,functional_role))
 *         @metadata ws type as Type
 *         @metadata ws name as Name
 *         @metadata ws source_id as Source ID
 *         @metadata ws source as Source
 *         @metadata ws length(otus) as Number OTUs
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "type",
    "name",
    "id",
    "source_id",
    "source",
    "confidence_type",
    "otus"
})
public class MetagenomeAnnotation {

    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("source")
    private String source;
    @JsonProperty("confidence_type")
    private String confidenceType;
    @JsonProperty("otus")
    private List<MetagenomeAnnotationOTU> otus;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public MetagenomeAnnotation withType(String type) {
        this.type = type;
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

    public MetagenomeAnnotation withName(String name) {
        this.name = name;
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

    public MetagenomeAnnotation withId(String id) {
        this.id = id;
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

    public MetagenomeAnnotation withSourceId(String sourceId) {
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

    public MetagenomeAnnotation withSource(String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("confidence_type")
    public String getConfidenceType() {
        return confidenceType;
    }

    @JsonProperty("confidence_type")
    public void setConfidenceType(String confidenceType) {
        this.confidenceType = confidenceType;
    }

    public MetagenomeAnnotation withConfidenceType(String confidenceType) {
        this.confidenceType = confidenceType;
        return this;
    }

    @JsonProperty("otus")
    public List<MetagenomeAnnotationOTU> getOtus() {
        return otus;
    }

    @JsonProperty("otus")
    public void setOtus(List<MetagenomeAnnotationOTU> otus) {
        this.otus = otus;
    }

    public MetagenomeAnnotation withOtus(List<MetagenomeAnnotationOTU> otus) {
        this.otus = otus;
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
        return ((((((((((((((((("MetagenomeAnnotation"+" [type=")+ type)+", name=")+ name)+", id=")+ id)+", sourceId=")+ sourceId)+", source=")+ source)+", confidenceType=")+ confidenceType)+", otus=")+ otus)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
