package com.bgi.esm.monitoring.portlets.Openview2EEBBridge.actions;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.bgi.esm.monitoring.portlets.Openview2EEBBridge.forms.NotifierEventForm;
import com.bgi.esm.monitoring.portlets.Openview2EEBBridge.Toolkit;

public class EventsFromNotifier extends DispatchAction
{
    final private static Logger _log = Logger.getLogger ( EventsFromNotifier.class );
    final private static SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy, dd MMM - HH:mm:ss z Z" );

    public ActionForward retrieveMessage ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "EventsFromNotifier::retrieveMessage()" );

        HashMap <String, String> params = Toolkit.retrieveHttpRequestParameters ( request );

        NotifierEventForm data_form = (NotifierEventForm) form;
        data_form.setMessageID         ( params.get ( "MessageId"     ) );
        data_form.setNodeName          ( params.get ( "NodeName"      ) );
        data_form.setNodeType          ( params.get ( "NodeType"      ) );
        data_form.setCreationDate      ( params.get ( "CreationDate"  ) );
        data_form.setCreationTime      ( params.get ( "CreationTime"  ) );
        data_form.setReceiveDate       ( params.get ( "ReceiveDate"   ) );
        data_form.setReceiveTime       ( params.get ( "ReceiveTime"   ) );
        data_form.setApplication       ( params.get ( "Application"   ) );
        data_form.setMessageGroup      ( params.get ( "MessageGroup"  ) );
        data_form.setObject            ( params.get ( "Object"        ) );
        data_form.setSeverity          ( params.get ( "Severity"      ) );
        data_form.setOperators         ( params.get ( "Operators"     ) );
        data_form.setMessage           ( params.get ( "Message"       ) );
        data_form.setInstruction       ( params.get ( "Instruction"   ) );
        data_form.setMessageAttributes ( params.get ( "Attributes"    ) );
        data_form.setSuppressCount     ( params.get ( "SuppressCount" ) );

        _log.info ( data_form.toString() );

        //  Attempt to send this into the EEB

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
}
