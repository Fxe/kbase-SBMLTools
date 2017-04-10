package sbmltools.test;

import sbmltools.SbmlImportParams;
import sbmltools.SbmlTools;

public class ImportSbml {
  public static void main(String[] args) {
    SbmlTools tools = new SbmlTools(null, null, null, null);
    SbmlImportParams params = new SbmlImportParams();
    params.setAssemblyInputRef("does not matter");
    params.setMinLength(0L);
    String urlString = "https://raw.githubusercontent.com/Fxe/biomodels/master/iBROKEN.xml";
    urlString = "http://193.137.11.210/models/biomodels/iBROKEN.xml";
    params.setUrl(urlString);
    
    try {
//      URL url = new URL(urlString);
//      URLConnection connection = url.openConnection();
      String res = tools.importModel(params);
      System.out.println(res);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
