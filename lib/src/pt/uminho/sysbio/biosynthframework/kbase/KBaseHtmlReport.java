package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KBaseHtmlReport {
  
  public static String LOCAL_CACHE = "./cache";
  public static String TEMPLATE_FOLDER = "/opt/nginx-1.9.6/html/biosynth-web-biobase/exports";
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseHtmlReport.class);
  
  public static List<File> makeStaticReport() {
    List<File> files = new ArrayList<> ();
    
//    String uuid = UUID.randomUUID().toString();
//    File file = new File(String.format("%s/%s", LOCAL_CACHE, uuid));
//    if (file.mkdirs()) {
//      logger.info("created {}", file.getAbsolutePath());
//    }
//    
//    String f = String.format("%s/%s", TEMPLATE_FOLDER, "kbase-example.zip");
//    List<Map<String, Object>> streams = BIOUtils.readZipFiles(f);
//    for (Map<String, Object> s : streams) {
//      System.out.println(s);
//      OutputStream os = null;
//      InputStream is = null;
//      try {
//        File ofile = new File(String.format("%s/%s", file.getAbsolutePath(), s.get("name")));
//        os = new FileOutputStream(ofile);
//        is = (InputStream) s.get("is");
//        logger.info("copy {}", ofile.getAbsolutePath());
//        IOUtils.copy(is, os);
//        files.add(ofile);
//      } catch (IOException e) {
//        e.printStackTrace();
//      } finally {
//        IOUtils.closeQuietly(is);
//        IOUtils.closeQuietly(os);
//      }
//    }
    
    return files;
  }
  
  public static List<File> makeStaticReport(String fname, String data) {
    List<File> files = new ArrayList<> ();

    String uuid = UUID.randomUUID().toString();
    File file = new File(String.format("%s/%s", LOCAL_CACHE, uuid));
    if (file.mkdirs()) {
      logger.info("created {}", file.getAbsolutePath());
    }

    //    String f = String.format("%s/%s", TEMPLATE_FOLDER, "kbase-example.zip");
    //    List<Map<String, Object>> streams = BIOUtils.readZipFiles(f);
    //    for (Map<String, Object> s : streams) {
    //      System.out.println(s);
    OutputStream os = null;
    InputStream is = null;
    try {
      File ofile = new File(String.format("%s/%s", file.getAbsolutePath(), fname));
      os = new FileOutputStream(ofile);
      is = new ByteArrayInputStream(data.getBytes());
      logger.info("copy {}", ofile.getAbsolutePath());
      IOUtils.copy(is, os);
      files.add(ofile);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
      IOUtils.closeQuietly(os);
    }
    //    }

    return files;
  }
}
