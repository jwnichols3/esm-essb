#!/apps/misc/gentools/gnu/bin/perl
#
# $Id: Global_Functions.pl,v 1.1 2006/06/06 05:06:29 linden Exp $
#
# $Source: /shared/data/ccvs/repository/esm-websites/portal-tools/CommandLineSuppression/Global_Functions.pl,v $
#
# $State: Exp $
#
# $Log: Global_Functions.pl,v $
# Revision 1.1  2006/06/06 05:06:29  linden
# *** empty log message ***
#
# Revision 1.11  2005/03/09 19:39:40  sarakum
# Modified getpw function so that it will work for sql server named instances (removed the double quote in the awk to a single quote)
#
# Revision 1.10  2004/07/21 18:37:21  martrao
# Replaced vposend with send_major_alert.ksh
#
# Revision 1.9  2003/12/15 23:16:29  martrao
# Modified to send pages via vposend instead of telalert
#
# Revision 1.8  2003/03/06 20:25:57  wongsyd
# *** empty log message ***
#
# Revision 1.7  2003/03/06 17:45:20  wongsyd
# added type parameter to getExcludeList
#
# Revision 1.6  2003/03/06 01:34:53  wongsyd
# rename table_nm to object_nm in t_exclude_rep
#
# Revision 1.5  2003/01/07 02:01:49  vidaesc
# Sydney Wong changes and functions additions
#
# Revision 1.4  2003/01/06 23:55:03  vidaesc
# Global_Functions.pl was modified by Sydney Wong, he added more functions. Alternates callback handlers.
#
# Revision 1.3  2002/12/04 18:46:41  martrao
# Added generic functions to retrieve replication server lists and BRP pairs
#
#
# Note: this script resides in CVS.  Do not edit local copies
#       without updating the CVS repository!
#

#use Apache::Sybase::CTlib;
use Sybase::CTlib;

#----------------------------------------------------------------------------------
# THIS FUNCTION MAKES USE OF THE OLD getpw KORN SHELL SCRIPT.
# Function get_pwd ( $servername , $username )
# 
# Takes a server name and optionally a username.
#
# Returns an array of the password and error message.  If
# the password is set, the error message is undefined.
#
# Currently only 'sa' is recognized if specified for
# username, as we are still dependent on the korn shell
# script getpw.
#
# E.g.
#
# ($password, $error_message) = &getpwd ("SA_SBU");
# if ( defined $error_message ) {
#    print "Houston, we have a problem: $error_message\n";
#    &get_into_escape_capsule_and_exit();
# } else {
#    $handle=&DoTheLoginThing("SA_SBU","sa",$password);
#    &DoSomeThingMagical($handle);
# }
#

sub get_pwd {
    my($server,$user)=@_;

    my($pass);

    # Strip any whitespace in the server name and user name
    $server=~s/\s//g;
    $user=~s/\s//g;
    
    if ( $server eq "" ) {
	return(undef,"A server name is a required parameter");
    }

    if ( $user ne "sa"  && $user ne "" ) {
	return(undef,"getpwd() currently only takes 'sa' as a user argument");
    }

    chop($pass=`getpw $server`);
    if ( $? != 0 ) {
	return(undef,"Error shelling out to call getpw");
    } else {
	return($pass,undef);
    }
    
}


#----------------------------------------------------------------------------------
# Function  do_query ( $handle , $sql )
#
# Takes a valid connection handle to a Sybase server and a SQL string to 
# execute as parameters.
#
# Returns an array of array references, each is a result row from the 
# resulting query.  Distinct result sets are not distinguished.  Keep in
# mind print statements will come back through the server handler, and not
# as part of the ct_results() loop.  Finally, recall that stored procedures
# send back the return code as the final result set in CT-lib.
# 
# E.g.
#
# @results=&do_query($mr_database_handle_from_ct_connect, "exec sp_who");
# $stored_proc_return_code_ref=unshift(@results);
# @return_code=@$stored_proc_return_code;
# $ok_finally_the_single_return_code_value=$return_code[0];
# foreach $rowref ( @results ) {
#    print join(" ",@$rowref)."\n";
# }

sub do_query {
    my ($handle,$sql)=@_;
    my ($e_status,$r_status,$res_type,@row,@results);

    $e_status=$handle->ct_execute($sql);
    @results=();

    while ( CS_SUCCEED == ( $r_status = $handle->ct_results($res_type)) ) {
        if ( $handle->ct_fetchable($res_type) ) {
            while ( @row=$handle->ct_fetch() ) {
                push(@results,[@row]);
            }
        }
    }

    return(@results);
}


#----------------------------------------------------------------------------------
# Function server_handler ( $cmd, $number, $severity, $state, $line, $server, $proc, $msg )
#
# This procedure should never be called directly. Instead, it can be installed as
# a "generic" server callback handler.
# 
# For severity codes greater than 10, the callback handler spits out an error and
# exits immediately.  Keep in mind some innocuous backupserver messages have a severity
# of 11 - this is done just to annoy us all.
# 
# Print statements are printed to stdout.
#
# E.g. 
# ct_callback(CS_SERVERMSG_CB, \&server_handler);
#

sub server_handler {
    my ($cmd,$number,$severity,$state,$line,$server,$proc,$msg)=@_;

    # Ignore the 'changed db context' message
    return(CS_SUCCEED)  if ( $number == 5701 );

    if ( $severity > 10 ) {
        &logstatus("Msg $number, Severity $severity, State $state : $msg");

        &logstatus("SQL Cmd: $cmd")  if ( defined $cmd);

        &logstatus("Program exiting due to database error", 1);
    } else {
        &logstatus("$msg");
    }

    return(CS_SUCCEED);
}


#----------------------------------------------------------------------------------
# Function message_handler ( $layer, $origin, $severity, $number, $msg, $osmsg )
#
# This function should not be called directly.  Rather, it can be installed as
# a "generic" Open Client message handler.  The procedure exits on any non-zero
# severity message from Open Client.
#
# E.g.
# ct_callback(CS_CLIENTMSG_CB, \&message_handler);
#  

sub message_handler {
    my ($layer,$origin,$severity,$number,$msg,$osmsg)=@_;
    
    if ( $severity > 0 ) {
        &logstatus("CT-lib error : Layer $layer, Origin $origin, Severity $severity");
        &logstatus("Msgtext : $msg")  if ( $msg ne "" );

        &logstatus("OS msgtext : $osmsg")  if ( $osmsg ne "" );
          
        &logstatus("Program exiting due to Open Client error", 1);
    }

    return(CS_SUCCEED);
}




#----------------------------------------------------------------------------------
# returns passwd or error string if a validation error occurs
#
sub getpw {
    my($server,$user)=@_;
    my($line,$pass,$x);

    return(undef,"ERROR: User is not authorized to invoke this function")  if ( getgrgid $( ne "sybase" );

    # strip any whitespace in the server name and user name
    $server =~ s/\s//g;
    $user =~ s/\s//g;

    return (undef,"ERROR: Server name is a required parameter")   if ( $server eq "" );

    $user = "sa"  if ( $user eq "" );

    $line= `crypt servers < $ENV{HOME}/etc/.passwd_data | awk '/server_nm=$server / && / $user=/ {print }' `;

    if ( $line eq "" ) {
        return(undef,"ERROR: No record for $user on $server was found");
    } else {
        (undef,$line)= split(/$user=/, $line);
        ($pass,undef)= split(/ /, $line);
        $pass =~ s/\s+//g;		

        return($pass,undef);
    }

}


#----------------------------------------------------------------------------------
# Returns server connection handler
#
# if $hndl_flg is not defined than the generic error/message handlers will be
# installed. Otherwise it's the responsbility of the calling script to install
# its own specific handlers.
#
sub dbLogin {
    my($sybusr, $sybsrv, $sybpwd, $hndl_flg)=@_;
    my($dbh, $error);

    if (! defined $sybpwd) {
       ($sybpwd,$error)= &getpw($sybsrv,$sybusr);
       die "\n\n$error\n"  if ( defined $error );
    }

    if ( ! defined $hndl_flg ) {
        &ct_callback(CS_CLIENTMSG_CB, \&message_handler);
        &ct_callback(CS_SERVERMSG_CB, \&server_handler);
    }

    $dbh = Sybase::CTlib->ct_connect($sybusr, $sybpwd, $sybsrv);
    if ( defined $dbh ) {
        return ($dbh);
    } else{
        return undef;
    }

}


#===========================================================================================
# for simple queries it returns a single array.
#
sub dbSubmit {
    my($dbh,$sql)=@_;
    my(@results, $retcode, @data);

    $retcode=$dbh->ct_execute($sql);

    while (($dbh->ct_results($retcode)) == CS_SUCCEED) {
        next if (! $dbh->ct_fetchable($retcode));
        while ( @data= $dbh->ct_fetch() ) {
            push(@results,join("|",@data));
        }
    }

    return(@results);
}


#===========================================================================================
# returns yyyymmdd format, if $hr_min_flg is defined the yyyymmdd.hhmmss string is returned.
#
sub getdate {
    my($hr_min_flg)=@_;
    my($sec, $min, $hr, $dy, $mon, $yr, $str);

    ($sec, $min, $hr, $dy, $mon, $yr, undef, undef, undef)= localtime(time);

    $mon++;
#   $yr += 1900;

    $yr = ($yr >= 99) ? $yr+1900 : $yr+2000;

    if ( defined $hr_min_flg ) {
        return (sprintf "%04d%02d%02d.%02d%02d%02d",$yr,$mon,$dy,$hr,$min,$sec);
    } else{
        return (sprintf "%04d%02d%02d",$yr,$mon,$dy);
    }

    return $str;

}



#===========================================================================================
sub sendpage {
    my ($str)=@_;
    my ($rc);

    return "Must provide a valid message string"  if ($str eq "");

    $rc= `send_major_alert.ksh -m"$str" `;

    if ( $? == 0 ) {
        return undef;
    } else{
        return $rc;
    }


}

#=========================================================================
# this is an older function that shouldn't be used. use logstatus instead.
#
sub statuslog {
    local (*F, $str, $exitflg)=@_;
    my ($stime);

    $stime= &getdate(1);

    printf F ("%-15s -- %-80s\n",$stime,$str);

    if ( defined $exitflg ) {
	close F;
	exit(-1);
    }

}



#=========================================================================
# Prints a timestamped string msg to filehandle. If exitflg is defined the
# filehandle F gets closed and program exits, this can be used to exit
# a script when a fatal error is encountered.
# CALLS:  &getdate() to get formated date string.
#         &interactive() to determine whether to print str on STDOUT or not.
#
sub logstatus {
    my ($str, $exitflg, $mailto)=@_;
    my ($stime);
				    ## $mailto= "Jalal.Radwan Tung.Chen ...";
    $stime= &getdate(1);

    $str = sprintf ("%-15s == %-80s\n",$stime,$str);

    print LOGFILE "$str";
    print "$str"  if ( &interactive() );

    if ( defined $exitflg ) {
        &sendmail($mailto,$str)  if ( defined $mailto );
        close LOGFILE;
        exit;
    }

}


#=========================================================================
# 
sub sendmail {
    my ($to,$msg)=@_;

    `echo \"$msg\" | mailx -s "$0 job status " $to `;

}



#=========================================================================
# Returns 1 (true) if program is running interactively. if program is running
# from a shell it's interactive, if it's running from cron it's not.
#
sub interactive {

    return -t STDOUT && -t STDERR;

}



#=========================================================================================================
#
sub init_log {
    my ($logfile)=@_;

    my ($oldh, $rcode);

    ## get single value only, otherwise 'find' generates an error.
    ($rcode)= glob("$logfile*");
    system("find $logfile* -mtime +5 -exec rm -f {} \\; 2>&1 ")  if ( defined $rcode );

    $logfile.= ".". &getdate(1);                # append date & seconds to logfile

    ## redirect all STDOUT output to log file.
    open (LOGFILE,">>$logfile") || die "Failed to open log file: $logfile\n";
    $oldh= select(LOGFILE);
    $|= 1;

    select ($oldh)  if ( &interactive() );

    return $logfile;

}

#===========================================================================================
# Returns list of Tables Excluded from Replication
#
sub getExcludeFromRepl {
    my ($dbh, $srv, $db, $type) = @_;

    return dbSubmit ($dbh, "select  distinct object_nm
                            from    dba_db..t_exclude_rep
                            where   server_nm = '$srv'
                            and     database_nm = '$db'
							" . ($type ? "and		upper ($object_type) = '$type'" : "")
                            );
}


local ($have_cb, $exit_sql, $skip_sql) = (0, 1, 0);

sub set_skip_query {
    my ($skip_flg) = @_;

	$exit_sql = 0;
    $skip_sql = $skip_flg;
}

sub prtLog {
    my ( $msg ) = @_;

    my ($sec,$min,$hour,$mday,$mon,$year) = localtime (time ());
    $year -= 100 if $year > 99;

    printf ("%02d/%02d/%02d %02d:%02d:%02d - $msg\n", $mon + 1, $mday, $year, $hour, $min, $sec);
}

sub syb_msg_cb {
   	my ($layer, $origin, $severity, $number, $msg, $osmsg) = @_;

	my $txt = "CT-lib error : Layer $layer, Origin $origin, Severity $severity\nMsgtext : $msg";

	prtLog ("Client Msg: $txt\n");

	return CS_SUCCEED;
}

sub syb_srv_cb {
	my ($cmd, $number, $severity, $state, $line, $server, $proc, $msg) = @_;

	my $txt = "Msg $number, Severity $severity, State $state : $msg";

	if ( $severity > 10 ) {
		prtLog ("Server Msg: $txt\n");
		if ( $exit_sql ) {
			prtLog ("Aborting...");
			exit -1;
		} else {
			$skip_sql = 1;
		}
	} else {
		if ( ($severity == 10) && ($number == 0 || $number == 5701) ) {
#			print ($msg . "\n");
		} else {
			print ("Server Msg:\n$txt");
		}
	}

	return CS_SUCCEED;
}

sub dbConnect {
	my ($dbUser, $dbServer, $dbPwd, $appName, $retry) = @_;

	unless ( $have_cb ) {
		ct_callback (CS_CLIENTMSG_CB, "syb_msg_cb");
		ct_callback (CS_SERVERMSG_CB, "syb_srv_cb");
		$have_cb = 1;
	}

	my ($db, $cnt, $waitTime, $logInfo);

	$retry = 1 unless $retry;
	$cnt = 0;
	do {
		$db = new Sybase::CTlib $dbUser, $dbPwd, $dbServer, $appName;
		if ( defined $db && $db->{RC} == 0) {
			return $db;
		} else {
			$cnt++;
			unless ( $cnt >= $retry ) {
				$waitTime = 10 * $cnt;
				$logInfo = (($cnt == 1) ?
									"Using: " .
									"dbUser = ($dbUser), dbPwd = ($dbPwd), " .
									"dbSrv = ($dbServer), appName = ($appName)\n" .
									"                          "
								: "" );
				prtLog ($logInfo . "Attempt $cnt, DB Connect Failed, Retry in $waitTime secs...");
				sleep $waitTime;
			}
		}
	} while ( $cnt < $retry );
}

sub dbDisconnect {
	undef $db;
}

sub dbSql {
	my ( $db, $sqlstmt ) = @_;

	return undef if $skip_sql;

	return dbSubmit ($db, $sqlstmt);
}

sub dbQuery {
    my ( $db, $sqlstmt ) = @_;

    return undef if $skip_sql;

    return do_query ($db, $sqlstmt);
}

(1);


