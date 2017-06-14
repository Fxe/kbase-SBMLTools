package pt.uminho.sysbio.biosynthframework.sbml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.uminho.sysbio.biosynthframework.MultiNodeTree;

public class XmlSbmlReaction extends XmlObject {
  
  private Map<String, List<XmlObject>> listOfAnnotations = new HashMap<> ();
  private List<XmlObject> listOfReactants = new ArrayList<> ();
  private List<XmlObject> listOfProducts  = new ArrayList<> ();
  private List<XmlObject> listOfParameters  = new ArrayList<> ();
  private MultiNodeTree<Object> gpr = null;
  
  private List<String> notes  = new ArrayList<> ();
  
  public Map<String, List<XmlObject>> getListOfAnnotations() { return listOfAnnotations;}
  public void setListOfAnnotations(Map<String, List<XmlObject>> listOfAnnotations) { this.listOfAnnotations = listOfAnnotations;}
  
  public List<XmlObject> getListOfReactants() { return listOfReactants;}
  public void setListOfReactants(List<XmlObject> listOfReactants) { this.listOfReactants = listOfReactants;}
  
  public List<XmlObject> getListOfProducts() { return listOfProducts;}
  public void setListOfProducts(List<XmlObject> listOfProducts) { this.listOfProducts = listOfProducts;}
  
  public List<XmlObject> getListOfParameters() { return listOfParameters;}
  public void setListOfParameters(List<XmlObject> listOfParameters) { this.listOfParameters = listOfParameters;}
  
  public List<String> getNotes() { return notes;}
  public void setNotes(List<String> notes) { this.notes = notes;}
  
  public MultiNodeTree<Object> getGpr() { return gpr;}
  public void setGpr(MultiNodeTree<Object> gpr) { this.gpr = gpr;}
  
}
