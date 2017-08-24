
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
 * <p>Original spec-file type: Feature_id_filters</p>
 * <pre>
 * *
 * * Filters passed to :meth:`get_feature_ids`
 * * @optional type_list region_list function_list alias_list
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "type_list",
    "region_list",
    "function_list",
    "alias_list"
})
public class FeatureIdFilters {

    @JsonProperty("type_list")
    private List<String> typeList;
    @JsonProperty("region_list")
    private List<Region> regionList;
    @JsonProperty("function_list")
    private List<String> functionList;
    @JsonProperty("alias_list")
    private List<String> aliasList;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("type_list")
    public List<String> getTypeList() {
        return typeList;
    }

    @JsonProperty("type_list")
    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public FeatureIdFilters withTypeList(List<String> typeList) {
        this.typeList = typeList;
        return this;
    }

    @JsonProperty("region_list")
    public List<Region> getRegionList() {
        return regionList;
    }

    @JsonProperty("region_list")
    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }

    public FeatureIdFilters withRegionList(List<Region> regionList) {
        this.regionList = regionList;
        return this;
    }

    @JsonProperty("function_list")
    public List<String> getFunctionList() {
        return functionList;
    }

    @JsonProperty("function_list")
    public void setFunctionList(List<String> functionList) {
        this.functionList = functionList;
    }

    public FeatureIdFilters withFunctionList(List<String> functionList) {
        this.functionList = functionList;
        return this;
    }

    @JsonProperty("alias_list")
    public List<String> getAliasList() {
        return aliasList;
    }

    @JsonProperty("alias_list")
    public void setAliasList(List<String> aliasList) {
        this.aliasList = aliasList;
    }

    public FeatureIdFilters withAliasList(List<String> aliasList) {
        this.aliasList = aliasList;
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
        return ((((((((((("FeatureIdFilters"+" [typeList=")+ typeList)+", regionList=")+ regionList)+", functionList=")+ functionList)+", aliasList=")+ aliasList)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
