
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
 * <p>Original spec-file type: FBAModel</p>
 * <pre>
 * FBAModel object
 * @optional gapfilledcandidates metagenome_otu_ref metagenome_ref genome_ref template_refs ATPSynthaseStoichiometry ATPMaintenance quantopts
 *     @metadata ws source_id as Source ID
 *     @metadata ws source as Source
 *     @metadata ws name as Name
 *     @metadata ws type as Type
 *     @metadata ws genome_ref as Genome
 *     @metadata ws length(biomasses) as Number biomasses
 *     @metadata ws length(modelcompartments) as Number compartments
 *     @metadata ws length(modelcompounds) as Number compounds
 *     @metadata ws length(modelreactions) as Number reactions
 *     @metadata ws length(gapgens) as Number gapgens
 *     @metadata ws length(gapfillings) as Number gapfills
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "source",
    "source_id",
    "name",
    "type",
    "genome_ref",
    "metagenome_ref",
    "metagenome_otu_ref",
    "template_ref",
    "ATPSynthaseStoichiometry",
    "ATPMaintenance",
    "template_refs",
    "gapfillings",
    "gapgens",
    "quantopts",
    "biomasses",
    "modelcompartments",
    "modelcompounds",
    "modelreactions",
    "gapfilledcandidates"
})
public class FBAModel {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("source")
    private java.lang.String source;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("genome_ref")
    private java.lang.String genomeRef;
    @JsonProperty("metagenome_ref")
    private java.lang.String metagenomeRef;
    @JsonProperty("metagenome_otu_ref")
    private java.lang.String metagenomeOtuRef;
    @JsonProperty("template_ref")
    private java.lang.String templateRef;
    @JsonProperty("ATPSynthaseStoichiometry")
    private Double ATPSynthaseStoichiometry;
    @JsonProperty("ATPMaintenance")
    private Double ATPMaintenance;
    @JsonProperty("template_refs")
    private List<String> templateRefs;
    @JsonProperty("gapfillings")
    private List<ModelGapfill> gapfillings;
    @JsonProperty("gapgens")
    private List<ModelGapgen> gapgens;
    @JsonProperty("quantopts")
    private List<ModelQuantOpt> quantopts;
    @JsonProperty("biomasses")
    private List<Biomass> biomasses;
    @JsonProperty("modelcompartments")
    private List<ModelCompartment> modelcompartments;
    @JsonProperty("modelcompounds")
    private List<ModelCompound> modelcompounds;
    @JsonProperty("modelreactions")
    private List<kbasefba.ModelReaction> modelreactions;
    @JsonProperty("gapfilledcandidates")
    private List<kbasefba.ModelReaction> gapfilledcandidates;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FBAModel withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("source")
    public java.lang.String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(java.lang.String source) {
        this.source = source;
    }

    public FBAModel withSource(java.lang.String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("source_id")
    public java.lang.String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
    }

    public FBAModel withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
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

    public FBAModel withName(java.lang.String name) {
        this.name = name;
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

    public FBAModel withType(java.lang.String type) {
        this.type = type;
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

    public FBAModel withGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("metagenome_ref")
    public java.lang.String getMetagenomeRef() {
        return metagenomeRef;
    }

    @JsonProperty("metagenome_ref")
    public void setMetagenomeRef(java.lang.String metagenomeRef) {
        this.metagenomeRef = metagenomeRef;
    }

    public FBAModel withMetagenomeRef(java.lang.String metagenomeRef) {
        this.metagenomeRef = metagenomeRef;
        return this;
    }

    @JsonProperty("metagenome_otu_ref")
    public java.lang.String getMetagenomeOtuRef() {
        return metagenomeOtuRef;
    }

    @JsonProperty("metagenome_otu_ref")
    public void setMetagenomeOtuRef(java.lang.String metagenomeOtuRef) {
        this.metagenomeOtuRef = metagenomeOtuRef;
    }

    public FBAModel withMetagenomeOtuRef(java.lang.String metagenomeOtuRef) {
        this.metagenomeOtuRef = metagenomeOtuRef;
        return this;
    }

    @JsonProperty("template_ref")
    public java.lang.String getTemplateRef() {
        return templateRef;
    }

    @JsonProperty("template_ref")
    public void setTemplateRef(java.lang.String templateRef) {
        this.templateRef = templateRef;
    }

    public FBAModel withTemplateRef(java.lang.String templateRef) {
        this.templateRef = templateRef;
        return this;
    }

    @JsonProperty("ATPSynthaseStoichiometry")
    public Double getATPSynthaseStoichiometry() {
        return ATPSynthaseStoichiometry;
    }

    @JsonProperty("ATPSynthaseStoichiometry")
    public void setATPSynthaseStoichiometry(Double ATPSynthaseStoichiometry) {
        this.ATPSynthaseStoichiometry = ATPSynthaseStoichiometry;
    }

    public FBAModel withATPSynthaseStoichiometry(Double ATPSynthaseStoichiometry) {
        this.ATPSynthaseStoichiometry = ATPSynthaseStoichiometry;
        return this;
    }

    @JsonProperty("ATPMaintenance")
    public Double getATPMaintenance() {
        return ATPMaintenance;
    }

    @JsonProperty("ATPMaintenance")
    public void setATPMaintenance(Double ATPMaintenance) {
        this.ATPMaintenance = ATPMaintenance;
    }

    public FBAModel withATPMaintenance(Double ATPMaintenance) {
        this.ATPMaintenance = ATPMaintenance;
        return this;
    }

    @JsonProperty("template_refs")
    public List<String> getTemplateRefs() {
        return templateRefs;
    }

    @JsonProperty("template_refs")
    public void setTemplateRefs(List<String> templateRefs) {
        this.templateRefs = templateRefs;
    }

    public FBAModel withTemplateRefs(List<String> templateRefs) {
        this.templateRefs = templateRefs;
        return this;
    }

    @JsonProperty("gapfillings")
    public List<ModelGapfill> getGapfillings() {
        return gapfillings;
    }

    @JsonProperty("gapfillings")
    public void setGapfillings(List<ModelGapfill> gapfillings) {
        this.gapfillings = gapfillings;
    }

    public FBAModel withGapfillings(List<ModelGapfill> gapfillings) {
        this.gapfillings = gapfillings;
        return this;
    }

    @JsonProperty("gapgens")
    public List<ModelGapgen> getGapgens() {
        return gapgens;
    }

    @JsonProperty("gapgens")
    public void setGapgens(List<ModelGapgen> gapgens) {
        this.gapgens = gapgens;
    }

    public FBAModel withGapgens(List<ModelGapgen> gapgens) {
        this.gapgens = gapgens;
        return this;
    }

    @JsonProperty("quantopts")
    public List<ModelQuantOpt> getQuantopts() {
        return quantopts;
    }

    @JsonProperty("quantopts")
    public void setQuantopts(List<ModelQuantOpt> quantopts) {
        this.quantopts = quantopts;
    }

    public FBAModel withQuantopts(List<ModelQuantOpt> quantopts) {
        this.quantopts = quantopts;
        return this;
    }

    @JsonProperty("biomasses")
    public List<Biomass> getBiomasses() {
        return biomasses;
    }

    @JsonProperty("biomasses")
    public void setBiomasses(List<Biomass> biomasses) {
        this.biomasses = biomasses;
    }

    public FBAModel withBiomasses(List<Biomass> biomasses) {
        this.biomasses = biomasses;
        return this;
    }

    @JsonProperty("modelcompartments")
    public List<ModelCompartment> getModelcompartments() {
        return modelcompartments;
    }

    @JsonProperty("modelcompartments")
    public void setModelcompartments(List<ModelCompartment> modelcompartments) {
        this.modelcompartments = modelcompartments;
    }

    public FBAModel withModelcompartments(List<ModelCompartment> modelcompartments) {
        this.modelcompartments = modelcompartments;
        return this;
    }

    @JsonProperty("modelcompounds")
    public List<ModelCompound> getModelcompounds() {
        return modelcompounds;
    }

    @JsonProperty("modelcompounds")
    public void setModelcompounds(List<ModelCompound> modelcompounds) {
        this.modelcompounds = modelcompounds;
    }

    public FBAModel withModelcompounds(List<ModelCompound> modelcompounds) {
        this.modelcompounds = modelcompounds;
        return this;
    }

    @JsonProperty("modelreactions")
    public List<kbasefba.ModelReaction> getModelreactions() {
        return modelreactions;
    }

    @JsonProperty("modelreactions")
    public void setModelreactions(List<kbasefba.ModelReaction> modelreactions) {
        this.modelreactions = modelreactions;
    }

    public FBAModel withModelreactions(List<kbasefba.ModelReaction> modelreactions) {
        this.modelreactions = modelreactions;
        return this;
    }

    @JsonProperty("gapfilledcandidates")
    public List<kbasefba.ModelReaction> getGapfilledcandidates() {
        return gapfilledcandidates;
    }

    @JsonProperty("gapfilledcandidates")
    public void setGapfilledcandidates(List<kbasefba.ModelReaction> gapfilledcandidates) {
        this.gapfilledcandidates = gapfilledcandidates;
    }

    public FBAModel withGapfilledcandidates(List<kbasefba.ModelReaction> gapfilledcandidates) {
        this.gapfilledcandidates = gapfilledcandidates;
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
        return ((((((((((((((((((((((((((((((((((((((((((("FBAModel"+" [id=")+ id)+", source=")+ source)+", sourceId=")+ sourceId)+", name=")+ name)+", type=")+ type)+", genomeRef=")+ genomeRef)+", metagenomeRef=")+ metagenomeRef)+", metagenomeOtuRef=")+ metagenomeOtuRef)+", templateRef=")+ templateRef)+", ATPSynthaseStoichiometry=")+ ATPSynthaseStoichiometry)+", ATPMaintenance=")+ ATPMaintenance)+", templateRefs=")+ templateRefs)+", gapfillings=")+ gapfillings)+", gapgens=")+ gapgens)+", quantopts=")+ quantopts)+", biomasses=")+ biomasses)+", modelcompartments=")+ modelcompartments)+", modelcompounds=")+ modelcompounds)+", modelreactions=")+ modelreactions)+", gapfilledcandidates=")+ gapfilledcandidates)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
