package com.bgi.esm.monitoring.platform.weblogic;

import java.util.Date;
import java.util.Iterator;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.utility.JmsFacade;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.DrainMessage;
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;
import com.bgi.esm.monitoring.platform.shared.value.StormCalculationMessage;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionDrainMessage;

/**
 * Timer service
 *
 * @ejb.bean
 *    name="TimerEjb"
 *    type="Stateless"
 *    jndi-name="${jndi.base}/TimerDemoRemote"
 *    local-jndi-name="${jndi.base}/TimerDemoLocalHome"
 *    description="timer service"
 *
 * @ejb.transaction
 *    type="Required"
 *
 * @ejb.transaction-type
 *    type="Container"
 *
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class TimerBean implements SessionBean, TimedObject {

    /**
     * ctor, instantiate WebLogic logging
     */
    public TimerBean() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            System.err.println("TimerBean ctor failure");
            _log = Logger.getLogger ( TimerBean.class );
        }
    }

    /**
     * Create a timer.
     * Tests to insure only a single timer instance is started,
     * (i.e. only one storm, only one drain timer).
     *
     * @param arg timer name
     *
     * @ejb.interface-method
     */
    public void startTimer(String arg) {
        _log.debug("setupTimer:" + arg);

        if (findTimer(arg) == null) {
            _log.info("creating timer:" + arg);
        } else {
            _log.info("ignoring duplicate timer:" + arg);
            return;
        }

        long duration = 0;

        if (arg.equals(AP_DRAIN_TIMER)) {
            duration = AP_DRAIN_DURATION;
        //} else if (arg.equals(SC_BUFFER_TIMER)) {
        //    duration = SC_BUFFER_DURATION;
        } else if (arg.equals(SC_DRAIN_TIMER)) {
            duration = SC_DRAIN_DURATION;
        } else if (arg.equals(OPENVIEW_ACKNOWLEDGE_TIMER)) {
            duration = OPENVIEW_ACKNOWLEDGE_DURATION;
        } else if (arg.equals(SUPPRESSION_EMAIL_TIMER)) {
            duration = SUPPRESSION_EMAIL_DURATION;
        } else if (arg.equals(STORM_TIMER)) {
            duration = STORM_DURATION;
        } else if (arg.equals(SUPPRESSION_TIMER)) {
            duration = SUPPRESSION_DURATION;
        } else{
            _log.error("unknown timer target:" + arg);
            return;
        }

        try {
            TimerService ts = _context.getTimerService();
            ts.createTimer(new Date(), duration, arg);
        } catch(Exception exception) {
            _log.error("timer choke:", exception);
        }
    }

    /**
     *
     * @param arg
     *
     * @ejb.interface-method
     */
    public void stopTimer(String arg) {
        _log.debug("stopTimer:" + arg);

        Timer target = findTimer(arg);
        if (target == null) {
            _log.error("no cancel unknown timer:" + arg);
        } else{
            _log.info("cancel timer:" + arg);
            target.cancel();
        }
    }

    /**
     * @param arg
     */
    public void ejbTimeout(Timer arg) {
    //  System.out.println("xxx ejb time out xxx:" + arg);

        if (AP_DRAIN_TIMER.equals(arg.getInfo())) {
    //      System.out.println("drain timeout");
            try {
                JmsFacade jf = new JmsFacade(BussModule.AP_DRAIN);
                jf.queueWriter(new DrainMessage());
            } catch(Exception exception) {
                _log.error("jms choke:", exception);
            }
        //} else if (SC_BUFFER_TIMER.equals(arg.getInfo())) {
        //    //      System.out.println("drain timeout");
        //    try {
        //        JmsFacade jf = new JmsFacade(BussModule.SC_BUFFER);
        //        jf.queueWriter(new DrainMessage());
        //    } catch(Exception exception) {
        //        _log.error("jms choke:", exception);
        //    }
        } else if (SC_DRAIN_TIMER.equals(arg.getInfo())) {
            //      System.out.println("drain timeout");
            try {
                JmsFacade jf = new JmsFacade(BussModule.SC_DRAIN);
                jf.queueWriter(new DrainMessage());
            } catch(Exception exception) {
                _log.error("jms choke:", exception);
            }
        } else if (OPENVIEW_ACKNOWLEDGE_TIMER.equals(arg.getInfo())) {
            //      System.out.println("drain timeout");
            try {
                JmsFacade jf = new JmsFacade(BussModule.OPENVIEW_ACKNOWLEDGE);
                jf.queueWriter(new DrainMessage());
            } catch(Exception exception) {
                _log.error("jms choke:", exception);
            }
        } else if (SUPPRESSION_EMAIL_TIMER.equals(arg.getInfo())) {
            //      System.out.println("drain timeout");
            try {
                JmsFacade jf = new JmsFacade(BussModule.SUPPRESSION_EMAIL);
                jf.queueWriter(new DrainMessage());
            } catch(Exception exception) {
                _log.error("jms choke:", exception);
            }
        } else if (STORM_TIMER.equals(arg.getInfo())) {
        //      System.out.println("storm timeout");
            try {
                JmsFacade jf = new JmsFacade(BussModule.STORM);
                jf.queueWriter(new StormCalculationMessage());
            } catch(Exception exception) {
                _log.error("jms choke", exception);
            }
        } else if (SUPPRESSION_TIMER.equals(arg.getInfo())) {
        //      System.out.println("suppression timeout");
            try {
                JmsFacade jf = new JmsFacade(BussModule.SUPPRESSION_DRAIN);
                jf.queueWriter(new SuppressionDrainMessage());
            } catch(Exception exception) {
                _log.error("jms choke", exception);
            }
        } else{
            _log.error("unknown timeout:" + arg.getInfo());
            return;
        }
    }

    /**
     * Define session arguments
     *
     * @param arg current session context
     */
    public void setSessionContext(SessionContext arg) {
        _context = arg;
    }

    /**
     * Return a instance to active timer or null if not found
     *
     * @param arg timer
     * @return live instance or null if not found
     */
    private Timer findTimer(String arg) {
        Iterator ii = _context.getTimerService().getTimers().iterator();

        while(ii.hasNext()) {
            Timer temp = (Timer) ii.next();

            if (temp.getInfo().equals(arg)) {
                return(temp);
            }
        }

        return(null);
    }

    public static final String OPENVIEW_ACKNOWLEDGE_TIMER  = "openview_drain_timer";
    public static final long OPENVIEW_ACKNOWLEDGE_DURATION = 1L * 30L * 1000L;

    public static final String SUPPRESSION_EMAIL_TIMER     = "suppression_email_timer";
    public static final long SUPPRESSION_EMAIL_DURATION    = 1L * 60L * 1000L;

    public static final String AP_DRAIN_TIMER              = "ap_drain_timer";
    public static final long AP_DRAIN_DURATION             = 1L * 60L * 1000L;
    //public static final long AP_DRAIN_DURATION          = 13L * 1000L;

    public static final String SC_DRAIN_TIMER              = "sc_drain_timer";
    public static final long SC_DRAIN_DURATION             = 1L * 60L * 1000L;

    //public static final String SC_BUFFER_TIMER             = "sc_buffer_timer";
    //public static final long SC_BUFFER_DURATION            = 5L * 1000L + 200L;
    //public static final long SC_BUFFER_DURATION            = 60 * 1000L;

    public static final String STORM_TIMER                 = "storm_timer";
    public static final long STORM_DURATION                = 5L * 1000L;

    public static final String SUPPRESSION_TIMER           = "suppression";
    public static final long SUPPRESSION_DURATION          = 1L * 1000L;

    private SessionContext _context;

    /**
     * Handle to ORM dispatcher
     */
    private final OrmFacade _orm = new OrmFacade();

    /**
     * Define logger
     */
    private Logger _log;
}
