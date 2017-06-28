
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
 * <p>Original spec-file type: MapCompound</p>
 * <pre>
 * MapCompound object
 * @optional link label_x label_y
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "label",
    "label_x",
    "label_y",
    "name",
    "shape",
    "link",
    "h",
    "w",
    "y",
    "x",
    "cpds",
    "link_refs"
})
public class MapCompound {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("label")
    private java.lang.String label;
    @JsonProperty("label_x")
    private Long labelX;
    @JsonProperty("label_y")
    private Long labelY;
    @JsonProperty("name")
    private java.lang.String name;
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
    @JsonProperty("cpds")
    private List<String> cpds;
    @JsonProperty("link_refs")
    private List<String> linkRefs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public MapCompound withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("label")
    public java.lang.String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(java.lang.String label) {
        this.label = label;
    }

    public MapCompound withLabel(java.lang.String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("label_x")
    public Long getLabelX() {
        return labelX;
    }

    @JsonProperty("label_x")
    public void setLabelX(Long labelX) {
        this.labelX = labelX;
    }

    public MapCompound withLabelX(Long labelX) {
        this.labelX = labelX;
        return this;
    }

    @JsonProperty("label_y")
    public Long getLabelY() {
        return labelY;
    }

    @JsonProperty("label_y")
    public void setLabelY(Long labelY) {
        this.labelY = labelY;
    }

    public MapCompound withLabelY(Long labelY) {
        this.labelY = labelY;
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

    public MapCompound withName(java.lang.String name) {
        this.name = name;
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

    public MapCompound withShape(java.lang.String shape) {
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

    public MapCompound withLink(java.lang.String link) {
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

    public MapCompound withH(Long h) {
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

    public MapCompound withW(Long w) {
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

    public MapCompound withY(Long y) {
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

    public MapCompound withX(Long x) {
        this.x = x;
        return this;
    }

    @JsonProperty("cpds")
    public List<String> getCpds() {
        return cpds;
    }

    @JsonProperty("cpds")
    public void setCpds(List<String> cpds) {
        this.cpds = cpds;
    }

    public MapCompound withCpds(List<String> cpds) {
        this.cpds = cpds;
        return this;
    }

    @JsonProperty("link_refs")
    public List<String> getLinkRefs() {
        return linkRefs;
    }

    @JsonProperty("link_refs")
    public void setLinkRefs(List<String> linkRefs) {
        this.linkRefs = linkRefs;
    }

    public MapCompound withLinkRefs(List<String> linkRefs) {
        this.linkRefs = linkRefs;
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
        return ((((((((((((((((((((((((((((("MapCompound"+" [id=")+ id)+", label=")+ label)+", labelX=")+ labelX)+", labelY=")+ labelY)+", name=")+ name)+", shape=")+ shape)+", link=")+ link)+", h=")+ h)+", w=")+ w)+", y=")+ y)+", x=")+ x)+", cpds=")+ cpds)+", linkRefs=")+ linkRefs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
