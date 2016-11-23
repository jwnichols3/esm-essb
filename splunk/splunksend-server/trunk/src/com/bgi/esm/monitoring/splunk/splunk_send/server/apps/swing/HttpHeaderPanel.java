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
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpHeader;

public class HttpHeaderPanel extends BasePanel implements ActionListener
{
    @SuppressWarnings ( "unused" )
    final private static Logger _log = Logger.getLogger ( HttpHeaderPanel.class );
    final private static long serialVersionUID = 8735704504251094797L;

    /**
     *  GUI Components
     */
    private JLabel     RequestIDLabel                             = null;
    private JLabel     HeaderNameLabel                            = null;
    private JLabel     HeaderPersistenceLabel                     = null;
    private JComponent RequestIDComponent                         = null;
    private JComponent HeaderNameComponent                        = null;
    private JComponent HeaderPersistenceComponent                 = null;

    public HttpHeaderPanel()
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
        RequestIDLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_header.request_id.display_name" ) );
        if (( null != AbstractHttpHeader.getDefaultValuesForRequestID() ) && ( AbstractHttpHeader.getDefaultValuesForRequestID().length > 0 ))
        {
            String defaultValues[] = AbstractHttpHeader.getDefaultValuesForRequestID();
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

        //  GUI components for the 'header_name' column
        HeaderNameLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_header.header_name.display_name" ) );
        if (( null != AbstractHttpHeader.getDefaultValuesForHeaderName() ) && ( AbstractHttpHeader.getDefaultValuesForHeaderName().length > 0 ))
        {
            String defaultValues[] = AbstractHttpHeader.getDefaultValuesForHeaderName();
            JComboBox HeaderNameComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                HeaderNameComboBox.addItem ( defaultValues[counter] );
            }

            HeaderNameComponent = HeaderNameComboBox;
        }
        else
        {
            HeaderNameComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'header_persistence' column
        HeaderPersistenceLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_header.header_persistence.display_name" ) );
        if (( null != AbstractHttpHeader.getDefaultValuesForHeaderPersistence() ) && ( AbstractHttpHeader.getDefaultValuesForHeaderPersistence().length > 0 ))
        {
            String defaultValues[] = AbstractHttpHeader.getDefaultValuesForHeaderPersistence();
            JComboBox HeaderPersistenceComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                HeaderPersistenceComboBox.addItem ( defaultValues[counter] );
            }

            HeaderPersistenceComponent = HeaderPersistenceComboBox;
        }
        else
        {
            HeaderPersistenceComponent = new JTextField ( 20 );
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

        //  Setting up the fields for the 'header_name' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( HeaderNameLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( HeaderNameComponent, c );

        row_counter++;

        //  Setting up the fields for the 'header_persistence' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( HeaderPersistenceLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( HeaderPersistenceComponent, c );
    }

    public void actionPerformed ( ActionEvent event )
    {
        final Object eventSource = event.getSource();

             if ( eventSource == RequestIDComponent                        )
        {
        }
        else if ( eventSource == HeaderNameComponent                       )
        {
        }
        else if ( eventSource == HeaderPersistenceComponent                )
        {
        }
    }
}