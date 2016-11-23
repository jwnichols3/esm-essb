package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Properties;
import org.apache.log4j.Logger;


public abstract class AbstractHttpParameter extends CommonObject implements Serializable, IHttpParameter
{
    /*
     *
     */
    final private static long serialVersionUID = 7713490181942960105L;
    @SuppressWarnings ( "unused" )
    final private static Logger _log           = Logger.getLogger ( AbstractHttpParameter.class );

    private String    RowID                 = null;
    private String    RequestID             = null;
    private String    ParameterName         = null;
    private String    ParameterPersistence  = null;

    //  Default values
    private static String DefaultValuesForRequestID[]              = null;
    private static String DefaultValuesForParameterName[]          = null;
    private static String DefaultValuesForParameterPersistence[]   = null;

    static
    {
        initializeDefaultValues();
    }

    private static void initializeDefaultValues()
    {
        Properties properties = loadProperties ( "content/defaults/http_parameter.properties" );
        if ( null == properties )
        {
            return;
        }

        DefaultValuesForRequestID                = retrieveStringDefaultValues ( properties, "database.http_parameter.request_id."               );
        DefaultValuesForParameterName            = retrieveStringDefaultValues ( properties, "database.http_parameter.parameter_name."           );
        DefaultValuesForParameterPersistence     = retrieveStringDefaultValues ( properties, "database.http_parameter.parameter_persistence."    );
    }

    public void setValue ( IHttpParameter object )
    {
        setRequestID             ( object.getRequestID()             );
        setParameterName         ( object.getParameterName()         );
        setParameterPersistence  ( object.getParameterPersistence()  );
    }

    final public static String[] getDefaultValuesForRequestID()
    {
        return DefaultValuesForRequestID;
    }

    final public static String[] getDefaultValuesForParameterName()
    {
        return DefaultValuesForParameterName;
    }

    final public static String[] getDefaultValuesForParameterPersistence()
    {
        return DefaultValuesForParameterPersistence;
    }

    public boolean equals ( Object object )
    {
        if ( object instanceof IHttpParameter )
        {
            boolean match_result = true;
            IHttpParameter data = (IHttpParameter) object;
                match_result = match_result && compareStrings   ( RequestID,             data.getRequestID()              );
                match_result = match_result && compareStrings   ( ParameterName,         data.getParameterName()          );
                match_result = match_result && compareStrings   ( ParameterPersistence,  data.getParameterPersistence()   );
            return match_result;
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( " ( " );
            message.get().append ( "RowID=" );
            message.get().append ( getRowID() );
            message.get().append ( ", RequestID=" );
            message.get().append ( getRequestID() );
            message.get().append ( ", ParameterName=" );
            message.get().append ( getParameterName() );
            message.get().append ( ", ParameterPersistence=" );
            message.get().append ( getParameterPersistence() );
            message.get().append ( " )" );
        return message.get().toString();
    }

    public String toXML()
    {
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "<item classname=\"" );
            message.get().append ( getClass().getName() );
            message.get().append ( "\">\n" );
            message.get().append ( "    <row_id><![CDATA[" );
            message.get().append ( getRowID() );
            message.get().append ( "]]></row_id>\n" );
            message.get().append ( "    <request_id><![CDATA[" );
            message.get().append ( getRequestID() );
            message.get().append ( "]]></request_id>\n" );
            message.get().append ( "    <parameter_name><![CDATA[" );
            message.get().append ( getParameterName() );
            message.get().append ( "]]></parameter_name>\n" );
            message.get().append ( "    <parameter_persistence><![CDATA[" );
            message.get().append ( getParameterPersistence() );
            message.get().append ( "]]></parameter_persistence>\n" );
            message.get().append ( "</item>" );
        return message.get().toString();
    }

    /**
     *  Setter method for the 'row_id' column
     *
     *  @param row_id  The new value for the 'row_id' column
     */
    public void setRowID ( String row_id )
    {
        this.RowID = row_id;
    }

    /**
     *  Getter method for the 'row_id' column
     *  @return The new value for the 'row_id' column
     */
    public String getRowID ()
    {
        return RowID;
    }

    /**
     *  Setter method for the 'request_id' column
     *
     *  @param request_id  The new value for the 'request_id' column
     */
    public void setRequestID ( String request_id )
    {
        this.RequestID = request_id;
    }

    /**
     *  Getter method for the 'request_id' column
     *  @return The new value for the 'request_id' column
     */
    public String getRequestID ()
    {
        return RequestID;
    }

    /**
     *  Setter method for the 'parameter_name' column
     *
     *  @param parameter_name  The new value for the 'parameter_name' column
     */
    public void setParameterName ( String parameter_name )
    {
        this.ParameterName = parameter_name;
    }

    /**
     *  Getter method for the 'parameter_name' column
     *  @return The new value for the 'parameter_name' column
     */
    public String getParameterName ()
    {
        return ParameterName;
    }

    /**
     *  Setter method for the 'parameter_persistence' column
     *
     *  @param parameter_persistence  The new value for the 'parameter_persistence' column
     */
    public void setParameterPersistence ( String parameter_persistence )
    {
        this.ParameterPersistence = parameter_persistence;
    }

    /**
     *  Getter method for the 'parameter_persistence' column
     *  @return The new value for the 'parameter_persistence' column
     */
    public String getParameterPersistence ()
    {
        return ParameterPersistence;
    }

}