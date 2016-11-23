package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.local;

import java.util.List;
import javax.ejb.Local;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute;

@Local
public interface IHttpAttributeLocal
{
    /**
     *  Selects all objects
     *
     *  @return a list of all objects in the persistence layer if successful, null otherwise
     */
    public List <IHttpAttribute> selectAllLocal ();
    /**
     *  Selects all objects
     *
     *  @param page_num For pagination; the page num.
     *  @param page_size For pagination, the size of the page
     *  @return the object that was inserted if successful, null otherwise
     */
    public List <IHttpAttribute> selectAllLocal ( int page_size, int page_num );

    /**
     *  Inserts the specified object
     *
     *  @param object the object to insert
     *  @return the object that was inserted if successful, null otherwise
     */
    public IHttpAttribute insertLocal ( IHttpAttribute object );

    /**
     *  Updates the specified object
     *
     *  @param object the object to update
     *  @return the object that was updated if successful, null otherwise
     */
    public IHttpAttribute updateLocal ( IHttpAttribute object );

    /**
     *  Inserts/Updates the specified object
     *
     *  @param object the object to insert/update
     *  @return the object that was inserted/updated if successful, null otherwise
     */
    public IHttpAttribute insertOrUpdateLocal ( IHttpAttribute object );

    /**
     *  Deletes the specified object
     *
     *  @param object the object to delete
     *  @return the object that was deleted if successful, null otherwise
     */
    public boolean deleteLocal ( IHttpAttribute object );

    /**
     *  Selects the specified object
     *
     *  @param object the id of the object to select
     *  @return the object that was if found, null otherwise
     */
    public IHttpAttribute selectLocal ( String row_id );
}