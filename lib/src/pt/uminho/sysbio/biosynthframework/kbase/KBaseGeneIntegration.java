package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kbasefba.FBAModel;
import kbasefba.ModelReaction;
import kbsolrutil.KBSolrUtilClient;
import kbsolrutil.SearchSolrParams;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;
import us.kbase.common.service.JsonClientException;

public class KBaseGeneIntegration {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseGeneIntegration.class);
  
  private final KBSolrUtilClient solrClient;
  
  public KBaseGeneIntegration(KBSolrUtilClient solrClient) {
    this.solrClient  = solrClient;
  }
  
  public Map<String, Set<KBaseSolrDocument>> processSolrOutput(String ojson) throws IOException {
    Map<String, Set<KBaseSolrDocument>> galiasIndex = new HashMap<> ();
    ObjectMapper om = new ObjectMapper();
    KBaseSolrResponse solrResponse = om.readValue(ojson, KBaseSolrResponse.class);
    
    for (KBaseSolrDocument doc : solrResponse.response.docs) {
      for (String a : doc.aliases) {
        if (!galiasIndex.containsKey(a)) {
          galiasIndex.put(a, new HashSet<KBaseSolrDocument> ());
        }
        galiasIndex.get(a).add(doc);
      }
    }
    
    return galiasIndex;
  }
  
  public Object process(String[] genes, Map<String, Set<KBaseSolrDocument>> galiasIndex) {
    return process(new HashSet<> (Arrays.asList(genes)), galiasIndex);
  }
  
  public String process(Set<String> genes, Map<String, Set<KBaseSolrDocument>> galiasIndex) {
    Map<String, Set<String>> orgGenomeIds = new HashMap<> ();
    Map<String, Integer> organismGeneHits = new HashMap<> ();
    for (String gene : genes) {
      Set<KBaseSolrDocument> docs = galiasIndex.get(gene);
      Set<String> organism = new HashSet<> ();
//      System.out.println(gene);
      if (docs != null && !docs.isEmpty()) {
        for (KBaseSolrDocument doc : docs) {
          organism.add(doc.scientific_name);
//          System.out.println("\t" + doc.scientific_name + "\t" + doc.functions);
          if (!orgGenomeIds.containsKey(doc.scientific_name)) {
            orgGenomeIds.put(doc.scientific_name, new HashSet<String> ());
          }
          if (doc.genome_id != null) {
            orgGenomeIds.get(doc.scientific_name).add(doc.genome_id);
          }
        }
      }
      for (String org : organism) {
        CollectionUtils.increaseCount(organismGeneHits, org, 1);
      }
    }
    //%organism cover
    //%
    System.out.println(orgGenomeIds);
    String wut = "";
    for (String org : organismGeneHits.keySet()) {
      double total = genes.size();
      wut += org + " " + organismGeneHits.get(org) / total + "\n";
//      System.out.println(org + " " + organismGeneHits.get(org) / total);
    }
    
    return wut; 
  }

  public String aaa(FBAModel kmodel) {
    String tempResult = "";
    Set<String> genes = new HashSet<> ();
    
    for (ModelReaction krxn : kmodel.getModelreactions()) {
      String gpr = krxn.getImportedGpr();
      Set<String> grp = KBaseUtils.getGenes(gpr);
      if (gpr != null && !gpr.isEmpty()) {
        System.out.println(gpr);
        genes.addAll(grp);
      }
    }
    
    
    String query = String.format("aliases:(%s)", StringUtils.join(genes, " OR "));
    logger.debug("Query: {}", query);
    
    Map<String, String> searchQuery = new HashMap<> ();
    searchQuery.put("q", query);
    Map<String, String> searchParam = new HashMap<> ();
    searchParam.put("fl", "aliases,scientific_name,functions,workspace_name,genome_id");
    searchParam.put("start", "0");
    searchParam.put("rows", String.format("%d", genes.size()));
    

    SearchSolrParams sparams = new SearchSolrParams()
        .withSearchCore("GenomeFeatures_prod")
        .withResultFormat("json")
        .withSearchQuery(searchQuery)
        .withSearchParam(searchParam)
        .withGroupOption("");
    
    if (solrClient != null) {
      Map<String, String> solrClientOutput;
      try {
        solrClientOutput = solrClient.searchKbaseSolr(sparams);
        System.out.println(solrClientOutput.keySet());
        String ojson = solrClientOutput.get("solr_search_result");
        Map<String, Set<KBaseSolrDocument>> galiasIndex = this.processSolrOutput(ojson);
        tempResult += process(genes, galiasIndex);
        
      } catch (IOException | JsonClientException e) {
        e.printStackTrace();
      }
    }

    return tempResult;
  }
}
