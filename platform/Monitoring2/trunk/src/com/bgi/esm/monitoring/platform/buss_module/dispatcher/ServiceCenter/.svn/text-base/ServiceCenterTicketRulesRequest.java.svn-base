package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ServiceCenterTicketRulesRequest extends RuleSetBase
{
    public void addRuleInstances ( Digester digester )
    {
		digester.addObjectCreate ( "recordset/probsummary", "com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenterTicketNew");

        digester.addCallMethod ( "recordset/probsummary/number",              "setTicketNumber",    0 );
        digester.addCallMethod ( "recordset/probsummary/category",            "setCategory",        0 );
        digester.addCallMethod ( "recordset/probsummary/open.time",           "setCreationTime",    0 );
        digester.addCallMethod ( "recordset/probsummary/opened.by",           "setOpenedBy",        0 );
        digester.addCallMethod ( "recordset/probsummary/severity.code",       "setSeverityCode",    0 );
        digester.addCallMethod ( "recordset/probsummary/priority.code",       "setPriorityCode",    0 );
        digester.addCallMethod ( "recordset/probsummary/assignment",          "setAssignmentGroup", 0 );
        digester.addCallMethod ( "recordset/probsummary/status",              "setStatus",          0 );
        digester.addCallMethod ( "recordset/probsummary/close.time",          "setCloseTime",       0 );
        digester.addCallMethod ( "recordset/probsummary/closed.by",           "setClosedBy",        0 );
        digester.addCallMethod ( "recordset/probsummary/reference.no",        "setReferenceNum",    0 );
        digester.addCallMethod ( "recordset/probsummary/action/entry",        "addAction",          0 );
        digester.addCallMethod ( "recordset/probsummary/resolution/entry",    "addResolution",      0 );
        digester.addCallMethod ( "recordset/probsummary/contact.name",        "setContactName",     0 );
        digester.addCallMethod ( "recordset/probsummary/update.action/entry", "addUpdateAction",    0 );
        digester.addCallMethod ( "recordset/probsummary/actor",               "setActor",           0 );
        digester.addCallMethod ( "recordset/probsummary/deadline.group",      "setDeadlineGroup",   0 );
        digester.addCallMethod ( "recordset/probsummary/format",              "setFormat",          0 );
    }
};
