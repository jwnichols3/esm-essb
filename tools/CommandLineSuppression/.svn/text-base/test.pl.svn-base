#!c:/ActiveState/perl/bin

use LWP;
use Time::Local;
use Date::Calc qw ( Delta_DHMS );

print "Hello, World!\n";


$time_local = timelocal ( localtime() );
$time_gmt   = timegm    ( gmtime()    );
$time_diff  = $time_gmt - $time_local;

print "Local time: $time_local\n";
print "GMT Time:   $time_gmt\n";
print "Offset:     $time_diff\n";

@s_time_local = localtime();
@s_time_gmt   = gmtime();

#@s_time_diff  = Delta_DHMS ( @s_time_local, @s_time_gmt );

print "@s_time_local\n";
print "@s_time_gmt\n";
print "@s_time_diff\n";

( $diff_days, $diff_hours, $diff_minutes, $diff_seconds )
        = Delta_DHMS (
             @s_time_gmt[5],   @s_time_gmt[4],   @s_time_gmt[3],   @s_time_gmt[2],   @s_time_gmt[1],   @s_time_gmt[0],
             @s_time_local[5], @s_time_local[4], @s_time_local[3], @s_time_local[2], @s_time_local[1], @s_time_local[0]
                     );
print "Hours offset: $diff_hours\n";

