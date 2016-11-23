package com.bgi.esm.portlets.Suppression.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.test.CommonTestCase;

public class TestFailovers extends CommonTestCase
{
    @SuppressWarnings("unused")	private static final String TEST_DESCRIPTION   = "Test Description";
    @SuppressWarnings("unused")	private static final String TEST_APPLICATION   = "Test Application";
    @SuppressWarnings("unused")	private static final String TEST_USERNAME      = "somebody";
    @SuppressWarnings("unused")	private static final String TEST_HOSTNAME      = "test_node";
    @SuppressWarnings("unused")	private static final String TEST_WILDCARD      = "-";
    @SuppressWarnings("unused")	private static final String TEST_DB_SERVER     = "Test DB Server";
    @SuppressWarnings("unused")	private static final String TEST_DB_SERVER_MSG = "Test DB Server Message";

    public TestFailovers ( String param )
    {
        super ( param );
    }
    
    private void closeConnection ( Connection con )
    {
    	try
    	{
    		if (( null != con ) && ( !con.isClosed() ) )
    		{
    			con.close();
    		}
    	}
    	catch ( SQLException exception )
    	{
    		exception.printStackTrace();
    	}
    }
    

    /**
     * Reads in the default properties for this portlet and modifies them to generate valid/invalid sets 
     * of properties for testing purposes.  Note: the original set of properties must be valid in order for
     * all the unit tests to pass
     * 
     * @param use_valid_sup_props whether or not to keep the suppression database connection properties valid
     * @param use_valid_sup_backup_props whether or not to keep the suppression database connection backup properties valid
     * @param use_valid_vpo_props whether or not to keep the data source database connection properties valid
     * @param use_valid_vpo_backup_props whether or not to keep the data source database connection backup properties valid
     * @param use_valid_inst_props whether or not to keep the instance database connection properties valid
     * @param use_valid_inst_backup_props whether or not to keep the instance database connection backup properties valid
     * 
     * @return the modified java.util.Properties object to be fed into Toolkit.setCommonProperties ( Properties )
     */
    public Properties generateTestProperties ( boolean use_valid_sup_props, boolean use_valid_sup_backup_props, 
            boolean use_valid_vpo_props, boolean use_valid_vpo_backup_props,
            boolean use_valid_inst_props, boolean use_valid_inst_backup_props )
            throws IOException
    {
        //  Retrieve the default properties
        Properties common_properties = readCommonProperties();

        if ( false == use_valid_sup_props )
        {
            common_properties.setProperty ( "suppression.suppressions.database.username", "bad-username" );
            common_properties.setProperty ( "suppression.suppressions.database.password", "bad-password" );
        }
        
        if ( false == use_valid_sup_backup_props )
        {
            common_properties.setProperty ( "suppression.suppressions.backup.database.username", "bad-username" );
            common_properties.setProperty ( "suppression.suppressions.backup.database.password", "bad-password" );
        }
        
        if ( false == use_valid_vpo_props )
        {
            common_properties.setProperty ( "suppression.data_source.database.username", "bad-username" );
            common_properties.setProperty ( "suppression.data_source.database.password", "bad-password" );
        }
        
        if ( false == use_valid_vpo_backup_props )
        {
            common_properties.setProperty ( "suppression.data_source.backup.database.username", "bad-username" );
            common_properties.setProperty ( "suppression.data_source.backup.database.password", "bad-password" );
        }
        
        if ( false == use_valid_inst_props )
        {
            common_properties.setProperty ( "suppression.inst.database.username", "bad-username" );
            common_properties.setProperty ( "suppression.inst.database.password", "bad-password" ); 
        }
        
        if ( false == use_valid_inst_backup_props )
        {
            common_properties.setProperty ( "suppression.inst.backup.database.username", "bad-username" );
            common_properties.setProperty ( "suppression.inst.backup.database.password", "bad-password" );  
        }
           
        return common_properties;
    }

    public void testDatabaseConnectionProperties() throws Exception
    {
        Properties common_props = readCommonProperties();
        Properties test_props   = generateTestProperties ( true, true, true, true, true, true );

        Toolkit.setCommonProperties ( test_props );

        Toolkit.setCommonProperties ( common_props );
    }

    public void testDatabaseConnections_Suppressions_Default() throws Exception
    {
        Properties common_properties = readCommonProperties();

        Toolkit.setCommonProperties ( common_properties );

        Connection con = Toolkit.getSuppressionDatabaseConnection();
        assertNotNull ( con );
    }

    public void testDatabaseConnections_Suppressions_Primary() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, true, true );
        
        Toolkit.setCommonProperties ( props );
        
        Connection con = Toolkit.getSuppressionDatabaseConnection();
        
        assertNotNull ( con );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        
        closeConnection ( con );
    }
    
    public void testDatabaseConnections_Suppressions_Backup() throws Exception
    {
        Properties props = generateTestProperties ( false, true, true, true, true, true );
        
        Toolkit.setCommonProperties ( props );
        
        Connection con = Toolkit.getSuppressionDatabaseConnection(); 
        
        assertNotNull ( con );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        
        closeConnection ( con );
    }
    
    public void testDatabaseConnections_Suppressions_PrimaryIfBCPUnavailable() throws Exception
    {
    	Properties props = generateTestProperties ( true, false, true, true, true, true );
        
        Toolkit.setCommonProperties ( props );
        
        Connection con = Toolkit.getSuppressionDatabaseConnection(); 
        
        assertNotNull ( con );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        
        closeConnection ( con );
    }

    /**
     * Tests the connection to the default instance of the instance database
     * 
     * @throws Exception
     */
    public void testDatabaseConnections_DataSource_Default() throws Exception
    {
        Properties common_properties = readCommonProperties();

        Toolkit.setCommonProperties ( common_properties );

        assertNotNull ( Toolkit.getDataSourceDatabaseConnection() );
    }
    
    /**
     * Tests the connection to the primary instance of the VPO database
     * 
     * @throws Exception
     */
    public void testDatabaseConnections_DataSource_Primary() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, true, true );
        
        Toolkit.setCommonProperties ( props );
        
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        
        assertNotNull ( con_data_source1 );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        
        closeConnection ( con_data_source1 );
    }
    
    /**
     * Tests the connection to the BCP instance of the VPO database
     * 
     * @throws Exception
     */
    public void testDatabaseConnections_DataSource_Backup() throws Exception
    {
        Properties props = generateTestProperties ( true, true, false, true, true, true );
        
        Toolkit.setCommonProperties ( props );
        
        assertNotNull ( Toolkit.getDataSourceDatabaseConnection() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
    }
    
    /**
     * Tests to confirm that the connection to the primary instance of the VPO database is maintained 
     * even if the BCP connection is unavailable
     * 
     * @throws Exception
     */
    public void testDatabaseConnections_DataSource_PrimaryIfBCPUnavailable() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, false, true, true );
        
        Toolkit.setCommonProperties ( props );
        
        Connection con = Toolkit.getDataSourceDatabaseConnection(); 
        
        assertNotNull ( con );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        
        closeConnection ( con );
    }
    
    
    /**
     * Tests the connection to the primary instance of the instance database
     * 
     * @throws Exception
     */
    public void testDatabaseConnections_Instance_Primary() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, true, true );
        
        Toolkit.setCommonProperties ( props );
        
        Connection con_data_source1 = Toolkit.getInstanceDatabaseConnection();
        
        assertNotNull ( con_data_source1 );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_data_source1 );
    }
    
    /**
     * Tests the connection to the BCP instance of the instance database
     * 
     * @throws Exception
     */
    public void testDatabaseConnections_Instance_Backup() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, false, true );
        
        Toolkit.setCommonProperties ( props );
        
        assertNotNull ( Toolkit.getInstanceDatabaseConnection() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
    }
    
    /**
     * Tests to confirm that the connection to the primary instance of the instance database is maintained
     * even if the BCP connection is unavailable
     * 
     * @throws Exception
     */
    public void testDatabaseConenctions_Instance_PrimaryIfBCPUnavailable() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, false );
        
        Toolkit.setCommonProperties ( props );
        
        Connection con = Toolkit.getInstanceDatabaseConnection(); 
        
        assertNotNull ( con );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con );
    }

    public void testDatabaseConnections_DefaultAllToBCP() throws Exception
    {
        Properties props = generateTestProperties ( false, true, false, true, false, true );
        
        Toolkit.setCommonProperties ( props );
        
        assertNotNull ( Toolkit.getSuppressionDatabaseConnection() );
        assertNotNull ( Toolkit.getInstanceDatabaseConnection()    );
        assertNotNull ( Toolkit.getDataSourceDatabaseStatement()   );
        
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP == Toolkit.getDatabaseConnectionStateInstance() );
    }
    
    public void testDatabaseConnections_DefaultSuppressionsToBCP() throws Exception
    {
        Properties props = generateTestProperties ( false, true, true, true, true, true );
        
        Toolkit.setCommonProperties ( props );

        
        assertNotNull ( Toolkit.getSuppressionDatabaseConnection() );
        assertNotNull ( Toolkit.getInstanceDatabaseConnection()    );
        assertNotNull ( Toolkit.getDataSourceDatabaseStatement()   );
        
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
    }
    
    public void testDatabaseConnections_DefaultDataSourceToBCP() throws Exception
    {
        Properties props = generateTestProperties ( true, true, false, true, true, true );
        
        Toolkit.setCommonProperties ( props );
        
        assertNotNull ( Toolkit.getSuppressionDatabaseConnection() );
        assertNotNull ( Toolkit.getInstanceDatabaseConnection()    );
        assertNotNull ( Toolkit.getDataSourceDatabaseStatement()   );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
    }
    
    public void testDatabaseConnections_DefaultInstanceToBCP() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, false, true );
        
        Toolkit.setCommonProperties ( props );
        
        assertNotNull ( Toolkit.getSuppressionDatabaseConnection() );
        assertNotNull ( Toolkit.getInstanceDatabaseConnection()    );
        assertNotNull ( Toolkit.getDataSourceDatabaseStatement()   );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
    }
    
    public void testDatabaseConnections_FailoverToBCP_Suppressions() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );

        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
        
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource()   );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance()     );
        
        props = generateTestProperties ( false, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );
        
        con_suppression1.close();
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( con_suppression1 != con_suppression2 );
        assertTrue ( con_data_source1 != con_data_source2 );
        assertTrue ( con_instance1    != con_instance2    );
        
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
    }
    
    public void testDatabaseConnections_FailoverToBCP_DataSource() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );

        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
        
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource()   );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance()     );
        
        props = generateTestProperties ( true, true, false, true, true, true );      
        Toolkit.setCommonProperties ( props );
        
        con_data_source1.close();
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( con_suppression1 != con_suppression2 );
        assertTrue ( con_data_source1 != con_data_source2 );
        assertTrue ( con_instance1    != con_instance2    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
    }
    
    public void testDatabaseConnections_FailoverToBCP_Instance() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );

        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
        
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource()   );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance()     );
        
        props = generateTestProperties ( true, true, true, true, false, true );      
        Toolkit.setCommonProperties ( props );
        
        con_instance1.close();
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
    }

    /**
     * Tests failover to and recovery from BCP for the VPO database
     * 
     * @throws Exception
     */
    public void testDatabaseConnectionsFailoverToBCPAndRecover_Suppressions() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );

        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
        
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource()   );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance()     );
        
        props = generateTestProperties ( false, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );
        
        con_suppression1.close();
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );

        props = generateTestProperties ( true, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );

        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
    }

    public void testDatabaseConnectionsFailoverToBCPConnectAndRecover_Suppressions() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );

        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
        
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource()   );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance()     );
        
        props = generateTestProperties ( false, true, true, true, true, true );     
        Toolkit.setCommonProperties ( props );
        
        con_suppression1.close();
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( con_suppression1 != con_suppression2 );
        assertTrue ( con_data_source1 != con_data_source2 );
        assertTrue ( con_instance1    != con_instance2    );
        
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );

        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );

        props = generateTestProperties ( true, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );

        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
    }

    /**
     * Tests retrieving two consecutive connections from the suppressions database while in BCP mode and another connection after recovering
     * 
     * @throws Exception
     */
    public void testDatabaseConnectionsFailoverToBCPConnectCloseAndRecover_Suppressions() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
        
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource()   );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance()     );
        
        props = generateTestProperties ( false, true, true, true, true, true );     
        Toolkit.setCommonProperties ( props );
        
        con_suppression1.close();
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( con_suppression1 != con_suppression2 );
        assertTrue ( con_data_source1 != con_data_source2 );
        assertTrue ( con_instance1    != con_instance2    );

        con_suppression2.close();
        
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );

        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( con_suppression3 != con_suppression2 );
        assertTrue ( con_data_source3 == con_data_source2 );
        assertTrue ( con_instance3    == con_instance2    );
        
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );

        props = generateTestProperties ( true, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );

        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
    }


    
    /**
     * Tests failover to and recovery from BCP for the VPO database
     * 
     * @throws Exception
     */
    public void testDatabaseConnectionsFailoverToBCPAndRecover_DataSource() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );

        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
        
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource()   );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance()     );
        
        props = generateTestProperties ( true, true, false, true, true, true );      
        Toolkit.setCommonProperties ( props );
        
        con_suppression1.close();
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );

        props = generateTestProperties ( true, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );

        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
    }

    /**
     * Tests retrieving two consecutive connections from the VPO database while in BCP mode
     *  
     * @throws Exception
     */
    public void testDatabaseConnectionsFailoverToBCPConnectAndRecover_DataSource() throws Exception
    {
        Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
        
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource()   );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance()     );
        
        props = generateTestProperties ( true, true, false, true, true, true );     
        Toolkit.setCommonProperties ( props );
        
        con_suppression1.close();
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( con_suppression1 != con_suppression2 );
        assertTrue ( con_data_source1 != con_data_source2 );
        assertTrue ( con_instance1    != con_instance2    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );

        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );

        props = generateTestProperties ( true, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );

        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
    }

    /**
     * Tests retrieving two consecutive connections from the VPO database while in BCP mode and
     * another connection after recovering
     *  
     * @throws Exception
     */
    public void testDatabaseConnectionsFailoverToBCPConnectCloseAndRecover_DataSource() throws Exception
    {
        Properties props = generateTestProperties ( true, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
        
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP == Toolkit.getDatabaseConnectionStateDataSource()   );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance()     );
        
        props = generateTestProperties ( true, true, false, true, true, true );     
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( con_suppression1 != con_suppression2 );
        assertTrue ( con_data_source1 != con_data_source2 );
        assertTrue ( con_instance1    != con_instance2    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( con_suppression3 == con_suppression2 );
        assertTrue ( con_data_source3 != con_data_source2 );
        assertTrue ( con_instance3    == con_instance2    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );

        props = generateTestProperties ( true, true, true, true, true, true );      
        Toolkit.setCommonProperties ( props );

        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
    }
    
    /**
     * Tests recovering from a failure of the suppressions database while experiencing a failure with the VPO database
     * @throws Exception
     */
    public void testAlternateFailoverAndRecover_Suppressions_DataSource() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    
    /**
     * Tests recovering from a failure of the suppressions database while experiencing a failure with the instance database
     * @throws Exception
     */
    public void testAlternateFailoverAndRecover_Suppressions_Instance() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    /**
     * Tests recovering from a failure of the VPO  database while experiencing a failure with the suppressions database
     * @throws Exception
     */
    public void testAlternateFailoverAndRecover_DataSource_Suppressions() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    /**
     * Tests recovering from a failure of the VPO  database while experiencing a failure with the instance database
     * @throws Exception
     */
    public void testAlternateFailoverAndRecover_DataSource_Instance() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    /**
     * Tests recovering from a failure of the instance database while experiencing a failure with the suppressions database
     * @throws Exception
     */
    public void testAlternateFailoverAndRecover_Instance_Suppressions() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    
    /**
     * Tests recovering from a failure of the instance database while experiencing a failure with the VPO database
     * @throws Exception
     */
    public void testAlternateFailoverAndRecover_Instance_DataSource() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }

    /**
     * Tests the a failure of the suppressions database followed by another failure with the suppressions database
     * @throws Exception
     */
    public void testConsecutiveFailoverAndRecover_Suppressions_Suppressions() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    /**
     * Tests the a failure of the suppressions database followed by another failure with the VPO database
     * @throws Exception
     */
    public void testConsecutiveFailoverAndRecover_Suppressions_DataSource() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    /**
     * Tests the a failure of the suppressions database followed by another failure with the instance database
     * @throws Exception
     */
    public void testConsecutiveFailoverAndRecover_Suppressions_Instance() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    /**
     * Tests the a failure of the VPO database followed by another failure with the suppressions database
     * @throws Exception
     */
    public void testConsecutiveFailoverAndRecover_DataSource_Suppressions() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    /**
     * Tests the a failure of the VPO database followed by another failure with the VPO database
     * @throws Exception
     */
    public void testConsecutiveFailoverAndRecover_DataSource_DataSource() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    /**
     * Tests the a failure of the VPO database followed by another failure with the instance database
     * @throws Exception
     */
    public void testConsecutiveFailoverAndRecover_DataSource_Instance() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    /**
     * Tests the a failure of the instance database followed by another failure with the suppressions database
     * @throws Exception
     */
    public void testConsecutiveFailoverAndRecover_Instance_Suppressions() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( false, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    /**
     * Tests the a failure of the instance database followed by another failure with the VPO database
     * @throws Exception
     */
    public void testConsecutiveFailoverAndRecover_Instance_DataSource() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, false, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
    
    /**
     * Tests the a failure of the instance database followed by another failure with the instance database
     * @throws Exception
     */
    public void testConsecutiveFailoverAndRecover_Instance_Instance() throws Exception
    {
    	Properties props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression1 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source1 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance1    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression1 );
        assertNotNull ( con_data_source1 );
        assertNotNull ( con_instance1    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression2 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source2 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance2    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression2 );
        assertNotNull ( con_data_source2 );
        assertNotNull ( con_instance2    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression3 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source3 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance3    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression3 );
        assertNotNull ( con_data_source3 );
        assertNotNull ( con_instance3    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, false, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression4 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source4 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance4    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression4 );
        assertNotNull ( con_data_source4 );
        assertNotNull ( con_instance4    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_BACKUP  == Toolkit.getDatabaseConnectionStateInstance() );
        
        
        props = generateTestProperties ( true, true, true, true, true, true );
        Toolkit.setCommonProperties ( props );
        
        Connection con_suppression5 = Toolkit.getSuppressionDatabaseConnection();
        Connection con_data_source5 = Toolkit.getDataSourceDatabaseConnection();
        Connection con_instance5    = Toolkit.getInstanceDatabaseConnection();
     
        assertNotNull ( con_suppression5 );
        assertNotNull ( con_data_source5 );
        assertNotNull ( con_instance5    );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateSuppressions() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateDataSource() );
        assertTrue ( Toolkit.DATABASE_MODE_PRIMARY == Toolkit.getDatabaseConnectionStateInstance() );
        
        closeConnection ( con_suppression1 );
        closeConnection ( con_data_source1 );
        closeConnection ( con_instance1    );
        
        closeConnection ( con_suppression2 );
        closeConnection ( con_data_source2 );
        closeConnection ( con_instance2    );
        
        closeConnection ( con_suppression3 );
        closeConnection ( con_data_source3 );
        closeConnection ( con_instance3    );
        
        closeConnection ( con_suppression4 );
        closeConnection ( con_data_source4 );
        closeConnection ( con_instance4    );
        
        closeConnection ( con_suppression5 );
        closeConnection ( con_data_source5 );
        closeConnection ( con_instance5    );
    }
}
