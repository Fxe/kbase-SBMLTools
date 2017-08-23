
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
 * <p>Original spec-file type: ProteinFamily</p>
 * <pre>
 * Structure for a protein family
 *         @optional query_begin query_end subject_begin subject_end score evalue subject_description release_version
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "subject_db",
    "release_version",
    "subject_description",
    "query_begin",
    "query_end",
    "subject_begin",
    "subject_end",
    "score",
    "evalue"
})
public class ProteinFamily {

    @JsonProperty("id")
    private String id;
    @JsonProperty("subject_db")
    private String subjectDb;
    @JsonProperty("release_version")
    private String releaseVersion;
    @JsonProperty("subject_description")
    private String subjectDescription;
    @JsonProperty("query_begin")
    private Long queryBegin;
    @JsonProperty("query_end")
    private Long queryEnd;
    @JsonProperty("subject_begin")
    private Long subjectBegin;
    @JsonProperty("subject_end")
    private Long subjectEnd;
    @JsonProperty("score")
    private Double score;
    @JsonProperty("evalue")
    private Double evalue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ProteinFamily withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("subject_db")
    public String getSubjectDb() {
        return subjectDb;
    }

    @JsonProperty("subject_db")
    public void setSubjectDb(String subjectDb) {
        this.subjectDb = subjectDb;
    }

    public ProteinFamily withSubjectDb(String subjectDb) {
        this.subjectDb = subjectDb;
        return this;
    }

    @JsonProperty("release_version")
    public String getReleaseVersion() {
        return releaseVersion;
    }

    @JsonProperty("release_version")
    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public ProteinFamily withReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
        return this;
    }

    @JsonProperty("subject_description")
    public String getSubjectDescription() {
        return subjectDescription;
    }

    @JsonProperty("subject_description")
    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public ProteinFamily withSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
        return this;
    }

    @JsonProperty("query_begin")
    public Long getQueryBegin() {
        return queryBegin;
    }

    @JsonProperty("query_begin")
    public void setQueryBegin(Long queryBegin) {
        this.queryBegin = queryBegin;
    }

    public ProteinFamily withQueryBegin(Long queryBegin) {
        this.queryBegin = queryBegin;
        return this;
    }

    @JsonProperty("query_end")
    public Long getQueryEnd() {
        return queryEnd;
    }

    @JsonProperty("query_end")
    public void setQueryEnd(Long queryEnd) {
        this.queryEnd = queryEnd;
    }

    public ProteinFamily withQueryEnd(Long queryEnd) {
        this.queryEnd = queryEnd;
        return this;
    }

    @JsonProperty("subject_begin")
    public Long getSubjectBegin() {
        return subjectBegin;
    }

    @JsonProperty("subject_begin")
    public void setSubjectBegin(Long subjectBegin) {
        this.subjectBegin = subjectBegin;
    }

    public ProteinFamily withSubjectBegin(Long subjectBegin) {
        this.subjectBegin = subjectBegin;
        return this;
    }

    @JsonProperty("subject_end")
    public Long getSubjectEnd() {
        return subjectEnd;
    }

    @JsonProperty("subject_end")
    public void setSubjectEnd(Long subjectEnd) {
        this.subjectEnd = subjectEnd;
    }

    public ProteinFamily withSubjectEnd(Long subjectEnd) {
        this.subjectEnd = subjectEnd;
        return this;
    }

    @JsonProperty("score")
    public Double getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Double score) {
        this.score = score;
    }

    public ProteinFamily withScore(Double score) {
        this.score = score;
        return this;
    }

    @JsonProperty("evalue")
    public Double getEvalue() {
        return evalue;
    }

    @JsonProperty("evalue")
    public void setEvalue(Double evalue) {
        this.evalue = evalue;
    }

    public ProteinFamily withEvalue(Double evalue) {
        this.evalue = evalue;
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
        return ((((((((((((((((((((((("ProteinFamily"+" [id=")+ id)+", subjectDb=")+ subjectDb)+", releaseVersion=")+ releaseVersion)+", subjectDescription=")+ subjectDescription)+", queryBegin=")+ queryBegin)+", queryEnd=")+ queryEnd)+", subjectBegin=")+ subjectBegin)+", subjectEnd=")+ subjectEnd)+", score=")+ score)+", evalue=")+ evalue)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
