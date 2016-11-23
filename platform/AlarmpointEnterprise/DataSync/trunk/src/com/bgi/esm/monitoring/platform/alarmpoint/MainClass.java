package com.bgi.esm.monitoring.platform.alarmpoint;

import java.io.IOException;
import java.sql.SQLException;
import com.bgi.esm.monitoring.platform.alarmpoint.SyncCellPhones;
import com.bgi.esm.monitoring.platform.alarmpoint.SyncEmail;
import com.bgi.esm.monitoring.platform.alarmpoint.SyncGroups;
import com.bgi.esm.monitoring.platform.alarmpoint.SyncPeople;
import com.bgi.esm.monitoring.platform.alarmpoint.SyncTeams;
import com.bgi.esm.monitoring.platform.alarmpoint.SyncVoiceDevices;
import org.apache.log4j.Logger;

public class MainClass
{
    final private static Logger _log = Logger.getLogger ( "Alarmpoint DataSync" );

    public static void main ( String args[] ) throws SQLException, IOException, ClassNotFoundException, InterruptedException
    {
        long timestamp_start = System.currentTimeMillis();
        _log.info ( "Starting data sync operation" );
        syncPeople();

        syncEmail();
        syncCellPhones();
        syncVoiceDevices();

        //  Requested by Kris to *NOT* update groups and teams
        /*
        syncGroups();
        syncTeams();
        //*/
        long timestamp_end = System.currentTimeMillis();

        _log.info ( "Total time to retrieve information from Active Directory: " + (timestamp_end-timestamp_start) + " ms" );

        Runtime rt = Runtime.getRuntime();

        String command = "datasync.bat -f examples\\config\\alarmpoint-import.xml -p common\\common.properties > out";

        _log.info ( "Executing Alarmpoint DataSync command..." );
        timestamp_start = System.currentTimeMillis();
        rt.exec( command ).waitFor();
        timestamp_end = System.currentTimeMillis();
        _log.info ( "Total time to execute DataSync: " + (timestamp_end-timestamp_start) + " ms" );
        _log.info ( "Finished executing Alarmpoint datasync command" );
    }

    private static void syncPeople() throws SQLException, IOException, ClassNotFoundException
    {
        long timestamp_start = System.currentTimeMillis();

        _log.info ( "Loading employee information from ShareIT into Alarmpoint" );

        SyncPeople.synchronizeDatabase();

        long timestamp_end = System.currentTimeMillis();

        _log.info ( "Total time: " + (timestamp_end-timestamp_start) + " ms" );
    }

    private static void syncEmail() throws SQLException, IOException, ClassNotFoundException
    {
        long timestamp_start = System.currentTimeMillis();

        _log.info ( "Loading employee email addresses from ShareIT into Alarmpoint" );

        SyncEmail.synchronizeDatabase();

        long timestamp_end = System.currentTimeMillis();

        _log.info ( "Time to update email: " + (timestamp_end-timestamp_start) + " ms" );
    }

    private static void syncCellPhones() throws SQLException, IOException, ClassNotFoundException
    {
        long timestamp_start = System.currentTimeMillis();

        _log.info ( "Loading employee mobile phones from ShareIT into Alarmpoint" );

        SyncCellPhones.synchronizeDatabase();

        long timestamp_end = System.currentTimeMillis();

        _log.info ( "Time to update cell phone information: " + (timestamp_end-timestamp_start) + " ms" );
    }

    private static void syncVoiceDevices() throws SQLException, IOException, ClassNotFoundException
    {
        long timestamp_start = System.currentTimeMillis();

        _log.info ( "Loading employee office phones from ShareIT into Alarmpoint" );

        SyncVoiceDevices.synchronizeDatabase();

        long timestamp_end = System.currentTimeMillis();

        _log.info ( "Time to update phone information: " + (timestamp_end-timestamp_start) + " ms" );
    }

    private static void syncGroups() throws SQLException, IOException, ClassNotFoundException
    {
        long timestamp_start = System.currentTimeMillis();

        _log.info ( "Loading group information from Alarmpoint Server 6" );

        SyncGroups.synchronizeDatabase();

        long timestamp_end = System.currentTimeMillis();

        _log.info ( "Time to update group information: " + (timestamp_end-timestamp_start) + " ms" );
    }

    private static void syncTeams() throws SQLException, IOException, ClassNotFoundException
    {
        long timestamp_start = System.currentTimeMillis();

        _log.info ( "Loading team information from Alarmpoint Server 6" );

        SyncTeams.synchronizeDatabase();

        long timestamp_end = System.currentTimeMillis();

        _log.info ( "Time to update team information: " + (timestamp_end-timestamp_start) + " ms" );
    }
};
