package rastsdk;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JobState;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.UnauthorizedException;

/**
 * <p>Original spec-file module name: RAST_SDK</p>
 * <pre>
 * The SDK version of the KBaase Genome Annotation Service.
 * This wraps genome_annotation which is based off of the SEED annotations.
 * </pre>
 */
public class RASTSDKClient {
    private JsonClientCaller caller;
    private long asyncJobCheckTimeMs = 100;
    private int asyncJobCheckTimeScalePercent = 150;
    private long asyncJobCheckMaxTimeMs = 300000;  // 5 minutes
    private String serviceVersion = "release";


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public RASTSDKClient(URL url) {
        caller = new JsonClientCaller(url);
    }
    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public RASTSDKClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
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
    public RASTSDKClient(URL url, String user, String password) throws UnauthorizedException, IOException {
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
    public RASTSDKClient(URL url, String user, String password, URL auth) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password, auth);
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

    public long getAsyncJobCheckTimeMs() {
        return this.asyncJobCheckTimeMs;
    }

    public void setAsyncJobCheckTimeMs(long newValue) {
        this.asyncJobCheckTimeMs = newValue;
    }

    public int getAsyncJobCheckTimeScalePercent() {
        return this.asyncJobCheckTimeScalePercent;
    }

    public void setAsyncJobCheckTimeScalePercent(int newValue) {
        this.asyncJobCheckTimeScalePercent = newValue;
    }

    public long getAsyncJobCheckMaxTimeMs() {
        return this.asyncJobCheckMaxTimeMs;
    }

    public void setAsyncJobCheckMaxTimeMs(long newValue) {
        this.asyncJobCheckMaxTimeMs = newValue;
    }

    public String getServiceVersion() {
        return this.serviceVersion;
    }

    public void setServiceVersion(String newValue) {
        this.serviceVersion = newValue;
    }

    protected <T> JobState<T> _checkJob(String jobId, TypeReference<List<JobState<T>>> retType) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(jobId);
        List<JobState<T>> res = caller.jsonrpcCall("RAST_SDK._check_job", args, retType, true, true);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: annotate_genome</p>
     * <pre>
     * annotate genome
     * params - a param hash that includes the workspace id and options
     * </pre>
     * @param   params   instance of type {@link rastsdk.AnnotateGenomeParams AnnotateGenomeParams}
     * @return   instance of type {@link rastsdk.AnnotateGenomeResults AnnotateGenomeResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _annotateGenomeSubmit(AnnotateGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("RAST_SDK._annotate_genome_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: annotate_genome</p>
     * <pre>
     * annotate genome
     * params - a param hash that includes the workspace id and options
     * </pre>
     * @param   params   instance of type {@link rastsdk.AnnotateGenomeParams AnnotateGenomeParams}
     * @return   instance of type {@link rastsdk.AnnotateGenomeResults AnnotateGenomeResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public AnnotateGenomeResults annotateGenome(AnnotateGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _annotateGenomeSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<AnnotateGenomeResults>>>> retType = new TypeReference<List<JobState<List<AnnotateGenomeResults>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<AnnotateGenomeResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: annotate_genomes</p>
     * <pre>
     * annotate genomes
     * params - a param hash that includes the workspace id and options
     * </pre>
     * @param   params   instance of type {@link rastsdk.AnnotateGenomesParams AnnotateGenomesParams}
     * @return   instance of type {@link rastsdk.AnnotateGenomesResults AnnotateGenomesResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _annotateGenomesSubmit(AnnotateGenomesParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("RAST_SDK._annotate_genomes_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: annotate_genomes</p>
     * <pre>
     * annotate genomes
     * params - a param hash that includes the workspace id and options
     * </pre>
     * @param   params   instance of type {@link rastsdk.AnnotateGenomesParams AnnotateGenomesParams}
     * @return   instance of type {@link rastsdk.AnnotateGenomesResults AnnotateGenomesResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public AnnotateGenomesResults annotateGenomes(AnnotateGenomesParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _annotateGenomesSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<AnnotateGenomesResults>>>> retType = new TypeReference<List<JobState<List<AnnotateGenomesResults>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<AnnotateGenomesResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    public Map<String, Object> status(RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<String>> retType1 = new TypeReference<List<String>>() {};
        List<String> res1 = caller.jsonrpcCall("RAST_SDK._status_submit", args, retType1, true, true, jsonRpcContext);
        String jobId = res1.get(0);
        TypeReference<List<JobState<List<Map<String, Object>>>>> retType2 = new TypeReference<List<JobState<List<Map<String, Object>>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<Map<String, Object>>> res2 = _checkJob(jobId, retType2);
            if (res2.getFinished() != 0L)
                return res2.getResult().get(0);
        }
    }
}
