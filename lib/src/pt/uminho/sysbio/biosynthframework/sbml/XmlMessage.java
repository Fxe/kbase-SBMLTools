package pt.uminho.sysbio.biosynthframework.sbml;

import java.util.HashMap;
import java.util.Map;

public class XmlMessage {
  public MessageType type;
  public String message;
  public final int lineNumber;
  public final int columnNumber;
//  public XmlObject cause;
  public long time;

  public XmlMessage(XmlObject xo, MessageType type, String str, Object...args) {
    this.type = type;
    this.lineNumber = xo.lineNumber;
    this.columnNumber = xo.columnNumber;
//    this.cause = xo;
    this.message = String.format(str, args).replaceAll("\n", "");
    this.time = System.currentTimeMillis();
  }

  public Map<String, String> removeEndline(Map<String, String> attributes) {
    Map<String, String> noEndl = new HashMap<> ();
    
    for (String k : attributes.keySet()) {
      noEndl.put(k, attributes.get(k).replaceAll("\n", ""));
    }
    
    return noEndl;
  }
  
  @Override
  public String toString() {
    return String.format("L:%d C:%d %s - %s", lineNumber, columnNumber, type, message);
  }
}
