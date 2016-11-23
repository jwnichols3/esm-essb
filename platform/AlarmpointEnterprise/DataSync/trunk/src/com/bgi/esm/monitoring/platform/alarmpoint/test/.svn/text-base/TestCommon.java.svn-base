package com.bgi.esm.monitoring.platform.alarmpoint.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.alarmpoint.Common;

public class TestCommon extends TestCase
{
    public TestCommon ( String param )
    {
        super ( param );
    }

    public void testSourceDatabaseConnection() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con = Common.getSourceConnection();

        assertNotNull ( con );
    }

    public void testDestDatabaseConnection() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con = Common.getDestConnection();

        assertNotNull ( con );
    }

    public void testClearStagingTables() throws SQLException, ClassNotFoundException, IOException
    {
        Common.clearStagingTables();
    }

    /*
    public void testRetrieveDatabaseTables() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con       = Common.getSourceConnection();
        DatabaseMetaData dmd = con.getMetaData();
        String[] types       = {"TABLE"};
        ResultSet results    = dmd.getTables ( null, null, "%", types );

        while ( results.next() )
        {
            System.out.println ( "Found table: " + results.getString ( 3 ) );
        }
    }
    //*/
    //

    public void testRetrievePeopleInformation() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con    = Common.getDestConnection();
        Statement stmt    = con.createStatement();

        ResultSet results = Common.retrievePeople();

        Common.queryDump ( results, "people.out" );
    }


    public void testExistTable_DSUsers() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con    = Common.getDestConnection();
        Statement stmt    = con.createStatement();

        ResultSet results = stmt.executeQuery ( "SELECT TOP 10 * FROM " + Common.getDestDatabaseOwner() + "ds_users" );

        stmt.close();
    }

    public void testExistTable_DSEmailDevices() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con    = Common.getDestConnection();
        Statement stmt    = con.createStatement();

        ResultSet results = stmt.executeQuery ( "SELECT TOP 10 * FROM " + Common.getDestDatabaseOwner() + "ds_email_devices" );

        stmt.close();
    }
    public void testExistTable_DSVoiceDevices() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con    = Common.getDestConnection();
        Statement stmt    = con.createStatement();

        ResultSet results = stmt.executeQuery ( "SELECT TOP 10 * FROM " + Common.getDestDatabaseOwner() + "ds_voice_devices" );

        stmt.close();
    }

    public void testRetrievePeople() throws SQLException, ClassNotFoundException, IOException
    {
        ResultSet results = Common.retrievePeople();

        assertNotNull ( results );
    }

    public void testRetrieveAppPeople() throws SQLException, ClassNotFoundException, IOException
    {
        ResultSet results = Common.retrieveAppPeople();

        assertNotNull ( results );
    }
};
