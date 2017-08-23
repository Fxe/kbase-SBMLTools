
package kbasegenomes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple4;


/**
 * <p>Original spec-file type: mRNA</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "location",
    "md5",
    "parent_gene",
    "cds"
})
public class MRNA {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("location")
    private List<Tuple4 <String, Long, String, Long>> location;
    @JsonProperty("md5")
    private java.lang.String md5;
    @JsonProperty("parent_gene")
    private java.lang.String parentGene;
    @JsonProperty("cds")
    private java.lang.String cds;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public MRNA withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("location")
    public List<Tuple4 <String, Long, String, Long>> getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(List<Tuple4 <String, Long, String, Long>> location) {
        this.location = location;
    }

    public MRNA withLocation(List<Tuple4 <String, Long, String, Long>> location) {
        this.location = location;
        return this;
    }

    @JsonProperty("md5")
    public java.lang.String getMd5() {
        return md5;
    }

    @JsonProperty("md5")
    public void setMd5(java.lang.String md5) {
        this.md5 = md5;
    }

    public MRNA withMd5(java.lang.String md5) {
        this.md5 = md5;
        return this;
    }

    @JsonProperty("parent_gene")
    public java.lang.String getParentGene() {
        return parentGene;
    }

    @JsonProperty("parent_gene")
    public void setParentGene(java.lang.String parentGene) {
        this.parentGene = parentGene;
    }

    public MRNA withParentGene(java.lang.String parentGene) {
        this.parentGene = parentGene;
        return this;
    }

    @JsonProperty("cds")
    public java.lang.String getCds() {
        return cds;
    }

    @JsonProperty("cds")
    public void setCds(java.lang.String cds) {
        this.cds = cds;
    }

    public MRNA withCds(java.lang.String cds) {
        this.cds = cds;
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
        return ((((((((((((("MRNA"+" [id=")+ id)+", location=")+ location)+", md5=")+ md5)+", parentGene=")+ parentGene)+", cds=")+ cds)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
