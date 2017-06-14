package pt.uminho.sysbio.biosynthframework.sbml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlSbmlSpecie extends XmlObject {
  private List<String> notes  = new ArrayList<> ();
  private Map<String, List<XmlObject>> listOfAnnotations = new HashMap<> ();

  public Map<String, List<XmlObject>> getListOfAnnotations() { return listOfAnnotations;}
  public void setListOfAnnotations(Map<String, List<XmlObject>> listOfAnnotations) { this.listOfAnnotations = listOfAnnotations;}

  public List<String> getNotes() { return notes;}
  public void setNotes(List<String> notes) { this.notes = notes;}
}
