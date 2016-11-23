#!/apps/gentools/gnu/bin/perl

use LWP::Simple;
use LWP::UserAgent;

my $commandLineArgument;
my %parameters;

foreach $commandLineArgument (@ARGV)
{
    ($name, $value) = split ( /=/, $commandLineArgument );

    $parameters{ $name } = $value;
};

foreach my $key ( keys %parameters )
{
    my $value = $parameters{$key};
}

my $url             = "http://calntalp202:9580/splunksend-server/CommandLine/AddRequest.do";
my $user_agent      = LWP::UserAgent->new;
my $response        = $user_agent->post ( $url, [%parameters] );

print "Response:\n";
print $response->content;
