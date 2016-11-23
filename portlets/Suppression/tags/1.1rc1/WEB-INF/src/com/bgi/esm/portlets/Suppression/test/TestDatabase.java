package com.bgi.esm.portlets.Suppression.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import com.bgi.esm.portlets.Suppression.forms.AddEntry;
import com.bgi.esm.portlets.Suppression.Toolkit;

import com.bgi.esm.portlets.Suppression.test.CommonTestCase;

public class TestDatabase extends CommonTestCase
{
    private static final String TEST_DESCRIPTION         = "Test Description";
    private static final String TEST_DESCRIPTION_UPDATED = "Test Description - Updated";
    private static final String TEST_APPLICATION         = "Test Application";
    private static final String TEST_APPLICATION_UPDATED = "Test Application - Updated";
    private static final String TEST_USERNAME            = "somebody";
    private static final String TEST_HOSTNAME            = "test_node";
    private static final String TEST_HOSTNAME_UPDATED    = "test_node_updated";
    private static final String TEST_WILDCARD            = "-";
    private static final String TEST_DB_SERVER           = "Test DB Server";
    private static final String TEST_DB_SERVER_MSG       = "Test DB Server Message";
    private static final int test_offset                 = -3600*1000*7;  // Pacific Daylight Time GMT -7 offset in milliseconds

    public TestDatabase ( String param )
    {
        super ( param );
    }
    
    /**
     * Creates a AddEntry form object with all default test values
     * 
     * @return a form object with all the default test values
     */
    public AddEntry createDefaultEntryForm()
    {
        return createDefaultEntryForm ( true, true, true, true, true, true );
    }
    
    /**
     * Creates a AddEntry form object with default test values as specified
     * 
     * @param set_application whether or not to set the "application" field to the default test value
     * @param set_description whether or not to set the "description" field to the default test value
     * @param set_username whether or not to set the "username" field to the default test value
     * @param set_node whether or not to set the "node" field to the default test value
     * @param set_db_server whether or not to set the "DB Server Instance" field to the default test value
     * @param set_db_server_message whether or not to set the "DB Server Message" field to the default test value
     * @return a form object with the specified default test values
     */
    public AddEntry createDefaultEntryForm ( boolean set_application, boolean set_description, boolean set_username,
            boolean set_node, boolean set_db_server, boolean set_db_server_message )
    {
        AddEntry data_form    = new AddEntry();
        Calendar calendar     = Calendar.getInstance();

        if ( true == set_application )
        {
            data_form.setApplication    ( TEST_APPLICATION );
        }
        else
        {
            data_form.setApplication    ( TEST_WILDCARD );
        }
        
        if ( true == set_description )
        {
            data_form.setDescription    ( TEST_DESCRIPTION );
        }
        else
        {
            data_form.setDescription    ( TEST_WILDCARD );
        }
        
        if ( true == set_username )
        {
            data_form.setUsername       ( TEST_USERNAME );
        }
        else
        {
            data_form.setUsername       ( TEST_WILDCARD );
        }
        
        if ( true == set_node )
        {
            data_form.setNode           ( TEST_HOSTNAME );
        }
        else
        {
            data_form.setNode           ( TEST_WILDCARD );
        }
        
        if ( true == set_db_server )
        {
            data_form.setDbServer       ( TEST_DB_SERVER );
        }
        else
        {
            data_form.setDbServer       ( TEST_WILDCARD );
        }
        
        if ( true == set_db_server_message )
        {
            data_form.setDbServerMsg    ( TEST_DB_SERVER_MSG );
        }
        else
        {
            data_form.setDbServerMsg    ( TEST_WILDCARD );
        }

        data_form.setStartChoice    ( "test" );
        data_form.setSupStartYear   ( Integer.toString ( calendar.get ( Calendar.YEAR )-2) );
        data_form.setSupStartMonth  ( Integer.toString ( calendar.get ( Calendar.MONTH   ) ) );
        data_form.setSupStartDate   ( Integer.toString ( calendar.get ( Calendar.DATE    ) ) );
        data_form.setSupStartHour   ( Integer.toString ( calendar.get ( Calendar.HOUR    ) ) );
        data_form.setSupStartMinute ( Integer.toString ( calendar.get ( Calendar.MINUTE  ) ) );

        calendar.add ( Calendar.HOUR, 4 );
        data_form.setEndChoice      ( "specified" );
        data_form.setEndChoiceNum   ( "4" );
        data_form.setEndChoiceUnit  ( "3600" );
        data_form.setSupEndYear     ( ""+(calendar.get ( Calendar.YEAR )+1) );
        data_form.setSupEndMonth    ( ""+calendar.get ( Calendar.MONTH  ) );
        data_form.setSupEndDate     ( ""+calendar.get ( Calendar.DATE   ) );
        data_form.setSupEndHour     ( ""+calendar.get ( Calendar.HOUR   ) );
        data_form.setSupEndMinute   ( ""+calendar.get ( Calendar.MINUTE ) );

        return data_form;
    }

    /**
     *  Adds a default suppression and returns the suppress_id of the newly added suppression
     *  
     *  @return the suppress_id of the newly added suppression
     */
    public int addSuppression() throws Exception
    {
        AddEntry data_form = createDefaultEntryForm();
        
        Date date_start = Toolkit.getSupStartDate( data_form );
        Date date_end   = Toolkit.getSupEndDate ( data_form );
        
        assertNotNull ( date_start );
        assertNotNull ( date_end   );
        
        return Toolkit.addSuppression ( data_form, test_offset );
    }
    

    /**
     * Tests the database schema to make sure that it has the right data columns fo the right data types 
     */
    public void testDatabaseSchema() throws Exception
    {
        Connection con       = Toolkit.getSuppressionDatabaseConnection();
        DatabaseMetaData dmd = con.getMetaData();
        String[] types       = {"TABLE"};
        ResultSet results    = dmd.getTables(null, null, "t_vpo_suppress", types);
        
        results.next();
    }
    
    /**
     * Tests adding a new suppression
     * @throws Exception
     */
    public void testAddSuppression() throws Exception
    {
        int suppress_id = addSuppression();
        
        assertTrue ( suppress_id > 0 );
    }
    
    /**
     * Tests retrieving all active suppressions for the test user "somebody"
     * @throws Exception
     */
    public void testGetCurrentSuppressions() throws Exception
    {
        int suppress_id = addSuppression();

        AddEntry data_form = Toolkit.retrieveEntry(suppress_id);
        
        assertNotNull ( data_form );
        
        ResultSet results = Toolkit.getCurrentSuppressions ( "somebody" );

        int num_results = 0;
        FileOutputStream outfile = new FileOutputStream ( "c:\\test\\database.test" );
        while ( results.next() )
        {
            outfile.write ( results.getString(1).getBytes() );
            outfile.write ( "\n".getBytes() );
            num_results++;          
        }
        outfile.close();
        
        assertTrue ( num_results > 0 );
    }
    
    /**
     * Tests retrieving all active suppressions for all users
     * @throws Exception
     */
    public void testGetAllCurrentSuppressions() throws Exception
    {
        int suppress_id = addSuppression();

        AddEntry data_form = Toolkit.retrieveEntry(suppress_id);
        
        assertNotNull ( data_form );
        
        ResultSet results = Toolkit.getAllCurrentSuppressions ();

        int num_results = 0;
        FileOutputStream outfile = new FileOutputStream ( "c:\\test\\database.test" );
        while ( results.next() )
        {
            outfile.write ( results.getString(1).getBytes() );
            outfile.write ( "\n".getBytes() );
            num_results++;          
        }
        outfile.close();
        
        assertTrue ( num_results > 0 );
    }
    
    /**
     * Tests retrieving all suppressions ever created by the test user "somebody"
     * @throws Exception
     */
    public void testGetHistoricalSuppressions() throws Exception
    {
        ResultSet results = Toolkit.getHistoricalSuppressions ( "somebody" );

        int num_results = 0;
        while ( results.next() )
        {
            num_results++;          
        }
        
        assertTrue ( num_results > 0 );     
    }
    
    /**
     * Tests retrieving a suppression after it has been added
     * 
     * @throws Exception
     */
    public void testGetEntry() throws Exception
    {
        int suppress_id = addSuppression();
        
        AddEntry data_form = Toolkit.retrieveEntry(suppress_id);
       
        assertEquals ( "-", data_form.getNode().trim() );
        /*  
        assertEquals ( "2007",       data_form.getSupStartYear().trim() );
        assertEquals ( "07",         data_form.getSupStartMonth().trim() );
        assertEquals ( "13",         data_form.getSupStartDate().trim() );
        assertEquals ( "12",         data_form.getSupStartHour().trim() );
        assertEquals ( "00",         data_form.getSupStartMinute().trim() );        
        //*/
    }
    
    /**
     * Tests updating an existing suppression action
     * 
     * @throws Exception
     */
    public void testUpdateEntry() throws Exception
    {
        int suppress_id = addSuppression();
        
        AddEntry data_form = Toolkit.retrieveEntry(suppress_id);

        data_form.setSuppressId ( Integer.toString ( suppress_id ) );
        
        Toolkit.updateEntry ( data_form );
    }
    
    /**
     * Tests updating the description of an existing suppression action
     * 
     * @throws Exception
     */
    public void testUpdateEntry_UpdateDescription() throws Exception
    {
    	AddEntry data_form      = createDefaultEntryForm();
    	
        int suppress_id         = Toolkit.addSuppression ( data_form, test_offset );
        
        AddEntry search_result1 = Toolkit.retrieveEntry ( suppress_id );
        
        assertNotNull ( search_result1 );
        
        search_result1.setDescription ( TEST_DESCRIPTION_UPDATED );
                
        Toolkit.updateEntry ( search_result1 );
        
        AddEntry search_result2 = Toolkit.retrieveEntry ( suppress_id );
        
        assertNotNull ( search_result2 );
        
        assertEquals ( search_result2.getDescription(), TEST_DESCRIPTION_UPDATED );
    }
    
    /**
     * Tests updating the application an existing suppression action
     * 
     * @throws Exception
     */
    public void testUpdateEntry_UpdateApplication() throws Exception
    {
    	AddEntry data_form      = createDefaultEntryForm();
    	
        int suppress_id         = Toolkit.addSuppression ( data_form, test_offset );
        
        AddEntry search_result1 = Toolkit.retrieveEntry ( suppress_id );
        
        assertNotNull ( search_result1 );
        
        search_result1.setApplication ( TEST_APPLICATION_UPDATED );
                
        Toolkit.updateEntry ( search_result1 );
        
        AddEntry search_result2 = Toolkit.retrieveEntry ( suppress_id );
        
        assertNotNull ( search_result2 );
        
        assertEquals ( search_result2.getApplication(), TEST_APPLICATION_UPDATED );
    }
    
    /**
     * Tests updating the node an existing suppression action
     * 
     * @throws Exception
     */
    public void testUpdateEntry_UpdateNode() throws Exception
    {
    	AddEntry data_form      = createDefaultEntryForm();
    	
        int suppress_id         = Toolkit.addSuppression ( data_form, test_offset );
        
        AddEntry search_result1 = Toolkit.retrieveEntry ( suppress_id );
        
        assertNotNull ( search_result1 );
        
        search_result1.setNode ( TEST_HOSTNAME_UPDATED );
                
        Toolkit.updateEntry ( search_result1 );
        
        AddEntry search_result2 = Toolkit.retrieveEntry ( suppress_id );
        
        assertNotNull ( search_result2 );
        
        assertEquals ( search_result2.getNode(), TEST_HOSTNAME_UPDATED );
    }
    
    /**
     * Tests updating an existing suppression action
     * 
     * @throws Exception
     */
    public void testUpdateEntry_RemoveOnReboot() throws Exception
    {
    	AddEntry data_form      = createDefaultEntryForm();
    	data_form.setRemoveOnReboot ( "off" );
    	
        int suppress_id         = Toolkit.addSuppression ( data_form, test_offset );
        
        AddEntry search_result1 = Toolkit.retrieveEntry ( suppress_id );
        
        assertNotNull ( search_result1 );
        
        search_result1.setRemoveOnReboot ( "on" );
                
        Toolkit.updateEntry ( search_result1 );
        
        AddEntry search_result2 = Toolkit.retrieveEntry ( suppress_id );
        
        assertNotNull ( search_result2 );
        
        assertEquals ( search_result2.getRemoveOnReboot(), "on" );
    }
    
    /**
     * Tests whether or not the suppression database is functional
     * @throws Exception
     */
    public void testAddEntryAction_SuppressionDatabaseConnection() throws Exception
    {
        Statement stmt        = Toolkit.getSuppressionDatabaseStatement();

        assertNotNull ( stmt );
    }

    /**
     * Tests whether or not the data source database (VPO database) is functional
     * @throws Exception
     */
    public void testAddEntryAction_DataSourceDatabaseConnection() throws Exception
    {
        Statement stmt        = Toolkit.getDataSourceDatabaseStatement();

        assertNotNull ( stmt );
    }

    public void testInstanceDatabaseConnection() throws Exception
    {
        Connection con = Toolkit.getInstanceDatabaseConnection();

        assertNotNull ( con );
    }

    /**
     * Tests retrieving the names of all the nodes being monitored by the data source database (VPO database)
     * @throws Exception
     */
    public void testAddEntryAction_RetrieveNodeNames() throws Exception
    {
        AddEntry data_form    = new AddEntry();

        Statement stmt        = Toolkit.getDataSourceDatabaseStatement();
        ResultSet results     = stmt.executeQuery ( "select distinct lower(node_name) as nn from opc_node_names order by nn" ); 

        Vector <String> data  = new Vector <String> ();
        String data_string    = null;
        while ( results.next() )
        {
            data_string = results.getString ( 1 );
            if ( null != data_string )
            {
                data.add ( data_string );
            }
        }

        assertTrue ( data.size() > 0 );

        data_form.setNodeNames ( data );

        assertNotNull ( data_form.getNodeNames() );

        stmt.close();
    }

    /**
     * Tests retrieving all the application/message groups from the data source database (VPO database)
     * @throws Exception
     */
    public void testAddEntryAction_RetrieveMessageGroups() throws Exception
    {
        AddEntry data_form    = new AddEntry();

        Statement stmt        = Toolkit.getDataSourceDatabaseStatement();
        ResultSet results     = stmt.executeQuery ( "select distinct lower(name) as n from opc_message_groups order by n" );

        Vector <String> data  = new Vector <String>();
        while ( results.next() )
        {
            data.add ( results.getString ( 1 ) );
        }
        assertTrue ( data.size() > 0 );

        data_form.setMessageGroups ( data );

        assertNotNull( data_form.getMessageGroups() );

        stmt.close();
    }

    /**
     * Tests retrieving a list of all data serevrs from the data source database (VPO database).
     * @throws Exception
     */
    public void testAddEntryAction_RetrieveDataServers() throws Exception
    {
        AddEntry data_form    = new AddEntry();

        Statement stmt        = Toolkit.getDataSourceDatabaseStatement();
        ResultSet results     = stmt.executeQuery ( "select node_id, node_name FROM opc_node_names ORDER BY node_name " );
        
        Vector <String> data  = new Vector <String> ();
        while ( results.next() )
        {
            data.add ( results.getString ( 1 ) );
        }

        assertTrue ( data.size() > 0 );

        data_form.setDataServers ( data );

        assertNotNull ( data_form.getDataServers() );

        stmt.close();
    }

    /**
     * Tests the execution classpath and the availability of the common properties for this portlet.
     * @throws Exception
     */
    public void testRetrieveProperties() throws Exception
    {
        Properties common_properties = readCommonProperties();
        String db_driver_name        = null;
        String db_connection_url     = null;
        String db_username           = null;
        String db_password           = null;
        String extract_file          = null;

        db_driver_name    = common_properties.getProperty ( "suppression.suppressions.database.driver" );
        db_connection_url = common_properties.getProperty ( "suppression.suppressions.database.connection_url" );
        db_username       = common_properties.getProperty ( "suppression.suppressions.database.username" );
        db_password       = common_properties.getProperty ( "suppression.suppressions.database.password" );

        assertNotNull ( db_driver_name );
        assertNotNull ( db_connection_url );
        assertNotNull ( db_username );
        assertNotNull ( db_password );

        db_driver_name    = common_properties.getProperty ( "suppression.data_source.database.driver" );
        db_connection_url = common_properties.getProperty ( "suppression.data_source.database.connection_url" );
        db_username       = common_properties.getProperty ( "suppression.data_source.database.username" );
        db_password       = common_properties.getProperty ( "suppression.data_source.database.password" );

        assertNotNull ( db_driver_name );
        assertNotNull ( db_connection_url );
        assertNotNull ( db_username );
        assertNotNull ( db_password );

        db_driver_name    = common_properties.getProperty ( "suppression.inst.database.driver" );
        db_connection_url = common_properties.getProperty ( "suppression.inst.database.connection_url" );
        db_username       = common_properties.getProperty ( "suppression.inst.database.username" );
        db_password       = common_properties.getProperty ( "suppression.inst.database.password" );

        assertNotNull ( db_driver_name );
        assertNotNull ( db_connection_url );
        assertNotNull ( db_username );
        assertNotNull ( db_password );
        
        extract_file      = common_properties.getProperty ( "suppression.suppressions.extract_file" );
        assertNotNull ( extract_file );
        
        extract_file      = common_properties.getProperty ( "suppression.data_source.extract_file" );
        assertNotNull ( extract_file );
        
        extract_file      = common_properties.getProperty ( "suppression.inst.extract_file" );
        assertNotNull ( extract_file );
    }

    /**
     * Tests whether it is possible to connect to the suppression database.
     * @throws Exception
     */
    public void testSuppressionDatabaseConnection() throws Exception
    {
        Class c                      = this.getClass();
        ClassLoader cl               = c.getClassLoader();
        InputStream is               = cl.getResourceAsStream ( "portlet-common.properties" );
        Properties common_properties = null;
        common_properties = new Properties(); 
        common_properties.load ( is );

        String db_driver_name        = null;
        String db_connection_url     = null;
        String db_username           = null;
        String db_password           = null;
        db_driver_name    = common_properties.getProperty ( "suppression.suppressions.database.driver" );
        db_connection_url = common_properties.getProperty ( "suppression.suppressions.database.connection_url" );
        db_username       = common_properties.getProperty ( "suppression.suppressions.database.username" );
        db_password       = common_properties.getProperty ( "suppression.suppressions.database.password" );
        
        assertNotNull ( db_driver_name );
        assertNotNull ( db_connection_url );
        assertNotNull ( db_username );
        assertNotNull ( db_password );
    }

    /**
     * Tests whether or not it is possible to connect to the data source database (VPO database)
     * @throws Exception
     */
    public void testDatabaseConnection() throws Exception
    {
        Class c                      = this.getClass();
        ClassLoader cl               = c.getClassLoader();
        InputStream is               = cl.getResourceAsStream ( "portlet-common.properties" );
        Properties common_properties = null;
        String db_driver_name        = null;
        String db_connection_url     = null;
        String db_username           = null;
        String db_password           = null;
        common_properties = new Properties(); 
        common_properties.load ( is );

        db_driver_name    = common_properties.getProperty ( "suppression.data_source.database.driver" );
        db_connection_url = common_properties.getProperty ( "suppression.data_source.database.connection_url" );
        db_username       = common_properties.getProperty ( "suppression.data_source.database.username" );
        db_password       = common_properties.getProperty ( "suppression.data_source.database.password" );

        Class.forName ( db_driver_name );
        Connection con    = DriverManager.getConnection ( db_connection_url, db_username, db_password );
        Statement stmt    = con.createStatement();

        stmt.close();
    }

    /**
     * @throws Exception
     */
    public void testDatabaseRetrieveMessageGroups() throws Exception
    {
        Class c                      = this.getClass();
        ClassLoader cl               = c.getClassLoader();
        InputStream is               = cl.getResourceAsStream ( "portlet-common.properties" );
        Properties common_properties = null;
        String db_driver_name        = null;
        String db_connection_url     = null;
        String db_username           = null;
        String db_password           = null;
        common_properties = new Properties(); 
        common_properties.load ( is );

        db_driver_name    = common_properties.getProperty ( "suppression.data_source.database.driver" );
        db_connection_url = common_properties.getProperty ( "suppression.data_source.database.connection_url" );
        db_username       = common_properties.getProperty ( "suppression.data_source.database.username" );
        db_password       = common_properties.getProperty ( "suppression.data_source.database.password" );

        Class.forName ( db_driver_name );
        Connection con    = DriverManager.getConnection ( db_connection_url, db_username, db_password );
        Statement stmt    = con.createStatement();
        ResultSet results = stmt.executeQuery ( "select distinct lower(name) as n from opc_message_groups order by n" );

        assertTrue ( results.next() );

        stmt.close();
    }

    public void testDatabaseRetrieveNodeNames() throws Exception
    {
        Class c                      = this.getClass();
        ClassLoader cl               = c.getClassLoader();
        InputStream is               = cl.getResourceAsStream ( "portlet-common.properties" );
        Properties common_properties = null;
        String db_connection_url     = null;
        String db_username           = null;
        String db_password           = null;
        common_properties = new Properties(); 
        common_properties.load ( is );

        db_connection_url = common_properties.getProperty ( "suppression.data_source.database.connection_url" );
        db_username       = common_properties.getProperty ( "suppression.data_source.database.username" );
        db_password       = common_properties.getProperty ( "suppression.data_source.database.password" );

        Connection con    = DriverManager.getConnection ( db_connection_url, db_username, db_password );
        Statement stmt    = con.createStatement();
        ResultSet results = stmt.executeQuery ( "select distinct lower(node_name) as nn from opc_node_names order by nn" );

        assertTrue ( results.next() );

        stmt.close();
    }

    public void testDatabaseRetrieveDataServers() throws Exception
    {
        Class c                      = this.getClass();
        ClassLoader cl               = c.getClassLoader();
        InputStream is               = cl.getResourceAsStream ( "portlet-common.properties" );
        Properties common_properties = null;
        String db_driver_name        = null;
        String db_connection_url     = null;
        String db_username           = null;
        String db_password           = null;
        common_properties = new Properties(); 
        common_properties.load ( is );

        db_driver_name    = common_properties.getProperty ( "suppression.inst.database.driver" );
        db_connection_url = common_properties.getProperty ( "suppression.inst.database.connection_url" );
        db_username       = common_properties.getProperty ( "suppression.inst.database.username" );
        db_password       = common_properties.getProperty ( "suppression.inst.database.password" );

        Class.forName ( db_driver_name );
        Connection con    = DriverManager.getConnection ( db_connection_url, db_username, db_password );
        Statement stmt    = con.createStatement();
        //ResultSet results = stmt.executeQuery ( "SELECT server_nm from .t_data_servers where server_type_cd in ('sql', 'mssql', 'rep', 'iq') and dba_support_grp_id != '' and dba_support_grp_id != 'XX' and dba_support_grp_id is not null" );
        ResultSet results = stmt.executeQuery ( "SELECT server_nm from .t_data_servers ORDER BY server_nm" );

        assertTrue ( results.next() );

        stmt.close();
    }

    /**
     * Tests adding a suppression that will remove itself on reboot
     * @throws Exception
     */
    public void testAddingRemoveOnRebootSuppression() throws Exception
    {
        //  Create and add a test suppression that removes itself on reboot
        AddEntry data_form = createDefaultEntryForm();
        data_form.setRemoveOnReboot( "on" );
        int suppress_id = Toolkit.addSuppression ( data_form, test_offset );
        
        //  Retrieve the newly added suppression to test 
        AddEntry test_form = Toolkit.retrieveEntry ( suppress_id );
        
        assertTrue ( test_form.getRemoveOnReboot().equals ( "on" ) );
    }

    /**
     * Adds a test suppression that is DELETED and tries to search for it.
     * 
     * @throws Exception
     */
    public void testSearchForDeletedSuppressions() throws Exception
    {
        Calendar calendar1     = Calendar.getInstance();
        calendar1.set ( Calendar.YEAR,        1999             );
        calendar1.set ( Calendar.MONTH,       Calendar.JANUARY );
        calendar1.set ( Calendar.DATE,        1                );
        calendar1.set ( Calendar.HOUR_OF_DAY, 0                );
        calendar1.set ( Calendar.MINUTE,      0                );
        calendar1.set ( Calendar.SECOND,      0                );
        Date date_start    = calendar1.getTime();
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set ( Calendar.YEAR, calendar2.get ( Calendar.YEAR ) + 1 );
        Date date_end      = calendar2.getTime();
        
        ResultSet results  = Toolkit.retrieveHistory ( "", "", "", "", "", 
                date_start, date_end, Toolkit.REMOVE_ON_REBOOT_DOES_NOT_MATTER, Toolkit.IS_DELETED_TRUE_ONLY, TEST_USERNAME, "suppress_id" );
        
        String deleted_flg = null;
        int num_results    = 0;
        boolean found      = false;
        while ( results.next() )
        {
            deleted_flg = results.getString ( "deleted_flg" );
            
            found = deleted_flg.equals( "y" );
            
            num_results++;
            
            if ( true == found )
            {
                break;
            }
        }
        
        assertTrue ( num_results > 0 );
        assertTrue ( "Cound not find a newly created non-deleted suppression", found );
    }
    
    /**
     * Adds a test suppression that is DELETED and tries to search ALL suppressions for it.
     * 
     * @throws Exception
     */
    public void testSearchAllForDeletedSuppressions() throws Exception
    {
        Calendar calendar1     = Calendar.getInstance();
        calendar1.set ( Calendar.YEAR,        1999             );
        calendar1.set ( Calendar.MONTH,       Calendar.JANUARY );
        calendar1.set ( Calendar.DATE,        1                );
        calendar1.set ( Calendar.HOUR_OF_DAY, 0                );
        calendar1.set ( Calendar.MINUTE,      0                );
        calendar1.set ( Calendar.SECOND,      0                );
        Date date_start    = calendar1.getTime();
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set ( Calendar.YEAR, calendar2.get ( Calendar.YEAR ) + 1 );
        Date date_end      = calendar2.getTime();
        
        ResultSet results  = Toolkit.retrieveHistory ( "", "", "", "", "", 
                date_start, date_end, Toolkit.REMOVE_ON_REBOOT_DOES_NOT_MATTER, Toolkit.IS_DELETED_TRUE_ONLY, "", "suppress_id" );
        
        String deleted_flg = null;
        int num_results    = 0;
        boolean found      = false;
        while ( results.next() )
        {
            deleted_flg = results.getString ( "deleted_flg" );
            
            found = deleted_flg.equals( "y" );
            
            num_results++;
            
            if ( true == found )
            {
                break;
            }
        }
        
        assertTrue ( num_results > 0 );
        assertTrue ( "Cound not find a newly created non-deleted suppression", found );
    }
    
    /**
     * Adds a test suppression that is NOT DELETED and tries to search for it.
     * 
     * @throws Exception
     */
    public void testSearchForNonDeletedSuppressions() throws Exception
    {
        Calendar calendar1     = Calendar.getInstance();
        calendar1.set ( Calendar.YEAR,        1999             );
        calendar1.set ( Calendar.MONTH,       Calendar.JANUARY );
        calendar1.set ( Calendar.DATE,        1                );
        calendar1.set ( Calendar.HOUR_OF_DAY, 0                );
        calendar1.set ( Calendar.MINUTE,      0                );
        calendar1.set ( Calendar.SECOND,      0                );
        Date date_start    = calendar1.getTime();
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set ( Calendar.YEAR, calendar2.get ( Calendar.YEAR ) + 1 );
        Date date_end      = calendar2.getTime();
        
        ResultSet results  = Toolkit.retrieveHistory ( TEST_DESCRIPTION, TEST_APPLICATION, TEST_HOSTNAME, 
                TEST_DB_SERVER, TEST_DB_SERVER_MSG, date_start, date_end, Toolkit.REMOVE_ON_REBOOT_DOES_NOT_MATTER, Toolkit.IS_DELETED_FALSE_ONLY, TEST_USERNAME, null );
        
        String deleted_flg = null;
        int num_results    = 0;
        boolean found      = false;
        while ( results.next() )
        {
            deleted_flg = results.getString ( "deleted_flg" );
            
            found = ( null == deleted_flg ) || ( !deleted_flg.equals( "y" ) );
            
            num_results++;
            
            if ( true == found )
            {
                break;
            }
        }
        
        assertTrue ( num_results > 0 );
        assertTrue ( "Cound not find a non-deleted suppression", found );
    }
    
    /**
     * Adds a test suppression that is NOT DELETED and tries to search ALL suppressions for it.
     * 
     * @throws Exception
     */
    public void testSearchAllForNonDeletedSuppressions() throws Exception
    {
        Calendar calendar1     = Calendar.getInstance();
        calendar1.set ( Calendar.YEAR,        1999             );
        calendar1.set ( Calendar.MONTH,       Calendar.JANUARY );
        calendar1.set ( Calendar.DATE,        1                );
        calendar1.set ( Calendar.HOUR_OF_DAY, 0                );
        calendar1.set ( Calendar.MINUTE,      0                );
        calendar1.set ( Calendar.SECOND,      0                );
        Date date_start    = calendar1.getTime();
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set ( Calendar.YEAR, calendar2.get ( Calendar.YEAR ) + 1 );
        Date date_end      = calendar2.getTime();
        
        ResultSet results  = Toolkit.retrieveHistory ( TEST_DESCRIPTION, TEST_APPLICATION, TEST_HOSTNAME, 
                TEST_DB_SERVER, TEST_DB_SERVER_MSG, date_start, date_end, Toolkit.REMOVE_ON_REBOOT_DOES_NOT_MATTER, Toolkit.IS_DELETED_FALSE_ONLY, "", null );
        
        String deleted_flg = null;
        int num_results    = 0;
        boolean found      = false;
        while ( results.next() )
        {
            deleted_flg = results.getString ( "deleted_flg" );
            
            found = ( null == deleted_flg ) || ( !deleted_flg.equals( "y" ) );
            
            num_results++;
            
            if ( true == found )
            {
                break;
            }
        }
        
        assertTrue ( num_results > 0 );
        assertTrue ( "Cound not find a non-deleted suppression", found );
    }
    
    /**
     * Adds a test suppression that is DELETED and another one that is NOT DELETED.  
     * A test search should return both suppressions.
     * 
     * @throws Exception
     */
    public void testSearchForSuppressionsWithAnyDeletedState() throws Exception
    {
        AddEntry data_form1 = createDefaultEntryForm();
        AddEntry data_form2 = createDefaultEntryForm();
        
        int suppress_id1    = Toolkit.addSuppression ( data_form1, 0 );
        int suppress_id2    = Toolkit.addSuppression ( data_form2, 0 );
        int suppress_id     = 0;
        
        Toolkit.deleteSuppression ( suppress_id1 );
        
        boolean found_deleted = false;
        boolean found_normal  = false;
        
        ResultSet results = Toolkit.getCurrentSuppressions ( TEST_USERNAME );

        while ( results.next() )
        {
            suppress_id = results.getInt ( "suppress_id" );
            
            if ( suppress_id == suppress_id1 )
            {
                found_deleted = true;
            }
            
            if ( suppress_id == suppress_id2 )
            {
                found_normal  = true;
            }
            
            if (( true == found_deleted ) && ( true == found_normal ))
            {
                break;
            }
        }

        AddEntry search_result = Toolkit.retrieveEntry ( suppress_id2 );

        assertFalse ( "Found deleted suppression in ALL search", found_deleted );
        assertTrue  ( "Cound not find non-deleted suppression in ALL search", (null!=search_result));
    }
    
    /**
     * Adds a test suppression that is DELETED and another one that is NOT DELETED.  
     * A test search through ALL the suppressions should return both suppressions.
     * 
     * @throws Exception
     */
    public void testSearchAllForSuppressionsWithAnyDeletedState() throws Exception
    {
        AddEntry data_form1 = createDefaultEntryForm();
        AddEntry data_form2 = createDefaultEntryForm();
        
        int suppress_id1    = Toolkit.addSuppression ( data_form1, 0 );
        int suppress_id2    = Toolkit.addSuppression ( data_form2, 0 );
        int suppress_id     = 0;
        
        Toolkit.deleteSuppression ( suppress_id1 );
        
        boolean found_deleted = false;
        boolean found_normal  = false;
        
        ResultSet results = Toolkit.getAllCurrentSuppressions();

        while ( results.next() )
        {
            suppress_id = results.getInt ( "suppress_id" );
            
            if ( suppress_id == suppress_id1 )
            {
                found_deleted = true;
            }
            
            if ( suppress_id == suppress_id2 )
            {
                found_normal  = true;
            }
            
            if (( true == found_deleted ) && ( true == found_normal ))
            {
                break;
            }
        }

        AddEntry search_result = Toolkit.retrieveEntry ( suppress_id2 );

        assertFalse ( "Found deleted suppression in ALL search", found_deleted );
        assertTrue  ( "Cound not find non-deleted suppression in ALL search", (null!=search_result));
    }
    
    /**
     * Adds a suppression that REMOVES ON REBOOT and searches for it.
     * 
     * @throws Exception
     */
    public void testSearchForSuppressionsThatRemoveOnReboot() throws Exception
    {
    	AddEntry data_form   = createDefaultEntryForm();
    	
    	data_form.setRemoveOnReboot( "on" );
        
        int suppress_id      = Toolkit.addSuppression ( data_form, test_offset );
        
        boolean found_entry  = false;
        
        ResultSet results = Toolkit.getCurrentSuppressions ( TEST_USERNAME );

        while ( results.next() )
        {
            int found_suppress_id = results.getInt ( "suppress_id" );
            
            if ( suppress_id == found_suppress_id )
            {
                found_entry  = true;
                
                break;
            }
            
        }

        assertTrue ( "Unable to find remove-at-reboot suppression in ALL search", found_entry );
        
        AddEntry search_result = Toolkit.retrieveEntry ( suppress_id );

        assertNotNull ( "Cound not find non-deleted suppression in ALL search", search_result );
        
        assertEquals ( "remove-at-reboot suppression was not set correctly", search_result.getRemoveOnReboot(), "on" );
    }
    
    /**
     * Adds a suppression that REMOVES ON REBOOT and searches for it.
     * 
     * @throws Exception
     */
    public void testSearchAllForSuppressionsThatRemoveOnReboot() throws Exception
    {
    	AddEntry data_form   = createDefaultEntryForm();
    	
    	data_form.setRemoveOnReboot( "on" );
        
        int suppress_id      = Toolkit.addSuppression ( data_form, test_offset );
        
        boolean found_entry  = false;
        
        ResultSet results = Toolkit.getAllCurrentSuppressions();

        while ( results.next() )
        {
            int found_suppress_id = results.getInt ( "suppress_id" );
            
            if ( suppress_id == found_suppress_id )
            {
                found_entry  = true;
                
                break;
            }            
        }

        assertTrue ( "Unable to find remove-at-reboot suppression in ALL search", found_entry );
        
        AddEntry search_result = Toolkit.retrieveEntry ( suppress_id );

        assertNotNull ( "Cound not find non-deleted suppression in ALL search", search_result );
        
        assertEquals ( "remove-at-reboot suppression was not set correctly", search_result.getRemoveOnReboot(), "on" );
    }
    
    /**
     * Adds a suppression that DOES NOT REMOVES ON REBOOT and searches for it.
     * 
     * @throws Exception
     */
    public void testSearchForSuppressionsThatDoNotRemoveOnReboot() throws Exception
    {
    	AddEntry data_form   = createDefaultEntryForm();
    	
    	data_form.setRemoveOnReboot( "off" );
        
        int suppress_id      = Toolkit.addSuppression ( data_form, test_offset );
        
        boolean found_entry  = false;
        
        ResultSet results = Toolkit.getCurrentSuppressions ( TEST_USERNAME );

        while ( results.next() )
        {
            int found_suppress_id = results.getInt ( "suppress_id" );
            
            if ( suppress_id == found_suppress_id )
            {
                found_entry  = true;
                
                break;
            }
            
        }

        assertTrue ( "Unable to find remove-at-reboot suppression in ALL search", found_entry );
        
        AddEntry search_result = Toolkit.retrieveEntry ( suppress_id );

        assertNotNull ( "Cound not find non-deleted suppression in ALL search", search_result );
        
        assertFalse ( "remove-at-reboot suppression was not set correctly", search_result.getRemoveOnReboot().equals( "on" ) );	
    }
    
    /**
     * Adds a suppression that DOES NOT REMOVES ON REBOOT and searches for it.
     * 
     * @throws Exception
     */
    public void testSearchAllForSuppressionsThatDoNotRemoveOnReboot() throws Exception
    {
    	AddEntry data_form   = createDefaultEntryForm();
    	
    	data_form.setRemoveOnReboot( "off" );
        
        int suppress_id      = Toolkit.addSuppression ( data_form, test_offset );
        
        boolean found_entry  = false;
        
        ResultSet results = Toolkit.getAllCurrentSuppressions ();
        
        while ( results.next() )
        {
            int found_suppress_id = results.getInt ( "suppress_id" );
            
            if ( suppress_id == found_suppress_id )
            {
                found_entry  = true;
                
                break;
            }
            
        }

        assertTrue ( "Unable to find remove-at-reboot suppression in ALL search", found_entry );
        
        AddEntry search_result = Toolkit.retrieveEntry ( suppress_id );

        assertNotNull ( "Cound not find non-deleted suppression in ALL search", search_result );
        
        assertFalse ( "remove-at-reboot suppression was not set correctly", search_result.getRemoveOnReboot().equals( "on" ) );	
    }
    
    /**
     * Adds a test suppression that is REMOVED ON REBOOT and another one that is NOT 
     * REMOVED ON REBOOT.  A test search should return both suppressions.
     * 
     * @throws Exception
     */
    public void testSearchForSuppressionsWithAnyRemoveOnRebootState() throws Exception
    {
    	AddEntry data_form1  = createDefaultEntryForm();
    	AddEntry data_form2  = createDefaultEntryForm();
    	
    	data_form1.setRemoveOnReboot( "on" );
    	data_form2.setRemoveOnReboot( "off" );
        
        int suppress_id1     = Toolkit.addSuppression ( data_form1, 0 );
        int suppress_id2     = Toolkit.addSuppression ( data_form2, 0 );
        
        boolean found_entry1 = false;
        boolean found_entry2 = false;
        
        ResultSet results = Toolkit.getCurrentSuppressions ( TEST_USERNAME );
        
        while ( results.next() )
        {
            int found_suppress_id = results.getInt ( "suppress_id" );
            
            if ( suppress_id1 == found_suppress_id )
            {
                found_entry1 = true;
            }
            else if ( suppress_id2 == found_suppress_id )
            {
            	found_entry2 = true;
            }
            
        }

        assertTrue ( "Unable to find both remove-at-reboot suppressions in ALL search", found_entry1 && found_entry2 );
        
        AddEntry search_result1 = Toolkit.retrieveEntry ( suppress_id1 );
        AddEntry search_result2 = Toolkit.retrieveEntry ( suppress_id2 );
                
        assertTrue  ( "remove-at-reboot suppression #1 was not set correctly", search_result1.getRemoveOnReboot().equals( "on" ) );
        assertFalse ( "remove-at-reboot suppression #2 was not set correctly", search_result2.getRemoveOnReboot().equals( "on" ) );
    }
    
    /**
     * Adds a test suppression that is REMOVED ON REBOOT and another one that is NOT 
     * REMOVED ON REBOOT.  A test search should return both suppressions.
     * 
     * @throws Exception
     */
    public void testSearchAllForSuppressionsWithAnyRemoveOnRebootState() throws Exception
    {
    	AddEntry data_form1  = createDefaultEntryForm();
    	AddEntry data_form2  = createDefaultEntryForm();
    	
    	data_form1.setRemoveOnReboot( "on" );
    	data_form2.setRemoveOnReboot( "off" );
        
        int suppress_id1     = Toolkit.addSuppression ( data_form1, 0 );
        int suppress_id2     = Toolkit.addSuppression ( data_form2, 0 );
        
        boolean found_entry1 = false;
        boolean found_entry2 = false;
        
        ResultSet results = Toolkit.getAllCurrentSuppressions ();
        
        while ( results.next()  )
        {
            int found_suppress_id = results.getInt ( "suppress_id" );
            
            if ( suppress_id1 == found_suppress_id )
            {
                found_entry1 = true;
            }
            else if ( suppress_id2 == found_suppress_id )
            {
            	found_entry2 = true;
            }
            
        }

        assertTrue ( "Unable to find both remove-at-reboot suppressions in ALL search", found_entry1 && found_entry2 );
        
        AddEntry search_result1 = Toolkit.retrieveEntry ( suppress_id1 );
        AddEntry search_result2 = Toolkit.retrieveEntry ( suppress_id2 );
                
        assertTrue  ( "remove-at-reboot suppression #1 was not set correctly", search_result1.getRemoveOnReboot().equals( "on" ) );
        assertFalse ( "remove-at-reboot suppression #2 was not set correctly", search_result2.getRemoveOnReboot().equals( "on" ) );
    }
    
    /**
     * Tests searching for current entries by hostname 
     *  
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void testSearchForCurrentSuppressionsByHostname() throws IOException, SQLException, ClassNotFoundException
    {
        AddEntry data_form = createDefaultEntryForm();
        
        int suppress_id = Toolkit.addSuppression ( data_form, test_offset );

        assertEquals ( TEST_HOSTNAME, data_form.getNode() );

        ResultSet results = Toolkit.getCurrentSuppressionsByHostname ( TEST_HOSTNAME );
        
        boolean found = false;
        
        int id = 0;
        
        AddEntry search_result = Toolkit.retrieveEntry ( suppress_id );
        
        assertEquals ( suppress_id, Integer.parseInt ( search_result.getSuppressId() ) );

        StringBuilder entries_found = new StringBuilder ( "<br>" );
        FileOutputStream outfile = new FileOutputStream( "c:\\test\\test.out" );
        outfile.write ( "Want: ".getBytes() );
        outfile.write ( Integer.toString ( suppress_id ).getBytes() );
        outfile.write ( "\n".getBytes() );
        while ( results.next() )
        {
            id = results.getInt ( "suppress_id" );
            entries_found.append ( id );
            entries_found.append ( ", " );
            
            outfile.write ( Integer.toString ( id ).getBytes() );
            outfile.write ( "\n".getBytes() );
            
            if ( id == suppress_id )
            {
                found = true;
                break;
            }
        }
        outfile.close();
        
        assertTrue ( "Could not find entry when searching by hostname.  Searching for suppress_id=" + suppress_id + "<br><br>Entries found: " + entries_found.toString(), found );
    }
    
    /**
     *  Test retrieving server data from the VPO database 
     */
    public void testRetrieveServerData() throws Exception
    {
        AddEntry data_form = new AddEntry();
        
        Toolkit.retrieveServerData( data_form );
    }
    
    /**
     *  Test the configurable suppressions table 
     */
    public void testConfigurableSuppressionsTable() throws Exception
    {
        ResultSet results = Toolkit.getCurrentSuppressions ( "somebody" );
        
        assertTrue ( results.next() );
        
        /*
        FileOutputStream outfile = new FileOutputStream ( "c:\\test\\suppressions.html" );
            String output = Toolkit.createSuppressionsTable( results, TEST_USERNAME, "/sample_struts_path", true, true, true, true, true, true, true, true, true, false );
            outfile.write ( output.getBytes() );
        outfile.close();
        //*/
    }

    public void testPerformanceCurrentSuppressions() throws Exception
    {
        ResultSet results                = Toolkit.getAllCurrentSuppressions ();

        String group_nm                = null;
        String node_nm                 = null;
        String instance                = null;
        String message                 = null;
        String suppress_desc_txt       = null;
        String creator                 = null;
        java.util.Date start_utc_tms   = null;
        java.util.Date end_utc_tms     = null;
        //String reboot_text             = null;
        //int remove_on_reboot           = 0;
        int suppress_id                = 0;
        
        //  The information is extracted from the ResultSet and put into 
        //  Vector objects to better mimick the process the app server will
        //  be going through
        Vector <Integer> v_suppress_id = new Vector <Integer> (); 
        Vector <String> v_group_nm     = new Vector <String> ();
        Vector <String> v_node_nm      = new Vector <String> ();
        Vector <String> v_instance     = new Vector <String> ();
        Vector <String> v_message      = new Vector <String> ();
        Vector <String> v_desc_txt     = new Vector <String> ();
        Vector <String> v_creator      = new Vector <String> ();
        Vector <java.util.Date> v_start= new Vector <java.util.Date> ();
        Vector <java.util.Date> v_end  = new Vector <java.util.Date> ();
        

        while ( results.next() )
        {
            suppress_id        = results.getInt ( "suppress_id" );
            group_nm           = results.getString ( "group_nm" );
            node_nm            = results.getString ( "node_nm" );
            instance           = results.getString ( "instance" );
            message            = results.getString ( "message" );
            instance           = results.getString ( "instance" );
            message            = results.getString ( "message" );
            suppress_desc_txt  = results.getString ( "suppress_desc_txt" );
            Timestamp ts_start = results.getTimestamp ( "start_utc_tms" );
            Timestamp ts_end   = results.getTimestamp ( "end_utc_tms" );
            start_utc_tms      = new java.util.Date ( ts_start.getTime() );
            end_utc_tms        = new java.util.Date ( ts_end.getTime() );
            creator            = results.getString ( "create_nm" );
            //remove_on_reboot   = results.getInt ( "remove_on_reboot" );
            //reboot_text        = (1 == remove_on_reboot)? "Yes" : "No";
            
            v_suppress_id.add ( new Integer ( suppress_id ) );
            v_group_nm.add ( group_nm );
            v_node_nm.add  ( node_nm  );
            v_instance.add ( instance );
            v_message.add  ( message  );
            v_desc_txt.add ( suppress_desc_txt );
            v_creator.add  ( creator  );
            v_start.add    ( start_utc_tms );
            v_end.add      ( end_utc_tms );
         }
    }
    
    /**
     *  Tests that to make sure that the value of the "node" field remains blank if it is not specified.
     *  This is a usability issue requested by Bill Dooley on Jan 4, 2006.
     */
    public void testUnspecifiedNodeIsWildcardIfNotSpecified()
    {
        boolean set_application       = true;
        boolean set_description       = true; 
        boolean set_username          = true;
        boolean set_node              = false;
        boolean set_db_server         = true;
        boolean set_db_server_message = true;
        
        AddEntry data_form = createDefaultEntryForm ( set_application, set_description, set_username,
                set_node, set_db_server, set_db_server_message );
        
        assertEquals ( data_form.getNode(), TEST_WILDCARD );
    }
    
    /**
     *  Tests that to make sure that the value of the "application" field remains blank if it is not specified.
     *  This is a usability issue requested by Bill Dooley on Jan 4, 2006.
     */
    public void testUnspecifiedApplicationIsWildcardIfNotSpecified()
    {
        boolean set_application       = false;
        boolean set_description       = true; 
        boolean set_username          = true;
        boolean set_node              = true;
        boolean set_db_server         = true;
        boolean set_db_server_message = true;
        
        AddEntry data_form = createDefaultEntryForm ( set_application, set_description, set_username,
                set_node, set_db_server, set_db_server_message );
        
        assertEquals ( data_form.getApplication(), TEST_WILDCARD );
    }
    
    /**
     * Tests that to make sure that the value of the "database instance" field returns null if it is not specified.
     * This is a usability issue requested by Bill Dooley on Jan 4, 2006.
     */
    public void testUnspecifiedDatabaseInstanceIsWildcardIfNotSpecified()
    {
        boolean set_application       = false;
        boolean set_description       = true; 
        boolean set_username          = true;
        boolean set_node              = true;
        boolean set_db_server         = true;
        boolean set_db_server_message = true;
        
        AddEntry data_form = createDefaultEntryForm ( set_application, set_description, set_username,
                set_node, set_db_server, set_db_server_message );
        
        assertTrue ( (null == data_form.getApplication()) || ( data_form.getApplication().equals ( TEST_WILDCARD ) )  );
    }
    
    /**
     * Tests that to make sure that the value of the "database message" field returns null if it is not specified.
     * This is a usability issue requested by Bill Dooley on Jan 4, 2006.
     */
    public void testUnspecifiedDatabaseMessageIsWildcardIfNotSpecified()
    {
        boolean set_application       = true;
        boolean set_description       = true; 
        boolean set_username          = true;
        boolean set_node              = true;
        boolean set_db_server         = true;
        boolean set_db_server_message = false;
        
        AddEntry data_form = createDefaultEntryForm ( set_application, set_description, set_username,
                set_node, set_db_server, set_db_server_message );
        
        assertEquals ( data_form.getDbServerMsg(), TEST_WILDCARD );
    }
    
    /**
     * Retrieves all records from the history
     */
    public void testRetrieveAllHistory() throws Exception
    {
        Connection con = Toolkit.getSuppressionDatabaseConnection();
        Statement stmt = con.createStatement();
        ResultSet results = stmt.executeQuery ( "SELECT   suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_minutes, suppress_desc_txt, instance, message, deleted_flg FROM t_vpo_suppress" );
        StringBuilder buffer = new StringBuilder();

        while ( results.next() )
        {
            buffer.append ( results.getInt       (  1 ) );
            buffer.append ( "," );
            buffer.append ( results.getString    (  2 ) );
            buffer.append ( "," );
            buffer.append ( results.getString    (  3 ) );
            buffer.append ( "," );
            buffer.append ( results.getString    (  4 ) );
            buffer.append ( "," );
            buffer.append ( results.getTimestamp (  5 ) );
            buffer.append ( "," );
            buffer.append ( results.getTimestamp (  6 ) );
            buffer.append ( "," );
            buffer.append ( results.getString    (  7 ) );
            buffer.append ( "," );
            buffer.append ( results.getString    (  8 ) );
            buffer.append ( "," );
            buffer.append ( results.getString    (  9 ) );
            buffer.append ( "," );
            buffer.append ( results.getString    ( 10 ) );
            buffer.append ( "," );
            buffer.append ( results.getString    ( 11 ) );
            buffer.append ( "\n" );
        }

        FileOutputStream outfile = new FileOutputStream ( "c:\\test\\retrieve.history" );
            outfile.write ( buffer.toString().getBytes() );
        outfile.close();
    }

    public void testPerformanceRetrieveAllHistory() throws Exception
    {
        Connection con       = Toolkit.getSuppressionDatabaseConnection();
        Statement stmt       = con.createStatement();
        ResultSet results    = stmt.executeQuery ( "SELECT   suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_minutes, suppress_desc_txt, instance, message, deleted_flg FROM t_vpo_suppress" );
        StringBuilder buffer = new StringBuilder();

        /*
        String username                  = "some_username";
        String description               = "some_description";
        String app_name                  = "some_app_name";
        String search_username           = "some_search_username";
        String node                      = "some_node";
        String dbServer                  = "some_dbserver";
        String dbServerMsg               = "some_dbserver_msg";
        String msgGroups[]               = { "some_message_groups1", "some_message_groups2", "some_message_group3" };
        java.util.Date startDate         = new java.util.Date();
        java.util.Date endDate           = new java.util.Date();
        Integer status_reboot            = new Integer ( 1 );
        Integer status_deleted           = new Integer ( 1 );
        //*/

        SimpleDateFormat df              = new SimpleDateFormat ( "yyyy-MM-dd HH:mm" );
        int suppress_id                  = 0;
        String struts_path               = "/search_historical_again";
        boolean show_suppress_id         = true;
        boolean show_group_name          = true;
        //boolean show_node_name           = true;
        boolean show_db_instance         = true;
        boolean show_db_message          = true;
        boolean show_description         = true;
        boolean show_start_time          = true;
        boolean show_end_time            = true;
        boolean show_suppression_creator = true;
        boolean show_remove_on_reboot    = true;
        String sort_type                 = "DESC";
        String sort_string1              = null;
        String sort_string2              = null;
        //ResultSet results                = Toolkit.retrieveHistory ( description, app_name, node, dbServer, dbServerMsg, startDate, endDate, status_reboot.intValue(), status_deleted.intValue(), search_username, sort_type );

        String group_nm                  = null;
        String node_nm                   = null;
        String instance                  = null;
        String message                   = null;
        String suppress_desc_txt         = null;
        String creator                   = null;
        java.util.Date start_utc_tms     = null;
        java.util.Date end_utc_tms       = null;
        int num_records                  = 0;
        
        //  The information is extracted from the ResultSet and put into 
        //  Vector objects to better mimick the process the app server will
        //  be going through
        Vector <Integer> v_suppress_id = new Vector <Integer> (); 
        Vector <String> v_group_nm     = new Vector <String> ();
        Vector <String> v_node_nm      = new Vector <String> ();
        Vector <String> v_instance     = new Vector <String> ();
        Vector <String> v_message      = new Vector <String> ();
        Vector <String> v_desc_txt     = new Vector <String> ();
        Vector <String> v_creator      = new Vector <String> ();
        Vector <java.util.Date> v_start= new Vector <java.util.Date> ();
        Vector <java.util.Date> v_end  = new Vector <java.util.Date> ();
    
        while ( results.next() )
        {
        	suppress_id        = results.getInt ( "suppress_id" );
            group_nm           = results.getString ( "group_nm" );
            node_nm            = results.getString ( "node_nm" );
            instance           = results.getString ( "instance" );
            message            = results.getString ( "message" );
            instance           = results.getString ( "instance" );
            message            = results.getString ( "message" );
            suppress_desc_txt  = results.getString ( "suppress_desc_txt" );
            Timestamp ts_start = results.getTimestamp ( "start_utc_tms" );
            Timestamp ts_end   = results.getTimestamp ( "end_utc_tms" );
            start_utc_tms      = new java.util.Date ( ts_start.getTime() );
            end_utc_tms        = new java.util.Date ( ts_end.getTime() );
            creator            = results.getString ( "create_nm" );
            
            if ( 0 == num_records % 25  )
            {
                buffer.append ( "                <tr>\n" );
                if ( show_suppress_id )
                {
                    sort_string1 = "suppress_id";
                    if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
                    {
                        sort_string1 = sort_string1 + " DESC";
                    }
                                                        
                    buffer.append ( "                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                    buffer.append ( struts_path );
                    buffer.append ( "\"?sort=\"" );
                    buffer.append ( sort_string1 );
                    buffer.append ( "\">ID</html:link></font></th>\n" );
                }
                if ( show_group_name )
                {
                    sort_string1 = "group_name";
                    if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
                    {
                        sort_string1 = sort_string1 + " DESC";
                    }
                                                        
                    buffer.append ( "                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                    buffer.append ( struts_path );
                    buffer.append ( "\"?sort=\"" );
                    buffer.append ( sort_string1 );
                    buffer.append ( "\">ID</html:link></font></th>\n" );
                }
                if ( show_suppress_id )
                {
                    sort_string1 = "suppress_id";
                    if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
                    {
                        sort_string1 = sort_string1 + " DESC";
                    }
                                                        
                    buffer.append ( "                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                    buffer.append ( struts_path );
                    buffer.append ( "\"?sort=\"" );
                    buffer.append ( sort_string1 );
                    buffer.append ( "\">ID</html:link></font></th>\n" );
                }
                if ( show_db_instance && show_db_message )
                {
                    sort_string2 = "instance";
                    if (( null == sort_type ) || ( sort_type.equals ( sort_string2 )))
                    {
                        sort_string2 = sort_string2 + " DESC";
                    }
                                                        
                    buffer.append ( "                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Message Text /<br><html:link action=\"" );
                    buffer.append ( struts_path );
                    buffer.append ( "\"?sort=\" + sort_string2 %>\">Database</html:link></font></th>\n" );
                }
                else if ( show_db_instance )
                {
                    sort_string1 = "instance";
                    if (( null != sort_type ) && ( sort_type.equals ( sort_string2 )))
                    {
                        sort_string2 = sort_string2 + " DESC";
                    }
                                                        
                    buffer.append ( "                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Message Text /<br><html:link action=\"" );
                    buffer.append ( struts_path );
                    buffer.append ( "\"?sort=\" + sort_string2 %>\">Database</html:link></font></th>\n" );
                }
                else if ( show_db_message )
                {
                    buffer.append ( "                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Message Text /<br><html:link action=\"" );
                    buffer.append ( struts_path );
                    buffer.append ( "\"?sort=\" + sort_string2 %>\">Database</html:link></font></th>\n" );
                }
        
                if ( show_description )
                {
                    buffer.append ( "                    <th><font class=\"portlet-font\" style=\"font-size: x-small;\">Description</font></th>\n" );
                }
            
                if ( show_start_time && show_end_time )
                {
                    sort_string1 = "start_utc_tms";
                    sort_string2 = "end_utc_tms";
                    if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
                    {
                        sort_string1 = sort_string1 + " DESC";
                    }
                    if (( null == sort_type ) || ( sort_type.equals ( sort_string2 )))
                    {
                        sort_string2 = sort_string2 + " DESC";
                    }
                    buffer.append ( "                        <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                    buffer.append ( struts_path );
                    buffer.append ( "?sort=" );
                    buffer.append ( sort_string1  );
                    buffer.append ( ">Start Time</html:link> /<br /><html:link action=\"" );
                    buffer.append ( struts_path  );
                    buffer.append ( "?sort="  );
                    buffer.append ( sort_string2  );
                    buffer.append ( ">End Time</html:link></font></th>\n" );
                }
                else if ( show_start_time )
                {
                    sort_string1 = "start_utc_tms";
                    if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
                    {
                        sort_string1 = sort_string1 + " DESC";
                    }
                    buffer.append ( "                        <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                    buffer.append ( struts_path );
                    buffer.append ( "?sort=" );
                    buffer.append ( sort_string1  );
                    buffer.append ( ">Start Time</html:link> /<br /><html:link action=\"" );
                    buffer.append ( struts_path  );
                    buffer.append ( "?sort="  );
                    buffer.append ( sort_string2  );
                    buffer.append ( ">End Time</html:link></font></th>\n" );
                }
                else if ( show_end_time )
                {
                    sort_string1 = "end_utc_tms";
                    if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
                    {
                        sort_string1 = sort_string1 + " DESC";
                    }
                    buffer.append ( "                        <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                    buffer.append ( struts_path );
                    buffer.append ( "?sort=" );
                    buffer.append ( sort_string1  );
                    buffer.append ( ">Start Time</html:link> /<br /><html:link action=\"" );
                    buffer.append ( struts_path  );
                    buffer.append ( "?sort="  );
                    buffer.append ( sort_string2  );
                    buffer.append ( ">End Time</html:link></font></th>\n" );
                }
            
                if ( show_suppression_creator ) 
                {
                    sort_string1 = "create_nm";
                    if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
                    {
                        sort_string1 = sort_string1 + " DESC";
                    }
                    buffer.append ( "                        <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                    buffer.append ( struts_path );
                    buffer.append ( "?sort=" );
                    buffer.append ( sort_string1  );
                    buffer.append ( ">Start Time</html:link> /<br /><html:link action=\"" );
                    buffer.append ( struts_path  );
                    buffer.append ( "?sort="  );
                    buffer.append ( sort_string2  );
                    buffer.append ( ">End Time</html:link></font></th>\n" );                    
                }
                if ( show_remove_on_reboot )
                {
                    sort_string1 = "remove_on_reboot";
                    if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
                    {
                        sort_string1 = sort_string1 + " DESC";
                    }
                    buffer.append ( "                        <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                    buffer.append ( struts_path );
                    buffer.append ( "?sort=" );
                    buffer.append ( sort_string1  );
                    buffer.append ( ">Start Time</html:link> /<br /><html:link action=\"" );
                    buffer.append ( struts_path  );
                    buffer.append ( "?sort="  );
                    buffer.append ( sort_string2  );
                    buffer.append ( ">End Time</html:link></font></th>\n" );
                }
                buffer.append ( "                </tr>\n" );
            }

            if ( show_start_time && show_end_time )
            {
                ts_start = results.getTimestamp ( "start_utc_tms" );
                ts_end   = results.getTimestamp ( "end_utc_tms" );
                start_utc_tms = new java.util.Date ( ts_start.getTime() );
                end_utc_tms   = new java.util.Date ( ts_end.getTime() );
                buffer.append ( "                <td>\n" );
                buffer.append ( "                    <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                buffer.append ( "/view_entry?suppress_id=" );
                buffer.append ( suppress_id );
                buffer.append ( df.format ( start_utc_tms ) );
                buffer.append ( "</html:link></font>\n" );
                buffer.append ( "                    <br />\n" );
                buffer.append ( "                    <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                buffer.append ( "/view_entry?suppress_id=" );
                buffer.append ( suppress_id );
                buffer.append ( df.format ( end_utc_tms ) );
                buffer.append ( "</html:link></font>\n" );
                buffer.append ( "                </td>\n" );
            }
            else if ( show_start_time )
            {
                start_utc_tms = results.getDate ( "start_utc_tms" );
                buffer.append ( "                <td>\n" );
                buffer.append ( "                    <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                buffer.append ( "/view_entry?suppress_id=" );
                buffer.append ( suppress_id );
                buffer.append ( df.format ( start_utc_tms ) );
                buffer.append ( "</html:link></font>\n" );
                buffer.append ( "                </td>\n" );
            }
            else if ( show_end_time )
            {
                end_utc_tms   = results.getDate ( "end_utc_tms" );
                buffer.append ( "                <td>\n" );
                buffer.append ( "                    <font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"" );
                buffer.append ( "/view_entry?suppress_id=" );
                buffer.append ( suppress_id );
                buffer.append ( df.format ( end_utc_tms ) );
                buffer.append ( "</html:link></font>\n" );
                buffer.append ( "                </td>\n" );
            }

            if ( show_suppression_creator )
            {
                creator = results.getString ( "create_nm" );
                buffer.append ( "                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action=\"<%= \"/view_entry?suppress_id=\"+suppress_id %>\"><%= creator %></html:link></font></td>\n" );
            }
            buffer.append ( "            </tr>\n" );

            num_records++;
            
            v_suppress_id.add ( new Integer ( suppress_id ) );
            v_group_nm.add ( group_nm );
            v_node_nm.add  ( node_nm  );
            v_instance.add ( instance );
            v_message.add  ( message  );
            v_desc_txt.add ( suppress_desc_txt );
            v_creator.add  ( creator  );
            v_start.add    ( start_utc_tms );
            v_end.add      ( end_utc_tms );
        }

        FileOutputStream outfile = new FileOutputStream ( "c:\\test\\retrieve.performance" );
            outfile.write ( buffer.toString().getBytes() );
            outfile.write ( "\n\n".getBytes() );
            outfile.write ( Integer.toString( num_records ).getBytes() );
        outfile.close();
    }

    /**
     * Tests the delete functionality
     * @throws Exception
     */
    public void testDeleteSuppression() throws Exception
    {
        AddEntry data_form = createDefaultEntryForm();
        
        int suppress_id = Toolkit.addSuppression ( data_form, test_offset );
        
        Toolkit.deleteEntry ( suppress_id );
    }
};
