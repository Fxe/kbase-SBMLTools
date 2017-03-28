
package kbasefba;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple5;


/**
 * <p>Original spec-file type: ModelComparisonModel</p>
 * <pre>
 * ModelComparisonModel object: this object holds information about a model in a model comparison
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "model_ref",
    "genome_ref",
    "model_similarity",
    "name",
    "taxonomy",
    "reactions",
    "families",
    "compounds",
    "biomasscpds",
    "biomasses"
})
public class ModelComparisonModel {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("model_ref")
    private java.lang.String modelRef;
    @JsonProperty("genome_ref")
    private java.lang.String genomeRef;
    @JsonProperty("model_similarity")
    private Map<String, Tuple5 <Long, Long, Long, Long, Long>> modelSimilarity;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("taxonomy")
    private java.lang.String taxonomy;
    @JsonProperty("reactions")
    private java.lang.Long reactions;
    @JsonProperty("families")
    private java.lang.Long families;
    @JsonProperty("compounds")
    private java.lang.Long compounds;
    @JsonProperty("biomasscpds")
    private java.lang.Long biomasscpds;
    @JsonProperty("biomasses")
    private java.lang.Long biomasses;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ModelComparisonModel withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("model_ref")
    public java.lang.String getModelRef() {
        return modelRef;
    }

    @JsonProperty("model_ref")
    public void setModelRef(java.lang.String modelRef) {
        this.modelRef = modelRef;
    }

    public ModelComparisonModel withModelRef(java.lang.String modelRef) {
        this.modelRef = modelRef;
        return this;
    }

    @JsonProperty("genome_ref")
    public java.lang.String getGenomeRef() {
        return genomeRef;
    }

    @JsonProperty("genome_ref")
    public void setGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
    }

    public ModelComparisonModel withGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("model_similarity")
    public Map<String, Tuple5 <Long, Long, Long, Long, Long>> getModelSimilarity() {
        return modelSimilarity;
    }

    @JsonProperty("model_similarity")
    public void setModelSimilarity(Map<String, Tuple5 <Long, Long, Long, Long, Long>> modelSimilarity) {
        this.modelSimilarity = modelSimilarity;
    }

    public ModelComparisonModel withModelSimilarity(Map<String, Tuple5 <Long, Long, Long, Long, Long>> modelSimilarity) {
        this.modelSimilarity = modelSimilarity;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public ModelComparisonModel withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("taxonomy")
    public java.lang.String getTaxonomy() {
        return taxonomy;
    }

    @JsonProperty("taxonomy")
    public void setTaxonomy(java.lang.String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public ModelComparisonModel withTaxonomy(java.lang.String taxonomy) {
        this.taxonomy = taxonomy;
        return this;
    }

    @JsonProperty("reactions")
    public java.lang.Long getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(java.lang.Long reactions) {
        this.reactions = reactions;
    }

    public ModelComparisonModel withReactions(java.lang.Long reactions) {
        this.reactions = reactions;
        return this;
    }

    @JsonProperty("families")
    public java.lang.Long getFamilies() {
        return families;
    }

    @JsonProperty("families")
    public void setFamilies(java.lang.Long families) {
        this.families = families;
    }

    public ModelComparisonModel withFamilies(java.lang.Long families) {
        this.families = families;
        return this;
    }

    @JsonProperty("compounds")
    public java.lang.Long getCompounds() {
        return compounds;
    }

    @JsonProperty("compounds")
    public void setCompounds(java.lang.Long compounds) {
        this.compounds = compounds;
    }

    public ModelComparisonModel withCompounds(java.lang.Long compounds) {
        this.compounds = compounds;
        return this;
    }

    @JsonProperty("biomasscpds")
    public java.lang.Long getBiomasscpds() {
        return biomasscpds;
    }

    @JsonProperty("biomasscpds")
    public void setBiomasscpds(java.lang.Long biomasscpds) {
        this.biomasscpds = biomasscpds;
    }

    public ModelComparisonModel withBiomasscpds(java.lang.Long biomasscpds) {
        this.biomasscpds = biomasscpds;
        return this;
    }

    @JsonProperty("biomasses")
    public java.lang.Long getBiomasses() {
        return biomasses;
    }

    @JsonProperty("biomasses")
    public void setBiomasses(java.lang.Long biomasses) {
        this.biomasses = biomasses;
    }

    public ModelComparisonModel withBiomasses(java.lang.Long biomasses) {
        this.biomasses = biomasses;
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
        return ((((((((((((((((((((((((("ModelComparisonModel"+" [id=")+ id)+", modelRef=")+ modelRef)+", genomeRef=")+ genomeRef)+", modelSimilarity=")+ modelSimilarity)+", name=")+ name)+", taxonomy=")+ taxonomy)+", reactions=")+ reactions)+", families=")+ families)+", compounds=")+ compounds)+", biomasscpds=")+ biomasscpds)+", biomasses=")+ biomasses)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
