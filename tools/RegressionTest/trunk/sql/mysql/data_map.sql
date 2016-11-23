create table data_map (
  rule_id bigint auto_increment,
  bgi_group varchar(127) not null,
  bgi_method varchar(127) not null,
  ap_group varchar(127) not null,
  ap_script varchar(127) not null,
  per_cat varchar(127) not null,
  per_subcat varchar(127) not null,
  per_product varchar(127) not null,
  per_problem varchar(127) not null,
  per_assign varchar(127) not null,
  per_location varchar(127) not null,
  PRIMARY KEY ( rule_id )
)
