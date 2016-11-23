package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.forms;

import java.lang.ref.WeakReference;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;

public class HttpCookieForm extends ActionForm implements IHttpCookie
{
    /**
     *
     */
    final private static long serialVersionUID = -4835712276048455997L;
    final private static Logger _log           = Logger.getLogger ( HttpCookieForm.class );

    private String    RowID              = null;
    private String    RequestID          = null;
    private String    CookieComment      = null;
    private String    Domain             = null;
    private String    Path               = null;
    private String    CookieName         = null;
    private String    CookiePersistence  = null;
    private boolean   IsSecure           = false;
    private long      MaxAge             = 0;
    private long      Version            = 0;

    public void setValue ( IHttpCookie object )
    {
        setRequestID          ( object.getRequestID()          );
        setCookieComment      ( object.getCookieComment()      );
        setDomain             ( object.getDomain()             );
        setPath               ( object.getPath()               );
        setCookieName         ( object.getCookieName()         );
        setCookiePersistence  ( object.getCookiePersistence()  );
        setIsSecure           ( object.getIsSecure()           );
        setMaxAge             ( object.getMaxAge()             );
        setVersion            ( object.getVersion()            );
    }

    public IHttpCookie getValue()
    {
        try
        {
            Class <? extends IHttpCookie> c = this.getClass();
            IHttpCookie object = c.newInstance();
            object.setValue ( (IHttpCookie) this );

            return object;
        }
        catch ( IllegalAccessException exception )
        {
            _log.error ( "IllegalAccessException when trying to instantiate class=" + this.getClass().getName(), exception );
        }
        catch ( InstantiationException exception )
        {
            _log.error ( "Could not instantiate class=" + this.getClass().getName(), exception );
        }

        return null;
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
            message.get().append ( ", CookieComment=" );
            message.get().append ( getCookieComment() );
            message.get().append ( ", Domain=" );
            message.get().append ( getDomain() );
            message.get().append ( ", Path=" );
            message.get().append ( getPath() );
            message.get().append ( ", CookieName=" );
            message.get().append ( getCookieName() );
            message.get().append ( ", CookiePersistence=" );
            message.get().append ( getCookiePersistence() );
            message.get().append ( ", IsSecure=" );
            message.get().append ( getIsSecure() );
            message.get().append ( ", MaxAge=" );
            message.get().append ( getMaxAge() );
            message.get().append ( ", Version=" );
            message.get().append ( getVersion() );
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
            message.get().append ( "    <cookie_comment><![CDATA[" );
            message.get().append ( getCookieComment() );
            message.get().append ( "]]></cookie_comment>\n" );
            message.get().append ( "    <domain><![CDATA[" );
            message.get().append ( getDomain() );
            message.get().append ( "]]></domain>\n" );
            message.get().append ( "    <path><![CDATA[" );
            message.get().append ( getPath() );
            message.get().append ( "]]></path>\n" );
            message.get().append ( "    <cookie_name><![CDATA[" );
            message.get().append ( getCookieName() );
            message.get().append ( "]]></cookie_name>\n" );
            message.get().append ( "    <cookie_persistence><![CDATA[" );
            message.get().append ( getCookiePersistence() );
            message.get().append ( "]]></cookie_persistence>\n" );
            message.get().append ( "    <is_secure><![CDATA[" );
            message.get().append ( getIsSecure() );
            message.get().append ( "]]></is_secure>\n" );
            message.get().append ( "    <max_age><![CDATA[" );
            message.get().append ( getMaxAge() );
            message.get().append ( "]]></max_age>\n" );
            message.get().append ( "    <version><![CDATA[" );
            message.get().append ( getVersion() );
            message.get().append ( "]]></version>\n" );
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
     *  Setter method for the 'cookie_comment' column
     *
     *  @param cookie_comment  The new value for the 'cookie_comment' column
     */
    public void setCookieComment ( String cookie_comment )
    {
        this.CookieComment = cookie_comment;
    }

    /**
     *  Getter method for the 'cookie_comment' column
     *  @return The new value for the 'cookie_comment' column
     */
    public String getCookieComment ()
    {
        return CookieComment;
    }

    /**
     *  Setter method for the 'domain' column
     *
     *  @param domain  The new value for the 'domain' column
     */
    public void setDomain ( String domain )
    {
        this.Domain = domain;
    }

    /**
     *  Getter method for the 'domain' column
     *  @return The new value for the 'domain' column
     */
    public String getDomain ()
    {
        return Domain;
    }

    /**
     *  Setter method for the 'path' column
     *
     *  @param path  The new value for the 'path' column
     */
    public void setPath ( String path )
    {
        this.Path = path;
    }

    /**
     *  Getter method for the 'path' column
     *  @return The new value for the 'path' column
     */
    public String getPath ()
    {
        return Path;
    }

    /**
     *  Setter method for the 'cookie_name' column
     *
     *  @param cookie_name  The new value for the 'cookie_name' column
     */
    public void setCookieName ( String cookie_name )
    {
        this.CookieName = cookie_name;
    }

    /**
     *  Getter method for the 'cookie_name' column
     *  @return The new value for the 'cookie_name' column
     */
    public String getCookieName ()
    {
        return CookieName;
    }

    /**
     *  Setter method for the 'cookie_persistence' column
     *
     *  @param cookie_persistence  The new value for the 'cookie_persistence' column
     */
    public void setCookiePersistence ( String cookie_persistence )
    {
        this.CookiePersistence = cookie_persistence;
    }

    /**
     *  Getter method for the 'cookie_persistence' column
     *  @return The new value for the 'cookie_persistence' column
     */
    public String getCookiePersistence ()
    {
        return CookiePersistence;
    }

    /**
     *  Setter method for the 'is_secure' column
     *
     *  @param is_secure  The new value for the 'is_secure' column
     */
    public void setIsSecure ( String is_secure )
    {
        this.IsSecure = Boolean.parseBoolean ( is_secure );
    }

    /**
     *  Setter method for the 'is_secure' column
     *
     *  @param is_secure  The new value for the 'is_secure' column
     */
    public void setIsSecure ( int is_secure )
    {
        this.IsSecure = ( 1 == is_secure );
    }

    /**
     *  Setter method for the 'is_secure' column
     *
     *  @param is_secure  The new value for the 'is_secure' column
     */
    public void setIsSecure ( boolean is_secure )
    {
        this.IsSecure = is_secure;
    }

    /**
     *  Getter method for the 'is_secure' column
     *  @return The new value for the 'is_secure' column
     */
    public boolean getIsSecure ()
    {
        return IsSecure;
    }

    /**
     *  Setter method for the 'max_age' column
     *
     *  @param max_age  The new value for the 'max_age' column
     */
    public void setMaxAge ( String max_age )
    {
        this.MaxAge = Long .parseLong  ( max_age );
    }

    /**
     *  Setter method for the 'max_age' column
     *
     *  @param max_age  The new value for the 'max_age' column
     */
    public void setMaxAge ( long max_age )
    {
        this.MaxAge = max_age;
    }

    /**
     *  Getter method for the 'max_age' column
     *  @return The new value for the 'max_age' column
     */
    public long getMaxAge ()
    {
        return MaxAge;
    }

    /**
     *  Setter method for the 'version' column
     *
     *  @param version  The new value for the 'version' column
     */
    public void setVersion ( String version )
    {
        this.Version = Long .parseLong  ( version );
    }

    /**
     *  Setter method for the 'version' column
     *
     *  @param version  The new value for the 'version' column
     */
    public void setVersion ( long version )
    {
        this.Version = version;
    }

    /**
     *  Getter method for the 'version' column
     *  @return The new value for the 'version' column
     */
    public long getVersion ()
    {
        return Version;
    }



    public ActionErrors validate ( ActionMapping mapping, HttpServletRequest request )
    {
        ActionErrors errors = new ActionErrors();

        return errors;
    }

    public void reset ( ActionMapping mapping, HttpServletRequest request )
    {
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );

        if ( null != renderRequest )
        {
            PortletPreferences prefs = renderRequest.getPreferences();

            prefs.getMap();
        }
        else
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass() );
                message.get().append ( "::reset () - could not find PortletPreferences" );
            _log.warn ( message.get().toString() );
        }
    }
}