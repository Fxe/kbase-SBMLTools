
package kbasebiochem;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: MapReactionReactant</p>
 * <pre>
 * MapReactionReactant object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "compound_ref"
})
public class MapReactionReactant {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("compound_ref")
    private String compoundRef;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public MapReactionReactant withId(Long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("compound_ref")
    public String getCompoundRef() {
        return compoundRef;
    }

    @JsonProperty("compound_ref")
    public void setCompoundRef(String compoundRef) {
        this.compoundRef = compoundRef;
    }

    public MapReactionReactant withCompoundRef(String compoundRef) {
        this.compoundRef = compoundRef;
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
        return ((((((("MapReactionReactant"+" [id=")+ id)+", compoundRef=")+ compoundRef)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
