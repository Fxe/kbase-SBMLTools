package pt.uminho.sysbio.biosynthframework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatrixS extends Matrix<String> {

  private static final Logger logger = LoggerFactory.getLogger(MatrixS.class);
  
  public String[][] matrix;
  
  public MatrixS(int x, int y) {
    super(x, y);
    matrix = new String[x][y];
  }
  
  @Override
  public void rowSwap(int a, int b) {
    if (a == b) {
      return;
    }
    //row is Y btw ..
    logger.info("swap {} -> {} ({} -> {})", a, b, xLabel[a], xLabel[b]);
    
    String[] aArray = matrix[a];
    String[] bArray = matrix[b];
    matrix[a] = bArray;
    matrix[b] = aArray;
    String x = xLabel[a];
    xLabel[a] = xLabel[b];
    xLabel[b] = x;
  }

  @Override
  public void set(int x, int y, String v) {
    matrix[x][y] = v;
  }

  @Override
  public String get(int x, int y) {
    return matrix[x][y];
  }

  public String[] getRow(int x) {
    return matrix[x];
  }

  @Override
  public void setSafe(int x, int y, String v) {
    if (x >= this.matrix.length || y >= this.matrix[x].length) {
      logger.warn("invalid position {}, {} -> [{} x {}]", x, y, this.matrix.length, this.matrix[0].length);
    } else {
      set(x, y, v);
    }
  }
}
