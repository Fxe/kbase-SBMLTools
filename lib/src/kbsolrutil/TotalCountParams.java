
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
 * <p>Original spec-file type: TotalCountParams</p>
 * <pre>
 * Arguments for the get_total_count function - search solr according to the parameters passed and return the count of docs found, or -1 if error.
 * string search_core - the name of the solr core to be searched
 * searchdata search_query - arbitrary user-supplied key-value pairs specifying the fields to be searched and their values to be matched, a hash which specifies how the documents will be searched, see the example below:
 *         search_query={
 *                 parent_taxon_ref => '1779/116411/1',
 *                 rank => 'species',
 *                 scientific_lineage => 'cellular organisms; Bacteria; Proteobacteria; Alphaproteobacteria; Rhizobiales; Bradyrhizobiaceae; Bradyrhizobium',
 *                 scientific_name => 'Bradyrhizobium sp.*',
 *                 domain => 'Bacteria'
 *         }
 * OR, simply:
 *         search_query= { q => "*" };
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "search_core",
    "search_query"
})
public class TotalCountParams {

    @JsonProperty("search_core")
    private java.lang.String searchCore;
    @JsonProperty("search_query")
    private Map<String, String> searchQuery;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("search_core")
    public java.lang.String getSearchCore() {
        return searchCore;
    }

    @JsonProperty("search_core")
    public void setSearchCore(java.lang.String searchCore) {
        this.searchCore = searchCore;
    }

    public TotalCountParams withSearchCore(java.lang.String searchCore) {
        this.searchCore = searchCore;
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

    public TotalCountParams withSearchQuery(Map<String, String> searchQuery) {
        this.searchQuery = searchQuery;
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
        return ((((((("TotalCountParams"+" [searchCore=")+ searchCore)+", searchQuery=")+ searchQuery)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
