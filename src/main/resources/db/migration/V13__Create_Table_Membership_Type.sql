CREATE TABLE IF NOT EXISTS `membership_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `membership_type` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT (now()),
  `updated_at` datetime DEFAULT (now()),
  PRIMARY KEY (`id`)
);