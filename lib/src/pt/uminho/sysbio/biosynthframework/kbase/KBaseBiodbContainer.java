package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.ReactionMajorLabel;
import pt.uminho.sysbio.biosynthframework.io.FileImportKb;
import pt.uminho.sysbio.ext.BiodbServiceFactory;
import pt.uminho.sysbio.ext.FileBiodbService;
import pt.uminho.sysbio.ext.NameIntegration;

public class KBaseBiodbContainer {
  
  private static final Logger logger = LoggerFactory.getLogger(KBaseBiodbContainer.class);
  
  public FileBiodbService biodbService;
  public Map<Set<String>, String> nameMap = new HashMap<> ();
  
  public KBaseBiodbContainer(String path) {
    FileImportKb.EXPORT_PATH = path;
    logger.info("loading database from [{}] ...", path);
    biodbService = null;
    biodbService = new BiodbServiceFactory()
        .withMetaboliteDatabases()
        .withMetaboliteFormulas()
        .withMetaboliteInchiKeys()
        .withMetaboliteSmiles()
        .withReactionDatabase(ReactionMajorLabel.BiGG.toString())
        .withReactionDatabase(ReactionMajorLabel.LigandReaction.toString())
        .withReactionDatabase(ReactionMajorLabel.MetaCyc.toString())
        .withReactionDatabase(ReactionMajorLabel.ModelSeedReaction.toString())
        .withReactionDatabase(ReactionMajorLabel.Seed.toString())
        .build();
    logger.info("loading names");
    nameMap = NameIntegration.buildNameDictionary();
  }
}
