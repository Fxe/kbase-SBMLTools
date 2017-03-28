
package datafileutil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.UObject;


/**
 * <p>Original spec-file type: PackageForDownloadParams</p>
 * <pre>
 * Input for the package_for_download function.
 * Required parameters:
 * file_path - the location of the directory to compress as zip archive  
 *     before loading to Shock. This argument will be appended with the
 *     '.zip' file extension prior to writing. If it is a directory, file 
 *     name of the created archive will be set to the directory name 
 *     followed by '.zip', possibly overwriting an existing file. 
 *     Attempting to pack the root directory is an error. Do not attempt
 *     to pack the scratch space root as noted in the module description.
 * ws_ref - list of references to workspace objects which will be used to
 *     produce info-files in JSON format containing workspace metadata and
 *     provenance structures. It produces new files in folder pointed 
 *     by file_path (or folder containing file pointed by file_path if 
 *     it's not folder).
 * Optional parameters:
 * attributes - user-specified attributes to save to the Shock node along
 *     with the file.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "file_path",
    "attributes",
    "ws_refs"
})
public class PackageForDownloadParams {

    @JsonProperty("file_path")
    private java.lang.String filePath;
    @JsonProperty("attributes")
    private Map<String, UObject> attributes;
    @JsonProperty("ws_refs")
    private List<String> wsRefs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("file_path")
    public java.lang.String getFilePath() {
        return filePath;
    }

    @JsonProperty("file_path")
    public void setFilePath(java.lang.String filePath) {
        this.filePath = filePath;
    }

    public PackageForDownloadParams withFilePath(java.lang.String filePath) {
        this.filePath = filePath;
        return this;
    }

    @JsonProperty("attributes")
    public Map<String, UObject> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Map<String, UObject> attributes) {
        this.attributes = attributes;
    }

    public PackageForDownloadParams withAttributes(Map<String, UObject> attributes) {
        this.attributes = attributes;
        return this;
    }

    @JsonProperty("ws_refs")
    public List<String> getWsRefs() {
        return wsRefs;
    }

    @JsonProperty("ws_refs")
    public void setWsRefs(List<String> wsRefs) {
        this.wsRefs = wsRefs;
    }

    public PackageForDownloadParams withWsRefs(List<String> wsRefs) {
        this.wsRefs = wsRefs;
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
        return ((((((((("PackageForDownloadParams"+" [filePath=")+ filePath)+", attributes=")+ attributes)+", wsRefs=")+ wsRefs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
