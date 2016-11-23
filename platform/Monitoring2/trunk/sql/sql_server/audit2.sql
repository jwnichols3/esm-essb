CREATE TABLE dbo.audit (
  row_id varchar(47),
  time_stamp datetime not null default current_timestamp,
  module varchar(31) not null,
  action varchar(31) not null,
  message_id varchar(63) not null);

ALTER TABLE dbo.audit ADD CONSTRAINT pk_audit PRIMARY KEY row_id;
GRANT SELECT ON dbo.audit TO mon_role;
GRANT INSERT ON dbo.audit TO mon_role;
GRANT UPDATE ON dbo.audit TO mon_role;
GO;
