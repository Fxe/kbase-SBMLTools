
package genomeannotationapi;

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
 * <p>Original spec-file type: Protein_data</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "protein_id",
    "protein_amino_acid_sequence",
    "protein_function",
    "protein_aliases",
    "protein_md5",
    "protein_domain_locations"
})
public class ProteinData {

    @JsonProperty("protein_id")
    private java.lang.String proteinId;
    @JsonProperty("protein_amino_acid_sequence")
    private java.lang.String proteinAminoAcidSequence;
    @JsonProperty("protein_function")
    private java.lang.String proteinFunction;
    @JsonProperty("protein_aliases")
    private Map<String, List<String>> proteinAliases;
    @JsonProperty("protein_md5")
    private java.lang.String proteinMd5;
    @JsonProperty("protein_domain_locations")
    private List<String> proteinDomainLocations;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("protein_id")
    public java.lang.String getProteinId() {
        return proteinId;
    }

    @JsonProperty("protein_id")
    public void setProteinId(java.lang.String proteinId) {
        this.proteinId = proteinId;
    }

    public ProteinData withProteinId(java.lang.String proteinId) {
        this.proteinId = proteinId;
        return this;
    }

    @JsonProperty("protein_amino_acid_sequence")
    public java.lang.String getProteinAminoAcidSequence() {
        return proteinAminoAcidSequence;
    }

    @JsonProperty("protein_amino_acid_sequence")
    public void setProteinAminoAcidSequence(java.lang.String proteinAminoAcidSequence) {
        this.proteinAminoAcidSequence = proteinAminoAcidSequence;
    }

    public ProteinData withProteinAminoAcidSequence(java.lang.String proteinAminoAcidSequence) {
        this.proteinAminoAcidSequence = proteinAminoAcidSequence;
        return this;
    }

    @JsonProperty("protein_function")
    public java.lang.String getProteinFunction() {
        return proteinFunction;
    }

    @JsonProperty("protein_function")
    public void setProteinFunction(java.lang.String proteinFunction) {
        this.proteinFunction = proteinFunction;
    }

    public ProteinData withProteinFunction(java.lang.String proteinFunction) {
        this.proteinFunction = proteinFunction;
        return this;
    }

    @JsonProperty("protein_aliases")
    public Map<String, List<String>> getProteinAliases() {
        return proteinAliases;
    }

    @JsonProperty("protein_aliases")
    public void setProteinAliases(Map<String, List<String>> proteinAliases) {
        this.proteinAliases = proteinAliases;
    }

    public ProteinData withProteinAliases(Map<String, List<String>> proteinAliases) {
        this.proteinAliases = proteinAliases;
        return this;
    }

    @JsonProperty("protein_md5")
    public java.lang.String getProteinMd5() {
        return proteinMd5;
    }

    @JsonProperty("protein_md5")
    public void setProteinMd5(java.lang.String proteinMd5) {
        this.proteinMd5 = proteinMd5;
    }

    public ProteinData withProteinMd5(java.lang.String proteinMd5) {
        this.proteinMd5 = proteinMd5;
        return this;
    }

    @JsonProperty("protein_domain_locations")
    public List<String> getProteinDomainLocations() {
        return proteinDomainLocations;
    }

    @JsonProperty("protein_domain_locations")
    public void setProteinDomainLocations(List<String> proteinDomainLocations) {
        this.proteinDomainLocations = proteinDomainLocations;
    }

    public ProteinData withProteinDomainLocations(List<String> proteinDomainLocations) {
        this.proteinDomainLocations = proteinDomainLocations;
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
        return ((((((((((((((("ProteinData"+" [proteinId=")+ proteinId)+", proteinAminoAcidSequence=")+ proteinAminoAcidSequence)+", proteinFunction=")+ proteinFunction)+", proteinAliases=")+ proteinAliases)+", proteinMd5=")+ proteinMd5)+", proteinDomainLocations=")+ proteinDomainLocations)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
