package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.Configuration;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpRequest;

public class HttpRequestPanel03 extends BasePanel implements ActionListener
{
    @SuppressWarnings ( "unused" )
    final private static Logger _log = Logger.getLogger ( HttpRequestPanel03.class );
    final private static long serialVersionUID = -6843168702175478219L;

    /**
     *  GUI Components
     */
    private JLabel     RequestTimeLabel                       = null;
    private JLabel     ProcessTimeLabel                       = null;
    private JLabel     ContentLengthLabel                     = null;
    private JLabel     LocalPortLabel                         = null;
    private JLabel     RemotePortLabel                        = null;
    private JLabel     ServerPortLabel                        = null;
    private JLabel     IsProcessedLabel                       = null;
    private JLabel     WasSuccessfulLabel                     = null;
    private JLabel     ReturnCodeLabel                        = null;
    private JComponent RequestTimeHelperYearComponent         = null;
    private JComponent RequestTimeHelperMonthComponent        = null;
    private JComponent RequestTimeHelperDateComponent         = null;
    private JComponent RequestTimeHelperHourComponent         = null;
    private JComponent RequestTimeHelperMinuteComponent       = null;
    private JComponent ProcessTimeHelperYearComponent         = null;
    private JComponent ProcessTimeHelperMonthComponent        = null;
    private JComponent ProcessTimeHelperDateComponent         = null;
    private JComponent ProcessTimeHelperHourComponent         = null;
    private JComponent ProcessTimeHelperMinuteComponent       = null;
    private JComponent ContentLengthComponent                 = null;
    private JComponent LocalPortComponent                     = null;
    private JComponent RemotePortComponent                    = null;
    private JComponent ServerPortComponent                    = null;
    private JComponent IsProcessedComponent                   = null;
    private JComponent WasSuccessfulComponent                 = null;
    private JComponent ReturnCodeComponent                    = null;

    public HttpRequestPanel03()
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
        Calendar timestamp            = Calendar.getInstance();
        int timestamp_year_max        = timestamp.get ( Calendar.YEAR ) + 2;
        int timestamp_year_min        = timestamp.get ( Calendar.YEAR ) - 100;
        int timestamp_month_max       = 1;
        int timestamp_month_min       = 12;
        JComboBox tempComboBox        = null;

        //  GUI components for the 'request_time' column
        RequestTimeLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.request_time.display_name" ) + " - (YYYY/MM/DD format)" );
        tempComboBox = null;

        tempComboBox = new JComboBox();
        for ( int counter = timestamp_year_max; counter >= timestamp_year_min; counter-- )
        {
            tempComboBox.addItem ( new Integer ( counter ) );
        }
        RequestTimeHelperYearComponent = tempComboBox;

        tempComboBox = new JComboBox();
        for ( int counter = timestamp_month_min; counter <= timestamp_month_max; counter++ )
        {
            tempComboBox.addItem ( new Integer ( counter ) );
        }
        RequestTimeHelperMonthComponent = tempComboBox;

        tempComboBox = new JComboBox();
        for ( int counter = 1; counter <= 31; counter++ )
        {
            tempComboBox.addItem ( new Integer ( counter ) );
        }
        RequestTimeHelperDateComponent = tempComboBox;

        tempComboBox = new JComboBox();
        for ( int counter = 0; counter <= 23; counter++ )
        {
            tempComboBox.addItem ( new Integer ( counter ) );
        }
        RequestTimeHelperHourComponent = tempComboBox;

        tempComboBox = new JComboBox();
        for ( int counter = 0; counter <= 59; counter++ )
        {
            tempComboBox.addItem ( new Integer ( counter ) );
        }
        RequestTimeHelperMinuteComponent = tempComboBox;


        //  GUI components for the 'process_time' column
        ProcessTimeLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.process_time.display_name" ) + " - (YYYY/MM/DD format)" );
        tempComboBox = null;

        tempComboBox = new JComboBox();
        for ( int counter = timestamp_year_max; counter >= timestamp_year_min; counter-- )
        {
            tempComboBox.addItem ( new Integer ( counter ) );
        }
        ProcessTimeHelperYearComponent = tempComboBox;

        tempComboBox = new JComboBox();
        for ( int counter = timestamp_month_min; counter <= timestamp_month_max; counter++ )
        {
            tempComboBox.addItem ( new Integer ( counter ) );
        }
        ProcessTimeHelperMonthComponent = tempComboBox;

        tempComboBox = new JComboBox();
        for ( int counter = 1; counter <= 31; counter++ )
        {
            tempComboBox.addItem ( new Integer ( counter ) );
        }
        ProcessTimeHelperDateComponent = tempComboBox;

        tempComboBox = new JComboBox();
        for ( int counter = 0; counter <= 23; counter++ )
        {
            tempComboBox.addItem ( new Integer ( counter ) );
        }
        ProcessTimeHelperHourComponent = tempComboBox;

        tempComboBox = new JComboBox();
        for ( int counter = 0; counter <= 59; counter++ )
        {
            tempComboBox.addItem ( new Integer ( counter ) );
        }
        ProcessTimeHelperMinuteComponent = tempComboBox;


        //  GUI components for the 'content_length' column
        ContentLengthLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.content_length.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForContentLength() ) && ( AbstractHttpRequest.getDefaultValuesForContentLength().length > 0 ))
        {
            long defaultValues[] = AbstractHttpRequest.getDefaultValuesForContentLength();
            JComboBox ContentLengthComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                ContentLengthComboBox.addItem ( defaultValues[counter] );
            }

            ContentLengthComponent = ContentLengthComboBox;
        }
        else
        {
            ContentLengthComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'local_port' column
        LocalPortLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.local_port.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForLocalPort() ) && ( AbstractHttpRequest.getDefaultValuesForLocalPort().length > 0 ))
        {
            long defaultValues[] = AbstractHttpRequest.getDefaultValuesForLocalPort();
            JComboBox LocalPortComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                LocalPortComboBox.addItem ( defaultValues[counter] );
            }

            LocalPortComponent = LocalPortComboBox;
        }
        else
        {
            LocalPortComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'remote_port' column
        RemotePortLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.remote_port.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForRemotePort() ) && ( AbstractHttpRequest.getDefaultValuesForRemotePort().length > 0 ))
        {
            long defaultValues[] = AbstractHttpRequest.getDefaultValuesForRemotePort();
            JComboBox RemotePortComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                RemotePortComboBox.addItem ( defaultValues[counter] );
            }

            RemotePortComponent = RemotePortComboBox;
        }
        else
        {
            RemotePortComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'server_port' column
        ServerPortLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.server_port.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForServerPort() ) && ( AbstractHttpRequest.getDefaultValuesForServerPort().length > 0 ))
        {
            long defaultValues[] = AbstractHttpRequest.getDefaultValuesForServerPort();
            JComboBox ServerPortComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                ServerPortComboBox.addItem ( defaultValues[counter] );
            }

            ServerPortComponent = ServerPortComboBox;
        }
        else
        {
            ServerPortComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'is_processed' column
        IsProcessedLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.is_processed.display_name" ) );
        JComboBox IsProcessedComboBox = new JComboBox();
            IsProcessedComboBox.addItem ( new Boolean ( true ) );
            IsProcessedComboBox.addItem ( new Boolean ( false ) );
        IsProcessedComponent = IsProcessedComboBox;

        //  GUI components for the 'was_successful' column
        WasSuccessfulLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.was_successful.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForWasSuccessful() ) && ( AbstractHttpRequest.getDefaultValuesForWasSuccessful().length > 0 ))
        {
            long defaultValues[] = AbstractHttpRequest.getDefaultValuesForWasSuccessful();
            JComboBox WasSuccessfulComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                WasSuccessfulComboBox.addItem ( defaultValues[counter] );
            }

            WasSuccessfulComponent = WasSuccessfulComboBox;
        }
        else
        {
            WasSuccessfulComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'return_code' column
        ReturnCodeLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_request.return_code.display_name" ) );
        if (( null != AbstractHttpRequest.getDefaultValuesForReturnCode() ) && ( AbstractHttpRequest.getDefaultValuesForReturnCode().length > 0 ))
        {
            long defaultValues[] = AbstractHttpRequest.getDefaultValuesForReturnCode();
            JComboBox ReturnCodeComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                ReturnCodeComboBox.addItem ( defaultValues[counter] );
            }

            ReturnCodeComponent = ReturnCodeComboBox;
        }
        else
        {
            ReturnCodeComponent = new JTextField ( 20 );
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

        //  Setting up the fields for the 'request_time' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RequestTimeLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RequestTimeHelperYearComponent, c );
        c.gridx = 2;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RequestTimeHelperMonthComponent, c );
        c.gridx = 3;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RequestTimeHelperDateComponent, c );
        c.gridx = 4;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RequestTimeHelperHourComponent, c );
        c.gridx = 5;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RequestTimeHelperMinuteComponent, c );

        row_counter++;

        //  Setting up the fields for the 'process_time' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ProcessTimeLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ProcessTimeHelperYearComponent, c );
        c.gridx = 2;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ProcessTimeHelperMonthComponent, c );
        c.gridx = 3;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ProcessTimeHelperDateComponent, c );
        c.gridx = 4;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ProcessTimeHelperHourComponent, c );
        c.gridx = 5;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ProcessTimeHelperMinuteComponent, c );

        row_counter++;

        //  Setting up the fields for the 'content_length' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ContentLengthLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( ContentLengthComponent, c );

        row_counter++;

        //  Setting up the fields for the 'local_port' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( LocalPortLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( LocalPortComponent, c );

        row_counter++;

        //  Setting up the fields for the 'remote_port' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( RemotePortLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( RemotePortComponent, c );

        row_counter++;

        //  Setting up the fields for the 'server_port' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ServerPortLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( ServerPortComponent, c );

        row_counter++;

        //  Setting up the fields for the 'is_processed' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( IsProcessedLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( IsProcessedComponent, c );

        row_counter++;

        //  Setting up the fields for the 'was_successful' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( WasSuccessfulLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( WasSuccessfulComponent, c );

        row_counter++;

        //  Setting up the fields for the 'return_code' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( ReturnCodeLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( ReturnCodeComponent, c );
    }

    public void actionPerformed ( ActionEvent event )
    {
        final Object eventSource = event.getSource();

             if ( eventSource == RequestTimeHelperYearComponent        )
        {
        }
        else if ( eventSource == RequestTimeHelperMonthComponent       )
        {
        }
        else if ( eventSource == RequestTimeHelperDateComponent        )
        {
        }
        else if ( eventSource == RequestTimeHelperHourComponent        )
        {
        }
        else if ( eventSource == RequestTimeHelperMinuteComponent      )
        {
        }
        else if ( eventSource == ProcessTimeHelperYearComponent        )
        {
        }
        else if ( eventSource == ProcessTimeHelperMonthComponent       )
        {
        }
        else if ( eventSource == ProcessTimeHelperDateComponent        )
        {
        }
        else if ( eventSource == ProcessTimeHelperHourComponent        )
        {
        }
        else if ( eventSource == ProcessTimeHelperMinuteComponent      )
        {
        }
        else if ( eventSource == ContentLengthComponent                )
        {
        }
        else if ( eventSource == LocalPortComponent                    )
        {
        }
        else if ( eventSource == RemotePortComponent                   )
        {
        }
        else if ( eventSource == ServerPortComponent                   )
        {
        }
        else if ( eventSource == IsProcessedComponent                  )
        {
        }
        else if ( eventSource == WasSuccessfulComponent                )
        {
        }
        else if ( eventSource == ReturnCodeComponent                   )
        {
        }
    }
}