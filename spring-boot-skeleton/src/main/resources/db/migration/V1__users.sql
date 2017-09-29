CREATE TABLE users (
  id            INT PRIMARY KEY                     AUTO_INCREMENT,
  username      VARCHAR(255)            NOT NULL,
  password      CHAR(60)                NOT NULL,
  status        TINYINT(4)              NOT NULL    DEFAULT 1
  COMMENT '1:正常 2:删除',
  last_login_at TIMESTAMP               NULL        DEFAULT NULL,
  created_at    TIMESTAMP               NOT NULL    DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP               NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);