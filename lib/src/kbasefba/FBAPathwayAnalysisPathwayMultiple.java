
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
 * <p>Original spec-file type: FBAPathwayAnalysisPathwayMultiple</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "expression_matrix_ref",
    "expression_condition",
    "fbamodel_ref",
    "fba_ref",
    "count_list"
})
public class FBAPathwayAnalysisPathwayMultiple {

    @JsonProperty("expression_matrix_ref")
    private String expressionMatrixRef;
    @JsonProperty("expression_condition")
    private String expressionCondition;
    @JsonProperty("fbamodel_ref")
    private String fbamodelRef;
    @JsonProperty("fba_ref")
    private String fbaRef;
    @JsonProperty("count_list")
    private List<FBAPathwayAnalysisCounts> countList;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("expression_matrix_ref")
    public String getExpressionMatrixRef() {
        return expressionMatrixRef;
    }

    @JsonProperty("expression_matrix_ref")
    public void setExpressionMatrixRef(String expressionMatrixRef) {
        this.expressionMatrixRef = expressionMatrixRef;
    }

    public FBAPathwayAnalysisPathwayMultiple withExpressionMatrixRef(String expressionMatrixRef) {
        this.expressionMatrixRef = expressionMatrixRef;
        return this;
    }

    @JsonProperty("expression_condition")
    public String getExpressionCondition() {
        return expressionCondition;
    }

    @JsonProperty("expression_condition")
    public void setExpressionCondition(String expressionCondition) {
        this.expressionCondition = expressionCondition;
    }

    public FBAPathwayAnalysisPathwayMultiple withExpressionCondition(String expressionCondition) {
        this.expressionCondition = expressionCondition;
        return this;
    }

    @JsonProperty("fbamodel_ref")
    public String getFbamodelRef() {
        return fbamodelRef;
    }

    @JsonProperty("fbamodel_ref")
    public void setFbamodelRef(String fbamodelRef) {
        this.fbamodelRef = fbamodelRef;
    }

    public FBAPathwayAnalysisPathwayMultiple withFbamodelRef(String fbamodelRef) {
        this.fbamodelRef = fbamodelRef;
        return this;
    }

    @JsonProperty("fba_ref")
    public String getFbaRef() {
        return fbaRef;
    }

    @JsonProperty("fba_ref")
    public void setFbaRef(String fbaRef) {
        this.fbaRef = fbaRef;
    }

    public FBAPathwayAnalysisPathwayMultiple withFbaRef(String fbaRef) {
        this.fbaRef = fbaRef;
        return this;
    }

    @JsonProperty("count_list")
    public List<FBAPathwayAnalysisCounts> getCountList() {
        return countList;
    }

    @JsonProperty("count_list")
    public void setCountList(List<FBAPathwayAnalysisCounts> countList) {
        this.countList = countList;
    }

    public FBAPathwayAnalysisPathwayMultiple withCountList(List<FBAPathwayAnalysisCounts> countList) {
        this.countList = countList;
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
        return ((((((((((((("FBAPathwayAnalysisPathwayMultiple"+" [expressionMatrixRef=")+ expressionMatrixRef)+", expressionCondition=")+ expressionCondition)+", fbamodelRef=")+ fbamodelRef)+", fbaRef=")+ fbaRef)+", countList=")+ countList)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
