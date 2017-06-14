
package kbasereport;

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
 * <p>Original spec-file type: Report</p>
 * <pre>
 * A simple Report of a method run in KBase.
 * It only provides for now a way to display a fixed width text output summary message, a
 * list of warnings, and a list of objects created (each with descriptions).
 * @optional warnings file_links html_links direct_html direct_html_link_index
 * @metadata ws length(warnings) as Warnings
 * @metadata ws length(text_message) as Size(characters)
 * @metadata ws length(objects_created) as Objects Created
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "text_message",
    "warnings",
    "objects_created",
    "file_links",
    "html_links",
    "direct_html",
    "direct_html_link_index"
})
public class Report {

    @JsonProperty("text_message")
    private java.lang.String textMessage;
    @JsonProperty("warnings")
    private List<String> warnings;
    @JsonProperty("objects_created")
    private List<WorkspaceObject> objectsCreated;
    @JsonProperty("file_links")
    private List<kbasereport.LinkedFile> fileLinks;
    @JsonProperty("html_links")
    private List<kbasereport.LinkedFile> htmlLinks;
    @JsonProperty("direct_html")
    private java.lang.String directHtml;
    @JsonProperty("direct_html_link_index")
    private Long directHtmlLinkIndex;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("text_message")
    public java.lang.String getTextMessage() {
        return textMessage;
    }

    @JsonProperty("text_message")
    public void setTextMessage(java.lang.String textMessage) {
        this.textMessage = textMessage;
    }

    public Report withTextMessage(java.lang.String textMessage) {
        this.textMessage = textMessage;
        return this;
    }

    @JsonProperty("warnings")
    public List<String> getWarnings() {
        return warnings;
    }

    @JsonProperty("warnings")
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public Report withWarnings(List<String> warnings) {
        this.warnings = warnings;
        return this;
    }

    @JsonProperty("objects_created")
    public List<WorkspaceObject> getObjectsCreated() {
        return objectsCreated;
    }

    @JsonProperty("objects_created")
    public void setObjectsCreated(List<WorkspaceObject> objectsCreated) {
        this.objectsCreated = objectsCreated;
    }

    public Report withObjectsCreated(List<WorkspaceObject> objectsCreated) {
        this.objectsCreated = objectsCreated;
        return this;
    }

    @JsonProperty("file_links")
    public List<kbasereport.LinkedFile> getFileLinks() {
        return fileLinks;
    }

    @JsonProperty("file_links")
    public void setFileLinks(List<kbasereport.LinkedFile> fileLinks) {
        this.fileLinks = fileLinks;
    }

    public Report withFileLinks(List<kbasereport.LinkedFile> fileLinks) {
        this.fileLinks = fileLinks;
        return this;
    }

    @JsonProperty("html_links")
    public List<kbasereport.LinkedFile> getHtmlLinks() {
        return htmlLinks;
    }

    @JsonProperty("html_links")
    public void setHtmlLinks(List<kbasereport.LinkedFile> htmlLinks) {
        this.htmlLinks = htmlLinks;
    }

    public Report withHtmlLinks(List<kbasereport.LinkedFile> htmlLinks) {
        this.htmlLinks = htmlLinks;
        return this;
    }

    @JsonProperty("direct_html")
    public java.lang.String getDirectHtml() {
        return directHtml;
    }

    @JsonProperty("direct_html")
    public void setDirectHtml(java.lang.String directHtml) {
        this.directHtml = directHtml;
    }

    public Report withDirectHtml(java.lang.String directHtml) {
        this.directHtml = directHtml;
        return this;
    }

    @JsonProperty("direct_html_link_index")
    public Long getDirectHtmlLinkIndex() {
        return directHtmlLinkIndex;
    }

    @JsonProperty("direct_html_link_index")
    public void setDirectHtmlLinkIndex(Long directHtmlLinkIndex) {
        this.directHtmlLinkIndex = directHtmlLinkIndex;
    }

    public Report withDirectHtmlLinkIndex(Long directHtmlLinkIndex) {
        this.directHtmlLinkIndex = directHtmlLinkIndex;
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
        return ((((((((((((((((("Report"+" [textMessage=")+ textMessage)+", warnings=")+ warnings)+", objectsCreated=")+ objectsCreated)+", fileLinks=")+ fileLinks)+", htmlLinks=")+ htmlLinks)+", directHtml=")+ directHtml)+", directHtmlLinkIndex=")+ directHtmlLinkIndex)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
