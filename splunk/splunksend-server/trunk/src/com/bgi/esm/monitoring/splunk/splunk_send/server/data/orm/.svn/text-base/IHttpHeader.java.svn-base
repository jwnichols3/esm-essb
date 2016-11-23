package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;



public interface IHttpHeader
{
    //  HTTP Session Attribute Names
    final public static String SESSION_BROWSE  = "HttpHeader-BrowseRecords";
    final public static String SESSION_SELECT  = "HttpHeader-SelectRecord";
    final public static String SESSION_MAP     = "HttpHeader-MapByPrimaryKey";

    public void setValue ( IHttpHeader object );
    public String toXML();

    public void setRowID ( String row_id );
    public String getRowID();

    public void setRequestID ( String request_id );
    public String getRequestID();

    public void setHeaderName ( String header_name );
    public String getHeaderName();

    public void setHeaderPersistence ( String header_persistence );
    public String getHeaderPersistence();
}
