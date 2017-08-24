
package genomeannotationapi;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: inputs_get_feature_ids</p>
 * <pre>
 * @optional filters group_by
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "filters",
    "group_by"
})
public class InputsGetFeatureIds {

    @JsonProperty("ref")
    private String ref;
    /**
     * <p>Original spec-file type: Feature_id_filters</p>
     * <pre>
     * *
     * * Filters passed to :meth:`get_feature_ids`
     * * @optional type_list region_list function_list alias_list
     * </pre>
     * 
     */
    @JsonProperty("filters")
    private FeatureIdFilters filters;
    @JsonProperty("group_by")
    private String groupBy;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ref")
    public String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(String ref) {
        this.ref = ref;
    }

    public InputsGetFeatureIds withRef(String ref) {
        this.ref = ref;
        return this;
    }

    /**
     * <p>Original spec-file type: Feature_id_filters</p>
     * <pre>
     * *
     * * Filters passed to :meth:`get_feature_ids`
     * * @optional type_list region_list function_list alias_list
     * </pre>
     * 
     */
    @JsonProperty("filters")
    public FeatureIdFilters getFilters() {
        return filters;
    }

    /**
     * <p>Original spec-file type: Feature_id_filters</p>
     * <pre>
     * *
     * * Filters passed to :meth:`get_feature_ids`
     * * @optional type_list region_list function_list alias_list
     * </pre>
     * 
     */
    @JsonProperty("filters")
    public void setFilters(FeatureIdFilters filters) {
        this.filters = filters;
    }

    public InputsGetFeatureIds withFilters(FeatureIdFilters filters) {
        this.filters = filters;
        return this;
    }

    @JsonProperty("group_by")
    public String getGroupBy() {
        return groupBy;
    }

    @JsonProperty("group_by")
    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public InputsGetFeatureIds withGroupBy(String groupBy) {
        this.groupBy = groupBy;
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
        return ((((((((("InputsGetFeatureIds"+" [ref=")+ ref)+", filters=")+ filters)+", groupBy=")+ groupBy)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
