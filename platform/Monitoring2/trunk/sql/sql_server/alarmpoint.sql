create table alarmpoint
(
  row_id          varchar(63) primary key,
  incident_id     bigint, 
  message_id      varchar(63),
  time_stamp      datetime,
  ap_event_id     varchar(63) not null,
  ap_notif_target varchar(63) not null,
  ap_device       varchar(63) not null,
  ap_message      text not null
);