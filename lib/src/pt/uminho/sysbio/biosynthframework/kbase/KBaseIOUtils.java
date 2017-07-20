package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.Arrays;
import java.util.Map;

import datafileutil.DataFileUtilClient;
import datafileutil.ObjectSaveData;
import datafileutil.SaveObjectsParams;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;

public class KBaseIOUtils {
  
  public static String getRefFromObjectInfo(Tuple11<Long, String, String, String, 
      Long, String, Long, String, String, Long, Map<String,String>> info) {
    return info.getE7() + "/" + info.getE1() + "/" + info.getE5();
  }
  
  public static String saveDataSafe(String nameId, String dataType, Object o, String ws, final DataFileUtilClient dfuClient) {
    String ref = null;
    if (dfuClient != null) {
      try {
        ref = saveData(nameId, dataType, o, ws, dfuClient);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return ref;
  }

  public static String saveData(String nameId, String dataType, Object o, String ws, final DataFileUtilClient dfuClient) throws Exception {
    long wsId = dfuClient.wsNameToId(ws);

    SaveObjectsParams params = new SaveObjectsParams()
        .withId(wsId)
        .withObjects(Arrays.asList(
            new ObjectSaveData().withName(nameId)
            .withType(dataType)
            .withData(new UObject(o))));
    ////  params.setId(wsId);
    ////  List<ObjectSaveData> saveData = new ArrayList<> ();
    ////  ObjectSaveData odata = new ObjectSaveData();
    ////  odata.set
    ////  
    ////  params.setObjects(saveData);
    ////  ;
    String ref = getRefFromObjectInfo(dfuClient.saveObjects(params).get(0));

    return ref;
  }
}
