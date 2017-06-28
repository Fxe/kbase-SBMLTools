
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
 * <p>Original spec-file type: ModelReactionProtein</p>
 * <pre>
 * ModelReactionProtein object
 * @optional source complex_ref
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "complex_ref",
    "note",
    "modelReactionProteinSubunits",
    "source"
})
public class ModelReactionProtein {

    @JsonProperty("complex_ref")
    private String complexRef;
    @JsonProperty("note")
    private String note;
    @JsonProperty("modelReactionProteinSubunits")
    private List<ModelReactionProteinSubunit> modelReactionProteinSubunits;
    @JsonProperty("source")
    private String source;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("complex_ref")
    public String getComplexRef() {
        return complexRef;
    }

    @JsonProperty("complex_ref")
    public void setComplexRef(String complexRef) {
        this.complexRef = complexRef;
    }

    public ModelReactionProtein withComplexRef(String complexRef) {
        this.complexRef = complexRef;
        return this;
    }

    @JsonProperty("note")
    public String getNote() {
        return note;
    }

    @JsonProperty("note")
    public void setNote(String note) {
        this.note = note;
    }

    public ModelReactionProtein withNote(String note) {
        this.note = note;
        return this;
    }

    @JsonProperty("modelReactionProteinSubunits")
    public List<ModelReactionProteinSubunit> getModelReactionProteinSubunits() {
        return modelReactionProteinSubunits;
    }

    @JsonProperty("modelReactionProteinSubunits")
    public void setModelReactionProteinSubunits(List<ModelReactionProteinSubunit> modelReactionProteinSubunits) {
        this.modelReactionProteinSubunits = modelReactionProteinSubunits;
    }

    public ModelReactionProtein withModelReactionProteinSubunits(List<ModelReactionProteinSubunit> modelReactionProteinSubunits) {
        this.modelReactionProteinSubunits = modelReactionProteinSubunits;
        return this;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    public ModelReactionProtein withSource(String source) {
        this.source = source;
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
        return ((((((((((("ModelReactionProtein"+" [complexRef=")+ complexRef)+", note=")+ note)+", modelReactionProteinSubunits=")+ modelReactionProteinSubunits)+", source=")+ source)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
