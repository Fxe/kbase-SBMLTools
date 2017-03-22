
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
 * <p>Original spec-file type: DownloadWebFileParams</p>
 * <pre>
 * Input parameters for the "download_web_file" function.
 *       Required parameters:
 *       file_url: file URL
 *       download_type: one of ['Direct Download', 'FTP', 'DropBox', 'Google Drive']
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "file_url",
    "download_type"
})
public class DownloadWebFileParams {

    @JsonProperty("file_url")
    private String fileUrl;
    @JsonProperty("download_type")
    private String downloadType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("file_url")
    public String getFileUrl() {
        return fileUrl;
    }

    @JsonProperty("file_url")
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public DownloadWebFileParams withFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    @JsonProperty("download_type")
    public String getDownloadType() {
        return downloadType;
    }

    @JsonProperty("download_type")
    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public DownloadWebFileParams withDownloadType(String downloadType) {
        this.downloadType = downloadType;
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
        return ((((((("DownloadWebFileParams"+" [fileUrl=")+ fileUrl)+", downloadType=")+ downloadType)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
