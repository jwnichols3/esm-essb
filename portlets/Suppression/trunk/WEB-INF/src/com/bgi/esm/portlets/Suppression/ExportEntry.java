package com.bgi.esm.portlets.Suppression;

import java.sql.*;
import com.bgi.esm.portlets.Suppression.Toolkit;

public class ExportEntry
{
    public static void main ( String args[] ) throws Exception
    {
        System.out.println ( "Entry #: " + args[0] );
        System.out.println ( "Output:  " + args[1] );

        Connection con = Toolkit.getSuppressionDatabaseConnection();
        PreparedStatement ps = con.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where suppress_id=?" );
        ps.setInt ( 1, Integer.parseInt ( args[0] ));
        ResultSet results = ps.executeQuery();
        results.next();

        System.out.println ( "****************** Description:" );
        System.out.println ( results.getString ( "suppress_desc_txt" ) );
        System.out.println ( "****************** Group:" );
        System.out.println ( results.getString ( "group_nm" ) );
        System.out.println ( "****************** Node:" );
        System.out.println ( results.getString ( "node_nm" ) );
        System.out.println ( "****************** Start Date:" );
        System.out.println ( results.getTimestamp ( "start_utc_tms" ) );
        System.out.println ( "****************** Stop Date:" );
        System.out.println ( results.getTimestamp ( "end_utc_tms" ) );
    }
}
