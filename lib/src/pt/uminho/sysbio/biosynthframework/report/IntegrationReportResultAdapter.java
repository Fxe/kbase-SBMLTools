package pt.uminho.sysbio.biosynthframework.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.uminho.sysbio.biosynthframework.integration.model.SpecieIntegrationFacade;
import pt.uminho.sysbio.biosynthframework.sbml.MessageType;
import pt.uminho.sysbio.biosynthframework.sbml.XmlMessage;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlSpecie;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;

public class IntegrationReportResultAdapter {
  protected final IntegrationReportResult report;
  
  public IntegrationReportResultAdapter(final IntegrationReportResult report) {
    this.report = report;
  }
  
  public IntegrationReportResultAdapter fillImportData(XmlSbmlModel xmodel) {
    int nspi = xmodel.getSpecies().size();
    int ncmp = xmodel.getCompartments().size();
    int nrxn = xmodel.getReactions().size();
    int ngne = 0;
    
    int i = 0;
    for (XmlSbmlSpecie xspi : xmodel.getSpecies()) {
      Map<String, String> xspiData = new HashMap<> ();
      xspiData.put("id", xspi.getAttributes().get("id"));
      xspiData.put("name", xspi.getAttributes().get("name"));
      xspiData.put("cmp", xspi.getAttributes().get("compartment"));
      this.report.species.put(i++, xspiData);
    }
    
    this.report.importData.put("species", nspi);
    this.report.importData.put("reactions", nrxn);
    this.report.importData.put("genes", ngne);
    this.report.importData.put("compartments", ncmp);
    
    return this;
  }
  
  public IntegrationReportResultAdapter fillValidationData(List<XmlMessage> messages) {
    Map<MessageType, Integer> messageTypeCount = new HashMap<> ();
    
    for (XmlMessage msg : messages) {
//      Map<String, Object> a = new HashMap<> ();
////      a.put("model", name);
//      a.put("line", msg.lineNumber);
//      a.put("column", msg.columnNumber);
//      a.put("text", msg.message);
//      a.put("level", msg.type);
      CollectionUtils.increaseCount(messageTypeCount, msg.type, 1);
    }
    
    this.report.importData.put("validation_summary", messageTypeCount);
    
    return this;
  }
  
  public IntegrationReportResultAdapter fillIntegrationData(
      SpecieIntegrationFacade integrationFacade) {
    
//    List<Object> databases = new ArrayList<> ();
//    this.report.integrationData.put("dbs", databases);
    
    if (integrationFacade.clean != null) {
      this.report.integrationData.put("species", new HashMap<> (integrationFacade.clean));
    }
    
    this.report.integrationData.put("reactions", new HashMap<> ());
    
    return this;
  }
}
