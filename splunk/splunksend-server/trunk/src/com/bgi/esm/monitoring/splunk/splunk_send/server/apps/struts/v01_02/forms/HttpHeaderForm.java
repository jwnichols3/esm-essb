package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.forms;

import java.lang.ref.WeakReference;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader;

public class HttpHeaderForm extends ActionForm implements IHttpHeader
{
    /**
     *
     */
    final private static long serialVersionUID = -1340793470350387472L;
    final private static Logger _log           = Logger.getLogger ( HttpHeaderForm.class );

    private String    RowID              = null;
    private String    RequestID          = null;
    private String    HeaderName         = null;
    private String    HeaderPersistence  = null;

    public void setValue ( IHttpHeader object )
    {
        setRequestID          ( object.getRequestID()          );
        setHeaderName         ( object.getHeaderName()         );
        setHeaderPersistence  ( object.getHeaderPersistence()  );
    }

    public IHttpHeader getValue()
    {
        try
        {
            Class <? extends IHttpHeader> c = this.getClass();
            IHttpHeader object = c.newInstance();
            object.setValue ( (IHttpHeader) this );

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
            message.get().append ( ", HeaderName=" );
            message.get().append ( getHeaderName() );
            message.get().append ( ", HeaderPersistence=" );
            message.get().append ( getHeaderPersistence() );
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
            message.get().append ( "    <header_name><![CDATA[" );
            message.get().append ( getHeaderName() );
            message.get().append ( "]]></header_name>\n" );
            message.get().append ( "    <header_persistence><![CDATA[" );
            message.get().append ( getHeaderPersistence() );
            message.get().append ( "]]></header_persistence>\n" );
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
     *  Setter method for the 'header_name' column
     *
     *  @param header_name  The new value for the 'header_name' column
     */
    public void setHeaderName ( String header_name )
    {
        this.HeaderName = header_name;
    }

    /**
     *  Getter method for the 'header_name' column
     *  @return The new value for the 'header_name' column
     */
    public String getHeaderName ()
    {
        return HeaderName;
    }

    /**
     *  Setter method for the 'header_persistence' column
     *
     *  @param header_persistence  The new value for the 'header_persistence' column
     */
    public void setHeaderPersistence ( String header_persistence )
    {
        this.HeaderPersistence = header_persistence;
    }

    /**
     *  Getter method for the 'header_persistence' column
     *  @return The new value for the 'header_persistence' column
     */
    public String getHeaderPersistence ()
    {
        return HeaderPersistence;
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