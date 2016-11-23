package com.bgi.esm.monitoring.platform.regression;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *  Retrieves information froM CSM through a web interface.
 */
public class BuildServiceCenterQueuesData
{
    private static SimpleDateFormat sdf_month  = new SimpleDateFormat ( "MMM" );
    private static SimpleDateFormat sdf_year   = new SimpleDateFormat ( "yyyy" );
    private static SimpleDateFormat sdf_date   = new SimpleDateFormat ( "d" );

    public static void main ( String args[] )
    {
    }

    private static String convertDateToURLString ( Date date )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime ( date );

        return convertDateToURLString ( calendar );
    }

    private static String convertDateToURLString ( Calendar calendar )
    {
        String month = sdf_month.format ( calendar );
        String year  = sdf_year.format  ( calendar );
        String date  = sdf_date.format  ( calendar );

        StringBuilder s = new StringBuilder();
        s.append ( month );
        s.append ( "+"   );
        s.append ( date  );
        s.append ( "+"   );
        s.append ( year  );

        return s.toString();
    }

    private static String createRequestURL ( Date date )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime ( date );

        return createRequestURL ( calendar );
    }


    private static String createRequestURL ( Calendar calendar )
    {
        /*
        http://csm/display/incidents_results.cfm?save_as_type=INCIDENT&display_mode=all&save_as_name=Saved+Search&Search=Search&maxrecords=14000&flag=&open_time=EUQ&date_opened_first_dt=Jan+2+2007&date_opened_second_dt=&close_time=ALL&date_closed_first_dt=&date_closed_second_dt=&update_time=ALL&date_last_updated_first_dt=&date_last_updated_second_dt=&includebox=contact_full_name+as+columnorder016_customer&contact_name=ALL&includebox=dept+as+columnorder020_department&dept=ALL&includebox=contact_location+as+columnorder024_contact_location&contact_location=ALL&mrc=ALL&edit_field=Type+MRC&vip=&includebox=assignment+as+columnorder036_assignment&assignment=ALL&assignee_name=ALL&ticket_owner=ALL&edit_field=Type+Ticket+Owner&includebox=opened_by+as+columnorder044_opened_by&opened_by=VPO%3DVPO+auto+open&edit_field=Type+Opened+By&Severity=ALL&status=ALL&includebox=category+as+columnorder056_category&category=ALL&includebox=subcategory+as+columnorder060_subcategory&subcategory=ALL&edit_field=Type+Subcategory&includebox=product_type+as+columnorder064_product_type&Product_Type=ALL&edit_field=Type+Product+Type&includebox=problem_type+as+columnorder068_problem_type&Problem_Type=ALL&closed_by=ALL&edit_field=Type+Closed+By&resolution_mins=&number=&sort=number&brief_description=&bd_search=All&action=&desc_search=All&update_action=&ca_search=All&resolution=&res_search=All&survey_question_1=ALL&survey_join_1=OR&survey_question_2=ALL&survey_join_2=OR&survey_question_3=ALL&survey_join_3=OR&survey_question_4=ALL&survey_join_4=OR&survey_question_5=ALL&survey_comments=&survey_search=All
        */
        String date_string = convertDateToURLString ( calendar );
        StringBuilder url  = new StringBuilder();
        url.append ( "http://csm/display/incidents_results.cfm?" );
        url.append ( "save_as_type=INCIDENT" );
        url.append ( "&display_mode=all" );
        url.append ( "&save_as_name=Saved+Search" );
        url.append ( "&Search=Search" );
        url.append ( "&maxrecords=14000" );
        url.append ( "&flag=" );
        url.append ( "&open_time=EUQ" );
        url.append ( "&date_opened_first_dt=" );
        url.append ( date_string );
        url.append ( "&date_opened_second_dt=" );
        url.append ( "&close_time=ALL" );
        url.append ( "&date_closed_first_dt=" );
        url.append ( "&date_closed_second_dt=" );
        url.append ( "&update_time=ALL" );
        url.append ( "&date_last_updated_first_dt=" );
        url.append ( "&date_last_updated_second_dt=" );
        url.append ( "&includebox=contact_full_name+as+columnorder016_customer" );
        url.append ( "&contact_name=ALL" );
        url.append ( "&includebox=dept+as+columnorder020_department" );
        url.append ( "&dept=ALL" );
        url.append ( "&includebox=contact_location+as+columnorder024_contact_location" );
        url.append ( "&contact_location=ALL" );
        url.append ( "&mrc=ALL" );
        url.append ( "&edit_field=Type+MRC" );
        url.append ( "&vip=" );
        url.append ( "&includebox=assignment+as+columnorder036_assignment" );
        url.append ( "&assignment=ALL" );
        url.append ( "&assignee_name=ALL" );
        url.append ( "&ticket_owner=ALL" );
        url.append ( "&edit_field=Type+Ticket+Owner" );
        url.append ( "&includebox=opened_by+as+columnorder044_opened_by" );
        url.append ( "&opened_by=VPO%3DVPO+auto+open" );
        url.append ( "&edit_field=Type+Opened+By" );
        url.append ( "&Severity=ALL" );
        url.append ( "&status=ALL" );
        url.append ( "&includebox=category+as+columnorder056_category" );
        url.append ( "&category=ALL" );
        url.append ( "&includebox=subcategory+as+columnorder060_subcategory" );
        url.append ( "&subcategory=ALL" );
        url.append ( "&edit_field=Type+Subcategory" );
        url.append ( "&includebox=product_type+as+columnorder064_product_type" );
        url.append ( "&Product_Type=ALL" );
        url.append ( "&edit_field=Type+Product+Type" );
        url.append ( "&includebox=problem_type+as+columnorder068_problem_type" );
        url.append ( "&Problem_Type=ALL" );
        url.append ( "&closed_by=ALL" );
        url.append ( "&edit_field=Type+Closed+By" );
        url.append ( "&resolution_mins=" );
        url.append ( "&number=" );
        url.append ( "&sort=number" );
        url.append ( "&brief_description=" );
        url.append ( "&bd_search=All" );
        url.append ( "&action=" );
        url.append ( "&desc_search=All" );
        url.append ( "&update_action=" );
        url.append ( "&ca_search=All" );
        url.append ( "&resolution=" );
        url.append ( "&res_search=All" );
        url.append ( "&survey_question_1=ALL" );
        url.append ( "&survey_join_1=OR" );
        url.append ( "&survey_question_2=ALL" );
        url.append ( "&survey_join_2=OR" );
        url.append ( "&survey_question_3=ALL" );
        url.append ( "&survey_join_3=OR" );
        url.append ( "&survey_question_4=ALL" );
        url.append ( "&survey_join_4=OR" );
        url.append ( "&survey_question_5=ALL" );
        url.append ( "&survey_comments=" );
        url.append ( "&survey_search=All" );

        return url.toString();
    }
};
