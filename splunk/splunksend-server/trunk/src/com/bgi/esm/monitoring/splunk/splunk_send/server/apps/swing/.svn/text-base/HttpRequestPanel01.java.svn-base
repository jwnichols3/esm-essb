package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.Configuration;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpRequest;

public class HttpRequestPanel01 extends BasePanel implements ActionListener
{
    @SuppressWarnings ( "unused" )
    final private static Logger _log = Logger.getLogger ( HttpRequestPanel01.class );
    final private static long serialVersionUID = 3123733864983190907L;

    /**
     *  GUI Components
     */
    private JLabel     AuthTypeLabel                                = null;
    private JLabel     ContextPathLabel                             = null;
    private JLabel     MethodLabel                                  = null;
    private JLabel     PathInfoLabel                                = null;
    private JLabel     PathInfoTranslatedLabel                      = null;
    private JLabel     RemoteUserLabel                              = null;
    private JLabel     RequestedSessionIDLabel                      = null;
    private JLabel     RequestUriLabel                              = null;
    private JLabel     RequestUrlLabel                              = null;
    private JComponent AuthTypeComponent                            = null;
    private JComponent ContextPathComponent                         = null;
    private JComponent MethodComponent                              = null;
    private JComponent PathInfoComponent                            = null;
    private JComponent PathInfoTranslatedComponent                  = null;
    private JComponent RemoteUserComponent                          = null;
    private JComponent RequestedSessionIDComponent                  = null;
    private JComponent RequestUriComponent                          = null;
    private JComponent RequestUrlComponent                          = null;

    public HttpRequestPanel01()
    {
        super();

        initialize();
    }

    private void initialize()
    {
        ////////////////////////////////////////////////////////////////////////
        //  Create the GUI components
        ////////////////////////////////////////////////////////////////////////
        Properties DatabaseProperties = Configuration.getInstance().getDatabaseProperties();

        //  GUI components for the 'auth_type' column
        AuthTypeLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.auth_type.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForAuthType() ) && ( AbstractHttpRequest.getDefaultValuesForAuthType().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForAuthType();
            JComboBox AuthTypeComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                AuthTypeComboBox.addItem ( defaultValues[counter] );
            }

            AuthTypeComponent = AuthTypeComboBox;
        }
        else
        {
            AuthTypeComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'context_path' column
        ContextPathLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.context_path.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForContextPath() ) && ( AbstractHttpRequest.getDefaultValuesForContextPath().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForContextPath();
            JComboBox ContextPathComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                ContextPathComboBox.addItem ( defaultValues[counter] );
            }

            ContextPathComponent = ContextPathComboBox;
        }
        else
        {
            ContextPathComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'method' column
        MethodLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.method.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForMethod() ) && ( AbstractHttpRequest.getDefaultValuesForMethod().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForMethod();
            JComboBox MethodComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                MethodComboBox.addItem ( defaultValues[counter] );
            }

            MethodComponent = MethodComboBox;
        }
        else
        {
            MethodComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'path_info' column
        PathInfoLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.path_info.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForPathInfo() ) && ( AbstractHttpRequest.getDefaultValuesForPathInfo().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForPathInfo();
            JComboBox PathInfoComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                PathInfoComboBox.addItem ( defaultValues[counter] );
            }

            PathInfoComponent = PathInfoComboBox;
        }
        else
        {
            PathInfoComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'path_info_translated' column
        PathInfoTranslatedLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.path_info_translated.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForPathInfoTranslated() ) && ( AbstractHttpRequest.getDefaultValuesForPathInfoTranslated().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForPathInfoTranslated();
            JComboBox PathInfoTranslatedComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                PathInfoTranslatedComboBox.addItem ( defaultValues[counter] );
            }

            PathInfoTranslatedComponent = PathInfoTranslatedComboBox;
        }
        else
        {
            PathInfoTranslatedComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'remote_user' column
        RemoteUserLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.remote_user.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForRemoteUser() ) && ( AbstractHttpRequest.getDefaultValuesForRemoteUser().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForRemoteUser();
            JComboBox RemoteUserComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                RemoteUserComboBox.addItem ( defaultValues[counter] );
            }

            RemoteUserComponent = RemoteUserComboBox;
        }
        else
        {
            RemoteUserComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'requested_session_id' column
        RequestedSessionIDLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.requested_session_id.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForRequestedSessionID() ) && ( AbstractHttpRequest.getDefaultValuesForRequestedSessionID().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForRequestedSessionID();
            JComboBox RequestedSessionIDComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                RequestedSessionIDComboBox.addItem ( defaultValues[counter] );
            }

            RequestedSessionIDComponent = RequestedSessionIDComboBox;
        }
        else
        {
            RequestedSessionIDComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'request_uri' column
        RequestUriLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.request_uri.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForRequestUri() ) && ( AbstractHttpRequest.getDefaultValuesForRequestUri().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForRequestUri();
            JComboBox RequestUriComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                RequestUriComboBox.addItem ( defaultValues[counter] );
            }

            RequestUriComponent = RequestUriComboBox;
        }
        else
        {
            RequestUriComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'request_url' column
        RequestUrlLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.request_url.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForRequestUrl() ) && ( AbstractHttpRequest.getDefaultValuesForRequestUrl().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForRequestUrl();
            JComboBox RequestUrlComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                RequestUrlComboBox.addItem ( defaultValues[counter] );
            }

            RequestUrlComponent = RequestUrlComboBox;
        }
        else
        {
            RequestUrlComponent = new JTextField ( 20 );
        }

        ////////////////////////////////////////////////////////////////////////
        //  Set up the GUI layout
        ////////////////////////////////////////////////////////////////////////
        setLayout ( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        c.fill               = GridBagConstraints.HORIZONTAL;
        c.anchor             = GridBagConstraints.NORTHWEST;
        c.insets             = new Insets ( 0, 10, 0, 0 );  // top padding
        int row_counter      = 0;

        //  Setting up the fields for the 'auth_type' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( AuthTypeLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( AuthTypeComponent, c );

        row_counter++;

        //  Setting up the fields for the 'context_path' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ContextPathLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( ContextPathComponent, c );

        row_counter++;

        //  Setting up the fields for the 'method' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( MethodLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( MethodComponent, c );

        row_counter++;

        //  Setting up the fields for the 'path_info' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( PathInfoLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( PathInfoComponent, c );

        row_counter++;

        //  Setting up the fields for the 'path_info_translated' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( PathInfoTranslatedLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( PathInfoTranslatedComponent, c );

        row_counter++;

        //  Setting up the fields for the 'remote_user' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RemoteUserLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( RemoteUserComponent, c );

        row_counter++;

        //  Setting up the fields for the 'requested_session_id' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RequestedSessionIDLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( RequestedSessionIDComponent, c );

        row_counter++;

        //  Setting up the fields for the 'request_uri' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RequestUriLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( RequestUriComponent, c );

        row_counter++;

        //  Setting up the fields for the 'request_url' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RequestUrlLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( RequestUrlComponent, c );
    }

    public void actionPerformed ( ActionEvent event )
    {
        final Object eventSource = event.getSource();

             if ( eventSource == AuthTypeComponent                           )
        {
        }
        else if ( eventSource == ContextPathComponent                        )
        {
        }
        else if ( eventSource == MethodComponent                             )
        {
        }
        else if ( eventSource == PathInfoComponent                           )
        {
        }
        else if ( eventSource == PathInfoTranslatedComponent                 )
        {
        }
        else if ( eventSource == RemoteUserComponent                         )
        {
        }
        else if ( eventSource == RequestedSessionIDComponent                 )
        {
        }
        else if ( eventSource == RequestUriComponent                         )
        {
        }
        else if ( eventSource == RequestUrlComponent                         )
        {
        }
    }
}