package sbmltools.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import kbasefba.FBAModel;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.integration.model.ConnectedComponents;
import pt.uminho.sysbio.biosynthframework.io.FileImport;
import pt.uminho.sysbio.biosynthframework.kbase.FBAModelFactory;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelSeedIntegration;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;

public class LocalTest {
  

  
  public static void main(String[] args) {
    String a = "/var/biobase/export";
    String b = "/var/biobase/integration/cc/cpd_curation.tsv";

    String sbmlPath = "/var/biomodels/joana_bigg_old/iJO1366_bigg2.xml";
    sbmlPath = "/var/biomodels/joana_bigg_old/iSB619_bigg2.xml";
//    sbmlPath = "/var/biomodels/sbml/Ec_core_flux1.xml";
    try {
      XmlStreamSbmlReader reader = new XmlStreamSbmlReader(sbmlPath);
      XmlSbmlModel xmodel = reader.parse();
      
      KBaseModelSeedIntegration integration = new KBaseModelSeedIntegration(a, b);
      integration.generateDatabaseReferences(xmodel, "teh_model");;
      Map<String, String> spiToModelSeedReference = integration.spiToModelSeedReference;
      
      FBAModel fbaModel = new FBAModelFactory()
          .withModelSeedReference(spiToModelSeedReference)
          .withXmlSbmlModel(xmodel)
          .build();

      ObjectMapper om = new ObjectMapper();
      om.enable(SerializationFeature.INDENT_OUTPUT);
      System.out.println(om.writeValueAsString(fbaModel));
      System.out.println("model" + fbaModel);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
}
