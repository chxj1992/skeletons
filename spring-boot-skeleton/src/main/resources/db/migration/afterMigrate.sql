TRUNCATE users;
INSERT INTO users (`id`, `username`, `password`) VALUES
  (1, 'user1', '$2a$10$1T5lvtfWI5DsDtpKSHGCAOBE8qpHmpWlvLPLSCfMRxjLZGpuMsqle'),
  (2, 'user2', '$2a$10$1T5lvtfWI5DsDtpKSHGCAOBE8qpHmpWlvLPLSCfMRxjLZGpuMsqle'),
  (3, 'user3', '$2a$10$1T5lvtfWI5DsDtpKSHGCAOBE8qpHmpWlvLPLSCfMRxjLZGpuMsqle');

TRUNCATE role_user;
INSERT INTO role_user (`id`, `user_id`, `role`) VALUES
  (1, 1, 'ROLE_ADMIN'),
  (2, 1, 'ROLE_USER'),
  (3, 2, 'ROLE_USER'),
  (4, 3, 'ROLE_USER');
