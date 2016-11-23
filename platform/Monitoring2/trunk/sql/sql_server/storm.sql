create table storm (
  bgi_group varchar(127) primary key,
  storm_level int not null,
  action varchar(31) not null,
  spool_key varchar(47) not null,
  spool_reminder datetime not null)
