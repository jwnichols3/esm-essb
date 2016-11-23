package com.bgi.esm.monitoring.platform.suppression;

import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.DataMapQueue;
import com.bgi.esm.monitoring.platform.utility.OviPublisher;
import com.bgi.esm.monitoring.platform.utility.SuppressionQueue;
import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;

import com.bgi.esm.monitoring.platform.value.AcknowledgeMessage;
import com.bgi.esm.monitoring.platform.value.AnnotationMessage;
import com.bgi.esm.monitoring.platform.value.DataMapMessage;
import com.bgi.esm.monitoring.platform.value.KillMessage;
import com.bgi.esm.monitoring.platform.value.SuppressionMessage;
import com.bgi.esm.monitoring.platform.value.TicketMessage;
import com.bgi.esm.monitoring.platform.value.XmlIf;

/**
 * The suppression module is the first gatekeeper from OVO to Alarm Point and/or
 * Service Center. Each OVO message is compared against a rule base to determine
 * if the message should be suppressed or forwarded to downstream modules for
 * evaluation.
 * 
 * <p>
 * The rule base is an external configuration file accessed by RuleManager.
 * 
 * <p>
 * Suppressed messages are returned to OVO for acknowledgement/annotation.
 * 
 * <p>
 * Forwarded messages are send to the DataMap buss module.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class Suppression {

	/**
	 * ctor
	 * 
	 * @param arg instance name (i.e. primary, secondary, etc)
	 */
	public Suppression(String arg) {
		_instance_name = arg;

		_in_queue_name = StaticConfiguration.getString("queue.suppression.name");
		_out_queue_name = StaticConfiguration.getString("queue.data_map.name");

		_log.info("start:" + _instance_name);
		_log.info("inbound:" + _in_queue_name);
		_log.info("outbound:" + _out_queue_name);

		_rm.start();
	}

	/**
	 * Process incoming messages and dispatch accordingly.
	 * 
	 * @throws Exception for anything
	 */
	public void execute() throws Exception {
		_sq = new SuppressionQueue(_in_queue_name);
		_dmq = new DataMapQueue(_out_queue_name);
		
		String topic_name = StaticConfiguration.getString("topic.mei.name");
		_op = new OviPublisher(topic_name);

		while (_run_flag) {
			_log.debug("blocking for queue read");
			XmlIf message = _sq.suppressionQueueReader();
			_log.debug("fresh message:" + message);

			if (message instanceof KillMessage) {
				_log.debug("kill message type noted");

				_dmq.queueWriterKiller();

				shutDown();
			} else if (message instanceof SuppressionMessage) {
				_log.debug("suppression message type noted");

				SuppressionMessage sm = (SuppressionMessage) message;

				if (suppressMessage(sm.getTicketMessage())) {
					writeToOpenView(sm.getTicketMessage());
				} else {
					DataMapMessage dmm = new DataMapMessage();
					dmm.setTicketMessage(sm.getTicketMessage());
					_dmq.queueWriter(dmm);
				}
			} else {
				_log.error("unknown message type:" + message);
			}
		}
	}

	/**
	 * Graceful shut down
	 */
	public void shutDown() {
		_log.warn("orderly shutdown");

		_run_flag = false;

		_rm.shutDown();

		if (_sq != null) {
			_sq.shutDown();
		}

		if (_dmq != null) {
			_dmq.shutDown();
		}
	}

	/**
	 * Evaluate a ticket message and determine if it should be suppressed.
	 * 
	 * @param arg candidate message
	 * @return true, message should be suppressed
	 */
	private boolean suppressMessage(TicketMessage arg) {
		_log.debug("testing:" + arg);

		ListIterator iterator = _rm.getRuleList().listIterator();	
		while (iterator.hasNext()) {
			Rule rule = (Rule) iterator.next();
			_log.debug("rule:" + rule);

			if (rule.isActive()) {
				
				//
				// application name
				//
				if (rule.getApplicationName().equals("-")) {
					_log.debug("skip application name test");
				} else {
					if (!arg.getApplication().equalsIgnoreCase(rule.getApplicationName())) {
						_log.debug("application name match failure");
						continue;
					}
				}

				//
				// node name
				//
				if (rule.getNodeName().equals("-")) {
					_log.debug("skip node name test");
				} else {
					if (!arg.getSourceNode().equalsIgnoreCase(rule.getNodeName())) {
						_log.debug("node name match failure");
						continue;
					}
				}

				//
				// message text
				//
				if (rule.getMessage().equals("-")) {
					_log.debug("skip message test");
				} else {
					if (!arg.getMessageText().equalsIgnoreCase(rule.getMessage())) {
						_log.debug("message match failure");
						continue;
					}
				}

				//
				// database server name
				//
				if (rule.getDatabaseServerName().equals("-")) {
					_log.debug("skip db server test");
				} else {
					String target = arg.getAttribute("INSTANCE");
					if (target == null) {
						_log.debug("database name match failure");
						continue;
					}

					if (!target.equalsIgnoreCase(rule.getDatabaseServerName())) {
						_log.debug("database name match failure");
						continue;
					}
				}

				_log.debug("suppression match noted");

				return(true);
			} else {
				_log.debug("rule is not active");
			}
		}

		_log.debug("suppression match failure");

		return(false);
	}

	/**
	 * This message is suppressed and must be returned to OVO
	 * 
	 * @param arg message to return
	 */
	private void writeToOpenView(TicketMessage arg) {
		_log.debug("suppress message");
		
		AnnotationMessage annotation = new AnnotationMessage();
		annotation.setMessageId(arg.getMessageId());
		annotation.setText("rule mandated suppression");
		_op.topicWriter(annotation.toXml());
		
		AcknowledgeMessage acknowledge = new AcknowledgeMessage();
		acknowledge.setMessageId(arg.getMessageId());
		_op.topicWriter(acknowledge.toXml());
	}

	/**
	 * Main entry point for the Suppresion Buss module.
	 * 
	 * @param args command line arguments
	 * @throws Exception for anything
	 */
	public static void main(String[] args) throws Exception {
		Suppression suppression = new Suppression(args[0]);
		
		Thread.sleep(1000L); //read rule file
		
		suppression.execute();
	}

	/**
	 * Name of this instance (i.e. "primary", "secondary", etc)
	 */
	private final String _instance_name;

	/**
	 * Inbound queue name, defined in configuration file.
	 */
	private final String _in_queue_name;

	/**
	 * Downstream buss module
	 */
	private DataMapQueue _dmq;

	/**
	 * Input queue
	 */
	private SuppressionQueue _sq;
	
	/**
	 * OVI publisher
	 */
	private OviPublisher _op;

	/**
	 * Outbound queue name, defined in configuration file.
	 */
	private final String _out_queue_name;

	/**
	 * Determine when Suppression should gracefully exit
	 */
	private boolean _run_flag = true;

	/**
	 * Rule manager thread
	 */
	private final RuleManager _rm = new RuleManager();

	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(Suppression.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */