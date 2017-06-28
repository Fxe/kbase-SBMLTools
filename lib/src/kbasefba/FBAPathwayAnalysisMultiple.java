
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
 * <p>Original spec-file type: FBAPathwayAnalysisMultiple</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "pathwayType",
    "fbaexpression"
})
public class FBAPathwayAnalysisMultiple {

    @JsonProperty("pathwayType")
    private String pathwayType;
    @JsonProperty("fbaexpression")
    private List<FBAPathwayAnalysisPathwayMultiple> fbaexpression;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("pathwayType")
    public String getPathwayType() {
        return pathwayType;
    }

    @JsonProperty("pathwayType")
    public void setPathwayType(String pathwayType) {
        this.pathwayType = pathwayType;
    }

    public FBAPathwayAnalysisMultiple withPathwayType(String pathwayType) {
        this.pathwayType = pathwayType;
        return this;
    }

    @JsonProperty("fbaexpression")
    public List<FBAPathwayAnalysisPathwayMultiple> getFbaexpression() {
        return fbaexpression;
    }

    @JsonProperty("fbaexpression")
    public void setFbaexpression(List<FBAPathwayAnalysisPathwayMultiple> fbaexpression) {
        this.fbaexpression = fbaexpression;
    }

    public FBAPathwayAnalysisMultiple withFbaexpression(List<FBAPathwayAnalysisPathwayMultiple> fbaexpression) {
        this.fbaexpression = fbaexpression;
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
        return ((((((("FBAPathwayAnalysisMultiple"+" [pathwayType=")+ pathwayType)+", fbaexpression=")+ fbaexpression)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
