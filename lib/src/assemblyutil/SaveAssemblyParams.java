
package assemblyutil;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: SaveAssemblyParams</p>
 * <pre>
 * Options supported:
 *     file / shock_id / ftp_url - mutualy exclusive parameters pointing to file content
 *     workspace_name - target workspace
 *     assembly_name - target object name
 *     type - should be one of 'isolate', 'metagenome', (maybe 'transcriptome')
 *     min_contig_length - if set and value is greater than 1, this will only include sequences
 *                         with length greater or equal to the min_contig_length specified, discarding
 *                         all other sequences
 *     taxon_ref         - sets the taxon_ref if present
 *     contig_info       - map from contig_id to a small structure that can be used to set the is_circular
 *                         and description fields for Assemblies (optional)
 * Uploader options not yet supported
 *     taxon_reference: The ws reference the assembly points to.  (Optional)
 *     source: The source of the data (Ex: Refseq)
 *     date_string: Date (or date range) associated with data. (Optional)
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "file",
    "shock_id",
    "ftp_url",
    "workspace_name",
    "assembly_name",
    "external_source",
    "external_source_id",
    "taxon_ref",
    "min_contig_length",
    "contig_info"
})
public class SaveAssemblyParams {

    /**
     * <p>Original spec-file type: FastaAssemblyFile</p>
     * 
     * 
     */
    @JsonProperty("file")
    private FastaAssemblyFile file;
    @JsonProperty("shock_id")
    private java.lang.String shockId;
    @JsonProperty("ftp_url")
    private java.lang.String ftpUrl;
    @JsonProperty("workspace_name")
    private java.lang.String workspaceName;
    @JsonProperty("assembly_name")
    private java.lang.String assemblyName;
    @JsonProperty("external_source")
    private java.lang.String externalSource;
    @JsonProperty("external_source_id")
    private java.lang.String externalSourceId;
    @JsonProperty("taxon_ref")
    private java.lang.String taxonRef;
    @JsonProperty("min_contig_length")
    private Long minContigLength;
    @JsonProperty("contig_info")
    private Map<String, ExtraContigInfo> contigInfo;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    /**
     * <p>Original spec-file type: FastaAssemblyFile</p>
     * 
     * 
     */
    @JsonProperty("file")
    public FastaAssemblyFile getFile() {
        return file;
    }

    /**
     * <p>Original spec-file type: FastaAssemblyFile</p>
     * 
     * 
     */
    @JsonProperty("file")
    public void setFile(FastaAssemblyFile file) {
        this.file = file;
    }

    public SaveAssemblyParams withFile(FastaAssemblyFile file) {
        this.file = file;
        return this;
    }

    @JsonProperty("shock_id")
    public java.lang.String getShockId() {
        return shockId;
    }

    @JsonProperty("shock_id")
    public void setShockId(java.lang.String shockId) {
        this.shockId = shockId;
    }

    public SaveAssemblyParams withShockId(java.lang.String shockId) {
        this.shockId = shockId;
        return this;
    }

    @JsonProperty("ftp_url")
    public java.lang.String getFtpUrl() {
        return ftpUrl;
    }

    @JsonProperty("ftp_url")
    public void setFtpUrl(java.lang.String ftpUrl) {
        this.ftpUrl = ftpUrl;
    }

    public SaveAssemblyParams withFtpUrl(java.lang.String ftpUrl) {
        this.ftpUrl = ftpUrl;
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

    public SaveAssemblyParams withWorkspaceName(java.lang.String workspaceName) {
        this.workspaceName = workspaceName;
        return this;
    }

    @JsonProperty("assembly_name")
    public java.lang.String getAssemblyName() {
        return assemblyName;
    }

    @JsonProperty("assembly_name")
    public void setAssemblyName(java.lang.String assemblyName) {
        this.assemblyName = assemblyName;
    }

    public SaveAssemblyParams withAssemblyName(java.lang.String assemblyName) {
        this.assemblyName = assemblyName;
        return this;
    }

    @JsonProperty("external_source")
    public java.lang.String getExternalSource() {
        return externalSource;
    }

    @JsonProperty("external_source")
    public void setExternalSource(java.lang.String externalSource) {
        this.externalSource = externalSource;
    }

    public SaveAssemblyParams withExternalSource(java.lang.String externalSource) {
        this.externalSource = externalSource;
        return this;
    }

    @JsonProperty("external_source_id")
    public java.lang.String getExternalSourceId() {
        return externalSourceId;
    }

    @JsonProperty("external_source_id")
    public void setExternalSourceId(java.lang.String externalSourceId) {
        this.externalSourceId = externalSourceId;
    }

    public SaveAssemblyParams withExternalSourceId(java.lang.String externalSourceId) {
        this.externalSourceId = externalSourceId;
        return this;
    }

    @JsonProperty("taxon_ref")
    public java.lang.String getTaxonRef() {
        return taxonRef;
    }

    @JsonProperty("taxon_ref")
    public void setTaxonRef(java.lang.String taxonRef) {
        this.taxonRef = taxonRef;
    }

    public SaveAssemblyParams withTaxonRef(java.lang.String taxonRef) {
        this.taxonRef = taxonRef;
        return this;
    }

    @JsonProperty("min_contig_length")
    public Long getMinContigLength() {
        return minContigLength;
    }

    @JsonProperty("min_contig_length")
    public void setMinContigLength(Long minContigLength) {
        this.minContigLength = minContigLength;
    }

    public SaveAssemblyParams withMinContigLength(Long minContigLength) {
        this.minContigLength = minContigLength;
        return this;
    }

    @JsonProperty("contig_info")
    public Map<String, ExtraContigInfo> getContigInfo() {
        return contigInfo;
    }

    @JsonProperty("contig_info")
    public void setContigInfo(Map<String, ExtraContigInfo> contigInfo) {
        this.contigInfo = contigInfo;
    }

    public SaveAssemblyParams withContigInfo(Map<String, ExtraContigInfo> contigInfo) {
        this.contigInfo = contigInfo;
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
        return ((((((((((((((((((((((("SaveAssemblyParams"+" [file=")+ file)+", shockId=")+ shockId)+", ftpUrl=")+ ftpUrl)+", workspaceName=")+ workspaceName)+", assemblyName=")+ assemblyName)+", externalSource=")+ externalSource)+", externalSourceId=")+ externalSourceId)+", taxonRef=")+ taxonRef)+", minContigLength=")+ minContigLength)+", contigInfo=")+ contigInfo)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
