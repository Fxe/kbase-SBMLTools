
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
 * <p>Original spec-file type: MissingRoleItem</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "role_id",
    "role_description",
    "genome_hits",
    "blast_score",
    "perc_identity",
    "hit_location",
    "protein_sequence",
    "reactions"
})
public class MissingRoleItem {

    @JsonProperty("role_id")
    private String roleId;
    @JsonProperty("role_description")
    private String roleDescription;
    @JsonProperty("genome_hits")
    private String genomeHits;
    @JsonProperty("blast_score")
    private String blastScore;
    @JsonProperty("perc_identity")
    private Double percIdentity;
    @JsonProperty("hit_location")
    private String hitLocation;
    @JsonProperty("protein_sequence")
    private String proteinSequence;
    @JsonProperty("reactions")
    private List<ReactionItem> reactions;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("role_id")
    public String getRoleId() {
        return roleId;
    }

    @JsonProperty("role_id")
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public MissingRoleItem withRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    @JsonProperty("role_description")
    public String getRoleDescription() {
        return roleDescription;
    }

    @JsonProperty("role_description")
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public MissingRoleItem withRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
        return this;
    }

    @JsonProperty("genome_hits")
    public String getGenomeHits() {
        return genomeHits;
    }

    @JsonProperty("genome_hits")
    public void setGenomeHits(String genomeHits) {
        this.genomeHits = genomeHits;
    }

    public MissingRoleItem withGenomeHits(String genomeHits) {
        this.genomeHits = genomeHits;
        return this;
    }

    @JsonProperty("blast_score")
    public String getBlastScore() {
        return blastScore;
    }

    @JsonProperty("blast_score")
    public void setBlastScore(String blastScore) {
        this.blastScore = blastScore;
    }

    public MissingRoleItem withBlastScore(String blastScore) {
        this.blastScore = blastScore;
        return this;
    }

    @JsonProperty("perc_identity")
    public Double getPercIdentity() {
        return percIdentity;
    }

    @JsonProperty("perc_identity")
    public void setPercIdentity(Double percIdentity) {
        this.percIdentity = percIdentity;
    }

    public MissingRoleItem withPercIdentity(Double percIdentity) {
        this.percIdentity = percIdentity;
        return this;
    }

    @JsonProperty("hit_location")
    public String getHitLocation() {
        return hitLocation;
    }

    @JsonProperty("hit_location")
    public void setHitLocation(String hitLocation) {
        this.hitLocation = hitLocation;
    }

    public MissingRoleItem withHitLocation(String hitLocation) {
        this.hitLocation = hitLocation;
        return this;
    }

    @JsonProperty("protein_sequence")
    public String getProteinSequence() {
        return proteinSequence;
    }

    @JsonProperty("protein_sequence")
    public void setProteinSequence(String proteinSequence) {
        this.proteinSequence = proteinSequence;
    }

    public MissingRoleItem withProteinSequence(String proteinSequence) {
        this.proteinSequence = proteinSequence;
        return this;
    }

    @JsonProperty("reactions")
    public List<ReactionItem> getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(List<ReactionItem> reactions) {
        this.reactions = reactions;
    }

    public MissingRoleItem withReactions(List<ReactionItem> reactions) {
        this.reactions = reactions;
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
        return ((((((((((((((((((("MissingRoleItem"+" [roleId=")+ roleId)+", roleDescription=")+ roleDescription)+", genomeHits=")+ genomeHits)+", blastScore=")+ blastScore)+", percIdentity=")+ percIdentity)+", hitLocation=")+ hitLocation)+", proteinSequence=")+ proteinSequence)+", reactions=")+ reactions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
