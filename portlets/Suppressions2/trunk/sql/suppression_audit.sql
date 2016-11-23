CREATE TABLE suppression_audit ( 
    audit_id bigint auto_increment,
    audit_version_num bigint,
    audit_timestamp datetime,
    audit_modified_by varchar(8000),
    row_id varchar(8000),
    suppress_id bigint,
    start_time datetime,
    end_time datetime,
    ap_name varchar(8000),
    node_name varchar(8000),
    group_name varchar(8000),
    db_server varchar(8000),
    notify_flg int,
    notify_minutes int,
    remove_on_reboot int,
    description varchar(8000),
    message varchar(8000),
    PRIMARY KEY ( audit_id ) );