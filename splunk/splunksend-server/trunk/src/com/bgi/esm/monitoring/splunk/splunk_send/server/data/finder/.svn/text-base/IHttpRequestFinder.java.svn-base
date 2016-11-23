package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder;

import java.util.List;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;

public interface IHttpRequestFinder
{
    /**
     * Creates a new instance of the data object that this finder uses
     */
    public IHttpRequest newInstance();

    /**
     *
     */
    public void createFinderQueries();

    /**
     *
     */
    public List <IHttpRequest> selectAll();

    /**
     *
     */
    public List <IHttpRequest> selectAll ( int page_size, int page_num );

    /**
     *
     */
    public int selectCountAll();

    /**
     *
     */
    public IHttpRequest select ( String row_id );

    /**
     *
     */
    public boolean insert ( IHttpRequest object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean update ( IHttpRequest object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean lockForUpdate ( IHttpRequest object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean insertOrUpdate ( IHttpRequest object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean delete ( IHttpRequest object, String username, String hostname, String remote_address, int remote_port );
}