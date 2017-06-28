
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
 * <p>Original spec-file type: ModelCompound</p>
 * <pre>
 * ModelCompound object
 * @optional aliases maxuptake dblinks displayID smiles inchikey string_attributes numerical_attributes
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "displayID",
    "compound_ref",
    "dblinks",
    "string_attributes",
    "numerical_attributes",
    "aliases",
    "name",
    "charge",
    "maxuptake",
    "formula",
    "smiles",
    "inchikey",
    "modelcompartment_ref"
})
public class ModelCompound {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("displayID")
    private java.lang.String displayID;
    @JsonProperty("compound_ref")
    private java.lang.String compoundRef;
    @JsonProperty("dblinks")
    private Map<String, List<String>> dblinks;
    @JsonProperty("string_attributes")
    private Map<String, String> stringAttributes;
    @JsonProperty("numerical_attributes")
    private Map<String, Double> numericalAttributes;
    @JsonProperty("aliases")
    private List<String> aliases;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("charge")
    private java.lang.Double charge;
    @JsonProperty("maxuptake")
    private java.lang.Double maxuptake;
    @JsonProperty("formula")
    private java.lang.String formula;
    @JsonProperty("smiles")
    private java.lang.String smiles;
    @JsonProperty("inchikey")
    private java.lang.String inchikey;
    @JsonProperty("modelcompartment_ref")
    private java.lang.String modelcompartmentRef;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ModelCompound withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("displayID")
    public java.lang.String getDisplayID() {
        return displayID;
    }

    @JsonProperty("displayID")
    public void setDisplayID(java.lang.String displayID) {
        this.displayID = displayID;
    }

    public ModelCompound withDisplayID(java.lang.String displayID) {
        this.displayID = displayID;
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

    public ModelCompound withCompoundRef(java.lang.String compoundRef) {
        this.compoundRef = compoundRef;
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

    public ModelCompound withDblinks(Map<String, List<String>> dblinks) {
        this.dblinks = dblinks;
        return this;
    }

    @JsonProperty("string_attributes")
    public Map<String, String> getStringAttributes() {
        return stringAttributes;
    }

    @JsonProperty("string_attributes")
    public void setStringAttributes(Map<String, String> stringAttributes) {
        this.stringAttributes = stringAttributes;
    }

    public ModelCompound withStringAttributes(Map<String, String> stringAttributes) {
        this.stringAttributes = stringAttributes;
        return this;
    }

    @JsonProperty("numerical_attributes")
    public Map<String, Double> getNumericalAttributes() {
        return numericalAttributes;
    }

    @JsonProperty("numerical_attributes")
    public void setNumericalAttributes(Map<String, Double> numericalAttributes) {
        this.numericalAttributes = numericalAttributes;
    }

    public ModelCompound withNumericalAttributes(Map<String, Double> numericalAttributes) {
        this.numericalAttributes = numericalAttributes;
        return this;
    }

    @JsonProperty("aliases")
    public List<String> getAliases() {
        return aliases;
    }

    @JsonProperty("aliases")
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public ModelCompound withAliases(List<String> aliases) {
        this.aliases = aliases;
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

    public ModelCompound withName(java.lang.String name) {
        this.name = name;
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

    public ModelCompound withCharge(java.lang.Double charge) {
        this.charge = charge;
        return this;
    }

    @JsonProperty("maxuptake")
    public java.lang.Double getMaxuptake() {
        return maxuptake;
    }

    @JsonProperty("maxuptake")
    public void setMaxuptake(java.lang.Double maxuptake) {
        this.maxuptake = maxuptake;
    }

    public ModelCompound withMaxuptake(java.lang.Double maxuptake) {
        this.maxuptake = maxuptake;
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

    public ModelCompound withFormula(java.lang.String formula) {
        this.formula = formula;
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

    public ModelCompound withSmiles(java.lang.String smiles) {
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

    public ModelCompound withInchikey(java.lang.String inchikey) {
        this.inchikey = inchikey;
        return this;
    }

    @JsonProperty("modelcompartment_ref")
    public java.lang.String getModelcompartmentRef() {
        return modelcompartmentRef;
    }

    @JsonProperty("modelcompartment_ref")
    public void setModelcompartmentRef(java.lang.String modelcompartmentRef) {
        this.modelcompartmentRef = modelcompartmentRef;
    }

    public ModelCompound withModelcompartmentRef(java.lang.String modelcompartmentRef) {
        this.modelcompartmentRef = modelcompartmentRef;
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
        return ((((((((((((((((((((((((((((((("ModelCompound"+" [id=")+ id)+", displayID=")+ displayID)+", compoundRef=")+ compoundRef)+", dblinks=")+ dblinks)+", stringAttributes=")+ stringAttributes)+", numericalAttributes=")+ numericalAttributes)+", aliases=")+ aliases)+", name=")+ name)+", charge=")+ charge)+", maxuptake=")+ maxuptake)+", formula=")+ formula)+", smiles=")+ smiles)+", inchikey=")+ inchikey)+", modelcompartmentRef=")+ modelcompartmentRef)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
