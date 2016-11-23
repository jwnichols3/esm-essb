package com.bgi.esm.monitoring.platform.buss_module.throttle;

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
import com.bgi.esm.monitoring.platform.shared.value.Census;
import com.bgi.esm.monitoring.platform.shared.value.DispatcherMessage;
import com.bgi.esm.monitoring.platform.shared.value.Spool;
import com.bgi.esm.monitoring.platform.shared.value.Storm;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleAction;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleMessage;
import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;

/**
 * 
 * @ejb.bean 
 *   name="ThrottleConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable" 
 *   description="throttle queue consumer"
 *  
 * @weblogic.message-driven destination-jndi-name="replatform.queue.throttle"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class ThrottleConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

	/**
	 * ctor
	 */
	public ThrottleConsumerBean() {
		super(BussModule.THROTTLE);

		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
			System.err.println("ThrottleConsumerBean ctor failure");
            _log = Logger.getLogger ( ThrottleConsumerBean.class );
		}
		
		_log.debug("throttle consumer ctor");
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
			ThrottleMessage tm = (ThrottleMessage) om.getObject();
            TicketMessage ticket_message = tm.getTicketMessage();
            listMessageAttributes ( "c:\\test\\JMS-NetIQ.txt", ticket_message );

			if (tm == null) {
				_log.error("skipping null message");
			} else {
				logEntryMessage(tm.getTicketMessage().getMessageId());

				ThrottleAction action = addFreshMessage(tm);

				logMessage(tm.getTicketMessage().getMessageId(), action.toString());
			}
		} catch (JMSException exception) {
			_log.error("throttle consumer:" + exception, exception);
		}
	}

	/**
	 * 
	 */
	private ThrottleAction addFreshMessage(ThrottleMessage arg) {

		String group = arg.getDataMapRule().getGroup();
		
		Storm storm = _orm.selectStorm(group);
		if (storm == null) {
			storm = new Storm();
			storm.setGroup(group);
			storm.setStormLevel(0);
			storm.setAction(ThrottleAction.PASS_THRU);

			storm = _orm.addOrUpdateStorm(storm);
		}

		ThrottleAction action = storm.getAction();
		_log.info("target group:" + group + ":" + action.toString());

		if (action.equals(ThrottleAction.PASS_THRU)) {
			DispatcherMessage dm = new DispatcherMessage();
			dm.setTicketMessage ( arg.getTicketMessage() );
            dm.setDataMapRule   ( arg.getDataMapRule()   );

            LogToFile ( "c:\\test\\JMS-Throttle.out", dm.toString() );
			nbmq.queueWriter(dm);
		} else if (action.equals(ThrottleAction.SPOOL)) {
			Spool spool = new Spool();
			spool.setGroup(group);
			spool.setSpoolKey(storm.getSpoolKey());
			spool.setOvoKey(arg.getTicketMessage().getMessageId());
			_orm.addOrUpdateSpoolRow(spool);
		} else if (action.equals(ThrottleAction.DISCARD)) {
			// empty
		}

		Census census = new Census();
		census.setGroup(group);
		_orm.addOrUpdateCensus(census);
		
		return(action);
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
