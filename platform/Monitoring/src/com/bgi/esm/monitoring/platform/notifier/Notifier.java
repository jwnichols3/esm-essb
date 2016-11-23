package com.bgi.esm.monitoring.platform.notifier;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.SuppressionQueue;
import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;

import com.bgi.esm.monitoring.platform.value.DateOnly;
import com.bgi.esm.monitoring.platform.value.TicketMessage;
import com.bgi.esm.monitoring.platform.value.SuppressionMessage;
import com.bgi.esm.monitoring.platform.value.TimeOnly;

/**
 * Notifier is the bridge between OVO manager and JMS.
 *
 * <p>
 * Notifier is invoked by OVO manager notification service and converts
 * the 16 parameter OVO message into a XML message that is fed to a JMS
 * queue.
 *
 * <p>
 * Notifier is a transient process, a fresh instance is created for 
 * each OVO message.
 *
 * @author G.S. Cole (guycole at gmail dot com)
 * @version $Id$
 */

public class Notifier {

    /**
     * Process this OVO message
     *
     * @param args OVO message elements, must be 16
     * @throws Exception for anything
     */
    public void execute(String[] args) throws Exception {
	SuppressionMessage sm = new SuppressionMessage();
	sm.setTicketMessage(parseArgs(0, args));

 	String queue_name = StaticConfiguration.getString("queue.suppression.name");

	SuppressionQueue sq = new SuppressionQueue(queue_name);
	boolean flag = sq.queueWriter(sm);
	sq.shutDown();

	if (flag) {
	    _log.debug("queue write success");
	} else {
	    _log.error("queue write failure");
	}
    }

    /**
     * Process the command line arguments and produce a TicketMessage
     *
     * @param origin index within args to start
     * @param args OVO message elements, must be 16
     * @return populated TicketMessage value object
     * @throws NullPointerException if null arg
     * @throws ParseException if date or time parse problem
     */
    public TicketMessage parseArgs(int origin, String[] args) throws ParseException {
	TicketMessage result = new TicketMessage();

	result.setMessageId(args[origin++].trim());
	result.setSourceNode(args[origin++].trim());
	result.setSourceNodeType(args[origin++].trim());
	result.setCreationDate(new DateOnly(args[origin++].trim()));
	result.setCreationTime(new TimeOnly(args[origin++].trim()));
	result.setReceiveDate(new DateOnly(args[origin++].trim()));
	result.setReceiveTime(new TimeOnly(args[origin++].trim()));
	result.setApplication(args[origin++].trim());
	result.setMessageGroup(args[origin++].trim());
	result.setObject(args[origin++].trim());
	result.setSeverity(args[origin++].trim());
	result.setOperators(parseOperators(args[origin++].trim()));
	result.setMessageText(args[origin++].trim());
	result.setInstructionText(args[origin++].trim());
	result.setAttributes(parseAttributes(args[origin++].trim()));
	result.setSuppressCount(Integer.parseInt(args[origin++].trim()));

	return(result);
    }

    /**
     * Parse the operators
     *
     * @return list of String w/each operator in a single string
     */
    private List parseOperators(String arg) {
	ArrayList result = new ArrayList();
	
	String[] argz = arg.split(" ");
	
	for (int ii = 0; ii < argz.length; ii++) {
	    result.add(argz[ii]);
	}
	
	return(result);
    }
    
    /**
     * Parse message attributes
     *
     * @return map of attributes
     */
    private Map parseAttributes(String arg) {
	HashMap result = new HashMap();
	
	String[] argz = arg.split(";;");
	
 	for (int ii = 0; ii < argz.length; ii++) {
	    String[] tempz = argz[ii].split("=");
	    
 	    result.put(tempz[0], tempz[1]);
 	}
	
	return(result);
    }
    
    /**
     * Main entry point for the Notifier.
     *
     * @param args OVO message elements, must be 16
     * @throws Exception for anything
     */
    public static void main(String[] args) throws Exception {
	Notifier notifier = new Notifier();
	notifier.execute(args);
    }
    
    /**
     * Logger
     */
    private final Log _log = LogFactory.getLog(Notifier.class);
}

/*
 * Development Environment:
 *   Fedora 4
 *   Sun Java Developers Kit 1.5.0_06
 *
 * Maintenance History:
 *   $Log$
 */
