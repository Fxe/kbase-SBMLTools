package SBMLTools::SBMLToolsClient;

use JSON::RPC::Client;
use POSIX;
use strict;
use Data::Dumper;
use URI;
use Bio::KBase::Exceptions;
my $get_time = sub { time, 0 };
eval {
    require Time::HiRes;
    $get_time = sub { Time::HiRes::gettimeofday() };
};

use Bio::KBase::AuthToken;

# Client version should match Impl version
# This is a Semantic Version number,
# http://semver.org
our $VERSION = "0.1.0";

=head1 NAME

SBMLTools::SBMLToolsClient

=head1 DESCRIPTION


A KBase module: SBMLTools
This sample module contains one small method - filter_contigs.


=cut

sub new
{
    my($class, $url, @args) = @_;
    

    my $self = {
	client => SBMLTools::SBMLToolsClient::RpcClient->new,
	url => $url,
	headers => [],
    };

    chomp($self->{hostname} = `hostname`);
    $self->{hostname} ||= 'unknown-host';

    #
    # Set up for propagating KBRPC_TAG and KBRPC_METADATA environment variables through
    # to invoked services. If these values are not set, we create a new tag
    # and a metadata field with basic information about the invoking script.
    #
    if ($ENV{KBRPC_TAG})
    {
	$self->{kbrpc_tag} = $ENV{KBRPC_TAG};
    }
    else
    {
	my ($t, $us) = &$get_time();
	$us = sprintf("%06d", $us);
	my $ts = strftime("%Y-%m-%dT%H:%M:%S.${us}Z", gmtime $t);
	$self->{kbrpc_tag} = "C:$0:$self->{hostname}:$$:$ts";
    }
    push(@{$self->{headers}}, 'Kbrpc-Tag', $self->{kbrpc_tag});

    if ($ENV{KBRPC_METADATA})
    {
	$self->{kbrpc_metadata} = $ENV{KBRPC_METADATA};
	push(@{$self->{headers}}, 'Kbrpc-Metadata', $self->{kbrpc_metadata});
    }

    if ($ENV{KBRPC_ERROR_DEST})
    {
	$self->{kbrpc_error_dest} = $ENV{KBRPC_ERROR_DEST};
	push(@{$self->{headers}}, 'Kbrpc-Errordest', $self->{kbrpc_error_dest});
    }

    #
    # This module requires authentication.
    #
    # We create an auth token, passing through the arguments that we were (hopefully) given.

    {
	my %arg_hash2 = @args;
	if (exists $arg_hash2{"token"}) {
	    $self->{token} = $arg_hash2{"token"};
	} elsif (exists $arg_hash2{"user_id"}) {
	    my $token = Bio::KBase::AuthToken->new(@args);
	    if (!$token->error_message) {
	        $self->{token} = $token->token;
	    }
	}
	
	if (exists $self->{token})
	{
	    $self->{client}->{token} = $self->{token};
	}
    }

    my $ua = $self->{client}->ua;	 
    my $timeout = $ENV{CDMI_TIMEOUT} || (30 * 60);	 
    $ua->timeout($timeout);
    bless $self, $class;
    #    $self->_validate_version();
    return $self;
}




=head2 sbml_importer

  $output = $obj->sbml_importer($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a SBMLTools.SbmlImporterParams
$output is a SBMLTools.SbmlImporterResults
SbmlImporterParams is a reference to a hash where the following keys are defined:
	sbml_url has a value which is a string
	workspace_name has a value which is a string
	biomass has a value which is a reference to a list where each element is a string
	model_name has a value which is a string
	automatically_integrate has a value which is an int
	remove_boundary has a value which is an int
SbmlImporterResults is a reference to a hash where the following keys are defined:
	report_name has a value which is a string
	report_ref has a value which is a string
	fbamodel_id has a value which is a string

</pre>

=end html

=begin text

$params is a SBMLTools.SbmlImporterParams
$output is a SBMLTools.SbmlImporterResults
SbmlImporterParams is a reference to a hash where the following keys are defined:
	sbml_url has a value which is a string
	workspace_name has a value which is a string
	biomass has a value which is a reference to a list where each element is a string
	model_name has a value which is a string
	automatically_integrate has a value which is an int
	remove_boundary has a value which is an int
SbmlImporterResults is a reference to a hash where the following keys are defined:
	report_name has a value which is a string
	report_ref has a value which is a string
	fbamodel_id has a value which is a string


=end text

=item Description

The actual function is declared using 'funcdef' to specify the name
and input/return arguments to the function.  For all typical KBase
Apps that run in the Narrative, your function should have the 
'authentication required' modifier.

=back

=cut

 sub sbml_importer
{
    my($self, @args) = @_;

# Authentication: required

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function sbml_importer (received $n, expecting 1)");
    }
    {
	my($params) = @args;

	my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to sbml_importer:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'sbml_importer');
	}
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
	    method => "SBMLTools.sbml_importer",
	    params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{error}->{code},
					       method_name => 'sbml_importer',
					       data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method sbml_importer",
					    status_line => $self->{client}->status_line,
					    method_name => 'sbml_importer',
				       );
    }
}
 


=head2 integrate_model

  $output = $obj->integrate_model($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a SBMLTools.IntegrateModelParams
$output is a SBMLTools.SbmlImporterResults
IntegrateModelParams is a reference to a hash where the following keys are defined:
	model_name has a value which is a string
	workspace_name has a value which is a string
	output_model_name has a value which is a string
	output_media_name has a value which is a string
	template_id has a value which is a string
	genome_id has a value which is a string
	compartment_translation has a value which is a reference to a list where each element is a SBMLTools.CompartmentMapping
	biomass_reactions has a value which is a string
	compound_mappings has a value which is a string
	gene_mappings has a value which is a string
	create_extracellular has a value which is an int
	remove_boundary has a value which is an int
	fill_metadata has a value which is an int
	translate_database has a value which is a string
CompartmentMapping is a reference to a hash where the following keys are defined:
	kbase_compartment_id has a value which is a string
	model_compartment_id has a value which is a reference to a list where each element is a string
SbmlImporterResults is a reference to a hash where the following keys are defined:
	report_name has a value which is a string
	report_ref has a value which is a string
	fbamodel_id has a value which is a string

</pre>

=end html

=begin text

$params is a SBMLTools.IntegrateModelParams
$output is a SBMLTools.SbmlImporterResults
IntegrateModelParams is a reference to a hash where the following keys are defined:
	model_name has a value which is a string
	workspace_name has a value which is a string
	output_model_name has a value which is a string
	output_media_name has a value which is a string
	template_id has a value which is a string
	genome_id has a value which is a string
	compartment_translation has a value which is a reference to a list where each element is a SBMLTools.CompartmentMapping
	biomass_reactions has a value which is a string
	compound_mappings has a value which is a string
	gene_mappings has a value which is a string
	create_extracellular has a value which is an int
	remove_boundary has a value which is an int
	fill_metadata has a value which is an int
	translate_database has a value which is a string
CompartmentMapping is a reference to a hash where the following keys are defined:
	kbase_compartment_id has a value which is a string
	model_compartment_id has a value which is a reference to a list where each element is a string
SbmlImporterResults is a reference to a hash where the following keys are defined:
	report_name has a value which is a string
	report_ref has a value which is a string
	fbamodel_id has a value which is a string


=end text

=item Description



=back

=cut

 sub integrate_model
{
    my($self, @args) = @_;

# Authentication: required

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function integrate_model (received $n, expecting 1)");
    }
    {
	my($params) = @args;

	my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to integrate_model:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'integrate_model');
	}
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
	    method => "SBMLTools.integrate_model",
	    params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{error}->{code},
					       method_name => 'integrate_model',
					       data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method integrate_model",
					    status_line => $self->{client}->status_line,
					    method_name => 'integrate_model',
				       );
    }
}
 


=head2 auto_propagate_genome

  $output = $obj->auto_propagate_genome($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a SBMLTools.AutoPropagateModelParams
$output is a SBMLTools.SbmlImporterResults
AutoPropagateModelParams is a reference to a hash where the following keys are defined:
	genome_id has a value which is a string
	workspace_name has a value which is a string
	output_model_name has a value which is a string
SbmlImporterResults is a reference to a hash where the following keys are defined:
	report_name has a value which is a string
	report_ref has a value which is a string
	fbamodel_id has a value which is a string

</pre>

=end html

=begin text

$params is a SBMLTools.AutoPropagateModelParams
$output is a SBMLTools.SbmlImporterResults
AutoPropagateModelParams is a reference to a hash where the following keys are defined:
	genome_id has a value which is a string
	workspace_name has a value which is a string
	output_model_name has a value which is a string
SbmlImporterResults is a reference to a hash where the following keys are defined:
	report_name has a value which is a string
	report_ref has a value which is a string
	fbamodel_id has a value which is a string


=end text

=item Description



=back

=cut

 sub auto_propagate_genome
{
    my($self, @args) = @_;

# Authentication: required

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function auto_propagate_genome (received $n, expecting 1)");
    }
    {
	my($params) = @args;

	my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to auto_propagate_genome:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'auto_propagate_genome');
	}
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
	    method => "SBMLTools.auto_propagate_genome",
	    params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{error}->{code},
					       method_name => 'auto_propagate_genome',
					       data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method auto_propagate_genome",
					    status_line => $self->{client}->status_line,
					    method_name => 'auto_propagate_genome',
				       );
    }
}
 
  
sub status
{
    my($self, @args) = @_;
    if ((my $n = @args) != 0) {
        Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
                                   "Invalid argument count for function status (received $n, expecting 0)");
    }
    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
        method => "SBMLTools.status",
        params => \@args,
    });
    if ($result) {
        if ($result->is_error) {
            Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
                           code => $result->content->{error}->{code},
                           method_name => 'status',
                           data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
                          );
        } else {
            return wantarray ? @{$result->result} : $result->result->[0];
        }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method status",
                        status_line => $self->{client}->status_line,
                        method_name => 'status',
                       );
    }
}
   

sub version {
    my ($self) = @_;
    my $result = $self->{client}->call($self->{url}, $self->{headers}, {
        method => "SBMLTools.version",
        params => [],
    });
    if ($result) {
        if ($result->is_error) {
            Bio::KBase::Exceptions::JSONRPC->throw(
                error => $result->error_message,
                code => $result->content->{code},
                method_name => 'auto_propagate_genome',
            );
        } else {
            return wantarray ? @{$result->result} : $result->result->[0];
        }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(
            error => "Error invoking method auto_propagate_genome",
            status_line => $self->{client}->status_line,
            method_name => 'auto_propagate_genome',
        );
    }
}

sub _validate_version {
    my ($self) = @_;
    my $svr_version = $self->version();
    my $client_version = $VERSION;
    my ($cMajor, $cMinor) = split(/\./, $client_version);
    my ($sMajor, $sMinor) = split(/\./, $svr_version);
    if ($sMajor != $cMajor) {
        Bio::KBase::Exceptions::ClientServerIncompatible->throw(
            error => "Major version numbers differ.",
            server_version => $svr_version,
            client_version => $client_version
        );
    }
    if ($sMinor < $cMinor) {
        Bio::KBase::Exceptions::ClientServerIncompatible->throw(
            error => "Client minor version greater than Server minor version.",
            server_version => $svr_version,
            client_version => $client_version
        );
    }
    if ($sMinor > $cMinor) {
        warn "New client version available for SBMLTools::SBMLToolsClient\n";
    }
    if ($sMajor == 0) {
        warn "SBMLTools::SBMLToolsClient version is $svr_version. API subject to change.\n";
    }
}

=head1 TYPES



=head2 assembly_ref

=over 4



=item Description

A 'typedef' allows you to provide a more specific name for
a type.  Built-in primitive types include 'string', 'int',
'float'.  Here we define a type named assembly_ref to indicate
a string that should be set to a KBase ID reference to an
Assembly data object.


=item Definition

=begin html

<pre>
a string
</pre>

=end html

=begin text

a string

=end text

=back



=head2 FilterContigsParams

=over 4



=item Description

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


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
assembly_input_ref has a value which is a SBMLTools.assembly_ref
workspace_name has a value which is a string
min_length has a value which is an int

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
assembly_input_ref has a value which is a SBMLTools.assembly_ref
workspace_name has a value which is a string
min_length has a value which is an int


=end text

=back



=head2 SbmlImportParams

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
assembly_input_ref has a value which is a SBMLTools.assembly_ref
workspace_name has a value which is a string
url has a value which is a string
min_length has a value which is an int

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
assembly_input_ref has a value which is a SBMLTools.assembly_ref
workspace_name has a value which is a string
url has a value which is a string
min_length has a value which is an int


=end text

=back



=head2 CompartmentMapping

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
kbase_compartment_id has a value which is a string
model_compartment_id has a value which is a reference to a list where each element is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
kbase_compartment_id has a value which is a string
model_compartment_id has a value which is a reference to a list where each element is a string


=end text

=back



=head2 IntegrateModelParams

=over 4



=item Description

list<mapping<string, string>> compartment_translation;


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
model_name has a value which is a string
workspace_name has a value which is a string
output_model_name has a value which is a string
output_media_name has a value which is a string
template_id has a value which is a string
genome_id has a value which is a string
compartment_translation has a value which is a reference to a list where each element is a SBMLTools.CompartmentMapping
biomass_reactions has a value which is a string
compound_mappings has a value which is a string
gene_mappings has a value which is a string
create_extracellular has a value which is an int
remove_boundary has a value which is an int
fill_metadata has a value which is an int
translate_database has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
model_name has a value which is a string
workspace_name has a value which is a string
output_model_name has a value which is a string
output_media_name has a value which is a string
template_id has a value which is a string
genome_id has a value which is a string
compartment_translation has a value which is a reference to a list where each element is a SBMLTools.CompartmentMapping
biomass_reactions has a value which is a string
compound_mappings has a value which is a string
gene_mappings has a value which is a string
create_extracellular has a value which is an int
remove_boundary has a value which is an int
fill_metadata has a value which is an int
translate_database has a value which is a string


=end text

=back



=head2 SbmlImporterParams

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
sbml_url has a value which is a string
workspace_name has a value which is a string
biomass has a value which is a reference to a list where each element is a string
model_name has a value which is a string
automatically_integrate has a value which is an int
remove_boundary has a value which is an int

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
sbml_url has a value which is a string
workspace_name has a value which is a string
biomass has a value which is a reference to a list where each element is a string
model_name has a value which is a string
automatically_integrate has a value which is an int
remove_boundary has a value which is an int


=end text

=back



=head2 AutoPropagateModelParams

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
genome_id has a value which is a string
workspace_name has a value which is a string
output_model_name has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
genome_id has a value which is a string
workspace_name has a value which is a string
output_model_name has a value which is a string


=end text

=back



=head2 FilterContigsResults

=over 4



=item Description

Here is the definition of the output of the function.  The output
can be used by other SDK modules which call your code, or the output
visualizations in the Narrative.  'report_name' and 'report_ref' are
special output fields- if defined, the Narrative can automatically
render your Report.


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
report_name has a value which is a string
report_ref has a value which is a string
assembly_output has a value which is a SBMLTools.assembly_ref
n_initial_contigs has a value which is an int
n_contigs_removed has a value which is an int
n_contigs_remaining has a value which is an int

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
report_name has a value which is a string
report_ref has a value which is a string
assembly_output has a value which is a SBMLTools.assembly_ref
n_initial_contigs has a value which is an int
n_contigs_removed has a value which is an int
n_contigs_remaining has a value which is an int


=end text

=back



=head2 SbmlImporterResults

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
report_name has a value which is a string
report_ref has a value which is a string
fbamodel_id has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
report_name has a value which is a string
report_ref has a value which is a string
fbamodel_id has a value which is a string


=end text

=back



=cut

package SBMLTools::SBMLToolsClient::RpcClient;
use base 'JSON::RPC::Client';
use POSIX;
use strict;

#
# Override JSON::RPC::Client::call because it doesn't handle error returns properly.
#

sub call {
    my ($self, $uri, $headers, $obj) = @_;
    my $result;


    {
	if ($uri =~ /\?/) {
	    $result = $self->_get($uri);
	}
	else {
	    Carp::croak "not hashref." unless (ref $obj eq 'HASH');
	    $result = $self->_post($uri, $headers, $obj);
	}

    }

    my $service = $obj->{method} =~ /^system\./ if ( $obj );

    $self->status_line($result->status_line);

    if ($result->is_success) {

        return unless($result->content); # notification?

        if ($service) {
            return JSON::RPC::ServiceObject->new($result, $self->json);
        }

        return JSON::RPC::ReturnObject->new($result, $self->json);
    }
    elsif ($result->content_type eq 'application/json')
    {
        return JSON::RPC::ReturnObject->new($result, $self->json);
    }
    else {
        return;
    }
}


sub _post {
    my ($self, $uri, $headers, $obj) = @_;
    my $json = $self->json;

    $obj->{version} ||= $self->{version} || '1.1';

    if ($obj->{version} eq '1.0') {
        delete $obj->{version};
        if (exists $obj->{id}) {
            $self->id($obj->{id}) if ($obj->{id}); # if undef, it is notification.
        }
        else {
            $obj->{id} = $self->id || ($self->id('JSON::RPC::Client'));
        }
    }
    else {
        # $obj->{id} = $self->id if (defined $self->id);
	# Assign a random number to the id if one hasn't been set
	$obj->{id} = (defined $self->id) ? $self->id : substr(rand(),2);
    }

    my $content = $json->encode($obj);

    $self->ua->post(
        $uri,
        Content_Type   => $self->{content_type},
        Content        => $content,
        Accept         => 'application/json',
	@$headers,
	($self->{token} ? (Authorization => $self->{token}) : ()),
    );
}



1;
