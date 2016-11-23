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
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class MonotonicSuppressionFacade {

	/**
	 * ctor, initialize weblogic loggging
	 */
	public MonotonicSuppressionFacade() {
		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
            _log = Logger.getLogger ( MonotonicSuppressionFacade.class );
		}
        
        if ( null == _log ) {
			System.err.println("MonotonicSuppressionFacade ctor/log failure");
        }
	}

	/**
	 * Return next generated sequence
	 * 
	 * @return next generated sequence
	 */
	public int getSequenceValue() {
		MonotonicSuppressionEjbLocal local = findByKey();
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
	private MonotonicSuppressionEjbLocal findByKey() {
		try {
			MonotonicSuppressionEjbLocalHome home = MonotonicSuppressionEjbUtil.getLocalHome();
			return(home.findSequence());
		} catch(FinderException fe) {
			_log.error("MonotonicSuppressionFacade::findByKey() - finder exception:", fe);
		} catch(NamingException ne) {
			_log.error("MonotonicSuppressionFacade::findByKey() - naming exception:", ne);
		}

		return(null);
	}

	/**
	 * Define logger
	 */
	private Logger _log;
}
