CREATE TABLE user (
  id int(11) NOT NULL auto_increment,
  name varchar(32) default NULL,
  email varchar(50) default NULL,
  code varchar(50) default NULL,
  password varchar(50) default NULL,
  createTime datetime NOT NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

