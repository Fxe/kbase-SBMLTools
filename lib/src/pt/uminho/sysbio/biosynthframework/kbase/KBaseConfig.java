package pt.uminho.sysbio.biosynthframework.kbase;

public class KBaseConfig {
  public static final String TEMPLATE_WORKSPACE = "NewKBaseModelTemplates";
  
  public static final String T_G_POS = "GramPosModelTemplate";
  public static final String T_G_NEG = "GramNegModelTemplate";
  public static final String T_CORE = "CoreModelTemplate";
  
  public static final String REF_EMPTY_GENOME = "PlantSEED/Empty";
  
  public static final String REF_TEMPLATE_G_NEG = TEMPLATE_WORKSPACE + "/" + T_G_NEG;
  public static final String REF_TEMPLATE_G_POS = TEMPLATE_WORKSPACE + "/" + T_G_POS;
  public static final String REF_TEMPLATE_CORE =  TEMPLATE_WORKSPACE + "/" + T_CORE;
  
  public static String DATA_EXPORT_PATH = "/data/integration/export";
  public static String CURATION_DATA = "/data/integration/cc/cpd_curation.tsv";
  
  public static String REPORT_OUTPUT_FILE = "/kb/module/data/readerData.json";
  public static String REPORT_OUTPUT_PATH = "/kb/module/data/";
}
