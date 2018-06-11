package pt.uminho.sysbio.biosynthframework.integration;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pt.uminho.sysbio.biosynthframework.io.kbase.KBaseService;

public class KBaseSearchApiGenomeIntegration implements GenomeIntegration {

  private final KBaseService service;
  
  public KBaseSearchApiGenomeIntegration(KBaseService service) {
    this.service = service;
    this.service.threads = 40;
  }
  
  @Override
  public GenomeIntegrationResult matchGenome(Set<String> genes) {
    Map<String, Map<String, String>> result = service.searchReferenceGenomeByMultipleGeneAlias(
        new HashSet<>(genes));
    for (String g : genes) {
      System.out.println(g + " " + result.get(g));
    }
    return null;
  }

}
