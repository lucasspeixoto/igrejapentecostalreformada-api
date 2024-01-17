CREATE TABLE IF NOT EXISTS `scholarship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `scholarship` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT (now()),
  `updated_at` datetime DEFAULT (now()),
  PRIMARY KEY (`id`)
);