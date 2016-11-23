package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;



public interface IHttpCookie
{
    //  HTTP Session Attribute Names
    final public static String SESSION_BROWSE  = "HttpCookie-BrowseRecords";
    final public static String SESSION_SELECT  = "HttpCookie-SelectRecord";
    final public static String SESSION_MAP     = "HttpCookie-MapByPrimaryKey";

    public void setValue ( IHttpCookie object );
    public String toXML();

    public void setRowID ( String row_id );
    public String getRowID();

    public void setRequestID ( String request_id );
    public String getRequestID();

    public void setCookieComment ( String cookie_comment );
    public String getCookieComment();

    public void setDomain ( String domain );
    public String getDomain();

    public void setPath ( String path );
    public String getPath();

    public void setCookieName ( String cookie_name );
    public String getCookieName();

    public void setCookiePersistence ( String cookie_persistence );
    public String getCookiePersistence();

    public void setIsSecure ( String is_secure );
    public void setIsSecure ( int is_secure );
    public void setIsSecure ( boolean is_secure );
    public boolean getIsSecure();

    public void setMaxAge ( String max_age );
    public void setMaxAge ( long max_age );
    public long getMaxAge();

    public void setVersion ( String version );
    public void setVersion ( long version );
    public long getVersion();
}
