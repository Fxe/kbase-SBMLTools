package pt.uminho.sysbio.biosynthframework.kbase;

public class KBaseId {
  public final String reference;
  public final String name;
  public final String workspace;
  
  public KBaseId(String name, String ws, String ref) {
    this.name = name;
    this.workspace = ws;
    this.reference = ref;
  }
  
  @Override
  public String toString() {
    return String.format("[%s] %s@%s", reference, name, workspace);
  }
}
