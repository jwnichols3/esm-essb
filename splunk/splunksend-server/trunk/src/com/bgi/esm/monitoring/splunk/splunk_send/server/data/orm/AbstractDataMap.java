package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Properties;
import org.apache.log4j.Logger;


public abstract class AbstractDataMap extends CommonObject implements Serializable, IDataMap
{
    /*
     *
     */
    final private static long serialVersionUID = -5947374801822130969L;
    @SuppressWarnings ( "unused" )
    final private static Logger _log           = Logger.getLogger ( AbstractDataMap.class );

    private String    RuleID           = null;
    private String    ApplicationName  = null;
    private String    Hostname         = null;
    private String    TargetPath       = null;

    //  Default values
    private static String DefaultValuesForApplicationName[]   = null;
    private static String DefaultValuesForHostname[]          = null;
    private static String DefaultValuesForTargetPath[]        = null;

    static
    {
        initializeDefaultValues();
    }

    private static void initializeDefaultValues()
    {
        Properties properties = loadProperties ( "content/defaults/data_map.properties" );
        if ( null == properties )
        {
            return;
        }

        DefaultValuesForApplicationName     = retrieveStringDefaultValues ( properties, "database.data_map.application_name."    );
        DefaultValuesForHostname            = retrieveStringDefaultValues ( properties, "database.data_map.hostname."            );
        DefaultValuesForTargetPath          = retrieveStringDefaultValues ( properties, "database.data_map.target_path."         );
    }

    public void setValue ( IDataMap object )
    {
        setApplicationName  ( object.getApplicationName()  );
        setHostname         ( object.getHostname()         );
        setTargetPath       ( object.getTargetPath()       );
    }

    final public static String[] getDefaultValuesForApplicationName()
    {
        return DefaultValuesForApplicationName;
    }

    final public static String[] getDefaultValuesForHostname()
    {
        return DefaultValuesForHostname;
    }

    final public static String[] getDefaultValuesForTargetPath()
    {
        return DefaultValuesForTargetPath;
    }

    public boolean equals ( Object object )
    {
        if ( object instanceof IDataMap )
        {
            boolean match_result = true;
            IDataMap data = (IDataMap) object;
                match_result = match_result && compareStrings   ( ApplicationName,  data.getApplicationName()   );
                match_result = match_result && compareStrings   ( Hostname,         data.getHostname()          );
                match_result = match_result && compareStrings   ( TargetPath,       data.getTargetPath()        );
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
            message.get().append ( "RuleID=" );
            message.get().append ( getRuleID() );
            message.get().append ( ", ApplicationName=" );
            message.get().append ( getApplicationName() );
            message.get().append ( ", Hostname=" );
            message.get().append ( getHostname() );
            message.get().append ( ", TargetPath=" );
            message.get().append ( getTargetPath() );
            message.get().append ( " )" );
        return message.get().toString();
    }

    public String toXML()
    {
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "<item classname=\"" );
            message.get().append ( getClass().getName() );
            message.get().append ( "\">\n" );
            message.get().append ( "    <rule_id><![CDATA[" );
            message.get().append ( getRuleID() );
            message.get().append ( "]]></rule_id>\n" );
            message.get().append ( "    <application_name><![CDATA[" );
            message.get().append ( getApplicationName() );
            message.get().append ( "]]></application_name>\n" );
            message.get().append ( "    <hostname><![CDATA[" );
            message.get().append ( getHostname() );
            message.get().append ( "]]></hostname>\n" );
            message.get().append ( "    <target_path><![CDATA[" );
            message.get().append ( getTargetPath() );
            message.get().append ( "]]></target_path>\n" );
            message.get().append ( "</item>" );
        return message.get().toString();
    }

    /**
     *  Setter method for the 'rule_id' column
     *
     *  @param rule_id  The new value for the 'rule_id' column
     */
    public void setRuleID ( String rule_id )
    {
        this.RuleID = rule_id;
    }

    /**
     *  Getter method for the 'rule_id' column
     *  @return The new value for the 'rule_id' column
     */
    public String getRuleID ()
    {
        return RuleID;
    }

    /**
     *  Setter method for the 'application_name' column
     *
     *  @param application_name  The new value for the 'application_name' column
     */
    public void setApplicationName ( String application_name )
    {
        this.ApplicationName = application_name;
    }

    /**
     *  Getter method for the 'application_name' column
     *  @return The new value for the 'application_name' column
     */
    public String getApplicationName ()
    {
        return ApplicationName;
    }

    /**
     *  Setter method for the 'hostname' column
     *
     *  @param hostname  The new value for the 'hostname' column
     */
    public void setHostname ( String hostname )
    {
        this.Hostname = hostname;
    }

    /**
     *  Getter method for the 'hostname' column
     *  @return The new value for the 'hostname' column
     */
    public String getHostname ()
    {
        return Hostname;
    }

    /**
     *  Setter method for the 'target_path' column
     *
     *  @param target_path  The new value for the 'target_path' column
     */
    public void setTargetPath ( String target_path )
    {
        this.TargetPath = target_path;
    }

    /**
     *  Getter method for the 'target_path' column
     *  @return The new value for the 'target_path' column
     */
    public String getTargetPath ()
    {
        return TargetPath;
    }

}