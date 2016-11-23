package com.bgi.esm.monitoring.platform.shared.exception;

/**
 * RMI back end is not responding. Is application server running?
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class BackEndFailure extends EmsException {

	/**
	 * Back end failure
	 */
	public BackEndFailure() {
		super(RMI_FAILURE);
	}

	/**
	 * Back end failure
	 * 
	 * @param arg complaint
	 */
	public BackEndFailure(String arg) {
		super(arg);
	}

	/**
	 * Error String
	 */
	public static final String RMI_FAILURE = "RMI Failure";

	/**
	 * Serial version identifier. 
	 * Be sure to update this when the class is updated.
	 */
	public static final long serialVersionUID = 1L;
}
