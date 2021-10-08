create table channel (
  id int primary key AUTO_INCREMENT,
  channel_name varchar(255) not null,
  status smallint default 1,
  created_ts timestamp default CURRENT_TIMESTAMP,
  modified_ts timestamp default CURRENT_TIMESTAMP,

unique(channel_name)
);