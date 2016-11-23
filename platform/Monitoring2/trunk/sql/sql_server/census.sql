create table census (
  row_id varchar(47) primary key,
  bgi_group varchar(127) not null,
  time_stamp datetime not null default current_timestamp)
