
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
 * <p>Original spec-file type: Classifier</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "attribute_type",
    "classifier_type",
    "trainingset_ref",
    "data",
    "readable",
    "correctly_classified_instances",
    "incorrectly_classified_instances",
    "total_instances",
    "kappa",
    "mean_absolute_error",
    "root_mean_squared_error",
    "relative_absolute_error",
    "relative_squared_error",
    "classes"
})
public class Classifier {

    @JsonProperty("id")
    private String id;
    @JsonProperty("attribute_type")
    private String attributeType;
    @JsonProperty("classifier_type")
    private String classifierType;
    @JsonProperty("trainingset_ref")
    private String trainingsetRef;
    @JsonProperty("data")
    private String data;
    @JsonProperty("readable")
    private String readable;
    @JsonProperty("correctly_classified_instances")
    private Long correctlyClassifiedInstances;
    @JsonProperty("incorrectly_classified_instances")
    private Long incorrectlyClassifiedInstances;
    @JsonProperty("total_instances")
    private Long totalInstances;
    @JsonProperty("kappa")
    private Double kappa;
    @JsonProperty("mean_absolute_error")
    private Double meanAbsoluteError;
    @JsonProperty("root_mean_squared_error")
    private Double rootMeanSquaredError;
    @JsonProperty("relative_absolute_error")
    private Double relativeAbsoluteError;
    @JsonProperty("relative_squared_error")
    private Double relativeSquaredError;
    @JsonProperty("classes")
    private List<ClassifierClasses> classes;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Classifier withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("attribute_type")
    public String getAttributeType() {
        return attributeType;
    }

    @JsonProperty("attribute_type")
    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public Classifier withAttributeType(String attributeType) {
        this.attributeType = attributeType;
        return this;
    }

    @JsonProperty("classifier_type")
    public String getClassifierType() {
        return classifierType;
    }

    @JsonProperty("classifier_type")
    public void setClassifierType(String classifierType) {
        this.classifierType = classifierType;
    }

    public Classifier withClassifierType(String classifierType) {
        this.classifierType = classifierType;
        return this;
    }

    @JsonProperty("trainingset_ref")
    public String getTrainingsetRef() {
        return trainingsetRef;
    }

    @JsonProperty("trainingset_ref")
    public void setTrainingsetRef(String trainingsetRef) {
        this.trainingsetRef = trainingsetRef;
    }

    public Classifier withTrainingsetRef(String trainingsetRef) {
        this.trainingsetRef = trainingsetRef;
        return this;
    }

    @JsonProperty("data")
    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    public Classifier withData(String data) {
        this.data = data;
        return this;
    }

    @JsonProperty("readable")
    public String getReadable() {
        return readable;
    }

    @JsonProperty("readable")
    public void setReadable(String readable) {
        this.readable = readable;
    }

    public Classifier withReadable(String readable) {
        this.readable = readable;
        return this;
    }

    @JsonProperty("correctly_classified_instances")
    public Long getCorrectlyClassifiedInstances() {
        return correctlyClassifiedInstances;
    }

    @JsonProperty("correctly_classified_instances")
    public void setCorrectlyClassifiedInstances(Long correctlyClassifiedInstances) {
        this.correctlyClassifiedInstances = correctlyClassifiedInstances;
    }

    public Classifier withCorrectlyClassifiedInstances(Long correctlyClassifiedInstances) {
        this.correctlyClassifiedInstances = correctlyClassifiedInstances;
        return this;
    }

    @JsonProperty("incorrectly_classified_instances")
    public Long getIncorrectlyClassifiedInstances() {
        return incorrectlyClassifiedInstances;
    }

    @JsonProperty("incorrectly_classified_instances")
    public void setIncorrectlyClassifiedInstances(Long incorrectlyClassifiedInstances) {
        this.incorrectlyClassifiedInstances = incorrectlyClassifiedInstances;
    }

    public Classifier withIncorrectlyClassifiedInstances(Long incorrectlyClassifiedInstances) {
        this.incorrectlyClassifiedInstances = incorrectlyClassifiedInstances;
        return this;
    }

    @JsonProperty("total_instances")
    public Long getTotalInstances() {
        return totalInstances;
    }

    @JsonProperty("total_instances")
    public void setTotalInstances(Long totalInstances) {
        this.totalInstances = totalInstances;
    }

    public Classifier withTotalInstances(Long totalInstances) {
        this.totalInstances = totalInstances;
        return this;
    }

    @JsonProperty("kappa")
    public Double getKappa() {
        return kappa;
    }

    @JsonProperty("kappa")
    public void setKappa(Double kappa) {
        this.kappa = kappa;
    }

    public Classifier withKappa(Double kappa) {
        this.kappa = kappa;
        return this;
    }

    @JsonProperty("mean_absolute_error")
    public Double getMeanAbsoluteError() {
        return meanAbsoluteError;
    }

    @JsonProperty("mean_absolute_error")
    public void setMeanAbsoluteError(Double meanAbsoluteError) {
        this.meanAbsoluteError = meanAbsoluteError;
    }

    public Classifier withMeanAbsoluteError(Double meanAbsoluteError) {
        this.meanAbsoluteError = meanAbsoluteError;
        return this;
    }

    @JsonProperty("root_mean_squared_error")
    public Double getRootMeanSquaredError() {
        return rootMeanSquaredError;
    }

    @JsonProperty("root_mean_squared_error")
    public void setRootMeanSquaredError(Double rootMeanSquaredError) {
        this.rootMeanSquaredError = rootMeanSquaredError;
    }

    public Classifier withRootMeanSquaredError(Double rootMeanSquaredError) {
        this.rootMeanSquaredError = rootMeanSquaredError;
        return this;
    }

    @JsonProperty("relative_absolute_error")
    public Double getRelativeAbsoluteError() {
        return relativeAbsoluteError;
    }

    @JsonProperty("relative_absolute_error")
    public void setRelativeAbsoluteError(Double relativeAbsoluteError) {
        this.relativeAbsoluteError = relativeAbsoluteError;
    }

    public Classifier withRelativeAbsoluteError(Double relativeAbsoluteError) {
        this.relativeAbsoluteError = relativeAbsoluteError;
        return this;
    }

    @JsonProperty("relative_squared_error")
    public Double getRelativeSquaredError() {
        return relativeSquaredError;
    }

    @JsonProperty("relative_squared_error")
    public void setRelativeSquaredError(Double relativeSquaredError) {
        this.relativeSquaredError = relativeSquaredError;
    }

    public Classifier withRelativeSquaredError(Double relativeSquaredError) {
        this.relativeSquaredError = relativeSquaredError;
        return this;
    }

    @JsonProperty("classes")
    public List<ClassifierClasses> getClasses() {
        return classes;
    }

    @JsonProperty("classes")
    public void setClasses(List<ClassifierClasses> classes) {
        this.classes = classes;
    }

    public Classifier withClasses(List<ClassifierClasses> classes) {
        this.classes = classes;
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
        return ((((((((((((((((((((((((((((((((("Classifier"+" [id=")+ id)+", attributeType=")+ attributeType)+", classifierType=")+ classifierType)+", trainingsetRef=")+ trainingsetRef)+", data=")+ data)+", readable=")+ readable)+", correctlyClassifiedInstances=")+ correctlyClassifiedInstances)+", incorrectlyClassifiedInstances=")+ incorrectlyClassifiedInstances)+", totalInstances=")+ totalInstances)+", kappa=")+ kappa)+", meanAbsoluteError=")+ meanAbsoluteError)+", rootMeanSquaredError=")+ rootMeanSquaredError)+", relativeAbsoluteError=")+ relativeAbsoluteError)+", relativeSquaredError=")+ relativeSquaredError)+", classes=")+ classes)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
