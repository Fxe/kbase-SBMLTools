package pt.uminho.sysbio.biosynthframework.sbml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUnitDefinition extends XmlObject {
  
  private Map<String, List<XmlObject>> listOfAnnotations = new HashMap<> ();
  public List<XmlObject> listOfUnits = new ArrayList<> ();
  
  public Map<String, List<XmlObject>> getListOfAnnotations() { return listOfAnnotations;}
  public void setListOfAnnotations(Map<String, List<XmlObject>> listOfAnnotations) { this.listOfAnnotations = listOfAnnotations;}
  
}
