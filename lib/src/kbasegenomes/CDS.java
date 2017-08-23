
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
import us.kbase.common.service.Tuple4;


/**
 * <p>Original spec-file type: CDS</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "location",
    "md5",
    "parent_gene",
    "parent_mrna",
    "function",
    "ontology_terms",
    "protein_translation",
    "protein_translation_length",
    "aliases"
})
public class CDS {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("location")
    private List<Tuple4 <String, Long, String, Long>> location;
    @JsonProperty("md5")
    private java.lang.String md5;
    @JsonProperty("parent_gene")
    private java.lang.String parentGene;
    @JsonProperty("parent_mrna")
    private java.lang.String parentMrna;
    @JsonProperty("function")
    private java.lang.String function;
    @JsonProperty("ontology_terms")
    private Map<String, Map<String, OntologyData>> ontologyTerms;
    @JsonProperty("protein_translation")
    private java.lang.String proteinTranslation;
    @JsonProperty("protein_translation_length")
    private java.lang.Long proteinTranslationLength;
    @JsonProperty("aliases")
    private List<String> aliases;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public CDS withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("location")
    public List<Tuple4 <String, Long, String, Long>> getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(List<Tuple4 <String, Long, String, Long>> location) {
        this.location = location;
    }

    public CDS withLocation(List<Tuple4 <String, Long, String, Long>> location) {
        this.location = location;
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

    public CDS withMd5(java.lang.String md5) {
        this.md5 = md5;
        return this;
    }

    @JsonProperty("parent_gene")
    public java.lang.String getParentGene() {
        return parentGene;
    }

    @JsonProperty("parent_gene")
    public void setParentGene(java.lang.String parentGene) {
        this.parentGene = parentGene;
    }

    public CDS withParentGene(java.lang.String parentGene) {
        this.parentGene = parentGene;
        return this;
    }

    @JsonProperty("parent_mrna")
    public java.lang.String getParentMrna() {
        return parentMrna;
    }

    @JsonProperty("parent_mrna")
    public void setParentMrna(java.lang.String parentMrna) {
        this.parentMrna = parentMrna;
    }

    public CDS withParentMrna(java.lang.String parentMrna) {
        this.parentMrna = parentMrna;
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

    public CDS withFunction(java.lang.String function) {
        this.function = function;
        return this;
    }

    @JsonProperty("ontology_terms")
    public Map<String, Map<String, OntologyData>> getOntologyTerms() {
        return ontologyTerms;
    }

    @JsonProperty("ontology_terms")
    public void setOntologyTerms(Map<String, Map<String, OntologyData>> ontologyTerms) {
        this.ontologyTerms = ontologyTerms;
    }

    public CDS withOntologyTerms(Map<String, Map<String, OntologyData>> ontologyTerms) {
        this.ontologyTerms = ontologyTerms;
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

    public CDS withProteinTranslation(java.lang.String proteinTranslation) {
        this.proteinTranslation = proteinTranslation;
        return this;
    }

    @JsonProperty("protein_translation_length")
    public java.lang.Long getProteinTranslationLength() {
        return proteinTranslationLength;
    }

    @JsonProperty("protein_translation_length")
    public void setProteinTranslationLength(java.lang.Long proteinTranslationLength) {
        this.proteinTranslationLength = proteinTranslationLength;
    }

    public CDS withProteinTranslationLength(java.lang.Long proteinTranslationLength) {
        this.proteinTranslationLength = proteinTranslationLength;
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

    public CDS withAliases(List<String> aliases) {
        this.aliases = aliases;
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
        return ((((((((((((((((((((((("CDS"+" [id=")+ id)+", location=")+ location)+", md5=")+ md5)+", parentGene=")+ parentGene)+", parentMrna=")+ parentMrna)+", function=")+ function)+", ontologyTerms=")+ ontologyTerms)+", proteinTranslation=")+ proteinTranslation)+", proteinTranslationLength=")+ proteinTranslationLength)+", aliases=")+ aliases)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
