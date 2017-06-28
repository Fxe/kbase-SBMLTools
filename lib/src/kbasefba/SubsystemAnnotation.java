
package kbasefba;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple2;


/**
 * <p>Original spec-file type: SubsystemAnnotation</p>
 * <pre>
 * SubsystemAnnotation object: this object holds all reactions in subsystems
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "biochemistry_ref",
    "mapping_ref",
    "subsystems"
})
public class SubsystemAnnotation {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("biochemistry_ref")
    private java.lang.String biochemistryRef;
    @JsonProperty("mapping_ref")
    private java.lang.String mappingRef;
    @JsonProperty("subsystems")
    private Map<String, List<Tuple2 <String, SubsystemReaction>>> subsystems;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public SubsystemAnnotation withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("biochemistry_ref")
    public java.lang.String getBiochemistryRef() {
        return biochemistryRef;
    }

    @JsonProperty("biochemistry_ref")
    public void setBiochemistryRef(java.lang.String biochemistryRef) {
        this.biochemistryRef = biochemistryRef;
    }

    public SubsystemAnnotation withBiochemistryRef(java.lang.String biochemistryRef) {
        this.biochemistryRef = biochemistryRef;
        return this;
    }

    @JsonProperty("mapping_ref")
    public java.lang.String getMappingRef() {
        return mappingRef;
    }

    @JsonProperty("mapping_ref")
    public void setMappingRef(java.lang.String mappingRef) {
        this.mappingRef = mappingRef;
    }

    public SubsystemAnnotation withMappingRef(java.lang.String mappingRef) {
        this.mappingRef = mappingRef;
        return this;
    }

    @JsonProperty("subsystems")
    public Map<String, List<Tuple2 <String, SubsystemReaction>>> getSubsystems() {
        return subsystems;
    }

    @JsonProperty("subsystems")
    public void setSubsystems(Map<String, List<Tuple2 <String, SubsystemReaction>>> subsystems) {
        this.subsystems = subsystems;
    }

    public SubsystemAnnotation withSubsystems(Map<String, List<Tuple2 <String, SubsystemReaction>>> subsystems) {
        this.subsystems = subsystems;
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
        return ((((((((((("SubsystemAnnotation"+" [id=")+ id)+", biochemistryRef=")+ biochemistryRef)+", mappingRef=")+ mappingRef)+", subsystems=")+ subsystems)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
