package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KBaseHtmlReport {
  
  public String localCachePath = "./tmp/cache";
  public static String TEMPLATE_FOLDER = "/opt/nginx-1.9.6/html/biosynth-web-biobase/exports";
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseHtmlReport.class);
  
  public KBaseHtmlReport(Path scratch) {
    localCachePath = scratch.toFile().getAbsolutePath();
    logger.info("local cache {}", localCachePath);
  }
  
  public List<File> makeStaticReport() {
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
  
  public File makeStaticReport(String fname, String data) {
    File output = null;

    String uuid = UUID.randomUUID().toString();
    File file = new File(String.format("%s/%s", localCachePath, uuid));
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
      
      System.out.println(ofile.getParent());
      File pfolder = new File(ofile.getParent());
      if (pfolder.mkdirs()) {
        logger.info("created folder {}", pfolder.getAbsolutePath());
      }
      
      os = new FileOutputStream(ofile);
      is = new ByteArrayInputStream(data.getBytes());
      logger.info("copy {}", ofile.getAbsolutePath());
      IOUtils.copy(is, os);
      output = ofile;
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
      IOUtils.closeQuietly(os);
    }
    //    }

    return output;
  }
  
  public static class ReportFiles {
    public List<File> files = new ArrayList<>();
    public List<String> fileNames = new ArrayList<>();
    public List<String> filePaths = new ArrayList<>();
    public String path;
    public String uuid;
    public String baseFolder;
  }
  
  public ReportFiles makeStaticReport(Map<String, String> files) {
    ReportFiles reportFiles = new ReportFiles();

    String uuid = UUID.randomUUID().toString();
    File baseFolder = new File(String.format("%s/%s", localCachePath, uuid));
    if (baseFolder.mkdirs()) {
      logger.info("created {}", baseFolder.getAbsolutePath());
    }
    
    reportFiles.baseFolder = baseFolder.getAbsolutePath();
    //    String f = String.format("%s/%s", TEMPLATE_FOLDER, "kbase-example.zip");
    //    List<Map<String, Object>> streams = BIOUtils.readZipFiles(f);
    //    for (Map<String, Object> s : streams) {
    //      System.out.println(s);
    for (String fname : files.keySet()) {
//      String fname = paths.get(i);
      String data = files.get(fname);
      OutputStream os = null;
      InputStream is = null;
      try {
        File ofile = new File(
            String.format("%s/%s", baseFolder.getAbsolutePath(), fname));
        
        System.out.println(ofile.getParent());
        File pfolder = new File(ofile.getParent());
        if (pfolder.mkdirs()) {
          logger.info("created folder {}", pfolder.getAbsolutePath());
        }
        
        os = new FileOutputStream(ofile);
        is = new ByteArrayInputStream(data.getBytes());
        logger.info("copy {}", ofile.getAbsolutePath());
        IOUtils.copy(is, os);
        reportFiles.fileNames.add(ofile.getName());
        reportFiles.filePaths.add(fname);
        reportFiles.files.add(ofile);
//        files.add(ofile);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
      }
    }
    
    reportFiles.uuid = uuid;
    reportFiles.path = localCachePath;
    //    }

    return reportFiles;
  }
}
