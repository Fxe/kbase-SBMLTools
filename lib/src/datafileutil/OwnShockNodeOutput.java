
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
 * <p>Original spec-file type: OwnShockNodeOutput</p>
 * <pre>
 * Output of the own_shock_node function.
 *  shock_id - the id of the (possibly new) Shock node.
 *  handle - the handle, if requested. Null otherwise.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "shock_id",
    "handle"
})
public class OwnShockNodeOutput {

    @JsonProperty("shock_id")
    private String shockId;
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
    @JsonProperty("handle")
    private Handle handle;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("shock_id")
    public String getShockId() {
        return shockId;
    }

    @JsonProperty("shock_id")
    public void setShockId(String shockId) {
        this.shockId = shockId;
    }

    public OwnShockNodeOutput withShockId(String shockId) {
        this.shockId = shockId;
        return this;
    }

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
    @JsonProperty("handle")
    public Handle getHandle() {
        return handle;
    }

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
    @JsonProperty("handle")
    public void setHandle(Handle handle) {
        this.handle = handle;
    }

    public OwnShockNodeOutput withHandle(Handle handle) {
        this.handle = handle;
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
        return ((((((("OwnShockNodeOutput"+" [shockId=")+ shockId)+", handle=")+ handle)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
