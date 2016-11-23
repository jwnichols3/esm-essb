package com.bgi.esm.portlets.Suppression.tools;

import java.sql.*;
import java.util.Calendar;
import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.forms.AddEntry;

public class AddSuppressionBatch
{
    private static String target_hosts[] = 
    {
        "aa-backup1",
        "blastwave",
        "csb-sf-01",
        "dragon",
        "dvmt-sf-16",
        "isp-sa-03",
        "pantera",
        "raccoon",
        "rm-sa-01",
        "sfouxsrv003",
        "sfouxsrv005",
        "sfouxsrv006",
        "sfouxsrv007",
        "sfouxsrv008",
        "sfouxsrv010",
        "sfouxsrv012",
        "sfouxsrv013",
        "sfouxsrv014",
        "sfouxsrv017",
        "sfouxsrv018",
        "sfouxsrv019",
        "sfouxsrv020",
        "sfouxsrv021",
        "sfouxsrv023",
        "sfouxsrv024",
        "sfouxsrv025",
        "sfouxsrv026",
        "sfouxsrv027",
        "sfouxsrv028",
        "sfouxsrv029",
        "sfouxsrv030",
        "sfouxsrv031",
        "sfouxsrv033",
        "sfouxsrv035",
        "sfouxsrv036",
        "sfouxsrv037",
        "sfouxsrv038",
        "sfouxsrv039",
        "sfouxsrv040",
        "sfouxsrv041",
        "sfouxsrv042",
        "sfouxsrv043",
        "sfouxsrv044",
        "sfouxsrv045",
        "sfouxsrv046",
        "sfouxsrv047",
        "sfouxsrv049",
        "sfouxsrv050",
        "sfouxsrv051",
        "sfouxsrv052",
        "sfouxsrv053",
        "syb-sf-19",
        "web-sf-01"
    };

    public static void main ( String args[] ) throws Exception
    {
        Connection con  = Toolkit.getDataSourceDatabaseConnection();
        
        con.clearWarnings();
        
        AddEntry data_form = new AddEntry();

        Toolkit.retrieveServerData ( data_form );

        String data_servers[] = data_form.getDataServers();
        String server_name = null;

        for ( int counter = 0; counter < data_servers.length; counter++ )
        {
            server_name = data_servers[counter].toLowerCase();
            if ( server_name.indexOf ( "syd" ) == 0 )
            {
                System.out.println ( data_servers[counter] );
            }
        }
    }

    public static void main2 ( String args[] ) throws Exception
    {
        Toolkit.getSuppressionDatabaseConnection();
        
        //PreparedStatement ps = con.prepareStatement ( "DELETE FROM t_vpo_suppress WHERE suppress_id >= 16380 AND suppress_id <= 16452" );
        //ps.setString ( 1, "Requested by Darien Lum of the UNIX Team" );
        //int num_updated = ps.executeUpdate();
        //System.out.println ( "Num deleted: " + num_updated );

        AddEntry original_entry = Toolkit.retrieveEntry ( 16379 );
        
        Calendar calendar_start = Calendar.getInstance();
        calendar_start.set ( Calendar.YEAR,        2006 );
        calendar_start.set ( Calendar.MONTH,       Calendar.MAY);
        calendar_start.set ( Calendar.DATE,        19 );
        calendar_start.set ( Calendar.HOUR_OF_DAY, 20 );  
        calendar_start.set ( Calendar.MINUTE,      00 );
        calendar_start.set ( Calendar.SECOND,      00 );

        Calendar calendar_end = Calendar.getInstance();
        calendar_end.set ( Calendar.YEAR,        2006 );
        calendar_end.set ( Calendar.MONTH,       Calendar.MAY );
        calendar_end.set ( Calendar.DATE,        20 );
        calendar_end.set ( Calendar.HOUR_OF_DAY, 20 );  
        calendar_end.set ( Calendar.MINUTE,      00 );
        calendar_end.set ( Calendar.SECOND,      00 );

        calendar_start.add ( Calendar.HOUR, 7 );
        calendar_end.add ( Calendar.HOUR, 7 );
        
        Toolkit.setSupStartDate ( original_entry, calendar_start.getTime() );
        Toolkit.setSupEndDate ( original_entry, calendar_end.getTime() );

        for ( int counter = 0; counter < target_hosts.length; counter++ )
        {
            original_entry.setByNode ( "on" );
            original_entry.setNode ( target_hosts[counter] );
            original_entry.setApplication ( "-" );
            original_entry.setUsername ( "linden" );
            original_entry.setEndChoice ( "specified" );
            original_entry.setStartChoice ( "specified" );

            System.out.println ( "Processing host=" + original_entry.getNode() );
            System.out.println ( "\tUser:  " + original_entry.getUsername() );
            System.out.println ( "\tApp:   " + original_entry.getApplication() );
            System.out.println ( "\tStart: " + Toolkit.getSupStartDateString ( original_entry ) );
            System.out.println ( "\tEnd:   " + Toolkit.getSupEndDateString ( original_entry ) );

            Toolkit.addSuppression ( original_entry, -7*3600*1000 );
        }
        //*/
    }
};
