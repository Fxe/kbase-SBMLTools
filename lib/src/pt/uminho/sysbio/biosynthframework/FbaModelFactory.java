package pt.uminho.sysbio.biosynthframework;

import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;

public class FbaModelFactory {
  
  public FbaModelFactory withXmlModel(XmlSbmlModel model) {
    
    return this;
  }
  
  public Object build() {
    return "";
  }
}
