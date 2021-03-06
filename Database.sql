-- MySQL Script generated by MySQL Workbench
-- 05/13/15 02:19:37
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema plance
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema plance
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `plance` DEFAULT CHARACTER SET utf8 ;
USE `plance` ;

-- -----------------------------------------------------
-- Table `plance`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`address` (
  `address_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `number` INT(11) NOT NULL,
  `postcode` INT(11) NOT NULL,
  `country` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`address_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`category` (
  `category_id` INT(11) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`timeinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`timeinfo` (
  `create_time` DATE NULL DEFAULT NULL,
  `update_time` DATE NULL DEFAULT NULL,
  `ts_ID` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ts_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`location` (
  `location_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `address_address_ID` INT(11) NULL DEFAULT NULL,
  `capacity` INT(11) NULL DEFAULT NULL,
  `fk_timeinfo` INT(11) NOT NULL,
  `publicity` TINYINT(1) NOT NULL,
  PRIMARY KEY (`location_ID`),
  INDEX `fk_adressID_idx` (`address_address_ID` ASC),
  INDEX `fk_timestamp_idx` (`fk_timeinfo` ASC),
  CONSTRAINT `fk_l_adressID`
    FOREIGN KEY (`address_address_ID`)
    REFERENCES `plance`.`address` (`address_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_l_timeinfo`
    FOREIGN KEY (`fk_timeinfo`)
    REFERENCES `plance`.`timeinfo` (`ts_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`motto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`motto` (
  `mottoName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`mottoName`),
  UNIQUE INDEX `mottoName_UNIQUE` (`mottoName` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`celebration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`celebration` (
  `c_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `c_name` VARCHAR(45) NOT NULL,
  `fk_category_category_id` INT(11) NOT NULL,
  `fk_timeinfo` INT(11) NOT NULL,
  `fk_location_location_ID` INT(11) NOT NULL,
  `fk_motto_mottoName` VARCHAR(45) NOT NULL,
  `fk_user_organizer` INT(11) NOT NULL,
  PRIMARY KEY (`c_ID`),
  INDEX `fk_category_id_idx` (`fk_category_category_id` ASC),
  INDEX `ts_ID_idx` (`fk_timeinfo` ASC),
  INDEX `tk_loc_ID_idx` (`fk_location_location_ID` ASC),
  INDEX `fk_motto_idx` (`fk_motto_mottoName` ASC),
  CONSTRAINT `fk_c_category_id`
    FOREIGN KEY (`fk_category_category_id`)
    REFERENCES `plance`.`category` (`category_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_c_loc_ID`
    FOREIGN KEY (`fk_location_location_ID`)
    REFERENCES `plance`.`location` (`location_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_c_motto`
    FOREIGN KEY (`fk_motto_mottoName`)
    REFERENCES `plance`.`motto` (`mottoName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_c_timeinfo_ID`
    FOREIGN KEY (`fk_timeinfo`)
    REFERENCES `plance`.`timeinfo` (`ts_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`person` (
  `personID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `pseodo` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`personID`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`invite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`invite` (
  `celebrationID` INT(11) NOT NULL,
  `personID` INT(11) NOT NULL,
  `participatin` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`celebrationID`, `personID`),
  INDEX `fk_pc_personID_idx` (`personID` ASC),
  CONSTRAINT `fk_pc_c_ID`
    FOREIGN KEY (`celebrationID`)
    REFERENCES `plance`.`celebration` (`c_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pc_personID`
    FOREIGN KEY (`personID`)
    REFERENCES `plance`.`person` (`personID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`tag` (
  `tagname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tagname`),
  UNIQUE INDEX `name_UNIQUE` (`tagname` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`location_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`location_tag` (
  `locations_location_ID` INT(11) NOT NULL,
  `tags_tagname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`locations_location_ID`, `tags_tagname`),
  INDEX `fk_tag_idx` (`tags_tagname` ASC),
  CONSTRAINT `fk_lt_locTag`
    FOREIGN KEY (`tags_tagname`)
    REFERENCES `plance`.`tag` (`tagname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_lt_locationID`
    FOREIGN KEY (`locations_location_ID`)
    REFERENCES `plance`.`location` (`location_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`motto_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`motto_tag` (
  `fk_mt_mottoName` VARCHAR(45) NOT NULL,
  `tags_tagname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tags_tagname`, `fk_mt_mottoName`),
  INDEX `fk_tag_idx` (`tags_tagname` ASC),
  INDEX `fk_mt_motto` (`fk_mt_mottoName` ASC),
  CONSTRAINT `fk_mt_motTag`
    FOREIGN KEY (`tags_tagname`)
    REFERENCES `plance`.`tag` (`tagname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_mt_motto`
    FOREIGN KEY (`fk_mt_mottoName`)
    REFERENCES `plance`.`motto` (`mottoName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`paidobject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`paidobject` (
  `poID` INT(11) NOT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `amount` INT(11) NULL DEFAULT NULL,
  `reflink` VARCHAR(255) NULL DEFAULT NULL,
  `fk_c_ID` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`poID`),
  INDEX `fk_po_c_ID_idx` (`fk_c_ID` ASC),
  CONSTRAINT `fk_po_c_ID`
    FOREIGN KEY (`fk_c_ID`)
    REFERENCES `plance`.`celebration` (`c_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`payment` (
  `PaidObject_poID` INT(11) NOT NULL,
  `Person_personID` INT(11) NOT NULL,
  `amount` DOUBLE NULL DEFAULT NULL,
  `paymentOption` VARCHAR(45) NOT NULL,
  `paid` TINYINT(1) NULL DEFAULT NULL,
  INDEX `fk_PaidObject_has_Person_Person1_idx` (`Person_personID` ASC),
  INDEX `fk_PaidObject_has_Person_PaidObject1_idx` (`PaidObject_poID` ASC),
  CONSTRAINT `fk_PaidObject_has_Person_PaidObject1`
    FOREIGN KEY (`PaidObject_poID`)
    REFERENCES `plance`.`paidobject` (`poID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_PaidObject_has_Person_Person1`
    FOREIGN KEY (`Person_personID`)
    REFERENCES `plance`.`person` (`personID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `plance`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plance`.`user` (
  `username` VARCHAR(16) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `fk_timeinfo` INT(11) NOT NULL,
  `oauth` BLOB NULL DEFAULT NULL,
  `person_personID` INT(11) NOT NULL,
  PRIMARY KEY (`person_personID`),
  INDEX `fk_u_personID_idx` (`person_personID` ASC),
  UNIQUE INDEX `person_personID_UNIQUE` (`person_personID` ASC),
  CONSTRAINT `fk_u_personID`
    FOREIGN KEY (`person_personID`)
    REFERENCES `plance`.`person` (`personID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
