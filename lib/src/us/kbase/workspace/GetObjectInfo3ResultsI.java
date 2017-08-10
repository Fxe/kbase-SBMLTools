
package us.kbase.workspace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple11;


/**
 * <p>Original spec-file type: GetObjectInfo3Results</p>
 * <pre>
 * Output from the get_object_info3 function.
 *         list<object_info> infos - the object_info data for each object.
 *         list<list<obj_ref> paths - the path to the object through the object reference graph for
 *                 each object. All the references in the path are absolute.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "infos",
    "paths"
})
public class GetObjectInfo3ResultsI {

    @JsonProperty("infos")
    private List<Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> infos;
    @JsonProperty("paths")
    private List<List<String>> paths;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("infos")
    public List<Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> getInfos() {
        return infos;
    }

    @JsonProperty("infos")
    public void setInfos(List<Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> infos) {
        this.infos = infos;
    }

    public GetObjectInfo3ResultsI withInfos(List<Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> infos) {
        this.infos = infos;
        return this;
    }

    @JsonProperty("paths")
    public List<List<String>> getPaths() {
        return paths;
    }

    @JsonProperty("paths")
    public void setPaths(List<List<String>> paths) {
        this.paths = paths;
    }

    public GetObjectInfo3ResultsI withPaths(List<List<String>> paths) {
        this.paths = paths;
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
        return ((((((("GetObjectInfo3Results"+" [infos=")+ infos)+", paths=")+ paths)+", additionalProperties=")+ additionalProperties)+"]");
    }

}