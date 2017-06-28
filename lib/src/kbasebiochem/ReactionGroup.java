
package kbasebiochem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple2;


/**
 * <p>Original spec-file type: ReactionGroup</p>
 * <pre>
 * ReactionGroup object
 * @optional substrate_path product_path spline dasharray
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "rxn_ids",
    "x",
    "y",
    "substrate_path",
    "product_path",
    "spline",
    "dasharray"
})
public class ReactionGroup {

    @JsonProperty("rxn_ids")
    private List<String> rxnIds;
    @JsonProperty("x")
    private java.lang.Long x;
    @JsonProperty("y")
    private java.lang.Long y;
    @JsonProperty("substrate_path")
    private List<Tuple2 <Long, Long>> substratePath;
    @JsonProperty("product_path")
    private List<Tuple2 <Long, Long>> productPath;
    @JsonProperty("spline")
    private java.lang.String spline;
    @JsonProperty("dasharray")
    private java.lang.String dasharray;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("rxn_ids")
    public List<String> getRxnIds() {
        return rxnIds;
    }

    @JsonProperty("rxn_ids")
    public void setRxnIds(List<String> rxnIds) {
        this.rxnIds = rxnIds;
    }

    public ReactionGroup withRxnIds(List<String> rxnIds) {
        this.rxnIds = rxnIds;
        return this;
    }

    @JsonProperty("x")
    public java.lang.Long getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(java.lang.Long x) {
        this.x = x;
    }

    public ReactionGroup withX(java.lang.Long x) {
        this.x = x;
        return this;
    }

    @JsonProperty("y")
    public java.lang.Long getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(java.lang.Long y) {
        this.y = y;
    }

    public ReactionGroup withY(java.lang.Long y) {
        this.y = y;
        return this;
    }

    @JsonProperty("substrate_path")
    public List<Tuple2 <Long, Long>> getSubstratePath() {
        return substratePath;
    }

    @JsonProperty("substrate_path")
    public void setSubstratePath(List<Tuple2 <Long, Long>> substratePath) {
        this.substratePath = substratePath;
    }

    public ReactionGroup withSubstratePath(List<Tuple2 <Long, Long>> substratePath) {
        this.substratePath = substratePath;
        return this;
    }

    @JsonProperty("product_path")
    public List<Tuple2 <Long, Long>> getProductPath() {
        return productPath;
    }

    @JsonProperty("product_path")
    public void setProductPath(List<Tuple2 <Long, Long>> productPath) {
        this.productPath = productPath;
    }

    public ReactionGroup withProductPath(List<Tuple2 <Long, Long>> productPath) {
        this.productPath = productPath;
        return this;
    }

    @JsonProperty("spline")
    public java.lang.String getSpline() {
        return spline;
    }

    @JsonProperty("spline")
    public void setSpline(java.lang.String spline) {
        this.spline = spline;
    }

    public ReactionGroup withSpline(java.lang.String spline) {
        this.spline = spline;
        return this;
    }

    @JsonProperty("dasharray")
    public java.lang.String getDasharray() {
        return dasharray;
    }

    @JsonProperty("dasharray")
    public void setDasharray(java.lang.String dasharray) {
        this.dasharray = dasharray;
    }

    public ReactionGroup withDasharray(java.lang.String dasharray) {
        this.dasharray = dasharray;
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
        return ((((((((((((((((("ReactionGroup"+" [rxnIds=")+ rxnIds)+", x=")+ x)+", y=")+ y)+", substratePath=")+ substratePath)+", productPath=")+ productPath)+", spline=")+ spline)+", dasharray=")+ dasharray)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
