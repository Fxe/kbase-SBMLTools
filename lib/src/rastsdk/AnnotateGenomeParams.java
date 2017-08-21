
package rastsdk;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: AnnotateGenomeParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "workspace",
    "input_genome",
    "input_contigset",
    "genetic_code",
    "domain",
    "scientific_name",
    "output_genome",
    "call_features_rRNA_SEED",
    "call_features_tRNA_trnascan",
    "call_selenoproteins",
    "call_pyrrolysoproteins",
    "call_features_repeat_region_SEED",
    "call_features_insertion_sequences",
    "call_features_strep_suis_repeat",
    "call_features_strep_pneumo_repeat",
    "call_features_crispr",
    "call_features_CDS_glimmer3",
    "call_features_CDS_prodigal",
    "call_features_CDS_genemark",
    "annotate_proteins_kmer_v2",
    "kmer_v1_parameters",
    "annotate_proteins_similarity",
    "resolve_overlapping_features",
    "find_close_neighbors",
    "call_features_prophage_phispy",
    "retain_old_anno_for_hypotheticals"
})
public class AnnotateGenomeParams {

    @JsonProperty("workspace")
    private String workspace;
    @JsonProperty("input_genome")
    private String inputGenome;
    @JsonProperty("input_contigset")
    private String inputContigset;
    @JsonProperty("genetic_code")
    private Long geneticCode;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("scientific_name")
    private String scientificName;
    @JsonProperty("output_genome")
    private String outputGenome;
    @JsonProperty("call_features_rRNA_SEED")
    private Long callFeaturesRRNASEED;
    @JsonProperty("call_features_tRNA_trnascan")
    private Long callFeaturesTRNATrnascan;
    @JsonProperty("call_selenoproteins")
    private Long callSelenoproteins;
    @JsonProperty("call_pyrrolysoproteins")
    private Long callPyrrolysoproteins;
    @JsonProperty("call_features_repeat_region_SEED")
    private Long callFeaturesRepeatRegionSEED;
    @JsonProperty("call_features_insertion_sequences")
    private Long callFeaturesInsertionSequences;
    @JsonProperty("call_features_strep_suis_repeat")
    private Long callFeaturesStrepSuisRepeat;
    @JsonProperty("call_features_strep_pneumo_repeat")
    private Long callFeaturesStrepPneumoRepeat;
    @JsonProperty("call_features_crispr")
    private Long callFeaturesCrispr;
    @JsonProperty("call_features_CDS_glimmer3")
    private Long callFeaturesCDSGlimmer3;
    @JsonProperty("call_features_CDS_prodigal")
    private Long callFeaturesCDSProdigal;
    @JsonProperty("call_features_CDS_genemark")
    private Long callFeaturesCDSGenemark;
    @JsonProperty("annotate_proteins_kmer_v2")
    private Long annotateProteinsKmerV2;
    @JsonProperty("kmer_v1_parameters")
    private Long kmerV1Parameters;
    @JsonProperty("annotate_proteins_similarity")
    private Long annotateProteinsSimilarity;
    @JsonProperty("resolve_overlapping_features")
    private Long resolveOverlappingFeatures;
    @JsonProperty("find_close_neighbors")
    private Long findCloseNeighbors;
    @JsonProperty("call_features_prophage_phispy")
    private Long callFeaturesProphagePhispy;
    @JsonProperty("retain_old_anno_for_hypotheticals")
    private Long retainOldAnnoForHypotheticals;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("workspace")
    public String getWorkspace() {
        return workspace;
    }

    @JsonProperty("workspace")
    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public AnnotateGenomeParams withWorkspace(String workspace) {
        this.workspace = workspace;
        return this;
    }

    @JsonProperty("input_genome")
    public String getInputGenome() {
        return inputGenome;
    }

    @JsonProperty("input_genome")
    public void setInputGenome(String inputGenome) {
        this.inputGenome = inputGenome;
    }

    public AnnotateGenomeParams withInputGenome(String inputGenome) {
        this.inputGenome = inputGenome;
        return this;
    }

    @JsonProperty("input_contigset")
    public String getInputContigset() {
        return inputContigset;
    }

    @JsonProperty("input_contigset")
    public void setInputContigset(String inputContigset) {
        this.inputContigset = inputContigset;
    }

    public AnnotateGenomeParams withInputContigset(String inputContigset) {
        this.inputContigset = inputContigset;
        return this;
    }

    @JsonProperty("genetic_code")
    public Long getGeneticCode() {
        return geneticCode;
    }

    @JsonProperty("genetic_code")
    public void setGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
    }

    public AnnotateGenomeParams withGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
        return this;
    }

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public AnnotateGenomeParams withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    @JsonProperty("scientific_name")
    public String getScientificName() {
        return scientificName;
    }

    @JsonProperty("scientific_name")
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public AnnotateGenomeParams withScientificName(String scientificName) {
        this.scientificName = scientificName;
        return this;
    }

    @JsonProperty("output_genome")
    public String getOutputGenome() {
        return outputGenome;
    }

    @JsonProperty("output_genome")
    public void setOutputGenome(String outputGenome) {
        this.outputGenome = outputGenome;
    }

    public AnnotateGenomeParams withOutputGenome(String outputGenome) {
        this.outputGenome = outputGenome;
        return this;
    }

    @JsonProperty("call_features_rRNA_SEED")
    public Long getCallFeaturesRRNASEED() {
        return callFeaturesRRNASEED;
    }

    @JsonProperty("call_features_rRNA_SEED")
    public void setCallFeaturesRRNASEED(Long callFeaturesRRNASEED) {
        this.callFeaturesRRNASEED = callFeaturesRRNASEED;
    }

    public AnnotateGenomeParams withCallFeaturesRRNASEED(Long callFeaturesRRNASEED) {
        this.callFeaturesRRNASEED = callFeaturesRRNASEED;
        return this;
    }

    @JsonProperty("call_features_tRNA_trnascan")
    public Long getCallFeaturesTRNATrnascan() {
        return callFeaturesTRNATrnascan;
    }

    @JsonProperty("call_features_tRNA_trnascan")
    public void setCallFeaturesTRNATrnascan(Long callFeaturesTRNATrnascan) {
        this.callFeaturesTRNATrnascan = callFeaturesTRNATrnascan;
    }

    public AnnotateGenomeParams withCallFeaturesTRNATrnascan(Long callFeaturesTRNATrnascan) {
        this.callFeaturesTRNATrnascan = callFeaturesTRNATrnascan;
        return this;
    }

    @JsonProperty("call_selenoproteins")
    public Long getCallSelenoproteins() {
        return callSelenoproteins;
    }

    @JsonProperty("call_selenoproteins")
    public void setCallSelenoproteins(Long callSelenoproteins) {
        this.callSelenoproteins = callSelenoproteins;
    }

    public AnnotateGenomeParams withCallSelenoproteins(Long callSelenoproteins) {
        this.callSelenoproteins = callSelenoproteins;
        return this;
    }

    @JsonProperty("call_pyrrolysoproteins")
    public Long getCallPyrrolysoproteins() {
        return callPyrrolysoproteins;
    }

    @JsonProperty("call_pyrrolysoproteins")
    public void setCallPyrrolysoproteins(Long callPyrrolysoproteins) {
        this.callPyrrolysoproteins = callPyrrolysoproteins;
    }

    public AnnotateGenomeParams withCallPyrrolysoproteins(Long callPyrrolysoproteins) {
        this.callPyrrolysoproteins = callPyrrolysoproteins;
        return this;
    }

    @JsonProperty("call_features_repeat_region_SEED")
    public Long getCallFeaturesRepeatRegionSEED() {
        return callFeaturesRepeatRegionSEED;
    }

    @JsonProperty("call_features_repeat_region_SEED")
    public void setCallFeaturesRepeatRegionSEED(Long callFeaturesRepeatRegionSEED) {
        this.callFeaturesRepeatRegionSEED = callFeaturesRepeatRegionSEED;
    }

    public AnnotateGenomeParams withCallFeaturesRepeatRegionSEED(Long callFeaturesRepeatRegionSEED) {
        this.callFeaturesRepeatRegionSEED = callFeaturesRepeatRegionSEED;
        return this;
    }

    @JsonProperty("call_features_insertion_sequences")
    public Long getCallFeaturesInsertionSequences() {
        return callFeaturesInsertionSequences;
    }

    @JsonProperty("call_features_insertion_sequences")
    public void setCallFeaturesInsertionSequences(Long callFeaturesInsertionSequences) {
        this.callFeaturesInsertionSequences = callFeaturesInsertionSequences;
    }

    public AnnotateGenomeParams withCallFeaturesInsertionSequences(Long callFeaturesInsertionSequences) {
        this.callFeaturesInsertionSequences = callFeaturesInsertionSequences;
        return this;
    }

    @JsonProperty("call_features_strep_suis_repeat")
    public Long getCallFeaturesStrepSuisRepeat() {
        return callFeaturesStrepSuisRepeat;
    }

    @JsonProperty("call_features_strep_suis_repeat")
    public void setCallFeaturesStrepSuisRepeat(Long callFeaturesStrepSuisRepeat) {
        this.callFeaturesStrepSuisRepeat = callFeaturesStrepSuisRepeat;
    }

    public AnnotateGenomeParams withCallFeaturesStrepSuisRepeat(Long callFeaturesStrepSuisRepeat) {
        this.callFeaturesStrepSuisRepeat = callFeaturesStrepSuisRepeat;
        return this;
    }

    @JsonProperty("call_features_strep_pneumo_repeat")
    public Long getCallFeaturesStrepPneumoRepeat() {
        return callFeaturesStrepPneumoRepeat;
    }

    @JsonProperty("call_features_strep_pneumo_repeat")
    public void setCallFeaturesStrepPneumoRepeat(Long callFeaturesStrepPneumoRepeat) {
        this.callFeaturesStrepPneumoRepeat = callFeaturesStrepPneumoRepeat;
    }

    public AnnotateGenomeParams withCallFeaturesStrepPneumoRepeat(Long callFeaturesStrepPneumoRepeat) {
        this.callFeaturesStrepPneumoRepeat = callFeaturesStrepPneumoRepeat;
        return this;
    }

    @JsonProperty("call_features_crispr")
    public Long getCallFeaturesCrispr() {
        return callFeaturesCrispr;
    }

    @JsonProperty("call_features_crispr")
    public void setCallFeaturesCrispr(Long callFeaturesCrispr) {
        this.callFeaturesCrispr = callFeaturesCrispr;
    }

    public AnnotateGenomeParams withCallFeaturesCrispr(Long callFeaturesCrispr) {
        this.callFeaturesCrispr = callFeaturesCrispr;
        return this;
    }

    @JsonProperty("call_features_CDS_glimmer3")
    public Long getCallFeaturesCDSGlimmer3() {
        return callFeaturesCDSGlimmer3;
    }

    @JsonProperty("call_features_CDS_glimmer3")
    public void setCallFeaturesCDSGlimmer3(Long callFeaturesCDSGlimmer3) {
        this.callFeaturesCDSGlimmer3 = callFeaturesCDSGlimmer3;
    }

    public AnnotateGenomeParams withCallFeaturesCDSGlimmer3(Long callFeaturesCDSGlimmer3) {
        this.callFeaturesCDSGlimmer3 = callFeaturesCDSGlimmer3;
        return this;
    }

    @JsonProperty("call_features_CDS_prodigal")
    public Long getCallFeaturesCDSProdigal() {
        return callFeaturesCDSProdigal;
    }

    @JsonProperty("call_features_CDS_prodigal")
    public void setCallFeaturesCDSProdigal(Long callFeaturesCDSProdigal) {
        this.callFeaturesCDSProdigal = callFeaturesCDSProdigal;
    }

    public AnnotateGenomeParams withCallFeaturesCDSProdigal(Long callFeaturesCDSProdigal) {
        this.callFeaturesCDSProdigal = callFeaturesCDSProdigal;
        return this;
    }

    @JsonProperty("call_features_CDS_genemark")
    public Long getCallFeaturesCDSGenemark() {
        return callFeaturesCDSGenemark;
    }

    @JsonProperty("call_features_CDS_genemark")
    public void setCallFeaturesCDSGenemark(Long callFeaturesCDSGenemark) {
        this.callFeaturesCDSGenemark = callFeaturesCDSGenemark;
    }

    public AnnotateGenomeParams withCallFeaturesCDSGenemark(Long callFeaturesCDSGenemark) {
        this.callFeaturesCDSGenemark = callFeaturesCDSGenemark;
        return this;
    }

    @JsonProperty("annotate_proteins_kmer_v2")
    public Long getAnnotateProteinsKmerV2() {
        return annotateProteinsKmerV2;
    }

    @JsonProperty("annotate_proteins_kmer_v2")
    public void setAnnotateProteinsKmerV2(Long annotateProteinsKmerV2) {
        this.annotateProteinsKmerV2 = annotateProteinsKmerV2;
    }

    public AnnotateGenomeParams withAnnotateProteinsKmerV2(Long annotateProteinsKmerV2) {
        this.annotateProteinsKmerV2 = annotateProteinsKmerV2;
        return this;
    }

    @JsonProperty("kmer_v1_parameters")
    public Long getKmerV1Parameters() {
        return kmerV1Parameters;
    }

    @JsonProperty("kmer_v1_parameters")
    public void setKmerV1Parameters(Long kmerV1Parameters) {
        this.kmerV1Parameters = kmerV1Parameters;
    }

    public AnnotateGenomeParams withKmerV1Parameters(Long kmerV1Parameters) {
        this.kmerV1Parameters = kmerV1Parameters;
        return this;
    }

    @JsonProperty("annotate_proteins_similarity")
    public Long getAnnotateProteinsSimilarity() {
        return annotateProteinsSimilarity;
    }

    @JsonProperty("annotate_proteins_similarity")
    public void setAnnotateProteinsSimilarity(Long annotateProteinsSimilarity) {
        this.annotateProteinsSimilarity = annotateProteinsSimilarity;
    }

    public AnnotateGenomeParams withAnnotateProteinsSimilarity(Long annotateProteinsSimilarity) {
        this.annotateProteinsSimilarity = annotateProteinsSimilarity;
        return this;
    }

    @JsonProperty("resolve_overlapping_features")
    public Long getResolveOverlappingFeatures() {
        return resolveOverlappingFeatures;
    }

    @JsonProperty("resolve_overlapping_features")
    public void setResolveOverlappingFeatures(Long resolveOverlappingFeatures) {
        this.resolveOverlappingFeatures = resolveOverlappingFeatures;
    }

    public AnnotateGenomeParams withResolveOverlappingFeatures(Long resolveOverlappingFeatures) {
        this.resolveOverlappingFeatures = resolveOverlappingFeatures;
        return this;
    }

    @JsonProperty("find_close_neighbors")
    public Long getFindCloseNeighbors() {
        return findCloseNeighbors;
    }

    @JsonProperty("find_close_neighbors")
    public void setFindCloseNeighbors(Long findCloseNeighbors) {
        this.findCloseNeighbors = findCloseNeighbors;
    }

    public AnnotateGenomeParams withFindCloseNeighbors(Long findCloseNeighbors) {
        this.findCloseNeighbors = findCloseNeighbors;
        return this;
    }

    @JsonProperty("call_features_prophage_phispy")
    public Long getCallFeaturesProphagePhispy() {
        return callFeaturesProphagePhispy;
    }

    @JsonProperty("call_features_prophage_phispy")
    public void setCallFeaturesProphagePhispy(Long callFeaturesProphagePhispy) {
        this.callFeaturesProphagePhispy = callFeaturesProphagePhispy;
    }

    public AnnotateGenomeParams withCallFeaturesProphagePhispy(Long callFeaturesProphagePhispy) {
        this.callFeaturesProphagePhispy = callFeaturesProphagePhispy;
        return this;
    }

    @JsonProperty("retain_old_anno_for_hypotheticals")
    public Long getRetainOldAnnoForHypotheticals() {
        return retainOldAnnoForHypotheticals;
    }

    @JsonProperty("retain_old_anno_for_hypotheticals")
    public void setRetainOldAnnoForHypotheticals(Long retainOldAnnoForHypotheticals) {
        this.retainOldAnnoForHypotheticals = retainOldAnnoForHypotheticals;
    }

    public AnnotateGenomeParams withRetainOldAnnoForHypotheticals(Long retainOldAnnoForHypotheticals) {
        this.retainOldAnnoForHypotheticals = retainOldAnnoForHypotheticals;
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
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((("AnnotateGenomeParams"+" [workspace=")+ workspace)+", inputGenome=")+ inputGenome)+", inputContigset=")+ inputContigset)+", geneticCode=")+ geneticCode)+", domain=")+ domain)+", scientificName=")+ scientificName)+", outputGenome=")+ outputGenome)+", callFeaturesRRNASEED=")+ callFeaturesRRNASEED)+", callFeaturesTRNATrnascan=")+ callFeaturesTRNATrnascan)+", callSelenoproteins=")+ callSelenoproteins)+", callPyrrolysoproteins=")+ callPyrrolysoproteins)+", callFeaturesRepeatRegionSEED=")+ callFeaturesRepeatRegionSEED)+", callFeaturesInsertionSequences=")+ callFeaturesInsertionSequences)+", callFeaturesStrepSuisRepeat=")+ callFeaturesStrepSuisRepeat)+", callFeaturesStrepPneumoRepeat=")+ callFeaturesStrepPneumoRepeat)+", callFeaturesCrispr=")+ callFeaturesCrispr)+", callFeaturesCDSGlimmer3=")+ callFeaturesCDSGlimmer3)+", callFeaturesCDSProdigal=")+ callFeaturesCDSProdigal)+", callFeaturesCDSGenemark=")+ callFeaturesCDSGenemark)+", annotateProteinsKmerV2=")+ annotateProteinsKmerV2)+", kmerV1Parameters=")+ kmerV1Parameters)+", annotateProteinsSimilarity=")+ annotateProteinsSimilarity)+", resolveOverlappingFeatures=")+ resolveOverlappingFeatures)+", findCloseNeighbors=")+ findCloseNeighbors)+", callFeaturesProphagePhispy=")+ callFeaturesProphagePhispy)+", retainOldAnnoForHypotheticals=")+ retainOldAnnoForHypotheticals)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
