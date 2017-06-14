
package fbatools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple4;


/**
 * <p>Original spec-file type: EditMediaParams</p>
 * <pre>
 * EditMediaParams object: arguments for the edit model function
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "workspace",
    "media_id",
    "media_workspace",
    "compounds_to_remove",
    "compounds_to_change",
    "compounds_to_add",
    "media_output_id"
})
public class EditMediaParams {

    @JsonProperty("workspace")
    private java.lang.String workspace;
    @JsonProperty("media_id")
    private java.lang.String mediaId;
    @JsonProperty("media_workspace")
    private java.lang.String mediaWorkspace;
    @JsonProperty("compounds_to_remove")
    private List<String> compoundsToRemove;
    @JsonProperty("compounds_to_change")
    private List<Tuple4 <String, Double, Double, Double>> compoundsToChange;
    @JsonProperty("compounds_to_add")
    private List<Tuple4 <String, Double, Double, Double>> compoundsToAdd;
    @JsonProperty("media_output_id")
    private java.lang.String mediaOutputId;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("workspace")
    public java.lang.String getWorkspace() {
        return workspace;
    }

    @JsonProperty("workspace")
    public void setWorkspace(java.lang.String workspace) {
        this.workspace = workspace;
    }

    public EditMediaParams withWorkspace(java.lang.String workspace) {
        this.workspace = workspace;
        return this;
    }

    @JsonProperty("media_id")
    public java.lang.String getMediaId() {
        return mediaId;
    }

    @JsonProperty("media_id")
    public void setMediaId(java.lang.String mediaId) {
        this.mediaId = mediaId;
    }

    public EditMediaParams withMediaId(java.lang.String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    @JsonProperty("media_workspace")
    public java.lang.String getMediaWorkspace() {
        return mediaWorkspace;
    }

    @JsonProperty("media_workspace")
    public void setMediaWorkspace(java.lang.String mediaWorkspace) {
        this.mediaWorkspace = mediaWorkspace;
    }

    public EditMediaParams withMediaWorkspace(java.lang.String mediaWorkspace) {
        this.mediaWorkspace = mediaWorkspace;
        return this;
    }

    @JsonProperty("compounds_to_remove")
    public List<String> getCompoundsToRemove() {
        return compoundsToRemove;
    }

    @JsonProperty("compounds_to_remove")
    public void setCompoundsToRemove(List<String> compoundsToRemove) {
        this.compoundsToRemove = compoundsToRemove;
    }

    public EditMediaParams withCompoundsToRemove(List<String> compoundsToRemove) {
        this.compoundsToRemove = compoundsToRemove;
        return this;
    }

    @JsonProperty("compounds_to_change")
    public List<Tuple4 <String, Double, Double, Double>> getCompoundsToChange() {
        return compoundsToChange;
    }

    @JsonProperty("compounds_to_change")
    public void setCompoundsToChange(List<Tuple4 <String, Double, Double, Double>> compoundsToChange) {
        this.compoundsToChange = compoundsToChange;
    }

    public EditMediaParams withCompoundsToChange(List<Tuple4 <String, Double, Double, Double>> compoundsToChange) {
        this.compoundsToChange = compoundsToChange;
        return this;
    }

    @JsonProperty("compounds_to_add")
    public List<Tuple4 <String, Double, Double, Double>> getCompoundsToAdd() {
        return compoundsToAdd;
    }

    @JsonProperty("compounds_to_add")
    public void setCompoundsToAdd(List<Tuple4 <String, Double, Double, Double>> compoundsToAdd) {
        this.compoundsToAdd = compoundsToAdd;
    }

    public EditMediaParams withCompoundsToAdd(List<Tuple4 <String, Double, Double, Double>> compoundsToAdd) {
        this.compoundsToAdd = compoundsToAdd;
        return this;
    }

    @JsonProperty("media_output_id")
    public java.lang.String getMediaOutputId() {
        return mediaOutputId;
    }

    @JsonProperty("media_output_id")
    public void setMediaOutputId(java.lang.String mediaOutputId) {
        this.mediaOutputId = mediaOutputId;
    }

    public EditMediaParams withMediaOutputId(java.lang.String mediaOutputId) {
        this.mediaOutputId = mediaOutputId;
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
        return ((((((((((((((((("EditMediaParams"+" [workspace=")+ workspace)+", mediaId=")+ mediaId)+", mediaWorkspace=")+ mediaWorkspace)+", compoundsToRemove=")+ compoundsToRemove)+", compoundsToChange=")+ compoundsToChange)+", compoundsToAdd=")+ compoundsToAdd)+", mediaOutputId=")+ mediaOutputId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
