CREATE DATABASE  IF NOT EXISTS `taylortyler` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `taylortyler`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: taylortyler
-- ------------------------------------------------------
-- Server version	5.6.23-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `account_id_UNIQUE1` (`account_id`),
  KEY `employee_id_id1` (`employee_id`),
  CONSTRAINT `employee_id1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,5,'manager5','123','salonmanager'),(2,6,'taylor','123','owner'),(3,7,'manager7','123','salonmanager');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `branch_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `pettycash` float NOT NULL,
  PRIMARY KEY (`branch_id`),
  UNIQUE KEY `branch_id_UNIQUEx` (`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'branch1',4000),(2,'branch2',4000),(3,'branch3',4000);
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `card_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `stamps` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) NOT NULL,
  `mname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `contactNumber` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dateJoined` date NOT NULL,
  `dateLastVisited` date NOT NULL,
  `birthday` date NOT NULL,
  `gender` varchar(45) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `idclient_UNIQUE` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Client','','One','Manila','09000000001','one@mail.com','2015-01-01','2015-03-01','1996-01-01','Male'),(2,'Client','','Two','Manila','09000000002','two@mail.com','2015-01-02','2015-03-02','1996-02-01','Male'),(3,'Client','','Three','Manila','09000000003','three@mail.com','2015-01-03','2015-03-03','1996-03-01','Male'),(4,'Client','','Four','Manila','09000000004','four@mail.com','2015-01-04','2015-03-04','1996-04-01','Male'),(5,'Client','','Five','Manila','09000000005','five@mail.com','2015-01-05','2015-03-05','1996-05-01','Male'),(6,'Client','','Six','Manila','09000000006','six@mail.com','2015-01-06','2015-03-06','1996-06-01','Female'),(7,'Client','T','Seven','Manila','09000000007','seven@mail.com','2015-01-07','2015-03-07','1996-07-01','Female'),(8,'Client','T','Eigth','Manila','09000000008','eight@mail.com','2015-01-08','2015-03-08','1996-08-01','Female'),(9,'Client','T','Nine','Manila','09000000009','nine@mail.com','2015-01-09','2015-03-09','1996-09-01','Female'),(10,'Client','T','Ten','Manila','09000000010','ten@mail.com','2015-01-10','2015-03-10','1996-10-01','Female'),(11,'Sea','','Bear','Manila','1000000000','bearygood','2015-04-21','2015-04-21','2015-06-21','Male'),(12,'not so','very','hot','earth','09169','notveryhot@gmail.not','2015-04-21','2015-04-21','1958-04-01','Female'),(13,'Aj','IDK','Amadora','hahaha','09123456789','aj@yahoo.com','2015-04-21','2015-04-21','2015-04-21','Male');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `dateStartedWorking` date NOT NULL,
  `type` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `employee_id_UNIQUE` (`employee_id`),
  KEY `branch_id_idx` (`branch_id`),
  CONSTRAINT `branch_id` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`branch_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,1,'Sen One','2013-01-02','senior','active'),(2,3,'Sen Two','2014-07-10','senior','active'),(3,2,'Jun Three','2015-03-12','junior','active'),(4,3,'Jun Four','2015-03-12','junior','active'),(5,1,'Man Five','2013-02-13','salonmanager','active'),(6,3,'Taylor Tyler','2010-01-01','owner','active'),(7,2,'Man Seven','2011-02-14','salonmanager','active'),(8,1,'Emp1 Goo','2015-04-22','junior','out'),(9,1,'haha 12','2015-04-22','senior','inactive');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `product`
--

DROP TABLE IF EXISTS `product`;
/*!50001 DROP VIEW IF EXISTS `product`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `product` (
  `product_id` tinyint NOT NULL,
  `name` tinyint NOT NULL,
  `description` tinyint NOT NULL,
  `quantity` tinyint NOT NULL,
  `threshold` tinyint NOT NULL,
  `price` tinyint NOT NULL,
  `measurement` tinyint NOT NULL,
  `available` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `productlineitem`
--

DROP TABLE IF EXISTS `productlineitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productlineitem` (
  `productlineitem_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`productlineitem_id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `xproduct` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productlineitem`
--

LOCK TABLES `productlineitem` WRITE;
/*!40000 ALTER TABLE `productlineitem` DISABLE KEYS */;
INSERT INTO `productlineitem` VALUES (1,1,2,NULL),(2,1,1,NULL),(3,1,1,NULL);
/*!40000 ALTER TABLE `productlineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productlist`
--

DROP TABLE IF EXISTS `productlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productlist` (
  `productlineitem_id` int(11) NOT NULL,
  `transaction_id` int(11) NOT NULL,
  PRIMARY KEY (`productlineitem_id`,`transaction_id`),
  KEY `transaction_id` (`transaction_id`),
  CONSTRAINT `productlineitem_id` FOREIGN KEY (`productlineitem_id`) REFERENCES `productlineitem` (`productlineitem_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `transaction_id` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`transaction_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productlist`
--

LOCK TABLES `productlist` WRITE;
/*!40000 ALTER TABLE `productlist` DISABLE KEYS */;
INSERT INTO `productlist` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `productlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt` (
  `receipt_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `modeOfPayment` varchar(45) NOT NULL,
  `totalBill` float NOT NULL,
  PRIMARY KEY (`receipt_id`),
  UNIQUE KEY `receipt_id_UNIQUE` (`receipt_id`),
  KEY `client_id_idx` (`client_id`),
  CONSTRAINT `client_id2` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (1,1,'2015-04-19 00:06:34','Cash',100),(2,1,'2015-04-19 00:17:54','Cash',100),(3,1,'2015-04-19 03:24:54','Cash',100),(4,1,'2015-04-19 03:42:09','Cash',0),(5,1,'2015-04-21 18:27:03','Cash',0);
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `receiptsummary`
--

DROP TABLE IF EXISTS `receiptsummary`;
/*!50001 DROP VIEW IF EXISTS `receiptsummary`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `receiptsummary` (
  `receipt_id` tinyint NOT NULL,
  `client_id` tinyint NOT NULL,
  `date` tinyint NOT NULL,
  `modeOfPayment` tinyint NOT NULL,
  `totalBill` tinyint NOT NULL,
  `name1` tinyint NOT NULL,
  `name2` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `service_id_UNIQUE` (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Precision Hair Cut','description1',100),(2,'Shampoo-Blow Dry','description1',50),(3,'Herbal Hair Treatment','description1',1000),(4,'Hair & Make-up','description1',500),(5,'Manicure/Pedicure','description1',200),(6,'Wax/Threading','description1',300);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicelineitem`
--

DROP TABLE IF EXISTS `servicelineitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicelineitem` (
  `servicelineitem_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `employee_id1` int(11) DEFAULT NULL,
  `employee_id2` int(11) DEFAULT NULL,
  PRIMARY KEY (`servicelineitem_id`),
  UNIQUE KEY `servicelineitem_id_UNIQUE` (`servicelineitem_id`),
  KEY `service_id_idx` (`service_id`),
  KEY `employee_id1_idx` (`employee_id1`),
  KEY `employee_id2_idx` (`employee_id2`),
  CONSTRAINT `employee_id13` FOREIGN KEY (`employee_id1`) REFERENCES `employee` (`employee_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `employee_id23` FOREIGN KEY (`employee_id2`) REFERENCES `employee` (`employee_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `service_id3` FOREIGN KEY (`service_id`) REFERENCES `service` (`service_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicelineitem`
--

LOCK TABLES `servicelineitem` WRITE;
/*!40000 ALTER TABLE `servicelineitem` DISABLE KEYS */;
INSERT INTO `servicelineitem` VALUES (1,1,1,1,3),(2,1,1,1,NULL);
/*!40000 ALTER TABLE `servicelineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicelist`
--

DROP TABLE IF EXISTS `servicelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicelist` (
  `servicelineitem_id` int(11) NOT NULL,
  `transaction_id` int(11) NOT NULL,
  PRIMARY KEY (`servicelineitem_id`,`transaction_id`),
  KEY `transaction_id2` (`transaction_id`),
  CONSTRAINT `servicelineitem_id2` FOREIGN KEY (`servicelineitem_id`) REFERENCES `servicelineitem` (`servicelineitem_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `transaction_id2` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`transaction_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicelist`
--

LOCK TABLES `servicelist` WRITE;
/*!40000 ALTER TABLE `servicelist` DISABLE KEYS */;
INSERT INTO `servicelist` VALUES (1,2),(2,3);
/*!40000 ALTER TABLE `servicelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timelog`
--

DROP TABLE IF EXISTS `timelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timelog` (
  `timelog_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `timein` datetime NOT NULL,
  `timeout` datetime NOT NULL,
  PRIMARY KEY (`timelog_id`),
  UNIQUE KEY `timelog_id_UNIQUE2` (`timelog_id`),
  KEY `employee_id_id2` (`employee_id`),
  CONSTRAINT `employee_id2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timelog`
--

LOCK TABLES `timelog` WRITE;
/*!40000 ALTER TABLE `timelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `timelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `feedback` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  UNIQUE KEY `transaction_id_UNIQUE` (`transaction_id`),
  KEY `client_id_idx` (`client_id`),
  CONSTRAINT `client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,1,''),(2,1,''),(3,1,'');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactionlist`
--

DROP TABLE IF EXISTS `transactionlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactionlist` (
  `transaction_id` int(11) NOT NULL,
  `receipt_id` int(11) NOT NULL,
  PRIMARY KEY (`transaction_id`,`receipt_id`),
  KEY `receipt_id_idx` (`receipt_id`),
  CONSTRAINT `receipt_id3` FOREIGN KEY (`receipt_id`) REFERENCES `receipt` (`receipt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `transaction_id3` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`transaction_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactionlist`
--

LOCK TABLES `transactionlist` WRITE;
/*!40000 ALTER TABLE `transactionlist` DISABLE KEYS */;
INSERT INTO `transactionlist` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `transactionlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xproduct`
--

DROP TABLE IF EXISTS `xproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xproduct` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `quantity` float NOT NULL,
  `threshold` int(11) NOT NULL,
  `price` float DEFAULT NULL,
  `measurement` varchar(45) DEFAULT NULL,
  `available` varchar(45) NOT NULL DEFAULT 'available',
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xproduct`
--

LOCK TABLES `xproduct` WRITE;
/*!40000 ALTER TABLE `xproduct` DISABLE KEYS */;
INSERT INTO `xproduct` VALUES (1,'Shampoo','For the hair',44,0,100,'Cups','available'),(2,'Conditioner','For the hair',40,0,90,'Cups','available'),(3,'Herbal','for the hair',30,0,200,NULL,'available'),(4,'Wax','for styling the hair',20,0,150,NULL,'available'),(5,'Nail Polish','for the nails',10,0,50,'mL','available'),(6,'Hair Color','for coloring the hair',10,0,NULL,'mL','available');
/*!40000 ALTER TABLE `xproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `product`
--

/*!50001 DROP TABLE IF EXISTS `product`*/;
/*!50001 DROP VIEW IF EXISTS `product`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `product` AS select `xproduct`.`product_id` AS `product_id`,`xproduct`.`name` AS `name`,`xproduct`.`description` AS `description`,`xproduct`.`quantity` AS `quantity`,`xproduct`.`threshold` AS `threshold`,`xproduct`.`price` AS `price`,`xproduct`.`measurement` AS `measurement`,`xproduct`.`available` AS `available` from `xproduct` where (`xproduct`.`available` like 'available') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `receiptsummary`
--

/*!50001 DROP TABLE IF EXISTS `receiptsummary`*/;
/*!50001 DROP VIEW IF EXISTS `receiptsummary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `receiptsummary` AS select `receipt`.`receipt_id` AS `receipt_id`,`receipt`.`client_id` AS `client_id`,`receipt`.`date` AS `date`,`receipt`.`modeOfPayment` AS `modeOfPayment`,`receipt`.`totalBill` AS `totalBill`,concat(`c`.`fname`,' ',`c`.`mname`,' ',`c`.`lname`) AS `name1`,concat(`c`.`fname`,' ',`c`.`lname`) AS `name2` from (`receipt` join `client` `c` on((`c`.`client_id` = `receipt`.`client_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-22 12:50:51
