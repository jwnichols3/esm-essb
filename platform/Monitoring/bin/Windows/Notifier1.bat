#!/bin/sh
#
# Title:
#   Notifier1.sh
#
# Description:
#   Notification/Ticket Interface
#
#   ARG[0] = Message ID
#   ARG[1] = Node Name
#   ARG[2] = Node Type
#   ARG[3] = Creation Date
#   ARG[4] = Creation Time
#   ARG[5] = Receive Date
#   ARG[6] = Receive Time
#   ARG[7] = Application
#   ARG[8] = Message Group
#   ARG[9] = Object
#   ARG[10] = Severity
#   ARG[11] = Operators
#   ARG[12] = Message
#   ARG[13] = Instruction
#   ARG[14] = Message Attributes
#   ARG[15] = Suppression Count
#
# Development Environment:
#   Fedora Core 4
#
# Author:
#   G.S. Cole (guycole at gmail dot com)
#
# Maintenance History:
#   $Id$
#
#   $Log$
#
. environment.sh
#
#java -cp $CLASSPATH -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.Log4JCategoryLog com.bgi.replatform1.notifier1.Notifier "test event"
#
#java -cp $CLASSPATH -Dorg.apache.commons.logging.LogFactory=org.apache.commons.logging.impl.Log4JLogger com.bgi.replatform1.notifier1.Notifier "test event"
#
java -cp $CLASSPATH com.bgi.esm.monitoring.platform.notifier.Notifier "e3145bcc-6395-71db-1406-4534661e0000" "rdcuxsrv217" "Sun SPARC(HTTPS)" "10/24/2006" "12:28:54" "10/24/2006" "12:28:54" "SSM-no-app-def" "esm" "SSM-no-obj-def-CLI" "normal" "op1 op2 op3 op4 op5" "test message one" "instruction message" "key1=value1;;key2=value2" "666"
#
