package sbmltools.test;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynthframework.io.FileBiodbService;
import pt.uminho.sysbio.biosynthframework.util.IOUtils;

public class JoanaConstants {
  
  private static final Logger logger = LoggerFactory.getLogger(JoanaConstants.class);
  
  public static String[][] sinks = {
      {"iMO1056"}, {
        "M_hdca_c", "M_ocdca_c", //M_fa8_c M_fa13_c
        "M_aacoa_c", "M_accoa_c", "M_adp_c", "M_amp_c", "M_atp_c", "M_btn_c", "M_cdlp_c", "M_cmp_c", "M_coa_c", "M_ctp_c", "M_damp_c", "M_datp_c", "M_dcmp_c", "M_dctp_c", "M_dgmp_c", "M_dgtp_c", "M_dhap_c", "M_dtmp_c", "M_dttp_c", "M_e4p_c", "M_f6p_c", "M_fa13_c", "M_fa8_c", "M_g3p_c", "M_g6p_c", "M_glc__D_c", "M_glycogen_c", "M_gmp_c", "M_gtp_c", "M_hemeO_c", "M_malcoa_c", "M_mqn8_c", "M_nad_c", "M_nadp_c", "M_nadph_c", "M_oaa_c", "M_pep_c", "M_ppi_c", "M_r5p_c", "M_sheme_c", "M_thmpp_c", "M_ttdca_c", "M_uacgam_c", "M_uamr_c", "M_udcpdp_c", "M_udpg_c", "M_ump_c", "M_utp_c", "M_5mthf_c", "M_cdlp_PA_c", "M_fad_c", "M_nadh_c", "M_PA_core_lipidA_c", "M_pe_PA_c", "M_peptido_EC_c", "M_pg_PA_c", "M_pheme_c", "M_ps_PA_c", "M_succoa_c", "M_udcppfmm_c", "M_rha4glcnacppund_c", "M_algna_c", "M_llrhh_c", "M_pyo_c"},
      {"iAbaylyiv4"}, {"M_BIOMASS_c"},
      {"iYS432"}, {"M_Biomass_e"},
      {"iRS605_fixed"}, {"M_5mthf_LPAREN_c_RPAREN_", "M_accoa_LPAREN_c_RPAREN_", "M_ala__L_LPAREN_c_RPAREN_", "M_amp_LPAREN_c_RPAREN_", "M_asn__L_LPAREN_c_RPAREN_", "M_atp_LPAREN_c_RPAREN_", "M_ctp_LPAREN_c_RPAREN_", "M_datp_LPAREN_c_RPAREN_", "M_dctp_LPAREN_c_RPAREN_", "M_dgtp_LPAREN_c_RPAREN_", "M_dttp_LPAREN_c_RPAREN_", "M_fad_LPAREN_c_RPAREN_", "M_gln__L_LPAREN_c_RPAREN_", "M_glycogen_LPAREN_c_RPAREN_", "M_gtp_LPAREN_c_RPAREN_", "M_lps_EC_LPAREN_c_RPAREN_", "M_nadh_LPAREN_c_RPAREN_", "M_nadph_SPACE_bal_LPAREN_c_RPAREN_", "M_pe_EC_LPAREN_c_RPAREN_", "M_peptido_EC_LPAREN_c_RPAREN_", "M_pg_EC_LPAREN_c_RPAREN_", "M_ps_EC_LPAREN_c_RPAREN_", "M_succoa_LPAREN_c_RPAREN_", "M_trp__L_LPAREN_c_RPAREN_", "M_udpg_LPAREN_c_RPAREN_", "M_utp_LPAREN_c_RPAREN_"},
      {"GSMN-TB"}, {"M_BIOMASS_c"},
      {"iPS189"}, {"M_C03153_c", "M_pglyp_c"},
      {"iJW145"}, {"EC0154", "EC0132", "EC0029", "C0045", "C0006", "EC0132", "EC0029", "C9485", "EC0154", "EC0062"},
      {"iMA945"}, {"M_4hba_c", "M_5drib_c", "M_aacald_c", "M_hmfurn_c", "M_oxam_c"},
      {"iJB785"}, {"M_14glucan_c", "M_glycogen_c", "M_amob_c", "M_fum_c", "M_succ_c", "M_akg_c", "M_for_c", "M_h2_c", "M_dialurate_c", "M_co_c", "M_5drib_c", "M_amob_c", "M_pho_loss_c", "M_ac_c", "M_lac__D_c"},
  };
  
  public static String[] taxaNcbiFTPLink = {
      "txid216895", "/000/039/765/GCF_000039765.1_ASM3976v1/GCF_000039765.1_ASM3976v1_protein.faa.gz",
      "txid348780", "/000/026/045/GCF_000026045.1_ASM2604v1/GCF_000026045.1_ASM2604v1_protein.faa.gz",
      "txid376619", "/000/009/245/GCF_000009245.1_ASM924v1/GCF_000009245.1_ASM924v1_protein.faa.gz",
      "txid63612", "/000/010/085/GCF_000010085.1_ASM1008v1/GCF_000010085.1_ASM1008v1_protein.faa.gz",
      "txid395962", "/000/147/335/GCF_000147335.1_ASM14733v1/GCF_000147335.1_ASM14733v1_protein.faa.gz",
      "txid1051650", "/000/309/565/GCF_000309565.2_ASM30956v2/GCF_000309565.2_ASM30956v2_protein.faa.gz",
      "txid168695", "/000/016/425/GCF_000016425.1_ASM1642v1/GCF_000016425.1_ASM1642v1_protein.faa.gz",
      "txid229193", "/000/007/885/GCF_000007885.1_ASM788v1/GCF_000007885.1_ASM788v1_protein.faa.gz",
      "txid999378", "/000/194/785/GCF_000194785.1_ASM19478v1/GCF_000194785.1_ASM19478v1_protein.faa.gz",
      "txid696747", "/000/210/375/GCF_000210375.1_ASM21037v1/GCF_000210375.1_ASM21037v1_protein.faa.gz",
      "txid748727", "/000/143/685/GCF_000143685.1_ASM14368v1/GCF_000143685.1_ASM14368v1_protein.faa.gz",
      "txid595496", "/000/022/345/GCF_000022345.1_ASM2234v1/GCF_000022345.1_ASM2234v1_protein.faa.gz",
      "txid431946", "/000/010/485/GCF_000010485.1_ASM1048v1/GCF_000010485.1_ASM1048v1_protein.faa.gz",
      "txid316385", "/000/019/425/GCF_000019425.1_ASM1942v1/GCF_000019425.1_ASM1942v1_protein.faa.gz",
      "txid511145", "/000/005/845/GCF_000005845.2_ASM584v2/GCF_000005845.2_ASM584v2_protein.faa.gz",
      "txid511145", "/000/005/845/GCF_000005845.2_ASM584v2/GCF_000005845.2_ASM584v2_protein.faa.gz",
      "txid511145", "/000/005/845/GCF_000005845.2_ASM584v2/GCF_000005845.2_ASM584v2_protein.faa.gz",
      "txid243274", "/000/008/545/GCF_000008545.1_ASM854v1/GCF_000008545.1_ASM854v1_protein.faa.gz",
      "txid214092", "/000/009/065/GCF_000009065.1_ASM906v1/GCF_000009065.1_ASM906v1_protein.faa.gz",
      "txid573", "/000/016/305/GCF_000016305.1_ASM1630v1/GCF_000016305.1_ASM1630v1_protein.faa.gz",
      
      "txid272634", "/000/027/345/GCF_000027345.1_ASM2734v1/GCF_000027345.1_ASM2734v1_protein.faa.gz",
      "txid243273", "/000/027/325/GCF_000027325.1_ASM2732v1/GCF_000027325.1_ASM2732v1_protein.faa.gz",
      "txid255470", "/000/009/025/GCF_000009025.1_ASM902v1/GCF_000009025.1_ASM902v1_protein.faa.gz",
      "txid134676", "/000/237/145/GCF_000237145.1_ASM23714v1/GCF_000237145.1_ASM23714v1_protein.faa.gz",
      "txid411481", "/000/154/085/GCF_000154085.1_ASM15408v1/GCF_000154085.1_ASM15408v1_protein.faa.gz",
      "txid107806", "/000/009/605/GCF_000009605.1_ASM960v1/GCF_000009605.1_ASM960v1_protein.faa.gz",
      "txid192222", "/000/009/085/GCF_000009085.1_ASM908v1/GCF_000009085.1_ASM908v1_protein.faa.gz",
      "txid338966", "/000/015/045/GCF_000015045.1_ASM1504v1/GCF_000015045.1_ASM1504v1_protein.faa.gz",
      "txid338963", "/000/012/885/GCF_000012885.1_ASM1288v1/GCF_000012885.1_ASM1288v1_protein.faa.gz",
      "txid555217", "/000/175/255/GCF_000175255.2_ASM17525v2/GCF_000175255.2_ASM17525v2_protein.faa.gz",
      "txid331104", "/000/022/605/GCF_000022605.2_ASM2260v1/GCF_000022605.2_ASM2260v1_protein.faa.gz",
      "txid600809", "/000/093/165/GCF_000093165.1_ASM9316v1/GCF_000093165.1_ASM9316v1_protein.faa.gz",
      "txid224324", "/000/008/625/GCF_000008625.1_ASM862v1/GCF_000008625.1_ASM862v1_protein.faa.gz",
      "txid264199", "/000/011/825/GCF_000011825.1_ASM1182v1/GCF_000011825.1_ASM1182v1_protein.faa.gz",
  };
  
  public static String[] customUptakes = {
      "iLca334_548", "R_ex_cpd00030_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00048_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00063_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00099_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00205_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00220_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00254_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00305_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00393_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00971_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00001_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00009_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00011_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00067_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00027_E@iLca334_548", "-5",
      "iLca334_548", "R_ex_cpd00051_E@iLca334_548", "-9",
      "iLca334_548", "R_ex_cpd00322_E@iLca334_548", "-9",
      "iLca334_548", "R_ex_cpd00107_E@iLca334_548", "-9",
      "iLca334_548", "R_ex_cpd00066_E@iLca334_548", "-9",
      "iLca334_548", "R_ex_cpd00065_E@iLca334_548", "-9",
      "iLca334_548", "R_ex_cpd00069_E@iLca334_548", "-9",
      "iLca334_548", "R_ex_cpd00156_E@iLca334_548", "-9",
      "iLca334_548", "R_ex_cpd00034_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00058_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00149_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd10516_E@iLca334_548", "-1000",
      "iLca334_548", "R_ex_cpd00264_E@iLca334_548", "-1000",

      "iLca12A_640", "R_ex_cpd00030_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00048_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00063_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00099_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00205_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00220_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00254_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00305_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00393_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00971_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00001_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00009_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00011_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00067_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00027_E@iLca12A_640", "-5",
      "iLca12A_640", "R_ex_cpd00051_E@iLca12A_640", "-9",
      "iLca12A_640", "R_ex_cpd00322_E@iLca12A_640", "-9",
      "iLca12A_640", "R_ex_cpd00107_E@iLca12A_640", "-9",
      "iLca12A_640", "R_ex_cpd00066_E@iLca12A_640", "-9",
      "iLca12A_640", "R_ex_cpd00065_E@iLca12A_640", "-9",
      "iLca12A_640", "R_ex_cpd00069_E@iLca12A_640", "-9",
      "iLca12A_640", "R_ex_cpd00156_E@iLca12A_640", "-9",
      "iLca12A_640", "R_ex_cpd00023_E@iLca12A_640", "-9",
      "iLca12A_640", "R_ex_cpd00034_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00058_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00149_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd10516_E@iLca12A_640", "-1000",
      "iLca12A_640", "R_ex_cpd00264_E@iLca12A_640", "-1000",
    };

  
  public static String[][] sbmlFix = {
      {"iCyj826", "Proton exchange", "R_EX_H"},
      {"iCyj826", "Cobalt exchange", "R_EX_Co2"},
      {"iCyj826", "Copper exchange", "R_EX_Cu2"},
      {"iCyj826", "Fe2+ exchange", "R_EX_Fe2"},
      {"iCyj826", "Fe3+ exchange", "R_EX_Fe3"},
      {"iCyj826", "Mn2+ exchange", "R_EX_Mn2"},
      {"iCyj826", "H+ transport via diffusion (carboxysome)", "R_H_PLUS_ctr"},
      {"iCyn731", "Proton exchange", "R_EX_H"},
      {"iCyn731", "Cobalt exchange", "R_EX_Co2"},
      {"iCyn731", "Copper exchange", "R_EX_Cu2"},
      {"iCyn731", "Fe2+ exchange", "R_EX_Fe2"},
      {"iCyn731", "Fe3+ exchange", "R_EX_Fe3"},
      {"iCyn731", "Mn2+ exchange", "R_EX_Mn2"},
      {"iCyn731", "H+ transport via diffusion (carboxysome)", "R_H_PLUS_ctr"},
      {"iCyp752", "Proton exchange", "R_EX_H"},
      {"iCyp752", "Cobalt exchange", "R_EX_Co2"},
      {"iCyp752", "Copper exchange", "R_EX_Cu2"},
      {"iCyp752", "Fe2+ exchange", "R_EX_Fe2"},
      {"iCyp752", "Fe3+ exchange", "R_EX_Fe3"},
      {"iCyp752", "Mn2+ exchange", "R_EX_Mn2"},
      {"iCyp752", "H+ transport via diffusion (carboxysome)", "R_H_PLUS_ctr"},
      {"iCyh755", "Proton exchange", "R_EX_H"},
      {"iCyh755", "Cobalt exchange", "R_EX_Co2"},
      {"iCyh755", "Copper exchange", "R_EX_Cu2"},
      {"iCyh755", "Fe2+ exchange", "R_EX_Fe2"},
      {"iCyh755", "Fe3+ exchange", "R_EX_Fe3"},
      {"iCyh755", "Mn2+ exchange", "R_EX_Mn2"},
      {"iCyh755", "H+ transport via diffusion (carboxysome)", "R_H_PLUS_ctr"},
      {"iCyc792", "Proton exchange", "R_EX_H"},
      {"iCyc792", "Cobalt exchange", "R_EX_Co2"},
      {"iCyc792", "Copper exchange", "R_EX_Cu2"},
      {"iCyc792", "Fe2+ exchange", "R_EX_Fe2"},
      {"iCyc792", "Fe3+ exchange", "R_EX_Fe3"},
      {"iCyc792", "Mn2+ exchange", "R_EX_Mn2"},
      {"iCyc792", "H+ transport via diffusion (carboxysome)", "R_H_PLUS_ctr"},
  };
  
  public static String[][] sbmlDrains = {
      {"iRsp1095", "end", "exchange", "name"},
      {"iRsp1140", "end", "exchange", "name"},
      {"iDsh827", "start", "-boundary", "name"},
      {"iMZ1055", "start", "exchange", "name"},
      {"iVW583", "start", "Uptake", "name"},
      {"iAK692_auto", "end", "exX", "entry"},
      {"iAO358", "end", "extO", "entry"},
      {"iJC568", "end", "EX", "entry"},
      {"iSS352", "end", "_exchange", "entry"},
      {"iAbaylyiv4", "start", "R_EXF_", "entry"},
      {"iAI549", "start", "R_EX", "entry"},
      {"iAI558", "start", "R_EX", "entry"},
      {"iBif452", "start", "R_EX", "entry"},
      {"iBsu1103", "start", "EX", "entry"},
      {"iBT721_v2", "start", "R_EX", "entry"},
      {"iCA1273", "start", "R_EX", "entry"},
      {"iCAC490", "start", "R_EX", "entry"},
      {"iCac802", "start", "R_EX", "entry"},
//      {"iCac802_V_cobragitsengerpapou", "start", "R_EX", "entry"},
      {"iCce806", "start", "R_EX", "entry"},
      {"iCG230", "start", "R_EX", "entry"},
      {"iCG238", "start", "R_EX", "entry"},
      {"iCM925", "start", "R_EX", "entry"},
      {"iCR744", "start", "R_EX", "entry"},
      {"iCS291", "start", "R_EX", "entry"},
      {"iCS400", "start", "R_EX", "entry"},
      {"iCyc792", "start", "R_EX", "entry"},
      {"iCyh755", "start", "R_EX", "entry"},
      {"iCyj826", "start", "R_EX", "entry"},
      {"iCyn731", "start", "R_EX", "entry"},
      {"iCyp752", "start", "R_EX", "entry"},
      {"iCyt773", "start", "EX_", "entry"},
      {"iEB458", "start", "R_EX", "entry"},
      {"iEM439", "start", "R_EX", "entry"},
      {"iGB555_fixed", "start", "R_EX", "entry"},
      {"iGT196", "start", "UP_", "entry"},
      {"iJH728", "start", "R_EX", "entry"},
      {"iJL432", "start", "R_EX", "entry"},
      {"iJL480", "start", "R_EX", "entry"},
      {"iJP815", "start", "EX_", "entry"},
      {"iJP962", "start", "EX_", "entry"},
      {"iJS747", "start", "R_EX", "entry"},
      {"iKF1028", "start", "EX_", "entry"},
      {"iLca12A_640", "start", "R_EX", "entry"},
      {"iLca334_548", "start", "R_EX", "entry"},
      {"iMA789", "start", "R_EX", "entry"},
      {"iMA789_cobrafixed", "start", "R_EX", "entry"},
      {"iMA945", "start", "R_EX", "entry"},
      {"iMB745", "start", "R_EX", "entry"},
      
      {"iMF721", "start;start;start", "EX_;DEM_;SINK_", "entry;entry;entry"},
      
      {"iTZ479", "start;start", "R_EX;R_sink", "entry;entry"},
      
      {"iMG746", "start", "R_EX", "entry"},
      {"iMH551", "start", "R_EX", "entry"},
      {"iMK1208", "start", "R_EX", "entry"},
      {"iMLTC806cdf", "start", "R_EX", "entry"},
      {"iMO1056", "start", "R_EX", "entry"},
      {"iMP240", "start", "R_EX", "entry"},
      {"iMP429_fixed", "start", "R_EX", "entry"},
      {"iNF518", "start", "R_EX", "entry"},
      {"iNJ661m", "start", "R_EX", "entry"},
      {"iNJ661v", "start", "R_EX", "entry"},
      {"iNV706", "start", "R_EX", "entry"},
      {"iOG654", "start", "ER", "entry"},
      {"iPB890", "start", "R_EX", "entry"},
      {"iPS189", "start", "R_EX", "entry"},
      {"iRBaquifex", "start", "EX_", "entry"},
      {"iRM588", "start", "R_EX", "entry"},
      {"iRP911", "start", "EX_", "entry"},
      {"iRR1083", "start", "R_EX", "entry"},
      {"iRS605_fixed", "start", "R_EX", "entry"},
      {"iSB1139", "start", "R_MIRXN", "entry"},
      {"iSM197", "start", "EX_", "entry"},
      {"iSO783", "start", "R_EX", "entry"},
      {"iSR432", "start", "R_SRC", "entry"},
      {"iSyn731", "start", "EX_", "entry"},
      {"iTY425_fixed", "start", "R_EX", "entry"},
      {"iTZ479", "start;start", "R_EX;R_sink", "entry;entry"},
      {"iVM679", "start", "R_EX", "entry"},
      {"iVS941_fixed", "start", "R_EX", "entry"},
      {"iWX1009", "start", "EX_", "entry"},
      {"iZM363", "start", "R_EX", "entry"},
      {"iZmobMBEL601", "start", "R_Transport", "entry"},
      {"kb_fbamdl_1903", "start", "EX_", "entry"},
      {"Nmb_iTM560", "start", "R_EX", "entry"},
      {"PpuMBEL1071", "start", "TRANS_", "entry"},
  };
  
  public static String[] sbmlModelFiles = {
      "/var/biomodels/2batch/iJL480.xml",
      "/var/biomodels/2batch/iNV706.xml",
      "/var/biomodels/2batch/kb_fbamdl_1903.xml",
      "/var/biomodels/dirty/Abymbel891.xml",
      "/var/biomodels/dirty/iAbaylyiv4.xml",
      "/var/biomodels/dirty/iGT196.xml",
      "/var/biomodels/dirty/iMA945.xml",
      "/var/biomodels/dirty/sMtb.xml",
      "/var/biomodels/fix/iMK1208.xml",
      "/var/biomodels/hard/iDsh827.xml",
      "/var/biomodels/hard/iVW583.xml",
      "/var/biomodels/joana/GSMN-TB.xml",
      "/var/biomodels/joana/iAI549.xml",
      "/var/biomodels/joana/iAI558.xml",
      "/var/biomodels/joana/iAK692_auto.xml",
      "/var/biomodels/joana/iBif452.xml",
      "/var/biomodels/joana/iBsu1103.xml",
      "/var/biomodels/joana/iBT721_v2.xml",
      "/var/biomodels/joana/iCAC490.xml",
      "/var/biomodels/joana/iCac802.xml",
//      "/var/biomodels/joana/iCac802_V_cobragitsengerpapou.xml", //discarded
      "/var/biomodels/joana/iCce806.xml",
      "/var/biomodels/joana/iCG230.xml",
      "/var/biomodels/joana/iCG238.xml",
      "/var/biomodels/joana/iCM925.xml",
      "/var/biomodels/joana/iCR744.xml",
      "/var/biomodels/joana/iCS291.xml",
      "/var/biomodels/joana/iCS400.xml",
      "/var/biomodels/joana/iCyc792.xml",
      "/var/biomodels/joana/iCyh755.xml",
      "/var/biomodels/joana/iCyn731.xml",
      "/var/biomodels/joana/iCyp752.xml",
      "/var/biomodels/joana/iCyt773.xml",
      "/var/biomodels/joana/iEB458.xml",
      "/var/biomodels/joana/iEM439.xml",
      "/var/biomodels/joana/iFap484.xml",
      "/var/biomodels/joana/iGB555_fixed.xml",
      "/var/biomodels/joana/iIB700.xml",
      "/var/biomodels/joana/iJC568.xml",
      "/var/biomodels/joana/iJH728.xml",
      "/var/biomodels/joana/iJL432.xml",
      "/var/biomodels/joana/iJP815.xml",
      "/var/biomodels/joana/iJP962.xml",
      "/var/biomodels/joana/iJS747.xml",
      "/var/biomodels/joana/iJW145.xml",
      "/var/biomodels/joana/iKF1028.xml",
      "/var/biomodels/joana/iLca12A_640.xml",
//      "/var/biomodels/joana/iMA789.xml",
      "/var/biomodels/joana/iMA789_cobrafixed.xml",
      "/var/biomodels/joana/iMB745.xml",
      "/var/biomodels/joana/iMF721.xml",
      "/var/biomodels/joana/iMG746.xml",
      "/var/biomodels/joana/iMLTC806cdf.xml",
      "/var/biomodels/joana/iMM518.xml",
      "/var/biomodels/joana/iMO1056.xml",
      "/var/biomodels/joana/iMP240.xml",
      "/var/biomodels/joana/iMP429_fixed.xml",
      "/var/biomodels/joana/iMZ1055.xml",
      "/var/biomodels/joana/iNF518.xml",
      "/var/biomodels/joana/iNJ661m.xml",
      "/var/biomodels/joana/iNJ661v.xml",
      "/var/biomodels/joana/iOG654.xml",
      "/var/biomodels/joana/iPB890.xml",
      "/var/biomodels/joana/iRM588.xml",
      "/var/biomodels/joana/iRP911.xml",
      "/var/biomodels/joana/iRR1083.xml",
      "/var/biomodels/joana/iRS605_fixed.xml",
      "/var/biomodels/joana/iRsp1095.xml",
      "/var/biomodels/joana/iSH335.xml",
      "/var/biomodels/joana/iSM197.xml",
      "/var/biomodels/joana/iSR432.xml",
      "/var/biomodels/joana/iSS352.xml",
      "/var/biomodels/joana/iSyf715.xml",
      "/var/biomodels/joana/iSyn669.xml",
      "/var/biomodels/joana/iSyn731.xml",
      "/var/biomodels/joana/iTT548.xml",
      "/var/biomodels/joana/iTY425_fixed.xml",
      "/var/biomodels/joana/iVS941_fixed.xml",
      "/var/biomodels/joana/iWZ663.xml",
      "/var/biomodels/joana/iZM363.xml",
      "/var/biomodels/joana/iZmobMBEL601.xml",
      "/var/biomodels/joana/Nmb_iTM560.xml",
      "/var/biomodels/joana/PpuMBEL1071.xml",
      "/var/biomodels/joana/VvuMBEL943.xml",
      "/var/biomodels/proxy/iAO358.xml",
      "/var/biomodels/proxy/iSO783.xml",
      "/var/biomodels/test/iCA1273.xml",
      "/var/biomodels/test/iCyj826.xml",
      "/var/biomodels/test/iLca334_548.xml",
      "/var/biomodels/test/iMH551.xml",
      "/var/biomodels/test/iRBaquifex.xml",
      "/var/biomodels/test/iRsp1140.xml",
      "/var/biomodels/test/iTZ479.xml",
      "/var/biomodels/test/iVM679.xml",
      "/var/biomodels/test/iWX1009.xml",
      
      "/var/biomodels/optflux/iJM658.xml",
      "/var/biomodels/optflux/iKK446.xml",
      "/var/biomodels/optflux/iYS432.xml",
      
//      "/var/biomodels/joana/STM_v1.0.xml",
    //"D:/var/biomodels/proxy/iPS189.xml", //already exists
    //"/var/biomodels/joana/iSB1139.xml",
  };
  
  public static String[] notsure= {
      "iNV706",
//      "iGT196",
      "PpuMBEL1071",
      "iSB1139",
  };
  
  public static String[] modelBad = {
//      "iJW145",
//      "iVW583",
//      "sMtb",
//      "iJP815",
//      "iAK692_auto",
//      "iRsp1095",
//      "iRsp1140",
//      "iJP962",
//      "iPB890",
//      "iKF1028",
      
      "iVW583",
      "sMtb",
      
      

//      "iSR432",
      "iSyn731",
  };
  
  public static String[] modelGood = {
      "iAK692_auto",
      "iGT196", //scale, do not delete boundary, flux limits
      //ALL NEEDS SCALE
      "iJW145", //remove _b
      "iPB890",
      "iJP815",
      "iJP962",
      "iKF1028",
      //good !?
      "iJH728",
      "iWX1009",
      //REQUIRES: FLUSH ZERO BOUNDS
      "iLca12A_640", 
      "iLca334_548",
      //NORMAL KIDS !
      "iBT721_v2", // CARE ABOUT BIOMASS default bounds
      "iOG654",
      "iSM197",
      "iRP911",
      "iDsh827",
      "iMG746",
      "iAI558",
      "iSyf715",
      "iMK1208",
      "iJM658",
      "iMZ1055",
      "iCM925",
      "iSyn669",
      "iCS291",
      "iCyp752",
      "iCyh755",
      "iCyn731",
      "iCyc792",
      "iCyj826",
      "iJL432",
      "iTT548",
      "iVS941_fixed",
      "iMP240",
      "iEB458",
      "iMH551",
      "iMP429_fixed",
      "iTY425_fixed",
      "iSH335",
      "iYS432",
      "iZM363",
      "iVM679",
      "iCce806",
      "iAbaylyiv4",
      "iSS352",
      "iWZ663",
      "iJS747",
      "iJC568",
      "iMA945",
      "iRBaquifex",
      "iAI549",
      "VvuMBEL943",
      "Abymbel891",
      "iGB555_fixed",
      "iSO783",
      "iCyt773",
      "kb_fbamdl_1903",
      "iMA789_cobrafixed",
      "iBsu1103",
      "iMF721",
      "iJB785",
      //alles gut !
      "iAO358", 
      "iRR1083", 
      "iMLTC806cdf",
      "iJL480", "iZmobMBEL601", "iNJ661v", "iNJ661m",
      
      //new builder!
      "iMB745", "iFap484", "iBif452", "iNF518", "GSMN-TB", 
      "iCac802", "iCA1273", "Nmb_iTM560", "iKK446", "iIB700",
      
      //non verifiable
      "iCS400", "iEM439", "iMO1056", "iRS605_fixed", 
      "iCG230", "iCAC490", "iMM518", "iCR744", 
      "iCG238", "iRM588", "iPS189"
  };
  
  public static String[] modelEntrysBigg2 = {
      "iJO1366_bigg2",
      "iIT341_bigg2",
      "iJN746_bigg2",
      "iSB619_bigg2",
      "STM_v1_0",
      "iAF1260",
      "iAF692",
      "iAF987",
      "iBWG_1329",
      "iECDH10B_1368",
      "iECSF_1327",
      "iHN637",
      "iJN678",
      "iJR904",
      "iLJ478",
      "iPC815",
      "iYL1228",
      "iYO844",
  };
  
  public static String[] modelEntrys = {
      //KEGG
      
      //SEED
      
      //BIGG
      
      //None
      "iNF518",
      "iSS352",
      "iGT196",
      "iMO1056",
      "iMZ1055",
      "iJM658",
      "iZM363",
      "iDsh827",
      "iOG654",
      "iMA945",
      "iCyc792",
      "iRBaquifex",
      "sMtb",
      "iJL432",
      "iSO783",
      "iJS747",
      "iVS941_fixed",
      "iEM439",
      "Abymbel891",
      "iNJ661m",
      "VvuMBEL943",
      "iSB1139",
      "iRsp1095",
      "iSyn731",
      "iZmobMBEL601",
      "iMK1208",
      "Nmb_iTM560",
      "iNJ661v",
      "iMM518",
      "iMF721",
      "iCyt773",
      "iBif452",
      "iAI558",
      "iEB458",
      "iSH335",
      "iYS432",
      "iJC568",
      "iSyf715",
      "iSM197",
      "iTY425_fixed",
      "iCAC490",
      "iCac802",
      "iRsp1140",
      "iJW145",
      "iCR744",
      "iGB555_fixed",
      "iAI549",
      "iMP240",
      "PpuMBEL1071",
      "iKF1028",
      "iCyh755",
      "iNV706",
      "GSMN-TB",
      "iLca12A_640",
      "iCyn731",
      "iCyp752",
      "iJP962",
      "iJL480",
      "iCce806",
      "iRP911",
      "iMA789_cobrafixed",
      "iCyj826",
      "iAbaylyiv4",
      "iAK692_auto",
      "iCS291",
      "iCG238",
      "iSyn669",
      "iJH728",
      "iLca334_548",
      "iCG230",
      "iWX1009",
      "iIB700",
      "iFap484",
      "iCA1273",
      "iVM679",
      "iRR1083",
      "iMLTC806cdf",
      "iAO358",
      "iBT721_v2",
      "iTT548",
      "iRM588",
      "iBsu1103",
      "iMH551",
      "iWZ663",
//      "iCM925",
      "iMP429_fixed",
      "iVW583",
      "iMG746",
      "iPB890",
      "iJP815",
      "iRS605_fixed",
      "kb_fbamdl_1903",
      "iSR432",
//      "iTZ479", duplicate
      "iMB745",
      "iCS400",
      "iPS189",
      "iKK446",
      "iJB785",
      
//      "iCac802_V_cobragitsengerpapou",
      
      "iJSPpropionicus",
      "iCC908",
      "iJSPcarbinolicus",
      "iJL846",
      "iAN818m",
      "iHK760",
      "iYLW1028",
      "iAM388",
      "iKY620",
      "iMR539",
  };
  
  public static final String[][] hammerBiomass = {
      {"2626824", "Biomass Formation Dark Phase", "Biomass_Dark;-N"},
      {"2626825", "Biomass Formation Dark Phase_0", "Biomass_Dark;+N"},
      {"2626826", "Biomass Formation Light Phase", "Biomass_Light;-N"},
      {"2626827", "Biomass Formation Light Phase_1", "Biomass_Light;+N"},
      {"2655086", "Biomass Formation Dark Phase", "Biomass_Dark;-N"},
      {"2655087", "Biomass Formation Dark Phase_0", "Biomass_Dark;+N"},
      {"2655088", "Biomass Formation Light Phase", "Biomass_Light;-N"},
      {"2655089", "Biomass Formation Light Phase_1", "Biomass_Light;+N"},
      {"2632524", "Biomass Formation Dark Phase", "Biomass_Dark;-N"},
      {"2632525", "Biomass Formation Dark Phase_0", "Biomass_Dark;+N"},
      {"2632526", "Biomass Formation Light Phase", "Biomass_Light;-N"},
      {"2632527", "Biomass Formation Light Phase_1", "Biomass_Light;+N"},
      {"2633695", "Biomass Formation Dark Phase", "Biomass_Dark;-N"},
      {"2633696", "Biomass Formation Dark Phase_0", "Biomass_Dark;+N"},
      {"2633697", "Biomass Formation Light Phase", "Biomass_Light;-N"},
      {"2633698", "Biomass Formation Light Phase_1", "Biomass_Light;+N"},
      
      //2633695 Biomass Formation Dark Phase Biomass_Dark;-N
      //2633696 Biomass Formation Dark Phase_0 Biomass_Dark;+N
      //2633697 Biomass Formation Light Phase Biomass_Light;-N
      //2633698 Biomass Formation Light Phase_1 Biomass_Light;+N
      
      {"2646838", "Biomass Formation Dark Phase", "Biomass_Dark;-N"},
      {"2646839", "Biomass Formation Dark Phase_0", "Biomass_Dark;+N"},
      {"2646840", "Biomass Formation Light Phase", "Biomass_Light;-N"},
      {"2646841", "Biomass Formation Light Phase_1", "Biomass_Light;+N"},
  };
  
  public static Map<String, String> getModelDefaultBiomassReaction() {
    Map<String, String> result = new HashMap<> ();
    
    for (int i = 0; i < biomass.length; i+=2) {
      String model = biomass[i];
      String rxn = biomass[i + 1];
      result.put(model, rxn);
    }
    return result;
  }
  
  public static final String[] biomass = {
      "iJO1366_bigg2", "R_BIOMASS_Ec_iJO1366_WT_53p95M",
      "iBWG_1329", "R_BIOMASS_Ec_iJO1366_WT_53p95M",
      "iECDH10B_1368", "R_BIOMASS_Ec_iJO1366_WT_53p95M",
      "iECSF_1327", "R_BIOMASS_Ec_iJO1366_WT_53p95M",
      "iJN678", "R_BIOMASS_Ec_SynAuto",
      
//      "iJO1366_bigg2", "R_BIOMASS_Ec_iJO1366_core_53p95M",
//      "iBWG_1329", "R_BIOMASS_Ec_iJO1366_core_53p95M",
//      "iECDH10B_1368", "R_BIOMASS_Ec_iJO1366_core_53p95M",
//      "iECSF_1327", "R_BIOMASS_Ec_iJO1366_core_53p95M",
//      "iJN678", "R_BIOMASS_Ec_SynHetero",
      "iIT341_bigg2", "R_BIOMASS_HP_published",
      "iJN746_bigg2", "R_BIOMASS_KT_TEMP",
      "iSB619_bigg2", "R_BIOMASS_SA_8a",
      "STM_v1_0", "R_BIOMASS_iRR1083_metals",
      "iAF1260", "R_BIOMASS_Ec_iAF1260_core_59p81M",
      "iAF692", "R_BIOMASS_Mb_30",
      "iAF987", "R_BIOMASS_Gm_GS15_core_79p20M",
      "iHN637", "R_BIOMASS_Cl_DSM_WT_46p666M1",
      "iJR904", "R_BIOMASS_Ecoli",
      "iLJ478", "R_BIOMASS_Ecoli_TM",
      "iPC815", "R_BIOMASS_37C",
      "iYL1228", "R_BIOMASS_",
      "iYO844", "R_BIOMASS_BS_10",
      
      "iBsu1103", "bio00006",
      "iRBaquifex", "bio00035",
      "iSyn731", "Biomass_Auto",
      "iCyc792", "Biomass_Light;+N",
      "iCyh755", "Biomass_Light;+N",
      "iCyj826", "Biomass_Light;+N",
      "iCyn731", "Biomass_Light;+N",
      "iCyp752", "Biomass_Light;+N",
      "iCyt773", "Biomass_Light_N",
      "iSyf715", "Biomass_r",
      "kb_fbamdl_1903", "biomass0",
      "iWX1009", "Biomass00006",
      "GSMN-TB", "BIOMASSe",
      "sMtb", "BiomassGrowth",
      "iRP911", "BiomassSynthesis",
      "iSS352", "R_biomass1", //"BiomassSynthesis",
      "iJP815", "IR09885",
      "iJW145", "IR09955",
      "iJP962", "IR10370",
      "iKF1028", "IR10490",
      "iVW583", "R_1309",
      "iRM588", "R_agg_GS13m",
      "iJS747", "R_agg_GS13m_2",
      "iAI549", "R_BIO_CBDB1_DM_855",
      "iAI558", "R_BIO_MT",
      "iCR744", "R_BIO_Rfer3",
      "iLca12A_640", "R_bio02514",
      "iLca334_548", "R_bio06001",
      "iBif452", "R_Biomass",
      "iCM925", "R_biomass",
      "iCS291", "R_BIOMASS",
      "iCS400", "R_BIOMASS",
      "iEM439", "R_biomass",
      "iFap484", "R_Biomass",
      "iJC568", "R_BIOMASS",
      "iJL480", "R_biomass",
      "iMA789", "R_biomass",
      "iMA789_cobrafixed", "R_biomass",
      "iMLTC806cdf", "R_Biomass",
      "iPS189", "R_Biomass",
      "iSyn669", "R_Biomass",
      "iVM679", "R_Biomass",
      "iVS941_fixed", "R_Biomass",
      "iZM363", "R_Biomass",
      "PpuMBEL1071", "R_Biomass",
      "iAO358", "R_BIOMass_dup3",
      "iNF518", "R_biomass_LLA",
      "iBT721_v2", "R_biomass_LPL60",
      "iNJ661m", "R_biomass_Mtb_9_60atp_test_NOF",
      "iNJ661v", "R_biomass_Mtb_9_60atp_test_NOF",
      "iMK1208", "R_Biomass_SCO",
      "iMP429_fixed", "R_biomass_STR",
      "iSR432", "R_biomass_target",
      "iCG238", "R_BiomassBge",
      "iMP240", "R_BiomassBge",
      "iEB458", "R_BiomassEcoli",
      "iTZ479", "R_BiomassEcoli_TM",
      "iCG230", "R_BiomassPam",
      "iSB1139", "R_BIOMASSRXN",
      "iCce806", "r_CYANOBM",
      "iCA1273", "R_Ec_biomass_iCA1273_core_59p81M",
      "iRS605_fixed", "R_FT_BiomX_DM",
      "iAbaylyiv4", "R_GROWTH_DASH_RXN",
      "iPB890", "R_IR10372",
      "iGB555_fixed", "R_LUMP70",
      "iMG746", "R_Mb_biomass_65",
      "iJH728", "R_NEWBIOMASSCLIMITED",
      "Nmb_iTM560", "R_Nm_biomass",
      "iMB745", "R_overall",
      "iMO1056", "R_PA_Biomass6_DM",
      "iTT548", "R_R001",
      "iMH551", "R_R0221",
      "iCAC490", "R_R07230_B",
      "iWZ663", "R_R0822",
      "iCac802", "R_R1461",
      "iSH335", "R_R374",
      "iTY425_fixed", "R_R517",
      "iJL432", "R_rBIOMASS",
      "iZmobMBEL601", "R_Rxn_Biomass",
      "iMZ1055", "R_RxnBME1112",
      "iSO783", "R_SO_BiomassMacro_DM_noATP2",
      "iAK692_auto", "R_SP0699",
      "iMA945", "R_ST_biomass_core",
      "iRR1083", "R_ST_Biomass_Final",
      "iMM518", "R565",
      "Abymbel891", "R761",
      "iDsh827", "R786",
      "iIB700", "R793",
      "VvuMBEL943", "R806",
      "iOG654", "RM00001",
      "iRsp1095", "RXN1306",
      "iRsp1140", "RXN1306",
      "iMF721", "RXNbiomass",
      "iGT196", "VGRO",
      "iSM197", "VGRO",
      "iNV706", "R_BIOMASS",
      
      "iJM658", "R_Biomass",
      "iKK446", "R_Biomass",
      "iYS432", "R_Vgrowth_RXN",
      
      "iJB785", "R_BOF",
      
      "iAN818m", "R_Biomass_25",
      "iCC908", "R_B0000",
      "iJSPcarbinolicus", "R_agg_Pcar3",
      "iJSPpropionicus", "R_agg_Pcar3",
      "iJL846", "R_RNX_LC2W364",
      "iAM388", "R_Biomass_HP",
      "iYLW1028", "R_biomass",
      "iKY620", "R_AP001",
      "iMR539", "R_biomass0",
      "iHK760", "R90",
      
      "iJN746", "R_BIOMASS_KT_TEMP",
//      "iCac802_V_cobragitsengerpapou", "R_R1462",
  };
  
  public static final String[][] compartments = {
      {"BOUNDARY", "EXTRACELLULAR", "PERIPLASM", "CYTOSOL", "THYLAKOID_LUMEN", "CARBOXYSOME", "THYLAKOID MEMBRANE", "CYTOPLASMIC MEMBRANE"},
      {"Abymbel891", "-", "-", "-", "cell@Abymbel891 (cell)", "-", "-", "-", ""},
      {"GSMN-TB", "-", "Extra_organism@GSMN-TB (-)", "-", "Cytosol@GSMN-TB (-)", "-", "-", "-", ""},
      {"iAbaylyiv4", "-", "Extraorganism@iAbaylyiv4 (-)", "Periplasm@iAbaylyiv4 (-)", "Cytosol@iAbaylyiv4 (-)", "-", "-", "-", ""},
      {"iAI549", "-", "Extra_organism@iAI549 (-)", "-", "Cytosol@iAI549 (-)", "-", "-", "-", ""},
      {"iAI558", "-", "e@iAI558 (Extracellular)", "-", "c@iAI558 (Cytoplasm)", "-", "-", "-", ""},
      {"iAK692_auto", "Extra_organism2@iAK692_auto (-)", "Extra_organism@iAK692_auto (-)", "-", "Cytosol@iAK692_auto (-)", "-", "-", "-", ""},
      {"iAO358", "C_b@iAO358 (External)", "C_e@iAO358 (Extracellular)", "-", "C_c@iAO358 (Cytoplasm)", "-", "-", "-", ""},
      {"iBif452", "C_b@iBif452 (b)", "C_e@iBif452 (Extraorganism)", "-", "C_c@iBif452 (Cytosol)", "-", "-", "-", ""},
      {"iBsu1103", "-", "Extracellular@iBsu1103 (-)", "-", "Cytosol@iBsu1103 (-)", "-", "-", "-", ""},
      {"iBT721_v2", "-", "Extra_organism@iBT721_v2 (-)", "-", "Cytosol@iBT721_v2 (-)", "-", "-", "-", ""},
      {"iCA1273", "-", "Extra_organism@iCA1273 (-)", "Periplasm@iCA1273 (-)", "Cytosol@iCA1273 (-)", "-", "-", "-", ""},
      {"iCAC490", "-", "e@iCAC490 (Extracellular)", "-", "c@iCAC490 (Cytoplasm)", "-", "-", "-", ""},
      {"iCac802", "-", "e@iCac802 (Extracellular)", "-", "c@iCac802 (Cytoplasm)", "-", "-", "-", ""},
//      {"iCac802_V_cobragitsengerpapou", "-", "e@iCac802_V_cobragitsengerpapou (Extracellular)", "-", "c@iCac802_V_cobragitsengerpapou (Cytoplasm)", "-", "-", "-", ""},
      {"iCce806", "-", "Extracellular@iCce806 (-)", "-", "Cytosol@iCce806 (-)", "-", "-", "-", ""},
      {"iCG230", "-", "Extraorganism@iCG230 (-)", "-", "Cytosol@iCG230 (-)", "-", "-", "-", ""},
      {"iCG238", "-", "Extraorganism@iCG238 (-)", "-", "Cytosol@iCG238 (-)", "-", "-", "-", ""},
      {"iCM925", "-", "C_e@iCM925 (Extracellular)", "-", "C_c@iCM925 (Cytoplasm)", "-", "-", "-", ""},
      {"iCR744", "-", "e@iCR744 (Extracellular)", "-", "c@iCR744 (Cytoplasm)", "-", "-", "-", ""},
      {"iCS291", "-", "e@iCS291 (Extracellular)", "-", "c@iCS291 (Cytoplasm)", "-", "-", "-", ""},
      {"iCS400", "-", "e@iCS400 (Extracellular)", "-", "c@iCS400 (Cytoplasm)", "-", "-", "-", ""},
      {"iCyc792", "-", "e@iCyc792 (Extracellular)", "p@iCyc792 (Periplasm)", "c@iCyc792 (Cytosol)", "l@iCyc792 (Thylakoid Lumen)", "x@iCyc792 (Carboxysome)", "-", ""},
      {"iCyh755", "-", "e@iCyh755 (Extracellular)", "p@iCyh755 (Periplasm)", "c@iCyh755 (Cytosol)", "l@iCyh755 (Thylakoid Lumen)", "x@iCyh755 (Carboxysome)", "-", ""},
      {"iCyj826", "-", "e@iCyj826 (Extracellular)", "p@iCyj826 (Periplasm)", "c@iCyj826 (Cytosol)", "l@iCyj826 (Thylakoid Lumen)", "x@iCyj826 (Carboxysome)", "-", ""},
      {"iCyn731", "-", "e@iCyn731 (Extracellular)", "p@iCyn731 (Periplasm)", "c@iCyn731 (Cytosol)", "l@iCyn731 (Thylakoid Lumen)", "x@iCyn731 (Carboxysome)", "-", ""},
      {"iCyp752", "-", "e@iCyp752 (Extracellular)", "p@iCyp752 (Periplasm)", "c@iCyp752 (Cytosol)", "l@iCyp752 (Thylakoid Lumen)", "x@iCyp752 (Carboxysome)", "-", ""},
      {"iCyt773", "-", "e@iCyt773 (Extracellular)", "p@iCyt773 (Periplasm)", "c@iCyt773 (Cytosplasm)", "l@iCyt773 (Thylakoid Lumen)", "ca@iCyt773 (Carboxysome)", "-", ""},
      {"iDsh827", "Extra_organism@iDsh827 (-)", "", "-", "Cytosol@iDsh827 (-)", "-", "-", "-", ""},
      {"iEB458", "-", "Extra_organism@iEB458 (-)", "-", "Cytosol@iEB458 (-)", "-", "-", "-", ""},
      {"iEM439", "-", "e@iEM349 (Extracellular)", "-", "c@iEM349 (Cytoplasm)", "-", "-", "-", ""},
      {"iFap484", "C_b@iFap484 (b)", "C_e@iFap484 (Extraorganism)", "-", "C_c@iFap484 (Cytosol)", "-", "-", "-", ""},
      {"iGB555_fixed", "-", "-", "-", "default@iGB555_fixed (No Compartment)", "-", "-", "-", ""},
      {"iGT196", "-", "e@iGT196 (CCO-EXTRACELLULAR)", "p@iGT196 (CCO-PERIPLASM)", "c@iGT196 (CCO-CYTOSOL)", "-", "-", "-", ""},
      {"iIB700", "Extra_organism@iIB700 (-)", "", "-", "Cytosol@iIB700 (-)", "-", "-", "-", ""},
      {"iJC568", "C_b@iJC568 (boundary)", "C_e@iJC568 (extracellular)", "C_p@iJC568 (periplasm)", "C_c@iJC568 (cytoplasm)", "C_th@iJC568 (thylakoid_lumen)", "C_car@iJC568 (carboxysome)", "C_m@iJC568 (membrane)", ""},
      {"iJH728", "-", "e@iJH728 (Extracellular)", "pps@iJH728 (Periplasmic space)", "c@iJH728 (Cytoplasm)", "tll@iJH728 (Thylakoid lumen)", "cbx@iJH728 (Carboxysome)", "tlm@iJH728 (Thylakoid membrane)", "cpm@iJH728 (Cytoplasmic membrane)"},
      {"iJL432", "-", "-", "-", "Cell@iJL432 (boundary)", "-", "-", "-", ""},
      {"iJL480", "-", "e@iJL480 (e)", "-", "c@iJL480 (c)", "-", "-", "-", ""},
      {"iJP815", "-", "Extra_organism@iJP815 (-)", "-", "Cytosol@iJP815 (-)", "-", "-", "-", ""},
      {"iJP962", "-", "Extra_organism@iJP962 (-)", "-", "Cytosol@iJP962 (-)", "-", "-", "-", ""},
      {"iJS747", "-", "e@iJS747 (Extracellular)", "-", "c@iJS747 (Cytoplasm)", "-", "-", "-", ""},
      {"iJW145", "-", "Extra_organism@iJW145 (-)", "-", "Cytosol@iJW145 (-)", "-", "-", "-", ""},
      {"iKF1028", "-", "Extra_organism@iKF1028 (-)", "-", "Cytosol@iKF1028 (-)", "-", "-", "-", ""},
      {"iLca12A_640", "-", "e@iLca12A_640 (Extracellular)", "-", "c@iLca12A_640 (Cytoplasm)", "-", "-", "-", ""},
      {"iLca334_548", "-", "e@iLca334_548 (Extracellular)", "-", "c@iLca334_548 (Cytoplasm)", "-", "-", "-", ""},
      {"iMA789", "-", "Extra_organism@iMA789 (-)", "-", "Cytosol@iMA789 (-)", "-", "-", "-", ""},
      {"iMA789_cobrafixed", "-", "e@iMA789_cobrafixed (extracellular)", "p@iMA789_cobrafixed (periplasm)", "c@iMA789_cobrafixed (cytosol)", "-", "-", "-", ""},
      {"iMA945", "-", "e@iMA945 (Extracellular)", "p@iMA945 (Periplasm)", "c@iMA945 (Cytoplasm)", "-", "-", "-", ""},
      {"iMB745", "-", "C_e@iMB745 (Extracellular)", "-", "C_c@iMB745 (Cytoplasm)", "-", "-", "-", ""},
      {"iMF721", "b@iMF721 (Boundary)", "e@iMF721 (Extracellular)", "-", "c@iMF721 (Cytosol)", "-", "-", "-", ""},
      {"iMG746", "-", "e@iMG746 (Extracellular)", "-", "c@iMG746 (Cytoplasm)", "-", "-", "-", ""},
      {"iMH551", "", "e@iMH551 (Extracellular)", "-", "default@iMH551 (No Compartment)", "-", "-", "-", ""},
      {"iMK1208", "-", "e@iMK1208 (Extracellular)", "-", "c@iMK1208 (Cytoplasm)", "-", "-", "-", ""},
      {"iMLTC806cdf", "C_b@iMLTC806cdf (Boundary)", "C_e@iMLTC806cdf (Extracellular)", "-", "C_c@iMLTC806cdf (Cytosol)", "-", "-", "-", ""},
      {"iMM518", "-", "-", "-", "cell@iMM518 (-)", "-", "-", "-", ""},
      {"iMO1056", "-", "e@iMO1056 (extracellular)", "p@iMO1056 (periplasm)", "c@iMO1056 (cytosol)", "-", "-", "-", ""},
      {"iMP240", "-", "Extraorganism@iMP240 (-)", "-", "Cytosol@iMP240 (-)", "-", "-", "-", ""},
      {"iMP429_fixed", "-", "-", "-", "default@iMP429_fixed (No Compartment)", "-", "-", "-", ""},
      {"iMZ1055", "-", "e@iMZ1055 (Extracellular)", "-", "c@iMZ1055 (Cytoplasm)", "-", "-", "-", ""},
      {"iNF518", "-", "e@iNF518 (Extracellular)", "-", "c@iNF518 (Cytoplasm)", "-", "-", "-", ""},
      {"iNJ661m", "-", "C_e@iNJ661m (Extracellular)", "-", "C_c@iNJ661m (Cytoplasm)", "-", "-", "-", ""},
      {"iNJ661v", "-", "C_e@iNJ661v (Extracellular)", "-", "C_c@iNJ661v (Cytoplasm)", "-", "-", "-", ""},
      {"iNV706", "-", "e@iNV706 (e)", "-", "c@iNV706 (c)", "-", "-", "-", ""},
      {"iOG654", "-", "extracellular@iOG654 (-)", "-", "intracellular@iOG654 (-)", "-", "-", "-", ""},
      {"iPB890", "-", "e@iPB890 (Extracellular)", "-", "c@iPB890 (Cytoplasm)", "-", "-", "-", ""},
      {"iPS189", "-", "Extra_organism@iPS189 ()", "-", "Cytosol@iPS189 ()", "-", "-", "-", ""},
      {"iRBaquifex", "-", "Extracellular@iRBaquifex (-)", "-", "Cytosol@iRBaquifex (-)", "-", "-", "-", ""},
      {"iRM588", "-", "e@iRM588 (Extracellular)", "-", "c@iRM588 (Cytoplasm)", "-", "-", "-", ""},
      {"iRP911", "-", "External_Species@iRP911 (-)", "", "Internal_Species@iRP911 (-)", "-", "-", "-", ""},
      {"iRR1083", "-", "Extra_organism@iRR1083 (-)", "-", "Cytosol@iRR1083 (-)", "-", "-", "-", ""},
      {"iRS605_fixed", "-", "-", "-", "default@iRS605_fixed (No Compartment)", "-", "-", "-", ""},
      {"iRsp1095", "-", "Extracellular@iRsp1095 (Extracellular)", "Periplasm@iRsp1095 (Periplasm)", "Cytosol@iRsp1095 (Cytosol)", "-", "-", "-", ""},
      {"iRsp1140", "-", "Extracellular@iRsp1140 (Extracellular)", "Periplasm@iRsp1140 (Periplasm)", "Cytosol@iRsp1140 (Cytosol)", "-", "-", "-", ""},
      {"iSB1139", "C_3@iSB1139 (External)", "C_2@iSB1139 (Extracellular)", "-", "C_1@iSB1139 (Cytoplasm)", "-", "-", "-", ""},
      {"iSH335", "-", "-", "-", "Cell@iSH335 (boundary)", "-", "-", "-", ""},
      {"iSM197", "-", "-", "_p_@iSM197 (_p_)", "_c_@iSM197 (_c_)", "-", "-", "-", ""},
      {"iSO783", "-", "Extra_organism@iSO783 (-)", "-", "Cytosol@iSO783 (-)", "-", "-", "-", ""},
      {"iSR432", "-", "Extraorganism@iSR432 (-)", "-", "Cytosol@iSR432 (-)", "-", "-", "-", ""},
      {"iSS352", "-", "External_Species@iSS352 (-)", "-", "Internal_Species@iSS352 (-)", "-", "-", "-", ""},
      {"iSyf715", "-", "Extra_cellular@iSyf715 (-)", "-", "Cytosol@iSyf715 (-)", "-", "-", "-", ""},
      {"iSyn669", "-", "-", "-", "cell@iSyn669 (cell)", "-", "-", "-", ""},
      {"iSyn731", "-", "e@iSyn731 (Extracellular)", "p@iSyn731 (Periplasm)", "c@iSyn731 (Cytosplasm)", "l@iSyn731 (Thylakoid Lumen)", "ca@iSyn731 (Carboxysome)", "-", ""},
      {"iTT548", "-", "e@iTT548 (Extracellular)", "-", "c@iTT548 (Cytoplasm)", "-", "-", "-", ""},
      {"iTY425_fixed", "-", "e@iTY425_fixed (Extracellular)", "-", "c@iTY425_fixed (Cytoplasm)", "-", "-", "-", ""},
      {"iTZ479", "-", "C_e@iTZ479 (Extracellular)", "-", "C_c@iTZ479 (Cytoplasm)", "-", "-", "-", ""},
      {"iVM679", "-", "e@iVM679 (Extracellular)", "-", "c@iVM679 (Cytoplasm)", "-", "-", "-", ""},
      {"iVS941_fixed", "-", "e@iVS941_fixed (Extracellular)", "-", "c@iVS941_fixed (Cytoplasm)", "-", "-", "-", ""},
      {"iVW583", "C_3@iVW583 (External)", "C_2@iVW583 (Extracellular)", "-", "C_1@iVW583 (Cytoplasm)", "-", "-", "-", ""},
      {"iWX1009", "-", "Extracellular@iWX1009 (-)", "-", "Cytosol@iWX1009 (-)", "-", "-", "-", ""},
      {"iWZ663", "-", "e@iWZ663 (Extracellular)", "-", "c@iWZ663 (Cytoplasm)", "-", "-", "-", ""},
      {"iZM363", "-", "e@iZM363 (Extracellular)", "p@iZM363 (Periplasm)", "default@iZM363 (No Compartment)", "-", "-", "-", ""},
      {"iZmobMBEL601", "-", "-", "-", "Cell@iZmobMBEL601 (boundary)", "-", "-", "-", ""},
      {"kb_fbamdl_1903", "-", "e0@kb_fbamdl_1903 (e0)", "-", "c0@kb_fbamdl_1903 (c0)", "-", "-", "-", ""},
      {"Nmb_iTM560", "-", "Extra_organism@Nmb_iTM560 (-)", "-", "Cytosol@Nmb_iTM560 (-)", "-", "-", "-", ""},
      {"PpuMBEL1071", "-", "-", "-", "cell@PpuMBEL1071 (-)", "-", "-", "-", ""},
      {"sMtb", "-", "e@sMtb (Extracellular)", "-", "c@sMtb (Cytoplasm)", "-", "-", "-", ""},
      {"VvuMBEL943", "-", "-", "-", "cell@VvuMBEL943 (-)", "-", "-", "-", ""}
  };
  
  public static Map<String, Map<String, Map<String, String>>> getSimData() {
    String SEP = ";";
    String MODEL_FIELD = "Model";
    String REACTION_FIELD = "Reaction";
    InputStream is;
    
    //model -> reaction -> data
    Map<String, Map<String, Map<String, String>>> modelSimData = new HashMap<> ();
//    OptimizationData od;
    try {
      is = new FileInputStream("/var/biomodels/joana_model_FR_test.csv");
      List<String> lines = org.apache.commons.io.IOUtils.readLines(is);
      String[] header = lines.get(0).split(SEP);
      System.out.println(lines.get(0));
      List<Map<String, String>> ldata = new ArrayList<>();
      for (int i = 1; i < lines.size(); i++) {
        String[] columns = lines.get(i).split(SEP);
        Map<String, String> data = new HashMap<> ();
        for (int j = 0; j < columns.length; j++) {
          data.put(header[j], columns[j]);
        }
        ldata.add(data);
      }
      
      System.out.println(ldata.get(0));
      
      for (Map<String, String> data : ldata) {
        String model = data.get(MODEL_FIELD);
        String reaction = data.get(REACTION_FIELD);
        if (model != null && reaction != null && 
            !model.trim().isEmpty() && !reaction.trim().isEmpty()) {
          if (!modelSimData.containsKey(model)) {
            modelSimData.put(model, new HashMap<String, Map<String, String>>());
          }
          if (!modelSimData.get(model).containsKey(reaction)) {
            modelSimData.get(model).put(reaction, data);
          }
        } else {
          logger.warn("discard: {}", data);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return modelSimData;
  }
  
  public static final String[] worksMissingBounds = {
      "iSR432",
      
      "iMM518",
      "iPS189",
      "iRR1083",
      "iMLTC806cdf",
      "iSyf715",
      "iDsh827",
      "iOG654",
      "iAbaylyiv4",
      "iRP911",
      "iSS352",
      "iJC568",
      "iAI549",
      "VvuMBEL943",
      "Abymbel891",
      "iSO783",
      "iCyt773",
      //NEEDS TO OPEN ALL DRAINS
      "iRsp1095",
      "iRsp1140",
  };
  
  public static final String[] approved = {
      "iJW145", //remove _b
      "iPB890",
      "iJP815",
      "iJP962",
      "iKF1028",
      
      "kb_fbamdl_1903",
      "iBsu1103",
      "iMF721",
      "iRBaquifex",
      "iSM197",
      "iTT548",
      "iMP240",
      "iEB458",
      "iYS432",
      "iCce806",
      "iMK1208",
      "iJM658",
      "iMZ1055",
      "iCM925",
      "iSyn669",
      "iCS291",
      "iCyp752",
      "iCyh755",
      "iCyn731",
      "iCyc792",
      "iCyj826",
      "iJL432",
      "iVS941_fixed",
      "iMH551",
      "iMP429_fixed",
      "iTY425_fixed",
      "iSH335",
      "iZM363",
      "iVM679",
      "iWZ663",
      "iJS747",
      "iMA945",
      "iGB555_fixed",
      "iMA789_cobrafixed",
      "iJB785",
      "iMG746",
      "iAI558",
      "iMB745",
      "iFap484",
      "iBif452",
      "iNF518",
      "GSMN-TB",
      "iCac802",
      "iCA1273",
      "Nmb_iTM560",
      "iKK446",
      "iNJ661v",
      "iNJ661m",
      "iLca12A_640",
      "iZmobMBEL601",
      "iLca334_548",
      "iJL480",
      "iAO358",
      "iCR744",
      "iCG238",
      "iRM588",
      "iIB700",
      "iCS400",
      "iEM439",
      "iMO1056",
      "iRS605_fixed",
      "iCG230",
      "iCAC490",
      "iWX1009",
      "iJH728",
  };
  
  public static final String[] experimentalData = {
      "GSMN-TB",
      "iAbaylyiv4",
      "iBsu1103",
      "iJB785",
      "iJL480",
      "iJM658",
      "iJW145",
      
      "iKK446",
      
      "iMO1056",
      "iNJ661m",
      "iNJ661v",
      "iPS189",
      "iRR1083",
      
      "iSO783",
      "iYS432",
      "sMtb",
      
      "iMM518",
      "iMA945",
      "iKF1028",
      "iRS605_fixed",
  };
  
  public static final String[][] manualAnnotation = {
      {"", 
       "10fthf_c", 
       "2fe2s_c", 
       "4fe4s_c", 
       "amet_c", 
       "coa_c", 
       "fad_c", 
       "mlthf_c", 
       "nad_c", 
       "nadp_c", "pydx5p_c", "fmn_c", "thmpp_c", "utp_c", "ctp_c", "gtp_c", "datp_c", "dctp_c", "dgtp_c", "dttp_c", "mn2_c", "mobd_c", "so4_c", "zn2_c", "ca2_c", "cl_c", "cobalt2_c", "cu2_c", "fe2_c", "fe3_c", "k_c", "mg2_c", "nh4_c", "ni2_c"},
      {"iNF518", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "not found", "M_ribflv_c M_fmn_c M_ribflv_e", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c", "-", "M_so4_e M_so4_c", "M_zn2_e M_zn2_c", "-", "-", "M_co2_e M_co2_c", "-", "M_fe2_c M_fe2_e", "M_fe3_e M_fe3_c", "-", "-", "M_nh3_c M_nh4_e M_nh4_c", "-"},
      {"iSS352", "not found", "-", "-", "M_AdoMet_c", "M_CoA_c", "M_FAD_c", "not found", "M_NAD_c", "M_NADP_c", "not found", "not found", "not found", "M_UTP_c", "M_CTP_c", "M_GTP_c", "not found", "not found ", "not found ", "M_dTTP_c", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "M_NH4_c M_NH4_e", "-"},
      {"iGT196", "_10fthf", "-", "-", "amet", "coa", "fad", "mlthf", "nad", "nadp", "not found", "fmn", "thmpp", "utp", "ctp", "gtp", "datp", "dctp", "dgtp", "dttp", "-", "-", "so4", "-", "-", "-", "co2", "-", "fe2", "-", "-", "-", "nh4", "-"},
      {"iMO1056", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_ribflv_c M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c", "M_mobd_e M_mobd_c", "M_so4_e M_so4_c", "M_zn2_e M_zn2_c", "-", "-", "M_cobalt2_c M_cobalt2_e M_co2_e M_co2_c", "M_cu2_e M_cu2_c", "M_fe2_c M_fe2_e", "M_fe3_e M_fe3_c", "M_k_c M_k_e", "M_mg2_c M_mg2_e", "M_nh3_c M_nh4_e M_nh4_c", "M_ni2_e M_ni2_c"},
      {"iMZ1055", "M_FTHF_c", "-", "-", "M_SAM_c", "M_COA_c", "M_FAD_c", "M_METTHF_c", "M_NAD_c", "M_NADP_c", "M_PDXL5PI_c", "M_RIBFLAV_c M_FMN_e M_FMN_c", "M_THDP_c", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_DATP_c", "M_DCTP_c", "M_DGTP_c", "M_DTTP_c", "M_MN2_e M_MN2_c", "-", "M_SLF_e M_SLF_c", "M_ZN_c M_ZN_e", "M_CA_e M_CA_c", "M_CL_c", "M_CO2_c M_COBALT2_c M_COBALT2_e", "M_CU_e", "M_FE2_e M_FE2_c", "M_FE3_c M_FE3_e", "M_K_c M_K_e", "M_MG_e M_MG_c", "M_NH3_c M_NH3_e M_N2_e M_N2_c", "-"},
      {"iJM658", "M_FTHF_c", "-", "-", "M_SAM_c", "M_COA_c", "M_FAD_c", "M_METTHF_c", "M_NAD_c", "M_NADP_c", "M_PDXL5PI_c", "M_FMN_c", "M_THDP_e M_TPP_c M_THDP_c", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_DATP_c", "M_DCTP_c", "M_DGTP_c", "M_DTTP_c", "M_MN2_e", "-", "M_SLF_e M_SLF_c", "M_ZN_c M_ZN_e", "-", "M_CL_c", "M_COBALT2_c M_COBALT2_e", "M_CU_e", "M_FE2_e M_FE2_c", "M_FE3_c M_FE3_e", "M_K_c M_K_e", "M_MG_e M_MG_c", "M_NH3_c M_NH3_e", "-"},
      {"iZM363", "M_a_10fthf", "-", "-", "M_amet M_amet_e", "M_coa", "M_fad", "M_mlthf", "M_nad_e M_nad", "M_nadp", "M_pydx5p", "M_FMN_e M_FMN", "M_tpp M_thmpp", "M_utp", "M_ctp", "M_gtp", "M_datp", "M_dctp", "M_dgtp", "M_dttp", "-", "-", "M_so4_e M_so4", "-", "-", "-", "M_co2", "-", "M_fe2 M_fe2_e", "-", "-", "-", "M_nh4 M_nh4_e", "-"},
      {"iDsh827", "S553", "-", "-", "S476", "S140", "S426", "S116", "S91", "S100", "S993", "S1151 S385", "S415", "S160", "S767", "S927", "S895", "S1129", "S1078", "S901", "-", "S921", "S85", "-", "S912", "-", "S20 S22", "S911", "S26", "-", "-", "S34", "S121 S174", "-"},
      {"iOG654", "C00234", "-", "-", "C00019", "C00010", "C00016", "C00143", "C00003", "C00006", "not found", "C00255 C00061", "C00068", "C00075", "C00063", "C00044", "C00131", "C00458", "C00286", "C00459", "C00034", "-", "C00059", "C00038", "-", "-", "C00011 C00175", "C00070", "-", "-", "C00238", "C00305", "C00014", "-"},
      {"iMA945", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_e M_gtp_c M_gtp_p", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c M_mn2_p", "M_mobd_p M_mobd_e M_mobd_c", "M_so4_p M_so4_e M_so4_c", "M_zn2_e M_zn2_c M_zn2_p", "M_ca2_e M_ca2_c M_ca2_p", "M_cl_c M_cl_p M_cl_e", "M_cobalt2_c M_cobalt2_e M_cobalt2_p", "M_cu2_e M_cu2_c M_cu2_p", "M_fe2_p M_fe2_c M_fe2_e", "M_fe3_e M_fe3_p M_fe3_c", "M_k_p M_k_c M_k_e", "M_mg2_c M_mg2_e M_mg2_p", "M_nh4_p M_nh4_e M_nh4_c", "M_ni2_e M_ni2_c M_ni2_p"},
      {"iCyc792", "M_cpd00201_c", "-", "-", "M_cpd00017_c", "M_cpd00010_c", "M_cpd00015_c", "M_cpd00125_c", "M_cpd00003_c", "M_cpd00006_c", "M_cpd00016_c", "M_cpd00050_c M_cpd00220_c", "M_cpd00056_c", "M_cpd00062_c", "M_cpd00052_c", "M_cpd00038_c", "M_cpd00115_c", "M_cpd00356_c", "M_cpd00241_c", "M_cpd00357_c", "M_cpd00030_p M_cpd00030_c M_cpd00030_e", "M_cpd11574_p M_cpd11574_e", "M_cpd00048_c M_cpd00048_p M_cpd00048_e", "M_cpd00034_p M_cpd00034_e", "M_cpd00063_p M_cpd00063_e M_cpd00063_c", "-", "M_cpd00204_c M_cpd00204_e M_cpd00011_p M_cpd00011_c M_cpd00204_p M_cpd00011_e M_cpd00149_p M_cpd00149_c M_cpd00011_x M_cpd00149_e", "M_cpd00058_p M_cpd00058_c M_cpd00058_e", "M_cpd10515_p M_cpd10515_c M_cpd10515_e", "-", "M_cpd00205_c M_cpd00205_p M_cpd00205_e", "M_cpd00254_e M_cpd00254_c M_cpd00254_p", "M_cpd00013_p M_cpd00013_c M_cpd00013_e", "M_cpd00244_e M_cpd00244_c M_cpd00244_p"},
      {"iRBaquifex", "cpd00201_c", "-", "-", "cpd00017_c", "cpd00010_c", "cpd00015_c", "cpd00125_c", "cpd00003_c", "cpd00006_c", "cpd00016_c", "cpd00050_c", "cpd00056_c", "cpd00062_c", "cpd00052_c", "cpd00038_c", "cpd00115_c", "cpd00356_c", "cpd00241_c", "cpd00357_c", "cpd00030_c", "cpd11574_c", "cpd00048_c", "cpd00034_c", "cpd00063_c", "-", "cpd00149_c", "cpd00058_c", "cpd10515_c", "-", "cpd00205_c", "cpd00254_c", "cpd00013_c", "-"},
      {"sMtb", "M_FTHF_c", "-", "-", "M_SAM_c", "M_COA_c", "M_FAD_c", "M_METTHF_c", "M_NAD_c", "M_NADP_c", "M_PYRA5P_c", "M_RIBFLAV_c M_RIBFLAV_e M_FMN_c", "M_THIPP_c", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_DATP_c", "M_DCTP_c", "M_DGTP_c", "M_DTTP_c", "M_MN_c M_MN_e", "M_MOLYBDATE_c M_MOLYBDATE_e", "M_SLF_e M_SLF_c", "M_ZN_c M_ZN_e", "M_CA_e M_CA_c", "M_CL_c M_CL_e", "M_COII_c M_CO2_c M_CO_e M_CO2_e M_CO_c M_COII_e", "M_CU_e M_CU_c", "M_FE2_e M_FE2_c", "M_FE3_c M_FE3_e", "M_K_c M_K_e", "M_MG_e M_MG_c", "M_NH3_c M_NH3_e", "M_NI_c M_NI_e"},
      {"iJL432", "M__10FTHF_Cell", "-", "-", "M_AMET_Cell", "M_COA_Cell", "M_FAD_Cell", "M_MLTHF_Cell", "M_NAD_Cell", "M_NADP_Cell", "not found", "M_FMN_Cell", "M_THMPP_Cell", "M_UTP_Cell", "M_CTP_Cell", "M_GTP_Cell", "M_dATP_Cell", "M_dCTP_Cell", "M_dGTP_Cell", "M_dTTP_Cell", "-", "-", "M_SO4_Cell", "-", "-", "-", "M_CO2_Cell M_COBALT_Cell", "-", "M_Fe2_Cell", "-", "-", "-", "M_NH3_Cell", "-"},
      {"iSO783", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_b", "M_mobd_e M_mobd_b M_mobd_c", "M_so4_e M_so4_b M_so4_c", "-", "M_ca2_e M_ca2_b M_ca2_c", "M_cl_b M_cl_c M_cl_e", "M_cobalt2_b M_cobalt2_c M_cobalt2_e", "M_cu2_e M_cu2_b M_cu2_c", "M_fe2_b M_fe2_c M_fe2_e", "M_fe3_e M_fe3_b M_fe3_c", "M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e", "M_nh4_b M_nh4_e M_nh4_c", "M_ni2_e M_ni2_b M_ni2_c"},
      {"iJS747", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "not found", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c", "M_mobd_e M_mobd_c", "M_so4_e M_so4_c", "M_zn2_e M_zn2_c", "M_ca2_e M_ca2_c", "M_cl_c M_cl_e", "M_cobalt2_c M_cobalt2_e", "M_cu2_e M_cu2_c", "M_fe2_c M_fe2_e", "M_fe3_e", "M_k_c M_k_e", "M_mg2_c M_mg2_e", "M_nh4_e M_nh4_c", "M_ni2_e M_ni2_c"},
      {"iVS941_fixed", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "not found", "M_mlthf_c", "M_nad_c", "M_nadp_c", "not found", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c", "M_mobd_e", "M_so4_e M_so4_c", "M_zn2_e M_zn2_c", "M_ca2_e M_ca2_c", "M_cl_c M_cl_e", "M_cobalt2_c M_cobalt2_e", "M_cu2_e M_cu2_c", "M_fe2_c M_fe2_e", "M_fe3_e M_fe3_c", "M_k_c M_k_e", "M_mg2_c M_mg2_e", "M_nh4_e M_nh4_c M_C00014_c", "M_ni2_e M_ni2_c"},
      {"iEM439", "M_10fthf_c", "M_2fe2s_c", "M_4fe4s_c", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_ribflv_c M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "M_so4_e M_so4_c", "-", "-", "-", "M_co2_e M_co2_c", "M_cu2_e M_cu2_c", "M_fe2_c M_fe2_e", "-", "M_k_c M_k_e", "M_mg2_c M_mg2_e", "M_nh4_e M_nh4_c M_n2_c", "-"},
      {"Abymbel891", "FTHF", "-", "-", "SAM", "COA", "FAD", "METTHF", "NAD", "NADP", "PL5P", "FMN", "THMPP", "UTP", "CTP", "GTP", "DATP", "DCTP", "DGTP", "DTTP", "-", "-", "-", "-", "CA", "CL", "CO2", "-", "-", "-", "-", "-", "NH3", "-"},
      {"iNJ661m", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c M_coa_b M_coa_e", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_ribflv_c M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "M_so4_e M_so4_b M_so4_c", "-", "M_ca2_e M_ca2_b M_ca2_c", "M_cl_b M_cl_c M_cl_e", "M_cobalt2_b M_cobalt2_c M_cobalt2_e M_co2_e M_co2_c", "M_cu2_e M_cu2_b M_cu2_c", "M_fe2_b M_fe2_c M_fe2_e", "M_fe3_e M_fe3_b M_fe3_c", "M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e", "M_nh4_b M_nh4_e M_nh4_c", "-"},
      {"VvuMBEL943", "FTHF", "-", "-", "SAM", "COA", "FAD", "METTHF", "NAD", "NADP", "PL5P", "RIBFLAV FMN", "THMPP", "UTP", "CTP", "GTP", "DATP", "DCTP", "DGTP", "DTTP", "-", "-", "SLF", "-", "-", "CL", "CO2", "-", "-", "-", "-", "-", "NH3", "-"},
      {"iSB1139", "not found", "-", "-", "M_r1436", "M_r850", "M_r1000", "not found", "M_r1299", "M_r1301", "M_r1408", "M_r1427 M_r1005", "M_r1476", "M_r446", "M_r860", "M_r1070", "M_r886", "M_r890", "M_r920", "M_r979", "M_r166 M_r306 M_r307", "-", "M_r1462 M_r1461 M_r181", "M_r456 M_r457 M_r194", "M_r810 M_r809 M_r191", "-", "M_r843 M_r100 M_r210 M_r282 M_r844 M_r281", "-", "M_r123 M_r293 M_r1004", "-", "M_r138 M_r239 M_r1138 M_r1139", "M_r161 M_r1255 M_r1254", "M_r204 M_r771 M_r95 M_r772", "-"},
      {"iRsp1095", "CPD0159", "-", "-", "CPD0017", "CPD0010", "CPD0015", "CPD0110", "CPD0003", "CPD0006", "CPD0016", "CPD0168 CPD0049", "CPD0055", "CPD0059", "CPD0051", "CPD0036", "CPD0102", "CPD0173", "CPD0180", "CPD0236", "CPD0832 CPD0832_b CPD0830 CPD0831", "CPD0731 CPD0732_b CPD0721 CPD0732", "CPD0730 CPD0047 CPD0730_b CPD0729", "CPD0827_b CPD0825 CPD0826 CPD0827", "CPD0881 CPD0879 CPD0880 CPD0881_b", "-", "CPD0835_b CPD0129 CPD0834 CPD0835 CPD0011", "CPD0820", "CPD0658 CPD0829 CPD0829_b CPD0828", "-", "CPD1119 CPD0369 CPD1120_b CPD1120", "CPD1114 CPD1113 CPD1114_b CPD0186", "CPD0013 CPD0650 CPD0874 CPD0875_b CPD0875", "CPD1071 CPD1072 CPD0151 CPD1072_b"},
      {"iSyn731", "cpd00201_c", "-", "-", "cpd00017_c", "cpd00010_c", "cpd00015_c", "cpd00125_c", "cpd00003_c", "cpd00006_c", "cpd00016_c", "cpd00220_c cpd00050_c", "cpd00056_c", "cpd00062_c", "cpd00052_c", "cpd00038_c", "cpd00115_c", "cpd00356_c", "cpd00241_c", "cpd00357_c", "cpd00030_e cpd00030_c cpd00030_p", "cpd11574_e cpd11574_p cpd11574_c", "cpd00048_p cpd00048_c cpd00048_e", "cpd00034_c cpd00034_p cpd00034_e", "cpd00063_e cpd00063_c cpd00063_p", "-", "cpd00011_ca cpd00011_c cpd00204_e cpd00011_e cpd00204_c cpd00011_p cpd00204_p cpd00149_c cpd00149_p cpd00149_e", "cpd00058_p cpd00058_l cpd00058_c cpd00058_e", "cpd10515_e cpd10515_p cpd10515_c", "-", "cpd00205_e cpd00205_c cpd00205_p", "cpd00254_p cpd00254_c cpd00254_e", "cpd00013_p cpd00013_c cpd00013_e", "cpd00244_p cpd00244_c cpd00244_e"},
      {"iZmobMBEL601", "M_FTHF_Cell", "-", "-", "M_SAM_Cell", "M_COA_Cell", "M_FAD_Cell", "M_METTHF_Cell", "M_NAD_Cell", "M_NADP_Cell", "M_PL5P_Cell", "M_FMN_Cell", "M_TPP_Cell M_THMPP_Cell M_THPP_Cell", "M_UTP_Cell", "M_CTP_Cell", "M_GTP_Cell", "M_DATP_Cell", "M_DCTP_Cell", "M_DGTP_Cell", "M_DTTP_Cell", "-", "-", "-", "-", "-", "M_CL_Cell", "M_CO2_Cell", "-", "M_Fe_Cell", "-", "M_K_Cell", "-", "M_NH3_Cell", "-"},
      {"iMK1208", "M_10fthf_c", "M_2fe2s_c", "M_4fe4s_c", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_ribflv_c M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c", "M_mobd_e M_mobd_c", "M_so4_e M_so4_c", "M_zn2_e M_zn2_c", "M_ca2_e M_ca2_c", "M_cl_c M_cl_e", "M_cobalt2_c M_cobalt2_e M_co2_e M_co2_c", "M_cu2_e M_cu_e M_cu_c M_cu2_c", "M_fe2_c M_fe2_e", "M_fe3_e M_fe3_c", "M_k_c M_k_e", "M_mg2_c M_mg2_e", "M_nh4_e M_nh4_c", "M_ni2_e M_ni2_c"},
      {"Nmb_iTM560", "M_M_10fthf_c_c", "-", "-", "M_M_amet_c_c", "M_M_coa_c_c", "M_M_fad_c_c", "M_M_mlthf_c_c", "M_M_nad_c_c", "M_M_nadp_c_c", "M_M_pydx5p_c_c", "M_M_fmn_c_c", "M_M_thmpp_c_c", "M_M_utp_c_c", "M_M_ctp_c_c", "M_M_gtp_e_c M_M_gtp_c_c M_M_gtp_p_c M_M_gtp_b_xt_b", "M_M_datp_c_c", "M_M_dctp_c_c", "M_M_dgtp_c_c", "M_M_dttp_c_c", "M_M_mn2_b_xt_b M_M_mn2_p_c M_M_mn2_c_c M_M_mn2_e_c", "M_M_mobd_b_xt_b M_M_mobd_c_c M_M_mobd_e_c M_M_mobd_p_c", "M_M_so4_b_xt_b M_M_so4_e_c M_M_so4_c_c M_M_so4_p_c", "M_M_zn2_b_xt_b M_M_zn2_p_c M_M_zn2_c_c M_M_zn2_e_c", "M_M_ca2_b_xt_b M_M_ca2_c_c M_M_ca2_e_c M_M_ca2_p_c", "M_M_cl_c_c M_M_cl_e_c M_M_cl_p_c M_M_cl_b_xt_b", "M_M_cobalt2_p_c M_M_cobalt2_c_c M_M_cobalt2_e_c M_M_cobalt2_b_xt_b", "M_M_cu2_b_xt_b M_M_cu2_c_c M_M_cu2_e_c M_M_cu2_p_c", "M_M_fe2_b_xt_b M_M_fe2_c_c M_M_fe2_p_c M_M_fe2_e_c", "M_M_fe3_p_c M_M_fe3_b_xt_b M_M_fe3_c_c M_M_fe3_e_c", "M_M_k_e_c M_M_k_b_xt_b M_M_k_p_c M_M_k_c_c", "M_M_mg2_e_c M_M_mg2_c_c M_M_mg2_p_c M_M_mg2_b_xt_b", "M_M_nh4_c_c M_M_nh4_p_c M_M_nh4_b_xt_b M_M_nh4_e_c", "M_M_ni2_b_xt_b M_M_ni2_e_c M_M_ni2_c_c M_M_ni2_p_c"},
      {"iNJ661v", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c M_coa_b M_coa_e", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_ribflv_c M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "M_so4_e M_so4_b M_so4_c", "-", "M_ca2_e M_ca2_b M_ca2_c", "M_cl_b M_cl_c M_cl_e", "M_cobalt2_b M_cobalt2_c M_cobalt2_e M_co2_e M_co2_c", "M_cu2_e M_cu2_b M_cu2_c", "M_fe2_b M_fe2_c M_fe2_e", "M_fe3_e M_fe3_b M_fe3_c", "M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e", "M_nh4_b M_nh4_e M_nh4_c", "-"},
      {"iMM518", "fthf", "-", "-", "sam", "coa", "fad", "mlthf", "nad", "nadp", "pyr5p", "fmn ribflv", "thpp", "utp", "ctp", "gtp", "datp", "dctp", "dgtp", "dttp", "-", "mobd_e mobd", "so4 so4_e", "-", "-", "-", "-", "-", "fe2 fe2_e", "Fe3_e fe3", "-", "mg2_e mg2", "nh3_e n2 nh3 nh4 nh4_e", "ni2_e ni2"},
      {"iMF721", "cpd00201_c", "-", "-", "cpd00017_c", "cpd00010_b cpd00010_e cpd00010_c", "cpd00015_b cpd00015_c", "cpd00125_c", "cpd00003_c", "cpd00006_c", "cpd00016_c", "cpd00050_c", "cpd00056_c", "cpd00062_c", "cpd00052_c", "cpd00038_c", "cpd00115_c", "cpd00356_c", "cpd00241_c", "cpd00357_c", "cpd00030_e cpd00030_b cpd00030_c", "-", "cpd00048_c cpd00048_b cpd00048_e", "cpd00034_b cpd00034_c cpd00034_e", "cpd00063_e cpd00063_c cpd00063_b", "-", "cpd00149_c cpd00149_b cpd00149_e", "cpd00058_b cpd00058_c cpd00058_e", "cpd10515_e cpd10515_b cpd10515_c", "-", "cpd00205_e cpd00205_c cpd00205_b", "cpd00254_c cpd00254_b cpd00254_e", "cpd00013_b cpd00013_c cpd00013_e", "-"},
      {"iCyt773", "cpd00201_c", "-", "-", "cpd00017_c", "cpd00010_c", "cpd00015_c", "cpd00125_c", "cpd00003_c", "cpd00006_c", "cpd00016_c", "cpd00220_c cpd00050_c", "cpd00056_c", "cpd00062_c", "cpd00052_c", "cpd00038_c", "cpd00115_c", "cpd00356_c", "cpd00241_c", "cpd00357_c", "cpd00030_e cpd00030_c cpd00030_p", "cpd11574_e cpd11574_p cpd11574_c", "cpd00048_p cpd00048_c cpd00048_e", "cpd00034_c cpd00034_p cpd00034_e", "cpd00063_e cpd00063_c cpd00063_p", "-", "cpd00011_ca cpd00011_c cpd00204_e cpd00011_e cpd00204_c cpd00011_p cpd00204_p cpd00149_c cpd00149_p cpd00149_e", "cpd00058_p cpd00058_c cpd00058_e", "cpd10515_e cpd10515_p cpd10515_c", "-", "cpd00205_e cpd00205_c cpd00205_p", "cpd00254_p cpd00254_c cpd00254_e", "cpd00013_p cpd00013_c cpd00528_p cpd00528_e cpd00528_c cpd00013_e", "cpd00244_p cpd00244_c cpd00244_e"},
      {"iBif452", "M_m447", "-", "-", "M_m246", "M_m180", "M_m190", "M_m163", "-", "-", "-", "M_m426", "-", "M_m476", "M_m477", "M_m195 M_m537", "M_m503", "M_m478", "M_m500", "M_m550", "-", "-", "M_m419 M_m420 M_m421", "-", "-", "-", "-", "-", "-", "-", "-", "-", "M_m11 M_m10 M_m12", "-"},
      {"iAI558", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_ribflv_c M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c", "M_mobd_e M_mobd_c", "M_so4_e M_so4_c", "M_zn2_e M_zn2_c", "M_ca2_e M_ca2_c", "M_cl_c M_cl_e", "M_cobalt2_c M_cobalt2_e M_co_c", "M_cu2_e M_cu2_c", "M_fe3_e M_fe2_c M_fe3_c M_fe2_e", "M_fe3_e M_fe3_c", "M_k_c M_k_e", "M_mg2_c M_mg2_e", "M_nh4_e M_nh4_c M_n2_c", "-"},
      {"iEB458", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_e M_nad_c M_nad_b", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "M_so4_e M_so4_b M_so4_c", "-", "-", "-", "-", "-", "M_fe2_b M_fe2_c M_fe2_e", "-", "M_k_c M_k_b M_k_e", "-", "M_nh4_b M_nh4_e M_nh4_c", "-"},
      {"iSH335", "M_FTHF", "-", "-", "M_SAM", "M_COA", "M_FADH2 M_FAD", "M_METTHF", "M_NAD", "M_NADP", "M_PL5P", "M_FMN", "M_TPP", "M_UTP", "M_CTP", "M_GTP", "M_DATP", "M_DCTP", "M_DGTP", "M_TTP", "-", "-", "M_H2SO4", "-", "-", "M_CL", "-", "-", "-", "-", "-", "-", "M_NH3", "-"},
      {"iYS432", "M_10_FORMYL_THF_cytosol", "-", "-", "M_S_ADENOSYLMETHIONINE_cytosol", "M_CO_A_cytosol", "M_FAD_cytosol", "M_METHYLENE_THF_cytosol", "M_NAD_cytosol", "M_NADP_cytosol", "not found", "M_FMN_cytosol", "not found", "M_UTP_cytosol", "M_CTP_cytosol", "M_GTP_cytosol", "M_DATP_cytosol", "M_DCTP_cytosol", "M_DGTP_cytosol", "M_TTP_cytosol", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
      {"iJC568", "M_10_Formyltetrahydrofolate", "-", "-", "M_S_Adenosyl_L_methionine", "M_CoA", "M_FAD_e M_FAD", "M_5_10_Methylenetetrahydrofolate", "M_NAD", "M_NADP", "not found", "M_FMN M_Riboflavin", "M_Thiamin_diphosphate_e M_Thiamin_diphosphate", "M_UTP", "M_CTP", "M_GTP", "M_dATP", "M_dCTP", "M_dGTP", "M_dTTP", "-", "-", "M_Sulfate M_Sulfate_e", "M_Zn2_e M_Zn2", "M_Calcium_cation M_Calcium_cation_e", "-", "M_Cobalt_ion M_Cobalt_ion_e M_CO M_CO2_e M_CO2_car M_CO_e M_CO2", "M_Copper M_Copper_e", "M_Fe2_e M_Fe2", "-", "M_K_e M_K", "M_Magnesium_cation_e M_Magnesium_cation", "M_Ammonia_e M_Ammonia", "-"},
      {"iSyf715", "_10_formyl_tetrahydrofolate", "-", "-", "S_adenosyl_L_methionine", "CoA coenzymeA", "FAD", "_5_10_methylene_THF", "NAD_plus", "NADP_plus", "not found", "FMN riboflavin", "thiaminediphosphate", "UTP", "CTP", "GTP", "dATP", "dCTP", "dGTP", "dTTP", "-", "molybdate", "-", "Zn2_plus", "Ca2_plus", "-", "Co2_plus CO2 CO", "Cu2_plus", "Fe2_plus", "-", "K_plus", "Mg2_plus", "NH4_plus", "-"},
      {"iSM197", "_10fthf_c_", "-", "-", "amet_c_", "coa_c_", "fad_c_", "mlthf_c_", "nad_c_", "nadp_c_", "not found", "fmn_c_", "thmpp_c_", "utp_c_", "ctp_c_", "gtp_c_", "datp_c_", "dctp_c_", "dgtp_c_", "dttp_c_", "-", "-", "so4_c_", "-", "-", "-", "co2_c_", "-", "fe2_c_", "-", "-", "-", "nh4_c_", "-"},
      {"iTY425_fixed", "M_FTHF_c", "-", "-", "M_SAM_c", "M_COA_c", "M_FAD_c", "M_METTHF_c", "M_NAD_c", "M_NADP_c", "M_PL5P_c", "M_FMN_c", "M_THMPP_c", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_DATP_c", "M_DCTP_c", "M_DGTP_c", "M_DTTP_c", "-", "-", "-", "-", "-", "M_CL_c", "M_CO2_c", "-", "-", "-", "-", "-", "M_NH3_c M_NH3_e", "-"},
      {"iCAC490", "M_cid0000182_c", "-", "-", "M_cid0000018_c", "M_cid0000010_c", "M_cid0000015_c", "M_cid0000125_c", "M_cid0000003_c", "M_cid0000006_c", "M_cid0000017_c", "M_cid0000052_c M_cid0000201_c", "M_cid0000058_c", "M_cid0000063_c", "M_cid0000054_c", "M_cid0000041_c", "M_cid0000114_c", "M_cid0000320_c", "M_cid0000224_c", "M_cid0000321_c", "-", "-", "M_cid0000051_e M_cid0000051_c", "-", "-", "-", "M_cid0000185_c M_cid0000011_c M_cid0000011_e", "-", "-", "-", "M_cid0000186_c M_cid0000186_e", "-", "M_cid0000418_c M_cid0000013_c M_cid0000013_e M_cid0000418_e", "-"},
      {"iCac802", "M_cpd00201_c", "-", "-", "-", "M_cpd00010_c", "M_cpd00015_c", "-", "-", "-", "M_cpd00016_c", "M_cpd00050_c", "M_cpd00056_c", "M_cpd00062_c", "M_cpd00052_c", "M_cpd00038_c", "M_cpd00115_c", "M_cpd00356_c", "M_cpd00241_c", "M_cpd00357_c", "M_cpd00030_c M_cpd00030_e", "M_cpd11574_c M_cpd11574_e", "M_cpd00048_c M_cpd00048_e", "M_cpd00034_e M_cpd00034_c", "M_cpd00063_e M_cpd00063_c", "-", "M_cpd00149_c M_cpd00149_e", "M_cpd00058_c M_cpd00058_e", "M_cpd10515_c M_cpd10515_e", "-", "M_cpd00205_c M_cpd00205_e", "M_cpd00254_e M_cpd00254_c", "M_cpd00013_c M_cpd00013_e", "-"},
      {"iRsp1140", "CPD0159", "-", "-", "CPD0017", "CPD0010", "CPD0015", "CPD0110", "CPD0003", "CPD0006", "CPD0016", "CPD0168 CPD0049", "CPD0055", "CPD0059", "CPD0051", "CPD0036", "CPD0102", "CPD0173", "CPD0180", "CPD0236", "CPD0832 CPD0832_b CPD0830 CPD0831", "CPD0731 CPD0732_b CPD0732 CPD0721", "CPD0730 CPD0047 CPD0730_b CPD0729", "CPD0827_b CPD0825 CPD0826 CPD0827", "CPD0881 CPD0879 CPD0880 CPD0881_b", "-", "CPD0835_b CPD0833 CPD0129 CPD0638 CPD0834 CPD0835 CPD0011", "CPD0820 CPD0823", "CPD0658 CPD0829 CPD0829_b CPD0828", "-", "CPD1119 CPD0369 CPD1120_b CPD1120", "CPD1114 CPD1113 CPD1114_b CPD0186", "CPD0013 CPD0650 CPD0874 CPD0875_b CPD0875", "CPD1071 CPD1072 CPD0151 CPD1072_b"},
      {"iJW145", "C0193", "-", "-", "C0017", "C0010", "C0015", "C0122", "C0003", "C0006", "C0016", "C0212 C0049", "C0055", "C0060", "C0051", "C0038", "C0113", "C0351", "C0233", "C0352", "-", "-", "-", "-", "-", "-", "C0011", "-", "-", "-", "-", "-", "C0013 EC0013 EC0013_b", "-"},
      {"iCR744", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c", "M_mobd_e M_mobd_c", "M_so4_e M_so4_c", "M_zn2_e M_zn2_c", "M_ca2_e M_ca2_c", "-", "M_cobalt2_c M_cobalt2_e", "M_cu2_e M_cu2_c", "M_fe2_c M_fe2_e", "M_fe3_e M_fe3_c", "M_k_c M_k_e", "M_mg2_c M_mg2_e", "M_nh4_e M_nh4_c", "M_ni2_e M_ni2_c"},
      {"iGB555_fixed", "M_FTHF", "-", "-", "M_S__Adenosyl__L__MET", "M_COA", "M_FAD", "M_METHF", "M_NAD", "M_NADP", "M_PYROX5P", "M_FMN M_Riboflavin", "M_ThPP", "M_UTP", "M_CTP", "M_GTP", "M_dATP", "M_dCTP", "M_dGTP", "M_dTTP", "-", "-", "M_SO4_ext M_SO4", "-", "-", "M_CL", "M_CO2", "-", "M_Fe2", "-", "-", "-", "M_NH3_ext M_NH3", "-"},
      {"iAI549", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_b M_mn2_c", "M_mobd_e M_mobd_b M_mobd_c", "M_so4_e M_so4_b M_so4_c", "M_zn2_e M_zn2_c M_zn2_b", "-", "M_cl_b M_cl_e", "M_cobalt2_b M_cobalt2_c M_cobalt2_e", "M_cu2_e M_cu2_b M_cu2_c", "M_fe2_b M_fe2_c M_fe2_e", "-", "M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e", "M_nh4_b M_nh4_e M_nh4_c", "M_ni2_e M_ni2_b M_ni2_c"},
      {"iMP240", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "M_so4_e M_so4_b M_so4_c", "-", "-", "-", "-", "-", "M_fe2_b M_fe2_c M_fe2_e", "-", "M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e", "M_nh4_b M_nh4_e M_nh4_c", "-"},
      {"PpuMBEL1071", "FTHF", "-", "-", "SAM", "COA", "FAD", "METTHF", "NAD", "NADP", "PL5P", "FMN", "THMPP", "UTP", "CTP", "GTP", "DATP", "DCTP", "DGTP", "DTTP", "-", "-", "SO4", "-", "-", "CL", "-", "-", "-", "-", "K", "-", "NH3", "-"},
      {"iKF1028", "C0193", "-", "-", "C0017", "C0010", "C0015", "C0122", "C0003", "C0006", "C0016", "C0212 C0049", "C0055", "C0060", "C0051", "C0038", "C0113", "C0351", "C0233", "C0352", "-", "-", "C0048 EC0048", "-", "C0061 EC0061", "-", "C0144 C0011 EC0144", "C0056 EC0056", "-", "-", "C0197 EC0197", "EC0248 C0248", "C0013 C0957 EC0957", "EC0236 C0236"},
      {"iCyh755", "M_cpd00201_c", "-", "-", "-", "M_cpd00010_c", "M_cpd00015_c", "-", "-", "-", "M_cpd00016_c", "M_cpd00050_c M_cpd00220_c", "M_cpd00056_c", "M_cpd00062_c", "M_cpd00052_c", "M_cpd00038_c", "M_cpd00115_c", "M_cpd00356_c", "M_cpd00241_c", "M_cpd00357_c", "M_cpd00030_p M_cpd00030_c M_cpd00030_e", "M_cpd11574_p M_cpd11574_e", "M_cpd00048_c M_cpd00048_p M_cpd00048_e", "M_cpd00034_p M_cpd00034_e M_cpd00034_c", "M_cpd00063_p M_cpd00063_e M_cpd00063_c", "-", "M_cpd00204_c M_cpd00204_e M_cpd00011_p M_cpd00204_p M_cpd00011_c M_cpd00011_e M_cpd00149_p M_cpd00149_c M_cpd00011_x M_cpd00149_e", "M_cpd00058_p M_cpd00058_c M_cpd00058_e", "M_cpd10515_p M_cpd10515_c M_cpd10515_e", "-", "M_cpd00205_c M_cpd00205_p M_cpd00205_e", "M_cpd00254_e M_cpd00254_c M_cpd00254_p", "M_cpd00013_p M_cpd00013_c M_cpd00013_e", "M_cpd00244_e M_cpd00244_c M_cpd00244_p"},
      {"iNV706", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c", "-", "M_so4_e M_so4_c", "-", "-", "-", "-", "-", "-", "-", "-", "-", "M_nh3_c M_nh4_e M_nh4_c M_nh3_e", "-"},
      {"GSMN-TB", "M_FTHF_c", "-", "-", "M_SAM_c", "M_COA_c", "M_FAD_c", "M_METHF_c", "M_NAD_c", "M_NADP_c", "not found", "M_FMN_c", "not found", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_DATP_c", "M_DCTP_c", "M_DGTP_c", "M_DTTP_c", "-", "-", "-", "-", "-", "M_CL_c", "M_CO2_c", "-", "M_FE2_c", "M_FE3_c", "-", "-", "M_NH3_c", "-"},
      {"iLca12A_640", "M_cpd00201_c", "-", "-", "-", "M_cpd00010_c", "M_cpd00015_c", "-", "-", "-", "M_cpd00016_c", "M_cpd00050_c M_cpd00220_e M_cpd00220_c", "M_cpd00056_c", "M_cpd00062_c", "M_cpd00052_c", "M_cpd00038_c", "M_cpd00115_c", "M_cpd00356_c", "M_cpd00241_c", "M_cpd00357_c", "M_cpd00030_c M_cpd00030_e", "-", "M_cpd00048_c M_cpd00048_e", "M_cpd00034_e M_cpd00034_c", "M_cpd00063_e M_cpd00063_c", "-", "M_cpd00011_c M_cpd00011_e M_cpd00149_c M_cpd00149_e", "M_cpd00058_c M_cpd00058_e", "M_cpd10515_c M_cpd10515_e", "-", "M_cpd00205_c M_cpd00205_e", "M_cpd00254_e M_cpd00254_c", "M_cpd00013_c M_cpd00013_e", "-"},
      {"iCyn731", "M_cpd00201_c", "-", "-", "-", "M_cpd00010_c", "M_cpd00015_c", "-", "-", "-", "M_cpd00016_c", "M_cpd00050_c M_cpd00220_c", "M_cpd00056_c", "M_cpd00062_c", "M_cpd00052_c", "M_cpd00038_c", "M_cpd00115_c", "M_cpd00356_c", "M_cpd00241_c", "M_cpd00357_c", "M_cpd00030_p M_cpd00030_c M_cpd00030_e", "M_cpd11574_p M_cpd11574_e", "M_cpd00048_c M_cpd00048_p M_cpd00048_e", "M_cpd00034_p M_cpd00034_e M_cpd00034_c", "M_cpd00063_p M_cpd00063_e M_cpd00063_c", "-", "M_cpd00204_c M_cpd00204_e M_cpd00011_p M_cpd00204_p M_cpd00011_c M_cpd00011_e M_cpd00149_p M_cpd00149_c M_cpd00011_x M_cpd00149_e", "M_cpd00058_p M_cpd00058_c M_cpd00058_e", "M_cpd10515_p M_cpd10515_c M_cpd10515_e", "-", "M_cpd00205_c M_cpd00205_p M_cpd00205_e", "M_cpd00254_e M_cpd00254_c M_cpd00254_p", "M_cpd00013_p M_cpd00013_c M_cpd00013_e", "M_cpd00244_e M_cpd00244_c M_cpd00244_p"},
      {"iCyp752", "M_cpd00201_c", "-", "-", "-", "M_cpd00010_c", "M_cpd00015_c", "-", "-", "-", "M_cpd00016_c", "M_cpd00050_c M_cpd00220_c", "M_cpd00056_c", "M_cpd00062_c", "M_cpd00052_c", "M_cpd00038_c", "M_cpd00115_c", "M_cpd00356_c", "M_cpd00241_c", "M_cpd00357_c", "M_cpd00030_p M_cpd00030_c M_cpd00030_e", "M_cpd11574_p M_cpd11574_e", "M_cpd00048_c M_cpd00048_p M_cpd00048_e", "M_cpd00034_p M_cpd00034_e M_cpd00034_c", "M_cpd00063_p M_cpd00063_e M_cpd00063_c", "-", "M_cpd00204_c M_cpd00204_e M_cpd00011_p M_cpd00204_p M_cpd00011_c M_cpd00011_e M_cpd00149_p M_cpd00149_c M_cpd00011_x M_cpd00149_e", "M_cpd00058_p M_cpd00058_c M_cpd00058_e", "M_cpd10515_p M_cpd10515_c M_cpd10515_e", "-", "M_cpd00205_c M_cpd00205_p M_cpd00205_e", "M_cpd00254_e M_cpd00254_c M_cpd00254_p", "M_cpd00013_p M_cpd00013_c M_cpd00013_e", "M_cpd00244_e M_cpd00244_c M_cpd00244_p"},
      {"iJP962", "C0193", "-", "-", "C0017", "C0010", "C0015", "C0122", "C0003", "C0006", "C0016", "C0212 C0049", "C0055", "C0060", "C0051", "C0038", "C0113", "C0351", "C0233", "C0352", "C0030", "C9527", "C0048", "C0034", "-", "-", "C0144 C0011 C0196", "C0056", "-", "-", "C0197", "C0248", "C0957 C0518", "C0236"},
      {"iJL480", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_ribflv_c M_fmn_c M_ribflv_e", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c", "-", "M_so4_e M_so4_c", "-", "-", "-", "M_co2_e M_co2_c", "-", "-", "-", "-", "-", "M_nh3_c M_nh4_e M_nh4_c M_nh3_e", "-"},
      {"iCce806", "m_10fthf_c", "-", "-", "m_amet_c m_amet_b m_amet_e", "m_coa_c", "m_fad_c", "m_mlthf_c", "m_nad_c", "m_nadp_c", "m_pydx5p_c", "m_fmn_c", "m_thmpp_c", "m_utp_c", "m_ctp_c", "m_gtp_c", "m_datp_c", "m_dctp_c", "m_dgtp_c", "m_dttp_c", "m_mn2_e m_mn2_b m_mn2_c", "m_mobd_c m_mobd_b m_mobd_e", "m_so4_c m_so4_b m_so4_e", "m_zn2_b m_zn2_c m_zn2_e", "m_ca2_c m_ca2_b m_ca2_e", "-", "m_cobalt2_e m_cobalt2_b m_cobalt2_c", "m_cu2_e m_cu2_b m_cu2_c", "m_fe2_e m_fe2_c m_fe2_b", "m_fe3_e m_fe3_c m_fe3_b", "m_k_b m_k_c m_k_e", "m_mg2_c m_mg2_b m_mg2_e", "m_nh4_b m_nh4_c m_nh4_e", "m_ni2_e m_ni2_b m_ni2_c"},
      {"iRP911", "10_formyltetrahydrofolate", "-", "-", "_S__adenosyl_L_methionine", "CoA", "FAD", "5_10_methylenetetrahydrofolate", "NAD_", "NADP_", "pyridoxal_5__phosphate", "FMN riboflavin", "thiamine_diphosphate", "UTP", "CTP", "GTP", "dATP", "dCTP", "dGTP", "dTTP", "Mn2_", "molybdate", "SO42_", "Zn2_", "Ca2_", "-", "CO2 Co2_", "Cu2_", "Fe2_", "-", "K_", "Mg2_", "NH3", "-"},
      {"iMA789_cobrafixed", "M_F10THF_c", "-", "-", "M_SAMET_c", "M_COA_c", "M_FAD_c", "M_METHTHF_c", "M_NAD_c", "M_NADP_c", "not found", "M_FMN_c M_RIBOFLAVIN_c", "M_THPP_c M_TDP_c", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_DATP_c", "M_DCTP_c", "M_DGTP_c", "M_DTTP_c", "-", "-", "-", "-", "-", "-", "M_COBALT_c M_CO2_c M_CO2_e", "-", "M_FE_c M_FE_e", "-", "M_K_c M_K_e", "-", "M_NH3_c M_NH3_e", "-"},
      {"iCyj826", "M_cpd00201_c", "-", "-", "-", "M_cpd00010_c", "M_cpd00015_c", "-", "-", "-", "M_cpd00016_c", "M_cpd00050_c M_cpd00220_c", "M_cpd00056_c", "M_cpd00062_c", "M_cpd00052_c", "M_cpd00038_c", "M_cpd00115_c", "M_cpd00356_c", "M_cpd00241_c", "M_cpd00357_c", "M_cpd00030_p M_cpd00030_c M_cpd00030_e", "M_cpd11574_p M_cpd11574_e", "M_cpd00048_c M_cpd00048_p M_cpd00048_e", "M_cpd00034_p M_cpd00034_e", "M_cpd00063_p M_cpd00063_e M_cpd00063_c", "-", "M_cpd00204_c M_cpd00204_e M_cpd00011_p M_cpd00011_c M_cpd00204_p M_cpd00011_e M_cpd00149_p M_cpd00149_c M_cpd00011_x M_cpd00149_e", "M_cpd00058_p M_cpd00058_c M_cpd00058_e", "M_cpd10515_p M_cpd10515_c M_cpd10515_e", "-", "M_cpd00205_c M_cpd00205_p M_cpd00205_e", "M_cpd00254_e M_cpd00254_c M_cpd00254_p", "M_cpd00013_p M_cpd00013_c M_cpd00013_e", "M_cpd00244_e M_cpd00244_c M_cpd00244_p"},
      {"iAbaylyiv4", "M_10_DASH_FORMYL_DASH_THF_Cytosol", "-", "-", "M_S_DASH_ADENOSYLMETHIONINE_Cytosol", "M_CO_DASH_A_Cytosol", "M_FAD_Cytosol", "M_METHYLENE_DASH_THF_Cytosol", "M_NAD_Cytosol", "M_NADP_Cytosol", "M_PYRIDOXAL_PHOSPHATE_Cytosol", "M_FMN_Cytosol M_RIBOFLAVIN_Cytosol", "M_THIAMINE_DASH_PYROPHOSPHATE_Cytosol", "M_UTP_Cytosol", "M_CTP_Cytosol", "M_GTP_Cytosol", "M_DATP_Cytosol", "M_DCTP_Cytosol", "M_DGTP_Cytosol", "M_TTP_Cytosol", "-", "-", "M_SULFATE_Extraorganism M_SULFATE_Cytosol", "-", "-", "-", "M_CARBON_DASH_DIOXIDE_Extraorganism M_CARBON_DASH_DIOXIDE_Cytosol", "-", "M_FE_PLUS_2_Extraorganism M_FE_PLUS_2_Cytosol", "-", "-", "-", "M_AMMONIUM_Extraorganism M_AMMONIUM_Cytosol", "-"},
      {"iAK692_auto", "M_10-formyl-tetrahydrofolate_c M_N10-formyl-THF_c", "-", "-", "M_S-adenosyl-L-methionine_c", "M_coenzyme A_c", "M_FAD_c", "M_5,10-methylene-THF_c", "M_NAD+_c", "M_NADP+_c", "not found", "M_FMN_c", "M_thiamine-diphosphate_c", "M_UTP_c M_UTP_b M_UTP_e", "M_CTP_e M_CTP_c M_CTP_b", "M_GTP_b M_GTP_c M_GTP_e", "M_dATP_c", "M_dCTP_c", "M_dGTP_c", "M_dTTP_c", "M_Mn2+_c M_Mn2_e M_Mn2_b", "M_Mo_c", "M_SO4_c M_SO4_b M_SO4_e", "-", "M_Ca2+_c M_Ca2_e M_Ca2_b", "-", "M_Co2+_c", "M_Cu2_b M_Cu2_e", "-", "-", "M_K+_c M_K_b M_K_e", "M_Mg2+_c", "M_NH4+_c", "M_Ni2+_c M_Ni2_e M_Ni2_b"},
      {"iCS291", "M_FTHF_c", "-", "-", "M_SAM_c", "M_COA_c", "M_FAD_c", "M_METTHF_c", "M_NAD_c", "M_NADP_c", "not found", "M_FMN_c", "M_TPP_c", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_DATP_c", "M_DCTP_c", "M_DGTP_c", "M_DTTP_c", "-", "-", "-", "-", "-", "-", "M_CO2_c", "-", "-", "-", "-", "-", "M_NH3_c M_NH3_e", "-"},
      {"iCG238", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "M_so4_e M_so4_b M_so4_c", "-", "-", "-", "-", "-", "M_fe2_b M_fe2_c M_fe2_e", "-", "M_k_c M_k_b M_k_e", "-", "M_nh4_b M_nh4_e M_nh4_c", "-"},
      {"iSyn669", "M_10_formyl_tetrahydrofolate_c", "-", "-", "M_S_adenosyl_L_methionine_c", "M_coenzyme_A_c", "M_FAD_c", "M_5_10_methylene_THF_c", "M_NAD_c", "M_NADP_c", "M_pyridoxal_5_phosphate_c", "M_FMN_c M_riboflavin_c", "M_thiamine_diphosphate_c", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_dATP_c", "M_dCTP_c", "M_dGTP_c", "M_dTTP_c", "M_Mn2_c", "-", "-", "M_Zn2_c", "M_Ca2_c", "-", "M_Co2_c M_CO2_c M_CO_c", "-", "M_Fe2_c", "-", "M_K_c", "M_Mg2_c", "M_NH4_c", "-"},
      {"iJH728", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_ribflv_c M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_b M_mn2_c", "M_mobd_e M_mobd_b M_mobd_c", "M_so4_e M_so4_b M_so4_c", "M_zn2_e M_zn2_c M_zn2_b", "M_ca2_e M_ca2_b M_ca2_c", "-", "M_cobalt2_b M_cobalt2_c M_co2_b M_cobalt2_e M_co2_e M_co_e M_co2_c M_co2_cbx M_co_b", "M_cu2_e M_cu2_b M_cu2_c", "M_fe2_b M_fe2_c M_fe2_e", "M_fe3_e M_fe3_b M_fe3_c", "M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e", "M_nh4_b M_nh4_e M_nh4_c", "-"},
      {"iLca334_548", "M_cpd00201_c", "-", "-", "-", "M_cpd00010_c", "M_cpd00015_c", "-", "-", "-", "M_cpd00016_c", "M_cpd00050_c M_cpd00220_e M_cpd00220_c", "M_cpd00056_c", "M_cpd00062_c", "M_cpd00052_c", "M_cpd00038_c", "M_cpd00115_c", "M_cpd00356_c", "M_cpd00241_c", "M_cpd00357_c", "M_cpd00030_c M_cpd00030_e", "-", "M_cpd00048_c M_cpd00048_e", "M_cpd00034_e M_cpd00034_c", "M_cpd00063_e M_cpd00063_c", "-", "M_cpd00011_c M_cpd00011_e M_cpd00149_c M_cpd00149_e", "M_cpd00058_c M_cpd00058_e", "M_cpd10515_c M_cpd10515_e", "-", "M_cpd00205_c M_cpd00205_e", "M_cpd00254_e M_cpd00254_c", "M_cpd00013_c", "-"},
      {"iCG230", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "-", "-", "-", "-", "-", "-", "M_fe2_b M_fe2_c M_fe2_e", "-", "M_k_c M_k_b M_k_e", "-", "M_nh4_b M_nh4_e M_nh4_c", "-"},
      {"iWX1009", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "-", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_b M_mn2_c", "M_mobd_e M_mobd_b M_mobd_c", "M_so4_e M_so4_b M_so4_c", "M_zn2_e M_zn2_c M_zn2_b", "M_ca2_e M_ca2_b M_ca2_c", "M_cl_b M_cl_c M_cl_e", "M_cobalt2_b M_cobalt2_c M_cobalt2_e", "M_cu2_e M_cu2_b M_cu2_c", "M_fe2_b M_fe2_c M_fe2_e", "M_fe3_e M_fe3_b M_fe3_c", "M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e", "M_nh4_b M_nh4_e M_nh4_c", "M_ni2_e M_ni2_b M_ni2_c"},
      {"iIB700", "M_FTHF_c", "-", "-", "M_SAM_c", "M_COA_c", "M_FAD_c", "M_METTHF_c", "M_NAD_c", "M_NADP_c", "not found", "M_FMN_c", "not found", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_DATP_c", "M_DCTP_c", "M_DGTP_c", "M_DTTP_c", "-", "-", "-", "-", "-", "M_CL_c", "M_CO2_c", "-", "-", "-", "-", "-", "M_NH3_c", "-"},
      {"iFap484", "M_m447", "-", "-", "-", "M_m180", "M_m190", "-", "-", "-", "-", "M_m426", "-", "M_m476", "M_m477", "M_m195 M_m537", "M_m503", "M_m478", "M_m500", "M_m550", "-", "-", "M_m419 M_m420 M_m421", "-", "-", "-", "-", "-", "-", "-", "-", "-", "M_m11 M_m10 M_m12", "-"},
      {"iCA1273", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_e M_gtp_b M_gtp_c M_gtp_p", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_b M_mn2_c M_mn2_p", "M_mobd_p M_mobd_e M_mobd_b M_mobd_c", "M_so4_p M_so4_e M_so4_b M_so4_c", "M_zn2_e M_zn2_c M_zn2_b M_zn2_p", "M_ca2_e M_ca2_b M_ca2_c M_ca2_p", "M_cl_b M_cl_c M_cl_p M_cl_e", "M_cobalt2_b M_cobalt2_c M_cobalt2_e M_cobalt2_p", "M_cu2_e M_cu2_b M_cu2_c M_cu2_p", "M_fe2_p M_fe2_b M_fe2_c M_fe2_e", "M_fe3_e M_fe3_p M_fe3_b M_fe3_c", "M_k_p M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e M_mg2_p", "M_nh4_b M_nh4_p M_nh4_e M_nh4_c", "M_ni2_e M_ni2_b M_ni2_c M_ni2_p"},
      {"iVM679", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "M_so4_e", "-", "-", "-", "-", "-", "M_fe2_c M_fe2_e", "M_fe3_e", "-", "-", "M_nh3_c M_nh4_e M_nh4_c", "M_ni2_e"},
      {"iRR1083", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "M_so4_e M_so4_b M_so4_c", "-", "-", "M_cl_b M_cl_c M_cl_e", "M_cobalt2_b M_cobalt2_c M_cobalt2_e", "M_cu2_e M_cu2_b M_cu2_c", "M_fe2_b M_fe2_c M_fe2_e", "-", "M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e", "M_nh4_b M_nh4_e M_nh4_c", "M_ni2_e M_ni2_b M_ni2_c"},
      {"iMLTC806cdf", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_510mlethf_c", "M_nad_c", "M_nadp_c", "M_pyal5p_c", "M_ribflv_c M_ribflv_b M_fmn_c M_ribflv_e", "M_thmdp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "-", "M_zn2_e M_zn2_c M_zn2_b", "M_ca2_e M_ca2_b M_ca2_c", "-", "M_cobalt_b M_cobalt_c M_cobalt_e M_co2_b M_co2_e M_co2_c", "-", "M_fe2_b M_fe2_c M_fe2_e", "-", "M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e", "M_nh3_b M_nh3_c M_nh3_e", "-"},
      {"iAO358", "M_20", "-", "-", "M_394", "M_155", "M_237", "M_83", "M_341", "M_343", "not found", "M_390 M_239", "not found", "M_433", "M_156", "M_254", "M_167", "M_170", "M_201", "M_229", "-", "-", "M_405", "-", "-", "-", "M_154", "-", "-", "-", "-", "-", "M_346", "-"},
      {"iBT721_v2", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_b M_mn2_c", "-", "M_so4_e M_so4_b M_so4_c", "-", "-", "-", "-", "-", "-", "-", "-", "-", "M_nh3_c M_nh4_b M_nh4_e M_nh4_c M_nh3_e", "-"},
      {"iTT548", "M_10F_THF_c", "-", "-", "M_SAM_c", "M_CoA_c", "M_FAD_c", "M_5_10_MNTHF_c", "M_NAD_c", "M_NADP_c", "not found", "M_FMN_c", "M_TPP_c", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_dATP_c", "M_dCTP_c", "M_dGTP_c", "M_dTTP_c", "-", "-", "M_SO4_c M_SO4_e", "-", "-", "-", "M_CO_LPAREN_II_RPAREN__c", "-", "M_Fe2_c M_Fe2_e", "-", "-", "M_Mg_c M_Mg_e", "M_NH4_c M_NH4_e", "-"},
      {"iRM588", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "not found", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c", "M_mobd_e M_mobd_c", "M_so4_e M_so4_c", "M_zn2_e M_zn2_c", "M_ca2_e M_ca2_c", "M_cl_c M_cl_e", "M_cobalt2_c M_cobalt2_e", "M_cu2_e M_cu2_c", "M_fe2_c M_fe2_e", "M_fe3_e", "M_k_c M_k_e", "M_mg2_c M_mg2_e", "M_nh3_c M_nh4_e M_nh4_c", "M_ni2_e M_ni2_c"},
      {"iBsu1103", "cpd00201_c", "-", "-", "-", "cpd00010_c", "cpd00015_c", "-", "-", "-", "-", "cpd00050_c", "cpd00056_c", "cpd00062_c", "cpd00052_c", "cpd00038_c", "cpd00115_c", "cpd00356_c", "cpd00241_c", "cpd00357_c", "cpd00030_e cpd00030_b cpd00030_c", "cpd11574_e cpd11574_b cpd11574_c", "cpd00048_c cpd00048_b cpd00048_e", "cpd00034_b cpd00034_c cpd00034_e", "cpd00063_e cpd00063_c cpd00063_b", "-", "cpd00149_c cpd00149_b cpd00149_e", "cpd00058_b cpd00058_c cpd00058_e", "cpd10515_e cpd10515_b cpd10515_c", "-", "cpd00205_e cpd00205_c cpd00205_b", "cpd00254_c cpd00254_b cpd00254_e", "cpd00013_b cpd00013_c cpd00013_e", "cpd00244_c cpd00244_b cpd00244_e"},
      {"iMH551", "M_10__Formyltetrahydrofolate", "-", "-", "M_S__Adenosyl__L__methionine", "M_CoA", "M_FAD", "M_5_COMMA_10__Methylenetetrahydrofolate", "M_NAD", "M_NADP", "not found", "M_FMN M_Riboflavin", "M_Thiamin_diphosphate", "M_UTP", "M_CTP", "M_GTP", "M_dATP", "M_dCTP", "M_dGTP", "M_dTTP", "M_Mn2 M_Mn2_e", "M_Molybdate", "-", "M_Zn2_e M_Zn2", "-", "-", "M_Cobalt M_CO2", "-", "-", "-", "M_K_e M_K", "M_Mg2_e M_Mg2", "M_NH3_e M_NH3", "M_Ni2_e M_Ni2"},
      {"iWZ663", "M_FTHF_c", "-", "-", "M_SAM_c", "M_COA_c M_COA_e", "M_FAD_c", "M_METTHF_c", "M_NAD_c", "M_NADP_c", "M_PDXL5PI_c", "M_FMN_c", "M_THDP_c", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_DATP_c", "M_DCTP_c", "M_DGTP_c", "M_DTTP_c", "-", "-", "M_SLF_c", "-", "-", "M_CL_c", "-", "-", "M_FE2_e M_FE2_c", "-", "-", "-", "M_NH3_c M_NH3_e", "-"},
      {"iCM925", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmmp_c M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "M_so4_e M_so4_c", "-", "-", "-", "M_cobalt2_c", "-", "M_fe2_c", "-", "-", "-", "M_nh4_e M_nh4_c", "-"},
      {"iMP429_fixed", "M_10fthf_LSQBKTc_RSQBKT", "-", "-", "M_amet_LSQBKTc_RSQBKT", "M_coa_LSQBKTc_RSQBKT", "M_fad_LSQBKTc_RSQBKT", "M_mlthf_LSQBKTc_RSQBKT", "M_nad_LSQBKTc_RSQBKT", "M_nadp_LSQBKTc_RSQBKT", "M_pydx5p_LSQBKTc_RSQBKT", "M_ribflv_LSQBKTc_RSQBKT M_fmn_LSQBKTc_RSQBKT M_ribflv_LSQBKTe_RSQBKT", "M_thmpp_LSQBKTc_RSQBKT", "M_utp_LSQBKTc_RSQBKT", "M_ctp_LSQBKTc_RSQBKT", "M_gtp_LSQBKTc_RSQBKT", "M_datp_LSQBKTc_RSQBKT", "M_dctp_LSQBKTc_RSQBKT", "M_dgtp_LSQBKTc_RSQBKT", "M_dttp_LSQBKTc_RSQBKT", "M_mn2_LSQBKTc_RSQBKT M_mn2_LSQBKTe_RSQBKT", "-", "-", "M_zn2_LSQBKTe_RSQBKT M_zn2_LSQBKTc_RSQBKT", "-", "-", "M_co2_LSQBKTc_RSQBKT M_co2_LSQBKTe_RSQBKT", "-", "-", "M_fe3_LSQBKTe_RSQBKT M_fe3_LSQBKTc_RSQBKT", "M_k_LSQBKTc_RSQBKT M_k_LSQBKTe_RSQBKT", "-", "M_nh4_LSQBKTe_RSQBKT M_nh4_LSQBKTc_RSQBKT", "-"},
      {"iVW583", "M_379_c", "-", "-", "M_871_c", "M_231_c", "M_356_c", "M_663_c", "M_692_c", "M_696_c", "not found", "M_856_c M_367_c", "M_939_c", "M_985_c", "M_241_c", "M_454_c", "M_263_c", "M_268_c", "M_280_c", "M_338_c", "-", "M_679_c", "M_889_c", "-", "-", "-", "M_228_c", "-", "-", "-", "M_543_c", "-", "M_712_c", "-"},
      {"iMG746", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_ribflv_c M_fmn_c M_ribflv_e", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_c", "M_mobd_e M_mobd_c", "M_so4_e M_so4_c", "M_zn2_e M_zn2_c", "M_ca2_e M_ca2_c", "M_cl_c M_cl_e", "M_cobalt2_c M_cobalt2_e M_co2_e M_co2_c", "M_cu2_e M_cu2_c", "M_fe2_c M_fe2_e", "M_fe3_e M_fe3_c", "M_k_c M_k_e", "M_mg2_c M_mg2_e", "M_nh4_e M_nh4_c M_n2_c M_n2_e", "M_ni2_e M_ni2_c"},
      {"iPB890", "M_m160_c", "-", "-", "M_m306_c", "M_m752_c M_m856_e", "M_m98_c M_m839_e", "M_m611_c", "M_m815_c", "M_m620_c", "M_m458_c", "M_m124_c M_m331_c", "M_m460_c", "M_m302_c M_m846_e", "M_m845_e M_m248_c", "M_m820_c M_m858_e", "M_m843_e M_m179_c", "M_m842_e M_m168_c", "M_m851_e M_m462_c", "M_m334_c M_m847_e", "M_m835_e", "M_m531_c M_m52_e", "M_m382_c M_m15_e", "M_m443_c M_m62_e", "-", "-", "M_m837_e M_m221_c M_m1045_c M_m472_c M_m41_e", "M_m768_c M_m30_e", "-", "-", "M_m18_e M_m288_c", "M_m834_e", "M_m510_c M_m836_e M_m44_e M_m625_c", "M_m48_e M_m120_c"},
      {"iJP815", "C0193", "-", "-", "C0017", "C0010", "C0015", "C0122", "C0003", "C0006", "C0016", "C0212 C0049", "C0055", "C0060", "C0051", "C0038", "C0113", "C0351", "C0233", "C0352", "-", "-", "C0048", "-", "-", "-", "C0144 C0011", "-", "-", "-", "C0197", "C0248", "C0013 C0957", "-"},
      {"iRS605_fixed", "M_10fthf_LPAREN_c_RPAREN_", "-", "-", "M_amet_LPAREN_c_RPAREN_", "M_coa_LPAREN_c_RPAREN_", "M_fad_LPAREN_c_RPAREN_", "M_mlthf_LPAREN_c_RPAREN_", "M_nad_LPAREN_c_RPAREN_", "M_nadp_LPAREN_c_RPAREN_", "not found", "M_fmn_LPAREN_c_RPAREN_ M_ribflv_LPAREN_e_RPAREN_ M_fmn_LPAREN_e_RPAREN_ M_ribflv_LPAREN_c_RPAREN_", "not found", "M_utp_LPAREN_c_RPAREN_", "M_ctp_LPAREN_c_RPAREN_", "M_gtp_LPAREN_c_RPAREN_", "M_datp_LPAREN_c_RPAREN_", "M_dctp_LPAREN_c_RPAREN_", "M_dgtp_LPAREN_c_RPAREN_", "M_dttp_LPAREN_c_RPAREN_", "-", "-", "M_so4_LPAREN_c_RPAREN_ M_so4_LPAREN_e_RPAREN_", "M_zn2_LPAREN_e_RPAREN_ M_zn2_LPAREN_c_RPAREN_", "-", "M_cl_LPAREN_c_RPAREN_ M_cl_LPAREN_e_RPAREN_", "M_co2_LPAREN_c_RPAREN_ M_co2_LPAREN_e_RPAREN_", "-", "M_fe2_LPAREN_e_RPAREN_ M_fe2_LPAREN_c_RPAREN_", "-", "M_k_LPAREN_c_RPAREN_ M_k_LPAREN_e_RPAREN_", "-", "M_nh3_LPAREN_c_RPAREN_ M_nh4_LPAREN_c_RPAREN_ M_nh4_LPAREN_e_RPAREN_", "M_ni2_LPAREN_e_RPAREN_ M_ni2_LPAREN_c_RPAREN_"},
      {"kb_fbamdl_1903", "cpd00201_c0", "-", "-", "-", "cpd00010_c0", "cpd00015_c0", "-", "-", "-", "cpd00016_c0", "cpd00050_c0", "cpd00056_c0", "cpd00062_c0", "cpd00052_c0", "cpd00038_c0", "cpd00115_c0", "cpd00356_c0", "cpd00241_c0", "cpd00357_c0", "cpd00030_e0 cpd00030_b cpd00030_c0", "-", "cpd00048_e0 cpd00048_b cpd00048_c0", "cpd00034_e0 cpd00034_b cpd00034_c0", "cpd00063_b cpd00063_c0 cpd00063_e0", "-", "cpd00149_b cpd00149_c0 cpd00149_e0", "cpd00058_e0 cpd00058_b cpd00058_c0", "cpd10515_c0 cpd10515_e0 cpd10515_b", "-", "cpd00205_b cpd00205_e0 cpd00205_c0", "cpd00254_c0 cpd00254_b cpd00254_e0", "cpd00013_b cpd00013_c0 cpd00013_e0", "-"},
      {"iSR432", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "not found", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_e M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "M_so4_e M_so4_c", "-", "M_ca2_e M_ca2_c", "-", "-", "M_cu2_e M_cu2_c", "-", "M_fe3_e M_fe3_c", "M_k_c M_k_e", "M_mg2_c M_mg2_e", "M_nh4_e M_nh4_c", "-"},
      {"iTZ479", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c M_pydx5p_b", "M_ribflv_c M_fmn_c", "M_thmpp_b M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "-", "-", "-", "M_zn2_e M_zn2_c M_zn2_b", "-", "-", "M_co2_e M_co2_c", "-", "M_fe2_b M_fe2_e", "M_fe3_e M_fe3_b M_fe3_c", "-", "M_mg2_c M_mg2_b M_mg2_e", "M_nh3_c M_nh4_b M_nh4_e M_nh4_c", "-"},
      {"iMB745", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_c", "M_datp_c", "M_dctp_c", "M_dgtp_c", "M_dttp_c", "M_mn2_e M_mn2_b M_mn2_c", "M_mobd_e M_mobd_b M_mobd_c", "M_so4_e M_so4_b M_so4_c", "M_zn2_e M_zn2_c M_zn2_b", "M_ca2_e M_ca2_b M_ca2_c", "M_cl_b M_cl_c M_cl_e", "M_cobalt2_b M_cobalt2_c M_cobalt2_e", "M_cu2_e M_cu2_b M_cu2_c", "M_fe2_b M_fe2_c M_fe2_e", "M_fe3_e M_fe3_b M_fe3_c", "M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e", "M_nh4_b M_nh4_e M_nh4_c", "M_ni2_e M_ni2_b M_ni2_c"},
      {"iCS400", "not sure", "-", "-", "M_SAM_c", "M_COA_c", "M_FAD_c", "not sure", "M_NAD_e M_NAD_c", "M_NADP_c", "not sure", "M_FMN_c", "M_TPP_c", "M_UTP_c", "M_CTP_c", "M_GTP_c", "M_DATP_c", "M_DCTP_c", "M_DGTP_c", "M_DTTP_c", "-", "-", "-", "-", "-", "-", "M_CO2_c", "-", "-", "-", "M_K_c M_K_e", "-", "M_NH3_c M_NH3_e", "-"},
      {"iPS189", "M_10fthf_c", "-", "-", "M_amet_c", "M_coa_c M_coa_b M_coa_e", "M_fad_c", "M_mlthf_c", "M_nad_c", "M_nadp_c", "M_pydx5p_c", "M_fmn_c", "M_thmpp_c", "M_utp_c", "M_ctp_c", "M_gtp_e M_gtp_b M_gtp_c", "M_datp_b M_datp_c M_datp_e", "M_dctp_c", "M_dgtp_b M_dgtp_c M_dgtp_e", "M_dttp_c", "M_mn2_e M_mn2_b M_mn2_c", "M_mobd_e M_mobd_b M_mobd_c", "M_so4_e M_so4_b M_so4_c", "M_zn2_e M_zn2_c M_zn2_b", "M_ca2_e M_ca2_b M_ca2_c", "M_cl_b M_cl_c M_cl_e", "M_cobalt2_b M_cobalt2_c M_cobalt2_e", "M_cu2_e M_cu2_b M_cu2_c", "M_fe2_b M_fe2_c M_fe2_e", "M_fe3_e M_fe3_b M_fe3_c", "M_k_c M_k_b M_k_e", "M_mg2_c M_mg2_b M_mg2_e", "M_nh4_b M_nh4_e M_nh4_c", "M_ni2_e M_ni2_b M_ni2_c"},
      {"iKK446", "M_FTHF", "-", "-", "M_SAM", "M_COA", "M_FAD", "M_METTHF", "M_NAD", "M_NADP", "not found", "M_FMN", "not found", "M_UTP", "M_CTP", "M_GTP", "M_DATP", "M_DCTP", "M_DGTP", "M_DTTP", "-", "-", "-", "-", "-", "M_CL", "-", "-", "-", "-", "-", "-", "M_NH4 M_NH3", "-"}
  };
  
  public static final String[] reactionMerge = {
      "Abymbel891", "R761", "CAV;DNA;RNA", "",
      "iJW145", "IR09955", "C9484;C9498", "IR09861;IR09884",
      "PpuMBEL1071", "R_Biomass", "CAV;DNA;RNA", "",
      "VvuMBEL943", "R806", "CAV;DNA;RNA", "",
      "iOG654", "RM00001", "CM00008;CM00010;CM00006;CM00007", "",
      "iMH551", "R_R0221", "M_1gPool;M_1gDNA;M_1gRNA", "",
      "iVW583", "R_1309", "M_314_c;M_861_c", "",
      "iAO358", "R_BIOMass_dup3", "M_391;M_215", "R_RNAass;R_DNAass",
      "iJC568", "R_BIOMASS", "M_BioPool;M_Bmineral;M_deoxyribonucleic_acids;M_ribonucleic_acids", "",
      "iJB785", "R_BOF", "M_bm_cofactors_c;M_bm_rna_c;M_bm_dna_c", "",
      "iTY425_fixed", "R_R517", "M_CAV_c;M_DNA_c;M_RNA_c", "",
      "iCAC490", "R_R07230_B", "M_cid0001620_c;M_cid0000036_c;M_cid0001690_c", ";;R_R07226_B",
      "iSH335", "R_R374", "M_COFACTOR;M_DNA;M_RNA", "",
      "iAbaylyiv4", "R_GROWTH_DASH_RXN", "M_COFACTOR_W_Cytosol;M_RNA_W_Cytosol;M_DNA_W_Cytosol", "",
      "iCac802", "R_R1461", "M_cpde0003_c;M_cpd11461_c;M_cpd11613_c", ";;R_R1428",
//      "iKK446", "R_Biomass", "M_DNA;M_RNA", "R_R366;R_R367", //unable to merge needs uptake !
      "iGB555_fixed", "R_LUMP70", "M_DNA;M_RNA", "",
      "iCce806", "r_CYANOBM", "m_dna_cn_c;m_rna_cn_c", "",
      "iCR744", "R_BIO_Rfer3", "M_dna_Eco_c;M_rna_Eco_c", "",
      "iJL480", "R_biomass", "M_DNA_LLA_c;M_RNA_LLA_c", "",
      "iNF518", "R_biomass_LLA", "M_DNA_LLA_c;M_RNA_LLA_c", "R_DNAS_LLA;R_RNAS_LLA",
      "iNV706", "R_BIOMASS", "M_DNA_LPL_c;M_RNA_LPL_c", "",
      "iBT721_v2", "R_biomass_LPL60", "M_DNA_LPL_c;M_RNA_LPL_c", "R_DNAS_LPL;R_RNAS_LPL",
      "iSO783", "R_SO_BiomassMacro_DM_noATP2", "M_dna_Son_c;M_rna_Son_c", "",
      "iMP429_fixed", "R_biomass_STR", "M_DNA_STU_LSQBKTc_RSQBKT;M_RNA_LLA_LSQBKTc_RSQBKT", "",
      "iJH728", "R_NEWBIOMASSCLIMITED", "M_dna_syn_c;M_rna_syn_c", "R_DNASYN_SYN;R_RNASYN_SYN",
      "Nmb_iTM560", "R_Nm_biomass", "M_M_dna_c_c;M_M_rna_c_c", "R_DNAS;R_RNAS",
      "iAK692_auto", "R_SP0699", "M_RNA_c;M_DNA_c", "",
      "iWX1009", "Biomass00006", "M_RNA_W_c;M_dna_c", "",
      "iZM363", "R_Biomass", "M_sm;M_dna;M_rna", "",
      "iZmobMBEL601", "R_Rxn_Biomass", "M_SMALL_MOLECULES_Cell;M_DNA_Cell;M_RNA_Cell", "",
      "iEM439", "R_biomass", "M_smallmolecules_c;M_DNA_c;M_RNA_c", "",
      "iMA789_cobrafixed", "R_biomass", "M_SMALLMOLECULES_c;M_DNA_c;M_RNA_c", "",
      "GSMN-TB", "BIOMASSe", "M_SMALLMOLECULES_c;M_RNA_c;M_DNA_c", "",
      "iIB700", "R793", "M_SMALLMOLECULES_c;M_RNA_c;M_DNA_c", "",
      "iMLTC806cdf", "R_Biomass", "M_SPs_c;M_DNA_c;M_RNA_c", "",
      "iJL432", "R_rBIOMASS", "M_TRACE_Cell;M_DNA_Cell;M_RNA_Cell", "",
      "iCM925", "R_biomass", "M_trace_met_c;M_dna_met_c;M_rna_met_c", "",
      "iMB745", "R_overall", "M_trace_met_c;M_dna_met_c;M_rna_met_c", "",
      "iMM518", "R565", "Soluble_pools;rna;dna", ";;R566",
      
      "iCC908", "R_B0000", "M_Cofactor_biomass[c];M_DNA_biomass[c];M_RNA_biomass[c]", ";;",
      "iHK760", "R90", "B2_cyt;B3_cyt;B6_cyt;B7_cyt", ";;;",
  };
 
  public static final String[] taxonMapping = {
      "iJO1366_bigg2", "511145",
      "iIT341_bigg2", "85962",
      "iJN746_bigg2", "160488",
      "iSB619_bigg2", "158879",
      "STM_v1_0", "99287",
      "iAF1260", "511145",
      "iAF692", "269797",
      "iAF987", "269799",
      "iBWG_1329", "595496",
      "iECDH10B_1368", "316385",
      "iECSF_1327", "431946",
      "iHN637", "748727",
      "iJN678", "1111708",
      "iJR904", "511145",
      "iLJ478", "243274",
      "iPC815", "214092",
      "iYL1228", "573",
      "iYO844", "224308",
      
      "iND750", "559292",
      "iMM904", "559292",
      "iAZ900", "559292",
      "iIN800", "559292",
      "iTO977", "559292",
      "ymn1_0", "559292",
      "ymn6_06_cobra", "559292",
      "ymn_7_6_cobra", "559292",
      "iFF708", "559292",
      "iLL672", "559292",
      
      "iJO1366", "511145",
       
      "VvuMBEL943", "216895",
      "sMtb", "83332",
      "PpuMBEL1071", "160488",
      "Nmb_iTM560", "122586",
      "kb_fbamdl_1903", "67826",
      "iZmobMBEL601", "264203",
      "iZM363", "264203",
      "iYS432", "196627",
      "iWZ663", "759362",
      "iWX1009", "1126218",
      "iVW583", "1089545",
      "iVS941_fixed", "188937",
      "iVM679", "242619",
      "iTY425_fixed", "221988",
      "iTT548", "262724",
      "iSyn731", "1111708",
      "iSyn669", "1111708",
      "iSyf715", "1140",
      "iSS352", "509169",
      "iSR432", "203119",
      "iSO783", "211586",
      "iSM197", "107806",
      "iSH335", "221988",
      "iSB1139", "216595",
      "iRsp1140", "272943",
      "iRsp1095", "272943",
      "iRS605_fixed", "376619",
      "iRR1083", "99287",
      "iRP911", "272630",
      "iRM588", "243231",
      "iRBaquifex", "224324",
      "iPS189", "243273",
      "iPB890", "379731",
      "iOG654", "348780",
      "iNV706", "226185",
      "iNJ661v", "83332",
      "iNJ661m", "83332",
      "iNF518", "416870",
      "iMZ1055", "1006007",
      "iMP429_fixed", "264199",
      "iMP240", "331104",
      "iMO1056", "208964",
      "iMM518", "267377",
      "iMLTC806cdf", "272563",
      "iMK1208", "100226",
      "iMH551", "158879",
      "iMG746", "269797",
      "iMF721", "326442",
      "iMB745", "188937",
      "iMA945", "99287",
      "iMA789_cobrafixed", "100226",
      "iLca334_548", "321967",
      "iLca12A_640", "1051650",
      "iKK446", "196627",
      "iKF1028", "216591",
      "iJW145", "272634",
      "iJS747", "269799",
      "iJP962", "160488",
      "iJP815", "160488",
      "iJM658", "1075088",
      "iJL480", "471876",
      "iJL432", "272562",
      "iJH728", "32049",
      "iJC568", "59919",
      "iJB785", "1140",
      "iIB700", "100226",
      "iGT196", "107806",
      "iGB555_fixed", "122586",
      "iFap484", "411483",
      "iEM439", "555217",
      "iEB458", "63612",
      "iDsh827", "398580",
      "iCyt773", "43989",
      "iCyp752", "41431",
      "iCyn731", "395961",
      "iCyj826", "497965",
      "iCyh755", "395962",
      "iCyc792", "65393",
      "iCS400", "71421",
      "iCS291", "85962",
      "iCR744", "338969",
      "iCM925", "290402",
      "iCG238", "331104",
      "iCG230", "600809",
      "iCce806", "43989",
      "iCac802", "272562",
      "iCAC490", "272562",
      "iCA1273", "566546",
      "iBT721_v2", "220668",
      "iBsu1103", "224308",
      "iBif452", "411481",
      "iAO358", "272623",
      "iAK692_auto", "459495",
      "iAI558", "264732",
      "iAI549", "255470",
      "iAbaylyiv4", "62977",
      "GSMN-TB", "83332",
      "Abymbel891", "509173",
      
      "iHK760", "1111708",
      "iAN818m", "229193",
      "iCC908", "168695",
      "iJSPcarbinolicus", "338963",
      "iJSPpropionicus", "338966",
      "iJL846", "999378",
      "iAM388", "192222",
      "iYLW1028", "134676",
      "iKY620", "696747",
      "iMR539", "267377",
      
      "iJN746", "160488",
  };
  
  public static Map<String, Map<Long, Long>> getMergeMap(FileBiodbService service) {
    Map<String, Map<Long, Long>> mergeMap = new HashMap<> ();
    
    for (int i = 0; i < reactionMerge.length; i+=4) {
      String modelEntry = reactionMerge[i];
      String brxnEntry = reactionMerge[i + 1];
      String[] spiArray = reactionMerge[i + 2].split(";");
      String[] rxnArray = reactionMerge[i + 3].split(";");
      Map<Long, Long> reduceMap = new HashMap<> ();
      Long brxnId = service.getIdByEntryAndDatabase(String.format("%s@%s", brxnEntry, modelEntry), modelEntry);
//      Long brxnId = service.getIdByEntryAndDatabase(brxnEntry, modelEntry);
      if (brxnId != null) {
        for (int j = 0; j < spiArray.length; j++) {
          String spiEntry = spiArray[j];
          String rxnEntry = rxnArray.length > j ? rxnArray[j] : null;
          Long spiId = service.getIdByEntryAndDatabase(String.format("%s@%s", spiEntry, modelEntry), modelEntry);
          if (spiId == null) {
            logger.warn("specie {} not found", spiEntry);
          } else {
            Pair<Set<Long>, Set<Long>> r = service.getReactionsByCpd(spiId);
            r.getLeft().remove(brxnId);
            r.getRight().remove(brxnId);
            Set<Long> rxnIds = new HashSet<>(r.getLeft());
            rxnIds.addAll(r.getRight());
            if (rxnEntry != null && !rxnEntry.isEmpty()) {
              String rxnEntry_ = String.format("%s@%s", rxnEntry, modelEntry);
              Long rxnId = service.getIdByEntryAndDatabase(rxnEntry_, modelEntry);
              System.out.println(rxnEntry_ + " -> " + rxnId);
              if (rxnId == null) {
                logger.warn("reaction {} unable to select reaction", rxnEntry);
              } else {
                if (rxnIds.contains(rxnId)) {
                  rxnIds.clear();
                  rxnIds.add(rxnId);
                }
              }
            }
            if (rxnIds.size() == 1) {
              long rxnId = rxnIds.iterator().next();
              reduceMap.put(rxnId, spiId);
            } else {
              logger.warn("specie {} {} not single {}", spiEntry, r, rxnEntry);
            }
          }
        }
      } else {
        logger.warn("reaction {} not found", brxnEntry);
      }

      
      mergeMap.put(modelEntry, reduceMap);
    }
    
    return mergeMap;
  };
  
  public static final String DROPBOX_FOLDER = 
      "/home/fliu/Dropbox/DeCaF/Models_integration/SBMLs_not_inBiGG2/";
  
  public static Set<String> getExpectedModels() {
    Set<String> sbmlFiles = new HashSet<> ();
    Set<String> models = new HashSet<> ();
    IOUtils.folderScan(sbmlFiles, 
        new File(DROPBOX_FOLDER), filter);
//    System.out.println(sbmlFiles);
    for (String f : sbmlFiles) {
      File sbmlFile = new File(f);
      String modelEntry = sbmlFile.getName().replace(".xml", "");
      models.add(modelEntry);
    }
    models.remove("iAbaylyiV4");
    models.add("iAbaylyiv4");
    return models;
  }
  
  private static final FileFilter filter = new FileFilter() {
    @Override
    public boolean accept(File file) {
      if (file.isDirectory()) {
        return true;
      } else if (file.getName().endsWith(".xml")) {
        return true;
      }
//      Files.move(from, to);
      return false;
    }
  };
  
//  public static void manualScale(String modelEntry, IntegratedContainerFactory factory) {
//    switch (modelEntry) {
//      case "iJP815":
//        factory.scale(2684013, 0.0001);
//        factory.scale(2683930, 0.000001);
//        break;
//      case "iKF1028":
//        factory.scale(2681587, 0.00001);
//        break;
//      case "iJP962":
//        factory.scale(2685599, 0.0001);
//        factory.scale(2689092, 1e-6);
//        factory.scale(2688931, 1e-2);
//        break;
//      case "iPB890":
//        factory.scale(2740072, 1e-6);
//        factory.scale(2739820, 1e-6);
//        factory.scale(2739821, 1e-4);
//        break;
//      case "iJW145":
//        factory.scale(2691354, 1e-6);
//        factory.scale(2691250, 1e-4);
//        factory.scale(2691231, 1e-3);
//        factory.scale(2691346, 1e-3);
//        break;
//      case "iGT196":
//        factory.scale(2738374, 1/0.33333);
//        break;
//      default:
//        break;
//    }
//  }
  
//  public static void main(String[] args) {
//    for (String k : getExpectedModels()) {
//      System.out.println(k);
//    }
//  }
}
