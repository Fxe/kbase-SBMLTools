package pt.uminho.sysbio.biosynthframework.kbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

import datafileutil.DataFileUtilClient;
import genomeannotationapi.GenomeAnnotationAPIClient;
import genomeannotationapi.SaveGenomeResultV1;
import genomeannotationapi.SaveOneGenomeParamsV1;
import genomeproteomecomparison.GenomeProteomeComparisonClient;
import kbasefba.FBAModel;
import kbasefba.ModelReaction;
import kbasefba.ModelReactionProtein;
import kbasefba.ModelReactionProteinSubunit;
import kbasegenomes.Feature;
import kbasegenomes.Genome;
import kbasereport.CreateParams;
import kbasereport.KBaseReportClient;
import kbasereport.Report;
import kbasereport.ReportInfo;
import kbasereport.WorkspaceObject;
import pt.uminho.sysbio.biosynthframework.genome.NAlignTool;
import pt.uminho.sysbio.biosynthframework.kbase.KBaseHtmlReport.ReportFiles;
import pt.uminho.sysbio.biosynthframework.kbase.genome.AlignmentKernel;
import pt.uminho.sysbio.biosynthframework.kbase.genome.AlignmentKernel.AlignmentJob;
import pt.uminho.sysbio.biosynthframework.kbase.report.PropagationReport;
import pt.uminho.sysbio.biosynthframework.util.DataUtils;
import pt.uminho.sysbio.biosynthframework.kbase.genome.KbaseGenomeUtils;
import sbmltools.AutoPropagateModelParams;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.WorkspaceClient;

public class AutoPropagateGenomeFacade {
  
  private static final Logger logger = LoggerFactory.getLogger(AutoPropagateGenomeFacade.class);
  
  private final NAlignTool alignTool;
//  /kb/module/data/
  public static String BLAST_DB_PATH = "/kb/module/data/blast_db.faa";
  
  public static final String REF_GENOME_WORLSPACE = "ReferenceDataManager";
  public static final String REF_PMODEL_WORLSPACE = "filipeliu:narrative_1504796314698";
  
  public String modelToShow = "";
  
  private int p = 2;
  private String genomeId;
  private String workspace;
  private final WorkspaceClient wsClient;
  private final DataFileUtilClient dfuClient;
  private final GenomeAnnotationAPIClient gaClient;
  private final KBaseReportClient kbrClient;
  private final EasyKBase easyKBase;
  public PropagationReport report;
  private final Path scratch;
  
  public AutoPropagateGenomeFacade(AutoPropagateModelParams params, 
      WorkspaceClient wsClient,
      KBaseReportClient kbrClient,
      URL callbackUrl, AuthToken token, Path scratch) throws IOException, UnauthorizedException {
    this.alignTool = new NAlignTool(KbaseGenomeUtils.NUC44);
    this.genomeId = params.getGenomeId();
    this.workspace = params.getWorkspaceName();
    this.wsClient = wsClient;
    this.kbrClient = kbrClient;
    this.dfuClient = new DataFileUtilClient(callbackUrl, token);
    this.gaClient = new GenomeAnnotationAPIClient(callbackUrl, token);
    this.dfuClient.setIsInsecureHttpConnectionAllowed(true);
    this.gaClient.setIsInsecureHttpConnectionAllowed(true);
    easyKBase = new EasyKBase(callbackUrl, token);
    this.scratch = scratch;
  }
  
  public static class PropagationTask {
    public String genomeId;
    public String genomeWs;
    public String modelId;
    public String modelWs;
    public String pcompId;
    public String pcompWs;
    public String pmodelId;
    public String pmodelWs;
    
    @Override
    public String toString() {
      return String.format("%s %s %s", genomeId, modelId, pcompId);
    }
  }
  
  public List<KBaseId> listModels(String ws) throws IOException {
    List<KBaseId> result = new ArrayList<> ();
    List<String> workspaces = new ArrayList<> ();
    workspaces.add(ws);
    try {
      ListObjectsParams lparams = new ListObjectsParams().withWorkspaces(workspaces);
      lparams.withType("KBaseFBA.FBAModel");
      List<Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>>> o = 
      wsClient.listObjects(lparams);
      for (Tuple11<Long,String,String,String,Long,String,Long,String,String,Long,Map<String,String>> t : o) {
        String oref = String.format("%d/%d/%d", t.getE7(), t.getE1(), t.getE5());
        String oname = t.getE2();
        String ows = t.getE8();
        KBaseId kid = new KBaseId(oname, ows, oref);
        result.add(kid);
      }
    } catch (IOException | JsonClientException e) {
      throw new IOException(e);
    }
    
    return result;
  }
  
  public ReportInfo run() {
    
    PropagationReport htmlReportData = new PropagationReport(p);
    
    String out = "";
    Map<String, String> outputObjects = new HashMap<> ();
    try {
      List<KBaseId> repomodels = listModels(REF_PMODEL_WORLSPACE);
      Set<String> imodels = new HashSet<> ();
      for (KBaseId kid : repomodels) {
        if (kid.name.startsWith("kb.")) {
          imodels.add(kid.name.replace("kb.", ""));
        }
      }
      
      InputStream is = new FileInputStream(BLAST_DB_PATH);
      Map<String, DNASequence> seqs = FastaReaderHelper.readFastaDNASequence(is);
      //model + locus
//      Map<String, Genome> seqsGenome_ = new HashMap<> ();
      Map<String, Set<String>> genomeToModels = new HashMap<> ();
      for (String k : seqs.keySet()) {
        String h[] = seqs.get(k).getOriginalHeader().split("\\|");
        Set<String> models = new HashSet<> (Arrays.asList(h[3].split(";")));
        models = Sets.intersection(models, imodels);
        //filter models
//        seqsGenome.put(k, null);
        genomeToModels.put(k, models);
      }
      
      Pair<KBaseId, Object> data = KBaseIOUtils.getObject2(genomeId, workspace, null, wsClient);
      Genome genome = KBaseUtils.convert(data.getRight(), Genome.class);
      KBaseId targetGenomeKid = data.getLeft();
      Feature poly = null;
      if (genome != null && 
          (poly = KbaseGenomeUtils.findRnaPolymeraseBetaUnit(genome)) != null) {
        AlignmentKernel ma = new AlignmentKernel(alignTool, 4);
        String dnaA = poly.getDnaSequence();
        
        for (String k : seqs.keySet()) {
          AlignmentJob job = new AlignmentJob();
          job.dna1 = dnaA;
          job.dna2 = seqs.get(k).getSequenceAsString();
          job.genome1 = genome.getId();
          job.genome2 = k;
          job.targetOrganism = seqs.get(k).getOriginalHeader().split("\\|")[2];
          ma.jobs.add(job);
        }
        
        ma.run();
        
        Map<Double, Set<AlignmentJob>> sortedResults = ma.getSortedResults();
        for (Double score : sortedResults.keySet()) {
          for (AlignmentJob job : sortedResults.get(score)) {
            out += "\n" + score + ", " + job.targetOrganism + ", " + job.genome2 + " " + genomeToModels.get(job.genome2);
            htmlReportData.add(job.targetOrganism, score);
          }
        }
        
        Iterator<Double> it = sortedResults.keySet().iterator();
        List<PropagationTask> genomesToCompare = new ArrayList<> ();
        
      //collect the best genomes
        while (it.hasNext() && genomesToCompare.size() < p) {
          try {
            double score = it.next();
            AlignmentJob job = sortedResults.get(score).iterator().next();
            
            PropagationTask ptask = new PropagationTask();
            ptask.genomeId = job.genome2;
            //Get genome FBAModel
            ptask.genomeWs = REF_GENOME_WORLSPACE;
            ptask.modelWs = REF_PMODEL_WORLSPACE;
            Set<String> g = genomeToModels.get(job.genome2);
            if (g != null && !g.isEmpty()) {
              ptask.modelId = g.iterator().next();
            }
            
            if (!DataUtils.empty(ptask.modelId)) {
              logger.info("Added to proteome compare: {} [{}]", job, ptask.modelId);
              genomesToCompare.add(ptask);
            } else {
              logger.warn("skip: {}", ptask);
            }
            
//            Pair<KBaseId, Object> data_ = KBaseIOUtils.getObject2(job.genome2, workspace, null, wsClient);
//            Genome otherGenome = KBaseUtils.convert(data_.getRight(), Genome.class);
//            genomesToCompare.add(otherGenome);
          } catch (Exception e) {
            logger.error("{}", e.getMessage());
          }
//          Genome otherGenome = KBaseIOUtils.getObject2(genomeId, workspace, null, wsClient);
        }
        
        Map<String, String> proteomes = new HashMap<> ();
        
        for (PropagationTask ptask : genomesToCompare) {
          KBaseId genome2 = new KBaseId(ptask.genomeId, ptask.genomeWs, null);
          String other = ptask.genomeId;
          if (other.endsWith(".rast")) {
            other = other.replace(".rast", "");
            ptask.genomeId = other;
            genome2 = new KBaseId(other, genome2.workspace, genome2.reference);
          }
          KBaseId kout = new KBaseId(
              String.format("%s_%s", targetGenomeKid.name, ptask.genomeId), workspace, null);
          logger.info("compareProteomes: {} start", kout);
          String res = easyKBase.compareProteomes(targetGenomeKid, genome2, kout);
          if (!DataUtils.empty(res)) {
            outputObjects.put(res, String.format("%s - %s", targetGenomeKid.name, genome2.name));
            ptask.pcompId = kout.name;
            ptask.pcompWs = workspace;
          }
          
          logger.info("compareProteomes: {} done! {}", kout, res);
        }
        
        for (PropagationTask ptask : genomesToCompare) {
          String fbaModelId = ptask.modelId;
          if (!fbaModelId.startsWith("kb.")) {
            fbaModelId = "kb." + fbaModelId;
          }
          String fbaModelRepo = ptask.modelWs;
          String pmodelId = ptask.pcompId + ".fbamodel";
          String modelRef = easyKBase.propagateModelToNewGenome(fbaModelId, fbaModelRepo, ptask.pcompId, ptask.pcompWs, pmodelId, workspace);
          if (!DataUtils.empty(modelRef)) {
            modelToShow = ptask.pcompId + ".fbamodel";
            outputObjects.put(modelRef, "model");
            ptask.pmodelId = pmodelId;
            ptask.pmodelWs = workspace;
          }
        }

        /*
         * Compare created models to see which genes were transfered based on published model / genome
         */
        
        //fetch models
        Map<String, Set<String>> genesPropByModel = new HashMap<> ();
        for (PropagationTask ptask : genomesToCompare) {
          Set<String> genes = new HashSet<> ();
          if (!DataUtils.empty(ptask.pmodelId)) {
            FBAModel fbaModel = KBaseIOUtils.getObject(ptask.pmodelId, ptask.pmodelWs, null, FBAModel.class, wsClient);
            for (ModelReaction mr : fbaModel.getModelreactions()) {
              for (ModelReactionProtein mrp : mr.getModelReactionProteins()) {
                for (ModelReactionProteinSubunit mrps : mrp.getModelReactionProteinSubunits()) {
                  List<String> f = mrps.getFeatureRefs();
                  genes.addAll(f);
                }
              }
            }
          }
          
          for (String g : genes) {
            if (!genesPropByModel.containsKey(g)) {
              genesPropByModel.put(g, new HashSet<String>());
            }
            
            genesPropByModel.get(g).add(ptask.modelId);
          }
          //get genes
        }
        
        out += "\n\n";
        
        for (String g : genesPropByModel.keySet()) {
          out += '\n' + g;
          Set<String> m = genesPropByModel.get(g);
          for (PropagationTask ptask : genomesToCompare) {
            if (m.contains(ptask.modelId)) {
              htmlReportData.addGene(g, ptask.modelId);
              out += " X";
            } else {
              out += " _";
            }
          }
        }
        //HEAT MAP
        
//        SaveOneGenomeParamsV1 gparams = new SaveOneGenomeParamsV1().withData(genome).withWorkspace(workspace).withName("genome");
//        SaveGenomeResultV1 gresults = gaClient.saveOneGenomeV1(gparams);
//        String ref = KBaseIOUtils.getRefFromObjectInfo(gresults.getInfo());
//        outputObjects.put(ref, "test save genome");

      } else {
        logger.warn("unable to find feature");
      }
    } catch (IOException e) {
      out += e.getMessage();
      e.printStackTrace();
    }
    
    this.report = htmlReportData;
    
    try {
      
      List<WorkspaceObject> wsObjects = new ArrayList<> ();
      for (String ref : outputObjects.keySet()) {
        String def = outputObjects.get(ref);
        wsObjects.add(new WorkspaceObject().withDescription(def)
                                           .withRef(ref));
      }
      
      if (this.report != null) {
        String js = KBaseIOUtils.toJson(this.report);
        System.out.println(js);
        KBaseIOUtils.writeStringFile(js, "/kb/module/data/data.json");
      }
      
      KBaseHtmlReport htmlReport = new KBaseHtmlReport(scratch);
      List<String> files = new ArrayList<> ();
      files.add("index.html");
      List<String> datas = new ArrayList<> ();
      
      for (String f : files) {
        datas.add(KBaseIOUtils.getDataWeb("http://darwin.di.uminho.pt/fliu/report-propagation/" + f));
      }
      
      ReportFiles reportFiles = htmlReport.makeStaticReport(files, datas);
      File f = new File("/kb/module/data/data.json");
      
      if (f.exists()) {
        logger.info("copy {} -> {}", f.getAbsolutePath(), reportFiles.baseFolder);
        KBaseIOUtils.copy(f.getAbsolutePath(), reportFiles.baseFolder + "/");
      }
      
      if (kbrClient != null) {
        KBaseReporter reporter = new KBaseReporter(kbrClient, workspace);
        reporter.addWsObjects(wsObjects);
        reporter.addHtmlFolderShock("Propagation Report", "index.html", reportFiles.baseFolder, dfuClient);
        final ReportInfo reportInfo = reporter.extendedReport();
        return reportInfo;
      }
//      final ReportInfo reportInfo = kbrClient.create(
//          new CreateParams().withWorkspaceName(workspace)
//          .withReport(new Report()
//              .withObjectsCreated(wsObjects)
//              .withTextMessage(out)));
      
//      return reportInfo;
    } catch (IOException | JsonClientException e) {
      e.printStackTrace();
    }
    
    return null;
  }
}
