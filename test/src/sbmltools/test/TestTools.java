package sbmltools.test;

import java.io.IOException;

import kbasegenomes.Genome;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;

public class TestTools {
  
  public static void loadZipGenome(String path) {
    try {
      Genome genome = KBaseIOUtils.loadJsonGenomeFromZip(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    loadZipGenome("/var/biomodels/kbase/PlantSEED_Cre.rast.json.zip");
  }
}
