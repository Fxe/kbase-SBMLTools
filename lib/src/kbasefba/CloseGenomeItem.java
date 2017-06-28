
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
 * <p>Original spec-file type: CloseGenomeItem</p>
 * <pre>
 * description of a close genome
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "hit_count",
    "name"
})
public class CloseGenomeItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("hit_count")
    private Long hitCount;
    @JsonProperty("name")
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public CloseGenomeItem withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("hit_count")
    public Long getHitCount() {
        return hitCount;
    }

    @JsonProperty("hit_count")
    public void setHitCount(Long hitCount) {
        this.hitCount = hitCount;
    }

    public CloseGenomeItem withHitCount(Long hitCount) {
        this.hitCount = hitCount;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public CloseGenomeItem withName(String name) {
        this.name = name;
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
        return ((((((((("CloseGenomeItem"+" [id=")+ id)+", hitCount=")+ hitCount)+", name=")+ name)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
