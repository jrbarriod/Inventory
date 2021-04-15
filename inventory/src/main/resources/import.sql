DROP SCHEMA IF EXISTS inventory;

CREATE SCHEMA inventory DEFAULT CHARACTER SET = utf8 DEFAULT COLLATE = utf8_general_ci;
GRANT ALL PRIVILEGES ON inventory.* TO 'inventory'@'%' WITH GRANT OPTION;

use inventory;

CREATE TABLE `articles` (
  `article_id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `stock` bigint NOT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='Articles table';

CREATE TABLE `products` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='Products table';

CREATE TABLE `products_articles` (
  `product_id` bigint NOT NULL,
  `article_id` bigint NOT NULL,
  `amount` bigint NOT NULL,
  PRIMARY KEY (`product_id`, `article_id`),
  KEY `product_id` (`product_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `pa_fk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pa_fk_2` FOREIGN KEY (`article_id`) REFERENCES `articles` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='Articles by Product';