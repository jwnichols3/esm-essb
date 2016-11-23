# CVS Revision:  $Revision: 1.65 $
# CVS date:      $Date: 2007/08/27 19:39:06 $
#
# Purpose:   This program is executed as part of the TTI interface from 
#            VPO to Peregrine.
#
# REVISIONS:
#
#  2005-11-16 -   nichj - Added GMD_EUROPE_MI, sync'd the differences between
#                          server and CVS
#  2006-05-08 -   NICHJ - Added Oracle and Oracle-Test
#
# Dependencies:
#  This is called from the TTI.sh script as part of the VPO TTI interface.
#  It is dependent on the SCAuto program being installed and working correctly.
#
## ============== End of Header =============
#
# The available variables are the same as the attribute names of
# the OPCDTYPE_MESSAGE. This definition can be found in Chapter 4
# pg 196 of the HP Openview Developer's Toolkit Application Integration
# guide.
#
# OPCDATA_MSGID
# OPCDATA_NODENAME
# OPCDATA_CREATION_TIME
# OPCDATA_RECEIVE_TIME
# OPCDATA_MSGTYPE
# OPCDATA_GROUP
# OPCDATA_OBJECT
# OPCDATA_APPLICATION
# OPCDATA_SEVERITY
# OPCDATA_AACTION_NODE
# OPCDATA_AACTION_CALL
# OPCDATA_AACTION_ANNOTATE
# OPCDATA_AACTION_ACK
# OPCDATA_OPACTION_NODE
# OPCDATA_OPACTION_CALL
# OPCDATA_OPACTION_ANNOTATE
# OPCDATA_OPACTION_ACK
# OPCDATA_MSG_LOG_ONLY
# OPCDATA_UNMATCHED
# OPCDATA_TROUBLETICKET
# OPCDATA_TROUBLETICKET_ACK
# OPCDATA_NOTIFICATION
# OPCDATA_INSTR_IF_TYPE
# OPCDATA_INSTR_IF
# OPCDATA_INSTR_PAR
# OPCDATA_MSGSRC
# OPCDATA_MSGTEXT
# OPCDATA_ORIGMSGTEXT
# OPCDATA_ANNOTATIONS
# OPCDATA_LAST_ANNOTATION
#
# The following are the SCAuto created variables
#
# INSTALLDIR         - the home directory of product
#
# SC_MSGTYPE         - OPC message types
#   valid values are:
#      "New message"
#      "Message Owned by a user"
#      "Message Disowned by a user"
#      "Message Acknowledged"
#      "Message has new annotation(s)"
#      "All annotations of Message deleted"
#      "Message was ecalated"
#      "Message was ecalated from another server"
#      "Automatic action of message started"
#      "Automatic action of message finished"
#      "Operator initiated action of message started"
#      "Operator initiated action of message finished"
#
# SC_PROBLEM_NUMBER  - The ServiceCenter Problem Number embeded
#                      in the ITO Message annotation text.
#
# To Peregrine field map:
# -----------------------
# logical.name
# network.name
# device.name
# reference.no
# cause.code
# action
# action2
# action3
# network.address
# type
# category
# subcategory
# product.type
# problem.type
# contact.location
# severity.code
# failing.component
# system
# model
# serial.no
# vendor
# assignment
# objid
# version

# create only if its a new message from OPC
if { $SC_MSGTYPE != "New message" } { 
    set debugFile [open $INSTALLDIR./scito.log a]
    puts $debugFile "[clock format [clock seconds] -format "%m/%d/%Y %T"] eventmapTTI.tcl: skipping event - $SC_MSGTYPE"
    close $debugFile
    return 
}

# skip ServiceCenter events
if { $OPCDATA_APPLICATION == "scevmon" } {
   return
}

# Uppercase OPCDATA_GROUP
set opcMessageGroup [ string tolower $OPCDATA_GROUP ]

# skip ServiceCenter events
if { $opcMessageGroup == "SERVICECENTER" } { 
#   set debugFile [open $INSTALLDIR./scito.log a]
#    puts $debugFile "[clock format [clock seconds] -format "%m/%d/%Y %T"] eventmapTTI.tcl: skipping ServiceCenter event - $SC_MSGTYPE"
#    close $debugFile
#    return 
#}

# create the event object
create_sc_event eventObject

# set the event type using a template to define field names
# * if you don't use a template, you can use integer
# * indexes into the evfield array.

### This is the file to use for field mappings.
###  If anything changes in the field mapping, the Peregrine Support team
###  has to make corresponding changes to the Event Services map(s).
eventObject set_evtype vpopmo use_template "EventMap/ToSC/vpopmo.map"

# start mapping field names to ITO values

##
## Strip the domain name off of the node name and set the variable nodeName
## 04 Oct 2001 - jwn
#
# get the number of characters to the first period "."
set nodeLen [ string first "." $OPCDATA_NODENAME ]

# set the var nodeName to the characters up to the first period
set nodeName [ string range $OPCDATA_NODENAME 0 [ expr $nodeLen -1 ] ]

##
## End getting the short nodeName

# vpo:       shortened NODENAME ($nodeName)  
# peregrine: logical.name
eventObject set_evfield logical.name $nodeName

# vpo:       NODENAME (fqn)
# peregrine: network.name (fqn)
eventObject set_evfield network.name $OPCDATA_NODENAME

# 06 Sept 2001 jwn - added device.name 
# vpo: shortened NODENAME ($nodeName) 
# peregrine: device.name
eventObject set_evfield device.name $nodeName

# vpo:       MSGID     
# peregrine: reference.no
eventObject set_evfield reference.no $OPCDATA_MSGID

# vpo:       ???       
# peregrine: cause.code


# vpo:       MSGTEXT 
# peregrine: action
eventObject set_evfield action $OPCDATA_MSGTEXT

# Original line to be put back in place when Peregrine is fixed.
#eventObject set_evfield action $OPCDATA_MSGTEXT

# vpo:       NODENAME (fqn)
# peregrine: network.address (fqn)
eventObject set_evfield network.address $OPCDATA_NODENAME

# vpo:       <none>    
# peregrine: type - automatically set to "VPOEvent"
eventObject set_evfield type VPOEvent

# vpo:       <none>    
# peregrine: contact.location
# 
# By default this field is set to GLOBAL.
#  In some cases a particular VPO Message Group will have to point
#  to a specific location (e.g. Netbackup) as there are multiple support
#  teams associated with the application / component / system.
#
# Valid options for Location as of 09-21-2001
# (this should be validated with Peregrine):
#  GLOBAL
#  LON1
#  SFO1 
#  RAC1 (Swain Center)
#  SAC1
#  TOK1
#  SYD1
#  WAC1
eventObject set_evfield contact.location GLOBAL

# vpo:       GROUP     
# peregrine: system
eventObject set_evfield system $opcMessageGroup

# vpo:       SEVERITY  
# peregrine: severity.code
### Commented out original.
# eventObject set_evfield severity.code $OPCDATA_SEVERITY

### Added VPO Criticality to Peregrine Severity Mapping
switch $OPCDATA_SEVERITY {
	# CRITICAL
	# OPC_SEV_CRITICAL
	1
		{eventObject set_evfield severity.code 2}
	# MAJOR
	# OPC_SEV_MAJOR
	2
		{eventObject set_evfield severity.code 3}
	# MINOR
	# OPC_SEV_MINOR
	3
		{eventObject set_evfield severity.code 4}
	# WARNING
	# OPC_SEV_WARNING
	4
		{eventObject set_evfield severity.code 5}
	# Everything else
	default
		{eventObject set_evfield severity.code 5}
 }
		
# vpo:       GROUP + OBJECT + APPLICATION + MSGSRC  
# peregrine: failing.component
eventObject set_evfield failing.component [ concat $opcMessageGroup "," $OPCDATA_OBJECT "," $OPCDATA_APPLICATION "'" $OPCDATA_MSGSRC ]

# vpo:       MSGSRC    
# peregrine: system
eventObject set_evfield system $OPCDATA_MSGSRC

# vpo:       APPLICATION  
# peregrine: product.type
# eventObject set_evfield product.type [ string toupper $OPCDATA_APPLICATION ]

# vpo:       OBJECT    
# peregrine: problem.type
# eventObject set_evfield problem.type [ string toupper $OPCDATA_OBJECT ]

# vpo:       evaluate GROUP 
# peregrine: category / subcategory

# NichJ - Sorted the switch statement in alphabetical order for easier searching...
################################################
# V - V - V - V Start Main Switch V - V - V - V
################################################
switch -glob $opcMessageGroup {
    ais_inception {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    accounts-barclaysglobal {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ACCOUNTS.BARCLAYSGLOBAL.COM"
        eventObject set_evfield problem.type "FAULT"
        }

    advantage {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ADVANTAGE"
        eventObject set_evfield problem.type "FAULT"
        }

    amsx {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    amsx_load {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    ap1 {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    ap1_sender {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "AP1 SENDER"
        eventObject set_evfield problem.type "FAULT"
        }

    apex {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CORE PM"
        eventObject set_evfield problem.type "FAULT"
        }

    Apex {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CORE PM"
        eventObject set_evfield problem.type "FAULT"
        }

    appdesk {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "APPDESK"
        eventObject set_evfield problem.type "FAULT"
        }

    appmanager {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "WINDOWS 2000"
        eventObject set_evfield product.type "RESOURCES"
        eventObject set_evfield problem.type "FAULT"
        }
    
    appmanager_application {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "WINDOWS 2000"
        eventObject set_evfield product.type "RESOURCES"
        eventObject set_evfield problem.type "FAULT"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }
    
    appmanager_backupexec {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "BACKUPS"
        eventObject set_evfield product.type "BACKUPEXEC"
        eventObject set_evfield problem.type "FAULT"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }
    
    appmanager_cim {    
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "WINDOWS 2000"
        eventObject set_evfield product.type "RESOURCES"
        eventObject set_evfield problem.type "FAULT"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }
    
    appmanager_exchange {
        eventObject set_evfield category     "MESSAGING"
        eventObject set_evfield subcategory  "EXCHANGE SERVER"
        eventObject set_evfield product.type "EXCHANGE SERVER"
        eventObject set_evfield problem.type "FAULT"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }
    
    appmanager_helpdesk {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "WINDOWS 2000"
        eventObject set_evfield product.type "RESOURCES"
        eventObject set_evfield problem.type "FAULT"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }
    
    appmanager_iis {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "WINDOWS 2000"
        eventObject set_evfield product.type "RESOURCES"
        eventObject set_evfield problem.type "FAULT"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }
    
    appmanager_isa {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "WINDOWS 2000"
        eventObject set_evfield product.type "RESOURCES"
        eventObject set_evfield problem.type "FAULT"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }
    
    appmanager_nt {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "WINDOWS 2000"
        eventObject set_evfield product.type "RESOURCES"
        eventObject set_evfield problem.type "FAULT"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }
    
    appmanager_ntadmin {
        eventObject set_evfield category     "SECURITY"
        eventObject set_evfield subcategory  "ACCOUNT ADMINISTRATION"
        eventObject set_evfield product.type "ACCOUNT ADMINISTRATION"
        eventObject set_evfield problem.type "CHANGE"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }
    
    appmanager_sql {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "WINDOWS 2000"
        eventObject set_evfield product.type "RESOURCES"
        eventObject set_evfield problem.type "FAULT"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }
    
    appmanager_wts {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "WINDOWS 2000"
        eventObject set_evfield product.type "RESOURCES"
        eventObject set_evfield problem.type "FAULT"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }
    
    art {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ART (ACCESS REQUEST TOOL)"
        eventObject set_evfield problem.type "FAULT"
        }

    asi {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ASI (APPLICATION SYSTEM INVENTORY)"
        eventObject set_evfield problem.type "FAULT"
        }

    asi-network {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ASI (GLOBAL)"
        eventObject set_evfield problem.type "FILE MISSING NETWORK"
        }

    aria_jap_onloanreport {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO IT MN"
        eventObject set_evfield problem.type "FAULT"
        }

    aria_jap_generic {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO CP JAPAN"
        eventObject set_evfield problem.type "FAULT"
        }

    aria_jap_clientorder {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO CP JAPAN"
        eventObject set_evfield problem.type "FAULT"
        }

    aria_jap_client {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO CP JAPAN"
        eventObject set_evfield problem.type "FAULT"
        }

    aria_jap_client {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO CP JAPAN"
        eventObject set_evfield problem.type "FAULT"
        }

    aria_jap_execution {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO CP JAPAN"
        eventObject set_evfield problem.type "FAULT"
        }

    aria_jap_canotice {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO CP JAPAN"
        eventObject set_evfield problem.type "FAULT"
        }

    aria_jap_fxrate {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO CP JAPAN" 
        eventObject set_evfield problem.type "FAULT"
        }

    aria_jap_rrr {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO CP JAPAN"
        eventObject set_evfield problem.type "FAULT"
        }

    aria_jap_unitprice {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO CP JAPAN"
        eventObject set_evfield problem.type "FAULT"
        }

    aria_jap_loanquote {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO CP JAPAN"
        eventObject set_evfield problem.type "FAULT"
        }

    asm {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ASM (APPLICATION SECURITY MODEL)"
        eventObject set_evfield problem.type "FAULT"
        }

    autosys {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "AUTOSYS"
        eventObject set_evfield problem.type "INCIDENT"
        }
    
    autopackager {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "BGI DEVELOPER PACKAGING TOOLS"
        eventObject set_evfield problem.type "FAULT"
        }

    barclaysglobal {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CAST"
        eventObject set_evfield problem.type "FAULT"
        }

    bep {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "BEP"
        eventObject set_evfield problem.type "FAULT"
        }

    bgicashfunds {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "BGICASHFUNDS"
        eventObject set_evfield problem.type "FAULT"
        }
    
    bgiconnect_aus {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "BGICONNECT AUS"
        eventObject set_evfield problem.type "FAULT"
        }

    bgifunds {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "BGIFUNDS.COM"
        eventObject set_evfield problem.type "FAULT"
        }
    
    bidbook {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "BID BOOK"
        eventObject set_evfield problem.type "FAULT"
        }
    
    bip {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CAST"
        eventObject set_evfield problem.type "FAULT"
        }

    bg_aria {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "MQSERIES"
        eventObject set_evfield problem.type "FAULT-ARIA"
        }
    
    bg_aus {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "MQSERIES"
        eventObject set_evfield problem.type "FAULT-AUSTRALIA"
        }
    
    bg_can {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "MQSERIES"
        eventObject set_evfield problem.type "FAULT-CANADA"
        }
    
    bgis_uk {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "BGIS UK"
        eventObject set_evfield problem.type "FAULT"
        }
    
    bloomberg_recon {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "KM IT PRODUCTION SERVICES"
        eventObject set_evfield problem.type "FAULT"
        }

    bms {
        eventObject set_evfield category     "FACILITIES"
        eventObject set_evfield subcategory  "HVAC"
        eventObject set_evfield product.type "HVAC"
        eventObject set_evfield problem.type "REPAIR"
        eventObject set_evfield contact.location "RAC1"
        }

    brsi {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "BRSI"
        eventObject set_evfield problem.type "FAULT"
        }
    
    calypso {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "OTHER"
        eventObject set_evfield product.type "CALYPSO"
        eventObject set_evfield problem.type "FAULT"
        }
    
    candevemail {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CAN DEVELOPERS EMAIL"
        eventObject set_evfield problem.type "FAULT"
        }
    
    cast {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CAST"
        eventObject set_evfield problem.type "FAULT"
        }

    can* {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CAN INFRASTRUCTURE"
        eventObject set_evfield problem.type "FAULT"
        }
    
    charles_river* {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "COMPLIANCE-IT"
        eventObject set_evfield problem.type "FAULT"
        }
    
    cipit {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CIPIT"
        eventObject set_evfield problem.type "FAULT"
        }
    
    client_applications {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    client_applications {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    client_app {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    client_applications {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    compact {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "COMPACT"
        eventObject set_evfield problem.type "FAULT"
        }

    crs* {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    cp_ms_uk {
      # NichJ - Changed this to product type of CP_MS_UK.
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CP_MS_UK"
        eventObject set_evfield problem.type "FAULT"
        }

    cpdl {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "COMMON PLATFORM DATA LOADER"
        eventObject set_evfield problem.type "FAULT"
        }
    
    cpdl_jp {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "COMMON PLATFORM DATA LOADER JAPAN"
        eventObject set_evfield problem.type "FAULT"
        }
    
    com {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ORDERS ONLINE"
        eventObject set_evfield problem.type "FAULT"
        }
    
    concert {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CONCERT"
        eventObject set_evfield problem.type "FAULT"
        }

    cr_uk {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TBD APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    compact {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    comm_sec {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "COMM SECURITY"
        eventObject set_evfield problem.type "FAULT"
        }

    crm {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CRM"
        eventObject set_evfield problem.type "FAULT"
        }

    crs {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    csm {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CSM (CLIENT SERVICES MANAGEMENT)"
        eventObject set_evfield problem.type "FAULT"
        }

    dbssi {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "DBSSI"
        eventObject set_evfield problem.type "FAULT"
        }
    delta_uk {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "DELTA_UK"
        eventObject set_evfield problem.type "FAULT"
        }

    delta {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "DELTA"
        eventObject set_evfield problem.type "FAULT"
        }

    dev_rpt {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    devicequery {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "DEVICEQUERY"
        eventObject set_evfield problem.type "FAULT"
        }

    devnet {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "DEVNET"
        eventObject set_evfield problem.type "FAULT"
        }

    documentum {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "DOCUMENTUM"
        eventObject set_evfield problem.type "FAULT"
        }
    
    dynamo {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ATG DYNAMO"
        eventObject set_evfield problem.type "FAULT"
        }
    
    eat {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "EAT (EMERGENCY ACCESS TOOL)"
        eventObject set_evfield problem.type "FAULT"
        }

    esm* {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "ENTERPRISE SYSTEMS MANAGEMENT"
        eventObject set_evfield product.type "VPO"
        eventObject set_evfield problem.type "FAULT"
        }
    
    eshed_uk {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TBD APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    etf {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ETF"
        eventObject set_evfield problem.type "FAULT"
        }
    
    emx {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "EMX"
        eventObject set_evfield problem.type "FAULT"
        }

    flextrade {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "FLEXTRADE"
        eventObject set_evfield problem.type "FAULT"
        }
    
    fidap {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "FIDAP"
        eventObject set_evfield problem.type "FAULT"
        }
    
    fi_batch {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "FI BATCH"
        eventObject set_evfield problem.type "FAULT"
        }
    
    fids {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "FIDS"
        eventObject set_evfield problem.type "FAULT"
        }
    
    fi-sovplus {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "FI-SOVPLUS"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gaa_aasg {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA AASG"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gaa_acm {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA ACM"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_adg {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA ADG"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_aig {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA AIG"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gaa_amg {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA AMG"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_atg {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA ATG"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gaa_aurg {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA AURG"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_aplus {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA APLUS"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_asgintl {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA ASGINTL"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_asgus {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA ASGUS"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_cnrg {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA CNRG"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gaa_etg {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA ETG"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_faser {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA FASER"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_gaepm {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA GAEPM"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_gaepm_uk_aa {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA GAEPM_UK AA"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gaa_gaepm_uk_alphagen {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA GAEPM_UK ALPHAGEN"
        eventObject set_evfield problem.type "FAULT"
        }

    gos {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GOS"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_growtheq {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA GROWTHEQ"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gaa_gtaa {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA GTAA"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_intellis {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA INTELLIS"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gaa_neualpha {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "NEUALPHA"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_otg {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA OTG"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gaa_tpg {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA TPG"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_trg {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA TRG"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gaa_ukpm {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA UKPM"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_unix_work_in_progress {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "UNIX WIP"
        eventObject set_evfield product.type "GAA WIP"
        eventObject set_evfield problem.type "REPAIR/REPLACE"
        }

    gaa_unix_autogen {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "UNIX"
        eventObject set_evfield product.type "GAA UNIX OPERATING SYSTEM"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_unix {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "UNIX"
        eventObject set_evfield product.type "GAA UNIX SERVERS"
        eventObject set_evfield problem.type "FAULT"
        }

    gaa_usafi {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA USAFI"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gaa_usss {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GAA USSS"
        eventObject set_evfield problem.type "FAULT"
        }

    gcom {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GCOM"
        eventObject set_evfield problem.type "FAULT"
        }

    gcmform {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GCMFORM"
        eventObject set_evfield problem.type "FAULT"
        }
        
    gds {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    gift {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GIFT (GOOD INTENTIONS FOR TODAY)"
        eventObject set_evfield problem.type "FAULT"
        }
    gfit {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GFIT"
        eventObject set_evfield problem.type "FAULT"
        }

    gio {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GIO"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gio-unix {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GIO-UNIX"
        eventObject set_evfield problem.type "FAULT"
        }
    
    gitap {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "SHAREIT"
        eventObject set_evfield problem.type "FAULT"
        }

    gladis {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GLADIS"
        eventObject set_evfield problem.type "FAULT"
        }

    glm {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GLM"
        eventObject set_evfield problem.type "FAULT"
        }

    glm2 {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GLM"
        eventObject set_evfield problem.type "FAULT"
        }

    global_ishares {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ISHARES MESSAGING"
        eventObject set_evfield problem.type "FAULT"
        }

    gss {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    gls_gate* {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "MQSERIES"
        eventObject set_evfield problem.type "FAULT"
        }

    gmd {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GMD"
        eventObject set_evfield problem.type "FAULT"
        }

    gmd_europe_mi {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GMD_EUROPE_MI"
        eventObject set_evfield problem.type "FAULT"
        }

    got_fts {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GOT-FTS-SUPPORT"
        eventObject set_evfield problem.type "FAULT"
        }

    got_fts_email {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GOT-FTS-SUPPORT-EMAIL"
        eventObject set_evfield problem.type "FAULT"
        }

    gps {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GPS"
        eventObject set_evfield problem.type "FAULT"
        }

    gss_cfmail {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "GSS_CFMAIL"
        eventObject set_evfield problem.type "FAULT"
        }

    gsurveyor {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "MQSERIES"
        eventObject set_evfield problem.type "FAULT"
        }

    hardcatweb {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "HARDCATWEB"
        eventObject set_evfield problem.type "FAULT"
        }
    helpme {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "HELPME"
        eventObject set_evfield problem.type "FAULT"
        }

    hfdp-di {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "HFDP-DI"
        eventObject set_evfield problem.type "FAULT"
        }

    ibt_gateway {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "IBT GATEWAY"
        eventObject set_evfield problem.type "FAULT"
        }

    imm {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "IMM (INTERNET MEETING MANAGER)"
        eventObject set_evfield problem.type "FAULT"
        }

    indices {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TBD APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    infradev {
        eventObject set_evfield category     "APPS INFRASTRUCTURE"
        eventObject set_evfield subcategory  "DEVELOPMENT"
        eventObject set_evfield product.type "RESOURCES"
        eventObject set_evfield problem.type "FAULT"
        }

    internet_infrastructure {
        eventObject set_evfield category     "NETWORK"
        eventObject set_evfield subcategory  "INTERNET ACCESS"
        eventObject set_evfield product.type "OTHER"
        eventObject set_evfield problem.type "FAULT"
        }
    
    intraspect {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "INTRASPECT"
        eventObject set_evfield problem.type "FAULT"
        }
    
    iplanet {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "IPLANET"
        eventObject set_evfield problem.type "FAULT"
        }
    
    ishares_ca {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ISHARES CA"
        eventObject set_evfield problem.type "FAULT"
        }

    ishares_net {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ISHARES WEB"
        eventObject set_evfield problem.type "FAULT"
        }

    ishares* {
    # ISHARES_DYNAMO, ISHARES_INKTOMI, ISHARES_VP-IS
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ISHARES.COM"
        eventObject set_evfield problem.type "FAULT"
        }
    
    ishares_fi_bus {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TBD APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    ishares_fi_dev {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TBD APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    issue_price {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    iweb {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "IWEB"
        eventObject set_evfield problem.type "FAULT"
        }

    iunits* {
    # iunits 
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "IUNITS.COM"
        eventObject set_evfield problem.type "FAULT"
        }

    javaee_support {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "JAVA EE"
        eventObject set_evfield problem.type "FAULT"
        }

    k2desk {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "K2DESK"
        eventObject set_evfield problem.type "FAULT"
        }

    kmitps {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "KM IT PRODUCTION SERVICES"
        eventObject set_evfield problem.type "FAULT"
        }

    km_it_ps {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "KM IT PRODUCTION SERVICES"
        eventObject set_evfield problem.type "FAULT"
        }

    lockouts {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "LOCKOUTS TRACKER"
        eventObject set_evfield problem.type "FAULT"
        }

    mac {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "TRADE FLOOR"
        eventObject set_evfield product.type "MAC"
        eventObject set_evfield problem.type "FAULT"
        }

    marshall {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "MARSHALL ISLANDS"
        eventObject set_evfield problem.type "FAULT"
        }

    mssql-amr {
        eventObject set_evfield category     "DATABASE"
        eventObject set_evfield subcategory  "DATABASE ADMINISTRATION"
        eventObject set_evfield product.type "MICROSOFT"
        eventObject set_evfield problem.type "FAULT"
        }
    
    mssql {
        eventObject set_evfield category     "DATABASE"
        eventObject set_evfield subcategory  "DATABASE ADMINISTRATION"
        eventObject set_evfield product.type "MICROSOFT"
        eventObject set_evfield problem.type "FAULT"
        }
    
    mssql-test {
        eventObject set_evfield category     "DATABASE"
        eventObject set_evfield subcategory  "DATABASE ADMINISTRATION"
        eventObject set_evfield product.type "MSSQL-TEST"
        eventObject set_evfield problem.type "FAULT"
        }
    
    mqadmin {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "MQSERIES"
        eventObject set_evfield problem.type "FAULT"
        }
    
    mqadmin_syd {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "MQSERIES SYDNEY"
        eventObject set_evfield problem.type "FAULT"
        }
    
    multi_curr {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    nas {
        eventObject set_evfield category     "STORAGE"
        eventObject set_evfield subcategory  "CAPACITY EXPANSION"
        eventObject set_evfield product.type "NAS"
        eventObject set_evfield problem.type "FAULT"
        }

    #netbackup* {
    #NETBACKUP_MAINTENANCE, NETBACKUP_SYSTEM,
    #    eventObject set_evfield category     "SERVERS"
    #    eventObject set_evfield subcategory  "BACKUPS"
    #    eventObject set_evfield product.type "NETBACKUP"
    #    eventObject set_evfield problem.type "FAULT"
    #    eventObject set_evfield contact.location "RAC1"
    #    }
    
    netbackup* {
    #NETBACKUP_MAINTENANCE, NETBACKUP_SYSTEM,
        eventObject set_evfield category     "STORAGE"
        eventObject set_evfield subcategory  "DATA PROTECTION"
        eventObject set_evfield product.type "BACKUPS"
        eventObject set_evfield problem.type "FAULT"
        }
    
    network {
        eventObject set_evfield category     "NETWORK"
        eventObject set_evfield subcategory  "LAN"
        eventObject set_evfield product.type "LAN"
        eventObject set_evfield problem.type "REPAIR/REPLACE"
        }

    notification {
        eventObject set_evfield category     "MESSAGING"
        eventObject set_evfield subcategory  "END-TO-END"
        eventObject set_evfield product.type "VPO-SERVICECENTER-ALARMPOINT"
        eventObject set_evfield problem.type "MESSAGE CHECKER"
        }
    
    opendeploy {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "OPEN DEPLOY"
        eventObject set_evfield problem.type "FAULT"
        }
   
    ofa {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    oracle {
        eventObject set_evfield category     "DATABASE"
        eventObject set_evfield subcategory  "DATABASE ADMINISTRATION"
        eventObject set_evfield product.type "ORACLE"
        eventObject set_evfield problem.type "FAULT"
        }
   
    orbis {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ORBIS"
        eventObject set_evfield problem.type "FAULT"
        }
    
    orbis-disk-monitoring {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ORBIS-DISKMONITORING"
        eventObject set_evfield problem.type "FAULT"
        }
    
    orbis_support {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ORBIS MESSAGING"
        eventObject set_evfield problem.type "FAULT"
        }
    
    ordersonline {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ORDERS ONLINE"
        eventObject set_evfield problem.type "FAULT"
        }

    os {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "UNIX"
        eventObject set_evfield product.type "OPERATING SYSTEM"
        eventObject set_evfield problem.type "REPAIR/REPLACE"
        }
    
    p3 {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "PTOOLS"
        eventObject set_evfield problem.type "FAULT"
        }

    packaging {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "PATCH-COMPLIANCE-VPO"
        eventObject set_evfield problem.type "FAULT"
        }
    pars {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "PARS"
        eventObject set_evfield problem.type "FAULT"
        }

    pb_rpt {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    prep {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    prism {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "PRISM"
        eventObject set_evfield problem.type "FAULT"
        }

    proa {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    pst {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    purchasing {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "PURCHASING WEB"
        eventObject set_evfield problem.type "FAULT"
        }
    rk {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TBD APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    rkp {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TBD APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    san {
        eventObject set_evfield category     "STORAGE"
        eventObject set_evfield subcategory  "CAPACITY EXPANSION"
        eventObject set_evfield product.type "SAN"
        eventObject set_evfield problem.type "FAULT"
        }

    secsvcs {
        eventObject set_evfield category     "NETWORK"
        eventObject set_evfield subcategory  "INTERNET ACCESS"
        eventObject set_evfield product.type "OTHER"
        eventObject set_evfield problem.type "FAULT"
        }
    security {
        eventObject set_evfield category     "SECURITY"
        eventObject set_evfield subcategory  "SERVER SECURITY"
        eventObject set_evfield product.type "UNIX"
        eventObject set_evfield problem.type "FAULT"
        }
    
    servicecenter {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "SERVICECENTER"
        eventObject set_evfield problem.type "FAULT"
        }
    
    shareit {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "SHAREIT"
        eventObject set_evfield problem.type "FAULT"
        }
    
    shareit-test {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "SHAREIT-TEST"
        eventObject set_evfield problem.type "FAULT"
        }
    
    sourceforge {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "SFEE"
        eventObject set_evfield problem.type "FAULT"
        }
    
    sums {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "SUMS"
        eventObject set_evfield problem.type "FAULT"
        }

    sybase-test {
        eventObject set_evfield category     "DATABASE"
        eventObject set_evfield subcategory  "DATABASE ADMINISTRATION"
        eventObject set_evfield product.type "SYBASE-TEST"
        eventObject set_evfield problem.type "FAULT"
        }
    
    sybase {
        eventObject set_evfield category     "DATABASE"
        eventObject set_evfield subcategory  "DATABASE ADMINISTRATION"
        eventObject set_evfield product.type "SYBASE"
        eventObject set_evfield problem.type "FAULT"
        }
 
    swordfishwebservices {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "WEBSERVICES"
        eventObject set_evfield problem.type "FAULT"
        }
 
    torinfra {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CAN INFRASTRUCTURE"
        eventObject set_evfield problem.type "FAULT"
        }
    
    torapps {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CAN APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }
    
    tar {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TAR"
        eventObject set_evfield problem.type "FAULT"
        }
    techteam {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "HELPDESK-VPO"
        eventObject set_evfield problem.type "FAULT"
        }
    
    tky_it_bo {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TOKYO IT BACK OFFICE"
        eventObject set_evfield problem.type "FAULT"
        }
    
    tky_it_got_jp {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "IT GOT JP"
        eventObject set_evfield problem.type "FAULT"
        }
    
    tky_cdb {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CDB"
        eventObject set_evfield problem.type "FAULT"
        }
    
    tky_pas {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "PAS"
        eventObject set_evfield problem.type "FAULT"
        }
    tky_tpt {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TKY TPT"
        eventObject set_evfield problem.type "FAULT"
        }
    
    transvc {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TRANSITION SERVICES"
        eventObject set_evfield problem.type "FAULT"
        }
    
    tradefloor {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "TRADE FLOOR"
        eventObject set_evfield product.type "DR PC"
        eventObject set_evfield problem.type "FAULT"
        }

    triplea {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TBD APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

     tsweb {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TERMINAL SERVER WEB"
        eventObject set_evfield problem.type "FAULT"
        }
    teledirect {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "TELEDIRECT"
        eventObject set_evfield problem.type "FAULT"
        }
    
    uk_tradefloor {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "TRADE FLOOR"
        eventObject set_evfield product.type "UK PC"
        eventObject set_evfield problem.type "FAULT"
        }

    uk-it-dev {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "UK-IT DEV"
        eventObject set_evfield problem.type "FAULT"
        }
    
    uk-unixtest {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "UNIX-TEST"
        eventObject set_evfield problem.type "FAULT"
        }
    
    ultraseek {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ULTRASEEK"
        eventObject set_evfield problem.type "FAULT"
        }
    
    unix {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "UNIX"
        eventObject set_evfield product.type "OPERATING SYSTEM"
        eventObject set_evfield problem.type "REPAIR/REPLACE"
        }

    unix_security {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SECURITY"
        eventObject set_evfield product.type "UNIX SECURITY FTS"
        eventObject set_evfield problem.type "FAULT"
        }

    unix_security_env {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "UNIX SECURITY"
        eventObject set_evfield problem.type "FAULT"
        }

    unix_security_monitoring {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "UNIX SECURITY"
        eventObject set_evfield problem.type "FAULT"
        }

    unix_servers {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "UNIX"
        eventObject set_evfield product.type "UNIX OPERATING SYSTEM"
        eventObject set_evfield problem.type "FAULT"
        }

    unix_servers_work_in_progress {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "UNIX WIP"
        eventObject set_evfield product.type "UNIX SERVERS WIP"
        eventObject set_evfield problem.type "REPAIR/REPLACE"
        }

    unix_servers_autogen {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "UNIX"
        eventObject set_evfield product.type "OPERATING SYSTEM"
        eventObject set_evfield problem.type "REPAIR/REPLACE"
        }

    solaris {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "UNIX"
        eventObject set_evfield product.type "OPERATING SYSTEM"
        eventObject set_evfield problem.type "REPAIR/REPLACE"
        }

    uk_helpdesk {
        eventObject set_evfield category     "HARDWARE"
        eventObject set_evfield subcategory  "SERVERS"
        eventObject set_evfield product.type "DR PC"
        eventObject set_evfield problem.type "FAULT"
        }
    
    usoe {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "ORDER ENTRY"
        eventObject set_evfield problem.type "FAULT"
        }
    
    windows {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "WINDOWS 2000"
        eventObject set_evfield product.type "RESOURCES"
        eventObject set_evfield problem.type "FAULT"
        eventObject set_evfield device.name $OPCDATA_OBJECT
        }

    winfts {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "WINDOWS 2000"
        eventObject set_evfield product.type "RESOURCES-VPO1"
        eventObject set_evfield problem.type "FAULT"
        }
        
    weblogic {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "WEBLOGIC"
        eventObject set_evfield problem.type "ADVICE/GUIDANCE"
        }
    
    wl_prd {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "WEBLOGIC"
        eventObject set_evfield problem.type "ADVICE/GUIDANCE"
        }
    
    wl_qa {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "WEBLOGIC"
        eventObject set_evfield problem.type "ADVICE/GUIDANCE"
        }
    
    x_stats {
        eventObject set_evfield category     "SOFTWARE"
        eventObject set_evfield subcategory  "SOFTWARE"
        eventObject set_evfield product.type "CLIENT APPLICATIONS"
        eventObject set_evfield problem.type "FAULT"
        }

    default {
        eventObject set_evfield category     "SERVERS"
        eventObject set_evfield subcategory  "ENTERPRISE SYSTEMS MANAGEMENT"
        eventObject set_evfield product.type "SCAUTO"
        eventObject set_evfield problem.type "FAULT"
        switch $OPCDATA_SEVERITY {
                # CRITICAL
                # OPC_SEV_CRITICAL
                1
                        {eventObject set_evfield severity.code 4}
                # MAJOR
                # OPC_SEV_MAJOR
                2
                        {eventObject set_evfield severity.code 4}
                # MINOR
                # OPC_SEV_MINOR
                3
                        {eventObject set_evfield severity.code 4}
                # WARNING
                # OPC_SEV_WARNING
                4
                        {eventObject set_evfield severity.code 5}
                # Everything else
                default
                        {eventObject set_evfield severity.code 5}
        }

    } 
} 
################################################
# ^ - ^ - ^ - ^  End  Main Switch ^ - ^ - ^ - ^
################################################

# print out a debug of event created
#eventObject print

# send the event to queue

set debugFile [open $INSTALLDIR./scito.log a]
puts $debugFile "[clock format [clock seconds] -format "%m/%d/%Y %T"] eventmapTTI.tcl: queueing new ticket to be opened.       -- $OPCDATA_MSGID"
close $debugFile
eventObject send
