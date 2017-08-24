
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
 * <p>Original spec-file type: GenomeAnnotation_data</p>
 * <pre>
 * gene_id is a feature id of a gene feature.
 * mrna_id is a feature id of a mrna feature.
 * cds_id is a feature id of a cds feature.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "gene_type",
    "mrna_type",
    "cds_type",
    "feature_types",
    "feature_by_id_by_type",
    "protein_by_cds_id",
    "mrna_ids_by_gene_id",
    "cds_ids_by_gene_id",
    "cds_id_by_mrna_id",
    "exons_by_mrna_id",
    "utr_by_utr_type_by_mrna_id",
    "summary"
})
public class GenomeAnnotationData {

    @JsonProperty("gene_type")
    private java.lang.String geneType;
    @JsonProperty("mrna_type")
    private java.lang.String mrnaType;
    @JsonProperty("cds_type")
    private java.lang.String cdsType;
    @JsonProperty("feature_types")
    private List<String> featureTypes;
    @JsonProperty("feature_by_id_by_type")
    private Map<String, Map<String, FeatureData>> featureByIdByType;
    @JsonProperty("protein_by_cds_id")
    private Map<String, ProteinData> proteinByCdsId;
    @JsonProperty("mrna_ids_by_gene_id")
    private Map<String, List<String>> mrnaIdsByGeneId;
    @JsonProperty("cds_ids_by_gene_id")
    private Map<String, List<String>> cdsIdsByGeneId;
    @JsonProperty("cds_id_by_mrna_id")
    private Map<String, String> cdsIdByMrnaId;
    @JsonProperty("exons_by_mrna_id")
    private Map<String, List<ExonData>> exonsByMrnaId;
    @JsonProperty("utr_by_utr_type_by_mrna_id")
    private Map<String, Map<String, UTRData>> utrByUtrTypeByMrnaId;
    /**
     * <p>Original spec-file type: Summary_data</p>
     * 
     * 
     */
    @JsonProperty("summary")
    private SummaryData summary;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("gene_type")
    public java.lang.String getGeneType() {
        return geneType;
    }

    @JsonProperty("gene_type")
    public void setGeneType(java.lang.String geneType) {
        this.geneType = geneType;
    }

    public GenomeAnnotationData withGeneType(java.lang.String geneType) {
        this.geneType = geneType;
        return this;
    }

    @JsonProperty("mrna_type")
    public java.lang.String getMrnaType() {
        return mrnaType;
    }

    @JsonProperty("mrna_type")
    public void setMrnaType(java.lang.String mrnaType) {
        this.mrnaType = mrnaType;
    }

    public GenomeAnnotationData withMrnaType(java.lang.String mrnaType) {
        this.mrnaType = mrnaType;
        return this;
    }

    @JsonProperty("cds_type")
    public java.lang.String getCdsType() {
        return cdsType;
    }

    @JsonProperty("cds_type")
    public void setCdsType(java.lang.String cdsType) {
        this.cdsType = cdsType;
    }

    public GenomeAnnotationData withCdsType(java.lang.String cdsType) {
        this.cdsType = cdsType;
        return this;
    }

    @JsonProperty("feature_types")
    public List<String> getFeatureTypes() {
        return featureTypes;
    }

    @JsonProperty("feature_types")
    public void setFeatureTypes(List<String> featureTypes) {
        this.featureTypes = featureTypes;
    }

    public GenomeAnnotationData withFeatureTypes(List<String> featureTypes) {
        this.featureTypes = featureTypes;
        return this;
    }

    @JsonProperty("feature_by_id_by_type")
    public Map<String, Map<String, FeatureData>> getFeatureByIdByType() {
        return featureByIdByType;
    }

    @JsonProperty("feature_by_id_by_type")
    public void setFeatureByIdByType(Map<String, Map<String, FeatureData>> featureByIdByType) {
        this.featureByIdByType = featureByIdByType;
    }

    public GenomeAnnotationData withFeatureByIdByType(Map<String, Map<String, FeatureData>> featureByIdByType) {
        this.featureByIdByType = featureByIdByType;
        return this;
    }

    @JsonProperty("protein_by_cds_id")
    public Map<String, ProteinData> getProteinByCdsId() {
        return proteinByCdsId;
    }

    @JsonProperty("protein_by_cds_id")
    public void setProteinByCdsId(Map<String, ProteinData> proteinByCdsId) {
        this.proteinByCdsId = proteinByCdsId;
    }

    public GenomeAnnotationData withProteinByCdsId(Map<String, ProteinData> proteinByCdsId) {
        this.proteinByCdsId = proteinByCdsId;
        return this;
    }

    @JsonProperty("mrna_ids_by_gene_id")
    public Map<String, List<String>> getMrnaIdsByGeneId() {
        return mrnaIdsByGeneId;
    }

    @JsonProperty("mrna_ids_by_gene_id")
    public void setMrnaIdsByGeneId(Map<String, List<String>> mrnaIdsByGeneId) {
        this.mrnaIdsByGeneId = mrnaIdsByGeneId;
    }

    public GenomeAnnotationData withMrnaIdsByGeneId(Map<String, List<String>> mrnaIdsByGeneId) {
        this.mrnaIdsByGeneId = mrnaIdsByGeneId;
        return this;
    }

    @JsonProperty("cds_ids_by_gene_id")
    public Map<String, List<String>> getCdsIdsByGeneId() {
        return cdsIdsByGeneId;
    }

    @JsonProperty("cds_ids_by_gene_id")
    public void setCdsIdsByGeneId(Map<String, List<String>> cdsIdsByGeneId) {
        this.cdsIdsByGeneId = cdsIdsByGeneId;
    }

    public GenomeAnnotationData withCdsIdsByGeneId(Map<String, List<String>> cdsIdsByGeneId) {
        this.cdsIdsByGeneId = cdsIdsByGeneId;
        return this;
    }

    @JsonProperty("cds_id_by_mrna_id")
    public Map<String, String> getCdsIdByMrnaId() {
        return cdsIdByMrnaId;
    }

    @JsonProperty("cds_id_by_mrna_id")
    public void setCdsIdByMrnaId(Map<String, String> cdsIdByMrnaId) {
        this.cdsIdByMrnaId = cdsIdByMrnaId;
    }

    public GenomeAnnotationData withCdsIdByMrnaId(Map<String, String> cdsIdByMrnaId) {
        this.cdsIdByMrnaId = cdsIdByMrnaId;
        return this;
    }

    @JsonProperty("exons_by_mrna_id")
    public Map<String, List<ExonData>> getExonsByMrnaId() {
        return exonsByMrnaId;
    }

    @JsonProperty("exons_by_mrna_id")
    public void setExonsByMrnaId(Map<String, List<ExonData>> exonsByMrnaId) {
        this.exonsByMrnaId = exonsByMrnaId;
    }

    public GenomeAnnotationData withExonsByMrnaId(Map<String, List<ExonData>> exonsByMrnaId) {
        this.exonsByMrnaId = exonsByMrnaId;
        return this;
    }

    @JsonProperty("utr_by_utr_type_by_mrna_id")
    public Map<String, Map<String, UTRData>> getUtrByUtrTypeByMrnaId() {
        return utrByUtrTypeByMrnaId;
    }

    @JsonProperty("utr_by_utr_type_by_mrna_id")
    public void setUtrByUtrTypeByMrnaId(Map<String, Map<String, UTRData>> utrByUtrTypeByMrnaId) {
        this.utrByUtrTypeByMrnaId = utrByUtrTypeByMrnaId;
    }

    public GenomeAnnotationData withUtrByUtrTypeByMrnaId(Map<String, Map<String, UTRData>> utrByUtrTypeByMrnaId) {
        this.utrByUtrTypeByMrnaId = utrByUtrTypeByMrnaId;
        return this;
    }

    /**
     * <p>Original spec-file type: Summary_data</p>
     * 
     * 
     */
    @JsonProperty("summary")
    public SummaryData getSummary() {
        return summary;
    }

    /**
     * <p>Original spec-file type: Summary_data</p>
     * 
     * 
     */
    @JsonProperty("summary")
    public void setSummary(SummaryData summary) {
        this.summary = summary;
    }

    public GenomeAnnotationData withSummary(SummaryData summary) {
        this.summary = summary;
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
        return ((((((((((((((((((((((((((("GenomeAnnotationData"+" [geneType=")+ geneType)+", mrnaType=")+ mrnaType)+", cdsType=")+ cdsType)+", featureTypes=")+ featureTypes)+", featureByIdByType=")+ featureByIdByType)+", proteinByCdsId=")+ proteinByCdsId)+", mrnaIdsByGeneId=")+ mrnaIdsByGeneId)+", cdsIdsByGeneId=")+ cdsIdsByGeneId)+", cdsIdByMrnaId=")+ cdsIdByMrnaId)+", exonsByMrnaId=")+ exonsByMrnaId)+", utrByUtrTypeByMrnaId=")+ utrByUtrTypeByMrnaId)+", summary=")+ summary)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
