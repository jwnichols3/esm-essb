package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Properties;
import org.apache.log4j.Logger;


public abstract class AbstractHttpAttribute extends CommonObject implements Serializable, IHttpAttribute
{
    /*
     *
     */
    final private static long serialVersionUID = -5146345813451418450L;
    @SuppressWarnings ( "unused" )
    final private static Logger _log           = Logger.getLogger ( AbstractHttpAttribute.class );

    private String    RowID                 = null;
    private String    RequestID             = null;
    private String    AttributeName         = null;
    private String    AttributePersistence  = null;

    //  Default values
    private static String DefaultValuesForRequestID[]              = null;
    private static String DefaultValuesForAttributeName[]          = null;
    private static String DefaultValuesForAttributePersistence[]   = null;

    static
    {
        initializeDefaultValues();
    }

    private static void initializeDefaultValues()
    {
        Properties properties = loadProperties ( "content/defaults/http_attribute.properties" );
        if ( null == properties )
        {
            return;
        }

        DefaultValuesForRequestID                = retrieveStringDefaultValues ( properties, "database.http_attribute.request_id."               );
        DefaultValuesForAttributeName            = retrieveStringDefaultValues ( properties, "database.http_attribute.attribute_name."           );
        DefaultValuesForAttributePersistence     = retrieveStringDefaultValues ( properties, "database.http_attribute.attribute_persistence."    );
    }

    public void setValue ( IHttpAttribute object )
    {
        setRequestID             ( object.getRequestID()             );
        setAttributeName         ( object.getAttributeName()         );
        setAttributePersistence  ( object.getAttributePersistence()  );
    }

    final public static String[] getDefaultValuesForRequestID()
    {
        return DefaultValuesForRequestID;
    }

    final public static String[] getDefaultValuesForAttributeName()
    {
        return DefaultValuesForAttributeName;
    }

    final public static String[] getDefaultValuesForAttributePersistence()
    {
        return DefaultValuesForAttributePersistence;
    }

    public boolean equals ( Object object )
    {
        if ( object instanceof IHttpAttribute )
        {
            boolean match_result = true;
            IHttpAttribute data = (IHttpAttribute) object;
                match_result = match_result && compareStrings   ( RequestID,             data.getRequestID()              );
                match_result = match_result && compareStrings   ( AttributeName,         data.getAttributeName()          );
                match_result = match_result && compareStrings   ( AttributePersistence,  data.getAttributePersistence()   );
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
            message.get().append ( ", AttributeName=" );
            message.get().append ( getAttributeName() );
            message.get().append ( ", AttributePersistence=" );
            message.get().append ( getAttributePersistence() );
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
            message.get().append ( "    <attribute_name><![CDATA[" );
            message.get().append ( getAttributeName() );
            message.get().append ( "]]></attribute_name>\n" );
            message.get().append ( "    <attribute_persistence><![CDATA[" );
            message.get().append ( getAttributePersistence() );
            message.get().append ( "]]></attribute_persistence>\n" );
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
     *  Setter method for the 'attribute_name' column
     *
     *  @param attribute_name  The new value for the 'attribute_name' column
     */
    public void setAttributeName ( String attribute_name )
    {
        this.AttributeName = attribute_name;
    }

    /**
     *  Getter method for the 'attribute_name' column
     *  @return The new value for the 'attribute_name' column
     */
    public String getAttributeName ()
    {
        return AttributeName;
    }

    /**
     *  Setter method for the 'attribute_persistence' column
     *
     *  @param attribute_persistence  The new value for the 'attribute_persistence' column
     */
    public void setAttributePersistence ( String attribute_persistence )
    {
        this.AttributePersistence = attribute_persistence;
    }

    /**
     *  Getter method for the 'attribute_persistence' column
     *  @return The new value for the 'attribute_persistence' column
     */
    public String getAttributePersistence ()
    {
        return AttributePersistence;
    }

}