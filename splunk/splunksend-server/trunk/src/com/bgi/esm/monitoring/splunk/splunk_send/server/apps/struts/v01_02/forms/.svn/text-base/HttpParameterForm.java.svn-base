package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.forms;

import java.lang.ref.WeakReference;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter;

public class HttpParameterForm extends ActionForm implements IHttpParameter
{
    /**
     *
     */
    final private static long serialVersionUID = -6791321326790681699L;
    final private static Logger _log           = Logger.getLogger ( HttpParameterForm.class );

    private String    RowID                 = null;
    private String    RequestID             = null;
    private String    ParameterName         = null;
    private String    ParameterPersistence  = null;

    public void setValue ( IHttpParameter object )
    {
        setRequestID             ( object.getRequestID()             );
        setParameterName         ( object.getParameterName()         );
        setParameterPersistence  ( object.getParameterPersistence()  );
    }

    public IHttpParameter getValue()
    {
        try
        {
            Class <? extends IHttpParameter> c = this.getClass();
            IHttpParameter object = c.newInstance();
            object.setValue ( (IHttpParameter) this );

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
            message.get().append ( ", ParameterName=" );
            message.get().append ( getParameterName() );
            message.get().append ( ", ParameterPersistence=" );
            message.get().append ( getParameterPersistence() );
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
            message.get().append ( "    <parameter_name><![CDATA[" );
            message.get().append ( getParameterName() );
            message.get().append ( "]]></parameter_name>\n" );
            message.get().append ( "    <parameter_persistence><![CDATA[" );
            message.get().append ( getParameterPersistence() );
            message.get().append ( "]]></parameter_persistence>\n" );
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
     *  Setter method for the 'parameter_name' column
     *
     *  @param parameter_name  The new value for the 'parameter_name' column
     */
    public void setParameterName ( String parameter_name )
    {
        this.ParameterName = parameter_name;
    }

    /**
     *  Getter method for the 'parameter_name' column
     *  @return The new value for the 'parameter_name' column
     */
    public String getParameterName ()
    {
        return ParameterName;
    }

    /**
     *  Setter method for the 'parameter_persistence' column
     *
     *  @param parameter_persistence  The new value for the 'parameter_persistence' column
     */
    public void setParameterPersistence ( String parameter_persistence )
    {
        this.ParameterPersistence = parameter_persistence;
    }

    /**
     *  Getter method for the 'parameter_persistence' column
     *  @return The new value for the 'parameter_persistence' column
     */
    public String getParameterPersistence ()
    {
        return ParameterPersistence;
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