package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbasefba.FBAModel;
import kbasefba.ModelReaction;
import kbsolrutil.KBSolrUtilClient;
import kbsolrutil.SearchSolrParams;
import us.kbase.common.service.JsonClientException;

public class KBaseGeneIntegration {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseGeneIntegration.class);
  
  private final KBSolrUtilClient solrClient;
  
  public KBaseGeneIntegration(KBSolrUtilClient solrClient) {
    this.solrClient  = solrClient;
  }
  
  public void aaa(FBAModel kmodel) {
    
    Set<String> genes = new HashSet<> ();
    
    for (ModelReaction krxn : kmodel.getModelreactions()) {
      String gpr = krxn.getImportedGpr();
      Set<String> grp = KBaseUtils.getGenes(gpr);
      if (gpr != null && !gpr.isEmpty()) {
        System.out.println(gpr);
        genes.addAll(grp);
      }
    }
    
    logger.info("SOLR");
    String query = String.format("aliases:(%s)", StringUtils.join(genes, " OR "));
    
    logger.info("Query: {}", query);
    
    Map<String, String> searchQuery = new HashMap<> ();
    searchQuery.put("q", query);
    Map<String, String> searchParam = new HashMap<> ();
    searchParam.put("fl", "*");
    searchParam.put("start", "0");
    searchParam.put("rows", "10");
    

    SearchSolrParams sparams = new SearchSolrParams()
        .withSearchCore("GenomeFeatures_prod")
        .withResultFormat("json")
        .withSearchQuery(searchQuery)
        .withSearchParam(searchParam)
        .withGroupOption("");
    
    if (solrClient != null) {
      Map<String, String> a;
      try {
        a = solrClient.searchKbaseSolr(sparams);
        System.out.println(a);
      } catch (IOException | JsonClientException e) {
        e.printStackTrace();
      }
    }

    
    
    logger.info("SOLDONE !");
  }
}
