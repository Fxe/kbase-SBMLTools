
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
 * <p>Original spec-file type: Biomass</p>
 * <pre>
 * Biomass object
 * @optional removedcompounds
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "other",
    "dna",
    "rna",
    "protein",
    "cellwall",
    "lipid",
    "cofactor",
    "energy",
    "biomasscompounds",
    "removedcompounds"
})
public class Biomass {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("other")
    private Double other;
    @JsonProperty("dna")
    private Double dna;
    @JsonProperty("rna")
    private Double rna;
    @JsonProperty("protein")
    private Double protein;
    @JsonProperty("cellwall")
    private Double cellwall;
    @JsonProperty("lipid")
    private Double lipid;
    @JsonProperty("cofactor")
    private Double cofactor;
    @JsonProperty("energy")
    private Double energy;
    @JsonProperty("biomasscompounds")
    private List<kbasefba.BiomassCompound> biomasscompounds;
    @JsonProperty("removedcompounds")
    private List<kbasefba.BiomassCompound> removedcompounds;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Biomass withId(String id) {
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

    public Biomass withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("other")
    public Double getOther() {
        return other;
    }

    @JsonProperty("other")
    public void setOther(Double other) {
        this.other = other;
    }

    public Biomass withOther(Double other) {
        this.other = other;
        return this;
    }

    @JsonProperty("dna")
    public Double getDna() {
        return dna;
    }

    @JsonProperty("dna")
    public void setDna(Double dna) {
        this.dna = dna;
    }

    public Biomass withDna(Double dna) {
        this.dna = dna;
        return this;
    }

    @JsonProperty("rna")
    public Double getRna() {
        return rna;
    }

    @JsonProperty("rna")
    public void setRna(Double rna) {
        this.rna = rna;
    }

    public Biomass withRna(Double rna) {
        this.rna = rna;
        return this;
    }

    @JsonProperty("protein")
    public Double getProtein() {
        return protein;
    }

    @JsonProperty("protein")
    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Biomass withProtein(Double protein) {
        this.protein = protein;
        return this;
    }

    @JsonProperty("cellwall")
    public Double getCellwall() {
        return cellwall;
    }

    @JsonProperty("cellwall")
    public void setCellwall(Double cellwall) {
        this.cellwall = cellwall;
    }

    public Biomass withCellwall(Double cellwall) {
        this.cellwall = cellwall;
        return this;
    }

    @JsonProperty("lipid")
    public Double getLipid() {
        return lipid;
    }

    @JsonProperty("lipid")
    public void setLipid(Double lipid) {
        this.lipid = lipid;
    }

    public Biomass withLipid(Double lipid) {
        this.lipid = lipid;
        return this;
    }

    @JsonProperty("cofactor")
    public Double getCofactor() {
        return cofactor;
    }

    @JsonProperty("cofactor")
    public void setCofactor(Double cofactor) {
        this.cofactor = cofactor;
    }

    public Biomass withCofactor(Double cofactor) {
        this.cofactor = cofactor;
        return this;
    }

    @JsonProperty("energy")
    public Double getEnergy() {
        return energy;
    }

    @JsonProperty("energy")
    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Biomass withEnergy(Double energy) {
        this.energy = energy;
        return this;
    }

    @JsonProperty("biomasscompounds")
    public List<kbasefba.BiomassCompound> getBiomasscompounds() {
        return biomasscompounds;
    }

    @JsonProperty("biomasscompounds")
    public void setBiomasscompounds(List<kbasefba.BiomassCompound> biomasscompounds) {
        this.biomasscompounds = biomasscompounds;
    }

    public Biomass withBiomasscompounds(List<kbasefba.BiomassCompound> biomasscompounds) {
        this.biomasscompounds = biomasscompounds;
        return this;
    }

    @JsonProperty("removedcompounds")
    public List<kbasefba.BiomassCompound> getRemovedcompounds() {
        return removedcompounds;
    }

    @JsonProperty("removedcompounds")
    public void setRemovedcompounds(List<kbasefba.BiomassCompound> removedcompounds) {
        this.removedcompounds = removedcompounds;
    }

    public Biomass withRemovedcompounds(List<kbasefba.BiomassCompound> removedcompounds) {
        this.removedcompounds = removedcompounds;
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
        return ((((((((((((((((((((((((((("Biomass"+" [id=")+ id)+", name=")+ name)+", other=")+ other)+", dna=")+ dna)+", rna=")+ rna)+", protein=")+ protein)+", cellwall=")+ cellwall)+", lipid=")+ lipid)+", cofactor=")+ cofactor)+", energy=")+ energy)+", biomasscompounds=")+ biomasscompounds)+", removedcompounds=")+ removedcompounds)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
