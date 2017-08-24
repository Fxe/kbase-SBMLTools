/*
A KBase module: SBMLTools
This sample module contains one small method - filter_contigs.
*/

module SBMLTools {

    /* 
        A 'typedef' allows you to provide a more specific name for
        a type.  Built-in primitive types include 'string', 'int',
        'float'.  Here we define a type named assembly_ref to indicate
        a string that should be set to a KBase ID reference to an
        Assembly data object.
    */
    typedef string assembly_ref;

    /*
        A 'typedef' can also be used to define compound or container
        objects, like lists, maps, and structures.  The standard KBase
        convention is to use structures, as shown here, to define the
        input and output of your function.  Here the input is a
        reference to the Assembly data object, a workspace to save
        output, and a length threshold for filtering.

        To define lists and maps, use a syntax similar to C++ templates
        to indicate the type contained in the list or map.  For example:

            list <string> list_of_strings;
            mapping <string, int> map_of_ints;
    */
    typedef structure {
        assembly_ref assembly_input_ref;
        string workspace_name;
        int min_length;
    } FilterContigsParams;

    typedef structure {
        assembly_ref assembly_input_ref;
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
        string template_id;
        string genome_id;
        list<CompartmentMapping> compartment_translation;
        string biomass_reactions;
        string compound_mappings;
        string gene_mappings;
        int create_extracellular;
        int remove_boundary;
        int fill_metadata;
        string translate_database;
    } IntegrateModelParams;
   
            
    typedef structure {
        string sbml_url;
        string workspace_name;
        list<string> biomass;
        string model_name;
        int automatically_integrate;
        int remove_boundary;
    } SbmlImporterParams;

    /*
        Here is the definition of the output of the function.  The output
        can be used by other SDK modules which call your code, or the output
        visualizations in the Narrative.  'report_name' and 'report_ref' are
        special output fields- if defined, the Narrative can automatically
        render your Report.
    */
    typedef structure {
        string report_name;
        string report_ref;
        assembly_ref assembly_output;
        int n_initial_contigs;
        int n_contigs_removed;
        int n_contigs_remaining;
    } FilterContigsResults;
    
    typedef structure {
        string report_name;
        string report_ref;
        string fbamodel_id;
    } SbmlImporterResults;
    /*
        The actual function is declared using 'funcdef' to specify the name
        and input/return arguments to the function.  For all typical KBase
        Apps that run in the Narrative, your function should have the 
        'authentication required' modifier.
    */
    funcdef sbml_importer(SbmlImporterParams params)
        returns (SbmlImporterResults output) authentication required;
    funcdef integrate_model(IntegrateModelParams params)
        returns (SbmlImporterResults output) authentication required;              
};
