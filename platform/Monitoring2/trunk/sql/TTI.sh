#!/bin/sh

###################################################################
#
# File:      $RCSfile: TTI.sh,v $
# Revision:  $Revision: 1.7 $
# Author:    $Author: nichj $
# Edit date: $Date: 2004/03/04 17:09:31 $
#
# Description / Usage:
#  This script is called as part of the VPO TTI interface.
#  It checks to see if a message is in suppression mode, checks to see
#  if Peregrine is available and processes the message accordingly.
#
# Dependencies:
#  This program runs the following:
#   /opt/OV/suppress/hook.ksh - No longer required
#   /opt/OV/suppress/suppress_check - checks for notification suppression
#  
#  This program requires the following:
#   /usr/bin/tr - used to lower case some variables
#
#  This program looks for and/or uses the following files
#   /opt/OV/scauto/TTI.debug - if present will output debug information to
#   /opt/OV/scauto/TTI.testonly - for use when testing.
#   /opt/check-scauto/itsworking - if Peregrine is not working this 
#       file will be larger than 0 bytes.
#   /opt/OV/suppress/suppress.check.debug - if this file exists then
#       additional debug information will be sent to the log file
#   /opt/OV/suppress/suppress.check.log - Log file
#
#
# wpd Replace the call to hook.ksh with suppress_check.  This is now
#     a perl program that also provides the instance and text checks
#####################################################################

# Script Variables
TTI_LOG_FILE=/opt/OV/scauto/TTI.log

# DEBUG variable
# When the file /opt/OV/scauto/TTI.debug is present, then send detailed output to the TTI.log file
if [ -s /opt/OV/scauto/TTI.debug ] ; then
    TTI_DEBUG=1
 else
    TTI_DEBUG=0
fi

# When the file /opt/OV/scauto/TTI.testonly is present, then do not send an AlarmPoint notification
#  only used when Peregrine is detected as notworking.
if [ -s /opt/OV/scauto/TTI.testonly ] ; then
    TTI_TESTONLY=1
 else
    TTI_TESTONLY=0
fi

# If the itsworking file is more than 0 bytes, then SCAuto isn't working.
if [ -s /opt/check-scauto/itsworking ] ; then
    SCAUTO_NOT_WORKING=1
 else
    SCAUTO_NOT_WORKING=0
fi

# AlarmPoint variables
APSCRIPT="BGI VPO"
APGROUP=""
APSEVERITY="4"
APHOSTNAME=""
APNOTIFYMETHOD=""
APNOTIFY=0

# VPO Variables passed as part of the Notification and TTI interface
OPC_MSGID=${1}
OPC_NODENAME=${2}
OPC_NODETYPE=${3}
OPC_MGDNODEDATE=${4}
OPC_MGDNODETIME=${5}
OPC_MGMTNODEDATE=${6}
OPC_MGMTNODETIME=${7}
OPC_APPLNAME=${8}
OPC_MSGGROUP=`echo  ${9} | /usr/bin/tr '[:upper:]' '[:lower:]'`

if [ ${TTI_DEBUG} -eq 1 ] ; then
    echo "${OPC_MSGID} - ======= Starting Debug ========" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Parameter Name:    Passed Variables / Assigned Variables" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Message ID:        ${1} / ${OPC_MSGID}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Node name:         ${2} / ${OPC_NODENAME}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Node type:         ${3} / ${OPC_NODETYPE}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Managed Node Dt:   ${4} / ${OPC_MGDNODEDATE}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Managed Node Tm:   ${5} / ${OPC_MGDNODETIME}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Mgmt Server Dt:    ${6} / ${OPC_MGMTNODEDATE}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Mgmt Server Tm:    ${7} / ${OPC_MGMTNODETIME}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Application:       ${8} / ${OPC_APPLNAME}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Message Group:     ${9} / ${OPC_MSGGROUP}" >> ${TTI_LOG_FILE}
fi

shift 9

OPC_OBJNAME=${1}
OPC_MSGSEVERITY=`echo  ${2} | /usr/bin/tr '[:upper:]' '[:lower:]'`
OPC_OPERATORS=${3}
OPC_MSGTEXT=${4}
OPC_INSTTEXT=${5}
OPC_CMA=${6}   
 
# debug purposes
if [ ${TTI_DEBUG} -eq 1 ] ; then
    echo "${OPC_MSGID} - Object:            ${1} / ${OPC_OBJNAME}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Severity:          ${2} / ${OPC_MSGSEVERITY}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Operators:         ${3} / ${OPC_OPERATORS}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Message Text:      ${4} / ${OPC_MSGTEXT}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - Instrct Text:      ${5} / ${OPC_INSTTEXT}" >> ${TTI_LOG_FILE}
    echo "${OPC_MSGID} - CMA:               ${6} / ${OPC_CMA}" >> ${TTI_LOG_FILE}
fi

# Notification Suppression check details:
# 
# - People can schedule outage windows via a web page.  These windows are
#   stored in a Sybase database.
# - Every minute this information is extracted to a flatfile.  The extract
#   program and extract data file exist in /opt/OV/suppress.
# - We check first to see if the node or group (or both) are suppressing
#   notification by scanning the data file with a simple awk program.
# - If the awk program returns 0 to stdout, there are no matches in the 
#   extract file, so this script should continue and communicate as necessary 
#   with peregrine/alarmpoint.
# - If the awk program returns > 0, there are that many matches in the file,
#   and this script should then exit to prevent continuing on to 
#   peregrine/alarmpoint.
# - If awk returns a non-zero return code to the shell, there is a problem
#   with the script.  This should be immediately expedited to the VPO
#   group to correct.

echo "${OPC_MSGID} - Calling suppress_check Group: ${OPC_MSGGROUP} | Node: ${OPC_NODENAME} | Msg: ${OPC_MSGTEXT} | CMA: ${OPC_CMA} | MsgId ${OPC_MSGID} |`date`" >> ${TTI_LOG_FILE}

##
## suppress_check takes 4 arguments:  the node, the group, the message text
## and the CMA
##

echo "/opt/OV/suppress/suppress_check ${OPC_MSGGROUP} ${OPC_NODENAME} \"${OPC_MSGTEXT}\" \"${OPC_CMA}\" \"${OPC_MSGID}\"" >> /tmp/sup_call.log
echo "/opt/OV/suppress/suppress_check ${OPC_MSGGROUP} ${OPC_NODENAME} \"${OPC_MSGTEXT}\" \"${OPC_CMA}\" \"${OPC_MSGID}\"" >> ${TTI_LOG_FILE}

/opt/OV/suppress/suppress_check ${OPC_MSGGROUP} ${OPC_NODENAME} "${OPC_MSGTEXT}" "${OPC_CMA}" "${OPC_MSGID}" >> ${TTI_LOG_FILE}
SUPPRESS_COUNT=$?;

if [ ${SUPPRESS_COUNT} -ne  0 ]; then
    echo "${OPC_MSGID} - got a match - exiting. `date`" >> ${TTI_LOG_FILE}
    
    # Add an annotation to OVO    wpd 09/27/02
    /opt/OV/bin/OpC/opcannoadd ${OPC_MSGID} "
    `date` 
    This event was sent for notification, however, 
    notification suppression stopped the process."
    
    # Acknowledge the message 
    # 04-04-2003 uncommented (jwn) 
    /opt/OV/bin/OpC/opcmack ${OPC_MSGID}
   
    exit 0
else
    echo "${OPC_MSGID} - No Suppression Match - NOT exiting. `date`" >> ${TTI_LOG_FILE}
fi

# This line checks for Peregrine working, if it is, then execute the SCAuto interface.
#  If Peregrine isn't working, then gather the AlarmPoint data and talk directly to
#  AlarmPoint. 

if [ ${SCAUTO_NOT_WORKING} -eq 1 ]; then

    if [ ${TTI_DEBUG} -eq 1 ] ; then
        echo "${OPC_MSGID} - Sending directly to AlarmPoint." >> ${TTI_LOG_FILE}
        echo "${OPC_MSGID} - Beginning Switch Case." >> ${TTI_LOG_FILE}
    fi

    set -f		# Turn off file-name (*) expansion

    # Map the VPO Severities
    #
    # If the message is major or critical, call alarmpoint by setting the APNOTIFY bit to 1.
    #
    case $OPC_MSGSEVERITY in

            critical ) 
                APSEVERITY="1"
                APNOTIFY=1;;
            major ) 
                APSEVERITY="2"
                APNOTIFY=1;;
            minor ) 
                APSEVERITY="3"
                APNOTIFY=0;;
            warning ) 
                APSEVERITY="4"
                APNOTIFY=0;;
            normal ) 
                APSEVERITY="5"
                APNOTIFY=0;;

    esac
    
    if [ ${TTI_DEBUG} -eq 1 ] ; then
        echo "${OPC_MSGID} - APSEVERITY = ${APSEVERITY}" >> ${TTI_LOG_FILE}
        echo "${OPC_MSGID} - APNOTIFY = ${APNOTIFY}" >> ${TTI_LOG_FILE}
    fi

    # Map the VPO Message groups to the AlarmPoint group
    #
    case $OPC_MSGGROUP in
            
            ais_inception ) APGROUP="";;
            
            accounts-barclaysglobal ) APGROUP="";;
            
            advanced_active ) APGROUP="ADVANCED ACTIVE";;

            advantage ) APGROUP="support-advantage";;

            appmanager* ) APGROUP="WIN SERVERS FTS";;

            aria_dev ) APGROUP="SUP-ARIA DEV";;

            art ) APGROUP="SUPPORT-GSS";;

            asi ) APGROUP="SUPPORT-GSS";;

            asi-network ) APGROUP="SUPPORT-ASI NETWORK";;

            asm ) APGROUP="SUPPORT-GSS";;

            autosys* ) APGROUP="SUPPORT-GMD";;

            barcalysglobal* ) APGROUP="SUP-BARCLAYSGLOBAL";;

            bep ) APGROUP="support-bep";;

            bgiconnect_aus ) APGROUP="SUPPORT-BGICONNECT_AUS";;

            bgifunds ) APGROUP="SUP-BGIFUNDS.COM";;

            bidbook ) APGROUP="SUPPORT-BIDBOOK";;

            bip ) APGROUP="SUP-BIP";;

            bipsync ) APGROUP="SUPPORT-GSS";;

						bg_aria ) APGROUP="";;
						
						bg_aus ) APGROUP="";;
						
						bg_can ) APGROUP="";;

            bms ) APGROUP="DC FACILITIES-US";;

            candevemail ) APGROUP="support-candevemail";;

            cat ) APGROUP="SUPPORT-CAT";;

            channel-apps ) APGROUP="SUP-CHANNEL-APPS";;

            client_applications ) APGROUP="SUPPORT-CLIENT APPLICATIONS";;
            
            compact ) APGROUP="SUPPORT-COMPACT";;

            cold_fusion ) APGROUP="SUP-COLD FUSION";;

            cpdl ) APGROUP="SUP-CPDL";;

            crm ) APGROUP="SUPPORT-CRM";;

            csm ) APGROUP="SUPPORT-GSS";;

            devicequery ) APGROUP="SUPPORT-GSS";;

            devnet ) APGROUP="SUPPORT-DEVNET";;

            documentum ) APGROUP="DOCUMENTUM";;

            eat ) APGROUP="SUPPORT-GSS";;

            equilend ) APGROUP="SUP-EQUILEND";;

            equilend_b ) APGROUP="SUP-EQUILEND_B";;

            esm ) APGROUP="ESM";;

            etf ) APGROUP="SUP-ETF";;

            etf-cash ) APGROUP="SUP-ETF-CASH";;

            gcmform ) APGROUP="SUPPORT-GSS";;

            gfit ) APGROUP="SUPPORT-GFIT";;

            gift ) APGROUP="SUPPORT-GSS";;

            gio ) APGROUP="SUP-GIO";;

            gio-unix ) APGROUP="SUP-GIO-UNIX";;

            gitap ) APGROUP="SUPPORT-GSS";;

            gitap-test ) APGROUP="Support-GITAP-Test";;

            gladis ) APGROUP="SUPPORT-GLADIS";;

            glm2 ) APGROUP="SUPPORT-GLM2";;

            gmd ) APGROUP="SUPPORT-GMD";;

            gss_cfmail ) APGROUP="SUPPORT-GSS";;

            gsurveyor ) APGROUP="SUPPORT-GSURVEYOR";;

            hardcat ) APGROUP="SUPPORT-GSS";;

            helpme ) APGROUP="SUPPORT-GSS";;

            ibt_gateway ) APGROUP="SUPPORT-IBT GATEWAY SUPPORT";;

            icore-dynamo|dynamo ) APGROUP="SUP-ATG DYNAMO";;

            icore-opendeploy|opendeploy ) APGROUP="SUP-OPEN DEPLOY";;

            icore-web|icore-webadmin|icore-webservice|ishares|ishares_dynamo|ishares_inktomi ) APGROUP="SUP-TEAMSITE";;

            imm ) APGROUP="SUPPORT-GSS";;

            indices ) APGROUP="SUP-INDICES";;

            intlpm ) APGROUP="SUP-INTLPM";;

            intraspect ) APGROUP="SUP-INTRASPECT";;

            iplanet ) APGROUP="SUP-IPLANET";;

            ishares-fi ) APGROUP="ISHARES-FI";;

            ishares-fi ) APGROUP="SUP-ISHARES-FI";;

            ishares.com ) APGROUP="SUP-ISHARES.COM";;

            it_dev-eu ) APGROUP="IT DEV-EU";;

            iunits.com ) APGROUP="SUP-IUNITS.COM";;

            iweb ) APGROUP="SUPPORT-GSS";;

            jrun ) APGROUP="SUP-JRUN";;

            k2desk ) APGROUP="SUPPORT-GSS";;

            lob_com|lob_ordonline|weblogic|lob_wl_prd|lob_wl_qa|ordersonline ) APGROUP="SUP-ORDERS ONLINE";;

            lob_firewall|firewall|internet_infrastructure ) APGROUP="SECURED SERVICES";;

            lob_mqadmin|lob_mq_prd|mqadmin ) APGROUP="SUP-MQSERIES";;

            lob_orbis_usidxpm|orbis_usidxpm ) APGROUP="SUP-ORBIS-USIDXPM_B";;

            lob_orbis|orbis ) APGROUP="SUP-ORBIS";;

            london-new-joiners ) APGROUP="SUPPORT-LONDON-NEW-JOINERS";;

            mssql-amr ) APGROUP="DBA-FTS";;

            mssql-test ) APGROUP="MSSQL-TEST";;

            murray_house_local ) APGROUP="SUPPORT-MURRAY_HOUSE_LOCAL";;
            
            nas ) APGROUP="STORAGE-FTS";;

            network ) APGROUP="NETWORK-FTS";;

            notification ) APGROUP="SUP-VPOSCAP";;

            orbis_crossing ) APGROUP="SUP-ORBIS-CROSS";;

            orbis_cst ) APGROUP="SUP-ORBIS-CST";;

            orbis_equity ) APGROUP="SUP-ORBIS_EQUITY";;

            orbis_rm ) APGROUP="SUP-ORBIS-RM";;

            orbis_trd_japan ) APGROUP="SUP-ORBIS JAPAN_B";;

            orbis_trd|lob_orbis_trd ) APGROUP="SUP-ORBIS-TRD_B";;

            perfweb ) APGROUP="SUPPORT-PERFWEB";;

            purchasing_web|purchasing ) APGROUP="SUPPORT-GSS";;

            san ) APGROUP="STORAGE-FTS";;

            servicecenter ) APGROUP="SUPPORT-GSS";;

            sybase ) APGROUP="DBA-FTS";;

            sybase-test ) APGROUP="SYBASE-TEST";;

						tar ) APGROUP="SUPPORT-TAR";;

            torapps ) APGROUP="SUP-CAN-DEV";;

            torinfra ) APGROUP="SUP-CAN-INFRA";;

            tradefloor ) APGROUP="TRADE FLOOR-AMR";;

            transvc ) APGROUP="SUP-TRANSVC";;

            uk-unixtest ) APGROUP="uk-unixtest";;

            uktest ) APGROUP="uktest";;

            ultraseek ) APGROUP="SUP-ULTRASEEK";;

            unix|os|bb|disk_unix|san|nfs_home ) APGROUP="UNIX SERVERS-FTS";;

            usoe ) APGROUP="SUP-US ORDER ENTRY";;

            weblogic ) APGROUP="SUP-WEBLOGIC";;

            www-dba ) APGROUP="DBA-FTS";;

            z_ESM ) APGROUP="z_ESM";;

    esac
    
    # debug purposes
    if [ ${TTI_DEBUG} -eq 1 ] ; then
        echo "${OPC_MSGID} - AlarmPoint Group:  ${APGROUP}"  >> ${TTI_LOG_FILE}
    fi

    # Strip the domain name off of the VPO Hostname
    #
    APHOSTNAME=`echo $OPC_NODENAME | awk -F. '{print $1}'`

    # Orbis only: pull the logfile name out of the message and pass that to AlarmPoint
    #
    log=`echo $OPC_MSGTEXT | awk '{print $1}'`

    # Commenting out the Orbis-only AlarmPoint connectivity
    #
    #/opt/alarmpt/alarmpt -a "BGI ORBIS" "${APGROUP}" "${OPC_MSGID}" "${OPC_MSGTEXT}" "${hostname}" "${APSEVERITY}" " " "${log}"

    # Annotate the originating VPO event.
    /opt/OV/bin/OpC/opcannoadd ${OPC_MSGID} "
    `date` 
    AP Group: ${APGROUP}. 
    This was sent directly to AlarmPoint because 
    Peregrine was reporting unavailable."
    
    # The AlarmPoint command
    #  /opt/OV/apagent/APClient --map-data openview $1 $2 $3 $4 $5 $6 $7 $8
    #   Parameter #     Description
    #   -----------     --------------------------------
    #       $1          The notification script
    #       $2          The notification group or person
    #       $3          The VPO message id
    #       $4          The message text
    #       $5          The node name
    #       $6          The severity
    #       $7          This corresponds to ???
    #       $8          This corresponds to ???

    # Debug purposes
    if [ ${TTI_DEBUG} -eq 1 ] ; then
        echo "${OPC_MSGID} - AP Command line: /opt/OV/apagent/APClient --map-data openview '${APSCRIPT}' '${APGROUP}' '${OPC_MSGID}' '${OPC_MSGTEXT}' '${APHOSTNAME}' '${APSEVERITY}' ' ' ' '" >> ${TTI_LOG_FILE}
    fi

    # Send the event to AlarmPoint if Major or Critical. Check for the TESTONLY option.
    if [ ${APNOTIFY} -gt 0 ] ; then
    
      if [ ${TTI_TESTONLY} -eq 1 ] ; then
        echo "${OPC_MSGID} NOT SENDING TO ALARMPOINT.  TESTONLY indicated." >> ${TTI_LOG_FILE}
       else
       # call AlarmPoint for critical and major severities only.
        /opt/OV/apagent/APClient --map-data openview "${APSCRIPT}" "${APGROUP}" "${OPC_MSGID}" "${OPC_MSGTEXT}" "${APHOSTNAME}" "${APSEVERITY}" " " " "
      fi

     else

      if [ ${TTI_TESTONLY} -eq 1 ] ; then
        echo "${OPC_MSGID} NOT SENDING TO ALARMPOINT NOT MAJOR OR CRITICAL." >> ${TTI_LOG_FILE}
      fi
    
    fi

    # Debug purposes
    if [ ${TTI_DEBUG} -eq 1 ] ; then
        echo "${OPC_MSGID} - ======= Ending Debug ========" >> ${TTI_LOG_FILE}
    fi

 else

    # Debug purposes
    if [ ${TTI_DEBUG} -eq 1 ] ; then
        echo "${OPC_MSGID} - Sending ticket request to Peregrine." >> ${TTI_LOG_FILE}
        echo "${OPC_MSGID} - ======= Ending Debug ========" >> ${TTI_LOG_FILE}
    fi
    # If SCAuto is working, then execute the scfromitoTTI command
    #
    cd /opt/OV/scauto
    ./scfromitoTTI ${OPC_MSGID}

fi

