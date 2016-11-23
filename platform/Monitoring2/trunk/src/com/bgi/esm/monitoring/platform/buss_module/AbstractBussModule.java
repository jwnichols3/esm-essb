package com.bgi.esm.monitoring.platform.buss_module;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.shared.utility.JmsFacade;
import com.bgi.esm.monitoring.platform.shared.value.AcknowledgeMessage;
import com.bgi.esm.monitoring.platform.shared.value.AnnotationMessage;
import com.bgi.esm.monitoring.platform.shared.value.AuditMessage;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.OpenviewOwnMessage;
import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;

/**
 * Parent for all buss modules
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class AbstractBussModule {
    final protected SimpleDateFormat sdf = new SimpleDateFormat ( "MM/dd/yyyy HH:mm:ss" );
	
	/**
	 * ctor, determine downstream JMS plumbing
	 */
	public AbstractBussModule(BussModule arg) {
		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
			System.err.println("AbstractBussModule ctor failure");
            _log = Logger.getLogger ( AbstractBussModule.class );
		}
		
		_log.debug("ctor:" + arg);

		module = arg;

		try {
			if (module.equals(BussModule.AP_DRAIN)) {
				nbmq = null;
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.DATA_MAP)) {
				nbmq = new JmsFacade(BussModule.THROTTLE);
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.DISPATCHER)) {
				nbmq = null;
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.EVENT_MINE)) {
				nbmq = null;
				emq  = null;
			} else if (module.equals(BussModule.MODIFIER)) {
				nbmq = null;
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.NOTIFIER)) {
				nbmq = new JmsFacade(BussModule.SUPPRESSION);
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.SC_BUFFER)) {
				nbmq = null;
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.SC_DRAIN)) {
				nbmq = null;
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.OPENVIEW_ACKNOWLEDGE)) {
				nbmq = null;
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.SUPPRESSION_EMAIL)) {
				nbmq = null;
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.STORM)) {
				nbmq = new JmsFacade(BussModule.DISPATCHER);
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.SUPPRESSION)) {
				nbmq = new JmsFacade(BussModule.DATA_MAP);
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.SUPPRESSION_DRAIN)) {
				nbmq = null;
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else if (module.equals(BussModule.THROTTLE)) {
				nbmq = new JmsFacade(BussModule.DISPATCHER);
				emq  = new JmsFacade(BussModule.EVENT_MINE);
			} else {
				nbmq = null;
				emq  = null;
				_log.error("unknown buss module type:" + module);
			}
		} catch (Exception exception) {
			_log.error("AbstractBussModule ctor:", exception);
		}
	}

    protected boolean logMessage(String id, String action, BussModule logModule ) {
        AuditMessage am = new AuditMessage();

		am.setModule(logModule);
		am.setAction(action);
        try {
    		am.setMessageId(id);
        }
        catch ( Exception exception ) {
            _log.error ( String.format ( "Skipping logMessage for id=%s, action=%s, BussModule=%s: %s", id, action, logModule, exception.getMessage() ) );
            return false;
        }

        if ( null == emq )
        {
            StringBuilder message = new StringBuilder();
                message.append ( "Could not find EventMineQueue for BussModule='" );
                message.append ( module );
                message.append ( "'" );
            _log.error ( message.toString() );

            throw new NullPointerException ( message.toString() );
        }

		return(emq.queueWriter(am));
    }

	/**
	 * Audit message.
	 * 
	 * @param id message id
	 * @param id action
	 * @return true if success
	 */
	protected boolean logMessage(String id, String action ) {
		return logMessage ( id, action, module );
	}

    protected boolean ownOpenviewMessage ( String message_id, String username, String password )
    {
        if ( _log.isDebugEnabled() )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::ownOpenviewMessage ( MessageID=" );
                message.append ( message_id );
                message.append ( " )" );
            _log.debug ( message.toString() );
        }

        OpenviewOwnMessage message = new OpenviewOwnMessage ( username, password );
        message.setMessageId ( message_id );

		JmsFacade jms = null;

		try 
        {
			jms = new JmsFacade(BussModule.MODIFIER);
			
			jms.topicWriter(message.toXml());
		} 
        catch (Exception exception) 
        {
			_log.error("OVO write failure", exception);
			return(false);
		} 
        finally 
        {
			if (jms != null) 
            {
				jms.shutDown();
			}
		}
			
		return(true);
    }

    protected boolean annotateOpenviewMessage ( String message_id, String annotation_text )
    {
        if ( _log.isDebugEnabled() )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::annotateOpenviewMessage ( MessageID=" );
                message.append ( message_id );
                message.append ( ", CurrentTime=" );
                message.append ( sdf.format ( new Date() ) );
                message.append ( ", AnnotationText=" );
                message.append ( annotation_text );
                message.append ( " )" );
            _log.debug ( message.toString() );
        }

        AnnotationMessage annotation = new AnnotationMessage();
		annotation.setMessageId ( message_id      );
		annotation.setText      ( annotation_text );
        
		JmsFacade jms = null;

		try {
			jms = new JmsFacade(BussModule.MODIFIER);
			
			jms.topicWriter(annotation.toXml());
		} catch (Exception exception) {
			_log.error("OVO write failure", exception);
			return(false);
		} finally {
			if (jms != null) {
				jms.shutDown();
			}
		}
			
		return(true);
    }

	/**
	 * This message is suppressed and must be returned to OVO
	 * 
	 * @param arg message to suppress w/OVO
	 * @returns true success
	 */
	protected boolean acknowledgeOpenviewMessage ( String message_id, String annotation_text ) {
        if ( _log.isDebugEnabled() )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::acknowledgeOpenviewMessage ( MessageID=" );
                message.append ( message_id );
                message.append ( ", CurrentTime=" );
                message.append ( sdf.format ( new Date() ) );
                message.append ( ", AnnotationText=" );
                message.append ( annotation_text );
                message.append ( " )" );
            _log.debug ( message.toString() );
        }

		AnnotationMessage annotation = new AnnotationMessage();
		annotation.setMessageId ( message_id      );
		annotation.setText      ( annotation_text );

		AcknowledgeMessage acknowledge = new AcknowledgeMessage();
		acknowledge.setMessageId ( message_id );

		JmsFacade jms = null;

		try {
			jms = new JmsFacade(BussModule.MODIFIER);
			
			jms.topicWriter(annotation.toXml());
			jms.topicWriter(acknowledge.toXml());
		} catch (Exception exception) {
			_log.error("OVO write failure", exception);
			return(false);
		} finally {
			if (jms != null) {
				jms.shutDown();
			}
		}
			
		return(true);
	}

    public void listMessageAttributes ( String filename, TicketMessage tm )
    {
        Map map    = tm.getAttributes();
        Iterator i = map.keySet().iterator();
        String key = null;

        StringBuilder message = new StringBuilder();
            message.append ( "Message ID: " + tm.getMessageId() );
            message.append ( "\n" );
            while ( i.hasNext() )
            {
                key = i.next().toString();
                message.append ( "    Key=" );
                message.append ( key );
                message.append ( ", Value=" );
                message.append ( map.get( key ).toString() );
                message.append ( "\n" );
            }
        LogToFile ( filename , message.toString() );
    }

    public final void LogToFile ( String filename, String message )
    {
        if ( _log.isDebugEnabled() )
        {
            try
            {
                synchronized ( this.getClass() )
                {
                    FileOutputStream outfile = new FileOutputStream ( filename, true );
                        outfile.write ( (new java.util.Date()).toString().getBytes() );
                        outfile.write ( " - ".getBytes() );
                        outfile.write ( message.getBytes() );
                        outfile.write ( "\n".getBytes() );
                    outfile.close();
                }
            }
            catch ( IOException exception )
            {
                _log.fatal ( "Could not log message to filename: " + filename );
            }
        }
    }
    

	/**
	 * Audit entry message.
	 * 
	 * @param arg message id
	 * @return true if success
	 */
	protected boolean logEntryMessage(String arg) {
		return(logMessage(arg, "entry"));
	}

	/**
	 * Audit exit message.
	 * 
	 * @param arg message id
	 * @return true if success
	 */
	protected boolean logExitMessage(String arg) {
		return(logMessage(arg, "exit"));
	}

	/**
	 * Audit downstream message.
	 * 
	 * @param arg message id
	 * @return true if success
	 */
	protected boolean logDownStreamMessage(String arg) {
		return(logMessage(arg, "downstream"));
	}


	/**
	 * This message is suppressed and must be returned to OVO
	 * 
	 * @param arg message to suppress w/OVO
	 * @returns true success
	 */
	protected boolean writeToOpenView(TicketMessage arg, String message_text) {
		AnnotationMessage annotation = new AnnotationMessage();
		annotation.setMessageId(arg.getMessageId());
		annotation.setText( message_text );

		AcknowledgeMessage acknowledge = new AcknowledgeMessage();
		acknowledge.setMessageId(arg.getMessageId());

		JmsFacade jms = null;

		try {
			jms = new JmsFacade(BussModule.MODIFIER);
			
			jms.topicWriter(annotation.toXml());
			jms.topicWriter(acknowledge.toXml());
		} catch (Exception exception) {
			_log.error("OVO write failure", exception);
			return(false);
		} finally {
			if (jms != null) {
				jms.shutDown();
			}
		}
			
		return(true);
	}


	/**
	 * Pipe to next buss module
	 */
	protected JmsFacade nbmq;

	/**
	 * Event mine queue;
	 */
	protected JmsFacade emq;

	/**
	 * Module identifier, required to generate audit messages
	 */
	protected final BussModule module;
	
	/**
	 * Define logger
	 */
	private Logger _log;
}
