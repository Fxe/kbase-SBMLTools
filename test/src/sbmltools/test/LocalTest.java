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
import pt.uminho.sysbio.biosynthframework.kbase.FBAModelFactory;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import sbmltools.SbmlImporterParams;
import sbmltools.SbmlTools;
import sbmltools.SbmlTools.ImportModelResult;
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
        .withXmlSbmlModel(xmodel)
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
    
    
    String urlPath = sbmlPath;
    InputStream is = null; //file input stream
    ZipInputStream zis = null; //zip file manipulator
    ZipFile zf = null; //zip file pointer
    InputStream rfis = null; //file within zip file pointer
    String local = SbmlTools.fetchAndCache(sbmlPath);
    
    
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
  
  
  public static void main(String[] args) {
    
    SbmlTools.DATA_EXPORT_PATH = a;
    SbmlTools.CURATION_DATA = b;
    SbmlTools.LOCAL_CACHE = "/tmp/argonne";
    String sbmlPath = "http://193.137.11.210/models/biomodels/sbml/msb201165-sup-0003.xml";
//    sbmlPath = "http://193.137.11.210/models/biomodels/test_models.zip";
//    sbmlPath = "http://193.137.11.210/models/biomodels/joana/iSH335.xml";
    try {
      SbmlTools sbmlTools = new SbmlTools("", null, null, null);
      List<String> biomass = new ArrayList<> ();
      biomass.add("R_R07230_B");
      biomass.add("R_R374");
      SbmlImporterParams params = new SbmlImporterParams()
          .withAutomaticallyIntegrate(1L)
          .withModelName(null)
          .withSbmlUrl(sbmlPath)
          .withBiomass(biomass);
      ImportModelResult r = sbmlTools.importModel(params);
      System.out.println(r.message);
    } catch (UnauthorizedException | IOException e) {
      e.printStackTrace();
    }
  }
}
