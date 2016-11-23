package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpParameter;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter;

@Entity
@Table ( name="http_parameter" )
public class HttpParameter extends AbstractHttpParameter implements IHttpParameter
{
    /**
     *
     */
    final private static long serialVersionUID = 7127704669169393404L;
    @SuppressWarnings ( "unused" )
    final private static Logger _log           = Logger.getLogger ( HttpParameter.class );

    @Id
    @Column ( name="row_id" )
    public String getRowID()
    {
        return super.getRowID();
    }

    @Column ( name="request_id" )
    public String getRequestID()
    {
        return super.getRequestID();
    }

    @Column ( name="parameter_name" )
    public String getParameterName()
    {
        return super.getParameterName();
    }

    @Column ( name="parameter_persistence" )
    public String getParameterPersistence()
    {
        return super.getParameterPersistence();
    }
}