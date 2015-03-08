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
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `contactNumber` VARCHAR(45) NOT NULL,
  `picture` VARCHAR(45) NULL,
  `dateJoined` DATE NOT NULL,
  `dateLastVisited` DATE NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `idclient_UNIQUE` (`client_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `quantity` FLOAT NOT NULL,
  `price` FLOAT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`branch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`branch` (
  `branch_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `pettycash` FLOAT NOT NULL,
  PRIMARY KEY (`branch_id`),
  UNIQUE INDEX `branch_id_UNIQUE` (`branch_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`employee` (
  `employee_id` INT NOT NULL AUTO_INCREMENT,
  `branch_id` INT NOT NULL,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`service` (
  `service_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  UNIQUE INDEX `service_id_UNIQUE` (`service_id` ASC),
  PRIMARY KEY (`service_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`service line item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`service line item` (
  `servicelineitem_id` INT NOT NULL AUTO_INCREMENT,
  `service_id` INT NOT NULL,
  PRIMARY KEY (`servicelineitem_id`),
  UNIQUE INDEX `servicelineitem_id_UNIQUE` (`servicelineitem_id` ASC),
  INDEX `service_id_idx` (`service_id` ASC),
  CONSTRAINT `service_id`
    FOREIGN KEY (`service_id`)
    REFERENCES `mydb`.`service` (`service_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product line item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product line item` (
  `productlineitem_id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`productlineitem_id`),
  INDEX `product_id_idx` (`product_id` ASC),
  CONSTRAINT `product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transaction` (
  `transaction_id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `servicelineitem_id` INT NOT NULL,
  `productlineitem_id` INT NOT NULL,
  `feedback` VARCHAR(45) NULL,
  PRIMARY KEY (`transaction_id`),
  UNIQUE INDEX `transaction_id_UNIQUE` (`transaction_id` ASC),
  INDEX `client_id_idx` (`client_id` ASC),
  INDEX `employee_id_idx` (`employee_id` ASC),
  INDEX `servicelineitem_id_idx` (`servicelineitem_id` ASC),
  INDEX `productlineitem_id_idx` (`productlineitem_id` ASC),
  CONSTRAINT `client_id`
    FOREIGN KEY (`client_id`)
    REFERENCES `mydb`.`client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `employee_id`
    FOREIGN KEY (`employee_id`)
    REFERENCES `mydb`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `servicelineitem_id`
    FOREIGN KEY (`servicelineitem_id`)
    REFERENCES `mydb`.`service line item` (`servicelineitem_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `productlineitem_id`
    FOREIGN KEY (`productlineitem_id`)
    REFERENCES `mydb`.`product line item` (`productlineitem_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC),
  INDEX `employee_id_idx` (`employee_id` ASC),
  CONSTRAINT `employee_id`
    FOREIGN KEY (`employee_id`)
    REFERENCES `mydb`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`timelog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`timelog` (
  `timelog_id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `timein` DATETIME NOT NULL,
  `timeout` DATETIME NOT NULL,
  PRIMARY KEY (`timelog_id`),
  UNIQUE INDEX `timelog_id_UNIQUE` (`timelog_id` ASC),
  INDEX `employee_id_idx` (`employee_id` ASC),
  CONSTRAINT `employee_id`
    FOREIGN KEY (`employee_id`)
    REFERENCES `mydb`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`receipt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`receipt` (
  `receipt_id` INT NOT NULL AUTO_INCREMENT,
  `transaction_id` INT NOT NULL,
  `client_id` INT NOT NULL,
  `modeOfPayment` VARCHAR(45) NOT NULL,
  `totalBill` FLOAT NOT NULL,
  PRIMARY KEY (`receipt_id`),
  UNIQUE INDEX `receipt_id_UNIQUE` (`receipt_id` ASC),
  INDEX `transaction_id_idx` (`transaction_id` ASC),
  INDEX `client_id_idx` (`client_id` ASC),
  CONSTRAINT `transaction_id`
    FOREIGN KEY (`transaction_id`)
    REFERENCES `mydb`.`transaction` (`transaction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `client_id`
    FOREIGN KEY (`client_id`)
    REFERENCES `mydb`.`client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
