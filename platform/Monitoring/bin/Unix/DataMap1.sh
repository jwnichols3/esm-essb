#!/bin/sh
#
# Title:
#   DataMap1.sh
#
# Description:
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
java -cp $CLASSPATH com.bgi.esm.monitoring.platform.data_map.DataMap primary
#
