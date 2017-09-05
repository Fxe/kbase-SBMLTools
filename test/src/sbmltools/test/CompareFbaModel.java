package sbmltools.test;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

import kbasefba.FBAModel;
import kbasefba.ModelCompartment;
import kbasefba.ModelCompound;
import kbasefba.ModelReaction;

public class CompareFbaModel {
  private static final Logger logger = LoggerFactory.getLogger(CompareFbaModel.class);
  
  final FBAModel fbaModel1;
  final FBAModel fbaModel2;
  
  public CompareFbaModel(final FBAModel fbaModel1 , final FBAModel fbaModel2) {
    this.fbaModel1 = fbaModel1;
    this.fbaModel2 = fbaModel2;
  }
  
  public void compare() {
    compString("id", fbaModel1.getId(), fbaModel2.getId());
    compString("genomeRef", fbaModel1.getGenomeRef(), fbaModel2.getGenomeRef());
    compString("templateRef", fbaModel1.getTemplateRef(), fbaModel2.getTemplateRef());
    compString("type", fbaModel1.getType(), fbaModel2.getType());
    compString("source", fbaModel1.getSource(), fbaModel2.getSource());
    compString("sourceId", fbaModel1.getSourceId(), fbaModel2.getSourceId());
    compDouble("ATPMaintenance", fbaModel1.getATPMaintenance(), fbaModel2.getATPMaintenance());
    
    Map<String, ModelCompartment> kcmp1 = new HashMap<> ();
    Map<String, ModelCompartment> kcmp2 = new HashMap<> ();
    for (ModelCompartment kcmp : fbaModel1.getModelcompartments()) {
      kcmp1.put(kcmp.getId(), kcmp);
    }
    for (ModelCompartment kcmp : fbaModel2.getModelcompartments()) {
      kcmp2.put(kcmp.getId(), kcmp);
    }
    
    for (String kcmpEntry : Sets.intersection(kcmp1.keySet(), kcmp2.keySet())) {
      ModelCompartment kcmp1o = kcmp1.get(kcmpEntry);
      ModelCompartment kcmp2o = kcmp2.get(kcmpEntry);
      comp(String.format("CMP: %s label", kcmpEntry), kcmp1o.getLabel(), kcmp2o.getLabel());
      comp(String.format("CMP: %s compartmentRef", kcmpEntry), kcmp1o.getCompartmentRef(), kcmp2o.getCompartmentRef());
      comp(String.format("CMP: %s compartmentIndex", kcmpEntry), kcmp1o.getCompartmentIndex(), kcmp2o.getCompartmentIndex());
    }
    
    Map<String, ModelCompound> kspi1 = new HashMap<> ();
    Map<String, ModelCompound> kspi2 = new HashMap<> ();
    for (ModelCompound kspi : fbaModel1.getModelcompounds()) {
      kspi1.put(kspi.getId(), kspi);
    }
    for (ModelCompound kspi : fbaModel2.getModelcompounds()) {
      kspi2.put(kspi.getId(), kspi);
    }
    
    for (String kspiEntry : Sets.intersection(kspi1.keySet(), kspi2.keySet())) {
      ModelCompound kspi1o = kspi1.get(kspiEntry);
      ModelCompound kspi2o = kspi2.get(kspiEntry);
      comp(String.format("SPI: %s name", kspiEntry), kspi1o.getName(), kspi2o.getName());
      comp(String.format("SPI: %s formula", kspiEntry), kspi1o.getFormula(), kspi2o.getFormula());
      comp(String.format("SPI: %s maxuptake", kspiEntry), kspi1o.getMaxuptake(), kspi2o.getMaxuptake());
      comp(String.format("SPI: %s modelcompartmentRef", kspiEntry), kspi1o.getModelcompartmentRef(), kspi2o.getModelcompartmentRef());
      
    }
    
    Map<String, ModelReaction> krxn1 = new HashMap<> ();
    Map<String, ModelReaction> krxn2 = new HashMap<> ();
    for (ModelReaction krxn : fbaModel1.getModelreactions()) {
      if (krxn.getId().startsWith("R_")) {
        krxn1.put(krxn.getId().replace("R_", ""), krxn);
      } else {
        krxn1.put(krxn.getId(), krxn);
      }
    }
    for (ModelReaction krxn : fbaModel2.getModelreactions()) {
      if (krxn.getId().endsWith("_c0")) {
        krxn2.put(krxn.getId().replace("_c0", ""), krxn);
      } else {
        krxn2.put(krxn.getId(), krxn);
      }
    }
    
    for (String krxnEntry : Sets.intersection(krxn1.keySet(), krxn2.keySet())) {
      ModelReaction krxn1o = krxn1.get(krxnEntry);
      ModelReaction krxn2o = krxn2.get(krxnEntry);
      comp(String.format("RXN: %s name", krxnEntry), krxn1o.getName(), krxn2o.getName());
      comp(String.format("RXN: %s direction", krxnEntry), krxn1o.getDirection(), krxn2o.getDirection());
      comp(String.format("RXN: %s importedGpr", krxnEntry), krxn1o.getImportedGpr(), krxn2o.getImportedGpr());
      comp(String.format("RXN: %s maxforflux", krxnEntry), krxn1o.getMaxforflux(), krxn2o.getMaxforflux());
      comp(String.format("RXN: %s maxrevflux", krxnEntry), krxn1o.getMaxrevflux(), krxn2o.getMaxrevflux());
    }
    
    for (String krxnEntry : Sets.difference(krxn1.keySet(), krxn2.keySet())) {
      System.out.println("m1 " + krxnEntry);
    }
    for (String krxnEntry : Sets.difference(krxn2.keySet(), krxn1.keySet())) {
      System.out.println("m2 " + krxnEntry);
    }
  }
  
  public void comp(String prop, Object s1, Object s2) {
    if ((s1 != null && s1.equals(s2)) || 
        (s1 == null && s2 == null)) {
      logger.info("[{}] {}", prop, s2);
    } else {
      logger.warn("[{}] [{}] [{}]", prop, s1, s2);
    }
  }
  
  public void compString(String prop, String s1, String s2) {
    if ((s1 != null && s1.equals(s2)) || 
        (s1 == null && s2 == null)) {
      logger.info("[{}] {}", prop, s2);
    } else {
      logger.warn("[{}] [{}] [{}]", prop, s1, s2);
    }
  }
  
  public void compDouble(String prop, Double d1, Double d2) {
    if ((d1 != null && d1.equals(d2)) || 
        (d1 == null && d2 == null)) {
      logger.info("[{}] {}", prop, d1);
    } else {
      logger.warn("[{}] [{}] [{}]", prop, d1, d2);
    }
  }
}
