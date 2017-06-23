package sbmltools;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import datafileutil.DataFileUtilClient;
import datafileutil.ObjectSaveData;
import datafileutil.SaveObjectsParams;
import pt.uminho.sysbio.biosynth.integration.io.dao.neo4j.MetaboliteMajorLabel;
import pt.uminho.sysbio.biosynthframework.kbase.FBAModelFactory;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseModelSeedIntegration;
import pt.uminho.sysbio.biosynthframework.sbml.MessageType;
import pt.uminho.sysbio.biosynthframework.sbml.XmlMessage;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelValidator;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;

public class SbmlTools {
  
  private static final Logger logger = LoggerFactory.getLogger(SbmlTools.class);
  
  public final AuthToken authPart;
  public final RpcContext jsonRpcContext;
  public final URL callbackURL;
  public final String workspace;
  
  public SbmlTools(String workspace, AuthToken authPart, URL callbackURL, RpcContext jsonRpcContext) {
    this.authPart = authPart;
    this.jsonRpcContext = jsonRpcContext;
    this.callbackURL = callbackURL;
    this.workspace = workspace;
  }
  


  public static void validateSbmlImportParams(SbmlImportParams params) {
    /* Step 1 - Parse/examine the parameters and catch any errors
     * It is important to check that parameters exist and are defined, and that nice error
     * messages are returned to users.  Parameter values go through basic validation when
     * defined in a Narrative App, but advanced users or other SDK developers can call
     * this function directly, so validation is still important.
     */
    final String workspaceName = params.getWorkspaceName();
    if (workspaceName == null || workspaceName.isEmpty()) {
        throw new IllegalArgumentException(
            "Parameter workspace_name is not set in input arguments");
    }
    final String assyRef = params.getAssemblyInputRef();
    if (assyRef == null || assyRef.isEmpty()) {
        throw new IllegalArgumentException(
                "Parameter assembly_input_ref is not set in input arguments");
    }
    if (params.getMinLength() == null) {
        throw new IllegalArgumentException(
                "Parameter min_length is not set in input arguments");
    }
    final long minLength = params.getMinLength();
    if (minLength < 0) {
        throw new IllegalArgumentException("min_length parameter cannot be negative (" +
                minLength + ")");
    }
  }
  
  public static class ImportModelResult {
    public String message = "";
    public String modelRef = "";
  }
  
  public static void xrxnAttributes(XmlSbmlModelValidator validator) {
    String[] xrxnAttr = new String[] {
        "upperFluxBound", "fast", "metaid", "reversible", "sboTerm", "name", "lowerFluxBound", "id"
    };
    String[] xrxnSpecieAttr = new String[] {
        "stoichiometry", "constant", "species", "metaid", "sboTerm"
    };
    validator.xrxnAttr.addAll(Arrays.asList(xrxnAttr));
    validator.xrxnStoichAttr.addAll(Arrays.asList(xrxnSpecieAttr));
  }
  
  public static Map<String, MessageType> knownSpecieAttributes() {
    String[] attr = new String[] {
        "speciesType", "NONE",
        "charge", "NONE",
        "constant", "NONE",
        "metaid", "NONE",
        "hasOnlySubstanceUnits", "NONE",
        "sboTerm", "NONE",
        "boundaryCondition", "NONE",
        "chemicalFormula", "NONE",
        "initialAmount", "NONE",
        "name", "NONE",
        "compartment", "WARN",
        "id", "CRITICAL",
        "initialConcentration", "NONE",
    };
    Map<String, MessageType> fields = new HashMap<>();
    for (int i = 0; i < attr.length; i+=2) {
      fields.put(attr[i], MessageType.valueOf(attr[i + 1]));
    }
    return fields;
  }
  
  
  public ImportModelResult importModel(SbmlImportParams params) {
    
    logger.info("import model ...");
    ImportModelResult result = new ImportModelResult();
    String reportText = "";
    try {
      URL url = new URL(params.getUrl());
      URLConnection connection = url.openConnection();
      
//      URL url = new URL(params.getUrl());
      XmlStreamSbmlReader reader = new XmlStreamSbmlReader(connection.getInputStream());
      XmlSbmlModel xmodel = reader.parse();
//      msg = model.getAttributes().toString();
      XmlSbmlModelValidator validator = new XmlSbmlModelValidator(xmodel, knownSpecieAttributes());
      xrxnAttributes(validator);
      
      List<XmlMessage> msgs = validator.validate();
      reportText = String.format("Species %d, Reactions %s, %s", xmodel.getSpecies().size(), xmodel.getReactions().size(), params.getUrl());
//      String txt = "";
      for (XmlMessage m : msgs) {
        reportText +="\n" + String.format("%s", m);
      }
      
//      xmlsbmlmodelf
//      reportText += SbmlTools.aaa(validator.validate());
//      reportText = String.format("Species %d, Reactions %s, %s", model.getSpecies().size(), model.getReactions().size(), params.getUrl());
      
      connection.getInputStream().close();
      
      String modelId = getNameFromUrl(params.getUrl());
      FBAModel kmodel = new FBAModelFactory()
          .withXmlSbmlModel(xmodel)
          .withModelId(modelId)
          .build();
      
      String a = "/data/integration/export";
      String b = "/data/integration/cc/cpd_curation.tsv";
      boolean autoIntegration = true;
      if (autoIntegration) {
        //make integrated model
        String imodelEntry = "i" + modelId;
        KBaseModelSeedIntegration integration = new KBaseModelSeedIntegration(a, b);
        Map<String, Map<MetaboliteMajorLabel, String>> refs = 
            integration.generateDatabaseReferences(xmodel, imodelEntry);
        Map<String, String> spiToModelSeedReference = new HashMap<> ();
        for (String id : refs.keySet()) {
          String ref = refs.get(id).get(MetaboliteMajorLabel.ModelSeed);
          if (ref != null) {
            spiToModelSeedReference.put(id, ref);
          }
        }
        FBAModel ikmodel = new FBAModelFactory()
            .withModelSeedReference(spiToModelSeedReference)
            .withXmlSbmlModel(xmodel)
            .withModelId(imodelEntry)
            .build();
        
        String imodelRef = this.saveData(imodelEntry, KBaseType.FBAModel.value(), ikmodel);
      }
      
      Object kmedia = MockData.mockMedia();
      this.saveData("mock_media2", KBaseType.KBaseBiochemMedia.value(), kmedia);
      logger.info("save model [{}]", modelId);
      String modelRef = this.saveData(modelId, KBaseType.FBAModel.value(), kmodel);
      
//      FbaToolsClient fbaToolsClient = new FbaToolsClient(callbackURL, authPart);
//      RunFluxBalanceAnalysisParams fbaParams = new RunFluxBalanceAnalysisParams()
//          .withFbamodelId(modelId)
//          .withFbamodelWorkspace("")
//          .withMediaId("mock_media");
//      fbaToolsClient.runFluxBalanceAnalysis(fbaParams);
      result.modelRef = modelRef;
    } catch (Exception e) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      reportText = e.getMessage() + " " + sw.toString();
    }
    
    logger.info("import model [done]");
    
    result.message = reportText;
    
    return result;
  }
  
  
  public static String getRefFromObjectInfo(Tuple11<Long, String, String, String, 
      Long, String, Long, String, String, Long, Map<String,String>> info) {
    return info.getE7() + "/" + info.getE1() + "/" + info.getE5();
  }
  
  public String saveData(String nameId, String dataType, Object o) throws Exception {
    //  Object o = null;
    //  String nameId = "";
    //  String dataType = "";
    final DataFileUtilClient dfuClient = new DataFileUtilClient(callbackURL, authPart);
    dfuClient.setIsInsecureHttpConnectionAllowed(true);
    long wsId = dfuClient.wsNameToId(workspace);

    SaveObjectsParams params = new SaveObjectsParams()
        .withId(wsId)
        .withObjects(Arrays.asList(
            new ObjectSaveData().withName(nameId)
            .withType(dataType)
            .withData(new UObject(o))));
    ////  params.setId(wsId);
    ////  List<ObjectSaveData> saveData = new ArrayList<> ();
    ////  ObjectSaveData odata = new ObjectSaveData();
    ////  odata.set
    ////  
    ////  params.setObjects(saveData);
    ////  ;
    String ref = getRefFromObjectInfo(dfuClient.saveObjects(params).get(0));

    return ref;
  }

  public static String getNameFromUrl(String urlStr) {
    String[] strs = urlStr.split("/");
    String last = strs[strs.length - 1];
    if (last.contains(".")) {
      last = last.substring(0, last.indexOf('.'));
    }
    return last;
  }
  
//  public FBAModel convertModel(XmlSbmlModel xmodel, String modelId) {
//    
//
//    return model;
//  }
  

}
