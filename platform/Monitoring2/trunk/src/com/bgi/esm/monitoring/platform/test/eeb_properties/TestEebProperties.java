package com.bgi.esm.monitoring.platform.test.eeb_properties;

import java.util.Vector;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;
import com.bgi.esm.monitoring.platform.test.eeb_properties.CommonTestCase;
import org.apache.log4j.Logger;

public class TestEebProperties extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestEebProperties.class );
    protected BackEndFacade bef = new BackEndFacade();

    final private static String required_properties[] = 
    {
        "Alarmpoint.JavaClient.CommandLine.client",
        "Alarmpoint.JavaClient.CommandLine.full_path",
        "Alarmpoint.JavaClient.CommandLine.options",
        "Alarmpoint.JavaClient.hostname",
        "Alarmpoint.JavaClient.http.baseref",
        "Alarmpoint.JavaClient.http.form.action",
        "Alarmpoint.JavaClient.http.form.enctype",
        "Alarmpoint.JavaClient.http.form.message_field_name",
        "Alarmpoint.JavaClient.http.form.submit_target",
        "Alarmpoint.JavaClient.port",
        "Alarmpoint.active",
        "ServiceCenter.active",
        "ServiceCenter.hostname",
        "ServiceCenter.web_service_table"
    };

    public TestEebProperties ( String param )
    {
        super ( param );
    }

    public void testReadProperty() throws BackEndFailure
    {
        _log.info ( "**************************************** testReadProperty()" );

        EebProperty property = bef.selectEebProperty ( TEST_PROPERTY_NAME_01 );

        assertNotNull ( "Could not find test property #1", property );

        assertEquals ( "Incorrect test property value", property.getPropertyValue(), TEST_PROPERTY_VALUE_01 );
    }

    public void testAddDeleteProperty() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddDeleteProperty()" );

        long timestamp        = System.currentTimeMillis();
        String test_prop_name = "test-prop-" + timestamp;

        EebProperty property  = new EebProperty ( test_prop_name );
        property.setPropertyValue ( TEST_PROPERTY_VALUE_01 );

        EebProperty added     = bef.addOrUpdateEebProperty ( property );

        assertNotNull ( "Could not add EEB property", added );
        assertEquals  ( "Could not insert EEB property correctly", property, added );

        assertTrue ( "Could not delete test property " + test_prop_name, bef.deleteEebProperty ( added.getPropertyName() ) );
    }

    public void testAddUpdateDeleteProperty() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddUpdateDeleteProperty()" );

        long timestamp        = System.currentTimeMillis();
        String test_prop_name = "test-prop-" + timestamp;

        EebProperty property  = new EebProperty ( test_prop_name );
        property.setPropertyValue ( TEST_PROPERTY_VALUE_01 );

        EebProperty added     = bef.addOrUpdateEebProperty ( property );

        assertNotNull ( "Could not add EEB property", added );
        assertEquals  ( "Could not insert EEB property correctly", property, added );

        added.setPropertyValue ( TEST_PROPERTY_VALUE_02 );
        EebProperty updated   = bef.addOrUpdateEebProperty ( added );

        assertNotNull ( "Could not update EEB property at all", updated );
        assertTrue    ( "Could not update EEB property correctly", !updated.equals ( property ) );

        EebProperty retrieved = bef.selectEebProperty ( test_prop_name );
        assertNotNull ( "Could not retrieve previously added property: " + test_prop_name, retrieved );
        assertEquals ( "Unable to update EEB property: " + test_prop_name, retrieved, updated );

        assertTrue ( "Could not delete test property " + test_prop_name, bef.deleteEebProperty ( updated.getPropertyName() ) );
    }

    public void testRetrieveAllRequiredProperties() throws BackEndFailure
    {
        _log.info ( "**************************************** testRetrieveAllRequiredProperties()" );

        Vector <String> failed_properties = new Vector <String> ();

        for ( int counter = 0; counter < required_properties.length; counter++ )
        {
            EebProperty retrieved = bef.selectEebProperty ( required_properties[counter] );

            if ( null == retrieved )
            {
                failed_properties.add ( required_properties[counter] );

                _log.error ( "Could not find EEB property: " + required_properties[counter] );
            }
        }

        if ( failed_properties.size() > 0 )
        {
            fail ( "Failed properties detected: " + failed_properties.size() );
        }
    }

    public void testRetrieveInvalidProperty() throws BackEndFailure
    {
        _log.info ( "**************************************** testServiceCenterActive()" );

        EebProperty retrieved = bef.selectEebProperty ( "invalid-property-" + System.currentTimeMillis() );

        assertNull ( "Should have returned null for an invalid EEB property", retrieved );
    }

    public void testServiceCenterActive() throws BackEndFailure
    {
        _log.info ( "**************************************** testServiceCenterActive()" );

        EebProperty retrieved = bef.selectEebProperty ( EebProperty.PROPERTY_SERVICECENTER_ACTIVE );
        
        bef.setEebPropertyServiceCenterActive ( true );
        boolean test_active = bef.getEebPropertyServiceCenterActive();

        EebProperty test_property = bef.selectEebProperty ( EebProperty.PROPERTY_SERVICECENTER_ACTIVE );

        _log.info  ( "EEB Property retrieved: *" + test_property.getPropertyValue() + "*" );
        assertTrue ( "Could not set ServiceCenter to ACTIVE - boolean", test_active );
        assertTrue ( "Could not set ServiceCenter to ACTIVE - String",  test_property.getPropertyValue().equals ( "true" ) );
    }

    public void testServiceCenterInactive() throws BackEndFailure
    {
        _log.info ( "**************************************** testServiceCenterInactive()" );

        EebProperty retrieved = bef.selectEebProperty ( EebProperty.PROPERTY_SERVICECENTER_ACTIVE );
        
        bef.setEebPropertyServiceCenterActive ( false );
        boolean test_active = bef.getEebPropertyServiceCenterActive();

        EebProperty test_property = bef.selectEebProperty ( EebProperty.PROPERTY_SERVICECENTER_ACTIVE );

        _log.info  ( "EEB Property retrieved: *" + test_property.getPropertyValue() + "*" );
        assertFalse ( "Could not set ServiceCenter to INACTIVE - boolean", test_active );
        assertTrue  ( "Could not set ServiceCenter to INACTIVE - String",  test_property.getPropertyValue().equals ( "false" ) );
    }

    public void testAlarmpointActive() throws BackEndFailure
    {
        _log.info ( "**************************************** testAlarmpointActive()" );

        EebProperty retrieved = bef.selectEebProperty ( EebProperty.PROPERTY_ALARMPOINT_ACTIVE );
        
        bef.setEebPropertyAlarmpointActive ( true );
        boolean test_active = bef.getEebPropertyAlarmpointActive();

        EebProperty test_property = bef.selectEebProperty ( EebProperty.PROPERTY_ALARMPOINT_ACTIVE );

        _log.info  ( "EEB Property retrieved: *" + test_property.getPropertyValue() + "*" );
        assertTrue ( "Could not set Alarmpoint to INACTIVE - boolean", test_active );
        assertTrue ( "Could not set Alarmpoint to INACTIVE - String",  test_property.getPropertyValue().equals ( "true" ) );
    }

    public void testAlarmpointInactive() throws BackEndFailure
    {
        _log.info ( "**************************************** testAlarmpointInactive()" );

        EebProperty retrieved = bef.selectEebProperty ( EebProperty.PROPERTY_ALARMPOINT_ACTIVE );
        
        bef.setEebPropertyAlarmpointActive ( false );
        boolean test_active = bef.getEebPropertyAlarmpointActive();

        EebProperty test_property = bef.selectEebProperty ( EebProperty.PROPERTY_ALARMPOINT_ACTIVE );

        _log.info  ( "EEB Property retrieved: *" + test_property.getPropertyValue() + "*" );
        assertFalse ( "Could not set Alarmpoint to INACTIVE - boolean", test_active );
        assertTrue  ( "Could not set Alarmpoint to INACTIVE - String",  test_property.getPropertyValue().equals ( "false" ) );
    }
}
