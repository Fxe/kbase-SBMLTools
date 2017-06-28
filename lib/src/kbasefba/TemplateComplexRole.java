
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
 * <p>Original spec-file type: TemplateComplexRole</p>
 * <pre>
 * TemplateComplexRole object containing data relating to role in complex
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "templaterole_ref",
    "optional_role",
    "triggering"
})
public class TemplateComplexRole {

    @JsonProperty("templaterole_ref")
    private String templateroleRef;
    @JsonProperty("optional_role")
    private Long optionalRole;
    @JsonProperty("triggering")
    private Long triggering;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("templaterole_ref")
    public String getTemplateroleRef() {
        return templateroleRef;
    }

    @JsonProperty("templaterole_ref")
    public void setTemplateroleRef(String templateroleRef) {
        this.templateroleRef = templateroleRef;
    }

    public TemplateComplexRole withTemplateroleRef(String templateroleRef) {
        this.templateroleRef = templateroleRef;
        return this;
    }

    @JsonProperty("optional_role")
    public Long getOptionalRole() {
        return optionalRole;
    }

    @JsonProperty("optional_role")
    public void setOptionalRole(Long optionalRole) {
        this.optionalRole = optionalRole;
    }

    public TemplateComplexRole withOptionalRole(Long optionalRole) {
        this.optionalRole = optionalRole;
        return this;
    }

    @JsonProperty("triggering")
    public Long getTriggering() {
        return triggering;
    }

    @JsonProperty("triggering")
    public void setTriggering(Long triggering) {
        this.triggering = triggering;
    }

    public TemplateComplexRole withTriggering(Long triggering) {
        this.triggering = triggering;
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
        return ((((((((("TemplateComplexRole"+" [templateroleRef=")+ templateroleRef)+", optionalRole=")+ optionalRole)+", triggering=")+ triggering)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
