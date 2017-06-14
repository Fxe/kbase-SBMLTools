package sbmltools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Media {
  
  public static class MediaReagent {
    @JsonProperty("id")
    public java.lang.String id;
    @JsonProperty("name")
    public java.lang.String name;
    @JsonProperty("concentration")
    public Double concentration;
    @JsonProperty("concentration_units")
    public java.lang.String concentration_units;
    @JsonProperty("molecular_weight")
    public Double molecular_weight;
    
    @JsonProperty("associated_compounds")
    public Map<java.lang.String, Object> associated_compounds = new HashMap<java.lang.String, Object>();
  }
  
  public static class MediaCompound {
    @JsonProperty("compound_ref")
    public java.lang.String compound_ref;
    @JsonProperty("concentration")
    public Double concentration;
    @JsonProperty("maxFlux")
    public Double maxFlux;
    @JsonProperty("minFlux")
    public Double minFlux;
  }
  
  @JsonProperty("id")
  public java.lang.String id;
  @JsonProperty("source")
  public java.lang.String source;
  @JsonProperty("source_id")
  public java.lang.String sourceId;
  @JsonProperty("name")
  public java.lang.String name;
  @JsonProperty("type")
  public java.lang.String type;
  @JsonProperty("pH_data")
  public java.lang.String pH_data;
  @JsonProperty("protocol_link")
  public java.lang.String protocol_link;
  
  @JsonProperty("isDefined")
  public java.lang.Integer isDefined;
  @JsonProperty("isMinimal")
  public java.lang.Integer isMinimal;
  @JsonProperty("isAerobic")
  public java.lang.Integer isAerobic;
  
  @JsonProperty("atmosphere")
  public java.lang.String atmosphere;
  @JsonProperty("atmosphere_addition")
  public java.lang.String atmosphere_addition;
  
  @JsonProperty("temperature")
  public Double temperature;
  
  @JsonProperty("reagents")
  public List<MediaReagent> reagents;
  @JsonProperty("mediacompounds")
  public List<MediaCompound> mediacompounds = new ArrayList<> ();
  
  public void addMediaCompound(String cpd, double conc, double lb, double ub) {
    if (cpd == null || cpd.trim().isEmpty()) {
      throw new IllegalArgumentException("invalid compound ID " + cpd);
    }
    
    MediaCompound mediaCompound = new MediaCompound();
    mediaCompound.compound_ref = String.format("kbase/default/compounds/id/%s", cpd);
    mediaCompound.concentration = conc;
    mediaCompound.maxFlux = ub;
    mediaCompound.minFlux = lb;
    this.mediacompounds.add(mediaCompound);
  }
  
//  public void addMediaReagent() {
//    MediaReagent mediaReagent = new MediaReagent();
//    mediaReagent.associated_compounds.put("", null);
////    mediaReagent.
//  }
}
