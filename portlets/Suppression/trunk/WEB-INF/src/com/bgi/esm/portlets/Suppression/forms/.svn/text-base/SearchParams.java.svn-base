package com.bgi.esm.portlets.Suppression.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.Vector;
import java.util.Enumeration;
import java.util.StringTokenizer;

/**
 * <a href="SubscribeForm.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 *
 */
public class SearchParams extends ActionForm {
    /**
     * 
     */
    private static final long serialVersionUID = -650527819674905762L;
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

	public String getIncludeDeleted () {
		return _include_deleted;
	}

	public void setIncludeDeleted ( String param ) {
		_include_deleted = param;
	}

	public String getRemoveOnReboot () {
		return _remove_on_reboot;
	}

	public void setRemoveOnReboot ( String param ) {
		_remove_on_reboot = param;
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
        return _sup_end_ampm;
    }

    public void setSupEndAmpm ( String param ) {
        _sup_end_ampm = param;
    }

    public String getEndChoice() {
        return _end_choice;
    }

    public void setEndChoice ( String param ) {
        _end_choice = param;
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
        _num_min_prior = param;
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

    public void reset(ActionMapping mapping, HttpServletRequest req) {
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
	    _include_deleted      = "no-deleted";
        _sup_end_year         = "";
        _sup_end_month        = "";
        _sup_end_date         = "";
        _sup_end_hour         = "";
        _sup_end_minute       = "";
        _sup_end_ampm         = "";
        _end_choice           = "now";
        _end_choice_num       = "";
        _end_choice_unit      = "";
        _start_choice         = "beginning";
        _num_min_prior        = "";
        _email                = "";
    }

    public ActionErrors validate(
        ActionMapping mapping, HttpServletRequest req) {

        ActionErrors errors = new ActionErrors();

        //  Validate the start date
        if ( null != _sup_start_year )
        {
        }

        //  Validate the ending date

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
        return _username;
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
        if ( hour_of_day == 0 )
        {
            setSupStartHour ( "12" );
            setSupStartAmpm ( "AM" );
        }
        else if ( hour_of_day < 12 )
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
        if ( hour_of_day == 0 )
        {
            setSupEndHour ( "12" );
            setSupEndAmpm ( "AM" );
        }
        else if ( hour_of_day < 12 )
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
        
    public boolean notInitialized() 
    {
        return (( null == _node_names ) || ( null == _message_groups ) || ( null == _data_servers ));
    }

    private String _onlySunday           = "";
    private String _onlyMonday           = "";
    private String _onlyTuesday          = "";
    private String _onlyWednesday        = "";
    private String _onlyThursday         = "";
    private String _onlyFriday           = "";
    private String _onlySaturday         = "";
    private String _suppress_id          = "";
    private String _notify_before_expire = "";
    private String _description          = "";
    private String _by_application       = "";
    private String _application          = "";
    private String _by_node              = "";
    private String _node                 = "";
    private String _with_db_server       = "";
    private String _db_server            = "";
    private String _with_db_server_msg   = "";
    private String _db_server_msg        = "";
    private String _sup_start_year       = "";
    private String _sup_start_month      = "";
    private String _sup_start_date       = "";
    private String _sup_start_hour       = "";
    private String _sup_start_minute     = "";
    private String _sup_start_ampm       = "";
	private String _remove_on_reboot     = "";
	private String _include_deleted      = "";
    private String _sup_end_year         = "";
    private String _sup_end_month        = "";
    private String _sup_end_date         = "";
    private String _sup_end_hour         = "";
    private String _sup_end_minute       = "";
    private String _sup_end_ampm         = "";
    private String _end_choice           = "";
    private String _end_choice_num       = "";
    private String _end_choice_unit      = "";
    private String _start_choice         = "";
    private String _num_min_prior        = "";
    private String _email                = "";
    private String _username             = "";
    private String[] _message_groups     = null;
    private String[] _node_names         = null;
    private String[] _data_servers       = null;
}

