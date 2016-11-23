package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Properties;
import org.apache.log4j.Logger;


public abstract class AbstractHttpHeader extends CommonObject implements Serializable, IHttpHeader
{
    /*
     *
     */
    final private static long serialVersionUID = -684182486976406915L;
    @SuppressWarnings ( "unused" )
    final private static Logger _log           = Logger.getLogger ( AbstractHttpHeader.class );

    private String    RowID              = null;
    private String    RequestID          = null;
    private String    HeaderName         = null;
    private String    HeaderPersistence  = null;

    //  Default values
    private static String DefaultValuesForRequestID[]           = null;
    private static String DefaultValuesForHeaderName[]          = null;
    private static String DefaultValuesForHeaderPersistence[]   = null;

    static
    {
        initializeDefaultValues();
    }

    private static void initializeDefaultValues()
    {
        Properties properties = loadProperties ( "content/defaults/http_header.properties" );
        if ( null == properties )
        {
            return;
        }

        DefaultValuesForRequestID             = retrieveStringDefaultValues ( properties, "database.http_header.request_id."            );
        DefaultValuesForHeaderName            = retrieveStringDefaultValues ( properties, "database.http_header.header_name."           );
        DefaultValuesForHeaderPersistence     = retrieveStringDefaultValues ( properties, "database.http_header.header_persistence."    );
    }

    public void setValue ( IHttpHeader object )
    {
        setRequestID          ( object.getRequestID()          );
        setHeaderName         ( object.getHeaderName()         );
        setHeaderPersistence  ( object.getHeaderPersistence()  );
    }

    final public static String[] getDefaultValuesForRequestID()
    {
        return DefaultValuesForRequestID;
    }

    final public static String[] getDefaultValuesForHeaderName()
    {
        return DefaultValuesForHeaderName;
    }

    final public static String[] getDefaultValuesForHeaderPersistence()
    {
        return DefaultValuesForHeaderPersistence;
    }

    public boolean equals ( Object object )
    {
        if ( object instanceof IHttpHeader )
        {
            boolean match_result = true;
            IHttpHeader data = (IHttpHeader) object;
                match_result = match_result && compareStrings   ( RequestID,          data.getRequestID()           );
                match_result = match_result && compareStrings   ( HeaderName,         data.getHeaderName()          );
                match_result = match_result && compareStrings   ( HeaderPersistence,  data.getHeaderPersistence()   );
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
            message.get().append ( ", HeaderName=" );
            message.get().append ( getHeaderName() );
            message.get().append ( ", HeaderPersistence=" );
            message.get().append ( getHeaderPersistence() );
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
            message.get().append ( "    <header_name><![CDATA[" );
            message.get().append ( getHeaderName() );
            message.get().append ( "]]></header_name>\n" );
            message.get().append ( "    <header_persistence><![CDATA[" );
            message.get().append ( getHeaderPersistence() );
            message.get().append ( "]]></header_persistence>\n" );
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
     *  Setter method for the 'header_name' column
     *
     *  @param header_name  The new value for the 'header_name' column
     */
    public void setHeaderName ( String header_name )
    {
        this.HeaderName = header_name;
    }

    /**
     *  Getter method for the 'header_name' column
     *  @return The new value for the 'header_name' column
     */
    public String getHeaderName ()
    {
        return HeaderName;
    }

    /**
     *  Setter method for the 'header_persistence' column
     *
     *  @param header_persistence  The new value for the 'header_persistence' column
     */
    public void setHeaderPersistence ( String header_persistence )
    {
        this.HeaderPersistence = header_persistence;
    }

    /**
     *  Getter method for the 'header_persistence' column
     *  @return The new value for the 'header_persistence' column
     */
    public String getHeaderPersistence ()
    {
        return HeaderPersistence;
    }

}