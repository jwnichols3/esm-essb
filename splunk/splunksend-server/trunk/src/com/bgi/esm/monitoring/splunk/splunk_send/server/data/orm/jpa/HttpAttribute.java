package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpAttribute;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute;

@Entity
@Table ( name="http_attribute" )
public class HttpAttribute extends AbstractHttpAttribute implements IHttpAttribute
{
    /**
     *
     */
    final private static long serialVersionUID = -7398344344402505320L;
    @SuppressWarnings ( "unused" )
    final private static Logger _log           = Logger.getLogger ( HttpAttribute.class );

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

    @Column ( name="attribute_name" )
    public String getAttributeName()
    {
        return super.getAttributeName();
    }

    @Column ( name="attribute_persistence" )
    public String getAttributePersistence()
    {
        return super.getAttributePersistence();
    }
}