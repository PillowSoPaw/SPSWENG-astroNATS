-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`branch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`branch` (
  `branch_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `pettycash` FLOAT NOT NULL,
  PRIMARY KEY (`branch_id`),
  UNIQUE INDEX `branch_id_UNIQUEx` (`branch_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `mydb`.`branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `mydb`.`branch` VALUES (1,'branch1',4000),(2,'branch2',4000),(3,'branch3',4000);
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `mydb`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`employee` (
  `employee_id` INT(11) NOT NULL AUTO_INCREMENT,
  `branch_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `dateStartedWorking` DATE NOT NULL,
  `hoursRendered` FLOAT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC),
  INDEX `branch_id_idx` (`branch_id` ASC),
  CONSTRAINT `branch_id`
    FOREIGN KEY (`branch_id`)
    REFERENCES `mydb`.`branch` (`branch_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `mydb`.`employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `mydb`.`employee` VALUES (1,1,'Sen One','2013-01-02',10,'senior'),(2,3,'Sen Two','2014-07-10',20,'senior'),(3,2,'Jun Three','2015-03-12',1,'junior'),(4,3,'Jun Four','2015-03-12',5,'junior'),(5,1,'Man Five','2013-02-13',50,'salonmanager'),(6,3,'Taylor Tyler','2010-01-01',0,'owner'),(7,2,'Man Seven','2011-02-14',90,'salonmanager');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `mydb`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`account` (
  `account_id` INT(11) NOT NULL AUTO_INCREMENT,
  `employee_id` INT(11) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `account_id_UNIQUE1` (`account_id` ASC),
  INDEX `employee_id_id1` (`employee_id` ASC),
  CONSTRAINT `employee_id1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `mydb`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `mydb`.`account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `mydb`.`account` VALUES (1,5,'manager5','123','salonmanager'),(2,6,'taylor','123','owner'),(3,7,'manager7','123','salonmanager');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `mydb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client` (
  `client_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `contactNumber` VARCHAR(45) NOT NULL,
  `picture` VARCHAR(45) NULL DEFAULT NULL,
  `dateJoined` DATE NOT NULL,
  `dateLastVisited` DATE NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `idclient_UNIQUE` (`client_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `mydb`.`client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `mydb`.`client` VALUES (1,'Client One','Manila','09000000001',NULL,'2015-01-01','2015-03-01'),(2,'Client Two','Manila','09000000002',NULL,'2015-01-02','2015-03-02'),(3,'Client Three','Manila','09000000003',NULL,'2015-01-03','2015-03-03'),(4,'Client Four','Manila','09000000004',NULL,'2015-01-04','2015-03-04');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `quantity` FLOAT NOT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `mydb`.`product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `mydb`.`product` VALUES (1,'Shampoo',50,100),(2,'Conditioner',40,90),(3,'Herbal',30,200),(4,'Wax',20,150),(5,'Nail Polish',10,50);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `mydb`.`product line item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product line item` (
  `productlineitem_id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_id` INT(11) NOT NULL,
  `quantity` INT(11) NOT NULL,
  PRIMARY KEY (`productlineitem_id`),
  INDEX `product_id_idx` (`product_id` ASC),
  CONSTRAINT `product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`productlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`productlist` (
  `productlist_id` INT(11) NOT NULL,
  `productlineitem_id` INT(11) NOT NULL,
  PRIMARY KEY (`productlist_id`),
  UNIQUE INDEX `productlist_id_UNIQUE` (`productlist_id` ASC),
  INDEX `productlineitem_id_idx` (`productlineitem_id` ASC),
  CONSTRAINT `productlineitem_id`
    FOREIGN KEY (`productlineitem_id`)
    REFERENCES `mydb`.`product line item` (`productlineitem_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`transactionlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transactionlist` (
  `transactionlist_id` INT NOT NULL,
  `transaction_id` INT NOT NULL,
  PRIMARY KEY (`transactionlist_id`),
  UNIQUE INDEX `transactionlist_id_UNIQUE` (`transactionlist_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`receipt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`receipt` (
  `receipt_id` INT(11) NOT NULL AUTO_INCREMENT,
  `client_id` INT(11) NOT NULL,
  `transactionlist_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `modeOfPayment` VARCHAR(45) NOT NULL,
  `totalBill` FLOAT NOT NULL,
  PRIMARY KEY (`receipt_id`),
  UNIQUE INDEX `receipt_id_UNIQUE` (`receipt_id` ASC),
  INDEX `client_id_idx` (`client_id` ASC),
  INDEX `transactionlist_id_idx` (`transactionlist_id` ASC),
  CONSTRAINT `client_id2`
    FOREIGN KEY (`client_id`)
    REFERENCES `mydb`.`client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `transactionlist_id`
    FOREIGN KEY (`transactionlist_id`)
    REFERENCES `mydb`.`transactionlist` (`transactionlist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`service` (
  `service_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE INDEX `service_id_UNIQUE` (`service_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `mydb`.`service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `mydb`.`service` VALUES (1,'Precision Hair Cut','description1',100),(2,'Shampoo-Blow Dry','description1',50),(3,'Herbal Hair Treatment','description1',1000),(4,'Hair & Make-up','description1',500),(5,'Manicure/Pedicure','description1',200),(6,'Wax/Threading','description1',300);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `mydb`.`service line item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`service line item` (
  `servicelineitem_id` INT(11) NOT NULL AUTO_INCREMENT,
  `service_id` INT(11) NOT NULL,
  `quantity` INT(11) NOT NULL,
  `employee_id1` INT(11) NOT NULL,
  `employee_id2` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`servicelineitem_id`),
  UNIQUE INDEX `servicelineitem_id_UNIQUE` (`servicelineitem_id` ASC),
  INDEX `service_id_idx` (`service_id` ASC),
  CONSTRAINT `service_id`
    FOREIGN KEY (`service_id`)
    REFERENCES `mydb`.`service` (`service_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`servicelist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`servicelist` (
  `servicelist_id` INT(11) NOT NULL,
  `servicelineitem_id` INT(11) NOT NULL,
  PRIMARY KEY (`servicelist_id`),
  UNIQUE INDEX `servicelist_id_UNIQUE` (`servicelist_id` ASC),
  INDEX `servicelineitem_id_idx` (`servicelineitem_id` ASC),
  CONSTRAINT `servicelineitem_id`
    FOREIGN KEY (`servicelineitem_id`)
    REFERENCES `mydb`.`service line item` (`servicelineitem_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`timelog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`timelog` (
  `timelog_id` INT(11) NOT NULL AUTO_INCREMENT,
  `employee_id` INT(11) NOT NULL,
  `date` DATE NOT NULL,
  `timein` DATETIME NOT NULL,
  `timeout` DATETIME NOT NULL,
  PRIMARY KEY (`timelog_id`),
  UNIQUE INDEX `timelog_id_UNIQUE2` (`timelog_id` ASC),
  INDEX `employee_id_id2` (`employee_id` ASC),
  CONSTRAINT `employee_id2`
    FOREIGN KEY (`employee_id`)
    REFERENCES `mydb`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transaction` (
  `transaction_id` INT(11) NOT NULL AUTO_INCREMENT,
  `client_id` INT(11) NOT NULL,
  `productlist_id` INT(11) NOT NULL,
  `servicelist_id` INT(11) NOT NULL,
  `feedback` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  UNIQUE INDEX `transaction_id_UNIQUE` (`transaction_id` ASC),
  INDEX `client_id_idx` (`client_id` ASC),
  INDEX `servicelist_id_idx` (`servicelist_id` ASC),
  INDEX `productlist_id_idx` (`productlist_id` ASC),
  CONSTRAINT `client_id`
    FOREIGN KEY (`client_id`)
    REFERENCES `mydb`.`client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `productlist_id`
    FOREIGN KEY (`productlist_id`)
    REFERENCES `mydb`.`productlist` (`productlist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `servicelist_id`
    FOREIGN KEY (`servicelist_id`)
    REFERENCES `mydb`.`servicelist` (`servicelist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
