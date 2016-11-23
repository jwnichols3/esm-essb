package com.bgi.esm.monitoring.portlets.Openview2EEBBridge.actions;

import java.lang.ref.WeakReference;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionMessage;
import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;
import com.bgi.esm.monitoring.portlets.Openview2EEBBridge.forms.TTIEventForm;
import com.bgi.esm.monitoring.portlets.Openview2EEBBridge.Toolkit;

public class EventsFromTTI extends DispatchAction
{
    final private static Logger _log = Logger.getLogger ( EventsFromTTI.class );
    final private static BackEndFacade bef = new BackEndFacade();

    public ActionForward retrieveMessage ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "EventsFromTTI::retrieveMessage()" );

        HashMap <String, String> params = Toolkit.retrieveHttpRequestParameters ( request );

        String message_id     = params.get ( "MessageId"     );
        String node_name      = params.get ( "NodeName"      );
        String node_type      = params.get ( "NodeType"      );
        String creation_date  = params.get ( "CreationDate"  );
        String creation_time  = params.get ( "CreationTime"  );
        String receive_date   = params.get ( "ReceiveDate"   );
        String receive_time   = params.get ( "ReceiveTime"   );
        String application    = params.get ( "Application"   );
        String message_group  = params.get ( "MessageGroup"  );
        String object         = params.get ( "Object"        );
        String severity       = params.get ( "Severity"      );
        String operators      = params.get ( "Operators"     );
        String message        = params.get ( "Message"       );
        String instruction    = params.get ( "Instruction"   );
        String attributes     = params.get ( "Attributes"    );
        String suppress_count = params.get ( "SuppressCount" );

        //  Parse the message attributes
        HashMap <String, String> message_attributes = new HashMap <String, String> ();
        if ( null != attributes )
        {
            _log.info ( "Found custom message attributes: " + attributes );

            String str_message_attributes[] = attributes.split ( ";;" );
            message_attributes = new HashMap <String, String> ();
            for ( int counter = 0; counter < str_message_attributes.length; counter++ )
            {
                String key_value[] = str_message_attributes[counter].split ( "=" );

                if ( key_value.length == 2 )
                {
                    if ( _log.isInfoEnabled() )
                    {
                        WeakReference <StringBuilder> log_entry = new WeakReference <StringBuilder> ( new StringBuilder() );
                        log_entry.get().append ( "Found custom message attribute ( key=" );
                        log_entry.get().append ( key_value[0] );
                        log_entry.get().append ( ", value=" );
                        log_entry.get().append ( key_value[1] );
                        log_entry.get().append ( " )" );

                        _log.info ( log_entry.get().toString() );
                    }

                    message_attributes.put ( key_value[0], key_value[1] );
                }
            }
        }

        Vector <String> list_operators = new Vector <String> ();
        if ( null != operators )
        {
            _log.info ( "Found operators: " + attributes );

            String str_operators[] = operators.split ( " " );
            list_operators = new Vector <String> ();
            for ( int counter = 0; counter < str_operators.length; counter++ )
            {
                if (( null != str_operators[counter] ) && ( str_operators[counter].length() > 0 ))
                {
                    list_operators.add ( str_operators[counter] );
                }
            }
        }

        TTIEventForm data_form = (TTIEventForm) form;
        data_form.setMessageID         ( message_id     );
        data_form.setNodeName          ( node_name      );
        data_form.setNodeType          ( node_type      );
        data_form.setCreationDate      ( creation_date  );
        data_form.setCreationTime      ( creation_time  );
        data_form.setReceiveDate       ( receive_date   );
        data_form.setReceiveTime       ( receive_time   );
        data_form.setApplication       ( application    );
        data_form.setMessageGroup      ( message_group  );
        data_form.setObject            ( object         );
        data_form.setSeverity          ( severity       );
        data_form.setOperators         ( operators      );
        data_form.setMessage           ( message        );
        data_form.setInstruction       ( instruction    );
        data_form.setMessageAttributes ( attributes     );
        data_form.setSuppressCount     ( suppress_count );

        //  Initialize the JMS message objects
        TicketMessage ticket_message           = new TicketMessage();
        int iSuppressCount                     = 0;
        try
        {
            iSuppressCount = Integer.parseInt ( suppress_count );
        }
        catch ( NumberFormatException exception )
        {
        }
        SuppressionMessage suppression_message = new SuppressionMessage();
            ticket_message.setMessageId         ( message_id                          );
            ticket_message.setSourceNode        ( node_name                           );
            ticket_message.setSourceNodeType    ( node_type                           );
            ticket_message.setTimeStamp         ( Toolkit.getTimestamp ( creation_date, creation_time ) );
            ticket_message.setApplication       ( application                         );
            ticket_message.setMessageGroup      ( message_group                       );
            ticket_message.setObject            ( object                              );
            ticket_message.setSeverity          ( severity                            );
            ticket_message.setOperators         ( list_operators                      );
            ticket_message.setMessageText       ( message                             );
            ticket_message.setInstructionText   ( instruction                         );
            ticket_message.setSuppressCount     ( iSuppressCount                      );
            ticket_message.setAttributes        ( message_attributes                  );
            ticket_message.setEventInterface    ( TicketMessage.INTERFACE_TTI         );

            String message_key = ticket_message.getMessageKey();
        suppression_message.setTicketMessage ( ticket_message );

        _log.info ( data_form.toString() );

        LogToFile ( data_form.toString() );

        try
        {
            bef.sendSuppressionMessage ( suppression_message );
            _log.info ( "Processed message key: " + message_key );

            String user_agent  = request.getHeader ( "user-agent" );
            if ( user_agent.indexOf ( "libwww-perl" ) >= 0 )
            {
                _log.info ( "Perl agent detected" );
    
                return ( mapping.findForward ( "success-perl" ) );
            }
            else
            {
                return ( mapping.findForward ( "success" ) );
            }
        }
        catch ( Exception exception )
        {
            return ( mapping.findForward ( "failure" ) );
        }
    }


    private static void LogToFile ( String message )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( "c:\\test\\events_from_tti.out", true );
                outfile.write ( (new Date()).toString().getBytes() );
                outfile.write ( " - ".getBytes() );
                outfile.write ( message.toString().getBytes() );
                outfile.write ( "\n".getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
            _log.error ( "Could not write to log file", exception );
        }
    }
}
