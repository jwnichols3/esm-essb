package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder;

import java.util.List;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;

public interface IHttpCookieFinder
{
    /**
     * Creates a new instance of the data object that this finder uses
     */
    public IHttpCookie newInstance();

    /**
     *
     */
    public void createFinderQueries();

    /**
     *
     */
    public List <IHttpCookie> selectAll();

    /**
     *
     */
    public List <IHttpCookie> selectAll ( int page_size, int page_num );

    /**
     *
     */
    public int selectCountAll();

    /**
     *
     */
    public IHttpCookie select ( String row_id );

    /**
     *
     */
    public boolean insert ( IHttpCookie object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean update ( IHttpCookie object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean lockForUpdate ( IHttpCookie object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean insertOrUpdate ( IHttpCookie object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean delete ( IHttpCookie object, String username, String hostname, String remote_address, int remote_port );
}