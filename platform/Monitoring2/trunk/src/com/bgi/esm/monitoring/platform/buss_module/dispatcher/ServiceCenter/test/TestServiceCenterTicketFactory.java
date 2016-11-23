package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.test;

import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenterTicketFactory;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenterTicketNew;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestServiceCenterTicketFactory extends TestCase
{
    final private static Logger _log          = Logger.getLogger ( TestServiceCenterTicketFactory.class ); 
    private static final SimpleDateFormat sdf = new SimpleDateFormat ( "dd-MMM-yyyy, HH:mm:ss z Z" );

    //  Test URL: 
    //  http://peregrine-app-qa:8080/oaa/servlet/archway?P4Connect.genericAddIncident&company=BGI&ticket.owner=VPO&category=SERVERS&subcategory=ENTERPRISE%20SYSTEMS%20MANAGEMENT&product.type=VPO&brief.desc=Testing002&user.id=VPO&first.name=&last.name=&phone=&corp.title=&title=&dept=&contact.location=GLOBAL&contact.email=&contact.name=VPO&corp.structure=&priority.code=3&severity=4&incident.desc=Testing002

    public TestServiceCenterTicketFactory ( String param )
    {
        super ( param );
    }

    private static void logTicket ( ServiceCenterTicketNew ticket )
    {
        _log.info ( "Ticket number:   " + ticket.getTicketNumber()    );
        _log.info ( "Ticket Category: " + ticket.getCategory()        );
        if ( null != ticket.getCreationTime() )
        {
            _log.info ( "Creation time:   " + sdf.format ( ticket.getCreationTime().getTime() ) );
        }
        _log.info ( "Opened by:       " + ticket.getOpenedBy()        );
        _log.info ( "Severity code:   " + ticket.getSeverityCode()    );
        _log.info ( "Priority code:   " + ticket.getPriorityCode()    );
        _log.info ( "Closed by:       " + ticket.getClosedBy()        );
        _log.info ( "Assignment:      " + ticket.getAssignmentGroup() );
        _log.info ( "Status:          " + ticket.getStatus()          );
        if ( null != ticket.getCloseTime() )
        {
            _log.info ( "Close Time:      " + sdf.format ( ticket.getCloseTime().getTime() ) );
        }
        _log.info ( "Reference num:   " + ticket.getReferenceNum()    );
        _log.info ( "Contact Name:    " + ticket.getContactName()     );
        _log.info ( "Actor:           " + ticket.getActor()           );
        _log.info ( "Deadline group:  " + ticket.getDeadlineGroup()   );
        _log.info ( "Format:          " + ticket.getFormat()          );
        _log.info ( "Num actions:     " + ticket.getNumActions()      );
        for ( int counter = 0; counter < ticket.getNumActions(); counter++ )
        {
            _log.info ( "    Action:      " + ticket.getAction ( counter ) );
        }
        _log.info ( "Num resolutions: " + ticket.getNumResolutions()    );
        for ( int counter = 0; counter < ticket.getNumResolutions(); counter++ )
        {
            _log.info ( "    Resolution:  " + ticket.getResolution( counter ) );
        }
        _log.info ( "Num update actions: " + ticket.getNumUpdateActions() );
        for ( int counter = 0; counter < ticket.getNumUpdateActions(); counter++ )
        {
            _log.info ( "    Update action: " + ticket.getUpdateAction ( counter ) );
        }
    }

    public void testMakeHTTPRequest()
    {
        _log.info ( "**************************************** testMakeHTTPRequest() " );

        byte results[] = ServiceCenterTicketFactory.retrieveHTTPRequestContents ( "http://bip" );

        assertNotNull ( "Could not make HTTP requests", results );

        _log.info ( "Response length: " + results.length );
    }

    public void testRetrieveTicketInformationFromArchway()
    {
        _log.info ( "**************************************** testRetrieveTicketInformationFromArchway() " );

        byte results[] = ServiceCenterTicketFactory.retrieveTicketInformation ( "IM01006952" );

        assertNotNull ( "Could not retrieve ticket information from Archway", results );

        _log.info ( "Response length: " + results.length );
    }

    public void testParseTicketInformationRetrievedFromArchway()
    {
        _log.info ( "**************************************** testParseTicketInformationRetrievedFromArchway() " );

        ServiceCenterTicketNew ticket = ServiceCenterTicketFactory.retrieveServiceCenterTicket ( "IM01006952" );

        assertNotNull ( "Could not retrieve ticket from Archway", ticket );

        logTicket ( ticket );
    }

    public void testValidateNewValidTicket()
    {
        _log.info ( "**************************************** testValidateNewValidTicket() " );

        ServiceCenterTicketNew ticket = new ServiceCenterTicketNew();
        ticket.setCategory         ( "SERVERS" );
        ticket.setSubcategory      ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket.setAssignmentGroup  ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket.setBriefDescription ( "Test brief description" );
        ticket.setLongDescription  ( "Test long description" );
        ticket.setSeverityCode     ( 3 );
        ticket.setContactLocation  ( "GLOBAL" );

        ticket.validate();

        assertFalse ( "No errors should be detected", ticket.hasErrors() );
        assertFalse ( "No warnings should be detected", ticket.hasWarnings() );
    }

    public void testFailureToRetrieveWarningsBeforeValidate()
    {
        _log.info ( "**************************************** testFailureToRetrieveWarningsBeforeValidate() " );

        ServiceCenterTicketNew ticket = new ServiceCenterTicketNew();
        ticket.setCategory         ( "SERVERS" );
        ticket.setSubcategory      ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket.setAssignmentGroup  ( null );
        ticket.setBriefDescription ( "Test brief description" );
        ticket.setLongDescription  ( "Test long description" );
        ticket.setSeverityCode     ( 3 );
        ticket.setContactLocation  ( "GLOBAL" );

        boolean exception_caught   = false;
        try
        {
            ticket.hasWarnings();
        }
        catch ( IllegalStateException exception )
        {
            exception_caught = true;
        }

        assertTrue ( "Validation check for warnings has failed", exception_caught );
    }

    public void testFailureToRetrieveErrorsBeforeValidate()
    {
        _log.info ( "**************************************** testFailureToRetrieveErrorsBeforeValidate() " );

        ServiceCenterTicketNew ticket = new ServiceCenterTicketNew();
        ticket.setCategory         ( "SERVERS" );
        ticket.setSubcategory      ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket.setAssignmentGroup  ( null );
        ticket.setBriefDescription ( "Test brief description" );
        ticket.setLongDescription  ( "Test long description" );
        ticket.setSeverityCode     ( 3 );
        ticket.setContactLocation  ( "GLOBAL" );

        boolean exception_caught   = false;
        try
        {
            ticket.hasErrors();
        }
        catch ( IllegalStateException exception )
        {
            exception_caught = true;
        }

        assertTrue ( "Validation check for errors has failed", exception_caught );
    }

    public void testValidateNewTicketMissingAssignmentGroup()
    {
        _log.info ( "**************************************** testValidateNewTicketMissingAssignmentGroup() " );

        ServiceCenterTicketNew ticket = new ServiceCenterTicketNew();
        ticket.setCategory         ( "SERVERS" );
        ticket.setSubcategory      ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket.setAssignmentGroup  ( null );
        ticket.setBriefDescription ( "Test brief description" );
        ticket.setLongDescription  ( "Test long description" );
        ticket.setSeverityCode     ( 3 );
        ticket.setContactLocation  ( "GLOBAL" );

        ticket.validate();

        assertTrue  ( "Validation test for missing assignment group failed", ticket.hasErrors() );
        assertFalse ( "No warnings should be detected", ticket.hasWarnings() );
    }

    public void testValidateNewTicketMissingCategory()
    {
        _log.info ( "**************************************** testValidateNewTicketMissingCategory() " );

        ServiceCenterTicketNew ticket = new ServiceCenterTicketNew();
        ticket.setCategory         ( null );
        ticket.setSubcategory      ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket.setAssignmentGroup  ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket.setBriefDescription ( "Test brief description" );
        ticket.setLongDescription  ( "Test long description" );
        ticket.setSeverityCode     ( 3 );
        ticket.setContactLocation  ( "GLOBAL" );

        ticket.validate();

        assertTrue  ( "Validation test for missing category failed", ticket.hasErrors() );
        assertFalse ( "No warnings should be detected", ticket.hasWarnings() );
    }

    public void testValidateNewTicketMissingSubcategory()
    {
        _log.info ( "**************************************** testValidateNewTicketMissingSubcategory() " );

        ServiceCenterTicketNew ticket = new ServiceCenterTicketNew();
        ticket.setCategory         ( "SERVERS" );
        ticket.setSubcategory      ( null );
        ticket.setAssignmentGroup  ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket.setBriefDescription ( "Test brief description" );
        ticket.setLongDescription  ( "Test long description" );
        ticket.setSeverityCode     ( 3 );
        ticket.setContactLocation  ( "GLOBAL" );

        ticket.validate();

        assertTrue  ( "Validation test for missing subcategory failed", ticket.hasErrors() );
        assertFalse ( "No warnings should be detected", ticket.hasWarnings() );
    }

    public void testParseInformationForNewTicket()
    {
        _log.info ( "**************************************** testParseInformationForNewTicket() " );

        ServiceCenterTicketNew ticket_info = new ServiceCenterTicketNew();
        ticket_info.setCategory         ( "SERVERS" );
        ticket_info.setSubcategory      ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket_info.setAssignmentGroup  ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket_info.setBriefDescription ( "Test brief description" );
        ticket_info.setLongDescription  ( "Test long description" );
        ticket_info.setSeverityCode     ( 3 );
        ticket_info.setContactLocation  ( "GLOBAL" );

        ServiceCenterTicketNew ticket      = ServiceCenterTicketFactory.createNewTicket ( ticket_info );

        assertNotNull ( "Could not retrieve ticket from Archway", ticket );

        logTicket ( ticket );
    }

    /*
    public void testCreate100Tickets()
    {
        _log.info ( "**************************************** testCreate100Tickets() " );

        ServiceCenterTicketNew ticket_info = new ServiceCenterTicketNew();
        ticket_info.setCategory         ( "SERVERS" );
        ticket_info.setSubcategory      ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket_info.setAssignmentGroup  ( "ENTERPRISE SYSTEMS MANAGEMENT" );
        ticket_info.setBriefDescription ( "Test brief description" );
        ticket_info.setLongDescription  ( "Test long description" );
        ticket_info.setSeverityCode     ( 3 );
        ticket_info.setContactLocation  ( "GLOBAL" );

        long start_time = System.currentTimeMillis();
        for ( int counter = 0; counter < 100; counter++ )
        {
            ServiceCenterTicketNew ticket      = ServiceCenterTicketFactory.createNewTicket ( ticket_info );
        }
        long end_time = System.currentTimeMillis();

        _log.info ( "Time elapsed: " + (end_time-start_time) + " ms" );
    }
    //*/
};
