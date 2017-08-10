
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
 * <p>Original spec-file type: NewOrUpdatedParams</p>
 * <pre>
 * Arguments for the new_or_updated function - search solr according to the parameters passed and return the ones not found in solr.
 * string search_core - the name of the solr core to be searched
 * list<searchdata> search_docs - a list of arbitrary user-supplied key-value pairs specifying the definitions of docs 
 *     to be searched, a hash for each doc, see the example below:
 *         search_docs=[
 *             {
 *                 field1 => 'val1',
 *                 field2 => 'val2',
 *                 domain => 'Bacteria'
 *             },
 *             {
 *                 field1 => 'val3',
 *                 field2 => 'val4',
 *                 domain => 'Bacteria'                     
 *             }
 *          ];
 * string search_type - the object (genome) type to be searched
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "search_core",
    "search_docs",
    "search_type"
})
public class NewOrUpdatedParams {

    @JsonProperty("search_core")
    private java.lang.String searchCore;
    @JsonProperty("search_docs")
    private List<Map<String, String>> searchDocs;
    @JsonProperty("search_type")
    private java.lang.String searchType;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("search_core")
    public java.lang.String getSearchCore() {
        return searchCore;
    }

    @JsonProperty("search_core")
    public void setSearchCore(java.lang.String searchCore) {
        this.searchCore = searchCore;
    }

    public NewOrUpdatedParams withSearchCore(java.lang.String searchCore) {
        this.searchCore = searchCore;
        return this;
    }

    @JsonProperty("search_docs")
    public List<Map<String, String>> getSearchDocs() {
        return searchDocs;
    }

    @JsonProperty("search_docs")
    public void setSearchDocs(List<Map<String, String>> searchDocs) {
        this.searchDocs = searchDocs;
    }

    public NewOrUpdatedParams withSearchDocs(List<Map<String, String>> searchDocs) {
        this.searchDocs = searchDocs;
        return this;
    }

    @JsonProperty("search_type")
    public java.lang.String getSearchType() {
        return searchType;
    }

    @JsonProperty("search_type")
    public void setSearchType(java.lang.String searchType) {
        this.searchType = searchType;
    }

    public NewOrUpdatedParams withSearchType(java.lang.String searchType) {
        this.searchType = searchType;
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
        return ((((((((("NewOrUpdatedParams"+" [searchCore=")+ searchCore)+", searchDocs=")+ searchDocs)+", searchType=")+ searchType)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
