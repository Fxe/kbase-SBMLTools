
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
 * <p>Original spec-file type: MediaCompound</p>
 * <pre>
 * MediaCompound object
 * @optional id name smiles inchikey
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "compound_ref",
    "id",
    "name",
    "smiles",
    "inchikey",
    "concentration",
    "maxFlux",
    "minFlux"
})
public class MediaCompound {

    @JsonProperty("compound_ref")
    private String compoundRef;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("smiles")
    private String smiles;
    @JsonProperty("inchikey")
    private String inchikey;
    @JsonProperty("concentration")
    private Double concentration;
    @JsonProperty("maxFlux")
    private Double maxFlux;
    @JsonProperty("minFlux")
    private Double minFlux;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("compound_ref")
    public String getCompoundRef() {
        return compoundRef;
    }

    @JsonProperty("compound_ref")
    public void setCompoundRef(String compoundRef) {
        this.compoundRef = compoundRef;
    }

    public MediaCompound withCompoundRef(String compoundRef) {
        this.compoundRef = compoundRef;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public MediaCompound withId(String id) {
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

    public MediaCompound withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("smiles")
    public String getSmiles() {
        return smiles;
    }

    @JsonProperty("smiles")
    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }

    public MediaCompound withSmiles(String smiles) {
        this.smiles = smiles;
        return this;
    }

    @JsonProperty("inchikey")
    public String getInchikey() {
        return inchikey;
    }

    @JsonProperty("inchikey")
    public void setInchikey(String inchikey) {
        this.inchikey = inchikey;
    }

    public MediaCompound withInchikey(String inchikey) {
        this.inchikey = inchikey;
        return this;
    }

    @JsonProperty("concentration")
    public Double getConcentration() {
        return concentration;
    }

    @JsonProperty("concentration")
    public void setConcentration(Double concentration) {
        this.concentration = concentration;
    }

    public MediaCompound withConcentration(Double concentration) {
        this.concentration = concentration;
        return this;
    }

    @JsonProperty("maxFlux")
    public Double getMaxFlux() {
        return maxFlux;
    }

    @JsonProperty("maxFlux")
    public void setMaxFlux(Double maxFlux) {
        this.maxFlux = maxFlux;
    }

    public MediaCompound withMaxFlux(Double maxFlux) {
        this.maxFlux = maxFlux;
        return this;
    }

    @JsonProperty("minFlux")
    public Double getMinFlux() {
        return minFlux;
    }

    @JsonProperty("minFlux")
    public void setMinFlux(Double minFlux) {
        this.minFlux = minFlux;
    }

    public MediaCompound withMinFlux(Double minFlux) {
        this.minFlux = minFlux;
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
        return ((((((((((((((((((("MediaCompound"+" [compoundRef=")+ compoundRef)+", id=")+ id)+", name=")+ name)+", smiles=")+ smiles)+", inchikey=")+ inchikey)+", concentration=")+ concentration)+", maxFlux=")+ maxFlux)+", minFlux=")+ minFlux)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
