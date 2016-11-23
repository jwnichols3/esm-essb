package com.bgi.esm.monitoring.platform.buss_module.suppression; 

import java.util.Calendar;
import java.util.ListIterator;
import java.util.TimeZone;
import javax.ejb.MessageDrivenBean;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.buss_module.AbstractBussModule;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.utility.JmsFacade;
import com.bgi.esm.monitoring.platform.shared.value.AcknowledgeMessage;
import com.bgi.esm.monitoring.platform.shared.value.AnnotationMessage;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.DataMapMessage;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionMessage;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;

/**
 * Determine if a OVO event should be suppressed (i.e. inhibited from downstream
 * processing). Suppression rules come from database. Suppressed events are sent
 * a message back to OV (acknowledge/annotated).
 * 
 * @ejb.bean 
 *   name="SuppressionConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable" 
 *   description="suppression queue consumer"
 *  
 * @weblogic.message-driven destination-jndi-name="replatform.queue.suppression"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class SuppressionConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

    /**
     * ctor
     */
    public SuppressionConsumerBean() {
        super(BussModule.SUPPRESSION);

        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            System.err.println("SuppressionConsumerBean ctor failure");
            _log = Logger.getLogger ( SuppressionConsumerBean.class );
        }
        
        _log.debug("suppression consumer ctor");
    }

    /**
     * Invoked when messages arrive via queue
     * 
     * @param arg fresh message
     */
    public void onMessage(Message arg) {
        _log.info("fresh message noted");

        ObjectMessage om      = (ObjectMessage) arg;
        SuppressionMessage sm = null;

        try {
            sm = (SuppressionMessage) om.getObject();
            if (sm == null) {
                _log.error("skipping null message");
            } else {
                TicketMessage tix = sm.getTicketMessage();

                if ( _log.isDebugEnabled() )
                {
                    StringBuilder message = new StringBuilder();
                        message.append ( getClass().getName() );
                        message.append ( "::onMessage() - received message key - " );
                        message.append ( tix.getMessageKey() );
                    _log.debug ( message.toString() );
                }
 
                EventsByGroup e = new EventsByGroup();
                e.setGroupName       ( tix.getMessageGroup()    );
                e.setMessageId       ( tix.getMessageId()       );
                e.setSourceNode      ( tix.getSourceNode()      );
                e.setSourceNodeType  ( tix.getSourceNodeType()  );
                e.setApplication     ( tix.getApplication()     );
                e.setObject          ( tix.getObject()          );
                e.setSeverity        ( tix.getSeverity()        );
                e.setMessageText     ( tix.getMessageText()     );
                e.setInstructionText ( tix.getInstructionText() );

                //  Save the event
                EventsByGroup add = _orm.addOrUpdateEventsByGroup ( e );
                if ( null == add )
                {
                    _log.error ( "Could not add EventsByGroup shared object for messageID=" + tix.getMessageId() );
                }

                logEntryMessage(sm.getTicketMessage().getMessageId());

                listMessageAttributes ( "c:\\test\\JMS-CMA.txt", sm.getTicketMessage() );

                
                String timezone_string = _orm.selectEebProperty ( "server.time_zone" ).getPropertyValue();
                TimeZone tz            = TimeZone.getTimeZone( timezone_string );
                int timezone_offset    = tz.getRawOffset();
                int dst_offset         = tz.getDSTSavings();
                int offset             = timezone_offset + dst_offset;

                SuppressionRule suppressingRule = suppressMessage(sm.getTicketMessage(), e, offset );

                if ( null != suppressingRule )
                {
                    writeToOpenView(sm.getTicketMessage(), "Suppressed by Suppression Rule #" + suppressingRule.getSuppressId() );
                    logMessage(sm.getTicketMessage().getMessageId(), "suppressed by Suppression #" + suppressingRule.getSuppressId() );
                }
                else 
                {
                    DataMapMessage dmm = new DataMapMessage();
                    dmm.setTicketMessage(sm.getTicketMessage());
                    nbmq.queueWriter(dmm);

                    logDownStreamMessage(sm.getTicketMessage().getMessageId());
                }
            }
            

        } catch (Exception exception) {
            if ( null != sm )
            {
                _log.error("suppression consumer: " + sm.toString(), exception);
            }
            else
            {
                _log.error ( "suppression consumer: null message", exception );
            }
        }
    }

    /**
     * Evaluate a ticket message and determine if it should be suppressed.
     * 
     * @param arg candidate message
     * @return true, message should be suppressed
     */
    private SuppressionRule suppressMessage(TicketMessage arg, EventsByGroup event, int timezone_offset ) {
        _log.debug("testing:" + arg.getMessageId());

        ListIterator iterator = _orm.getAllActiveSuppressionRules().listIterator();

        Calendar testTimestamp = Calendar.getInstance();

        while (iterator.hasNext()) {
            SuppressionRule rule = (SuppressionRule) iterator.next();
            _log.debug("rule:" + rule);
            if ( _log.isDebugEnabled() )
            {
                StringBuilder message = new StringBuilder();
                    message.append ( getClass().getName() );
                    message.append ( "::suppressMessage ( rule=" );
                    message.append ( rule.toString() );
                    message.append ( ", StartTime=" );
                    message.append ( sdf.format ( rule.getStartTime().getTime() ) );
                    message.append ( ", EndTime=" );
                    message.append ( sdf.format ( rule.getEndTime().getTime() ) );
                    message.append ( " ) - current=" );
                    message.append ( sdf.format ( testTimestamp.getTime() ) );
                    message.append ( ", Offset in ms=" );
                    message.append ( timezone_offset );
                _log.debug ( message.toString() );
            }

            boolean flag = suppressionTest(arg, rule);
            if (flag) {
                event.setGroupName ( rule.getGroupName() );

                return rule;
            }
            /*
            if (rule.isActive(timezone_offset)) {
                boolean flag = suppressionTest(arg, rule);
                if (flag) {
                    event.setGroupName ( rule.getGroupName() );

                    return rule;
                }
            } else {
                _log.debug("expired rule");
            }
            //*/
        }

        _log.debug("suppression match failure");

        return null;
    }

    /**
     * Test this message against this rule. Only need one match.
     * 
     * @param arg candidate message
     * @return true, message should be suppressed
     */
    private boolean suppressionTest(TicketMessage tm, SuppressionRule rule) {
        //
        // application name
        //
        if (rule.getApplicationName().equals("-")) {
            _log.debug("skip application name test");
        } else {
            //if (!tm.getApplication().equalsIgnoreCase(rule.getApplicationName())) {
            if (!tm.getMessageGroup().equalsIgnoreCase(rule.getApplicationName())) {
                if ( _log.isDebugEnabled() )
                {
                    StringBuilder message = new StringBuilder();
                        message.append ( getClass().getName() );
                        message.append ( "::suppressionTest ( rule=" );
                        message.append ( rule.getApplicationName() );
                        message.append ( ", compare=" );
                        message.append ( tm.getMessageGroup() );
                        message.append ( " ) - match failure" );
                    _log.debug ( message.toString() );
                }
                return(false);
            }
        }

        //
        // node name
        //
        if (rule.getNodeName().equals("-")) {
            _log.debug("skip node name test");
        } else {
            String testNodeName = tm.getSourceNode();
            int test_index = testNodeName.indexOf ( "insidelive.net" );
            if ( test_index > 0 )
            {
                testNodeName = testNodeName.substring ( 0, test_index-1 );
            }
            if (!testNodeName.equalsIgnoreCase(rule.getNodeName())) 
            {
                if ( _log.isDebugEnabled() )
                {
                    StringBuilder message = new StringBuilder();
                        message.append ( "EEB Suppression Module: node name match failure ( expected=" );
                        message.append ( rule.getNodeName() );
                        message.append ( ", received=" );
                        message.append ( tm.getSourceNode() );
                        message.append ( " )" );
                    _log.debug ( message.toString() );
                }
                return(false);
            }
        }

        //
        // message text
        //
        if (rule.getMessage().equals("-")) {
            _log.debug("skip message test");
        } else {
            if (!tm.getMessageText().equalsIgnoreCase(rule.getMessage())) {
                _log.debug("message match failure");
                return(false);
            }
        }

        //
        // database server name
        //
        if (rule.getDatabaseServerName().equals("-")) {
            _log.debug("skip db server test");
        } else {
            String target = tm.getAttribute("INSTANCE");
            if (target == null) {
                _log.debug("database name match failure");
                return(false);
            }

            if (!target.equalsIgnoreCase(rule.getDatabaseServerName())) {
                _log.debug("database name match failure");
                return(false);
            }
        }

        _log.debug("suppression match noted");

        return(true);
    }

    /**
     * Handle to ORM dispatcher
     */
    private final OrmFacade _orm = new OrmFacade();
    
    /**
     * Define logger
     */
    private Logger _log;
}
