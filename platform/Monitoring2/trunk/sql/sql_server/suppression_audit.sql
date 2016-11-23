CREATE TABLE suppression_audit ( 
    audit_id bigint   identity,
    audit_version_num bigint,
    audit_timestamp   datetime,
    audit_modified_by varchar(63),
    row_id            varchar(63),
    suppress_id       bigint,
    start_time        datetime,
    notification_time datetime,
    end_time          datetime,
    app_name          varchar(63),
    node_name         varchar(63),
    group_name        varchar(63),
    db_server         varchar(63),
    owner             varchar(127),
    deleted_flg       integer,
    notify_email      varchar(255),
    is_notified       integer,
    notify_flg        integer,
    notify_minutes    integer,
    remove_on_reboot  integer,
    description       varchar(63),
    message           varchar(63),
    PRIMARY KEY ( audit_id ) );
