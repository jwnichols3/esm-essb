package com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import org.apache.log4j.Logger;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class Configuration
{
    final private static Logger _log                   = Logger.getLogger ( Configuration.class );
    private static Configuration ref                   = null;

    final private static OrmFacade _orm                = new OrmFacade();
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
    private static String apjc_command_line_retrieve   = null;

    private static String externalLogFileName          = "c:\\test\\JMS-Alarmpoint-Configuration.out";


    //  This is required for the Singleton pattern
    private Configuration()
    {
        try
        {
            LogToFile ( externalLogFileName, "Loading properties file: " + prop_filename );

            //  Set the variables used to connect to the Alarmpoint Java Client
            apjc_hostname                = _orm.selectEebProperty ( "Alarmpoint.JavaClient.hostname"                ).getPropertyValue();
            apjc_port                    = _orm.selectEebProperty ( "Alarmpoint.JavaClient.port"                    ).getPropertyValue();
            apjc_http_baseref            = _orm.selectEebProperty ( "Alarmpoint.JavaClient.http.baseref"            ).getPropertyValue();
            apjc_http_form_action        = _orm.selectEebProperty ( "Alarmpoint.JavaClient.http.form.action"        ).getPropertyValue();
            apjc_http_form_enctype       = _orm.selectEebProperty ( "Alarmpoint.JavaClient.http.form.enctype"       ).getPropertyValue();
            apjc_http_form_submit_target = _orm.selectEebProperty ( "Alarmpoint.JavaClient.http.form.submit_target" ).getPropertyValue();
            apjc_http_form_message_field = _orm.selectEebProperty ( "Alarmpoint.JavaClient.http.form.message_field" ).getPropertyValue();

            apjc_command_line_path       = _orm.selectEebProperty ( "Alarmpoint.JavaClient.CommandLine.path"        ).getPropertyValue();
            apjc_command_line_command    = _orm.selectEebProperty ( "Alarmpoint.JavaClient.CommandLine.command"     ).getPropertyValue();

            //  Construct the command-line command for executing 
            StringBuilder command = new StringBuilder();
            command.append ( apjc_command_line_path );
            command.append ( " --" );
            command.append ( apjc_command_line_command );

            LogToFile ( externalLogFileName, "Command-line command: " + command.toString() );

            //  Construct the URL that the HTML form will be submitted for processing
            apjc_http_form_submit_url    = null;
            StringBuilder url = new StringBuilder ( "http://" );
            url.append ( apjc_hostname                );
            url.append ( ":"                          );
            url.append ( apjc_port                    );
            url.append ( apjc_http_form_submit_target );
            apjc_http_form_submit_url    = url.toString();

            LogToFile ( externalLogFileName, "APJC Submission URL: " + apjc_http_form_submit_url );
        }
        catch ( Exception exception )
        {
            _log.fatal ( "Could not load properties for connecting to the Alarmpoint Java Client: prop_file=" + prop_filename + ", using default values" );
            LogToFile ( externalLogFileName,  "Could not load properties for connecting to the Alarmpoint Java Client: prop_file=" + prop_filename + ", using default values" );
            //apjc_hostname                = "rdcuxsrv217";
            apjc_hostname                = "localhost";
            apjc_port                    = "2010";
            apjc_http_baseref            = "/agent/http";
            apjc_http_form_action        = "POST";
            apjc_http_form_enctype       = "application/x-www-form-urlencoded";
            apjc_http_form_submit_target = "/agent";
            apjc_http_form_message_field = "message";
            apjc_command_line_path       = "C:/APAgent/APClient.bin.exe";
            apjc_command_line_command    = "";

            StringBuilder url = new StringBuilder ( "http://" );
            url.append ( apjc_hostname                );
            url.append ( ":"                          );
            url.append ( apjc_port                    );
            url.append ( apjc_http_form_submit_target );
            apjc_http_form_submit_url    = url.toString();

            StringBuilder cmd_retrieve   = new StringBuilder ( apjc_command_line_path );
            cmd_retrieve.append ( " --get-response openview" );
            apjc_command_line_retrieve   = cmd_retrieve.toString();
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

    public final static void LogToFile ( String filename, String message )
    {
        if ( _log.isDebugEnabled() )
        {
            try
            {
                FileOutputStream outfile = new FileOutputStream ( filename, true );
                    outfile.write ( (new java.util.Date()).toString().getBytes() );
                    outfile.write ( " - ".getBytes() );
                    outfile.write ( message.getBytes() );
                    outfile.write ( "\n".getBytes() );
                outfile.close();
            }
            catch ( IOException exception )
            {
                _log.fatal ( "Could not log message to filename: " + filename );
            }
        }
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
