package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;



public interface IDataMap
{
    //  HTTP Session Attribute Names
    final public static String SESSION_BROWSE  = "DataMap-BrowseRecords";
    final public static String SESSION_SELECT  = "DataMap-SelectRecord";
    final public static String SESSION_MAP     = "DataMap-MapByPrimaryKey";

    public void setValue ( IDataMap object );
    public String toXML();

    public void setRuleID ( String rule_id );
    public String getRuleID();

    public void setApplicationName ( String application_name );
    public String getApplicationName();

    public void setHostname ( String hostname );
    public String getHostname();

    public void setTargetPath ( String target_path );
    public String getTargetPath();
}
