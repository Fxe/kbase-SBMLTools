
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
 * <p>Original spec-file type: CopyShockNodeParams</p>
 * <pre>
 * Input for the copy_shock_node function.
 *        Required parameters:
 *        shock_id - the id of the node to copy.
 *        
 *        Optional parameters:
 *        make_handle - make a Handle Service handle for the shock node. Default
 *            false.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "shock_id",
    "make_handle"
})
public class CopyShockNodeParams {

    @JsonProperty("shock_id")
    private String shockId;
    @JsonProperty("make_handle")
    private Long makeHandle;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("shock_id")
    public String getShockId() {
        return shockId;
    }

    @JsonProperty("shock_id")
    public void setShockId(String shockId) {
        this.shockId = shockId;
    }

    public CopyShockNodeParams withShockId(String shockId) {
        this.shockId = shockId;
        return this;
    }

    @JsonProperty("make_handle")
    public Long getMakeHandle() {
        return makeHandle;
    }

    @JsonProperty("make_handle")
    public void setMakeHandle(Long makeHandle) {
        this.makeHandle = makeHandle;
    }

    public CopyShockNodeParams withMakeHandle(Long makeHandle) {
        this.makeHandle = makeHandle;
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
        return ((((((("CopyShockNodeParams"+" [shockId=")+ shockId)+", makeHandle=")+ makeHandle)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
