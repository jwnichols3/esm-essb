package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import com.bgi.esm.monitoring.platform.client.RmiProperties;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.Alarmpoint;
import com.bgi.esm.monitoring.platform.shared.value.Audit;
import com.bgi.esm.monitoring.platform.shared.value.Census;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRuleAudit;
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.shared.value.RawOvi;
import com.bgi.esm.monitoring.platform.shared.value.Responder;
import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;
import com.bgi.esm.monitoring.platform.shared.value.Spool;
import com.bgi.esm.monitoring.platform.shared.value.Storm;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRuleAudit;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;
import com.bgi.esm.monitoring.platform.weblogic.TimerBean;
import com.bgi.esm.monitoring.platform.weblogic.TimerEjbHomeRemote;
import com.bgi.esm.monitoring.platform.weblogic.TimerEjbRemote;
import com.bgi.esm.monitoring.platform.weblogic.TimerEjbUtil;

/**
 * Object Relational Mapping Facade.  
 * All database operations pass via this class.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class OrmFacade {

    /////////////////////////////////////////////////////////////
    /// Audit ///////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all audit datum
     * 
     * @return all audit datum, might be empty list (never null).
     */
    public List getAllAudit() {
        AuditFacade af = new AuditFacade();
        return(af.getAllRows());
    }

    public List getAllAuditForMessageId ( String message_id )
    {
        AuditFacade af = new AuditFacade();

        return af.getAllRowsForMessageId ( message_id );
    }

    /**
     * Delete specified audit row
     * 
     * @param arg row to delete
     * @return true success
     * @throws NullPointerException if null arg 
     */
    public boolean deleteAudit(Audit arg) {
        AuditFacade af = new AuditFacade();
        return(af.deleteRow(arg));
    }

    /**
     * Insert or update audit datum. Returns updated value object. 
     * Clients need the update because I might change the key, etc.
     * 
     * @param arg audit datum
     * @return updated row or null if problem
     * @throws NullPointerException if null arg 
     */
    public Audit addOrUpdateAudit(Audit arg) {
        AuditFacade af = new AuditFacade();
        boolean flag = af.addOrUpdateRow(arg);
        if (flag) {
            return(arg);
        }

        return(null);
    }

    /**
     * Return selected audit datum
     * 
     * @param arg audit key
     * @returns selected row or null if not found
     * @throws NullPointerException if null key
     * @throws IllegalArgumentException if empty key
     */
    public Audit selectAudit(String arg) {
        AuditFacade af = new AuditFacade();
        return(af.selectRow(arg));
    }

    /////////////////////////////////////////////////////////////
    /// Throttle (Census) ///////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all census rows
     * 
     * @return all census rows, might be empty list (never null).
     */
    public List getAllCensusLevels() {
        CensusFacade cf = new CensusFacade();
        return(cf.getAllRows());
    }

    /**
     * Delete specified census row
     * 
     * @param census to delete
     * @return true success
     */
    public boolean deleteCensus(Census arg) {
        CensusFacade cf = new CensusFacade();
        return(cf.deleteRow(arg));
    }

    /**
     * Insert or update a census row. Returns fresh value object. 
     * Clients need the update because I might change the key, etc.
     * 
     * @param arg census row
     * @returns updated census row or null if problem
     */
    public Census addOrUpdateCensus(Census arg) {
        CensusFacade cf = new CensusFacade();
        boolean flag = cf.addOrUpdateRow(arg);
        if (flag) {
            return(arg);
        }

        return(null);
    }

    /**
     * Return selected census row.
     * 
     * @param arg key (message group)
     * @returns selected census row or null if not found
     */
    public Census selectCensus(String arg) {
        CensusFacade cf = new CensusFacade();
        return(cf.selectRow(arg));
    }

    /**
     * Select census population by group
     * 
     * @param arg key (message group)
     * @returns selected census row or null if not found
     */
    public List<Census> selectCensusGroup(String arg) {
        CensusFacade cf = new CensusFacade();
        return(cf.selectByGroup(arg));
    }

    /////////////////////////////////////////////////////////////
    /// DataMap /////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all data map rows
     * 
     * @return all data map rows, might be empty list (never null).
     */
    public List getAllDataMapRules() {
        DataMapRuleFacade dmrf = new DataMapRuleFacade();
        return(dmrf.getAllRows());
    }

    public List getAllDataMapRulesPaginate ( int num_per_page, int page_num )
    {
        List list = getAllDataMapRules();

        return paginateList ( list, num_per_page, page_num );
    }


    /**
     * Delete specified data map rule
     * 
     * @param rule to delete
     * @return true success
     */
    public boolean deleteDataMapRule(DataMapRule arg) {
        DataMapRuleFacade dmrf = new DataMapRuleFacade();
        return(dmrf.deleteRow(arg));
    }

    /**
     * Insert or update a data map rule. Returns fresh value object. 
     * Clients need the update because I might change the key, etc.
     * 
     * @param arg data map rule
     * @returns updated data map rule or null if problem
     */
    public DataMapRule addOrUpdateDataMapRule ( DataMapRule arg ) {
        DataMapRuleFacade dmrf = new DataMapRuleFacade();
        boolean was_updated = dmrf.addOrUpdateRow(arg);

        System.out.println ( "Adding or Updating Data Map Rule" );

        if ( true == was_updated ) 
        {
            DataMapRuleAuditFacade dmraf = new DataMapRuleAuditFacade();
            Collection collection        = dmraf.findAuditVersions ( arg.getGroup() );
            DataMapRuleAudit audit       = new DataMapRuleAudit ( arg );
            audit.setAuditVersionNum ( collection.size() + 1 );
            //audit.setAuditModifiedBy ( username );
            dmraf.addOrUpdateRow ( audit );

            System.out.println ( "Updated existing data map rule" );

            return(arg);
        }
        else
        {
            DataMapRuleAuditFacade dmraf = new DataMapRuleAuditFacade();
            Collection collection        = dmraf.findAuditVersions ( arg.getGroup() );
            DataMapRuleAudit audit       = new DataMapRuleAudit ( arg );
            audit.setAuditVersionNum ( collection.size() + 1 );
            //audit.setAuditModifiedBy ( username );
            dmraf.addOrUpdateRow ( audit );

            System.out.println ( "Added a new data map rule" );

            return(null);
        }
    }

    /**
     * Return selected data map rule.
     * 
     * @param arg rule key (message group)
     * @returns selected rule or null if not found
     */
    public DataMapRule selectDataMapRule(String arg) {
        DataMapRuleFacade dmrf = new DataMapRuleFacade();
        return(dmrf.selectRow(arg));
    }

    /**
     * Return selected data map rule.
     * 
     * @param arg rule key (message group)
     * @returns selected rule or null if not found
     */
    public List <DataMapRuleAudit> getDataMapRuleAuditVersions (String group) {
        DataMapRuleAuditFacade dmrf = new DataMapRuleAuditFacade();

        return dmrf.findAuditVersions ( group );
    }

    /////////////////////////////////////////////////////////////
    /// Events By Group /////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    public List getAllEventsByGroup()
    {
        EventsByGroupFacade epf = new EventsByGroupFacade();

        return epf.getAllRows();
    }

    public List getAllEventsByGroup ( int num_per_page, int page_num )
    {
        List list = getAllEventsByGroup();

        return paginateList ( list, num_per_page, page_num );
    }

    public EventsByGroup selectEventsByGroup ( String row_id )
    {
        EventsByGroupFacade epf = new EventsByGroupFacade();

        return epf.selectRow ( row_id );
    }

    public EventsByGroup selectEventsByGroupByMessageId ( String message_id )
    {
        EventsByGroupFacade epf = new EventsByGroupFacade();

        return epf.selectRowMessageId ( message_id );
    }

    public EventsByGroup addOrUpdateEventsByGroup ( EventsByGroup arg )
    {
        EventsByGroupFacade epf = new EventsByGroupFacade();

        boolean flag = epf.addOrUpdateRow(arg);
        if (flag) 
        {
            return(arg);
        }

        return null;
    }

    public boolean deleteEventsByGroup ( EventsByGroup arg )
    {
        EventsByGroupFacade epf = new EventsByGroupFacade();

        return epf.deleteRow ( arg );
    }

    public List findAllEventsByGroupBetweenTimesByGroup ( String group, Calendar start_time, Calendar end_time )
    {
        EventsByGroupFacade epf = new EventsByGroupFacade();
        Timestamp start         = new Timestamp ( start_time.getTime().getTime() );
        Timestamp end           = new Timestamp ( end_time.getTime().getTime()   );

        return epf.findAllBetweenTimesByGroup ( group, start, end );
    }

    public List findAllEventsByGroupBetweenTimesByGroupPaginate ( String group, Calendar start_time, Calendar end_time, int num_per_page, int page_num )
    {
        List list = findAllEventsByGroupBetweenTimesByGroup ( group, start_time, end_time );

        return paginateList ( list, num_per_page, page_num );
    }

    public List findAllEventsByGroupsBetweenTimes ( Calendar start_time, Calendar end_time )
    {
        EventsByGroupFacade epf = new EventsByGroupFacade();
        Timestamp start         = new Timestamp ( start_time.getTime().getTime() );
        Timestamp end           = new Timestamp ( end_time.getTime().getTime()   );

        return epf.findAllBetweenTimes( start, end );
    }

    public List findAllEventsByGroupsBetweenTimesPaginate ( Calendar start_time, Calendar end_time, int num_per_page, int page_num )
    {
        List list = findAllEventsByGroupsBetweenTimes ( start_time, end_time );

        return paginateList ( list, num_per_page, page_num );
    }

    public List findAllBetweenTimesByGroup ( String group, Calendar start_time, Calendar end_time )
    {
        EventsByGroupFacade epf = new EventsByGroupFacade();
        Timestamp start         = new Timestamp ( start_time.getTime().getTime() );
        Timestamp end           = new Timestamp ( end_time.getTime().getTime()   );

        return epf.findAllBetweenTimesByGroup ( group, start, end );
    }

    public List findAllBetweenTimesByGroupPaginate ( String group, Calendar start_time, Calendar end_time, int num_per_page, int page_num )
    {
        List list = findAllBetweenTimesByGroup ( group, start_time, end_time );

        return paginateList ( list, num_per_page, page_num );
    }

    public List findAllBetweenTimesByApplication ( String application, Calendar start_time, Calendar end_time )
    {
        EventsByGroupFacade epf = new EventsByGroupFacade();
        Timestamp start         = new Timestamp ( start_time.getTime().getTime() );
        Timestamp end           = new Timestamp ( end_time.getTime().getTime()   );

        return epf.findAllBetweenTimesByApplication ( application, start, end );
    }

    public List findAllBetweenTimesByApplicationPaginate ( String application, Calendar start_time, Calendar end_time, int num_per_page, int page_num )
    {
        List list = findAllBetweenTimesByApplication ( application, start_time, end_time );

        return paginateList ( list, num_per_page, page_num );
    }

    public List findAllBetweenTimesByGroupApplication ( String group, String application, Calendar start_time, Calendar end_time )
    {
        EventsByGroupFacade epf = new EventsByGroupFacade();
        Timestamp start         = new Timestamp ( start_time.getTime().getTime() );
        Timestamp end           = new Timestamp ( end_time.getTime().getTime()   );

        return epf.findAllBetweenTimesByGroupApplication ( group, application, start, end );
    }

    public List findAllBetweenTimesByGroupApplicationPaginate ( String group, String application, Calendar start_time, Calendar end_time, int num_per_page, int page_num )
    {
        List list = findAllBetweenTimesByGroupApplication ( group, application, start_time, end_time );

        return paginateList ( list, num_per_page, page_num );
    }

    /////////////////////////////////////////////////////////////
    /// EEB Properties //////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    public List getAllEebProperties()
    {
        EebPropertyFacade epf = new EebPropertyFacade();

        return epf.getAllRows();
    }

    public EebProperty selectEebProperty ( String property_name )
    {
        EebPropertyFacade epf = new EebPropertyFacade();

        return epf.selectRow ( property_name );
    }

    public EebProperty addOrUpdateEebProperty ( EebProperty arg )
    {
        EebPropertyFacade epf = new EebPropertyFacade();

        boolean flag = epf.addOrUpdateRow(arg);
        if (flag) 
        {
            return(arg);
        }

        return null;
    }

    public boolean deleteEebProperty ( String property_name )
    {
        EebPropertyFacade epf = new EebPropertyFacade();

        return epf.deleteRow ( property_name );
    }

    public void setEebPropertyServiceCenterActive ( boolean is_active )
    {
        EebPropertyFacade epf = new EebPropertyFacade();

        EebProperty property  = epf.selectRow ( EebProperty.PROPERTY_SERVICECENTER_ACTIVE );

        if ( null == property )
        {
            //_log.warn ( "Could not find property 'ServiceCenter.active'.  Creating..." );

            property = new EebProperty( EebProperty.PROPERTY_SERVICECENTER_ACTIVE );
        }

        if ( is_active )
        {
            property.setPropertyValue ( "true" );
        }
        else
        {
            property.setPropertyValue ( "false" );
        }

        addOrUpdateEebProperty ( property );
    }

    public int getEebPropertyServiceCenterMaxNumUpdates()
    {
        EebPropertyFacade epf = new EebPropertyFacade();

        EebProperty property  = epf.selectRow ( EebProperty.PROPERTY_SERVICECENTER_MAX_NUM_UPDATES );

        if ( null == property )
        {
            return 20;
        }

        int max_num_updates = Integer.parseInt ( property.getPropertyValue() );

        return max_num_updates;
    }

    public boolean getEebPropertyServiceCenterActive()
    {
        EebPropertyFacade epf = new EebPropertyFacade();

        EebProperty property  = epf.selectRow ( EebProperty.PROPERTY_SERVICECENTER_ACTIVE );

        if ( null == property )
        {
            return false;
        }

        if ( property.getPropertyValue().equals ( "true" ) )
        {
            return true;
        }
        else if ( property.getPropertyValue().equals ( "false" ) )
        {
            return false;
        }
        else
        {
            StringBuilder message = new StringBuilder();
                message.append ( "EEB Property '" );
                message.append ( EebProperty.PROPERTY_SERVICECENTER_ACTIVE );
                message.append ( "' must be either 'true' or 'false', but has been found to be: " );
                message.append ( property.getPropertyValue() );
            throw new IllegalStateException ( message.toString() );
        }
    }

    public void setEebPropertyAlarmpointActive ( boolean is_active )
    {
        EebPropertyFacade epf = new EebPropertyFacade();

        EebProperty property  = epf.selectRow ( EebProperty.PROPERTY_ALARMPOINT_ACTIVE );

        if ( null == property )
        {
            property = new EebProperty( EebProperty.PROPERTY_ALARMPOINT_ACTIVE );
        }

        if ( is_active )
        {
            property.setPropertyValue ( "true" );
        }
        else
        {
            property.setPropertyValue ( "false" );
        }

        addOrUpdateEebProperty ( property );

        /*
        try
        {
            TimerEjbRemote ter = getTimerRemote();

            ter.stopTimer ( TimerBean.AP_DRAIN_TIMER );
        }
        catch ( Exception exception )
        {
            exception.printStackTrace();
        }
        //*/
    }

    public boolean getEebPropertyAlarmpointActive()
    {
        EebPropertyFacade epf = new EebPropertyFacade();

        EebProperty property  = epf.selectRow ( EebProperty.PROPERTY_ALARMPOINT_ACTIVE );

        if ( null == property )
        {
            return false;
        }

        if ( property.getPropertyValue().equals ( "true" ) )
        {
            return true;
        }
        else if ( property.getPropertyValue().equals ( "false" ) )
        {
            return false;
        }
        else
        {
            StringBuilder message = new StringBuilder();
                message.append ( "EEB Property '" );
                message.append ( EebProperty.PROPERTY_ALARMPOINT_ACTIVE );
                message.append ( "' must be either 'true' or 'false', but has been found to be: " );
                message.append ( property.getPropertyValue() );
            throw new IllegalStateException ( message.toString() );
        }
    }


    /////////////////////////////////////////////////////////////
    /// Raw OVI /////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all raw OVI datum
     * 
     * @return all raw OVI datum, might be empty list (never null).
     */
    public List getAllRawOvi() {
        RawOviFacade rof = new RawOviFacade();
        return(rof.getAllRows());
    }

    /**
     * Delete specified OVI datum
     * 
     * @param row to delete
     * @return true success
     */
    public boolean deleteRawOvi(RawOvi arg) {
        RawOviFacade rof = new RawOviFacade();
        return(rof.deleteRow(arg));
    }

    /**
     * Insert or update raw OVI datum. Returns updated value object. 
     * Clients need the update because I might change the key, etc.
     * 
     * @param arg raw OVI datum
     * @returns updated row or null if problem
     */
    public RawOvi addOrUpdateRawOvi(RawOvi arg) {
        RawOviFacade rof = new RawOviFacade();
        boolean flag = rof.addOrUpdateRow(arg);
        if (flag) {
            return(arg);
        }

        return(null);
    }

    /**
     * Return selected raw OVI datum
     * 
     * @param arg raw OVI key
     * @returns selected row or null if not found
     */
    public RawOvi selectRawOvi(String arg) {
        RawOviFacade rof = new RawOviFacade();
        return(rof.selectRow(arg));
    }

    /////////////////////////////////////////////////////////////
    /// Suppression /////////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all suppression rules
     * 
     * @return all suppression rules, might be empty list (never null).
     */
    public List <SuppressionRule> getAllSuppressionRules() {
        SuppressionRuleFacade srf = new SuppressionRuleFacade();
        return(srf.getAllRows());
    }

    public List <SuppressionRule> getAllSuppressionNotifications() {
        SuppressionRuleFacade srf = new SuppressionRuleFacade();
        return(srf.getAllSuppressionNotifications());
    }

    /**
     * Return all suppression rules paginated
     * 
     * @return all suppression rules, might be empty list (never null).
     */
    @SuppressWarnings ( value = "unchecked" )
    public List <SuppressionRule> getAllSuppressionRulesPaginate ( int num_per_page, int page_num )
    {
        SuppressionRuleFacade srf = new SuppressionRuleFacade();

        List <SuppressionRule> list = srf.getAllRows();

        return paginateList ( list, num_per_page, page_num );
    }

    public List <SuppressionRule> getAllActiveSuppressionRules()
    {
        SuppressionRuleFacade srf = new SuppressionRuleFacade();

        List list = srf.findAllActiveSuppressions();

        return list;

        /*
        List list = srf.getAllRows();

        SuppressionRule rule = null;
        Iterator i = list.iterator();

        List <SuppressionRule> return_list = new ArrayList <SuppressionRule> ();

        String timezone_string = selectEebProperty ( "server.time_zone" ).getPropertyValue();
        TimeZone tz         = TimeZone.getTimeZone( timezone_string );
        int timezone_offset = tz.getRawOffset();
        int dst_offset      = tz.getDSTSavings();
        int offset          = timezone_offset + dst_offset;

        while ( i.hasNext() )
        {
            rule = (SuppressionRule) i.next();

            if ( rule.isActive( offset ) )
            {
                return_list.add ( rule );
            }
        }

        return return_list;
        //*/
    }

    public List getAllActiveSuppressionRulesPaginate ( int num_per_page, int page_num )
    {
        List list = getAllActiveSuppressionRules();

        return paginateList ( list, num_per_page, page_num );
    }

    public List getAllActiveSuppressionRulesForUser ( String user )
    {
        List list            = getAllActiveSuppressionRules();
        List return_list     = new ArrayList();
        SuppressionRule rule = null;
        Iterator iterator    = list.iterator();

        while ( iterator.hasNext() )
        {
            rule = (SuppressionRule) iterator.next();
        }

        return return_list;
    }

    public List getAllActiveSuppressionRulesForUserPaginate ( String user, int num_per_page, int page_num )
    {
        List list = getAllActiveSuppressionRulesForUser ( user );

        return paginateList ( list, num_per_page, page_num );
    }

    public List searchActiveSuppressionRules 
            ( String user, String description, String app_name, String node_name, String db_server, 
              String message_text, Calendar start_time, Calendar end_time, int remove_on_reboot, int was_deleted  )
    {
        return SuppressionRule.searchSuppressionRules ( getAllActiveSuppressionRules(), user, description, app_name, node_name, db_server,
                    message_text, start_time, end_time, remove_on_reboot, was_deleted );
    }

    public List searchHistoricalSuppressionRules ( String user, String description, String app_name, String node_name, String db_server, 
            String message_text, Calendar start_time, Calendar end_time, int remove_on_reboot, int was_deleted  )
    {
        return SuppressionRule.searchSuppressionRules ( getAllSuppressionRules(), user, description, app_name, node_name, db_server,
                    message_text, start_time, end_time, remove_on_reboot, was_deleted );
    }

    /**
     * Delete specified suppression rule
     * 
     * @param rule to delete
     * @return true success
     */
    public boolean deleteSuppressionRule(SuppressionRule arg) {
        SuppressionRuleFacade srf = new SuppressionRuleFacade();

        boolean flag = srf.deleteRow(arg);

        if ( true == flag )
        {
            SuppressionRuleAuditFacade sraf = new SuppressionRuleAuditFacade();
            Collection collection           = sraf.findAuditVersions ( arg.getSuppressId() );
            SuppressionRuleAudit audit      = new SuppressionRuleAudit ( arg );
            audit.setAuditVersionNum ( collection.size() + 1 );

            sraf.addOrUpdateRow ( audit );
        }

        return flag;
    }

    /**
     * Insert or update a suppression rule. Returns updated value object.
     * Clients need the update because I might change the key, etc.
     * 
     * @param arg suppression rule
     * @returns updated suppression rule or null if problem
     */
    public SuppressionRule addOrUpdateSuppressionRule(SuppressionRule arg) {
        SuppressionRuleFacade srf = new SuppressionRuleFacade();
        boolean flag = srf.addOrUpdateRow(arg, this);
        if (flag) 
        {
            SuppressionRuleAuditFacade sraf = new SuppressionRuleAuditFacade();
            Collection collection           = sraf.findAuditVersions ( arg.getSuppressId() );
            SuppressionRuleAudit audit      = new SuppressionRuleAudit ( arg );
            audit.setAuditVersionNum ( collection.size() + 1 );

            sraf.addOrUpdateRow ( audit );

            return(arg);
        }

        return(null);
    }

    /**
     * Return selected suppression rule.
     * 
     * @param arg rule key
     * @returns selected rule or null if not found
     */
    public SuppressionRule selectSuppressionRule(String arg) {
        SuppressionRuleFacade srf = new SuppressionRuleFacade();
        return(srf.selectRow(arg));
    }

    public SuppressionRule selectSuppressionRuleBySuppressId ( long suppress_id )
    {
        SuppressionRuleFacade srf = new SuppressionRuleFacade();

        return(srf.selectRowBySuppressId( suppress_id ));
    }

    public List<SuppressionRuleAudit> getSuppressionRuleAuditVersions ( long suppress_id )
    {
        SuppressionRuleAuditFacade sraf = new SuppressionRuleAuditFacade();

        return sraf.findAuditVersions ( suppress_id );
    }

    /////////////////////////////////////////////////////////////
    /// Throttle (Spool) ////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all spool rows
     * 
     * @return all spool rows, might be empty list (never null).
     */
    public List getAllSpoolRows() {
        SpoolFacade sf = new SpoolFacade();
        return(sf.getAllRows());
    }

    /**
     * Delete specified spool row
     * 
     * @param spool to delete
     * @return true success
     */
    public boolean deleteSpoolRow(Spool arg) {
        SpoolFacade sf = new SpoolFacade();
        return(sf.deleteRow(arg));
    }

    /**
     * Insert or update a spool row. Returns fresh value object. 
     * Clients need the update because I might change the key, etc.
     * 
     * @param arg spool row
     * @returns updated spool row or null if problem
     */
    public Spool addOrUpdateSpoolRow(Spool arg) {
        SpoolFacade sf = new SpoolFacade();
        boolean flag = sf.addOrUpdateRow(arg);
        if (flag) {
            return(arg);
        }

        return(null);
    }

    /**
     * Return selected spool row.
     * 
     * @param arg key (message group)
     * @returns selected spool row or null if not found
     */
    public Spool selectSpoolRow(String arg) {
        SpoolFacade sf = new SpoolFacade();
        return(sf.selectRow(arg));
    }

    /////////////////////////////////////////////////////////////
    /// Throttle (Storm) ////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all storm rows
     * 
     * @return all storm rows, might be empty list (never null).
     */
    public List getAllStormLevels() {
        StormFacade sf = new StormFacade();
        return(sf.getAllRows());
    }

    /**
     * Delete specified storm row
     * 
     * @param storm to delete
     * @return true success
     */
    public boolean deleteStorm(Storm arg) {
        StormFacade sf = new StormFacade();
        return(sf.deleteRow(arg));
    }

    /**
     * Insert or update a storm level. Returns fresh value object. Clients need
     * the update because I might change the key, etc.
     * 
     * @param arg storm level
     * @returns updated storm level or null if problem
     */
    public Storm addOrUpdateStorm(Storm arg) {
        StormFacade sf = new StormFacade();
        boolean flag = sf.addOrUpdateRow(arg);
        if (flag) {
            return(arg);
        }

        return(null);
    }

    /**
     * Return selected storm level.
     * 
     * @param arg key (message group)
     * @returns selected storm level or null if not found
     */
    public Storm selectStorm(String arg) {
        StormFacade sf = new StormFacade();
        return(sf.selectRow(arg));
    }

    public List getAllActiveStorms()
    {
        StormFacade sf = new StormFacade();

        return sf.getAllActiveRows();
    }

    /////////////////////////////////////////////////////////////
    /// Throttle (Rules) ////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all throttle rows
     * 
     * @return all throttle rows, might be empty list (never null).
     */
    public List getAllThrottleRules() {
        ThrottleRuleFacade trf = new ThrottleRuleFacade();
        return(trf.getAllRows());
    }

    /**
     * Delete specified throttle rule
     * 
     * @param rule
     *            to delete
     * @return true success
     */
    public boolean deleteThrottleRule(ThrottleRule arg) {
        ThrottleRuleFacade trf = new ThrottleRuleFacade();
        return(trf.deleteRow(arg));
    }

    /**
     * Insert or update a throttle rule. Returns updated value object. Clients
     * need the update because I might change the key, etc.
     * 
     * @param arg throttle rule
     * @returns updated throttle rule or null if problem
     */
    public ThrottleRule addOrUpdateThrottleRule(ThrottleRule arg) {
        ThrottleRuleFacade trf = new ThrottleRuleFacade();
        boolean flag = trf.addOrUpdateRow(arg);
        if (flag) {
            return(arg);
        }

        return(null);
    }

    /**
     * Return selected throttle rule.
     * 
     * @param arg rule key
     * @returns selected rule or null if not found
     */
    public ThrottleRule selectThrottleRule(String arg) {
        ThrottleRuleFacade trf = new ThrottleRuleFacade();
        return(trf.selectRow(arg));
    }

    /**
     * Select throttle rules by group
     * 
     * @param arg key (message group)
     * @returns selected throttle rules or null if not found
     */
    public List<ThrottleRule> selectThrottleRuleGroup(String arg) {
        ThrottleRuleFacade trf = new ThrottleRuleFacade();
        return(trf.selectByGroup(arg));
    }

    /////////////////////////////////////////////////////////////
    /// Service Center records (ServiceCenter) //////////////////
    /////////////////////////////////////////////////////////////
    //
    /**
     * Insert or update a Service Center record. Returns updated value object. Clients
     * need the update because I might change the key, etc.
     * 
     * @param arg service center entry
     * @returns updated throttle rule or null if problem
     */
    public ServiceCenter addOrUpdateServiceCenter(ServiceCenter arg) {
        ServiceCenterFacade scf = new ServiceCenterFacade();
        boolean flag = scf.addOrUpdateRow(arg);
        if (flag) {
            return(arg);
        }

        return(null);
    }

    public List getAllServiceCenterTickets()
    {
        ServiceCenterFacade scf = new ServiceCenterFacade();

        return scf.getAllRows();
    }

    public ServiceCenter selectServiceCenterTicket ( String ticket_number )
    {
        ServiceCenterFacade scf = new ServiceCenterFacade();

        return scf.selectRow ( ticket_number );
    }

    /**
     * Delete queued Service Center ticket
     * 
     * @param rule to delete
     * @return true success
     */
    public boolean deleteServiceCenter(ServiceCenter arg) {
        ServiceCenterFacade scf = new ServiceCenterFacade();
        return(scf.deleteRow(arg));
    }

    /**
     * Return selected ServiceCenter ticket keyed by Openview event ID
     *
     * @param eventId  the Openview Event ID that generated this ticket
     * @return the details of the Service Center ticket associated 
     *         with this Openview event
     */
    public ServiceCenter selectTicketByEventId ( String eventID ) {
        List list  = getAllServiceCenterTickets();
        Iterator i = list.iterator();

        while ( i.hasNext() )
        {
            ServiceCenter sc = (ServiceCenter) i.next();
            if ( sc.getMessageId().equals ( eventID ) )
            {
                return sc;
            }
        }

        return null;
    }

    /**
     * Return selected ServiceCenter ticket keyed by Service Center 
     * ticket number
     *
     * @param ticketNumber the Service Center ticket number
     * @return the details of the Service Center ticket associated 
     *         with this ticket number
     */
    public ServiceCenter selectTicketByTicketNumber ( String ticketNumber ) {
        ServiceCenterFacade scf = new ServiceCenterFacade();

        return ( scf.findByTicketNumber ( ticketNumber ) );
    }

    public ServiceCenter selectTicketByMessageKey ( String message_key )
    {
        ServiceCenterFacade scf = new ServiceCenterFacade();

        return ( scf.findByMessageKey ( message_key ) );
    }

    /////////////////////////////////////////////////////////////
    /// Service Center Buffer records (ServiceCenter) ///////////
    /////////////////////////////////////////////////////////////
    //
    /**
     * Insert or update a Service Center record. Returns updated value object. Clients
     * need the update because I might change the key, etc.
     * 
     * @param arg service center entry
     * @returns updated throttle rule or null if problem
     */
    public ServiceCenter addOrUpdateServiceCenterBuffer(ServiceCenter arg) {
        ServiceCenterBufferFacade scf = new ServiceCenterBufferFacade();
        boolean flag = scf.addOrUpdateRow(arg);
        if (flag) {
            return(arg);
        }

        return(null);
    }

    public long getNumServiceCenterTicketsInBuffer()
    {
        ServiceCenterBufferFacade scf = new ServiceCenterBufferFacade();

        return scf.countRecords();
    }

    public List getAllServiceCenterTicketsInBuffer()
    {
        ServiceCenterBufferFacade scf = new ServiceCenterBufferFacade();

        return scf.getAllRows();
    }

    /**
     * Delete queued Service Center ticket
     * 
     * @param rule to delete
     * @return true success
     */
    public boolean deleteServiceCenterBuffer(ServiceCenter arg) {
        ServiceCenterBufferFacade scf = new ServiceCenterBufferFacade();
        return(scf.deleteRow(arg));
    }

    /*
    public ServiceCenter findOldestServiceCenterTicketInBuffer()
    {
        ServiceCenterBufferFacade scf = new ServiceCenterBufferFacade();

        return scf.findOldest();
    }

    public ServiceCenter findNewestServiceCenterTicketInBuffer()
    {
        ServiceCenterBufferFacade scf = new ServiceCenterBufferFacade();

        return scf.findNewest();
    }
    //*/

    /////////////////////////////////////////////////////////////
    /// Alarmpoint records (Alarmpoint) /////////////////////////
    /////////////////////////////////////////////////////////////
    /**
     * Insert or update a Service Center record. Returns updated value object. Clients
     * need the update because I might change the key, etc.
     * 
     * @param arg throttle rule
     * @returns updated throttle rule or null if problem
     */
    public Alarmpoint addOrUpdateAlarmpoint(Alarmpoint arg) {
        AlarmpointFacade apf = new AlarmpointFacade();
        boolean flag         = apf.addOrUpdateRow(arg);
        if (flag) {
            return(arg);
        }

        return(null);
    }

    public List getAllAlarmpointEvents()
    {
        AlarmpointFacade apf = new AlarmpointFacade();

        return apf.getAllRows();
    }

    public Alarmpoint selectAlarmpointEventByEventID ( String event_id )
    {
        AlarmpointFacade apf = new AlarmpointFacade();
        List list            = apf.getAllRows();
        Iterator i           = list.iterator();

        while ( i.hasNext() )
        {
            Alarmpoint a = (Alarmpoint) i.next();

            if ( a.getEventId().equals ( event_id ) )
            {
                return a;
            }
        }

        return null;
    }

    public boolean deleteAlarmpoint ( Alarmpoint arg )
    {
        AlarmpointFacade apf = new AlarmpointFacade();

        return apf.deleteRow ( arg );
    }

    /////////////////////////////////////////////////////////////
    /// Responder records (Dispatcher) /////////////////////////
    /////////////////////////////////////////////////////////////
    /**
     * Insert or update a responder record. Returns updated value object. Clients
     * need the update because I might change the key, etc.
     * 
     * @param arg responder record
     * @returns updated throttle rule or null if problem
     */
    public Responder addOrUpdateRow (Responder arg) {
        ResponderFacade scf = new ResponderFacade();
        boolean flag = scf.addOrUpdateRow(arg);
        if (flag) {
            return(arg);
        }

        return(null);
    }

    public List findAllResponderByServiceCenterTicketNumber ( String ticket_number )
    {
        ResponderFacade rf = new ResponderFacade();

        List responders = rf.findAllByTicketNumber ( ticket_number );

        return responders;
    }

    public Responder findResponderByServiceCenterTicketNumber ( String ticket_number )
    {
        ResponderFacade rf = new ResponderFacade();

        Responder responder = rf.findByServiceCenterTicketNumber ( ticket_number );

        return responder;
    }

    public List getAllResponderIncidents()
    {
        ResponderFacade scf = new ResponderFacade();

        return scf.getAllRows();
    }
    
    /**
     * Delete specified responder row
     * 
     * @param arg row to delete
     * @return true success
     * @throws NullPointerException if null arg 
     */
    public boolean deleteResponder(Responder arg) {
        ResponderFacade af = new ResponderFacade();
        return(af.deleteRow(arg));
    }


    public Responder selectResponderByMessageId ( String message_id )
    {
        ResponderFacade rf = new ResponderFacade();

        return rf.findByMessageId ( message_id );
    }

    public Responder selectResponderByMessageKey ( String message_key )
    {
        ResponderFacade rf = new ResponderFacade();

        return rf.findByMessageKeyLocal ( message_key );
    }

    public Responder selectResponderByMessageKey ( String message_key, Calendar searchTimestamp )
    {
        ResponderFacade rf  = new ResponderFacade();
        Timestamp timestamp = new Timestamp ( searchTimestamp.getTimeInMillis() );

        return rf.findByMessageKeyLocal ( message_key, timestamp );
    }

    public Responder selectResponder ( String row_id )
    {
        ResponderFacade rf = new ResponderFacade();

        return rf.selectRow ( row_id );
    }

    /////////////////////////////////////////////////////////////
    // Test the monotonic sequence generator ////////////////////
    /////////////////////////////////////////////////////////////
    public int nextMonotonicSequence()
    {
        MonotonicFacade mf = new MonotonicFacade();

        return mf.getSequenceValue();
    }

    public int nextMonotonicSequenceForDataMap()
    {
        MonotonicDataMapFacade mf = new MonotonicDataMapFacade();

        return mf.getSequenceValue();
    }

    public int nextMonotonicSequenceForSuppression()
    {
        MonotonicSuppressionFacade mf = new MonotonicSuppressionFacade();

        return mf.getSequenceValue();
    }

    public int nextMonotonicSequenceForThrottle()
    {
        MonotonicThrottleFacade mf = new MonotonicThrottleFacade();

        return mf.getSequenceValue();
    }

    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    private static List paginateList ( List list, int num_per_page, int page_num )
    {
        if ( list.size() < num_per_page )
        {
            return list;
        }

        if  ( page_num < 1 )
        {
            return null;
        }

        int num_remainder = list.size() % num_per_page;
        int num_pages     = list.size() / num_per_page + (( num_remainder > 0 )? 1 : 0 );

        if ( page_num > num_pages )
        {
            return null;
        }

        ArrayList <Object> return_list = new ArrayList <Object> ();
        int start_index  = num_per_page * page_num - num_per_page;
        int end_index    = num_per_page * page_num;
        end_index = ( end_index < list.size() )? end_index : list.size();

        for ( int counter = start_index; counter < end_index; counter++ )
        {
            Object o = list.get ( counter );
            return_list.add ( o );
        }

        return return_list; 
    }

    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    private TimerEjbRemote getTimerRemote() throws BackEndFailure 
    {
        try 
        {
            TimerEjbHomeRemote tehr = TimerEjbUtil.getHome(RmiProperties.getJndiHashtable());
            return(tehr.create());
        } 
        catch(Exception exception) 
        {
            //_log.error(BackEndFailure.RMI_FAILURE, exception);
            throw new BackEndFailure();
        }
    }

}
