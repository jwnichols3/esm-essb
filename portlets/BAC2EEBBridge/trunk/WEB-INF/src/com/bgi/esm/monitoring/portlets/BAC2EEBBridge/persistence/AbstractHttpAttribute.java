package com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence;

import java.io.Serializable;
import org.apache.log4j.Logger;

public abstract class AbstractHttpAttribute implements Serializable
{
    final private static Logger _log = Logger.getLogger ( AbstractHttpAttribute.class );
    protected String RowID           = null;
    protected String RequestID       = null;
    protected String AttributeName   = null;
    protected String AttributeValue  = null;

    public AbstractHttpAttribute()
    {
    }

    public AbstractHttpAttribute getValue()
    {
        try 
        {
            Class c = this.getClass();
            AbstractHttpAttribute object = (AbstractHttpAttribute) c.newInstance();
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

    public void setValue ( AbstractHttpAttribute parameter )
    {
        setRequestID      ( parameter.getRequestID()      );
        setAttributeName  ( parameter.getAttributeName()  );
        setAttributeValue ( parameter.getAttributeValue() );
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

    public void setAttributeName ( String attribute_name )
    {
        AttributeName = attribute_name;
    }

    public String getAttributeName()
    {
        return AttributeName;
    }

    public void setAttributeValue ( String attribute_value )
    {
        AttributeValue = attribute_value;
    }

    public String getAttributeValue()
    {
        return AttributeValue;
    }
}
