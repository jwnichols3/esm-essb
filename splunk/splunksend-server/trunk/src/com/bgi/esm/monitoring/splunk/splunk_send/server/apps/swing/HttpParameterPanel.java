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
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpParameter;

public class HttpParameterPanel extends BasePanel implements ActionListener
{
    @SuppressWarnings ( "unused" )
    final private static Logger _log = Logger.getLogger ( HttpParameterPanel.class );
    final private static long serialVersionUID = 7187521911089564446L;

    /**
     *  GUI Components
     */
    private JLabel     RequestIDLabel                                = null;
    private JLabel     ParameterNameLabel                            = null;
    private JLabel     ParameterPersistenceLabel                     = null;
    private JComponent RequestIDComponent                            = null;
    private JComponent ParameterNameComponent                        = null;
    private JComponent ParameterPersistenceComponent                 = null;

    public HttpParameterPanel()
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
        RequestIDLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_parameter.request_id.display_name" ) );
        if (( null != AbstractHttpParameter.getDefaultValuesForRequestID() ) && ( AbstractHttpParameter.getDefaultValuesForRequestID().length > 0 ))
        {
            String defaultValues[] = AbstractHttpParameter.getDefaultValuesForRequestID();
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

        //  GUI components for the 'parameter_name' column
        ParameterNameLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_parameter.parameter_name.display_name" ) );
        if (( null != AbstractHttpParameter.getDefaultValuesForParameterName() ) && ( AbstractHttpParameter.getDefaultValuesForParameterName().length > 0 ))
        {
            String defaultValues[] = AbstractHttpParameter.getDefaultValuesForParameterName();
            JComboBox ParameterNameComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                ParameterNameComboBox.addItem ( defaultValues[counter] );
            }

            ParameterNameComponent = ParameterNameComboBox;
        }
        else
        {
            ParameterNameComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'parameter_persistence' column
        ParameterPersistenceLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_parameter.parameter_persistence.display_name" ) );
        if (( null != AbstractHttpParameter.getDefaultValuesForParameterPersistence() ) && ( AbstractHttpParameter.getDefaultValuesForParameterPersistence().length > 0 ))
        {
            String defaultValues[] = AbstractHttpParameter.getDefaultValuesForParameterPersistence();
            JComboBox ParameterPersistenceComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                ParameterPersistenceComboBox.addItem ( defaultValues[counter] );
            }

            ParameterPersistenceComponent = ParameterPersistenceComboBox;
        }
        else
        {
            ParameterPersistenceComponent = new JTextField ( 20 );
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

        //  Setting up the fields for the 'parameter_name' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ParameterNameLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( ParameterNameComponent, c );

        row_counter++;

        //  Setting up the fields for the 'parameter_persistence' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ParameterPersistenceLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( ParameterPersistenceComponent, c );
    }

    public void actionPerformed ( ActionEvent event )
    {
        final Object eventSource = event.getSource();

             if ( eventSource == RequestIDComponent                           )
        {
        }
        else if ( eventSource == ParameterNameComponent                       )
        {
        }
        else if ( eventSource == ParameterPersistenceComponent                )
        {
        }
    }
}