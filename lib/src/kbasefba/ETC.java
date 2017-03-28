
package kbasefba;

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
 * <p>Original spec-file type: ETC</p>
 * <pre>
 * ElectronTransportChains (ETC) object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "pathways"
})
public class ETC {

    @JsonProperty("pathways")
    private List<ETCPathwayObj> pathways;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("pathways")
    public List<ETCPathwayObj> getPathways() {
        return pathways;
    }

    @JsonProperty("pathways")
    public void setPathways(List<ETCPathwayObj> pathways) {
        this.pathways = pathways;
    }

    public ETC withPathways(List<ETCPathwayObj> pathways) {
        this.pathways = pathways;
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
        return ((((("ETC"+" [pathways=")+ pathways)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
