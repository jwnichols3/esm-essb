package com.bgi.esm.monitoring.platform.buss_module.open_view;

import javax.ejb.MessageDrivenBean;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.buss_module.AbstractBussModule;
import com.bgi.esm.monitoring.platform.shared.utility.XmlParser;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.ParsedOvi;
import com.bgi.esm.monitoring.platform.shared.value.RawOviMessage;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionMessage;
import com.bgi.esm.monitoring.platform.shared.value.XmlIf;

/**
 * Read a raw HP OVI notification message, write to event mine and suppression
 * queue. No evaluation of HP OVI message contents.
 * 
 * @ejb.bean 
 *   name="NotifierConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Topic"
 *   subscription-durability="NonDurable" 
 *   description="notifier topic consumer"
 *
 * @weblogic.message-driven
 *   destination-jndi-name="replatform.topic.notify"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class NotifierConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

	/**
	 * ctor
	 */
	public NotifierConsumerBean() {
		super(BussModule.NOTIFIER);
		
		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
			System.err.println("NotifierConsumerBean ctor failure");
            _log = Logger.getLogger ( NotifierConsumerBean.class );
		}

		_log.debug("notifier consumer ctor");
	}

    final private static String logExternalFile = "c:\\test\\JMS-Openview.out";
    final private static String dup_string1     = "<numberOfDuplicates>";
    final private static String dup_string2     = "</numberOfDuplicates>";

	/**
	 * Invoked when messages arrive via topic from OVI. 
	 * Expecting OVI produced XML formatted messages.
	 * 
	 * @param arg fresh message
	 */
	public void onMessage(Message arg) {
		_log.info("fresh message noted");
		
		TextMessage message = (TextMessage) arg;
        String text = null;

		try {
			text = message.getText();

			if ((text == null) || (text.length() < 1)) {
				_log.error("skipping null/empty message");
			} else {
				XmlParser xp = new XmlParser();
				XmlIf xml = xp.rawOviNotifyParser(text);

				_log.debug(xml);

				if (xml instanceof ParsedOvi) {
					ParsedOvi po = (ParsedOvi) xml;

                    StringBuilder log = new StringBuilder();
                    log.append ( "Message detected: ( message_id, suppressCount, severity ) = ( " );
                    log.append ( po.getMessageId() );
                    log.append ( ", " );
                    log.append ( po.getDuplicateCount() );
                    log.append ( ", " );
                    log.append ( po.getSeverity() );
                    log.append ( " )" );
                    LogToFile ( logExternalFile, log.toString() );

                    String messageText = message.getText();
                    int index1         = messageText.indexOf ( dup_string1 );
                    int index2         = 0;
                    int num_duplicates = 0;

                    if ( index1 > 0 )
                    {
                        messageText = messageText.substring ( index1 + dup_string1.length() );
                        index2 = messageText.indexOf ( dup_string2 );
                        messageText = messageText.substring ( 0, index2 );

                        po.setDuplicateCount ( Integer.parseInt ( messageText ) );

                        LogToFile ( logExternalFile, "Duplicates detected -- num: " + messageText );
                    }

                    LogToFile ( logExternalFile, "logEntryMessage()" );
					logEntryMessage(po.getMessageId());

                    LogToFile ( logExternalFile, "legalMessage()" );
					legalMessage(po, text);

                    LogToFile ( logExternalFile, "logDownStreamMessage()" );
					logDownStreamMessage(po.getMessageId());

                    LogToFile ( logExternalFile, "Done!" );
				} else {
					_log.info("unknown parse results:" + text);

					logMessage("unknown", "discarded");

					illegalMessage(text);
				}
			}
		} catch (Exception exception) {
			_log.error("notifier consumer:" + text, exception );
		}
	}

	/**
	 * Valid OVI message noted, extract relevant fields and 
	 * write to downstream queues.
	 * 
	 * <OL>
	 * <LI>Write to audit regarding new message discovery.
	 * <LI>Write to raw to save raw xml
	 * <LI>Write suppression w/parsed xml contents
	 * <LI>Write to audit regarding message dispatch to suppression
	 * 
	 * @param po parsed OVI
	 * @param raw XML payload
	 * @return true success
	 */
	private boolean legalMessage(ParsedOvi po, String raw) {
		RawOviMessage rom = new RawOviMessage();
		rom.setModule(module);
		rom.setMessageId(po.getMessageId());
		rom.setXmlPayload(raw);

		emq.queueWriter(rom);

		SuppressionMessage sm = new SuppressionMessage();
		sm.setTicketMessage(po.getTicketMessage());
		nbmq.queueWriter(sm);

		return(true);
	}

	/**
	 * Invalid OVI message noted. Write to event mine for manual analysis.
	 * 
	 * @param raw XML payload
	 * @return true success
	 */
	private boolean illegalMessage(String raw) {
		RawOviMessage rom = new RawOviMessage();
		rom.setModule(module);
		rom.setMessageId("unknown");
		rom.setXmlPayload(raw);

		emq.queueWriter(rom);

		return(true);
	}
	
	/**
	 * Define logger
	 */
	private Logger _log;
}
