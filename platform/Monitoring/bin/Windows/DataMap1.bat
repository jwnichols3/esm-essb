@echo off
Starting DataMap module...
call environment.bat
java -cp %CLASSPATH% com.bgi.esm.monitoring.platform.data_map.DataMap primary
