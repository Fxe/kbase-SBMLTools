package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import datafileutil.DataFileUtilClient;
import datafileutil.ObjectSaveData;
import datafileutil.SaveObjectsParams;
import sbmltools.KBaseType;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;

public class KBaseIOUtils {

  public static class KBaseObject {
    public String nameId;
    public KBaseType dataType;
    public Object o;
  }

  public static String getRefFromObjectInfo(Tuple11<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> info) {
    if (info != null) {
      return info.getE7() + "/" + info.getE1() + "/" + info.getE5();
    }

    return null;
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

  public static List<String> saveData(List<KBaseObject> objects, String ws, final DataFileUtilClient dfuClient) throws Exception {
    long wsId = dfuClient.wsNameToId(ws);

    List<ObjectSaveData> data = new ArrayList<> ();
    for (KBaseObject o : objects) {
      data.add(new ObjectSaveData().withName(o.nameId)
          .withType(o.dataType.toString())
          .withData(new UObject(o.o)));
    }

    SaveObjectsParams params = new SaveObjectsParams()
        .withId(wsId)
        .withObjects(data);

    List<String> references = new ArrayList<> ();
    for (Tuple11<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> t : dfuClient.saveObjects(params)) {
      String ref = getRefFromObjectInfo(t);
      references.add(ref);
    }

    return references;
  }

  public static String toJson(Object o) {
    ObjectMapper om = new ObjectMapper();
    om.enable(SerializationFeature.INDENT_OUTPUT);
    String json = null;
    try {
      json = om.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return json;
  }
  
  public static String getDataWeb(String urlStr) {
    URLConnection urlConnection = null;
    InputStream is = null;
    OutputStream os = null;
    String out = null;
    
    try {
      URL url = new URL(urlStr);
      urlConnection = url.openConnection();
      is = urlConnection.getInputStream();
      os = new ByteArrayOutputStream();
      IOUtils.copy(is, os);
      out = os.toString();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.close(urlConnection);
      IOUtils.closeQuietly(is);
      IOUtils.closeQuietly(os);
    }

    return out;
  }
}
