
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
 * <p>Original spec-file type: OntologyData</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "ontology_ref",
    "term_lineage",
    "term_name",
    "evidence"
})
public class OntologyData {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("ontology_ref")
    private java.lang.String ontologyRef;
    @JsonProperty("term_lineage")
    private List<String> termLineage;
    @JsonProperty("term_name")
    private java.lang.String termName;
    @JsonProperty("evidence")
    private List<OntologyEvidence> evidence;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public OntologyData withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("ontology_ref")
    public java.lang.String getOntologyRef() {
        return ontologyRef;
    }

    @JsonProperty("ontology_ref")
    public void setOntologyRef(java.lang.String ontologyRef) {
        this.ontologyRef = ontologyRef;
    }

    public OntologyData withOntologyRef(java.lang.String ontologyRef) {
        this.ontologyRef = ontologyRef;
        return this;
    }

    @JsonProperty("term_lineage")
    public List<String> getTermLineage() {
        return termLineage;
    }

    @JsonProperty("term_lineage")
    public void setTermLineage(List<String> termLineage) {
        this.termLineage = termLineage;
    }

    public OntologyData withTermLineage(List<String> termLineage) {
        this.termLineage = termLineage;
        return this;
    }

    @JsonProperty("term_name")
    public java.lang.String getTermName() {
        return termName;
    }

    @JsonProperty("term_name")
    public void setTermName(java.lang.String termName) {
        this.termName = termName;
    }

    public OntologyData withTermName(java.lang.String termName) {
        this.termName = termName;
        return this;
    }

    @JsonProperty("evidence")
    public List<OntologyEvidence> getEvidence() {
        return evidence;
    }

    @JsonProperty("evidence")
    public void setEvidence(List<OntologyEvidence> evidence) {
        this.evidence = evidence;
    }

    public OntologyData withEvidence(List<OntologyEvidence> evidence) {
        this.evidence = evidence;
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
        return ((((((((((((("OntologyData"+" [id=")+ id)+", ontologyRef=")+ ontologyRef)+", termLineage=")+ termLineage)+", termName=")+ termName)+", evidence=")+ evidence)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
