package com.bgi.esm.portlets.Suppression.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import java.io.IOException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.forms.AddEntry;
import com.bgi.esm.portlets.Suppression.forms.SuppressionDate;
/**
 * <a href="SubscribeForm.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 *
 */
public class EditEntry extends ActionForm implements SuppressionDate {
    /**
     * 
     */
    private static final long serialVersionUID = 2519676828996142138L;
    final private static Logger _log           = Logger.getLogger ( EditEntry.class );

    public void DumpToFile ( String filename )
    {
    	StringBuilder contents = new StringBuilder();
    	contents.append ( "<html>\n" );
    	contents.append ( "    <body>\n" );
    	contents.append ( "        <table border='1'>\n" );
    	contents.append ( "            <tr>\n" );
    	contents.append ( "                <td>Suppress ID</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _suppress_id );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup Start Choice</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _start_choice );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup Start Year</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_start_year );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup Start Month</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_start_month );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup Start Date</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_start_date );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup Start Hour</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_start_hour );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup Start Minute</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_start_minute );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup Start AM/PM</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_start_ampm );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup End Choice</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _end_choice );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup End Year</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_end_year );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup End Month</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_end_month );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup End Date</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_end_date );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup End Hour</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_end_hour );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup End Minute</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_end_minute );
    	contents.append ( "</td>\n" );
    	contents.append ( "                <td>Sup End AM/PM</td>\n" );
    	contents.append ( "                <td>" );
    	contents.append ( _sup_end_ampm );
    	contents.append ( "</td>\n" );
    	contents.append ( "            </tr>\n" );
    	contents.append ( "        </table>\n" );
    	contents.append ( "    </body>\n" );
    	contents.append ( "</html>\n" );
    	try
    	{
    		FileOutputStream outfile = new FileOutputStream ( filename );
    			outfile.write ( contents.toString().getBytes() );
    	    outfile.close();
    	}
    	catch ( IOException exception )
    	{
    		exception.printStackTrace();
    	}
    }

    public String getDescription () {
        return _description;
    }

    public void setDescription ( String param ) {
        _description = param;
    }

    public String getApplication() {
        return _application;
    }

    public void setApplication ( String param ) {
        _application = param;
    }

    public String getDbServer() {
        return _db_server;
    }

    public void setDbServer ( String param ) {
        _db_server = param;
    }

    public String getDbServerMsg() {
        return _db_server_msg;
    }

    public void setDbServerMsg ( String param ) {
        _db_server_msg = param;
    }

    public String getSupStartYear() {
        return _sup_start_year;
    }

    public void setSupStartYear ( String param ) {
        _sup_start_year = param;
    }

    public int getSupStartMonthInt() {
        return Integer.parseInt ( _sup_start_month );
    }

    public String getSupStartMonth() {
        return _sup_start_month;
    }

    public void setSupStartMonth ( String param ) {
        _sup_start_month = param;
    }

    public String getSupStartDate() {
        return _sup_start_date;
    }

    public void setSupStartDate ( String param ) {
        _sup_start_date = param;
    }

    public String getSupStartHour() {
        return _sup_start_hour;
    }

    public void setSupStartHour ( String param ) {
        _sup_start_hour = param;
    }

    public String getSupStartMinute() {
        return _sup_start_minute;
    }

    public void setSupStartMinute ( String param ) {
        _sup_start_minute = param;
    }

    public String getSupStartAmpm() {
        return _sup_start_ampm;
    }

    public void setSupStartAmpm ( String param ) {
        _sup_start_ampm = param;
    }

    public String getByApplication() {
        return _by_application;
    }

    public void setByApplication ( String param ) {
        _by_application = param;
    }

    public String getByNode() {
        return _by_node;
    }

    public void setByNode ( String param ) {
        _by_node = param;
    }

    public String getNode() {
        return _node;
    }

    public void setNode ( String param ) {
        _node = param;
    }

    public String getWithDbServerInstance() {
        return _with_db_server_instance;
    }

    public void setWithDbServerInstance( String param ) {
        _with_db_server_instance = param;
    }


    public String getWithDbServer() {
        return _with_db_server;
    }

    public void setWithDbServer( String param ) {
        _with_db_server = param;
    }

    public String getWithDbServerMsg() {
        return _with_db_server_msg;
    }

    public void setWithDbServerMsg( String param ) {
        _with_db_server_msg = param;
    }

    public String getNotifyBeforeExpire() {
        return _notify_before_expire;
    }

    public void setNotifyBeforeExpire ( String param ) {
        _notify_before_expire = param;
    }
    
    public String getSupEndYear() {
        return _sup_end_year;
    }

    public void setSupEndYear ( String param ) {
        _sup_end_year = param;
    }

    public int getSupEndMonthInt() {
        return Integer.parseInt ( _sup_end_month );
    }

    public String getSupEndMonth() {
        return _sup_end_month;
    }

    public void setSupEndMonth ( String param ) {
        _sup_end_month = param;
    }

    public String getSupEndDate() {
        return _sup_end_date;
    }

    public void setSupEndDate ( String param ) {
        _sup_end_date = param;
    }

    public String getSupEndHour() {
        return _sup_end_hour;
    }

    public void setSupEndHour ( String param ) {
        _sup_end_hour = param;
    }

    public String getSupEndMinute() {
        return _sup_end_minute;
    }

    public void setSupEndMinute ( String param ) {
        _sup_end_minute = param;
    }

    public String getSupEndAmpm() {
        return returnWildCardIfNull ( _sup_end_ampm );
    }

    public void setSupEndAmpm ( String param ) {
        _sup_end_ampm = param;
    }

    public String getEndChoice() {
        return returnWildCardIfNull ( _end_choice );
    }

    public void setEndChoice ( String param ) {
        _end_choice = param;
    }

    public String getIncludeDeleted () {
        return returnWildCardIfNull ( _include_deleted );
    }

    public void setIncludeDeleted ( String param ) {
        _include_deleted = param;
    }

    public String getRemoveOnReboot () {
        return returnWildCardIfNull ( _remove_on_reboot );
    }

    public void setRemoveOnReboot ( String param ) {
        if ( null == param )
        {
            param = "off";
        }
        _remove_on_reboot = param;
    }


    public String getStartChoice() {
        return _start_choice;
    }

    public void setStartChoice ( String param ) {
        _start_choice = param;
    }

    public String getNumMinutesPrior() {
        return _num_min_prior;
    }

    public void setNumMinutesPrior ( String param ) {
        try
        {
            Integer i = new Integer ( param );
            _num_min_prior = i.toString();
        }
        catch ( NumberFormatException e )
        {
            _num_min_prior = "0";
        }
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail ( String param ) {
        _email = param;
    }
    
    public String getEndChoiceNum() {
        return _end_choice_num;
    }

    public void setEndChoiceNum ( String param ) {
        _end_choice_num = param;
    }

    public String getEndChoiceUnit() {
        return _end_choice_unit;
    }

    public void setEndChoiceUnit ( String param ) {
        _end_choice_unit = param;
    }

        public String getOnlySunday() {
        return _onlySunday;
    }
    
    public void setOnlySunday ( String param ) {
        _onlySunday = param;
    }
    
    public String getOnlyMonday () {
        return _onlyMonday;
    }
    
    public void setOnlyMonday ( String param ) {
        _onlyMonday = param;
    }

    public String getOnlyTuesday () {
        return _onlyTuesday;
    }
    
    public void setOnlyTuesday ( String param ) {
        _onlyTuesday = param;
    }

    public String getOnlyWednesday () {
        return _onlyWednesday;
    }
    
    public void setOnlyWednesday ( String param ) {
        _onlyWednesday = param;
    }

    public String getOnlyThursday () {
        return _onlyThursday;
    }
    
    public void setOnlyThursday ( String param ) {
        _onlyThursday = param;
    }

    public String getOnlyFriday () {
        return _onlyFriday;
    }
    
    public void setOnlyFriday ( String param ) {
        _onlyFriday = param;
    }

    public String getOnlySaturday () {
        return _onlySaturday;
    }
    
    public void setOnlySaturday ( String param ) {
        _onlySaturday = param;
    }

	public void setTimeZoneOffset ( String param ) {
		_timezone_offset = param;
	}

	public String getTimeZoneOffset () {
		return _timezone_offset;
	}

    public void reset(ActionMapping mapping, HttpServletRequest req) 
    {
        System.out.println ( "com.bgi.esm.portlets.Suppression.actions.EditEntryAction::reset" );
        LogToFile ( "com.bgi.esm.portlets.Suppression.actions.EditEntryAction::reset" );
        String forwards[] = mapping.findForwards();
        for ( int counter = 0; counter < forwards.length; counter++ )
        {
            LogToFile ( "\tforward = "+forwards[counter] );
        }

        HashMap <String, String> parameters = Toolkit.retrieveHttpRequestParameters ( req );

        String suppress_id = parameters.get ( "suppress_id" );
        try
        {
            AddEntry data_form = Toolkit.retrieveEntry ( Integer.parseInt ( suppress_id ) );

            copyFromAddForm ( data_form );

            _sup_start_year   = cleanUpInteger ( _sup_start_year );
            _sup_start_month  = cleanUpIntegerBaseZero ( _sup_start_month );
            _sup_start_date   = cleanUpInteger ( _sup_start_date );
            _sup_start_hour   = cleanUpInteger ( _sup_start_hour );
            _sup_start_minute = cleanUpInteger ( _sup_start_minute );

            _sup_end_year     = cleanUpInteger ( _sup_end_year );
            _sup_end_month    = cleanUpIntegerBaseZero ( _sup_end_month );
            _sup_end_date     = cleanUpInteger ( _sup_end_date );
            _sup_end_hour     = cleanUpInteger ( _sup_end_hour );
            _sup_end_minute   = cleanUpInteger ( _sup_end_minute );

            if (( null != _node ) && ( _node.length() > 1 ))
            {
                _by_node = "on";
            }
            if (( null != _application ) && ( _application.length() > 1 ))
            {
                _by_application = "on";
            }

            if (( null != _db_server_msg ) && ( _db_server_msg.trim().length() > 0 ))
            {
                _with_db_server="on";
                _with_db_server_msg="on";
            }
            if (( null != _db_server ) && ( _db_server.trim().length() > 0 ))
            {
                _with_db_server="on";
                _with_db_server_instance="on";
            }

            if (( null != _num_min_prior ) && ( _num_min_prior.trim().length() > 0 ))
            {
                _notify_before_expire = "on";
            }

            if (( null != _include_deleted ) && ( _include_deleted.length() > 0 ))
            {
                _include_deleted = "on";
            }

            int num_min_prior         = 0;
            try
            {
                num_min_prior         = Integer.parseInt ( _num_min_prior );
                if ( Integer.MIN_VALUE == num_min_prior )
                {
                    _num_min_prior = "0";
                    _notify_before_expire = "";
                }
            }
            catch ( NumberFormatException exception )
            {
                _notify_before_expire = "";
            }
        }
        catch ( Exception exception )
        {
            _onlySunday           = "";
            _onlyMonday           = "";
            _onlyTuesday          = "";
            _onlyWednesday        = "";
            _onlyThursday         = "";
            _onlyFriday           = "";
            _onlySaturday         = "";
            _notify_before_expire = "";
            _description          = "";
            _by_application       = "";
            _application          = "";
            _by_node              = "";
            _node                 = "";
            _with_db_server       = "";
            _db_server            = "";
            _with_db_server_msg   = "";
            _db_server_msg        = "";
            _sup_start_year       = "";
            _sup_start_month      = "";
            _sup_start_date       = "";
            _sup_start_hour       = "";
            _sup_start_minute     = "";
            _sup_start_ampm       = "";
            _remove_on_reboot     = "";
            _sup_end_year         = "";
            _sup_end_month        = "";
            _sup_end_date         = "";
            _sup_end_hour         = "";
            _sup_end_minute       = "";
            _sup_end_ampm         = "";
            _end_choice           = "";
            _end_choice_num       = "";
            _end_choice_unit      = "";
            _start_choice         = "";
            _num_min_prior        = "0";
            _email                = "";
        }

        _start_choice             = "later";
        _end_choice               = "specified";
    }

    private String cleanUpIntegerBaseZero ( String value )
    {
        int i = Integer.parseInt ( value ) - 1;

        return Integer.toString ( i );
    }

    private String cleanUpInteger ( String value )
    {
        int i = Integer.parseInt ( value );

        return Integer.toString ( i );
    }

    public ActionErrors validate(
        ActionMapping mapping, HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        
        //  Validate the description
        if (( null != _description ) && ( _description.length() < 4 ))
        {
            errors.add( "description", new ActionMessage( "The description is too short", false ));
        }

        System.out.println ( "Retrieving start/stop times from form" );
        Date sup_start_date = Toolkit.getSupStartDate ( this );
        Date sup_end_date   = Toolkit.getSupEndDate   ( this );
        System.out.println ( "\tStart: " + sup_start_date.toString() );
        System.out.println ( "\tEnd:   " + sup_end_date.toString() );

        int timezone_offset = 0;
        
        try
        {
            timezone_offset = Toolkit.computeTimeZoneOffset ( req );
        }
        catch ( NullPointerException exception )
        {
            _log.error ( "Retrieving time zone offset from form" );
            timezone_offset = Integer.parseInt ( getTimeZoneOffset() );
        }

        //  If we are using a specified date for the start date, we need to make sure that the 
        //  offset gets applied
        if ( !getStartChoice().equals ( "now" ) )
        {
            System.out.println ( "Specified start time" );
            sup_start_date  = new Date ( sup_start_date.getTime() - timezone_offset );
        }

        if ( !getEndChoice().equals ( "offset" ) )
        {
            System.out.println ( "Specified end time" );
            //sup_end_date  = new Date ( sup_end_date.getTime() - timezone_offset );
        }
        else
        {
            System.out.println ( "End time type: " + getEndChoice() );
        }
        sup_end_date  = new Date ( sup_end_date.getTime() - timezone_offset );

        System.out.println ( "Edit suppression validation for suppress ID #" + getSuppressId() + ":" );
        System.out.println ( "\tStart:  " + sup_start_date.toString() );
        System.out.println ( "\tEnd:    " + sup_end_date.toString() );
        System.out.println ( "\tOffset: " + timezone_offset );
        
        long date_start     = sup_start_date.getTime();
        long date_end       = sup_end_date.getTime();
        long date_now       = System.currentTimeMillis();
        
        //  Make sure that the end date is after the start date
        if ( date_end < date_start )
        {
            StringBuilder message = new StringBuilder ( "The end date ( " );
            message.append ( sup_end_date.toString() );
            message.append ( ", " );
            message.append ( date_end );
            message.append ( " ) for this suppression is before the start date ( " );
            message.append ( sup_start_date.toString() );
            message.append ( ", " );
            message.append ( date_start );
            message.append ( " )" );

            //errors.add ( "supStartDate", new ActionMessage ( "The end date for this suppression is before the start date", false ) );
            _log.error ( message.toString() );
            errors.add ( "supStartDate", new ActionMessage ( message.toString(), false ) );

            message = null;
        }
        
        //  Make sure that the end date is after the current time
        if ( getEndChoice().equals ( "specified" ) && ( date_end < date_now ))
        {
            _log.error ( "The end date must be a date/time in the future" );
            errors.add ( "supStartDate", new ActionMessage ( "The end date must be a date/time in the future", false) );
        }
        
        //  Make sure that at least either application or node is specified
        boolean is_specified_app  = (( null != _by_application ) && ( _by_application.equals ( "on" ) ) && ( null != _application ) && ( !_application.equals ( "" ) ) && ( !_application.equals ( "-- all applications --" ) ) );
        boolean is_specified_node = (( null != _by_node ) && ( _by_node.equals ( "on" ) ) && ( null != _node ) && ( !_node.equals ( "" ) ) && ( !_node.equals ( "-- all nodes --" ) ) );
        
        if ( !is_specified_app && !is_specified_node )
        {
            errors.add ( "application", new ActionMessage ( "You must specify either at least a node or an application", false ) );
        }
        
        return errors;
    }
    
    public ActionErrors validate2(
        ActionMapping mapping, HttpServletRequest req) {

        ActionErrors errors = new ActionErrors();

        //  Validate the description
        if (( null != _description ) && ( _description.length() < 4 ))
        {
            errors.add( "description", new ActionMessage( "The description is too short", false ));
        }

        Date sup_start_date = Toolkit.getSupStartDate ( this );
        Date sup_end_date   = Toolkit.getSupEndDate   ( this );

        System.out.println ( "Edit suppression validation: " );
        System.out.println ( "\tStart: " + sup_start_date.toString() );
        System.out.println ( "\tEnd:   " + sup_end_date.toString() );

        int offset = Toolkit.computeTimeZoneOffset ( req );
        
        long date_start     = sup_start_date.getTime() - offset;
        long date_end       = sup_end_date.getTime() - offset;
        long date_now       = System.currentTimeMillis();
        
        //  Make sure that the end date is after the start date
        if ( date_end < date_start )
        {
            errors.add ( "supStartDate", new ActionMessage ( "The end date for this suppression is before the start date", false ) );
        }
        
        //  Make sure that the end date is after the current time
        if ( ( !getEndChoice().equals ( "offset" ) ) && ( date_end < date_now ))
        {
            errors.add ( "supStartDate", new ActionMessage ( "The end date must be a date/time in the future", false) );
        }
        
        //  Make sure that at least either application or node is specified
        boolean is_specified_app  = (( null != _by_application ) && ( _by_application.equals ( "on" ) ) && ( null != _application ) && ( !_application.equals ( "" ) ) && ( !_application.equals ( "-- all applications --" ) ) );
        boolean is_specified_node = (( null != _by_node ) && ( _by_node.equals ( "on" ) ) && ( null != _node ) && ( !_node.equals ( "" ) ) && ( !_node.equals ( "-- all nodes --" ) ) );
        
        if ( !is_specified_app && !is_specified_node )
        {
            errors.add ( "application", new ActionMessage ( "You must specify either at least a node or an application", false ) );
        }
        
        return errors;
    }

    public void setMessageGroups ( Vector v ) {
        _message_groups = new String[v.size()];
        
        Enumeration e = v.elements();
        int counter = 0;
        while ( e.hasMoreElements() )
        {
            _message_groups[counter++] = e.nextElement().toString();
        }
    }

    public void setMessageGroups ( String[] data ) {
        _message_groups = data;
    }

    public String[] getMessageGroups () {
        return _message_groups;
    }

    public String toString() {
        return "";
    }

    public void setDataServers ( Vector v ) {
        _data_servers = new String[v.size()];
        
        Enumeration e = v.elements();
        int counter = 0;
        while ( e.hasMoreElements() )
        {
            _data_servers[counter++] = e.nextElement().toString();
        }
    }

    public void setDataServers ( String[] data ) {
        _data_servers = data;
    }

    public String[] getDataServers () {
        return _data_servers;
    }
    
    public void setNodeNames ( Vector v ) {
        
        Enumeration e = v.elements();
        if ( null != e )
        {
            _node_names = new String[v.size()];
            int counter = 0;
            while ( e.hasMoreElements() )
            {
                _node_names[counter++] = e.nextElement().toString();
            }
        }
        else
        {
            _node_names = new String[0];
        }
    }

    public void setNodeNames ( String[] data ) {
        _node_names = data;
    }

    public String[] getNodeNames () {
        return _node_names;
    }

    public void setUsername ( String param ) {
        _username = param;
    }

    public String getUsername () {
        return returnWildCardIfNull ( _username );
    }

    public void setSuppressId ( String param ) {
        _suppress_id = param;
    }

    public String getSuppressId () {
        return _suppress_id;
    }

    public void setStartTimeUTC ( String param ) {        
        StringTokenizer tokenizer = new StringTokenizer ( param, "-: " );
        
        setSupStartYear   ( tokenizer.nextToken() );
        setSupStartMonth  ( tokenizer.nextToken() );
        setSupStartDate   ( tokenizer.nextToken() );
        int hour_of_day = Integer.parseInt ( tokenizer.nextToken() );
        if ( hour_of_day < 12 )
        {
            setSupStartHour ( ""+hour_of_day );
            setSupStartAmpm ( "AM" );
        }
        else
        {
            setSupStartHour ( ""+(hour_of_day-12) );
            setSupStartAmpm ( "PM" );
        }
        setSupStartMinute ( tokenizer.nextToken() );
    }

    public void setEndTimeUTC ( String param ) {
        StringTokenizer tokenizer = new StringTokenizer ( param, "-: " );
        
        setSupEndYear   ( tokenizer.nextToken() );
        setSupEndMonth  ( tokenizer.nextToken() );
        setSupEndDate   ( tokenizer.nextToken() );
        int hour_of_day = Integer.parseInt ( tokenizer.nextToken() );
        if ( hour_of_day < 12 )
        {
            setSupEndHour ( ""+hour_of_day );
            setSupEndAmpm ( "AM" );
        }
        else
        {
            setSupEndHour ( ""+(hour_of_day-12) );
            setSupEndAmpm ( "PM" );
        }
        setSupEndMinute ( tokenizer.nextToken() );
    }

    public void saveToFile ( String filename )
    {
        StringBuilder contents = new StringBuilder ( "Created on " );
        contents.append ( (new java.util.Date()).toString() );
        contents.append ( "*\nonlySunday              = *" );
        contents.append ( _onlySunday );
        contents.append ( "*\nonlyMonday              = *" );
        contents.append ( _onlyMonday );
        contents.append ( "*\nonlyTuesday             = *" );
        contents.append ( _onlyTuesday );
        contents.append ( "*\nonlyWednesday           = *" );
        contents.append ( _onlyWednesday );
        contents.append ( "*\nonlyThursday            = *" );
        contents.append ( _onlyThursday );
        contents.append ( "*\nonlyFriday              = *" );
        contents.append ( _onlyFriday );
        contents.append ( "*\nonlySaturday            = *" );
        contents.append ( _onlySaturday );
        contents.append ( "*\nsuppress_id             = *" );
        contents.append ( _suppress_id );
        contents.append ( "*\nnotify_before_expire    = *" );
        contents.append ( _notify_before_expire );
        contents.append ( "*\ndescription             = *" );
        contents.append ( _description );
        contents.append ( "*\nby_application          = *" );
        contents.append ( _by_application );
        contents.append ( "*\napplication             = *" );
        contents.append ( _application );
        contents.append ( "*\nby_node                 = *" );
        contents.append ( _by_node );
        contents.append ( "*\nnode                    = *" );
        contents.append ( _node );
        contents.append ( "*\nwith_db_server          = *" );
        contents.append ( _with_db_server );
        contents.append ( "*\ndb_server               = *" );
        contents.append ( _db_server );
        contents.append ( "*\nwith_db_server_msg      = *" );
        contents.append ( _with_db_server_msg );
        contents.append ( "*\nwith_db_server_instance = *" );
        contents.append ( _with_db_server_instance );
        contents.append ( "*\ndb_server_msg           = *" );
        contents.append ( _db_server_msg );
        contents.append ( "*\nsup_start_year          = *" );
        contents.append ( _sup_start_year );
        contents.append ( "*\nsup_start_month         = *" );
        contents.append ( _sup_start_month );
        contents.append ( "*\nsup_start_date          = *" );
        contents.append ( _sup_start_date );
        contents.append ( "*\nsup_start_hour          = *" );
        contents.append ( _sup_start_hour );
        contents.append ( "*\nsup_start_minute        = *" );
        contents.append ( _sup_start_minute );
        contents.append ( "*\nsup_start_ampm          = *" );
        contents.append ( _sup_start_ampm );
        contents.append ( "*\nremove_no_reboot        = *" );
        contents.append ( _remove_on_reboot );
        contents.append ( "*\nsup_end_year            = *" );
        contents.append ( _sup_end_year );
        contents.append ( "*\nsup_end_month           = *" );
        contents.append ( _sup_end_month );
        contents.append ( "*\nsup_end_date            = *" );
        contents.append ( _sup_end_date );
        contents.append ( "*\nsup_end_hour            = *" );
        contents.append ( _sup_end_hour );
        contents.append ( "*\nsup_end_minute          = *" );
        contents.append ( _sup_end_minute );
        contents.append ( "*\nsup_end_ampm            = *" );
        contents.append ( _sup_end_ampm );
        contents.append ( "*\nend_choice              = *" );
        contents.append ( _end_choice );
        contents.append ( "*\nend_choice_num          = *" );
        contents.append ( _end_choice_num );
        contents.append ( "*\nend_choice_unit         = *" );
        contents.append ( _end_choice_unit );
        contents.append ( "*\nstart_choice            = *" );
        contents.append ( _start_choice );
        contents.append ( "*\nnum_min_prior           = *" );
        contents.append ( _num_min_prior );
        contents.append ( "*\nemail                   = *" );
        contents.append ( _email );
        contents.append ( "*\nusername                = *" );
        contents.append ( _username );

        try
        {
            FileOutputStream outfile = new FileOutputStream ( filename );
                outfile.write ( contents.toString().getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
        }
    }

    public void copyFromEditForm ( EditEntry edit_form )
    {
        _onlySunday              = edit_form.getOnlySunday();
        _onlyMonday              = edit_form.getOnlyMonday();
        _onlyTuesday             = edit_form.getOnlyTuesday();
        _onlyWednesday           = edit_form.getOnlyWednesday();
        _onlyThursday            = edit_form.getOnlyThursday();
        _onlyFriday              = edit_form.getOnlyFriday();
        _onlySaturday            = edit_form.getOnlySaturday();
        _notify_before_expire    = edit_form.getNotifyBeforeExpire();
        _description             = edit_form.getDescription();
        _by_application          = edit_form.getByApplication();
        _application             = edit_form.getApplication();
        _by_node                 = edit_form.getByNode();
        _node                    = edit_form.getNode();
        _with_db_server          = edit_form.getWithDbServer();
        _with_db_server_instance = edit_form.getWithDbServerInstance();
        _db_server               = edit_form.getDbServer();
        _with_db_server_msg      = edit_form.getWithDbServerMsg();
        _db_server_msg           = edit_form.getDbServerMsg();
        _sup_start_year          = edit_form.getSupStartYear();
        _sup_start_month         = edit_form.getSupStartMonth();
        _sup_start_date          = edit_form.getSupStartDate();
        _sup_start_hour          = edit_form.getSupStartHour();
        _sup_start_minute        = edit_form.getSupStartMinute();
        _sup_start_ampm          = edit_form.getSupStartAmpm();
        _remove_on_reboot        = edit_form.getRemoveOnReboot();
        _sup_end_year            = edit_form.getSupEndYear();
        _sup_end_month           = edit_form.getSupEndMonth();
        _sup_end_date            = edit_form.getSupEndDate();
        _sup_end_hour            = edit_form.getSupEndHour();
        _sup_end_minute          = edit_form.getSupEndMinute();
        _sup_end_ampm            = edit_form.getSupEndAmpm();
        _end_choice              = edit_form.getEndChoice();
        _end_choice_num          = edit_form.getEndChoiceNum();
        _end_choice_unit         = edit_form.getEndChoiceUnit();
        _start_choice            = edit_form.getStartChoice();
        _suppress_id             = edit_form.getSuppressId();
        _num_min_prior           = edit_form.getNumMinutesPrior();
        _email                   = edit_form.getEmail();
        _username                = edit_form.getUsername();
        _message_groups          = edit_form.getMessageGroups();
        _node_names              = edit_form.getNodeNames();
        _data_servers            = edit_form.getDataServers();
        start_timestamp          = edit_form.getStartTimestamp();
        end_timestamp            = edit_form.getEndTimestamp();
    }

    public void copyFromAddForm ( AddEntry add_form )
    {
        _onlySunday              = add_form.getOnlySunday();
        _onlyMonday              = add_form.getOnlyMonday();
        _onlyTuesday             = add_form.getOnlyTuesday();
        _onlyWednesday           = add_form.getOnlyWednesday();
        _onlyThursday            = add_form.getOnlyThursday();
        _onlyFriday              = add_form.getOnlyFriday();
        _onlySaturday            = add_form.getOnlySaturday();
        _notify_before_expire    = add_form.getNotifyBeforeExpire();
        _description             = add_form.getDescription();
        _by_application          = add_form.getByApplication();
        _application             = add_form.getApplication();
        _by_node                 = add_form.getByNode();
        _node                    = add_form.getNode();
        _with_db_server          = add_form.getWithDbServer();
        _with_db_server_instance = add_form.getWithDbServerInstance();
        _db_server               = add_form.getDbServer();
        _with_db_server_msg      = add_form.getWithDbServerMsg();
        _db_server_msg           = add_form.getDbServerMsg();
        _sup_start_year          = add_form.getSupStartYear();
        _sup_start_month         = add_form.getSupStartMonth();
        _sup_start_date          = add_form.getSupStartDate();
        _sup_start_hour          = add_form.getSupStartHour();
        _sup_start_minute        = add_form.getSupStartMinute();
        _sup_start_ampm          = add_form.getSupStartAmpm();
        _remove_on_reboot        = add_form.getRemoveOnReboot();
        _sup_end_year            = add_form.getSupEndYear();
        _sup_end_month           = add_form.getSupEndMonth();
        _sup_end_date            = add_form.getSupEndDate();
        _sup_end_hour            = add_form.getSupEndHour();
        _sup_end_minute          = add_form.getSupEndMinute();
        _sup_end_ampm            = add_form.getSupEndAmpm();
        _end_choice              = add_form.getEndChoice();
        _end_choice_num          = add_form.getEndChoiceNum();
        _end_choice_unit         = add_form.getEndChoiceUnit();
        _start_choice            = add_form.getStartChoice();
        _suppress_id             = add_form.getSuppressId();
        _num_min_prior           = add_form.getNumMinutesPrior();
        _email                   = add_form.getEmail();
        _username                = add_form.getUsername();
        _message_groups          = add_form.getMessageGroups();
        _node_names              = add_form.getNodeNames();
        _data_servers            = add_form.getDataServers();
        start_timestamp          = add_form.getStartTimestamp();
        end_timestamp            = add_form.getEndTimestamp();
    }
        
    public boolean notInitialized() 
    {
        return (( null == _node_names ) || ( null == _message_groups ) || ( null == _data_servers ));
    }
    
    public Timestamp getStartTimestamp()
    {
    	return start_timestamp;
    }
    
    public void setStartTimestamp ( Timestamp new_timestamp )
    {
    	start_timestamp = new_timestamp;
    }
    
    public Timestamp getEndTimestamp()
    {
    	return end_timestamp;
    }
    
    public void setEndTimestamp ( Timestamp new_timestamp )
    {
    	end_timestamp = new_timestamp;
    }

    public String returnWildCardIfNull ( String value )
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

    public void LogToFile ( String message )
    {
        try
        {
            if ( false )
            {
                FileOutputStream outfile = new FileOutputStream( "c:\\test\\editentryform.out", true );
                    outfile.write ( (new java.util.Date()).toString().getBytes() );
                    outfile.write ( " - ".getBytes() );
                    outfile.write ( message.getBytes() );
                    outfile.write ( "\n".getBytes() );
                outfile.close();
            }
        }
        catch ( IOException exception )
        {
            exception.printStackTrace();
        }
    }

    private String _onlySunday              = "";
    private String _onlyMonday              = "";
    private String _onlyTuesday             = "";
    private String _onlyWednesday           = "";
    private String _onlyThursday            = "";
    private String _onlyFriday              = "";
    private String _onlySaturday            = "";
    private String _suppress_id             = "";
    private String _notify_before_expire    = "";
    private String _description             = "";
    private String _by_application          = "";
    private String _application             = "";
    private String _by_node                 = "";
    private String _node                    = "";
    private String _with_db_server          = "";
    private String _with_db_server_instance = "";
    private String _db_server               = "";
    private String _with_db_server_msg      = "";
    private String _db_server_msg           = "";
    private String _sup_start_year          = "";
    private String _sup_start_month         = "";
    private String _sup_start_date          = "";
    private String _sup_start_hour          = "";
    private String _sup_start_minute        = "";
    private String _sup_start_ampm          = "";
    private String _sup_end_year            = "";
    private String _sup_end_month           = "";
    private String _sup_end_date            = "";
    private String _sup_end_hour            = "";
    private String _sup_end_minute          = "";
    private String _sup_end_ampm            = "";
    private String _end_choice              = "";
    private String _end_choice_num          = "";
    private String _end_choice_unit         = "";
    private String _remove_on_reboot        = "";
    private String _include_deleted         = "";
    private String _start_choice            = "";
    private String _num_min_prior           = "0";
    private String _email                   = "";
    private String _username                = "";
	private String _timezone_offset         = "";
    private Timestamp start_timestamp       = null;
    private Timestamp end_timestamp         = null;
    private String[] _message_groups        = null;
    private String[] _node_names            = null;
    private String[] _data_servers          = null;
}

