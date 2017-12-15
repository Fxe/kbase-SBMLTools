package sbmltools;

import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelAdapter;

/**
 * TEMPORARY CLASS TO MODIFY THE NOTES PARSER 
 * THE PARSER SHOULD BE INCLUDED IN THE CONSTRUCTOR
 * BAD DESIGN !
 * @author LocalUser
 *
 */
@Deprecated
public class FixedAdapter extends XmlSbmlModelAdapter {

  public FixedAdapter(XmlSbmlModel xmodel) {
    super(xmodel);
    this.notesParser.fields.put("GENE-ASSOCIATION", "gene_association");
  }
}
