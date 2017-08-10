package us.kbase.workspace;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.Tuple12;
import us.kbase.common.service.Tuple7;
import us.kbase.common.service.Tuple9;
import us.kbase.common.service.UObject;
import us.kbase.common.service.UnauthorizedException;

/**
 * <p>Original spec-file module name: Workspace</p>
 * <pre>
 * The Workspace Service (WSS) is primarily a language independent remote storage
 * and retrieval system for KBase typed objects (TO) defined with the KBase
 * Interface Description Language (KIDL). It has the following primary features:
 * - Immutable storage of TOs with
 *         - user defined metadata 
 *         - data provenance
 * - Versioning of TOs
 * - Referencing from TO to TO
 * - Typechecking of all saved objects against a KIDL specification
 * - Collecting typed objects into a workspace
 * - Sharing workspaces with specific KBase users or the world
 * - Freezing and publishing workspaces
 * </pre>
 */
public class WorkspaceClient {
    private JsonClientCaller caller;
    private String serviceVersion = null;
    private static URL DEFAULT_URL = null;
    static {
        try {
            DEFAULT_URL = new URL("https://kbase.us/services/ws/");
        } catch (MalformedURLException mue) {
            throw new RuntimeException("Compile error in client - bad url compiled");
        }
    }

    /** Constructs a client with the default url and no user credentials.*/
    public WorkspaceClient() {
       caller = new JsonClientCaller(DEFAULT_URL);
    }


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public WorkspaceClient(URL url) {
        caller = new JsonClientCaller(url);
    }
    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public WorkspaceClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, token);
    }

    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param user the user name.
     * @param password the password for the user name.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public WorkspaceClient(URL url, String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password);
    }

    /** Constructs a client with a custom URL
     * and a custom authorization service URL.
     * @param url the URL of the service.
     * @param user the user name.
     * @param password the password for the user name.
     * @param auth the URL of the authorization server.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public WorkspaceClient(URL url, String user, String password, URL auth) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password, auth);
    }

    /** Constructs a client with the default URL.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public WorkspaceClient(AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(DEFAULT_URL, token);
    }

    /** Constructs a client with the default URL.
     * @param user the user name.
     * @param password the password for the user name.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public WorkspaceClient(String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(DEFAULT_URL, user, password);
    }

    /** Get the token this client uses to communicate with the server.
     * @return the authorization token.
     */
    public AuthToken getToken() {
        return caller.getToken();
    }

    /** Get the URL of the service with which this client communicates.
     * @return the service URL.
     */
    public URL getURL() {
        return caller.getURL();
    }

    /** Set the timeout between establishing a connection to a server and
     * receiving a response. A value of zero or null implies no timeout.
     * @param milliseconds the milliseconds to wait before timing out when
     * attempting to read from a server.
     */
    public void setConnectionReadTimeOut(Integer milliseconds) {
        this.caller.setConnectionReadTimeOut(milliseconds);
    }

    /** Check if this client allows insecure http (vs https) connections.
     * @return true if insecure connections are allowed.
     */
    public boolean isInsecureHttpConnectionAllowed() {
        return caller.isInsecureHttpConnectionAllowed();
    }

    /** Deprecated. Use isInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public boolean isAuthAllowedForHttp() {
        return caller.isAuthAllowedForHttp();
    }

    /** Set whether insecure http (vs https) connections should be allowed by
     * this client.
     * @param allowed true to allow insecure connections. Default false
     */
    public void setIsInsecureHttpConnectionAllowed(boolean allowed) {
        caller.setInsecureHttpConnectionAllowed(allowed);
    }

    /** Deprecated. Use setIsInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public void setAuthAllowedForHttp(boolean isAuthAllowedForHttp) {
        caller.setAuthAllowedForHttp(isAuthAllowedForHttp);
    }

    /** Set whether all SSL certificates, including self-signed certificates,
     * should be trusted.
     * @param trustAll true to trust all certificates. Default false.
     */
    public void setAllSSLCertificatesTrusted(final boolean trustAll) {
        caller.setAllSSLCertificatesTrusted(trustAll);
    }
    
    /** Check if this client trusts all SSL certificates, including
     * self-signed certificates.
     * @return true if all certificates are trusted.
     */
    public boolean isAllSSLCertificatesTrusted() {
        return caller.isAllSSLCertificatesTrusted();
    }
    /** Sets streaming mode on. In this case, the data will be streamed to
     * the server in chunks as it is read from disk rather than buffered in
     * memory. Many servers are not compatible with this feature.
     * @param streamRequest true to set streaming mode on, false otherwise.
     */
    public void setStreamingModeOn(boolean streamRequest) {
        caller.setStreamingModeOn(streamRequest);
    }

    /** Returns true if streaming mode is on.
     * @return true if streaming mode is on.
     */
    public boolean isStreamingModeOn() {
        return caller.isStreamingModeOn();
    }

    public void _setFileForNextRpcResponse(File f) {
        caller.setFileForNextRpcResponse(f);
    }

    public String getServiceVersion() {
        return this.serviceVersion;
    }

    public void setServiceVersion(String newValue) {
        this.serviceVersion = newValue;
    }

    /**
     * <p>Original spec-file function name: ver</p>
     * <pre>
     * Returns the version of the workspace service.
     * </pre>
     * @return   parameter "ver" of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String ver(RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("Workspace.ver", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: create_workspace</p>
     * <pre>
     * Creates a new workspace.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.CreateWorkspaceParams CreateWorkspaceParams}
     * @return   parameter "info" of original type "workspace_info" (Information about a workspace. ws_id id - the numerical ID of the workspace. ws_name workspace - name of the workspace. username owner - name of the user who owns (e.g. created) this workspace. timestamp moddate - date when the workspace was last modified. int max_objid - the maximum object ID appearing in this workspace. Since cloning a workspace preserves object IDs, this number may be greater than the number of objects in a newly cloned workspace. permission user_permission - permissions for the authenticated user of this workspace. permission globalread - whether this workspace is globally readable. lock_status lockstat - the status of the workspace lock. usermeta metadata - arbitrary user-supplied metadata about the workspace.) &rarr; tuple of size 9: parameter "id" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "owner" of original type "username" (Login name of a KBase user account.), parameter "moddate" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "max_objid" of Long, parameter "user_permission" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "globalread" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "lockstat" of original type "lock_status" (The lock status of a workspace. One of 'unlocked', 'locked', or 'published'.), parameter "metadata" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>> createWorkspace(CreateWorkspaceParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>>() {};
        List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("Workspace.create_workspace", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: alter_workspace_metadata</p>
     * <pre>
     * Change the metadata associated with a workspace.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.AlterWorkspaceMetadataParams AlterWorkspaceMetadataParams}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void alterWorkspaceMetadata(AlterWorkspaceMetadataParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.alter_workspace_metadata", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: clone_workspace</p>
     * <pre>
     * Clones a workspace.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.CloneWorkspaceParams CloneWorkspaceParams}
     * @return   parameter "info" of original type "workspace_info" (Information about a workspace. ws_id id - the numerical ID of the workspace. ws_name workspace - name of the workspace. username owner - name of the user who owns (e.g. created) this workspace. timestamp moddate - date when the workspace was last modified. int max_objid - the maximum object ID appearing in this workspace. Since cloning a workspace preserves object IDs, this number may be greater than the number of objects in a newly cloned workspace. permission user_permission - permissions for the authenticated user of this workspace. permission globalread - whether this workspace is globally readable. lock_status lockstat - the status of the workspace lock. usermeta metadata - arbitrary user-supplied metadata about the workspace.) &rarr; tuple of size 9: parameter "id" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "owner" of original type "username" (Login name of a KBase user account.), parameter "moddate" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "max_objid" of Long, parameter "user_permission" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "globalread" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "lockstat" of original type "lock_status" (The lock status of a workspace. One of 'unlocked', 'locked', or 'published'.), parameter "metadata" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>> cloneWorkspace(CloneWorkspaceParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>>() {};
        List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("Workspace.clone_workspace", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: lock_workspace</p>
     * <pre>
     * Lock a workspace, preventing further changes.
     *         WARNING: Locking a workspace is permanent. A workspace, once locked,
     *         cannot be unlocked.
     *         
     *         The only changes allowed for a locked workspace are changing user
     *         based permissions or making a private workspace globally readable,
     *         thus permanently publishing the workspace. A locked, globally readable
     *         workspace cannot be made private.
     * </pre>
     * @param   wsi   instance of type {@link us.kbase.workspace.WorkspaceIdentity WorkspaceIdentity}
     * @return   parameter "info" of original type "workspace_info" (Information about a workspace. ws_id id - the numerical ID of the workspace. ws_name workspace - name of the workspace. username owner - name of the user who owns (e.g. created) this workspace. timestamp moddate - date when the workspace was last modified. int max_objid - the maximum object ID appearing in this workspace. Since cloning a workspace preserves object IDs, this number may be greater than the number of objects in a newly cloned workspace. permission user_permission - permissions for the authenticated user of this workspace. permission globalread - whether this workspace is globally readable. lock_status lockstat - the status of the workspace lock. usermeta metadata - arbitrary user-supplied metadata about the workspace.) &rarr; tuple of size 9: parameter "id" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "owner" of original type "username" (Login name of a KBase user account.), parameter "moddate" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "max_objid" of Long, parameter "user_permission" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "globalread" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "lockstat" of original type "lock_status" (The lock status of a workspace. One of 'unlocked', 'locked', or 'published'.), parameter "metadata" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>> lockWorkspace(WorkspaceIdentity wsi, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(wsi);
        TypeReference<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>>() {};
        List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("Workspace.lock_workspace", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_workspacemeta</p>
     * <pre>
     * Retrieves the metadata associated with the specified workspace.
     * Provided for backwards compatibility. 
     * @deprecated Workspace.get_workspace_info
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.GetWorkspacemetaParams GetWorkspacemetaParams} (original type "get_workspacemeta_params")
     * @return   parameter "metadata" of original type "workspace_metadata" (Meta data associated with a workspace. Provided for backwards compatibility. To be replaced by workspace_info. ws_name id - name of the workspace username owner - name of the user who owns (who created) this workspace timestamp moddate - date when the workspace was last modified int objects - the approximate number of objects currently stored in the workspace. permission user_permission - permissions for the currently logged in user for the workspace permission global_permission - default permissions for the workspace for all KBase users ws_id num_id - numerical ID of the workspace @deprecated Workspace.workspace_info) &rarr; tuple of size 7: parameter "id" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "owner" of original type "username" (Login name of a KBase user account.), parameter "moddate" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "objects" of Long, parameter "user_permission" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "global_permission" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "num_id" of original type "ws_id" (The unique, permanent numerical ID of a workspace.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple7<String, String, String, Long, String, String, Long> getWorkspacemeta(GetWorkspacemetaParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple7<String, String, String, Long, String, String, Long>>> retType = new TypeReference<List<Tuple7<String, String, String, Long, String, String, Long>>>() {};
        List<Tuple7<String, String, String, Long, String, String, Long>> res = caller.jsonrpcCall("Workspace.get_workspacemeta", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_workspace_info</p>
     * <pre>
     * Get information associated with a workspace.
     * </pre>
     * @param   wsi   instance of type {@link us.kbase.workspace.WorkspaceIdentity WorkspaceIdentity}
     * @return   parameter "info" of original type "workspace_info" (Information about a workspace. ws_id id - the numerical ID of the workspace. ws_name workspace - name of the workspace. username owner - name of the user who owns (e.g. created) this workspace. timestamp moddate - date when the workspace was last modified. int max_objid - the maximum object ID appearing in this workspace. Since cloning a workspace preserves object IDs, this number may be greater than the number of objects in a newly cloned workspace. permission user_permission - permissions for the authenticated user of this workspace. permission globalread - whether this workspace is globally readable. lock_status lockstat - the status of the workspace lock. usermeta metadata - arbitrary user-supplied metadata about the workspace.) &rarr; tuple of size 9: parameter "id" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "owner" of original type "username" (Login name of a KBase user account.), parameter "moddate" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "max_objid" of Long, parameter "user_permission" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "globalread" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "lockstat" of original type "lock_status" (The lock status of a workspace. One of 'unlocked', 'locked', or 'published'.), parameter "metadata" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>> getWorkspaceInfo(WorkspaceIdentity wsi, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(wsi);
        TypeReference<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>>() {};
        List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("Workspace.get_workspace_info", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_workspace_description</p>
     * <pre>
     * Get a workspace's description.
     * </pre>
     * @param   wsi   instance of type {@link us.kbase.workspace.WorkspaceIdentity WorkspaceIdentity}
     * @return   parameter "description" of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String getWorkspaceDescription(WorkspaceIdentity wsi, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(wsi);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("Workspace.get_workspace_description", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: set_permissions</p>
     * <pre>
     * Set permissions for a workspace.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.SetPermissionsParams SetPermissionsParams}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void setPermissions(SetPermissionsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.set_permissions", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: set_global_permission</p>
     * <pre>
     * Set the global permission for a workspace.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.SetGlobalPermissionsParams SetGlobalPermissionsParams}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void setGlobalPermission(SetGlobalPermissionsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.set_global_permission", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: set_workspace_description</p>
     * <pre>
     * Set the description for a workspace.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.SetWorkspaceDescriptionParams SetWorkspaceDescriptionParams}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void setWorkspaceDescription(SetWorkspaceDescriptionParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.set_workspace_description", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: get_permissions_mass</p>
     * <pre>
     * Get permissions for multiple workspaces.
     * </pre>
     * @param   mass   instance of type {@link us.kbase.workspace.GetPermissionsMassParams GetPermissionsMassParams}
     * @return   parameter "perms" of type {@link us.kbase.workspace.WorkspacePermissions WorkspacePermissions}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public WorkspacePermissions getPermissionsMass(GetPermissionsMassParams mass, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(mass);
        TypeReference<List<WorkspacePermissions>> retType = new TypeReference<List<WorkspacePermissions>>() {};
        List<WorkspacePermissions> res = caller.jsonrpcCall("Workspace.get_permissions_mass", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_permissions</p>
     * <pre>
     * Get permissions for a workspace.
     * @deprecated get_permissions_mass
     * </pre>
     * @param   wsi   instance of type {@link us.kbase.workspace.WorkspaceIdentity WorkspaceIdentity}
     * @return   parameter "perms" of mapping from original type "username" (Login name of a KBase user account.) to original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getPermissions(WorkspaceIdentity wsi, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(wsi);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("Workspace.get_permissions", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: save_object</p>
     * <pre>
     * Saves the input object data and metadata into the selected workspace,
     *         returning the object_metadata of the saved object. Provided
     *         for backwards compatibility.
     *         
     * @deprecated Workspace.save_objects
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.SaveObjectParams SaveObjectParams} (original type "save_object_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. Provided for backwards compatibility. obj_name id - name of the object. type_string type - type of the object. timestamp moddate - date when the object was saved obj_ver instance - the version of the object string command - Deprecated. Always returns the empty string. username lastmodifier - name of the user who last saved the object, including copying the object username owner - Deprecated. Same as lastmodifier. ws_name workspace - name of the workspace in which the object is stored string ref - Deprecated. Always returns the empty string. string chsum - the md5 checksum of the object. usermeta metadata - arbitrary user-supplied metadata about the object. obj_id objid - the numerical id of the object. @deprecated object_info) &rarr; tuple of size 12: parameter "id" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "moddate" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of a KBase user account.), parameter "owner" of original type "username" (Login name of a KBase user account.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "ref" of String, parameter "chsum" of String, parameter "metadata" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String, parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long> saveObject(SaveObjectParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long>>> retType = new TypeReference<List<Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long>>>() {};
        List<Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long>> res = caller.jsonrpcCall("Workspace.save_object", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: save_objects</p>
     * <pre>
     * Save objects to the workspace. Saving over a deleted object undeletes
     * it.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.SaveObjectsParams SaveObjectsParams}
     * @return   parameter "info" of list of original type "object_info" (Information about an object, including user provided metadata. obj_id objid - the numerical id of the object. obj_name name - the name of the object. type_string type - the type of the object. timestamp save_date - the save date of the object. obj_ver ver - the version of the object. username saved_by - the user that saved or copied the object. ws_id wsid - the workspace containing the object. ws_name workspace - the workspace containing the object. string chsum - the md5 checksum of the object. int size - the size of the object in bytes. usermeta meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.), parameter "name" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "save_date" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "version" of Long, parameter "saved_by" of original type "username" (Login name of a KBase user account.), parameter "wsid" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "chsum" of String, parameter "size" of Long, parameter "meta" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> saveObjects(SaveObjectsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>> retType = new TypeReference<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>>() {};
        List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>> res = caller.jsonrpcCall("Workspace.save_objects", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_object</p>
     * <pre>
     * Retrieves the specified object from the specified workspace.
     * Both the object data and metadata are returned.
     * Provided for backwards compatibility.
     * @deprecated Workspace.get_objects
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.GetObjectParams GetObjectParams} (original type "get_object_params")
     * @return   parameter "output" of type {@link us.kbase.workspace.GetObjectOutput GetObjectOutput} (original type "get_object_output")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GetObjectOutput getObject(GetObjectParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GetObjectOutput>> retType = new TypeReference<List<GetObjectOutput>>() {};
        List<GetObjectOutput> res = caller.jsonrpcCall("Workspace.get_object", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_object_provenance</p>
     * <pre>
     * DEPRECATED
     * Get object provenance from the workspace.
     * @deprecated Workspace.get_objects2
     * </pre>
     * @param   objectIds   instance of list of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @return   parameter "data" of list of type {@link us.kbase.workspace.ObjectProvenanceInfo ObjectProvenanceInfo}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<ObjectProvenanceInfo> getObjectProvenance(List<ObjectIdentity> objectIds, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(objectIds);
        TypeReference<List<List<ObjectProvenanceInfo>>> retType = new TypeReference<List<List<ObjectProvenanceInfo>>>() {};
        List<List<ObjectProvenanceInfo>> res = caller.jsonrpcCall("Workspace.get_object_provenance", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_objects</p>
     * <pre>
     * DEPRECATED
     * Get objects from the workspace.
     * @deprecated Workspace.get_objects2
     * </pre>
     * @param   objectIds   instance of list of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @return   parameter "data" of list of type {@link us.kbase.workspace.ObjectData ObjectData}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<ObjectData> getObjects(List<ObjectIdentity> objectIds, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(objectIds);
        TypeReference<List<List<ObjectData>>> retType = new TypeReference<List<List<ObjectData>>>() {};
        List<List<ObjectData>> res = caller.jsonrpcCall("Workspace.get_objects", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_objects2</p>
     * <pre>
     * Get objects from the workspace.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.GetObjects2Params GetObjects2Params}
     * @return   parameter "results" of type {@link us.kbase.workspace.GetObjects2Results GetObjects2Results}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GetObjects2Results getObjects2(GetObjects2Params params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GetObjects2Results>> retType = new TypeReference<List<GetObjects2Results>>() {};
        List<GetObjects2Results> res = caller.jsonrpcCall("Workspace.get_objects2", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_object_subset</p>
     * <pre>
     * DEPRECATED
     * Get portions of objects from the workspace.
     * When selecting a subset of an array in an object, the returned
     * array is compressed to the size of the subset, but the ordering of
     * the array is maintained. For example, if the array stored at the
     * 'feature' key of a Genome object has 4000 entries, and the object paths
     * provided are:
     *         /feature/7
     *         /feature/3015
     *         /feature/700
     * The returned feature array will be of length three and the entries will
     * consist, in order, of the 7th, 700th, and 3015th entries of the
     * original array.
     * @deprecated Workspace.get_objects2
     * </pre>
     * @param   subObjectIds   instance of list of type {@link us.kbase.workspace.SubObjectIdentity SubObjectIdentity}
     * @return   parameter "data" of list of type {@link us.kbase.workspace.ObjectData ObjectData}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<ObjectData> getObjectSubset(List<SubObjectIdentity> subObjectIds, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(subObjectIds);
        TypeReference<List<List<ObjectData>>> retType = new TypeReference<List<List<ObjectData>>>() {};
        List<List<ObjectData>> res = caller.jsonrpcCall("Workspace.get_object_subset", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_object_history</p>
     * <pre>
     * Get an object's history. The version argument of the ObjectIdentity is
     * ignored.
     * </pre>
     * @param   object   instance of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @return   parameter "history" of list of original type "object_info" (Information about an object, including user provided metadata. obj_id objid - the numerical id of the object. obj_name name - the name of the object. type_string type - the type of the object. timestamp save_date - the save date of the object. obj_ver ver - the version of the object. username saved_by - the user that saved or copied the object. ws_id wsid - the workspace containing the object. ws_name workspace - the workspace containing the object. string chsum - the md5 checksum of the object. int size - the size of the object in bytes. usermeta meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.), parameter "name" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "save_date" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "version" of Long, parameter "saved_by" of original type "username" (Login name of a KBase user account.), parameter "wsid" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "chsum" of String, parameter "size" of Long, parameter "meta" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> getObjectHistory(ObjectIdentity object, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(object);
        TypeReference<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>> retType = new TypeReference<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>>() {};
        List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>> res = caller.jsonrpcCall("Workspace.get_object_history", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: list_referencing_objects</p>
     * <pre>
     * List objects that reference one or more specified objects. References
     * in the deleted state are not returned.
     * </pre>
     * @param   objectIds   instance of list of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @return   parameter "referrers" of list of list of original type "object_info" (Information about an object, including user provided metadata. obj_id objid - the numerical id of the object. obj_name name - the name of the object. type_string type - the type of the object. timestamp save_date - the save date of the object. obj_ver ver - the version of the object. username saved_by - the user that saved or copied the object. ws_id wsid - the workspace containing the object. ws_name workspace - the workspace containing the object. string chsum - the md5 checksum of the object. int size - the size of the object in bytes. usermeta meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.), parameter "name" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "save_date" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "version" of Long, parameter "saved_by" of original type "username" (Login name of a KBase user account.), parameter "wsid" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "chsum" of String, parameter "size" of Long, parameter "meta" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>> listReferencingObjects(List<ObjectIdentity> objectIds, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(objectIds);
        TypeReference<List<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>>> retType = new TypeReference<List<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>>>() {};
        List<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>> res = caller.jsonrpcCall("Workspace.list_referencing_objects", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: list_referencing_object_counts</p>
     * <pre>
     * DEPRECATED
     * List the number of times objects have been referenced.
     * This count includes both provenance and object-to-object references
     * and, unlike list_referencing_objects, includes objects that are
     * inaccessible to the user.
     * @deprecated
     * </pre>
     * @param   objectIds   instance of list of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @return   parameter "counts" of list of Long
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Long> listReferencingObjectCounts(List<ObjectIdentity> objectIds, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(objectIds);
        TypeReference<List<List<Long>>> retType = new TypeReference<List<List<Long>>>() {};
        List<List<Long>> res = caller.jsonrpcCall("Workspace.list_referencing_object_counts", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_referenced_objects</p>
     * <pre>
     * DEPRECATED
     *         Get objects by references from other objects.
     *         NOTE: In the vast majority of cases, this method is not necessary and
     *         get_objects should be used instead. 
     *         
     *         get_referenced_objects guarantees that a user that has access to an
     *         object can always see a) objects that are referenced inside the object
     *         and b) objects that are referenced in the object's provenance. This
     *         ensures that the user has visibility into the entire provenance of the
     *         object and the object's object dependencies (e.g. references).
     *         
     *         The user must have at least read access to the first object in each
     *         reference chain, but need not have access to any further objects in
     *         the chain, and those objects may be deleted.
     *         
     *         @deprecated Workspace.get_objects2
     * </pre>
     * @param   refChains   instance of list of original type "ref_chain" (A chain of objects with references to one another. An object reference chain consists of a list of objects where the nth object possesses a reference, either in the object itself or in the object provenance, to the n+1th object.) &rarr; list of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @return   parameter "data" of list of type {@link us.kbase.workspace.ObjectData ObjectData}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<ObjectData> getReferencedObjects(List<List<ObjectIdentity>> refChains, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(refChains);
        TypeReference<List<List<ObjectData>>> retType = new TypeReference<List<List<ObjectData>>>() {};
        List<List<ObjectData>> res = caller.jsonrpcCall("Workspace.get_referenced_objects", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: list_workspaces</p>
     * <pre>
     * Lists the metadata of all workspaces a user has access to. Provided for
     * backwards compatibility - to be replaced by the functionality of
     * list_workspace_info
     * @deprecated Workspace.list_workspace_info
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.ListWorkspacesParams ListWorkspacesParams} (original type "list_workspaces_params")
     * @return   parameter "workspaces" of list of original type "workspace_metadata" (Meta data associated with a workspace. Provided for backwards compatibility. To be replaced by workspace_info. ws_name id - name of the workspace username owner - name of the user who owns (who created) this workspace timestamp moddate - date when the workspace was last modified int objects - the approximate number of objects currently stored in the workspace. permission user_permission - permissions for the currently logged in user for the workspace permission global_permission - default permissions for the workspace for all KBase users ws_id num_id - numerical ID of the workspace @deprecated Workspace.workspace_info) &rarr; tuple of size 7: parameter "id" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "owner" of original type "username" (Login name of a KBase user account.), parameter "moddate" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "objects" of Long, parameter "user_permission" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "global_permission" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "num_id" of original type "ws_id" (The unique, permanent numerical ID of a workspace.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple7<String, String, String, Long, String, String, Long>> listWorkspaces(ListWorkspacesParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<Tuple7<String, String, String, Long, String, String, Long>>>> retType = new TypeReference<List<List<Tuple7<String, String, String, Long, String, String, Long>>>>() {};
        List<List<Tuple7<String, String, String, Long, String, String, Long>>> res = caller.jsonrpcCall("Workspace.list_workspaces", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: list_workspace_info</p>
     * <pre>
     * List workspaces viewable by the user.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.ListWorkspaceInfoParams ListWorkspaceInfoParams}
     * @return   parameter "wsinfo" of list of original type "workspace_info" (Information about a workspace. ws_id id - the numerical ID of the workspace. ws_name workspace - name of the workspace. username owner - name of the user who owns (e.g. created) this workspace. timestamp moddate - date when the workspace was last modified. int max_objid - the maximum object ID appearing in this workspace. Since cloning a workspace preserves object IDs, this number may be greater than the number of objects in a newly cloned workspace. permission user_permission - permissions for the authenticated user of this workspace. permission globalread - whether this workspace is globally readable. lock_status lockstat - the status of the workspace lock. usermeta metadata - arbitrary user-supplied metadata about the workspace.) &rarr; tuple of size 9: parameter "id" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "owner" of original type "username" (Login name of a KBase user account.), parameter "moddate" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "max_objid" of Long, parameter "user_permission" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "globalread" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "lockstat" of original type "lock_status" (The lock status of a workspace. One of 'unlocked', 'locked', or 'published'.), parameter "metadata" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>> listWorkspaceInfo(ListWorkspaceInfoParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>>> retType = new TypeReference<List<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>>>() {};
        List<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>> res = caller.jsonrpcCall("Workspace.list_workspace_info", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: list_workspace_objects</p>
     * <pre>
     * Lists the metadata of all objects in the specified workspace with the
     * specified type (or with any type). Provided for backwards compatibility.
     * @deprecated Workspace.list_objects
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.ListWorkspaceObjectsParams ListWorkspaceObjectsParams} (original type "list_workspace_objects_params")
     * @return   parameter "objects" of list of original type "object_metadata" (Meta data associated with an object stored in a workspace. Provided for backwards compatibility. obj_name id - name of the object. type_string type - type of the object. timestamp moddate - date when the object was saved obj_ver instance - the version of the object string command - Deprecated. Always returns the empty string. username lastmodifier - name of the user who last saved the object, including copying the object username owner - Deprecated. Same as lastmodifier. ws_name workspace - name of the workspace in which the object is stored string ref - Deprecated. Always returns the empty string. string chsum - the md5 checksum of the object. usermeta metadata - arbitrary user-supplied metadata about the object. obj_id objid - the numerical id of the object. @deprecated object_info) &rarr; tuple of size 12: parameter "id" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "moddate" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of a KBase user account.), parameter "owner" of original type "username" (Login name of a KBase user account.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "ref" of String, parameter "chsum" of String, parameter "metadata" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String, parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long>> listWorkspaceObjects(ListWorkspaceObjectsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long>>>> retType = new TypeReference<List<List<Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long>>>>() {};
        List<List<Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long>>> res = caller.jsonrpcCall("Workspace.list_workspace_objects", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: list_objects</p>
     * <pre>
     * List objects in one or more workspaces.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.ListObjectsParams ListObjectsParams}
     * @return   parameter "objinfo" of list of original type "object_info" (Information about an object, including user provided metadata. obj_id objid - the numerical id of the object. obj_name name - the name of the object. type_string type - the type of the object. timestamp save_date - the save date of the object. obj_ver ver - the version of the object. username saved_by - the user that saved or copied the object. ws_id wsid - the workspace containing the object. ws_name workspace - the workspace containing the object. string chsum - the md5 checksum of the object. int size - the size of the object in bytes. usermeta meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.), parameter "name" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "save_date" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "version" of Long, parameter "saved_by" of original type "username" (Login name of a KBase user account.), parameter "wsid" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "chsum" of String, parameter "size" of Long, parameter "meta" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> listObjects(ListObjectsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>> retType = new TypeReference<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>>() {};
        List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>> res = caller.jsonrpcCall("Workspace.list_objects", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_objectmeta</p>
     * <pre>
     * Retrieves the metadata for a specified object from the specified
     * workspace. Provides access to metadata for all versions of the object
     * via the instance parameter. Provided for backwards compatibility.
     * @deprecated Workspace.get_object_info3
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.GetObjectmetaParams GetObjectmetaParams} (original type "get_objectmeta_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. Provided for backwards compatibility. obj_name id - name of the object. type_string type - type of the object. timestamp moddate - date when the object was saved obj_ver instance - the version of the object string command - Deprecated. Always returns the empty string. username lastmodifier - name of the user who last saved the object, including copying the object username owner - Deprecated. Same as lastmodifier. ws_name workspace - name of the workspace in which the object is stored string ref - Deprecated. Always returns the empty string. string chsum - the md5 checksum of the object. usermeta metadata - arbitrary user-supplied metadata about the object. obj_id objid - the numerical id of the object. @deprecated object_info) &rarr; tuple of size 12: parameter "id" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "moddate" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of a KBase user account.), parameter "owner" of original type "username" (Login name of a KBase user account.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "ref" of String, parameter "chsum" of String, parameter "metadata" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String, parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long> getObjectmeta(GetObjectmetaParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long>>> retType = new TypeReference<List<Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long>>>() {};
        List<Tuple12<String, String, String, Long, String, String, String, String, String, String, Map<String,String>, Long>> res = caller.jsonrpcCall("Workspace.get_objectmeta", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_object_info</p>
     * <pre>
     * Get information about objects from the workspace.
     * Set includeMetadata true to include the user specified metadata.
     * Otherwise the metadata in the object_info will be null.
     * This method will be replaced by the behavior of get_object_info_new
     * in the future.
     * @deprecated Workspace.get_object_info3
     * </pre>
     * @param   objectIds   instance of list of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @param   includeMetadata   instance of original type "boolean" (A boolean. 0 = false, other = true.)
     * @return   parameter "info" of list of original type "object_info" (Information about an object, including user provided metadata. obj_id objid - the numerical id of the object. obj_name name - the name of the object. type_string type - the type of the object. timestamp save_date - the save date of the object. obj_ver ver - the version of the object. username saved_by - the user that saved or copied the object. ws_id wsid - the workspace containing the object. ws_name workspace - the workspace containing the object. string chsum - the md5 checksum of the object. int size - the size of the object in bytes. usermeta meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.), parameter "name" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "save_date" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "version" of Long, parameter "saved_by" of original type "username" (Login name of a KBase user account.), parameter "wsid" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "chsum" of String, parameter "size" of Long, parameter "meta" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> getObjectInfo(List<ObjectIdentity> objectIds, Long includeMetadata, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(objectIds);
        args.add(includeMetadata);
        TypeReference<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>> retType = new TypeReference<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>>() {};
        List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>> res = caller.jsonrpcCall("Workspace.get_object_info", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_object_info_new</p>
     * <pre>
     * Get information about objects from the workspace.
     * @deprecated Workspace.get_object_info3
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.GetObjectInfoNewParams GetObjectInfoNewParams}
     * @return   parameter "info" of list of original type "object_info" (Information about an object, including user provided metadata. obj_id objid - the numerical id of the object. obj_name name - the name of the object. type_string type - the type of the object. timestamp save_date - the save date of the object. obj_ver ver - the version of the object. username saved_by - the user that saved or copied the object. ws_id wsid - the workspace containing the object. ws_name workspace - the workspace containing the object. string chsum - the md5 checksum of the object. int size - the size of the object in bytes. usermeta meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.), parameter "name" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "save_date" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "version" of Long, parameter "saved_by" of original type "username" (Login name of a KBase user account.), parameter "wsid" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "chsum" of String, parameter "size" of Long, parameter "meta" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> getObjectInfoNew(GetObjectInfoNewParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>> retType = new TypeReference<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>>() {};
        List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>> res = caller.jsonrpcCall("Workspace.get_object_info_new", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_object_info3</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.GetObjectInfo3Params GetObjectInfo3Params}
     * @return   parameter "results" of type {@link us.kbase.workspace.GetObjectInfo3Results GetObjectInfo3Results}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GetObjectInfo3Results getObjectInfo3(GetObjectInfo3Params params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GetObjectInfo3Results>> retType = new TypeReference<List<GetObjectInfo3Results>>() {};
        List<GetObjectInfo3Results> res = caller.jsonrpcCall("Workspace.get_object_info3", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: rename_workspace</p>
     * <pre>
     * Rename a workspace.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.RenameWorkspaceParams RenameWorkspaceParams}
     * @return   parameter "renamed" of original type "workspace_info" (Information about a workspace. ws_id id - the numerical ID of the workspace. ws_name workspace - name of the workspace. username owner - name of the user who owns (e.g. created) this workspace. timestamp moddate - date when the workspace was last modified. int max_objid - the maximum object ID appearing in this workspace. Since cloning a workspace preserves object IDs, this number may be greater than the number of objects in a newly cloned workspace. permission user_permission - permissions for the authenticated user of this workspace. permission globalread - whether this workspace is globally readable. lock_status lockstat - the status of the workspace lock. usermeta metadata - arbitrary user-supplied metadata about the workspace.) &rarr; tuple of size 9: parameter "id" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "owner" of original type "username" (Login name of a KBase user account.), parameter "moddate" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "max_objid" of Long, parameter "user_permission" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "globalread" of original type "permission" (Represents the permissions a user or users have to a workspace: 'a' - administrator. All operations allowed. 'w' - read/write. 'r' - read. 'n' - no permissions.), parameter "lockstat" of original type "lock_status" (The lock status of a workspace. One of 'unlocked', 'locked', or 'published'.), parameter "metadata" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>> renameWorkspace(RenameWorkspaceParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>>>() {};
        List<Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("Workspace.rename_workspace", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: rename_object</p>
     * <pre>
     * Rename an object. User meta data is always returned as null.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.RenameObjectParams RenameObjectParams}
     * @return   parameter "renamed" of original type "object_info" (Information about an object, including user provided metadata. obj_id objid - the numerical id of the object. obj_name name - the name of the object. type_string type - the type of the object. timestamp save_date - the save date of the object. obj_ver ver - the version of the object. username saved_by - the user that saved or copied the object. ws_id wsid - the workspace containing the object. ws_name workspace - the workspace containing the object. string chsum - the md5 checksum of the object. int size - the size of the object in bytes. usermeta meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.), parameter "name" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "save_date" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "version" of Long, parameter "saved_by" of original type "username" (Login name of a KBase user account.), parameter "wsid" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "chsum" of String, parameter "size" of Long, parameter "meta" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>> renameObject(RenameObjectParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>> retType = new TypeReference<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>() {};
        List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> res = caller.jsonrpcCall("Workspace.rename_object", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: copy_object</p>
     * <pre>
     * Copy an object. Returns the object_info for the newest version.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.CopyObjectParams CopyObjectParams}
     * @return   parameter "copied" of original type "object_info" (Information about an object, including user provided metadata. obj_id objid - the numerical id of the object. obj_name name - the name of the object. type_string type - the type of the object. timestamp save_date - the save date of the object. obj_ver ver - the version of the object. username saved_by - the user that saved or copied the object. ws_id wsid - the workspace containing the object. ws_name workspace - the workspace containing the object. string chsum - the md5 checksum of the object. int size - the size of the object in bytes. usermeta meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.), parameter "name" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "save_date" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "version" of Long, parameter "saved_by" of original type "username" (Login name of a KBase user account.), parameter "wsid" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "chsum" of String, parameter "size" of Long, parameter "meta" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>> copyObject(CopyObjectParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>> retType = new TypeReference<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>() {};
        List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> res = caller.jsonrpcCall("Workspace.copy_object", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: revert_object</p>
     * <pre>
     * Revert an object.
     *         The object specified in the ObjectIdentity is reverted to the version
     *         specified in the ObjectIdentity.
     * </pre>
     * @param   object   instance of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @return   parameter "reverted" of original type "object_info" (Information about an object, including user provided metadata. obj_id objid - the numerical id of the object. obj_name name - the name of the object. type_string type - the type of the object. timestamp save_date - the save date of the object. obj_ver ver - the version of the object. username saved_by - the user that saved or copied the object. ws_id wsid - the workspace containing the object. ws_name workspace - the workspace containing the object. string chsum - the md5 checksum of the object. int size - the size of the object in bytes. usermeta meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of original type "obj_id" (The unique, permanent numerical ID of an object.), parameter "name" of original type "obj_name" (A string used as a name for an object. Any string consisting of alphanumeric characters and the characters |._- that is not an integer is acceptable.), parameter "type" of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1), parameter "save_date" of original type "timestamp" (A time in the format YYYY-MM-DDThh:mm:ssZ, where Z is either the character Z (representing the UTC timezone) or the difference in time to UTC in the format +/-HHMM, eg: 2012-12-17T23:24:06-0500 (EST time) 2013-04-03T08:56:32+0000 (UTC time) 2013-04-03T08:56:32Z (UTC time)), parameter "version" of Long, parameter "saved_by" of original type "username" (Login name of a KBase user account.), parameter "wsid" of original type "ws_id" (The unique, permanent numerical ID of a workspace.), parameter "workspace" of original type "ws_name" (A string used as a name for a workspace. Any string consisting of alphanumeric characters and "_", ".", or "-" that is not an integer is acceptable. The name may optionally be prefixed with the workspace owner's user name and a colon, e.g. kbasetest:my_workspace.), parameter "chsum" of String, parameter "size" of Long, parameter "meta" of original type "usermeta" (User provided metadata about an object. Arbitrary key-value pairs provided by the user.) &rarr; mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>> revertObject(ObjectIdentity object, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(object);
        TypeReference<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>> retType = new TypeReference<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>() {};
        List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> res = caller.jsonrpcCall("Workspace.revert_object", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_names_by_prefix</p>
     * <pre>
     * Get object names matching a prefix. At most 1000 names are returned.
     * No particular ordering is guaranteed, nor is which names will be
     * returned if more than 1000 are found.
     * This function is intended for use as an autocomplete helper function.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.GetNamesByPrefixParams GetNamesByPrefixParams}
     * @return   parameter "res" of type {@link us.kbase.workspace.GetNamesByPrefixResults GetNamesByPrefixResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GetNamesByPrefixResults getNamesByPrefix(GetNamesByPrefixParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GetNamesByPrefixResults>> retType = new TypeReference<List<GetNamesByPrefixResults>>() {};
        List<GetNamesByPrefixResults> res = caller.jsonrpcCall("Workspace.get_names_by_prefix", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: hide_objects</p>
     * <pre>
     * Hide objects. All versions of an object are hidden, regardless of
     * the version specified in the ObjectIdentity. Hidden objects do not
     * appear in the list_objects method.
     * </pre>
     * @param   objectIds   instance of list of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void hideObjects(List<ObjectIdentity> objectIds, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(objectIds);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.hide_objects", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: unhide_objects</p>
     * <pre>
     * Unhide objects. All versions of an object are unhidden, regardless
     * of the version specified in the ObjectIdentity.
     * </pre>
     * @param   objectIds   instance of list of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void unhideObjects(List<ObjectIdentity> objectIds, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(objectIds);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.unhide_objects", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: delete_objects</p>
     * <pre>
     * Delete objects. All versions of an object are deleted, regardless of
     * the version specified in the ObjectIdentity.
     * </pre>
     * @param   objectIds   instance of list of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void deleteObjects(List<ObjectIdentity> objectIds, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(objectIds);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.delete_objects", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: undelete_objects</p>
     * <pre>
     * Undelete objects. All versions of an object are undeleted, regardless
     * of the version specified in the ObjectIdentity. If an object is not
     * deleted, no error is thrown.
     * </pre>
     * @param   objectIds   instance of list of type {@link us.kbase.workspace.ObjectIdentity ObjectIdentity}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void undeleteObjects(List<ObjectIdentity> objectIds, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(objectIds);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.undelete_objects", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: delete_workspace</p>
     * <pre>
     * Delete a workspace. All objects contained in the workspace are deleted.
     * </pre>
     * @param   wsi   instance of type {@link us.kbase.workspace.WorkspaceIdentity WorkspaceIdentity}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void deleteWorkspace(WorkspaceIdentity wsi, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(wsi);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.delete_workspace", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: undelete_workspace</p>
     * <pre>
     * Undelete a workspace. All objects contained in the workspace are
     * undeleted, regardless of their state at the time the workspace was
     * deleted.
     * </pre>
     * @param   wsi   instance of type {@link us.kbase.workspace.WorkspaceIdentity WorkspaceIdentity}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void undeleteWorkspace(WorkspaceIdentity wsi, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(wsi);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.undelete_workspace", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: request_module_ownership</p>
     * <pre>
     * Request ownership of a module name. A Workspace administrator
     * must approve the request.
     * </pre>
     * @param   mod   instance of original type "modulename" (A module name defined in a KIDL typespec.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void requestModuleOwnership(String mod, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(mod);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.request_module_ownership", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: register_typespec</p>
     * <pre>
     * Register a new typespec or recompile a previously registered typespec
     * with new options.
     * See the documentation of RegisterTypespecParams for more details.
     * Also see the release_types function.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.RegisterTypespecParams RegisterTypespecParams}
     * @return   instance of mapping from original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1) to original type "jsonschema" (The JSON Schema (v4) representation of a type definition.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> registerTypespec(RegisterTypespecParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("Workspace.register_typespec", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: register_typespec_copy</p>
     * <pre>
     * Register a copy of new typespec or refresh an existing typespec which is
     * loaded from another workspace for synchronization. Method returns new
     * version of module in current workspace.
     * Also see the release_types function.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.RegisterTypespecCopyParams RegisterTypespecCopyParams}
     * @return   parameter "new_local_version" of original type "spec_version" (The version of a typespec file.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long registerTypespecCopy(RegisterTypespecCopyParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Long>> retType = new TypeReference<List<Long>>() {};
        List<Long> res = caller.jsonrpcCall("Workspace.register_typespec_copy", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: release_module</p>
     * <pre>
     * Release a module for general use of its types.
     * Releases the most recent version of a module. Releasing a module does
     * two things to the module's types:
     * 1) If a type's major version is 0, it is changed to 1. A major
     *         version of 0 implies that the type is in development and may have
     *         backwards incompatible changes from minor version to minor version.
     *         Once a type is released, backwards incompatible changes always
     *         cause a major version increment.
     * 2) This version of the type becomes the default version, and if a 
     *         specific version is not supplied in a function call, this version
     *         will be used. This means that newer, unreleased versions of the
     *         type may be skipped.
     * </pre>
     * @param   mod   instance of original type "modulename" (A module name defined in a KIDL typespec.)
     * @return   parameter "types" of list of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> releaseModule(String mod, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(mod);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("Workspace.release_module", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: list_modules</p>
     * <pre>
     * List typespec modules.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.ListModulesParams ListModulesParams}
     * @return   parameter "modules" of list of original type "modulename" (A module name defined in a KIDL typespec.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> listModules(ListModulesParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("Workspace.list_modules", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: list_module_versions</p>
     * <pre>
     * List typespec module versions.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.ListModuleVersionsParams ListModuleVersionsParams}
     * @return   parameter "vers" of type {@link us.kbase.workspace.ModuleVersions ModuleVersions}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ModuleVersions listModuleVersions(ListModuleVersionsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<ModuleVersions>> retType = new TypeReference<List<ModuleVersions>>() {};
        List<ModuleVersions> res = caller.jsonrpcCall("Workspace.list_module_versions", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_module_info</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.GetModuleInfoParams GetModuleInfoParams}
     * @return   parameter "info" of type {@link us.kbase.workspace.ModuleInfo ModuleInfo}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ModuleInfo getModuleInfo(GetModuleInfoParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<ModuleInfo>> retType = new TypeReference<List<ModuleInfo>>() {};
        List<ModuleInfo> res = caller.jsonrpcCall("Workspace.get_module_info", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_jsonschema</p>
     * <pre>
     * Get JSON schema for a type.
     * </pre>
     * @param   type   instance of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1)
     * @return   parameter "schema" of original type "jsonschema" (The JSON Schema (v4) representation of a type definition.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String getJsonschema(String type, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(type);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("Workspace.get_jsonschema", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: translate_from_MD5_types</p>
     * <pre>
     * Translation from types qualified with MD5 to their semantic versions
     * </pre>
     * @param   md5Types   instance of list of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1)
     * @return   parameter "sem_types" of mapping from original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1) to list of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,List<String>> translateFromMD5Types(List<String> md5Types, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(md5Types);
        TypeReference<List<Map<String,List<String>>>> retType = new TypeReference<List<Map<String,List<String>>>>() {};
        List<Map<String,List<String>>> res = caller.jsonrpcCall("Workspace.translate_from_MD5_types", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: translate_to_MD5_types</p>
     * <pre>
     * Translation from types qualified with semantic versions to their MD5'ed versions
     * </pre>
     * @param   semTypes   instance of list of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1)
     * @return   parameter "md5_types" of mapping from original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1) to original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> translateToMD5Types(List<String> semTypes, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(semTypes);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("Workspace.translate_to_MD5_types", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_type_info</p>
     * <pre>
     * </pre>
     * @param   type   instance of original type "type_string" (A type string. Specifies the type and its version in a single string in the format [module].[typename]-[major].[minor]: module - a string. The module name of the typespec containing the type. typename - a string. The name of the type as assigned by the typedef statement. major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyType-3.1)
     * @return   parameter "info" of type {@link us.kbase.workspace.TypeInfo TypeInfo}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public TypeInfo getTypeInfo(String type, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(type);
        TypeReference<List<TypeInfo>> retType = new TypeReference<List<TypeInfo>>() {};
        List<TypeInfo> res = caller.jsonrpcCall("Workspace.get_type_info", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_all_type_info</p>
     * <pre>
     * </pre>
     * @param   mod   instance of original type "modulename" (A module name defined in a KIDL typespec.)
     * @return   instance of list of type {@link us.kbase.workspace.TypeInfo TypeInfo}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<TypeInfo> getAllTypeInfo(String mod, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(mod);
        TypeReference<List<List<TypeInfo>>> retType = new TypeReference<List<List<TypeInfo>>>() {};
        List<List<TypeInfo>> res = caller.jsonrpcCall("Workspace.get_all_type_info", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_func_info</p>
     * <pre>
     * </pre>
     * @param   func   instance of original type "func_string" (A function string for referencing a funcdef. Specifies the function and its version in a single string in the format [modulename].[funcname]-[major].[minor]: modulename - a string. The name of the module containing the function. funcname - a string. The name of the function as assigned by the funcdef statement. major - an integer. The major version of the function. A change in the major version implies the function has changed in a non-backwards compatible way. minor - an integer. The minor version of the function. A change in the minor version implies that the function has changed in a way that is backwards compatible with previous function definitions. In many cases, the major and minor versions are optional, and if not provided the most recent version will be used. Example: MyModule.MyFunc-3.1)
     * @return   parameter "info" of type {@link us.kbase.workspace.FuncInfo FuncInfo}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public FuncInfo getFuncInfo(String func, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(func);
        TypeReference<List<FuncInfo>> retType = new TypeReference<List<FuncInfo>>() {};
        List<FuncInfo> res = caller.jsonrpcCall("Workspace.get_func_info", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_all_func_info</p>
     * <pre>
     * </pre>
     * @param   mod   instance of original type "modulename" (A module name defined in a KIDL typespec.)
     * @return   parameter "info" of list of type {@link us.kbase.workspace.FuncInfo FuncInfo}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<FuncInfo> getAllFuncInfo(String mod, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(mod);
        TypeReference<List<List<FuncInfo>>> retType = new TypeReference<List<List<FuncInfo>>>() {};
        List<List<FuncInfo>> res = caller.jsonrpcCall("Workspace.get_all_func_info", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: grant_module_ownership</p>
     * <pre>
     * Grant ownership of a module. You must have grant ability on the
     * module.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.GrantModuleOwnershipParams GrantModuleOwnershipParams}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void grantModuleOwnership(GrantModuleOwnershipParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.grant_module_ownership", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: remove_module_ownership</p>
     * <pre>
     * Remove ownership from a current owner. You must have the grant ability
     * on the module.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.RemoveModuleOwnershipParams RemoveModuleOwnershipParams}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void removeModuleOwnership(RemoveModuleOwnershipParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("Workspace.remove_module_ownership", args, retType, false, true, jsonRpcContext, this.serviceVersion);
    }

    /**
     * <p>Original spec-file function name: list_all_types</p>
     * <pre>
     * List all released types with released version from all modules. Return
     * mapping from module name to mapping from type name to released type
     * version.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspace.ListAllTypesParams ListAllTypesParams}
     * @return   instance of mapping from original type "modulename" (A module name defined in a KIDL typespec.) to mapping from original type "typename" (A type definition name in a KIDL typespec.) to original type "typever" (A version of a type. Specifies the version of the type  in a single string in the format [major].[minor]: major - an integer. The major version of the type. A change in the major version implies the type has changed in a non-backwards compatible way. minor - an integer. The minor version of the type. A change in the minor version implies that the type has changed in a way that is backwards compatible with previous type definitions.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,String>> listAllTypes(ListAllTypesParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Map<String,Map<String,String>>>> retType = new TypeReference<List<Map<String,Map<String,String>>>>() {};
        List<Map<String,Map<String,String>>> res = caller.jsonrpcCall("Workspace.list_all_types", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: administer</p>
     * <pre>
     * The administration interface.
     * </pre>
     * @param   command   instance of unspecified object
     * @return   parameter "response" of unspecified object
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public UObject administer(UObject command, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(command);
        TypeReference<List<UObject>> retType = new TypeReference<List<UObject>>() {};
        List<UObject> res = caller.jsonrpcCall("Workspace.administer", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    public Map<String, Object> status(RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<Map<String, Object>>> retType = new TypeReference<List<Map<String, Object>>>() {};
        List<Map<String, Object>> res = caller.jsonrpcCall("Workspace.status", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }
}