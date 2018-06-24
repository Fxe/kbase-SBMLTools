
package genomefileutil;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: GenbankToGenomeParams</p>
 * <pre>
 * genome_name - becomes the name of the object
 * workspace_name - the name of the workspace it gets saved to.
 * source - Source of the file typically something like RefSeq or Ensembl
 * taxon_ws_name - where the reference taxons are : ReferenceTaxons
 * taxon_reference - if defined, will try to link the Genome to the specified
 *     taxonomy object insteas of performing the lookup during upload
 * release - Release or version number of the data 
 *       per example Ensembl has numbered releases of all their data: Release 31
 * generate_ids_if_needed - If field used for feature id is not there, 
 *       generate ids (default behavior is raising an exception)
 * genetic_code - Genetic code of organism. Overwrites determined GC from 
 *       taxon object
 * generate_missing_genes - If the file has CDS or mRNA with no corresponding
 *     gene, generate a spoofed gene.
 * use_existing_assembly - Supply an existing assembly reference
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "file",
    "genome_name",
    "workspace_name",
    "source",
    "taxon_wsname",
    "taxon_reference",
    "release",
    "generate_ids_if_needed",
    "genetic_code",
    "metadata",
    "generate_missing_genes",
    "use_existing_assembly"
})
public class GenbankToGenomeParams {

    /**
     * <p>Original spec-file type: File</p>
     * 
     * 
     */
    @JsonProperty("file")
    private File file;
    @JsonProperty("genome_name")
    private java.lang.String genomeName;
    @JsonProperty("workspace_name")
    private java.lang.String workspaceName;
    @JsonProperty("source")
    private java.lang.String source;
    @JsonProperty("taxon_wsname")
    private java.lang.String taxonWsname;
    @JsonProperty("taxon_reference")
    private java.lang.String taxonReference;
    @JsonProperty("release")
    private java.lang.String release;
    @JsonProperty("generate_ids_if_needed")
    private java.lang.String generateIdsIfNeeded;
    @JsonProperty("genetic_code")
    private Long geneticCode;
    @JsonProperty("metadata")
    private Map<String, String> metadata;
    @JsonProperty("generate_missing_genes")
    private Long generateMissingGenes;
    @JsonProperty("use_existing_assembly")
    private java.lang.String useExistingAssembly;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    /**
     * <p>Original spec-file type: File</p>
     * 
     * 
     */
    @JsonProperty("file")
    public File getFile() {
        return file;
    }

    /**
     * <p>Original spec-file type: File</p>
     * 
     * 
     */
    @JsonProperty("file")
    public void setFile(File file) {
        this.file = file;
    }

    public GenbankToGenomeParams withFile(File file) {
        this.file = file;
        return this;
    }

    @JsonProperty("genome_name")
    public java.lang.String getGenomeName() {
        return genomeName;
    }

    @JsonProperty("genome_name")
    public void setGenomeName(java.lang.String genomeName) {
        this.genomeName = genomeName;
    }

    public GenbankToGenomeParams withGenomeName(java.lang.String genomeName) {
        this.genomeName = genomeName;
        return this;
    }

    @JsonProperty("workspace_name")
    public java.lang.String getWorkspaceName() {
        return workspaceName;
    }

    @JsonProperty("workspace_name")
    public void setWorkspaceName(java.lang.String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public GenbankToGenomeParams withWorkspaceName(java.lang.String workspaceName) {
        this.workspaceName = workspaceName;
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

    public GenbankToGenomeParams withSource(java.lang.String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("taxon_wsname")
    public java.lang.String getTaxonWsname() {
        return taxonWsname;
    }

    @JsonProperty("taxon_wsname")
    public void setTaxonWsname(java.lang.String taxonWsname) {
        this.taxonWsname = taxonWsname;
    }

    public GenbankToGenomeParams withTaxonWsname(java.lang.String taxonWsname) {
        this.taxonWsname = taxonWsname;
        return this;
    }

    @JsonProperty("taxon_reference")
    public java.lang.String getTaxonReference() {
        return taxonReference;
    }

    @JsonProperty("taxon_reference")
    public void setTaxonReference(java.lang.String taxonReference) {
        this.taxonReference = taxonReference;
    }

    public GenbankToGenomeParams withTaxonReference(java.lang.String taxonReference) {
        this.taxonReference = taxonReference;
        return this;
    }

    @JsonProperty("release")
    public java.lang.String getRelease() {
        return release;
    }

    @JsonProperty("release")
    public void setRelease(java.lang.String release) {
        this.release = release;
    }

    public GenbankToGenomeParams withRelease(java.lang.String release) {
        this.release = release;
        return this;
    }

    @JsonProperty("generate_ids_if_needed")
    public java.lang.String getGenerateIdsIfNeeded() {
        return generateIdsIfNeeded;
    }

    @JsonProperty("generate_ids_if_needed")
    public void setGenerateIdsIfNeeded(java.lang.String generateIdsIfNeeded) {
        this.generateIdsIfNeeded = generateIdsIfNeeded;
    }

    public GenbankToGenomeParams withGenerateIdsIfNeeded(java.lang.String generateIdsIfNeeded) {
        this.generateIdsIfNeeded = generateIdsIfNeeded;
        return this;
    }

    @JsonProperty("genetic_code")
    public Long getGeneticCode() {
        return geneticCode;
    }

    @JsonProperty("genetic_code")
    public void setGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
    }

    public GenbankToGenomeParams withGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
        return this;
    }

    @JsonProperty("metadata")
    public Map<String, String> getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public GenbankToGenomeParams withMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    @JsonProperty("generate_missing_genes")
    public Long getGenerateMissingGenes() {
        return generateMissingGenes;
    }

    @JsonProperty("generate_missing_genes")
    public void setGenerateMissingGenes(Long generateMissingGenes) {
        this.generateMissingGenes = generateMissingGenes;
    }

    public GenbankToGenomeParams withGenerateMissingGenes(Long generateMissingGenes) {
        this.generateMissingGenes = generateMissingGenes;
        return this;
    }

    @JsonProperty("use_existing_assembly")
    public java.lang.String getUseExistingAssembly() {
        return useExistingAssembly;
    }

    @JsonProperty("use_existing_assembly")
    public void setUseExistingAssembly(java.lang.String useExistingAssembly) {
        this.useExistingAssembly = useExistingAssembly;
    }

    public GenbankToGenomeParams withUseExistingAssembly(java.lang.String useExistingAssembly) {
        this.useExistingAssembly = useExistingAssembly;
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
        return ((((((((((((((((((((((((((("GenbankToGenomeParams"+" [file=")+ file)+", genomeName=")+ genomeName)+", workspaceName=")+ workspaceName)+", source=")+ source)+", taxonWsname=")+ taxonWsname)+", taxonReference=")+ taxonReference)+", release=")+ release)+", generateIdsIfNeeded=")+ generateIdsIfNeeded)+", geneticCode=")+ geneticCode)+", metadata=")+ metadata)+", generateMissingGenes=")+ generateMissingGenes)+", useExistingAssembly=")+ useExistingAssembly)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
