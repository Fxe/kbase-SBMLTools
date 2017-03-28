
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
 * <p>Original spec-file type: ETCPathwayObj</p>
 * <pre>
 * ETCPathwayObj object
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "electron_acceptor",
    "steps"
})
public class ETCPathwayObj {

    @JsonProperty("electron_acceptor")
    private String electronAcceptor;
    @JsonProperty("steps")
    private List<ETCStep> steps;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("electron_acceptor")
    public String getElectronAcceptor() {
        return electronAcceptor;
    }

    @JsonProperty("electron_acceptor")
    public void setElectronAcceptor(String electronAcceptor) {
        this.electronAcceptor = electronAcceptor;
    }

    public ETCPathwayObj withElectronAcceptor(String electronAcceptor) {
        this.electronAcceptor = electronAcceptor;
        return this;
    }

    @JsonProperty("steps")
    public List<ETCStep> getSteps() {
        return steps;
    }

    @JsonProperty("steps")
    public void setSteps(List<ETCStep> steps) {
        this.steps = steps;
    }

    public ETCPathwayObj withSteps(List<ETCStep> steps) {
        this.steps = steps;
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
        return ((((((("ETCPathwayObj"+" [electronAcceptor=")+ electronAcceptor)+", steps=")+ steps)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
