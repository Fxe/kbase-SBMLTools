
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
 * <p>Original spec-file type: ShockToFileParams</p>
 * <pre>
 * Input for the shock_to_file function.
 * Required parameters:
 * shock_id | handle_id - the ID of the Shock node, or the Handle to a shock node.
 * file_path - the location to save the file output. If this is a
 *     directory, the file will be named as per the filename in Shock.
 * Optional parameters:
 * unpack - either null, 'uncompress', or 'unpack'. 'uncompress' will cause
 *     any bzip or gzip files to be uncompressed. 'unpack' will behave the
 *     same way, but it will also unpack tar and zip archive files
 *     (uncompressing gzipped or bzipped archive files if necessary). If
 *     'uncompress' is specified and an archive file is encountered, an
 *     error will be thrown. If the file is an archive, it will be
 *     unbundled into the directory containing the original output file.
 *     
 *     Note that if the file name (either as provided by the user or by
 *     Shock) without the a decompression extension (e.g. .gz, .zip or
 *     .tgz -> .tar) points to an existing file and unpack is specified,
 *     that file will be overwritten by the decompressed Shock file.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "shock_id",
    "handle_id",
    "file_path",
    "unpack"
})
public class ShockToFileParams {

    @JsonProperty("shock_id")
    private String shockId;
    @JsonProperty("handle_id")
    private String handleId;
    @JsonProperty("file_path")
    private String filePath;
    @JsonProperty("unpack")
    private String unpack;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("shock_id")
    public String getShockId() {
        return shockId;
    }

    @JsonProperty("shock_id")
    public void setShockId(String shockId) {
        this.shockId = shockId;
    }

    public ShockToFileParams withShockId(String shockId) {
        this.shockId = shockId;
        return this;
    }

    @JsonProperty("handle_id")
    public String getHandleId() {
        return handleId;
    }

    @JsonProperty("handle_id")
    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public ShockToFileParams withHandleId(String handleId) {
        this.handleId = handleId;
        return this;
    }

    @JsonProperty("file_path")
    public String getFilePath() {
        return filePath;
    }

    @JsonProperty("file_path")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ShockToFileParams withFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    @JsonProperty("unpack")
    public String getUnpack() {
        return unpack;
    }

    @JsonProperty("unpack")
    public void setUnpack(String unpack) {
        this.unpack = unpack;
    }

    public ShockToFileParams withUnpack(String unpack) {
        this.unpack = unpack;
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
        return ((((((((((("ShockToFileParams"+" [shockId=")+ shockId)+", handleId=")+ handleId)+", filePath=")+ filePath)+", unpack=")+ unpack)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
