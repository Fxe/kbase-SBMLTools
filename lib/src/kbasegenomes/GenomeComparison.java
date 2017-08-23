
package kbasegenomes;

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
 * <p>Original spec-file type: GenomeComparison</p>
 * <pre>
 * GenomeComparisonData object: this object holds information about a multigenome comparison
 * @optional protcomp_ref pangenome_ref
 * @metadata ws core_functions as Core functions
 *     @metadata ws core_families as Core families
 *     @metadata ws name as Name
 *     @metadata ws length(genomes) as Number genomes
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "core_functions",
    "core_families",
    "protcomp_ref",
    "pangenome_ref",
    "genomes",
    "families",
    "functions"
})
public class GenomeComparison {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("core_functions")
    private Long coreFunctions;
    @JsonProperty("core_families")
    private Long coreFamilies;
    @JsonProperty("protcomp_ref")
    private String protcompRef;
    @JsonProperty("pangenome_ref")
    private String pangenomeRef;
    @JsonProperty("genomes")
    private List<GenomeComparisonGenome> genomes;
    @JsonProperty("families")
    private List<GenomeComparisonFamily> families;
    @JsonProperty("functions")
    private List<GenomeComparisonFunction> functions;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public GenomeComparison withId(String id) {
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

    public GenomeComparison withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("core_functions")
    public Long getCoreFunctions() {
        return coreFunctions;
    }

    @JsonProperty("core_functions")
    public void setCoreFunctions(Long coreFunctions) {
        this.coreFunctions = coreFunctions;
    }

    public GenomeComparison withCoreFunctions(Long coreFunctions) {
        this.coreFunctions = coreFunctions;
        return this;
    }

    @JsonProperty("core_families")
    public Long getCoreFamilies() {
        return coreFamilies;
    }

    @JsonProperty("core_families")
    public void setCoreFamilies(Long coreFamilies) {
        this.coreFamilies = coreFamilies;
    }

    public GenomeComparison withCoreFamilies(Long coreFamilies) {
        this.coreFamilies = coreFamilies;
        return this;
    }

    @JsonProperty("protcomp_ref")
    public String getProtcompRef() {
        return protcompRef;
    }

    @JsonProperty("protcomp_ref")
    public void setProtcompRef(String protcompRef) {
        this.protcompRef = protcompRef;
    }

    public GenomeComparison withProtcompRef(String protcompRef) {
        this.protcompRef = protcompRef;
        return this;
    }

    @JsonProperty("pangenome_ref")
    public String getPangenomeRef() {
        return pangenomeRef;
    }

    @JsonProperty("pangenome_ref")
    public void setPangenomeRef(String pangenomeRef) {
        this.pangenomeRef = pangenomeRef;
    }

    public GenomeComparison withPangenomeRef(String pangenomeRef) {
        this.pangenomeRef = pangenomeRef;
        return this;
    }

    @JsonProperty("genomes")
    public List<GenomeComparisonGenome> getGenomes() {
        return genomes;
    }

    @JsonProperty("genomes")
    public void setGenomes(List<GenomeComparisonGenome> genomes) {
        this.genomes = genomes;
    }

    public GenomeComparison withGenomes(List<GenomeComparisonGenome> genomes) {
        this.genomes = genomes;
        return this;
    }

    @JsonProperty("families")
    public List<GenomeComparisonFamily> getFamilies() {
        return families;
    }

    @JsonProperty("families")
    public void setFamilies(List<GenomeComparisonFamily> families) {
        this.families = families;
    }

    public GenomeComparison withFamilies(List<GenomeComparisonFamily> families) {
        this.families = families;
        return this;
    }

    @JsonProperty("functions")
    public List<GenomeComparisonFunction> getFunctions() {
        return functions;
    }

    @JsonProperty("functions")
    public void setFunctions(List<GenomeComparisonFunction> functions) {
        this.functions = functions;
    }

    public GenomeComparison withFunctions(List<GenomeComparisonFunction> functions) {
        this.functions = functions;
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
        return ((((((((((((((((((((("GenomeComparison"+" [id=")+ id)+", name=")+ name)+", coreFunctions=")+ coreFunctions)+", coreFamilies=")+ coreFamilies)+", protcompRef=")+ protcompRef)+", pangenomeRef=")+ pangenomeRef)+", genomes=")+ genomes)+", families=")+ families)+", functions=")+ functions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
