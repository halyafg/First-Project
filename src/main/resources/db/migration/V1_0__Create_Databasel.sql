CREATE DATABASE  IF NOT EXISTS `dreamhouse2`;

use dreamhouse2;


CREATE TABLE `house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `pasportData` varchar(255) DEFAULT NULL,
  `pasportKimVidan` varchar(255) DEFAULT NULL,
  `pasportNumber` varchar(255) DEFAULT NULL,
  `pasportSeria` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3qgg01qojcmbdp47dkaom9x45` (`email`),
  UNIQUE KEY `UK_lff31qpmftf6n3c1wtbytskdx` (`password`),
  UNIQUE KEY `UK_akg8sq47af8on5q5s984n29fw` (`phone`)
  );


CREATE TABLE `flat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `flatNumber` int(11) DEFAULT NULL,
  `floor` int(11) DEFAULT NULL,
  `projectSize` double DEFAULT NULL,
  `realSize` double DEFAULT NULL,
  `romsNumber` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `house_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_255ae8geyedala4acojmntsn4` (`customer_id`),
  KEY `FK_bg62ldx1w7arjyewlf42eg21` (`house_id`),
  CONSTRAINT `FK_255ae8geyedala4acojmntsn4` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_bg62ldx1w7arjyewlf42eg21` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`)
  );
  

CREATE TABLE `pantry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `floor` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `projectSize` double DEFAULT NULL,
  `realSize` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `house_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_72sik6rgvka8912vh68gyjs9s` (`customer_id`),
  KEY `FK_lawkqsbs9l4wnanldm4ej63jv` (`house_id`),
  CONSTRAINT `FK_72sik6rgvka8912vh68gyjs9s` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_lawkqsbs9l4wnanldm4ej63jv` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`)
  );
  

CREATE TABLE `parking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `house_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1lri3o1n4umctbcr54stmaa6d` (`customer_id`),
  KEY `FK_6bxjhwhx5vj9kl71m5mop1il3` (`house_id`),
  CONSTRAINT `FK_1lri3o1n4umctbcr54stmaa6d` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_6bxjhwhx5vj9kl71m5mop1il3` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`)
  );


  

CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amountGRN` double DEFAULT NULL,
  `amountUSA` double DEFAULT NULL,
  `data` varchar(255) DEFAULT NULL,
  `quoteUSA` double DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `house_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1fv5iaanbro03u14qhglyrb4m` (`customer_id`),
  KEY `FK_i3lwfdu9uejg19ku82tg3knl0` (`house_id`),
  CONSTRAINT `FK_1fv5iaanbro03u14qhglyrb4m` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_i3lwfdu9uejg19ku82tg3knl0` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`)
);


CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amountUSA` double DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `house_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s2cvtgyqyfmbd49106sgjup0p` (`customer_id`),
  KEY `FK_78y72aplk0iuo7gq328r186th` (`house_id`),
  CONSTRAINT `FK_78y72aplk0iuo7gq328r186th` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`),
  CONSTRAINT `FK_s2cvtgyqyfmbd49106sgjup0p` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);


CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imagePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `flat_image` (
  `flatId` int(11) NOT NULL,
  `imageId` int(11) NOT NULL,
  KEY `FK_7af00cnuj2gjlr9vk26h5ui2e` (`imageId`),
  KEY `FK_4ssqkiq7a1rv3ua7n4vgqtlov` (`flatId`),
  CONSTRAINT `FK_4ssqkiq7a1rv3ua7n4vgqtlov` FOREIGN KEY (`flatId`) REFERENCES `flat` (`id`),
  CONSTRAINT `FK_7af00cnuj2gjlr9vk26h5ui2e` FOREIGN KEY (`imageId`) REFERENCES `image` (`id`)
);
