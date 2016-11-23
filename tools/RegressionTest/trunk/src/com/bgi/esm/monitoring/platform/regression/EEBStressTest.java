package com.bgi.esm.monitoring.platform.regression;

import java.io.InputStream;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.regression.data.Event;
import com.bgi.esm.monitoring.platform.regression.hibernate.EventHibernateFacade;

public class EEBStressTest
{
    final private static Logger _log = Logger.getLogger ( EEBStressTest.class );

    public static void main ( String args[] )
    {
        int num_records = (int) EventHibernateFacade.count();

        Runtime rt = Runtime.getRuntime();
        byte contents[] = new byte[1024];

        long timestamp_start = 0L;
        long timestamp_end   = 0L;

        for ( int counter = 1; counter <= num_records; counter++ )
        {
            timestamp_start = System.currentTimeMillis();
            // ARG[0] = Message ID
            // ARG[1] = Node Name
            // ARG[2] = Node Type
            // ARG[3] = Creation Date
            // ARG[4] = Creation Time
            // ARG[5] = Receive Date
            // ARG[6] = Receive Time
            // ARG[7] = Application
            // ARG[8] = Message Group
            // ARG[9] = Object
            // ARG[10] = Severity
            // ARG[11] = Operators
            // ARG[12] = Message
            // ARG[13] = Instruction
            // ARG[14] = Message Attributes
            // ARG[15] = Suppression Count
    
            _log.info ( "Timestamp: " + new java.util.Date() );
            Event event = EventHibernateFacade.select ( counter );
            StringBuilder command = new StringBuilder();
            command.append ( "c:/development/esm-platform/Tools/RegressionTest/trunk/src/" );
            command.append ( "OpenviewTTIClient.exe " );
            command.append ( "\"" );
            command.append ( event.getMessageID()         );
            command.append ( "\" \"" );
            command.append ( event.getNodeName()          );
            command.append ( "\" \"" );
            command.append ( event.getNodeType()          );
            command.append ( "\" \"" );
            command.append ( event.getNodeDate()          );
            command.append ( "\" \"" );
            command.append ( event.getNodeTime()          );
            command.append ( "\" \"" );
            command.append ( event.getServerDate()        );
            command.append ( "\" \"" );
            command.append ( event.getServerTime()        );
            command.append ( "\" \"" );
            command.append ( event.getApplication()       );
            command.append ( "\" \"" );
            command.append ( event.getMessageGroup()      );
            command.append ( "\" \"" );
            command.append ( event.getObject()            );
            command.append ( "\" \"" );
            command.append ( event.getSeverity()          );
            command.append ( "\" \"" );
            command.append ( event.getInstructions()      );
            command.append ( "\" \"" );
            command.append ( "\" \"" );
            command.append ( event.getMessageAttributes() );
            command.append ( "\" \"" );
            command.append ( 0 );
            command.append ( "\"" );
            _log.info ( "Command: " + command.toString() );

            try
            {
                Process p = rt.exec ( command.toString() );
                InputStream is =  p.getInputStream();
                int available = is.read ( contents );
                _log.info ( "Command output: " + (new String ( contents ) ) );
                p.waitFor();
            }
            catch ( Exception exception )
            {
                _log.error ( "Exception detected", exception );
            }


        }
    }
};
