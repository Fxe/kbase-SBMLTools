package sbmltools.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

import kbasereport.Report;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseHtmlReport;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseHtmlReport.ReportFiles;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;

public class TestKBaseReport {
  
//  public static void main(String[] args) {
//
////    System.out.println(template);
//  }
  
  public static void main(String[] args) {
//    System.out.println(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/index.html"));
//    System.out.println(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/css/bootstrap.min.css").length());
//    System.out.println(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/js/jquery-2.2.2.min.js").length());
//    System.out.println(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/js/underscore-min.js").length());
//    System.out.println(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/js/plotly-1.28.3.min.js").length());
//    
//    System.out.println(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/ids.json").length());
//    System.out.println(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/names.json").length());
//    System.out.println(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/refs.json").length());
//    
    Path p = new File("/tmp/argonne/report").toPath();
    KBaseHtmlReport htmlReport = new KBaseHtmlReport(p);
//    htmlReport.makeStaticReport("omg/f.html", "<p>hi!</p>");
    
    String root = "http://darwin.di.uminho.pt/fliu/model-integration-report2/";
    String index = KBaseIOUtils.getDataWeb(root + "index.html");
    String data1 = KBaseIOUtils.getDataWeb(root + "ids.json");
    String data2 = KBaseIOUtils.getDataWeb(root + "names.json");
    String data3 = KBaseIOUtils.getDataWeb(root + "refs.json");
    index = String.format(index, data1, data2, data3);
    
    List<String> files = new ArrayList<> ();
    
    files.add("css/bootstrap.min.css");
    files.add("js/jquery-2.2.2.min.js");
    files.add("js/underscore-min.js");
    files.add("js/plotly-1.28.3.min.js");

    List<String> datas = new ArrayList<> ();
    
    for (String f : files) {
      datas.add(KBaseIOUtils.getDataWeb(root + f));
    }
    
    files.add("index.html");
    datas.add(index);
    
    ReportFiles out = htmlReport.makeStaticReport(files, datas);

    try {
      OutputStream os = new FileOutputStream(out.path + File.separator +
                                             out.uuid + File.separator + 
                                             "report.zip");
      ZipOutputStream zos = new ZipOutputStream(os);
      
      
      for (int i = 0; i < files.size(); i++) {
        File f = out.files.get(i);
        System.out.println(f);
        ZipEntry ze = new ZipEntry(files.get(i));
        zos.putNextEntry(ze);
        InputStream is = new FileInputStream(f);
        IOUtils.copy(is, zos);
        IOUtils.closeQuietly(is);
        zos.closeEntry();
      }
      
      zos.close();
      os.close();
      
      System.out.println("done");
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}
