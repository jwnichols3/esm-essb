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

public class HttpRequestPanel02 extends BasePanel implements ActionListener
{
    @SuppressWarnings ( "unused" )
    final private static Logger _log = Logger.getLogger ( HttpRequestPanel02.class );
    final private static long serialVersionUID = 1733992840312091838L;

    /**
     *  GUI Components
     */
    private JLabel     ServletPathLabel                           = null;
    private JLabel     UserPrincipalLabel                         = null;
    private JLabel     CharacterEncodingLabel                     = null;
    private JLabel     ContentTypeLabel                           = null;
    private JLabel     LocalAddressLabel                          = null;
    private JLabel     ProtocolLabel                              = null;
    private JLabel     RemoteAddressLabel                         = null;
    private JLabel     RemoteHostLabel                            = null;
    private JLabel     SchemeLabel                                = null;
    private JLabel     ServerNameLabel                            = null;
    private JComponent ServletPathComponent                       = null;
    private JComponent UserPrincipalComponent                     = null;
    private JComponent CharacterEncodingComponent                 = null;
    private JComponent ContentTypeComponent                       = null;
    private JComponent LocalAddressComponent                      = null;
    private JComponent ProtocolComponent                          = null;
    private JComponent RemoteAddressComponent                     = null;
    private JComponent RemoteHostComponent                        = null;
    private JComponent SchemeComponent                            = null;
    private JComponent ServerNameComponent                        = null;

    public HttpRequestPanel02()
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

        //  GUI components for the 'servlet_path' column
        ServletPathLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.servlet_path.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForServletPath() ) && ( AbstractHttpRequest.getDefaultValuesForServletPath().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForServletPath();
            JComboBox ServletPathComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                ServletPathComboBox.addItem ( defaultValues[counter] );
            }

            ServletPathComponent = ServletPathComboBox;
        }
        else
        {
            ServletPathComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'user_principal' column
        UserPrincipalLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.user_principal.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForUserPrincipal() ) && ( AbstractHttpRequest.getDefaultValuesForUserPrincipal().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForUserPrincipal();
            JComboBox UserPrincipalComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                UserPrincipalComboBox.addItem ( defaultValues[counter] );
            }

            UserPrincipalComponent = UserPrincipalComboBox;
        }
        else
        {
            UserPrincipalComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'character_encoding' column
        CharacterEncodingLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.character_encoding.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForCharacterEncoding() ) && ( AbstractHttpRequest.getDefaultValuesForCharacterEncoding().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForCharacterEncoding();
            JComboBox CharacterEncodingComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                CharacterEncodingComboBox.addItem ( defaultValues[counter] );
            }

            CharacterEncodingComponent = CharacterEncodingComboBox;
        }
        else
        {
            CharacterEncodingComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'content_type' column
        ContentTypeLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.content_type.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForContentType() ) && ( AbstractHttpRequest.getDefaultValuesForContentType().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForContentType();
            JComboBox ContentTypeComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                ContentTypeComboBox.addItem ( defaultValues[counter] );
            }

            ContentTypeComponent = ContentTypeComboBox;
        }
        else
        {
            ContentTypeComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'local_address' column
        LocalAddressLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.local_address.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForLocalAddress() ) && ( AbstractHttpRequest.getDefaultValuesForLocalAddress().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForLocalAddress();
            JComboBox LocalAddressComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                LocalAddressComboBox.addItem ( defaultValues[counter] );
            }

            LocalAddressComponent = LocalAddressComboBox;
        }
        else
        {
            LocalAddressComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'protocol' column
        ProtocolLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.protocol.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForProtocol() ) && ( AbstractHttpRequest.getDefaultValuesForProtocol().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForProtocol();
            JComboBox ProtocolComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                ProtocolComboBox.addItem ( defaultValues[counter] );
            }

            ProtocolComponent = ProtocolComboBox;
        }
        else
        {
            ProtocolComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'remote_address' column
        RemoteAddressLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.remote_address.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForRemoteAddress() ) && ( AbstractHttpRequest.getDefaultValuesForRemoteAddress().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForRemoteAddress();
            JComboBox RemoteAddressComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                RemoteAddressComboBox.addItem ( defaultValues[counter] );
            }

            RemoteAddressComponent = RemoteAddressComboBox;
        }
        else
        {
            RemoteAddressComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'remote_host' column
        RemoteHostLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.remote_host.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForRemoteHost() ) && ( AbstractHttpRequest.getDefaultValuesForRemoteHost().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForRemoteHost();
            JComboBox RemoteHostComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                RemoteHostComboBox.addItem ( defaultValues[counter] );
            }

            RemoteHostComponent = RemoteHostComboBox;
        }
        else
        {
            RemoteHostComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'scheme' column
        SchemeLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.scheme.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForScheme() ) && ( AbstractHttpRequest.getDefaultValuesForScheme().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForScheme();
            JComboBox SchemeComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                SchemeComboBox.addItem ( defaultValues[counter] );
            }

            SchemeComponent = SchemeComboBox;
        }
        else
        {
            SchemeComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'server_name' column
        ServerNameLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.server_name.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForServerName() ) && ( AbstractHttpRequest.getDefaultValuesForServerName().length > 0 ))
        {
            String defaultValues[] = AbstractHttpRequest.getDefaultValuesForServerName();
            JComboBox ServerNameComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                ServerNameComboBox.addItem ( defaultValues[counter] );
            }

            ServerNameComponent = ServerNameComboBox;
        }
        else
        {
            ServerNameComponent = new JTextField ( 20 );
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

        //  Setting up the fields for the 'servlet_path' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ServletPathLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( ServletPathComponent, c );

        row_counter++;

        //  Setting up the fields for the 'user_principal' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( UserPrincipalLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( UserPrincipalComponent, c );

        row_counter++;

        //  Setting up the fields for the 'character_encoding' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( CharacterEncodingLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( CharacterEncodingComponent, c );

        row_counter++;

        //  Setting up the fields for the 'content_type' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ContentTypeLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( ContentTypeComponent, c );

        row_counter++;

        //  Setting up the fields for the 'local_address' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( LocalAddressLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( LocalAddressComponent, c );

        row_counter++;

        //  Setting up the fields for the 'protocol' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ProtocolLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( ProtocolComponent, c );

        row_counter++;

        //  Setting up the fields for the 'remote_address' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RemoteAddressLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( RemoteAddressComponent, c );

        row_counter++;

        //  Setting up the fields for the 'remote_host' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RemoteHostLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( RemoteHostComponent, c );

        row_counter++;

        //  Setting up the fields for the 'scheme' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( SchemeLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( SchemeComponent, c );

        row_counter++;

        //  Setting up the fields for the 'server_name' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ServerNameLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( ServerNameComponent, c );
    }

    public void actionPerformed ( ActionEvent event )
    {
        final Object eventSource = event.getSource();

             if ( eventSource == ServletPathComponent                      )
        {
        }
        else if ( eventSource == UserPrincipalComponent                    )
        {
        }
        else if ( eventSource == CharacterEncodingComponent                )
        {
        }
        else if ( eventSource == ContentTypeComponent                      )
        {
        }
        else if ( eventSource == LocalAddressComponent                     )
        {
        }
        else if ( eventSource == ProtocolComponent                         )
        {
        }
        else if ( eventSource == RemoteAddressComponent                    )
        {
        }
        else if ( eventSource == RemoteHostComponent                       )
        {
        }
        else if ( eventSource == SchemeComponent                           )
        {
        }
        else if ( eventSource == ServerNameComponent                       )
        {
        }
    }
}