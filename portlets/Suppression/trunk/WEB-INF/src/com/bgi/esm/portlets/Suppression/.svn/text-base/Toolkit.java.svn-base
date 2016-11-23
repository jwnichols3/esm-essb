package com.bgi.esm.portlets.Suppression;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;

import org.apache.log4j.Logger;

//  Servlet stuff
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import com.bgi.esm.portlets.Suppression.DatabaseUtils;
import com.bgi.esm.portlets.Suppression.forms.AddEntry;
import com.bgi.esm.portlets.Suppression.forms.EditEntry;
import com.bgi.esm.portlets.Suppression.forms.SuppressionDate;
import com.bgi.esm.portlets.Suppression.orm.ExpirationNotification;
import com.bgi.esm.portlets.Suppression.orm.SuppressionRecord;
import com.bgi.esm.portlets.Suppression.orm.HibernateFacade;


public class Toolkit
{
    //  Private static variables for database connection properties
    private static Class c                                          = null;
    private static ClassLoader cl                                   = null;
    private static InputStream is                                   = null;
    private static Properties common_properties                     = null;
    private static boolean is_production_library                    = false;
    private static final Logger _log                                = Logger.getLogger (Toolkit.class);

    private static String sup_db_driver_name                        = null;
    private static String sup_db_connection_url                     = null;
    private static String sup_db_username                           = null;
    private static String sup_db_password                           = null;
    private static String ds_db_driver_name                         = null;
    private static String ds_db_connection_url                      = null;
    private static String ds_db_username                            = null;
    private static String ds_db_password                            = null;
    private static String inst_db_driver_name                       = null;
    private static String inst_db_connection_url                    = null;
    private static String inst_db_username                          = null;
    private static String inst_db_password                          = null;
    private static String sup_backup_db_driver_name                 = null;
    private static String sup_backup_db_connection_url              = null;
    private static String sup_backup_db_username                    = null;
    private static String sup_backup_db_password                    = null;
    private static String ds_backup_db_driver_name                  = null;
    private static String ds_backup_db_connection_url               = null;
    private static String ds_backup_db_username                     = null;
    private static String ds_backup_db_password                     = null;
    private static String inst_backup_db_driver_name                = null;
    private static String inst_backup_db_connection_url             = null;
    private static String inst_backup_db_username                   = null;
    private static String inst_backup_db_password                   = null;
    private static String extract_file_suppressions                 = null;
    private static String extract_file_vpo                          = null;
    private static String extract_file_inst                         = null;

    private static HibernateFacade hibernateFacade                  = new HibernateFacade();
    
    public static final int DATABASE_MODE_INVALID                   = -1;
    public static final int DATABASE_MODE_PRIMARY                   =  1;
    public static final int DATABASE_MODE_BACKUP                    =  2;
    public static final int DATABASE_MODE_EXTRACT_FILE              =  3;
    
    private static int database_mode_suppressions                   = DATABASE_MODE_INVALID;
    private static int database_mode_vpo                            = DATABASE_MODE_INVALID;
    private static int database_mode_inst                           = DATABASE_MODE_INVALID;

    private static Connection con_suppressions                      = null;
    private static Connection con_data_source                       = null;
    private static Connection con_instance                          = null;
    private static PreparedStatement ps_insert_step1                = null;
    private static PreparedStatement ps_insert_step2                = null;
    private static PreparedStatement ps_insert_step3                = null;
    private static PreparedStatement ps_remove_test                 = null;
    private static PreparedStatement ps_get_current                 = null;
    private static PreparedStatement ps_get_current_hostname        = null;
    private static PreparedStatement ps_get_deleted                 = null;
    private static PreparedStatement ps_get_deleted_hostname        = null;
    private static PreparedStatement ps_get_deleted_username        = null;
    private static PreparedStatement ps_delete_entry                = null;
    private static PreparedStatement ps_get_all_current             = null;
    private static PreparedStatement ps_get_current_at_time         = null;
    private static PreparedStatement ps_get_historical              = null;
    private static PreparedStatement ps_get_historical_host         = null;
    private static PreparedStatement ps_get_all_historical          = null;
    private static PreparedStatement ps_get_entry                   = null;
    private static PreparedStatement ps_dump_entry                  = null;
    private static PreparedStatement ps_update_entry                = null;
    
    public static final String TIMEZONE_COOKIE_NAME                 = "struts-suppression-timezone-offset";
    public static final int REMOVE_ON_REBOOT_DOES_NOT_MATTER        = 0;
    public static final int REMOVE_ON_REBOOT_TRUE_ONLY              = 1;
    public static final int REMOVE_ON_REBOOT_FALSE_ONLY             = 2;
    public static final int IS_DELETED_DOES_NOT_MATTER              = 0;
    public static final int IS_DELETED_TRUE_ONLY                    = 1;
    public static final int IS_DELETED_FALSE_ONLY                   = 2;
    
    
    /**
     * Default constructor 
     */
    public Toolkit()
    {
    }

    /**
     * Debug function to dump all the database properties to a file
     * 
     * @param filename the name of the output file
     */
    public static void DumpDatabaseProperties ( String filename )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( filename );
                outfile.write ( sup_db_driver_name.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( sup_db_connection_url.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( sup_db_username.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( sup_db_password.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( ds_db_driver_name.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( ds_db_connection_url.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( ds_db_username.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( ds_db_password.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( inst_db_driver_name.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( inst_db_connection_url.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( inst_db_username.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( inst_db_password.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( sup_backup_db_driver_name.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( sup_backup_db_connection_url.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( sup_backup_db_username.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( sup_backup_db_password.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( ds_backup_db_driver_name.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( ds_backup_db_connection_url.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( ds_backup_db_username.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( ds_backup_db_password.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( inst_backup_db_driver_name.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( inst_backup_db_connection_url.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( inst_backup_db_username.getBytes() );
                outfile.write ( "\n".getBytes() );
                outfile.write ( inst_backup_db_password.getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
            exception.printStackTrace();
        }
    }
    
    /**
     * Creates the connection URl to a JDBC database
     * 
     * @param database_type The type of database (Sybase, SQL Server Oracle, MySQL, PostgreSQL, ODBC)
     * @param server the server the database resides on
     * @param name the name of the database
     * @param port the port that the database is listening on
     * @return the connection URl to a JDBC database
     */
    private static String createConnectionURL ( String database_type, String server, String name, String port )
    {
        StringBuilder connection_url = new StringBuilder();
       
        if ( null == database_type )
        {
            return "";
        } 
        if ( database_type.equals ( "Sybase" ) )
        {
            connection_url.append ( "jdbc:sybase:Tds:" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "SQL Server" ) )
        {
            connection_url.append ( "jdbc:jtds:sqlserver://" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "MySQL" ) )
        {
            connection_url.append ( "jdbc:mysql://" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "Oracle" ))
        {
            connection_url.append ( "jdbc:oracle:thin:@" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( ":" );
            connection_url.append ( name );
        }
        
        _log.info ( "Created connection URL: " + connection_url.toString() );
        return connection_url.toString();
    }
    
    /**
     * @param database_type the type of the database (Sybase, SQL Server, Oracle, PostgreSQL, MySQL)
     * @return the class name of the driver that the database will use
     */
    private static String getDatabaseDriverClassName ( String database_type )
    {
        if ( null == database_type )
        {
            return "Unknown";
        }
        else if ( database_type.equals ( "Sybase" ) )
        {
            return "com.sybase.jdbc2.jdbc.SybDriver";
        }
        else if ( database_type.equals ( "SQL Server" ) )
        {
            return "net.sourceforge.jtds.jdbc.Driver";
        }
        else if ( database_type.equals ( "MySQL" ) )
        {
            return "com.mysql.jdbc.Driver";
        }
        else if ( database_type.equals ( "Oracle" ))
        {
            return "oracle.jdbc.driver.OracleDriver";
        }
        
        return "Unknown";
    }
  
    /**
     * Retrieves the instance instance-specific information maintained by the portal from the session and
     * updates the portlet properties
     * 
     * @param request the HttpServletRequest object that holds the instance-specific information from the portal
     */
    public static void setPortletProperties ( HttpServletRequest request )
    {
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );

        setPortletProperties ( renderRequest );
    } 
    
    /**
     * Retrieves the instance-specific information maintained by the portal from the session and
     * updates the portlet properties
     * 
     * @param renderRequest the javax.portlet.RenderRequest object that holds the instance-specific information from the portal
     */
    public static void setPortletProperties ( RenderRequest renderRequest )
    {
        
        if ( null == renderRequest )
        {
            _log.error ( "Null renderRequest detected" );
        }
        else
        //if (( null == common_properties ) && ( null != renderRequest ))
        {
            _log.info ( "Set portlet properties" );
            PortletPreferences prefs = renderRequest.getPreferences();
            
            String db_type         = null;
            String db_server       = null;
            String db_name         = null;
            String db_port         = null;
            
            db_type                = prefs.getValue( "suppression_database_type",     "" );
            db_server              = prefs.getValue( "suppression_database_server",   "" );
            db_name                = prefs.getValue( "suppression_database_name",     "" );
            db_port                = prefs.getValue( "suppression_database_port",     "" );
            
            sup_db_driver_name     = getDatabaseDriverClassName ( db_type );
            sup_db_connection_url  = createConnectionURL ( db_type, db_server, db_name, db_port );
            sup_db_username        = prefs.getValue( "suppression_database_username", "" );
            sup_db_password        = prefs.getValue( "suppression_database_password", "" );
            
            db_type                = prefs.getValue( "vpo_database_type",     "" );
            db_server              = prefs.getValue( "vpo_database_server",   "" );
            db_name                = prefs.getValue( "vpo_database_name",     "" );
            db_port                = prefs.getValue( "vpo_database_port",     "" );
            
            ds_db_driver_name      = getDatabaseDriverClassName ( db_type );
            ds_db_connection_url   = createConnectionURL ( db_type, db_server, db_name, db_port );
            ds_db_username         = prefs.getValue( "vpo_database_username", "" );
            ds_db_password         = prefs.getValue( "vpo_database_password", "" );

            db_type                = prefs.getValue( "inst_database_type",     "" );
            db_server              = prefs.getValue( "inst_database_server",   "" );
            db_name                = prefs.getValue( "inst_database_name",     "" );
            db_port                = prefs.getValue( "inst_database_port",     "" );
            
            inst_db_driver_name    = getDatabaseDriverClassName ( db_type );
            inst_db_connection_url = createConnectionURL ( db_type, db_server, db_name, db_port );
            inst_db_username       = prefs.getValue( "inst_database_username", "" );
            inst_db_password       = prefs.getValue( "inst_database_password", "" );

            common_properties      = new Properties();
            
            common_properties.setProperty ( "suppression.suppressions.database.driver",         sup_db_driver_name        );
            common_properties.setProperty ( "suppression.suppressions.database.connection_url", sup_db_connection_url     );
            common_properties.setProperty ( "suppression.suppressions.database.username",       sup_db_username           );
            common_properties.setProperty ( "suppression.suppressions.database.password",       sup_db_password           );

            common_properties.setProperty ( "suppression.data_source.database.driver",          ds_db_driver_name         );
            common_properties.setProperty ( "suppression.data_source.database.connection_url",  ds_db_connection_url      );
            common_properties.setProperty ( "suppression.data_source.database.username",        ds_db_username            );
            common_properties.setProperty ( "suppression.data_source.database.password",        ds_db_password            );

            common_properties.setProperty ( "suppression.inst.database.driver",                 inst_db_driver_name       );
            common_properties.setProperty ( "suppression.inst.database.connection_url",         inst_db_connection_url    );
            common_properties.setProperty ( "suppression.inst.database.username",               inst_db_username          );
            common_properties.setProperty ( "suppression.inst.database.password",               inst_db_password          );

            _log.error ( "Extract_file_suppressions: " + extract_file_suppressions );
            _log.error ( "Extract_file_vpo:          " + extract_file_vpo          );
            _log.error ( "Extract_file_inst:         " + extract_file_inst         );

            if ( null == extract_file_suppressions ) extract_file_suppressions = "";
            if ( null == extract_file_vpo          ) extract_file_vpo          = "";
            if ( null == extract_file_inst         ) extract_file_inst         = "";

            common_properties.setProperty ( "suppression.suppressions.extract_file",            extract_file_suppressions );
            common_properties.setProperty ( "suppression.data_source.extract_file",             extract_file_vpo          );
            common_properties.setProperty ( "suppression.inst.extractFile",                     extract_file_inst         );
        }
    }

    /**
     * Retrieves the name of the extract file for the suppressions database as specified by the portlet
     * 
     * @param request the HttpServletRequest taken from the portlet's Struts MVC controller 
     * @return the name of the extract file for the suppressions database if the portlet is running as a portlet, 
     *         null otherwise
     */
    public static String getExtractFileSuppressions ( HttpServletRequest request )
    {
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );

        String return_value = null;

        if ( null != renderRequest )
        {
            PortletPreferences prefs    = renderRequest.getPreferences();

            return_value = prefs.getValue ( "extract_file_suppression", null );
        }

        String os = System.getProperties().getProperty ( "os.name" );

        if ( null == return_value )
        {
            return common_properties.getProperty ( "suppression.suppressions.extract_file" );
            /*
            if ( os.indexOf ( "Windows" ) >= 0 )
            {
                return_value = "c:\\test\\extract-suppressions.data";
            }
            else
            {
                return_value = "/opt/OV/suppress/extract.data";
            }
            //*/
        }

        return return_value;
    }

    /**
     * Retrieves the name of the extract file for the data source database as specified by the portlet
     * 
     * @param request the HttpServletRequest taken from the portlet's Struts MVC controller 
     * @return the name of the extract file for the data source (VPO) database if the portlet is running as a portlet, 
     *         null otherwise
     */
    public static String getExtractFileVpo ( HttpServletRequest request )
    {
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );

        String return_value = null;

        if ( null != renderRequest )
        {
            PortletPreferences prefs    = renderRequest.getPreferences();

            return_value = prefs.getValue ( "extract_file_vpo", null );
        }

        String os = System.getProperties().getProperty ( "os.name" );

        if ( null == return_value )
        {
            return common_properties.getProperty ( "suppression.data_source.extract_file" );

            /*
            if ( os.indexOf ( "Windows" ) >= 0 )
            {
                return_value = "c:\\test\\extract-vpo.data";
            }
            else
            {
                return_value = "/opt/OV/suppress/extract-vpo.data";
            }
            //*/
        }

        return return_value;    
    }

    /**
     * Retrieves the name of the extract file for the instance database as specified by the portlet
     * 
     * @param request the HttpServletRequest taken from the portlet's Struts MVC controller 
     * @return the name of the extract file for the instance database if the portlet is running as a portlet, 
     *         null otherwise
     */
    public static String getExtractFileInstance ( HttpServletRequest request )
    {
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );

        String return_value = null;

        if ( null != renderRequest )
        {
            PortletPreferences prefs    = renderRequest.getPreferences();

            return_value = prefs.getValue ( "extract_file_inst", null );
        }

        String os = System.getProperties().getProperty ( "os.name" );

        if ( null == return_value )
        {
            return common_properties.getProperty ( "suppression.inst.extract_file" );

            /*
            if ( os.indexOf ( "Windows" ) >= 0 )
            {
                return_value = "c:\\test\\extract-inst.data";
            }
            else
            {
                return_value = "/opt/OV/suppress/extract-inst.data";
            }
            //*/
        }

        return return_value;    
    }

    /**
     * The main function - used only for debugging purposes.
     * @throws IOException 
     */
    public static void main ( String args[] ) throws ClassNotFoundException, SQLException, IOException
    {
        @SuppressWarnings("unused") 
        AddEntry form = Toolkit.retrieveEntry ( 11981 );

        DumpSybaseDatabase();
    }

    /**
     * Creates an HTML table to display the results of a query against the &ldquo;t_vpo_suppress&rdquo; table in the 
     * &ldquo;suppression&rdquo; database.
     *
     * @param results                  The suppression query results to create an HTML table for
     * @param username                 The username of the current portal user
     * @param struts_path              The struts path of the page calling this function
     * @param show_suppress_id         Switch to show the suppression ID 
     * @param show_group_name          Switch to show the application group name
     * @param show_node_name           Switch to show the node name
     * @param show_db_instance         Switch to show the DB instance
     * @param show_db_message          Switch to show the DB message
     * @param show_description         Switch to show the description
     * @param show_start_time          Switch to show the start time of the suppression
     * @param show_end_time            Switch to show the end time of the suppression
     * @param show_suppression_creator Switch to show who created the suppression
     * @param show_remove_on_reboot    Switch to show the switch to remove the suppression on reboot
     * @return the HTML Table displaying all the results 
     */
    public static Vector<String> createSuppressionsTable ( ResultSet results, String username, String struts_path, boolean show_suppress_id,
                    boolean show_group_name, boolean show_node_name, boolean show_db_instance, boolean show_db_message,
                    boolean show_description, boolean show_start_time, boolean show_end_time, boolean show_suppression_creator,
                    boolean show_remove_on_reboot )
            throws SQLException
    {
        StringBuilder output     = new StringBuilder();
        String group_nm          = null;
        String node_nm           = null;
        String instance          = null;
        String message           = null;
        String suppress_desc_txt = null;
        String creator           = null;
        Date start_utc_tms       = null;
        Date end_utc_tms         = null;
        DateFormat df            = DateFormat.getDateInstance();
        String reboot_text       = null;
        int remove_on_reboot     = 0;
        int suppress_id          = 0;
        int num_columns          = 0;
        int counter              = 0;
        StringBuilder header_row = new StringBuilder ( "                            <tr>\n" );

        //  Count the number of columns to show
        if ( show_suppress_id )
        {
            header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\""+struts_path+"?sort=suppress_id\">ID</html:link></font></th>\n" );
            num_columns++;
        }
        if ( show_group_name )
        {
            num_columns++;
            header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\""+struts_path+"?sort=group_nm\">App</html:link></font></th>\n" );
        }
        if ( show_node_name )
        {
            num_columns++;
            header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\""+struts_path+"?sort=node_nm\">Node</html:link></font></th>\n" );
        }
        if ( show_db_instance ) num_columns++;
        if ( show_db_message )  num_columns++;

        if ( show_db_instance && show_db_message )
        {
            header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\""+struts_path+"?sort=message\">Message Text</html:link> /<br><html:link action=\""+struts_path+"?sort=instance\">Database</html:link></font></th>\n" );
        }
        else if ( show_db_instance )
        {
            header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Database</font></th>\n" );
        }
        else if ( show_db_message )
        {
            header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Message Text</font></th>\n" );
        }

        if ( show_description )
        {
            header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Description</font></th>\n" );
            num_columns++;
        }

        if ( show_start_time ) num_columns++;
        if ( show_end_time )   num_columns++;
        if ( show_start_time && show_end_time )
        {
           header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Start /<br> End </font></th>\n" );
        }
        else if ( show_start_time )
        {
           header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Start</font></th>\n" );
        }
        else if ( show_end_time )
        {
           header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\">End</font></th>\n" );
        }

        if ( show_suppression_creator ) 
        {
            header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Creator</font></th>\n" );
            num_columns++;
        }
        if ( show_remove_on_reboot )
        {
            header_row.append ( "                                <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Remove on<br>Reboot?</font></th>\n" );
            num_columns++;
        }
        header_row.append ( "                            </tr>\n" );

        //  Create the header
        output.append ( "                        <table border=\"1\" width=\"100%\">\n" );
        output.append ( "                            " );
        for ( counter = 1; counter <= num_columns; counter++ )
        {
            output.append ( "<col width=\"<%= width" );
            output.append ( counter );
            output.append ( " %>\">" );
        }
        output.append ( "\n" );

        int num_records = 0;
        StringBuilder table_row = null;
        Vector <String> return_value = new Vector <String> ();
        while ( results.next() )
        {
            remove_on_reboot  = ( show_remove_on_reboot    )? results.getInt    ( "remove_on_reboot" )  : 0;

            table_row = new StringBuilder();
            table_row.append ( "                            <tr>\n" );

            if ( show_suppress_id )
            {
                suppress_id =  results.getInt ( "suppress_id" );
                table_row.append ( "                                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+suppress_id             +"</html:link></font></td>\n" );
            }
            if ( show_group_name )
            {
                group_nm = results.getString ( "group_nm" );
                if (( null == group_nm ) || ( group_nm.length() < 1 ))
                {
                    group_nm = "&nbsp;";
                }
                table_row.append ( "                                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+group_nm                +"</html:link></font></td>\n" );
            }
            if ( show_node_name )
            {
                node_nm = results.getString ( "node_nm" );
                if (( null == node_nm ) || ( node_nm.length() < 1 ))
                {
                    node_nm = "&nbsp;";
                }
                table_row.append ( "                                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+node_nm                 +"</html:link></font></td>\n" );
            }
            if ( show_db_instance && show_db_message )
            {
                instance = results.getString ( "instance" );
                message  = results.getString ( "message" );
                table_row.append ( "                                <td>\n" );
                table_row.append ( "                                    <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+instance                +"</html:link></font>\n" );
                table_row.append ( "                                    <br />\n" );
                table_row.append ( "                                    <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+message                 +"</html:link></font>\n" );
                table_row.append ( "                                </td>\n" );
            }
            else if ( show_db_instance )
            {
                instance = results.getString ( "instance" );
                table_row.append ( "                                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+instance                +"</html:link></font></td>\n" );
            }
            else if ( show_db_message )
            {
                message  = results.getString ( "message" );
                table_row.append ( "                                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+message                 +"</html:link></font></td>\n" );
            }
            if ( show_description )
            {
                suppress_desc_txt = results.getString ( "suppress_desc_txt" );
                table_row.append ( "                                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+suppress_desc_txt       +"</html:link></font></td>\n" );
            }

            if ( show_start_time && show_end_time )
            {
                start_utc_tms = results.getDate ( "start_utc_tms" );
                end_utc_tms   = results.getDate ( "end_utc_tms" );
                table_row.append ( "                                <td>\n" );
                table_row.append ( "                                    <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+df.format ( start_utc_tms )+"</html:link></font>\n" );
                table_row.append ( "                                    <br />\n" );
                table_row.append ( "                                    <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+df.format ( end_utc_tms )  +"</html:link></font>\n" );
                table_row.append ( "                                </td>\n" );
            }
            else if ( show_start_time )
            {
                start_utc_tms = results.getDate ( "start_utc_tms" );
                table_row.append ( "                                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+df.format ( start_utc_tms )+"</html:link></font></td>\n" );
            }
            else if ( show_end_time )
            {
                end_utc_tms   = results.getDate ( "end_utc_tms" );
                table_row.append ( "                                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+df.format ( end_utc_tms )  +"</html:link></font></td>\n" );
            }
            if ( show_suppression_creator )
            {
                creator = results.getString ( "create_nm" );
                table_row.append ( "                                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\">"+creator       +"</html:link></font></td>\n" );
            }
            if ( show_remove_on_reboot )
            {
                remove_on_reboot  = results.getInt ( "remove_on_reboot" );
                reboot_text = (1 == remove_on_reboot)? "Yes" : "No"; 
                table_row.append ( "                                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id="+suppress_id+"\"><center>"+reboot_text   +"</center></html:link></font></td>\n" );
            }
            table_row.append ( "                            </tr>\n" );

            return_value.add ( table_row.toString() );
            
            num_records++;
        }
        output.append ( "                        </table>\n" ); 

        //return output.toString();
        return return_value;
    }

    /**
     * Creates an HTML table to display the results of a query against the &ldquo;t_vpo_suppress&rdquo; table in the 
     * &ldquo;suppression&rdquo; database.
     *
     * @param results the suppression query results to create an HTML table for
     * @param username the username of the current portal user
     * @return the HTML Table displaying all the results 
     */
    public static String createSuppressionsTable ( ResultSet results, String username ) throws SQLException
    {
        StringBuilder output     = new StringBuilder();
        String group_nm          = null;
        String node_nm           = null;
        String instance          = null;
        String message           = null;
        String suppress_desc_txt = null;
        Date start_utc_tms       = null;
        Date end_utc_tms         = null;
        DateFormat df            = DateFormat.getInstance();
        String reboot_text       = null;
        int remove_on_reboot     = 0;
        int suppress_id          = 0;

        //  Create the header
        output.append ( "                        <table border=\"1\" width=\"100%\">\n" );
        output.append ( "                            <col width=\"<%= width1 %>\"><col width=\"<%= width2 %>\"><col width=\"<%= width3 %>\"><col width=\"<%= width4 %>\"><col width=\"<%= width5 %>\"><col width=\"<%= width6 %>\"><col width=\"<%= width7 %>\"><col width=\"<%= width8 %>\">\n" );
        int num_records = 0;
        while ( results.next() )
        {
            suppress_id       = results.getInt    ( 1 );
            group_nm          = results.getString ( "group_nm" );
            node_nm           = results.getString ( "node_nm" );
            instance          = results.getString ( "instance" );
            message           = results.getString ( "message" );
            suppress_desc_txt = results.getString ( "suppress_desc_txt" );
            start_utc_tms     = results.getDate   ( "start_utc_tms" );
            end_utc_tms       = results.getDate   ( "end_utc_tms" );

            group_nm          = (null != group_nm)? group_nm : "All Applications";
            node_nm           = (null != node_nm )? node_nm  : "All Nodes";
            instance          = (null != instance)? instance : "&nbsp;"; 
            message           = (null != message )? message  : "&nbsp;";
            suppress_desc_txt = results.getString ( "suppress_desc_txt" );
            
            if ( false == is_production_library )
            {
                remove_on_reboot  = results.getInt ( "remove_on_reboot" );
            }
            reboot_text       = (1 == remove_on_reboot)? "Yes" : "No";
            
            if ( 0 == num_records % 25 )
            {
                output.append ( "                                <tr>\n" );
                output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">ID</html:link></font></th>\n" );
                output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">App</html:link></font></th>\n" );
                output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Node</html:link></font></th>\n" );
                output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Message Text /<br>Database</html:link></font></th>\n" );
                output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Description</html:link></font></th>\n" );
                output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Remove on<br>Reboot?</html:link></font></th>\n" );
                output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Start /<br> End </html:link></font></th>\n" );
                output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\"></html:link></font></th>\n" );
                output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\"></html:link></font></th>\n" );
                output.append ( "                                </tr>\n" );
            }
            
            if ( null != username )
            {
                 output.append ( "                                <tr>\n" );
                 output.append ( "                                    <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id=\""+suppress_id+"\">"+suppress_id             +"</html:link></font></td>\n" );
                 output.append ( "                                    <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id=\""+suppress_id+"\">"+group_nm                +"</html:link></font></td>\n" );
                 output.append ( "                                    <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id=\""+suppress_id+"\">"+node_nm                 +"</html:link></font></td>\n" );
                 output.append ( "                                    <td>\n" );
                 output.append ( "                                        <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id=\""+suppress_id+"\">"+instance                +"</html:link></font>\n" );
                 output.append ( "                                        <br />\n" );
                 output.append ( "                                        <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id=\""+suppress_id+"\">"+message                 +"</html:link></font>\n" );
                 output.append ( "                                    </td>\n" );
                 output.append ( "                                    <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id=\""+suppress_id+"\">"+suppress_desc_txt       +"</html:link></font></td>\n" );
                 output.append ( "                                    <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id=\""+suppress_id+"\">"+reboot_text             +"</html:link></font></td>\n" );
                 output.append ( "                                    <td>\n" );
                 output.append ( "                                        <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id=\""+suppress_id+"\">"+df.format ( start_utc_tms )+"</html:link></font>\n" );
                 output.append ( "                                        <br />\n" );
                 output.append ( "                                        <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"/view_entry?suppress_id=\""+suppress_id+"\">"+df.format ( end_utc_tms )  +"</html:link></font>\n" );
                 output.append ( "                                     </td>\n" );
                 output.append ( "                                </tr>\n" );
            }
            else
            {
                 output.append ( "                                <tr>\n" );
                 output.append ( "                                    <td><font class=\"portlet-font\" style=\"font-size: x-small;\">"+suppress_id             +"</td>\n" );
                 output.append ( "                                    <td><font class=\"portlet-font\" style=\"font-size: x-small;\">"+group_nm                +"</td>\n" );
                 output.append ( "                                    <td><font class=\"portlet-font\" style=\"font-size: x-small;\">"+node_nm                 +"</html:link></font></td>\n" );
                 output.append ( "                                    <td>\n" );
                 output.append ( "                                        <font class=\"portlet-font\" style=\"font-size: x-small;\">"+instance                +"</font>\n" );
                 output.append ( "                                        <br />\n" );
                 output.append ( "                                        <font class=\"portlet-font\" style=\"font-size: x-small;\">"+message                 +"</font>\n" );
                 output.append ( "                                    </td>\n" );
                 output.append ( "                                    <td><font class=\"portlet-font\" style=\"font-size: x-small;\">"+suppress_desc_txt       +"</font></td>\n" );
                 output.append ( "                                    <td><font class=\"portlet-font\" style=\"font-size: x-small;\">"+reboot_text             +"</font></td>\n" );
                 output.append ( "                                    <td>\n" );
                 output.append ( "                                        <font class=\"portlet-font\" style=\"font-size: x-small;\">"+df.format ( start_utc_tms )+"</font>\n" );
                 output.append ( "                                        <br />\n" );
                 output.append ( "                                        <font class=\"portlet-font\" style=\"font-size: x-small;\">"+df.format ( end_utc_tms )  +"</font>\n" );
                 output.append ( "                                     </td>\n" );
                 output.append ( "                                </tr>\n" );
            }
            
            num_records++;
        }
        output.append ( "                                <tr>\n" );
        output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">ID</html:link></font></th>\n" );
        output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">App</html:link></font></th>\n" );
        output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Node</html:link></font></th>\n" );
        output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Message Text /<br>Database</html:link></font></th>\n" );
        output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Description</html:link></font></th>\n" );
        output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Remove on<br>Reboot?</html:link></font></th>\n" );
        output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Start /<br> End </html:link></font></th>\n" );
        output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\"></html:link></font></th>\n" );
        output.append ( "                                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\"></html:link></font></th>\n" );
        output.append ( "                                </tr>\n" );
        output.append ( "                        </table>\n" );

        return output.toString();
    }

    /**
     * Create a portal-independent, common HashMap <String, String> object for the developer to retrieve
     * HTTP request parameters from.
     *
     * @param request the HttpServletRequest object to extract parameters from
     * @return a HashMap &lt;String, String&gt; of all the HTTP parameters in the HTTP request
     */
    public static HashMap <String, String> retrieveHttpRequestParameters ( HttpServletRequest request )
    {
        HashMap <String, String> param_map = new HashMap <String, String> ();

        if ( request.getParameter ( "_spage" ) != null ) // Liferay parameter handling
        {
            String parameter = request.getParameter ( "_spage" );
            parameter = parameter.substring ( parameter.indexOf ( "?" )+1 );

            // Parse out the tokens
            StringTokenizer tokenizer = new StringTokenizer ( parameter, "&" );
            String key                = null;
            String value              = null;
            int index                 = 0;
            param_map                 = new HashMap <String, String> ();
    
            while ( tokenizer.hasMoreTokens() )
            {
                parameter = tokenizer.nextToken();
                index     = parameter.indexOf ( "=" );
                if ( index >= 0 )
                {
                    key       = parameter.substring ( 0, index );
                    value     = parameter.substring ( index+1 );

                    param_map.put ( key, value );
                }
            }
        }
        else // Struts parameter handling
        {
            Enumeration e = request.getParameterNames();
            String es     = null;
            param_map     = new HashMap <String, String>();
            while ( e.hasMoreElements() )
            {
                es = e.nextElement().toString();
                param_map.put ( es, request.getParameter ( es ) );
            }
        }

        return param_map;
    }
    
    /**
     * Dumps the values of a suppression to a file.
     * 
     * @param suppress_id The ID of the suppression to dump
     * @param filename The name of the outptu file.
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void DumpSuppressionToFile ( int suppress_id, String filename ) throws SQLException, IOException, ClassNotFoundException
    {
        ps_dump_entry.setInt ( 1, suppress_id );
        
        ResultSet results      = ps_dump_entry.executeQuery();
        ResultSetMetaData rsmd = results.getMetaData();
        String column_name     = null;
        int num_columns        = rsmd.getColumnCount();
        int column_type        = 0;
        int counter            = 0;
        
        results.next();
        FileOutputStream outfile = new FileOutputStream ( filename );
            outfile.write ( "<suppression>\n".getBytes() );
            for ( counter = 1; counter <= num_columns; counter++ )
            {
                column_name = rsmd.getColumnName ( counter );
                column_type = rsmd.getColumnType ( counter );
                outfile.write ( "\t<".getBytes() );
                outfile.write ( column_name.getBytes() );
                outfile.write ( ">\n".getBytes() );
                
                if     (( Types.BIGINT   == column_type ) || ( Types.INTEGER == column_type ) || 
                        ( Types.SMALLINT == column_type ) || ( Types.TINYINT == column_type ))
                {
                    outfile.write ( Integer.toString( results.getInt ( counter ) ).getBytes() );
                }
                else if (( Types.DECIMAL == column_type ) ||  ( Types.DOUBLE  == column_type ) ||
                         ( Types.FLOAT   == column_type ) ||  ( Types.NUMERIC == column_type ) ||
                         ( Types.REAL    == column_type ))
                {
                    outfile.write ( Double.toString ( results.getDouble ( counter ) ).getBytes() );
                }
                else
                {
                    String temp_string = results.getString ( counter );
                    if ( null == temp_string ) temp_string = "";
                    outfile.write ( temp_string.getBytes() );
                }
                        
                
                outfile.write ( "\t</".getBytes() );
                outfile.write ( column_name.getBytes() );
                outfile.write ( ">\n".getBytes() );
            }
            outfile.write ( "</suppression>".getBytes() );
        outfile.close();
    }

    /**
     * Dumps the contents of the suppressions database (Sybase) into a hard-coded file.
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void DumpSybaseDatabase() throws SQLException, ClassNotFoundException, IOException
    {
        checkSuppressionDatabaseConnection();

        //  ResultSet results      = stmt.executeQuery ( "SELECT * FROM t_vpo_suppress" );
        long current_time      = System.currentTimeMillis();
        long last_time         = current_time-3600*24*45*1000;
        Date start_date        = new Date ( last_time );
        Date end_date          = new Date ( current_time );
        ResultSet results      = retrieveUserHistory ( null, null, null, null, null, start_date, end_date, REMOVE_ON_REBOOT_DOES_NOT_MATTER, IS_DELETED_DOES_NOT_MATTER, "linden" );
        ResultSetMetaData rsmd = results.getMetaData();
        int num_columns        = rsmd.getColumnCount();
        int column_type        = 0;
        StringBuilder contents = new StringBuilder();

        for ( int counter = 1; counter <=num_columns; counter++ )
        {
            contents.append ( rsmd.getColumnName ( counter ) );
            contents.append ( "," );
        }
        contents.append ( "\n" );

        while ( results.next() )
        {
            for ( int counter = 1; counter <= num_columns; counter++ )
            {
                column_type = rsmd.getColumnType ( counter );
                if (( Types.DECIMAL == column_type ) || ( Types.FLOAT == column_type ) || ( Types.DOUBLE == column_type ) ||
                    ( Types.REAL == column_type ))
                {
                    contents.append ( results.getDouble ( counter ) );
                }
                else if (( Types.CHAR == column_type ) || ( Types.LONGVARCHAR == column_type ) || ( Types.VARCHAR == column_type ))
                {
                    contents.append ( results.getString ( counter ) );
                }
                else if (( Types.INTEGER == column_type ) || ( Types.SMALLINT == column_type ) || ( Types.TINYINT == column_type ))
                {
                    contents.append ( results.getLong ( counter ) );
                }
                else if ( Types.DATE == column_type )
                {
                    contents.append ( results.getDate( counter ).toString() );
                }
                else if ( Types.TIME == column_type )
                {
                    contents.append ( results.getTime( counter ).toString() );
                }
                /*
                else if ( Types.TIMESTAMP == column_type )
                {
                    java.util.Date date = new Date ( results.getTimestamp ( counter ).getTime() );
                    contents.append ( date.toString() );
                }
                //*/
                else
                {
                    String s = results.getString ( counter );
                    s = ( null == s )? "null" : s;
                    //contents.append ( "Unknown type-" + rsmd.getColumnTypeName ( counter ) + "-" + s );
                    contents.append ( s );
                    //*/
                }
                contents.append ( "," );
            }
            contents.append ( "\n" );
        }

        FileOutputStream outfile = new FileOutputStream ( "c:\\test\\sybase.dump.csv" );
            outfile.write ( contents.toString().getBytes() );
        outfile.close();

    }
    
    /**
     * Configures the properties that this Toolkit will be using.  The properties that need to be defined are:
     * 
     *  suppression.suppressions.database.driver                - The class name of the database driver for the primary suppressions database
     *  suppression.suppressions.database.connection_url        - The connection URL to the primary suppressions database
     *  suppression.suppressions.database.username              - The username to the primary suppressions database
     *  suppression.suppressions.database.password              - The password to the primary suppressions database
     *      
     *  suppression.suppressions.backup.database.driver         - The class name of the database driver for the backup suppressions database
     *  suppression.suppressions.backup.database.connection_url - The connection URL to the backup suppressions database
     *  suppression.suppressions.backup.database.username       - The username to the backup suppressions database
     *  suppression.suppressions.backup.database.password       - The password to the backup suppressions database
     *
     *  suppression.data_source.database.driver                 - The class name of the database driver for the primary VPO database (the data source)
     *  suppression.data_source.database.connection_url         - The connection URL to the primary VPO database
     *  suppression.data_source.database.username               - The username to the primary VPO database
     *  suppression.data_source.database.password               - The password to the primary VPO database
     *      
     *  suppression.data_source.backup.database.driver          - The class name of the database driver for the backup VPO database (the data source)
     *  suppression.data_source.backup.database.connection_url  - The connection URL to the backup VPO database
     *  suppression.data_source.backup.database.username        - The username to the backup VPO database
     *  suppression.data_source.backup.database.password        - The password to the backup VPO database
     *
     *  suppression.inst.database.driver                        - The class name of the database driver for the primary instantiated database (the instance)
     *  suppression.inst.database.connection_url                - The connection URL to the primary instantiated database
     *  suppression.inst.database.username                      - The username to the primary instantiated database
     *  suppression.inst.database.password                      - The password to the primary instantiated database
     *      
     *  suppression.inst.backup.database.driver                 - The class name of the database driver for the backup instantiated database (the instance)
     *  suppression.inst.backup.database.connection_url         - The connection URL to the backup instantiated database
     *  suppression.inst.backup.database.username               - The username to the backup instantiated database
     *  suppression.inst.backup.database.password               - The password to the backup instantiated database
     * 
     * @param new_common_properties the new java.util.Properties object to configure this Toolkit with
     */
    public static void setCommonProperties ( Properties new_common_properties ) throws IOException
    {
        common_properties = new_common_properties;
        
        con_instance      = null;
        con_suppressions  = null;
        con_data_source   = null;
        
        retrieveProperties();
    }
    
    /**
     * Populates the toolkit with the values stored in the java.util.Properties object that this object uses
     */
    private static void retrieveProperties()
    {
        sup_db_driver_name             = common_properties.getProperty ( "suppression.suppressions.database.driver" );
        sup_db_connection_url          = common_properties.getProperty ( "suppression.suppressions.database.connection_url" );
        sup_db_username                = common_properties.getProperty ( "suppression.suppressions.database.username" );
        sup_db_password                = common_properties.getProperty ( "suppression.suppressions.database.password" );
        
        sup_backup_db_driver_name      = common_properties.getProperty ( "suppression.suppressions.backup.database.driver" );
        sup_backup_db_connection_url   = common_properties.getProperty ( "suppression.suppressions.backup.database.connection_url" );
        sup_backup_db_username         = common_properties.getProperty ( "suppression.suppressions.backup.database.username" );
        sup_backup_db_password         = common_properties.getProperty ( "suppression.suppressions.backup.database.password" );

        ds_db_driver_name              = common_properties.getProperty ( "suppression.data_source.database.driver" );
        ds_db_connection_url           = common_properties.getProperty ( "suppression.data_source.database.connection_url" );
        ds_db_username                 = common_properties.getProperty ( "suppression.data_source.database.username" );
        ds_db_password                 = common_properties.getProperty ( "suppression.data_source.database.password" );
        
        ds_backup_db_driver_name       = common_properties.getProperty ( "suppression.data_source.backup.database.driver" );
        ds_backup_db_connection_url    = common_properties.getProperty ( "suppression.data_source.backup.database.connection_url" );
        ds_backup_db_username          = common_properties.getProperty ( "suppression.data_source.backup.database.username" );
        ds_backup_db_password          = common_properties.getProperty ( "suppression.data_source.backup.database.password" );

        inst_db_driver_name            = common_properties.getProperty ( "suppression.inst.database.driver" );
        inst_db_connection_url         = common_properties.getProperty ( "suppression.inst.database.connection_url" );
        inst_db_username               = common_properties.getProperty ( "suppression.inst.database.username" );
        inst_db_password               = common_properties.getProperty ( "suppression.inst.database.password" );
        
        inst_backup_db_driver_name     = common_properties.getProperty ( "suppression.inst.backup.database.driver" );
        inst_backup_db_connection_url  = common_properties.getProperty ( "suppression.inst.backup.database.connection_url" );
        inst_backup_db_username        = common_properties.getProperty ( "suppression.inst.backup.database.username" );
        inst_backup_db_password        = common_properties.getProperty ( "suppression.inst.backup.database.password" );

        extract_file_suppressions      = common_properties.getProperty ( "suppression.suppressions.extract_file" );
        extract_file_vpo               = common_properties.getProperty ( "suppression.data_source.extract_file" );
        extract_file_inst              = common_properties.getProperty ( "suppression.inst.extract_file" );
    }
    
    /**
     * Checks to make sure this Toolkit instance is properly configured.  If no properties are defined, then the default properties
     * as definied in the $CLASSPATH\portlet-common.properties file is used.
     * 
     * @throws IOException
     */
    private static void checkProperties() throws IOException
    {
        if ( null == common_properties )
        {
            _log.info ( "Read from default properties file" );
            
            common_properties = new Properties(); 
            c  = (new Toolkit()).getClass();
            cl = c.getClassLoader();
            is = cl.getResourceAsStream ( "portlet-common.properties" );
            common_properties.load ( is );           
            
            retrieveProperties();
        }
    }
       
    /**
     * Retrieves the suppression start date from the a com.bgi.esm.portlets.Suppression.forms.AddEntry data form
     * 
     * @param data_form the com.bgi.esm.portlets.Suppression.forms.AddEntry data form
     * @return the suppression start date
     */
    public static Date getSupStartDate ( SuppressionDate data_form )
    {
        Calendar calendar = Calendar.getInstance();
        TimeZone timezone = calendar.getTimeZone();
        timezone.setRawOffset ( 0 );
        calendar.setTimeZone ( timezone );

        if (( data_form.getStartChoice() != null ) && ( data_form.getStartChoice().equals ( "now" ) ))
        {
            // Do nothing
        }
        else
        {
            calendar.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupStartYear() ) );
            calendar.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupStartMonth() ) );
            calendar.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupStartDate() ) );
            calendar.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupStartHour() ) );
            calendar.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupStartMinute() ) );
            calendar.set ( Calendar.AM_PM,  (0==data_form.getSupStartAmpm().compareTo ( "PM" ))? Calendar.PM : Calendar.AM );
        }

        Date return_value = calendar.getTime();
        calendar          = null;
        timezone          = null;
        
        return return_value;
    }

    
    /**
     * Sets the a data form that implements the com.bgi.esmportlets.Suppression.forms.SuppressionDate interface to have the specified start date
     * 
     * @param data_form the com.bgi.esm.portlets.Suppression.forms.AddEntry data form 
     * @param new_date the new start date
     */
    public static void setSupStartDate ( SuppressionDate data_form, Date new_date )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime ( new_date );

        data_form.setSupStartYear   ( Integer.toString ( calendar.get ( Calendar.YEAR   ) ) );
        data_form.setSupStartMonth  ( Integer.toString ( calendar.get ( Calendar.MONTH  ) ) );
        data_form.setSupStartDate   ( Integer.toString ( calendar.get ( Calendar.DATE   ) ) );
        data_form.setSupStartHour   ( Integer.toString ( calendar.get ( Calendar.HOUR   ) ) );
        data_form.setSupStartMinute ( Integer.toString ( calendar.get ( Calendar.MINUTE ) ) );
        data_form.setSupStartAmpm   ( (calendar.get ( Calendar.HOUR_OF_DAY ) > 12 )? "PM" : "AM" );
    }
    
    /**
     * Sets the a data form that implements the com.bgi.esmportlets.Suppression.forms.SuppressionDate interface to have the specified end date
     * 
     * @param data_form the com.bgi.esm.portlets.Suppression.forms.AddEntry data form 
     * @param new_date the new start date
     */
    public static void setSupEndDate ( SuppressionDate data_form, Date new_date )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime ( new_date );

        data_form.setSupEndYear   ( Integer.toString ( calendar.get ( Calendar.YEAR   ) ) );
        data_form.setSupEndMonth  ( Integer.toString ( calendar.get ( Calendar.MONTH  ) ) );
        data_form.setSupEndDate   ( Integer.toString ( calendar.get ( Calendar.DATE   ) ) );
        data_form.setSupEndHour   ( Integer.toString ( calendar.get ( Calendar.HOUR   ) ) );
        data_form.setSupEndMinute ( Integer.toString ( calendar.get ( Calendar.MINUTE ) ) );
        data_form.setSupEndAmpm   ( (calendar.get ( Calendar.HOUR_OF_DAY ) > 12 )? "PM" : "AM" );
    }

    /**
     * Returns the end date of as extracted from an object/form that implements the com.bgi.esm.portlets.Supperssion.forms.SuppressionDate interface 
     * @param data_form
     * @return the suppression end date
     */
    public static Date getSupEndDate ( SuppressionDate data_form )
    {
        Calendar calendar_end = Calendar.getInstance();
        int server_offset = calendar_end.getTimeZone().getOffset ( System.currentTimeMillis() );
        TimeZone timezone     = calendar_end.getTimeZone();
        timezone.setRawOffset ( 0 );
        calendar_end.setTimeZone ( timezone );


        if ( data_form.getEndChoice().equals ( "offset" ) )
        {
            _log.info ( "getSupDate() - Detected offset for end date" );
            int offset   = Integer.parseInt ( data_form.getEndChoiceNum() );
            int unit     = Integer.parseInt ( data_form.getEndChoiceUnit() ); 
            calendar_end = Calendar.getInstance();
            calendar_end.setTime ( Toolkit.getSupStartDate( data_form ) );
            calendar_end.add ( Calendar.SECOND, offset*unit );
            calendar_end.setTimeZone ( timezone );
        }
        else
        {
            _log.info ( "getSupDate() - Detected specified end date" );
            calendar_end = Calendar.getInstance();
            calendar_end.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupEndYear() ) );
            calendar_end.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupEndMonth() ) );
            calendar_end.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupEndDate() ) );
            calendar_end.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupEndHour() ) );
            calendar_end.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupEndMinute() ) );
            calendar_end.set ( Calendar.AM_PM,  (0==data_form.getSupEndAmpm().compareTo ( "PM" ))? Calendar.PM : Calendar.AM );
        }

        Date return_value = calendar_end.getTime();
        calendar_end      = null;
        timezone          = null;
        
        return return_value;
    }


    /**
     * Retrieves the start date and formats it as a String
     * 
     * @param data_form the object to extract the date from
     * @return the start date of the supperssion formatted as a String
     */
    public static String getSupStartDateString ( SuppressionDate data_form )
    {
        return formatDateString ( data_form.getSupStartYear(),
                Integer.toString ( Integer.parseInt ( data_form.getSupStartMonth() )-1 ),
                data_form.getSupStartDate(),
                data_form.getSupStartHour(),
                data_form.getSupStartMinute(),
                data_form.getSupStartAmpm() );
    }

    /**
     * Retrieves the end date and formats it as a String
     * 
     * @param data_form the object to extract the date from
     * @return the end date of the suppression formatted as a String
     */
    public static String getSupEndDateString ( SuppressionDate data_form )
    {
        return formatDateString ( data_form.getSupEndYear(),
                Integer.toString ( Integer.parseInt ( data_form.getSupEndMonth() )-1 ),
                data_form.getSupEndDate(),
                data_form.getSupEndHour(),
                data_form.getSupEndMinute(),
                data_form.getSupEndAmpm() );
    }
    
    /**
     * Retrieves active suppressions at the specified UTC time (in milliseconds) by coonecting to the database
     * specified by the information in the portlet.
     *
     * @param renderRequest the javax.portlet.RenderReponse object that contains database connection information
     * @param timestamp the UTC timestamp to search for
     * @return a java.sql.ResultSet of all the active suppressions at the specified time
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static ResultSet getCurrentAtTime ( RenderRequest renderRequest, long timestamp ) 
        throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection ( renderRequest );
        
        return getCurrentAtTime ( timestamp );
    }
    
    /**
     * Retrieves active suppressions at the specified UTC time (in milliseconds)
     * 
     * @param timestamp the UTC timestamp to search for
     * @return a java.sql.ResultSet of all the active suppressions at the specified time
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static synchronized ResultSet getCurrentAtTime ( long timestamp ) throws ClassNotFoundException, SQLException, IOException
    {
        ps_get_current_at_time.setTimestamp ( 1, new java.sql.Timestamp ( timestamp ) );
        
        return ps_get_current_at_time.executeQuery();
    }

    /**
     * Retrieves all the current suppressions using the RenderRequest object from the portlet to obtain the
     * database connection information
     * 
     * @param renderRequest the request from the portlet that contains the database connection information
     * @return a java.sql.ResultSet of all the currently active suppressions
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static synchronized ResultSet getAllCurrentSuppressions ( RenderRequest renderRequest, int timezone_offset )  throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection ( renderRequest );

        //Timestamp timestamp = new java.sql.Timestamp ( System.currentTimeMillis() - timezone_offset );
        Timestamp timestamp = new java.sql.Timestamp ( System.currentTimeMillis() );
        System.out.println ( "getAllCurrentSuppressions() - timestamp: " + timestamp.getTime() );
        ps_get_all_current.setTimestamp ( 1, timestamp );
        
        return ps_get_all_current.executeQuery();
    }

    /**
     * Retrieves all the current suppressions using the RenderRequest object from the portlet to obtain the
     * database connection information
     * 
     * @return a java.sql.ResultSet of all the currently active suppressions
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static synchronized ResultSet getAllCurrentSuppressions () throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection();

        Timestamp timestamp = new java.sql.Timestamp ( System.currentTimeMillis() );
        System.out.println ( "getAllCurrentSuppressions() - timestamp: " + timestamp.getTime() );
        ps_get_all_current.setTimestamp ( 1, timestamp );
        
        return ps_get_all_current.executeQuery();
    }

    public static synchronized ResultSet getCurrentSuppressions ( String username ) throws ClassNotFoundException, SQLException, IOException
    {
        return getCurrentSuppressions ( username, null );
    }

    public static synchronized ResultSet getCurrentSuppressions ( RenderRequest renderRequest, String username, String sort_by ) throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection ( renderRequest );
     
        Timestamp timestamp = new java.sql.Timestamp ( System.currentTimeMillis() );

        if ( null == sort_by )
        {
            _log.info ( "Null sort by..." );

            ps_get_current.setString ( 1, username );
            ps_get_current.setTimestamp ( 2, timestamp );

            return ps_get_current.executeQuery();
        }
        else
        {
            _log.info ( "Specified sort by..." );

            StringBuilder message = new StringBuilder ( "getCurrentSuppressions(RenderRequest) - Ordering by " );
            message.append ( sort_by );

            _log.debug ( message.toString() );

            StringBuilder query = new StringBuilder ( "SELECT * FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND create_nm=? AND end_utc_tms >= ? ORDER BY " );
            query.append ( sort_by );
            
            PreparedStatement ps = con_suppressions.prepareStatement ( query.toString() );
            ps.setString    ( 1, username );
            ps.setTimestamp ( 2, timestamp );

            _log.info ( "Date for my suppressions: " + (new Date ( timestamp.getTime() ) ).toString() );

            return ps.executeQuery();
        }
    }

    public static ResultSet getAllSuppressionsActiveAtTime ( java.util.Date date )
    {
        return null;
    }

    
    public static ResultSet getCurrentSuppressions ( String username, String sort_by ) throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection();

        if ( null == username )
        {
            return null;
        }
     
        if ( null == sort_by )
        {
            ps_get_current.setString ( 1, username );

            return ps_get_current.executeQuery();
        }
        else
        {
            StringBuilder message = new StringBuilder ( "getCurrentSuppressions() - Ordering by " );
            message.append ( sort_by );

            _log.debug ( message.toString() );

            StringBuilder query = new StringBuilder ( "SELECT * FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND create_nm='" );
            query.append ( username );
            query.append ( "' AND end_utc_tms >= getdate() ORDER BY " );
            query.append ( sort_by );
            
            Statement stmt = con_suppressions.createStatement();
            return stmt.executeQuery( query.toString() );
        }
    }

    public static ResultSet getAllHistoricalSuppressions ( RenderRequest renderRequest ) throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection ( renderRequest );

        return ps_get_all_historical.executeQuery();
    }
    
    public static ResultSet getAllHistoricalSuppressions() throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection();

        return ps_get_all_historical.executeQuery();
    }

    public static ResultSet getHistoricalSuppressionsByHostname ( RenderRequest renderRequest, String hostname ) throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection ( renderRequest );

        ps_get_historical_host.setString ( 1, hostname );
        
        return ps_get_historical_host.executeQuery();
    }
    
    public static ResultSet getHistoricalSuppressionsByHostname ( String hostname ) throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection();

        ps_get_historical_host.setString ( 1, hostname );
        
        return ps_get_historical_host.executeQuery();
    }

    public static ResultSet getHistoricalSuppressions ( RenderRequest renderRequest, String username ) throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection ( renderRequest );
        
        ps_get_historical.setString ( 1, username );
        ResultSet results = ps_get_historical.executeQuery();
        
        return results;
    }

    public static ResultSet getHistoricalSuppressions ( String username ) throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection();
        
        ps_get_historical.setString ( 1, username );
        ResultSet results = ps_get_historical.executeQuery();
        
        return results;
    }

    public static String getDataSourceDatabaseConnectionURL()
    {
        return ds_db_connection_url;
    }
    
    public static String getSuppressionDatabaseConnectionURL()
    {
        return sup_db_connection_url; 
    }

    public static String getSuppressionDatabaseUsername()
    {
        return sup_db_username;
    }

    public static Connection getInstanceDatabaseConnection ( RenderRequest renderRequest ) throws IOException, SQLException, ClassNotFoundException
    {
        checkInstanceDatabaseConnection ( renderRequest );

        _log.info ( "getInstanceDatabaseConnection ( renderRequest ) - Connection URL: " + inst_db_connection_url );

        return con_instance;
    }

    public static Connection getInstanceDatabaseConnection() throws IOException, SQLException, ClassNotFoundException
    {
        checkProperties();
        
        checkInstanceDatabaseConnection ();

        _log.info ( "getInstanceDatabaseConnection () - Connection URL: " + inst_db_connection_url );

        return con_instance;
    }

    public static Connection getSuppressionDatabaseConnection ( RenderRequest renderRequest ) throws IOException, SQLException, ClassNotFoundException
    {
        checkProperties();
        
        checkSuppressionDatabaseConnection ( renderRequest );

        return con_suppressions;
    }
    
    public static Connection getSuppressionDatabaseConnection() throws IOException, SQLException, ClassNotFoundException
    {
        checkProperties();
        
        checkSuppressionDatabaseConnection();
        
        return con_suppressions;
    }

    public static Statement getSuppressionDatabaseStatement ( RenderRequest renderRequest ) throws IOException, SQLException, ClassNotFoundException
    {
        checkSuppressionDatabaseConnection ( renderRequest );

        _log.info ( "getSuppressionDatabaseStatement (renderRequest) - Connection URL: " + sup_db_connection_url );
        
        return con_suppressions.createStatement();
    }

    public static Statement getSuppressionDatabaseStatement() throws IOException, SQLException, ClassNotFoundException
    {
        checkProperties();
        
        checkSuppressionDatabaseConnection();

        _log.info ( "getSuppressionDatabaseStatement () - Connection URL: " + sup_db_connection_url );
        
        return con_suppressions.createStatement();
    }

    public static Connection getDataSourceDatabaseConnection( RenderRequest renderRequest ) throws IOException, SQLException, ClassNotFoundException
    {
        checkDataSourceDatabaseConnection ( renderRequest );
        
        return con_data_source;
    }

    public static Statement getDataSourceDatabaseStatement ( RenderRequest renderRequest ) throws IOException, SQLException, ClassNotFoundException
    {
        if ( null == con_data_source )
        {
            con_data_source = getDataSourceDatabaseConnection( renderRequest );
        }
        else
        {
            _log.info ( "Connection to data source database already initiated" );
        }

        _log.info ( "getDataSourceDatabaseStatement ( renderRequest ) - Connection URL: " + ds_db_connection_url );

        return con_data_source.createStatement();
    }

    public static Connection getDataSourceDatabaseConnection() throws IOException, SQLException, ClassNotFoundException
    {
        checkProperties();
        
        checkDataSourceDatabaseConnection();
        
        return con_data_source;
    }
    
    public static Statement getDataSourceDatabaseStatement() throws IOException, SQLException, ClassNotFoundException
    {
        return getDataSourceDatabaseConnection().createStatement();
    }

    private static String formatDateString ( String year, String month, String date, String hour, String minute, String suffix )
    {
        return formatDateString ( Integer.parseInt ( year ), Integer.parseInt ( month ), Integer.parseInt ( date ), Integer.parseInt ( hour ), Integer.parseInt ( minute ), suffix );
    }

    private static String formatDateString ( int year, int month, int date, int hour, int minute, String suffix )
    {
        StringBuffer buffer = new StringBuffer (); 

        switch ( month )
        {
            case  0: buffer.append ( "Jan " ); break;
            case  1: buffer.append ( "Feb " ); break;
            case  2: buffer.append ( "Mar " ); break;
            case  3: buffer.append ( "Apr " ); break;
            case  4: buffer.append ( "May " ); break;
            case  5: buffer.append ( "Jun " ); break;
            case  6: buffer.append ( "Jul " ); break;
            case  7: buffer.append ( "Aug " ); break;
            case  8: buffer.append ( "Sep " ); break;
            case  9: buffer.append ( "Oct " ); break;
            case 10: buffer.append ( "Nov " ); break;
            case 11: buffer.append ( "Dec " ); break;
        }

        buffer.append ( date );
        buffer.append ( " " );
        buffer.append ( year );
        buffer.append ( " " );
        if ( 0 == hour )
        {
            buffer.append ( "12" );
        }
        else if ( hour < 10 )
        {
            buffer.append ( "0" );
            buffer.append ( hour );
        }
        else
        {
            buffer.append ( hour );            
        }
        buffer.append ( ":" );
        if ( minute >= 10 )
        {
            buffer.append ( minute );
        }
        else
        {
            buffer.append ( "0" );
            buffer.append ( minute );
        }
        buffer.append ( " " );
        buffer.append ( suffix );

        return buffer.toString();
    }
    
    /**
     * Removes all test suppressions
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void removeTestSuppressions() throws ClassNotFoundException, SQLException
    {
        ps_remove_test.executeUpdate();
    }

    /**
     * Deletes the suppression identifeid by the "suppress_id".  For auditing purposes, the actual suppression is not
     * deleted.  Rather, the "deleted_flg" is set to "y" instead.
     *
     * @param suppress_id The ID of the suppression to be deleted
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public static int deleteSuppression ( int suppress_id ) throws SQLException, ClassNotFoundException, IOException
    {
        checkSuppressionDatabaseConnection();
        
        ps_delete_entry.setInt( 1, suppress_id );
        
        return ps_delete_entry.executeUpdate();
    }

    /**
     * Creates and adds a new suppression into the suppression database based on the values in an AddEntry form object
     *
     * @param data_form The AddEntry form object to create the suppression for
     * @return the suppress_id of the newly added suppression
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static int addSuppression ( AddEntry data_form, int ptimezone_offset ) throws IOException, SQLException, ClassNotFoundException
    {
        Calendar calendar_start    = null;
        Calendar calendar_end      = null;
        Statement stmt             = Toolkit.getSuppressionDatabaseStatement();
        ResultSet results          = ps_insert_step1.executeQuery();
        String temp_string         = null;
        long current_time          = System.currentTimeMillis();
        java.sql.Date current_date = new java.sql.Date ( current_time );
         SuppressionRecord suppressionRecord = new SuppressionRecord();

        
        //  Obtain the next suppression ID
        results.next();
        int suppress_id   = results.getInt ( 1 );
        int timezone_offset = 0;

        //  Insert the suppression
        ps_insert_step2.setInt    ( 1, suppress_id );           // suppress_id
        suppressionRecord.setSuppressionID ( suppress_id );
        if ( "on".equals ( data_form.getByNode() ) )
        {
            temp_string = data_form.getNode();
            if (( null == temp_string ) || ( temp_string.length() < 2 ))
            {
                temp_string = "-";
            }
            //ps_insert_step2.setString ( 2, data_form.getNode() );   // node_nm
            ps_insert_step2.setString ( 2, temp_string );
            suppressionRecord.setNodeName ( temp_string );
        }
        else
        {
            ps_insert_step2.setString ( 2, "-" );   // node_nm
            suppressionRecord.setNodeName ( "-" );
        }
        
        if ( data_form.getStartChoice().equals ( "now" ) )
        {
            calendar_start = Calendar.getInstance();
            
            calendar_start.setTime( current_date );
            
            ps_insert_step2.setTimestamp ( 3, new java.sql.Timestamp ( calendar_start.getTimeInMillis() - timezone_offset ) ); // start_utc_tms
        }
        else
        {
            calendar_start = Calendar.getInstance();
            calendar_start.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupStartYear() ) );
            calendar_start.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupStartMonth() ) );
            calendar_start.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupStartDate() ) );
            calendar_start.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupStartHour() ) );
            calendar_start.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupStartMinute() ) );
            calendar_start.set ( Calendar.AM_PM,  (0==data_form.getSupStartAmpm().compareTo ( "PM" ))? Calendar.PM : Calendar.AM );
            
            ps_insert_step2.setTimestamp ( 3, new java.sql.Timestamp ( calendar_start.getTimeInMillis() - ptimezone_offset ) );  // start_utc_tms
        }
        suppressionRecord.setStartTime ( calendar_start );

        if ( data_form.getEndChoice().equals ( "offset" ) )
        {
            int offset   = Integer.parseInt ( data_form.getEndChoiceNum() );
            int unit     = Integer.parseInt ( data_form.getEndChoiceUnit() ); 

            calendar_end = Calendar.getInstance();
            calendar_end.setTime( calendar_start.getTime() );
            calendar_end.add ( Calendar.SECOND, offset*unit );

            _log.info ( "end - offset: " + calendar_end.getTime().toString() );
          
            if ( data_form.getStartChoice().equals ( "later" ) )
            {  
                ps_insert_step2.setTimestamp ( 4, new Timestamp ( calendar_end.getTimeInMillis() - ptimezone_offset ) ); // end_utc_tms
            }
            else
            {
                ps_insert_step2.setTimestamp ( 4, new Timestamp ( calendar_end.getTimeInMillis() ) ); // end_utc_tms
            }
        }
        else
        {
            calendar_end = Calendar.getInstance();
            calendar_end.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupEndYear() ) );
            calendar_end.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupEndMonth() ) );
            calendar_end.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupEndDate() ) );
            calendar_end.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupEndHour() ) );
            calendar_end.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupEndMinute() ) );
            calendar_end.set ( Calendar.AM_PM,  (0==data_form.getSupEndAmpm().compareTo ( "PM" ))? Calendar.PM : Calendar.AM );

            _log.info ( "end - specified: " + calendar_end.getTime().toString() );
            
            ps_insert_step2.setTimestamp ( 4, new Timestamp ( calendar_end.getTimeInMillis() - ptimezone_offset ) );  // end_utc_tms
        }
        suppressionRecord.setEndTime ( calendar_end );

        //*
        ps_insert_step2.setString (  5, "n" );                                                     // notify_flg
        ps_insert_step2.setString (  6, returnWildCardIfNull ( data_form.getDescription() ) );     // suppress_desc_txt
        ps_insert_step2.setString (  7, "n" );                                                     // deleted_flg
        ps_insert_step2.setString (  8, data_form.getUsername() );                                 // create_nm
        ps_insert_step2.setLong   (  9, Integer.parseInt ( data_form.getNumMinutesPrior() ) );     // notify_minutes

        suppressionRecord.setNotificationFlag    ( false );
        suppressionRecord.setDescription         ( returnWildCardIfNull ( data_form.getDescription() ) );
        suppressionRecord.setDeletedFlag         ( false );
        suppressionRecord.setCreatorName         ( data_form.getUsername() );
        suppressionRecord.setNotificationMinutes ( Integer.parseInt ( data_form.getNumMinutesPrior() ) );

        if ( "on".equals ( data_form.getWithDbServer() ) )
        {
            ps_insert_step2.setString ( 10, data_form.getDbServer() );                              // instance
            suppressionRecord.setDatabaseInstance ( data_form.getDbServer() );
        }
        else
        {
            ps_insert_step2.setString ( 10, "-" );                                                  // instance
            suppressionRecord.setDatabaseInstance ( "-" );
        }

        if ( "on".equals ( data_form.getByApplication() ) )
        {
            ps_insert_step2.setString ( 11, data_form.getApplication() );                           // group_nm
            suppressionRecord.setGroupName ( data_form.getApplication() );                          // group_nm
        }
        else
        {
            ps_insert_step2.setString ( 11, "-" );                                                  // group_nm
            suppressionRecord.setGroupName ( "-" );                                                 // group_nm
        }
        ps_insert_step2.setString ( 12, returnWildCardIfNull ( data_form.getDbServerMsg() ) );      // message
        suppressionRecord.setMessage (  returnWildCardIfNull ( data_form.getDbServerMsg() ) );      // message
        
        temp_string = data_form.getRemoveOnReboot();
        int reboot  = ((null != temp_string) && (temp_string.equals( "on" )))? 1 : 0;
        
        ps_insert_step2.setInt ( 13, reboot );
        suppressionRecord.setRemoveOnReboot ( (1 == reboot)? true : false );
        
        if ( false == is_production_library )
        {
            ps_insert_step2.setInt    ( 13, reboot );                                                  // remove_on_reboot
        }

        ps_insert_step2.executeUpdate();

        // Clean up adding a suppression
        ps_insert_step3.executeUpdate();
        
        stmt.close();

        //  Chcek to see if there is a notification email requested
        String strNumMinutesPrior = data_form.getNumMinutesPrior();
        try
        {
            int NumMinutesPrior = Integer.parseInt ( strNumMinutesPrior );

            ExpirationNotification notification = new ExpirationNotification();
            notification.setSuppressionID ( suppress_id );
            notification.setNotificationEmails ( data_form.getEmail() );

            Calendar notificationTime = Calendar.getInstance();
            notificationTime.setTimeInMillis ( calendar_end.getTimeInMillis() ); //+ ptimezone_offset );
            notification.setNotificationTime ( notificationTime );

            notificationTime.add ( Calendar.MINUTE, -NumMinutesPrior );

            hibernateFacade.insertOrUpdateNotification ( notification );

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Successfully added email notification for ( SuppressID=" );
                message.get().append ( suppress_id );
                message.get().append ( ", Emails=" );
                message.get().append ( data_form.getEmail() );
                message.get().append ( ", TimeOffset=" );
                message.get().append ( ptimezone_offset );
                message.get().append ( ", NotificationTime=" );
                message.get().append ( HibernateFacade.sdf.format ( notificationTime.getTime() ) );
                message.get().append ( " )" );

                _log.info ( message.get().toString() );
            }
        }
        catch ( Exception exception )
        {
            _log.error ( exception );
        }

        try
        {
            boolean result = hibernateFacade.insertOrUpdateSuppressionRecord ( suppressionRecord );

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Successfully added suppression for ( RowID=" );
                message.get().append ( suppressionRecord.getRowID() );
                message.get().append ( ", SuppressID=" );
                message.get().append ( suppress_id );
                message.get().append ( " ) - " );
                message.get().append ( suppressionRecord.getSuppressionID() );

                _log.info ( message.get().toString() );
            }
        }
        catch ( Exception exception )
        {
            _log.error ( exception );
        }
        
        return suppress_id;
    }

    private static String returnWildCardIfNull ( String value )
    {
        if (( null == value ) || ( value.length() < 1 ))
        {
            return "-";
        }
        else
        {
            return value;
        }
    }

    /**
     * Creates all the java.sql.PreparedStatement objects to execute against the suppressions database
     * 
     * @throws SQLException
     */
    private static void createSuppressionPreparedStatements () throws SQLException
    {
        ps_insert_step1         = con_suppressions.prepareStatement ( "SELECT isnull(max(suppress_id)+1, 1) from t_vpo_suppress" );
        if ( is_production_library )
        {
            ps_insert_step2     = con_suppressions.prepareStatement ( "INSERT INTO t_vpo_suppress ( suppress_id, node_nm, start_utc_tms, end_utc_tms, notify_flg, suppress_desc_txt, deleted_flg, create_nm, notify_minutes, create_tms, instance, group_nm, message ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, getdate(), ?, ?, ?  )" );
        }
        else
        {
            ps_insert_step2     = con_suppressions.prepareStatement ( "INSERT INTO t_vpo_suppress ( suppress_id, node_nm, start_utc_tms, end_utc_tms, notify_flg, suppress_desc_txt, deleted_flg, create_nm, notify_minutes, create_tms, instance, group_nm, message, remove_on_reboot ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, getdate(), ?, ?, ?, ?  )" );
        }
        //ps_insert_step2         = con_suppressions.prepareStatement ( "INSERT INTO t_vpo_suppress ( suppress_id, node_nm, start_utc_tms, end_utc_tms, notify_flg, suppress_desc_txt, deleted_flg, create_nm, notify_minutes, create_tms, instance, group_nm, message ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, getdate(), ?, ?, ?  )" );
        ps_insert_step3         = con_suppressions.prepareStatement ( "if exists ( select 1 from t_vpo_last_update where process_nm='data_change') update t_vpo_last_update set last_update_tms = getdate() where process_nm = 'data_change' else insert into t_vpo_last_update ( process_nm, last_update_tms ) values ('data_change',getdate())" );
        ps_remove_test          = con_suppressions.prepareStatement ( "DELETE FROM t_vpo_suppress WHERE create_nm='somebody' OR node_nm='test_node'" );
        ps_delete_entry         = con_suppressions.prepareStatement ( "UPDATE t_vpo_suppress SET deleted_flg='y' WHERE suppress_id=?" );
        
        if ( is_production_library )
        {
            ps_get_current                 = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND create_nm=? AND end_utc_tms >= getdate() ORDER BY suppress_id DESC" );
            ps_get_current_hostname        = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND node_nm=? AND end_utc_tms >= getdate() ORDER BY suppress_id DESC" );
            ps_get_deleted                 = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where deleted_flg = 'y'" );
            ps_get_deleted_hostname        = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where deleted_flg = 'y' AND node_nm=?" );
            ps_get_deleted_username        = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where deleted_flg = 'y' AND create_nm=?" );
            ps_get_all_current             = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND end_utc_tms >= ? ORDER BY suppress_id DESC" );
            ps_get_historical              = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND create_nm=? ORDER BY suppress_id DESC" );
            ps_get_historical_host         = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND node_nm=? ORDER BY suppress_id DESC" );
            ps_get_all_historical          = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) ORDER BY suppress_id DESC" );
            ps_get_entry                   = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where suppress_id=?" );
            ps_update_entry                = con_suppressions.prepareStatement ( "UPDATE t_vpo_suppress SET group_nm=?, node_nm=?, start_utc_tms=convert(DATETIME, ?), end_utc_tms=convert(DATETIME, ?), suppress_desc_txt=?, notify_minutes=?, instance=?, message=? WHERE suppress_id=?" );
        }
        else
        {
            ps_get_current                 = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, remove_on_reboot FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND create_nm=? AND end_utc_tms >= ? ORDER BY suppress_id DESC" );
            //ps_get_current                 = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, remove_on_reboot FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND create_nm=? AND end_utc_tms >= getdate() ORDER BY suppress_id DESC" );
            ps_get_current_hostname        = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND node_nm=? AND end_utc_tms >= getdate() ORDER BY suppress_id DESC" );
            ps_get_deleted                 = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, remove_on_reboot FROM t_vpo_suppress where deleted_flg = 'y'" );
            ps_get_deleted_hostname        = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, remove_on_reboot FROM t_vpo_suppress where deleted_flg = 'y' AND node_nm=?" );
            ps_get_deleted_username        = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, remove_on_reboot FROM t_vpo_suppress where deleted_flg = 'y' AND create_nm=?" );
            ps_get_all_current             = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, remove_on_reboot FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND end_utc_tms >= ? ORDER BY suppress_id DESC" );
            ps_get_current_at_time         = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, remove_on_reboot FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND end_utc_tms >= ? ORDER BY suppress_id, start_utc_tms" );
            ps_get_historical              = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, remove_on_reboot FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND create_nm=? ORDER BY suppress_id DESC"  );
            ps_get_historical_host         = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, remove_on_reboot FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) AND node_nm=? ORDER BY suppress_id DESC" );
            ps_get_all_historical          = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, remove_on_reboot FROM t_vpo_suppress where ( deleted_flg != 'y' or deleted_flg is null ) ORDER BY suppress_id DESC" );
            ps_get_entry                   = con_suppressions.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, remove_on_reboot FROM t_vpo_suppress where suppress_id=?" );
            ps_update_entry                = con_suppressions.prepareStatement ( "UPDATE t_vpo_suppress SET group_nm=?, node_nm=?, start_utc_tms=convert(DATETIME, ?), end_utc_tms=convert(DATETIME, ?), suppress_desc_txt=?, notify_minutes=?, instance=?, message=?, remove_on_reboot=? WHERE suppress_id=?" );
        }
        ps_dump_entry           = con_suppressions.prepareStatement ( "SELECT * FROM t_vpo_suppress where suppress_id=?" );
    }

    private static void checkInstanceDatabaseConnection ( RenderRequest renderRequest ) throws ClassNotFoundException, SQLException, IOException
    {
        if ( null == con_instance )
        {
            PortletPreferences prefs                = renderRequest.getPreferences();

            String instance_database_type           = prefs.getValue ( "inst_database_type",     null );
            String instance_database_server         = prefs.getValue ( "inst_database_server",   null );
            String instance_database_name           = prefs.getValue ( "inst_database_name",     null );
            String instance_database_port           = prefs.getValue ( "inst_database_port",     null );
            
            inst_db_driver_name                     = getDatabaseDriverClassName ( instance_database_type );
            inst_db_connection_url                  = createConnectionURL ( instance_database_type, instance_database_server, instance_database_name, instance_database_port );
            inst_db_username                        = prefs.getValue ( "inst_database_username", null );
            inst_db_password                        = prefs.getValue ( "inst_database_password", null );
            
            instance_database_type                  = prefs.getValue ( "inst_backup_database_type",     null );
            instance_database_server                = prefs.getValue ( "inst_backup_database_server",   null );
            instance_database_name                  = prefs.getValue ( "inst_backup_database_name",     null );
            instance_database_port                  = prefs.getValue ( "inst_backup_database_port",     null );
            
            inst_backup_db_driver_name              = getDatabaseDriverClassName ( instance_database_type );
            inst_backup_db_connection_url           = createConnectionURL ( instance_database_type, instance_database_server, instance_database_name, instance_database_port );
            inst_backup_db_username                 = prefs.getValue ( "inst_backup_database_username", null );
            inst_backup_db_password                 = prefs.getValue ( "inst_backup_database_password", null );
            
            checkInstanceDatabaseConnection();
            //createInstancePreparedStatements();
        }
        else
        {
            boolean get_connection = false;
            try
            {
                if (( null != con_instance ) && ( con_instance.isClosed() ))
                {
                    con_instance   = null;
                    get_connection = true;
                }
            }
            catch ( SQLException exception )
            {
                con_instance   = null;
                get_connection = true;
            }

            if ( true == get_connection )
            {
                checkInstanceDatabaseConnection ( renderRequest );
            }
        }
    }
    
    private static void checkDataSourceDatabaseConnection ( RenderRequest renderRequest ) throws ClassNotFoundException, SQLException, IOException
    {
        if (( null == con_data_source ) && ( null != renderRequest ))
        {
            PortletPreferences prefs    = renderRequest.getPreferences();

            String ds_database_type     = prefs.getValue ( "vpo_database_type",     null );
            String ds_database_server   = prefs.getValue ( "vpo_database_server",   null );
            String ds_database_name     = prefs.getValue ( "vpo_database_name",     null );
            String ds_database_port     = prefs.getValue ( "vpo_database_port",     null );
            
            ds_db_driver_name           = getDatabaseDriverClassName ( ds_database_type );
            ds_db_connection_url        = createConnectionURL ( ds_database_type, ds_database_server, ds_database_name, ds_database_port );
            ds_db_username              = prefs.getValue ( "vpo_database_username", null );
            ds_db_password              = prefs.getValue ( "vpo_database_password", null );
            
            ds_database_type            = prefs.getValue ( "vpo_backup_database_type",     null );
            ds_database_server          = prefs.getValue ( "vpo_backup_database_server",   null );
            ds_database_name            = prefs.getValue ( "vpo_backup_database_name",     null );
            ds_database_port            = prefs.getValue ( "vpo_backup_database_port",     null );
            
            ds_backup_db_driver_name    = getDatabaseDriverClassName ( ds_database_type );
            ds_backup_db_connection_url = createConnectionURL ( ds_database_type, ds_database_server, ds_database_name, ds_database_port );
            ds_backup_db_username       = prefs.getValue ( "vpo_backup_database_username", null );
            ds_backup_db_password       = prefs.getValue ( "vpo_backup_database_password", null );
            
            checkDataSourceDatabaseConnection();
            //createInstancePreparedStatements();
        }
        else
        {
            boolean get_connection = false;
            try
            {
                if (( null != con_data_source ) && ( con_data_source.isClosed() ))
                {
                    con_data_source   = null;
                    get_connection = true;
                }
            }
            catch ( SQLException exception )
            {
                con_data_source   = null;
                get_connection = true;
            }

            if ( true == get_connection )
            {
                checkDataSourceDatabaseConnection ( renderRequest );
            }
        }
    }

    private static void checkSuppressionDatabaseConnection ( RenderRequest renderRequest ) throws ClassNotFoundException, SQLException, IOException
    {
        if ( null == con_suppressions )
        {
            PortletPreferences prefs                   = renderRequest.getPreferences();

            String suppression_database_type           = prefs.getValue ( "suppression_database_type",     null );
            String suppression_database_server         = prefs.getValue ( "suppression_database_server",   null );
            String suppression_database_name           = prefs.getValue ( "suppression_database_name",     null );
            String suppression_database_port           = prefs.getValue ( "suppression_database_port",     null );

            sup_db_driver_name                         = getDatabaseDriverClassName ( suppression_database_type );
            sup_db_connection_url                      = createConnectionURL ( suppression_database_type, suppression_database_server, suppression_database_name, suppression_database_port );
            sup_db_username                            = prefs.getValue ( "suppression_database_username", null );
            sup_db_password                            = prefs.getValue ( "suppression_database_password", null );

            suppression_database_type                  = prefs.getValue ( "suppression_backup_database_type",     null );
            suppression_database_server                = prefs.getValue ( "suppression_backup_database_server",   null );
            suppression_database_name                  = prefs.getValue ( "suppression_backup_database_name",     null );
            suppression_database_port                  = prefs.getValue ( "suppression_backup_database_port",     null );
            
            sup_backup_db_driver_name                  = getDatabaseDriverClassName ( suppression_database_type );
            sup_backup_db_connection_url               = createConnectionURL ( suppression_database_type, suppression_database_server, suppression_database_name, suppression_database_port );
            sup_backup_db_username                     = prefs.getValue ( "suppression_database_username", null );
            sup_backup_db_password                     = prefs.getValue ( "suppression_databaske_password", null );
            
            WeakReference <StringBuilder> message      = new WeakReference <StringBuilder> ( new StringBuilder ( suppression_database_type ) );
            message.get().append ( " / " );
            message.get().append ( sup_backup_db_driver_name );
          
            _log.error ( message.get().toString() ); 

            message = null;

            checkSuppressionDatabaseConnection();
            createSuppressionPreparedStatements();
        }
        else
        {
            boolean get_connection = false;
            try
            {
                if (( null != con_suppressions ) && ( con_suppressions.isClosed() ))
                {
                    con_suppressions = null;
                    get_connection   = true;
                }
            }
            catch ( SQLException exception )
            {
                con_suppressions = null;
                get_connection   = true;
            }

            if ( true == get_connection )
            {
                checkSuppressionDatabaseConnection ( renderRequest );
            }
        }
    }


    public static void refreshDatabaseConnections ( RenderRequest renderRequest ) throws SQLException, ClassNotFoundException, IOException
    {
        con_suppressions = null;
        checkSuppressionDatabaseConnection ( renderRequest );
        
        con_data_source = null;
        checkDataSourceDatabaseConnection ( renderRequest );
        
        con_instance = null;
        checkInstanceDatabaseConnection ( renderRequest );

        PortletPreferences prefs  = renderRequest.getPreferences();

        extract_file_suppressions = null;
        extract_file_vpo          = null;
        extract_file_inst         = null;
    }

    private static void checkInstanceDatabaseConnection() throws ClassNotFoundException, SQLException, IOException
    {
        if (( null == con_instance ) || ( database_mode_inst != DATABASE_MODE_PRIMARY ))
        {
            try
            {
                Class.forName ( inst_db_driver_name );
                con_instance = DriverManager.getConnection ( inst_db_connection_url, inst_db_username, inst_db_password );
                
                database_mode_inst = DATABASE_MODE_PRIMARY;
            }
            catch ( ClassNotFoundException exception )
            {
                _log.error ( "checkInstanceDatabaseConnection() - could not connect to the primary instance database: URL=" + inst_db_connection_url );
                con_instance   = null;
                database_mode_inst = DATABASE_MODE_INVALID;
                
                exception.printStackTrace();
            }
            catch ( SQLException exception )
            {
                _log.error ( "checkInstanceDatabaseConnection() - could not connect to the primary instance database: URL=" + inst_db_connection_url );
                con_instance   = null;
                database_mode_inst = DATABASE_MODE_INVALID;
                
                exception.printStackTrace();
            }
            
            if ( null == con_instance )
            {
                try
                {
                    Class.forName ( inst_db_driver_name );
                    con_instance = DriverManager.getConnection ( inst_backup_db_connection_url, inst_backup_db_username, inst_backup_db_password );
                    database_mode_inst = DATABASE_MODE_BACKUP;
                }
                catch ( ClassNotFoundException exception )
                {
                    _log.error ( "checkInstanceDatabaseConnection() - could not connect to the BCP instance database: URL=" + inst_backup_db_connection_url );
                    con_instance   = null;
                    database_mode_inst = DATABASE_MODE_INVALID;
                    
                    exception.printStackTrace();
                }
                catch ( SQLException exception )
                {
                    _log.error ( "checkInstanceDatabaseConnection() - could not connect to the BCP instance database: URL=" + inst_backup_db_connection_url );
                    con_instance   = null;
                    database_mode_inst = DATABASE_MODE_INVALID;
                    
                    exception.printStackTrace();
                }
            }

            if ( null == con_instance )
            {
                _log.fatal ( "Could not connect to any instance database instance" );
                database_mode_inst = DATABASE_MODE_INVALID;
                throw new SQLException ( "Could not connect to any instance database instance" );
            }
        }
        else
        {
            boolean get_connection = false;
            try
            {
                if (( null != con_instance ) && ( con_instance.isClosed() ))
                {
                    con_instance   = null;
                    get_connection = true;
                }
            }
            catch ( SQLException exception )
            {
                con_instance   = null;
                get_connection = true;
            }

            if ( true == get_connection )
            {
                checkInstanceDatabaseConnection ();
            }
        }
    }
    
    private static void checkDataSourceDatabaseConnection() throws ClassNotFoundException, SQLException, IOException
    {
        //  If we are not using a connection to the primary database, then reset the connection and attempt to connect
        //  to the primary database
        if (( null == con_data_source ) || ( database_mode_vpo != DATABASE_MODE_PRIMARY ))
        {
            StringBuilder message = new StringBuilder();
            if ( null == con_data_source )
            {
                message.append ( "Creating new data source connection for first time...<br>\n" );
            }
            else
            {
                message.append ( "Chceking for valid primary data source connection...<br>\n" );
            }
            
            try
            {
                message.append ( "Trying primary connection - " );
                message.append ( ds_db_connection_url );
                message.append ( " ( " );
                message.append ( ds_db_username );
                message.append ( ", " );
                message.append ( ds_db_password );
                message.append ( " ), " );
                
                Class.forName ( ds_db_driver_name );
                con_data_source   = DriverManager.getConnection ( ds_db_connection_url, ds_db_username, ds_db_password );
                
                
                database_mode_vpo = DATABASE_MODE_PRIMARY;
            }
            catch ( ClassNotFoundException exception )
            {
                _log.error ( "checkDataSourceDatabaseConnection() - could not connect to the primary data source database: URL=" + ds_db_connection_url );
                con_data_source   = null;
                database_mode_vpo = DATABASE_MODE_INVALID;
                
                exception.printStackTrace();
            }
            catch ( SQLException exception )
            {
                _log.error ( "checkDataSourceDatabaseConnection() - could not connect to the primary data source database: URL=" + ds_db_connection_url );
                con_data_source   = null;
                database_mode_vpo = DATABASE_MODE_INVALID;
                
                exception.printStackTrace();
            }
            
            if ( null == con_data_source )
            {
                try
                {
                    message.append ( "Trying backup connection..." );
                    message.append ( ds_backup_db_connection_url );
                    message.append ( " ( " );
                    message.append ( ds_backup_db_username );
                    message.append ( ", " );
                    message.append ( ds_backup_db_password );
                    message.append ( " )" );

                    Class.forName ( ds_backup_db_driver_name );
                    con_data_source = DriverManager.getConnection ( ds_backup_db_connection_url, ds_backup_db_username, ds_backup_db_password );
                    database_mode_vpo = DATABASE_MODE_BACKUP;
                }
                catch ( ClassNotFoundException exception )
                {
                    _log.error ( "checkDataSourceDatabaseConnection() - could not connect to the BCP data source database: URL=" + ds_backup_db_connection_url );
                    con_data_source   = null;
                    database_mode_vpo = DATABASE_MODE_INVALID;
                    
                    exception.printStackTrace();
                }
                catch ( SQLException exception )
                {
                    _log.error ( "checkDataSourceDatabaseConnection() - could not connect to the BCP data source database: URL=" + ds_backup_db_connection_url );
                    con_data_source   = null;
                    database_mode_vpo = DATABASE_MODE_INVALID;
                    
                    exception.printStackTrace();
                }
            }

            if ( null == con_data_source )
            {
                _log.fatal ( "Could not connect to any data source database instance" );
                database_mode_vpo = DATABASE_MODE_INVALID;
                
                message.append ( "Could not connect to any data source database instance" );
                message.append ( ")\nMain: " );
                message.append ( ds_db_connection_url );
                message.append ( " ( " );
                message.append ( ds_db_username );
                message.append ( ", " );
                message.append ( ds_db_password );
                message.append ( " ), backup: " );
                message.append ( ds_backup_db_connection_url );
                message.append ( " ( " );
                message.append ( ds_backup_db_username );
                message.append ( ", " );
                message.append ( ds_backup_db_password );
                message.append ( " )" );
                //*/
                
                throw new SQLException ( message.toString() );
            }
        }
        else
        {
            boolean get_connection = false;
            try
            {
                if (( null != con_data_source ) && ( con_data_source.isClosed() ))
                {
                    con_data_source   = null;
                    get_connection = true;
                }
            }
            catch ( SQLException exception )
            {
                con_data_source   = null;
                get_connection = true;
            }

            if ( true == get_connection )
            {
                checkDataSourceDatabaseConnection ();
            }
        }
    }

    private static void checkSuppressionDatabaseConnection() throws ClassNotFoundException, SQLException, IOException
    {
        //  If we are not using a connection to the primary database, then reset the connection and attempt to connect
        //  to the primary database
        if (( null == con_suppressions ) || ( database_mode_suppressions != DATABASE_MODE_PRIMARY ))
        {
            try
            {
                checkProperties();

                Class.forName ( sup_db_driver_name );
                con_suppressions   = DriverManager.getConnection ( sup_db_connection_url, sup_db_username, sup_db_password );
                database_mode_suppressions = DATABASE_MODE_PRIMARY;
            }
            catch ( ClassNotFoundException exception )
            {
                _log.error ( "checkSuppressionDatabaseConnection() - could not connect to the primary suppressions database: URL=" + sup_db_connection_url );
                con_suppressions   = null;
                database_mode_suppressions = DATABASE_MODE_INVALID;
                
                exception.printStackTrace();
            }
            catch ( SQLException exception )
            {
                _log.error ( "checkSuppressionDatabaseConnection() - could not connect to the primary suppressions database: URL=" + sup_db_connection_url );
                con_suppressions   = null;
                database_mode_suppressions = DATABASE_MODE_INVALID;
                
                exception.printStackTrace();
            }
            
            if ( null == con_suppressions )
            {
                try
                {
                    Class.forName ( sup_backup_db_driver_name );
                    con_suppressions   = DriverManager.getConnection ( sup_backup_db_connection_url, sup_backup_db_username, sup_backup_db_password );
                    database_mode_suppressions = DATABASE_MODE_BACKUP;
                }
                catch ( ClassNotFoundException exception )
                {
                    _log.error ( "checkSuppressionDatabaseConnection() - could not connect to the BCP suppressions database: URL=" + sup_db_connection_url );
                    con_suppressions   = null;
                    database_mode_suppressions = DATABASE_MODE_INVALID;
                    
                    exception.printStackTrace();
                }
                catch ( SQLException exception )
                {
                    _log.error ( "checkSuppressionDatabaseConnection() - could not connect to the BCP suppressions database: URL=" + sup_db_connection_url );
                    con_suppressions   = null;
                    database_mode_suppressions = DATABASE_MODE_INVALID;
                    
                    exception.printStackTrace();
                }
            }
            
            if ( null != con_suppressions )
            {
                createSuppressionPreparedStatements();
            }
            else
            {
                _log.fatal ( "Could not connect to any suppressions database instance" );
                database_mode_suppressions = DATABASE_MODE_INVALID;
                throw new SQLException ( "Could not connect to any suppressions database instance" );
            }
        }
        else
        {
            boolean get_connection = false;
            try
            {
                if (( null != con_suppressions ) && ( con_suppressions.isClosed() ))
                {
                    con_suppressions = null;
                    get_connection   = true;
                }
            }
            catch ( SQLException exception )
            {
                con_suppressions = null;
                get_connection   = true;
            }

            if ( true == get_connection )
            {
                checkSuppressionDatabaseConnection ();
            }
        }
    }
    
    public static ResultSet getCurrentSuppressionsByHostname ( String hostname ) throws SQLException, ClassNotFoundException, IOException
    {
        checkSuppressionDatabaseConnection();
        
        ps_get_current_hostname.setString ( 1, hostname );

        return ps_get_current_hostname.executeQuery();
    }
    
    /**
     * Retrieves all the deleted suppressions in the database
     * 
     * @return a ResultSet object containing all the deleted suppressions in the database
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ResultSet getDeletedSuppressions() throws SQLException, ClassNotFoundException, IOException
    {
        checkSuppressionDatabaseConnection();
        
        return ps_get_deleted.executeQuery();
    }
    
    /**
     * Retrieves all the deleted suppressions in the database that affected the specified hostname
     * 
     * @param hostname The hostname to search by
     * @return a ResultSet object containing all the deleted suppressions in the database
     * @throws SQLException
     */
    public static ResultSet getDeletedSuppressionsByHostname ( String hostname ) throws SQLException
    {
        ps_get_deleted_hostname.setString( 1, hostname );
        
        return ps_get_deleted_hostname.executeQuery();
    }
    
    /**
     * Retrieves all the deleted suppressions in the database created by the specified username
     * 
     * @param username The username to search by
     * @return a ResultSet object containing all the deleted suppressions in the database
     * @throws SQLException
     */
    public static ResultSet getDeletedSuppressionsByUser ( String username ) throws SQLException
    {
        ps_get_deleted_username.setString ( 1, username );
        
        return ps_get_deleted_username.executeQuery();
    }
    
    /**
     * Updates the values of a suppression from an AddEntry form object
     * 
     * @param data_form the AddEntry form object that contains the new values for the suppression
     * @throws SQLException
     */
    public static int updateEntry ( AddEntry data_form ) throws SQLException
    {
        String suppress_id    = data_form.getSuppressId();
        int num_minutes_prior = 0;

        Calendar calendar_start = Calendar.getInstance();
        Calendar calendar_end   = Calendar.getInstance();
        if ( data_form.getStartChoice().equals ( "now" ) )
        {
            calendar_start = Calendar.getInstance();
            
            calendar_start.setTime ( new Date() );
        }
        else
        {
            calendar_start = Calendar.getInstance();
            calendar_start.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupStartYear() ) );
            calendar_start.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupStartMonth() ) );
            calendar_start.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupStartDate() ) );
            calendar_start.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupStartHour() ) );
            calendar_start.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupStartMinute() ) );
            calendar_start.set ( Calendar.AM_PM,  (0==data_form.getSupStartAmpm().compareTo ( "PM" ))? Calendar.PM : Calendar.AM );
        }

        if ( data_form.getEndChoice().equals ( "offset" ) )
        {
            int offset   = Integer.parseInt ( data_form.getEndChoiceNum() );
            int unit     = Integer.parseInt ( data_form.getEndChoiceUnit() ); 
            calendar_end = Calendar.getInstance();
            calendar_end.setTime( calendar_start.getTime() );
            calendar_end.add ( Calendar.SECOND, offset*unit );
        }
        else
        {
            calendar_end = Calendar.getInstance();
            calendar_end.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupEndYear() ) );
            calendar_end.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupEndMonth() ) );
            calendar_end.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupEndDate() ) );
            calendar_end.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupEndHour() ) );
            calendar_end.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupEndMinute() ) );
            calendar_end.set ( Calendar.AM_PM,  (0==data_form.getSupEndAmpm().compareTo ( "PM" ))? Calendar.PM : Calendar.AM );
        } 

        
        ps_update_entry.setString (  1, data_form.getApplication() );   // group_nm
        ps_update_entry.setString (  2, data_form.getNode() );          // node_nm
        ps_update_entry.setTimestamp ( 3, new java.sql.Timestamp ( calendar_start.getTimeInMillis() ) );  // start_utc_tms
        ps_update_entry.setTimestamp ( 4, new java.sql.Timestamp ( calendar_end.getTimeInMillis() ) );  // start_utc_tms
        ps_update_entry.setString (  5, data_form.getDescription() );   // suppress_desc_txt
        try
        {
            num_minutes_prior = Integer.parseInt ( data_form.getNumMinutesPrior() );
        }
        catch ( NumberFormatException exception )
        {
            num_minutes_prior = Integer.MIN_VALUE;
        }
        ps_update_entry.setInt    (  6, num_minutes_prior );             // notify_minutes
        ps_update_entry.setString (  7, data_form.getDbServer() );       // instance
        ps_update_entry.setString (  8, data_form.getDbServerMsg() );    // message
        String temp_string = data_form.getRemoveOnReboot();
        if ( false == is_production_library )
        {
            int reboot = (( null != temp_string ) && ( temp_string.equals ( "on" ) ) )? 1 : 0;
            ps_update_entry.setInt    (  9, reboot );                        // remove_on_reboot
        
            ps_update_entry.setInt    ( 10, Integer.parseInt ( suppress_id ) );
        }
        else
        {
            ps_update_entry.setInt    ( 9, Integer.parseInt ( suppress_id ) );
        }
        
        try
        {
            SuppressionRecord suppressionRecord = hibernateFacade.selectSuppressionRecord ( Long.parseLong ( suppress_id ) );
            suppressionRecord.setGroupName   ( data_form.getApplication() );
            suppressionRecord.setNodeName    ( data_form.getNode()        );
            suppressionRecord.setStartTime   ( calendar_start             );
            suppressionRecord.setEndTime     ( calendar_end               );
            suppressionRecord.setDescription ( data_form.getDescription() );
            suppressionRecord.setNotificationMinutes ( num_minutes_prior  );
            suppressionRecord.setDatabaseInstance    ( data_form.getDbServer() );
            suppressionRecord.setMessage             ( data_form.getDbServerMsg() );
            temp_string = data_form.getRemoveOnReboot();
            suppressionRecord.setRemoveOnReboot      ( ( null != temp_string ) && ( temp_string.equals ( "on" ) ) );

            hibernateFacade.insertOrUpdateSuppressionRecord ( suppressionRecord );

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Successfully edited Suppression ( SuppressionID=" );
                message.get().append ( suppress_id );
                message.get().append ( " )" );

                _log.info ( message.get().toString() );
            }
        }
        catch ( Exception exception )
        {
            _log.error ( exception );
        }
        
        return ps_update_entry.executeUpdate();
    }

    public static int updateEntry ( HttpServletRequest request, EditEntry data_form ) throws SQLException
    {
        TimeZone time_zone         = TimeZone.getDefault();
        //Cookie cookie              = Toolkit.getTimeZoneCookie ( request );
        int timezone_offset        = 0;
        try
        {
            timezone_offset = Toolkit.computeTimeZoneOffset ( request );
        }
        catch ( NullPointerException exception )
        {
            timezone_offset = Integer.parseInt ( data_form.getTimeZoneOffset() );
        }

        long current_time          = System.currentTimeMillis();
        java.sql.Date current_date = new java.sql.Date ( current_time );
        time_zone.setRawOffset ( -timezone_offset );
        
        String suppress_id    = data_form.getSuppressId();
        if ( null == suppress_id )
        {
            _log.error ( "updateEntry() - null suppress ID detected" );
        }

        int num_minutes_prior = 0;

        Calendar calendar_start = Calendar.getInstance();
        calendar_start.setTime ( getSupStartDate ( data_form ) );

        Calendar calendar_end = Calendar.getInstance();
        calendar_end.setTime ( getSupEndDate ( data_form ) );

        if ( data_form.getStartChoice().equals ( "now" ) )
        {
            calendar_start = Calendar.getInstance();
            
            calendar_start.setTime( new Date ( current_time + timezone_offset ) );
        }
        else
        {
            calendar_start = Calendar.getInstance();
            calendar_start.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupStartYear() ) );
            calendar_start.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupStartMonth() ) );
            calendar_start.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupStartDate() ) );
            calendar_start.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupStartHour() ) );
            calendar_start.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupStartMinute() ) );
            calendar_start.set ( Calendar.AM_PM,  (0==data_form.getSupStartAmpm().compareTo ( "PM" ))? Calendar.PM : Calendar.AM );
            
            //  ps_insert_step2.setTimestamp ( 3, new java.sql.Timestamp ( calendar_start.getTimeInMillis() - ptimezone_offset ) );  // start_utc_tms
            calendar_start.setTimeInMillis ( calendar_start.getTimeInMillis() );
        }

        if ( data_form.getEndChoice().equals ( "offset" ) )
        {
            int offset   = Integer.parseInt ( data_form.getEndChoiceNum() );
            int unit     = Integer.parseInt ( data_form.getEndChoiceUnit() ); 

            calendar_end = Calendar.getInstance();
            calendar_end.setTime( calendar_start.getTime() );
            calendar_end.add ( Calendar.SECOND, offset*unit );
          
            if ( data_form.getStartChoice().equals ( "later" ) )
            { 
                //ps_insert_step2.setTimestamp ( 4, new Timestamp ( calendar_end.getTimeInMillis() - ptimezone_offset ) ); // end_utc_tms
            }
            else
            {
                //ps_insert_step2.setTimestamp ( 4, new Timestamp ( calendar_end.getTimeInMillis() ) ); // end_utc_tms
            }
        }
        else
        {
            calendar_end = Calendar.getInstance();
            calendar_end.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupEndYear() ) );
            calendar_end.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupEndMonth() ) );
            calendar_end.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupEndDate() ) );
            calendar_end.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupEndHour() ) );
            calendar_end.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupEndMinute() ) );
            calendar_end.set ( Calendar.AM_PM,  (0==data_form.getSupEndAmpm().compareTo ( "PM" ))? Calendar.PM : Calendar.AM );
        }
        
        ps_update_entry.setString (  1, data_form.getApplication() );   // group_nm
        ps_update_entry.setString (  2, data_form.getNode() );          // node_nm
        ps_update_entry.setTimestamp ( 3, new java.sql.Timestamp ( calendar_start.getTimeInMillis() - timezone_offset ) );  // start_utc_tms
        ps_update_entry.setTimestamp ( 4, new java.sql.Timestamp ( calendar_end.getTimeInMillis() - timezone_offset ) );  // start_utc_tms
        ps_update_entry.setString (  5, data_form.getDescription() );   // suppress_desc_txt
        try
        {
            num_minutes_prior = Integer.parseInt ( data_form.getNumMinutesPrior() );
        }
        catch ( NumberFormatException exception )
        {
            num_minutes_prior = Integer.MIN_VALUE;
        }
        ps_update_entry.setInt    (  6, num_minutes_prior );             // notify_minutes
        ps_update_entry.setString (  7, data_form.getDbServer() );       // instance
        ps_update_entry.setString (  8, data_form.getDbServerMsg() );    // message
        String temp_string = data_form.getRemoveOnReboot();
        
        if ( false == is_production_library )
        {
            int reboot = (( null != temp_string ) && ( temp_string.equals ( "on" ) ) )? 1 : 0;
            ps_update_entry.setInt    (  9, reboot );                        // remove_on_reboot
        
            ps_update_entry.setInt    ( 10, Integer.parseInt ( suppress_id ) );
        }
        else
        {
            ps_update_entry.setInt    ( 9, Integer.parseInt ( suppress_id ) );
        }
        
        return ps_update_entry.executeUpdate();
    }

    private static String safeAssignValue ( String value )
    {
        if ( null == value )
        {
            return "";
        }
        else
        {
            return value;
        }
    }

    /**
     * Calls deleteSuppression
     * 
     * @param suppress_id
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int deleteEntry ( int suppress_id ) throws ClassNotFoundException, SQLException, IOException
    {
       return deleteSuppression ( suppress_id );
    }

    /**
     * Retrieves a suppression by Suppresion ID and returns the values in an AddEntry form object.
     * 
     * @param suppress_id The Suppression ID
     * @return an AddEntry form object of all the values for this suppression
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static AddEntry retrieveEntry ( int suppress_id ) throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection();
        
        AddEntry data_form = new AddEntry();
        data_form.setSuppressId ( "" + suppress_id );
        
        ps_get_entry.setInt ( 1, suppress_id );
        
        ResultSet results = ps_get_entry.executeQuery();
        if ( results.next() )
        {
            data_form.setDescription     ( safeAssignValue ( results.getString ( "suppress_desc_txt" ) ) );
            data_form.setNode            ( safeAssignValue ( results.getString ( "node_nm"           ) ) );
            data_form.setApplication     ( safeAssignValue ( results.getString ( "group_nm"          ) ) );
            data_form.setNumMinutesPrior ( safeAssignValue ( results.getString ( "notify_minutes"    ) ) );
            data_form.setDbServer        ( safeAssignValue ( results.getString ( "instance"          ) ) );
            data_form.setDbServerMsg     ( safeAssignValue ( results.getString ( "message"           ) ) );
            
            data_form.setStartTimestamp  ( results.getTimestamp ( "start_utc_tms" ) );
            data_form.setEndTimestamp    ( results.getTimestamp ( "end_utc_tms"   ) );
                        
            int reboot = results.getInt  ( "remove_on_reboot" );
            data_form.setRemoveOnReboot  ( ( 1 == reboot )? "on" : "" );
            
            return data_form;
        }
        else
        {
            return null;
        }
    }

    public static AddEntry retrieveEntry ( RenderRequest renderRequest, int suppress_id ) throws ClassNotFoundException, SQLException, IOException
    {
        checkSuppressionDatabaseConnection ( renderRequest );
        
        AddEntry data_form = new AddEntry();
        data_form.setSuppressId ( "" + suppress_id );
        
        ps_get_entry.setInt ( 1, suppress_id );
        
        ResultSet results = ps_get_entry.executeQuery();
        if ( results.next() )
        {
            data_form.setDescription     ( safeAssignValue ( results.getString ( "suppress_desc_txt" ) ) );
            data_form.setNode            ( safeAssignValue ( results.getString ( "node_nm"           ) ) );
            data_form.setApplication     ( safeAssignValue ( results.getString ( "group_nm"          ) ) );
            data_form.setStartTimeUTC    ( safeAssignValue ( results.getString ( "start_utc_tms"     ) ) );
            data_form.setEndTimeUTC      ( safeAssignValue ( results.getString ( "end_utc_tms"       ) ) );
            data_form.setNumMinutesPrior ( safeAssignValue ( results.getString ( "notify_minutes"    ) ) );
            data_form.setDbServer        ( safeAssignValue ( results.getString ( "instance"          ) ) );
            data_form.setDbServerMsg     ( safeAssignValue ( results.getString ( "message"           ) ) );
            
            if ( false == is_production_library )
            {
                int reboot = results.getInt  ( "remove_on_reboot" );
                data_form.setRemoveOnReboot  ( ( 1 == reboot )? "on" : "" );
            }
            
            return data_form;
        }
        else
        {
            return null;
        }
    }

    /**
     * Retrieves all the suppressions created by the specified user that meet the specified search patterns
     * 
     * @param description
     * @param application
     * @param node
     * @param db_server
     * @param db_server_msg
     * @param start_date
     * @param end_date
     * @param remove_on_reboot
     * @param is_deleted
     * @param username
     * @return a java.sql.ResultSet of the suppressions created by the user
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static ResultSet retrieveUserHistory ( String description, String application, String node, 
                    String db_server, String db_server_msg, Date start_date, Date end_date, int  remove_on_reboot, int is_deleted, String username )
            throws SQLException, ClassNotFoundException, IOException
    {
        checkSuppressionDatabaseConnection();

        boolean search_all_apps  = ( null == application )   || ( application.equals   ( "all" ));
        boolean search_all_nodes = ( null == node )          || ( node.equals          ( "all" ));
        boolean search_all_dbs   = ( null == db_server )     || ( db_server.equals     ( "all" ));
        boolean search_all_msgs  = ( null == db_server_msg ) || ( db_server_msg.equals ( "" ));
        boolean search_all_descs = ( null == description )   || ( description.equals   ( "" ));
        boolean search_start_date = ( null != start_date );
        boolean search_end_date   = ( null != end_date );
        int field_counter        = 1;

        StringBuilder query = new StringBuilder();
        query.append ( "SELECT   suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, " );
        query.append ( "         notify_minutes, suppress_desc_txt, instance, message, deleted_flg, remove_on_reboot " );
        query.append ( "FROM     t_vpo_suppress " );
        query.append ( "WHERE    suppress_id >= 0 " ); //( deleted_flg != 'y' or deleted_flg is null ) " );
        if ( !search_all_descs )
        {
            query.append ( "  AND    ( patindex (?, suppress_desc_txt) > 0 ) " );
        }
        if ( !search_all_msgs )
        {
            query.append ( "  AND    ( patindex (?, message ) > 0 ) " );
        }
        if ( !search_all_apps )
        {
            query.append ( "  AND    ( patindex (?, group_nm      ) > 0 ) " );
        }
        if ( !search_all_nodes )
        {
            query.append ( "  AND    ( patindex (?, node_nm       ) > 0 ) " );
        }
        if ( !search_all_dbs )
        {
            query.append ( "  AND    ( patindex (?, instance      ) > 0 ) " );
        }
        if ( search_start_date )
        {
            query.append ( "  AND    start_utc_tms >= ? " );
        }
        if ( search_end_date )
        {
            query.append ( "  AND    end_utc_tms <= ? " );
        }
        if ( false == is_production_library )
        {
            if ( REMOVE_ON_REBOOT_TRUE_ONLY == remove_on_reboot )
            {
                query.append ( "  AND remove_on_reboot=1 " );
            }
            else if ( REMOVE_ON_REBOOT_FALSE_ONLY == remove_on_reboot )
            {
                query.append ( "  AND remove_on_reboot=0 " );
            }
        }
        if ( IS_DELETED_TRUE_ONLY == is_deleted )
        {   
            query.append ( " AND deleted_flg='y' ");
        }
        else if ( IS_DELETED_FALSE_ONLY == is_deleted )
        {   
            query.append ( " AND deleted_flg!='y' ");
        }
        query.append ( "AND create_nm=? " );
        query.append ( "ORDER BY suppress_id" );

        PreparedStatement ps = con_suppressions.prepareStatement ( query.toString() );

        if ( !search_all_descs )
        {
            ps.setString ( field_counter++, "%" + description   + "%" );
        }
        if ( !search_all_msgs )
        {
            ps.setString ( field_counter++, "%" + db_server_msg + "%" );
        }
        if ( !search_all_apps )
        {
            ps.setString ( field_counter++, "%" + application   + "%" );
        }
        if ( !search_all_nodes )
        {
            ps.setString ( field_counter++, "%" + node          + "%" );
        }
        if ( !search_all_dbs )
        {
            ps.setString ( field_counter++, "%" + db_server     + "%" );
        }
        if ( search_start_date )
        {
            ps.setDate ( field_counter++, new java.sql.Date ( start_date.getTime() ) );
        }
        if ( search_end_date )
        {
            ps.setDate ( field_counter++, new java.sql.Date ( end_date.getTime() ) );
        }

        ps.setString ( field_counter++, username );

        StringBuilder message = new StringBuilder ( "retrieveUserHistory() - query=" );
        message.append ( query.toString() );
        message.append ( ", application=" );
        message.append ( (null != application)? application : "null" );
        message.append ( ", node=" );
        message.append ( (null != node)? node : "null" );
        message.append ( ", database=" );
        message.append ( (null != db_server)? db_server : "null" );
        
        _log.debug ( message );


        return ps.executeQuery();
    }

    /**
     * Creates the query used to retrieve a list of current suppressions from the database
     * 
     * @param description
     * @param application
     * @param node
     * @param db_server
     * @param db_server_msg
     * @param start_date
     * @param end_date
     * @param remove_on_reboot
     * @param is_deleted
     * @return a java.sql.resultSet of the current suppressions from the database
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static ResultSet retrieveCurrent ( RenderRequest renderRequest, String description, String application, String node, 
                    String db_server, String db_server_msg, Date start_date, Date end_date, int remove_on_reboot, int is_deleted, 
                    String username, String sort_type )
            throws SQLException, ClassNotFoundException, IOException
    {
        checkSuppressionDatabaseConnection ( renderRequest );

        boolean search_all_apps   = ( null == application )   || ( 0 == application.length() )   || ( application.equals   ( "all" ));
        boolean search_all_nodes  = ( null == node )          || ( 0 == node.length() )          || ( node.equals          ( "all" ));
        boolean search_all_dbs    = ( null == db_server )     || ( 0 == db_server.length() )     || ( db_server.equals     ( "all" ));
        boolean search_all_msgs   = ( null == db_server_msg ) || ( 0 == db_server_msg.length() ) || ( db_server_msg.equals ( "" ));
        boolean search_all_descs  = ( null == description )   || ( 0 == description.length() )   || ( description.equals   ( "" ));
        //boolean search_start_date = ( null != start_date );
        boolean search_end_date   = true;
        boolean search_username   = ( null == username )      || ( 0 == username.length() )      || ( username.equals ( "" ));
        int field_counter         = 1;

        StringBuilder query = new StringBuilder();
        query.append ( "SELECT   suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, " );
        if ( false == is_production_library )
        {
            query.append ( "         notify_minutes, suppress_desc_txt, instance, message, deleted_flg, remove_on_reboot " );
        }
        else
        {
            query.append ( "         notify_minutes, suppress_desc_txt, instance, message, deleted_flg " );
        }
        query.append ( "FROM     t_vpo_suppress " );
        query.append ( "WHERE    suppress_id >= 0 " );//( deleted_flg != 'y' or deleted_flg is null ) " );
        if ( !search_all_descs )
        {
            query.append ( "  AND    ( patindex (?, suppress_desc_txt) > 0 ) " );
        }
        if ( !search_all_msgs )
        {
            query.append ( "  AND    ( patindex (?, message ) > 0 ) " );
        }
        if ( !search_all_apps )
        {
            query.append ( "  AND    ( patindex (?, group_nm      ) > 0 ) " );
        }
        if ( !search_all_nodes )
        {
            query.append ( "  AND    ( patindex (?, node_nm       ) > 0 ) " );
        }
        if ( !search_all_dbs )
        {
            query.append ( "  AND    ( patindex (?, instance      ) > 0 ) " );
        }
        if ( search_end_date )
        {
            query.append ( "  AND    end_utc_tms >= ? " );
        }
        if ( !search_username )
        {
            query.append ( "  AND    ( patindex (?, create_nm     ) > 0 ) " );
        }
        if ( false == is_production_library )
        {
            if ( REMOVE_ON_REBOOT_TRUE_ONLY == remove_on_reboot )
            {
                query.append ( "  AND remove_on_reboot=1 " );
            }
            else if ( REMOVE_ON_REBOOT_FALSE_ONLY == remove_on_reboot )
            {
                query.append ( "  AND remove_on_reboot=0 " );
            }
        }
        if ( IS_DELETED_TRUE_ONLY == is_deleted )
        {   
            query.append ( " AND deleted_flg='y' ");
        }
        else if ( IS_DELETED_FALSE_ONLY == is_deleted )
        {   
            query.append ( " AND deleted_flg!='y' ");
        }
        
        if (( null == sort_type ) || ( sort_type.length() == 0 ))
        {
            query.append ( "ORDER BY suppress_id" );
        }
        else
        {
            query.append ( "ORDER BY " );
            query.append ( sort_type );
        }

        PreparedStatement ps = con_suppressions.prepareStatement ( query.toString() );

        String search_param_description   = "%";
        String search_param_db_server_msg = "%";
        String search_param_application   = "%";
        String search_param_node          = "%";
        String search_param_db_server     = "%";
        String search_param_username      = "%";
        if ( !search_all_descs )
        {
            if ( description.length() > 0 )
            {
                search_param_description = search_param_description + description + "%";
            }
            ps.setString ( field_counter++, search_param_description );
        }
        if ( !search_all_msgs )
        {
            if ( db_server_msg.length() > 0 )
            {
                search_param_db_server_msg = search_param_db_server_msg + db_server_msg + "%";
            }
            ps.setString ( field_counter++, search_param_db_server_msg );
        }
        if ( !search_all_apps )
        {
            if ( application.length() > 0 )
            {
                search_param_application = search_param_application + application + "%";
            }
            ps.setString ( field_counter++, search_param_application );
        }
        if ( !search_all_nodes )
        {
            if ( node.length() > 0 )
            {
                search_param_node = search_param_node + node + "%";
            }
            ps.setString ( field_counter++, search_param_node );
        }
        if ( !search_all_dbs )
        {
            if ( db_server.length() > 0 )
            {
                search_param_db_server = search_param_db_server + db_server + "%";
            }
            ps.setString ( field_counter++, search_param_db_server );
        }
        if ( search_end_date )
        {
            ps.setDate ( field_counter++, new java.sql.Date ( (new java.util.Date()).getTime() ) );
        }
        if ( !search_username )
        {
            if ( username.length() > 0 )
            {
                search_param_username = search_param_username + username + "%";
            }
            ps.setString ( field_counter++, search_param_username );
        }

        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
        message.get().append ( query.toString() );
        message.get().append ( "\nApplication: " );
        message.get().append ( (null != application)? application : "null" );
        message.get().append ( "\nNode         " );
        message.get().append ( (null != node)? node : "null" );
        message.get().append ( "\nDatabases:   " );
        message.get().append ( (null != db_server)? db_server : "null" );
        message.get().append ( "\nQuery: " );
        message.get().append ( query.toString() );
        message.get().append ( "\n\tParameter - start date:  " );
        message.get().append ( start_date.toString() );
        message.get().append ( "\n\tParameter - end date:    " );
        message.get().append ( end_date.toString() );
        message.get().append ( "\n\tParameter - description: " );
        message.get().append ( search_param_description );
        message.get().append ( "\n\tParameter - DB sev. msg: " );
        message.get().append ( search_param_db_server_msg );
        message.get().append ( "\n\tParameter - Application: " );
        message.get().append ( search_param_application );
        message.get().append ( "\n\tParameter - Node:        " );
        message.get().append ( search_param_node );
        message.get().append ( "\n\tParameter - DB Server:   " );
        message.get().append ( search_param_db_server );
        message.get().append ( "\n\tParameter - Username:    " );
        message.get().append ( search_param_username );
        message.get().append ( "\n" );

        _log.debug ( message.get().toString() );
        message = null;
    
        return ps.executeQuery();
    }


    /**
     * Creates the query used to retrieve a list of historical suppressions from the database
     * 
     * @param description
     * @param application
     * @param node
     * @param db_server
     * @param db_server_msg
     * @param start_date
     * @param end_date
     * @param remove_on_reboot
     * @param is_deleted
     * @return a java.sql.ResultSet of the historical suppressions
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static ResultSet retrieveHistory ( RenderRequest renderRequest, String description, String application, String node, 
                    String db_server, String db_server_msg, Date start_date, Date end_date, int remove_on_reboot, int is_deleted, 
                    String username, String sort_type )
            throws SQLException, ClassNotFoundException, IOException
    {
        checkSuppressionDatabaseConnection ( renderRequest );

        boolean search_all_apps   = ( null == application )   || ( 0 == application.length() )   || ( application.equals   ( "all" ));
        boolean search_all_nodes  = ( null == node )          || ( 0 == node.length() )          || ( node.equals          ( "all" ));
        boolean search_all_dbs    = ( null == db_server )     || ( 0 == db_server.length() )     || ( db_server.equals     ( "all" ));
        boolean search_all_msgs   = ( null == db_server_msg ) || ( 0 == db_server_msg.length() ) || ( db_server_msg.equals ( "" ));
        boolean search_all_descs  = ( null == description )   || ( 0 == description.length() )   || ( description.equals   ( "" ));
        boolean search_start_date = ( null != start_date );
        boolean search_end_date   = ( null != end_date );
        boolean search_username   = ( null == username )      || ( 0 == username.length() )      || ( username.equals ( "" ));
        int field_counter         = 1;

        StringBuilder query = new StringBuilder();
        query.append ( "SELECT   suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, " );
        if ( false == is_production_library )
        {
            query.append ( "         notify_minutes, suppress_desc_txt, instance, message, deleted_flg, remove_on_reboot " );
        }
        else
        {
            query.append ( "         notify_minutes, suppress_desc_txt, instance, message, deleted_flg " );
        }
        query.append ( "FROM     t_vpo_suppress " );
        query.append ( "WHERE    suppress_id >= 0 " );//( deleted_flg != 'y' or deleted_flg is null ) " );
        if ( !search_all_descs )
        {
            query.append ( "  AND    ( patindex (?, suppress_desc_txt) > 0 ) " );
        }
        if ( !search_all_msgs )
        {
            query.append ( "  AND    ( patindex (?, message ) > 0 ) " );
        }
        if ( !search_all_apps )
        {
            query.append ( "  AND    ( patindex (?, group_nm      ) > 0 ) " );
        }
        if ( !search_all_nodes )
        {
            query.append ( "  AND    ( patindex (?, node_nm       ) > 0 ) " );
        }
        if ( !search_all_dbs )
        {
            query.append ( "  AND    ( patindex (?, instance      ) > 0 ) " );
        }
        if ( search_start_date )
        {
            query.append ( "  AND    start_utc_tms >= ? " );
        }
        if ( search_end_date )
        {
            query.append ( "  AND    end_utc_tms <= ? " );
        }
        if ( !search_username )
        {
            query.append ( "  AND    ( patindex (?, create_nm     ) > 0 ) " );
        }
        if ( false == is_production_library )
        {
            if ( REMOVE_ON_REBOOT_TRUE_ONLY == remove_on_reboot )
            {
                query.append ( "  AND remove_on_reboot=1 " );
            }
            else if ( REMOVE_ON_REBOOT_FALSE_ONLY == remove_on_reboot )
            {
                query.append ( "  AND remove_on_reboot=0 " );
            }
        }
        if ( IS_DELETED_TRUE_ONLY == is_deleted )
        {   
            query.append ( " AND deleted_flg='y' ");
        }
        else if ( IS_DELETED_FALSE_ONLY == is_deleted )
        {   
            query.append ( " AND deleted_flg!='y' ");
        }
        
        if (( null == sort_type ) || ( sort_type.length() == 0 ))
        {
            query.append ( "ORDER BY suppress_id" );
        }
        else
        {
            query.append ( "ORDER BY " );
            query.append ( sort_type );
        }

        PreparedStatement ps = con_suppressions.prepareStatement ( query.toString() );

        String search_param_description   = "%";
        String search_param_db_server_msg = "%";
        String search_param_application   = "%";
        String search_param_node          = "%";
        String search_param_db_server     = "%";
        String search_param_username      = "%";
        if ( !search_all_descs )
        {
            if ( description.length() > 0 )
            {
                search_param_description = search_param_description + description + "%";
            }
            ps.setString ( field_counter++, search_param_description );
        }
        if ( !search_all_msgs )
        {
            if ( db_server_msg.length() > 0 )
            {
                search_param_db_server_msg = search_param_db_server_msg + db_server_msg + "%";
            }
            ps.setString ( field_counter++, search_param_db_server_msg );
        }
        if ( !search_all_apps )
        {
            if ( application.length() > 0 )
            {
                search_param_application = search_param_application + application + "%";
            }
            ps.setString ( field_counter++, search_param_application );
        }
        if ( !search_all_nodes )
        {
            if ( node.length() > 0 )
            {
                search_param_node = search_param_node + node + "%";
            }
            ps.setString ( field_counter++, search_param_node );
        }
        if ( !search_all_dbs )
        {
            if ( db_server.length() > 0 )
            {
                search_param_db_server = search_param_db_server + db_server + "%";
            }
            ps.setString ( field_counter++, search_param_db_server );
        }
        if ( search_start_date )
        {
            ps.setDate ( field_counter++, new java.sql.Date ( start_date.getTime() ) );
        }
        if ( search_end_date )
        {
            ps.setDate ( field_counter++, new java.sql.Date ( end_date.getTime() ) );
        }
        if ( !search_username )
        {
            if ( username.length() > 0 )
            {
                search_param_username = search_param_username + username + "%";
            }
            ps.setString ( field_counter++, search_param_username );
        }
        
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
        message.get().append ( query.toString() );
        message.get().append ( "\nApplication: " );
        message.get().append ( (null != application)? application : "null" );
        message.get().append ( "\nNode         " );
        message.get().append ( (null != node)? node : "null" );
        message.get().append ( "\nDatabases:   " );
        message.get().append ( (null != db_server)? db_server : "null" );
        message.get().append ( "\nQuery: " );
        message.get().append ( query.toString() );
        message.get().append ( "\n\tParameter - start date:  " );
        message.get().append ( start_date.toString() );
        message.get().append ( "\n\tParameter - end date:    " );
        message.get().append ( end_date.toString() );
        message.get().append ( "\n\tParameter - description: " );
        message.get().append ( search_param_description );
        message.get().append ( "\n\tParameter - DB sev. msg: " );
        message.get().append ( search_param_db_server_msg );
        message.get().append ( "\n\tParameter - Application: " );
        message.get().append ( search_param_application );
        message.get().append ( "\n\tParameter - Node:        " );
        message.get().append ( search_param_node );
        message.get().append ( "\n\tParameter - DB Server:   " );
        message.get().append ( search_param_db_server );
        message.get().append ( "\n\tParameter - Username:    " );
        message.get().append ( search_param_username );
        message.get().append ( "\n" );

        _log.debug ( message.get().toString() );
        message = null;
    
        return ps.executeQuery();
    }
    /**
     * Creates the query used to retrieve a list of historical suppressions from the database
     * 
     * @param description
     * @param application
     * @param node
     * @param db_server
     * @param db_server_msg
     * @param start_date
     * @param end_date
     * @param remove_on_reboot
     * @param is_deleted
     * @return a java.sql.ResultSet of the historical suppressions
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static ResultSet retrieveHistory ( String description, String application, String node, 
                    String db_server, String db_server_msg, Date start_date, Date end_date, int remove_on_reboot, int is_deleted, 
                    String username, String sort_type )
            throws SQLException, ClassNotFoundException, IOException
    {
        checkSuppressionDatabaseConnection();

        boolean search_all_apps   = ( null == application )   || ( 0 == application.length() )   || ( application.equals   ( "all" ));
        boolean search_all_nodes  = ( null == node )          || ( 0 == node.length() )          || ( node.equals          ( "all" ));
        boolean search_all_dbs    = ( null == db_server )     || ( 0 == db_server.length() )     || ( db_server.equals     ( "all" ));
        boolean search_all_msgs   = ( null == db_server_msg ) || ( 0 == db_server_msg.length() ) || ( db_server_msg.equals ( "" ));
        boolean search_all_descs  = ( null == description )   || ( 0 == description.length() )   || ( description.equals   ( "" ));
        boolean search_start_date = ( null != start_date );
        boolean search_end_date   = ( null != end_date );
        boolean search_username   = ( null == username )      || ( 0 == username.length() )      || ( username.equals ( "" ));
        int field_counter         = 1;

        StringBuilder query = new StringBuilder();
        query.append ( "SELECT   suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, " );
        if ( false == is_production_library )
        {
            query.append ( "         notify_minutes, suppress_desc_txt, instance, message, deleted_flg, remove_on_reboot " );
        }
        else
        {
            query.append ( "         notify_minutes, suppress_desc_txt, instance, message, deleted_flg " );
        }
        query.append ( "FROM     t_vpo_suppress " );
        query.append ( "WHERE    suppress_id >= 0 " );//( deleted_flg != 'y' or deleted_flg is null ) " );
        if ( !search_all_descs )
        {
            query.append ( "  AND    ( patindex (?, suppress_desc_txt) > 0 ) " );
        }
        if ( !search_all_msgs )
        {
            query.append ( "  AND    ( patindex (?, message ) > 0 ) " );
        }
        if ( !search_all_apps )
        {
            query.append ( "  AND    ( patindex (?, group_nm      ) > 0 ) " );
        }
        if ( !search_all_nodes )
        {
            query.append ( "  AND    ( patindex (?, node_nm       ) > 0 ) " );
        }
        if ( !search_all_dbs )
        {
            query.append ( "  AND    ( patindex (?, instance      ) > 0 ) " );
        }
        if ( search_start_date )
        {
            query.append ( "  AND    start_utc_tms >= ? " );
        }
        if ( search_end_date )
        {
            query.append ( "  AND    end_utc_tms <= ? " );
        }
        if ( !search_username )
        {
            query.append ( "  AND    ( patindex (?, create_nm     ) > 0 ) " );
        }
        if ( false == is_production_library )
        {
            if ( REMOVE_ON_REBOOT_TRUE_ONLY == remove_on_reboot )
            {
                query.append ( "  AND remove_on_reboot=1 " );
            }
            else if ( REMOVE_ON_REBOOT_FALSE_ONLY == remove_on_reboot )
            {
                query.append ( "  AND remove_on_reboot=0 " );
            }
        }
        if ( IS_DELETED_TRUE_ONLY == is_deleted )
        {   
            query.append ( " AND deleted_flg='y' ");
        }
        else if ( IS_DELETED_FALSE_ONLY == is_deleted )
        {   
            query.append ( " AND deleted_flg!='y' ");
        }
        
        if (( null == sort_type ) || ( sort_type.length() == 0 ))
        {
            query.append ( "ORDER BY suppress_id" );
        }
        else
        {
            query.append ( "ORDER BY " );
            query.append ( sort_type );
        }

        PreparedStatement ps = con_suppressions.prepareStatement ( query.toString() );

        String search_param_description   = "%";
        String search_param_db_server_msg = "%";
        String search_param_application   = "%";
        String search_param_node          = "%";
        String search_param_db_server     = "%";
        String search_param_username      = "%";
        if ( !search_all_descs )
        {
            if ( description.length() > 0 )
            {
                search_param_description = search_param_description + description + "%";
            }
            ps.setString ( field_counter++, search_param_description );
        }
        if ( !search_all_msgs )
        {
            if ( db_server_msg.length() > 0 )
            {
                search_param_db_server_msg = search_param_db_server_msg + db_server_msg + "%";
            }
            ps.setString ( field_counter++, search_param_db_server_msg );
        }
        if ( !search_all_apps )
        {
            if ( application.length() > 0 )
            {
                search_param_application = search_param_application + application + "%";
            }
            ps.setString ( field_counter++, search_param_application );
        }
        if ( !search_all_nodes )
        {
            if ( node.length() > 0 )
            {
                search_param_node = search_param_node + node + "%";
            }
            ps.setString ( field_counter++, search_param_node );
        }
        if ( !search_all_dbs )
        {
            if ( db_server.length() > 0 )
            {
                search_param_db_server = search_param_db_server + db_server + "%";
            }
            ps.setString ( field_counter++, search_param_db_server );
        }
        if ( search_start_date )
        {
            ps.setDate ( field_counter++, new java.sql.Date ( start_date.getTime() ) );
        }
        if ( search_end_date )
        {
            ps.setDate ( field_counter++, new java.sql.Date ( end_date.getTime() ) );
        }
        if ( !search_username )
        {
            if ( username.length() > 0 )
            {
                search_param_username = search_param_username + username + "%";
            }
            ps.setString ( field_counter++, search_param_username );
        }
        
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
        message.get().append ( query.toString() );
        message.get().append ( "\nApplication: " );
        message.get().append ( (null != application)? application : "null" );
        message.get().append ( "\nNode         " );
        message.get().append ( (null != node)? node : "null" );
        message.get().append ( "\nDatabases:   " );
        message.get().append ( (null != db_server)? db_server : "null" );
        message.get().append ( "\nQuery: " );
        message.get().append ( query.toString() );
        message.get().append ( "\n\tParameter - start date:  " );
        message.get().append ( start_date.toString() );
        message.get().append ( "\n\tParameter - end date:    " );
        message.get().append ( end_date.toString() );
        message.get().append ( "\n\tParameter - description: " );
        message.get().append ( search_param_description );
        message.get().append ( "\n\tParameter - DB sev. msg: " );
        message.get().append ( search_param_db_server_msg );
        message.get().append ( "\n\tParameter - Application: " );
        message.get().append ( search_param_application );
        message.get().append ( "\n\tParameter - Node:        " );
        message.get().append ( search_param_node );
        message.get().append ( "\n\tParameter - DB Server:   " );
        message.get().append ( search_param_db_server );
        message.get().append ( "\n\tParameter - Username:    " );
        message.get().append ( search_param_username );
        message.get().append ( "\n" );

        _log.debug ( message.get().toString() );
        message = null;

        return ps.executeQuery();
    }

    /**
     * Retrieves the list of availabe servers from the data source (VPO) database returns the list by populating
     * a com.bgi.esm.portlets.Suppressions.form.EditEntry form object
     * 
     * @param renderRequest the javax.portlet.RenderRequest object from the portlet
     * @param data_form the form to return the results in
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static void retrieveServerData ( RenderRequest renderRequest, EditEntry data_form ) throws ClassNotFoundException, SQLException, IOException 
    {
        AddEntry add_form = new AddEntry();

        add_form.copyFromEditForm ( data_form );

        retrieveServerData ( renderRequest, add_form );

        data_form.copyFromAddForm ( add_form );
    }

    /**
     * Retrieves the list of availabe servers from the data source (VPO) database returns the list by populating
     * a com.bgi.esm.portlets.Suppressions.form.AddEntry form object
     *
     * @param renderRequest the javax.portlet.RenderRequest object from the portlet
     * @param data_form the form to return the results in
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static void retrieveServerData ( RenderRequest renderRequest, AddEntry data_form ) throws ClassNotFoundException, SQLException, IOException 
    {
        Vector <String> data = null;
        ResultSet results    = null;
        Statement stmt       = null;
        String data_string   = null;
        StringTokenizer t    = null;
        checkProperties();
        
        stmt = getDataSourceDatabaseStatement( renderRequest );
            //  Query to get all the nodes that the notification suppression website will be concerned with
            _log.info ( "Obtaining list of nodes from VPO" );
            results = stmt.executeQuery ( "select distinct lower(node_name) as nn from opc_op.opc_node_names order by nn" );
            data    = new Vector <String> ();
            while ( results.next() )
            {
                data_string = results.getString ( 1 );
                if ( null != data_string )
                {
                    t = new StringTokenizer ( data_string, "." );
                    if ( t.hasMoreTokens() )
                    {
                        data_string = t.nextToken();
                        data.add ( data_string );
                        
                    }
                }
            }
            data_form.setNodeNames ( data );
               //    unshift @groups, "- select node here -";

            //    Message groups
            _log.info ( "Obtaining message groups from VPO");
            results = stmt.executeQuery ( "select distinct lower(name) as n from opc_op.opc_message_groups order by n" );
            data    = new Vector <String> ();
            while ( results.next() )
            {
                data_string = results.getString ( 1 );
                data_string = ( null != data_string )? data_string : "";
                if ( null != data_string )
                {
                    data.add ( data_string );
                }
            }
            data_form.setMessageGroups ( data );

        stmt.close();

        //stmt = getSuppressionDatabaseStatement ( renderRequest );
        stmt = getInstanceDatabaseConnection ( renderRequest ).createStatement();
        
            //    Data servers
            //results = stmt.executeQuery ( "select node_id, node_name FROM opc_node_names ORDER BY node_name " );
            _log.info ( "Obtaining data servers" );
            results = stmt.executeQuery ( "SELECT server_nm from t_data_servers ORDER BY server_nm" );
            //    unshift @dbSrvrs, "- select DB Server here -";
            data    = new Vector <String> ();
            while ( results.next() )
            {
                data_string = results.getString ( 1 );
                data_string = ( null != data_string )? data_string : "";
                data.add ( data_string );
            }
            data_form.setDataServers ( data );

        stmt.close();
    }

    
    /**
     * Retrieves the list of availabe servers from the data source (VPO) database returns the list by populating
     * a com.bgi.esm.portlets.Suppressions.form.AddEntry form object
     * 
     * @param data_form the form to return the results in
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @SuppressWarnings({"unchecked","unchecked", "unchecked"})
    public static void retrieveServerData ( AddEntry data_form ) throws ClassNotFoundException, SQLException, IOException 
    {
        Vector data        = null;
        ResultSet results  = null;
        Statement stmt     = null;
        String data_string = null;
        StringTokenizer t  = null;
        checkProperties();
      
        stmt = getDataSourceDatabaseStatement();
            //  Query to get all the nodes that the notification suppression website will be concerned with
            _log.info ( "Obtaining list of nodes from VPO" );
            //results = stmt.executeQuery ( "select distinct lower(node_name) from opc_node_names" );
            results = stmt.executeQuery ( "select distinct lower(node_name) as nn from opc_op.opc_node_names order by nn" );
            results = stmt.executeQuery ( "select distinct lower(node_name) as nn from opc_op.opc_node_names order by nn" );
            data    = new Vector();
            while ( results.next() )
            {
                data_string = results.getString ( 1 );
                if ( null != data_string )
                {
                    t = new StringTokenizer ( data_string, "." );
                    if ( t.hasMoreTokens() )
                    {
                        data_string = t.nextToken();
                        data.add ( data_string );
                        
                    }
                }
            }
            data_form.setNodeNames ( data );
               //    unshift @groups, "- select node here -";

            //    Message groups
            _log.info ( "Obtaining message groups from VPO");
            results = stmt.executeQuery ( "select distinct lower(name) as n from opc_message_groups order by n" );
            data    = new Vector();
            while ( results.next() )
            {
                data_string = results.getString ( 1 );
                data_string = ( null != data_string )? data_string : "";
                if ( null != data_string )
                {
                    data.add ( data_string );
                }
            }
            data_form.setMessageGroups ( data );

        stmt.close();

        stmt = getInstanceDatabaseConnection().createStatement();
        
            //    Data servers
            //results = stmt.executeQuery ( "select node_id, node_name FROM opc_node_names ORDER BY node_name " );
            _log.info ( "Obtaining data servers" );
            results = stmt.executeQuery ( "SELECT server_nm from t_data_servers ORDER BY server_nm" );
            //results = stmt.executeQuery ( "SELECT server_nm from .t_data_servers where server_type_cd in ('sql', 'mssql', 'rep', 'iq') and dba_support_grp_id != '' and dba_support_grp_id != 'XX' and dba_support_grp_id is not null" );
            //results = stmt.executeQuery ( "SELECT server_nm from .t_data_servers ORDER BY server_nm" );
            //    unshift @dbSrvrs, "- select DB Server here -";
            data    = new Vector();
            while ( results.next() )
            {
                data_string = results.getString ( 1 );
                data_string = ( null != data_string )? data_string : "";
                data.add ( data_string );
            }
            data_form.setDataServers ( data );

        stmt.close();
    }

    /**
     * Creates the header for dumping form values
     * 
     * @return the header for dumping form values
     */
    public static byte[] dumpFormValueHeader()
    {
        return "<table border='1'>\n".getBytes();
    }
 
    public static byte[] dumpFormValue ( String name, String value )
    {
        StringBuffer buffer = new StringBuffer( "\t<tr>\n" );
        buffer.append ( "\t\t<td>" );
        buffer.append ( name );
        buffer.append ( "</td>\n" );
        buffer.append ( "\t\t<td>" );
        buffer.append ( value );
        buffer.append ( "</td>\n" );
        buffer.append ( "\t</tr>\n" );

        return buffer.toString().getBytes();
    }

    public static byte[] dumpFormValueFooter()
    {
        return "</table>\n".getBytes();
    }

    /**
     * Retrieves the value of the timezone cookie the browser sets to notify the application of the user's timezone
     * 
     * @param request the HttpServletRequest from the application
     * @return the timezone cookie
     */
    public static Cookie getTimeZoneCookie ( HttpServletRequest request )
    {
        Cookie cookies[]       = request.getCookies();
        Cookie timezone_cookie = null;
        int counter            = 0;

        for ( counter = 0; counter < cookies.length; counter++ )
        {
            if ( cookies[counter].getName().equals ( TIMEZONE_COOKIE_NAME ) )
            {
                timezone_cookie = cookies[counter];
    
                break;
            }
        }

        return timezone_cookie;
    }

    /**
     * Computes the value of the timezone cookie
     * 
     * @param request the HttpServletReqeust from the application
     * @return the timezone offset in milliseconds
     */
    public static int computeTimeZoneOffset ( HttpServletRequest request )
    {
        Cookie timezone_cookie = Toolkit.getTimeZoneCookie ( request );

        if ( null == timezone_cookie )
        {
            return 0;
        }
        else
        {
            return Integer.parseInt ( timezone_cookie.getValue() ) * 3600000;
        }
    }
    
    /**
     * Updates the database connection parameters to the primary suppression database
     * 
     * @param driver_name the class name of the JDBC driver the connection will use
     * @param connection_url the connection URL to the database
     * @param username the username to login to the database
     * @param password the password to login ito the database
     */
    public static void updateSuppressionDatabaseProperties ( String driver_name, String connection_url, String username, String password )
    {
        sup_db_driver_name    = driver_name;
        sup_db_connection_url = connection_url;
        sup_db_username       = username;
        sup_db_password       = password;
    }
    
    /**
     * Updates the database connection parameters to the backup suppression database
     * 
     * @param driver_name the class name of the JDBC driver the connection will use
     * @param connection_url the connection URL to the database
     * @param username the username to login to the database
     * @param password the password to login ito the database
     */
    public static void updateSuppressionBackupDatabaseProperties ( String driver_name, String connection_url, String username, String password )
    {
        sup_backup_db_driver_name    = driver_name;
        sup_backup_db_connection_url = connection_url;
        sup_backup_db_username       = username;
        sup_backup_db_password       = password;
    }
    
    /**
     * Returns the state of the suppressions database connection
     * 
     * @return the state of the suppressions database connection
     */
    public static int getDatabaseConnectionStateSuppressions()
    {
        return database_mode_suppressions;
    }
    
    /**
     * Returns the state of the data source (VPO) database connection
     * 
     * @return the state of the data source (VPO) database connection
     */
    public static int getDatabaseConnectionStateDataSource()
    {
        return database_mode_vpo;
    }
    
    /**
     * Returns the state of the instance database connection
     * 
     * @return the state of the instance database connection
     */
    public static int getDatabaseConnectionStateInstance()
    {
        return database_mode_inst;
    }

    /**
     * Retrieves all the current suppressions and returns the result as a java.util.HashMap of java.util.Vector objects.  This function
     * was meant to keep all the database interaction within the C layer of the MVC.
     * 
     * @param renderRequest the javax.portlet.RenderRequest object taken from the portal
     * @return a java.util.HashMap of java.util.Vector objects of the suppressions table
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static HashMap getCurrentSuppressionsHashmap ( RenderRequest renderRequest, int timezone_offset ) throws ClassNotFoundException, SQLException, IOException
    {
        ResultSet results = getAllCurrentSuppressions ( renderRequest, timezone_offset );

        return DatabaseUtils.convertResultsToHashMaps ( results );
    }

    /**
     * Used to sanitize the output of a query against the suppressions database
     * 
     * @param value the value to check
     * @return the sanitized output value
     */
    public static String checkDisplayString ( String value )
    {
        if ( null == value )
        {
            return "<center>-</center>";
        }

        if ( value.length() < 3 )
        {
            return "<center>-</center>";
        }

        if ( value.equals ( "null" ) )
        {
            return "<center>-</center>";
        }

        return value;
    }

    public static void dumpRawHttpRequestParameters ( String filename, HttpServletRequest request ) throws IOException
    {
        Enumeration e = request.getParameterNames();
        String param  = null;

        java.io.FileOutputStream outfile = new java.io.FileOutputStream ( filename );
            while ( e.hasMoreElements() )
            {
                param = e.nextElement().toString();

                outfile.write ( param.getBytes() );
                outfile.write ( " = ".getBytes() );
                outfile.write ( request.getParameter( param ).getBytes() );
                outfile.write ( "\n".getBytes() );
            }
        outfile.close();
    }

    public static HibernateFacade getHibernateFacade()
    {
        return hibernateFacade;
    }
};
