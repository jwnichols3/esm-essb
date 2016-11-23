package com.bgi.esm.monitoring.platform.buss_module.suppress_drain;

import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
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
import com.bgi.esm.monitoring.platform.shared.value.SuppressionDrainMessage;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;

/**
 * Routinely checks the suppression database for expired suppressions.
 * 
 * @ejb.bean 
 *   name="SuppressionDrainConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable" 
 *   description="suppression drain queue consumer"
 * 
 * @weblogic.message-driven destination-jndi-name="replatform.queue.suppress_drain"
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class SuppressionDrainConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

    final private static String externalLogFileName = "c:\\test\\JMS-SuppressionDrain.out";
	/**
	 * ctor
	 */
	public SuppressionDrainConsumerBean() {
		super(BussModule.SUPPRESSION_DRAIN);

		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
			System.err.println("SuppressionDrainConsumerBean ctor failure");
            _log = Logger.getLogger ( SuppressionDrainConsumerBean.class );
		}
		
		_log.debug("suppress_drain consumer ctor");
	}

	/**
	 * Invoked when messages arrive via queue
	 * 
	 * @param arg fresh message
	 */
	public void onMessage(Message arg) 
    {
        ObjectMessage om            = (ObjectMessage) arg;
        SuppressionDrainMessage sdm = null;

        try
        {
            sdm = (SuppressionDrainMessage) om.getObject();
       
            _log.debug ( "checking for expired suppressions - message: " + sdm.toString() );

            List suppression_rules = _orm.getAllSuppressionRules();
            SuppressionRule rule   = null;
            Iterator iterator      = suppression_rules.iterator();
            long current_time      = System.currentTimeMillis();

            String timezone_string = _orm.selectEebProperty ( "server.time_zone" ).getPropertyValue();
            TimeZone tz            = TimeZone.getTimeZone( timezone_string );
            int timezone_offset    = tz.getRawOffset();
            int dst_offset         = tz.getDSTSavings();
            int offset             = timezone_offset + dst_offset;

            while ( iterator.hasNext() )
            {
                rule = (SuppressionRule) iterator.next();

                if (( true  == rule.isStarted(offset) ) && 
                    ( true  == rule.getNotificationFlag() ) && 
                    ( false == rule.getIsNotified() ))
                {
                    //  Generate a notification for this suppression
                    _log.debug ( "Generating notification for suppression #" + rule.getSuppressId() );

                    rule.setIsNotified ( true );

                    _orm.addOrUpdateSuppressionRule ( rule );
                }
            }
        }
        catch ( Exception exception )
        {
            if ( null != sdm )
            {
                _log.error ( "SC Drain Consumer Bean - exception detected: " + sdm.toString(), exception );
            }
            else
            {
                _log.error ( "SC Drain Consumer bean - exception detected - bad message: " + om.toString(), exception );
            }
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
