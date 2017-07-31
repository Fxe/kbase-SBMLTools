package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynth.BiodbServiceFactory;
import pt.uminho.sysbio.biosynth.FileBiodbService;
import pt.uminho.sysbio.biosynth.integration.FileImport;
import pt.uminho.sysbio.biosynth.integration.model.NameIntegration;

public class KBaseBiodbContainer {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseBiodbContainer.class);
  
  public FileBiodbService biodbService;
  public Map<Set<String>, String> nameMap = new HashMap<> ();
  
  public KBaseBiodbContainer(String path) {
    FileImport.EXPORT_PATH = path;
    logger.info("loading database from [{}] ...", path);
    biodbService = new BiodbServiceFactory().withMetaboliteDatabases().build();
    logger.info("loading names");
    nameMap = NameIntegration.buildNameDictionary();
  }
}
