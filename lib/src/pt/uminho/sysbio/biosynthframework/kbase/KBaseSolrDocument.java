package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KBaseSolrDocument {
  public List<String> aliases = new ArrayList<> ();
  public List<String> functions = new ArrayList<> ();
  public String scientific_name;
  public String genome_id;
  public String taxonomy_ref;
  public String workspace_name;
  public String save_date;
  
  public String object_name;
  public Integer dna_sequence_length;
  public Integer protein_translation_length;
  public String genome_source;
  
  public List<Integer> location_end;
  public String genetic_code;
  public List<String> taxonomy;
  public Double gc_content;
  
  public Integer num_contigs;
  public List<String> location_strand;
  public String md5;
  
  public String domain;
  public String object_id;
  public Boolean complete;
  public String gene_name;
  public String object_type;
  public Integer num_cds;
  public String genome_source_id;
  public String genome_feature_id;
  
  public List<Integer> location_begin;
  public String feature_id;
  public List<String> location_contig;
  
  public Integer genome_dna_size;
  public String ws_ref;
  public String feature_type;
  public String assembly_ref;
  public String refseq_category;
  
  @JsonProperty("_version_")
  public Object version;
}
