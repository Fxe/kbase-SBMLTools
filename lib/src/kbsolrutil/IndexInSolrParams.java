
package kbsolrutil;

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
 * <p>Original spec-file type: IndexInSolrParams</p>
 * <pre>
 * Arguments for the index_in_solr function - send doc data to solr for indexing
 * string solr_core - the name of the solr core to index to
 * list<docdata> doc_data - the doc to be indexed, a list of hashes
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "solr_core",
    "doc_data"
})
public class IndexInSolrParams {

    @JsonProperty("solr_core")
    private java.lang.String solrCore;
    @JsonProperty("doc_data")
    private List<Map<String, String>> docData;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("solr_core")
    public java.lang.String getSolrCore() {
        return solrCore;
    }

    @JsonProperty("solr_core")
    public void setSolrCore(java.lang.String solrCore) {
        this.solrCore = solrCore;
    }

    public IndexInSolrParams withSolrCore(java.lang.String solrCore) {
        this.solrCore = solrCore;
        return this;
    }

    @JsonProperty("doc_data")
    public List<Map<String, String>> getDocData() {
        return docData;
    }

    @JsonProperty("doc_data")
    public void setDocData(List<Map<String, String>> docData) {
        this.docData = docData;
    }

    public IndexInSolrParams withDocData(List<Map<String, String>> docData) {
        this.docData = docData;
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
        return ((((((("IndexInSolrParams"+" [solrCore=")+ solrCore)+", docData=")+ docData)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
