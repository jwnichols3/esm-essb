#!/opt/gentools/gnu/bin/perl
# $Id:
#
# $Source:
#

BEGIN {
	$ENV{HOME} = "/apps/home/sybase" unless exists $ENV{HOME};
	$ENV{SYBASE} = "/apps/sybase" unless exists $ENV{SYBASE};
	$ENV{LOG_DIR} = "/logs/sybase" unless exists $ENV{LOG_DIR};

	$ENV{DBA_ADMIN} = "$ENV{HOME}/etc/DBA_ADMIN";
	$ENV{DBA_ON_CALL} = "$ENV{HOME}/etc/DBA_ON_CALL";
}

require "$ENV{HOME}/util/Global_Functions.pl";

my $commonDir = $ENV{commonDir} || "$ENV{HOME}/util";
require "$commonDir/common.pl";

return 1;

