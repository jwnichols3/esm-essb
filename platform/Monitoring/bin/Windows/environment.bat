@echo off
set CLASSPATH=%ROOT_HOME%/etc:%ROOT_HOME%/build/classes:%ROOT_HOME%/lib/commons-logging-1.0.3.jar:%ROOT_HOME%/lib/log4j-1.2.7.jar:%ROOT_HOME%/lib/commons-lang-2.1.jar:%ROOT_HOME%/lib/commons-configuration-1.2.jar:%ROOT_HOME%/lib/commons-collections-3.1.jar:%ROOT_HOME%/lib/commons-digester-1.7.jar:%ROOT_HOME%/lib/commons-beanutils.jar:%ROOT_HOME%/lib/exolabcore-0.3.7.jar:%ROOT_HOME%/lib/jndi-1.2.1.jar.jar:%ROOT_HOME%/lib/jms-1.0.2a.jar:%ROOT_HOME%/lib/openjms-client-0.7.6.1.jar;

echo JAVA_HOME=%JAVA_HOME%
echo ROOT_HOME=..
echo CLASSPATH=%CLASSPATH%
rem # Author:
rem #   G.S. Cole (guycole at gmail dot com)
rem #
rem # Maintenance History:
rem #   $Id$
rem #
rem #   $Log$
rem #
rem JDK_HOME=/usr/local/java/jdk1.5.0_06; export JDK_HOME
rem JAVA_HOME=$JDK_HOME; export JAVA_HOME
rem #
rem PATH=$JDK_HOME/bin:$PATH; export PATH
rem #
rem ROOT_HOME=/home/gsc/bgi_ems/replatform; export ROOT_HOME
rem #
rem #CLASSPATH=$ROOT_HOME/etc:$ROOT_HOME/build/classes:$ROOT_HOME/lib/commons-logging-1.0.3.jar:$ROOT_HOME/lib/log4j-1.2.7.jar:$ROOT_HOME/lib/commons-lang-2.1.jar:$ROOT_HOME/lib/commons-configuration-1.2.jar:$ROOT_HOME/lib/commons-collections-3.1.jar; export CLASSPATH
rem #
rem CLASSPATH=$ROOT_HOME/etc:$ROOT_HOME/build/classes:$ROOT_HOME/lib/commons-logging-1.0.3.jar:$ROOT_HOME/lib/log4j-1.2.7.jar:$ROOT_HOME/lib/commons-lang-2.1.jar:$ROOT_HOME/lib/commons-configuration-1.2.jar:$ROOT_HOME/lib/commons-collections-3.1.jar:$ROOT_HOME/lib/commons-digester-1.7.jar:$ROOT_HOME/lib/commons-beanutils.jar:$ROOT_HOME/lib/exolabcore-0.3.7.jar:$ROOT_HOME/lib/jndi-1.2.1.jar.jar:$ROOT_HOME/lib/jms-1.0.2a.jar:$ROOT_HOME/lib/openjms-client-0.7.6.1.jar; export CLASSPATH
