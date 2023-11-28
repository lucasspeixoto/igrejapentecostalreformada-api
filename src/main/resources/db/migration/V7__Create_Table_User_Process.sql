CREATE TABLE IF NOT EXISTS `user_process` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `has_address` tinyint(1) DEFAULT 0,
  `has_baptism` tinyint(1) DEFAULT 0,
  `has_contact` tinyint(1) DEFAULT 0,
  `has_document` tinyint(1) DEFAULT 0,
  `has_education` tinyint(1) DEFAULT 0,
  `has_family` tinyint(1) DEFAULT 0,
  `has_member` tinyint(1) DEFAULT 0,
  `created_at` datetime DEFAULT (now()),
   `updated_at` datetime DEFAULT (now()),
   `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;