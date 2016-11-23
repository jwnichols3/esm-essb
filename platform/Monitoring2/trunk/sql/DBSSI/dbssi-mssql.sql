create table audit (
  row_id varchar(47) primary key,
  time_stamp datetime not null default current_timestamp,
  module varchar(31) not null,
  action varchar(31) not null,
  message_id varchar(63) not null);

create table census (
  row_id varchar(47) primary key,
  bgi_group varchar(127) not null,
  time_stamp datetime not null default current_timestamp);

create table data_map (
  bgi_group varchar(127) primary key,
  bgi_method varchar(127) not null,
  ap_group varchar(127) not null,
  ap_script varchar(127) not null,
  per_cat varchar(127) not null,
  per_subcat varchar(127) not null,
  per_product varchar(127) not null,
  per_problem varchar(127) not null,
  per_assign varchar(127) not null,
  per_location varchar(127) not null);

create table raw_ovi (
  row_id varchar(47) primary key,
  time_stamp datetime not null default current_timestamp,
  module varchar(31) not null,
  message_id varchar(63) not null,
  xml text not null);

create table spool (
  row_id varchar(47) primary key,
  time_stamp datetime not null default current_timestamp,
  bgi_group varchar(127) not null,
  spool_key varchar(47) not null,
  ovo_key varchar(63) not null);

create table storm (
  bgi_group varchar(127) primary key,
  storm_level int not null,
  action varchar(31) not null,
  spool_key varchar(47) not null,
  spool_reminder datetime not null);

create table suppression (
  row_id varchar(47) primary key,
  start_time datetime not null,
  end_time datetime not null,
  ap_name varchar(127) not null,
  node_name varchar(127) not null,
  db_server varchar(127) not null,
  message text not null);

create table throttle (
  row_id varchar(47) primary key,
  bgi_group varchar(127) not null,
  storm_level int not null,
  duration int not null,
  threshold int not null,
  action varchar(31) not null,
  message_flag char(1) not null);
