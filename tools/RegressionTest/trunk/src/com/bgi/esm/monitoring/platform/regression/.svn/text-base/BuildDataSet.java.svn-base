package com.bgi.esm.monitoring.platform.regression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import com.bgi.esm.monitoring.platform.regression.data.Event;
import com.bgi.esm.monitoring.platform.regression.hibernate.EventHibernateFacade;

/**
 *  Parses the OVO lots for TTI and injects the contents into the database
 */
public class BuildDataSet
{
    public static void main ( String args[] )
    {
        //parseTTILog ( "c:/test/tti/20070615/TTI.log" );
        //parseTTILog ( "c:/test/tti/20070618/TTI.log" );
        //parseTTILog ( "c:/test/tti/20070619/TTI.log" );
        parseTTILog ( "c:/test/tti/20070725/TTI.log" );
    }

    private static String[] retrieveFileContents ( String filename )
    {
        String[] return_value = null;
        try
        {
            FileInputStream infile = new FileInputStream ( filename );
            byte file_contents[]   = new byte[infile.available()];
            infile.read ( file_contents );
            infile.close();

            return (new String ( file_contents )).split ( "\n" );
        }
        catch ( IOException exception )
        {
        }

        return return_value;
    }

    private static String sanitizeTTIEntry ( String entry )
    {
        int index     = entry.indexOf ( ":" );
        String result = entry.substring ( index+1 ).trim();

        return result;
    }

    private static void parseTTILog ( String filename )
    {
        String[] lines        = retrieveFileContents ( filename );
        String current_line   = null;
        Event current_event   = null;
        Vector <Event> events = new Vector <Event> ();

        boolean found_event = false;

        for ( int line_counter = 0; line_counter < lines.length; line_counter++ )
        {
            current_line = lines[line_counter];
           
            if ( false == found_event )
            { 
                if ( current_line.matches ( "---vpo event: .*: start ---" ) )
                {
                    found_event = true;

                    System.out.println ( "Found new event: " + current_line );

                    current_event = new Event();
                }
            }
            else
            {
                if ( current_line.matches ( "---vpo event: .*: end ---" ) )
                {
                    found_event = false;

                    events.add ( current_event );
                    EventHibernateFacade.insertOrUpdate ( current_event );
                    current_event = null;
                }
                else if ( current_line.matches ( "\tmsgid:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setMessageID ( current_line );
                    System.out.println ( "    Message ID: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tnode:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setNodeName ( current_line );
                    System.out.println ( "    Node Name: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tnode type:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setNodeType ( current_line );
                    System.out.println ( "    Node Type: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tnode date:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setNodeDate ( current_line );
                    System.out.println ( "    Node Date: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tnode time:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setNodeTime ( current_line );
                    System.out.println ( "    Node Time: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tmgtsrv date:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setServerDate ( current_line );
                    System.out.println ( "    Mgmt Server Date: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tmgtsvr time:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setServerTime  ( current_line );
                    System.out.println ( "    Mgmt Server Time: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tappl:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setApplication ( current_line );
                    System.out.println ( "    Application: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tmsg_grp:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setMessageGroup ( current_line );
                    System.out.println ( "    Message Group: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tobject:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setObject ( current_line );
                    System.out.println ( "    Object: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tsev:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setSeverity ( current_line );
                    System.out.println ( "    Severity: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\toperators:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setOperators ( current_line );
                    System.out.println ( "    Operators: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tmsg text:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setMessageText ( current_line );
                    System.out.println ( "    Message Text: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tinstr txt:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setInstructions ( current_line );
                    System.out.println ( "    Instructions: " + current_line + " has length " + current_line.length() );
                }
                else if ( current_line.matches ( "\tcma:.*" ) )
                {
                    current_line = sanitizeTTIEntry ( current_line );
                    current_event.setMessageAttributes ( current_line );
                    System.out.println ( "    Message Attributes: " + current_line + " has length " + current_line.length() );
                }
            }
        }

        System.out.println ( "Num events found: " + events.size() );
    }
};
