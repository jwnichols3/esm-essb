package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 *  Retrieves Peregrine/ServiceCenter ticket information from the web interface
 *
 *  @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ExtractPeregrineTicket
{
    final private static String url_base    = "http://helpme/getservices/view_specific.cfm?number=";  //IM00943450
    private static String table_data_regex  = "<td.*>.*<\\/td>";
    private static PatternCompiler compiler = new Perl5Compiler();
    private static PatternMatcher matcher   = new Perl5Matcher();
    private static Pattern pattern          = null;

    private String ticket_num               = null;
    private String page_contents            = null;
    private String pt_ticket_logged_for     = null;
    private String pt_contact_info          = null;
    private String pt_pc_name               = null;
    private String pt_description           = null;
    private String pt_status                = null;
    private String pt_date                  = null;
    private String pt_ticket_opened_by      = null;
    private String pt_assigned_group        = null;
    private String pt_assigned_person       = null;
    private String pt_actions               = null;

    public static void main ( String args[] ) throws IOException, MalformedURLException, MalformedPatternException
    {
        ExtractPeregrineTicket ticket = new ExtractPeregrineTicket ( "IM00943450" );
    }

    public ExtractPeregrineTicket ( String peregrine_ticket_num ) throws IOException, MalformedURLException, MalformedPatternException
    {
        ticket_num = peregrine_ticket_num;

        retrieveTicketInfo();
    }

    private void retrieveTicketInfo() throws IOException, MalformedURLException, MalformedPatternException
    {
        URL url                       = new URL ( url_base + ticket_num );
        InputStream input_stream      = url.openStream();
        DataInputStream data_instream = new DataInputStream ( input_stream );
        BufferedReader reader         = new BufferedReader ( new InputStreamReader ( data_instream ) );
        String temp_string            = null;

        StringBuilder contents        = new StringBuilder();
        while ( null != ( temp_string = reader.readLine() ) )
        {
            contents.append ( temp_string );
            contents.append ( "\n" );
        }

        page_contents = contents.toString();

        String search_string = "<!--The Page Items Should go here -->";

        int index     = page_contents.indexOf ( search_string );
        page_contents = page_contents.substring ( index+search_string.length() + 1 );

        search_string = "<!--Include page Footer-->";
        index         = page_contents.indexOf ( search_string );
        page_contents = page_contents.substring ( 0, index );

        Vector <String> match_results = new Vector <String> ();
        PatternMatcherInput input     = new PatternMatcherInput ( page_contents );
        MatchResult match_result      = null;
        pattern                       = compiler.compile ( table_data_regex );

        while ( matcher.contains ( input, pattern ) )
        {
            match_result = matcher.getMatch();

            temp_string = match_result.toString();
            index       = temp_string.indexOf ( ">" );
            temp_string = temp_string.substring ( index );
            temp_string = temp_string.substring ( 1, temp_string.length() - 5 );

            match_results.add ( temp_string );
        }

        pt_pc_name          = match_results.elementAt (  4 );
        pt_status           = match_results.elementAt (  7 );
        pt_ticket_opened_by = match_results.elementAt ( 10 );
        pt_actions          = match_results.elementAt ( 14 );

        FileOutputStream outfile = new FileOutputStream ( "c:\\test\\peregrine.out" );
            outfile.write ( page_contents.getBytes() );
        outfile.close();
    }

    public String getSystemName ()
    {
        return pt_pc_name;
    }

    public String getStatus()
    {
        return pt_status;
    }

    public String getTicketOpenedBy()
    {
        return pt_ticket_opened_by;
    }
    
    public String getActions()
    {
        return pt_actions;
    }
};
