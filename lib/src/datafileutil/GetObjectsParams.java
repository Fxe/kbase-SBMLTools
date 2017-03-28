
package datafileutil;

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
 * <p>Original spec-file type: GetObjectsParams</p>
 * <pre>
 * Input parameters for the "get_objects" function.
 *     Required parameters:
 *     object_refs - a list of object references in the form X/Y/Z, where X is
 *         the workspace name or id, Y is the object name or id, and Z is the
 *         (optional) object version. In general, always use ids rather than
 *         names if possible to avoid race conditions.
 *     
 *     Optional parameters:
 *     ignore_errors - ignore any errors that occur when fetching an object
 *         and instead insert a null into the returned list.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "object_refs",
    "ignore_errors"
})
public class GetObjectsParams {

    @JsonProperty("object_refs")
    private List<String> objectRefs;
    @JsonProperty("ignore_errors")
    private Long ignoreErrors;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("object_refs")
    public List<String> getObjectRefs() {
        return objectRefs;
    }

    @JsonProperty("object_refs")
    public void setObjectRefs(List<String> objectRefs) {
        this.objectRefs = objectRefs;
    }

    public GetObjectsParams withObjectRefs(List<String> objectRefs) {
        this.objectRefs = objectRefs;
        return this;
    }

    @JsonProperty("ignore_errors")
    public Long getIgnoreErrors() {
        return ignoreErrors;
    }

    @JsonProperty("ignore_errors")
    public void setIgnoreErrors(Long ignoreErrors) {
        this.ignoreErrors = ignoreErrors;
    }

    public GetObjectsParams withIgnoreErrors(Long ignoreErrors) {
        this.ignoreErrors = ignoreErrors;
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
        return ((((((("GetObjectsParams"+" [objectRefs=")+ objectRefs)+", ignoreErrors=")+ ignoreErrors)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
