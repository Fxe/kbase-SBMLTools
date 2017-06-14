
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
 * <p>Original spec-file type: CreateExtendedReportParams</p>
 * <pre>
 * Parameters used to create a more complex report with file and html links
 * The following arguments allow the user to specify the classical data fields in the report object:
 * string message - simple text message to store in report object
 * list <WorkspaceObject> objects_created;
 * list <string> warnings - a list of warning messages in simple text
 * The following argument allows the user to specify the location of html files/directories that the report widget will render <or> link to:
 * list <fileRef> html_links - a list of paths or shock node IDs pointing to a single flat html file or to the top level directory of a website
 * The report widget can render one html view directly. Set one of the following fields to decide which view to render:
 * string direct_html - simple html text that will be rendered within the report widget
 * int  direct_html_link_index - use this to specify the index of the page in html_links to view directly in the report widget (ignored if html_string is set)
 * The following argument allows the user to specify the location of files that the report widget should link for download:
 * list <fileRef> file_links - a list of paths or shock node IDs pointing to a single flat file
 * The following parameters indicate where the report object should be saved in the workspace:
 * string report_object_name - name to use for the report object (job ID is used if left unspecified)
 * html_window_height - height of the html window in the narrative output widget
 * summary_window_height - height of summary window in the narrative output widget
 * string workspace_name - name of workspace where object should be saved
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "message",
    "objects_created",
    "warnings",
    "html_links",
    "direct_html",
    "direct_html_link_index",
    "file_links",
    "report_object_name",
    "html_window_height",
    "summary_window_height",
    "workspace_name"
})
public class CreateExtendedReportParams {

    @JsonProperty("message")
    private java.lang.String message;
    @JsonProperty("objects_created")
    private List<WorkspaceObject> objectsCreated;
    @JsonProperty("warnings")
    private List<String> warnings;
    @JsonProperty("html_links")
    private List<kbasereport.File> htmlLinks;
    @JsonProperty("direct_html")
    private java.lang.String directHtml;
    @JsonProperty("direct_html_link_index")
    private Long directHtmlLinkIndex;
    @JsonProperty("file_links")
    private List<kbasereport.File> fileLinks;
    @JsonProperty("report_object_name")
    private java.lang.String reportObjectName;
    @JsonProperty("html_window_height")
    private Double htmlWindowHeight;
    @JsonProperty("summary_window_height")
    private Double summaryWindowHeight;
    @JsonProperty("workspace_name")
    private java.lang.String workspaceName;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("message")
    public java.lang.String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(java.lang.String message) {
        this.message = message;
    }

    public CreateExtendedReportParams withMessage(java.lang.String message) {
        this.message = message;
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

    public CreateExtendedReportParams withObjectsCreated(List<WorkspaceObject> objectsCreated) {
        this.objectsCreated = objectsCreated;
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

    public CreateExtendedReportParams withWarnings(List<String> warnings) {
        this.warnings = warnings;
        return this;
    }

    @JsonProperty("html_links")
    public List<kbasereport.File> getHtmlLinks() {
        return htmlLinks;
    }

    @JsonProperty("html_links")
    public void setHtmlLinks(List<kbasereport.File> htmlLinks) {
        this.htmlLinks = htmlLinks;
    }

    public CreateExtendedReportParams withHtmlLinks(List<kbasereport.File> htmlLinks) {
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

    public CreateExtendedReportParams withDirectHtml(java.lang.String directHtml) {
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

    public CreateExtendedReportParams withDirectHtmlLinkIndex(Long directHtmlLinkIndex) {
        this.directHtmlLinkIndex = directHtmlLinkIndex;
        return this;
    }

    @JsonProperty("file_links")
    public List<kbasereport.File> getFileLinks() {
        return fileLinks;
    }

    @JsonProperty("file_links")
    public void setFileLinks(List<kbasereport.File> fileLinks) {
        this.fileLinks = fileLinks;
    }

    public CreateExtendedReportParams withFileLinks(List<kbasereport.File> fileLinks) {
        this.fileLinks = fileLinks;
        return this;
    }

    @JsonProperty("report_object_name")
    public java.lang.String getReportObjectName() {
        return reportObjectName;
    }

    @JsonProperty("report_object_name")
    public void setReportObjectName(java.lang.String reportObjectName) {
        this.reportObjectName = reportObjectName;
    }

    public CreateExtendedReportParams withReportObjectName(java.lang.String reportObjectName) {
        this.reportObjectName = reportObjectName;
        return this;
    }

    @JsonProperty("html_window_height")
    public Double getHtmlWindowHeight() {
        return htmlWindowHeight;
    }

    @JsonProperty("html_window_height")
    public void setHtmlWindowHeight(Double htmlWindowHeight) {
        this.htmlWindowHeight = htmlWindowHeight;
    }

    public CreateExtendedReportParams withHtmlWindowHeight(Double htmlWindowHeight) {
        this.htmlWindowHeight = htmlWindowHeight;
        return this;
    }

    @JsonProperty("summary_window_height")
    public Double getSummaryWindowHeight() {
        return summaryWindowHeight;
    }

    @JsonProperty("summary_window_height")
    public void setSummaryWindowHeight(Double summaryWindowHeight) {
        this.summaryWindowHeight = summaryWindowHeight;
    }

    public CreateExtendedReportParams withSummaryWindowHeight(Double summaryWindowHeight) {
        this.summaryWindowHeight = summaryWindowHeight;
        return this;
    }

    @JsonProperty("workspace_name")
    public java.lang.String getWorkspaceName() {
        return workspaceName;
    }

    @JsonProperty("workspace_name")
    public void setWorkspaceName(java.lang.String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public CreateExtendedReportParams withWorkspaceName(java.lang.String workspaceName) {
        this.workspaceName = workspaceName;
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
        return ((((((((((((((((((((((((("CreateExtendedReportParams"+" [message=")+ message)+", objectsCreated=")+ objectsCreated)+", warnings=")+ warnings)+", htmlLinks=")+ htmlLinks)+", directHtml=")+ directHtml)+", directHtmlLinkIndex=")+ directHtmlLinkIndex)+", fileLinks=")+ fileLinks)+", reportObjectName=")+ reportObjectName)+", htmlWindowHeight=")+ htmlWindowHeight)+", summaryWindowHeight=")+ summaryWindowHeight)+", workspaceName=")+ workspaceName)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
