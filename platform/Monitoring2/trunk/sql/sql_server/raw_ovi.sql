create table raw_ovi (
  row_id varchar(47) primary key,
  time_stamp datetime not null default current_timestamp,
  module varchar(31) not null,
  message_id varchar(63) not null,
  xml text not null)
