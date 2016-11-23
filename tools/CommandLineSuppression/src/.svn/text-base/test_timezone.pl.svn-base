#!c:/ActiveState/perl/bin

use Time::Local;
use Date::Calc qw ( Delta_DHMS );
use LWP;

$local_time = localtime();
print "Local time: $local_time\n";

$gmt_time   = gmtime();
print "Local time: $gmt_time\n";

$time_zone = $ENV{'TZ'};

print "Time zone: $time_zone\n";

my @s_time_local = localtime();
my @s_time_gmt   = gmtime();

( $diff_days, $diff_hours, $diff_minutes, $diff_seconds )
        = Delta_DHMS (
             @s_time_gmt[5],   @s_time_gmt[4],   @s_time_gmt[3],   @s_time_gmt[2],   @s_time_gmt[1],   @s_time_gmt[0],
             @s_time_local[5], @s_time_local[4], @s_time_local[3], @s_time_local[2], @s_time_local[1], @s_time_local[0]
                     );

print "Diff hours: $diff_hours\n";
