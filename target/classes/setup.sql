CREATE TABLE `products` (
  `code` BIGINT unsigned NOT NULL,
  `description` TEXT,
  `price` DOUBLE NOT NULL DEFAULT 0.0,
  `quantity` DOUBLE NOT NULL DEFAULT 0.0,
  PRIMARY KEY (`code`)
);
commit;

CREATE TABLE `customers` (
  `code` BIGINT unsigned NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `address` TEXT,
  `primary_phone_line` VARCHAR(20) DEFAULT 0.0,
  `secondary_phone_line` VARCHAR(20) DEFAULT 0.0,
  `credit_limit` DOUBLE NOT NULL DEFAULT 0.0,
  `current_credit` DOUBLE NOT NULL DEFAULT 0.0,
  PRIMARY KEY (`code`)
);
commit;

CREATE TABLE `sales_orders` (
  `code` BIGINT unsigned NOT NULL,
  `customer` VARCHAR(20) NOT NULL,
  `total_price` DOUBLE DEFAULT 0.0,
  PRIMARY KEY (`code`)
);
commit;

CREATE TABLE `order_lines` (
  `code` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `sales_code` BIGINT NOT NULL,
  `product_code` BIGINT NOT NULL,
  `product_price` DOUBLE NOT NULL,
  `quantity` DOUBLE DEFAULT 0.0,
  PRIMARY KEY (`code`)
);
commit;