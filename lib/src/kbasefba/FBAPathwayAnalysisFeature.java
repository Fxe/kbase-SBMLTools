
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
 * <p>Original spec-file type: FBAPathwayAnalysisFeature</p>
 * <pre>
 * FBAPathwayAnalysis object: this object holds the analysis of FBA, expression and gapfilling data
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "pegId",
    "expression"
})
public class FBAPathwayAnalysisFeature {

    @JsonProperty("pegId")
    private String pegId;
    @JsonProperty("expression")
    private Double expression;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("pegId")
    public String getPegId() {
        return pegId;
    }

    @JsonProperty("pegId")
    public void setPegId(String pegId) {
        this.pegId = pegId;
    }

    public FBAPathwayAnalysisFeature withPegId(String pegId) {
        this.pegId = pegId;
        return this;
    }

    @JsonProperty("expression")
    public Double getExpression() {
        return expression;
    }

    @JsonProperty("expression")
    public void setExpression(Double expression) {
        this.expression = expression;
    }

    public FBAPathwayAnalysisFeature withExpression(Double expression) {
        this.expression = expression;
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
        return ((((((("FBAPathwayAnalysisFeature"+" [pegId=")+ pegId)+", expression=")+ expression)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
