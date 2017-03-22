
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
 * <p>Original spec-file type: DownloadStagingFileOutput</p>
 * <pre>
 * Results from the download_staging_file function.
 *       copy_file_path: copied file scratch area path
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "copy_file_path"
})
public class DownloadStagingFileOutput {

    @JsonProperty("copy_file_path")
    private String copyFilePath;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("copy_file_path")
    public String getCopyFilePath() {
        return copyFilePath;
    }

    @JsonProperty("copy_file_path")
    public void setCopyFilePath(String copyFilePath) {
        this.copyFilePath = copyFilePath;
    }

    public DownloadStagingFileOutput withCopyFilePath(String copyFilePath) {
        this.copyFilePath = copyFilePath;
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
        return ((((("DownloadStagingFileOutput"+" [copyFilePath=")+ copyFilePath)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
