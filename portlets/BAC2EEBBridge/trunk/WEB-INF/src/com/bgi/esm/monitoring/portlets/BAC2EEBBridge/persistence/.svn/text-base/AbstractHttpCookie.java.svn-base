package com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence;

import java.io.Serializable;
import javax.servlet.http.Cookie;
import org.apache.log4j.Logger;

public abstract class AbstractHttpCookie implements Serializable
{
    final private static Logger _log = Logger.getLogger ( AbstractHttpCookie.class );
    protected String RowID           = null;
    protected String RequestID       = null;
    protected String Comment         = null;
    protected String Domain          = null;
    protected String Path            = null;
    protected String CookieName      = null;
    protected String CookieValue     = null;

    protected boolean Secure         = false;
    protected int MaxAge             = 0;
    protected int Version            = 0;

    
    public AbstractHttpCookie()
    {
    }

    public AbstractHttpCookie getValue()
    {
        try 
        {
            Class c = this.getClass();
            AbstractHttpCookie object = (AbstractHttpCookie) c.newInstance();
            object.setValue ( this );

            return object;
        } 
        catch ( IllegalAccessException exception ) 
        {
            _log.error ( exception.getMessage(), exception );

            return null;
        } 
        catch ( InstantiationException exception ) 
        {
            _log.error ( exception.getMessage(), exception );

            return null;
        }
    }

    public void setValue ( Cookie cookie )
    {
        setComment     ( cookie.getComment()     );
        setDomain      ( cookie.getDomain()      );
        setPath        ( cookie.getPath()        );
        setCookieName  ( cookie.getName()        );
        setCookieValue ( cookie.getValue()       );
        setSecure      ( cookie.getSecure()      );
        setMaxAge      ( cookie.getMaxAge()      );
        setVersion     ( cookie.getVersion()     );
    }

    public void setValue ( AbstractHttpCookie cookie )
    {
        setRequestID   ( cookie.getRequestID()   );
        setComment     ( cookie.getComment()     );
        setDomain      ( cookie.getDomain()      );
        setPath        ( cookie.getPath()        );
        setCookieName  ( cookie.getCookieName()  );
        setCookieValue ( cookie.getCookieValue() );
        setSecure      ( cookie.getSecure()      );
        setMaxAge      ( cookie.getMaxAge()      );
        setVersion     ( cookie.getVersion()     );
    }

    public void setRowID ( String row_id )
    {
        RowID = row_id;
    }

    public String getRowID()
    {
        return RowID;
    }

    public void setRequestID ( String request_id )
    {
        RequestID = request_id;
    }

    public String getRequestID()
    {
        return RequestID;
    }

    public void setComment ( String comment )
    {
        Comment = comment;
    }

    public String getComment()
    {
        return Comment;
    }

    public void setDomain ( String domain )
    {
        Domain = domain;
    }

    public String getDomain()
    {
        return Domain;
    }

    public void setPath ( String path )
    {
        Path = path;
    }

    public String getPath()
    {
        return Path;
    }

    public void setCookieName ( String cookie_name )
    {
        CookieName = cookie_name;
    }

    public String getCookieName()
    {
        return CookieName;
    }

    public void setCookieValue ( String cookie_value )
    {
        CookieValue = cookie_value;
    }

    public String getCookieValue()
    {
        return CookieValue;
    }

    public void setSecure ( boolean secure )
    {
        Secure = secure;
    }

    public boolean getSecure()
    {
        return Secure;
    }

    public void setMaxAge ( int max_age )
    {
        MaxAge = max_age;
    }

    public int getMaxAge()
    {
        return MaxAge;
    }

    public void setVersion ( int version )
    {
        Version = version;
    }

    public int getVersion()
    {
        return Version;
    }
}
