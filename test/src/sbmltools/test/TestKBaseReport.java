package sbmltools.test;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import pt.uminho.sysbio.biosynthframework.kbase.KBaseHtmlReport;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;

public class TestKBaseReport {
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
    htmlReport.makeStaticReport("omg/f.html", "<p>hi!</p>");
    
    List<String> files = new ArrayList<> ();
    files.add("index.html");
    files.add("css/bootstrap.min.css");
    files.add("js/jquery-2.2.2.min.js");
    files.add("js/underscore-min.js");
    files.add("js/plotly-1.28.3.min.js");
    files.add("ids.json");
    files.add("names.json");
    files.add("refs.json");
    List<String> datas = new ArrayList<> ();
    
    for (String f : files) {
      datas.add(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/model-integration-report/" + f));
    }
    
    htmlReport.makeStaticReport(files, datas);
    
  }
}
