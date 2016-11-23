ALTER TABLE dbo.storm ADD CONSTRAINT pk_storm PRIMARY KEY (row_id)
GRANT SELECT ON dbo.storm TO mon_role
GRANT INSERT ON dbo.storm TO mon_role
GRANT UPDATE ON dbo.storm TO mon_role
GO

CREATE TABLE dbo.suppression (
  row_id           varchar(47),
  suppress_id      int          default 0,
  start_time       datetime     not null,
  end_time         datetime     not null,
  app_name         varchar(127) not null,
  node_name        varchar(127) not null,
  group_name       varchar(127) not null,
  db_server        varchar(127) not null,
  deleted_flg      integer      default 0,
  notify_flg       integer      default 0,
  notify_minutes   integer      default 0,
  remove_on_reboot integer      default 0,
  is_notified      integer      default 0,
  description      text         not null,
  message          text         not null)
ALTER TABLE dbo.suppression ADD CONSTRAINT pk_suppression PRIMARY KEY (row_id)
GRANT SELECT ON dbo.suppression TO mon_role
GRANT INSERT ON dbo.suppression TO mon_role
GRANT UPDATE ON dbo.suppression TO mon_role
GO

CREATE TABLE dbo.suppression_audit ( 
    audit_id          int identity,
    audit_version_num int,
    audit_timestamp   datetime,
    audit_modified_by varchar(63),
    row_id            varchar(63),
    suppress_id       int,
    start_time        datetime,
    end_time          datetime,
    app_name          varchar(63),
    node_name         varchar(63),
    group_name        varchar(63),
    db_server         varchar(63),
    deleted_flg       integer,
    notify_flg        integer,
    notify_minutes    integer,
    remove_on_reboot  integer,
    is_notified       integer,
    description       varchar(63),
    message           varchar(63) )
ALTER TABLE dbo.suppression_audit ADD CONSTRAINT pk_suppression_audit PRIMARY KEY (audit_id)
GRANT SELECT ON dbo.suppression_audit TO mon_role
GRANT INSERT ON dbo.suppression_audit TO mon_role
GRANT UPDATE ON dbo.suppression_audit TO mon_role
GO

CREATE TABLE dbo.throttle (
  row_id varchar(47),
  bgi_group varchar(127) not null,
  storm_level int not null,
  duration int not null,
  threshold int not null,
  action varchar(31) not null,
  message_flag char(1) not null)
ALTER TABLE dbo.throttle ADD CONSTRAINT pk_throttle PRIMARY KEY (row_id)
GRANT SELECT ON dbo.throttle TO mon_role
GRANT INSERT ON dbo.throttle TO mon_role
GRANT UPDATE ON dbo.throttle TO mon_role
GO

CREATE TABLE dbo.throttle_audit ( 
    audit_id int identity,
    audit_version_num int,
    audit_timestamp datetime,
    audit_modified_by varchar(63),
    rule_id int,
    row_id varchar(63),
    bgi_group varchar(63),
    storm_level int,
    duration int,
    threshold int,
    action varchar(63),
    message_flag char(512) )
ALTER TABLE dbo.throttle_audit ADD CONSTRAINT pk_throttle_audit PRIMARY KEY (audit_id)
GRANT SELECT ON dbo.throttle_audit TO mon_role
GRANT INSERT ON dbo.throttle_audit TO mon_role
GRANT UPDATE ON dbo.throttle_audit TO mon_role
GO

