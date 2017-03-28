
package datafileutil;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: PackageForDownloadOutput</p>
 * <pre>
 * Output of the package_for_download function.
 *     shock_id - the ID of the new Shock node.
 *     node_file_name - the name of the file stored in Shock.
 *     size - the size of the file stored in shock.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "shock_id",
    "node_file_name",
    "size"
})
public class PackageForDownloadOutput {

    @JsonProperty("shock_id")
    private String shockId;
    @JsonProperty("node_file_name")
    private String nodeFileName;
    @JsonProperty("size")
    private String size;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("shock_id")
    public String getShockId() {
        return shockId;
    }

    @JsonProperty("shock_id")
    public void setShockId(String shockId) {
        this.shockId = shockId;
    }

    public PackageForDownloadOutput withShockId(String shockId) {
        this.shockId = shockId;
        return this;
    }

    @JsonProperty("node_file_name")
    public String getNodeFileName() {
        return nodeFileName;
    }

    @JsonProperty("node_file_name")
    public void setNodeFileName(String nodeFileName) {
        this.nodeFileName = nodeFileName;
    }

    public PackageForDownloadOutput withNodeFileName(String nodeFileName) {
        this.nodeFileName = nodeFileName;
        return this;
    }

    @JsonProperty("size")
    public String getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(String size) {
        this.size = size;
    }

    public PackageForDownloadOutput withSize(String size) {
        this.size = size;
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
        return ((((((((("PackageForDownloadOutput"+" [shockId=")+ shockId)+", nodeFileName=")+ nodeFileName)+", size=")+ size)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
