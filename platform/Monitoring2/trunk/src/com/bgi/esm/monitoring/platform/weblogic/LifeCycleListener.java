package com.bgi.esm.monitoring.platform.weblogic;

import java.util.Hashtable;
import javax.naming.Context;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.client.RmiProperties;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import weblogic.application.ApplicationLifecycleEvent;
import weblogic.application.ApplicationLifecycleListener;
import weblogic.logging.log4j.Log4jLoggingHelper;

/**
 * @author coleguy
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class LifeCycleListener extends ApplicationLifecycleListener {

	public LifeCycleListener() {
	}
	
	/**
	 * 
	 * @param arg
	 */
	public void postStart(ApplicationLifecycleEvent arg) {
		_log.info("xxx post start event xxx");
	//	System.out.println("xxx post start event xxx");
		
		try {
			loadLocalHost();
			TimerEjbRemote ter = getRemote();
			ter.startTimer(TimerBean.AP_DRAIN_TIMER);
			ter.startTimer(TimerBean.SC_DRAIN_TIMER);
			ter.startTimer(TimerBean.OPENVIEW_ACKNOWLEDGE_TIMER);
			ter.startTimer(TimerBean.SUPPRESSION_EMAIL_TIMER);
			//ter.startTimer(TimerBean.SC_BUFFER_TIMER);
			ter.startTimer(TimerBean.STORM_TIMER);
			ter.startTimer(TimerBean.SUPPRESSION_TIMER);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param arg
	 */
	public void preStop(ApplicationLifecycleEvent arg) {
		_log.info("xxx pre stop event xxx");
	//	System.out.println("xxx pre stop event xxx");
		
		try {
			loadLocalHost();
			TimerEjbRemote ter = getRemote();
			ter.stopTimer(TimerBean.AP_DRAIN_TIMER);
			ter.stopTimer(TimerBean.SC_DRAIN_TIMER);
			ter.stopTimer(TimerBean.OPENVIEW_ACKNOWLEDGE_TIMER);
			ter.stopTimer(TimerBean.SUPPRESSION_EMAIL_TIMER);
			//ter.stopTimer(TimerBean.SC_BUFFER_TIMER);
			ter.stopTimer(TimerBean.STORM_TIMER);
			ter.stopTimer(TimerBean.SUPPRESSION_TIMER);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Configure RMI for localhost.
	 */
	public void loadLocalHost() {
		Hashtable<String, String> jndi = new Hashtable<String, String>();

		jndi.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		jndi.put(Context.PROVIDER_URL, "t3://localhost:7001");

		RmiProperties.setJndi(jndi);
	}

	/**
	 * Return remote interface to TimerBean
	 * 
	 * @return remote interface to TimerBean
	 * @throws BackEndFailure if RMI failure
	 */
	private TimerEjbRemote getRemote() throws BackEndFailure {
		try {
			TimerEjbHomeRemote tehr = TimerEjbUtil.getHome(RmiProperties.getJndiHashtable());
			return(tehr.create());
		} catch(Exception exception) {
			_log.error(BackEndFailure.RMI_FAILURE, exception);
			throw new BackEndFailure();
		}
	}
	
	/**
	 * Define logger
	 */
	private static Logger _log;

    static {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            System.err.println("LifeCycleListener ctor failure");
            _log = Logger.getLogger ( LifeCycleListener.class );
        }
    }
}
