package genomeannotationapi;

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
import us.kbase.common.service.Tuple2;
import us.kbase.common.service.UnauthorizedException;

/**
 * <p>Original spec-file module name: GenomeAnnotationAPI</p>
 * <pre>
 * </pre>
 */
public class GenomeAnnotationAPIServiceClient {
    private JsonClientCaller caller;
    private String serviceVersion = "release";
    private static URL DEFAULT_URL = null;
    static {
        try {
            DEFAULT_URL = new URL("https://kbase.us/services/service_wizard");
        } catch (MalformedURLException mue) {
            throw new RuntimeException("Compile error in client - bad url compiled");
        }
    }

    /** Constructs a client with the default url and no user credentials.*/
    public GenomeAnnotationAPIServiceClient() {
       caller = new JsonClientCaller(DEFAULT_URL);
        caller.setDynamic(true);
    }


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public GenomeAnnotationAPIServiceClient(URL url) {
        caller = new JsonClientCaller(url);
        caller.setDynamic(true);
    }
    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public GenomeAnnotationAPIServiceClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, token);
        caller.setDynamic(true);
    }

    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param user the user name.
     * @param password the password for the user name.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public GenomeAnnotationAPIServiceClient(URL url, String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password);
        caller.setDynamic(true);
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
    public GenomeAnnotationAPIServiceClient(URL url, String user, String password, URL auth) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password, auth);
        caller.setDynamic(true);
    }

    /** Constructs a client with the default URL.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public GenomeAnnotationAPIServiceClient(AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(DEFAULT_URL, token);
        caller.setDynamic(true);
    }

    /** Constructs a client with the default URL.
     * @param user the user name.
     * @param password the password for the user name.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public GenomeAnnotationAPIServiceClient(String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(DEFAULT_URL, user, password);
        caller.setDynamic(true);
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
     * <p>Original spec-file function name: get_taxon</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetTaxon InputsGetTaxon} (original type "inputs_get_taxon")
     * @return   instance of original type "ObjectReference"
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String getTaxon(InputsGetTaxon arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_taxon", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_assembly</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetAssembly InputsGetAssembly} (original type "inputs_get_assembly")
     * @return   instance of original type "ObjectReference"
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String getAssembly(InputsGetAssembly arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_assembly", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_feature_types</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetFeatureTypes InputsGetFeatureTypes} (original type "inputs_get_feature_types")
     * @return   instance of list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getFeatureTypes(InputsGetFeatureTypes arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_feature_types", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_feature_type_descriptions</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetFeatureTypeDescriptions InputsGetFeatureTypeDescriptions} (original type "inputs_get_feature_type_descriptions")
     * @return   instance of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getFeatureTypeDescriptions(InputsGetFeatureTypeDescriptions arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_feature_type_descriptions", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_feature_type_counts</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetFeatureTypeCounts InputsGetFeatureTypeCounts} (original type "inputs_get_feature_type_counts")
     * @return   instance of mapping from String to Long
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Long> getFeatureTypeCounts(InputsGetFeatureTypeCounts arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,Long>>> retType = new TypeReference<List<Map<String,Long>>>() {};
        List<Map<String,Long>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_feature_type_counts", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_feature_ids</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetFeatureIds InputsGetFeatureIds} (original type "inputs_get_feature_ids")
     * @return   instance of type {@link genomeannotationapi.FeatureIdMapping FeatureIdMapping} (original type "Feature_id_mapping")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public FeatureIdMapping getFeatureIds(InputsGetFeatureIds arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<FeatureIdMapping>> retType = new TypeReference<List<FeatureIdMapping>>() {};
        List<FeatureIdMapping> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_feature_ids", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_features</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetFeatures InputsGetFeatures} (original type "inputs_get_features")
     * @return   instance of mapping from String to type {@link genomeannotationapi.FeatureData FeatureData} (original type "Feature_data")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FeatureData> getFeatures(InputsGetFeatures arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,FeatureData>>> retType = new TypeReference<List<Map<String,FeatureData>>>() {};
        List<Map<String,FeatureData>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_features", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_features2</p>
     * <pre>
     * *
     * * Retrieve Feature data, v2.
     * *
     * * @param feature_id_list List of Features to retrieve.
     * *   If None, returns all Feature data.
     * * @return Mapping from Feature IDs to dicts of available data.
     * </pre>
     * @param   params   instance of type {@link genomeannotationapi.GetFeatures2Params GetFeatures2Params}
     * @return   instance of mapping from String to type {@link genomeannotationapi.FeatureData FeatureData} (original type "Feature_data")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FeatureData> getFeatures2(GetFeatures2Params params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Map<String,FeatureData>>> retType = new TypeReference<List<Map<String,FeatureData>>>() {};
        List<Map<String,FeatureData>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_features2", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_proteins</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetProteins InputsGetProteins} (original type "inputs_get_proteins")
     * @return   instance of mapping from String to type {@link genomeannotationapi.ProteinData ProteinData} (original type "Protein_data")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,ProteinData> getProteins(InputsGetProteins arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,ProteinData>>> retType = new TypeReference<List<Map<String,ProteinData>>>() {};
        List<Map<String,ProteinData>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_proteins", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_feature_locations</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetFeatureLocations InputsGetFeatureLocations} (original type "inputs_get_feature_locations")
     * @return   instance of mapping from String to list of type {@link genomeannotationapi.Region Region}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,List<Region>> getFeatureLocations(InputsGetFeatureLocations arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,List<Region>>>> retType = new TypeReference<List<Map<String,List<Region>>>>() {};
        List<Map<String,List<Region>>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_feature_locations", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_feature_publications</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetFeaturePublications InputsGetFeaturePublications} (original type "inputs_get_feature_publications")
     * @return   instance of mapping from String to list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,List<String>> getFeaturePublications(InputsGetFeaturePublications arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,List<String>>>> retType = new TypeReference<List<Map<String,List<String>>>>() {};
        List<Map<String,List<String>>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_feature_publications", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_feature_dna</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetFeatureDna InputsGetFeatureDna} (original type "inputs_get_feature_dna")
     * @return   instance of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getFeatureDna(InputsGetFeatureDna arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_feature_dna", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_feature_functions</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetFeatureFunctions InputsGetFeatureFunctions} (original type "inputs_get_feature_functions")
     * @return   instance of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getFeatureFunctions(InputsGetFeatureFunctions arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_feature_functions", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_feature_aliases</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetFeatureAliases InputsGetFeatureAliases} (original type "inputs_get_feature_aliases")
     * @return   instance of mapping from String to list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,List<String>> getFeatureAliases(InputsGetFeatureAliases arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,List<String>>>> retType = new TypeReference<List<Map<String,List<String>>>>() {};
        List<Map<String,List<String>>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_feature_aliases", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_cds_by_gene</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetCdsByGene InputsGetCdsByGene} (original type "inputs_get_cds_by_gene")
     * @return   instance of mapping from String to list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,List<String>> getCdsByGene(InputsGetCdsByGene arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,List<String>>>> retType = new TypeReference<List<Map<String,List<String>>>>() {};
        List<Map<String,List<String>>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_cds_by_gene", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_cds_by_mrna</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsMrnaIdList InputsMrnaIdList} (original type "inputs_mrna_id_list")
     * @return   instance of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getCdsByMrna(InputsMrnaIdList arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_cds_by_mrna", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_gene_by_cds</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetGeneByCds InputsGetGeneByCds} (original type "inputs_get_gene_by_cds")
     * @return   instance of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getGeneByCds(InputsGetGeneByCds arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_gene_by_cds", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_gene_by_mrna</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetGeneByMrna InputsGetGeneByMrna} (original type "inputs_get_gene_by_mrna")
     * @return   instance of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getGeneByMrna(InputsGetGeneByMrna arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_gene_by_mrna", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_mrna_by_cds</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetMrnaByCds InputsGetMrnaByCds} (original type "inputs_get_mrna_by_cds")
     * @return   instance of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getMrnaByCds(InputsGetMrnaByCds arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_mrna_by_cds", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_mrna_by_gene</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetMrnaByGene InputsGetMrnaByGene} (original type "inputs_get_mrna_by_gene")
     * @return   instance of mapping from String to list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,List<String>> getMrnaByGene(InputsGetMrnaByGene arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,List<String>>>> retType = new TypeReference<List<Map<String,List<String>>>>() {};
        List<Map<String,List<String>>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_mrna_by_gene", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_mrna_exons</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetMrnaExons InputsGetMrnaExons} (original type "inputs_get_mrna_exons")
     * @return   instance of mapping from String to list of type {@link genomeannotationapi.ExonData ExonData} (original type "Exon_data")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,List<ExonData>> getMrnaExons(InputsGetMrnaExons arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,List<ExonData>>>> retType = new TypeReference<List<Map<String,List<ExonData>>>>() {};
        List<Map<String,List<ExonData>>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_mrna_exons", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_mrna_utrs</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetMrnaUtrs InputsGetMrnaUtrs} (original type "inputs_get_mrna_utrs")
     * @return   instance of mapping from String to mapping from String to type {@link genomeannotationapi.UTRData UTRData} (original type "UTR_data")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,UTRData>> getMrnaUtrs(InputsGetMrnaUtrs arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<Map<String,Map<String,UTRData>>>> retType = new TypeReference<List<Map<String,Map<String,UTRData>>>>() {};
        List<Map<String,Map<String,UTRData>>> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_mrna_utrs", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_summary</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsGetSummary InputsGetSummary} (original type "inputs_get_summary")
     * @return   instance of type {@link genomeannotationapi.SummaryData SummaryData} (original type "Summary_data")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public SummaryData getSummary(InputsGetSummary arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<SummaryData>> retType = new TypeReference<List<SummaryData>>() {};
        List<SummaryData> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_summary", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: save_summary</p>
     * <pre>
     * </pre>
     * @param   arg1   instance of type {@link genomeannotationapi.InputsSaveSummary InputsSaveSummary} (original type "inputs_save_summary")
     * @return   multiple set: (1) instance of Long, (2) instance of type {@link genomeannotationapi.SummaryData SummaryData} (original type "Summary_data")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple2<Long, SummaryData> saveSummary(InputsSaveSummary arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<Tuple2<Long, SummaryData>> retType = new TypeReference<Tuple2<Long, SummaryData>>() {};
        Tuple2<Long, SummaryData> res = caller.jsonrpcCall("GenomeAnnotationAPI.save_summary", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res;
    }

    /**
     * <p>Original spec-file function name: get_combined_data</p>
     * <pre>
     * * Retrieve any part of GenomeAnnotation. Please don't use this method in full mode (with all parts included) in cases
     * * of large eukaryotic datasets. It may lead to out-of-memory errors.
     * </pre>
     * @param   params   instance of type {@link genomeannotationapi.GetCombinedDataParams GetCombinedDataParams}
     * @return   instance of type {@link genomeannotationapi.GenomeAnnotationData GenomeAnnotationData} (original type "GenomeAnnotation_data")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GenomeAnnotationData getCombinedData(GetCombinedDataParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GenomeAnnotationData>> retType = new TypeReference<List<GenomeAnnotationData>>() {};
        List<GenomeAnnotationData> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_combined_data", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_genome_v1</p>
     * <pre>
     * A reasonably simple wrapper on get_objects2, but with Genome specific
     * filters instead of arbitrary get subdata included paths.
     * </pre>
     * @param   params   instance of type {@link genomeannotationapi.GetGenomeParamsV1 GetGenomeParamsV1}
     * @return   parameter "data" of type {@link genomeannotationapi.GenomeDataSetV1 GenomeDataSetV1}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GenomeDataSetV1 getGenomeV1(GetGenomeParamsV1 params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GenomeDataSetV1>> retType = new TypeReference<List<GenomeDataSetV1>>() {};
        List<GenomeDataSetV1> res = caller.jsonrpcCall("GenomeAnnotationAPI.get_genome_v1", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: save_one_genome_v1</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link genomeannotationapi.SaveOneGenomeParamsV1 SaveOneGenomeParamsV1}
     * @return   parameter "result" of type {@link genomeannotationapi.SaveGenomeResultV1 SaveGenomeResultV1}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public SaveGenomeResultV1 saveOneGenomeV1(SaveOneGenomeParamsV1 params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<SaveGenomeResultV1>> retType = new TypeReference<List<SaveGenomeResultV1>>() {};
        List<SaveGenomeResultV1> res = caller.jsonrpcCall("GenomeAnnotationAPI.save_one_genome_v1", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    public Map<String, Object> status(RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<Map<String, Object>>> retType = new TypeReference<List<Map<String, Object>>>() {};
        List<Map<String, Object>> res = caller.jsonrpcCall("GenomeAnnotationAPI.status", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }
}
