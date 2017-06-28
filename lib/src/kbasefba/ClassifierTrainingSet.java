
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
import us.kbase.common.service.Tuple2;
import us.kbase.common.service.Tuple3;
import us.kbase.common.service.Tuple4;


/**
 * <p>Original spec-file type: ClassifierTrainingSet</p>
 * <pre>
 * @optional attribute_type source description
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "description",
    "source",
    "attribute_type",
    "workspace_training_set",
    "external_training_set",
    "class_data"
})
public class ClassifierTrainingSet {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("description")
    private java.lang.String description;
    @JsonProperty("source")
    private java.lang.String source;
    @JsonProperty("attribute_type")
    private java.lang.String attributeType;
    @JsonProperty("workspace_training_set")
    private List<Tuple3 <String, String, List<String>>> workspaceTrainingSet;
    @JsonProperty("external_training_set")
    private List<Tuple4 <String, String, String, List<String>>> externalTrainingSet;
    @JsonProperty("class_data")
    private List<Tuple2 <String, String>> classData;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ClassifierTrainingSet withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("description")
    public java.lang.String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public ClassifierTrainingSet withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("source")
    public java.lang.String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(java.lang.String source) {
        this.source = source;
    }

    public ClassifierTrainingSet withSource(java.lang.String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("attribute_type")
    public java.lang.String getAttributeType() {
        return attributeType;
    }

    @JsonProperty("attribute_type")
    public void setAttributeType(java.lang.String attributeType) {
        this.attributeType = attributeType;
    }

    public ClassifierTrainingSet withAttributeType(java.lang.String attributeType) {
        this.attributeType = attributeType;
        return this;
    }

    @JsonProperty("workspace_training_set")
    public List<Tuple3 <String, String, List<String>>> getWorkspaceTrainingSet() {
        return workspaceTrainingSet;
    }

    @JsonProperty("workspace_training_set")
    public void setWorkspaceTrainingSet(List<Tuple3 <String, String, List<String>>> workspaceTrainingSet) {
        this.workspaceTrainingSet = workspaceTrainingSet;
    }

    public ClassifierTrainingSet withWorkspaceTrainingSet(List<Tuple3 <String, String, List<String>>> workspaceTrainingSet) {
        this.workspaceTrainingSet = workspaceTrainingSet;
        return this;
    }

    @JsonProperty("external_training_set")
    public List<Tuple4 <String, String, String, List<String>>> getExternalTrainingSet() {
        return externalTrainingSet;
    }

    @JsonProperty("external_training_set")
    public void setExternalTrainingSet(List<Tuple4 <String, String, String, List<String>>> externalTrainingSet) {
        this.externalTrainingSet = externalTrainingSet;
    }

    public ClassifierTrainingSet withExternalTrainingSet(List<Tuple4 <String, String, String, List<String>>> externalTrainingSet) {
        this.externalTrainingSet = externalTrainingSet;
        return this;
    }

    @JsonProperty("class_data")
    public List<Tuple2 <String, String>> getClassData() {
        return classData;
    }

    @JsonProperty("class_data")
    public void setClassData(List<Tuple2 <String, String>> classData) {
        this.classData = classData;
    }

    public ClassifierTrainingSet withClassData(List<Tuple2 <String, String>> classData) {
        this.classData = classData;
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
        return ((((((((((((((((("ClassifierTrainingSet"+" [id=")+ id)+", description=")+ description)+", source=")+ source)+", attributeType=")+ attributeType)+", workspaceTrainingSet=")+ workspaceTrainingSet)+", externalTrainingSet=")+ externalTrainingSet)+", classData=")+ classData)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
