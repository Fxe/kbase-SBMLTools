package sbmltools.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbasefba.FBAModel;
import kbasereport.WorkspaceObject;
import pt.uminho.sysbio.biosynthframework.kbase.FBAModelFactory;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseConfig;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseIOUtils;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseSbmlImporter;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseSbmlImporter.ImportModelResult;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseUtils;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import sbmltools.SbmlImportParams;
import sbmltools.SbmlImporterParams;
import us.kbase.common.service.UnauthorizedException;

public class LocalTest {
  
  private static final Logger logger = LoggerFactory.getLogger(LocalTest.class);
  
  public static String a = "/var/biobase/export";
  public static String b = "/var/biobase/integration/cc/cpd_curation.tsv";
  
  public static void test(InputStream is) throws IOException {
    XmlStreamSbmlReader reader = new XmlStreamSbmlReader(is);
    XmlSbmlModel xmodel = reader.parse();
    
//    KBaseModelSeedIntegration integration = new KBaseModelSeedIntegration(a, b);
//    integration.generateDatabaseReferences(xmodel, "teh_model");;
//    Map<String, String> spiToModelSeedReference = integration.spiToModelSeedReference;
    
    FBAModel fbaModel = new FBAModelFactory()
//        .withModelSeedReference(spiToModelSeedReference)
        .withXmlSbmlModel(xmodel, false)
        .build();
    System.out.println(fbaModel.getModelcompartments());

//    ObjectMapper om = new ObjectMapper();
//    om.enable(SerializationFeature.INDENT_OUTPUT);
//    System.out.println(om.writeValueAsString(fbaModel));
//    System.out.println("model" + fbaModel);
  }
  
  public static void test1() {


    String sbmlPath = "/var/biomodels/joana_bigg_old/iJO1366_bigg2.xml";
    sbmlPath = "/var/biomodels/joana_bigg_old/iSB619_bigg2.xml";
    sbmlPath = "/var/biomodels/2batch/iJB785.xml";
    sbmlPath = "/tmp/joana_model/test_models.zip";
//    sbmlPath = "http://193.137.11.210/models/biomodels/sbml/msb201165-sup-0003.xml";
    sbmlPath = "http://193.137.11.210/models/biomodels/test_models.zip";
//    sbmlPath = "/var/biomodels/sbml/Ec_core_flux1.xml";
    sbmlPath = "http://darwin.di.uminho.pt/fliu/kbase/kbase_published_models.zip";
    
    String urlPath = sbmlPath;
    InputStream is = null; //file input stream
    ZipInputStream zis = null; //zip file manipulator
    ZipFile zf = null; //zip file pointer
    InputStream rfis = null; //file within zip file pointer
    String local = KBaseIOUtils.fetchAndCache(sbmlPath, KBaseSbmlImporter.LOCAL_CACHE);
    
    
    if (urlPath.endsWith(".zip") && local != null) {
      try {
//        URL url = new URL(urlPath);
//        URI uri = new URI(urlPath);
//        URLConnection connection = url.openConnection();
//        is = connection.getInputStream();
        is = new FileInputStream(local);
//        File zipFile = new File(uri);
//        FileUtils.copyURLToFile(url, zipFile);
        zis = new ZipInputStream(is);
        zf = new ZipFile(local);
        
        ZipEntry ze = null;
//        zf = new 
        while ((ze = zis.getNextEntry()) != null) {
          Map<String, Object> record = new HashMap<> ();
          record.put("name", ze.getName());
          record.put("size", ze.getSize());
          record.put("compressed_size", ze.getCompressedSize());
          record.put("method", ze.getMethod());
          System.out.println(record);
          try {
            rfis = zf.getInputStream(ze);
//            System.out.println(IOUtils.readLines(rfis).size());
            System.out.println(record.get("name"));
            test(rfis);
          } catch (Exception e) {
            e.printStackTrace();
          } finally {
            IOUtils.closeQuietly(rfis);
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        rfis = new FileInputStream(urlPath);
        test(rfis);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(rfis);
      }
    }
    
//    try {
////      URL url = new URL("http://193.137.11.210/models/biomodels/sbml/msb201165-sup-0003.xml");
////      URLConnection connection = url.openConnection();
//
//      
//      
//      
//
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
    
  }
  
  
  public static void integrationTest() {
    KBaseConfig.DATA_EXPORT_PATH = a;
    KBaseConfig.CURATION_DATA = b;
    KBaseSbmlImporter.LOCAL_CACHE = "/tmp/argonne/data";
    String sbmlPath = "http://193.137.11.210/models/biomodels/sbml/msb201165-sup-0003.xml";
    sbmlPath = "http://193.137.11.210/models/biomodels/test_models.zip";
    sbmlPath = "http://193.137.11.210/models/biomodels/test_models_with_invalid_sbml.zip";
//    sbmlPath = "http://193.137.11.210/models/biomodels/joana/iSH335.xml";
    sbmlPath = "http://193.137.11.210/models/biomodels/sbml/Ec_core_flux1.xml";
//    sbmlPath = "http://127.0.0.1/models/biomodels/sbml/hsa/MODEL1109130000.xml";
//    sbmlPath = "http://darwin.di.uminho.pt/fliu/kbase/kbase_published_models.zip";
    
    try {
      KBaseSbmlImporter sbmlTools = new KBaseSbmlImporter(null, null, null);
      List<String> biomass = new ArrayList<> ();
      biomass.add("R_R07230_B");
      biomass.add("R_R374");
      SbmlImporterParams params = new SbmlImporterParams()
          .withAutomaticallyIntegrate(1L)
          .withRemoveBoundary(1L)
          .withModelName(null)
          .withSbmlUrl(sbmlPath)
          .withBiomass(biomass);
      ImportModelResult r = sbmlTools.importModel(params);
      System.out.println(r.message);
      System.out.println(r.objects);
      for (WorkspaceObject wso : r.objects) {
        System.out.println(wso);
      }
    } catch (UnauthorizedException | IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void dataTest() {
    KBaseConfig.DATA_EXPORT_PATH = a;
    KBaseConfig.CURATION_DATA = b;
    KBaseSbmlImporter.LOCAL_CACHE = "/tmp/argonne";
    try {
      KBaseSbmlImporter sbmlTools = new KBaseSbmlImporter(null, null, null);
      SbmlImportParams params = new SbmlImportParams()
          .withAssemblyInputRef("111")
          .withMinLength(10L)
          .withUrl("url");
      ImportModelResult result = sbmlTools.debugThings(params);
      System.out.println(result.message);
    } catch (UnauthorizedException | IOException e) {
      e.printStackTrace();
    }
  }
  

  
  public static void main(String[] args) {
    integrationTest();
//    dataTest();
//    test1();
    
//    System.out.println(KBaseUtils.getGenes("(a1 and a2 )"));
//    System.out.println(KBaseUtils.getGenes("(a1 a2 )"));
    
//    try {
//      //iMO1056.xml
//      //
//      test(new FileInputStream("/var/biomodels/sbml/Ec_core_flux1.xml"));
//      test(new FileInputStream("/var/biomodels/joana/iMO1056.xml"));
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
  }
}
