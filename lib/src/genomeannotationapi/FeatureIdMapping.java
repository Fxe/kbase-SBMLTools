
package genomeannotationapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: Feature_id_mapping</p>
 * <pre>
 * @optional by_type by_region by_function by_alias
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "by_type",
    "by_region",
    "by_function",
    "by_alias"
})
public class FeatureIdMapping {

    @JsonProperty("by_type")
    private Map<String, List<String>> byType;
    @JsonProperty("by_region")
    private Map<String, Map<String, Map<String, List<String>>>> byRegion;
    @JsonProperty("by_function")
    private Map<String, List<String>> byFunction;
    @JsonProperty("by_alias")
    private Map<String, List<String>> byAlias;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("by_type")
    public Map<String, List<String>> getByType() {
        return byType;
    }

    @JsonProperty("by_type")
    public void setByType(Map<String, List<String>> byType) {
        this.byType = byType;
    }

    public FeatureIdMapping withByType(Map<String, List<String>> byType) {
        this.byType = byType;
        return this;
    }

    @JsonProperty("by_region")
    public Map<String, Map<String, Map<String, List<String>>>> getByRegion() {
        return byRegion;
    }

    @JsonProperty("by_region")
    public void setByRegion(Map<String, Map<String, Map<String, List<String>>>> byRegion) {
        this.byRegion = byRegion;
    }

    public FeatureIdMapping withByRegion(Map<String, Map<String, Map<String, List<String>>>> byRegion) {
        this.byRegion = byRegion;
        return this;
    }

    @JsonProperty("by_function")
    public Map<String, List<String>> getByFunction() {
        return byFunction;
    }

    @JsonProperty("by_function")
    public void setByFunction(Map<String, List<String>> byFunction) {
        this.byFunction = byFunction;
    }

    public FeatureIdMapping withByFunction(Map<String, List<String>> byFunction) {
        this.byFunction = byFunction;
        return this;
    }

    @JsonProperty("by_alias")
    public Map<String, List<String>> getByAlias() {
        return byAlias;
    }

    @JsonProperty("by_alias")
    public void setByAlias(Map<String, List<String>> byAlias) {
        this.byAlias = byAlias;
    }

    public FeatureIdMapping withByAlias(Map<String, List<String>> byAlias) {
        this.byAlias = byAlias;
        return this;
    }

    @JsonAnyGetter
    public Map<java.lang.String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(java.lang.String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public java.lang.String toString() {
        return ((((((((((("FeatureIdMapping"+" [byType=")+ byType)+", byRegion=")+ byRegion)+", byFunction=")+ byFunction)+", byAlias=")+ byAlias)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
