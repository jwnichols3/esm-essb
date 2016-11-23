package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;



public interface IHttpAttribute
{
    //  HTTP Session Attribute Names
    final public static String SESSION_BROWSE  = "HttpAttribute-BrowseRecords";
    final public static String SESSION_SELECT  = "HttpAttribute-SelectRecord";
    final public static String SESSION_MAP     = "HttpAttribute-MapByPrimaryKey";

    public void setValue ( IHttpAttribute object );
    public String toXML();

    public void setRowID ( String row_id );
    public String getRowID();

    public void setRequestID ( String request_id );
    public String getRequestID();

    public void setAttributeName ( String attribute_name );
    public String getAttributeName();

    public void setAttributePersistence ( String attribute_persistence );
    public String getAttributePersistence();
}
