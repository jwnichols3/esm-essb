package com.bgi.esm.monitoring.platform.event_mine;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;
import com.bgi.esm.monitoring.platform.utility.EventMineQueue;

import com.bgi.esm.monitoring.platform.value.KillMessage;
import com.bgi.esm.monitoring.platform.value.EventMineMessage;
import com.bgi.esm.monitoring.platform.value.XmlIf;

/**
 * Event mine module
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class EventMine {

	/**
	 * ctor
	 * 
	 * @param arg instance name (i.e. primary, secondary, etc)
	 */
	public EventMine(String arg) throws Exception {
		_instance_name = arg;

		_in_queue_name = StaticConfiguration.getString("queue.event_mine.name");

		_log.info("start:" + _instance_name);
		_log.info("inbound:" + _in_queue_name);
		
		String jdbc_url = StaticConfiguration.getString("event_mine.jdbc.url");
		_log.debug("URL:" + jdbc_url);
		
		String jdbc_driver = StaticConfiguration.getString("event_mine.jdbc.driver");
		_log.debug("driver:" + jdbc_driver);
		
		String jdbc_user = StaticConfiguration.getString("event_mine.jdbc.user");
		_log.debug("user:" + jdbc_user);
		
		String jdbc_password = StaticConfiguration.getString("event_mine.jdbc.password");
		_log.debug("password:" + jdbc_password);
		
		BasicDataSource bds = new BasicDataSource();
		bds.setUrl(jdbc_url);
		bds.setDriverClassName(jdbc_driver);
		bds.setUsername(jdbc_user);
		bds.setPassword(jdbc_password);
		
		_ds = bds;
	}

	/**
	 * Process incoming messages and dispatch accordingly.
	 * 
	 * @throws Exception for anything
	 */
	public void execute() throws Exception {
		_eq = new EventMineQueue(_in_queue_name);

		while (_run_flag) {
			_log.debug("blocking for queue read");
			XmlIf message = _eq.eventMineQueueReader();
			_log.debug("fresh message:" + message);

			if (message instanceof KillMessage) {
				_log.debug("kill message type noted");

				shutDown();
			} else if (message instanceof EventMineMessage) {
				_log.debug("write event mine message to database");
				dataBaseWrite((EventMineMessage) message);
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

		if (_eq != null) {
			_eq.shutDown();
		}
	}
	
	/**
	 * write EventMineMessage to database
	 * 
	 * @param arg
	 */
	private void dataBaseWrite(EventMineMessage arg) {
		String insert = "insert into raw_datum(source, xml) values('" + arg.getSource() + "','" + arg.getPayload() + "')";
		_log.debug(insert);
		
		Connection connection = null; 
		Statement statement = null;
		
		try {
			connection = _ds.getConnection(); 
			statement = connection.createStatement();
			statement.executeUpdate(insert);
		} catch(Exception exception) {
			_log.error("choke", exception);
		} finally {
			try {
				statement.close();
			} catch(Exception exception) {
				_log.info("choke", exception);
			}
			
			try {
				connection.close();
			} catch(Exception exception) {
				_log.info("choke", exception);
			}
		}
	}

	/**
	 * Main entry point for the DataMap Buss module.
	 * 
	 * @param args command line arguments
	 * @throws Exception for anything
	 */
	public static void main(String[] args) throws Exception {
		EventMine event_mine = new EventMine(args[0]);
		event_mine.execute();
	}
	
	/**
	 * Handle to database
	 */
	private final DataSource _ds;
	
	/**
	 * Name of this instance (i.e. "primary", "secondary", etc)
	 */
	private final String _instance_name;

	/**
	 * Inbound queue name, defined in configuration file.
	 */
	private final String _in_queue_name;

	/**
	 * Input queue
	 */
	private EventMineQueue _eq;

	/**
	 * Determine when EventMine should gracefully exit
	 */
	private boolean _run_flag = true;

	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(EventMine.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */