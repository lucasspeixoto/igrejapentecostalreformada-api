CREATE TABLE IF NOT EXISTS `community_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `community_type` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT (now()),
  `updated_at` datetime DEFAULT (now()),
  PRIMARY KEY (`id`)
);