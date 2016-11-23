package com.bgi.esm.monitoring.platform.filters.Archway.orm.facades;

import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.filters.Archway.orm.beans.ArchwayRequestBean;

public abstract class AbstractArchwayRequestFacade
{
    final private static Logger _log = Logger.getLogger ( AbstractArchwayRequestFacade.class );

    public abstract List selectAllRequests();

    public abstract ArchwayRequestBean select ( long request_id );

    public abstract boolean insertOrUpdateRow ( ArchwayRequestBean object );

    public abstract List findRequestsBetweenTimes ( Calendar start_time, Calendar end_time );
};
