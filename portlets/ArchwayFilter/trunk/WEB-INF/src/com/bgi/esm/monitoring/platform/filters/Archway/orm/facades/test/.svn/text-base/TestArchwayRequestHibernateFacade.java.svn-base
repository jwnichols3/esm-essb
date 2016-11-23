package com.bgi.esm.monitoring.platform.filters.Archway.orm.facades.test;

import java.util.Calendar;
import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.filters.Archway.orm.facades.AbstractArchwayRequestFacade;
import com.bgi.esm.monitoring.platform.filters.Archway.orm.facades.ArchwayRequestHibernateFacade;
import com.bgi.esm.monitoring.platform.filters.Archway.orm.beans.ArchwayRequestBean;

public class TestArchwayRequestHibernateFacade extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestArchwayRequestHibernateFacade.class );

    private static AbstractArchwayRequestFacade _orm = null;


    public TestArchwayRequestHibernateFacade ( String param )
    {
        super ( param );

        if ( null == _orm )
        {
            _orm = new ArchwayRequestHibernateFacade();
        }
    }

    private ArchwayRequestBean createTestArchwayRequestBean()
    {
        ArchwayRequestBean object = new ArchwayRequestBean();

        return object;
    }

    public ArchwayRequestBean createUpdateArchwayRequestBean()
    {
        ArchwayRequestBean object = new ArchwayRequestBean();

        return object;
    }

    public void testSelectInsertUpdate()
    {
        ArchwayRequestBean object = createTestArchwayRequestBean();

        assertFalse ( "Could not insert new ArchwayRequestBean object", _orm.insertOrUpdateRow ( object ) );

        long request_id = object.getRequestID();

        ArchwayRequestBean selected = _orm.select ( request_id );
        assertNotNull ( "Could not find object that was originally inserted", selected );
        assertTrue ( "The saved object and the retrieved object are not equal", object.equals ( selected ) );

        ArchwayRequestBean update_original = createUpdateArchwayRequestBean();
        selected.setValue ( update_original );
        assertTrue ( "Could not update ArchwayRequestBean object", _orm.insertOrUpdateRow ( selected ) );

        ArchwayRequestBean update_selected = _orm.select ( request_id );
        assertNotNull ( "Could nto find object that was just updated", update_selected );
        assertTrue ( "Updated object was not persisted correctly", update_original.equals ( update_selected ) );
    }
}
