
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
 * <p>Original spec-file type: SearchSolrParams</p>
 * <pre>
 * Arguments for the search_solr function - search solr according to the parameters passed and return a string
 * string search_core - the name of the solr core to be searched
 * searchdata search_param - arbitrary user-supplied key-value pairs for controlling the presentation of the query response, 
 *                         a hash, see the example below:
 *         search_param={
 *                 fl => 'object_id,gene_name,genome_source',
 *                 wt => 'json',
 *                 rows => 20,
 *                 sort => 'object_id asc',
 *                 hl => 'false',
 *                 start => 100
 *         }
 * OR, default to SOLR default settings, i
 *         search_param={{fl=>'*',wt=>'xml',rows=>10,sort=>'',hl=>'false',start=>0}
 * searchdata search_query - arbitrary user-supplied key-value pairs specifying the fields to be searched and their values 
 *                         to be matched, a hash which specifies how the documents will be searched, see the example below:
 *         search_query={
 *                 parent_taxon_ref => '1779/116411/1',
 *                 rank => 'species',
 *                 scientific_lineage => 'cellular organisms; Bacteria; Proteobacteria; Alphaproteobacteria; Rhizobiales; Bradyrhizobiaceae; Bradyrhizobium',
 *                 scientific_name => 'Bradyrhizobium sp.*',
 *                 domain => 'Bacteria'
 *         }
 * OR, simply:
 *         search_query= { q => "*" };
 * string result_format - the format of the search result, 'xml' as the default, can be 'json', 'csv', etc.
 * string group_option - the name of the field to be grouped for the result
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "search_core",
    "search_param",
    "search_query",
    "result_format",
    "group_option"
})
public class SearchSolrParams {

    @JsonProperty("search_core")
    private java.lang.String searchCore;
    @JsonProperty("search_param")
    private Map<String, String> searchParam;
    @JsonProperty("search_query")
    private Map<String, String> searchQuery;
    @JsonProperty("result_format")
    private java.lang.String resultFormat;
    @JsonProperty("group_option")
    private java.lang.String groupOption;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("search_core")
    public java.lang.String getSearchCore() {
        return searchCore;
    }

    @JsonProperty("search_core")
    public void setSearchCore(java.lang.String searchCore) {
        this.searchCore = searchCore;
    }

    public SearchSolrParams withSearchCore(java.lang.String searchCore) {
        this.searchCore = searchCore;
        return this;
    }

    @JsonProperty("search_param")
    public Map<String, String> getSearchParam() {
        return searchParam;
    }

    @JsonProperty("search_param")
    public void setSearchParam(Map<String, String> searchParam) {
        this.searchParam = searchParam;
    }

    public SearchSolrParams withSearchParam(Map<String, String> searchParam) {
        this.searchParam = searchParam;
        return this;
    }

    @JsonProperty("search_query")
    public Map<String, String> getSearchQuery() {
        return searchQuery;
    }

    @JsonProperty("search_query")
    public void setSearchQuery(Map<String, String> searchQuery) {
        this.searchQuery = searchQuery;
    }

    public SearchSolrParams withSearchQuery(Map<String, String> searchQuery) {
        this.searchQuery = searchQuery;
        return this;
    }

    @JsonProperty("result_format")
    public java.lang.String getResultFormat() {
        return resultFormat;
    }

    @JsonProperty("result_format")
    public void setResultFormat(java.lang.String resultFormat) {
        this.resultFormat = resultFormat;
    }

    public SearchSolrParams withResultFormat(java.lang.String resultFormat) {
        this.resultFormat = resultFormat;
        return this;
    }

    @JsonProperty("group_option")
    public java.lang.String getGroupOption() {
        return groupOption;
    }

    @JsonProperty("group_option")
    public void setGroupOption(java.lang.String groupOption) {
        this.groupOption = groupOption;
    }

    public SearchSolrParams withGroupOption(java.lang.String groupOption) {
        this.groupOption = groupOption;
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
        return ((((((((((((("SearchSolrParams"+" [searchCore=")+ searchCore)+", searchParam=")+ searchParam)+", searchQuery=")+ searchQuery)+", resultFormat=")+ resultFormat)+", groupOption=")+ groupOption)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
