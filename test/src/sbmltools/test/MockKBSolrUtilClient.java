package sbmltools.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;

import kbsolrutil.KBSolrUtilClient;
import kbsolrutil.SearchSolrParams;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.UnauthorizedException;

public class MockKBSolrUtilClient extends KBSolrUtilClient {

  private static final Logger logger = LoggerFactory.getLogger(MockKBSolrUtilClient.class);

  public static final String EXAMPLE = "{\"responseHeader\":{\"status\":0,\"QTime\":0,\"params\":{\"fl\":\"*\",\"start\":\"0\",\"q\":\"aliases:(ECW_m3682)\",\"wt\":\"json\",\"rows\":\"2000\"}},\"response\":{\"numFound\":1,\"start\":0,\"docs\":[{\"object_name\":\"GCF_000184185.1|feature--ECW_RS18855\",\"dna_sequence_length\":327,\"protein_translation_length\":108,\"genome_source\":\"refseq\",\"location_end\":[3759891],\"genetic_code\":\"11\",\"taxonomy\":[\"cellular organisms\",\"Bacteria\",\"Proteobacteria\",\"Gammaproteobacteria\",\"Enterobacterales\",\"Enterobacteriaceae\",\"Escherichia\",\"Escherichia coli\"],\"gc_content\":0.5082009,\"num_contigs\":3,\"aliases\":[\"GI:446294073\",\"ECW_RS18855\",\"WP_000371928.1\",\"ECW_m3682\"],\"location_strand\":[\"-\"],\"md5\":\"ec91636b1845ef0fd360e099acbfc1cd\",\"object_id\":\"kb|ws_ref--19217/26121/1|feature--ECW_RS18855\",\"domain\":\"Bacteria\",\"taxonomy_ref\":\"12570/575172/2\",\"workspace_name\":\"ReferenceDataManager\",\"genome_id\":\"GCF_000184185.1\",\"genome_dna_size\":5008864,\"feature_type\":\"gene\",\"num_cds\":5095,\"scientific_name\":\"Escherichia coli W\",\"object_type\":\"KBaseGenomes.Genome-8.2.Feature\",\"ws_ref\":\"19217/26121/1\",\"save_date\":\"2017-02-13\",\"genome_feature_id\":\"GCF_000184185.1|feature--ECW_RS18855\",\"genome_source_id\":\"NC_017635 (2 more accessions)\",\"location_contig\":[\"NC_017635\"],\"location_begin\":[0,3760218],\"feature_id\":\"ECW_RS18855\",\"assembly_ref\":\"19217/26120/1\",\"refseq_category\":\"User upload\",\"functions\":[\"thiosulfate sulfurtransferase\"],\"_version_\":1568815727399403521}]}}";
  public static final String EMPTY_RESPONSE = "{\"response\":{}}";
  public boolean cache = true;
  public Map<String, String> cacheData = null;
  public File cacheIndex = new File("/var/argonne/solr_cache/index.txt");

  public MockKBSolrUtilClient() {
    super(null);
  }

  public MockKBSolrUtilClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
    super(url, token);
  }

  public void buildCacheIndex() {
    cacheData = new HashMap<> ();
    if (cacheIndex.exists() && cacheIndex.isFile()) {
      InputStream is = null;
      try {
        is = new FileInputStream(cacheIndex);
        for (String l : IOUtils.readLines(is)) {
          String data[] = l.split("\t");
          cacheData.put(data[0], data[1]);
        }
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(is);
      }
    }
    
    System.out.println(cacheData);
  }
  
  
  
  public String getCacheData(String q) {
    String response = EMPTY_RESPONSE;
    if (cacheData.containsKey(q)) {
      File f = new File(cacheIndex.getParent() + "/" + cacheData.get(q));
      InputStream is = null;
      try {
        is = new FileInputStream(f);
        List<String> data = IOUtils.readLines(is);
        response = StringUtils.join(data, "");
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(is);
      }
    }
    
    return response;
  }
  
  public void saveCache(String q, String data) {
    String uuid = UUID.randomUUID().toString();
    OutputStream os = null;
    try {
      File f = new File(cacheIndex.getParent() + "/" + uuid + ".json");
      os = new FileOutputStream(f);
      IOUtils.write(data, os);
      this.cacheData.put(q, f.getName());
      logger.info("query saved !");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(os);
    }
    updateIndex();
  }
  
  public void updateIndex() {
    OutputStream os = null;
    try {
      os = new FileOutputStream(cacheIndex);
      IOUtils.write(Joiner.on('\n').withKeyValueSeparator("\t").join(cacheData), os);
      logger.info("index updated !");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(os);
    }
  }

  @Override
  public Map<String, String> searchKbaseSolr(SearchSolrParams params, RpcContext... jsonRpcContext)
      throws IOException, JsonClientException {
    if (cacheData == null) {
      buildCacheIndex();
    }
//    cacheData.put("aliases:(ECW_m3682)", EXAMPLE);
    //    System.out.println(params.getSearchQuery());
    System.out.println(params.getSearchParam());
    String q = params.getSearchQuery().get("q");

    Map<String, String> result = new HashMap<> ();

    if (!cacheData.containsKey(q)) {
//      result.put("solr_search_result", EMPTY_RESPONSE);
      params.getSearchParam().put("fl", "*");
      logger.info("query {} not found. live search ...", q);
      result = super.searchKbaseSolr(params, jsonRpcContext);
      String rdata = result.get("solr_search_result");
      saveCache(q, rdata);
    } else {
//      
      logger.info("query {} found. get cache ...", q);
      result.put("solr_search_result", getCacheData(q));
    }

    //    result.put("solr_search_result", "{\"response\":{}}");

    return result;
  }
}
