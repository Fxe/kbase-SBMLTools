package pt.uminho.sysbio.biosynthframework.kbase;

import pt.uminho.sysbio.biosynthframework.io.biodb.modelseed.GithubModelSeedMetaboliteDaoImpl;
import pt.uminho.sysbio.biosynthframework.io.biodb.modelseed.GithubModelSeedReactionDaoImpl;
import pt.uminho.sysbio.biosynthframework.kbase.app.KBaseSbmlImporter;
import us.kbase.biosynthframework.ReactionIntegrationDriver;

public class KBaseConfig {
  public static final String TEMPLATE_WORKSPACE = "NewKBaseModelTemplates";
  
  public static final String T_G_POS = "GramPosModelTemplate";
  public static final String T_G_NEG = "GramNegModelTemplate";
  public static final String T_CORE = "CoreModelTemplate";
  
  public static final String REF_EMPTY_GENOME = "PlantSEED/Empty";
  
  public static final String REF_TEMPLATE_G_NEG = TEMPLATE_WORKSPACE + "/" + T_G_NEG;
  public static final String REF_TEMPLATE_G_POS = TEMPLATE_WORKSPACE + "/" + T_G_POS;
  public static final String REF_TEMPLATE_CORE =  TEMPLATE_WORKSPACE + "/" + T_CORE;
  
  public static String DATA_EXPORT_PATH = "/kb/module/data/export";
  public static String CURATION_DATA = "/kb/module/data/cpd_curation.tsv";
  
  public static String REPORT_OUTPUT_FILE = "/kb/module/data/readerData.json";
  public static String REPORT_OUTPUT_PATH = "/kb/module/data/";
  
  public static String BLAST_DB_PATH = "/kb/module/data/blast_db.faa";
  
  public static final String REF_GENOME_WORLSPACE = "ReferenceDataManager";
  public static final String REF_PMODEL_WORLSPACE = "filipeliu:narrative_1504796314698";
 
  public static final String version = "48c089f4f0128ed3c06ce716750693b4feccb623";
  public static String dbPath = "/kb/module/data/modelseed";
  public static final String dbPathLocal = "/home/fliu/workspace/java/kbase-SBMLTools-auth2/data/modelseed";
  public static boolean production = true;
  
  public static GithubModelSeedMetaboliteDaoImpl cpdDao;
  public static GithubModelSeedReactionDaoImpl rxnDao;
 
  public static GithubModelSeedMetaboliteDaoImpl getModelSeedCpdDao() {
    return getModelSeedCpdDao(production);
  }
  
  public static GithubModelSeedReactionDaoImpl getModelSeedRxnDao() {
    return getModelSeedRxnDao(production);
  }
  
  public static void setupLocalPaths() {
    KBaseConfig.DATA_EXPORT_PATH = "/var/biobase/export";
    KBaseConfig.CURATION_DATA = "/var/biobase/integration/cc/cpd_curation.tsv";
    KBaseConfig.REPORT_OUTPUT_FILE = "/tmp/argonne/report/readerData.json";
    KBaseConfig.REPORT_OUTPUT_PATH = "/tmp/argonne/report/";
    KBaseSbmlImporter.LOCAL_CACHE = "/tmp/argonne/data";
    ReactionIntegrationDriver.modelseedDbPath = "D:\\home\\fliu\\workspace\\java\\kbase-SBMLTools-auth2\\data\\modelseed\\";
    production = false;
  }
  
  public static GithubModelSeedMetaboliteDaoImpl getModelSeedCpdDao(boolean production) {
    if (cpdDao == null) {
      cpdDao = new GithubModelSeedMetaboliteDaoImpl(version);
      if (production) {
        cpdDao.path = dbPath;
      } else {
        cpdDao.path = dbPathLocal;
      }
    }
    
    return cpdDao;
  }
  
  public static GithubModelSeedReactionDaoImpl getModelSeedRxnDao(boolean production) {
    if (rxnDao == null) {
      rxnDao = new GithubModelSeedReactionDaoImpl(version);
      if (production) {
        rxnDao.path = dbPath;
      } else {
        rxnDao.path = dbPathLocal;
      }
    }
    
    return rxnDao;
  }
}
