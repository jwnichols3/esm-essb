create table events_by_group (
  row_id           varchar(47)  primary key,
  responder_id     varchar(63),
  bgi_group        varchar(127) not null,
  message_id       varchar(63)  not null,
  time_stamp       datetime     not null,
  source_node      varchar(127) not null,
  source_node_type varchar(127) not null,
  application      varchar(127) not null,
  object           varchar(127) not null,
  severity         varchar(15)  not null,
  message_key      text,
  message_text     text,
  instruction_text text
)
