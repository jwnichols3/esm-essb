package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder;

import java.util.List;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap;

public interface IDataMapFinder
{
    /**
     * Creates a new instance of the data object that this finder uses
     */
    public IDataMap newInstance();

    /**
     *
     */
    public void createFinderQueries();

    /**
     *
     */
    public List <IDataMap> selectAll();

    /**
     *
     */
    public List <IDataMap> selectAll ( int page_size, int page_num );

    /**
     *
     */
    public int selectCountAll();

    /**
     *
     */
    public IDataMap select ( String row_id );

    /**
     *
     */
    public boolean insert ( IDataMap object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean update ( IDataMap object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean lockForUpdate ( IDataMap object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean insertOrUpdate ( IDataMap object, String username, String hostname, String remote_address, int remote_port );

    /**
     *
     */
    public boolean delete ( IDataMap object, String username, String hostname, String remote_address, int remote_port );
}