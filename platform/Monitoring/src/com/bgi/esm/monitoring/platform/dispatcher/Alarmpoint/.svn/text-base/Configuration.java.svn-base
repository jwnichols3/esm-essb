package com.bgi.esm.monitoring.platform.dispatcher.Alarmpoint;

import java.io.InputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Properties;
import org.apache.log4j.Logger;

public class Configuration
{
    final private static Logger _log = Logger.getLogger ( Configuration.class );
    private static Configuration ref = null;

    //  Connection parameters to the Alarmpoint Java Client
    final private static String prop_filename          = "AlarmpointJavaClient.properties";
    private static String apjc_hostname                = null;
    private static String apjc_port                    = null;
    private static String apjc_http_baseref            = null;
    private static String apjc_http_form_action        = null;
    private static String apjc_http_form_enctype       = null;
    private static String apjc_http_form_submit_target = null;
    private static String apjc_http_form_message_field = null;
    private static String apjc_http_form_submit_url    = null;
    private static String apjc_command_line_path       = null;
    private static String apjc_command_line_command    = null;
    private static String apjc_command_line_execute    = null;

    private static Properties apjc_props               = null;


    //  This is required for the Singleton pattern
    private Configuration()
    {
        try
        {
            if ( null == apjc_props )
            {
                //  Load the properties file
                apjc_props = new Properties();
                ClassLoader cl = Notification.class.getClassLoader();
                InputStream is = cl.getResourceAsStream ( prop_filename );
                apjc_props.load ( is );

                //  Set the variables used to connect to the Alarmpoint Java Client
                apjc_hostname                = apjc_props.getProperty ( "alarmpoint.JavaClient.hostname",                null );
                apjc_port                    = apjc_props.getProperty ( "alarmpoint.JavaClient.port",                    "80" );
                apjc_http_baseref            = apjc_props.getProperty ( "alarmpoint.JavaClient.http.baseref",            null );
                apjc_http_form_action        = apjc_props.getProperty ( "alarmpoint.JavaClient.http.form.action",        null );
                apjc_http_form_enctype       = apjc_props.getProperty ( "alarmpoint.JavaClient.http.form.enctype",       null );
                apjc_http_form_submit_target = apjc_props.getProperty ( "alarmpoint.JavaClient.http.form.submit_target", null );
                apjc_http_form_message_field = apjc_props.getProperty ( "alarmpoint.JavaClient.http.form.message_field", null );

                apjc_command_line_path       = apjc_props.getProperty ( "alarmpoint.JavaClient.CommandLine.path",        null );
                apjc_command_line_command    = apjc_props.getProperty ( "alarmpoint.JavaClient.CommandLine.command",     null );

                //  Construct the command-line command for executing 
                StringBuilder command = new StringBuilder();
                command.append ( apjc_command_line_path );
                command.append ( " --" );
                command.append ( apjc_command_line_command );

                //  Construct the URL that the HTML form will be submitted for processing
                apjc_http_form_submit_url    = null;
                StringBuilder url = new StringBuilder ( "http://" );
                url.append ( apjc_hostname                );
                url.append ( ":"                          );
                url.append ( apjc_port                    );
                url.append ( apjc_http_form_submit_target );
                apjc_http_form_submit_url    = url.toString();
            }
        }
        catch ( IOException exception )
        {
            _log.fatal ( "Could not load properties for connecting to the Alarmpoint Java Client: prop_file=" + prop_filename );
        }
    }

    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException ( Configuration.class.getName() + " can not be cloned" );
    }

    public static Configuration getInstance()
    {
        if ( null == ref )
        {
            ref = new Configuration();
        }

        return ref;
    }

    public static String getHttpClientFormActionURL()
    {
        return apjc_http_form_submit_url;
    }

    public static String getHostname()
    {
        getInstance();

        return apjc_hostname;
    }

    public static int getPort()
    {
        getInstance();

        return Integer.parseInt ( apjc_port );
    }

    public static String getHTTPBaseRef()
    {
        getInstance();

        return apjc_http_baseref;
    }

    public static String getHTTPFormAction()
    {
        getInstance();

        return apjc_http_form_action;
    }

    public static String getHTTPEncType()
    {
        getInstance();

        return apjc_http_form_enctype;
    }

    public static String getHTTPFormSubmitTarget()
    {
        getInstance();

        return apjc_http_form_submit_target;
    }

    public static String getHTTPFormMessageFieldName()
    {
        getInstance();

        return apjc_http_form_message_field;
    }

    public static String getCommandLinePath()
    {
        getInstance();

        return apjc_command_line_path;
    }

    public static String getCommandLineCommand()
    {
        getInstance();

        return apjc_command_line_command;
    }

    public static String getCommandLineExecutionCommand()
    {
        getInstance();

        return apjc_command_line_execute;
    }
};
