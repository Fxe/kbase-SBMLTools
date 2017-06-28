
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
 * <p>Original spec-file type: ModelComparison</p>
 * <pre>
 * ModelComparisonResult object: this object holds information about a comparison of multiple models
 * @optional protcomp_ref pangenome_ref
 * @metadata ws core_reactions as Core reactions
 * @metadata ws core_compounds as Core compounds
 * @metadata ws core_families as Core families
 * @metadata ws core_biomass_compounds as Core biomass compounds
 * @metadata ws name as Name
 * @metadata ws id as ID
 * @metadata ws length(models) as Number models
 * @metadata ws length(reactions) as Number reactions
 * @metadata ws length(compounds) as Number compounds
 * @metadata ws length(families) as Number families
 * @metadata ws length(biomasscpds) as Number biomass compounds
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "core_reactions",
    "core_compounds",
    "core_families",
    "core_biomass_compounds",
    "protcomp_ref",
    "pangenome_ref",
    "models",
    "reactions",
    "compounds",
    "families",
    "biomasscpds"
})
public class ModelComparison {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("core_reactions")
    private Long coreReactions;
    @JsonProperty("core_compounds")
    private Long coreCompounds;
    @JsonProperty("core_families")
    private Long coreFamilies;
    @JsonProperty("core_biomass_compounds")
    private Long coreBiomassCompounds;
    @JsonProperty("protcomp_ref")
    private String protcompRef;
    @JsonProperty("pangenome_ref")
    private String pangenomeRef;
    @JsonProperty("models")
    private List<ModelComparisonModel> models;
    @JsonProperty("reactions")
    private List<ModelComparisonReaction> reactions;
    @JsonProperty("compounds")
    private List<ModelComparisonCompound> compounds;
    @JsonProperty("families")
    private List<ModelComparisonFamily> families;
    @JsonProperty("biomasscpds")
    private List<ModelComparisonBiomassCompound> biomasscpds;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ModelComparison withId(String id) {
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

    public ModelComparison withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("core_reactions")
    public Long getCoreReactions() {
        return coreReactions;
    }

    @JsonProperty("core_reactions")
    public void setCoreReactions(Long coreReactions) {
        this.coreReactions = coreReactions;
    }

    public ModelComparison withCoreReactions(Long coreReactions) {
        this.coreReactions = coreReactions;
        return this;
    }

    @JsonProperty("core_compounds")
    public Long getCoreCompounds() {
        return coreCompounds;
    }

    @JsonProperty("core_compounds")
    public void setCoreCompounds(Long coreCompounds) {
        this.coreCompounds = coreCompounds;
    }

    public ModelComparison withCoreCompounds(Long coreCompounds) {
        this.coreCompounds = coreCompounds;
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

    public ModelComparison withCoreFamilies(Long coreFamilies) {
        this.coreFamilies = coreFamilies;
        return this;
    }

    @JsonProperty("core_biomass_compounds")
    public Long getCoreBiomassCompounds() {
        return coreBiomassCompounds;
    }

    @JsonProperty("core_biomass_compounds")
    public void setCoreBiomassCompounds(Long coreBiomassCompounds) {
        this.coreBiomassCompounds = coreBiomassCompounds;
    }

    public ModelComparison withCoreBiomassCompounds(Long coreBiomassCompounds) {
        this.coreBiomassCompounds = coreBiomassCompounds;
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

    public ModelComparison withProtcompRef(String protcompRef) {
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

    public ModelComparison withPangenomeRef(String pangenomeRef) {
        this.pangenomeRef = pangenomeRef;
        return this;
    }

    @JsonProperty("models")
    public List<ModelComparisonModel> getModels() {
        return models;
    }

    @JsonProperty("models")
    public void setModels(List<ModelComparisonModel> models) {
        this.models = models;
    }

    public ModelComparison withModels(List<ModelComparisonModel> models) {
        this.models = models;
        return this;
    }

    @JsonProperty("reactions")
    public List<ModelComparisonReaction> getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(List<ModelComparisonReaction> reactions) {
        this.reactions = reactions;
    }

    public ModelComparison withReactions(List<ModelComparisonReaction> reactions) {
        this.reactions = reactions;
        return this;
    }

    @JsonProperty("compounds")
    public List<ModelComparisonCompound> getCompounds() {
        return compounds;
    }

    @JsonProperty("compounds")
    public void setCompounds(List<ModelComparisonCompound> compounds) {
        this.compounds = compounds;
    }

    public ModelComparison withCompounds(List<ModelComparisonCompound> compounds) {
        this.compounds = compounds;
        return this;
    }

    @JsonProperty("families")
    public List<ModelComparisonFamily> getFamilies() {
        return families;
    }

    @JsonProperty("families")
    public void setFamilies(List<ModelComparisonFamily> families) {
        this.families = families;
    }

    public ModelComparison withFamilies(List<ModelComparisonFamily> families) {
        this.families = families;
        return this;
    }

    @JsonProperty("biomasscpds")
    public List<ModelComparisonBiomassCompound> getBiomasscpds() {
        return biomasscpds;
    }

    @JsonProperty("biomasscpds")
    public void setBiomasscpds(List<ModelComparisonBiomassCompound> biomasscpds) {
        this.biomasscpds = biomasscpds;
    }

    public ModelComparison withBiomasscpds(List<ModelComparisonBiomassCompound> biomasscpds) {
        this.biomasscpds = biomasscpds;
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
        return ((((((((((((((((((((((((((((("ModelComparison"+" [id=")+ id)+", name=")+ name)+", coreReactions=")+ coreReactions)+", coreCompounds=")+ coreCompounds)+", coreFamilies=")+ coreFamilies)+", coreBiomassCompounds=")+ coreBiomassCompounds)+", protcompRef=")+ protcompRef)+", pangenomeRef=")+ pangenomeRef)+", models=")+ models)+", reactions=")+ reactions)+", compounds=")+ compounds)+", families=")+ families)+", biomasscpds=")+ biomasscpds)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
