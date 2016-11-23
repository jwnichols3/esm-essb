@echo off

del vpo_suppress2.exe

perlapp --bind src/localEnv.pl --bind src/Global_Functions.pl vpo_suppress2.pl


rem --lib /opt/OV/activeperl-5.8/lib/site_perl/5.8.8/Apache 
rem --lib /opt/OV/activeperl-5.8/lib/site_perl/5.8.8/Apache/Sybase 
rem --lib /opt/OV/activeperl-5.8/lib/site_perl/5.8.8/CGI
rem --lib /opt/OV/activeperl-5.8/lib/site_perl/5.8.8/Apache/Sybase
rem --lib /opt/OV/activeperl-5.8/lib/site_perl/5.8.8/Apache/Sybase

