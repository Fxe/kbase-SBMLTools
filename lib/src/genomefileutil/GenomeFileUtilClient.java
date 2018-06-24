package genomefileutil;

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
import us.kbase.common.service.UObject;
import us.kbase.common.service.UnauthorizedException;

/**
 * <p>Original spec-file module name: GenomeFileUtil</p>
 * <pre>
 * </pre>
 */
public class GenomeFileUtilClient {
    private JsonClientCaller caller;
    private long asyncJobCheckTimeMs = 100;
    private int asyncJobCheckTimeScalePercent = 150;
    private long asyncJobCheckMaxTimeMs = 300000;  // 5 minutes
    private String serviceVersion = "release";


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public GenomeFileUtilClient(URL url) {
        caller = new JsonClientCaller(url);
    }
    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public GenomeFileUtilClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
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
    public GenomeFileUtilClient(URL url, String user, String password) throws UnauthorizedException, IOException {
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
    public GenomeFileUtilClient(URL url, String user, String password, URL auth) throws UnauthorizedException, IOException {
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
        List<JobState<T>> res = caller.jsonrpcCall("GenomeFileUtil._check_job", args, retType, true, true);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: genbank_to_genome</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.GenbankToGenomeParams GenbankToGenomeParams}
     * @return   parameter "result" of type {@link genomefileutil.GenomeSaveResult GenomeSaveResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _genbankToGenomeSubmit(GenbankToGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("GenomeFileUtil._genbank_to_genome_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: genbank_to_genome</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.GenbankToGenomeParams GenbankToGenomeParams}
     * @return   parameter "result" of type {@link genomefileutil.GenomeSaveResult GenomeSaveResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GenomeSaveResult genbankToGenome(GenbankToGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _genbankToGenomeSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<GenomeSaveResult>>>> retType = new TypeReference<List<JobState<List<GenomeSaveResult>>>>() {};
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
            JobState<List<GenomeSaveResult>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: genome_to_gff</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.GenomeToGFFParams GenomeToGFFParams}
     * @return   parameter "result" of type {@link genomefileutil.GenomeToGFFResult GenomeToGFFResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _genomeToGffSubmit(GenomeToGFFParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("GenomeFileUtil._genome_to_gff_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: genome_to_gff</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.GenomeToGFFParams GenomeToGFFParams}
     * @return   parameter "result" of type {@link genomefileutil.GenomeToGFFResult GenomeToGFFResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GenomeToGFFResult genomeToGff(GenomeToGFFParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _genomeToGffSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<GenomeToGFFResult>>>> retType = new TypeReference<List<JobState<List<GenomeToGFFResult>>>>() {};
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
            JobState<List<GenomeToGFFResult>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: genome_to_genbank</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.GenomeToGenbankParams GenomeToGenbankParams}
     * @return   parameter "result" of type {@link genomefileutil.GenomeToGenbankResult GenomeToGenbankResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _genomeToGenbankSubmit(GenomeToGenbankParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("GenomeFileUtil._genome_to_genbank_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: genome_to_genbank</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.GenomeToGenbankParams GenomeToGenbankParams}
     * @return   parameter "result" of type {@link genomefileutil.GenomeToGenbankResult GenomeToGenbankResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GenomeToGenbankResult genomeToGenbank(GenomeToGenbankParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _genomeToGenbankSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<GenomeToGenbankResult>>>> retType = new TypeReference<List<JobState<List<GenomeToGenbankResult>>>>() {};
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
            JobState<List<GenomeToGenbankResult>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: export_genome_as_genbank</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.ExportParams ExportParams}
     * @return   parameter "output" of type {@link genomefileutil.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportGenomeAsGenbankSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("GenomeFileUtil._export_genome_as_genbank_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_genome_as_genbank</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.ExportParams ExportParams}
     * @return   parameter "output" of type {@link genomefileutil.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportGenomeAsGenbank(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportGenomeAsGenbankSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<ExportOutput>>>> retType = new TypeReference<List<JobState<List<ExportOutput>>>>() {};
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
            JobState<List<ExportOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: export_genome_as_gff</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.ExportParams ExportParams}
     * @return   parameter "output" of type {@link genomefileutil.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportGenomeAsGffSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("GenomeFileUtil._export_genome_as_gff_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_genome_as_gff</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.ExportParams ExportParams}
     * @return   parameter "output" of type {@link genomefileutil.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportGenomeAsGff(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportGenomeAsGffSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<ExportOutput>>>> retType = new TypeReference<List<JobState<List<ExportOutput>>>>() {};
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
            JobState<List<ExportOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: fasta_gff_to_genome</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.FastaGFFToGenomeParams FastaGFFToGenomeParams}
     * @return   parameter "returnVal" of type {@link genomefileutil.GenomeSaveResult GenomeSaveResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _fastaGffToGenomeSubmit(FastaGFFToGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("GenomeFileUtil._fasta_gff_to_genome_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: fasta_gff_to_genome</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.FastaGFFToGenomeParams FastaGFFToGenomeParams}
     * @return   parameter "returnVal" of type {@link genomefileutil.GenomeSaveResult GenomeSaveResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GenomeSaveResult fastaGffToGenome(FastaGFFToGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _fastaGffToGenomeSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<GenomeSaveResult>>>> retType = new TypeReference<List<JobState<List<GenomeSaveResult>>>>() {};
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
            JobState<List<GenomeSaveResult>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: fasta_gff_to_genome_json</p>
     * <pre>
     * As above but returns the genome instead
     * </pre>
     * @param   params   instance of type {@link genomefileutil.FastaGFFToGenomeParams FastaGFFToGenomeParams}
     * @return   parameter "genome" of unspecified object
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _fastaGffToGenomeJsonSubmit(FastaGFFToGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("GenomeFileUtil._fasta_gff_to_genome_json_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: fasta_gff_to_genome_json</p>
     * <pre>
     * As above but returns the genome instead
     * </pre>
     * @param   params   instance of type {@link genomefileutil.FastaGFFToGenomeParams FastaGFFToGenomeParams}
     * @return   parameter "genome" of unspecified object
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public UObject fastaGffToGenomeJson(FastaGFFToGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _fastaGffToGenomeJsonSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<UObject>>>> retType = new TypeReference<List<JobState<List<UObject>>>>() {};
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
            JobState<List<UObject>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: save_one_genome</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.SaveOneGenomeParams SaveOneGenomeParams}
     * @return   parameter "returnVal" of type {@link genomefileutil.SaveGenomeResult SaveGenomeResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _saveOneGenomeSubmit(SaveOneGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("GenomeFileUtil._save_one_genome_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: save_one_genome</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomefileutil.SaveOneGenomeParams SaveOneGenomeParams}
     * @return   parameter "returnVal" of type {@link genomefileutil.SaveGenomeResult SaveGenomeResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public SaveGenomeResult saveOneGenome(SaveOneGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _saveOneGenomeSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<SaveGenomeResult>>>> retType = new TypeReference<List<JobState<List<SaveGenomeResult>>>>() {};
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
            JobState<List<SaveGenomeResult>> res = _checkJob(jobId, retType);
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
        List<String> res1 = caller.jsonrpcCall("GenomeFileUtil._status_submit", args, retType1, true, true, jsonRpcContext);
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
