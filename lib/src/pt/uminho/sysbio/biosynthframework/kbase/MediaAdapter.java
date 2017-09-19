package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbasebiochem.Media;
import kbasebiochem.MediaCompound;
import kbasebiochem.MediaReagent;

public class MediaAdapter {
  
  private static final Logger logger = LoggerFactory.getLogger(MediaAdapter.class);
  
  public static String REF_URI = "489/6/5/compounds/id";
  
  private final Media media;
  
  public MediaAdapter(Media media) {
    this.media = media;
  }
  
  private static String getCompoundRef(String cpdEntry) {
    return String.format("%s/%s", REF_URI, cpdEntry);
  }
  
  public void setUptake(String cpdEntry, double uptake) {
    for (MediaCompound mc : this.media.getMediacompounds()) {
      if (mc.getCompoundRef().endsWith(cpdEntry)) {
        mc.setMaxFlux(uptake);
        return;
      }
    }
    
    logger.warn("media compound not found {}", cpdEntry);
  }
  
  public void addModelSeedMetabolite(String cpdEntry) {
    MediaCompound mc = new MediaCompound().withCompoundRef(getCompoundRef(cpdEntry))
                                          .withId(null)
                                          .withName(cpdEntry)
                                          .withSmiles(null)
                                          .withInchikey(null)
                                          .withConcentration(0.001)
                                          .withMaxFlux(100.0)
                                          .withMinFlux(-100.0);
    
    this.media.getMediacompounds().add(mc);
  }
  
  public void addModelSeedMetabolite(String cpdEntry, double lb, double ub) {
    //-10 10000 LB is output ! 
    double klb = -1 * ub;
    double kub = -1 * lb;
    MediaCompound mc = new MediaCompound().withCompoundRef(getCompoundRef(cpdEntry))
                                          .withId(null)
                                          .withName(cpdEntry)
                                          .withSmiles(null)
                                          .withInchikey(null)
                                          .withConcentration(0.001)
                                          .withMaxFlux(kub)
                                          .withMinFlux(klb);
    
    this.media.getMediacompounds().add(mc);
  }
  
  public void addCustomMetabolite(String cpdEntry, double lb, double ub) {
    //-10 10000 LB is output ! 
    double klb = -1 * ub;
    double kub = -1 * lb;
    MediaCompound mc = new MediaCompound().withCompoundRef(getCompoundRef("cpd00000"))
                                          .withId(cpdEntry)
                                          .withName(cpdEntry)
                                          .withSmiles(null)
                                          .withInchikey(null)
                                          .withConcentration(0.001)
                                          .withMaxFlux(kub)
                                          .withMinFlux(klb);
    
    this.media.getMediacompounds().add(mc);
  }
  
  public void addMediaCompound(MediaCompound mediaCompound) {
    MediaCompound mc = new MediaCompound().withCompoundRef(mediaCompound.getCompoundRef())
                                          .withId(mediaCompound.getId())
                                          .withName(mediaCompound.getName())
                                          .withSmiles(mediaCompound.getSmiles())
                                          .withInchikey(mediaCompound.getInchikey())
                                          .withConcentration(mediaCompound.getConcentration())
                                          .withMaxFlux(mediaCompound.getMaxFlux())
                                          .withMinFlux(mediaCompound.getMinFlux());
    this.media.getMediacompounds().add(mc);
  }
  
  public static Media buildDefaultEmptyMedia(String id) {
    /*
     * Media [
     * id=kb|media.5189, 
     * name=Carbon-D-Glucose, 
     * sourceId=Carbon-D-Glucose, 
     * source=null, 
     * protocolLink=null, 
     * isDefined=0, 
     * isMinimal=0, 
     * isAerobic=null, 
     * type=custom, 
     * pHData=null, 
     * temperature=null, 
     * atmosphere=null, 
     * atmosphereAddition=null, 
     * reagents=[], 
     * mediacompounds=[
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd00149, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd00099, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd00067, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}],  
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd00009, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd00001, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd00007, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=10.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd00205, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd00254, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd00971, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd10515, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd10516, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd11574, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd00244, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=100.0, minFlux=-100.0, additionalProperties={}], 
     *  MediaCompound [compoundRef=489/6/5/compounds/id/cpd00027, id=null, name=null, smiles=null, inchikey=null, concentration=0.001, maxFlux=5.0, minFlux=-100.0, additionalProperties={}]
     *  ], 
     *  additionalProperties={__VERSION__=1}]
     */
    Media media = new Media().withId(id)
        .withName(id)
        .withSourceId(id)
        .withSource(null)
        .withProtocolLink(null)
        .withIsDefined(0L)
        .withIsMinimal(0L)
        .withIsAerobic(null)
        .withType("custom")
        .withPHData(null)
        .withTemperature(null)
        .withAtmosphere(null)
        .withAtmosphereAddition(null)
        .withMediacompounds(new ArrayList<MediaCompound> ())
        .withReagents(new ArrayList<MediaReagent> ());
    return media;
  }
}
