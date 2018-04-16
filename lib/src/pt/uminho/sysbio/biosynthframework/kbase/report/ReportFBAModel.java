package pt.uminho.sysbio.biosynthframework.kbase.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kbasefba.FBAModel;
import kbasefba.ModelCompound;
import kbasefba.ModelReaction;

public class ReportFBAModel {
  
  public static class ReportData {
    public FBAModel model;
    public Map<String, Record> spiMap = new HashMap<>();
    public Map<String, Record> rxnMap = new HashMap<>();
    
    public void addSpiData(String id, String name, String cmp,
                           Map<String, List<String>> references) {
      RecordSpi data = new RecordSpi();
      data.id = id;
      data.name = name;
      data.cmp = cmp;
      data.references = references;
      
      spiMap.put(id, data);
    }
    
    public void addRxnData(String id, String name, String gpr,
                           Map<String, List<String>> references) {
      RecordRxn data = new RecordRxn();
      data.id = id;
      data.name = name;
      data.gpr = gpr;
      data.references = references;

      rxnMap.put(id, data);
    }
    
    public static class Record {
      public String id;
      public String name;
      public Map<String, List<String>> references;
    }
    
    public static class RecordRxn extends Record {
      public String gpr;
    }
    
    public static class RecordSpi extends Record {
      public String cmp;
    }
  }
  
  public ReportData report(FBAModel model) {
    ReportData result = new ReportData();
    result.model = model;
    
    for (ModelCompound spi : model.getModelcompounds()) {
      String id = spi.getId();
      String name = spi.getName();
      String cmp = spi.getModelcompartmentRef();
      Map<String, List<String>> references = spi.getDblinks();
      result.addSpiData(id, name, cmp, references);
    }
    
    for (ModelReaction rxn : model.getModelreactions()) {
      String id = rxn.getId();
      String name = rxn.getName();
      String gprStr = rxn.getImportedGpr();
      Map<String, List<String>> references = rxn.getDblinks();
      result.addRxnData(id, name, gprStr, references);
    }
    
    return result;
  }
}
