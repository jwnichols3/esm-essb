package com.bgi.esm.monitoring.platform.test;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

/**
 * Base class that the RMI test cases inherit from in order to provide
 * a common set of functionality and common values.
 * 
 * @author Dennis Lin (linden)
 */
public class BaseClientScript
{
    final public static String TEST_USER                      = "test-user";
    final public static String TEST_APPLICATION               = "test-application";
    final public static String TEST_NODE                      = "test-node";
    final public static String TEST_DATASERVER                = "test-dataserver";
    final public static String TEST_MESSAGE                   = "test-message";
    final public static String TEST_DESCRIPTION               = "test description";
    final public static String TEST_DESCRIPTION_MULTI_LINE    = "test description 01\ntest description 02\ntest description 03\n";
    final public static String TEST_DATAMAP_GROUP             = "test-esm";
    final public static String TEST_DATAMAP_METHOD_TICKET     = "ticket";
    final public static String TEST_DATAMAP_METHOD_ALARMPOINT = "alarmpoint";
    final public static String TEST_ALARMPOINT_GROUP          = "esm-test-group";
    final public static String TEST_ALARMPOINT_SCRIPT         = "BGI VPO";
    final public static String TEST_SERVICECENTER_CATEGORY    = "test-category";
    final public static String TEST_SERVICECENTER_SUBCATEGORY = "test-subcategory";
    final public static String TEST_SERVICECENTER_PRODUCT     = "test-product";
    final public static String TEST_SERVICECENTER_PROBLEM     = "test-problem";
    final public static String TEST_SERVICECENTER_ASSIGNMENT  = "ESM";
    final public static String TEST_SERVICECENTER_LOCATION    = "global";

    protected BackEndFacade bef = null;

    protected BaseClientScript()
    {
        bef = new BackEndFacade();
    }
};
