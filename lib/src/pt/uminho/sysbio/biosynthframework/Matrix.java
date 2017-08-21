package pt.uminho.sysbio.biosynthframework;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Matrix<T> {
  
  private static final Logger logger = LoggerFactory.getLogger(Matrix.class);
  
  public int x;
  public int y;
  
  public String yAxis = "";
//  public Number[][] matrix;
  public String[] xLabel;
  public String[] yLabel;
  
  public Matrix(int x, int y) {
    this.x = x;
    this.y = y;
    xLabel = new String[x];
    yLabel = new String[y];
  }
  
  public void setAll(T v) {
    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        this.set(i, j, v);
      }
    }
  }
  
  public void orderRowsByLabel(String[] order) {
    int top = 0;
    for (int i = 0; i < order.length; i++) {
      String label = order[i];
      int index = this.getXIndexOf(label);
      if (index >= 0) {
        logger.debug("Move {} @ {} -> {}", order[i], index, top);
        this.rowSwap(index, top++);
      }
    }
  }
  
//  public abstract T get(int x, int y);
  
  public abstract void set(int x, int y, T v);
  public abstract void setSafe(int x, int y, T v);
  public abstract T get(int x, int y);

  
  public int getXIndexOf(String label) {
    for (int i = 0; i < xLabel.length; i++) {
      if (label.equals(xLabel[i])) {
        return i;
      }
    }
    return -1;
  }
  
  public String toMatrixString() {
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < y; j++) {
      sb.append("\t" + yLabel[j]);
    }
    sb.append("\n");
    
    
    for (int i = 0; i < x; i++) {
      sb.append(xLabel[i] + "\t");
      for (int j = 0; j < y; j++) {
        sb.append(get(i, j) + "\t");        
      }
      sb.append("\n");
    }
    
    return sb.toString();
  }
  
  public abstract void rowSwap(int a, int b);
  
//  public String toTsv() {
//    final char SEP = '\t';
//    StringBuilder sb = new StringBuilder();
//    sb.append(yAxis)
//      .append(SEP)
//      .append(StringUtils.join(yLabel, SEP))
//      .append('\n');
//    
//    for (int i = 0; i < x; i++) {
//      sb.append(xLabel[i])
//        .append(SEP)
//        .append(get(i, 0));
//      for (int j = 1; j < y; j++) {
//        sb.append(SEP)
//          .append(get(i, j));  
//      }
//      sb.append('\n');
//    }
//    
//    return sb.toString();
//  }
  
  public String toTsv(String...order) {
    final char SEP = '\t';
    StringBuilder sb = new StringBuilder();
    
    
//    sb.append(StringUtils.join(yLabel, SEP));
//    sb.append('\n');
    
    List<Integer> runOrder = new ArrayList<> ();
    for (String o : order) {
      for (int i = 0; i < y; i++) {
        if (yLabel != null && 
            yLabel[i] != null && 
            o != null && 
            yLabel[i].equals(o)) {
          runOrder.add(i);
        }
      }
    }
    
    for (int i = 0; i < y; i++) {
      if (!runOrder.contains(i)) {
        runOrder.add(i);
      }
    }
    
    sb.append(yAxis);
    for (int i = 0; i < y; i++) {
      sb.append(SEP).append(yLabel[runOrder.get(i)]);
    }
    sb.append('\n');
    
    for (int i = 0; i < x; i++) {
      sb.append(xLabel[i])
        .append(SEP)
        .append(get(i, runOrder.get(0)));
      for (int j = 1; j < y; j++) {
        sb.append(SEP)
          .append(get(i, runOrder.get(j)));  
      }
      sb.append('\n');
    }
    
    return sb.toString();
  }
  
  public void printMatrix() {
//    for (int j = 0; j < y; j++) {
//      System.out.print("\t" + yLabel[j]);
//    }
//    System.out.println();
//    
//    
//    for (int i = 0; i < x; i++) {
//      System.out.print(xLabel[i] + "\t");
//      for (int j = 0; j < y; j++) {
//        System.out.print(get(i, j) + "\t");        
//      }
//      System.out.println();
//    }
    System.out.println(this.toTsv());
  }
  
  public void printMatrix(String...order) {
    System.out.println(this.toTsv(order));
  }
}
