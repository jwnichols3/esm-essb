package com.bgi.esm.monitoring.platform.buss_module.throttle;

import javax.ejb.MessageDrivenBean;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import weblogic.logging.log4j.Log4jLoggingHelper;

import com.bgi.esm.monitoring.platform.buss_module.AbstractBussModule;

import com.bgi.esm.monitoring.platform.shared.value.BussModule;

/**
 * Calculate storm levels
 * 
 * @ejb.bean 
 *   name="StormConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable" 
 *   description="storm queue consumer"
 * 
 * @weblogic.message-driven 
 *   destination-jndi-name="replatform.queue.storm"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class StormConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

    /**
     * ctor, initialize weblogic logging
     */
    public StormConsumerBean() {
        super(BussModule.STORM);

        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch (Exception exception) {
            System.err.println("StormConsumerBean ctor failure");
            _log = Logger.getLogger ( StormConsumerBean.class );
        }

        _log.debug("storm consumer ctor");
    }

    /**
     * Invoked when messages arrive via queue.
     * 
     * @param arg fresh message
     */
    public void onMessage(Message arg) {
        _log.debug("fresh message noted");

        ObjectMessage om = (ObjectMessage) arg;

           LogToFile ( "c:\\test\\JMS-Storm.out", "Message class: " + om.getClass().getName() );
        if (om == null) {
            _log.error("null message");
        }

        _sc.execute(nbmq);
    }

    /**
     * Handle to calculator
     */
    private final StormCalculator _sc = new StormCalculator();

    /**
     * Define logger
     */
    private Logger _log;
}
