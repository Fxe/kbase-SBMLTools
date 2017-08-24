
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
 * <p>Original spec-file type: GetCombinedDataParams</p>
 * <pre>
 * * Retrieve any part of GenomeAnnotation.
 * * Any of exclude_genes, include_mrnas and exclude_cdss flags override values listed in include_features_by_type.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "exclude_genes",
    "include_mrnas",
    "exclude_cdss",
    "include_features_by_type",
    "exclude_protein_by_cds_id",
    "include_mrna_ids_by_gene_id",
    "exclude_cds_ids_by_gene_id",
    "include_cds_id_by_mrna_id",
    "include_exons_by_mrna_id",
    "include_utr_by_utr_type_by_mrna_id",
    "exclude_summary"
})
public class GetCombinedDataParams {

    @JsonProperty("ref")
    private java.lang.String ref;
    @JsonProperty("exclude_genes")
    private Long excludeGenes;
    @JsonProperty("include_mrnas")
    private Long includeMrnas;
    @JsonProperty("exclude_cdss")
    private Long excludeCdss;
    @JsonProperty("include_features_by_type")
    private List<String> includeFeaturesByType;
    @JsonProperty("exclude_protein_by_cds_id")
    private Long excludeProteinByCdsId;
    @JsonProperty("include_mrna_ids_by_gene_id")
    private Long includeMrnaIdsByGeneId;
    @JsonProperty("exclude_cds_ids_by_gene_id")
    private Long excludeCdsIdsByGeneId;
    @JsonProperty("include_cds_id_by_mrna_id")
    private Long includeCdsIdByMrnaId;
    @JsonProperty("include_exons_by_mrna_id")
    private Long includeExonsByMrnaId;
    @JsonProperty("include_utr_by_utr_type_by_mrna_id")
    private Long includeUtrByUtrTypeByMrnaId;
    @JsonProperty("exclude_summary")
    private Long excludeSummary;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public GetCombinedDataParams withRef(java.lang.String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("exclude_genes")
    public Long getExcludeGenes() {
        return excludeGenes;
    }

    @JsonProperty("exclude_genes")
    public void setExcludeGenes(Long excludeGenes) {
        this.excludeGenes = excludeGenes;
    }

    public GetCombinedDataParams withExcludeGenes(Long excludeGenes) {
        this.excludeGenes = excludeGenes;
        return this;
    }

    @JsonProperty("include_mrnas")
    public Long getIncludeMrnas() {
        return includeMrnas;
    }

    @JsonProperty("include_mrnas")
    public void setIncludeMrnas(Long includeMrnas) {
        this.includeMrnas = includeMrnas;
    }

    public GetCombinedDataParams withIncludeMrnas(Long includeMrnas) {
        this.includeMrnas = includeMrnas;
        return this;
    }

    @JsonProperty("exclude_cdss")
    public Long getExcludeCdss() {
        return excludeCdss;
    }

    @JsonProperty("exclude_cdss")
    public void setExcludeCdss(Long excludeCdss) {
        this.excludeCdss = excludeCdss;
    }

    public GetCombinedDataParams withExcludeCdss(Long excludeCdss) {
        this.excludeCdss = excludeCdss;
        return this;
    }

    @JsonProperty("include_features_by_type")
    public List<String> getIncludeFeaturesByType() {
        return includeFeaturesByType;
    }

    @JsonProperty("include_features_by_type")
    public void setIncludeFeaturesByType(List<String> includeFeaturesByType) {
        this.includeFeaturesByType = includeFeaturesByType;
    }

    public GetCombinedDataParams withIncludeFeaturesByType(List<String> includeFeaturesByType) {
        this.includeFeaturesByType = includeFeaturesByType;
        return this;
    }

    @JsonProperty("exclude_protein_by_cds_id")
    public Long getExcludeProteinByCdsId() {
        return excludeProteinByCdsId;
    }

    @JsonProperty("exclude_protein_by_cds_id")
    public void setExcludeProteinByCdsId(Long excludeProteinByCdsId) {
        this.excludeProteinByCdsId = excludeProteinByCdsId;
    }

    public GetCombinedDataParams withExcludeProteinByCdsId(Long excludeProteinByCdsId) {
        this.excludeProteinByCdsId = excludeProteinByCdsId;
        return this;
    }

    @JsonProperty("include_mrna_ids_by_gene_id")
    public Long getIncludeMrnaIdsByGeneId() {
        return includeMrnaIdsByGeneId;
    }

    @JsonProperty("include_mrna_ids_by_gene_id")
    public void setIncludeMrnaIdsByGeneId(Long includeMrnaIdsByGeneId) {
        this.includeMrnaIdsByGeneId = includeMrnaIdsByGeneId;
    }

    public GetCombinedDataParams withIncludeMrnaIdsByGeneId(Long includeMrnaIdsByGeneId) {
        this.includeMrnaIdsByGeneId = includeMrnaIdsByGeneId;
        return this;
    }

    @JsonProperty("exclude_cds_ids_by_gene_id")
    public Long getExcludeCdsIdsByGeneId() {
        return excludeCdsIdsByGeneId;
    }

    @JsonProperty("exclude_cds_ids_by_gene_id")
    public void setExcludeCdsIdsByGeneId(Long excludeCdsIdsByGeneId) {
        this.excludeCdsIdsByGeneId = excludeCdsIdsByGeneId;
    }

    public GetCombinedDataParams withExcludeCdsIdsByGeneId(Long excludeCdsIdsByGeneId) {
        this.excludeCdsIdsByGeneId = excludeCdsIdsByGeneId;
        return this;
    }

    @JsonProperty("include_cds_id_by_mrna_id")
    public Long getIncludeCdsIdByMrnaId() {
        return includeCdsIdByMrnaId;
    }

    @JsonProperty("include_cds_id_by_mrna_id")
    public void setIncludeCdsIdByMrnaId(Long includeCdsIdByMrnaId) {
        this.includeCdsIdByMrnaId = includeCdsIdByMrnaId;
    }

    public GetCombinedDataParams withIncludeCdsIdByMrnaId(Long includeCdsIdByMrnaId) {
        this.includeCdsIdByMrnaId = includeCdsIdByMrnaId;
        return this;
    }

    @JsonProperty("include_exons_by_mrna_id")
    public Long getIncludeExonsByMrnaId() {
        return includeExonsByMrnaId;
    }

    @JsonProperty("include_exons_by_mrna_id")
    public void setIncludeExonsByMrnaId(Long includeExonsByMrnaId) {
        this.includeExonsByMrnaId = includeExonsByMrnaId;
    }

    public GetCombinedDataParams withIncludeExonsByMrnaId(Long includeExonsByMrnaId) {
        this.includeExonsByMrnaId = includeExonsByMrnaId;
        return this;
    }

    @JsonProperty("include_utr_by_utr_type_by_mrna_id")
    public Long getIncludeUtrByUtrTypeByMrnaId() {
        return includeUtrByUtrTypeByMrnaId;
    }

    @JsonProperty("include_utr_by_utr_type_by_mrna_id")
    public void setIncludeUtrByUtrTypeByMrnaId(Long includeUtrByUtrTypeByMrnaId) {
        this.includeUtrByUtrTypeByMrnaId = includeUtrByUtrTypeByMrnaId;
    }

    public GetCombinedDataParams withIncludeUtrByUtrTypeByMrnaId(Long includeUtrByUtrTypeByMrnaId) {
        this.includeUtrByUtrTypeByMrnaId = includeUtrByUtrTypeByMrnaId;
        return this;
    }

    @JsonProperty("exclude_summary")
    public Long getExcludeSummary() {
        return excludeSummary;
    }

    @JsonProperty("exclude_summary")
    public void setExcludeSummary(Long excludeSummary) {
        this.excludeSummary = excludeSummary;
    }

    public GetCombinedDataParams withExcludeSummary(Long excludeSummary) {
        this.excludeSummary = excludeSummary;
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
        return ((((((((((((((((((((((((((("GetCombinedDataParams"+" [ref=")+ ref)+", excludeGenes=")+ excludeGenes)+", includeMrnas=")+ includeMrnas)+", excludeCdss=")+ excludeCdss)+", includeFeaturesByType=")+ includeFeaturesByType)+", excludeProteinByCdsId=")+ excludeProteinByCdsId)+", includeMrnaIdsByGeneId=")+ includeMrnaIdsByGeneId)+", excludeCdsIdsByGeneId=")+ excludeCdsIdsByGeneId)+", includeCdsIdByMrnaId=")+ includeCdsIdByMrnaId)+", includeExonsByMrnaId=")+ includeExonsByMrnaId)+", includeUtrByUtrTypeByMrnaId=")+ includeUtrByUtrTypeByMrnaId)+", excludeSummary=")+ excludeSummary)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
