
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
 * <p>Original spec-file type: ExtraContigInfo</p>
 * <pre>
 * Structure for setting additional Contig information per contig
 *     is_circ - flag if contig is circular, 0 is false, 1 is true, missing
 *               indicates unknown
 *     description - if set, sets the description of the field in the assembly object
 *                   which may override what was in the fasta file
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "is_circ",
    "description"
})
public class ExtraContigInfo {

    @JsonProperty("is_circ")
    private Long isCirc;
    @JsonProperty("description")
    private String description;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("is_circ")
    public Long getIsCirc() {
        return isCirc;
    }

    @JsonProperty("is_circ")
    public void setIsCirc(Long isCirc) {
        this.isCirc = isCirc;
    }

    public ExtraContigInfo withIsCirc(Long isCirc) {
        this.isCirc = isCirc;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public ExtraContigInfo withDescription(String description) {
        this.description = description;
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
        return ((((((("ExtraContigInfo"+" [isCirc=")+ isCirc)+", description=")+ description)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
