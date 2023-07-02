-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `enjoytrip` ;

-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `enjoytrip` ;

-- -----------------------------------------------------
-- Table `enjoytrip`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`user` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`user` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `id` VARCHAR(20) NOT NULL,
  `pw` VARCHAR(100) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `status` ENUM('admin', 'normal') NOT NULL DEFAULT 'normal',
  `token` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`board` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`board` (
  `board_id` INT NOT NULL AUTO_INCREMENT,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `user_id` VARCHAR(16) NOT NULL,
  `hit` INT NULL DEFAULT '0',
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` ENUM('notice', 'free', 'qna') NOT NULL DEFAULT 'free',
  PRIMARY KEY (`board_id`),
  INDEX `board_to_user_user_id_idx` (`user_id` ASC),
  CONSTRAINT `board_to_user_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`id`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB
AUTO_INCREMENT = 33871
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`comment` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `comment` VARCHAR(500) NULL DEFAULT NULL,
  `memo_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `board_id` INT NOT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `comment_to_board_board_id_fk_idx` (`board_id` ASC),
  INDEX `comment_to_user_user_id_fk_idx` (`user_id` ASC),
  CONSTRAINT `comment_to_board_board_id_fk`
    FOREIGN KEY (`board_id`)
    REFERENCES `enjoytrip`.`board` (`board_id`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `comment_to_user_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`id`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`trip` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`trip` (
  `trip_id` INT NOT NULL AUTO_INCREMENT,
  `trip_name` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(20) NULL DEFAULT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`trip_id`),
  INDEX `plan_to_user_user_id_idx` (`user_id` ASC),
  CONSTRAINT `plan_to_user_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`id`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`plan` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`plan` (
  `plan_id` INT NOT NULL auto_increment,
 `trip_id` INT NOT NULL,
  `attraction_id` INT NOT NULL,
  `plan_date` INT NOT NULL DEFAULT 1,
  `plan_order` INT NOT NULL DEFAULT 1,
  `plan_budget` INT NOT NULL DEFAULT 0,
  `plan_desc` VARCHAR(2000) NULL,
  PRIMARY KEY (`plan_id`),
  INDEX `plan_to_trip_trip_id_idx` (`trip_id` ASC),
  CONSTRAINT `plan_to_trip_trip_id`
    FOREIGN KEY (`trip_id`)
    REFERENCES `enjoytrip`.`trip` (`trip_id`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
