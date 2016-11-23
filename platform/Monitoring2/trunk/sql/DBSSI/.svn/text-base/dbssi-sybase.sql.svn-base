CREATE TABLE dbo.alarmpoint
(
  row_id          varchar(63),
  incident_id     integer     default 0, 
  message_id      varchar(63) null,
  time_stamp      datetime    default getdate(),
  ap_event_id     varchar(63) not null,
  ap_notif_target varchar(63) not null,
  ap_device       varchar(63) not null,
  ap_message      text not null,
)
ALTER TABLE dbo.alarmpoint ADD CONSTRAINT pk_alarmpoint PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.alarmpoint TO ESM_eeb_role
GRANT INSERT ON dbo.alarmpoint TO ESM_eeb_role
GRANT UPDATE ON dbo.alarmpoint TO ESM_eeb_role
GO

CREATE TABLE dbo.audit (
  row_id     varchar(47),
  time_stamp datetime default getdate(),
  module     varchar(31) not null,
  action     varchar(31) not null,
  message_id varchar(63) not null
)
ALTER TABLE dbo.audit ADD CONSTRAINT pk_audit PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.audit TO ESM_eeb_role
GRANT INSERT ON dbo.audit TO ESM_eeb_role
GRANT UPDATE ON dbo.audit TO ESM_eeb_role
GO

CREATE TABLE dbo.census (
  row_id     varchar(47),
  bgi_group  varchar(127) not null,
  time_stamp datetime default getdate()
)
ALTER TABLE dbo.census ADD CONSTRAINT pk_census PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.census TO ESM_eeb_role
GRANT INSERT ON dbo.census TO ESM_eeb_role
GRANT UPDATE ON dbo.census TO ESM_eeb_role
GO

CREATE TABLE dbo.data_map (
  rule_id      integer identity,
  bgi_group    varchar(127) not null,
  bgi_method   varchar(127) not null,
  ap_group     varchar(127) not null,
  ap_script    varchar(127) not null,
  per_queue    varchar(127) null,
  per_cat      varchar(127) not null,
  per_subcat   varchar(127) not null,
  per_product  varchar(127) not null,
  per_problem  varchar(127) not null,
  per_assign   varchar(127) not null,
  per_location varchar(127) not null,
  description  text null
)
ALTER TABLE dbo.data_map ADD CONSTRAINT pk_data_map PRIMARY KEY ( rule_id )
GRANT SELECT ON dbo.data_map TO ESM_eeb_role
GRANT INSERT ON dbo.data_map TO ESM_eeb_role
GRANT UPDATE ON dbo.data_map TO ESM_eeb_role
GO

CREATE TABLE dbo.data_map_audit ( 
    audit_id          integer identity,
    audit_version_num integer,
    audit_timestamp   datetime,
    audit_modified_by varchar(63) null,
    rule_id           integer,
    bgi_group         varchar(63) null,
    bgi_method        varchar(63) null,
    ap_group          varchar(63) null,
    ap_script         varchar(63) null,
    per_queue         varchar(63) null,
    per_cat           varchar(63) null,
    per_subcat        varchar(63) null,
    per_product       varchar(63) null,
    per_problem       varchar(63) null,
    per_assign        varchar(63) null,
    per_location      varchar(63) null
)
ALTER TABLE dbo.data_map_audit ADD CONSTRAINT pk_data_map_audit PRIMARY KEY ( audit_id )
GRANT SELECT ON dbo.data_map_audit TO ESM_eeb_role
GRANT INSERT ON dbo.data_map_audit TO ESM_eeb_role
GRANT UPDATE ON dbo.data_map_audit TO ESM_eeb_role
GO

CREATE TABLE dbo.eeb_properties 
( 
    property_name  varchar(127), 
    property_value varchar(127),
    description    text
)
ALTER TABLE dbo.eeb_properties ADD CONSTRAINT pk_eeb_properties PRIMARY KEY ( property_name )
GRANT SELECT ON dbo.eeb_properties TO ESM_eeb_role
GRANT INSERT ON dbo.eeb_properties TO ESM_eeb_role
GRANT UPDATE ON dbo.eeb_properties TO ESM_eeb_role
GO

CREATE TABLE dbo.events_by_group (
  row_id           varchar(47),
  responder_id     varchar(63)  null,
  bgi_group        varchar(127) not null,
  message_id       varchar(63)  not null,
  time_stamp       datetime     not null,
  source_node      varchar(127) not null,
  source_node_type varchar(127) not null,
  application      varchar(127) not null,
  object           varchar(127) not null,
  severity         varchar(15)  not null,
  message_key      text null,
  message_text     text null,
  instruction_text text null
)
ALTER TABLE dbo.events_by_group ADD CONSTRAINT pk_events_by_group PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.events_by_group TO ESM_eeb_role
GRANT INSERT ON dbo.events_by_group TO ESM_eeb_role
GRANT UPDATE ON dbo.events_by_group TO ESM_eeb_role
GO


CREATE TABLE monotonic 
(
    row_id        integer,
    current_value integer not null
)
ALTER TABLE dbo.monotonic ADD CONSTRAINT pk_montonic PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.monotonic TO ESM_eeb_role
GRANT INSERT ON dbo.monotonic TO ESM_eeb_role
GRANT UPDATE ON dbo.monotonic TO ESM_eeb_role
GO

CREATE TABLE monotonic_datamap
(
    row_id        integer,
    current_value integer not null
)
ALTER TABLE dbo.monotonic_datamap ADD CONSTRAINT pk_montonic_datamap PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.monotonic_datamap TO ESM_eeb_role
GRANT INSERT ON dbo.monotonic_datamap TO ESM_eeb_role
GRANT UPDATE ON dbo.monotonic_datamap TO ESM_eeb_role
GO

CREATE TABLE monotonic_suppression 
(
    row_id        integer,
    current_value integer not null
)
ALTER TABLE dbo.monotonic_suppression ADD CONSTRAINT pk_montonic_suppression PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.monotonic_suppression TO ESM_eeb_role
GRANT INSERT ON dbo.monotonic_suppression TO ESM_eeb_role
GRANT UPDATE ON dbo.monotonic_suppression TO ESM_eeb_role
GO

CREATE TABLE monotonic_throttle 
(
    row_id        integer,
    current_value integer not null
)
ALTER TABLE dbo.monotonic_throttle ADD CONSTRAINT pk_montonic_throttle PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.monotonic_throttle TO ESM_eeb_role
GRANT INSERT ON dbo.monotonic_throttle TO ESM_eeb_role
GRANT UPDATE ON dbo.monotonic_throttle TO ESM_eeb_role
GO

CREATE TABLE raw_ovi (
  row_id     varchar(47),
  time_stamp datetime default getdate(),
  module     varchar(31) not null,
  message_id varchar(63) not null,
  xml        text not null)
ALTER TABLE dbo.raw_ovi ADD CONSTRAINT pk_raw_ovi PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.raw_ovi TO ESM_eeb_role
GRANT INSERT ON dbo.raw_ovi TO ESM_eeb_role
GRANT UPDATE ON dbo.raw_ovi TO ESM_eeb_role
GO

CREATE TABLE dbo.responder (
  row_id               varchar(63),
  duplicate_count      integer,
  message_id           varchar(63) null,
  sc_ticket_num        varchar(63) null,
  sc_ticket_owner      varchar(63) null,
  sc_ticket_message    text null,
  ap_notif_target      varchar(63) null,
  ap_event_id          varchar(63) null,
  message_group        varchar(127) null,
  ap_message           text null,
  sc_request_receipt   varchar(127) null,
  message_key          text null,
  locked_timestamp     integer,
  time_stamp datetime default getdate()
)
ALTER TABLE dbo.responder ADD CONSTRAINT pk_responder PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.responder TO ESM_eeb_role
GRANT INSERT ON dbo.responder TO ESM_eeb_role
GRANT UPDATE ON dbo.responder TO ESM_eeb_role
GO

CREATE TABLE dbo.service_center (
  row_id                varchar(63),
  message_id            varchar(63),
  incident_id           integer,
  time_stamp            datetime default getdate(),
  ticket_num            varchar(63) not null,
  ticket_category       varchar(63) not null,
  ticket_open_time      varchar(63) not null,
  ticket_opened_by      varchar(63) not null,
  ticket_priority_code  varchar(63) not null,
  ticket_severity_code  varchar(63) not null,
  ticket_update_time    varchar(63) not null,
  ticket_assignment     varchar(63) not null,
  ticket_alert_time     varchar(63) not null,
  ticket_status         varchar(63) not null,
  ticket_close_time     varchar(63) not null,
  ticket_closed_by      varchar(63) not null,
  ticket_flag           varchar(63) not null,
  ticket_assignee_name  varchar(63) not null,
  ticket_respond_time   varchar(63) not null,
  ticket_contact_name   varchar(63) not null,
  ticket_actor          varchar(63) not null,
  ticket_format         varchar(63) not null,
  ticket_deadline_group varchar(63) not null,
  ticket_deadline_alert varchar(63) not null,
  ticket_product        varchar(63) not null,
  ticket_problem        varchar(63) not null,
  ticket_location       varchar(63) not null,
  ticket_message        text not null,
  ticket_sub_category   varchar(63) not null,
  message_key           text not null,
  num_attempts          integer default 0
)
ALTER TABLE dbo.service_center ADD CONSTRAINT pk_service_center PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.service_center TO ESM_eeb_role
GRANT INSERT ON dbo.service_center TO ESM_eeb_role
GRANT UPDATE ON dbo.service_center TO ESM_eeb_role
GO


CREATE TABLE dbo.service_center_buffer 
(
  row_id                varchar(63),
  message_id            varchar(63),
  incident_id           integer,
  time_stamp            datetime default gettime(),
  ticket_num            varchar(63) not null,
  ticket_category       varchar(63) not null,
  ticket_open_time      varchar(63) not null,
  ticket_opened_by      varchar(63) not null,
  ticket_priority_code  varchar(63) not null,
  ticket_severity_code  varchar(63) not null,
  ticket_update_time    varchar(63) not null,
  ticket_assignment     varchar(63) not null,
  ticket_alert_time     varchar(63) not null,
  ticket_status         varchar(63) not null,
  ticket_close_time     varchar(63) not null,
  ticket_closed_by      varchar(63) not null,
  ticket_flag           varchar(63) not null,
  ticket_assignee_name  varchar(63) not null,
  ticket_respond_time   varchar(63) not null,
  ticket_contact_name   varchar(63) not null,
  ticket_actor          varchar(63) not null,
  ticket_format         varchar(63) not null,
  ticket_deadline_group varchar(63) not null,
  ticket_deadline_alert varchar(63) not null,
  ticket_product        varchar(63) not null,
  ticket_problem        varchar(63) not null,
  ticket_location       varchar(63) not null,
  ticket_message        text not null,
  ticket_sub_category   varchar(63) not null,
  message_key           text not null,
  num_attempts          integer default 0
)
ALTER TABLE dbo.service_center_buffer ADD CONSTRAINT pk_service_center_buffer PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.service_center_buffer TO ESM_eeb_role
GRANT INSERT ON dbo.service_center_buffer TO ESM_eeb_role
GRANT UPDATE ON dbo.service_center_buffer TO ESM_eeb_role
GO


CREATE TABLE dbo.spool (
  row_id     varchar(47),
  time_stamp datetime default getdate(),
  bgi_group  varchar(127) not null,
  spool_key  varchar(47) not null,
  ovo_key    varchar(63) not null)
ALTER TABLE dbo.spool ADD CONSTRAINT pk_spool PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.spool TO ESM_eeb_role
GRANT INSERT ON dbo.spool TO ESM_eeb_role
GRANT UPDATE ON dbo.spool TO ESM_eeb_role
GO

CREATE TABLE dbo.storm (
  bgi_group      varchar(127),
  storm_level    integer not null,
  action         varchar(31) not null,
  spool_key      varchar(47) not null,
  spool_reminder datetime not null)
GO
ALTER TABLE dbo.storm ADD CONSTRAINT pk_storm PRIMARY KEY ( bgi_group )
GRANT SELECT ON dbo.storm TO ESM_eeb_role
GRANT INSERT ON dbo.storm TO ESM_eeb_role
GRANT UPDATE ON dbo.storm TO ESM_eeb_role
GO

CREATE TABLE dbo.suppression (
  row_id            varchar(47),
  suppress_id       integer      default 0,
  start_time        datetime     not null,
  notification_time datetime     null,
  end_time          datetime     not null,
  app_name          varchar(127) not null,
  node_name         varchar(127) not null,
  group_name        varchar(127) null,
  db_server         varchar(127) not null,
  owner             varchar(127) not null,
  deleted_flg       integer      default 0,
  notify_flg        integer      default 0,
  notify_email      varchar(255) default 0,
  is_notified       integer      default 0,
  notify_minutes    integer      default 0,
  remove_on_reboot  integer      default 0,
  description       text         not null,
  message           text         not null)
ALTER TABLE dbo.suppression ADD CONSTRAINT pk_suppression PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.suppression TO ESM_eeb_role
GRANT INSERT ON dbo.suppression TO ESM_eeb_role
GRANT UPDATE ON dbo.suppression TO ESM_eeb_role
GO

CREATE TABLE dbo.suppression_audit ( 
    audit_id integer  identity,
    audit_version_num integer,
    audit_timestamp   datetime null,
    audit_modified_by varchar(63) null,
    row_id            varchar(63) null,
    suppress_id       integer,
    start_time        datetime,
    notification_time datetime null,
    end_time          datetime,
    app_name          varchar(63) null,
    node_name         varchar(63) null,
    group_name        varchar(63) null,
    db_server         varchar(63) null,
    owner             varchar(127) null,
    deleted_flg       integer,
    notify_email      varchar(255) null,
    is_notified       integer,
    notify_flg        integer,
    notify_minutes    integer,
    remove_on_reboot  integer,
    description       varchar(63) null,
    message           varchar(63) null
)
ALTER TABLE dbo.suppression_audit ADD CONSTRAINT pk_suppression_audit PRIMARY KEY ( audit_id )
GRANT SELECT ON dbo.suppression_audit TO ESM_eeb_role
GRANT INSERT ON dbo.suppression_audit TO ESM_eeb_role
GRANT UPDATE ON dbo.suppression_audit TO ESM_eeb_role
GO

CREATE TABLE dbo.throttle (
  row_id       varchar(47),
  rule_id      integer,
  bgi_group    varchar(127) not null,
  storm_level  integer not null,
  duration     integer not null,
  threshold    integer not null,
  action       varchar(31) not null,
  message_flag char(1) not null)
ALTER TABLE dbo.throttle ADD CONSTRAINT pk_throttle PRIMARY KEY ( row_id )
GRANT SELECT ON dbo.throttle TO ESM_eeb_role
GRANT INSERT ON dbo.throttle TO ESM_eeb_role
GRANT UPDATE ON dbo.throttle TO ESM_eeb_role
GO

CREATE TABLE dbo.throttle_audit ( 
    audit_id          integer,
    audit_version_num integer,
    audit_timestamp   datetime,
    audit_modified_by varchar(63),
    rule_id           integer,
    row_id            varchar(63),
    bgi_group         varchar(63),
    storm_level       integer,
    duration          integer,
    threshold         integer,
    action            varchar(63),
    message_flag      char(1) )
ALTER TABLE dbo.throttle_audit ADD CONSTRAINT pk_throttle_audit PRIMARY KEY ( audit_id )
GRANT SELECT ON dbo.throttle_audit TO ESM_eeb_role
GRANT INSERT ON dbo.throttle_audit TO ESM_eeb_role
GRANT UPDATE ON dbo.throttle_audit TO ESM_eeb_role
GO
