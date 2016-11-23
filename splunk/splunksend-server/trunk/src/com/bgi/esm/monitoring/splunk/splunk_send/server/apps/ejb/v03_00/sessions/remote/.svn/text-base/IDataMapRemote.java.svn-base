package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.remote;

import java.util.List;
import javax.ejb.Remote;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap;

@Remote
public interface IDataMapRemote
{
    /**
     *  Selects all objects
     *
     *  @return a list of all objects in the persistence layer if successful, null otherwise
     */
    public List <IDataMap> selectAllRemote ();
    /**
     *  Selects all objects
     *
     *  @param page_num For pagination; the page num.
     *  @param page_size For pagination, the size of the page
     *  @return the object that was inserted if successful, null otherwise
     */
    public List <IDataMap> selectAllRemote ( int page_size, int page_num );

    /**
     *  Inserts the specified object
     *
     *  @param object the object to insert
     *  @return the object that was inserted if successful, null otherwise
     */
    public IDataMap insertRemote ( IDataMap object );

    /**
     *  Updates the specified object
     *
     *  @param object the object to update
     *  @return the object that was updated if successful, null otherwise
     */
    public IDataMap updateRemote ( IDataMap object );

    /**
     *  Inserts/updates the specified object
     *
     *  @param object the object to insert/update
     *  @return the object that was inserted/updated if successful, null otherwise
     */
    public IDataMap insertOrUpdateRemote ( IDataMap object );

    /**
     *  Deletes the specified object
     *
     *  @param object the object to delete
     *  @return the object that was deleted if successful, null otherwise
     */
    public boolean deleteRemote ( IDataMap object );

    /**
     *  Selects the specified object
     *
     *  @param object the id of the object to select
     *  @return the object that was if found, null otherwise
     */
    public IDataMap selectRemote ( String row_id );
}