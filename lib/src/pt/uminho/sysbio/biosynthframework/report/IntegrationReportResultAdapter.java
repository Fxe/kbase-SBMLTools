package pt.uminho.sysbio.biosynthframework.report;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.ReactionMajorLabel;
import pt.uminho.sysbio.biosynthframework.integration.model.SpecieIntegrationFacade;
import pt.uminho.sysbio.biosynthframework.sbml.MessageType;
import pt.uminho.sysbio.biosynthframework.sbml.XmlMessage;
import pt.uminho.sysbio.biosynthframework.sbml.XmlObject;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlReaction;
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
    Map<String, String> spiToCmp = new HashMap<> ();
    for (XmlSbmlSpecie xspi : xmodel.getSpecies()) {
      Map<String, String> xspiData = new HashMap<> ();
      String spiEntry = xspi.getAttributes().get("id");
      String spiCmp = xspi.getAttributes().get("compartment");          
      xspiData.put("id", spiEntry);
      xspiData.put("name", xspi.getAttributes().get("name"));
      xspiData.put("cmp", spiCmp);
      if (spiEntry != null && spiCmp != null) {
        spiToCmp.put(spiEntry, spiCmp);
      }
      this.report.species.put(i++, xspiData);
    }
    for (XmlSbmlReaction xrxn : xmodel.getReactions()) {
      Map<String, String> xrxnData = new HashMap<> ();
      xrxnData.put("id", xrxn.getAttributes().get("id"));
      xrxnData.put("name", xrxn.getAttributes().get("name"));
      
      xrxnData.put("cmp", "reaction");
      
      Set<String> cmps = new HashSet<String> ();
      for (XmlObject o : xrxn.getListOfReactants()) {
        String species = o.getAttributes().get("species");
        if (species == null || !spiToCmp.containsKey(species)) {
          xrxnData.put("cmp", "error");
        } else {
          cmps.add(spiToCmp.get(species));
        }
      }
      for (XmlObject o : xrxn.getListOfProducts()) {
        String species = o.getAttributes().get("species");
        if (species == null || !spiToCmp.containsKey(species)) {
          xrxnData.put("cmp", "error");
        } else {
          cmps.add(spiToCmp.get(species));
        }
      }
      
      if (cmps.size() > 1) {
        xrxnData.put("cmp", "t");
      }
      
      
      if (xrxn.getAttributes().get("id") != null &&
          xrxn.getAttributes().get("id").startsWith("R_EX_")) {
        xrxnData.put("cmp", "ex");
      }

      
      this.report.reactions.put(i++, xrxnData);
    }
    
    this.report.importData.put("species", nspi);
    this.report.importData.put("reactions", nrxn);
    this.report.importData.put("genes", ngne);
    this.report.importData.put("compartments", ncmp);
    
    return this;
  }
  
  @Deprecated
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
  
  public int getMessageTypeCount(MessageType type) {
    Integer i = this.report.validationCount.get(type);
//    @SuppressWarnings("unchecked")
//    Map<MessageType, Integer> m = (Map<MessageType, Integer>) this.report.importData.get("validation_summary");
//    Integer i = m.get(type);
    if (i == null) {
      i = 0;
    }
    
    return i;
  }
  
  public IntegrationReportResultAdapter fillSpeciesIntegrationData(
      SpecieIntegrationFacade integrationFacade) {
    
    if (integrationFacade.clean != null) {
      this.report.integrationData.put("species", new HashMap<> (integrationFacade.clean));
    }
    
    return this;
  }
  
  public IntegrationReportResultAdapter fillReactionIntegrationData(
      Map<String, Map<ReactionMajorLabel, String>> imap) {
    
    if (imap != null) {
      this.report.integrationData.put("reactions", new HashMap<>(imap));
    }
    
    return this;
  }
}
