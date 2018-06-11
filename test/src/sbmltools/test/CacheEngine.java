package sbmltools.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import sbmltools.KBaseType;
import us.kbase.common.service.Tuple11;

public class CacheEngine {
  
  private static final Logger logger = LoggerFactory.getLogger(CacheEngine.class);
  
  public enum CacheFieldType {
    FILENAME,
    VERSION,
    OTYPE,
    AUTHOR,
    REFERENCE,
  }
  
  public final String version;
  
  @Deprecated
  public CacheEngine(String version) {
    this.cacheIndex = null;
    this.version = version;
  }
  
  public CacheEngine(Map<String, Map<Pair<String, String>, Map<CacheFieldType, String>>> cacheIndex, String version) {
    this.cacheIndex = cacheIndex;
    this.version = version;
  }
  
//  public Map<Pair<String, String>, Map<CacheFieldType, String>> cacheIndexMap = null;
//  public Map<Pair<String, String>, Map<CacheFieldType, String>> cacheIndexMapOthers = null;
  public final Map<String, Map<Pair<String, String>, Map<CacheFieldType, String>>> cacheIndex;
  public static File cacheIndexFile = new File("/var/argonne/kbase_object_cache/index.txt");
  public static String base = "/var/argonne/kcache";
  
  public void status() {
    logger.info("{} cache index loaded. records {}, total {}", 
        this.version, this.cacheIndex.get(version).size(), this.cacheIndex.size());
  }
  
  public static void buildCacheIndex(File cacheIndexFile, final Map<String, Map<Pair<String, String>, Map<CacheFieldType, String>>> cacheIndex) {
    if (!cacheIndex.isEmpty()) {
      return;
    }
    logger.info("build cache index..");
    cacheIndex.put("appdev", new HashMap<Pair<String, String>, Map<CacheFieldType, String>>());
    cacheIndex.put("prod", new HashMap<Pair<String, String>, Map<CacheFieldType, String>>());
    if (cacheIndexFile.exists() && cacheIndexFile.isFile()) {
      InputStream is = null;
      try {
        is = new FileInputStream(cacheIndexFile);
        for (String l : IOUtils.readLines(is)) {
          String data[] = l.split("\t");
          String id = data[0];
          String ws = data[1];
          String version = data[2];
          String file = data[3];
          String otype = data[4];
          String author = data[5];
          String ref = data[6];
          Pair<String, String> p = new ImmutablePair<String, String>(id, ws);
          Map<CacheFieldType, String> cdata = new HashMap<> ();
          cdata.put(CacheFieldType.FILENAME, file);
          cdata.put(CacheFieldType.VERSION, version);
          cdata.put(CacheFieldType.OTYPE, otype);
          cdata.put(CacheFieldType.AUTHOR, author);
          cdata.put(CacheFieldType.REFERENCE, ref);
          if (!cacheIndex.containsKey(version)) {
            cacheIndex.put(version, new HashMap<Pair<String, String>, Map<CacheFieldType, String>>());
          }
          cacheIndex.get(version).put(p, cdata);

        }
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(is);
      }
    }
  }
  
  public boolean isCached(String id, String ws, KBaseType ktype) {
    File folder = new File(String.format("%s/%s/%s", base, version, StringUtils.replaceChars(ws, ':', '_')));
    File jfile = new File(String.format("%s/%s.json", folder.getAbsolutePath(), id));
    
    return jfile.exists();
  }
  
  public boolean isCached(String id, String ws) {
    String path = String.format("%s/%s/%s/%s.json.zip", base, version, StringUtils.replaceChars(ws, ':', '_'), id);
    File cacheFile = new File(path);
    logger.debug("cache lookup: [E:{}, F:{}] {}", cacheFile.exists(), cacheFile.isFile(), path);
//    Pair<String, String> p = new ImmutablePair<String, String>(id, ws);
//    return this.cacheIndex.get(version).containsKey(p);
    if (cacheFile.exists() && cacheFile.isFile()) {
      logger.debug("found compress file: {}", cacheFile.getAbsolutePath());
      return true;
    }
    
    path = String.format("%s/%s/%s/%s.json", base, version, StringUtils.replaceChars(ws, ':', '_'), id);
    cacheFile = new File(path);
    
    return cacheFile.exists() && cacheFile.isFile();
  }
  
  public String getObjectType(String id, String ws) {
    if (isCached(id, ws)) {
      Pair<String, String> p = new ImmutablePair<String, String>(id, ws);
      return this.cacheIndex.get(version).get(p).get(CacheFieldType.OTYPE);
    }
    return null;
  }
  
  public Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>> getInfo(String id, String ws) {
    Pair<String, String> p = new ImmutablePair<String, String>(id, ws);
    Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>> info = new Tuple11<>();
    Map<CacheFieldType, String> data = cacheIndex.get(version).get(p);
    data.get(CacheFieldType.OTYPE);
    String[] f715 = data.get(CacheFieldType.REFERENCE).split("/");
    info.setE7(Long.parseLong(f715[0]));
    info.setE1(Long.parseLong(f715[1]));
    info.setE5(Long.parseLong(f715[2]));
    return info;
  }
  
  public<T> T getCacheData(String id, String ws, Class<T> clazz) {
    String jsonStr = this.getJson(id, ws);
    ObjectMapper om = new ObjectMapper();
    T object = null;
    
    try {
      object = om.readValue(jsonStr, clazz);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return object;
  }
  
  public Object getCacheData(String id, String ws) {
    String jsonStr = this.getJson(id, ws);
    ObjectMapper om = new ObjectMapper();
    Object object = null;
    
    try {
      object = om.readValue(jsonStr, Object.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return object;
  }
  
  public String getJson(File f) {
    String json = null;
    if (f.exists() && f.isFile()) {
      logger.debug("read {}", f.getAbsolutePath());
      InputStream is = null;
      try {
        is = new FileInputStream(f);
        List<String> data = IOUtils.readLines(is);
        json = StringUtils.join(data, "");
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(is);
      }
    }
    
    return json;
  }
  
  public String getJson(String id, String ws) {
    String response = null;
    
    File folder = new File(String.format("%s/%s/%s", base, version, StringUtils.replaceChars(ws, ':', '_')));
    File jfile = new File(String.format("%s/%s.json", folder.getAbsolutePath(), id));
    if (jfile.exists()) {
      logger.debug("read JSON {}", jfile.getAbsolutePath());
      response = getJson(jfile);
    } else if (new File(String.format("%s/%s.json.zip", folder.getAbsolutePath(), id)).exists()) {
      jfile = new File(String.format("%s/%s.json.zip", folder.getAbsolutePath(), id));
      logger.debug("read ZIP {}", jfile.getAbsolutePath());
      try {
        response = new String(KBaseIOUtils.loadJsonFromZip(jfile.getAbsolutePath()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
//      response = null;
//      Pair<String, String> p = new ImmutablePair<String, String>(id, ws);
//      if (cacheIndex.get(version).containsKey(p)) {
//        File f = new File(cacheIndexFile.getParent() + "/" + 
//            cacheIndex.get(version).get(p).get(CacheFieldType.FILENAME));
//        InputStream is = null;
//        try {
//          is = new FileInputStream(f);
//          List<String> data = IOUtils.readLines(is);
//          response = StringUtils.join(data, "");
//        } catch (IOException e) {
//          e.printStackTrace();
//        } finally {
//          IOUtils.closeQuietly(is);
//        }
//      }
    }
    
    return response;
  }
  
  public synchronized void save(String id, String ws, String ref, String type, String author, String data) {
    
    File folder = new File(String.format("%s/%s/%s", base, version, StringUtils.replaceChars(ws, ':', '_')));
    if (!folder.exists()) {
      logger.warn("path not found creating ... [{}]", folder.getAbsolutePath());
      folder.mkdirs();
    }
    File jfile = new File(String.format("%s/%s.json", folder.getAbsolutePath(), id));
    {
      OutputStream os = null;
      try {
        os = new FileOutputStream(jfile);
        IOUtils.write(data, os);
        logger.info("saving {} bytes", data.length());
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(os);
      }
    }
    
    String uuid = UUID.randomUUID().toString();
    {
      OutputStream os = null;
      try {
        File f = new File(cacheIndexFile.getParent() + "/" + uuid + ".json");
        os = new FileOutputStream(f);
        IOUtils.write(data, os);
        Map<CacheFieldType, String> cdata = new HashMap<> ();
        cdata.put(CacheFieldType.FILENAME, f.getName());
        cdata.put(CacheFieldType.VERSION, version);
        cdata.put(CacheFieldType.OTYPE, type);
        cdata.put(CacheFieldType.AUTHOR, author);
        cdata.put(CacheFieldType.REFERENCE, ref);
        this.cacheIndex.get(version).put(new ImmutablePair<String, String>(id, ws), cdata);
  
        logger.info("object cached!");
        updateIndex();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(os);
      }
    }
//  KBaseIOUtils.writeStringFile(KBaseIOUtils.toJson(g), 
//  "/var/argonne/kbase_object_cache/" + UUID.randomUUID().toString() + ".json");
  }
  
  public void updateIndex() {
    OutputStream os = null;
    try {
      os = new FileOutputStream(cacheIndexFile);
      StringBuilder sb = new StringBuilder();
      
      for (String v : cacheIndex.keySet()) {
        for (Pair<String, String> k : cacheIndex.get(v).keySet()) {
          List<Object> record = new ArrayList<> ();
          record.add(k.getLeft());
          record.add(k.getRight());
          Map<CacheFieldType, String> cdata = cacheIndex.get(v).get(k);
          record.add(cdata.get(CacheFieldType.VERSION));
          record.add(cdata.get(CacheFieldType.FILENAME));
          record.add(cdata.get(CacheFieldType.OTYPE));
          record.add(cdata.get(CacheFieldType.AUTHOR));
          record.add(cdata.get(CacheFieldType.REFERENCE));
          sb.append(StringUtils.join(record, "\t")).append("\n");
        }
      }

      IOUtils.write(sb, os);
      logger.info("index updated !");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(os);
    }
  }
}
