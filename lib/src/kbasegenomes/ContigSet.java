
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
 * <p>Original spec-file type: ContigSet</p>
 * <pre>
 * Type spec for the "ContigSet" object
 *                 contigset_id id - unique kbase ID of the contig set
 *                 string name - name of the contig set
 *                 string type - type of the contig set (values are: Genome,Transcripts,Environment,Collection)
 *                 source_id source_id - source ID of the contig set
 *                 string source - source of the contig set
 *                 list<Contig> contigs - list of contigs in the contig set
 *                 reads_ref reads_ref - reference to the shocknode with the rawreads from which contigs were assembled
 *                 fasta_ref fasta_ref - reference to fasta file from which contig set were read
 *                 @optional name type reads_ref fasta_ref
 *             @metadata ws type as Type
 *                 @metadata ws source_id as Source ID
 *                 @metadata ws source as Source
 *                 @metadata ws name as Name
 *                 @metadata ws length(contigs) as Number contigs
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
    "reads_ref",
    "fasta_ref",
    "contigs"
})
public class ContigSet {

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
    @JsonProperty("reads_ref")
    private String readsRef;
    @JsonProperty("fasta_ref")
    private String fastaRef;
    @JsonProperty("contigs")
    private List<Contig> contigs;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ContigSet withId(String id) {
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

    public ContigSet withName(String name) {
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

    public ContigSet withMd5(String md5) {
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

    public ContigSet withSourceId(String sourceId) {
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

    public ContigSet withSource(String source) {
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

    public ContigSet withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("reads_ref")
    public String getReadsRef() {
        return readsRef;
    }

    @JsonProperty("reads_ref")
    public void setReadsRef(String readsRef) {
        this.readsRef = readsRef;
    }

    public ContigSet withReadsRef(String readsRef) {
        this.readsRef = readsRef;
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

    public ContigSet withFastaRef(String fastaRef) {
        this.fastaRef = fastaRef;
        return this;
    }

    @JsonProperty("contigs")
    public List<Contig> getContigs() {
        return contigs;
    }

    @JsonProperty("contigs")
    public void setContigs(List<Contig> contigs) {
        this.contigs = contigs;
    }

    public ContigSet withContigs(List<Contig> contigs) {
        this.contigs = contigs;
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
        return ((((((((((((((((((((("ContigSet"+" [id=")+ id)+", name=")+ name)+", md5=")+ md5)+", sourceId=")+ sourceId)+", source=")+ source)+", type=")+ type)+", readsRef=")+ readsRef)+", fastaRef=")+ fastaRef)+", contigs=")+ contigs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
