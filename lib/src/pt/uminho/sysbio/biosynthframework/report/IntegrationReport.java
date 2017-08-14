package pt.uminho.sysbio.biosynthframework.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynthframework.Dataset;

public class IntegrationReport {
  
  private static final Logger logger = LoggerFactory.getLogger(IntegrationReport.class);
  
  public Map<String, Object> models = new HashMap<> ();
  public Map<String, Object> all = new HashMap<String, Object> ();
  
  public void setSpeciesIntegrationSummary(IntegrationByDatabase summary) {
    if (validateSpeciesIntegrationSummary(summary)) {
      all.put("species", summary);
    }
  }
  
  public void addIntegrationReport(String modelEntry, 
                                   IntegrationReportResult report) {
    if (modelEntry != null && report != null) {
      models.put(modelEntry, report);
    }
  }
  
  public boolean validateSpeciesIntegrationSummary(IntegrationByDatabase summary) {
    if (summary == null) {
      return false;
    }
    
    if (summary.modelTotal == null) {
      summary.modelTotal = new HashMap<> ();
    }
    if (summary.databases == null) {
      summary.databases = new ArrayList<> ();
    }
    if (summary.dataAbsolute == null) {
      summary.dataAbsolute = new Dataset<>();
    }
    
    Integer i = null;
    if ((i = summary.modelTotal.remove(null)) != null) {
      logger.warn("remove null modelId with value {}", i);
    }
    
    Object o = null;
    if ((o = all.remove(null)) != null) {
      logger.warn("remove null model data");
    }
    
    return true;
  }
}
