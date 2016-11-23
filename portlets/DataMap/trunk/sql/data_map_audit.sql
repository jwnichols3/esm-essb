CREATE TABLE data_map_audit ( 
    audit_id bigint auto_increment,
    audit_version_num bigint,
    audit_timestamp datetime,
    audit_modified_by varchar(8000),
    rule_id bigint,
    bgi_group varchar(8000),
    bgi_method varchar(8000),
    ap_group varchar(8000),
    ap_script varchar(8000),
    per_cat varchar(8000),
    per_subcat varchar(8000),
    per_product varchar(8000),
    per_problem varchar(8000),
    per_assign varchar(8000),
    per_location varchar(8000),
    PRIMARY KEY ( audit_id ) );
