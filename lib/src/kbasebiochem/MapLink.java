
package kbasebiochem;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: MapLink</p>
 * <pre>
 * MapLink object
 * @optional link
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "map_ref",
    "name",
    "shape",
    "link",
    "h",
    "w",
    "y",
    "x",
    "map_id"
})
public class MapLink {

    @JsonProperty("id")
    private String id;
    @JsonProperty("map_ref")
    private String mapRef;
    @JsonProperty("name")
    private String name;
    @JsonProperty("shape")
    private String shape;
    @JsonProperty("link")
    private String link;
    @JsonProperty("h")
    private Long h;
    @JsonProperty("w")
    private Long w;
    @JsonProperty("y")
    private Long y;
    @JsonProperty("x")
    private Long x;
    @JsonProperty("map_id")
    private String mapId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public MapLink withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("map_ref")
    public String getMapRef() {
        return mapRef;
    }

    @JsonProperty("map_ref")
    public void setMapRef(String mapRef) {
        this.mapRef = mapRef;
    }

    public MapLink withMapRef(String mapRef) {
        this.mapRef = mapRef;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public MapLink withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("shape")
    public String getShape() {
        return shape;
    }

    @JsonProperty("shape")
    public void setShape(String shape) {
        this.shape = shape;
    }

    public MapLink withShape(String shape) {
        this.shape = shape;
        return this;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    public MapLink withLink(String link) {
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

    public MapLink withH(Long h) {
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

    public MapLink withW(Long w) {
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

    public MapLink withY(Long y) {
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

    public MapLink withX(Long x) {
        this.x = x;
        return this;
    }

    @JsonProperty("map_id")
    public String getMapId() {
        return mapId;
    }

    @JsonProperty("map_id")
    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public MapLink withMapId(String mapId) {
        this.mapId = mapId;
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
        return ((((((((((((((((((((((("MapLink"+" [id=")+ id)+", mapRef=")+ mapRef)+", name=")+ name)+", shape=")+ shape)+", link=")+ link)+", h=")+ h)+", w=")+ w)+", y=")+ y)+", x=")+ x)+", mapId=")+ mapId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
