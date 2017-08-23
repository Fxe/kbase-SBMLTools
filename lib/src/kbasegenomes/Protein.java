
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
 * <p>Original spec-file type: Protein</p>
 * <pre>
 * Type spec for the "Protein" object
 *         Protein_id id - unique external ID of protein
 *         string function - annotated function for protein
 *         string md5 - md5 hash of protein sequence
 *         string sequence - amino acid sequence of protein
 *         int length - length of protein
 *         list<ProteinFamily> protein_families - families to which the protein belongs
 *         list<string> aliases - aliases for the protein
 *         list<annotation> annotations - curator annotations on protein
 *         list<subsystem_data> subsystem_data;
 *         
 *         @optional function
 *     @searchable ws_subset id md5 function length aliases
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "function",
    "md5",
    "sequence",
    "length",
    "protein_families",
    "aliases",
    "annotations"
})
public class Protein {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("function")
    private java.lang.String function;
    @JsonProperty("md5")
    private java.lang.String md5;
    @JsonProperty("sequence")
    private java.lang.String sequence;
    @JsonProperty("length")
    private Long length;
    @JsonProperty("protein_families")
    private List<ProteinFamily> proteinFamilies;
    @JsonProperty("aliases")
    private List<String> aliases;
    @JsonProperty("annotations")
    private List<Tuple3 <String, String, Double>> annotations;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Protein withId(java.lang.String id) {
        this.id = id;
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

    public Protein withFunction(java.lang.String function) {
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

    public Protein withMd5(java.lang.String md5) {
        this.md5 = md5;
        return this;
    }

    @JsonProperty("sequence")
    public java.lang.String getSequence() {
        return sequence;
    }

    @JsonProperty("sequence")
    public void setSequence(java.lang.String sequence) {
        this.sequence = sequence;
    }

    public Protein withSequence(java.lang.String sequence) {
        this.sequence = sequence;
        return this;
    }

    @JsonProperty("length")
    public Long getLength() {
        return length;
    }

    @JsonProperty("length")
    public void setLength(Long length) {
        this.length = length;
    }

    public Protein withLength(Long length) {
        this.length = length;
        return this;
    }

    @JsonProperty("protein_families")
    public List<ProteinFamily> getProteinFamilies() {
        return proteinFamilies;
    }

    @JsonProperty("protein_families")
    public void setProteinFamilies(List<ProteinFamily> proteinFamilies) {
        this.proteinFamilies = proteinFamilies;
    }

    public Protein withProteinFamilies(List<ProteinFamily> proteinFamilies) {
        this.proteinFamilies = proteinFamilies;
        return this;
    }

    @JsonProperty("aliases")
    public List<String> getAliases() {
        return aliases;
    }

    @JsonProperty("aliases")
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public Protein withAliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }

    @JsonProperty("annotations")
    public List<Tuple3 <String, String, Double>> getAnnotations() {
        return annotations;
    }

    @JsonProperty("annotations")
    public void setAnnotations(List<Tuple3 <String, String, Double>> annotations) {
        this.annotations = annotations;
    }

    public Protein withAnnotations(List<Tuple3 <String, String, Double>> annotations) {
        this.annotations = annotations;
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
        return ((((((((((((((((((("Protein"+" [id=")+ id)+", function=")+ function)+", md5=")+ md5)+", sequence=")+ sequence)+", length=")+ length)+", proteinFamilies=")+ proteinFamilies)+", aliases=")+ aliases)+", annotations=")+ annotations)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
