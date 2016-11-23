package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;



public interface IHttpParameter
{
    //  HTTP Session Attribute Names
    final public static String SESSION_BROWSE  = "HttpParameter-BrowseRecords";
    final public static String SESSION_SELECT  = "HttpParameter-SelectRecord";
    final public static String SESSION_MAP     = "HttpParameter-MapByPrimaryKey";

    public void setValue ( IHttpParameter object );
    public String toXML();

    public void setRowID ( String row_id );
    public String getRowID();

    public void setRequestID ( String request_id );
    public String getRequestID();

    public void setParameterName ( String parameter_name );
    public String getParameterName();

    public void setParameterPersistence ( String parameter_persistence );
    public String getParameterPersistence();
}
