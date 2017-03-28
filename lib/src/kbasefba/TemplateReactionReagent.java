
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
 * <p>Original spec-file type: TemplateReactionReagent</p>
 * <pre>
 * TemplateReactionReagent object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "templatecompcompound_ref",
    "coefficient"
})
public class TemplateReactionReagent {

    @JsonProperty("templatecompcompound_ref")
    private String templatecompcompoundRef;
    @JsonProperty("coefficient")
    private Double coefficient;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("templatecompcompound_ref")
    public String getTemplatecompcompoundRef() {
        return templatecompcompoundRef;
    }

    @JsonProperty("templatecompcompound_ref")
    public void setTemplatecompcompoundRef(String templatecompcompoundRef) {
        this.templatecompcompoundRef = templatecompcompoundRef;
    }

    public TemplateReactionReagent withTemplatecompcompoundRef(String templatecompcompoundRef) {
        this.templatecompcompoundRef = templatecompcompoundRef;
        return this;
    }

    @JsonProperty("coefficient")
    public Double getCoefficient() {
        return coefficient;
    }

    @JsonProperty("coefficient")
    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public TemplateReactionReagent withCoefficient(Double coefficient) {
        this.coefficient = coefficient;
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
        return ((((((("TemplateReactionReagent"+" [templatecompcompoundRef=")+ templatecompcompoundRef)+", coefficient=")+ coefficient)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
