package pt.uminho.sysbio.biosynthframework.report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynthframework.Dataset;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;

public class IntegrationReport {
  
  private static final Logger logger = LoggerFactory.getLogger(IntegrationReport.class);
  
  public Map<String, IntegrationReportResult> models = new HashMap<> ();
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
      logger.warn("remove null model data: {}", o);
    }
    
    return true;
  }
  
  public static void write(IntegrationReport sbmlImportData, String path) {
    String jsonData = KBaseIOUtils.toJson(sbmlImportData, true);
    logger.info("[EXPORT] size: {}", jsonData.length());
    OutputStream os = null;
    try {
      os = new FileOutputStream(path);
      IOUtils.write(jsonData, os);
      
      logger.info("[EXPORT] file written: {}", path);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(os);
    }
  }
}
