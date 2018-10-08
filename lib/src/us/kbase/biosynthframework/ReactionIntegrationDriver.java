package us.kbase.biosynthframework;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;

import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.ReactionMajorLabel;
import pt.uminho.sysbio.biosynthframework.CompartmentalizedStoichiometry;
import pt.uminho.sysbio.biosynthframework.ModelAdapter;
import pt.uminho.sysbio.biosynthframework.biodb.modelseed.ModelSeedReactionEntity;
import pt.uminho.sysbio.biosynthframework.biodb.modelseed.ModelSeedReactionReagentEntity;
import pt.uminho.sysbio.biosynthframework.biodb.modelseed.ModelSeedUtils;
import pt.uminho.sysbio.biosynthframework.integration.ReactionTMatcher;
import pt.uminho.sysbio.biosynthframework.integration.model.SimpleMetabolicModelReactionIntegrationImpl;
import pt.uminho.sysbio.biosynthframework.io.BiosDao;
import pt.uminho.sysbio.biosynthframework.io.biodb.modelseed.GithubModelSeedReactionDaoImpl;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;

public class ReactionIntegrationDriver {
  
  private static final Logger logger = LoggerFactory.getLogger(ReactionIntegrationDriver.class);
  
  public static String modelseedDbPath = "/kb/module/data/modelseed";
  
  public Map<String, Map<ReactionMajorLabel, String>> run(final Map<String, String> spiMapping, final ModelAdapter model) {
//    spiMapping.put("M_m1352", "cpd00067");
//    spiMapping.put("M_m20",   "cpd00067");
//    spiMapping.put("M_m920",  "cpd00067");
//    spiMapping.put("M_m1104", "cpd00067");

    Function<String, String> a = new Function<String, String>() {

      @Override
      public String apply(String id) {
        return spiMapping.containsKey(id) ? spiMapping.get(id) : id;
      }
    };

    Function<Set<String>, String> b = new Function<Set<String>, String>() {

      @Override
      public String apply(Set<String> ids) {
        return ModelSeedUtils.selectLowestId(ids);
      }
    };
    
    SimpleMetabolicModelReactionIntegrationImpl<ModelSeedReactionEntity> reactionIntegration = 
        new SimpleMetabolicModelReactionIntegrationImpl<>(model, a, b);

    BiosDao<ModelSeedReactionEntity> rxnDao = new GithubModelSeedReactionDaoImpl(
        "48c089f4f0128ed3c06ce716750693b4feccb623", 
        modelseedDbPath);
    reactionIntegration.dao = rxnDao;
    reactionIntegration.excludeIds.add("cpd00067");
    reactionIntegration.isBasic = new Function<ModelSeedReactionEntity, Boolean>() {
      @Override
      public Boolean apply(ModelSeedReactionEntity t) {
        Set<Integer> cmps = new HashSet<>();
        for (ModelSeedReactionReagentEntity reagent : t.getReagents()) {
          cmps.add(reagent.getCompartment());
        }
        return cmps.size() == 1;
      }
    };
    
    reactionIntegration.convertToStoich = new Function<ModelSeedReactionEntity, CompartmentalizedStoichiometry<String,Integer>>() {
      @Override
      public CompartmentalizedStoichiometry<String, Integer> apply(ModelSeedReactionEntity t) {
        CompartmentalizedStoichiometry<String, Integer> cstoich = new CompartmentalizedStoichiometry<>();
        for (ModelSeedReactionReagentEntity reagent : t.getReagents()) {
          cstoich.add(reagent.getCpdEntry(), reagent.getCompartment(), reagent.getCoefficient());
        }
        return cstoich;
      }
    };


    ReactionTMatcher<Integer, String> tmatcher = new ReactionTMatcher<>();
    tmatcher.allowSingle = false;
    tmatcher.testReverse = true;
    ReactionTMatcher<Integer, String> mmatcher = new ReactionTMatcher<>();
    mmatcher.allowSingle = true;
    mmatcher.testReverse = true;

    logger.info("loading basic reactions");
    for (String rxnEntry : rxnDao.getAllEntries()) {
      ModelSeedReactionEntity rxn = rxnDao.getByEntry(rxnEntry);
      if (!reactionIntegration.isBasic.apply(rxn)) {
        CompartmentalizedStoichiometry<String, Integer> cstoich = reactionIntegration.convertToStoich.apply(rxn);
        tmatcher.addReaction(cstoich, rxnEntry);
      }
    }
    
    logger.info("loading translocation reactions");
    for (String rxnEntry : rxnDao.getAllEntries()) {
      ModelSeedReactionEntity rxn = rxnDao.getByEntry(rxnEntry);
      if (reactionIntegration.isBasic.apply(rxn)) {
        CompartmentalizedStoichiometry<String, Integer> cstoich = reactionIntegration.convertToStoich.apply(rxn);
        for (String k : reactionIntegration.excludeIds) {
          cstoich.remove(k);
        }
        mmatcher.addReaction(cstoich, rxnEntry);
      }
    }
    
    for (String mrxnId : model.getReactionIds()) {
      CompartmentalizedStoichiometry<String, String> cstoich = model.getCompartmentalizedStoichiometry(mrxnId);
      if (model.isTranslocation(mrxnId)) {
        reactionIntegration.aaa(mrxnId, cstoich, tmatcher, null, reactionIntegration.treport);
      } else {
        reactionIntegration.aaa(mrxnId, cstoich, mmatcher, reactionIntegration.excludeIds, reactionIntegration.mreport);        
      }
    }
    
    Map<String, Map<ReactionMajorLabel, String>> dblinks = new HashMap<>();
    
    for (String mrxnId : reactionIntegration.treport.dataset.keySet()) {
      Map<String, Object> data = reactionIntegration.treport.dataset.get(mrxnId);
      if (data != null) {
        Object msid = data.get("best");
        if (!DataUtils.empty(msid)) {
          Map<ReactionMajorLabel, String> single = new HashMap<>();
          single.put(ReactionMajorLabel.ModelSeedReaction, msid.toString());
          dblinks.put(mrxnId, single);
        }
      }
    }
    
    return dblinks;
  }
}
