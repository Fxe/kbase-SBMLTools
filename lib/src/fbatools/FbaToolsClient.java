package fbatools;

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
 * <p>Original spec-file module name: fba_tools</p>
 * <pre>
 * A KBase module: fba_tools
 * This module contains the implementation for the primary methods in KBase for metabolic model reconstruction, gapfilling, and analysis
 * </pre>
 */
public class FbaToolsClient {
    private JsonClientCaller caller;
    private long asyncJobCheckTimeMs = 100;
    private int asyncJobCheckTimeScalePercent = 150;
    private long asyncJobCheckMaxTimeMs = 300000;  // 5 minutes
    private String serviceVersion = "release";


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public FbaToolsClient(URL url) {
        caller = new JsonClientCaller(url);
    }
    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public FbaToolsClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
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
    public FbaToolsClient(URL url, String user, String password) throws UnauthorizedException, IOException {
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
    public FbaToolsClient(URL url, String user, String password, URL auth) throws UnauthorizedException, IOException {
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
        List<JobState<T>> res = caller.jsonrpcCall("fba_tools._check_job", args, retType, true, true);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: build_metabolic_model</p>
     * <pre>
     * Build a genome-scale metabolic model based on annotations in an input genome typed object
     * </pre>
     * @param   params   instance of type {@link fbatools.BuildMetabolicModelParams BuildMetabolicModelParams}
     * @return   instance of type {@link fbatools.BuildMetabolicModelResults BuildMetabolicModelResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _buildMetabolicModelSubmit(BuildMetabolicModelParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._build_metabolic_model_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: build_metabolic_model</p>
     * <pre>
     * Build a genome-scale metabolic model based on annotations in an input genome typed object
     * </pre>
     * @param   params   instance of type {@link fbatools.BuildMetabolicModelParams BuildMetabolicModelParams}
     * @return   instance of type {@link fbatools.BuildMetabolicModelResults BuildMetabolicModelResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public BuildMetabolicModelResults buildMetabolicModel(BuildMetabolicModelParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _buildMetabolicModelSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<BuildMetabolicModelResults>>>> retType = new TypeReference<List<JobState<List<BuildMetabolicModelResults>>>>() {};
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
            JobState<List<BuildMetabolicModelResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: build_multiple_metabolic_models</p>
     * <pre>
     * Build multiple genome-scale metabolic models based on annotations in an input genome typed object
     * </pre>
     * @param   params   instance of type {@link fbatools.BuildMultipleMetabolicModelsParams BuildMultipleMetabolicModelsParams}
     * @return   instance of type {@link fbatools.BuildMultipleMetabolicModelsResults BuildMultipleMetabolicModelsResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _buildMultipleMetabolicModelsSubmit(BuildMultipleMetabolicModelsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._build_multiple_metabolic_models_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: build_multiple_metabolic_models</p>
     * <pre>
     * Build multiple genome-scale metabolic models based on annotations in an input genome typed object
     * </pre>
     * @param   params   instance of type {@link fbatools.BuildMultipleMetabolicModelsParams BuildMultipleMetabolicModelsParams}
     * @return   instance of type {@link fbatools.BuildMultipleMetabolicModelsResults BuildMultipleMetabolicModelsResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public BuildMultipleMetabolicModelsResults buildMultipleMetabolicModels(BuildMultipleMetabolicModelsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _buildMultipleMetabolicModelsSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<BuildMultipleMetabolicModelsResults>>>> retType = new TypeReference<List<JobState<List<BuildMultipleMetabolicModelsResults>>>>() {};
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
            JobState<List<BuildMultipleMetabolicModelsResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: gapfill_metabolic_model</p>
     * <pre>
     * Gapfills a metabolic model to induce flux in a specified reaction
     * </pre>
     * @param   params   instance of type {@link fbatools.GapfillMetabolicModelParams GapfillMetabolicModelParams}
     * @return   parameter "results" of type {@link fbatools.GapfillMetabolicModelResults GapfillMetabolicModelResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _gapfillMetabolicModelSubmit(GapfillMetabolicModelParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._gapfill_metabolic_model_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: gapfill_metabolic_model</p>
     * <pre>
     * Gapfills a metabolic model to induce flux in a specified reaction
     * </pre>
     * @param   params   instance of type {@link fbatools.GapfillMetabolicModelParams GapfillMetabolicModelParams}
     * @return   parameter "results" of type {@link fbatools.GapfillMetabolicModelResults GapfillMetabolicModelResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GapfillMetabolicModelResults gapfillMetabolicModel(GapfillMetabolicModelParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _gapfillMetabolicModelSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<GapfillMetabolicModelResults>>>> retType = new TypeReference<List<JobState<List<GapfillMetabolicModelResults>>>>() {};
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
            JobState<List<GapfillMetabolicModelResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: run_flux_balance_analysis</p>
     * <pre>
     * Run flux balance analysis and return ID of FBA object with results
     * </pre>
     * @param   params   instance of type {@link fbatools.RunFluxBalanceAnalysisParams RunFluxBalanceAnalysisParams}
     * @return   parameter "results" of type {@link fbatools.RunFluxBalanceAnalysisResults RunFluxBalanceAnalysisResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _runFluxBalanceAnalysisSubmit(RunFluxBalanceAnalysisParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
//        System.out.println();
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._run_flux_balance_analysis_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: run_flux_balance_analysis</p>
     * <pre>
     * Run flux balance analysis and return ID of FBA object with results
     * </pre>
     * @param   params   instance of type {@link fbatools.RunFluxBalanceAnalysisParams RunFluxBalanceAnalysisParams}
     * @return   parameter "results" of type {@link fbatools.RunFluxBalanceAnalysisResults RunFluxBalanceAnalysisResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public RunFluxBalanceAnalysisResults runFluxBalanceAnalysis(RunFluxBalanceAnalysisParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _runFluxBalanceAnalysisSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<RunFluxBalanceAnalysisResults>>>> retType = new TypeReference<List<JobState<List<RunFluxBalanceAnalysisResults>>>>() {};
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
            JobState<List<RunFluxBalanceAnalysisResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: compare_fba_solutions</p>
     * <pre>
     * Compares multiple FBA solutions and saves comparison as a new object in the workspace
     * </pre>
     * @param   params   instance of type {@link fbatools.CompareFBASolutionsParams CompareFBASolutionsParams}
     * @return   parameter "results" of type {@link fbatools.CompareFBASolutionsResults CompareFBASolutionsResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _compareFbaSolutionsSubmit(CompareFBASolutionsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._compare_fba_solutions_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: compare_fba_solutions</p>
     * <pre>
     * Compares multiple FBA solutions and saves comparison as a new object in the workspace
     * </pre>
     * @param   params   instance of type {@link fbatools.CompareFBASolutionsParams CompareFBASolutionsParams}
     * @return   parameter "results" of type {@link fbatools.CompareFBASolutionsResults CompareFBASolutionsResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public CompareFBASolutionsResults compareFbaSolutions(CompareFBASolutionsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _compareFbaSolutionsSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<CompareFBASolutionsResults>>>> retType = new TypeReference<List<JobState<List<CompareFBASolutionsResults>>>>() {};
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
            JobState<List<CompareFBASolutionsResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: propagate_model_to_new_genome</p>
     * <pre>
     * Translate the metabolic model of one organism to another, using a mapping of similar proteins between their genomes
     * </pre>
     * @param   params   instance of type {@link fbatools.PropagateModelToNewGenomeParams PropagateModelToNewGenomeParams}
     * @return   parameter "results" of type {@link fbatools.PropagateModelToNewGenomeResults PropagateModelToNewGenomeResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _propagateModelToNewGenomeSubmit(PropagateModelToNewGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._propagate_model_to_new_genome_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: propagate_model_to_new_genome</p>
     * <pre>
     * Translate the metabolic model of one organism to another, using a mapping of similar proteins between their genomes
     * </pre>
     * @param   params   instance of type {@link fbatools.PropagateModelToNewGenomeParams PropagateModelToNewGenomeParams}
     * @return   parameter "results" of type {@link fbatools.PropagateModelToNewGenomeResults PropagateModelToNewGenomeResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public PropagateModelToNewGenomeResults propagateModelToNewGenome(PropagateModelToNewGenomeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _propagateModelToNewGenomeSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<PropagateModelToNewGenomeResults>>>> retType = new TypeReference<List<JobState<List<PropagateModelToNewGenomeResults>>>>() {};
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
            JobState<List<PropagateModelToNewGenomeResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: simulate_growth_on_phenotype_data</p>
     * <pre>
     * Use Flux Balance Analysis (FBA) to simulate multiple growth phenotypes.
     * </pre>
     * @param   params   instance of type {@link fbatools.SimulateGrowthOnPhenotypeDataParams SimulateGrowthOnPhenotypeDataParams}
     * @return   parameter "results" of type {@link fbatools.SimulateGrowthOnPhenotypeDataResults SimulateGrowthOnPhenotypeDataResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _simulateGrowthOnPhenotypeDataSubmit(SimulateGrowthOnPhenotypeDataParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._simulate_growth_on_phenotype_data_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: simulate_growth_on_phenotype_data</p>
     * <pre>
     * Use Flux Balance Analysis (FBA) to simulate multiple growth phenotypes.
     * </pre>
     * @param   params   instance of type {@link fbatools.SimulateGrowthOnPhenotypeDataParams SimulateGrowthOnPhenotypeDataParams}
     * @return   parameter "results" of type {@link fbatools.SimulateGrowthOnPhenotypeDataResults SimulateGrowthOnPhenotypeDataResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public SimulateGrowthOnPhenotypeDataResults simulateGrowthOnPhenotypeData(SimulateGrowthOnPhenotypeDataParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _simulateGrowthOnPhenotypeDataSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<SimulateGrowthOnPhenotypeDataResults>>>> retType = new TypeReference<List<JobState<List<SimulateGrowthOnPhenotypeDataResults>>>>() {};
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
            JobState<List<SimulateGrowthOnPhenotypeDataResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: merge_metabolic_models_into_community_model</p>
     * <pre>
     * Merge two or more metabolic models into a compartmentalized community model
     * </pre>
     * @param   params   instance of type {@link fbatools.MergeMetabolicModelsIntoCommunityModelParams MergeMetabolicModelsIntoCommunityModelParams}
     * @return   parameter "results" of type {@link fbatools.MergeMetabolicModelsIntoCommunityModelResults MergeMetabolicModelsIntoCommunityModelResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _mergeMetabolicModelsIntoCommunityModelSubmit(MergeMetabolicModelsIntoCommunityModelParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._merge_metabolic_models_into_community_model_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: merge_metabolic_models_into_community_model</p>
     * <pre>
     * Merge two or more metabolic models into a compartmentalized community model
     * </pre>
     * @param   params   instance of type {@link fbatools.MergeMetabolicModelsIntoCommunityModelParams MergeMetabolicModelsIntoCommunityModelParams}
     * @return   parameter "results" of type {@link fbatools.MergeMetabolicModelsIntoCommunityModelResults MergeMetabolicModelsIntoCommunityModelResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public MergeMetabolicModelsIntoCommunityModelResults mergeMetabolicModelsIntoCommunityModel(MergeMetabolicModelsIntoCommunityModelParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _mergeMetabolicModelsIntoCommunityModelSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<MergeMetabolicModelsIntoCommunityModelResults>>>> retType = new TypeReference<List<JobState<List<MergeMetabolicModelsIntoCommunityModelResults>>>>() {};
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
            JobState<List<MergeMetabolicModelsIntoCommunityModelResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: compare_flux_with_expression</p>
     * <pre>
     * Merge two or more metabolic models into a compartmentalized community model
     * </pre>
     * @param   params   instance of type {@link fbatools.CompareFluxWithExpressionParams CompareFluxWithExpressionParams}
     * @return   parameter "results" of type {@link fbatools.CompareFluxWithExpressionResults CompareFluxWithExpressionResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _compareFluxWithExpressionSubmit(CompareFluxWithExpressionParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._compare_flux_with_expression_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: compare_flux_with_expression</p>
     * <pre>
     * Merge two or more metabolic models into a compartmentalized community model
     * </pre>
     * @param   params   instance of type {@link fbatools.CompareFluxWithExpressionParams CompareFluxWithExpressionParams}
     * @return   parameter "results" of type {@link fbatools.CompareFluxWithExpressionResults CompareFluxWithExpressionResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public CompareFluxWithExpressionResults compareFluxWithExpression(CompareFluxWithExpressionParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _compareFluxWithExpressionSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<CompareFluxWithExpressionResults>>>> retType = new TypeReference<List<JobState<List<CompareFluxWithExpressionResults>>>>() {};
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
            JobState<List<CompareFluxWithExpressionResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: check_model_mass_balance</p>
     * <pre>
     * Identifies reactions in the model that are not mass balanced
     * </pre>
     * @param   params   instance of type {@link fbatools.CheckModelMassBalanceParams CheckModelMassBalanceParams}
     * @return   parameter "results" of type {@link fbatools.CheckModelMassBalanceResults CheckModelMassBalanceResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _checkModelMassBalanceSubmit(CheckModelMassBalanceParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._check_model_mass_balance_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: check_model_mass_balance</p>
     * <pre>
     * Identifies reactions in the model that are not mass balanced
     * </pre>
     * @param   params   instance of type {@link fbatools.CheckModelMassBalanceParams CheckModelMassBalanceParams}
     * @return   parameter "results" of type {@link fbatools.CheckModelMassBalanceResults CheckModelMassBalanceResults}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public CheckModelMassBalanceResults checkModelMassBalance(CheckModelMassBalanceParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _checkModelMassBalanceSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<CheckModelMassBalanceResults>>>> retType = new TypeReference<List<JobState<List<CheckModelMassBalanceResults>>>>() {};
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
            JobState<List<CheckModelMassBalanceResults>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: compare_models</p>
     * <pre>
     * Compare models
     * </pre>
     * @param   params   instance of type {@link fbatools.ModelComparisonParams ModelComparisonParams}
     * @return   instance of type {@link fbatools.ModelComparisonResult ModelComparisonResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _compareModelsSubmit(ModelComparisonParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._compare_models_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: compare_models</p>
     * <pre>
     * Compare models
     * </pre>
     * @param   params   instance of type {@link fbatools.ModelComparisonParams ModelComparisonParams}
     * @return   instance of type {@link fbatools.ModelComparisonResult ModelComparisonResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ModelComparisonResult compareModels(ModelComparisonParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _compareModelsSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<ModelComparisonResult>>>> retType = new TypeReference<List<JobState<List<ModelComparisonResult>>>>() {};
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
            JobState<List<ModelComparisonResult>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: edit_metabolic_model</p>
     * <pre>
     * Edit models
     * </pre>
     * @param   params   instance of type {@link fbatools.EditMetabolicModelParams EditMetabolicModelParams}
     * @return   instance of type {@link fbatools.EditMetabolicModelResult EditMetabolicModelResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _editMetabolicModelSubmit(EditMetabolicModelParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._edit_metabolic_model_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: edit_metabolic_model</p>
     * <pre>
     * Edit models
     * </pre>
     * @param   params   instance of type {@link fbatools.EditMetabolicModelParams EditMetabolicModelParams}
     * @return   instance of type {@link fbatools.EditMetabolicModelResult EditMetabolicModelResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public EditMetabolicModelResult editMetabolicModel(EditMetabolicModelParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _editMetabolicModelSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<EditMetabolicModelResult>>>> retType = new TypeReference<List<JobState<List<EditMetabolicModelResult>>>>() {};
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
            JobState<List<EditMetabolicModelResult>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: edit_media</p>
     * <pre>
     * Edit models
     * </pre>
     * @param   params   instance of type {@link fbatools.EditMediaParams EditMediaParams}
     * @return   instance of type {@link fbatools.EditMediaResult EditMediaResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _editMediaSubmit(EditMediaParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._edit_media_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: edit_media</p>
     * <pre>
     * Edit models
     * </pre>
     * @param   params   instance of type {@link fbatools.EditMediaParams EditMediaParams}
     * @return   instance of type {@link fbatools.EditMediaResult EditMediaResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public EditMediaResult editMedia(EditMediaParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _editMediaSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<EditMediaResult>>>> retType = new TypeReference<List<JobState<List<EditMediaResult>>>>() {};
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
            JobState<List<EditMediaResult>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: excel_file_to_model</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.ModelCreationParams ModelCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _excelFileToModelSubmit(ModelCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(p);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._excel_file_to_model_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: excel_file_to_model</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.ModelCreationParams ModelCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public WorkspaceRef excelFileToModel(ModelCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _excelFileToModelSubmit(p, jsonRpcContext);
        TypeReference<List<JobState<List<WorkspaceRef>>>> retType = new TypeReference<List<JobState<List<WorkspaceRef>>>>() {};
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
            JobState<List<WorkspaceRef>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: sbml_file_to_model</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.ModelCreationParams ModelCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _sbmlFileToModelSubmit(ModelCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(p);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._sbml_file_to_model_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: sbml_file_to_model</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.ModelCreationParams ModelCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public WorkspaceRef sbmlFileToModel(ModelCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _sbmlFileToModelSubmit(p, jsonRpcContext);
        TypeReference<List<JobState<List<WorkspaceRef>>>> retType = new TypeReference<List<JobState<List<WorkspaceRef>>>>() {};
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
            JobState<List<WorkspaceRef>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: tsv_file_to_model</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.ModelCreationParams ModelCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _tsvFileToModelSubmit(ModelCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(p);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._tsv_file_to_model_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: tsv_file_to_model</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.ModelCreationParams ModelCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public WorkspaceRef tsvFileToModel(ModelCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _tsvFileToModelSubmit(p, jsonRpcContext);
        TypeReference<List<JobState<List<WorkspaceRef>>>> retType = new TypeReference<List<JobState<List<WorkspaceRef>>>>() {};
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
            JobState<List<WorkspaceRef>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: model_to_excel_file</p>
     * <pre>
     * </pre>
     * @param   model   instance of type {@link fbatools.ModelObjectSelectionParams ModelObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _modelToExcelFileSubmit(ModelObjectSelectionParams model, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(model);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._model_to_excel_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: model_to_excel_file</p>
     * <pre>
     * </pre>
     * @param   model   instance of type {@link fbatools.ModelObjectSelectionParams ModelObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public fbatools.File modelToExcelFile(ModelObjectSelectionParams model, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _modelToExcelFileSubmit(model, jsonRpcContext);
        TypeReference<List<JobState<List<fbatools.File>>>> retType = new TypeReference<List<JobState<List<fbatools.File>>>>() {};
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
            JobState<List<fbatools.File>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: model_to_sbml_file</p>
     * <pre>
     * </pre>
     * @param   model   instance of type {@link fbatools.ModelObjectSelectionParams ModelObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _modelToSbmlFileSubmit(ModelObjectSelectionParams model, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(model);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._model_to_sbml_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: model_to_sbml_file</p>
     * <pre>
     * </pre>
     * @param   model   instance of type {@link fbatools.ModelObjectSelectionParams ModelObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public fbatools.File modelToSbmlFile(ModelObjectSelectionParams model, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _modelToSbmlFileSubmit(model, jsonRpcContext);
        TypeReference<List<JobState<List<fbatools.File>>>> retType = new TypeReference<List<JobState<List<fbatools.File>>>>() {};
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
            JobState<List<fbatools.File>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: model_to_tsv_file</p>
     * <pre>
     * </pre>
     * @param   model   instance of type {@link fbatools.ModelObjectSelectionParams ModelObjectSelectionParams}
     * @return   parameter "files" of type {@link fbatools.ModelTsvFiles ModelTsvFiles}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _modelToTsvFileSubmit(ModelObjectSelectionParams model, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(model);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._model_to_tsv_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: model_to_tsv_file</p>
     * <pre>
     * </pre>
     * @param   model   instance of type {@link fbatools.ModelObjectSelectionParams ModelObjectSelectionParams}
     * @return   parameter "files" of type {@link fbatools.ModelTsvFiles ModelTsvFiles}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ModelTsvFiles modelToTsvFile(ModelObjectSelectionParams model, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _modelToTsvFileSubmit(model, jsonRpcContext);
        TypeReference<List<JobState<List<ModelTsvFiles>>>> retType = new TypeReference<List<JobState<List<ModelTsvFiles>>>>() {};
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
            JobState<List<ModelTsvFiles>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: export_model_as_excel_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportModelAsExcelFileSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._export_model_as_excel_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_model_as_excel_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportModelAsExcelFile(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportModelAsExcelFileSubmit(params, jsonRpcContext);
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
     * <p>Original spec-file function name: export_model_as_tsv_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportModelAsTsvFileSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._export_model_as_tsv_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_model_as_tsv_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportModelAsTsvFile(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportModelAsTsvFileSubmit(params, jsonRpcContext);
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
     * <p>Original spec-file function name: export_model_as_sbml_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportModelAsSbmlFileSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._export_model_as_sbml_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_model_as_sbml_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportModelAsSbmlFile(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportModelAsSbmlFileSubmit(params, jsonRpcContext);
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
     * <p>Original spec-file function name: fba_to_excel_file</p>
     * <pre>
     * </pre>
     * @param   fba   instance of type {@link fbatools.FBAObjectSelectionParams FBAObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _fbaToExcelFileSubmit(FBAObjectSelectionParams fba, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(fba);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._fba_to_excel_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: fba_to_excel_file</p>
     * <pre>
     * </pre>
     * @param   fba   instance of type {@link fbatools.FBAObjectSelectionParams FBAObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public fbatools.File fbaToExcelFile(FBAObjectSelectionParams fba, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _fbaToExcelFileSubmit(fba, jsonRpcContext);
        TypeReference<List<JobState<List<fbatools.File>>>> retType = new TypeReference<List<JobState<List<fbatools.File>>>>() {};
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
            JobState<List<fbatools.File>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: fba_to_tsv_file</p>
     * <pre>
     * </pre>
     * @param   fba   instance of type {@link fbatools.FBAObjectSelectionParams FBAObjectSelectionParams}
     * @return   parameter "files" of type {@link fbatools.FBATsvFiles FBATsvFiles}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _fbaToTsvFileSubmit(FBAObjectSelectionParams fba, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(fba);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._fba_to_tsv_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: fba_to_tsv_file</p>
     * <pre>
     * </pre>
     * @param   fba   instance of type {@link fbatools.FBAObjectSelectionParams FBAObjectSelectionParams}
     * @return   parameter "files" of type {@link fbatools.FBATsvFiles FBATsvFiles}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public FBATsvFiles fbaToTsvFile(FBAObjectSelectionParams fba, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _fbaToTsvFileSubmit(fba, jsonRpcContext);
        TypeReference<List<JobState<List<FBATsvFiles>>>> retType = new TypeReference<List<JobState<List<FBATsvFiles>>>>() {};
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
            JobState<List<FBATsvFiles>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: export_fba_as_excel_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportFbaAsExcelFileSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._export_fba_as_excel_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_fba_as_excel_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportFbaAsExcelFile(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportFbaAsExcelFileSubmit(params, jsonRpcContext);
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
     * <p>Original spec-file function name: export_fba_as_tsv_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportFbaAsTsvFileSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._export_fba_as_tsv_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_fba_as_tsv_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportFbaAsTsvFile(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportFbaAsTsvFileSubmit(params, jsonRpcContext);
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
     * <p>Original spec-file function name: tsv_file_to_media</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.MediaCreationParams MediaCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _tsvFileToMediaSubmit(MediaCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(p);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._tsv_file_to_media_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: tsv_file_to_media</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.MediaCreationParams MediaCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public WorkspaceRef tsvFileToMedia(MediaCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _tsvFileToMediaSubmit(p, jsonRpcContext);
        TypeReference<List<JobState<List<WorkspaceRef>>>> retType = new TypeReference<List<JobState<List<WorkspaceRef>>>>() {};
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
            JobState<List<WorkspaceRef>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: excel_file_to_media</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.MediaCreationParams MediaCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _excelFileToMediaSubmit(MediaCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(p);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._excel_file_to_media_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: excel_file_to_media</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.MediaCreationParams MediaCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public WorkspaceRef excelFileToMedia(MediaCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _excelFileToMediaSubmit(p, jsonRpcContext);
        TypeReference<List<JobState<List<WorkspaceRef>>>> retType = new TypeReference<List<JobState<List<WorkspaceRef>>>>() {};
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
            JobState<List<WorkspaceRef>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: media_to_tsv_file</p>
     * <pre>
     * </pre>
     * @param   media   instance of type {@link fbatools.MediaObjectSelectionParams MediaObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _mediaToTsvFileSubmit(MediaObjectSelectionParams media, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(media);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._media_to_tsv_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: media_to_tsv_file</p>
     * <pre>
     * </pre>
     * @param   media   instance of type {@link fbatools.MediaObjectSelectionParams MediaObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public fbatools.File mediaToTsvFile(MediaObjectSelectionParams media, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _mediaToTsvFileSubmit(media, jsonRpcContext);
        TypeReference<List<JobState<List<fbatools.File>>>> retType = new TypeReference<List<JobState<List<fbatools.File>>>>() {};
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
            JobState<List<fbatools.File>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: media_to_excel_file</p>
     * <pre>
     * </pre>
     * @param   media   instance of type {@link fbatools.MediaObjectSelectionParams MediaObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _mediaToExcelFileSubmit(MediaObjectSelectionParams media, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(media);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._media_to_excel_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: media_to_excel_file</p>
     * <pre>
     * </pre>
     * @param   media   instance of type {@link fbatools.MediaObjectSelectionParams MediaObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public fbatools.File mediaToExcelFile(MediaObjectSelectionParams media, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _mediaToExcelFileSubmit(media, jsonRpcContext);
        TypeReference<List<JobState<List<fbatools.File>>>> retType = new TypeReference<List<JobState<List<fbatools.File>>>>() {};
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
            JobState<List<fbatools.File>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: export_media_as_excel_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportMediaAsExcelFileSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._export_media_as_excel_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_media_as_excel_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportMediaAsExcelFile(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportMediaAsExcelFileSubmit(params, jsonRpcContext);
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
     * <p>Original spec-file function name: export_media_as_tsv_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportMediaAsTsvFileSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._export_media_as_tsv_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_media_as_tsv_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportMediaAsTsvFile(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportMediaAsTsvFileSubmit(params, jsonRpcContext);
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
     * <p>Original spec-file function name: tsv_file_to_phenotype_set</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.PhenotypeSetCreationParams PhenotypeSetCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _tsvFileToPhenotypeSetSubmit(PhenotypeSetCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(p);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._tsv_file_to_phenotype_set_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: tsv_file_to_phenotype_set</p>
     * <pre>
     * </pre>
     * @param   p   instance of type {@link fbatools.PhenotypeSetCreationParams PhenotypeSetCreationParams}
     * @return   instance of type {@link fbatools.WorkspaceRef WorkspaceRef}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public WorkspaceRef tsvFileToPhenotypeSet(PhenotypeSetCreationParams p, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _tsvFileToPhenotypeSetSubmit(p, jsonRpcContext);
        TypeReference<List<JobState<List<WorkspaceRef>>>> retType = new TypeReference<List<JobState<List<WorkspaceRef>>>>() {};
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
            JobState<List<WorkspaceRef>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: phenotype_set_to_tsv_file</p>
     * <pre>
     * </pre>
     * @param   phenotypeSet   instance of type {@link fbatools.PhenotypeSetObjectSelectionParams PhenotypeSetObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _phenotypeSetToTsvFileSubmit(PhenotypeSetObjectSelectionParams phenotypeSet, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(phenotypeSet);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._phenotype_set_to_tsv_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: phenotype_set_to_tsv_file</p>
     * <pre>
     * </pre>
     * @param   phenotypeSet   instance of type {@link fbatools.PhenotypeSetObjectSelectionParams PhenotypeSetObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public fbatools.File phenotypeSetToTsvFile(PhenotypeSetObjectSelectionParams phenotypeSet, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _phenotypeSetToTsvFileSubmit(phenotypeSet, jsonRpcContext);
        TypeReference<List<JobState<List<fbatools.File>>>> retType = new TypeReference<List<JobState<List<fbatools.File>>>>() {};
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
            JobState<List<fbatools.File>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: export_phenotype_set_as_tsv_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportPhenotypeSetAsTsvFileSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._export_phenotype_set_as_tsv_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_phenotype_set_as_tsv_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportPhenotypeSetAsTsvFile(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportPhenotypeSetAsTsvFileSubmit(params, jsonRpcContext);
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
     * <p>Original spec-file function name: phenotype_simulation_set_to_excel_file</p>
     * <pre>
     * </pre>
     * @param   pss   instance of type {@link fbatools.PhenotypeSimulationSetObjectSelectionParams PhenotypeSimulationSetObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _phenotypeSimulationSetToExcelFileSubmit(PhenotypeSimulationSetObjectSelectionParams pss, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(pss);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._phenotype_simulation_set_to_excel_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: phenotype_simulation_set_to_excel_file</p>
     * <pre>
     * </pre>
     * @param   pss   instance of type {@link fbatools.PhenotypeSimulationSetObjectSelectionParams PhenotypeSimulationSetObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public fbatools.File phenotypeSimulationSetToExcelFile(PhenotypeSimulationSetObjectSelectionParams pss, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _phenotypeSimulationSetToExcelFileSubmit(pss, jsonRpcContext);
        TypeReference<List<JobState<List<fbatools.File>>>> retType = new TypeReference<List<JobState<List<fbatools.File>>>>() {};
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
            JobState<List<fbatools.File>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: phenotype_simulation_set_to_tsv_file</p>
     * <pre>
     * </pre>
     * @param   pss   instance of type {@link fbatools.PhenotypeSimulationSetObjectSelectionParams PhenotypeSimulationSetObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _phenotypeSimulationSetToTsvFileSubmit(PhenotypeSimulationSetObjectSelectionParams pss, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(pss);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._phenotype_simulation_set_to_tsv_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: phenotype_simulation_set_to_tsv_file</p>
     * <pre>
     * </pre>
     * @param   pss   instance of type {@link fbatools.PhenotypeSimulationSetObjectSelectionParams PhenotypeSimulationSetObjectSelectionParams}
     * @return   parameter "f" of type {@link fbatools.File File}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public fbatools.File phenotypeSimulationSetToTsvFile(PhenotypeSimulationSetObjectSelectionParams pss, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _phenotypeSimulationSetToTsvFileSubmit(pss, jsonRpcContext);
        TypeReference<List<JobState<List<fbatools.File>>>> retType = new TypeReference<List<JobState<List<fbatools.File>>>>() {};
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
            JobState<List<fbatools.File>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: export_phenotype_simulation_set_as_excel_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportPhenotypeSimulationSetAsExcelFileSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._export_phenotype_simulation_set_as_excel_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_phenotype_simulation_set_as_excel_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportPhenotypeSimulationSetAsExcelFile(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportPhenotypeSimulationSetAsExcelFileSubmit(params, jsonRpcContext);
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
     * <p>Original spec-file function name: export_phenotype_simulation_set_as_tsv_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportPhenotypeSimulationSetAsTsvFileSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._export_phenotype_simulation_set_as_tsv_file_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_phenotype_simulation_set_as_tsv_file</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.ExportParams ExportParams}
     * @return   parameter "output" of type {@link fbatools.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportPhenotypeSimulationSetAsTsvFile(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportPhenotypeSimulationSetAsTsvFileSubmit(params, jsonRpcContext);
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
     * <p>Original spec-file function name: bulk_export_objects</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.BulkExportObjectsParams BulkExportObjectsParams}
     * @return   parameter "output" of type {@link fbatools.BulkExportObjectsResult BulkExportObjectsResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _bulkExportObjectsSubmit(BulkExportObjectsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("fba_tools._bulk_export_objects_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: bulk_export_objects</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link fbatools.BulkExportObjectsParams BulkExportObjectsParams}
     * @return   parameter "output" of type {@link fbatools.BulkExportObjectsResult BulkExportObjectsResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public BulkExportObjectsResult bulkExportObjects(BulkExportObjectsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _bulkExportObjectsSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<BulkExportObjectsResult>>>> retType = new TypeReference<List<JobState<List<BulkExportObjectsResult>>>>() {};
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
            JobState<List<BulkExportObjectsResult>> res = _checkJob(jobId, retType);
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
        List<String> res1 = caller.jsonrpcCall("fba_tools._status_submit", args, retType1, true, true, jsonRpcContext);
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
