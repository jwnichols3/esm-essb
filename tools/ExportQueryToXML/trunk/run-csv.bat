@echo off
rem java -jar ExportQueryToCSV.jar -dbtype=sybase -server=rdcuxsrv210 -port=14111 -username=esm_eeb -password=App1talk -database=monitoring_db -query="SELECT TOP 100 * FROM audit WHERE time_stamp >= '2009-06-01' AND time_stamp < '2009-06-02'"
java -jar ExportQueryToCSV.jar -dbtype=sybase -server=rdcuxsrv210 -port=14111 -username=esm_eeb -password=App1talk -database=monitoring_db -query="SELECT TOP 100 * FROM events_by_group WHERE bgi_group='esm' ORDER BY time_stamp DESC"

