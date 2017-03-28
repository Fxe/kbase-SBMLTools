package datafileutil;

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
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.Tuple2;
import us.kbase.common.service.UnauthorizedException;

/**
 * <p>Original spec-file module name: DataFileUtil</p>
 * <pre>
 * Contains utilities for saving and retrieving data to and from KBase data
 * services. Requires Shock 0.9.6+ and Workspace Service 0.4.1+.
 * Note that some calls may create files or directories in the root of the scratch space (typically
 * /kb/module/work/tmp). For this reason client programmers should not request that DFU archive from
 * the root of the scratch space - always create a new directory (e.g. using a UUID for a name or a
 * standard library temporary directory utility) and add the target files to that directory when
 * archiving.
 * </pre>
 */
public class DataFileUtilClient {
    private JsonClientCaller caller;
    private long asyncJobCheckTimeMs = 100;
    private int asyncJobCheckTimeScalePercent = 150;
    private long asyncJobCheckMaxTimeMs = 300000;  // 5 minutes
    private String serviceVersion = "release";


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public DataFileUtilClient(URL url) {
        caller = new JsonClientCaller(url);
    }
    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public DataFileUtilClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
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
    public DataFileUtilClient(URL url, String user, String password) throws UnauthorizedException, IOException {
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
    public DataFileUtilClient(URL url, String user, String password, URL auth) throws UnauthorizedException, IOException {
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
        List<JobState<T>> res = caller.jsonrpcCall("DataFileUtil._check_job", args, retType, true, true);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: shock_to_file</p>
     * <pre>
     * Download a file from Shock.
     * </pre>
     * @param   params   instance of type {@link datafileutil.ShockToFileParams ShockToFileParams}
     * @return   parameter "out" of type {@link datafileutil.ShockToFileOutput ShockToFileOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _shockToFileSubmit(ShockToFileParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._shock_to_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: shock_to_file</p>
     * <pre>
     * Download a file from Shock.
     * </pre>
     * @param   params   instance of type {@link datafileutil.ShockToFileParams ShockToFileParams}
     * @return   parameter "out" of type {@link datafileutil.ShockToFileOutput ShockToFileOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ShockToFileOutput shockToFile(ShockToFileParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _shockToFileSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<ShockToFileOutput>>>> retType = new TypeReference<List<JobState<List<ShockToFileOutput>>>>() {};
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
            JobState<List<ShockToFileOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: shock_to_file_mass</p>
     * <pre>
     * Download multiple files from Shock.
     * </pre>
     * @param   params   instance of list of type {@link datafileutil.ShockToFileParams ShockToFileParams}
     * @return   parameter "out" of list of type {@link datafileutil.ShockToFileOutput ShockToFileOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _shockToFileMassSubmit(List<ShockToFileParams> params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._shock_to_file_mass_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: shock_to_file_mass</p>
     * <pre>
     * Download multiple files from Shock.
     * </pre>
     * @param   params   instance of list of type {@link datafileutil.ShockToFileParams ShockToFileParams}
     * @return   parameter "out" of list of type {@link datafileutil.ShockToFileOutput ShockToFileOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<ShockToFileOutput> shockToFileMass(List<ShockToFileParams> params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _shockToFileMassSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<List<ShockToFileOutput>>>>> retType = new TypeReference<List<JobState<List<List<ShockToFileOutput>>>>>() {};
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
            JobState<List<List<ShockToFileOutput>>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: file_to_shock</p>
     * <pre>
     * Load a file to Shock.
     * </pre>
     * @param   params   instance of type {@link datafileutil.FileToShockParams FileToShockParams}
     * @return   parameter "out" of type {@link datafileutil.FileToShockOutput FileToShockOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _fileToShockSubmit(FileToShockParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._file_to_shock_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: file_to_shock</p>
     * <pre>
     * Load a file to Shock.
     * </pre>
     * @param   params   instance of type {@link datafileutil.FileToShockParams FileToShockParams}
     * @return   parameter "out" of type {@link datafileutil.FileToShockOutput FileToShockOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public FileToShockOutput fileToShock(FileToShockParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _fileToShockSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<FileToShockOutput>>>> retType = new TypeReference<List<JobState<List<FileToShockOutput>>>>() {};
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
            JobState<List<FileToShockOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: unpack_file</p>
     * <pre>
     * Using the same logic as unpacking a Shock file, this method will cause
     * any bzip or gzip files to be uncompressed, and then unpack tar and zip
     * archive files (uncompressing gzipped or bzipped archive files if 
     * necessary). If the file is an archive, it will be unbundled into the 
     * directory containing the original output file.
     * </pre>
     * @param   params   instance of type {@link datafileutil.UnpackFileParams UnpackFileParams}
     * @return   parameter "out" of type {@link datafileutil.UnpackFileResult UnpackFileResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _unpackFileSubmit(UnpackFileParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._unpack_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: unpack_file</p>
     * <pre>
     * Using the same logic as unpacking a Shock file, this method will cause
     * any bzip or gzip files to be uncompressed, and then unpack tar and zip
     * archive files (uncompressing gzipped or bzipped archive files if 
     * necessary). If the file is an archive, it will be unbundled into the 
     * directory containing the original output file.
     * </pre>
     * @param   params   instance of type {@link datafileutil.UnpackFileParams UnpackFileParams}
     * @return   parameter "out" of type {@link datafileutil.UnpackFileResult UnpackFileResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public UnpackFileResult unpackFile(UnpackFileParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _unpackFileSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<UnpackFileResult>>>> retType = new TypeReference<List<JobState<List<UnpackFileResult>>>>() {};
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
            JobState<List<UnpackFileResult>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: pack_file</p>
     * <pre>
     * Pack a file or directory into gzip, targz, or zip archives.
     * </pre>
     * @param   params   instance of type {@link datafileutil.PackFileParams PackFileParams}
     * @return   parameter "out" of type {@link datafileutil.PackFileResult PackFileResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _packFileSubmit(PackFileParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._pack_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: pack_file</p>
     * <pre>
     * Pack a file or directory into gzip, targz, or zip archives.
     * </pre>
     * @param   params   instance of type {@link datafileutil.PackFileParams PackFileParams}
     * @return   parameter "out" of type {@link datafileutil.PackFileResult PackFileResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public PackFileResult packFile(PackFileParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _packFileSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<PackFileResult>>>> retType = new TypeReference<List<JobState<List<PackFileResult>>>>() {};
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
            JobState<List<PackFileResult>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: package_for_download</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link datafileutil.PackageForDownloadParams PackageForDownloadParams}
     * @return   instance of type {@link datafileutil.PackageForDownloadOutput PackageForDownloadOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _packageForDownloadSubmit(PackageForDownloadParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._package_for_download_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: package_for_download</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link datafileutil.PackageForDownloadParams PackageForDownloadParams}
     * @return   instance of type {@link datafileutil.PackageForDownloadOutput PackageForDownloadOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public PackageForDownloadOutput packageForDownload(PackageForDownloadParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _packageForDownloadSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<PackageForDownloadOutput>>>> retType = new TypeReference<List<JobState<List<PackageForDownloadOutput>>>>() {};
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
            JobState<List<PackageForDownloadOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: file_to_shock_mass</p>
     * <pre>
     * Load multiple files to Shock.
     * </pre>
     * @param   params   instance of list of type {@link datafileutil.FileToShockParams FileToShockParams}
     * @return   parameter "out" of list of type {@link datafileutil.FileToShockOutput FileToShockOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _fileToShockMassSubmit(List<FileToShockParams> params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._file_to_shock_mass_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: file_to_shock_mass</p>
     * <pre>
     * Load multiple files to Shock.
     * </pre>
     * @param   params   instance of list of type {@link datafileutil.FileToShockParams FileToShockParams}
     * @return   parameter "out" of list of type {@link datafileutil.FileToShockOutput FileToShockOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<FileToShockOutput> fileToShockMass(List<FileToShockParams> params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _fileToShockMassSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<List<FileToShockOutput>>>>> retType = new TypeReference<List<JobState<List<List<FileToShockOutput>>>>>() {};
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
            JobState<List<List<FileToShockOutput>>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: copy_shock_node</p>
     * <pre>
     * Copy a Shock node.
     * </pre>
     * @param   params   instance of type {@link datafileutil.CopyShockNodeParams CopyShockNodeParams}
     * @return   parameter "out" of type {@link datafileutil.CopyShockNodeOutput CopyShockNodeOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _copyShockNodeSubmit(CopyShockNodeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._copy_shock_node_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: copy_shock_node</p>
     * <pre>
     * Copy a Shock node.
     * </pre>
     * @param   params   instance of type {@link datafileutil.CopyShockNodeParams CopyShockNodeParams}
     * @return   parameter "out" of type {@link datafileutil.CopyShockNodeOutput CopyShockNodeOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public CopyShockNodeOutput copyShockNode(CopyShockNodeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _copyShockNodeSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<CopyShockNodeOutput>>>> retType = new TypeReference<List<JobState<List<CopyShockNodeOutput>>>>() {};
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
            JobState<List<CopyShockNodeOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: own_shock_node</p>
     * <pre>
     * Gain ownership of a Shock node.
     * Returns a shock node id which is owned by the caller, given a shock
     * node id.
     * If the shock node is already owned by the caller, returns the same
     * shock node ID. If not, the ID of a copy of the original node will be
     * returned.
     * If a handle is requested, the node is already owned by the caller, and
     * a handle already exists, that handle will be returned. Otherwise a new
     * handle will be created and returned.
     * </pre>
     * @param   params   instance of type {@link datafileutil.OwnShockNodeParams OwnShockNodeParams}
     * @return   parameter "out" of type {@link datafileutil.OwnShockNodeOutput OwnShockNodeOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _ownShockNodeSubmit(OwnShockNodeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._own_shock_node_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: own_shock_node</p>
     * <pre>
     * Gain ownership of a Shock node.
     * Returns a shock node id which is owned by the caller, given a shock
     * node id.
     * If the shock node is already owned by the caller, returns the same
     * shock node ID. If not, the ID of a copy of the original node will be
     * returned.
     * If a handle is requested, the node is already owned by the caller, and
     * a handle already exists, that handle will be returned. Otherwise a new
     * handle will be created and returned.
     * </pre>
     * @param   params   instance of type {@link datafileutil.OwnShockNodeParams OwnShockNodeParams}
     * @return   parameter "out" of type {@link datafileutil.OwnShockNodeOutput OwnShockNodeOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public OwnShockNodeOutput ownShockNode(OwnShockNodeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _ownShockNodeSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<OwnShockNodeOutput>>>> retType = new TypeReference<List<JobState<List<OwnShockNodeOutput>>>>() {};
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
            JobState<List<OwnShockNodeOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: ws_name_to_id</p>
     * <pre>
     * Translate a workspace name to a workspace ID.
     * </pre>
     * @param   name   instance of String
     * @return   parameter "id" of Long
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _wsNameToIdSubmit(String name, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(name);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._ws_name_to_id_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: ws_name_to_id</p>
     * <pre>
     * Translate a workspace name to a workspace ID.
     * </pre>
     * @param   name   instance of String
     * @return   parameter "id" of Long
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long wsNameToId(String name, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _wsNameToIdSubmit(name, jsonRpcContext);
        TypeReference<List<JobState<List<Long>>>> retType = new TypeReference<List<JobState<List<Long>>>>() {};
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
            JobState<List<Long>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: save_objects</p>
     * <pre>
     * Save objects to the workspace. Saving over a deleted object undeletes
     * it.
     * </pre>
     * @param   params   instance of type {@link datafileutil.SaveObjectsParams SaveObjectsParams}
     * @return   parameter "info" of list of original type "object_info" (Information about an object, including user provided metadata. objid - the numerical id of the object. name - the name of the object. type - the type of the object. save_date - the save date of the object. ver - the version of the object. saved_by - the user that saved or copied the object. wsid - the id of the workspace containing the object. workspace - the name of the workspace containing the object. chsum - the md5 checksum of the object. size - the size of the object in bytes. meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of Long, parameter "name" of String, parameter "type" of String, parameter "save_date" of String, parameter "version" of Long, parameter "saved_by" of String, parameter "wsid" of Long, parameter "workspace" of String, parameter "chsum" of String, parameter "size" of Long, parameter "meta" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _saveObjectsSubmit(SaveObjectsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._save_objects_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: save_objects</p>
     * <pre>
     * Save objects to the workspace. Saving over a deleted object undeletes
     * it.
     * </pre>
     * @param   params   instance of type {@link datafileutil.SaveObjectsParams SaveObjectsParams}
     * @return   parameter "info" of list of original type "object_info" (Information about an object, including user provided metadata. objid - the numerical id of the object. name - the name of the object. type - the type of the object. save_date - the save date of the object. ver - the version of the object. saved_by - the user that saved or copied the object. wsid - the id of the workspace containing the object. workspace - the name of the workspace containing the object. chsum - the md5 checksum of the object. size - the size of the object in bytes. meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of Long, parameter "name" of String, parameter "type" of String, parameter "save_date" of String, parameter "version" of Long, parameter "saved_by" of String, parameter "wsid" of Long, parameter "workspace" of String, parameter "chsum" of String, parameter "size" of Long, parameter "meta" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> saveObjects(SaveObjectsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _saveObjectsSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>>>> retType = new TypeReference<List<JobState<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>>>>() {};
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
            JobState<List<List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>>>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: get_objects</p>
     * <pre>
     * Get objects from the workspace.
     * </pre>
     * @param   params   instance of type {@link datafileutil.GetObjectsParams GetObjectsParams}
     * @return   parameter "results" of type {@link datafileutil.GetObjectsResults GetObjectsResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _getObjectsSubmit(GetObjectsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._get_objects_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_objects</p>
     * <pre>
     * Get objects from the workspace.
     * </pre>
     * @param   params   instance of type {@link datafileutil.GetObjectsParams GetObjectsParams}
     * @return   parameter "results" of type {@link datafileutil.GetObjectsResults GetObjectsResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GetObjectsResults getObjects(GetObjectsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _getObjectsSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<GetObjectsResults>>>> retType = new TypeReference<List<JobState<List<GetObjectsResults>>>>() {};
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
            JobState<List<GetObjectsResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: versions</p>
     * <pre>
     * Get the versions of the Workspace service and Shock service.
     * </pre>
     * @return   multiple set: (1) parameter "wsver" of String, (2) parameter "shockver" of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _versionsSubmit(RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._versions_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: versions</p>
     * <pre>
     * Get the versions of the Workspace service and Shock service.
     * </pre>
     * @return   multiple set: (1) parameter "wsver" of String, (2) parameter "shockver" of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple2<String, String> versions(RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _versionsSubmit(jsonRpcContext);
        TypeReference<List<JobState<Tuple2<String, String>>>> retType = new TypeReference<List<JobState<Tuple2<String, String>>>>() {};
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
            JobState<Tuple2<String, String>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult();
        }
    }

    /**
     * <p>Original spec-file function name: download_staging_file</p>
     * <pre>
     * Download a staging area file to scratch area
     * </pre>
     * @param   params   instance of type {@link datafileutil.DownloadStagingFileParams DownloadStagingFileParams}
     * @return   parameter "results" of type {@link datafileutil.DownloadStagingFileOutput DownloadStagingFileOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _downloadStagingFileSubmit(DownloadStagingFileParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._download_staging_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: download_staging_file</p>
     * <pre>
     * Download a staging area file to scratch area
     * </pre>
     * @param   params   instance of type {@link datafileutil.DownloadStagingFileParams DownloadStagingFileParams}
     * @return   parameter "results" of type {@link datafileutil.DownloadStagingFileOutput DownloadStagingFileOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public DownloadStagingFileOutput downloadStagingFile(DownloadStagingFileParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _downloadStagingFileSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<DownloadStagingFileOutput>>>> retType = new TypeReference<List<JobState<List<DownloadStagingFileOutput>>>>() {};
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
            JobState<List<DownloadStagingFileOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: download_web_file</p>
     * <pre>
     * Download a web file to scratch area
     * </pre>
     * @param   params   instance of type {@link datafileutil.DownloadWebFileParams DownloadWebFileParams}
     * @return   parameter "results" of type {@link datafileutil.DownloadWebFileOutput DownloadWebFileOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _downloadWebFileSubmit(DownloadWebFileParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("DataFileUtil._download_web_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: download_web_file</p>
     * <pre>
     * Download a web file to scratch area
     * </pre>
     * @param   params   instance of type {@link datafileutil.DownloadWebFileParams DownloadWebFileParams}
     * @return   parameter "results" of type {@link datafileutil.DownloadWebFileOutput DownloadWebFileOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public DownloadWebFileOutput downloadWebFile(DownloadWebFileParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _downloadWebFileSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<DownloadWebFileOutput>>>> retType = new TypeReference<List<JobState<List<DownloadWebFileOutput>>>>() {};
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
            JobState<List<DownloadWebFileOutput>> res = _checkJob(jobId, retType);
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
        List<String> res1 = caller.jsonrpcCall("DataFileUtil._status_submit", args, retType1, true, true, jsonRpcContext);
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
