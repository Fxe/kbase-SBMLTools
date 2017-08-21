package pt.uminho.sysbio.biosynthframework.kbase;

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

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sbmltools.test.CacheEngine.CacheFieldType;

public class CacheIndex {
  
  private static final Logger logger = LoggerFactory.getLogger(CacheIndex.class);
  
  public final Map<String, Map<Pair<String, String>, Map<CacheFieldType, String>>> cacheIndex = null;
  public File cacheIndexFile = new File("/var/argonne/kbase_object_cache/index.txt");
  
  public CacheIndex(String cacheIndexFile) {
    // TODO Auto-generated constructor stub
  }
  
  public void buildCacheIndex(File cacheIndexFile, final Map<String, Map<Pair<String, String>, Map<CacheFieldType, String>>> cacheIndex) {
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
          Pair<String, String> p = new ImmutablePair<String, String>(id, ws);
          Map<CacheFieldType, String> cdata = new HashMap<> ();
          cdata.put(CacheFieldType.FILENAME, file);
          cdata.put(CacheFieldType.VERSION, version);
          cdata.put(CacheFieldType.OTYPE, otype);
          cdata.put(CacheFieldType.AUTHOR, author);
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
  
  public synchronized void updateIndex() {
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
