package pt.uminho.sysbio.biosynthframework.integration;

import java.util.Set;

public interface GenomeIntegration {
  public GenomeIntegrationResult matchGenome(Set<String> genes);
}
