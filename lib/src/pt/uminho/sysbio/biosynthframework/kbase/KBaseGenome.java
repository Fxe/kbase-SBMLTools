package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class KBaseGenome {

  public static class Cds {
    public Integer dna_sequence_length;
    public Integer protein_translation_length;
    
    public String dna_sequence;
    public String function;
    public String id;
    public String domain;
    public String md5;
    public String parent_gene;
    public String parent_mrna;
    public String protein_translation;
    public String type;
    
    public List<String> aliases  = new ArrayList<> ();
    public List<?> location  = new ArrayList<> ();
    
    public Map<String, Object> ontology_terms  = new HashMap<> ();
    
    @Override
    public String toString() {
      return String.format("%s %s %s %s", id, type, function, aliases);
    }
  }
  
  public static class Feature {
    public String dna_sequence;
    public String protein_translation;
    public String type;
    public String md5;
    public String id;
    public String function;
    
    public Map<String, Object> ontology_terms = new HashMap<> ();;
    
    public Integer dna_sequence_length;
    public Integer protein_translation_length;
    
    public List<String> aliases  = new ArrayList<> ();
    public List<?> cdss  = new ArrayList<> ();
    public List<?> location  = new ArrayList<> ();
    public List<?> mrnas  = new ArrayList<> ();
    
    public Map<String, ?> quality  = new HashMap<> ();
    
    @Override
    public String toString() {
      return String.format("%s %s %s %s", id, type, function, aliases);
    }
  }
  
  @JsonIgnore
  public KBaseId kid;
  
  public String assembly_ref;
  public Object gff_handle_ref;
  public List<Cds> cdss = new ArrayList<> ();
  public List<?> close_genomes  = new ArrayList<> ();
  public List<?> mrnas  = new ArrayList<> ();
  public List<Feature> features  = new ArrayList<> ();
  public List<?> publications  = new ArrayList<> ();
  public Integer dna_size;
  public Integer num_contigs;
  public Integer reference_annotation;
  public Integer genetic_code;
  public Double gc_content;
  
  public String external_source_origination_date;
  public String original_source_file_name;
  public String domain;
  public String genbank_handle_ref;
  public String id;
  public String md5;
  public String notes;
  public String release;
  public String scientific_name;
  public String source_id;
  public String taxon_ref;
  public String taxonomy;
  public String type;
  public String source;
}
