DROP DATABASE IF EXISTS `datasource1`;
CREATE DATABASE `datasource1` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use datasource1;

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
   `id` INT(11) NOT NULL AUTO_INCREMENT,
	 `code` INT(11) DEFAULT NULL,
	 `quantity` INT(11) DEFAULT NULL,
	 PRIMARY KEY (`id`)
);

DROP DATABASE IF EXISTS `datasource2`;
CREATE DATABASE `datasource2` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use datasource2;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
		`name` VARCHAR(45) DEFAULT NULL,
		`age` INT(11) DEFAULT NULL,
		PRIMARY KEY (`id`)
);