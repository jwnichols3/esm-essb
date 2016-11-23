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
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractDataMap;

public class DataMapPanel extends BasePanel implements ActionListener
{
    @SuppressWarnings ( "unused" )
    final private static Logger _log = Logger.getLogger ( DataMapPanel.class );
    final private static long serialVersionUID = -8647396153997170676L;

    /**
     *  GUI Components
     */
    private JLabel     ApplicationNameLabel                     = null;
    private JLabel     HostnameLabel                            = null;
    private JLabel     TargetPathLabel                          = null;
    private JComponent ApplicationNameComponent                 = null;
    private JComponent HostnameComponent                        = null;
    private JComponent TargetPathComponent                      = null;

    public DataMapPanel()
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

        //  GUI components for the 'application_name' column
        ApplicationNameLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.data_map.application_name.display_name" ) );
        if (( null != AbstractDataMap.getDefaultValuesForApplicationName() ) && ( AbstractDataMap.getDefaultValuesForApplicationName().length > 0 ))
        {
            String defaultValues[] = AbstractDataMap.getDefaultValuesForApplicationName();
            JComboBox ApplicationNameComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                ApplicationNameComboBox.addItem ( defaultValues[counter] );
            }

            ApplicationNameComponent = ApplicationNameComboBox;
        }
        else
        {
            ApplicationNameComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'hostname' column
        HostnameLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.data_map.hostname.display_name" ) );
        if (( null != AbstractDataMap.getDefaultValuesForHostname() ) && ( AbstractDataMap.getDefaultValuesForHostname().length > 0 ))
        {
            String defaultValues[] = AbstractDataMap.getDefaultValuesForHostname();
            JComboBox HostnameComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                HostnameComboBox.addItem ( defaultValues[counter] );
            }

            HostnameComponent = HostnameComboBox;
        }
        else
        {
            HostnameComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'target_path' column
        TargetPathLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.data_map.target_path.display_name" ) );
        if (( null != AbstractDataMap.getDefaultValuesForTargetPath() ) && ( AbstractDataMap.getDefaultValuesForTargetPath().length > 0 ))
        {
            String defaultValues[] = AbstractDataMap.getDefaultValuesForTargetPath();
            JComboBox TargetPathComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                TargetPathComboBox.addItem ( defaultValues[counter] );
            }

            TargetPathComponent = TargetPathComboBox;
        }
        else
        {
            TargetPathComponent = new JTextField ( 20 );
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

        //  Setting up the fields for the 'application_name' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ApplicationNameLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( ApplicationNameComponent, c );

        row_counter++;

        //  Setting up the fields for the 'hostname' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( HostnameLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( HostnameComponent, c );

        row_counter++;

        //  Setting up the fields for the 'target_path' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( TargetPathLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( TargetPathComponent, c );
    }

    public void actionPerformed ( ActionEvent event )
    {
        final Object eventSource = event.getSource();

             if ( eventSource == ApplicationNameComponent                )
        {
        }
        else if ( eventSource == HostnameComponent                       )
        {
        }
        else if ( eventSource == TargetPathComponent                     )
        {
        }
    }
}