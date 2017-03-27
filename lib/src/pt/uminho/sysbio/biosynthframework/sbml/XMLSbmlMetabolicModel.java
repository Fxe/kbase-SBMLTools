package pt.uminho.sysbio.biosynthframework.sbml;

import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated no clue why I did this
 * @author Filipe Liu
 *
 */
@Deprecated
public class XMLSbmlMetabolicModel extends XmlObject {
	private List<XmlSbmlSpecie> species = new ArrayList<> ();
	
	public List<XmlSbmlSpecie> getSpecies() {
		return species;
	}
	public void setSpecies(List<XmlSbmlSpecie> species) {
		this.species = species;
	}
	
	
}
