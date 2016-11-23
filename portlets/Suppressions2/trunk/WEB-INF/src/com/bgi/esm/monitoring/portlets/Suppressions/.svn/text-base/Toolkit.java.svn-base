package com.bgi.esm.monitoring.portlets.Suppressions;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.AddEntry;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.EditEntry;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.SuppressionDate;

public class Toolkit
{
    public static final int REMOVE_ON_REBOOT_DOES_NOT_MATTER        = 0;
    public static final int REMOVE_ON_REBOOT_TRUE_ONLY              = 1;
    public static final int REMOVE_ON_REBOOT_FALSE_ONLY             = 2;
    public static final int IS_DELETED_DOES_NOT_MATTER              = 0;
    public static final int IS_DELETED_TRUE_ONLY                    = 1;
    public static final int IS_DELETED_FALSE_ONLY                   = 2;

    final private static int    DEFAULT_INT             = 0;
    final private static long   DEFAULT_LONG            = 0;
    final private static double DEFAULT_DOUBLE          = 0.0;
    final private static float  DEFAULT_FLOAT           = 0.0f;
    final public static String TIMEZONE_COOKIE_NAME     = "struts-suppression-timezone-offset";

    final public static SimpleDateFormat sdf            = new SimpleDateFormat ( "yyyy-MM-dd HH:m:ss" );

    final private static Logger _log                    = Logger.getLogger( Toolkit.class );

    private static Class c                              = null;
    private static ClassLoader cl                       = null;
    private static InputStream is                       = null;
    private static Properties common_properties         = null;
    private static Connection con_data_source           = null;
    private static Connection con_instance              = null;
    private static String ds_db_driver_name             = null;
    private static String ds_db_connection_url          = null;
    private static String ds_db_username                = null;
    private static String ds_db_password                = null;
    private static String ds_backup_db_driver_name      = null;
    private static String ds_backup_db_connection_url   = null;
    private static String ds_backup_db_username         = null;
    private static String ds_backup_db_password         = null;

    private static String inst_db_driver_name           = null;
    private static String inst_db_connection_url        = null;
    private static String inst_db_username              = null;
    private static String inst_db_password              = null;
    private static String inst_backup_db_driver_name    = null;
    private static String inst_backup_db_connection_url = null;
    private static String inst_backup_db_username       = null;
    private static String inst_backup_db_password       = null;

    public static final int DATABASE_MODE_INVALID       = -1;
    public static final int DATABASE_MODE_PRIMARY       =  1;
    public static final int DATABASE_MODE_BACKUP        =  2;
    public static final int DATABASE_MODE_EXTRACT_FILE  =  3;
    
    private static int database_mode_vpo                = DATABASE_MODE_INVALID;
    private static int database_mode_inst               = DATABASE_MODE_INVALID;


    /**
     * Sets the size of the portlet window according to javax.portlet.WindowState.  This is to be called in the 
     * execute() method of the org.apache.struts.action.Action class.
     *
     * @param request the HttpServletRequest of the current page
     * @param window_state the new size of the window
     */
    public static void setPortletWindowSize ( HttpServletRequest request, WindowState window_state )
        throws WindowStateException
    {
        PortletResponse portletResponse = (PortletResponse) request.getAttribute ( "javax.portlet.response" );

        if ( portletResponse instanceof ActionResponse )
        {
            _log.info ( "MaximizePortletWindowAction::execute() - detected ActionResponse object" );
            ActionResponse actionResponse = (ActionResponse) portletResponse;

            actionResponse.setWindowState ( window_state );
        }
        else
        {
            _log.info ( "Toolkit::setPortletWindowSize() - could not set the portlet window size" );
        }
    }

    /**
     * Create a portal-independent, common HashMap <String, String> object for the developer to retrieve
     * HTTP request parameters from.
     *
     * @param request the HttpServletRequest object to extract parameters from
     */
    public static HashMap <String, String> retrieveHttpRequestParameters ( HttpServletRequest request )
    {
        HashMap <String, String> param_map = new HashMap <String, String> ();

        if (( request.getParameter ( "_spage" ) != null ) || ( request.getParameter ( "_spageview" ) != null )) // Liferay parameter handling
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

    public static void dumpHttpRequestParameters ( String filename, HttpServletRequest request ) throws IOException
    {
        HashMap <String, String> param_map = Toolkit.retrieveHttpRequestParameters ( request );

        StringBuilder buffer = new StringBuilder();
        String temp_string   = null;
        Iterator <String> i  = param_map.keySet().iterator();
        while ( i.hasNext() )
        {
            temp_string = i.next();
            buffer.append ( temp_string );
            buffer.append ( " = " );
            buffer.append ( param_map.get ( temp_string ) );
            buffer.append ( "\n" );
        }

        FileOutputStream outfile = new FileOutputStream ( filename );
            outfile.write ( buffer.toString().getBytes() );
        outfile.close();
    }

    /**
     * Parses a String object as an int, returning 0 if there are a NumberFormatException is thrown
     *
     * @param s the String object to parse
     * @return a String object as an int, returning DEFAULT_INT if there are a NumberFormatException is thrown
     */
    public static int parseInt ( String s )
    {
        try
        {
            return Integer.parseInt ( s );
        }
        catch ( NumberFormatException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder( "Could not parse to int: ") );
            message.get().append( s );
            
            _log.error ( message.get().toString() );
            
            message = null;
            
            exception.printStackTrace();
            return DEFAULT_INT;
        }
    }

    /**
     * Parses a String object as an long, returning 0 if there are a NumberFormatException is thrown
     *
     * @param s the String object to parse
     * @return a String object as an long, returning DEFAULT_LONG if there are a NumberFormatException is thrown
     */
    public static long parseLong ( String s )
    {
        try
        {
            return Long.parseLong ( s );
        }
        catch ( NumberFormatException exception )
        {   
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder( "Could not parse to long: ") );
            message.get().append( s );
            
            _log.error ( message.get().toString() );
            
            message = null;
            
            exception.printStackTrace();
            return DEFAULT_LONG;
        }
    }

    /**
     * Parses a String object as an double, returning 0 if there are a NumberFormatException is thrown
     *
     * @param s the String object to parse
     * @return a String object as an double, returning DEFAULT_DOUBLE if there are a NumberFormatException is thrown
     */
    public static double parseDouble ( String s )
    {
        try
        {
            return Double.parseDouble ( s );
        }
        catch ( NumberFormatException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder( "Could not parse to double: ") );
            message.get().append( s );
            
            _log.error ( message.get().toString() );
            
            message = null;
            
            exception.printStackTrace();
            return DEFAULT_DOUBLE;
        }
    }

    /**
     * Parses a String object as an float, returning 0 if there are a NumberFormatException is thrown
     *
     * @param s the String object to parse
     * @return a String object as an float, returning DEFAULT_FLOAT if there are a NumberFormatException is thrown
     */
    public static float parseFloat ( String s )
    {
        try
        {
            return Float.parseFloat ( s );
        }
        catch ( NumberFormatException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder( "Could not parse to float: ") );
            message.get().append( s );
            
            _log.error ( message.get().toString() );
            
            message = null;
            
            exception.printStackTrace();
            return DEFAULT_FLOAT;
        }
    }

    /**
     * Dumps all the HttpServletRequest attributes and parameters to a file
     */
    @SuppressWarnings("unchecked")
    public static void dumpHttpServletRequest ( String filename, HttpServletRequest request ) throws IOException
    {
        FileOutputStream outfile = new FileOutputStream ( filename );
        Enumeration <String> e   = request.getAttributeNames();
        String property          = null;
        
        outfile.write ( "************* Attributes\n".getBytes() );
        while ( e.hasMoreElements() )
        {
            property = e.nextElement();
            
            outfile.write ( property.getBytes() );
            outfile.write ( " - ".getBytes() );
            outfile.write ( request.getAttribute( property ).toString().getBytes() );
            outfile.write ( "\n".getBytes() );
        }
        
        outfile.write ( "************* Parameters\n".getBytes() );
        e = request.getParameterNames();
        while ( e.hasMoreElements() )
        {
            property = e.nextElement();
            
            outfile.write ( property.getBytes() );
            outfile.write ( " - ".getBytes() );
            outfile.write ( request.getParameter( property ).toString().getBytes() );
            outfile.write ( "\n".getBytes() );
        }
        
        outfile.close();
    }
    
    /**
     * Retrieves the PortletRequest object from the HTTP session
     * 
     * @return the PortletRequest object from the HTTP session
     */
    public static PortletRequest getPortletRequest ( HttpServletRequest request )
    {
        HttpSession session = request.getSession();
        
        return (PortletRequest) session.getAttribute ( "javax.portlet.request" );
    }
    
    public static PortletPreferences getPortletPreferences ( HttpServletRequest request )
    {
        HttpSession session           = request.getSession();
        PortletRequest portletRequest = (PortletRequest) session.getAttribute ( "javax.portlet.request" );
        
        return getPortletPreferences ( portletRequest );
    }
    
    public static PortletPreferences getPortletPreferences ( PortletRequest request )
    {
        return request.getPreferences();
    }

    /**
     * Returns the end date of as extracted from an object/form that implements the com.bgi.esm.portlets.Supperssion.forms.SuppressionDate interface 
     * @param data_form
     * @return the suppression end date
     */
    public static Date getSupEndDate ( SuppressionDate data_form )
    {
        Calendar calendar_end = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        int server_offset = calendar_end.getTimeZone().getOffset ( System.currentTimeMillis() );
        TimeZone timezone     = calendar_end.getTimeZone();
        timezone.setRawOffset ( 0 );
        calendar_end.setTimeZone ( timezone );


        if ( data_form.getEndChoice().equals ( "offset" ) )
        {
            _log.info ( "getSupDate() - Detected offset for end date" );
            int offset   = Integer.parseInt ( data_form.getEndChoiceNum() );
            int unit     = Integer.parseInt ( data_form.getEndChoiceUnit() ); 
            calendar_end = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
            calendar_end.setTime ( Toolkit.getSupStartDate( data_form ) );
            calendar_end.add ( Calendar.SECOND, offset*unit );
            calendar_end.setTimeZone ( timezone );
        }
        else
        {
            _log.info ( "getSupDate() - Detected specified end date" );
            calendar_end = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
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
     * Retrieves the suppression start date from the a com.bgi.esm.portlets.Suppression.forms.AddEntry data form
     * 
     * @param data_form the com.bgi.esm.portlets.Suppression.forms.AddEntry data form
     * @return the suppression start date
     */
    public static Date getSupStartDate ( SuppressionDate data_form )
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
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
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
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
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        calendar.setTime ( new_date );

        data_form.setSupEndYear   ( Integer.toString ( calendar.get ( Calendar.YEAR   ) ) );
        data_form.setSupEndMonth  ( Integer.toString ( calendar.get ( Calendar.MONTH  ) ) );
        data_form.setSupEndDate   ( Integer.toString ( calendar.get ( Calendar.DATE   ) ) );
        data_form.setSupEndHour   ( Integer.toString ( calendar.get ( Calendar.HOUR   ) ) );
        data_form.setSupEndMinute ( Integer.toString ( calendar.get ( Calendar.MINUTE ) ) );
        data_form.setSupEndAmpm   ( (calendar.get ( Calendar.HOUR_OF_DAY ) > 12 )? "PM" : "AM" );
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

    public static long addSuppression ( AddEntry data_form, int timezone_offset )
    {
        if ( null == data_form )
        {
            throw new NullPointerException ( "Tried to add a null suppression" );
        }

        SuppressionRule rule    = convert ( data_form );
        SuppressionRule added   = null;

        WeakReference <BackEndFacade> bef = new WeakReference <BackEndFacade> ( new BackEndFacade() );

        try
        {
            added = bef.get().addOrUpdateSuppressionRule ( rule );
        }
        catch ( BackEndFailure exception )
        {
            _log.error ( "Could not add suppression rule" );
        }

        if ( null != added )
        {
            return added.getSuppressId();
        }
        else
        {
            return -1; 
        }
    }

    public static AddEntry retrieveEntry ( long suppress_id )
    {
        WeakReference <BackEndFacade> bef = new WeakReference <BackEndFacade> ( new BackEndFacade() );
        SuppressionRule rule = null;

        try
        {
            rule = bef.get().selectSuppressionRuleBySuppressId ( suppress_id );
        }
        catch ( BackEndFailure exception )
        {
            _log.error ( "Could not connect to the Enterprise Event Bus", exception );

            return null;
        }

        if ( null == rule )
        {
            _log.error ( "Could not retrieve suppression ID #" + suppress_id );

            return null;
        }

        AddEntry data_form   = convert ( rule );

        return data_form;
    }

    public static boolean updateEntry ( HttpServletRequest request, EditEntry data_form )
    {
        SuppressionRule rule      = convert ( data_form );
        WeakReference <BackEndFacade> bef = new WeakReference <BackEndFacade> ( new BackEndFacade() );
        SuppressionRule retrieved = null;
        SuppressionRule updated   = null;

        _log.info ( "Attempting to update suppression ID: " + rule.getSuppressId() );

        //  Retrieve suppression rule from the EEB
        try
        {
            retrieved = bef.get().selectSuppressionRuleBySuppressId ( rule.getSuppressId() );
        }
        catch ( BackEndFailure exception )
        {
            _log.error ( "Could not retrieve suppression ID #" + rule.getSuppressId() + " for updating" );
        }

        //  Set the values of the suppression rule
        rule.setRuleKey ( retrieved.getRuleKey() );

        //  Retrieve suppression rule from the EEB
        try
        {
            updated = bef.get().addOrUpdateSuppressionRule ( rule );
        }
        catch ( BackEndFailure exception )
        {
            _log.error ( "Could not update suppression ID #" + rule.getSuppressId() + " for updating" );

            return false;
        }

        _log.info ( "Successfully updated suppression ID #" + rule.getSuppressId() );

        return ( null != updated );
    }

    private static AddEntry convert ( SuppressionRule rule )
    {
        AddEntry data_form        = new AddEntry();
        Calendar start_time       = rule.getStartTime();
        Calendar end_time         = rule.getEndTime();
        boolean notification_flag = rule.getNotificationFlag();

        data_form.setDescription        ( rule.getDescription()        );
        data_form.setApplication        ( rule.getApplicationName()    );
        data_form.setDbServer           ( rule.getDatabaseServerName() );
        data_form.setNode               ( rule.getNodeName()           );
        data_form.setUsername           ( rule.getOwner()              );
        data_form.setDbServerMsg        ( rule.getMessage()            );
        data_form.setNotifyBeforeExpire ( notification_flag ? "on" : "off" );
        data_form.setNumMinutesPrior    ( Long.toString ( rule.getNotifyMinutes() ) );

        setSupStartDate ( data_form, start_time.getTime() );
        setSupEndDate   ( data_form, end_time.getTime()   );

        return data_form;
    }

    private static SuppressionRule convert ( AddEntry data_form )
    {
        SuppressionRule rule    = new SuppressionRule();
        Calendar start_time     = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        Calendar end_time       = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        long notify_num_minutes = Long.parseLong ( data_form.getNumMinutesPrior() );

        start_time.setTime ( getSupStartDate ( data_form ) );
        end_time.setTime   ( getSupEndDate   ( data_form ) );

        _log.info ( "Notify on expiration? " + data_form.getNotifyBeforeExpire() );

        rule.setDescription        ( data_form.getDescription()  );
        rule.setApplicationName    ( data_form.getApplication()  );
        rule.setDatabaseServerName ( data_form.getDbServer()     );
        rule.setStartTime          ( start_time                  );
        rule.setEndTime            ( end_time                    );
        rule.setNodeName           ( data_form.getNode()         );
        rule.setOwner              ( data_form.getUsername()     );
        rule.setDeletedFlag        ( false                       );
        rule.setIsNotified         ( false                       );
        rule.setNotifyMinutes      ( notify_num_minutes          );
        rule.setMessage            ( data_form.getDbServerMsg()  );
        rule.setNotifyEmail        ( data_form.getEmail()        );
        rule.setNotificationFlag   ( data_form.getNotifyBeforeExpire().equals ( "checked" ) );
        rule.setRemoveOnReboot     ( data_form.getRemoveOnReboot().equals ( "checked" ) );

        return rule;
    }

    private static SuppressionRule convert ( EditEntry data_form )
    {
        SuppressionRule rule    = new SuppressionRule();
        Calendar start_time     = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        Calendar end_time       = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        long notify_num_minutes = Long.parseLong ( data_form.getNumMinutesPrior() );

        start_time.setTime ( getSupStartDate ( data_form ) );
        end_time.setTime   ( getSupEndDate   ( data_form ) );

        rule.setSuppressId         ( Long.parseLong ( data_form.getSuppressId() ) );
        rule.setDescription        ( data_form.getDescription()  );
        rule.setApplicationName    ( data_form.getApplication()  );
        rule.setDatabaseServerName ( data_form.getDbServer()     );
        rule.setStartTime          ( start_time                  );
        rule.setEndTime            ( end_time                    );
        rule.setNodeName           ( data_form.getNode()         );
        rule.setOwner              ( data_form.getUsername()     );
        rule.setDeletedFlag        ( false                       );
        rule.setIsNotified         ( false                       );
        rule.setNotifyMinutes      ( notify_num_minutes          );
        rule.setMessage            ( data_form.getDbServerMsg()  );
        rule.setNotificationFlag   ( data_form.getNotifyBeforeExpire().equals ( "checked" ) );
        rule.setRemoveOnReboot     ( data_form.getRemoveOnReboot().equals ( "checked" ) );

        return rule;
    }

    public static void refreshDatabaseConnections ( RenderRequest renderRequest ) throws SQLException, ClassNotFoundException, IOException
    {
        con_data_source = null;
        checkDataSourceDatabaseConnection ( renderRequest );
        
        con_instance = null;
        checkInstanceDatabaseConnection ( renderRequest );
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

    public static String getDataSourceDatabaseConnectionURL()
    {
        return ds_db_connection_url;
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

    private static void checkDataSourceDatabaseConnection ( RenderRequest renderRequest ) throws ClassNotFoundException, SQLException, IOException
    {
        if ( null == con_data_source )
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

    /**
     * Populates the toolkit with the values stored in the java.util.Properties object that this object uses
     */
    private static void retrieveProperties()
    {
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
    }
}
