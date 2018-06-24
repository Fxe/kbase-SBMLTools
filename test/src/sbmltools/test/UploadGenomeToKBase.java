package sbmltools.test;

import java.io.IOException;

import genomeannotationapi.SaveOneGenomeParamsV1;
import kbasegenomes.Genome;
import kbsolrutil.KBaseAPI;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import us.kbase.common.service.JsonClientException;

public class UploadGenomeToKBase {
  // IT IS IMPOSSIBLE !! :D
  public static void main(String[] args) {
    String token = args[0];
    String file = args[1];
    String name = args[2];
    String ws = args[3];
    String method = args[4];
//    try {
//      Genome genome = KBaseIOUtils.loadJsonGenomeFromZip(file);
//      KBaseAPI api = new KBaseAPI(token, KBaseAPI.getConfigProd(), false);
//      if (method == "client") {
//        SaveOneGenomeParamsV1 gparams = new SaveOneGenomeParamsV1()
//            .withData(genome)
//            .withWorkspace(ws)
//            .withName(name);
//        api.getGenomeClient().saveOneGenomeV1(gparams);
//      } else {
//        api.saveGenome(name, ws, genome);
//      }
//      
//
//    } catch (IOException | JsonClientException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
  }
}
