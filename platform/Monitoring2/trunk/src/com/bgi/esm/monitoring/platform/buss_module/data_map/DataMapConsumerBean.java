package com.bgi.esm.monitoring.platform.buss_module.data_map;

import javax.ejb.MessageDrivenBean;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.buss_module.AbstractBussModule;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.DataMapMessage;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleMessage;
import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;

/**
 * Data Map Buss Module.
 * 
 * <p>
 * The data map buss module is the second module (from OVO). This module awaits
 * messages from the suppression module and usually forwards messages to the
 * throttle module.
 * 
 * <p>
 * Based upon the message group field within the OVO ticket message, the data
 * map selects the supporting alarm point/peregrine rule and forwards this
 * information to the throttle module.
 * 
 * <p>
 * Messages which fail to match a data map rule are logged as errors and not
 * forwarded to throttle.
 * 
 * <p>
 * Data map rules are kept within a database table.
 * 
 * @ejb.bean 
 *   name="DataMapConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable" 
 *   description="data map queue consumer"
 * 
 * @weblogic.message-driven destination-jndi-name="replatform.queue.data_map"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class DataMapConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

    final private String logExternalFileName = "c:\\test\\JMS-DataMap.out";

    /**
     * ctor
     */
    public DataMapConsumerBean() {
        super(BussModule.DATA_MAP);

        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            System.err.println("DataMapConsumerBean ctor failure");
            _log = Logger.getLogger ( DataMapConsumerBean.class );
        }
        
        _log.debug("dataMap consumer ctor");
    }

    /**
     * Invoked when messages arrive via queue
     * 
     * @param arg fresh message
     */
    public void onMessage(Message arg) {
        _log.debug("fresh message noted");

        ObjectMessage om = (ObjectMessage) arg;

        try {
            DataMapMessage dmm = (DataMapMessage) om.getObject();
            if (dmm == null) {
                _log.error("skipping null message");
            } else {
                logEntryMessage(dmm.getTicketMessage().getMessageId());

                TicketMessage tix = dmm.getTicketMessage();

                String key = tix.getMessageGroup();

                if ( _log.isDebugEnabled() )
                {
                    StringBuilder message = new StringBuilder ( "DataMap processing... ( MessageID, MessageGroup ) = ( " );
                    message.append ( dmm.getTicketMessage().getMessageId() );
                    message.append ( ", " );
                    message.append ( key );
                    message.append ( " )" );

                    LogToFile ( logExternalFileName, message.toString() );
                }

                DataMapRule rule = _orm.selectDataMapRule(key);
                /*
                if (rule == null) 
                {
                    _log.error("unable to get rule for:" + key);
                    logMessage(dmm.getTicketMessage().getMessageId(), "DefaultRule");

                    rule = _orm.selectDataMapRule ( "esm" );

                    if ( null != rule )
                    {
                        _log.warn ( "Using default rule: esm" );
                    }
                } 
                //*/
              
                if ( null == rule )
                {
                    _log.warn ("unable to get default rule for: " + key );
                    logMessage(dmm.getTicketMessage().getMessageId(), "norule");
                }
                else
                {  
                    ThrottleMessage tm = new ThrottleMessage();
                    tm.setTicketMessage(tix);
                    tm.setDataMapRule(rule);

                    nbmq.queueWriter(tm);

                    logDownStreamMessage(dmm.getTicketMessage().getMessageId());

                    EventsByGroup e = _orm.selectEventsByGroupByMessageId ( dmm.getTicketMessage().getMessageId() );
                    if ( null != e )
                    {
                        EventsByGroup add = _orm.addOrUpdateEventsByGroup ( e );
                        if ( null == add )
                        {
                            _log.error ( "Could not add EventsByGroup shared object" );
                        }
                    }
                    else
                    {
                        _log.error ( "Could not find EventsByGroup object for MessageID=" + dmm.getTicketMessage().getMessageId() );
                    }

                    LogToFile ( logExternalFileName, "Finished sending message to Throttle" );
                }
            }
        } catch(JMSException exception) {
            _log.error("data_map consumer:" + exception, exception );
        }
        catch ( Exception exception )
        {
            _log.error ( "DataMap Consumer exception: " + exception, exception );
        }
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
