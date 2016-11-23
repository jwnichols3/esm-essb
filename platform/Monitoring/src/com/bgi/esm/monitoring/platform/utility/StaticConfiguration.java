package com.bgi.esm.monitoring.platform.utility;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Global configuration, implemented as a singleton and shared by all instances
 * of the "replatform" project. Relies upon Jakarta Commons Configuration.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class StaticConfiguration {

	/**
	 * Return a Boolean from the configuration
	 * 
	 * @return a Boolean from the configuration
	 * @throws ConfigurationException if problem
	 */
	public static Boolean getBoolean(String key) {
		try {
			if (_configuration == null) {
				_configuration = new PropertiesConfiguration(FILE_NAME);
			}

			return (_configuration.getBoolean(key, new Boolean(false)));
		} catch (ConfigurationException exception) {
			_log.error("bad configuration", exception);
		}

		return (null);
	}	

	/**
	 * Return a Integer from the configuration
	 * 
	 * @return a Integer from the configuration
	 * @throws ConfigurationException if problem
	 */
	public static Integer getInteger(String key) {
		try {
			if (_configuration == null) {
				_configuration = new PropertiesConfiguration(FILE_NAME);
			}

			return (_configuration.getInteger(key, new Integer(0)));
		} catch (ConfigurationException exception) {
			_log.error("bad configuration", exception);
		}

		return (null);
	}

	/**
	 * Return a String from the configuration
	 * 
	 * @return a String from the configuration or null if not found
	 */
	public static String getString(String key) {
		try {
			if (_configuration == null) {
				_configuration = new PropertiesConfiguration(FILE_NAME);
			}

			return (_configuration.getString(key));
		} catch (ConfigurationException exception) {
			_log.error("bad configuration", exception);
		}

		return (null);
	}

	/**
	 * Return a configuration
	 * 
	 * @return populated configuration or null if not found
	 */
	public static Configuration getConfiguration() {
		try {
			if (_configuration == null) {
				_configuration = new PropertiesConfiguration(FILE_NAME);
			}

			return (_configuration);
		} catch (ConfigurationException exception) {
			_log.error("bad configuration", exception);
		}

		return (null);
	}

	/**
	 * Handle to current configuration.
	 */
	private static Configuration _configuration;

	/**
	 * Configuration file name, must be in CLASSPATH at runtime.
	 */
	private static final String FILE_NAME = "bgi.properties";

	/**
	 * Logger
	 */
	private static final Log _log = LogFactory.getLog(StaticConfiguration.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
