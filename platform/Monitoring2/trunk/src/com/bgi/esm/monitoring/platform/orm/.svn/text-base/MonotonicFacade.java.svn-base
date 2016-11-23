package com.bgi.esm.monitoring.platform.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import weblogic.logging.log4j.Log4jLoggingHelper;

import com.bgi.esm.monitoring.platform.shared.value.Storm;

/**
 * Manipulate monotonic sequence generator
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class MonotonicFacade {

	/**
	 * ctor, initialize weblogic loggging
	 */
	public MonotonicFacade() {
		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
			System.err.println("MonotonicFacade ctor/log failure");
            _log = Logger.getLogger ( MonotonicFacade.class );
		}
	}

	/**
	 * Return next generated sequence
	 * 
	 * @return next generated sequence
	 */
	public int getSequenceValue() {
		MonotonicEjbLocal local = findByKey();
		if (local == null) {
			return(-1);
		}
		
		int result = local.getValue().intValue();
		local.setValue(new Integer(result+1));

		return(result);
	}

	/**
	 * Retrieve the sequence row
	 * 
	 * @return row or null if not found
	 * @throws FinderException if select problem
	 * @throws NamingException if JNDI problem
	 */
	private MonotonicEjbLocal findByKey() {
		try {
			MonotonicEjbLocalHome home = MonotonicEjbUtil.getLocalHome();
			return(home.findSequence());
		} catch(FinderException fe) {
			_log.error("MonotonicFacade::findByKey() - finder exception:", fe);
		} catch(NamingException ne) {
			_log.error("MonotonicFacade::findByKey() - naming exception:", ne);
		}

		return(null);
	}

	/**
	 * Define logger
	 */
	private Logger _log;
}
