package pt.uminho.sysbio.biosynthframework;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import pt.uminho.sysbio.biosynthframework.util.SbmlUtils;

public class MultiNodeTree<T> {
  public T body = null;
  protected List<MultiNodeTree<T>> childs = new ArrayList<> ();
  public MultiNodeTree<T> parent = null;
  
  public MultiNodeTree(T body) {
    this.body = body;
  }
  
  public void addChild(MultiNodeTree<T> child) {
    child.parent = this;
    this.childs.add(child);
  }
  
  public List<MultiNodeTree<T>> getChilds() {
    return childs;
  }

  public void setChilds(List<MultiNodeTree<T>> childs) {
    for (MultiNodeTree<T> c : this.childs) {
      c.parent = null;
    }
    for (MultiNodeTree<T> c : childs) {
      c.parent = this;
    }
    this.childs = childs;
  }

  public static void main(String[] args) {
    MultiNodeTree<Object> s = new MultiNodeTree<>("alone");
    MultiNodeTree<Object> gpr = new MultiNodeTree<>(Operator.OR);
    MultiNodeTree<Object> gpr2 = new MultiNodeTree<>(Operator.AND);
    gpr2.addChild(new MultiNodeTree<Object>("A"));
    gpr2.addChild(new MultiNodeTree<Object>("B"));
    
    MultiNodeTree<Object> gpr3 = new MultiNodeTree<>(Operator.AND);
    gpr3.addChild(new MultiNodeTree<Object>("C"));
    gpr3.addChild(new MultiNodeTree<Object>("D"));
    
    MultiNodeTree<Object> gpr4 = new MultiNodeTree<>(Operator.OR);
    gpr4.addChild(new MultiNodeTree<Object>("K"));
    gpr4.addChild(new MultiNodeTree<Object>("J"));
    gpr3.addChild(gpr4);
    gpr.addChild(gpr2);
    gpr.addChild(gpr3);
    
    java.util.function.Function<Object, String> f = 
        new Function<Object, String>() {
      
      @Override
      public String apply(Object t) {
        return t.toString();
      }
    };
    
    System.out.println(SbmlUtils.gprTreeToString(gpr, f));
    System.out.println(SbmlUtils.gprTreeToString(s, f));
  }
}
