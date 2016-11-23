package com.bgi.esm.monitoring.platform.filters.Archway.orm.beans;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.servlet.http.Cookie;
import org.apache.log4j.Logger;

public class ArchwayRequestBean implements Serializable
{
    final private static Logger _log              = Logger.getLogger ( ArchwayRequestBean.class );
    final private static long serialVersionUID    = 4314809347838732987L;
    protected long   RequestID                    = 0L;
    protected String ServerName                   = null;
    protected int    ServerPort                   = 0;
    protected Calendar RequestTime                = null;
    protected Calendar ProcessedTime              = null;
    protected String StringAttributes             = null;
    protected String StringParameters             = null;
    protected String StringCookies                = null;
    protected String QueryString                  = null;
    protected String Method                       = null;
    protected String RemoteUser                   = null;

    protected HashMap <String, Cookie> Cookies    = null;
    protected HashMap <String, String> Parameters = null;
    protected HashMap <String, String> Attributes = null;

    public ArchwayRequestBean()
    {
        Cookies    = new HashMap <String, Cookie> ();
        Parameters = new HashMap <String, String> ();
        Attributes = new HashMap <String, String> ();
    }

    public void setValue ( ArchwayRequestBean object )
    {
        setServerName       ( object.getServerName()       );
        setServerPort       ( object.getServerPort()       );
        setRequestTime      ( object.getRequestTime()      );
        setProcessedTime    ( object.getProcessedTime()    );
        setStringAttributes ( object.getStringAttributes() );
        setStringParameters ( object.getStringParameters() );
        setStringCookies    ( object.getStringCookies()    );
        setQueryString      ( object.getQueryString()      );
        setMethod           ( object.getMethod()           );
        setRemoteUser       ( object.getRemoteUser()       );
    }

    public ArchwayRequestBean getValue()
    {
        ArchwayRequestBean bean = new ArchwayRequestBean();
        bean.setValue ( this );
        
        return bean;
    }

    public boolean equals ( Object object )
    {
        if ( object instanceof ArchwayRequestBean )
        {
            ArchwayRequestBean request = (ArchwayRequestBean) object;

            boolean match_result = true;

            return match_result;
        }
        else
        {
            return false;
        }
    }

    protected boolean compareCalendars ( Calendar a, Calendar b )
    {
        if (( null == a ) && ( null == b ))
        {
            return true;
        }
        else if (( null != a ) && ( null != b ))
        {
            boolean return_result = true;
            return_result = return_result && ( a.get ( Calendar.YEAR   ) == b.get ( Calendar.YEAR   ) );
            return_result = return_result && ( a.get ( Calendar.MONTH  ) == b.get ( Calendar.MONTH  ) );
            return_result = return_result && ( a.get ( Calendar.DATE   ) == b.get ( Calendar.DATE   ) );
            return_result = return_result && ( a.get ( Calendar.HOUR   ) == b.get ( Calendar.HOUR   ) );
            return_result = return_result && ( a.get ( Calendar.MINUTE ) == b.get ( Calendar.MINUTE ) );

            return return_result;
        }
        else
        {
            return false;
        }
    }

    protected boolean compareObjects ( String a, String b )
    {
        if (( null == a ) && ( null == b ))
        {
            return true;
        }
        else if (( null != a ) && ( null != b ))
        {
            return a.equals ( b );
        }
        else
        {
            return false;
        }
    }

    protected boolean compareStrings ( String a, String b )
    {
        if (( null == a ) && ( null == b ))
        {
            return true;
        }
        else if (( null != a ) && ( null != b ))
        {
            return a.equals ( b );
        }
        else
        {
            return false;
        }
    }

    public Map <String, Cookie> getCookies()
    {
        return Cookies;
    }

    public Map <String, String> getParameters()
    {
        return Parameters;
    }

    public Map <String, String> getAttributes()
    {
        return Attributes;
    }

    public void setRequestID ( long request_id )
    {
        RequestID = request_id;
    }

    public long getRequestID()
    {
        return RequestID;
    }

    public void setServerName ( String server_name )
    {
        ServerName = server_name;
    }

    public String getServerName()
    {
        return ServerName;
    }

    public void setServerPort ( int server_port_num )
    {
        ServerPort = server_port_num;
    }

    public int getServerPort()
    {
        return ServerPort;
    }

    public void setStringAttributes ( String string_attributes )
    {
        StringAttributes = string_attributes;

        //  Parse out the values
        String token           = null;
        String name            = null;
        String value           = null;
        int index              = 0;
        StringTokenizer tokens = new StringTokenizer ( string_attributes, "&" );
        while ( tokens.hasMoreTokens() )
        {
            token = tokens.nextToken();
            index = token.indexOf ( "=" );

            name  = token.substring ( 0, index  );
            value = token.substring ( index + 1 );

            Attributes.put ( name, value );
        }

        //  Old StringAttributes value may be invalid.  Nullify so it will be generated
        //  the next time it is asked for.
        StringAttributes = null;
    }

    public String getStringAttributes()
    {
        if ( null == StringAttributes )
        {
            String key          = null;
            Iterator <String> i = Attributes.keySet().iterator();
            WeakReference <StringBuilder> string = new WeakReference <StringBuilder> ( new StringBuilder() );
            while ( i.hasNext() )
            {
                key = i.next();
            
                string.get().append ( key );
                string.get().append ( "=" );
                string.get().append ( Attributes.get ( key ) );

                if ( i.hasNext() )
                {
                    string.get().append ( "&" );
                }
            }

            StringAttributes = string.get().toString();
        }

        return StringAttributes;
    }

    public String getAttribute ( String key )
    {
        return Attributes.get ( key );
    }

    public void setStringParameters ( String string_parameters )
    {
        StringParameters = string_parameters;

        //  Parse out the values
        String token           = null;
        String name            = null;
        String value           = null;
        int index              = 0;
        StringTokenizer tokens = new StringTokenizer ( string_parameters, "&" );
        while ( tokens.hasMoreTokens() )
        {
            token = tokens.nextToken();
            index = token.indexOf ( "=" );

            name  = token.substring ( 0, index  );
            value = token.substring ( index + 1 );

            Parameters.put ( name, value );
        }

        //  Old StringParameters value may be invalid.  Nullify so it will be generated
        //  the next time it is asked for.
        StringParameters = null;
    }

    public String getStringParameters()
    {
        if ( null == StringParameters )
        {
            String key          = null;
            Iterator <String> i = Parameters.keySet().iterator();
            WeakReference <StringBuilder> string = new WeakReference <StringBuilder> ( new StringBuilder() );
            while ( i.hasNext() )
            {
                key = i.next();
            
                string.get().append ( key );
                string.get().append ( "=" );
                string.get().append ( Parameters.get ( key ) );

                if ( i.hasNext() )
                {
                    string.get().append ( "&" );
                }
            }

            StringParameters = string.get().toString();
        }

        return StringParameters;
    }

    public String getParameter ( String key )
    {
        return Parameters.get ( key );
    }

    public void setStringCookies ( String string_cookies )
    {
        StringCookies = string_cookies;

        //  Parse out the values
        String token           = null;
        String name            = null;
        String value           = null;
        int index              = 0;
        StringTokenizer tokens = new StringTokenizer ( string_cookies, "&" );
        while ( tokens.hasMoreTokens() )
        {
            token = tokens.nextToken();
            index = token.indexOf ( "=" );

            name  = token.substring ( 0, index  );
            value = token.substring ( index + 1 );

            Cookies.put ( name, new Cookie ( name, value ) );
        }

        //  Old StringCookies value may be invalid.  Nullify so it will be generated
        //  the next time it is asked for.
        StringCookies = null;
    }

    public String getStringCookies()
    {
        if ( null == StringCookies )
        {
            String key          = null;
            Iterator <String> i = Cookies.keySet().iterator();
            WeakReference <StringBuilder> string = new WeakReference <StringBuilder> ( new StringBuilder() );

            while ( i.hasNext() )
            {
                key = i.next();

                string.get().append ( key );
                string.get().append ( "=" );
                string.get().append ( Cookies.get( key ).getValue() );

                if ( i.hasNext() )
                {
                    string.get().append ( "&" );
                }
            }

            StringCookies = string.get().toString();
        }

        return StringCookies;
    }

    public Cookie getCookie ( String key )
    {
        return Cookies.get ( key );
    }

    public void setQueryString ( String query_string )
    {
        QueryString = query_string;
    }

    public String getQueryString()
    {
        return QueryString;
    }

    public void setMethod ( String method )
    {
        Method = method;
    }

    public String getMethod()
    {
        return Method;
    }

    public void setRemoteUser ( String remote_user )
    {
        RemoteUser = remote_user;
    }

    public String getRemoteUser()
    {
        return RemoteUser;
    }

    public void setRequestTime ( Calendar request_time )
    {
        RequestTime = request_time;
    }

    public Calendar getRequestTime()
    {
        return RequestTime;
    }

    public void setProcessedTime ( Calendar processed_time )
    {
        ProcessedTime = processed_time;
    }

    public Calendar getProcessedTime()
    {
        return ProcessedTime;
    }
};
