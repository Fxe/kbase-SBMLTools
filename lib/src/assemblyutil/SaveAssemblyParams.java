
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
 * Uploader options not yet supported
 *     taxon_reference: The ws reference the assembly points to.  (Optional)
 *     source: The source of the data (Ex: Refseq)
 *     date_string: Date (or date range) associated with data. (Optional)
 *     contig_information_dict: A mapping that has is_circular and description information (Optional)
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
    "assembly_name"
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
    private String shockId;
    @JsonProperty("ftp_url")
    private String ftpUrl;
    @JsonProperty("workspace_name")
    private String workspaceName;
    @JsonProperty("assembly_name")
    private String assemblyName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    public String getShockId() {
        return shockId;
    }

    @JsonProperty("shock_id")
    public void setShockId(String shockId) {
        this.shockId = shockId;
    }

    public SaveAssemblyParams withShockId(String shockId) {
        this.shockId = shockId;
        return this;
    }

    @JsonProperty("ftp_url")
    public String getFtpUrl() {
        return ftpUrl;
    }

    @JsonProperty("ftp_url")
    public void setFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl;
    }

    public SaveAssemblyParams withFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl;
        return this;
    }

    @JsonProperty("workspace_name")
    public String getWorkspaceName() {
        return workspaceName;
    }

    @JsonProperty("workspace_name")
    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public SaveAssemblyParams withWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
        return this;
    }

    @JsonProperty("assembly_name")
    public String getAssemblyName() {
        return assemblyName;
    }

    @JsonProperty("assembly_name")
    public void setAssemblyName(String assemblyName) {
        this.assemblyName = assemblyName;
    }

    public SaveAssemblyParams withAssemblyName(String assemblyName) {
        this.assemblyName = assemblyName;
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
        return ((((((((((((("SaveAssemblyParams"+" [file=")+ file)+", shockId=")+ shockId)+", ftpUrl=")+ ftpUrl)+", workspaceName=")+ workspaceName)+", assemblyName=")+ assemblyName)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
