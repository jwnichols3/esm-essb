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
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpAttribute;

public class HttpAttributePanel extends BasePanel implements ActionListener
{
    @SuppressWarnings ( "unused" )
    final private static Logger _log = Logger.getLogger ( HttpAttributePanel.class );
    final private static long serialVersionUID = -8269059419826671605L;

    /**
     *  GUI Components
     */
    private JLabel     RequestIDLabel                                = null;
    private JLabel     AttributeNameLabel                            = null;
    private JLabel     AttributePersistenceLabel                     = null;
    private JComponent RequestIDComponent                            = null;
    private JComponent AttributeNameComponent                        = null;
    private JComponent AttributePersistenceComponent                 = null;

    public HttpAttributePanel()
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
        RequestIDLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_attribute.request_id.display_name" ) );
        if (( null != AbstractHttpAttribute.getDefaultValuesForRequestID() ) && ( AbstractHttpAttribute.getDefaultValuesForRequestID().length > 0 ))
        {
            String defaultValues[] = AbstractHttpAttribute.getDefaultValuesForRequestID();
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

        //  GUI components for the 'attribute_name' column
        AttributeNameLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_attribute.attribute_name.display_name" ) );
        if (( null != AbstractHttpAttribute.getDefaultValuesForAttributeName() ) && ( AbstractHttpAttribute.getDefaultValuesForAttributeName().length > 0 ))
        {
            String defaultValues[] = AbstractHttpAttribute.getDefaultValuesForAttributeName();
            JComboBox AttributeNameComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                AttributeNameComboBox.addItem ( defaultValues[counter] );
            }

            AttributeNameComponent = AttributeNameComboBox;
        }
        else
        {
            AttributeNameComponent = new JTextField ( 20 );
        }

        //  GUI components for the 'attribute_persistence' column
        AttributePersistenceLabel  = new JLabel ( DatabaseProperties.getProperty ( "database.http_attribute.attribute_persistence.display_name" ) );
        if (( null != AbstractHttpAttribute.getDefaultValuesForAttributePersistence() ) && ( AbstractHttpAttribute.getDefaultValuesForAttributePersistence().length > 0 ))
        {
            String defaultValues[] = AbstractHttpAttribute.getDefaultValuesForAttributePersistence();
            JComboBox AttributePersistenceComboBox = new JComboBox();
            for ( int counter = 0; counter < defaultValues.length; counter++ )
            {
                AttributePersistenceComboBox.addItem ( defaultValues[counter] );
            }

            AttributePersistenceComponent = AttributePersistenceComboBox;
        }
        else
        {
            AttributePersistenceComponent = new JTextField ( 20 );
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

        //  Setting up the fields for the 'attribute_name' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( AttributeNameLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( AttributeNameComponent, c );

        row_counter++;

        //  Setting up the fields for the 'attribute_persistence' column
        c.gridx = 0;
        c.gridy = row_counter;
        c.gridwidth = 1;
        add ( AttributePersistenceLabel, c );

        c.gridx = 1;
        c.gridy = row_counter;
        c.gridwidth = 5;
        add ( AttributePersistenceComponent, c );
    }

    public void actionPerformed ( ActionEvent event )
    {
        final Object eventSource = event.getSource();

             if ( eventSource == RequestIDComponent                           )
        {
        }
        else if ( eventSource == AttributeNameComponent                       )
        {
        }
        else if ( eventSource == AttributePersistenceComponent                )
        {
        }
    }
}