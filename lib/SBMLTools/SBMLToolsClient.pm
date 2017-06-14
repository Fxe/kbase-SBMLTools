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




=head2 filter_contigs

  $output = $obj->filter_contigs($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a SBMLTools.SbmlImportParams
$output is a SBMLTools.FilterContigsResults
SbmlImportParams is a reference to a hash where the following keys are defined:
	assembly_input_ref has a value which is a SBMLTools.assembly_ref
	workspace_name has a value which is a string
	url has a value which is a string
	min_length has a value which is an int
assembly_ref is a string
FilterContigsResults is a reference to a hash where the following keys are defined:
	report_name has a value which is a string
	report_ref has a value which is a string
	assembly_output has a value which is a SBMLTools.assembly_ref
	n_initial_contigs has a value which is an int
	n_contigs_removed has a value which is an int
	n_contigs_remaining has a value which is an int

</pre>

=end html

=begin text

$params is a SBMLTools.SbmlImportParams
$output is a SBMLTools.FilterContigsResults
SbmlImportParams is a reference to a hash where the following keys are defined:
	assembly_input_ref has a value which is a SBMLTools.assembly_ref
	workspace_name has a value which is a string
	url has a value which is a string
	min_length has a value which is an int
assembly_ref is a string
FilterContigsResults is a reference to a hash where the following keys are defined:
	report_name has a value which is a string
	report_ref has a value which is a string
	assembly_output has a value which is a SBMLTools.assembly_ref
	n_initial_contigs has a value which is an int
	n_contigs_removed has a value which is an int
	n_contigs_remaining has a value which is an int


=end text

=item Description

The actual function is declared using 'funcdef' to specify the name
and input/return arguments to the function.  For all typical KBase
Apps that run in the Narrative, your function should have the 
'authentication required' modifier.

=back

=cut

 sub filter_contigs
{
    my($self, @args) = @_;

# Authentication: required

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function filter_contigs (received $n, expecting 1)");
    }
    {
	my($params) = @args;

	my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to filter_contigs:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'filter_contigs');
	}
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
	    method => "SBMLTools.filter_contigs",
	    params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{error}->{code},
					       method_name => 'filter_contigs',
					       data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method filter_contigs",
					    status_line => $self->{client}->status_line,
					    method_name => 'filter_contigs',
				       );
    }
}
 


=head2 import_model_xml

  $output = $obj->import_model_xml($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a SBMLTools.SbmlImportParams
$output is a SBMLTools.FilterContigsResults
SbmlImportParams is a reference to a hash where the following keys are defined:
	assembly_input_ref has a value which is a SBMLTools.assembly_ref
	workspace_name has a value which is a string
	url has a value which is a string
	min_length has a value which is an int
assembly_ref is a string
FilterContigsResults is a reference to a hash where the following keys are defined:
	report_name has a value which is a string
	report_ref has a value which is a string
	assembly_output has a value which is a SBMLTools.assembly_ref
	n_initial_contigs has a value which is an int
	n_contigs_removed has a value which is an int
	n_contigs_remaining has a value which is an int

</pre>

=end html

=begin text

$params is a SBMLTools.SbmlImportParams
$output is a SBMLTools.FilterContigsResults
SbmlImportParams is a reference to a hash where the following keys are defined:
	assembly_input_ref has a value which is a SBMLTools.assembly_ref
	workspace_name has a value which is a string
	url has a value which is a string
	min_length has a value which is an int
assembly_ref is a string
FilterContigsResults is a reference to a hash where the following keys are defined:
	report_name has a value which is a string
	report_ref has a value which is a string
	assembly_output has a value which is a SBMLTools.assembly_ref
	n_initial_contigs has a value which is an int
	n_contigs_removed has a value which is an int
	n_contigs_remaining has a value which is an int


=end text

=item Description



=back

=cut

 sub import_model_xml
{
    my($self, @args) = @_;

# Authentication: required

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function import_model_xml (received $n, expecting 1)");
    }
    {
	my($params) = @args;

	my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to import_model_xml:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'import_model_xml');
	}
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
	    method => "SBMLTools.import_model_xml",
	    params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{error}->{code},
					       method_name => 'import_model_xml',
					       data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method import_model_xml",
					    status_line => $self->{client}->status_line,
					    method_name => 'import_model_xml',
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
                method_name => 'import_model_xml',
            );
        } else {
            return wantarray ? @{$result->result} : $result->result->[0];
        }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(
            error => "Error invoking method import_model_xml",
            status_line => $self->{client}->status_line,
            method_name => 'import_model_xml',
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



=head2 fbamodel_id

=over 4



=item Description

FBAModel ID
@id kb


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



=head2 source_id

=over 4



=item Description

Source ID
@id external


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



=head2 genome_ref

=over 4



=item Description

Reference to a model template
@id ws KBaseGenomes.Genome KBaseGenomeAnnotations.GenomeAnnotation


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



=head2 metagenome_ref

=over 4



=item Description

Reference to a metagenome object
@id ws KBaseGenomes.MetagenomeAnnotation


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



=head2 metagenome_otu_ref

=over 4



=item Description

Reference to an OTU in a metagenome
@id subws KBaseGenomes.MetagenomeAnnotation.otus.[*].id


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



=head2 template_ref

=over 4



=item Description

Reference to a model template


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



=head2 gapfill_id

=over 4



=item Description

Gapfill ID
@id kb


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



=head2 gapfill_ref

=over 4



=item Description

Reference to a gapfilling object
@id ws KBaseFBA.Gapfilling


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



=head2 fba_ref

=over 4



=item Description

Reference to a FBA object
@id ws KBaseFBA.FBA


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



=head2 bool

=over 4



=item Definition

=begin html

<pre>
an int
</pre>

=end html

=begin text

an int

=end text

=back



=head2 media_ref

=over 4



=item Description

Reference to a model template
@id ws KBaseBiochem.Media


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



=head2 ModelGapfill

=over 4



=item Description

ModelGapfill object
 
@optional integrated_solution
@optional fba_ref
@optional gapfill_ref jobnode


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.gapfill_id
gapfill_id has a value which is a SBMLTools.gapfill_id
gapfill_ref has a value which is a SBMLTools.gapfill_ref
fba_ref has a value which is a SBMLTools.fba_ref
integrated has a value which is a SBMLTools.bool
integrated_solution has a value which is a string
media_ref has a value which is a SBMLTools.media_ref
jobnode has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.gapfill_id
gapfill_id has a value which is a SBMLTools.gapfill_id
gapfill_ref has a value which is a SBMLTools.gapfill_ref
fba_ref has a value which is a SBMLTools.fba_ref
integrated has a value which is a SBMLTools.bool
integrated_solution has a value which is a string
media_ref has a value which is a SBMLTools.media_ref
jobnode has a value which is a string


=end text

=back



=head2 gapgen_id

=over 4



=item Description

Gapgen ID
@id kb


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



=head2 gapgen_ref

=over 4



=item Description

Reference to a gapgen object
@id ws KBaseFBA.Gapgeneration


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



=head2 ModelGapgen

=over 4



=item Description

ModelGapgen object

@optional integrated_solution
@optional fba_ref
@optional gapgen_ref jobnode


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.gapgen_id
gapgen_id has a value which is a SBMLTools.gapgen_id
gapgen_ref has a value which is a SBMLTools.gapgen_ref
fba_ref has a value which is a SBMLTools.fba_ref
integrated has a value which is a SBMLTools.bool
integrated_solution has a value which is a string
media_ref has a value which is a SBMLTools.media_ref
jobnode has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.gapgen_id
gapgen_id has a value which is a SBMLTools.gapgen_id
gapgen_ref has a value which is a SBMLTools.gapgen_ref
fba_ref has a value which is a SBMLTools.fba_ref
integrated has a value which is a SBMLTools.bool
integrated_solution has a value which is a string
media_ref has a value which is a SBMLTools.media_ref
jobnode has a value which is a string


=end text

=back



=head2 QuantOptSolution

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
integrated has a value which is a SBMLTools.bool
ReactionMaxBounds has a value which is a reference to a list where each element is a reference to a list containing 3 items:
	0: a string
	1: a float
	2: a SBMLTools.bool

UptakeMaxBounds has a value which is a reference to a list where each element is a reference to a list containing 2 items:
	0: a string
	1: a float

BiomassChanges has a value which is a reference to a list where each element is a reference to a list containing 3 items:
	0: a string
	1: a string
	2: a float

ATPSynthase has a value which is a float
ATPMaintenance has a value which is a float

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
integrated has a value which is a SBMLTools.bool
ReactionMaxBounds has a value which is a reference to a list where each element is a reference to a list containing 3 items:
	0: a string
	1: a float
	2: a SBMLTools.bool

UptakeMaxBounds has a value which is a reference to a list where each element is a reference to a list containing 2 items:
	0: a string
	1: a float

BiomassChanges has a value which is a reference to a list where each element is a reference to a list containing 3 items:
	0: a string
	1: a string
	2: a float

ATPSynthase has a value which is a float
ATPMaintenance has a value which is a float


=end text

=back



=head2 ModelQuantOpt

=over 4



=item Description

ModelQuantOpt object


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a string
fba_ref has a value which is a SBMLTools.fba_ref
media_ref has a value which is a SBMLTools.media_ref
integrated has a value which is a SBMLTools.bool
integrated_solution has a value which is an int
solutions has a value which is a reference to a list where each element is a SBMLTools.QuantOptSolution

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a string
fba_ref has a value which is a SBMLTools.fba_ref
media_ref has a value which is a SBMLTools.media_ref
integrated has a value which is a SBMLTools.bool
integrated_solution has a value which is an int
solutions has a value which is a reference to a list where each element is a SBMLTools.QuantOptSolution


=end text

=back



=head2 biomass_id

=over 4



=item Description

Biomass reaction ID
@id external


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



=head2 modelcompound_ref

=over 4



=item Description

Reference to a compound object in a model
@id subws KBaseFBA.FBAModel.modelcompounds.[*].id


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



=head2 BiomassCompound

=over 4



=item Description

BiomassCompound object

    @searchable ws_subset modelcompound_ref coefficient
    @optional gapfill_data


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
modelcompound_ref has a value which is a SBMLTools.modelcompound_ref
coefficient has a value which is a float
gapfill_data has a value which is a reference to a hash where the key is a SBMLTools.gapfill_id and the value is a SBMLTools.bool

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
modelcompound_ref has a value which is a SBMLTools.modelcompound_ref
coefficient has a value which is a float
gapfill_data has a value which is a reference to a hash where the key is a SBMLTools.gapfill_id and the value is a SBMLTools.bool


=end text

=back



=head2 Biomass

=over 4



=item Description

Biomass object

@optional removedcompounds


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.biomass_id
name has a value which is a string
other has a value which is a float
dna has a value which is a float
rna has a value which is a float
protein has a value which is a float
cellwall has a value which is a float
lipid has a value which is a float
cofactor has a value which is a float
energy has a value which is a float
biomasscompounds has a value which is a reference to a list where each element is a SBMLTools.BiomassCompound
removedcompounds has a value which is a reference to a list where each element is a SBMLTools.BiomassCompound

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.biomass_id
name has a value which is a string
other has a value which is a float
dna has a value which is a float
rna has a value which is a float
protein has a value which is a float
cellwall has a value which is a float
lipid has a value which is a float
cofactor has a value which is a float
energy has a value which is a float
biomasscompounds has a value which is a reference to a list where each element is a SBMLTools.BiomassCompound
removedcompounds has a value which is a reference to a list where each element is a SBMLTools.BiomassCompound


=end text

=back



=head2 modelcompartment_id

=over 4



=item Description

Model compartment ID
@id external


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



=head2 compartment_ref

=over 4



=item Description

Reference to a compartment object
@id subws KBaseBiochem.Biochemistry.compartments.[*].id


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



=head2 ModelCompartment

=over 4



=item Description

ModelCompartment object


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.modelcompartment_id
compartment_ref has a value which is a SBMLTools.compartment_ref
compartmentIndex has a value which is an int
label has a value which is a string
pH has a value which is a float
potential has a value which is a float

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.modelcompartment_id
compartment_ref has a value which is a SBMLTools.compartment_ref
compartmentIndex has a value which is an int
label has a value which is a string
pH has a value which is a float
potential has a value which is a float


=end text

=back



=head2 modelcompound_id

=over 4



=item Description

Model compound ID
@id external


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



=head2 compound_ref

=over 4



=item Description

Reference to a compound object
@id subws KBaseBiochem.Biochemistry.compounds.[*].id


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



=head2 modelcompartment_ref

=over 4



=item Description

Reference to a compartment object in a model
@id subws KBaseFBA.FBAModel.modelcompartments.[*].id


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



=head2 ModelCompound

=over 4



=item Description

ModelCompound object

@optional aliases maxuptake


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.modelcompound_id
compound_ref has a value which is a SBMLTools.compound_ref
aliases has a value which is a reference to a list where each element is a string
name has a value which is a string
charge has a value which is a float
maxuptake has a value which is a float
formula has a value which is a string
modelcompartment_ref has a value which is a SBMLTools.modelcompartment_ref

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.modelcompound_id
compound_ref has a value which is a SBMLTools.compound_ref
aliases has a value which is a reference to a list where each element is a string
name has a value which is a string
charge has a value which is a float
maxuptake has a value which is a float
formula has a value which is a string
modelcompartment_ref has a value which is a SBMLTools.modelcompartment_ref


=end text

=back



=head2 modelreaction_id

=over 4



=item Description

Model reaction ID
@id external


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



=head2 reaction_ref

=over 4



=item Description

Reference to a reaction object in a biochemistry
@id subws KBaseBiochem.Biochemistry.reactions.[*].id


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



=head2 ModelReactionReagent

=over 4



=item Description

ModelReactionReagent object

    @searchable ws_subset modelcompound_ref coefficient


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
modelcompound_ref has a value which is a SBMLTools.modelcompound_ref
coefficient has a value which is a float

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
modelcompound_ref has a value which is a SBMLTools.modelcompound_ref
coefficient has a value which is a float


=end text

=back



=head2 complex_ref

=over 4



=item Description

Reference to a complex object
@id subws KBaseOntology.Mapping.complexes.[*].id


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



=head2 feature_ref

=over 4



=item Description

Reference to a feature of a genome object
@id subws KBaseGenomes.Genome.features.[*].id


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



=head2 ModelReactionProteinSubunit

=over 4



=item Description

ModelReactionProteinSubunit object

    @searchable ws_subset role triggering optionalSubunit feature_refs


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
role has a value which is a string
triggering has a value which is a SBMLTools.bool
optionalSubunit has a value which is a SBMLTools.bool
note has a value which is a string
feature_refs has a value which is a reference to a list where each element is a SBMLTools.feature_ref

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
role has a value which is a string
triggering has a value which is a SBMLTools.bool
optionalSubunit has a value which is a SBMLTools.bool
note has a value which is a string
feature_refs has a value which is a reference to a list where each element is a SBMLTools.feature_ref


=end text

=back



=head2 ModelReactionProtein

=over 4



=item Description

ModelReactionProtein object

@optional source complex_ref


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
complex_ref has a value which is a SBMLTools.complex_ref
note has a value which is a string
modelReactionProteinSubunits has a value which is a reference to a list where each element is a SBMLTools.ModelReactionProteinSubunit
source has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
complex_ref has a value which is a SBMLTools.complex_ref
note has a value which is a string
modelReactionProteinSubunits has a value which is a reference to a list where each element is a SBMLTools.ModelReactionProteinSubunit
source has a value which is a string


=end text

=back



=head2 ModelReaction

=over 4



=item Description

ModelReaction object

@optional gapfill_data name pathway reference aliases maxforflux maxrevflux


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.modelreaction_id
reaction_ref has a value which is a SBMLTools.reaction_ref
name has a value which is a string
aliases has a value which is a reference to a list where each element is a string
pathway has a value which is a string
reference has a value which is a string
direction has a value which is a string
protons has a value which is a float
maxforflux has a value which is a float
maxrevflux has a value which is a float
modelcompartment_ref has a value which is a SBMLTools.modelcompartment_ref
probability has a value which is a float
modelReactionReagents has a value which is a reference to a list where each element is a SBMLTools.ModelReactionReagent
modelReactionProteins has a value which is a reference to a list where each element is a SBMLTools.ModelReactionProtein
gapfill_data has a value which is a reference to a hash where the key is a string and the value is a reference to a hash where the key is an int and the value is a reference to a list containing 3 items:
	0: a string
	1: a SBMLTools.bool
	2: a reference to a list where each element is a SBMLTools.ModelReactionProtein


</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.modelreaction_id
reaction_ref has a value which is a SBMLTools.reaction_ref
name has a value which is a string
aliases has a value which is a reference to a list where each element is a string
pathway has a value which is a string
reference has a value which is a string
direction has a value which is a string
protons has a value which is a float
maxforflux has a value which is a float
maxrevflux has a value which is a float
modelcompartment_ref has a value which is a SBMLTools.modelcompartment_ref
probability has a value which is a float
modelReactionReagents has a value which is a reference to a list where each element is a SBMLTools.ModelReactionReagent
modelReactionProteins has a value which is a reference to a list where each element is a SBMLTools.ModelReactionProtein
gapfill_data has a value which is a reference to a hash where the key is a string and the value is a reference to a hash where the key is an int and the value is a reference to a list containing 3 items:
	0: a string
	1: a SBMLTools.bool
	2: a reference to a list where each element is a SBMLTools.ModelReactionProtein



=end text

=back



=head2 FBAModel

=over 4



=item Description

FBAModel object

@optional gapfilledcandidates metagenome_otu_ref metagenome_ref genome_ref template_refs ATPSynthaseStoichiometry ATPMaintenance quantopts
    @metadata ws source_id as Source ID
    @metadata ws source as Source
    @metadata ws name as Name
    @metadata ws type as Type
    @metadata ws genome_ref as Genome
    @metadata ws length(biomasses) as Number biomasses
    @metadata ws length(modelcompartments) as Number compartments
    @metadata ws length(modelcompounds) as Number compounds
    @metadata ws length(modelreactions) as Number reactions
    @metadata ws length(gapgens) as Number gapgens
    @metadata ws length(gapfillings) as Number gapfills


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.fbamodel_id
source has a value which is a string
source_id has a value which is a SBMLTools.source_id
name has a value which is a string
type has a value which is a string
genome_ref has a value which is a SBMLTools.genome_ref
metagenome_ref has a value which is a SBMLTools.metagenome_ref
metagenome_otu_ref has a value which is a SBMLTools.metagenome_otu_ref
template_ref has a value which is a SBMLTools.template_ref
ATPSynthaseStoichiometry has a value which is a float
ATPMaintenance has a value which is a float
template_refs has a value which is a reference to a list where each element is a SBMLTools.template_ref
gapfillings has a value which is a reference to a list where each element is a SBMLTools.ModelGapfill
gapgens has a value which is a reference to a list where each element is a SBMLTools.ModelGapgen
quantopts has a value which is a reference to a list where each element is a SBMLTools.ModelQuantOpt
biomasses has a value which is a reference to a list where each element is a SBMLTools.Biomass
modelcompartments has a value which is a reference to a list where each element is a SBMLTools.ModelCompartment
modelcompounds has a value which is a reference to a list where each element is a SBMLTools.ModelCompound
modelreactions has a value which is a reference to a list where each element is a SBMLTools.ModelReaction
gapfilledcandidates has a value which is a reference to a list where each element is a SBMLTools.ModelReaction

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a SBMLTools.fbamodel_id
source has a value which is a string
source_id has a value which is a SBMLTools.source_id
name has a value which is a string
type has a value which is a string
genome_ref has a value which is a SBMLTools.genome_ref
metagenome_ref has a value which is a SBMLTools.metagenome_ref
metagenome_otu_ref has a value which is a SBMLTools.metagenome_otu_ref
template_ref has a value which is a SBMLTools.template_ref
ATPSynthaseStoichiometry has a value which is a float
ATPMaintenance has a value which is a float
template_refs has a value which is a reference to a list where each element is a SBMLTools.template_ref
gapfillings has a value which is a reference to a list where each element is a SBMLTools.ModelGapfill
gapgens has a value which is a reference to a list where each element is a SBMLTools.ModelGapgen
quantopts has a value which is a reference to a list where each element is a SBMLTools.ModelQuantOpt
biomasses has a value which is a reference to a list where each element is a SBMLTools.Biomass
modelcompartments has a value which is a reference to a list where each element is a SBMLTools.ModelCompartment
modelcompounds has a value which is a reference to a list where each element is a SBMLTools.ModelCompound
modelreactions has a value which is a reference to a list where each element is a SBMLTools.ModelReaction
gapfilledcandidates has a value which is a reference to a list where each element is a SBMLTools.ModelReaction


=end text

=back



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
