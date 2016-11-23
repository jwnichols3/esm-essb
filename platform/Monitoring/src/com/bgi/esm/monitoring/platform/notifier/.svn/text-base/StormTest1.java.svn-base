package com.bgi.esm.monitoring.platform.notifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.SuppressionQueue;
import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;

import com.bgi.esm.monitoring.platform.value.DateOnly;
import com.bgi.esm.monitoring.platform.value.TicketMessage;
import com.bgi.esm.monitoring.platform.value.SuppressionMessage;
import com.bgi.esm.monitoring.platform.value.TimeOnly;

/**

 *
 * @author G.S. Cole (guycole at gmail dot com)
 * @version $Id$
 */

public class StormTest1 {

    /**
     * Process this OVO message
     *
     * @param args OVO message elements, must be 16
     * @throws Exception for anything
     */
    public void execute(int arg) throws Exception {
 	String queue_name = StaticConfiguration.getString("queue.suppression.name");
	SuppressionQueue sq = new SuppressionQueue(queue_name);

	int message_id = _random.nextInt();

	for (int ii = 0; ii < arg; ii++) {
	    TicketMessage tm = new TicketMessage();
	    tm.setMessageId(Integer.toHexString(message_id++));

	    int source_node_ndx = selectSourceNode();
	    tm.setSourceNode(_all_source_nodes[source_node_ndx]);
	    tm.setSourceNodeType(_all_source_node_types[source_node_ndx]);

	    tm.setCreationDate(new DateOnly());
	    tm.setCreationTime(new TimeOnly());
	    tm.setReceiveDate(new DateOnly());
	    tm.setReceiveTime(new TimeOnly());

	    tm.setApplication(selectApplication());
	    tm.setMessageGroup(selectMessageGroup());
	    tm.setObject("bogus object");
	    tm.setSeverity(selectSeverity());
	    tm.setOperators(selectOperators());
	    tm.setMessageText("bogus text");
	    tm.setInstructionText("bogus instruction");
	    tm.setAttributes(selectAttributes());
	    tm.setSuppressCount(0);

	    System.out.println(tm);

	    SuppressionMessage sm = new SuppressionMessage();
	    sm.setTicketMessage(tm);

	    sq.queueWriter(sm);
	}

	sq.shutDown();
	
	_log.debug("done");
    }

    /**
     *
     * @return map of attributes
     */
    private Map selectAttributes() {
	HashMap result = new HashMap();
	
	result.put("key1", "value1");
	result.put("key2", "value2");
	result.put("key3", "value3");
	
	return(result);
    }

    /**
     *
     * @return list of String w/each operator in a single string
     */
    private List selectOperators() {
	ArrayList result = new ArrayList();

	result.add("op1");
	result.add("op2");
	result.add("op3");
	
	return(result);
    }

    /**
     *
     */
    private int selectSourceNode() {
	int limit = _all_source_nodes.length;
	return(_random.nextInt(limit));
    }

    /**
     *
     */
    private String selectApplication() {
	int limit = _all_applications.length;
	return(_all_applications[_random.nextInt(limit)]);
    }

    /**
     *
     */
    private String selectMessageGroup() {
	//	int limit = _all_groups.length;
	int limit = 10;
	return(_all_groups[_random.nextInt(limit)]);
    }

    /**
     *
     */
    private String selectSeverity() {
	int limit = _severity.length;
	return(_severity[_random.nextInt(limit)]);
    }

    /**
     * Main entry point for the Notifier.
     *
     * @param args OVO message elements, must be 16
     * @throws Exception for anything
     */
    public static void main(String[] args) throws Exception {
	StormTest1 st1 = new StormTest1();
	st1.execute(250);
    }

    private final String[] _severity = {
      "normal",  "minor",  "major",  "warning",  "critical"
    };

    private final String[] _all_source_nodes = {
      "node0",  "node1",  "node2",  "node3",  "node4",  
      "node5",  "node6",  "node7",  "node8",  "node9"
    };

    private final String[] _all_source_node_types = {
      "Sun SPARC (HTTPS)",  
      "HP-UX PA-RISC (HTTPS)",  
      "Windoze WinTel",
      "Red Hat Intel",
      "Sun SPARC (HTTPS)",  
      "HP-UX PA-RISC (HTTPS)",  
      "Windoze WinTel",
      "Red Hat Intel",
      "Sun SPARC (HTTPS)",  
      "HP-UX PA-RISC (HTTPS)"
    };

    private final String[] _all_applications = {
      "app0",  "app1",  "app2",  "app3",  "app4",  
      "app5",  "app6",  "app7",  "app8",  "app9"
    };

    private final String[] _all_groups = { 
      "accounts-barclaysglobal", "administration",     "advantage", 
      "ais",                     "ais_inception",      "alarmpoint", 
      "amsx",                    "amsx_load",          "ap1", 
      "AppManager",              "appmanager",         
      "appmanager_application",  "appmanager_backupexec",
      "appmanager_cim",          "appmanager_exchange", 
      "appmanager_helpdesk",     "appmanager_iis",     "appmanager_isa", 
      "appmanager_nt",           "appmanager_ntadmin", "appmanager_sql", 
      "appmanager_wts",          "art",                "ascot", 
      "asi",                     "asi-network",        "asm", 
      "aust_ftinteractive",      "autosys",            "backup", 
      "barclaysglobal",          "bep",                "bg-aria", 
      "bg_aus",                  "bg_can",             "bgicash", 
      "bgicashfunds",            "bgiconnect_aus",     "bgifunds", 
      "bgis_uk",                 "bgis_uk_qa",         "bgis_uk_dev", 
      "bidbook",                 "bip",                "bms", 
      "candevemail",             "cem",                "checkpoint", 
      "citrix",                  "client_applications", 
      "com",                     "compact",            "concert", 
      "cp_ms_uk",                "cpdl",               "cr_uk", 
      "crs",                     "crm",                "crystal_enterprise", 
      "csm",                     "datacenter",         "dbspi", 
      "delta_uk",                "desktop_antivirus",  "dev_rpt", 
      "devicequery",             "devnet",             "documentum", 
      "dynamo",                  "eat",                "emc_alarms", 
      "equilend",                "equilend_b",         "esm", 
      "esm-notify",              "eshed_uk",           "etf", 
      "fids",                    "flextrade",          "gaa_aasg", 
      "gaa_acm",                 "gaa_adg",            "gaa_aig", 
      "gaa_amg",                 "gaa_aplus",          "gaa_asgintl", 
      "gaa_asgus",               "gaa_atg",            "gaa_aurg", 
      "gaa_cnrg",                "gaa_etg",            "gaa_faser", 
      "gaa_gaepm",               "gaa_gaepm_uk_aa",    "gaa_gaepm_uk_alphagen",
      "gaa_growtheq",            "gaa_gtaa",           "gaa_intellis", 
      "gaa_neualpha",            "gaa_otg",            "gaa_tpg", 
      "gaa_trg",                 "gaa_ukpm",           "gaa_usafi", 
      "gaa_usss",                "gate",               "gcmform", 
      "gcom",                    "gds",                "gem", 
      "gfit",                    "gio",                "gio-unix", 
      "gitap",                   "gladis",             "glm", 
      "glm2",                    "gls_gate",           "gmd", 
      "gmsg",                    "gos",                "gps", 
      "gss",                     "gss_cfmail",         "gsurveyor", 
      "ha",                      "hardware",           "helpme", 
      "ibt_gateway",             "icore",              "idl", 
      "imm",                     "ims",                "indices", 
      "internet_infrastructure", "intlpm",             "intraspect", 
      "iplanet",                 "ishares",            "ishares_dynamo", 
      "ishares_inktomi",         "ishares_fi_bus",     "ishares_fi_dev", 
      "issue_price",             "iunits",             "iweb", 
      "job",                     "jrun",               "k2desk", 
      "marshall",                "misc",               "mqadmin", 
      "mss_admin",               "mss_conf",           "mss_fault", 
      "mss_perf",                "mssql-amr",          "mssql", 
      "mssql-test",              "multi_curr",         "nas", 
      "nas_email",               "netapps",            "netbackup", 
      "netbackup_email",         "network",            "nodestatus", 
      "notification",            "ofa",                "opc", 
      "opendeploy",              "orbis",              "orbis_escalate", 
      "orbis_trd_japan_escalate","orbis_aus_escalate", "orbis_can_escalate", 
      "orbis_europe_escalate",   "orbis_aus",          "orbis_aus_risk", 
      "orbis_aus_trd",           "orbis_can",          "orbis_can_risk", 
      "orbis_can_trd",           "orbis_crossing",     "orbis_cst", 
      "orbis_equity",            "orbis_rm",           "orbis_trd", 
      "orbis_trd_japan",         "orbis_trd_japan",    "orbis_uk", 
      "orbis_usidxpm",           "ordersonline",       "os", 
      "ovis",                    "pars",               "pb_rpt", 
      "performance",             "pims",               "prep", 
      "proa",                    "pst",                "purchasing_web", 
      "rk",                      "rkp",                "san", 
      "san_email",               "security",           "servicecenter", 
      "shareit",                 "shareit-test",       "solaris", 
      "snmp",                    "spi_svcdisc",        "ssp", 
      "statement",               "storage_apac",       "storage_ldn", 
      "storage_rdc",             "storage_sfo",        "sums", 
      "sybase",                  "sybase-test",        "tar", 
      "teledirect",              "torapps",            "torinfra", 
      "tradefloor",              "transfer",           "transvc", 
      "triplea",                 "uk-it-dev",          "uk-unixtest", 
      "uktest",                  "ultraseek",          "unix", 
      "usoe",                    "vpo-servicecenter-alarmpoint", 
      "webadmin",                "weblogic",           "windows", 
      "wl_prd",                  "wl_qa",              "x_stats", 
      "z-spreads"
    };
    
    /**
     * 
     */
    private final Log _log = LogFactory.getLog(StormTest1.class);

    /**
     *
     */
    private final Random _random = new Random();
}

/*
 * Development Environment:
 *   Fedora 4
 *   Sun Java Developers Kit 1.5.0_06
 *
 * Maintenance History:
 *   $Log$
 */
