package sbmltools.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import pt.uminho.sysbio.biosynth.integration.BiodbService;
import pt.uminho.sysbio.ext.FileBiodbService;

public class StandaloneIntegration {
  
  public StandaloneIntegration() {
    
//    Enumeration<URL> e;
//    try {
//      e = StandaloneIntegration.class.getClassLoader().getResources(".");
//      while (e.hasMoreElements())
//      {
//          System.out.println("ClassLoader Resource: " + e.nextElement());
//      }
//    } catch (IOException e1) {
//      // TODO Auto-generated catch block
//      e1.printStackTrace();
//    }

//    System.out.println("Class Resource: " + Test.class.getResource("/"));
//    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//    URL resource = classLoader.getResource(name);
//    InputStream is = getClass().getClassLoader().getResourceAsStream("bigg1_metabolites.tsv");
  }
  
  public static void main(String[] args) {
    StandaloneIntegration si = new StandaloneIntegration();
  }
}
