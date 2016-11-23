DROP TABLE http_request_attributes;
CREATE TABLE http_request_attributes (
  row_id varchar(255) NOT NULL,
  request_id varchar(255) default NULL,
  attribute_name varchar(255) default NULL,
  attribute_persistence varchar(255) default NULL,
  PRIMARY KEY  (row_id)
);

DROP TABLE http_request_cookies;
CREATE TABLE http_request_cookies (
  row_id varchar(255) NOT NULL,
  request_id varchar(255) default NULL,
  comment varchar(255) default NULL,
  domain varchar(255) default NULL,
  path varchar(255) default NULL,
  cookie_name varchar(255) default NULL,
  cookie_persistence varchar(255) default NULL,
  is_secure int default NULL,
  max_age int default NULL,
  version int default NULL,
  PRIMARY KEY  (row_id)
);

DROP TABLE http_request_headers;
CREATE TABLE http_request_headers (
  row_id varchar(255) NOT NULL,
  request_id varchar(255) default NULL,
  header_name varchar(255) default NULL,
  header_persistence varchar(255) default NULL,
  PRIMARY KEY  (row_id)
);


DROP TABLE http_request_parameters;
CREATE TABLE http_request_parameters (
  row_id varchar(255) NOT NULL,
  request_id varchar(255) default NULL,
  parameter_name varchar(255) default NULL,
  parameter_persistence varchar(255) default NULL,
  PRIMARY KEY  (row_id)
);

DROP TABLE http_requests;
CREATE TABLE http_requests (
  request_id varchar(255) NOT NULL,
  auth_type varchar(255) default NULL,
  context_path varchar(255) default NULL,
  method varchar(255) default NULL,
  path_info varchar(255) default NULL,
  path_info_translated varchar(255) default NULL,
  remote_user varchar(255) default NULL,
  requested_session_id varchar(255) default NULL,
  request_uri varchar(255) default NULL,
  request_url varchar(255) default NULL,
  servlet_path varchar(255) default NULL,
  user_principal varchar(255) default NULL,
  character_encoding varchar(255) default NULL,
  content_type varchar(255) default NULL,
  local_address varchar(255) default NULL,
  protocol varchar(255) default NULL,
  remote_address varchar(255) default NULL,
  remote_host varchar(255) default NULL,
  scheme varchar(255) default NULL,
  server_name varchar(255) default NULL,
  request_time datetime,
  content_length int default NULL,
  local_port int default NULL,
  remote_port int default NULL,
  server_port int default NULL,
  PRIMARY KEY  (request_id)
);
