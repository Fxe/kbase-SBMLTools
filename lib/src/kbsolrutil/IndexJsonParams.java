
package kbsolrutil;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: IndexJsonParams</p>
 * <pre>
 * Arguments for the add_json_2solr function - send a JSON doc data to solr for indexing
 * string solr_core - the name of the solr core to index to
 * string json_data - the doc to be indexed, a JSON string 
 *  =for example:
 *      $json_data = '[
 *      {
 * "taxonomy_id":1297193,
 * "domain":"Eukaryota",
 * "genetic_code":1,
 * "embl_code":"CS",
 * "division_id":1,
 * "inherited_div_flag":1,
 * "inherited_MGC_flag":1,
 * "parent_taxon_ref":"12570/1217907/1",
 * "scientific_name":"Camponotus sp. MAS010",
 * "mitochondrial_genetic_code":5,
 * "hidden_subtree_flag":0,
 * "scientific_lineage":"cellular organisms; Eukaryota; Opisthokonta; Metazoa; Eumetazoa; Bilateria; Protostomia; Ecdysozoa; Panarthropoda; Arthropoda; Mandibulata; Pancrustacea; Hexapoda; Insecta; Dicondylia; Pterygota; Neoptera; Endopterygota; Hymenoptera; Apocrita; Aculeata; Vespoidea; Formicidae; Formicinae; Camponotini; Camponotus",
 * "rank":"species",
 * "ws_ref":"12570/1253105/1",
 * "kingdom":"Metazoa",
 * "GenBank_hidden_flag":1,
 * "inherited_GC_flag":1,"
 * "deleted":0
 *       },
 *       {
 * "inherited_MGC_flag":1,
 * "inherited_div_flag":1,
 * "parent_taxon_ref":"12570/1217907/1",
 * "genetic_code":1,
 * "division_id":1,
 * "embl_code":"CS",
 * "domain":"Eukaryota",
 * "taxonomy_id":1297190,
 * "kingdom":"Metazoa",
 * "GenBank_hidden_flag":1,
 * "inherited_GC_flag":1,
 * "ws_ref":"12570/1253106/1",
 * "scientific_lineage":"cellular organisms; Eukaryota; Opisthokonta; Metazoa; Eumetazoa; Bilateria; Protostomia; Ecdysozoa; Panarthropoda; Arthropoda; Mandibulata; Pancrustacea; Hexapoda; Insecta; Dicondylia; Pterygota; Neoptera; Endopterygota; Hymenoptera; Apocrita; Aculeata; Vespoidea; Formicidae; Formicinae; Camponotini; Camponotus",
 * "rank":"species",
 * "scientific_name":"Camponotus sp. MAS003",
 * "hidden_subtree_flag":0,
 * "mitochondrial_genetic_code":5,
 * "deleted":0
 *       },
 * ...
 *   ]';
 *  =cut end of example
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "solr_core",
    "json_data"
})
public class IndexJsonParams {

    @JsonProperty("solr_core")
    private String solrCore;
    @JsonProperty("json_data")
    private String jsonData;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("solr_core")
    public String getSolrCore() {
        return solrCore;
    }

    @JsonProperty("solr_core")
    public void setSolrCore(String solrCore) {
        this.solrCore = solrCore;
    }

    public IndexJsonParams withSolrCore(String solrCore) {
        this.solrCore = solrCore;
        return this;
    }

    @JsonProperty("json_data")
    public String getJsonData() {
        return jsonData;
    }

    @JsonProperty("json_data")
    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public IndexJsonParams withJsonData(String jsonData) {
        this.jsonData = jsonData;
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
        return ((((((("IndexJsonParams"+" [solrCore=")+ solrCore)+", jsonData=")+ jsonData)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
