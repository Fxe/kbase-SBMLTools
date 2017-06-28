
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
import us.kbase.common.service.Tuple3;
import us.kbase.common.service.Tuple4;


/**
 * <p>Original spec-file type: ClassifierResult</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "classifier_ref",
    "workspace_genomes",
    "external_genomes"
})
public class ClassifierResult {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("classifier_ref")
    private java.lang.String classifierRef;
    @JsonProperty("workspace_genomes")
    private List<Tuple3 <String, String, Double>> workspaceGenomes;
    @JsonProperty("external_genomes")
    private List<Tuple4 <String, String, String, Double>> externalGenomes;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ClassifierResult withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("classifier_ref")
    public java.lang.String getClassifierRef() {
        return classifierRef;
    }

    @JsonProperty("classifier_ref")
    public void setClassifierRef(java.lang.String classifierRef) {
        this.classifierRef = classifierRef;
    }

    public ClassifierResult withClassifierRef(java.lang.String classifierRef) {
        this.classifierRef = classifierRef;
        return this;
    }

    @JsonProperty("workspace_genomes")
    public List<Tuple3 <String, String, Double>> getWorkspaceGenomes() {
        return workspaceGenomes;
    }

    @JsonProperty("workspace_genomes")
    public void setWorkspaceGenomes(List<Tuple3 <String, String, Double>> workspaceGenomes) {
        this.workspaceGenomes = workspaceGenomes;
    }

    public ClassifierResult withWorkspaceGenomes(List<Tuple3 <String, String, Double>> workspaceGenomes) {
        this.workspaceGenomes = workspaceGenomes;
        return this;
    }

    @JsonProperty("external_genomes")
    public List<Tuple4 <String, String, String, Double>> getExternalGenomes() {
        return externalGenomes;
    }

    @JsonProperty("external_genomes")
    public void setExternalGenomes(List<Tuple4 <String, String, String, Double>> externalGenomes) {
        this.externalGenomes = externalGenomes;
    }

    public ClassifierResult withExternalGenomes(List<Tuple4 <String, String, String, Double>> externalGenomes) {
        this.externalGenomes = externalGenomes;
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
        return ((((((((((("ClassifierResult"+" [id=")+ id)+", classifierRef=")+ classifierRef)+", workspaceGenomes=")+ workspaceGenomes)+", externalGenomes=")+ externalGenomes)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
