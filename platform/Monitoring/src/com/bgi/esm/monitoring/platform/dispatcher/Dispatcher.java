package com.bgi.esm.monitoring.platform.dispatcher;

import org.apache.commons.configuration.ConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.DispatcherQueue;
import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;

import com.bgi.esm.monitoring.platform.value.KillMessage;
import com.bgi.esm.monitoring.platform.value.DispatcherMessage;
import com.bgi.esm.monitoring.platform.value.XmlIf;

/**
 * The dispatcher module is the fourth buss module.
 *
 * @author G.S. Cole (guycole at gmail dot com)
 * @version $Id$
 */
public class Dispatcher {

    /**
     * ctor
     *
     * @param arg instance name (i.e. primary, secondary, etc)
     */
    public Dispatcher(String arg) throws Exception {
	_instance_name = arg;

 	_in_queue_name = StaticConfiguration.getString("queue.dispatcher.name");

 	_log.info("start:" + _instance_name);
 	_log.info("inbound:" + _in_queue_name);
    }

    /**
     * Process incoming messages and dispatch accordingly.
     *
     * @throws Exception for anything
     */
    public void execute() throws Exception {
	_dq = new DispatcherQueue(_in_queue_name);

	while (_run_flag) {
	    _log.debug("blocking for queue read");
 	    XmlIf message = _dq.dispatcherQueueReader();
 	    _log.debug("fresh message:" + message);

	    if (message instanceof KillMessage) {
                _log.debug("kill message type noted");
		
                _dq.queueWriterKiller();
		
                shutDown();
            } else if (message instanceof DispatcherMessage) {
                _log.debug("dispatch message type noted");

                dispatchMessage ((DispatcherMessage) message );
 	    } else {
 		_log.error("unknown message type");
	    }
	}
    }

    /**
     * Graceful shut down
     */
    public void shutDown() {
	_log.warn("orderly shutdown");

 	_run_flag = false;

 	if (_dq != null) {
 	    _dq.shutDown();
 	}
    }

    /**
     *
     *
     *
     */
    public void dispatchMessage ( DispatcherMessage arg ) {
    }

    /**
     * Main entry point for the Dispatcher module.
     *
     * @param args command line arguments
     * @throws Exception for anything
     */
    public static void main(String[] args) throws Exception {
	Dispatcher dispatcher = new Dispatcher(args[0]);
	dispatcher.execute();
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
     * Input queue
     */
    private DispatcherQueue _dq;

    /**
     * Determine when Dispatcher should gracefully exit
     */
    private boolean _run_flag = true;

    /**
     * Logger
     */
    private final Log _log = LogFactory.getLog(Dispatcher.class);
}

/*
 * Development Environment:
 *   Fedora 4
 *   Sun Java Developers Kit 1.5.0_06
 *
 * Maintenance History:
 *   $Log$
 */
