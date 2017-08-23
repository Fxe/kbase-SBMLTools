
package kbasegenomes;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: Contig</p>
 * <pre>
 * Type spec for a "Contig" subobject in the "ContigSet" object
 *                 Contig_id id - ID of contig in contigset
 *                 string md5 - unique hash of contig sequence
 *                 string sequence - sequence of the contig
 *                 string description - Description of the contig (e.g. everything after the ID in a FASTA file)
 *                 @optional length md5 genetic_code cell_compartment replicon_geometry replicon_type name description complete
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "length",
    "md5",
    "sequence",
    "genetic_code",
    "cell_compartment",
    "replicon_type",
    "replicon_geometry",
    "name",
    "description",
    "complete"
})
public class Contig {

    @JsonProperty("id")
    private String id;
    @JsonProperty("length")
    private Long length;
    @JsonProperty("md5")
    private String md5;
    @JsonProperty("sequence")
    private String sequence;
    @JsonProperty("genetic_code")
    private Long geneticCode;
    @JsonProperty("cell_compartment")
    private String cellCompartment;
    @JsonProperty("replicon_type")
    private String repliconType;
    @JsonProperty("replicon_geometry")
    private String repliconGeometry;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("complete")
    private Long complete;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Contig withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("length")
    public Long getLength() {
        return length;
    }

    @JsonProperty("length")
    public void setLength(Long length) {
        this.length = length;
    }

    public Contig withLength(Long length) {
        this.length = length;
        return this;
    }

    @JsonProperty("md5")
    public String getMd5() {
        return md5;
    }

    @JsonProperty("md5")
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Contig withMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    @JsonProperty("sequence")
    public String getSequence() {
        return sequence;
    }

    @JsonProperty("sequence")
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Contig withSequence(String sequence) {
        this.sequence = sequence;
        return this;
    }

    @JsonProperty("genetic_code")
    public Long getGeneticCode() {
        return geneticCode;
    }

    @JsonProperty("genetic_code")
    public void setGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
    }

    public Contig withGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
        return this;
    }

    @JsonProperty("cell_compartment")
    public String getCellCompartment() {
        return cellCompartment;
    }

    @JsonProperty("cell_compartment")
    public void setCellCompartment(String cellCompartment) {
        this.cellCompartment = cellCompartment;
    }

    public Contig withCellCompartment(String cellCompartment) {
        this.cellCompartment = cellCompartment;
        return this;
    }

    @JsonProperty("replicon_type")
    public String getRepliconType() {
        return repliconType;
    }

    @JsonProperty("replicon_type")
    public void setRepliconType(String repliconType) {
        this.repliconType = repliconType;
    }

    public Contig withRepliconType(String repliconType) {
        this.repliconType = repliconType;
        return this;
    }

    @JsonProperty("replicon_geometry")
    public String getRepliconGeometry() {
        return repliconGeometry;
    }

    @JsonProperty("replicon_geometry")
    public void setRepliconGeometry(String repliconGeometry) {
        this.repliconGeometry = repliconGeometry;
    }

    public Contig withRepliconGeometry(String repliconGeometry) {
        this.repliconGeometry = repliconGeometry;
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

    public Contig withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Contig withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("complete")
    public Long getComplete() {
        return complete;
    }

    @JsonProperty("complete")
    public void setComplete(Long complete) {
        this.complete = complete;
    }

    public Contig withComplete(Long complete) {
        this.complete = complete;
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
        return ((((((((((((((((((((((((("Contig"+" [id=")+ id)+", length=")+ length)+", md5=")+ md5)+", sequence=")+ sequence)+", geneticCode=")+ geneticCode)+", cellCompartment=")+ cellCompartment)+", repliconType=")+ repliconType)+", repliconGeometry=")+ repliconGeometry)+", name=")+ name)+", description=")+ description)+", complete=")+ complete)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
