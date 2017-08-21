package pt.uminho.sysbio.biosynthframework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

public class DataUtils {
  
  public static void printData(List<Map<String, Object>> a) {
    Set<String> hset = new HashSet<> ();
    for (Map<String, Object> record : a) {
      hset.addAll(record.keySet());
    }
    List<String> hs = new ArrayList<> (hset);
    for (Map<String, Object> record : a) {
      for (String h : hs) {
        Object o = record.get(h);
        System.out.print(o + "\t");
      }
      System.out.println();
    }
  }
  
  public static<X, Y, D> void printData(Map<X, Map<Y, D>> data) {
    MatrixSFactory<X, Y, D> f = new MatrixSFactory<>();
    f.withData(data);
    f.build().printMatrix();
  }
  
  public static<X, Y, D> String getTableStr(Map<X, Map<Y, D>> data, String yAxis, String...order) {
    MatrixSFactory<X, Y, D> f = new MatrixSFactory<>();
    f.withData(data)
     .withYAxisLabel(yAxis);
    return f.build().toTsv(order);
  }
  
  public static<X, Y, D> void printData(Map<X, Map<Y, D>> data, String yAxis, String...order) {
    System.out.println(getTableStr(data, yAxis, order));
  }
  
  public static<X, Y, D> void writeData(String path, Map<X, Map<Y, D>> data, String yAxis, String...order) {
    OutputStream os = null;
    try {
      os = new FileOutputStream(path);
      IOUtils.write(getTableStr(data, yAxis, order), os);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(os);
    }
  }
  
  public static List<Map<String, String>> readTsv(String path) {
    List<Map<String, String>> a = new ArrayList<> ();
    
    InputStream is = null;
    try {
      is = new FileInputStream(path);
      List<String> lines = IOUtils.readLines(is);
      String[] cols = lines.get(0).split("\t");
//      int headerIndex = -1;
      Map<String, Integer> columns = new HashMap<> ();
      for (int i = 0; i < cols.length; i++) {
        String cname = cols[i];
//        if (cname.equals(x)) {
//          headerIndex = i;
//        }
        columns.put(cname, i);
      }
      
      for (int lineIndex = 1; lineIndex < lines.size(); lineIndex++) {
        Map<String, String> lineData = new HashMap<> ();
        
        String[] data = lines.get(lineIndex).split("\t");
        for (int j = 0; j < data.length; j++) {
          lineData.put(cols[j], data[j]);
        }
        a.add(lineData);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(is);
    }
    
    return a;
  }
}
