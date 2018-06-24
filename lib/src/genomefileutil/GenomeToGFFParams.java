
package genomefileutil;

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
 * <p>Original spec-file type: GenomeToGFFParams</p>
 * <pre>
 * is_gtf - optional flag switching export to GTF format (default is 0, 
 *     which means GFF)
 * target_dir - optional target directory to create file in (default is
 *     temporary folder with name 'gff_<timestamp>' created in scratch)
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "genome_ref",
    "ref_path_to_genome",
    "is_gtf",
    "target_dir"
})
public class GenomeToGFFParams {

    @JsonProperty("genome_ref")
    private java.lang.String genomeRef;
    @JsonProperty("ref_path_to_genome")
    private List<String> refPathToGenome;
    @JsonProperty("is_gtf")
    private Long isGtf;
    @JsonProperty("target_dir")
    private java.lang.String targetDir;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("genome_ref")
    public java.lang.String getGenomeRef() {
        return genomeRef;
    }

    @JsonProperty("genome_ref")
    public void setGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
    }

    public GenomeToGFFParams withGenomeRef(java.lang.String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("ref_path_to_genome")
    public List<String> getRefPathToGenome() {
        return refPathToGenome;
    }

    @JsonProperty("ref_path_to_genome")
    public void setRefPathToGenome(List<String> refPathToGenome) {
        this.refPathToGenome = refPathToGenome;
    }

    public GenomeToGFFParams withRefPathToGenome(List<String> refPathToGenome) {
        this.refPathToGenome = refPathToGenome;
        return this;
    }

    @JsonProperty("is_gtf")
    public Long getIsGtf() {
        return isGtf;
    }

    @JsonProperty("is_gtf")
    public void setIsGtf(Long isGtf) {
        this.isGtf = isGtf;
    }

    public GenomeToGFFParams withIsGtf(Long isGtf) {
        this.isGtf = isGtf;
        return this;
    }

    @JsonProperty("target_dir")
    public java.lang.String getTargetDir() {
        return targetDir;
    }

    @JsonProperty("target_dir")
    public void setTargetDir(java.lang.String targetDir) {
        this.targetDir = targetDir;
    }

    public GenomeToGFFParams withTargetDir(java.lang.String targetDir) {
        this.targetDir = targetDir;
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
        return ((((((((((("GenomeToGFFParams"+" [genomeRef=")+ genomeRef)+", refPathToGenome=")+ refPathToGenome)+", isGtf=")+ isGtf)+", targetDir=")+ targetDir)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
