package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.forms;

import java.lang.ref.WeakReference;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap;

public class DataMapForm extends ActionForm implements IDataMap
{
    /**
     *
     */
    final private static long serialVersionUID = -7396176132200398261L;
    final private static Logger _log           = Logger.getLogger ( DataMapForm.class );

    private String    RuleID           = null;
    private String    ApplicationName  = null;
    private String    Hostname         = null;
    private String    TargetPath       = null;

    public void setValue ( IDataMap object )
    {
        setApplicationName  ( object.getApplicationName()  );
        setHostname         ( object.getHostname()         );
        setTargetPath       ( object.getTargetPath()       );
    }

    public IDataMap getValue()
    {
        try
        {
            Class <? extends IDataMap> c = this.getClass();
            IDataMap object = c.newInstance();
            object.setValue ( (IDataMap) this );

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
            message.get().append ( "RuleID=" );
            message.get().append ( getRuleID() );
            message.get().append ( ", ApplicationName=" );
            message.get().append ( getApplicationName() );
            message.get().append ( ", Hostname=" );
            message.get().append ( getHostname() );
            message.get().append ( ", TargetPath=" );
            message.get().append ( getTargetPath() );
            message.get().append ( " )" );
        return message.get().toString();
    }

    public String toXML()
    {
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "<item classname=\"" );
            message.get().append ( getClass().getName() );
            message.get().append ( "\">\n" );
            message.get().append ( "    <rule_id><![CDATA[" );
            message.get().append ( getRuleID() );
            message.get().append ( "]]></rule_id>\n" );
            message.get().append ( "    <application_name><![CDATA[" );
            message.get().append ( getApplicationName() );
            message.get().append ( "]]></application_name>\n" );
            message.get().append ( "    <hostname><![CDATA[" );
            message.get().append ( getHostname() );
            message.get().append ( "]]></hostname>\n" );
            message.get().append ( "    <target_path><![CDATA[" );
            message.get().append ( getTargetPath() );
            message.get().append ( "]]></target_path>\n" );
            message.get().append ( "</item>" );
        return message.get().toString();
    }

    /**
     *  Setter method for the 'rule_id' column
     *
     *  @param rule_id  The new value for the 'rule_id' column
     */
    public void setRuleID ( String rule_id )
    {
        this.RuleID = rule_id;
    }

    /**
     *  Getter method for the 'rule_id' column
     *  @return The new value for the 'rule_id' column
     */
    public String getRuleID ()
    {
        return RuleID;
    }

    /**
     *  Setter method for the 'application_name' column
     *
     *  @param application_name  The new value for the 'application_name' column
     */
    public void setApplicationName ( String application_name )
    {
        this.ApplicationName = application_name;
    }

    /**
     *  Getter method for the 'application_name' column
     *  @return The new value for the 'application_name' column
     */
    public String getApplicationName ()
    {
        return ApplicationName;
    }

    /**
     *  Setter method for the 'hostname' column
     *
     *  @param hostname  The new value for the 'hostname' column
     */
    public void setHostname ( String hostname )
    {
        this.Hostname = hostname;
    }

    /**
     *  Getter method for the 'hostname' column
     *  @return The new value for the 'hostname' column
     */
    public String getHostname ()
    {
        return Hostname;
    }

    /**
     *  Setter method for the 'target_path' column
     *
     *  @param target_path  The new value for the 'target_path' column
     */
    public void setTargetPath ( String target_path )
    {
        this.TargetPath = target_path;
    }

    /**
     *  Getter method for the 'target_path' column
     *  @return The new value for the 'target_path' column
     */
    public String getTargetPath ()
    {
        return TargetPath;
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