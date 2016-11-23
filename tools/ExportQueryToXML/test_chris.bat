@echo off
java -jar ExportQueryToXML.jar -server=calntesm502 -database=Reporter -port=1433 -username=openview -password=HYPertext01 -dbtype=sqlserver -outfile=chris.xml -query="select DATETIME, TARGET, AVAILABILITY, RESPONSETIME from IOPS_DETAIL_DATA_DAILY" -no-cdata

