
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
 * <p>Original spec-file type: PackFileParams</p>
 * <pre>
 * Input for the pack_file function.
 *        Required parameters:
 *        file_path - the location of the file (or directory if using the
 *            pack parameter) to load to Shock.
 *        pack - The format into which the file or files will be packed.
 *            The file_path argument will be appended with the appropriate file
 *            extension prior to writing. For gzips only, if the file extension
 *            denotes that the file is already compressed, it will be skipped. If
 *            file_path is a directory and tarring or zipping is specified, the
 *            created file name will be set to the directory name, possibly
 *            overwriting an existing file. Attempting to pack the root directory
 *            is an error. Do not attempt to pack the scratch space root as noted
 *            in the module description.
 *            The allowed values are:
 *                gzip - gzip the file given by file_path.
 *                targz - tar and gzip the directory specified by the directory
 *                    portion of the file_path into the file specified by the
 *                    file_path.
 *                zip - as targz but zip the directory.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "file_path",
    "pack"
})
public class PackFileParams {

    @JsonProperty("file_path")
    private String filePath;
    @JsonProperty("pack")
    private String pack;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("file_path")
    public String getFilePath() {
        return filePath;
    }

    @JsonProperty("file_path")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PackFileParams withFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    @JsonProperty("pack")
    public String getPack() {
        return pack;
    }

    @JsonProperty("pack")
    public void setPack(String pack) {
        this.pack = pack;
    }

    public PackFileParams withPack(String pack) {
        this.pack = pack;
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
        return ((((((("PackFileParams"+" [filePath=")+ filePath)+", pack=")+ pack)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
