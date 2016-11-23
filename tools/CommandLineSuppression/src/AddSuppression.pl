#!c:/ActiveState/Perl/bin

use LWP::Simple;
use LWP::UserAgent;

$string = addSuppression ( "test_node", "", "", "20060520 12:00:00", "20060520 12:05:00", "", "", "test description", "test text", "linden" );
print "$string\n";

$string = editSuppression ( 3648, "test_node", "", "", "20060520 12:00:00", "20060520 12:05:00", "", "", "test description", "test text", "linden" );
print "$string\n";

sub addSuppression
{
    #  Give the variables easy to read names
    my $node        = $_[0];
    my $group       = $_[1];
    my $instance    = $_[2];
    my $start_tm    = $_[3];
    my $end_tm      = $_[4];
    my $address_exp = $_[5];
    my $minutes_exp = $_[6];
    my $description = $_[7];
    my $text        = $_[8];
    my $userid      = $_[9];

    #  Assumes that the timestamps are in "YYYYMMDD HH:MM:SS" format, with hours in 24-hour style
    my $supStartYear   = substr ( $start_tm,  0, 4 );
    my $supStartMonth  = substr ( $start_tm,  4, 2 );
    my $supStartDate   = substr ( $start_tm,  6, 2 );
    my $supStartHour   = substr ( $start_tm,  9, 2 );
    my $supStartMinute = substr ( $start_tm, 12, 2 );
    if ( $supStartHour > 11 )
    {
        my $supStartAmpm = "PM";
        my $supStartHour = $supStartHour - 12;
    }
    else
    {
        my $supStartAmpm = "AM";
    }

    my $supEndYear     = substr ( $end_tm,  0, 4 );
    my $supEndMonth    = substr ( $end_tm,  4, 2 );
    my $supEndDate     = substr ( $end_tm,  6, 2 );
    my $supEndHour     = substr ( $end_tm,  9, 2 );
    my $supEndMinute   = substr ( $end_tm, 12, 2 );
    if ( $supEndHour > 11 )
    {
        my $supEndAmpm = "PM";
        my $supEndHour = $supEndHour - 12;
    }
    else
    {
        my $supEndAmpm = "AM";
    }

    #  Create the POST request to send the form
    my $url            = "http://$monitoring_portal_server/struts-suppression/html/form_add_entry.jsp";
    my $user_agent     = LWP::UserAgent->new;
    my $response       = $user_agent->post ( $url,
        [
            startChoice     => "later",
            supStartYear    => $supStartYear,
            supStartMonth   => $supStartMonth,
            supStartDate    => $supStartDate,
            supStartHour    => $supStartHour,
            supStartMinute  => $supStartMinute,
            supStartAmpm    => $supStartAmpm,
            endChoice       => "specified",
            supEndYear      => $supEndYear,
            supEndMonth     => $supEndMonth,
            supEndDate      => $supEndDate,
            supEndHour      => $supEndHour,
            supEndMinute    => $supEndMinute,
            supEndAmpm      => $supEndAmpm,
            application     => $group,
            node            => $node,
            description     => $description,
            numMinutesPrior => $minutes_exp,
            email           => $address_exp,
            dbServer        => $instance,
            dbServerMsg     => $text
        ] );

    my $submit_results = $response->content;
    #  Submit the form

    return $submit_results;
}

sub editSuppression
{
    #  Give the variables easy to read names
    my $suppress_id = $_[ 0];
    my $node        = $_[ 1];
    my $group       = $_[ 2];
    my $instance    = $_[ 3];
    my $start_tm    = $_[ 4];
    my $end_tm      = $_[ 5];
    my $address_exp = $_[ 6];
    my $minutes_exp = $_[ 7];
    my $description = $_[ 8];
    my $text        = $_[ 9];
    my $userid      = $_[10];

    #  Assumes that the timestamps are in "YYYYMMDD HH:MM:SS" format, with hours in 24-hour style
    my $supStartYear   = substr ( $start_tm,  0, 4 );
    my $supStartMonth  = substr ( $start_tm,  4, 2 );
    my $supStartDate   = substr ( $start_tm,  6, 2 );
    my $supStartHour   = substr ( $start_tm,  9, 2 );
    my $supStartMinute = substr ( $start_tm, 12, 2 );
    if ( $supStartHour > 11 )
    {
        my $supStartAmpm = "PM";
        my $supStartHour = $supStartHour - 12;
    }
    else
    {
        my $supStartAmpm = "AM";
    }

    my $supEndYear     = substr ( $end_tm,  0, 4 );
    my $supEndMonth    = substr ( $end_tm,  4, 2 );
    my $supEndDate     = substr ( $end_tm,  6, 2 );
    my $supEndHour     = substr ( $end_tm,  9, 2 );
    my $supEndMinute   = substr ( $end_tm, 12, 2 );
    if ( $supEndHour > 11 )
    {
        my $supEndAmpm = "PM";
        my $supEndHour = $supEndHour - 12;
    }
    else
    {
        my $supEndAmpm = "AM";
    }

    #  Create the POST request to send the form
    my $url            = "http://$monitoring_portal_server/struts-suppression/html/form_edit_entry.jsp";
    my $user_agent     = LWP::UserAgent->new;
    my $response       = $user_agent->post ( $url,
        [
            startChoice     => "later",
            supStartYear    => $supStartYear,
            supStartMonth   => $supStartMonth,
            supStartDate    => $supStartDate,
            supStartHour    => $supStartHour,
            supStartMinute  => $supStartMinute,
            supStartAmpm    => $supStartAmpm,
            endChoice       => "specified",
            supEndYear      => $supEndYear,
            supEndMonth     => $supEndMonth,
            supEndDate      => $supEndDate,
            supEndHour      => $supEndHour,
            supEndMinute    => $supEndMinute,
            supEndAmpm      => $supEndAmpm,
            application     => $group,
            node            => $node,
            description     => $description,
            numMinutesPrior => $minutes_exp,
            email           => $address_exp,
            dbServer        => $instance,
            dbServerMsg     => $text,
            suppressId      => $suppress_id
        ] );

    my $submit_results = $response->content;
    #  Submit the form

    return $submit_results;
}


sub URLDecode 
{
    my $theURL = $_[0];
    $theURL =~ tr/+/ /;
    $theURL =~ s/%([a-fA-F0-9]{2,2})/chr(hex($1))/eg;
    $theURL =~ s/<!--(.|\n)*-->//g;
    return $theURL;
}

sub URLEncode 
{
    my $theURL = $_[0];
    $theURL =~ s/([\W])/"%" . uc(sprintf("%2.2x",ord($1)))/eg;
    return $theURL;
}
