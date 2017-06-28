
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
 * <p>Original spec-file type: ClassifierClasses</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "description",
    "tp_rate",
    "fb_rate",
    "precision",
    "recall",
    "f_measure",
    "ROC_area",
    "missclassifications"
})
public class ClassifierClasses {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("description")
    private java.lang.String description;
    @JsonProperty("tp_rate")
    private Double tpRate;
    @JsonProperty("fb_rate")
    private Double fbRate;
    @JsonProperty("precision")
    private Double precision;
    @JsonProperty("recall")
    private Double recall;
    @JsonProperty("f_measure")
    private Double fMeasure;
    @JsonProperty("ROC_area")
    private Double ROCArea;
    @JsonProperty("missclassifications")
    private Map<String, Long> missclassifications;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ClassifierClasses withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("description")
    public java.lang.String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public ClassifierClasses withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("tp_rate")
    public Double getTpRate() {
        return tpRate;
    }

    @JsonProperty("tp_rate")
    public void setTpRate(Double tpRate) {
        this.tpRate = tpRate;
    }

    public ClassifierClasses withTpRate(Double tpRate) {
        this.tpRate = tpRate;
        return this;
    }

    @JsonProperty("fb_rate")
    public Double getFbRate() {
        return fbRate;
    }

    @JsonProperty("fb_rate")
    public void setFbRate(Double fbRate) {
        this.fbRate = fbRate;
    }

    public ClassifierClasses withFbRate(Double fbRate) {
        this.fbRate = fbRate;
        return this;
    }

    @JsonProperty("precision")
    public Double getPrecision() {
        return precision;
    }

    @JsonProperty("precision")
    public void setPrecision(Double precision) {
        this.precision = precision;
    }

    public ClassifierClasses withPrecision(Double precision) {
        this.precision = precision;
        return this;
    }

    @JsonProperty("recall")
    public Double getRecall() {
        return recall;
    }

    @JsonProperty("recall")
    public void setRecall(Double recall) {
        this.recall = recall;
    }

    public ClassifierClasses withRecall(Double recall) {
        this.recall = recall;
        return this;
    }

    @JsonProperty("f_measure")
    public Double getFMeasure() {
        return fMeasure;
    }

    @JsonProperty("f_measure")
    public void setFMeasure(Double fMeasure) {
        this.fMeasure = fMeasure;
    }

    public ClassifierClasses withFMeasure(Double fMeasure) {
        this.fMeasure = fMeasure;
        return this;
    }

    @JsonProperty("ROC_area")
    public Double getROCArea() {
        return ROCArea;
    }

    @JsonProperty("ROC_area")
    public void setROCArea(Double ROCArea) {
        this.ROCArea = ROCArea;
    }

    public ClassifierClasses withROCArea(Double ROCArea) {
        this.ROCArea = ROCArea;
        return this;
    }

    @JsonProperty("missclassifications")
    public Map<String, Long> getMissclassifications() {
        return missclassifications;
    }

    @JsonProperty("missclassifications")
    public void setMissclassifications(Map<String, Long> missclassifications) {
        this.missclassifications = missclassifications;
    }

    public ClassifierClasses withMissclassifications(Map<String, Long> missclassifications) {
        this.missclassifications = missclassifications;
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
        return ((((((((((((((((((((("ClassifierClasses"+" [id=")+ id)+", description=")+ description)+", tpRate=")+ tpRate)+", fbRate=")+ fbRate)+", precision=")+ precision)+", recall=")+ recall)+", fMeasure=")+ fMeasure)+", ROCArea=")+ ROCArea)+", missclassifications=")+ missclassifications)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
