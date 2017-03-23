
package sbmltools;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: ReadSBMLParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "test_url",
    "workspace_name",
    "random_int"
})
public class ReadSBMLParams {

    @JsonProperty("test_url")
    private String testUrl;
    @JsonProperty("workspace_name")
    private String workspaceName;
    @JsonProperty("random_int")
    private Long randomInt;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("test_url")
    public String getTestUrl() {
        return testUrl;
    }

    @JsonProperty("test_url")
    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    public ReadSBMLParams withTestUrl(String testUrl) {
        this.testUrl = testUrl;
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

    public ReadSBMLParams withWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
        return this;
    }

    @JsonProperty("random_int")
    public Long getRandomInt() {
        return randomInt;
    }

    @JsonProperty("random_int")
    public void setRandomInt(Long randomInt) {
        this.randomInt = randomInt;
    }

    public ReadSBMLParams withRandomInt(Long randomInt) {
        this.randomInt = randomInt;
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
        return ((((((((("ReadSBMLParams"+" [testUrl=")+ testUrl)+", workspaceName=")+ workspaceName)+", randomInt=")+ randomInt)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
