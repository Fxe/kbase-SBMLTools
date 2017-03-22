
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
 * <p>Original spec-file type: Handle</p>
 * <pre>
 * A handle for a file stored in Shock.
 * hid - the id of the handle in the Handle Service that references this
 *    shock node
 * id - the id for the shock node
 * url - the url of the shock server
 * type - the type of the handle. This should always be shock.
 * file_name - the name of the file
 * remote_md5 - the md5 digest of the file.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "hid",
    "file_name",
    "id",
    "url",
    "type",
    "remote_md5"
})
public class Handle {

    @JsonProperty("hid")
    private String hid;
    @JsonProperty("file_name")
    private String fileName;
    @JsonProperty("id")
    private String id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("type")
    private String type;
    @JsonProperty("remote_md5")
    private String remoteMd5;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("hid")
    public String getHid() {
        return hid;
    }

    @JsonProperty("hid")
    public void setHid(String hid) {
        this.hid = hid;
    }

    public Handle withHid(String hid) {
        this.hid = hid;
        return this;
    }

    @JsonProperty("file_name")
    public String getFileName() {
        return fileName;
    }

    @JsonProperty("file_name")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Handle withFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Handle withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public Handle withUrl(String url) {
        this.url = url;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Handle withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("remote_md5")
    public String getRemoteMd5() {
        return remoteMd5;
    }

    @JsonProperty("remote_md5")
    public void setRemoteMd5(String remoteMd5) {
        this.remoteMd5 = remoteMd5;
    }

    public Handle withRemoteMd5(String remoteMd5) {
        this.remoteMd5 = remoteMd5;
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
        return ((((((((((((((("Handle"+" [hid=")+ hid)+", fileName=")+ fileName)+", id=")+ id)+", url=")+ url)+", type=")+ type)+", remoteMd5=")+ remoteMd5)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
