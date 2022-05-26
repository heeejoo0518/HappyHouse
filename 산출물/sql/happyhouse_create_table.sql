-- ------------------------------------------------------ 
-- create table
-- ------------------------------------------------------

-- create schema happyhouse;
-- use happyhouse;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- ssafy_member
-- DROP TABLE IF EXISTS `happyhouse`.`ssafy_member` ;
CREATE TABLE IF NOT EXISTS `happyhouse`.`ssafy_member` (
  `userid` VARCHAR(16) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `userpwd` VARCHAR(16) NOT NULL,
  `email` VARCHAR(20) NULL DEFAULT NULL,
  `joindate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- board
-- DROP TABLE IF EXISTS board;
CREATE TABLE IF NOT EXISTS `happyhouse`.`board` (
  `articleno` INT NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`articleno`),
  INDEX `board_to_member_fk` (`userid` ASC) VISIBLE,
  CONSTRAINT `board_to_member_fk`
    FOREIGN KEY (`userId`)
    REFERENCES `happyhouse`.`ssafy_member` (`userid`) on delete cascade)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- comment
-- DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `happyhouse`.`comment` (
 `commentno` INT NOT NULL AUTO_INCREMENT,
 `articleno`  INT NOT NULL ,
 `userid` VARCHAR(16) NULL DEFAULT NULL,
 `content` VARCHAR(2000) NULL DEFAULT NULL,
 `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`commentno`),
INDEX `comment_to_member_fk` (`userid` ASC) VISIBLE,
CONSTRAINT `comment_to_member_fk`
FOREIGN KEY (`userid`) REFERENCES `happyhouse`.`ssafy_member` (`userid`) on delete cascade,
INDEX `comment_to_board_fk` (`articleno` ASC) VISIBLE,
CONSTRAINT `comment_to_board_fk`
FOREIGN KEY (`articleno`) REFERENCES `happyhouse`.`board` (`articleno`) on delete cascade
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- dongcode
DROP TABLE IF EXISTS `dongcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dongcode` (
  `dongCode` varchar(10) NOT NULL,
  `sidoName` varchar(30) DEFAULT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  `dongName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`dongCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- baseaddress
DROP TABLE IF EXISTS `baseaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baseaddress` (
  `no` int NOT NULL AUTO_INCREMENT,
  `sidoName` varchar(30) DEFAULT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  `dongName` varchar(30) DEFAULT NULL,
  `dongCode` varchar(10) DEFAULT NULL,
  `lat` varchar(20) DEFAULT NULL,
  `lng` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `dongCode` (`dongCode`),
  CONSTRAINT `baseaddress_ibfk_1` FOREIGN KEY (`dongCode`) REFERENCES `dongcode` (`dongCode`)
) ENGINE=InnoDB AUTO_INCREMENT=3597 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- houseinfo
DROP TABLE IF EXISTS `houseinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `houseinfo` (
  `aptCode` bigint NOT NULL,
  `buildYear` int DEFAULT NULL,
  `roadName` varchar(40) DEFAULT NULL,
  `roadNameBonbun` varchar(5) DEFAULT NULL,
  `roadNameBubun` varchar(5) DEFAULT NULL,
  `roadNameSeq` varchar(2) DEFAULT NULL,
  `roadNameBasementCode` varchar(1) DEFAULT NULL,
  `roadNameCode` varchar(7) DEFAULT NULL,
  `dong` varchar(40) DEFAULT NULL,
  `bonbun` varchar(4) DEFAULT NULL,
  `bubun` varchar(4) DEFAULT NULL,
  `sigunguCode` varchar(5) DEFAULT NULL,
  `eubmyundongCode` varchar(5) DEFAULT NULL,
  `dongCode` varchar(10) DEFAULT NULL,
  `landCode` varchar(1) DEFAULT NULL,
  `apartmentName` varchar(40) DEFAULT NULL,
  `jibun` varchar(10) DEFAULT NULL,
  `lng` varchar(30) DEFAULT NULL,
  `lat` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`aptCode`),
  UNIQUE KEY `UNIQUE` (`buildYear`,`apartmentName`,`jibun`,`sigunguCode`,`eubmyundongCode`) /*!80000 INVISIBLE */,
  KEY `houseinfo_dongCode_dongcode_dongCode_fk_idx` (`dongCode`) /*!80000 INVISIBLE */,
  CONSTRAINT `houseinfo_dongCode_dongcode_dongCode_fk` FOREIGN KEY (`dongCode`) REFERENCES `dongcode` (`dongCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- housedeal
DROP TABLE IF EXISTS `housedeal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `housedeal` (
  `no` bigint NOT NULL,
  `dealAmount` varchar(20) DEFAULT NULL,
  `dealYear` int DEFAULT NULL,
  `dealMonth` int DEFAULT NULL,
  `dealDay` int DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `floor` varchar(4) DEFAULT NULL,
  `cancelDealType` varchar(1) DEFAULT NULL,
  `aptCode` bigint DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `housedeal_aptCode_houseinfo_aptCode_fk_idx` (`aptCode`),
  CONSTRAINT `housedeal_aptCode_houseinfo_aptCode_fk` FOREIGN KEY (`aptCode`) REFERENCES `houseinfo` (`aptCode`) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- area
-- DROP TABLE IF EXISTS  area;
create table if not exists `happyhouse`.`area` (
	`no` int not null auto_increment,
    `userid` varchar(16) not null,
    `dongcode` varchar(10) not null,
    primary key(`no`),
    index `area_to_member_fk` (`userid` ASC) visible,
    constraint `area_to_member_fk`
		foreign key(`userid`)
        references `happyhouse`.`ssafy_member` (`userid`)  on delete cascade,
	index `area_to_dongcode_fk` (`dongCode` ASC) visible,
    constraint `area_to_dongcode_fk`
		foreign key(`dongcode`)
        references `happyhouse`.`dongcode` (`dongCode`)  on delete cascade
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- likeapt
create table if not exists `happyhouse`.`likeapt` (
	`no` int not null auto_increment,
    `userid` varchar(16) not null,
    `aptCode` bigint not null,
    primary key(`no`),
    unique(`userid`,`aptCode`),
    index `apt_to_member_fk` (`userid` ASC) visible,
    constraint `apt_to_member_fk`
		foreign key(`userid`)
        references `happyhouse`.`ssafy_member` (`userid`)  on delete cascade,
	index `apt_to_houseinfo_fk` (`aptCode` ASC) visible,
    constraint `apt_to_houseinfo_fk`
		foreign key(`aptCode`)
        references `happyhouse`.`houseinfo` (`aptCode`) on delete cascade
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- tickets
-- DROP TABLE IF EXISTS tickets;
CREATE TABLE IF NOT EXISTS `happyhouse`.`tickets` (
  `ticketno` INT NOT NULL AUTO_INCREMENT,
  `artist` VARCHAR(16) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `location` VARCHAR(100) NULL DEFAULT NULL,
  `ticketdate` VARCHAR(100),
  `img` VARCHAR(300),
  `link` VARCHAR(300),
  PRIMARY KEY (`ticketno`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
