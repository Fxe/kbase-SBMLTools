package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import datafileutil.DataFileUtilClient;
import kbasefba.FBAModel;
import kbasefba.ModelReaction;
import kbasegenomes.Feature;
import kbasegenomes.Genome;
import kbasegenomes.OntologyData;
import kbsolrutil.KBSolrUtilClient;
import kbsolrutil.SearchSolrParams;
import me.fxe.kbase.KBaseGenomeAdapter;
import pt.uminho.sysbio.biosynthframework.BFunction;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;
import sbmltools.KBaseType;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.WorkspaceClient;

public class KBaseGeneIntegration {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseGeneIntegration.class);
  
  private final KBSolrUtilClient solrClient;
  private final WorkspaceClient wspClient;
  private final DataFileUtilClient dfuClient;
  public KBaseGenomeReport report;
  public BFunction<String, String> geneTransformer = null;
  
  public KBaseGeneIntegration(WorkspaceClient wspClient, DataFileUtilClient dfuClient, KBSolrUtilClient solrClient) {
    this.solrClient  = solrClient;
    this.wspClient = wspClient;
    this.dfuClient = dfuClient;
  }
  
  public Map<String, Set<KBaseSolrDocument>> processSolrOutput(String ojson, 
      Map<String, Set<KBaseSolrDocument>> galiasIndex) throws IOException {

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
  
  public Set<String> getReactions(String function) {
    return null;
  }
  
  
  
  public String process(Set<String> genes, Map<String, Set<KBaseSolrDocument>> galiasIndex) {
    
    logger.info("process !");
    KBaseGenomeReport report = new KBaseGenomeReport();
    this.report = report;
    Map<String, String> genomeWorkspace = new HashMap<> ();
    Map<String, Set<String>> orgGenomeIds = new HashMap<> ();
    Map<String, Integer> organismGeneHits = new HashMap<> ();
    for (String gene : genes) {
      Set<KBaseSolrDocument> docs = galiasIndex.get(gene);
      Set<String> organism = new HashSet<> ();
//      System.out.println(gene);
      if (docs != null && !docs.isEmpty()) {
        for (KBaseSolrDocument doc : docs) {
//          System.out.println(doc.workspace_name);
//          System.out.println(doc.genome_id);
          organism.add(doc.scientific_name);
//          System.out.println("\t" + doc.scientific_name + "\t" + doc.functions);
          if (!orgGenomeIds.containsKey(doc.scientific_name)) {
            orgGenomeIds.put(doc.scientific_name, new HashSet<String> ());
          }
          if (doc.genome_id != null) {
            orgGenomeIds.get(doc.scientific_name).add(doc.genome_id);
            if (!genomeWorkspace.containsKey(doc.genome_id)) {
              genomeWorkspace.put(doc.genome_id, doc.workspace_name);
            }
          }
        }
      }
      for (String org : organism) {
        CollectionUtils.increaseCount(organismGeneHits, org, 1);
      }
    }
    //%organism cover
    //%
    logger.info("found {}", orgGenomeIds);
    
    String wut = "";
    
    Map<String, Set<String>> best = new HashMap<> ();
    double bestScore = 0.0;
    for (String org : organismGeneHits.keySet()) {
      double total = genes.size();
      double coverage = organismGeneHits.get(org) / total;
      if (coverage > bestScore) {
        best.clear();
        bestScore = coverage;
      }
      if (coverage == bestScore) {
        if (!best.containsKey(org)) {
          best.put(org, new HashSet<String> ());
        }
        best.get(org).addAll(orgGenomeIds.get(org));
        report.bestMatch = org;
        report.bestScore = coverage;
      }
      
      wut += org + " " + coverage + "\n";
      for (String genomeId : orgGenomeIds.get(org)) {
        report.addMatchGenome(genomeId, org, coverage);
      }
//      System.out.println(org + " " + organismGeneHits.get(org) / total);
    }
    
    for (String bestOrg : best.keySet()) {
      Set<String> genomes = best.get(bestOrg);
      for (String genome : genomes) {
        
      }
    }
    
    Set<String> nomatch = new HashSet<> (genes);
    Set<String> mappedFeatures = new HashSet<> ();
    Map<String, Set<String>> ftoreactions = new HashMap<> ();
    Set<String> unmappedFeatures = new HashSet<> ();
    for (String g : genes) {
      report.mgenes.put(g, null);
    }
    for (String org : best.keySet()) {
      for (String genome : best.get(org)) {
        String wsName = genomeWorkspace.get(genome);
        
        logger.info("[BEST HIT] Genome: {} @ {}", genome, wsName);
        try {
          logger.info("loading Genome: {} {}", genome, wsName);
          Genome g = KBaseIOUtils.getGenome(genome, wsName, null, wspClient);
          for (Feature f : g.getFeatures()) {
            //ontology_terms should not be null
            if (f.getOntologyTerms() == null) {
              f.setOntologyTerms(new HashMap<String, Map<String, Object>>());
//              f.setOntologyTerms(new HashMap<String, Map<String, List<Object>>>());
            }
          }
//          String targetWs = "filipeliu:narrative_1502913563238";
//          List<ObjectSaveData> odata = new ArrayList<> ();
//          SaveObjectsParams soparams = new SaveObjectsParams().withWorkspace(targetWs).withObjects(odata);
//          odata.add(new ObjectSaveData().withData(new UObject(g)).withName(genome).withType(KBaseType.Genome.value()));
//          try {
//            for (Tuple11<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> t : wspClient.saveObjects(soparams)) {
//              String gref = KBaseIOUtils.getRefFromObjectInfo(t);
//              System.out.println("saved: " + gref);
//              g.kid = new KBaseId(genome, wsName, gref);
//            }
//
//          } catch (JsonClientException e) {
//            e.printStackTrace();
//          }
//          String gref = KBaseIOUtils.saveDataSafe(genome, KBaseType.Genome, g, targetWs, dfuClient);

          report.bestGenomeKID.add(g.getId());
//          System.out.println("ID: " + g.id);
//          System.out.println("ID: " + g.);1
          report.features = g.getFeatures().size();
          for (Feature f : g.getFeatures()) {
            report.geneFunction.put(f.getId(), f.getFunction());
            Set<String> faliases = new HashSet<String> ();
            for (List<String> aliases : f.getAliases()) {
              faliases.add(KBaseGenomeAdapter.getAlias(aliases));
//              f.getAliases()
            }
//            System.out.println(f);
            
            faliases.add(f.getId());
            Set<String> fgene = Sets.intersection(faliases, genes);
            nomatch.removeAll(fgene);
            //should warning if fgenes size > 1
            if (!fgene.isEmpty()) {
              report.mapGeneToFeature(fgene, f.getId());
              report.mgenesMapped.addAll(fgene);
              mappedFeatures.add(f.getId());
//              System.out.println(fgene + " " + f);
            } else {
              unmappedFeatures.add(f.getId());
            }
            //ftoreactions update
            Set<String> frxn = getReactions(f.getFunction());
            ftoreactions.put(f.getId(), frxn);
          }
          
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
//    
    return wut; 
  }

  public String searchGenome(Set<String> genes) {
    String tempResult = "";
    
    Map<String, Set<KBaseSolrDocument>> galiasIndex = new HashMap<> ();
    if (solrClient != null) {
      List<List<String>> chunks = Lists.partition(new ArrayList<String> (genes), 200);
      for (List<String> chunk : chunks) {
        String query = String.format("aliases:(%s)", StringUtils.join(chunk, " OR "));
        logger.debug("Query: {}", query);
        tempResult += query + "\n";

        Map<String, String> searchQuery = new HashMap<> ();
        searchQuery.put("q", query);
        Map<String, String> searchParam = new HashMap<> ();
        searchParam.put("fl", "aliases,scientific_name,functions,workspace_name,genome_id");
        searchParam.put("start", "0");
        searchParam.put("rows", "5000");

        SearchSolrParams sparams = new SearchSolrParams()
            .withSearchCore("GenomeFeatures_prod")
            .withResultFormat("json")
            .withSearchQuery(searchQuery)
            .withSearchParam(searchParam)
            .withGroupOption("");

        Map<String, String> solrClientOutput;
        try {
          solrClientOutput = solrClient.searchKbaseSolr(sparams);
//          System.out.println(solrClientOutput.keySet()); //[solr_search_result]
          String ojson = solrClientOutput.get("solr_search_result");
          this.processSolrOutput(ojson, galiasIndex);
          
        } catch (IOException | JsonClientException e) {
          tempResult += e.getMessage() + "\n";
          e.printStackTrace();
        }
      }
      //frole cover
      //f roles -> 
      //non role but in model
      //missing reactions
      tempResult += process(genes, galiasIndex);
    }
    
    return tempResult;
  }
  
  public String searchGenome(FBAModelAdapter kmodel) {
    Set<String> genes = new TreeSet<> ();
    
    for (ModelReaction krxn : kmodel.fbaModel.getModelreactions()) {
      Set<String> grp = kmodel.getGenes(krxn.getId());
      if (grp != null && !grp.isEmpty()) {
        genes.addAll(grp);
      }
    }

    return searchGenome(genes);
  }
  
  public String searchGenome(FBAModel kmodel, boolean allowNumberLocus) {
    Set<String> genes = new TreeSet<> ();
    
    for (ModelReaction krxn : kmodel.getModelreactions()) {
      String gpr = krxn.getImportedGpr();
      Set<String> grp = KBaseUtils.getGenes(gpr);
      if (grp != null && !grp.isEmpty()) {
        Set<String> validLocus = new HashSet<> ();
        for (String g : grp) {
          if (!NumberUtils.isNumber(g.trim()) || allowNumberLocus) {
            if (geneTransformer != null) {
              g = geneTransformer.apply(g);
            }
            if (g != null && !g.trim().isEmpty()) {
              validLocus.add(g);
            }
          } else {
            logger.warn("[IGNORE NUMBER LOCUS] gene: {}", g);
          }
        }
        genes.addAll(validLocus);
      }
    }

    return searchGenome(genes);
  }
}
