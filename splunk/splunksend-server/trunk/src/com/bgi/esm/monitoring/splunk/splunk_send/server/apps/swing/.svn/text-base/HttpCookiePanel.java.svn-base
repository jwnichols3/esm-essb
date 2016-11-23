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
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpCookie;

public class HttpCookiePanel extends BasePanel implements ActionListener
{
    @SuppressWarnings ( "unused" )
    final private static Logger _log = Logger.getLogger ( HttpCookiePanel.class );
    final private static long serialVersionUID = -685631468767551327L;

    /**
     *  GUI Components
     */
    private JLabel     RequestIDLabel                             = null;
    private JLabel     CookieCommentLabel                         = null;
    private JLabel     DomainLabel                                = null;
    private JLabel     PathLabel                                  = null;
    private JLabel     CookieNameLabel                            = null;
    private JLabel     CookiePersistenceLabel                     = null;
    private JLabel     IsSecureLabel                              = null;
    private JLabel     MaxAgeLabel                                = null;
    private JLabel     VersionLabel                               = null;
    private JComponent RequestIDComponent                         = null;
    private JComponent CookieCommentComponent                     = null;
    private JComponent DomainComponent                            = null;
    private JComponent PathComponent                              = null;
    private JComponent CookieNameComponent                        = null;
    private JComponent CookiePersistenceComponent                 = null;
    private JComponent IsSecureComponent                          = null;
    private JComponent MaxAgeComponent                            = null;
    private JComponent VersionComponent                           = null;

    public HttpCookiePanel()
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

        //  GUI components for the 'request_id' column
        RequestIDLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_cookie.request_id.display_name" ) );
        if (( null != AbstractHttpCookie.getDefaultValuesForRequestID() ) && ( AbstractHttpCookie.getDefaultValuesForRequestID().length > 0 ))
        {
            String defaultValues[] = AbstractHttpCookie.getDefaultValuesForRequestID();
            JComboBox RequestIDComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                RequestIDComboBox.addItem ( defaultValues[counter] );
            }

            RequestIDComponent = RequestIDComboBox;
        }
        else
        {
            RequestIDComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'cookie_comment' column
        CookieCommentLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_cookie.cookie_comment.display_name" ) );
        if (( null != AbstractHttpCookie.getDefaultValuesForCookieComment() ) && ( AbstractHttpCookie.getDefaultValuesForCookieComment().length > 0 ))
        {
            String defaultValues[] = AbstractHttpCookie.getDefaultValuesForCookieComment();
            JComboBox CookieCommentComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                CookieCommentComboBox.addItem ( defaultValues[counter] );
            }

            CookieCommentComponent = CookieCommentComboBox;
        }
        else
        {
            CookieCommentComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'domain' column
        DomainLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_cookie.domain.display_name" ) );
        if (( null != AbstractHttpCookie.getDefaultValuesForDomain() ) && ( AbstractHttpCookie.getDefaultValuesForDomain().length > 0 ))
        {
            String defaultValues[] = AbstractHttpCookie.getDefaultValuesForDomain();
            JComboBox DomainComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                DomainComboBox.addItem ( defaultValues[counter] );
            }

            DomainComponent = DomainComboBox;
        }
        else
        {
            DomainComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'path' column
        PathLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_cookie.path.display_name" ) );
        if (( null != AbstractHttpCookie.getDefaultValuesForPath() ) && ( AbstractHttpCookie.getDefaultValuesForPath().length > 0 ))
        {
            String defaultValues[] = AbstractHttpCookie.getDefaultValuesForPath();
            JComboBox PathComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                PathComboBox.addItem ( defaultValues[counter] );
            }

            PathComponent = PathComboBox;
        }
        else
        {
            PathComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'cookie_name' column
        CookieNameLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_cookie.cookie_name.display_name" ) );
        if (( null != AbstractHttpCookie.getDefaultValuesForCookieName() ) && ( AbstractHttpCookie.getDefaultValuesForCookieName().length > 0 ))
        {
            String defaultValues[] = AbstractHttpCookie.getDefaultValuesForCookieName();
            JComboBox CookieNameComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                CookieNameComboBox.addItem ( defaultValues[counter] );
            }

            CookieNameComponent = CookieNameComboBox;
        }
        else
        {
            CookieNameComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'cookie_persistence' column
        CookiePersistenceLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_cookie.cookie_persistence.display_name" ) );
        if (( null != AbstractHttpCookie.getDefaultValuesForCookiePersistence() ) && ( AbstractHttpCookie.getDefaultValuesForCookiePersistence().length > 0 ))
        {
            String defaultValues[] = AbstractHttpCookie.getDefaultValuesForCookiePersistence();
            JComboBox CookiePersistenceComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                CookiePersistenceComboBox.addItem ( defaultValues[counter] );
            }

            CookiePersistenceComponent = CookiePersistenceComboBox;
        }
        else
        {
            CookiePersistenceComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'is_secure' column
        IsSecureLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_cookie.is_secure.display_name" ) );
        JComboBox IsSecureComboBox = new JComboBox();
            IsSecureComboBox.addItem ( new Boolean ( true ) );
            IsSecureComboBox.addItem ( new Boolean ( false ) );
        IsSecureComponent = IsSecureComboBox;

        //  GUI components for the 'max_age' column
        MaxAgeLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_cookie.max_age.display_name" ) );
        if (( null != AbstractHttpCookie.getDefaultValuesForMaxAge() ) && ( AbstractHttpCookie.getDefaultValuesForMaxAge().length > 0 ))
        {
            long defaultValues[] = AbstractHttpCookie.getDefaultValuesForMaxAge();
            JComboBox MaxAgeComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                MaxAgeComboBox.addItem ( defaultValues[counter] );
            }

            MaxAgeComponent = MaxAgeComboBox;
        }
        else
        {
            MaxAgeComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'version' column
        VersionLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_cookie.version.display_name" ) );
        if (( null != AbstractHttpCookie.getDefaultValuesForVersion() ) && ( AbstractHttpCookie.getDefaultValuesForVersion().length > 0 ))
        {
            long defaultValues[] = AbstractHttpCookie.getDefaultValuesForVersion();
            JComboBox VersionComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                VersionComboBox.addItem ( defaultValues[counter] );
            }

            VersionComponent = VersionComboBox;
        }
        else
        {
            VersionComponent = new JTextField ( 20 );
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

        //  Setting up the fields for the 'request_id' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RequestIDLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( RequestIDComponent, c );

        row_counter++;

        //  Setting up the fields for the 'cookie_comment' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( CookieCommentLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( CookieCommentComponent, c );

        row_counter++;

        //  Setting up the fields for the 'domain' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( DomainLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( DomainComponent, c );

        row_counter++;

        //  Setting up the fields for the 'path' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( PathLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( PathComponent, c );

        row_counter++;

        //  Setting up the fields for the 'cookie_name' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( CookieNameLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( CookieNameComponent, c );

        row_counter++;

        //  Setting up the fields for the 'cookie_persistence' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( CookiePersistenceLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( CookiePersistenceComponent, c );

        row_counter++;

        //  Setting up the fields for the 'is_secure' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( IsSecureLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( IsSecureComponent, c );

        row_counter++;

        //  Setting up the fields for the 'max_age' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( MaxAgeLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( MaxAgeComponent, c );

        row_counter++;

        //  Setting up the fields for the 'version' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( VersionLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( VersionComponent, c );
    }

    public void actionPerformed ( ActionEvent event )
    {
        final Object eventSource = event.getSource();

             if ( eventSource == RequestIDComponent                        )
        {
        }
        else if ( eventSource == CookieCommentComponent                    )
        {
        }
        else if ( eventSource == DomainComponent                           )
        {
        }
        else if ( eventSource == PathComponent                             )
        {
        }
        else if ( eventSource == CookieNameComponent                       )
        {
        }
        else if ( eventSource == CookiePersistenceComponent                )
        {
        }
        else if ( eventSource == IsSecureComponent                         )
        {
        }
        else if ( eventSource == MaxAgeComponent                           )
        {
        }
        else if ( eventSource == VersionComponent                          )
        {
        }
    }
}