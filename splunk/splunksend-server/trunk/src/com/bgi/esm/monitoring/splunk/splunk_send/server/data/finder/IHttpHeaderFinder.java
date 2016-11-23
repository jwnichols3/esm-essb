package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder;

import java.util.List;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader;

public interface IHttpHeaderFinder
{
    /**
     * Creates a new instance of the data object that this finder uses
     */
    public IHttpHeader newInstance();

    /**
     *
     */
    public void createFinderQueries();

    /**
     *
     */
    public List <IHttpHeader> selectAll();

    /**
     *
     */
    public List <IHttpHeader> selectAll ( int page_size, int page_num );

    /**
     *
     */
    public int selectCountAll();

    /**
     *
     */
    public IHttpHeader select ( String row_id );

    /**
     *
     */
    public boolean insert ( IHttpHeader object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean update ( IHttpHeader object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean lockForUpdate ( IHttpHeader object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean insertOrUpdate ( IHttpHeader object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean delete ( IHttpHeader object, String username, String hostname, String remote_address, int remote_port );
}