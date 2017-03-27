package pt.uminho.sysbio.biosynthframework.sbml;

import java.util.ArrayList;
import java.util.List;

public class XmlSbmlGroup extends XmlObject {
	
	private List<XmlObject> listOfMembers = new ArrayList<> ();

	
	public List<XmlObject> getListOfMembers() { return listOfMembers; }
	public void setListOfMembers(List<XmlObject> listOfMembers) { this.listOfMembers = listOfMembers;}
	
	
}
