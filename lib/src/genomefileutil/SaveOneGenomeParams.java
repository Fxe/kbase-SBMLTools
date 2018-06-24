
package genomefileutil;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kbasegenomes.Genome;


/**
 * <p>Original spec-file type: SaveOneGenomeParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "workspace",
    "name",
    "data",
    "hidden",
    "upgrade"
})
public class SaveOneGenomeParams {

    @JsonProperty("workspace")
    private String workspace;
    @JsonProperty("name")
    private String name;
    /**
     * <p>Original spec-file type: Genome</p>
     * <pre>
     * Genome object holds much of the data relevant for a genome in KBase
     *     Genome publications should be papers about the genome
     * Should the Genome object contain a list of contig_ids too?
     * Source: allowed entries RefSeq, Ensembl, Phytozome, RAST, Prokka, User_upload
     *     #allowed entries RefSeq, Ensembl, Phytozome, RAST, Prokka,
     * User_upload controlled vocabulary managed by API
     * Domain is a controlled vocabulary
     * Warnings : mostly controlled vocab but also allow for unstructured
     * Genome_tiers : controlled vocabulary (based on ap input and API checked)
     * Allowed values: #Representative, Reference, ExternalDB, User
     * Examples Tiers:
     * All phytozome - Representative and ExternalDB
     * Phytozome flagship genomes - Reference, Representative and ExternalDB
     * Ensembl - Representative and ExternalDB
     * RefSeq Reference - Reference, Representative and ExternalDB
     * RefSeq Representative - Representative and ExternalDB
     * RefSeq Latest or All Assemblies folder - ExternalDB
     * User Data - User tagged
     * Example Sources:
     * RefSeq, Ensembl, Phytozome, Microcosm, User, RAST, Prokka, (other annotators)
     * @optional warnings contig_lengths contig_ids source_id taxonomy publications
     * @optional ontology_events ontologies_present non_coding_features mrnas
     * @optional genbank_handle_ref gff_handle_ref external_source_origination_date
     * @optional release original_source_file_name notes quality_scores suspect assembly_ref
     * @metadata ws gc_content as GC content
     *     @metadata ws taxonomy as Taxonomy
     *     @metadata ws md5 as MD5
     *     @metadata ws dna_size as Size
     *     @metadata ws genetic_code as Genetic code
     *     @metadata ws domain as Domain
     *     @metadata ws source_id as Source ID
     *     @metadata ws source as Source
     *     @metadata ws scientific_name as Name
     *     @metadata ws length(features) as Number of Protein Encoding Genes
     * @metadata ws length(cdss) as Number of CDS
     *     @metadata ws assembly_ref as Assembly Object
     * @metadata ws num_contigs as Number contigs
     * </pre>
     * 
     */
    @JsonProperty("data")
    private Genome data;
    @JsonProperty("hidden")
    private Long hidden;
    @JsonProperty("upgrade")
    private Long upgrade;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("workspace")
    public String getWorkspace() {
        return workspace;
    }

    @JsonProperty("workspace")
    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public SaveOneGenomeParams withWorkspace(String workspace) {
        this.workspace = workspace;
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

    public SaveOneGenomeParams withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * <p>Original spec-file type: Genome</p>
     * <pre>
     * Genome object holds much of the data relevant for a genome in KBase
     *     Genome publications should be papers about the genome
     * Should the Genome object contain a list of contig_ids too?
     * Source: allowed entries RefSeq, Ensembl, Phytozome, RAST, Prokka, User_upload
     *     #allowed entries RefSeq, Ensembl, Phytozome, RAST, Prokka,
     * User_upload controlled vocabulary managed by API
     * Domain is a controlled vocabulary
     * Warnings : mostly controlled vocab but also allow for unstructured
     * Genome_tiers : controlled vocabulary (based on ap input and API checked)
     * Allowed values: #Representative, Reference, ExternalDB, User
     * Examples Tiers:
     * All phytozome - Representative and ExternalDB
     * Phytozome flagship genomes - Reference, Representative and ExternalDB
     * Ensembl - Representative and ExternalDB
     * RefSeq Reference - Reference, Representative and ExternalDB
     * RefSeq Representative - Representative and ExternalDB
     * RefSeq Latest or All Assemblies folder - ExternalDB
     * User Data - User tagged
     * Example Sources:
     * RefSeq, Ensembl, Phytozome, Microcosm, User, RAST, Prokka, (other annotators)
     * @optional warnings contig_lengths contig_ids source_id taxonomy publications
     * @optional ontology_events ontologies_present non_coding_features mrnas
     * @optional genbank_handle_ref gff_handle_ref external_source_origination_date
     * @optional release original_source_file_name notes quality_scores suspect assembly_ref
     * @metadata ws gc_content as GC content
     *     @metadata ws taxonomy as Taxonomy
     *     @metadata ws md5 as MD5
     *     @metadata ws dna_size as Size
     *     @metadata ws genetic_code as Genetic code
     *     @metadata ws domain as Domain
     *     @metadata ws source_id as Source ID
     *     @metadata ws source as Source
     *     @metadata ws scientific_name as Name
     *     @metadata ws length(features) as Number of Protein Encoding Genes
     * @metadata ws length(cdss) as Number of CDS
     *     @metadata ws assembly_ref as Assembly Object
     * @metadata ws num_contigs as Number contigs
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
     *     Genome publications should be papers about the genome
     * Should the Genome object contain a list of contig_ids too?
     * Source: allowed entries RefSeq, Ensembl, Phytozome, RAST, Prokka, User_upload
     *     #allowed entries RefSeq, Ensembl, Phytozome, RAST, Prokka,
     * User_upload controlled vocabulary managed by API
     * Domain is a controlled vocabulary
     * Warnings : mostly controlled vocab but also allow for unstructured
     * Genome_tiers : controlled vocabulary (based on ap input and API checked)
     * Allowed values: #Representative, Reference, ExternalDB, User
     * Examples Tiers:
     * All phytozome - Representative and ExternalDB
     * Phytozome flagship genomes - Reference, Representative and ExternalDB
     * Ensembl - Representative and ExternalDB
     * RefSeq Reference - Reference, Representative and ExternalDB
     * RefSeq Representative - Representative and ExternalDB
     * RefSeq Latest or All Assemblies folder - ExternalDB
     * User Data - User tagged
     * Example Sources:
     * RefSeq, Ensembl, Phytozome, Microcosm, User, RAST, Prokka, (other annotators)
     * @optional warnings contig_lengths contig_ids source_id taxonomy publications
     * @optional ontology_events ontologies_present non_coding_features mrnas
     * @optional genbank_handle_ref gff_handle_ref external_source_origination_date
     * @optional release original_source_file_name notes quality_scores suspect assembly_ref
     * @metadata ws gc_content as GC content
     *     @metadata ws taxonomy as Taxonomy
     *     @metadata ws md5 as MD5
     *     @metadata ws dna_size as Size
     *     @metadata ws genetic_code as Genetic code
     *     @metadata ws domain as Domain
     *     @metadata ws source_id as Source ID
     *     @metadata ws source as Source
     *     @metadata ws scientific_name as Name
     *     @metadata ws length(features) as Number of Protein Encoding Genes
     * @metadata ws length(cdss) as Number of CDS
     *     @metadata ws assembly_ref as Assembly Object
     * @metadata ws num_contigs as Number contigs
     * </pre>
     * 
     */
    @JsonProperty("data")
    public void setData(Genome data) {
        this.data = data;
    }

    public SaveOneGenomeParams withData(Genome data) {
        this.data = data;
        return this;
    }

    @JsonProperty("hidden")
    public Long getHidden() {
        return hidden;
    }

    @JsonProperty("hidden")
    public void setHidden(Long hidden) {
        this.hidden = hidden;
    }

    public SaveOneGenomeParams withHidden(Long hidden) {
        this.hidden = hidden;
        return this;
    }

    @JsonProperty("upgrade")
    public Long getUpgrade() {
        return upgrade;
    }

    @JsonProperty("upgrade")
    public void setUpgrade(Long upgrade) {
        this.upgrade = upgrade;
    }

    public SaveOneGenomeParams withUpgrade(Long upgrade) {
        this.upgrade = upgrade;
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
        return ((((((((((((("SaveOneGenomeParams"+" [workspace=")+ workspace)+", name=")+ name)+", data=")+ data)+", hidden=")+ hidden)+", upgrade=")+ upgrade)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
