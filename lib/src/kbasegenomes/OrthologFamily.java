
package kbasegenomes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple3;


/**
 * <p>Original spec-file type: OrthologFamily</p>
 * <pre>
 * OrthologFamily object: this object holds all data for a single ortholog family in a metagenome
 * @optional type function md5 protein_translation
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "type",
    "function",
    "md5",
    "protein_translation",
    "orthologs"
})
public class OrthologFamily {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("function")
    private java.lang.String function;
    @JsonProperty("md5")
    private java.lang.String md5;
    @JsonProperty("protein_translation")
    private java.lang.String proteinTranslation;
    @JsonProperty("orthologs")
    private List<Tuple3 <String, Double, String>> orthologs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public OrthologFamily withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("type")
    public java.lang.String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(java.lang.String type) {
        this.type = type;
    }

    public OrthologFamily withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("function")
    public java.lang.String getFunction() {
        return function;
    }

    @JsonProperty("function")
    public void setFunction(java.lang.String function) {
        this.function = function;
    }

    public OrthologFamily withFunction(java.lang.String function) {
        this.function = function;
        return this;
    }

    @JsonProperty("md5")
    public java.lang.String getMd5() {
        return md5;
    }

    @JsonProperty("md5")
    public void setMd5(java.lang.String md5) {
        this.md5 = md5;
    }

    public OrthologFamily withMd5(java.lang.String md5) {
        this.md5 = md5;
        return this;
    }

    @JsonProperty("protein_translation")
    public java.lang.String getProteinTranslation() {
        return proteinTranslation;
    }

    @JsonProperty("protein_translation")
    public void setProteinTranslation(java.lang.String proteinTranslation) {
        this.proteinTranslation = proteinTranslation;
    }

    public OrthologFamily withProteinTranslation(java.lang.String proteinTranslation) {
        this.proteinTranslation = proteinTranslation;
        return this;
    }

    @JsonProperty("orthologs")
    public List<Tuple3 <String, Double, String>> getOrthologs() {
        return orthologs;
    }

    @JsonProperty("orthologs")
    public void setOrthologs(List<Tuple3 <String, Double, String>> orthologs) {
        this.orthologs = orthologs;
    }

    public OrthologFamily withOrthologs(List<Tuple3 <String, Double, String>> orthologs) {
        this.orthologs = orthologs;
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
        return ((((((((((((((("OrthologFamily"+" [id=")+ id)+", type=")+ type)+", function=")+ function)+", md5=")+ md5)+", proteinTranslation=")+ proteinTranslation)+", orthologs=")+ orthologs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
