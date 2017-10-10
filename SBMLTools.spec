/*
A KBase module: SBMLTools
This sample module contains one small method - filter_contigs.
*/

module SBMLTools {
    
    typedef structure {
        string assembly_input_ref;
        string workspace_name;
        int min_length;
    } FilterContigsParams;
    
    typedef structure {
        string assembly_input_ref;
        string workspace_name;
        string url;
        int min_length;
    } SbmlImportParams;
    
    typedef structure {
        string kbase_compartment_id;
        list<string> model_compartment_id;
    } CompartmentMapping;
    
    /*
      list<mapping<string, string>> compartment_translation;
    */
    typedef structure {
        string model_name;
        string workspace_name;
        string output_model_name;
        string output_media_name;
        string template_id;
        string genome_id;
        list<CompartmentMapping> compartment_translation;
        string biomass_reactions;
        string compound_mappings;
        string gene_mappings;
        int create_extracellular;
        int remove_boundary;
        int fill_metadata;
        int integrate_model;
        string conflict_resolution;
        string translate_database;
    } IntegrateModelParams;
    
    typedef structure {
        string sbml_url;
        string sbml_local_path;
        string file_type;
        string workspace_name;
        string genome_ref;
        list<string> biomass;
        string model_name;
        int automatically_integrate;
        int remove_boundary;
        string conflict_resolution;
    } SbmlImporterParams;
    
    typedef structure {
        string genome_id;
        string workspace_name;
        string output_model_name;
        int num_models_propagate;
    } AutoPropagateModelParams;
    
    typedef structure {
        string report_name;
        string report_ref;
        string fbamodel_id;
    } SbmlImporterResults;
    
    funcdef sbml_importer(SbmlImporterParams params)
        returns (SbmlImporterResults output) authentication required;
        
    funcdef integrate_model(IntegrateModelParams params)
        returns (SbmlImporterResults output) authentication required;
        
    funcdef auto_propagate_genome(AutoPropagateModelParams params)
        returns (SbmlImporterResults output) authentication required;
};
