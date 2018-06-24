package kbsolrutil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import com.fasterxml.jackson.databind.ObjectMapper;

import datafileutil.DataFileUtilClient;
import genomeannotationapi.GenomeAnnotationAPIClient;
import kbasegenomes.Genome;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseId;
import sbmltools.KBaseType;
import sbmltools.test.CacheEngine;
import sbmltools.test.CacheEngine.CacheFieldType;
import sbmltools.test.MockKBSolrUtilClient;
import sbmltools.test.WSCLIENT;
import us.kbase.auth.AuthConfig;
import us.kbase.auth.AuthException;
import us.kbase.auth.AuthToken;
import us.kbase.auth.ConfigurableAuthService;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.Tuple4;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.WorkspaceClient;
import us.kbase.workspace.WorkspaceIdentity;

public class KBaseAPI {
  
  public static Map<String, String> getConfigProd() {
    Map<String, String> config = new HashMap<String, String> ();
    if (!config.containsKey("kbase-endpoint")) {
      config.put("kbase-endpoint", "https://kbase.us/services");
    }
    if (!config.containsKey("job-service-url")) {
      config.put("job-service-url", "https://kbase.us/services/userandjobstate/");
    }
    config.put("callback-url", "https://kbase.us/services/njs_wrapper");
    config.put("workspace-url", "https://kbase.us/services/ws/");
    config.put("shock-url", "https://kbase.us/services/shock-api");
    config.put("auth-service-url", "https://kbase.us/services/auth/api/legacy/KBase/Sessions/Login");
    config.put("auth-service-url-allow-insecure", "false");
    config.put("version", "prod");
    return config;
  }
  
  public static Map<String, String> getConfigDev() {
    Map<String, String> config = new HashMap<String, String> ();
    if (!config.containsKey("kbase-endpoint")) {
      config.put("kbase-endpoint", "https://appdev.kbase.us/services");
    }
    if (!config.containsKey("job-service-url")) {
      config.put("job-service-url", "https://appdev.kbase.us/services/userandjobstate/");
    }
    config.put("callback-url", "https://appdev.kbase.us/services/njs_wrapper");
    config.put("workspace-url", "https://appdev.kbase.us/services/ws/");
    config.put("shock-url", "https://appdev.kbase.us/services/shock-api");
    config.put("auth-service-url", "https://appdev.kbase.us/services/auth/api/legacy/KBase/Sessions/Login");
    config.put("auth-service-url-allow-insecure", "false");
    config.put("version", "appdev");
    return config;
  }
  
  public final String token;
  public AuthToken authToken;
  public URL callbackURL;
//  public final boolean cacheData = false;
  public WorkspaceClient wsClient;
  public KBSolrUtilClient solrClient;
  public DataFileUtilClient dfuClient;
  public GenomeAnnotationAPIClient gaClient;
  
  public static Map<String, Map<Pair<String, String>, Map<CacheFieldType, String>>> cacheIndexMap = new HashMap<> ();
  
  public KBaseAPI(final String token, final Map<String, String> config, final boolean cache) throws IOException {
    this.token = token;
    String authUrl = config.get("auth-service-url");
    
    try {
      ConfigurableAuthService authService =  new ConfigurableAuthService(
          new AuthConfig().withKBaseAuthServerURL(new URL(authUrl))
          .withAllowInsecureURLs("true".equals(config.get("auth-service-url-allow-insecure"))));
      authToken = authService.validateToken(token);
      
      callbackURL = new URL(config.get("callback-url"));
      if (cache) {
        
        
        CacheEngine cengine = new CacheEngine(cacheIndexMap, config.get("version"));
        CacheEngine.buildCacheIndex(CacheEngine.cacheIndexFile, cacheIndexMap);
        cengine.status();
        wsClient = new WSCLIENT(new URL(config.get("workspace-url")), authToken, cengine);
        solrClient = new MockKBSolrUtilClient(callbackURL, authToken);
      } else {
        wsClient = new WorkspaceClient(new URL(config.get("workspace-url")), authToken);
        solrClient = new KBSolrUtilClient(callbackURL, authToken);
      }
      gaClient = new GenomeAnnotationAPIClient(callbackURL, authToken);
      gaClient.setConnectionReadTimeOut(900000000);
      gaClient.setAsyncJobCheckMaxTimeMs(6000000);
      dfuClient = new DataFileUtilClient(callbackURL, authToken);
      dfuClient.setIsInsecureHttpConnectionAllowed(false);
      gaClient.setIsInsecureHttpConnectionAllowed(false);
      wsClient.setIsInsecureHttpConnectionAllowed(false);
      solrClient.setIsInsecureHttpConnectionAllowed(false);
      solrClient.setServiceVersion("beta");
    } catch (IOException | URISyntaxException | AuthException | UnauthorizedException e) {
      authToken = null; 
      throw new IOException(e);
    }
  }
  
  public WorkspaceClient getWorkspaceClient() {
    return wsClient;
  }
  
  public KBSolrUtilClient getKBSolrUtilClient() {
    return solrClient;
  }
  
  public GenomeAnnotationAPIClient getGenomeClient() {
    return gaClient;
  }
  
  public Object getWorkspaceInfo(String ws) throws IOException {
    try {
      WorkspaceIdentity wsi = new WorkspaceIdentity().withWorkspace(ws);
      return getWorkspaceClient().getWorkspaceInfo(wsi);
    } catch (JsonClientException e) {
      throw new IOException(e);
    }
  }
  
  public Object getWorkspaceObject(String id, String ws) throws IOException {
    Object o = KBaseIOUtils.getObject(id, ws, null, getWorkspaceClient());
    return o;
  }
  
  public<T> T getWorkspaceObject(String id, String ws, Class<T> clazz) throws IOException {
    Object o = KBaseIOUtils.getObject(id, ws, null, getWorkspaceClient());
    ObjectMapper om = new ObjectMapper();
    
    return om.convertValue(o, clazz);
  }
  
  public Genome getGenome(String id, String ws) throws IOException {
    Genome o = KBaseIOUtils.getObject(id, ws, null, Genome.class, getWorkspaceClient());
    return o;
  }
  
  public Genome getGenome(KBaseId kid) throws IOException {
    Genome o = KBaseIOUtils.getObject(kid.name, kid.workspace, kid.reference, Genome.class, getWorkspaceClient());
    return o;
  }
  
  public KBaseId saveGenome(String id, String ws, Genome o) throws IOException {
    try {
      KBaseId kid = KBaseIOUtils.saveData(id, KBaseType.Genome.value(), o, ws, getWorkspaceClient());
      return kid;
    } catch (Exception e) {
      throw new IOException(e);
    }
  }
  
  public KBaseId saveObject(String id, String ws, String otype, Object o) throws IOException {
    try {
      KBaseId kid = KBaseIOUtils.saveData(id, otype, o, ws, getWorkspaceClient());
      return kid;
    } catch (Exception e) {
      throw new IOException(e);
    }
  }
  
  public KBaseId saveObject(String id, String ws, KBaseType otype, Object o) throws IOException {
    try {
      KBaseId kid = KBaseIOUtils.saveData(id, otype, o, ws, getWorkspaceClient());
      return kid;
    } catch (Exception e) {
      throw new IOException(e);
    }
  }
  
  public List<KBaseId> listNarrative(String ws, KBaseType otype) throws IOException {
    return listNarrative(ws, otype.value());
  }
  
  public List<KBaseId> listNarrative(String ws) throws IOException {
    return listNarrative(ws, KBaseType.Any.value());
  }
  
  public List<KBaseId> listNarrative(String ws, String otype) throws IOException {
    List<KBaseId> result = new ArrayList<> ();
    List<String> workspaces = new ArrayList<> ();
    workspaces.add(ws);
    try {
      ListObjectsParams lparams = new ListObjectsParams().withWorkspaces(workspaces);
      if (!"Any".equals(otype)) {
        lparams.withType(otype);
      }
      List<Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>>> o = 
      wsClient.listObjects(lparams);
      for (Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>> t : o) {
        String oref = String.format("%d/%d/%d", t.getE7(), t.getE1(), t.getE5());
        String oname = t.getE2();
        String ows = t.getE8();
        KBaseId kid = new KBaseId(oname, ows, oref);
        result.add(kid);
      }
    } catch (IOException | JsonClientException e) {
      throw new IOException(e);
    }
    
    return result;
  }
  
  public KBaseId getReference(String name, String ws, String otype) throws IOException {
    KBaseId result = null;
    List<String> workspaces = new ArrayList<> ();
    workspaces.add(ws);
    try {
      List<Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>>> o =
      wsClient.listObjects(
          new ListObjectsParams().withWorkspaces(workspaces).withType(otype));
      
//      GetObjects2Results r =   wsClient.getObjects2(null);
//      ObjectData odata;
//      odata.
      for (Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>> t : o) {
//        System.out.println(t);
        String oname = t.getE2();
        String ows = t.getE8();
        System.out.println(name + " " + ws + " " + oname + " " + ows);
        if (oname.equals(name) && ows.equals(ws)) {
          String oref = String.format("%d/%d/%d", t.getE7(), t.getE1(), t.getE5());
          result = new KBaseId(oname, ows, oref);      
        }
      }
    } catch (IOException | JsonClientException e) {
      throw new IOException(e);
    }
    
    return result;
  }
  
  public void find(String otype, String user) throws IOException {
    Map<String, String> result = new HashMap<> ();
    List<String> workspaces = new ArrayList<> ();
    List<String> users = new ArrayList<> ();
    users.add(user);
    try {
      List<Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>>> o = 
      wsClient.listObjects(
          new ListObjectsParams().withSavedby(users).withType(otype).withWorkspaces(workspaces));
      for (Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>> t : o) {
        System.out.println(t);
        result.put(t.getE2(), t.getE3());
      }
    } catch (IOException | JsonClientException e) {
      throw new IOException(e);
    }
  }
  
  public void importModel(String path) {
    
  }
  
  public void importModel(InputStream is) {
    
  }
}
