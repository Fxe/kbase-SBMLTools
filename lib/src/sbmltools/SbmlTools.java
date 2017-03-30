package sbmltools;

import java.util.List;

import pt.uminho.sysbio.biosynthframework.sbml.XmlMessage;

public class SbmlTools {
  public void filterContigs() {
    
  }
  
  public static String aaa(List<XmlMessage> msgs) {
    String txt = "";
    for (XmlMessage m : msgs) {
      txt +="\n" + String.format("%s", m);
    }
    
    return txt;
  }
}
