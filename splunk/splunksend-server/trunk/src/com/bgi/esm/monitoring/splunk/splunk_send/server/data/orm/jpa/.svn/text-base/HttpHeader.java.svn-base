package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpHeader;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader;

@Entity
@Table ( name="http_header" )
public class HttpHeader extends AbstractHttpHeader implements IHttpHeader
{
    /**
     *
     */
    final private static long serialVersionUID = -3820266132585108779L;
    @SuppressWarnings ( "unused" )
    final private static Logger _log           = Logger.getLogger ( HttpHeader.class );

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

    @Column ( name="header_name" )
    public String getHeaderName()
    {
        return super.getHeaderName();
    }

    @Column ( name="header_persistence" )
    public String getHeaderPersistence()
    {
        return super.getHeaderPersistence();
    }
}