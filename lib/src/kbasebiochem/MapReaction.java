
package kbasebiochem;

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
 * <p>Original spec-file type: MapReaction</p>
 * <pre>
 * MapReaction object
 * @optional link
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "reversible",
    "name",
    "ec",
    "shape",
    "link",
    "h",
    "w",
    "y",
    "x",
    "rxns",
    "substrate_refs",
    "product_refs"
})
public class MapReaction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("reversible")
    private Long reversible;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("ec")
    private java.lang.String ec;
    @JsonProperty("shape")
    private java.lang.String shape;
    @JsonProperty("link")
    private java.lang.String link;
    @JsonProperty("h")
    private Long h;
    @JsonProperty("w")
    private Long w;
    @JsonProperty("y")
    private Long y;
    @JsonProperty("x")
    private Long x;
    @JsonProperty("rxns")
    private List<String> rxns;
    @JsonProperty("substrate_refs")
    private List<kbasebiochem.MapReactionReactant> substrateRefs;
    @JsonProperty("product_refs")
    private List<kbasebiochem.MapReactionReactant> productRefs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public MapReaction withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("reversible")
    public Long getReversible() {
        return reversible;
    }

    @JsonProperty("reversible")
    public void setReversible(Long reversible) {
        this.reversible = reversible;
    }

    public MapReaction withReversible(Long reversible) {
        this.reversible = reversible;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public MapReaction withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("ec")
    public java.lang.String getEc() {
        return ec;
    }

    @JsonProperty("ec")
    public void setEc(java.lang.String ec) {
        this.ec = ec;
    }

    public MapReaction withEc(java.lang.String ec) {
        this.ec = ec;
        return this;
    }

    @JsonProperty("shape")
    public java.lang.String getShape() {
        return shape;
    }

    @JsonProperty("shape")
    public void setShape(java.lang.String shape) {
        this.shape = shape;
    }

    public MapReaction withShape(java.lang.String shape) {
        this.shape = shape;
        return this;
    }

    @JsonProperty("link")
    public java.lang.String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(java.lang.String link) {
        this.link = link;
    }

    public MapReaction withLink(java.lang.String link) {
        this.link = link;
        return this;
    }

    @JsonProperty("h")
    public Long getH() {
        return h;
    }

    @JsonProperty("h")
    public void setH(Long h) {
        this.h = h;
    }

    public MapReaction withH(Long h) {
        this.h = h;
        return this;
    }

    @JsonProperty("w")
    public Long getW() {
        return w;
    }

    @JsonProperty("w")
    public void setW(Long w) {
        this.w = w;
    }

    public MapReaction withW(Long w) {
        this.w = w;
        return this;
    }

    @JsonProperty("y")
    public Long getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(Long y) {
        this.y = y;
    }

    public MapReaction withY(Long y) {
        this.y = y;
        return this;
    }

    @JsonProperty("x")
    public Long getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(Long x) {
        this.x = x;
    }

    public MapReaction withX(Long x) {
        this.x = x;
        return this;
    }

    @JsonProperty("rxns")
    public List<String> getRxns() {
        return rxns;
    }

    @JsonProperty("rxns")
    public void setRxns(List<String> rxns) {
        this.rxns = rxns;
    }

    public MapReaction withRxns(List<String> rxns) {
        this.rxns = rxns;
        return this;
    }

    @JsonProperty("substrate_refs")
    public List<kbasebiochem.MapReactionReactant> getSubstrateRefs() {
        return substrateRefs;
    }

    @JsonProperty("substrate_refs")
    public void setSubstrateRefs(List<kbasebiochem.MapReactionReactant> substrateRefs) {
        this.substrateRefs = substrateRefs;
    }

    public MapReaction withSubstrateRefs(List<kbasebiochem.MapReactionReactant> substrateRefs) {
        this.substrateRefs = substrateRefs;
        return this;
    }

    @JsonProperty("product_refs")
    public List<kbasebiochem.MapReactionReactant> getProductRefs() {
        return productRefs;
    }

    @JsonProperty("product_refs")
    public void setProductRefs(List<kbasebiochem.MapReactionReactant> productRefs) {
        this.productRefs = productRefs;
    }

    public MapReaction withProductRefs(List<kbasebiochem.MapReactionReactant> productRefs) {
        this.productRefs = productRefs;
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
        return ((((((((((((((((((((((((((((("MapReaction"+" [id=")+ id)+", reversible=")+ reversible)+", name=")+ name)+", ec=")+ ec)+", shape=")+ shape)+", link=")+ link)+", h=")+ h)+", w=")+ w)+", y=")+ y)+", x=")+ x)+", rxns=")+ rxns)+", substrateRefs=")+ substrateRefs)+", productRefs=")+ productRefs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
