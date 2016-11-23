DROP TABLE IF EXISTS tti_events;

CREATE TABLE tti_events
(
    row_id        bigint auto_increment,
    message_id    varchar(127),
    node_name     varchar(127),
    node_type     varchar(127),
    node_time     varchar(127),
    node_date     varchar(127),
    server_date   varchar(127),
    server_time   varchar(127),
    application   varchar(127),
    message_group varchar(127),
    severity      varchar(127),
    object        text,
    operators     text,
    message_text  text,
    instructions  text,
    message_attributes text,
    PRIMARY KEY ( row_id )
);

