
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
 * <p>Original spec-file type: TemplateBiomass</p>
 * <pre>
 * TemplateBiomass object holds data on biomass in template
 * @searchable ws_subset id name type other dna rna protein lipid cellwall cofactor energy
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "type",
    "other",
    "dna",
    "rna",
    "protein",
    "lipid",
    "cellwall",
    "cofactor",
    "energy",
    "templateBiomassComponents"
})
public class TemplateBiomass {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("other")
    private Double other;
    @JsonProperty("dna")
    private Double dna;
    @JsonProperty("rna")
    private Double rna;
    @JsonProperty("protein")
    private Double protein;
    @JsonProperty("lipid")
    private Double lipid;
    @JsonProperty("cellwall")
    private Double cellwall;
    @JsonProperty("cofactor")
    private Double cofactor;
    @JsonProperty("energy")
    private Double energy;
    @JsonProperty("templateBiomassComponents")
    private List<TemplateBiomassComponent> templateBiomassComponents;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public TemplateBiomass withId(String id) {
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

    public TemplateBiomass withName(String name) {
        this.name = name;
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

    public TemplateBiomass withType(String type) {
        this.type = type;
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

    public TemplateBiomass withOther(Double other) {
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

    public TemplateBiomass withDna(Double dna) {
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

    public TemplateBiomass withRna(Double rna) {
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

    public TemplateBiomass withProtein(Double protein) {
        this.protein = protein;
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

    public TemplateBiomass withLipid(Double lipid) {
        this.lipid = lipid;
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

    public TemplateBiomass withCellwall(Double cellwall) {
        this.cellwall = cellwall;
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

    public TemplateBiomass withCofactor(Double cofactor) {
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

    public TemplateBiomass withEnergy(Double energy) {
        this.energy = energy;
        return this;
    }

    @JsonProperty("templateBiomassComponents")
    public List<TemplateBiomassComponent> getTemplateBiomassComponents() {
        return templateBiomassComponents;
    }

    @JsonProperty("templateBiomassComponents")
    public void setTemplateBiomassComponents(List<TemplateBiomassComponent> templateBiomassComponents) {
        this.templateBiomassComponents = templateBiomassComponents;
    }

    public TemplateBiomass withTemplateBiomassComponents(List<TemplateBiomassComponent> templateBiomassComponents) {
        this.templateBiomassComponents = templateBiomassComponents;
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
        return ((((((((((((((((((((((((((("TemplateBiomass"+" [id=")+ id)+", name=")+ name)+", type=")+ type)+", other=")+ other)+", dna=")+ dna)+", rna=")+ rna)+", protein=")+ protein)+", lipid=")+ lipid)+", cellwall=")+ cellwall)+", cofactor=")+ cofactor)+", energy=")+ energy)+", templateBiomassComponents=")+ templateBiomassComponents)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
