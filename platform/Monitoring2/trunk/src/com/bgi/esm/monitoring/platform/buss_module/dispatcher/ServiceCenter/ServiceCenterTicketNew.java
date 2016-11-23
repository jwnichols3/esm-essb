package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Vector;
import org.apache.log4j.Logger;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ServiceCenterTicketNew implements Serializable
{
    final private static Logger _log = Logger.getLogger ( ServiceCenterTicketNew.class );
    private String TicketNumber     = null;
    private String Category         = null;
    private String Subcategory      = null;
    private Calendar CreationTime   = null;
    private String OpenedBy         = null;
    private int SeverityCode        = 0;
    private int PriorityCode        = 0;
    private String AssignmentGroup  = null;
    private String Status           = null;
    private Calendar CloseTime      = null;
    private String ClosedBy         = null;
    private String ReferenceNum     = null;
    private String ContactName      = null;
    private String Actor            = null; 
    private String DeadlineGroup    = null; 
    private String Format           = null;
    private String DescriptionLong  = null;
    private String DescriptionBrief = null;
    private String ContactLocation  = null;

    private Vector <String> actions        = new Vector <String> ();
    private Vector <String> resolutions    = new Vector <String> ();
    private Vector <String> update_actions = new Vector <String> ();
    private Vector <String> errors         = null;
    private Vector <String> warnings       = null;

    public void setTicketNumber ( String ticket_number )
    {
        TicketNumber = ticket_number;
    }

    public String getTicketNumber()
    {
        return TicketNumber;
    }

    public void setCategory ( String category )
    {
        Category = category;
    }

    public String getCategory()
    {
        return Category;
    }

    public void setSubcategory ( String sub_category )
    {
        Subcategory = sub_category;
    }

    public String getSubcategory()
    {
        return Subcategory;
    }

    private static Calendar retrieveDateFromString ( String time_string )
    {
        if ( time_string.length() >= 19 )
        {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            String cal_year   = time_string.substring (  0,  4 );
            String cal_month  = time_string.substring (  5,  7 );
            String cal_date   = time_string.substring (  8, 10 );
            String cal_hour   = time_string.substring ( 11, 13 );
            String cal_minute = time_string.substring ( 14, 16 );
            String cal_second = time_string.substring ( 17, 19 );

            calendar.set ( Calendar.YEAR,   Integer.parseInt ( cal_year   ) );
            calendar.set ( Calendar.MONTH,  Integer.parseInt ( cal_month  ) - 1);
            calendar.set ( Calendar.DATE,   Integer.parseInt ( cal_date   ) );
            calendar.set ( Calendar.HOUR,   Integer.parseInt ( cal_hour   ) );
            calendar.set ( Calendar.MINUTE, Integer.parseInt ( cal_minute ) );
            calendar.set ( Calendar.SECOND, Integer.parseInt ( cal_second ) );

            return calendar;
        }
        else if ( time_string.length() >= 17 )
        {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            String cal_month  = time_string.substring (  0,  2 );
            String cal_date   = time_string.substring (  3,  5 );
            String cal_year   = time_string.substring (  6,  8 );
            String cal_hour   = time_string.substring (  9, 11 );
            String cal_minute = time_string.substring ( 12, 14 );
            String cal_second = time_string.substring ( 15, 17 );

            calendar.set ( Calendar.YEAR,   Integer.parseInt ( cal_year   ) + 2000 );
            calendar.set ( Calendar.MONTH,  Integer.parseInt ( cal_month  ) - 1 );
            calendar.set ( Calendar.DATE,   Integer.parseInt ( cal_date   ) );
            calendar.set ( Calendar.HOUR,   Integer.parseInt ( cal_hour   ) );
            calendar.set ( Calendar.MINUTE, Integer.parseInt ( cal_minute ) );
            calendar.set ( Calendar.SECOND, Integer.parseInt ( cal_second ) );

            return calendar;
        }
        else
        {
            _log.error ( "Attempted to format invalid date string: " + time_string );

            return null;
        }
    }

    /**
     *  Sets the creation time of this ticket
     *
     *  @param creation_time the time in YYYY-HH-MMTHH:mm:ss+00:00
     */
    public void setCreationTime ( String creation_time )
    {
        CreationTime = retrieveDateFromString ( creation_time );
    }

    public void setCreationTime ( Calendar creation_time )
    {
        CreationTime = creation_time;
    }

    public Calendar getCreationTime()
    {
        return CreationTime;
    }

    public void setOpenedBy ( String opened_by )
    {
        OpenedBy = opened_by;
    }

    public String getOpenedBy()
    {
        return OpenedBy;
    }

    public void setSeverityCode ( int severity_code )
    {
        SeverityCode = severity_code;
    }

    public void setSeverityCode ( String severity_code )
    {
        try
        {
            SeverityCode = Integer.parseInt ( severity_code );
        }
        catch ( NumberFormatException exception )
        {
            SeverityCode = 0;

            StringBuilder message = new StringBuilder();
                message.append ( "Attempted to set to invalid severity code: " );
                message.append ( severity_code );
                message.append ( ".  Defaulting to 0." );
            _log.error ( message.toString() );
        }
    }

    public int getSeverityCode()
    {
        return SeverityCode;
    }

    public void setPriorityCode ( int priority_code )
    {
        PriorityCode = priority_code;
    }

    public void setPriorityCode ( String priority_code )
    {
        try
        {
            PriorityCode = Integer.parseInt ( priority_code );
        }
        catch ( NumberFormatException exception )
        {
            PriorityCode = 0;

            StringBuilder message = new StringBuilder();
                message.append ( "Attempted to set to invalid priority code: " );
                message.append ( priority_code );
                message.append ( ".  Defaulting to 0." );
            _log.error ( message.toString() );
        }
    }

    public int getPriorityCode()
    {
        return PriorityCode;
    }

    public void setAssignmentGroup ( String assignment )
    {
        AssignmentGroup = assignment;
    }

    public String getAssignmentGroup()
    {
        return AssignmentGroup;
    }

    public void setStatus ( String status )
    {
        Status = status;
    }

    public String getStatus ()
    {
        return Status;
    }

    public void setCloseTime ( String close_type )
    {
    }

    public void setCloseTime ( Calendar close_time )
    {
        CloseTime = close_time;
    }

    public Calendar getCloseTime()
    {
        return CloseTime;
    }

    public void setClosedBy ( String closed_by )
    {
        ClosedBy = closed_by;
    }

    public String getClosedBy()
    {
        return ClosedBy;
    }

    public void setReferenceNum ( String reference_number )
    {
        ReferenceNum = reference_number;
    }

    public String getReferenceNum ()
    {
        return ReferenceNum;
    }

    public void setOVOEvenvID ( String reference_number )
    {
        ReferenceNum = reference_number;
    }

    public String getOVOEventID()
    {
        return ReferenceNum;
    }

    public void setContactName ( String contact_name )
    {
        ContactName = contact_name;
    }

    public String getContactName()
    {
        return ContactName;
    }

    public void setActor ( String actor )
    {
        Actor = actor;
    }

    public String getActor ()
    {
        return Actor;
    }

    public void setDeadlineGroup ( String deadline_group )
    {
        DeadlineGroup = deadline_group;
    }

    public String getDeadlineGroup ()
    {
        return DeadlineGroup;
    }

    public void setFormat ( String format )
    {
        Format = format;
    }

    public String getFormat()
    {
        return Format;
    }

    public void addAction ( String action )
    {
        actions.add ( action );
    }

    public int getNumActions()
    {
        return actions.size();
    }

    public String getAction ( int index )
    {
        return actions.get ( index );
    }

    public void addResolution ( String resolution )
    {
        resolutions.add ( resolution );
    }

    public int getNumResolutions()
    {
        return resolutions.size();
    }

    public String getResolution ( int index )
    {
        return resolutions.get ( index );
    }

    public void setLongDescription ( String long_description )
    {
        setDescriptionLong ( long_description );
    }

    public void setDescriptionLong ( String long_description )
    {
        DescriptionLong = long_description;
    }

    public String getDescriptionLong()
    {
        return DescriptionLong;
    }

    public String getLongDescription()
    {
        return getDescriptionLong();
    }

    public void setDescriptionBrief ( String brief_description )
    {
        DescriptionBrief = brief_description;
    }

    public void setBriefDescription ( String brief_description )
    {
        setDescriptionBrief ( brief_description );
    }

    public String getDescriptionBrief()
    {
        return DescriptionBrief;
    }

    public String getBriefDescription()
    {
        return getDescriptionBrief();
    }

    public void setContactLocation ( String contact_location )
    {
        ContactLocation = contact_location;
    }

    public String getContactLocation()
    {
        return ContactLocation;
    }

    public void addUpdateAction ( String update_action )
    {
        update_actions.add ( update_action );
    }

    public int getNumUpdateActions ()
    {
        return update_actions.size();
    }

    public String getUpdateAction ( int index )
    {
        return update_actions.get ( index );
    }

    public boolean hasBriefDescription()
    {
        return (( null != DescriptionBrief ) && ( DescriptionBrief.trim().length() > 0 ));
    }

    public boolean hasLongDescription()
    {
        return (( null != DescriptionLong ) && ( DescriptionLong.trim().length() > 0 ));
    }

    public void validate()
    {
        errors   = new Vector <String> ();
        warnings = new Vector <String> ();
        if (( null == Category ) || ( Category.trim().length() == 0 ))
        {
            errors.add ( "Empty category detected" );
        }
        if (( null == Subcategory ) || ( Subcategory.trim().length() == 0 ))
        {
            errors.add ( "Sub category detected" );
        }
        if (( null == AssignmentGroup ) || ( AssignmentGroup.trim().length() == 0 ))
        {
            errors.add ( "No assignment group detected" );
        }
        if (( false == hasBriefDescription() ) || ( false == hasLongDescription() ))
        {
            errors.add ( "No description specified.  Needs to specify at least a long description or a brief description." );
        }
    }

    public boolean hasErrors()
    {
        if ( null == errors )
        {
            throw new IllegalStateException ( "Could not retrieve errors because this object has not been validated yet.  Please call validate() first." );
        }

        return ( errors.size() > 0 );
    }

    public int getNumErrors()
    {
        if ( null == errors )
        {
            throw new IllegalStateException ( "Could not retrieve num errors because this object has not been validated yet.  Please call validate() first." );
        }
        
        return errors.size();
    }

    public String getError ( int index )
    {
        if ( null == errors )
        {
            throw new IllegalStateException ( "Could not retrieve the specified error because this object has not been validated yet.  Please call validate() first." );
        }

        return errors.get ( index );
    }


    public boolean hasWarnings()
    {
        if ( null == warnings )
        {
            throw new IllegalStateException ( "Could not retrieve warnings because this object has not been validated yet.  Please call validate() first." );
        }
        return ( warnings.size() > 0 );
    }

    public int getNumWarnings()
    {
        if ( null == warnings )
        {
            throw new IllegalStateException ( "Could not retrieve num warnings because this object has not been validated yet.  Please call validate() first." );
        }

        return warnings.size();
    }

    public String getWarning ( int index )
    {
        if ( null == warnings )
        {
            throw new IllegalStateException ( "Could not retrieve the specified warnings because this object has not been validated yet.  Please call validate() first." );
        }

        return warnings.get ( index );
    }
};
