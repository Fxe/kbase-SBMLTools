
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
 * <p>Original spec-file type: ImportedCompound</p>
 * <pre>
 * ImportedCompound object
 * @optional deltag deltagerr mass exactmass compound_ref modelcompound_ref charge name
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "smiles",
    "inchikey",
    "charge",
    "formula",
    "mass",
    "exactmass",
    "compound_ref",
    "modelcompound_ref",
    "dblinks",
    "fingerprints",
    "deltag",
    "deltagerr"
})
public class ImportedCompound {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("smiles")
    private java.lang.String smiles;
    @JsonProperty("inchikey")
    private java.lang.String inchikey;
    @JsonProperty("charge")
    private java.lang.Double charge;
    @JsonProperty("formula")
    private java.lang.String formula;
    @JsonProperty("mass")
    private java.lang.Double mass;
    @JsonProperty("exactmass")
    private java.lang.Double exactmass;
    @JsonProperty("compound_ref")
    private java.lang.String compoundRef;
    @JsonProperty("modelcompound_ref")
    private java.lang.String modelcompoundRef;
    @JsonProperty("dblinks")
    private Map<String, List<String>> dblinks;
    @JsonProperty("fingerprints")
    private Map<String, Map<String, Double>> fingerprints;
    @JsonProperty("deltag")
    private java.lang.Double deltag;
    @JsonProperty("deltagerr")
    private java.lang.Double deltagerr;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ImportedCompound withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public ImportedCompound withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("smiles")
    public java.lang.String getSmiles() {
        return smiles;
    }

    @JsonProperty("smiles")
    public void setSmiles(java.lang.String smiles) {
        this.smiles = smiles;
    }

    public ImportedCompound withSmiles(java.lang.String smiles) {
        this.smiles = smiles;
        return this;
    }

    @JsonProperty("inchikey")
    public java.lang.String getInchikey() {
        return inchikey;
    }

    @JsonProperty("inchikey")
    public void setInchikey(java.lang.String inchikey) {
        this.inchikey = inchikey;
    }

    public ImportedCompound withInchikey(java.lang.String inchikey) {
        this.inchikey = inchikey;
        return this;
    }

    @JsonProperty("charge")
    public java.lang.Double getCharge() {
        return charge;
    }

    @JsonProperty("charge")
    public void setCharge(java.lang.Double charge) {
        this.charge = charge;
    }

    public ImportedCompound withCharge(java.lang.Double charge) {
        this.charge = charge;
        return this;
    }

    @JsonProperty("formula")
    public java.lang.String getFormula() {
        return formula;
    }

    @JsonProperty("formula")
    public void setFormula(java.lang.String formula) {
        this.formula = formula;
    }

    public ImportedCompound withFormula(java.lang.String formula) {
        this.formula = formula;
        return this;
    }

    @JsonProperty("mass")
    public java.lang.Double getMass() {
        return mass;
    }

    @JsonProperty("mass")
    public void setMass(java.lang.Double mass) {
        this.mass = mass;
    }

    public ImportedCompound withMass(java.lang.Double mass) {
        this.mass = mass;
        return this;
    }

    @JsonProperty("exactmass")
    public java.lang.Double getExactmass() {
        return exactmass;
    }

    @JsonProperty("exactmass")
    public void setExactmass(java.lang.Double exactmass) {
        this.exactmass = exactmass;
    }

    public ImportedCompound withExactmass(java.lang.Double exactmass) {
        this.exactmass = exactmass;
        return this;
    }

    @JsonProperty("compound_ref")
    public java.lang.String getCompoundRef() {
        return compoundRef;
    }

    @JsonProperty("compound_ref")
    public void setCompoundRef(java.lang.String compoundRef) {
        this.compoundRef = compoundRef;
    }

    public ImportedCompound withCompoundRef(java.lang.String compoundRef) {
        this.compoundRef = compoundRef;
        return this;
    }

    @JsonProperty("modelcompound_ref")
    public java.lang.String getModelcompoundRef() {
        return modelcompoundRef;
    }

    @JsonProperty("modelcompound_ref")
    public void setModelcompoundRef(java.lang.String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
    }

    public ImportedCompound withModelcompoundRef(java.lang.String modelcompoundRef) {
        this.modelcompoundRef = modelcompoundRef;
        return this;
    }

    @JsonProperty("dblinks")
    public Map<String, List<String>> getDblinks() {
        return dblinks;
    }

    @JsonProperty("dblinks")
    public void setDblinks(Map<String, List<String>> dblinks) {
        this.dblinks = dblinks;
    }

    public ImportedCompound withDblinks(Map<String, List<String>> dblinks) {
        this.dblinks = dblinks;
        return this;
    }

    @JsonProperty("fingerprints")
    public Map<String, Map<String, Double>> getFingerprints() {
        return fingerprints;
    }

    @JsonProperty("fingerprints")
    public void setFingerprints(Map<String, Map<String, Double>> fingerprints) {
        this.fingerprints = fingerprints;
    }

    public ImportedCompound withFingerprints(Map<String, Map<String, Double>> fingerprints) {
        this.fingerprints = fingerprints;
        return this;
    }

    @JsonProperty("deltag")
    public java.lang.Double getDeltag() {
        return deltag;
    }

    @JsonProperty("deltag")
    public void setDeltag(java.lang.Double deltag) {
        this.deltag = deltag;
    }

    public ImportedCompound withDeltag(java.lang.Double deltag) {
        this.deltag = deltag;
        return this;
    }

    @JsonProperty("deltagerr")
    public java.lang.Double getDeltagerr() {
        return deltagerr;
    }

    @JsonProperty("deltagerr")
    public void setDeltagerr(java.lang.Double deltagerr) {
        this.deltagerr = deltagerr;
    }

    public ImportedCompound withDeltagerr(java.lang.Double deltagerr) {
        this.deltagerr = deltagerr;
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
        return ((((((((((((((((((((((((((((((("ImportedCompound"+" [id=")+ id)+", name=")+ name)+", smiles=")+ smiles)+", inchikey=")+ inchikey)+", charge=")+ charge)+", formula=")+ formula)+", mass=")+ mass)+", exactmass=")+ exactmass)+", compoundRef=")+ compoundRef)+", modelcompoundRef=")+ modelcompoundRef)+", dblinks=")+ dblinks)+", fingerprints=")+ fingerprints)+", deltag=")+ deltag)+", deltagerr=")+ deltagerr)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
