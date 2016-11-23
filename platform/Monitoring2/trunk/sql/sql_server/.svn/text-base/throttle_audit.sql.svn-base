CREATE TABLE throttle_audit ( 
    audit_id bigint identity,
    audit_version_num bigint,
    audit_timestamp datetime,
    audit_modified_by varchar(63),
    rule_id bigint,
    row_id varchar(63),
    bgi_group varchar(63),
    storm_level int,
    duration int,
    threshold int,
    action varchar(63),
    message_flag char(512),
    PRIMARY KEY ( audit_id ) );
