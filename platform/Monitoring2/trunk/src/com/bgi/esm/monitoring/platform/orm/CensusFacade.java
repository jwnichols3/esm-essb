package com.bgi.esm.monitoring.platform.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.shared.value.Census;

/**
 * Manipulate census entity beans
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class CensusFacade {

	/**
	 * ctor, initialize weblogic monitoring
	 */
	public CensusFacade() {
		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
            _log = Logger.getLogger ( CensusFacade.class );
		}
        
        if ( null == _log ) {
			System.err.println("CensusFacade ctor/log failure");
        }
	}

	/**
	 * Return all census rows
	 * 
	 * @return all census rows, might be empty list (never null).
	 */
	public List getAllRows() {
		ArrayList<Census> results = new ArrayList<Census>();

		try {
			CensusEjbLocalHome home = CensusEjbUtil.getLocalHome();
			Iterator iterator = home.findAll().iterator();
			while(iterator.hasNext()) {
				CensusEjbLocal local = (CensusEjbLocal) iterator.next();
				results.add(local.getValue());
			}
		} catch(FinderException fe) {
			_log.error("CensusFacade::getAllRows() - finder exception", fe);
		} catch(NamingException ne) {
			_log.error("CensusFacade::getAllRows() - naming exception", ne);
		}

		return(results);
	}

	/**
	 * Delete specified census row
	 * 
	 * @param row to delete
	 * @return true success
	 */
	public boolean deleteRow(Census arg) {
		_log.debug("delete:" + arg);

		CensusEjbLocal local = findByKey(arg.getRowKey());

		if (local == null) {
			_log.error("CensusFacade::deleteRow() - attempt to delete unknown row:" + arg.getRowKey());
			return(false);
		}

		try {
			local.remove();
		} catch(RemoveException re) {
			_log.error("CensusFacade::deleteRow() - remove exception:" + arg.getRowKey() + ":", re);
			return(false);
		}

		return(true);
	}

	/**
	 * Insert or update a census row.
	 * 
	 * @param arg data map rule
	 * @returns true if success
	 */
	public boolean addOrUpdateRow(Census arg) {
		if (arg.getRowKey() == null) {
			_log.debug("insert:" + arg);

			arg.setRowKey(CensusEjbUtil.generateGUID(this));

			CensusEjbLocalHome home = null;

			try {
				home = CensusEjbUtil.getLocalHome();
				home.create(arg);
			} catch(CreateException ce) {
				_log.error("CensusFacade::addOrUpdateRow() - create exception:" + arg + ":", ce);
				return(false);
			} catch(NamingException ne) {
				_log.error("CensusFacade::addOrUpdateRow() - naming exception:" + arg + ":", ne);
				return(false);
			}
		} else {
			_log.debug("update:" + arg);

			CensusEjbLocal local = findByKey(arg.getRowKey());
			if (local == null) {
				_log.error("CensusFacade::addOrUpdateRow() - unknown key:" + arg.getRowKey());
				return(false);
			}

			local.setValue(arg);
		}

		return(true);
	}

	/**
	 * Return selected census row.
	 * 
	 * @param arg row key
	 * @returns selected row or null if not found
	 */
	public Census selectRow(String arg) {
		_log.debug("select:" + arg);

		CensusEjbLocal local = findByKey(arg);
		if (local == null) {
			return(null);
		}

		return(local.getValue());
	}

	/**
	 * Return all census rows by group
	 * 
	 * @return all census rows, might be empty list (never null).
	 */
	public List<Census> selectByGroup(String arg) {
		ArrayList<Census> results = new ArrayList<Census>();

		try {
			CensusEjbLocalHome home = CensusEjbUtil.getLocalHome();
			Iterator iterator = home.findByGroup(arg).iterator();
			while(iterator.hasNext()) {
				CensusEjbLocal local = (CensusEjbLocal) iterator.next();
				results.add(local.getValue());
			}
        } catch(ObjectNotFoundException exception ) {
            if ( _log.isInfoEnabled() ) {
                StringBuilder message = new StringBuilder();
                    message.append ( "CensusFacade::selectByGroup ( GroupName=" );
                    message.append ( arg );
                    message.append ( " ) - no records found" );
                _log.info ( message.toString() );
            }
		} catch(FinderException fe) {
			_log.error("CensusFacade::selectByGroup() - finder exception", fe);
		} catch(NamingException ne) {
			_log.error("CensusFacade::selectByGroup() - naming exception", ne);

		}

		return(results);
	}

	/**
	 * Retrieve an individual census row
	 * 
	 * @param target key to select
	 * @return rule or null if not found
	 * @throws FinderException if select problem
	 * @throws NamingException if JNDI problem
	 */
	private CensusEjbLocal findByKey(String target) {
		try {
			CensusEjbLocalHome home = CensusEjbUtil.getLocalHome();
			return (home.findByKey(target));
        } catch(ObjectNotFoundException exception ) {
            if ( _log.isInfoEnabled() ) {
                StringBuilder message = new StringBuilder();
                    message.append ( "CensusFacade::findByKey ( target=" );
                    message.append ( target );
                    message.append ( " ) - no records found" );
                _log.info ( message.toString() );
            }
		} catch(FinderException fe) {
			_log.error("CensusFacade::findByKey() - finder exception:" + target + ":", fe);
		} catch(NamingException ne) {
			_log.error("CensusFacade::findByKey() - naming exception:" + target + ":", ne);
		}

		return(null);
	}

	/**
	 * Define logger
	 */
	private Logger _log;
}
