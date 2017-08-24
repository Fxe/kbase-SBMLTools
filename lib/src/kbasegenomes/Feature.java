
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
import us.kbase.common.service.Tuple2;
import us.kbase.common.service.Tuple3;
import us.kbase.common.service.Tuple4;
import us.kbase.common.service.Tuple7;


/**
 * <p>Original spec-file type: Feature</p>
 * <pre>
 * Structure for a single feature of a genome
 *     
 *     Should genome_id contain the genome_id in the Genome object,
 *     the workspace id of the Genome object, a genomeref,
 *     something else?
 *     Should sequence be in separate objects too?
 *     We may want to add additional fields for other CDM functions
 *     (e.g., atomic regulons, coexpressed fids, co_occurring fids,...)
 *     @optional orthologs quality feature_creation_event md5 location function ontology_terms protein_translation protein_families subsystems publications subsystem_data aliases annotations regulon_data atomic_regulons coexpressed_fids co_occurring_fids dna_sequence protein_translation_length dna_sequence_length
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "location",
    "type",
    "function",
    "ontology_terms",
    "md5",
    "protein_translation",
    "dna_sequence",
    "protein_translation_length",
    "dna_sequence_length",
    "publications",
    "subsystems",
    "protein_families",
    "aliases",
    "orthologs",
    "annotations",
    "subsystem_data",
    "regulon_data",
    "atomic_regulons",
    "coexpressed_fids",
    "co_occurring_fids",
    "quality",
    "feature_creation_event"
})
public class Feature {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("location")
    private List<Tuple4 <String, Long, String, Long>> location;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("function")
    private java.lang.String function;
    @JsonProperty("ontology_terms")
    private Map<String, Map<String, OntologyData>> ontologyTerms;
    @JsonProperty("md5")
    private java.lang.String md5;
    @JsonProperty("protein_translation")
    private java.lang.String proteinTranslation;
    @JsonProperty("dna_sequence")
    private java.lang.String dnaSequence;
    @JsonProperty("protein_translation_length")
    private java.lang.Long proteinTranslationLength;
    @JsonProperty("dna_sequence_length")
    private java.lang.Long dnaSequenceLength;
    @JsonProperty("publications")
    private List<Tuple7 <Long, String, String, String, String, String, String>> publications;
    @JsonProperty("subsystems")
    private List<String> subsystems;
    @JsonProperty("protein_families")
    private List<ProteinFamily> proteinFamilies;
    @JsonProperty("aliases")
    private List<String> aliases;
    @JsonProperty("orthologs")
    private List<Tuple2 <String, Double>> orthologs;
    @JsonProperty("annotations")
    private List<Tuple3 <String, String, Double>> annotations;
    @JsonProperty("subsystem_data")
    private List<Tuple3 <String, String, String>> subsystemData;
    @JsonProperty("regulon_data")
    private List<Tuple3 <String, List<String> , List<String>>> regulonData;
    @JsonProperty("atomic_regulons")
    private List<Tuple2 <String, Long>> atomicRegulons;
    @JsonProperty("coexpressed_fids")
    private List<Tuple2 <String, Double>> coexpressedFids;
    @JsonProperty("co_occurring_fids")
    private List<Tuple2 <String, Double>> coOccurringFids;
    /**
     * <p>Original spec-file type: Feature_quality_measure</p>
     * <pre>
     * @optional weighted_hit_count hit_count existence_priority overlap_rules pyrrolysylprotein truncated_begin truncated_end existence_confidence frameshifted selenoprotein
     * </pre>
     * 
     */
    @JsonProperty("quality")
    private FeatureQualityMeasure quality;
    /**
     * <p>Original spec-file type: Analysis_event</p>
     * <pre>
     * @optional tool_name execution_time parameters hostname
     * </pre>
     * 
     */
    @JsonProperty("feature_creation_event")
    private AnalysisEvent featureCreationEvent;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Feature withId(java.lang.String id) {
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

    public Feature withLocation(List<Tuple4 <String, Long, String, Long>> location) {
        this.location = location;
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

    public Feature withType(java.lang.String type) {
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

    public Feature withFunction(java.lang.String function) {
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

    public Feature withOntologyTerms(Map<String, Map<String, OntologyData>> ontologyTerms) {
        this.ontologyTerms = ontologyTerms;
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

    public Feature withMd5(java.lang.String md5) {
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

    public Feature withProteinTranslation(java.lang.String proteinTranslation) {
        this.proteinTranslation = proteinTranslation;
        return this;
    }

    @JsonProperty("dna_sequence")
    public java.lang.String getDnaSequence() {
        return dnaSequence;
    }

    @JsonProperty("dna_sequence")
    public void setDnaSequence(java.lang.String dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    public Feature withDnaSequence(java.lang.String dnaSequence) {
        this.dnaSequence = dnaSequence;
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

    public Feature withProteinTranslationLength(java.lang.Long proteinTranslationLength) {
        this.proteinTranslationLength = proteinTranslationLength;
        return this;
    }

    @JsonProperty("dna_sequence_length")
    public java.lang.Long getDnaSequenceLength() {
        return dnaSequenceLength;
    }

    @JsonProperty("dna_sequence_length")
    public void setDnaSequenceLength(java.lang.Long dnaSequenceLength) {
        this.dnaSequenceLength = dnaSequenceLength;
    }

    public Feature withDnaSequenceLength(java.lang.Long dnaSequenceLength) {
        this.dnaSequenceLength = dnaSequenceLength;
        return this;
    }

    @JsonProperty("publications")
    public List<Tuple7 <Long, String, String, String, String, String, String>> getPublications() {
        return publications;
    }

    @JsonProperty("publications")
    public void setPublications(List<Tuple7 <Long, String, String, String, String, String, String>> publications) {
        this.publications = publications;
    }

    public Feature withPublications(List<Tuple7 <Long, String, String, String, String, String, String>> publications) {
        this.publications = publications;
        return this;
    }

    @JsonProperty("subsystems")
    public List<String> getSubsystems() {
        return subsystems;
    }

    @JsonProperty("subsystems")
    public void setSubsystems(List<String> subsystems) {
        this.subsystems = subsystems;
    }

    public Feature withSubsystems(List<String> subsystems) {
        this.subsystems = subsystems;
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

    public Feature withProteinFamilies(List<ProteinFamily> proteinFamilies) {
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

    public Feature withAliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }

    @JsonProperty("orthologs")
    public List<Tuple2 <String, Double>> getOrthologs() {
        return orthologs;
    }

    @JsonProperty("orthologs")
    public void setOrthologs(List<Tuple2 <String, Double>> orthologs) {
        this.orthologs = orthologs;
    }

    public Feature withOrthologs(List<Tuple2 <String, Double>> orthologs) {
        this.orthologs = orthologs;
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

    public Feature withAnnotations(List<Tuple3 <String, String, Double>> annotations) {
        this.annotations = annotations;
        return this;
    }

    @JsonProperty("subsystem_data")
    public List<Tuple3 <String, String, String>> getSubsystemData() {
        return subsystemData;
    }

    @JsonProperty("subsystem_data")
    public void setSubsystemData(List<Tuple3 <String, String, String>> subsystemData) {
        this.subsystemData = subsystemData;
    }

    public Feature withSubsystemData(List<Tuple3 <String, String, String>> subsystemData) {
        this.subsystemData = subsystemData;
        return this;
    }

    @JsonProperty("regulon_data")
    public List<Tuple3 <String, List<String> , List<String>>> getRegulonData() {
        return regulonData;
    }

    @JsonProperty("regulon_data")
    public void setRegulonData(List<Tuple3 <String, List<String> , List<String>>> regulonData) {
        this.regulonData = regulonData;
    }

    public Feature withRegulonData(List<Tuple3 <String, List<String> , List<String>>> regulonData) {
        this.regulonData = regulonData;
        return this;
    }

    @JsonProperty("atomic_regulons")
    public List<Tuple2 <String, Long>> getAtomicRegulons() {
        return atomicRegulons;
    }

    @JsonProperty("atomic_regulons")
    public void setAtomicRegulons(List<Tuple2 <String, Long>> atomicRegulons) {
        this.atomicRegulons = atomicRegulons;
    }

    public Feature withAtomicRegulons(List<Tuple2 <String, Long>> atomicRegulons) {
        this.atomicRegulons = atomicRegulons;
        return this;
    }

    @JsonProperty("coexpressed_fids")
    public List<Tuple2 <String, Double>> getCoexpressedFids() {
        return coexpressedFids;
    }

    @JsonProperty("coexpressed_fids")
    public void setCoexpressedFids(List<Tuple2 <String, Double>> coexpressedFids) {
        this.coexpressedFids = coexpressedFids;
    }

    public Feature withCoexpressedFids(List<Tuple2 <String, Double>> coexpressedFids) {
        this.coexpressedFids = coexpressedFids;
        return this;
    }

    @JsonProperty("co_occurring_fids")
    public List<Tuple2 <String, Double>> getCoOccurringFids() {
        return coOccurringFids;
    }

    @JsonProperty("co_occurring_fids")
    public void setCoOccurringFids(List<Tuple2 <String, Double>> coOccurringFids) {
        this.coOccurringFids = coOccurringFids;
    }

    public Feature withCoOccurringFids(List<Tuple2 <String, Double>> coOccurringFids) {
        this.coOccurringFids = coOccurringFids;
        return this;
    }

    /**
     * <p>Original spec-file type: Feature_quality_measure</p>
     * <pre>
     * @optional weighted_hit_count hit_count existence_priority overlap_rules pyrrolysylprotein truncated_begin truncated_end existence_confidence frameshifted selenoprotein
     * </pre>
     * 
     */
    @JsonProperty("quality")
    public FeatureQualityMeasure getQuality() {
        return quality;
    }

    /**
     * <p>Original spec-file type: Feature_quality_measure</p>
     * <pre>
     * @optional weighted_hit_count hit_count existence_priority overlap_rules pyrrolysylprotein truncated_begin truncated_end existence_confidence frameshifted selenoprotein
     * </pre>
     * 
     */
    @JsonProperty("quality")
    public void setQuality(FeatureQualityMeasure quality) {
        this.quality = quality;
    }

    public Feature withQuality(FeatureQualityMeasure quality) {
        this.quality = quality;
        return this;
    }

    /**
     * <p>Original spec-file type: Analysis_event</p>
     * <pre>
     * @optional tool_name execution_time parameters hostname
     * </pre>
     * 
     */
    @JsonProperty("feature_creation_event")
    public AnalysisEvent getFeatureCreationEvent() {
        return featureCreationEvent;
    }

    /**
     * <p>Original spec-file type: Analysis_event</p>
     * <pre>
     * @optional tool_name execution_time parameters hostname
     * </pre>
     * 
     */
    @JsonProperty("feature_creation_event")
    public void setFeatureCreationEvent(AnalysisEvent featureCreationEvent) {
        this.featureCreationEvent = featureCreationEvent;
    }

    public Feature withFeatureCreationEvent(AnalysisEvent featureCreationEvent) {
        this.featureCreationEvent = featureCreationEvent;
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
        return ((((((((((((((((((((((((((((((((((((((((((((((((("Feature"+" [id=")+ id)+", location=")+ location)+", type=")+ type)+", function=")+ function)+", ontologyTerms=")+ ontologyTerms)+", md5=")+ md5)+", proteinTranslation=")+ proteinTranslation)+", dnaSequence=")+ dnaSequence)+", proteinTranslationLength=")+ proteinTranslationLength)+", dnaSequenceLength=")+ dnaSequenceLength)+", publications=")+ publications)+", subsystems=")+ subsystems)+", proteinFamilies=")+ proteinFamilies)+", aliases=")+ aliases)+", orthologs=")+ orthologs)+", annotations=")+ annotations)+", subsystemData=")+ subsystemData)+", regulonData=")+ regulonData)+", atomicRegulons=")+ atomicRegulons)+", coexpressedFids=")+ coexpressedFids)+", coOccurringFids=")+ coOccurringFids)+", quality=")+ quality)+", featureCreationEvent=")+ featureCreationEvent)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
