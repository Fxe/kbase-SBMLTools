
package assemblyutil;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: FastaAssemblyFile</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "path",
    "assembly_name"
})
public class FastaAssemblyFile {

    @JsonProperty("path")
    private String path;
    @JsonProperty("assembly_name")
    private String assemblyName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    public FastaAssemblyFile withPath(String path) {
        this.path = path;
        return this;
    }

    @JsonProperty("assembly_name")
    public String getAssemblyName() {
        return assemblyName;
    }

    @JsonProperty("assembly_name")
    public void setAssemblyName(String assemblyName) {
        this.assemblyName = assemblyName;
    }

    public FastaAssemblyFile withAssemblyName(String assemblyName) {
        this.assemblyName = assemblyName;
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
        return ((((((("FastaAssemblyFile"+" [path=")+ path)+", assemblyName=")+ assemblyName)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
