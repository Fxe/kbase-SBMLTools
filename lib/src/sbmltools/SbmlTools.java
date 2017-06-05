package sbmltools;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assemblyutil.AssemblyUtilClient;
import assemblyutil.FastaAssemblyFile;
import assemblyutil.GetAssemblyParams;
import assemblyutil.SaveAssemblyParams;
import datafileutil.DataFileUtilClient;
import datafileutil.ObjectSaveData;
import datafileutil.SaveObjectsParams;
import fbatools.FbaToolsClient;
import fbatools.RunFluxBalanceAnalysisParams;
import pt.uminho.sysbio.biosynthframework.sbml.MessageType;
import pt.uminho.sysbio.biosynthframework.sbml.XmlMessage;
import pt.uminho.sysbio.biosynthframework.sbml.XmlObject;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlCompartment;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModel;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlModelValidator;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlReaction;
import pt.uminho.sysbio.biosynthframework.sbml.XmlSbmlSpecie;
import pt.uminho.sysbio.biosynthframework.sbml.XmlStreamSbmlReader;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;
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
  
  public SbmlTools(String workspace, AuthToken authPart, URL callbackURL, RpcContext jsonRpcContext) {
    this.authPart = authPart;
    this.jsonRpcContext = jsonRpcContext;
    this.callbackURL = callbackURL;
    this.workspace = workspace;
  }
  
  public static enum Orientation {
    LeftToRight, RightToLeft, Reversible, Unknown, 
    Fixed, //fixed constant non zero 
    Zero, //zero constant
    Range, //zero not included
}
  
  /**
   * Determine reaction reversibility
   */
  public static String[] validateReactionContraint(String rxnEntry, XmlSbmlModel xmodel, XmlSbmlReaction xrxn, List<XmlObject> rxnParameters) {
    //    System.out.println(rxnAttributes);

    String lb = null;
    String ub = null;
    
//    System.out.println(xrxn.getAttributes());
    
    for (XmlObject xo : rxnParameters) {
//      System.out.println(xo.getAttributes());
      String id = xo.getAttributes().get("id");
      if (id == null) {
//        this.messages.add(new XmlMessage(xo, MessageType.WARN, "parameter without ID (rxn: %s)", rxnEntry));
        logger.debug("parameter without ID {}", xo.getAttributes());
      } else {
        String value = xo.getAttributes().get("value");
        //      String units = xo.getAttributes().get("units");
        //      System.out.println("\t\t:\t" + xo.getAttributes());
        switch (id.toUpperCase()) {
        case "OBJECTIVE_COEFFICIENT":
//          this.defaultObjective.put(rxnEntry, value);
          break;
        case "LOWER_BOUND":
          lb = value;
          break;
        case "UPPER_BOUND":
          ub = value;
          break;
        default:
//          CollectionUtils.increaseCount(ignoredParameter, id, 1);
          logger.debug("ignored {}", id);
          break;
        }
      }
    }
    
    if (xrxn.getAttributes().containsKey("upperFluxBound")) {
      String ufbcId = xrxn.getAttributes().get("upperFluxBound");
      for (XmlObject xo : xmodel.getListOfParameters()) {
        String id = xo.getAttributes().get("id");
        if (id != null && id.equals(ufbcId)) {
          String valueStr = xo.getAttributes().get("value");
          if (ub != null && !ub.equals(valueStr)) {
            logger.warn("fbc conflict with reaction parameters [{}] -> [{}]", valueStr, ub);
          } else {
            ub = valueStr;
          }
        }
      }
    }
    
    if (xrxn.getAttributes().containsKey("lowerFluxBound")) {
      String ufbcId = xrxn.getAttributes().get("lowerFluxBound");
      for (XmlObject xo : xmodel.getListOfParameters()) {
        String id = xo.getAttributes().get("id");
        if (id != null && id.equals(ufbcId)) {
          String valueStr = xo.getAttributes().get("value");
          if (lb != null && !lb.equals(valueStr)) {
            logger.warn("fbc conflict with reaction parameters [{}] -> [{}]", valueStr, lb);
          } else {
            lb = valueStr;
          }
        }
      }
    }
    
    Orientation parametersOrientation = Orientation.Unknown;
    Orientation attributesOrientation = Orientation.Unknown;
//    if (lb == null || ub == null) {
////      logger.warn("{}, {}", lb, ub);
//    } else {
//      if (NumberUtils.isNumber(ub.trim()) && NumberUtils.isNumber(lb.trim())) {
//        double ub_ = Double.parseDouble(ub.trim());
//        double lb_ = Double.parseDouble(lb.trim());
//        if (ub_ > 0.0 && lb_ < 0.0) {
//          parametersOrientation = Orientation.Reversible;
//        } else if (lb_ == 0.0 && ub_ > 0.0){
//          parametersOrientation = Orientation.LeftToRight;
//        } else if (lb_ < 0.0 && ub_ == 0.0) {
//          parametersOrientation = Orientation.RightToLeft;
//        } else if (lb_ == 0.0 && ub_ == 0.0) {
//          logger.debug("zero [{}, {}]", lb, ub);
//          parametersOrientation = Orientation.Zero;
//        } else if (lb_ == ub_) {
//          logger.debug("fixed bounds [{}, {}]", lb, ub);
//          parametersOrientation = Orientation.Fixed;
//        } else {
//          parametersOrientation = Orientation.Range;
//          //logger.error("strange bounds [{}, {}]", lb, ub);
//        }
//
//      } else {
//        logger.error("non numeric bounds [{}, {}]", lb, ub);
//      }
//    }

    String rev = xrxn.getAttributes().get("reversible");
    if (rev != null) {
      boolean reversible = Boolean.parseBoolean(rev);
      if (reversible) {
        attributesOrientation = Orientation.Reversible;
      } else {
        attributesOrientation = Orientation.LeftToRight;
      }
    }


//    logger.debug("{} - type:{}, Attribute: {}, Parameters: {}", rxnEntry, xrxnTypeMap.get(rxnEntry), attributesOrientation, parametersOrientation);
//
//    //drains are special case we need to have lower and upper bounds !
//    if (xrxnTypeMap.get(rxnEntry).equals(EntityType.DRAIN)) {
//      if (lb == null || ub == null) {
//        logger.debug("drain reaction with undefined bounds [{}, {}] !", lb, ub);
//        this.messages.add(new XmlMessage(xrxn, MessageType.WARN, "%s drain reaction with undefined bounds [%s, %s] !", rxnEntry, lb, ub));
//      }
//      
//      //if drain assume parameters or attributes (its up to the user to define uptakes)
//      if (!parametersOrientation.equals(Orientation.Unknown)) {
//        this.rxnDirection.get(parametersOrientation).add(rxnEntry);
//      } else {
//        this.rxnDirection.get(attributesOrientation).add(rxnEntry);
//      }
//    } else if (parametersOrientation.equals(Orientation.Unknown) && attributesOrientation.equals(Orientation.Unknown)) {
//      this.rxnDirection.get(Orientation.Unknown).add(rxnEntry);
//      this.messages.add(new XmlMessage(xrxn, MessageType.WARN, "no reversiblity information assume reversible (reversible:%s, #parameters %d)", rev, rxnParameters.size()));
//      logger.debug("unable to determine orientation reversible:{}, #parameters {}", rev, rxnParameters.size());
//    } else if (parametersOrientation.equals(attributesOrientation)) {
//      logger.debug("orientation match ! {}", attributesOrientation);
//      this.rxnDirection.get(attributesOrientation).add(rxnEntry);
//    } else if (parametersOrientation.equals(Orientation.Unknown) && 
//        !attributesOrientation.equals(Orientation.Unknown)) {
//      logger.debug("using attribute orientation reversible:{} -> {}, #parameters {}", rev, attributesOrientation, rxnParameters.size());
//      this.rxnDirection.get(attributesOrientation).add(rxnEntry);
//    } else if (!parametersOrientation.equals(Orientation.Unknown) && 
//        attributesOrientation.equals(Orientation.Unknown)) {
//      logger.debug("using parameter orientation [{}, {}] -> {}, reversible:{}", lb, ub, parametersOrientation, rev);
//      this.rxnDirection.get(parametersOrientation).add(rxnEntry);
//    } else {
//      this.messages.add(new XmlMessage(xrxn, MessageType.ERROR, "invalid orientation [%s, %s] -> %s", lb, ub, xrxnTypeMap.get(rxnEntry)));
//      logger.debug("invalid orientation {}, [{}, {}] -> {}", xrxn.getAttributes(), lb, ub, xrxnTypeMap.get(rxnEntry));
//      this.rxnDirection.get(Orientation.Unknown).add(rxnEntry);
//    }
    return new String[]{lb, ub};
  }
  
  public FBAModel convertModel(XmlSbmlModel xmodel, String modelId) {
    
    FBAModel model = new FBAModel();
    model.setId(modelId);
    model.setName(modelId); //get from xml if exists
    model.setGenomeRef("4345/2/1");
    model.setSource("External");
    model.setSourceId(modelId);
    model.setType("SBML Model");
    model.setTemplateRef("50/1/1");
    model.setGapfillings(new ArrayList<ModelGapfill> ());
    model.setGapgens(new ArrayList<ModelGapgen> ());
    model.setBiomasses(new ArrayList<Biomass> ());
    model.setModelcompounds(new ArrayList<ModelCompound> ());
    model.setModelcompartments(new ArrayList<ModelCompartment> ());
    model.setModelreactions(new ArrayList<ModelReaction> ());
    
    List<String> cmpArray = new ArrayList<> ();

    cmpArray.add("c");
//    cmpArray.add("d");
    cmpArray.add("e");
    cmpArray.add("f");
    cmpArray.add("g");
    cmpArray.add("a");
    cmpArray.add("b");
    Iterator<String> cmpIt = cmpArray.iterator();
    
    Map<String, String> cmpMap = new HashMap<> ();
    for (XmlSbmlCompartment xcmp : xmodel.getCompartments()) {
      String cmpEntry = xcmp.getAttributes().get("id");
      String cmpName = xcmp.getAttributes().get("name");
      if (cmpName == null || cmpName.trim().isEmpty()) {
        cmpName = "undefined";
      }
      String cmpId = cmpIt.next();
      long cmpIndex = 0;
      String cmpIdAndIndex = cmpId + cmpIndex;
      ModelCompartment cmp = new ModelCompartment().withId(cmpIdAndIndex)
                                                   .withLabel(cmpName)
                                                   .withPH(7.3)
                                                   .withPotential(1.0)
                                                   .withCompartmentIndex(cmpIndex)
                                                   .withCompartmentRef("~/template/compartments/id/" + cmpId);
      model.getModelcompartments().add(cmp);
      cmpMap.put(cmpEntry, cmpIdAndIndex);
    }
    for (XmlSbmlSpecie xspi : xmodel.getSpecies()) {
      String spiEntry = xspi.getAttributes().get("id");
      String cmpEntry = xspi.getAttributes().get("compartment");
      String spiName = xspi.getAttributes().get("name");
      if (spiName == null || spiName.trim().isEmpty()) {
        spiName = "undefined";
      }
      ModelCompound cpd = new ModelCompound().withId(spiEntry)
                                             .withCompoundRef("~/template/compounds/id/cpd00000")
                                             .withModelcompartmentRef(
                                                 String.format("~/modelcompartments/id/%s", cmpMap.get(cmpEntry)))
                                             .withFormula("R")
                                             .withCharge(1.0)
                                             .withName(spiName);
      
      model.getModelcompounds().add(cpd);
    }
    for (XmlSbmlReaction xrxn : xmodel.getReactions()) {
      String rxnEntry = xrxn.getAttributes().get("id");
      String rxnName = xrxn.getAttributes().get("name");
      if (rxnName == null || rxnName.trim().isEmpty()) {
        rxnName = "undefined";
      }
      List<ModelReactionReagent> reagents = new ArrayList<> ();
      
      for (XmlObject o : xrxn.getListOfReactants()) {
        String species = o.getAttributes().get("species");
        //small cheat to test the ecoli model (should be decided by the integration)
        if (!species.endsWith("_b")) {
          String stoich = o.getAttributes().get("stoichiometry");
          if (stoich == null) {
            stoich = "1";
          }
          double stoichiometry = Double.parseDouble(stoich);
          ModelReactionReagent r = new ModelReactionReagent()
              .withCoefficient(-1 * stoichiometry)
              .withModelcompoundRef(String.format("~/modelcompounds/id/%s", species));
          reagents.add(r);
        }
      }
      
      for (XmlObject o : xrxn.getListOfProducts()) {
        String species = o.getAttributes().get("species");
        //small cheat to test the ecoli model (should be decided by the integration)
        if (!species.endsWith("_b")) {
          String stoich = o.getAttributes().get("stoichiometry");
          if (stoich == null) {
            stoich = "1";
          }
          double stoichiometry = Double.parseDouble(stoich);
          ModelReactionReagent r = new ModelReactionReagent()
              .withCoefficient(stoichiometry)
              .withModelcompoundRef(String.format("~/modelcompounds/id/%s", species));
          reagents.add(r);
        }
      }
      
      String rxnCmpRef = String.format("~/modelcompartments/id/%s", cmpMap.values().iterator().next());
//      System.out.println(rxnCmpRef);
//      System.out.println(reagents);
      ModelReaction rxn = new ModelReaction().withId(rxnEntry)
                                             .withAliases(new ArrayList<String> ())
                                             .withName(rxnName)
                                             .withDirection("=")
                                             .withProtons(1.0)
                                             .withReactionRef("50/1/1/reactions/id/rxn00000_c")
                                             .withModelReactionProteins(new ArrayList<ModelReactionProtein> ())
                                             .withProbability(1.0)
                                             .withPathway("entire model")
                                             .withModelcompartmentRef(rxnCmpRef);
      rxn.setModelReactionReagents(reagents);
      
//      ModelReactionProtein protein = new ModelReactionProtein();
//      protein.setModelReactionProteinSubunits(null);
//      protein.setComplexRef("");
//      protein.setSource("");
//      protein.setNote("");
//      ModelReactionProteinSubunit proteinSubunit = new ModelReactionProteinSubunit();
////      proteinSubunit.setFeatureRefs(featureRefs);
////      proteinSubunit.setOptionalSubunit(optionalSubunit);
//      proteinSubunit.setRole("");
//      proteinSubunit.setNote("");
//      proteinSubunit.setTriggering(0L);
//      
//      List<ModelReactionProtein> proteins = new ArrayList<> ();
//      proteins.add(protein);
//      rxn.setModelReactionProteins(proteins);
//      
//      //??
//      rxn.setDirection("=");
//      //LB, UB
//      xrxn.get
      String[] boundStr = validateReactionContraint(rxnEntry, xmodel, xrxn, xrxn.getListOfParameters());
      
      double lb = -1000;
      double ub =  1000;
      try {
        lb = Double.parseDouble(boundStr[0]);
      } catch (Exception e) {
        
      }
      try {
        ub = Double.parseDouble(boundStr[1]);
      } catch (Exception e) {
        
      }
      rxn.setMaxrevflux(lb);
      rxn.setMaxforflux(ub);
      System.out.println(rxnEntry + " " + lb + " " + ub);
      
      model.getModelreactions().add(rxn);
      
    }
    
//    //setup biomasses if any detected
//    Biomass biomass = new Biomass();
//    biomass.setId("biomass1");
//    BiomassCompound bspi1 = new BiomassCompound();
//    bspi1.setCoefficient(1.0);
//    bspi1.setModelcompoundRef("spiRef");
//    biomass.getBiomasscompounds().add(bspi1);
//    model.getBiomasses().add(biomass);

    
    return model;
  }
  

  
  public static String getRefFromObjectInfo(Tuple11<Long, String, String, String, 
      Long, String, Long, String, String, Long, Map<String,String>> info) {
    return info.getE7() + "/" + info.getE1() + "/" + info.getE5();
  }
  
  public String saveData(String nameId, String dataType, Object o) throws Exception {
//    Object o = null;
//    String nameId = "";
//    String dataType = "";
    final DataFileUtilClient dfuClient = new DataFileUtilClient(callbackURL, authPart);
    dfuClient.setIsInsecureHttpConnectionAllowed(true);
    long wsId = dfuClient.wsNameToId(workspace);
    
    SaveObjectsParams params = new SaveObjectsParams()
        .withId(wsId)
        .withObjects(Arrays.asList(
            new ObjectSaveData().withName(nameId)
                                .withType(dataType)
                                .withData(new UObject(o))));
////    params.setId(wsId);
////    List<ObjectSaveData> saveData = new ArrayList<> ();
////    ObjectSaveData odata = new ObjectSaveData();
////    odata.set
////    
////    params.setObjects(saveData);
////    ;
    String ref = getRefFromObjectInfo(dfuClient.saveObjects(params).get(0));
    
    return ref;
  }
  
  public String filterContigs(String assyRef, Path scratch) throws Exception {
    /* Step 2 - Download the input data as a Fasta file
     * We can use the AssemblyUtils module to download a FASTA file from our Assembly data
     * object. The return object gives us the path to the file that was created.
     */
    System.out.println("Downloading assembly data as FASTA file.");
//    final AssemblyUtilClient assyUtil = new AssemblyUtilClient(callbackURL, authPart);
    /* Normally this is bad practice, but the callback server (which runs on the same machine
     * as the docker container running the method) is http only
     * TODO Should allow the clients to not require a token, even for auth required methods,
     * since the callback server ignores the incoming token. No need to transmit the token
     * here.
     */
//    assyUtil.setIsInsecureHttpConnectionAllowed(true);
//    final FastaAssemblyFile fileobj = assyUtil.getAssemblyAsFasta(new GetAssemblyParams()
//            .withRef(assyRef));
    
    /* Step 3 - Actually perform the filter operation, saving the good contigs to a new
     * fasta file.
     */
//    final Path out = scratch.resolve("filtered.fasta");
//    long total = 0;
//    long remaining = 0;
//    try (final FASTAFileReader fastaRead = new FASTAFileReaderImpl(
//                new File(fileobj.getPath()));
//            final FASTAFileWriter fastaWrite = new FASTAFileWriter(out.toFile())) {
//        final FASTAElementIterator iter = fastaRead.getIterator();
//        while (iter.hasNext()) {
//            total++;
//            final FASTAElement fe = iter.next();
//            if (fe.getSequenceLength() >= minLength) {
//                remaining++;
//                fastaWrite.write(fe);
//            }
//        }
//    }
//    SaveObjectsParams saveObjectsParams = new SaveObjectsParams();
//    List<ObjectSaveData> objects = null;
//    ObjectSaveData odata = new ObjectSaveData();
//    odata.setType("kbase.asds");
//    UObject udata = new UObject("");
//    odata.setData(udata);
////    odata.setName(name);
////    saveObjectsParams.set
//    saveObjectsParams.setObjects(objects);
//    
//    DataFileUtilClient dataFileUtilClient = new DataFileUtilClient(callbackURL, authPart);
//    long wsid = dataFileUtilClient.wsNameToId(workspaceName);
//    dataFileUtilClient.saveObjects(saveObjectsParams);
    // Step 4 - Save the new Assembly back to the system
    final String newAssyRef = assyRef;
//    final String newAssyRef = assyUtil.saveAssemblyFromFasta(new SaveAssemblyParams()
//        .withAssemblyName(fileobj.getAssemblyName() + "_new")
//        .withWorkspaceName(workspace)
//        .withFile(new FastaAssemblyFile().withPath(fileobj.getPath())));
    
    return newAssyRef;
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
  
  public static String getNameFromUrl(String urlStr) {
    String[] strs = urlStr.split("/");
    String last = strs[strs.length - 1];
    if (last.contains(".")) {
      last = last.substring(0, last.indexOf('.'));
    }
    return last;
  }
  
  public static class ImportModelResult {
    public String message = "";
    public String modelRef = "";
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
//      for (XmlMessage m : msgs) {
//        reportText +="\n" + String.format("%s", m);
//      }
      
//      xmlsbmlmodelf
//      reportText += SbmlTools.aaa(validator.validate());
//      reportText = String.format("Species %d, Reactions %s, %s", model.getSpecies().size(), model.getReactions().size(), params.getUrl());
      
      connection.getInputStream().close();
      
      String modelId = getNameFromUrl(params.getUrl());
      FBAModel kmodel = this.convertModel(xmodel, modelId);
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
  
//  public static String aaa(List<XmlMessage> msgs) {
//    String txt = "";
//    for (XmlMessage m : msgs) {
//      txt +="\n" + String.format("%s", m);
//    }
//    
//    return txt;
//  }
}
