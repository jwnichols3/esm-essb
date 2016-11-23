package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder;

import java.util.List;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter;

public interface IHttpParameterFinder
{
    /**
     * Creates a new instance of the data object that this finder uses
     */
    public IHttpParameter newInstance();

    /**
     *
     */
    public void createFinderQueries();

    /**
     *
     */
    public List <IHttpParameter> selectAll();

    /**
     *
     */
    public List <IHttpParameter> selectAll ( int page_size, int page_num );

    /**
     *
     */
    public int selectCountAll();

    /**
     *
     */
    public IHttpParameter select ( String row_id );

    /**
     *
     */
    public boolean insert ( IHttpParameter object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean update ( IHttpParameter object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean lockForUpdate ( IHttpParameter object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean insertOrUpdate ( IHttpParameter object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean delete ( IHttpParameter object, String username, String hostname, String remote_address, int remote_port );
}