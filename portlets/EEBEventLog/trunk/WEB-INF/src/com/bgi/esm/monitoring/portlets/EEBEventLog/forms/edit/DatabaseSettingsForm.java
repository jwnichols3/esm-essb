package com.bgi.esm.monitoring.portlets.EEBEventLog.forms.edit;

import java.lang.ref.WeakReference;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class DatabaseSettingsForm extends ActionForm
{
    final private static Logger _log  = Logger.getLogger ( DatabaseSettingsForm.class );
    protected String DatabaseType     = null;
    protected String DatabaseServer   = null;
    protected String DatabaseName     = null;
    protected String DatabasePort     = null;
    protected String DatabaseUsername = null;
    protected String DatabasePassword = null;

    public ActionErrors validate ( ActionMapping mapping, HttpServletRequest request )
    {
        ActionErrors errors = new ActionErrors();

        if (( null == DatabaseType ) || ( DatabaseType.trim().length() < 1 ))
        {
            errors.add ( "databaseType", new ActionMessage ( "errors.DatabaseSettings.DatebaseType.missing" ) );
        }
        if (( null == DatabaseServer ) || ( DatabaseServer.trim().length() < 1 ))
        {
            errors.add ( "databaseServer", new ActionMessage ( "errors.DatabaseSettings.DatebaseServer.missing" ) );
        }
        if (( null == DatabaseName ) || ( DatabaseName.trim().length() < 1 ))
        {
            errors.add ( "databaseName", new ActionMessage ( "errors.DatabaseSettings.DatebaseName.missing" ) );
        }
        if (( null == DatabasePort ) || ( DatabasePort.trim().length() < 1 ))
        {
            errors.add ( "databasePort", new ActionMessage ( "errors.DatabaseSettings.DatebasePort.missing" ) );
        }
        if (( null == DatabaseUsername ) || ( DatabaseUsername.trim().length() < 1 ))
        {
            errors.add ( "databaseUsername", new ActionMessage ( "errors.DatabaseSettings.DatebaseUsername.missing" ) );
        }
        if (( null == DatabasePassword ) || ( DatabasePassword.trim().length() < 1 ))
        {
            errors.add ( "databasePassword", new ActionMessage ( "errors.DatabaseSettings.DatebasePassword.missing" ) );
        }

        return errors;
    }

    public void reset ( ActionMapping mapping, HttpServletRequest request )
    {
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );

        if ( null != renderRequest )
        {
            PortletPreferences prefs = renderRequest.getPreferences();

            prefs.getMap();

            DatabaseType     = prefs.getValue ( "EEBEventLog-DatabaseType",     "" );
            DatabaseServer   = prefs.getValue ( "EEBEventLog-DatabaseServer",   "" );
            DatabaseName     = prefs.getValue ( "EEBEventLog-DatabaseName",     "" );
            DatabasePort     = prefs.getValue ( "EEBEventLog-DatabasePort",     "" );
            DatabaseUsername = prefs.getValue ( "EEBEventLog-DatabaseUsername", "" );
            DatabasePassword = prefs.getValue ( "EEBEventLog-DatabasePassword", "" );
        }
    }

    public void setDatabaseType ( String database_type )
    {
        DatabaseType = database_type;
    }

    public String getDatabaseType()
    {
        return DatabaseType;
    }

    public void setDatabaseServer ( String database_server )
    {
        DatabaseServer = database_server;
    }

    public String getDatabaseServer()
    {
        return DatabaseServer;
    }

    public void setDatabaseName ( String database_name )
    {
        DatabaseName = database_name;
    }

    public String getDatabaseName()
    {
        return DatabaseName;
    }

    public void setDatabasePort ( String database_port )
    {
        DatabasePort = database_port;
    }

    public String getDatabasePort()
    {
        return DatabasePort;
    }

    public void setDatabaseUsername ( String database_username )
    {
        DatabaseUsername = database_username;
    }

    public String getDatabaseUsername()
    {
        return DatabaseUsername;
    }

    public void setDatabasePassword ( String database_password )
    {
        DatabasePassword = database_password;
    }

    public String getDatabasePassword()
    {
        return DatabasePassword;
    }
}
