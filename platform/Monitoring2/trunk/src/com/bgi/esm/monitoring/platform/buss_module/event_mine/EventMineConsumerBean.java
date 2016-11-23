package com.bgi.esm.monitoring.platform.buss_module.event_mine;

import javax.ejb.MessageDrivenBean;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.buss_module.AbstractBussModule;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.value.AbstractMessage;
import com.bgi.esm.monitoring.platform.shared.value.Audit;
import com.bgi.esm.monitoring.platform.shared.value.AuditMessage;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.RawOvi;
import com.bgi.esm.monitoring.platform.shared.value.RawOviMessage;

/**
 * Process messages rom the event mine queue. 
 * Supports multiple message types, AuditMessage or RawOviMessage. 
 * Messages to Event Mine are simply written to a RDBMS, 
 * there is no downstream processing or evaluation of the contents.
 * 
 * @ejb.bean 
 *   name="EventMineConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable" 
 *   description="eventMine queue consumer"
 * 
 * @weblogic.message-driven destination-jndi-name="replatform.queue.event_mine"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class EventMineConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

	/**
	 * ctor
	 */
	public EventMineConsumerBean() {
		super(BussModule.EVENT_MINE);

		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
			System.err.println("EventMineConsumerBean ctor failure");
            _log = Logger.getLogger ( EventMineConsumerBean.class );
		}
		
		_log.debug("event mine consumer ctor");
	}

	/**
	 * Invoked when messages arrive via queue
	 * 
	 * @param arg fresh message, either AuditMessage or RawOviMessage
	 */
	public void onMessage(Message arg) {
		_log.debug("fresh message noted");

		ObjectMessage om = (ObjectMessage) arg;

		try {
			AbstractMessage am = (AbstractMessage) om.getObject();

			if (am == null) {
				_log.error("skipping null message");
			} else if (am instanceof AuditMessage) {
				Audit ad = new Audit((AuditMessage) am);
				_orm.addOrUpdateAudit(ad);
			} else if (am instanceof RawOviMessage) {
				RawOvi rod = new RawOvi((RawOviMessage) am);
				_orm.addOrUpdateRawOvi(rod);
			} else {
				_log.error("unknown message type:" + am);
			}
		} catch(Exception exception) {
			_log.error("event mine consumer:" + exception, exception );
		}
	}

	/**
	 * Handle to RDBMS
	 */
	private final OrmFacade _orm = new OrmFacade();
	
	/**
	 * Define logger
	 */
	private Logger _log;
}
