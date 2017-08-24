
package genomeannotationapi;

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
 * <p>Original spec-file type: Summary_data</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "scientific_name",
    "taxonomy_id",
    "kingdom",
    "scientific_lineage",
    "genetic_code",
    "organism_aliases",
    "assembly_source",
    "assembly_source_id",
    "assembly_source_date",
    "gc_content",
    "dna_size",
    "num_contigs",
    "contig_ids",
    "external_source",
    "external_source_date",
    "release",
    "original_source_filename",
    "feature_type_counts"
})
public class SummaryData {

    @JsonProperty("scientific_name")
    private java.lang.String scientificName;
    @JsonProperty("taxonomy_id")
    private java.lang.Long taxonomyId;
    @JsonProperty("kingdom")
    private java.lang.String kingdom;
    @JsonProperty("scientific_lineage")
    private List<String> scientificLineage;
    @JsonProperty("genetic_code")
    private java.lang.Long geneticCode;
    @JsonProperty("organism_aliases")
    private List<String> organismAliases;
    @JsonProperty("assembly_source")
    private java.lang.String assemblySource;
    @JsonProperty("assembly_source_id")
    private java.lang.String assemblySourceId;
    @JsonProperty("assembly_source_date")
    private java.lang.String assemblySourceDate;
    @JsonProperty("gc_content")
    private Double gcContent;
    @JsonProperty("dna_size")
    private java.lang.Long dnaSize;
    @JsonProperty("num_contigs")
    private java.lang.Long numContigs;
    @JsonProperty("contig_ids")
    private List<String> contigIds;
    @JsonProperty("external_source")
    private java.lang.String externalSource;
    @JsonProperty("external_source_date")
    private java.lang.String externalSourceDate;
    @JsonProperty("release")
    private java.lang.String release;
    @JsonProperty("original_source_filename")
    private java.lang.String originalSourceFilename;
    @JsonProperty("feature_type_counts")
    private Map<String, Long> featureTypeCounts;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("scientific_name")
    public java.lang.String getScientificName() {
        return scientificName;
    }

    @JsonProperty("scientific_name")
    public void setScientificName(java.lang.String scientificName) {
        this.scientificName = scientificName;
    }

    public SummaryData withScientificName(java.lang.String scientificName) {
        this.scientificName = scientificName;
        return this;
    }

    @JsonProperty("taxonomy_id")
    public java.lang.Long getTaxonomyId() {
        return taxonomyId;
    }

    @JsonProperty("taxonomy_id")
    public void setTaxonomyId(java.lang.Long taxonomyId) {
        this.taxonomyId = taxonomyId;
    }

    public SummaryData withTaxonomyId(java.lang.Long taxonomyId) {
        this.taxonomyId = taxonomyId;
        return this;
    }

    @JsonProperty("kingdom")
    public java.lang.String getKingdom() {
        return kingdom;
    }

    @JsonProperty("kingdom")
    public void setKingdom(java.lang.String kingdom) {
        this.kingdom = kingdom;
    }

    public SummaryData withKingdom(java.lang.String kingdom) {
        this.kingdom = kingdom;
        return this;
    }

    @JsonProperty("scientific_lineage")
    public List<String> getScientificLineage() {
        return scientificLineage;
    }

    @JsonProperty("scientific_lineage")
    public void setScientificLineage(List<String> scientificLineage) {
        this.scientificLineage = scientificLineage;
    }

    public SummaryData withScientificLineage(List<String> scientificLineage) {
        this.scientificLineage = scientificLineage;
        return this;
    }

    @JsonProperty("genetic_code")
    public java.lang.Long getGeneticCode() {
        return geneticCode;
    }

    @JsonProperty("genetic_code")
    public void setGeneticCode(java.lang.Long geneticCode) {
        this.geneticCode = geneticCode;
    }

    public SummaryData withGeneticCode(java.lang.Long geneticCode) {
        this.geneticCode = geneticCode;
        return this;
    }

    @JsonProperty("organism_aliases")
    public List<String> getOrganismAliases() {
        return organismAliases;
    }

    @JsonProperty("organism_aliases")
    public void setOrganismAliases(List<String> organismAliases) {
        this.organismAliases = organismAliases;
    }

    public SummaryData withOrganismAliases(List<String> organismAliases) {
        this.organismAliases = organismAliases;
        return this;
    }

    @JsonProperty("assembly_source")
    public java.lang.String getAssemblySource() {
        return assemblySource;
    }

    @JsonProperty("assembly_source")
    public void setAssemblySource(java.lang.String assemblySource) {
        this.assemblySource = assemblySource;
    }

    public SummaryData withAssemblySource(java.lang.String assemblySource) {
        this.assemblySource = assemblySource;
        return this;
    }

    @JsonProperty("assembly_source_id")
    public java.lang.String getAssemblySourceId() {
        return assemblySourceId;
    }

    @JsonProperty("assembly_source_id")
    public void setAssemblySourceId(java.lang.String assemblySourceId) {
        this.assemblySourceId = assemblySourceId;
    }

    public SummaryData withAssemblySourceId(java.lang.String assemblySourceId) {
        this.assemblySourceId = assemblySourceId;
        return this;
    }

    @JsonProperty("assembly_source_date")
    public java.lang.String getAssemblySourceDate() {
        return assemblySourceDate;
    }

    @JsonProperty("assembly_source_date")
    public void setAssemblySourceDate(java.lang.String assemblySourceDate) {
        this.assemblySourceDate = assemblySourceDate;
    }

    public SummaryData withAssemblySourceDate(java.lang.String assemblySourceDate) {
        this.assemblySourceDate = assemblySourceDate;
        return this;
    }

    @JsonProperty("gc_content")
    public Double getGcContent() {
        return gcContent;
    }

    @JsonProperty("gc_content")
    public void setGcContent(Double gcContent) {
        this.gcContent = gcContent;
    }

    public SummaryData withGcContent(Double gcContent) {
        this.gcContent = gcContent;
        return this;
    }

    @JsonProperty("dna_size")
    public java.lang.Long getDnaSize() {
        return dnaSize;
    }

    @JsonProperty("dna_size")
    public void setDnaSize(java.lang.Long dnaSize) {
        this.dnaSize = dnaSize;
    }

    public SummaryData withDnaSize(java.lang.Long dnaSize) {
        this.dnaSize = dnaSize;
        return this;
    }

    @JsonProperty("num_contigs")
    public java.lang.Long getNumContigs() {
        return numContigs;
    }

    @JsonProperty("num_contigs")
    public void setNumContigs(java.lang.Long numContigs) {
        this.numContigs = numContigs;
    }

    public SummaryData withNumContigs(java.lang.Long numContigs) {
        this.numContigs = numContigs;
        return this;
    }

    @JsonProperty("contig_ids")
    public List<String> getContigIds() {
        return contigIds;
    }

    @JsonProperty("contig_ids")
    public void setContigIds(List<String> contigIds) {
        this.contigIds = contigIds;
    }

    public SummaryData withContigIds(List<String> contigIds) {
        this.contigIds = contigIds;
        return this;
    }

    @JsonProperty("external_source")
    public java.lang.String getExternalSource() {
        return externalSource;
    }

    @JsonProperty("external_source")
    public void setExternalSource(java.lang.String externalSource) {
        this.externalSource = externalSource;
    }

    public SummaryData withExternalSource(java.lang.String externalSource) {
        this.externalSource = externalSource;
        return this;
    }

    @JsonProperty("external_source_date")
    public java.lang.String getExternalSourceDate() {
        return externalSourceDate;
    }

    @JsonProperty("external_source_date")
    public void setExternalSourceDate(java.lang.String externalSourceDate) {
        this.externalSourceDate = externalSourceDate;
    }

    public SummaryData withExternalSourceDate(java.lang.String externalSourceDate) {
        this.externalSourceDate = externalSourceDate;
        return this;
    }

    @JsonProperty("release")
    public java.lang.String getRelease() {
        return release;
    }

    @JsonProperty("release")
    public void setRelease(java.lang.String release) {
        this.release = release;
    }

    public SummaryData withRelease(java.lang.String release) {
        this.release = release;
        return this;
    }

    @JsonProperty("original_source_filename")
    public java.lang.String getOriginalSourceFilename() {
        return originalSourceFilename;
    }

    @JsonProperty("original_source_filename")
    public void setOriginalSourceFilename(java.lang.String originalSourceFilename) {
        this.originalSourceFilename = originalSourceFilename;
    }

    public SummaryData withOriginalSourceFilename(java.lang.String originalSourceFilename) {
        this.originalSourceFilename = originalSourceFilename;
        return this;
    }

    @JsonProperty("feature_type_counts")
    public Map<String, Long> getFeatureTypeCounts() {
        return featureTypeCounts;
    }

    @JsonProperty("feature_type_counts")
    public void setFeatureTypeCounts(Map<String, Long> featureTypeCounts) {
        this.featureTypeCounts = featureTypeCounts;
    }

    public SummaryData withFeatureTypeCounts(Map<String, Long> featureTypeCounts) {
        this.featureTypeCounts = featureTypeCounts;
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
        return ((((((((((((((((((((((((((((((((((((((("SummaryData"+" [scientificName=")+ scientificName)+", taxonomyId=")+ taxonomyId)+", kingdom=")+ kingdom)+", scientificLineage=")+ scientificLineage)+", geneticCode=")+ geneticCode)+", organismAliases=")+ organismAliases)+", assemblySource=")+ assemblySource)+", assemblySourceId=")+ assemblySourceId)+", assemblySourceDate=")+ assemblySourceDate)+", gcContent=")+ gcContent)+", dnaSize=")+ dnaSize)+", numContigs=")+ numContigs)+", contigIds=")+ contigIds)+", externalSource=")+ externalSource)+", externalSourceDate=")+ externalSourceDate)+", release=")+ release)+", originalSourceFilename=")+ originalSourceFilename)+", featureTypeCounts=")+ featureTypeCounts)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
