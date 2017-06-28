
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


/**
 * <p>Original spec-file type: FBAPathwayAnalysisReaction</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "flux",
    "gapfill",
    "expressed",
    "pegs"
})
public class FBAPathwayAnalysisReaction {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("flux")
    private Double flux;
    @JsonProperty("gapfill")
    private Long gapfill;
    @JsonProperty("expressed")
    private Long expressed;
    @JsonProperty("pegs")
    private List<FBAPathwayAnalysisFeature> pegs;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FBAPathwayAnalysisReaction withId(String id) {
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

    public FBAPathwayAnalysisReaction withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("flux")
    public Double getFlux() {
        return flux;
    }

    @JsonProperty("flux")
    public void setFlux(Double flux) {
        this.flux = flux;
    }

    public FBAPathwayAnalysisReaction withFlux(Double flux) {
        this.flux = flux;
        return this;
    }

    @JsonProperty("gapfill")
    public Long getGapfill() {
        return gapfill;
    }

    @JsonProperty("gapfill")
    public void setGapfill(Long gapfill) {
        this.gapfill = gapfill;
    }

    public FBAPathwayAnalysisReaction withGapfill(Long gapfill) {
        this.gapfill = gapfill;
        return this;
    }

    @JsonProperty("expressed")
    public Long getExpressed() {
        return expressed;
    }

    @JsonProperty("expressed")
    public void setExpressed(Long expressed) {
        this.expressed = expressed;
    }

    public FBAPathwayAnalysisReaction withExpressed(Long expressed) {
        this.expressed = expressed;
        return this;
    }

    @JsonProperty("pegs")
    public List<FBAPathwayAnalysisFeature> getPegs() {
        return pegs;
    }

    @JsonProperty("pegs")
    public void setPegs(List<FBAPathwayAnalysisFeature> pegs) {
        this.pegs = pegs;
    }

    public FBAPathwayAnalysisReaction withPegs(List<FBAPathwayAnalysisFeature> pegs) {
        this.pegs = pegs;
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
        return ((((((((((((((("FBAPathwayAnalysisReaction"+" [id=")+ id)+", name=")+ name)+", flux=")+ flux)+", gapfill=")+ gapfill)+", expressed=")+ expressed)+", pegs=")+ pegs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
