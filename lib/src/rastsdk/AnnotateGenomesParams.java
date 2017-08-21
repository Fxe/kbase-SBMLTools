
package rastsdk;

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
 * <p>Original spec-file type: AnnotateGenomesParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "workspace",
    "genomes",
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
public class AnnotateGenomesParams {

    @JsonProperty("workspace")
    private String workspace;
    @JsonProperty("genomes")
    private List<GenomeParams> genomes;
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

    public AnnotateGenomesParams withWorkspace(String workspace) {
        this.workspace = workspace;
        return this;
    }

    @JsonProperty("genomes")
    public List<GenomeParams> getGenomes() {
        return genomes;
    }

    @JsonProperty("genomes")
    public void setGenomes(List<GenomeParams> genomes) {
        this.genomes = genomes;
    }

    public AnnotateGenomesParams withGenomes(List<GenomeParams> genomes) {
        this.genomes = genomes;
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

    public AnnotateGenomesParams withCallFeaturesRRNASEED(Long callFeaturesRRNASEED) {
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

    public AnnotateGenomesParams withCallFeaturesTRNATrnascan(Long callFeaturesTRNATrnascan) {
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

    public AnnotateGenomesParams withCallSelenoproteins(Long callSelenoproteins) {
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

    public AnnotateGenomesParams withCallPyrrolysoproteins(Long callPyrrolysoproteins) {
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

    public AnnotateGenomesParams withCallFeaturesRepeatRegionSEED(Long callFeaturesRepeatRegionSEED) {
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

    public AnnotateGenomesParams withCallFeaturesInsertionSequences(Long callFeaturesInsertionSequences) {
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

    public AnnotateGenomesParams withCallFeaturesStrepSuisRepeat(Long callFeaturesStrepSuisRepeat) {
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

    public AnnotateGenomesParams withCallFeaturesStrepPneumoRepeat(Long callFeaturesStrepPneumoRepeat) {
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

    public AnnotateGenomesParams withCallFeaturesCrispr(Long callFeaturesCrispr) {
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

    public AnnotateGenomesParams withCallFeaturesCDSGlimmer3(Long callFeaturesCDSGlimmer3) {
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

    public AnnotateGenomesParams withCallFeaturesCDSProdigal(Long callFeaturesCDSProdigal) {
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

    public AnnotateGenomesParams withCallFeaturesCDSGenemark(Long callFeaturesCDSGenemark) {
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

    public AnnotateGenomesParams withAnnotateProteinsKmerV2(Long annotateProteinsKmerV2) {
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

    public AnnotateGenomesParams withKmerV1Parameters(Long kmerV1Parameters) {
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

    public AnnotateGenomesParams withAnnotateProteinsSimilarity(Long annotateProteinsSimilarity) {
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

    public AnnotateGenomesParams withResolveOverlappingFeatures(Long resolveOverlappingFeatures) {
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

    public AnnotateGenomesParams withFindCloseNeighbors(Long findCloseNeighbors) {
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

    public AnnotateGenomesParams withCallFeaturesProphagePhispy(Long callFeaturesProphagePhispy) {
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

    public AnnotateGenomesParams withRetainOldAnnoForHypotheticals(Long retainOldAnnoForHypotheticals) {
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
        return ((((((((((((((((((((((((((((((((((((((((((((("AnnotateGenomesParams"+" [workspace=")+ workspace)+", genomes=")+ genomes)+", callFeaturesRRNASEED=")+ callFeaturesRRNASEED)+", callFeaturesTRNATrnascan=")+ callFeaturesTRNATrnascan)+", callSelenoproteins=")+ callSelenoproteins)+", callPyrrolysoproteins=")+ callPyrrolysoproteins)+", callFeaturesRepeatRegionSEED=")+ callFeaturesRepeatRegionSEED)+", callFeaturesInsertionSequences=")+ callFeaturesInsertionSequences)+", callFeaturesStrepSuisRepeat=")+ callFeaturesStrepSuisRepeat)+", callFeaturesStrepPneumoRepeat=")+ callFeaturesStrepPneumoRepeat)+", callFeaturesCrispr=")+ callFeaturesCrispr)+", callFeaturesCDSGlimmer3=")+ callFeaturesCDSGlimmer3)+", callFeaturesCDSProdigal=")+ callFeaturesCDSProdigal)+", callFeaturesCDSGenemark=")+ callFeaturesCDSGenemark)+", annotateProteinsKmerV2=")+ annotateProteinsKmerV2)+", kmerV1Parameters=")+ kmerV1Parameters)+", annotateProteinsSimilarity=")+ annotateProteinsSimilarity)+", resolveOverlappingFeatures=")+ resolveOverlappingFeatures)+", findCloseNeighbors=")+ findCloseNeighbors)+", callFeaturesProphagePhispy=")+ callFeaturesProphagePhispy)+", retainOldAnnoForHypotheticals=")+ retainOldAnnoForHypotheticals)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
