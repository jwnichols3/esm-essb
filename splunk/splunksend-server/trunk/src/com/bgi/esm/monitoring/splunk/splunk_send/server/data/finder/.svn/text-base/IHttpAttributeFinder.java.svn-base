package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder;

import java.util.List;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute;

public interface IHttpAttributeFinder
{
    /**
     * Creates a new instance of the data object that this finder uses
     */
    public IHttpAttribute newInstance();

    /**
     *
     */
    public void createFinderQueries();

    /**
     *
     */
    public List <IHttpAttribute> selectAll();

    /**
     *
     */
    public List <IHttpAttribute> selectAll ( int page_size, int page_num );

    /**
     *
     */
    public int selectCountAll();

    /**
     *
     */
    public IHttpAttribute select ( String row_id );

    /**
     *
     */
    public boolean insert ( IHttpAttribute object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean update ( IHttpAttribute object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean lockForUpdate ( IHttpAttribute object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean insertOrUpdate ( IHttpAttribute object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean delete ( IHttpAttribute object, String username, String hostname, String remote_address, int remote_port );
}