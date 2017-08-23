
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
 * <p>Original spec-file type: Pangenome</p>
 * <pre>
 * Pangenome object: this object holds all data regarding a pangenome
 * @searchable ws_subset id name
 *     @metadata ws type as Type
 *     @metadata ws name as Name
 *     @metadata ws length(orthologs) as Number orthologs
 *     @metadata ws length(genome_refs) as Number genomes
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "type",
    "genome_refs",
    "orthologs"
})
public class Pangenome {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("genome_refs")
    private List<String> genomeRefs;
    @JsonProperty("orthologs")
    private List<OrthologFamily> orthologs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Pangenome withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Pangenome withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("type")
    public java.lang.String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(java.lang.String type) {
        this.type = type;
    }

    public Pangenome withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("genome_refs")
    public List<String> getGenomeRefs() {
        return genomeRefs;
    }

    @JsonProperty("genome_refs")
    public void setGenomeRefs(List<String> genomeRefs) {
        this.genomeRefs = genomeRefs;
    }

    public Pangenome withGenomeRefs(List<String> genomeRefs) {
        this.genomeRefs = genomeRefs;
        return this;
    }

    @JsonProperty("orthologs")
    public List<OrthologFamily> getOrthologs() {
        return orthologs;
    }

    @JsonProperty("orthologs")
    public void setOrthologs(List<OrthologFamily> orthologs) {
        this.orthologs = orthologs;
    }

    public Pangenome withOrthologs(List<OrthologFamily> orthologs) {
        this.orthologs = orthologs;
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
        return ((((((((((((("Pangenome"+" [id=")+ id)+", name=")+ name)+", type=")+ type)+", genomeRefs=")+ genomeRefs)+", orthologs=")+ orthologs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
