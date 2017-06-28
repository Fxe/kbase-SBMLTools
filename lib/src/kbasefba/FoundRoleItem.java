
package kbasefba;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: FoundRoleItem</p>
 * <pre>
 * description of a role found in the contigs
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "role_id",
    "role_description"
})
public class FoundRoleItem {

    @JsonProperty("role_id")
    private String roleId;
    @JsonProperty("role_description")
    private String roleDescription;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("role_id")
    public String getRoleId() {
        return roleId;
    }

    @JsonProperty("role_id")
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public FoundRoleItem withRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    @JsonProperty("role_description")
    public String getRoleDescription() {
        return roleDescription;
    }

    @JsonProperty("role_description")
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public FoundRoleItem withRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
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
        return ((((((("FoundRoleItem"+" [roleId=")+ roleId)+", roleDescription=")+ roleDescription)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
