
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
 * <p>Original spec-file type: Media</p>
 * <pre>
 * Media object
 * @optional reagents atmosphere_addition atmosphere temperature pH_data isAerobic protocol_link source source_id         
 *     @metadata ws source_id as Source ID
 *     @metadata ws source as Source
 *     @metadata ws name as Name
 *     @metadata ws temperature as Temperature
 *     @metadata ws isAerobic as Is Aerobic
 *     @metadata ws isMinimal as Is Minimal
 *     @metadata ws isDefined as Is Defined
 *     @metadata ws length(mediacompounds) as Number compounds
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "source_id",
    "source",
    "protocol_link",
    "isDefined",
    "isMinimal",
    "isAerobic",
    "type",
    "pH_data",
    "temperature",
    "atmosphere",
    "atmosphere_addition",
    "reagents",
    "mediacompounds"
})
public class Media {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("source")
    private String source;
    @JsonProperty("protocol_link")
    private String protocolLink;
    @JsonProperty("isDefined")
    private Long isDefined;
    @JsonProperty("isMinimal")
    private Long isMinimal;
    @JsonProperty("isAerobic")
    private Long isAerobic;
    @JsonProperty("type")
    private String type;
    @JsonProperty("pH_data")
    private String pHData;
    @JsonProperty("temperature")
    private Double temperature;
    @JsonProperty("atmosphere")
    private String atmosphere;
    @JsonProperty("atmosphere_addition")
    private String atmosphereAddition;
    @JsonProperty("reagents")
    private List<MediaReagent> reagents;
    @JsonProperty("mediacompounds")
    private List<MediaCompound> mediacompounds;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Media withId(String id) {
        this.id = id;
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

    public Media withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("source_id")
    public String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Media withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    public Media withSource(String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("protocol_link")
    public String getProtocolLink() {
        return protocolLink;
    }

    @JsonProperty("protocol_link")
    public void setProtocolLink(String protocolLink) {
        this.protocolLink = protocolLink;
    }

    public Media withProtocolLink(String protocolLink) {
        this.protocolLink = protocolLink;
        return this;
    }

    @JsonProperty("isDefined")
    public Long getIsDefined() {
        return isDefined;
    }

    @JsonProperty("isDefined")
    public void setIsDefined(Long isDefined) {
        this.isDefined = isDefined;
    }

    public Media withIsDefined(Long isDefined) {
        this.isDefined = isDefined;
        return this;
    }

    @JsonProperty("isMinimal")
    public Long getIsMinimal() {
        return isMinimal;
    }

    @JsonProperty("isMinimal")
    public void setIsMinimal(Long isMinimal) {
        this.isMinimal = isMinimal;
    }

    public Media withIsMinimal(Long isMinimal) {
        this.isMinimal = isMinimal;
        return this;
    }

    @JsonProperty("isAerobic")
    public Long getIsAerobic() {
        return isAerobic;
    }

    @JsonProperty("isAerobic")
    public void setIsAerobic(Long isAerobic) {
        this.isAerobic = isAerobic;
    }

    public Media withIsAerobic(Long isAerobic) {
        this.isAerobic = isAerobic;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Media withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("pH_data")
    public String getPHData() {
        return pHData;
    }

    @JsonProperty("pH_data")
    public void setPHData(String pHData) {
        this.pHData = pHData;
    }

    public Media withPHData(String pHData) {
        this.pHData = pHData;
        return this;
    }

    @JsonProperty("temperature")
    public Double getTemperature() {
        return temperature;
    }

    @JsonProperty("temperature")
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Media withTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    @JsonProperty("atmosphere")
    public String getAtmosphere() {
        return atmosphere;
    }

    @JsonProperty("atmosphere")
    public void setAtmosphere(String atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Media withAtmosphere(String atmosphere) {
        this.atmosphere = atmosphere;
        return this;
    }

    @JsonProperty("atmosphere_addition")
    public String getAtmosphereAddition() {
        return atmosphereAddition;
    }

    @JsonProperty("atmosphere_addition")
    public void setAtmosphereAddition(String atmosphereAddition) {
        this.atmosphereAddition = atmosphereAddition;
    }

    public Media withAtmosphereAddition(String atmosphereAddition) {
        this.atmosphereAddition = atmosphereAddition;
        return this;
    }

    @JsonProperty("reagents")
    public List<MediaReagent> getReagents() {
        return reagents;
    }

    @JsonProperty("reagents")
    public void setReagents(List<MediaReagent> reagents) {
        this.reagents = reagents;
    }

    public Media withReagents(List<MediaReagent> reagents) {
        this.reagents = reagents;
        return this;
    }

    @JsonProperty("mediacompounds")
    public List<MediaCompound> getMediacompounds() {
        return mediacompounds;
    }

    @JsonProperty("mediacompounds")
    public void setMediacompounds(List<MediaCompound> mediacompounds) {
        this.mediacompounds = mediacompounds;
    }

    public Media withMediacompounds(List<MediaCompound> mediacompounds) {
        this.mediacompounds = mediacompounds;
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
        return ((((((((((((((((((((((((((((((((("Media"+" [id=")+ id)+", name=")+ name)+", sourceId=")+ sourceId)+", source=")+ source)+", protocolLink=")+ protocolLink)+", isDefined=")+ isDefined)+", isMinimal=")+ isMinimal)+", isAerobic=")+ isAerobic)+", type=")+ type)+", pHData=")+ pHData)+", temperature=")+ temperature)+", atmosphere=")+ atmosphere)+", atmosphereAddition=")+ atmosphereAddition)+", reagents=")+ reagents)+", mediacompounds=")+ mediacompounds)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
