package pt.uminho.sysbio.biosynthframework;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MatrixSFactory<X, Y, D> {
  
  public static interface CellPrinter<X, Y, D> {
    public String apply(X x, Y y, D data);
  }
  
  private Map<X, Map<Y, D>> data = new HashMap<> ();
  private CellPrinter<X, Y, D> printer = null;
  private BFunction<X, String> xlabelPrinter = null;
  private BFunction<Y, String> ylabelPrinter = null;
  private boolean swapCols = false;
  private D defaultValue = null;
  private String label = "";
  
  public MatrixSFactory<X, Y, D> withData(Map<X, Map<Y, D>> data) {
    this.data.putAll(data);
    return this;
  }
  
  public MatrixSFactory<X, Y, D> withYAxisLabel(String label) {
    this.label = label;
    return this;
  }
  
  public MatrixSFactory<X, Y, D> withPrinter(CellPrinter<X, Y, D> printer) {
    this.printer = printer;
    return this;
  }
  
  public MatrixSFactory<X, Y, D> withXLabelPrinter(BFunction<X, String> printer) {
    this.xlabelPrinter = printer;
    return this;
  }
  
  public MatrixSFactory<X, Y, D> withYLabelPrinter(BFunction<Y, String> printer) {
    this.ylabelPrinter = printer;
    return this;
  }
  
  public MatrixSFactory<X, Y, D> withDefaultValue(D value) {
    this.defaultValue = value;
    return this;
  }
  
  public MatrixS buildSwap() {
    return null;
  }

  public MatrixS build() {
    
    if (swapCols) {
      return buildSwap();
    }
    
    Map<X, Integer> xIndexMap = new HashMap<> ();
    Map<Y, Integer> yIndexMap = new HashMap<> ();
    
    int index = 0;
    Set<Y> yKeys = new HashSet<> ();
    for (X xKey : data.keySet()) {
      xIndexMap.put(xKey, index++);
      yKeys.addAll(data.get(xKey).keySet());
    }
    index = 0;
    for (Y yKey : yKeys) {
      yIndexMap.put(yKey, index++);
    }
    
    MatrixS result = new MatrixS(xIndexMap.size(), yKeys.size());
    if (defaultValue != null) {
//      String val = null;
//      if (printer != null) {
//        val = printer.apply(xKey, yKey, value);
//      }
      result.setAll(defaultValue.toString());
    }
    
//    System.out.println(data);
    for (X xKey : xIndexMap.keySet()) {
      String xLabelStr = xKey.toString();
      if (xlabelPrinter != null) {
        xLabelStr = xlabelPrinter.apply(xKey);
      }
      result.xLabel[xIndexMap.get(xKey)] = xLabelStr;
    }
    for (Y yKey : yIndexMap.keySet()) {
      String yLabelStr = yKey.toString();
      if (xlabelPrinter != null) {
        yLabelStr = ylabelPrinter.apply(yKey);
      }
      result.yLabel[yIndexMap.get(yKey)] = yLabelStr;
    }
    
    //print missing
    for (X xKey : data.keySet()) {
      int xIndex = xIndexMap.get(xKey);
      for (Y yKey : data.get(xKey).keySet()) {
        int yIndex = yIndexMap.get(yKey);
        D value = data.get(xKey).get(yKey);
        if (value == null) {
//          logger.info("[{}] - {} missing", modelEntry, ucomp);
        } else {
          String str = "";
          if (printer != null) {
            str = printer.apply(xKey, yKey, value);
          } else {
            str = value.toString();
          }
          result.set(xIndex, yIndex, str);
        }
      }
    }
    
    result.yAxis = label;
    
    return result;
  }


}

