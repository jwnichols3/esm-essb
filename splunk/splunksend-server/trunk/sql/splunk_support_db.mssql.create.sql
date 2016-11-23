CREATE TABLE data_map
( 
    rule_id VARCHAR(200),
    application_name VARCHAR(200),
    hostname VARCHAR(200),
    target_path VARCHAR(200),
    PRIMARY KEY ( rule_id )
);

CREATE TABLE http_attribute
( 
    row_id VARCHAR(200),
    request_id VARCHAR(200),
    attribute_name VARCHAR(200),
    attribute_persistence VARCHAR(200),
    PRIMARY KEY ( row_id )
);


CREATE TABLE http_cookie
( 
    row_id VARCHAR(200),
    request_id VARCHAR(200),
    cookie_comment VARCHAR(200),
    domain VARCHAR(200),
    path VARCHAR(200),
    cookie_name VARCHAR(200),
    cookie_persistence VARCHAR(200),
    is_secure BIT,
    max_age INT,
    version INT,
    PRIMARY KEY ( row_id )
);


CREATE TABLE http_header
( 
    row_id VARCHAR(200),
    request_id VARCHAR(200),
    header_name VARCHAR(200),
    header_persistence VARCHAR(200),
    PRIMARY KEY ( row_id )
);


CREATE TABLE http_parameter
( 
    row_id VARCHAR(200),
    request_id VARCHAR(200),
    parameter_name VARCHAR(200),
    parameter_persistence VARCHAR(200),
    PRIMARY KEY ( row_id )
);


CREATE TABLE http_request
( 
    request_id VARCHAR(200),
    auth_type VARCHAR(200),
    context_path VARCHAR(200),
    method VARCHAR(200),
    path_info VARCHAR(200),
    path_info_translated VARCHAR(200),
    remote_user VARCHAR(200),
    requested_session_id VARCHAR(200),
    request_uri VARCHAR(200),
    request_url VARCHAR(200),
    servlet_path VARCHAR(200),
    user_principal VARCHAR(200),
    character_encoding VARCHAR(200),
    content_type VARCHAR(200),
    local_address VARCHAR(200),
    protocol VARCHAR(200),
    remote_address VARCHAR(200),
    remote_host VARCHAR(200),
    scheme VARCHAR(200),
    server_name VARCHAR(200),
    request_time DATETIME,
    process_time DATETIME,
    content_length INT,
    local_port INT,
    remote_port INT,
    server_port INT,
    is_processed BIT,
    was_successful INT,
    return_code INT,
    PRIMARY KEY ( request_id )
);
