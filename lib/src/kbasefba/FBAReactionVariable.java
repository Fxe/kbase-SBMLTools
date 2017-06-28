
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
import us.kbase.common.service.Tuple2;


/**
 * <p>Original spec-file type: FBAReactionVariable</p>
 * <pre>
 * FBAReactionVariable object
 * @optional biomass_dependencies coupled_reactions exp_state expression scaled_exp other_values other_max other_min
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "modelreaction_ref",
    "variableType",
    "upperBound",
    "lowerBound",
    "class",
    "min",
    "max",
    "value",
    "exp_state",
    "expression",
    "scaled_exp",
    "biomass_dependencies",
    "coupled_reactions",
    "other_values",
    "other_max",
    "other_min"
})
public class FBAReactionVariable {

    @JsonProperty("modelreaction_ref")
    private java.lang.String modelreactionRef;
    @JsonProperty("variableType")
    private java.lang.String variableType;
    @JsonProperty("upperBound")
    private java.lang.Double upperBound;
    @JsonProperty("lowerBound")
    private java.lang.Double lowerBound;
    @JsonProperty("class")
    private java.lang.String _class;
    @JsonProperty("min")
    private java.lang.Double min;
    @JsonProperty("max")
    private java.lang.Double max;
    @JsonProperty("value")
    private java.lang.Double value;
    @JsonProperty("exp_state")
    private java.lang.String expState;
    @JsonProperty("expression")
    private java.lang.Double expression;
    @JsonProperty("scaled_exp")
    private java.lang.Double scaledExp;
    @JsonProperty("biomass_dependencies")
    private List<Tuple2 <String, String>> biomassDependencies;
    @JsonProperty("coupled_reactions")
    private List<String> coupledReactions;
    @JsonProperty("other_values")
    private List<Double> otherValues;
    @JsonProperty("other_max")
    private List<Double> otherMax;
    @JsonProperty("other_min")
    private List<Double> otherMin;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("modelreaction_ref")
    public java.lang.String getModelreactionRef() {
        return modelreactionRef;
    }

    @JsonProperty("modelreaction_ref")
    public void setModelreactionRef(java.lang.String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
    }

    public FBAReactionVariable withModelreactionRef(java.lang.String modelreactionRef) {
        this.modelreactionRef = modelreactionRef;
        return this;
    }

    @JsonProperty("variableType")
    public java.lang.String getVariableType() {
        return variableType;
    }

    @JsonProperty("variableType")
    public void setVariableType(java.lang.String variableType) {
        this.variableType = variableType;
    }

    public FBAReactionVariable withVariableType(java.lang.String variableType) {
        this.variableType = variableType;
        return this;
    }

    @JsonProperty("upperBound")
    public java.lang.Double getUpperBound() {
        return upperBound;
    }

    @JsonProperty("upperBound")
    public void setUpperBound(java.lang.Double upperBound) {
        this.upperBound = upperBound;
    }

    public FBAReactionVariable withUpperBound(java.lang.Double upperBound) {
        this.upperBound = upperBound;
        return this;
    }

    @JsonProperty("lowerBound")
    public java.lang.Double getLowerBound() {
        return lowerBound;
    }

    @JsonProperty("lowerBound")
    public void setLowerBound(java.lang.Double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public FBAReactionVariable withLowerBound(java.lang.Double lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    @JsonProperty("class")
    public java.lang.String getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(java.lang.String _class) {
        this._class = _class;
    }

    public FBAReactionVariable withClass(java.lang.String _class) {
        this._class = _class;
        return this;
    }

    @JsonProperty("min")
    public java.lang.Double getMin() {
        return min;
    }

    @JsonProperty("min")
    public void setMin(java.lang.Double min) {
        this.min = min;
    }

    public FBAReactionVariable withMin(java.lang.Double min) {
        this.min = min;
        return this;
    }

    @JsonProperty("max")
    public java.lang.Double getMax() {
        return max;
    }

    @JsonProperty("max")
    public void setMax(java.lang.Double max) {
        this.max = max;
    }

    public FBAReactionVariable withMax(java.lang.Double max) {
        this.max = max;
        return this;
    }

    @JsonProperty("value")
    public java.lang.Double getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(java.lang.Double value) {
        this.value = value;
    }

    public FBAReactionVariable withValue(java.lang.Double value) {
        this.value = value;
        return this;
    }

    @JsonProperty("exp_state")
    public java.lang.String getExpState() {
        return expState;
    }

    @JsonProperty("exp_state")
    public void setExpState(java.lang.String expState) {
        this.expState = expState;
    }

    public FBAReactionVariable withExpState(java.lang.String expState) {
        this.expState = expState;
        return this;
    }

    @JsonProperty("expression")
    public java.lang.Double getExpression() {
        return expression;
    }

    @JsonProperty("expression")
    public void setExpression(java.lang.Double expression) {
        this.expression = expression;
    }

    public FBAReactionVariable withExpression(java.lang.Double expression) {
        this.expression = expression;
        return this;
    }

    @JsonProperty("scaled_exp")
    public java.lang.Double getScaledExp() {
        return scaledExp;
    }

    @JsonProperty("scaled_exp")
    public void setScaledExp(java.lang.Double scaledExp) {
        this.scaledExp = scaledExp;
    }

    public FBAReactionVariable withScaledExp(java.lang.Double scaledExp) {
        this.scaledExp = scaledExp;
        return this;
    }

    @JsonProperty("biomass_dependencies")
    public List<Tuple2 <String, String>> getBiomassDependencies() {
        return biomassDependencies;
    }

    @JsonProperty("biomass_dependencies")
    public void setBiomassDependencies(List<Tuple2 <String, String>> biomassDependencies) {
        this.biomassDependencies = biomassDependencies;
    }

    public FBAReactionVariable withBiomassDependencies(List<Tuple2 <String, String>> biomassDependencies) {
        this.biomassDependencies = biomassDependencies;
        return this;
    }

    @JsonProperty("coupled_reactions")
    public List<String> getCoupledReactions() {
        return coupledReactions;
    }

    @JsonProperty("coupled_reactions")
    public void setCoupledReactions(List<String> coupledReactions) {
        this.coupledReactions = coupledReactions;
    }

    public FBAReactionVariable withCoupledReactions(List<String> coupledReactions) {
        this.coupledReactions = coupledReactions;
        return this;
    }

    @JsonProperty("other_values")
    public List<Double> getOtherValues() {
        return otherValues;
    }

    @JsonProperty("other_values")
    public void setOtherValues(List<Double> otherValues) {
        this.otherValues = otherValues;
    }

    public FBAReactionVariable withOtherValues(List<Double> otherValues) {
        this.otherValues = otherValues;
        return this;
    }

    @JsonProperty("other_max")
    public List<Double> getOtherMax() {
        return otherMax;
    }

    @JsonProperty("other_max")
    public void setOtherMax(List<Double> otherMax) {
        this.otherMax = otherMax;
    }

    public FBAReactionVariable withOtherMax(List<Double> otherMax) {
        this.otherMax = otherMax;
        return this;
    }

    @JsonProperty("other_min")
    public List<Double> getOtherMin() {
        return otherMin;
    }

    @JsonProperty("other_min")
    public void setOtherMin(List<Double> otherMin) {
        this.otherMin = otherMin;
    }

    public FBAReactionVariable withOtherMin(List<Double> otherMin) {
        this.otherMin = otherMin;
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
        return ((((((((((((((((((((((((((((((((((("FBAReactionVariable"+" [modelreactionRef=")+ modelreactionRef)+", variableType=")+ variableType)+", upperBound=")+ upperBound)+", lowerBound=")+ lowerBound)+", _class=")+ _class)+", min=")+ min)+", max=")+ max)+", value=")+ value)+", expState=")+ expState)+", expression=")+ expression)+", scaledExp=")+ scaledExp)+", biomassDependencies=")+ biomassDependencies)+", coupledReactions=")+ coupledReactions)+", otherValues=")+ otherValues)+", otherMax=")+ otherMax)+", otherMin=")+ otherMin)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
