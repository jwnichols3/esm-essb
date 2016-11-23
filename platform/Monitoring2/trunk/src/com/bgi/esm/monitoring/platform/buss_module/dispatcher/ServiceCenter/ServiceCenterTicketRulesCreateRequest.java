package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ServiceCenterTicketRulesCreateRequest extends RuleSetBase
{
    public void addRuleInstances ( Digester digester )
    {
		digester.addObjectCreate ( "epmo", "com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenterTicketNew");

        digester.addCallMethod ( "epmo/number",              "setTicketNumber",    0 );
        digester.addCallMethod ( "epmo/category",            "setCategory",        0 );
        digester.addCallMethod ( "epmo/open.time",           "setCreationTime",    0 );
        digester.addCallMethod ( "epmo/opened.by",           "setOpenedBy",        0 );
        digester.addCallMethod ( "epmo/severity.code",       "setSeverityCode",    0 );
        digester.addCallMethod ( "epmo/priority.code",       "setPriorityCode",    0 );
        digester.addCallMethod ( "epmo/assignment",          "setAssignmentGroup", 0 );
        digester.addCallMethod ( "epmo/status",              "setStatus",          0 );
        digester.addCallMethod ( "epmo/close.time",          "setCloseTime",       0 );
        digester.addCallMethod ( "epmo/closed.by",           "setClosedBy",        0 );
        digester.addCallMethod ( "epmo/reference.no",        "setReferenceNum",    0 );
        digester.addCallMethod ( "epmo/action/entry",        "addAction",          0 );
        digester.addCallMethod ( "epmo/resolution/entry",    "addResolution",      0 );
        digester.addCallMethod ( "epmo/contact.name",        "setContactName",     0 );
        digester.addCallMethod ( "epmo/update.action/entry", "addUpdateAction",    0 );
        digester.addCallMethod ( "epmo/actor",               "setActor",           0 );
        digester.addCallMethod ( "epmo/deadline.group",      "setDeadlineGroup",   0 );
        digester.addCallMethod ( "epmo/format",              "setFormat",          0 );
    }
};

