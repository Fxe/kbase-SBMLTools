
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
 * <p>Original spec-file type: ProteinSet</p>
 * <pre>
 * Type spec for the "ProteinSet" object
 *                 proteinset_id id - unique kbase ID of the protein set
 *                 string name - name of the protein set
 *                 string type - type of the protein set (values are: Organism,Environment,Collection)
 *                 source_id source_id - source ID of the protein set
 *                 string source - source of the protein set
 *                 list<Protein> proteins - list of proteins in the protein set
 *                 fasta_ref fasta_ref - reference to fasta file from which contig set were read
 *                 @optional name type fasta_ref
 *             @searchable ws_subset proteins.[*].(id,md5,function,length,aliases) md5 id name source_id source type
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "md5",
    "source_id",
    "source",
    "type",
    "fasta_ref",
    "proteins"
})
public class ProteinSet {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("md5")
    private String md5;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("source")
    private String source;
    @JsonProperty("type")
    private String type;
    @JsonProperty("fasta_ref")
    private String fastaRef;
    @JsonProperty("proteins")
    private List<Protein> proteins;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ProteinSet withId(String id) {
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

    public ProteinSet withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("md5")
    public String getMd5() {
        return md5;
    }

    @JsonProperty("md5")
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public ProteinSet withMd5(String md5) {
        this.md5 = md5;
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

    public ProteinSet withSourceId(String sourceId) {
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

    public ProteinSet withSource(String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public ProteinSet withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("fasta_ref")
    public String getFastaRef() {
        return fastaRef;
    }

    @JsonProperty("fasta_ref")
    public void setFastaRef(String fastaRef) {
        this.fastaRef = fastaRef;
    }

    public ProteinSet withFastaRef(String fastaRef) {
        this.fastaRef = fastaRef;
        return this;
    }

    @JsonProperty("proteins")
    public List<Protein> getProteins() {
        return proteins;
    }

    @JsonProperty("proteins")
    public void setProteins(List<Protein> proteins) {
        this.proteins = proteins;
    }

    public ProteinSet withProteins(List<Protein> proteins) {
        this.proteins = proteins;
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
        return ((((((((((((((((((("ProteinSet"+" [id=")+ id)+", name=")+ name)+", md5=")+ md5)+", sourceId=")+ sourceId)+", source=")+ source)+", type=")+ type)+", fastaRef=")+ fastaRef)+", proteins=")+ proteins)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
