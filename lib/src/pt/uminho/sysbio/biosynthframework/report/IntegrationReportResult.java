package pt.uminho.sysbio.biosynthframework.report;

import java.util.HashMap;
import java.util.Map;

import pt.uminho.sysbio.biosynthframework.sbml.MessageType;

public class IntegrationReportResult {
//  Map<String, List<Map<String, Object>>> readerReport = new HashMap<> ();
  public String name;
  public String date;
  public long epochTime;
  public String error;
  public Map<Integer, Map<String, String>> species = new HashMap<> ();
  public Map<Integer, Map<String, String>> reactions = new HashMap<> ();
  public Map<String, Object> integrationData = new HashMap<> ();
  public Map<String, Object> importData = new HashMap<> ();
  public Map<MessageType, Integer> validationCount = new HashMap<> ();
  
  public String validationData;
}
