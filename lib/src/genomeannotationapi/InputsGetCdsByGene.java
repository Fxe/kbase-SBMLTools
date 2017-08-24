
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
 * <p>Original spec-file type: inputs_get_cds_by_gene</p>
 * <pre>
 * *
 * * Retrieves coding sequence Features (cds) for given gene Feature IDs.
 * *
 * * @param gene_id_list List of gene Feature IDS for which to retrieve CDS.
 * *     If empty, returns data for all features.
 * * @return Mapping of gene Feature IDs to a list of CDS Feature IDs.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "gene_id_list"
})
public class InputsGetCdsByGene {

    @JsonProperty("ref")
    private java.lang.String ref;
    @JsonProperty("gene_id_list")
    private List<String> geneIdList;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public InputsGetCdsByGene withRef(java.lang.String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("gene_id_list")
    public List<String> getGeneIdList() {
        return geneIdList;
    }

    @JsonProperty("gene_id_list")
    public void setGeneIdList(List<String> geneIdList) {
        this.geneIdList = geneIdList;
    }

    public InputsGetCdsByGene withGeneIdList(List<String> geneIdList) {
        this.geneIdList = geneIdList;
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
        return ((((((("InputsGetCdsByGene"+" [ref=")+ ref)+", geneIdList=")+ geneIdList)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
