IF OBJECT_ID ('dbo.DeleteEEBRecords_pr') IS NOT NULL
 DROP PROCEDURE dbo.DeleteEEBRecords_pr
GO

CREATE PROC DeleteEEBRecords_pr
	@audit_days int,
        @responder_days int,
        @event_days int,
        @responder_def_days int,
	@raw_ovi_days int
        
AS

BEGIN
	SELECT getdate() as "LAST RUN DATE" 

	DELETE FROM audit WHERE datediff(dd,time_stamp,getdate()) > @audit_days
        SELECT @@ROWCOUNT as "Audit records deleted"
       	
	DELETE FROM responder WHERE datediff(dd,time_stamp,getdate()) > @responder_days
        SELECT @@ROWCOUNT as "Responder records deleted"
	
	DELETE FROM events_by_group WHERE datediff(dd,time_stamp,getdate()) > @event_days
        SELECT @@ROWCOUNT as "Events records deleted"

	DELETE FROM responder WHERE datediff(dd,time_stamp,getdate()) > @responder_def_days
	AND sc_ticket_num='IM-Default'
        SELECT @@ROWCOUNT as "Responder IM-Default deleted"

	DELETE FROM raw_ovi WHERE datediff(dd,time_stamp,getdate()) > @raw_ovi_days
        SELECT @@ROWCOUNT as "Raw OVI records deleted"

END

GO	
	GRANT EXECUTE ON dbo.DeleteEEBRecords_pr TO ESM_eeb_role
GO

