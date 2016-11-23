create table responder (
  row_id               varchar(63) primary key,
  duplicate_count      bigint,
  message_id           varchar(63),
  sc_ticket_num        varchar(63),
  sc_ticket_owner      varchar(63),
  sc_ticket_message    text,
  ap_notif_target      varchar(63),
  ap_event_id          varchar(63),
  message_group        varchar(127),
  sc_request_receipt   varchar(127),
  ap_message           text,
  message_key          text,
  locked_timestamp     bigint,
  time_stamp datetime not null default current_timestamp
);
