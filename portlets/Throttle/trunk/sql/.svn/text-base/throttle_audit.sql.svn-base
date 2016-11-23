CREATE TABLE throttle_audit ( 
    audit_id bigint auto_increment,
    audit_version_num bigint,
    audit_timestamp datetime,
    audit_modified_by varchar(8000),
    rule_id bigint,
    row_id varchar(8000),
    bgi_group varchar(8000),
    storm_level int,
    duration int,
    threshold int,
    action varchar(8000),
    message_flag char(512),
    PRIMARY KEY ( audit_id ) );
