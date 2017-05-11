-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
  user_id int(11) NOT NULL DEFAULT '0',
  name varchar(20) NOT NULL DEFAULT '',
  email varchar(60) NOT NULL DEFAULT '',
  reg_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id)
);