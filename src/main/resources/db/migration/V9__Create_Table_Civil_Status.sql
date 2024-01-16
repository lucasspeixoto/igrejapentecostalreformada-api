CREATE TABLE IF NOT EXISTS `civil_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `civil_status` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT (now()),
  `updated_at` datetime DEFAULT (now()),
  PRIMARY KEY (`id`)
);