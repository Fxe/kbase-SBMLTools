package pt.uminho.sysbio.biosynthframework.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegrationReportResult {
//  Map<String, List<Map<String, Object>>> readerReport = new HashMap<> ();
  public String name;
  public String date;
  public long epochTime;
  public Map<Integer, Map<String, String>> species = new HashMap<> ();
  public Map<String, Object> integrationData = new HashMap<> ();
  public Map<String, Object> importData = new HashMap<> ();
}
