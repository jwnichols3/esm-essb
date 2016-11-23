package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.jpa;

import java.lang.ref.WeakReference;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject;

public abstract class JPAFinderUtilities
{
    final private static Logger _log        = Logger.getLogger ( JPAFinderUtilities.class );
    private static EntityManagerFactory emf = null;

    public static EntityManagerFactory getEntityManagerFactory()
    {
        if ( null == emf )
        {
            synchronized ( JPAFinderUtilities.class )
            {
                try
                {
                    if ( null == emf )
                    {
                        if ( _log.isInfoEnabled() )
                        {
                            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                                message.get().append ( JPAFinderUtilities.class.getName() );
                                message.get().append ( "::getEntityManagerFactory() - begin initializing EntityManagerFactory at " );
                                message.get().append ( CommonObject.sdf.format ( new Date() ) );
                            _log.info ( message.get().toString() );
                        }
                        emf = Persistence.createEntityManagerFactory ( "JPAFinder" );
                        if ( _log.isInfoEnabled() )
                        {
                            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                                message.get().append ( JPAFinderUtilities.class.getName() );
                                message.get().append ( "::getEntityManagerFactory() - finish initializing EntityManagerFactory at " );
                                message.get().append ( CommonObject.sdf.format ( new Date() ) );
                            _log.info ( message.get().toString() );
                        }
                    }
                }
                catch ( RuntimeException exception )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( JPAFinderUtilities.class.getName() );
                        message.get().append ( " -- could not instantiate JPA.  Maybe JPA libraries are not included in the classpath?" );
                    _log.fatal ( message.get().toString(), exception );
                }
            }
        }

        return emf;
    }
}