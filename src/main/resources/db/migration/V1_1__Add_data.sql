USE `dreamhouse2`;

LOCK TABLES `customer` WRITE;
INSERT INTO `customer` VALUES (1,'petro@g.com','Stepanovich','Petro','15.03.2001','','56681','CE','petro','0978956100','Seniv'),(2,'ostap@g.com','Andriyovich','Ostap','2.03.1995','','88724','AB','ostap','0974059466','Hlushko');
UNLOCK TABLES;

LOCK TABLES `house` WRITE;
INSERT INTO `house` VALUES (1,'Striyska 180-A','no description','House 1'),(2,'Shevchenka 14','no description','House 2');
UNLOCK TABLES;


LOCK TABLES `flat` WRITE;
INSERT INTO `flat` VALUES (1,'',1,1,72,0,2,'free',NULL,1),(2,'',2,1,65,0,1,'free',NULL,1),(3,'',3,1,80,0,2,'free',NULL,1),(4,'',4,1,98,0,3,'free',NULL,1),(5,'',5,2,72,0,2,'sold',1,1),(6,'',6,2,65,0,1,'free',NULL,1),(7,'',7,2,80,0,2,'free',NULL,1),(8,'',8,2,98,0,3,'free',NULL,1),(9,'',9,3,72,0,2,'free',NULL,1),(10,'',10,3,65,0,1,'free',NULL,1),(11,'',11,3,80,0,2,'free',NULL,1),(12,'',12,3,98,0,3,'sold',2,1);
UNLOCK TABLES;


LOCK TABLES `pantry` WRITE;
INSERT INTO `pantry` VALUES (1,'','0/1',1,5,0,'free',NULL,1),(2,'','0/1',2,6.5,0,'free',NULL,1),(3,'','1/2',3,5,0,'free',NULL,1),(4,'','1/2',4,6.5,0,'free',NULL,1),(5,'','2/3',5,5,0,'sold',2,1),(6,'','2/3',6,6.5,0,'free',NULL,1);
UNLOCK TABLES;


LOCK TABLES `parking` WRITE;
INSERT INTO `parking` VALUES (1,1,'sold',1,1),(2,2,'sold',2,1),(3,3,'free',NULL,1),(4,4,'free',NULL,1),(5,5,'free',NULL,1);
UNLOCK TABLES;

LOCK TABLES `payment` WRITE;
INSERT INTO `payment` VALUES (1,730000,26043.8,'03.01.2018',28.0297,2,1),(2,135000,4965.63,'02.02.2018',27.1869,2,1);
UNLOCK TABLES;

LOCK TABLES `schedule` WRITE;
INSERT INTO `schedule` VALUES (1,10000,'02.01.2018',2,1),(2,5000,'02.02.2018',2,1),(3,5000,'02.03.2018',2,1),(4,5000,'02.04.2018',2,1),(5,5000,'02.05.2018',2,1);
UNLOCK TABLES;