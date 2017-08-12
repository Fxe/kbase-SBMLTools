package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import datafileutil.DataFileUtilClient;
import datafileutil.FileToShockOutput;
import datafileutil.FileToShockParams;
import datafileutil.ObjectSaveData;
import datafileutil.SaveObjectsParams;
import kbasefba.FBAModel;
import sbmltools.KBaseType;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.workspace.GetObjects2Params;
import us.kbase.workspace.GetObjects2Results;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectSpecification;
import us.kbase.workspace.WorkspaceClient;

public class KBaseIOUtils {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseIOUtils.class);

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
  
  /**
   * 
   * @param nameId
   * @param dataType
   * @param o
   * @param ws
   * @param dfuClient
   * @return object ref
   */
  public static String saveDataSafe(String nameId, KBaseType dataType, Object o, String ws, final DataFileUtilClient dfuClient) {
    return saveDataSafe(nameId, dataType.value(), o, ws, dfuClient);
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
  
  public static void copy(String src, String dst) {
    File fsrc = new File(src);
    File fdst = new File(dst);
    
    InputStream is = null;
    OutputStream os = null;
    try {
      is = new FileInputStream(fsrc);
      if (fdst.isDirectory()) {
        os = new FileOutputStream(fdst.getAbsolutePath() + "/" + fsrc.getName());
      } else if (!fdst.exists()) {
        os = new FileOutputStream(fdst);
      }
      
      IOUtils.copy(is, os);
      
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
      IOUtils.closeQuietly(os);
    }
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
    logger.info("load web url [{}] data ...", urlStr);
    
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
  
  public static Object getFBAModel(String name, String ws, String ref, 
                                   WorkspaceClient wsClient) throws IOException {
    try {
      List<ObjectSpecification> objects = new ArrayList<> ();
      ObjectSpecification ospec = new ObjectSpecification();
      if (name != null) {
        ospec.withName(name);
      }
      if (ws != null) {
        ospec.withWorkspace(ws);
      }
      if (ref != null) {
        ospec.withRef(ref);
      }
      
      objects.add(ospec);
      
      GetObjects2Params params = new GetObjects2Params().withObjects(objects);
      GetObjects2Results result = wsClient.getObjects2(params);
      List<ObjectData> odata = result.getData();
      Object o = odata.iterator().next().getData().asInstance();
      ObjectMapper om = new ObjectMapper();
      FBAModel fbaModel = om.convertValue(o, FBAModel.class);
//      om.readValue(src, FBAModel.class);
      System.out.println(o.getClass().getSimpleName());
    } catch (IOException | JsonClientException e) {
      throw new IOException(e);
    }
    return null;
  }
  
  public static Object getObject(String name, String ws, String ref, 
      WorkspaceClient wsClient) throws IOException {
    try {
      List<ObjectSpecification> objects = new ArrayList<> ();
      ObjectSpecification ospec = new ObjectSpecification();
      if (name != null) {
        ospec.withName(name);
      }
      if (ws != null) {
        ospec.withWorkspace(ws);
      }
      if (ref != null) {
        ospec.withRef(ref);
      }

      objects.add(ospec);

      GetObjects2Params params = new GetObjects2Params().withObjects(objects);
      GetObjects2Results result = wsClient.getObjects2(params);
      List<ObjectData> odata = result.getData();
      Object o = odata.iterator().next().getData().asInstance();
      return o;
    } catch (IOException | JsonClientException e) {
      throw new IOException(e);
    }
  }
  
  public static<T> T getObject(String name, String ws, String ref, Class<T> clazz,
      WorkspaceClient wsClient) throws IOException {
    Object o = getObject(name, ws, ref, wsClient);
    ObjectMapper om = new ObjectMapper();
    T fbaModel = om.convertValue(o, clazz);
    return fbaModel;
  }
  
  public static String folderToShock(String path, final DataFileUtilClient dfuClient) throws IOException {
    FileToShockParams params = new FileToShockParams()
        .withFilePath(path)
        .withMakeHandle(0L)
        .withPack("zip");
    try {
      FileToShockOutput output = dfuClient.fileToShock(params);
      System.out.println(output);
      
      return output.getShockId();
    } catch (IOException | JsonClientException e) {
      throw new IOException(e);
    }
  }
}
