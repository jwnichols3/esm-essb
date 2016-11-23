package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing;

import java.lang.ref.WeakReference;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

public class BasePanel extends JPanel
{
    final private static Logger _log = Logger.getLogger ( BasePanel.class );

    /**
     *
     */
    final private static long serialVersionUID = -3521823745875672456L;

    /**
     *  Helper function that sets the value of the specified AWT component
     *
     *  @param component the AWT component to modify
     *  @param value the new value that the AWT component should take on
     *  @return true if successful, false otherwise
     */
    protected boolean setComponentValue ( JComponent component, String value )
    {
        boolean was_success = false;

        if ( component instanceof JTextField )
        {
            JTextField textField = (JTextField) component;

            textField.setText ( value );
            was_success = true;
        }
        else if ( component instanceof JComboBox )
        {
            JComboBox comboBox = (JComboBox) component;

            comboBox.setSelectedItem ( value );
            was_success = comboBox.getSelectedIndex() >= 0;
        }

        return was_success;
    }

    /**
     *  Helper function that sets the value of the specified AWT component
     *
     *  @param component the AWT component to modify
     *  @param value the new value that the AWT component should take on
     *  @return true if successful, false otherwise
     */
    protected boolean setComponentValue ( JComponent component, int value )
    {
        boolean was_success = false;

        if ( component instanceof JTextField )
        {
            JTextField textField = (JTextField) component;

            textField.setText ( Integer.toString ( value ) );
            was_success = true;
        }
        else if ( component instanceof JComboBox )
        {
            JComboBox comboBox   = (JComboBox) component;
            Integer compareValue = new Integer ( value );

            comboBox.setSelectedItem ( compareValue );
            was_success = comboBox.getSelectedIndex() >= 0;
        }

        return was_success;
    }

    /**
     *  Helper function that sets the value of the specified AWT component
     *
     *  @param component the AWT component to modify
     *  @param value the new value that the AWT component should take on
     *  @return true if successful, false otherwise
     */
    protected boolean setComponentValue ( JComponent component, long value )
    {
        boolean was_success = false;

        if ( component instanceof JTextField )
        {
            JTextField textField = (JTextField) component;

            textField.setText ( Long.toString ( value ) );
            was_success = true;
        }
        else if ( component instanceof JComboBox )
        {
            JComboBox comboBox = (JComboBox) component;
            Long compareValue  = new Long ( value );

            comboBox.setSelectedItem ( compareValue );
            was_success = comboBox.getSelectedIndex() >= 0;
        }

        return was_success;
    }

    /**
     *  Helper function that sets the value of the specified AWT component
     *
     *  @param component the AWT component to modify
     *  @param value the new value that the AWT component should take on
     *  @return true if successful, false otherwise
     */
    protected boolean setComponentValue ( JComponent component, float value )
    {
        boolean was_success = false;

        if ( component instanceof JTextField )
        {
            JTextField textField = (JTextField) component;

            textField.setText ( Float.toString ( value ) );
            was_success = true;
        }
        else if ( component instanceof JComboBox )
        {
            JComboBox comboBox = (JComboBox) component;
            Float compareValue = new Float ( value );

            comboBox.setSelectedItem ( compareValue );
            was_success = comboBox.getSelectedIndex() >= 0;
        }

        return was_success;
    }

    /**
     *  Helper function that sets the value of the specified AWT component
     *
     *  @param component the AWT component to modify
     *  @param value the new value that the AWT component should take on
     *  @return true if successful, false otherwise
     */
    protected boolean setComponentValue ( JComponent component, double value )
    {
        boolean was_success = false;

        if ( component instanceof JTextField )
        {
            JTextField textField = (JTextField) component;

            textField.setText ( Double.toString ( value ) );
            was_success = true;
        }
        else if ( component instanceof JComboBox )
        {
            JComboBox comboBox  = (JComboBox) component;
            Double compareValue = new Double ( value );

            comboBox.setSelectedItem ( compareValue );
            was_success = comboBox.getSelectedIndex() >= 0;
        }

        return was_success;
    }

    /**
     *  Retrieves the value of the specified component as a String
     *
     *  @param component the component to retrieve the value from
     *  @return the value extracted if it exists, null otherwise
     */
    protected String getComponentValueAsString ( JComponent component )
    {
        if ( component instanceof JTextField )
        {
            JTextField textField = (JTextField) component;

            return textField.getText();
        }
        else if ( component instanceof JComboBox )
        {
            JComboBox comboBox  = (JComboBox) component;
            Object selectedItem = comboBox.getSelectedItem();
            return ( null != selectedItem )? selectedItem.toString() : null;
        }

        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::getComponentValueAsString ( Component=" );
            message.get().append ( component.toString() );
            message.get().append ( " ) - unknown component type" );
        throw new IllegalArgumentException ( message.get().toString() );
    }

    /**
     *  Retrieves the value of the specified component as a String
     *
     *  @param component the component to retrieve the value from
     *  @return the value extracted if it exists, null otherwise
     */
    protected Integer getComponentValueAsInteger ( JComponent component )
    {
        if ( component instanceof JTextField )
        {
            JTextField textField = (JTextField) component;
            Integer valueObject  = null;

            try
            {
                valueObject = new Integer ( textField.getText() );
            }
            catch ( NumberFormatException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::getComponentValueAsInteger ( Component=" );
                    message.get().append ( component.toString() );
                    message.get().append ( " ) - error when trying to parse '" );
                    message.get().append ( textField.getText() );
                    message.get().append ( "'" );
                _log.error ( message.get().toString(), exception );

                valueObject = null;
            }

            return valueObject;
        }
        else if ( component instanceof JComboBox )
        {
            JComboBox comboBox  = (JComboBox) component;
            Object selectedItem = comboBox.getSelectedItem();
            if ( null == selectedItem )
            {
                return null;
            }

            Integer valueObject = null;
            try
            {
                valueObject = new Integer ( selectedItem.toString() );
            }
            catch ( NumberFormatException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::getComponentValueAsInteger ( Component=" );
                    message.get().append ( component.toString() );
                    message.get().append ( " ) - error when trying to parse '" );
                    message.get().append ( selectedItem.toString() );
                    message.get().append ( "'" );
                _log.error ( message.get().toString(), exception );

                valueObject = null;
            }

            return valueObject;
        }

        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::getComponentValueAsString ( Component=" );
            message.get().append ( component.toString() );
            message.get().append ( " ) - unknown component type" );
        throw new IllegalArgumentException ( message.get().toString() );
    }

    /**
     *  Retrieves the value of the specified component as a String
     *
     *  @param component the component to retrieve the value from
     *  @return the value extracted if it exists, null otherwise
     */
    protected Long getComponentValueAsLong ( JComponent component )
    {
        if ( component instanceof JTextField )
        {
            JTextField textField = (JTextField) component;
            Long valueObject     = null;

            try
            {
                valueObject = new Long ( textField.getText() );
            }
            catch ( NumberFormatException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::getComponentValueAsLong ( Component=" );
                    message.get().append ( component.toString() );
                    message.get().append ( " ) - error when trying to parse '" );
                    message.get().append ( textField.getText() );
                    message.get().append ( "'" );
                _log.error ( message.get().toString(), exception );

                valueObject = null;
            }

            return valueObject;
        }
        else if ( component instanceof JComboBox )
        {
            JComboBox comboBox  = (JComboBox) component;
            Object selectedItem = comboBox.getSelectedItem();
            if ( null == selectedItem )
            {
                return null;
            }

            Long valueObject = null;
            try
            {
                valueObject = new Long ( selectedItem.toString() );
            }
            catch ( NumberFormatException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::getComponentValueAsLong ( Component=" );
                    message.get().append ( component.toString() );
                    message.get().append ( " ) - error when trying to parse '" );
                    message.get().append ( selectedItem.toString() );
                    message.get().append ( "'" );
                _log.error ( message.get().toString(), exception );

                valueObject = null;
            }

            return valueObject;
        }

        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::getComponentValueAsString ( Component=" );
            message.get().append ( component.toString() );
            message.get().append ( " ) - unknown component type" );
        throw new IllegalArgumentException ( message.get().toString() );
    }

    /**
     *  Retrieves the value of the specified component as a String
     *
     *  @param component the component to retrieve the value from
     *  @return the value extracted if it exists, null otherwise
     */
    protected Float getComponentValueAsFloat ( JComponent component )
    {
        if ( component instanceof JTextField )
        {
            JTextField textField = (JTextField) component;
            Float valueObject    = null;

            try
            {
                valueObject = new Float ( textField.getText() );
            }
            catch ( NumberFormatException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::getComponentValueAsFloat ( Component=" );
                    message.get().append ( component.toString() );
                    message.get().append ( " ) - error when trying to parse '" );
                    message.get().append ( textField.getText() );
                    message.get().append ( "'" );
                _log.error ( message.get().toString(), exception );

                valueObject = null;
            }

            return valueObject;
        }
        else if ( component instanceof JComboBox )
        {
            JComboBox comboBox  = (JComboBox) component;
            Object selectedItem = comboBox.getSelectedItem();
            if ( null == selectedItem )
            {
                return null;
            }

            Float valueObject = null;
            try
            {
                valueObject = new Float ( selectedItem.toString() );
            }
            catch ( NumberFormatException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::getComponentValueAsFloat ( Component=" );
                    message.get().append ( component.toString() );
                    message.get().append ( " ) - error when trying to parse '" );
                    message.get().append ( selectedItem.toString() );
                    message.get().append ( "'" );
                _log.error ( message.get().toString(), exception );

                valueObject = null;
            }

            return valueObject;
        }

        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::getComponentValueAsString ( Component=" );
            message.get().append ( component.toString() );
            message.get().append ( " ) - unknown component type" );
        throw new IllegalArgumentException ( message.get().toString() );
    }

    /**
     *  Retrieves the value of the specified component as a String
     *
     *  @param component the component to retrieve the value from
     *  @return the value extracted if it exists, null otherwise
     */
    protected Double getComponentValueAsDouble ( JComponent component )
    {
        if ( component instanceof JTextField )
        {
            JTextField textField = (JTextField) component;
            Double valueObject  = null;

            try
            {
                valueObject = new Double ( textField.getText() );
            }
            catch ( NumberFormatException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::getComponentValueAsDouble ( Component=" );
                    message.get().append ( component.toString() );
                    message.get().append ( " ) - error when trying to parse '" );
                    message.get().append ( textField.getText() );
                    message.get().append ( "'" );
                _log.error ( message.get().toString(), exception );

                valueObject = null;
            }

            return valueObject;
        }
        else if ( component instanceof JComboBox )
        {
            JComboBox comboBox  = (JComboBox) component;
            Object selectedItem = comboBox.getSelectedItem();
            if ( null == selectedItem )
            {
                return null;
            }

            Double valueObject = null;
            try
            {
                valueObject = new Double ( selectedItem.toString() );
            }
            catch ( NumberFormatException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::getComponentValueAsDouble ( Component=" );
                    message.get().append ( component.toString() );
                    message.get().append ( " ) - error when trying to parse '" );
                    message.get().append ( selectedItem.toString() );
                    message.get().append ( "'" );
                _log.error ( message.get().toString(), exception );

                valueObject = null;
            }

            return valueObject;
        }

        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::getComponentValueAsString ( Component=" );
            message.get().append ( component.toString() );
            message.get().append ( " ) - unknown component type" );
        throw new IllegalArgumentException ( message.get().toString() );
    }
}