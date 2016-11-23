package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractDataMap;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap;

@Entity
@Table ( name="data_map" )
public class DataMap extends AbstractDataMap implements IDataMap
{
    /**
     *
     */
    final private static long serialVersionUID = -140488734799559193L;
    @SuppressWarnings ( "unused" )
    final private static Logger _log           = Logger.getLogger ( DataMap.class );

    @Id
    @Column ( name="rule_id" )
    public String getRuleID()
    {
        return super.getRuleID();
    }

    @Column ( name="application_name" )
    public String getApplicationName()
    {
        return super.getApplicationName();
    }

    @Column ( name="hostname" )
    public String getHostname()
    {
        return super.getHostname();
    }

    @Column ( name="target_path" )
    public String getTargetPath()
    {
        return super.getTargetPath();
    }
}