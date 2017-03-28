
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
 * <p>Original spec-file type: PromConstraint</p>
 * <pre>
 * An object that encapsulates the information necessary to apply PROM-based constraints to an FBA model. This
 * includes a regulatory network consisting of a set of regulatory interactions (implied by the set of TFtoTGmap
 * objects) and interaction probabilities as defined in each TargetGeneProbabilities object.  A link the the annotation
 * object is required in order to properly link to an FBA model object.  A reference to the expression_data_collection
 * used to compute the interaction probabilities is provided for future reference.
 *     string id                                         - the id of this prom_constraints object in a
 *                                                                     workspace
 *     genome_ref                                                                        
 *                                                                     which specfies how TFs and targets are named
 *     list<TFtoTGmap> transcriptionFactorMaps                                     - the list of TFMaps which specifies both the
 *                                                                     regulatory network and interaction probabilities
 *                                                                     between TF and target genes
 *     expression_series_ref expression_series_ref   - the id of the expresion_data_collection object in
 *                                                                     the workspace which was used to compute the
 *                                                                     regulatory interaction probabilities
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "genome_ref",
    "transcriptionFactorMaps",
    "expression_series_ref",
    "regulome_ref"
})
public class PromConstraint {

    @JsonProperty("id")
    private String id;
    @JsonProperty("genome_ref")
    private String genomeRef;
    @JsonProperty("transcriptionFactorMaps")
    private List<TFtoTGmap> transcriptionFactorMaps;
    @JsonProperty("expression_series_ref")
    private String expressionSeriesRef;
    @JsonProperty("regulome_ref")
    private String regulomeRef;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public PromConstraint withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("genome_ref")
    public String getGenomeRef() {
        return genomeRef;
    }

    @JsonProperty("genome_ref")
    public void setGenomeRef(String genomeRef) {
        this.genomeRef = genomeRef;
    }

    public PromConstraint withGenomeRef(String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("transcriptionFactorMaps")
    public List<TFtoTGmap> getTranscriptionFactorMaps() {
        return transcriptionFactorMaps;
    }

    @JsonProperty("transcriptionFactorMaps")
    public void setTranscriptionFactorMaps(List<TFtoTGmap> transcriptionFactorMaps) {
        this.transcriptionFactorMaps = transcriptionFactorMaps;
    }

    public PromConstraint withTranscriptionFactorMaps(List<TFtoTGmap> transcriptionFactorMaps) {
        this.transcriptionFactorMaps = transcriptionFactorMaps;
        return this;
    }

    @JsonProperty("expression_series_ref")
    public String getExpressionSeriesRef() {
        return expressionSeriesRef;
    }

    @JsonProperty("expression_series_ref")
    public void setExpressionSeriesRef(String expressionSeriesRef) {
        this.expressionSeriesRef = expressionSeriesRef;
    }

    public PromConstraint withExpressionSeriesRef(String expressionSeriesRef) {
        this.expressionSeriesRef = expressionSeriesRef;
        return this;
    }

    @JsonProperty("regulome_ref")
    public String getRegulomeRef() {
        return regulomeRef;
    }

    @JsonProperty("regulome_ref")
    public void setRegulomeRef(String regulomeRef) {
        this.regulomeRef = regulomeRef;
    }

    public PromConstraint withRegulomeRef(String regulomeRef) {
        this.regulomeRef = regulomeRef;
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
        return ((((((((((((("PromConstraint"+" [id=")+ id)+", genomeRef=")+ genomeRef)+", transcriptionFactorMaps=")+ transcriptionFactorMaps)+", expressionSeriesRef=")+ expressionSeriesRef)+", regulomeRef=")+ regulomeRef)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
