package com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence;

import java.io.Serializable;
import org.apache.log4j.Logger;

public abstract class AbstractHttpParameter implements Serializable
{
    final private static Logger _log = Logger.getLogger ( AbstractHttpParameter.class );
    protected String RowID           = null;
    protected String RequestID       = null;
    protected String ParameterName   = null;
    protected String ParameterValue  = null;

    public AbstractHttpParameter()
    {
    }

    public AbstractHttpParameter getValue()
    {
        try 
        {
            Class c = this.getClass();
            AbstractHttpParameter object = (AbstractHttpParameter) c.newInstance();
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

    public void setValue ( AbstractHttpParameter parameter )
    {
        setRequestID      ( parameter.getRequestID()      );
        setParameterName  ( parameter.getParameterName()  );
        setParameterValue ( parameter.getParameterValue() );
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

    public void setParameterName ( String parameter_name )
    {
        ParameterName = parameter_name;
    }

    public String getParameterName()
    {
        return ParameterName;
    }

    public void setParameterValue ( String parameter_value )
    {
        ParameterValue = parameter_value;
    }

    public String getParameterValue()
    {
        return ParameterValue;
    }
}
