package sbmltools.test;

import java.io.IOException;

import kbasegenomes.Genome;
import kbsolrutil.KBaseAPI;

public class TestCache {
  public static void main(String[] args) {
    try {
      KBaseAPI api = new KBaseAPI("MDYG23GDVXXX463IR5NZLDB5TYPMWMDY", KBaseAPI.getConfigProd(), true);
      Genome genome = api.getGenome("GCF_000005845.2.RAST", IntegrationLocalRun.PROD_MS_2_0);
      System.out.println(genome.getFeatures().size());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
