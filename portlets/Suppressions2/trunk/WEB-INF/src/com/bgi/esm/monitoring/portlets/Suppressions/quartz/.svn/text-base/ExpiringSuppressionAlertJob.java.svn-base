package com.bgi.esm.monitoring.portlets.Suppressions.quartz;

import java.util.Date;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *  Sends out an email notifying about an expiring suppression
 * 
 *  @author Dennis Lin (linden)
 */
public class ExpiringSuppressionAlertJob implements Job 
{
    final private static Logger _log = Logger.getLogger ( ExpiringSuppressionAlertJob.class );

    /**
     */
    public ExpiringSuppressionAlertJob() 
    {
    }

    /**
     * <p>
     * Called by the <code>{@link org.quartz.Scheduler}</code> when  
     * <code>{@link org.quartz.Trigger}</code> fires that is associated with
     * the <code>Job</code>.
     * </p>
     * 
     * @throws JobExecutionException
     *             if there is an exception while executing the job.
     */
    public void execute(JobExecutionContext context) throws JobExecutionException 
    {
        // Say Hello to the World and display the date/time
        _log.info("Hello World! - " + new Date());
    }
}

