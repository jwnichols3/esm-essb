package com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence;

import java.io.Serializable;
import org.apache.log4j.Logger;

public abstract class AbstractHttpHeader implements Serializable
{
    final private static Logger _log = Logger.getLogger ( AbstractHttpHeader.class );
    protected String RowID           = null;
    protected String RequestID       = null;
    protected String HeaderName      = null;
    protected String HeaderValue     = null;

    public AbstractHttpHeader()
    {
    }

    public AbstractHttpHeader getValue()
    {
        try 
        {
            Class c = this.getClass();
            AbstractHttpHeader object = (AbstractHttpHeader) c.newInstance();
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

    public void setValue ( AbstractHttpHeader parameter )
    {
        setRequestID   ( parameter.getRequestID()   );
        setHeaderName  ( parameter.getHeaderName()  );
        setHeaderValue ( parameter.getHeaderValue() );
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

    public void setHeaderName ( String header_name )
    {
        HeaderName = header_name;
    }

    public String getHeaderName()
    {
        return HeaderName;
    }

    public void setHeaderValue ( String header_value )
    {
        HeaderValue = header_value;
    }

    public String getHeaderValue()
    {
        return HeaderValue;
    }
}
