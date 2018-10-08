package us.kbase.biosynthframework;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;

import pt.uminho.sysbio.biosynthframework.CompartmentalizedStoichiometry;
import pt.uminho.sysbio.biosynthframework.ModelAdapter;
import pt.uminho.sysbio.biosynthframework.biodb.modelseed.ModelSeedReactionEntity;
import pt.uminho.sysbio.biosynthframework.biodb.modelseed.ModelSeedReactionReagentEntity;
import pt.uminho.sysbio.biosynthframework.biodb.modelseed.ModelSeedUtils;
import pt.uminho.sysbio.biosynthframework.integration.ReactionTMatcher;
import pt.uminho.sysbio.biosynthframework.integration.model.SimpleMetabolicModelReactionIntegrationImpl;
import pt.uminho.sysbio.biosynthframework.io.BiosDao;
import pt.uminho.sysbio.biosynthframework.io.biodb.modelseed.GithubModelSeedReactionDaoImpl;

public class ReactionIntegrationDriver {
  public void run(final Map<String, String> spiMapping, final ModelAdapter model) {
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
        "D:\\home\\fliu\\workspace\\java\\kbase-SBMLTools-auth2\\data\\modelseed\\");
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

    for (String rxnEntry : rxnDao.getAllEntries()) {
      ModelSeedReactionEntity rxn = rxnDao.getByEntry(rxnEntry);
      if (!reactionIntegration.isBasic.apply(rxn)) {
        CompartmentalizedStoichiometry<String, Integer> cstoich = reactionIntegration.convertToStoich.apply(rxn);
        tmatcher.addReaction(cstoich, rxnEntry);
      }
    }
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
  }
}
