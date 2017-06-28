
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
 * <p>Original spec-file type: MissingRoleData</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "contigset_id",
    "missing_roles",
    "close_genomes",
    "found_roles"
})
public class MissingRoleData {

    @JsonProperty("contigset_id")
    private String contigsetId;
    @JsonProperty("missing_roles")
    private List<MissingRoleItem> missingRoles;
    @JsonProperty("close_genomes")
    private List<CloseGenomeItem> closeGenomes;
    @JsonProperty("found_roles")
    private List<FoundRoleItem> foundRoles;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("contigset_id")
    public String getContigsetId() {
        return contigsetId;
    }

    @JsonProperty("contigset_id")
    public void setContigsetId(String contigsetId) {
        this.contigsetId = contigsetId;
    }

    public MissingRoleData withContigsetId(String contigsetId) {
        this.contigsetId = contigsetId;
        return this;
    }

    @JsonProperty("missing_roles")
    public List<MissingRoleItem> getMissingRoles() {
        return missingRoles;
    }

    @JsonProperty("missing_roles")
    public void setMissingRoles(List<MissingRoleItem> missingRoles) {
        this.missingRoles = missingRoles;
    }

    public MissingRoleData withMissingRoles(List<MissingRoleItem> missingRoles) {
        this.missingRoles = missingRoles;
        return this;
    }

    @JsonProperty("close_genomes")
    public List<CloseGenomeItem> getCloseGenomes() {
        return closeGenomes;
    }

    @JsonProperty("close_genomes")
    public void setCloseGenomes(List<CloseGenomeItem> closeGenomes) {
        this.closeGenomes = closeGenomes;
    }

    public MissingRoleData withCloseGenomes(List<CloseGenomeItem> closeGenomes) {
        this.closeGenomes = closeGenomes;
        return this;
    }

    @JsonProperty("found_roles")
    public List<FoundRoleItem> getFoundRoles() {
        return foundRoles;
    }

    @JsonProperty("found_roles")
    public void setFoundRoles(List<FoundRoleItem> foundRoles) {
        this.foundRoles = foundRoles;
    }

    public MissingRoleData withFoundRoles(List<FoundRoleItem> foundRoles) {
        this.foundRoles = foundRoles;
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
        return ((((((((((("MissingRoleData"+" [contigsetId=")+ contigsetId)+", missingRoles=")+ missingRoles)+", closeGenomes=")+ closeGenomes)+", foundRoles=")+ foundRoles)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
