
package genomeannotationapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kbasegenomes.Genome;
import us.kbase.common.service.Tuple11;
import workspace.ProvenanceAction;


/**
 * <p>Original spec-file type: GenomeDataV1</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "data",
    "info",
    "provenance",
    "creator",
    "orig_wsid",
    "copied",
    "copy_source_inaccessible",
    "created",
    "epoch",
    "refs",
    "extracted_ids",
    "handle_error",
    "handle_stacktrace"
})
public class GenomeDataV1 {

    /**
     * <p>Original spec-file type: Genome</p>
     * <pre>
     * Genome object holds much of the data relevant for a genome in KBase
     *         Genome publications should be papers about the genome, not
     *         papers about certain features of the genome (which go into the
     *         Feature object)
     *         Should the Genome object have a list of feature ids? (in
     *         addition to having a list of feature_refs)
     *         Should the Genome object contain a list of contig_ids too?
     * @optional assembly_ref quality close_genomes analysis_events features source_id source contigs contig_ids publications md5 taxonomy gc_content complete dna_size num_contigs contig_lengths contigset_ref
     * @metadata ws gc_content as GC content
     * @metadata ws taxonomy as Taxonomy
     * @metadata ws md5 as MD5
     * @metadata ws dna_size as Size
     * @metadata ws genetic_code as Genetic code
     * @metadata ws domain as Domain
     *     @metadata ws source_id as Source ID
     *     @metadata ws source as Source
     *     @metadata ws scientific_name as Name
     *     @metadata ws length(close_genomes) as Close genomes
     *     @metadata ws length(features) as Number features
     *     @metadata ws num_contigs as Number contigs
     * </pre>
     * 
     */
    @JsonProperty("data")
    private Genome data;
    @JsonProperty("info")
    private Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> info;
    @JsonProperty("provenance")
    private List<ProvenanceAction> provenance;
    @JsonProperty("creator")
    private java.lang.String creator;
    @JsonProperty("orig_wsid")
    private java.lang.String origWsid;
    @JsonProperty("copied")
    private java.lang.String copied;
    @JsonProperty("copy_source_inaccessible")
    private java.lang.Long copySourceInaccessible;
    @JsonProperty("created")
    private java.lang.String created;
    @JsonProperty("epoch")
    private java.lang.Long epoch;
    @JsonProperty("refs")
    private List<String> refs;
    @JsonProperty("extracted_ids")
    private Map<String, List<String>> extractedIds;
    @JsonProperty("handle_error")
    private java.lang.String handleError;
    @JsonProperty("handle_stacktrace")
    private java.lang.String handleStacktrace;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    /**
     * <p>Original spec-file type: Genome</p>
     * <pre>
     * Genome object holds much of the data relevant for a genome in KBase
     *         Genome publications should be papers about the genome, not
     *         papers about certain features of the genome (which go into the
     *         Feature object)
     *         Should the Genome object have a list of feature ids? (in
     *         addition to having a list of feature_refs)
     *         Should the Genome object contain a list of contig_ids too?
     * @optional assembly_ref quality close_genomes analysis_events features source_id source contigs contig_ids publications md5 taxonomy gc_content complete dna_size num_contigs contig_lengths contigset_ref
     * @metadata ws gc_content as GC content
     * @metadata ws taxonomy as Taxonomy
     * @metadata ws md5 as MD5
     * @metadata ws dna_size as Size
     * @metadata ws genetic_code as Genetic code
     * @metadata ws domain as Domain
     *     @metadata ws source_id as Source ID
     *     @metadata ws source as Source
     *     @metadata ws scientific_name as Name
     *     @metadata ws length(close_genomes) as Close genomes
     *     @metadata ws length(features) as Number features
     *     @metadata ws num_contigs as Number contigs
     * </pre>
     * 
     */
    @JsonProperty("data")
    public Genome getData() {
        return data;
    }

    /**
     * <p>Original spec-file type: Genome</p>
     * <pre>
     * Genome object holds much of the data relevant for a genome in KBase
     *         Genome publications should be papers about the genome, not
     *         papers about certain features of the genome (which go into the
     *         Feature object)
     *         Should the Genome object have a list of feature ids? (in
     *         addition to having a list of feature_refs)
     *         Should the Genome object contain a list of contig_ids too?
     * @optional assembly_ref quality close_genomes analysis_events features source_id source contigs contig_ids publications md5 taxonomy gc_content complete dna_size num_contigs contig_lengths contigset_ref
     * @metadata ws gc_content as GC content
     * @metadata ws taxonomy as Taxonomy
     * @metadata ws md5 as MD5
     * @metadata ws dna_size as Size
     * @metadata ws genetic_code as Genetic code
     * @metadata ws domain as Domain
     *     @metadata ws source_id as Source ID
     *     @metadata ws source as Source
     *     @metadata ws scientific_name as Name
     *     @metadata ws length(close_genomes) as Close genomes
     *     @metadata ws length(features) as Number features
     *     @metadata ws num_contigs as Number contigs
     * </pre>
     * 
     */
    @JsonProperty("data")
    public void setData(Genome data) {
        this.data = data;
    }

    public GenomeDataV1 withData(Genome data) {
        this.data = data;
        return this;
    }

    @JsonProperty("info")
    public Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> getInfo() {
        return info;
    }

    @JsonProperty("info")
    public void setInfo(Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> info) {
        this.info = info;
    }

    public GenomeDataV1 withInfo(Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> info) {
        this.info = info;
        return this;
    }

    @JsonProperty("provenance")
    public List<ProvenanceAction> getProvenance() {
        return provenance;
    }

    @JsonProperty("provenance")
    public void setProvenance(List<ProvenanceAction> provenance) {
        this.provenance = provenance;
    }

    public GenomeDataV1 withProvenance(List<ProvenanceAction> provenance) {
        this.provenance = provenance;
        return this;
    }

    @JsonProperty("creator")
    public java.lang.String getCreator() {
        return creator;
    }

    @JsonProperty("creator")
    public void setCreator(java.lang.String creator) {
        this.creator = creator;
    }

    public GenomeDataV1 withCreator(java.lang.String creator) {
        this.creator = creator;
        return this;
    }

    @JsonProperty("orig_wsid")
    public java.lang.String getOrigWsid() {
        return origWsid;
    }

    @JsonProperty("orig_wsid")
    public void setOrigWsid(java.lang.String origWsid) {
        this.origWsid = origWsid;
    }

    public GenomeDataV1 withOrigWsid(java.lang.String origWsid) {
        this.origWsid = origWsid;
        return this;
    }

    @JsonProperty("copied")
    public java.lang.String getCopied() {
        return copied;
    }

    @JsonProperty("copied")
    public void setCopied(java.lang.String copied) {
        this.copied = copied;
    }

    public GenomeDataV1 withCopied(java.lang.String copied) {
        this.copied = copied;
        return this;
    }

    @JsonProperty("copy_source_inaccessible")
    public java.lang.Long getCopySourceInaccessible() {
        return copySourceInaccessible;
    }

    @JsonProperty("copy_source_inaccessible")
    public void setCopySourceInaccessible(java.lang.Long copySourceInaccessible) {
        this.copySourceInaccessible = copySourceInaccessible;
    }

    public GenomeDataV1 withCopySourceInaccessible(java.lang.Long copySourceInaccessible) {
        this.copySourceInaccessible = copySourceInaccessible;
        return this;
    }

    @JsonProperty("created")
    public java.lang.String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(java.lang.String created) {
        this.created = created;
    }

    public GenomeDataV1 withCreated(java.lang.String created) {
        this.created = created;
        return this;
    }

    @JsonProperty("epoch")
    public java.lang.Long getEpoch() {
        return epoch;
    }

    @JsonProperty("epoch")
    public void setEpoch(java.lang.Long epoch) {
        this.epoch = epoch;
    }

    public GenomeDataV1 withEpoch(java.lang.Long epoch) {
        this.epoch = epoch;
        return this;
    }

    @JsonProperty("refs")
    public List<String> getRefs() {
        return refs;
    }

    @JsonProperty("refs")
    public void setRefs(List<String> refs) {
        this.refs = refs;
    }

    public GenomeDataV1 withRefs(List<String> refs) {
        this.refs = refs;
        return this;
    }

    @JsonProperty("extracted_ids")
    public Map<String, List<String>> getExtractedIds() {
        return extractedIds;
    }

    @JsonProperty("extracted_ids")
    public void setExtractedIds(Map<String, List<String>> extractedIds) {
        this.extractedIds = extractedIds;
    }

    public GenomeDataV1 withExtractedIds(Map<String, List<String>> extractedIds) {
        this.extractedIds = extractedIds;
        return this;
    }

    @JsonProperty("handle_error")
    public java.lang.String getHandleError() {
        return handleError;
    }

    @JsonProperty("handle_error")
    public void setHandleError(java.lang.String handleError) {
        this.handleError = handleError;
    }

    public GenomeDataV1 withHandleError(java.lang.String handleError) {
        this.handleError = handleError;
        return this;
    }

    @JsonProperty("handle_stacktrace")
    public java.lang.String getHandleStacktrace() {
        return handleStacktrace;
    }

    @JsonProperty("handle_stacktrace")
    public void setHandleStacktrace(java.lang.String handleStacktrace) {
        this.handleStacktrace = handleStacktrace;
    }

    public GenomeDataV1 withHandleStacktrace(java.lang.String handleStacktrace) {
        this.handleStacktrace = handleStacktrace;
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
        return ((((((((((((((((((((((((((((("GenomeDataV1"+" [data=")+ data)+", info=")+ info)+", provenance=")+ provenance)+", creator=")+ creator)+", origWsid=")+ origWsid)+", copied=")+ copied)+", copySourceInaccessible=")+ copySourceInaccessible)+", created=")+ created)+", epoch=")+ epoch)+", refs=")+ refs)+", extractedIds=")+ extractedIds)+", handleError=")+ handleError)+", handleStacktrace=")+ handleStacktrace)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
