#!/apps/gentools/gnu/bin/perl

use Date::Calc qw ( Delta_DHMS Add_Delta_YMDHMS Day_of_Year );
use Date::Manip;
use Getopt::Long;
use FileHandle;
use File::Basename;
use LWP::Simple;
use LWP::UserAgent;
use Time::Local;
use strict;

#STDOUT->autoflush (1);
#STDERR->autoflush (1);

my $os_type = os_type();
my $Update = "test";
my $monitoring_portal_server = "monitoring:5080";

my (%opt);
my ($start_tm,$end_tm,$msg,$vpoId);
my ($delta,$err,$tmp_tm)
&Getopt::Long::config("noignore_case");
my $retstat = GetOptions(\%opt, "Add",
    "Update=i",
    "Delete=i",
    "node=s",
    "group=s",
    "instance=s",
    "text=s",
    "description=s",
    "start=s",
    "end=s",
    "address_exp=s",
    "minutes_exp=i",
    "caller=s",
    "portal=s",
    "version_tag",
    "help",
    "Verbose=i");

#########################################################################################
#  Processes the command-line arguments
#########################################################################################
if ( "" ne $opt{portal} )
{
    $monitoring_portal_server = $opt{portal};
}

if ( $opt{version_tag} == 1 )
{
    &show_version;
    exit ( 1 );
}

if ( $opt{help} )
{
    &show_usage(0);
    exit ( 1 );
}

if (( "" eq $opt{caller} ) && ( $opt{Help} != 1 ))
{
    print "ERROR: Please specify the Windows NT username of the suppression owner\n";
    &show_usage(0);
}

if ( $opt{Add} == 1 )
{
    if ( "" eq $opt{description} )
    {
        print "ERROR when adding suppression: Please specify the description of the suppression\n";

        &show_usage(0);
    }

    if (( "" eq $opt{group} ) && ( "" eq $opt{node} ))
    {
        print "ERROR when adding suppression: Please specify the group AND/OR the node\n";

        &show_usage(0);
    }
}


if ( $opt{Verbose} >= 1 )
{
    print "Using Portal: $monitoring_portal_server\n";
}


&show_usage(1)  if $retstat != 1;
# 
# Required options
if ($opt{Add} != 1 && $opt{Delete} == "" && $opt{Update} == "") {
    print ("$opt{Add}|$opt{Delete}|$opt{Update}\n") if $opt{Verbose} >= 2;
    print "Syntax error: either -A, -U<id> or -D<id> must be specified.\n\n";
    &show_usage(3);
}
#
# Additional options required for -Add
if ($opt{Add} == 1 && $opt{node} eq "" && $opt{group} eq "" && $opt{instance} eq "" && $opt{text} eq "" ) {
    print ("$opt{Add}|$opt{node}|$opt{group}|$opt{instance}|$opt{text}\n") if $opt{Verbose} >= 2;
    print "Syntax error: when adding suppression, one or more of -n, -g, -i or -t must be specified.\n\n";
    &show_usage(4);
}
if ($opt{Add} == 1 && ($opt{caller} eq "" && $opt{description} eq "" )) {
    print "Syntax error: when adding suppression, both -c and -d must be specified.\n\n";
    &show_usage(5);
}
#
# Additional options for -Update
if ($opt{Update} ne "" && $opt{node} eq "" && $opt{group} eq "") {
    print "Updates require either node (-n) or group (-g) be specified.\n\n";
    &show_usage(11);
}
if ($opt{Update} ne "" && ($opt{address_exp} ne "" || $opt{minutes_exp} ne "" || $opt{text} ne "")) {
    print "Updates to the expiration notification address (-a), expiration notification time (-m)\n";
    print "    and suppression text (-t) are not supported.\n";
    &show_usage(9);
}
if (($opt{Update} ne "" || $opt{Delete} ne "") && $opt{caller} eq "") {
    print "Syntax error: when updating or deleting suppression, -c must be specified.\n\n";
    &show_usage(10);
}

################################################################################
#  Calculate time zone offset
################################################################################
my @s_time_local = localtime();
my @s_time_gmt   = gmtime();

my $diff_days;
my $diff_hours;
my $diff_minutes;
my $diff_seconds;

my $diff_misc;
my $day_of_year1;
my $day_of_year2;

$day_of_year1 = Day_of_Year ( @s_time_gmt[5]+1900, @s_time_gmt[4]+1, @s_time_gmt[3] );
$day_of_year2 = Day_of_Year ( @s_time_local[5]+1900, @s_time_local[4]+1, @s_time_local[3] );

if ( $day_of_year1 == $day_of_year2 )
{
    $diff_hours = @s_time_local[2] - @s_time_gmt[2];
}
elsif ( $day_of_year1 < $day_of_year2 )
{
    $diff_hours = 24 + @s_time_local[2] - @s_time_gmt[2];
}
elsif ( $day_of_year1 > $day_of_year2 )
{
    $diff_hours = @s_time_local[2] - (@s_time_gmt[2] + 24);
}
else
{
    die "Unexpected error: unable to correctly determine the system's timezone\n";
}

###
###  Bug discovered by Jason O'Rourke - Delta_DHMS does not like 2007 dates.
###

#( $diff_days, $diff_hours, $diff_minutes, $diff_seconds, $diff_misc )
#        = Delta_DHMS (
#             @s_time_gmt[5]+1900,   @s_time_gmt[4],   @s_time_gmt[3],   @s_time_gmt[2],   @s_time_gmt[1],   @s_time_gmt[0],
#             @s_time_local[5]+1900, @s_time_local[4], @s_time_local[3], @s_time_local[2], @s_time_local[1], @s_time_local[0]
#                     );

# Parse start time
my $wasStartTimeSpecified = "false";

if ($opt{start} eq "" ) 
{
    $start_tm = localtime();

    my $local_year   = 1900 + @s_time_local[5];
    my $local_month  = @s_time_local[4] + 1;
    if ( $local_month < 10 )
    {
        $local_month = "0$local_month";
    }
    my $local_date   = @s_time_local[3];
    if ( $local_date < 10 )
    {
        $local_date  = "0$local_date";
    }
    my $local_hour   = @s_time_local[2];
    my $local_minute = @s_time_local[1];

    $start_tm = "$local_year$local_month$local_date $local_hour:$local_minute";

    chomp $start_tm;
} 
else 
{
    #$tmp_tm=ParseDate($opt{start});
    #$start_tm=substr($tmp_tm,0,8).' '.substr($tmp_tm,8,5);
    $wasStartTimeSpecified = "true";

    $tmp_tm = $opt{start};
    print ("Specified start date - debug #1: $tmp_tm\n") if $opt{Verbose} >= 1;;
    my $start_year   = substr ( $tmp_tm,  0, 4 );
    my $start_month  = substr ( $tmp_tm,  4, 2 );
    my $start_date   = substr ( $tmp_tm,  6, 2 );
    my $start_hour   = substr ( $tmp_tm,  9, 2 );
    my $start_min    = substr ( $tmp_tm, 12, 2 );
    my $start_second = "0";
    if ( length ( $tmp_tm ) > 15 )
    {
        $start_second = substr ( $tmp_tm, 15, 2 );
    }
    print ("Specified start date - debug #2: $start_year|$start_month|$start_date $start_hour|$start_min|$start_second\n") if $opt{Verbose} >= 1;

    if (( $start_month  < 10 ) && ( length ( $start_month  ) < 2 )) { $start_month  = "0$start_month";  }
    if (( $start_date   < 10 ) && ( length ( $start_date   ) < 2 )) { $start_date   = "0$start_date";   }
    if (( $start_hour   < 10 ) && ( length ( $start_hour   ) < 2 )) { $start_hour   = "0$start_hour";   }
    if (( $start_min    < 10 ) && ( length ( $start_min    ) < 2 )) { $start_min    = "0$start_min";    } 
    if (( $start_second < 10 ) && ( length ( $start_second ) < 2 )) { $start_second = "0$start_second"; }

    print ("Specified start date - debug #3: $start_year:$start_month:$start_date $start_hour:$start_min:$start_second\n") if $opt{Verbose} >= 1;

    my $date_half1 = "$start_year$start_month$start_date";
    my $date_half2 = "$start_hour:$start_min:$start_second";
    #my $date_half2 = "$start_hour:$start_min:$start_second";

    chomp ( $date_half1 );
    chomp ( $date_half2 );

    $start_tm = "$date_half1 $date_half2";

    print ( "Specified start date: $start_tm\n") if $opt{Verbose} >= 1;;
}
#
# Parse end time
if ($opt{end} eq "" ) 
{
    $tmp_tm = $start_tm;
    my $start_year   = substr ( $tmp_tm,  0, 4 );
    my $start_month  = substr ( $tmp_tm,  4, 2 );
    my $start_date   = substr ( $tmp_tm,  6, 2 );
    my $start_hour   = substr ( $tmp_tm,  9, 2 );
    my $start_min    = substr ( $tmp_tm, 12, 2 );
    my $start_second = "0";

    my $end_year;
    my $end_month;
    my $end_date;
    my $end_hour;
    my $end_min;
    my $end_second;
    ($end_year, $end_month, $end_date, $end_hour, $end_min, $end_second ) = 
    Add_Delta_YMDHMS ( 
         $start_year, $start_month, $start_date, $start_hour, $start_min, $start_second, 
         0,           0,            0,           4,           0,          0 );

    #$end_month = $end_month + 1;
    $end_year  = $end_year;
    if ( $end_month  < 10 ) { $end_month  = "0$end_month";  }
    if ( $end_date   < 10 ) { $end_date   = "0$end_date";   }
    if ( $end_hour   < 10 ) { $end_hour   = "0$end_hour";   }
    if ( $end_min    < 10 ) { $end_min    = "0$end_min";    } 
    if ( $end_second < 10 ) { $end_second = "0$end_second"; }

    $end_tm = "$end_year$end_month$end_date $end_hour:$end_min:$end_second";
    print ( "Default time: $end_tm\n" ) if $opt{Verbose} >= 1;;
}
else 
{
    $tmp_tm = $opt{end};
    print ("Specified end date - debug #1: $tmp_tm\n") if $opt{Verbose} >= 1;;
    my $end_year   = substr ( $tmp_tm,  0, 4 );
    my $end_month  = substr ( $tmp_tm,  4, 2 );
    my $end_date   = substr ( $tmp_tm,  6, 2 );
    my $end_hour   = substr ( $tmp_tm,  9, 2 );
    my $end_min    = substr ( $tmp_tm, 12, 2 );
    my $end_second = "0";
    if ( length ( $tmp_tm ) > 15 )
    {
        $end_second = substr ( $tmp_tm, 15, 2 );
    }
    print ("Specified end date - debug #2: $end_year|$end_month|$end_date $end_hour|$end_min|$end_second\n") if $opt{Verbose} >= 1;

    if (( $end_month  < 10 ) && ( length ( $end_month  ) < 2 )) { $end_month  = "0$end_month";  }
    if (( $end_date   < 10 ) && ( length ( $end_date   ) < 2 )) { $end_date   = "0$end_date";   }
    if (( $end_hour   < 10 ) && ( length ( $end_hour   ) < 2 )) { $end_hour   = "0$end_hour";   }
    if (( $end_min    < 10 ) && ( length ( $end_min    ) < 2 )) { $end_min    = "0$end_min";    } 
    if (( $end_second < 10 ) && ( length ( $end_second ) < 2 )) { $end_second = "0$end_second"; }

    print ("Specified end date - debug #3: $end_year:$end_month:$end_date $end_hour:$end_min:$end_second\n") if $opt{Verbose} >= 1;

    my $date_half1 = "$end_year$end_month$end_date";
    my $date_half2 = "$end_hour:$end_min:$end_second";
    #my $date_half2 = "$end_hour:$end_min:$end_second";

    chomp ( $date_half1 );
    chomp ( $date_half2 );

    $end_tm = "$date_half1 $date_half2";

    print ("Specified time: $end_tm\n") if $opt{Verbose} >= 1;;
}
# Open connection to the suppression database
# Paramters ($vpoSrv, $vpoUsr, $vpoPwd, $vpoDbn)
#my $vpoH = getVpoDbh ("SYBPRD_SUPPRESS");
#my $vpoH = getVpoDbh ($ENV{DBA_SERVER});
#my $vpoH = getVpoDbh ();

if ( $opt{Add} == 1 ) {
    print ("Adding suppression\n") if $opt{Verbose} >= 1;
    # Parameters ($vpoH, $nodeNm, $grpNm, $instance, $startTms, $endTms, $notifyAddr, $notifyMins, $desc, $msg, $usrNm)
    #my ($vpoId, $msg) = addVpo ($vpoH, undef, "gorup", undef, "20031226 10:00:00", undef,
    #                   undef, undef, "testing", undef, "wongsyd");
    #my ($vpoId, $msg) = addVpo ($vpoH, "dba-rc-01", undef, undef, "20031226 10:00:00", "20031226 17:00:00",
    #                   "sydney.wong\@barclaysglobal.com", 60, "testing", undef, "wongsyd");
    #my ($vpoId, $msg) = addVpo ($vpoH, $opt{node}, $opt{group}, $opt{instance}, $start_tm, $end_tm,
    #                   $opt{address_exp}, $opt{minutes_exp}, $opt{description}, $opt{text}, $opt{caller});
    addSuppression ( $opt{node}, $opt{group}, $opt{instance}, $start_tm, $end_tm,
                     $opt{address_exp}, $opt{minutes_exp}, $opt{description}, $opt{text}, $opt{caller});
    if ( $msg ) {
        print "0 $msg\n";
    } else {
        print "$vpoId\n";
    }
}


if ( $opt{Update} ne "" ) {
    print ("Updating suppression $opt{Update}\n") if $opt{Verbose} >= 1;
    # Parameters ($vpoH, $suppressId, $nodeNm, $grpNm, $instance, $startTms, $endTms, $desc, $usrNm) 
    #$msg = updVpo ($vpoH, $opt{Update}, undef, "new application", undef, "20031226 10:00:00", "20031226 17:00:00",
    #                   "new desc", "sybase");
    #my ($msg) = updVpo ($vpoH, $opt{Update}, $opt{node}, $opt{group}, $opt{instance}, $start_tm, $end_tm,
    #               $opt{description}, $opt{caller});
    editSuppression ( $opt{Update}, $opt{node}, $opt{group}, $opt{instance}, $start_tm, $end_tm,
                      $opt{address_exp}, $opt{minutes_exp}, $opt{description}, $opt{text}, $opt{caller});
    if ( $msg ) {
        print "$msg\n";
    }
}

if ( $opt{Delete} ne "" ) {
    print ("Deleting suppression $opt{Delete}\n") if $opt{Verbose} >= 1;
    # Parameters ($vpoH, $suppressId, $usrNm)
    my $vpoH = "";
    $msg = deleteSuppression ($vpoH, $opt{Delete}, $opt{caller});
    if ( $msg ) {
        chomp $msg;
        print "$msg\n";
    }
}

exit (0);

#=============================================================================
sub show_usage {
    my ($stat)=@_;
    my ($script_nm);
    $script_nm=fileparse ($0);
    print "Usage: $script_nm -A|-U <id>|-D <id> -c [-ngit] [-dseam] [-vh] \n";
    print "        -A[dd]                    Add suppression \n";
    print "        -U[pdate] <id>            Update suppression \n";
    print "        -D[elete] <id>            Delete suppression \n";
    print "        -n[ode] <node>            Node name \n";
    print "        -g[group] <group>         Group name \n";
    print "        -i[nstance] <server>      Server or instance name \n";
    print "        -t[ext] <string>          Text of messages to be suppressed \n";
    print "        -d[escription] <string>   Suppression description; required for -Add \n";
    print "        -s[tart] <start-time>     Suppression start time (in YYYYMMDD HH:MM[:SS] format);\n";
    print "                                  Defaults to now\n";
    print "        -e[nd] <end-time>         Suppression end time (in YYYYMMDD HH:MM[:SS] format);\n";
    print "                                  Defaults to four hours from start time \n";
    print "        -a[ddress_exp] <email>    Address to email prior to suppression expiration \n";
    print "        -m[inutes_exp] <minutes>  Number of minutes prior to expiration to send email \n";
    print "        -c[aller] <caller-name>   Calling script or user; REQUIRED \n";
    print "        -V[erbose] <debug-level>  run in debug mode\n";
    print "        -v[ersion_tag]            Show version and source tags\n";
    print "        -p[ortal]                 The server:port of the portal to connect to\n";
    print "                                  Defaults to http://monitoring\n";
    print "        -h[elp]                   Show help (this screen) \n";
    print "\n";
    print "    Either -A, \"-U <id>\" or \"-D <id>\" must be specified. \n";
    print "    For -A, at least one of -n, -g, -i or -t must be specified,  \n";
    print "        and both -d & -c must be specified. \n";
    print "    For -U and -D, only -c must be specified. \n";
    print "\n";
    exit ($stat);
}       # end show_usage

#=============================================================================
sub show_version {
    print "vpo_suppress Command-line utility Version 2.1 (January 5, 2007)\n";
    #printf "%s\n",'$Id: vpo_suppress2.pl,v 1.6 2006/11/10 19:26:30 linden Exp $';
    #printf "%s\n",'$Source: /shared/data/ccvs/repository/esm-websites/portal-tools/CommandLineSuppression/vpo_suppress2.pl,v $';
    exit (2);
}       # end show_version


#=============================================================================
sub addSuppression {
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
    my $supStartMonth  = substr ( $start_tm,  4, 2 ) - 1;
    my $supStartDate   = substr ( $start_tm,  6, 2 );
    my $supStartHour   = substr ( $start_tm,  9, 2 );
    my $supStartMinute = substr ( $start_tm, 12, 2 );
    my $supStartAmpm   = "";

    ##  Scrub the hour/minute information
    $supStartHour      =~ s/://g;
    $supStartMinute    =~ s/://g;

    if ( $supStartHour > 11 )
    {
        $supStartAmpm = "PM";
        $supStartHour = $supStartHour - 12;
    }
    else
    {
        $supStartAmpm = "AM";
    }

    my $supEndYear     = substr ( $end_tm,  0, 4 );
    my $supEndMonth    = substr ( $end_tm,  4, 2 ) - 1;
    my $supEndDate     = substr ( $end_tm,  6, 2 );
    my $supEndHour     = substr ( $end_tm,  9, 2 );
    my $supEndMinute   = substr ( $end_tm, 12, 2 );
    my $supEndAmpm     = "PM";

    ##  Scrub the hour/minute information
    $supEndHour        =~ s/://g;
    $supEndMinute      =~ s/://g;

    print ( "addSuppression trace: end_tm=$end_tm, supEndHour=$supEndHour\n" ) if $opt{Verbose} >= 1;
    if ( $supEndHour > 11 )
    {
        $supEndAmpm = "PM";
        $supEndHour = $supEndHour - 12;
    }
    else
    {
        $supEndAmpm = "AM";
    }
    #  Check to see if the node has been specified
    my $by_node = "off";
    if ( $node ne "" )
    {
        $by_node = "on";
    }

    #  Check to see if the app has been specfied
    my $by_app  = "off";
    if ( $group ne "" )
    {
        $by_app = "on";
    }

    #  Check to see if the app has a db server instance/text specified
    my $withDbServer         = "off";
    my $withDbServerInstance = "off";
    my $withDbServerMessage  = "off";
    if (( $instance ne "" ) || ( $text ne "" ))
    {
        $withDbServer = "on";
        if ( $instance ne "" )
        {
            $withDbServerInstance = "on";
        }
        if ( $text ne "" )
        {
            $withDbServerMessage = "on";
        }
    }

    #  Create the POST request to send the form
    my $timezone_offset = $diff_hours*3600*1000;

    print ( "addSuppression Trace #01\n"            ) if $opt{Verbose} >= 1;
    print ( "    supStartYear:   $supStartYear\n"   ) if $opt{Verbose} >= 1;
    print ( "    supStartMonth:  $supStartMonth\n"  ) if $opt{Verbose} >= 1;
    print ( "    supStartDate:   $supStartDate\n"   ) if $opt{Verbose} >= 1;
    print ( "    supStartHour:   $supStartHour\n"   ) if $opt{Verbose} >= 1;
    print ( "    supStartMinute: $supStartMinute\n" ) if $opt{Verbose} >= 1;
    print ( "    supStartAmpm:   $supStartAmpm\n"   ) if $opt{Verbose} >= 1;
    print ( "    supEndYear:     $supEndYear\n"     ) if $opt{Verbose} >= 1;
    print ( "    supEndMonth:    $supEndMonth\n"    ) if $opt{Verbose} >= 1;
    print ( "    supEndDate:     $supEndDate\n"     ) if $opt{Verbose} >= 1;
    print ( "    supEndHour:     $supEndHour\n"     ) if $opt{Verbose} >= 1;
    print ( "    supEndMinute:   $supEndMinute\n"   ) if $opt{Verbose} >= 1;
    print ( "    supEndAmpm:     $supEndAmpm\n"     ) if $opt{Verbose} >= 1;

    my $url             = "http://$monitoring_portal_server/struts-suppression/add_entry_process.do";
    my $user_agent      = LWP::UserAgent->new;
    print ( "addSuppression URL: $url\n" ) if $opt{Verbose} >= 1;
    my $response        = $user_agent->post ( $url,
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
            username        => $userid,
            byApplication   => $by_app,
            byNode          => $by_node,
            withDbServer    => $withDbServer,
            withDbServerInstance => $withDbServerInstance,
            withDbServerMessage  => $withDbServerMessage,
            timeZoneOffset  => $timezone_offset
            
        ] );

    if ( $response->is_success )
    {
        print ( "addSuppression Trace #02\n" ) if $opt{Verbose} >= 1;
        #  Submit the form
        my $submit_results = $response->content;

        print "$submit_results";
        print ( "addSuppression Trace #03\n" ) if $opt{Verbose} >= 1;
    
        return $submit_results;
    }
    else
    {
        my $status_line = $response->status_line;

        print "Status: $status_line\n";

        die "Failed web request: $status_line";
    }
}

#=============================================================================
sub editSuppression {
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
    my $supStartMonth  = substr ( $start_tm,  4, 2 ) - 1;
    my $supStartDate   = substr ( $start_tm,  6, 2 );
    my $supStartHour   = substr ( $start_tm,  9, 2 );
    my $supStartMinute = substr ( $start_tm, 12, 2 );
    my $supStartAmpm   = "";
    my $supEndAmpm   = "";
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
    my $supEndMonth    = substr ( $end_tm,  4, 2 ) - 1;
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
    #  Check to see if the node has been specified
    my $by_node = "off";
    if ( $node ne "" )
    {
        $by_node = "on";
    }

    #  Check to see if the app has been specfied
    my $by_app  = "off";
    if ( $group ne "" )
    {
        $by_app = "on";
    }

    #  Create the POST request to send the form
    #my $url            = "http://$monitoring_portal_server/struts-suppression/html/form_edit_entry.jsp";
    my $timezone_offset = $diff_hours*3600*1000;
    my $url            = "http://$monitoring_portal_server/struts-suppression/edit_entry_process.do";
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
            suppressId      => $suppress_id,
            username        => $userid,
            byApplication   => $by_app,
            byNode          => $by_node,
            suppressId      => $suppress_id,
            timeZoneOffset  => $timezone_offset
        ] );

    my $submit_results = $response->content;
    #  Submit the form
    print "$submit_results";

    return $submit_results;
}

#=============================================================================
sub deleteSuppression {
    my ($vpoH, $suppressId, $usrNm) = @_;

    my $url = "http://$monitoring_portal_server/struts-suppression/html/form_delete_entry.jsp?suppressId=$suppressId";
    my $user_agent     = LWP::UserAgent->new;
    my $response       = $user_agent->post ( $url );
    my $submit_results = $response->content;

    chomp ( $submit_results );
    $submit_results =~ s/\n//g;

    return $submit_results;

    #my @result = dbSubmit ($vpoH, "select  isnull (deleted_flg, 'n')
    #                                from    t_vpo_suppress
    #                                where   suppress_id = $suppressId
    #                                ");
    #return "Suppress ID $suppressId have already been deleted, no action..." if lc ($result[0]) eq "y";
    #
    #dbSubmit ($vpoH, "  update  t_vpo_suppress
    #                    set     deleted_flg = 'y'
    #                    ,       last_update_nm = " . vpoStr ($usrNm, "SYBASE") . "
    #                    ,       last_update_tms = getdate ()
    #                    where   suppress_id = $suppressId
    #                    ");
    #vpoChgTms ($vpoH);

    return undef;
}

#=============================================================================
sub URLDecode {
    my $theURL = $_[0];
    $theURL =~ tr/+/ /;
    $theURL =~ s/%([a-fA-F0-9]{2,2})/chr(hex($1))/eg;
    $theURL =~ s/<!--(.|\n)*-->//g;
    return $theURL;
}

#=============================================================================
sub URLEncode {
    my $theURL = $_[0];
    $theURL =~ s/([\W])/"%" . uc(sprintf("%2.2x",ord($1)))/eg;
    return $theURL;
}

#=============================================================================
sub os_type {
  my $retval;    
  my $platform = "$^O";
  chomp ($platform);
     
  if ( "$platform" eq "MSWin32" ) {
    $retval = 'WINDOWS';
  }
  elsif ( lc "$platform" eq "linux" ) {
    
    $retval = 'LINUX';
    
  }
  else {
    $retval = 'UNIX';
  }
  
  return $retval;
  
}  

