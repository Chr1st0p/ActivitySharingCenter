CREATE DATABASE  IF NOT EXISTS `sos` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sos`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: sos
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.16-MariaDB

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `description` text NOT NULL,
  `creator` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `joined` int(11) NOT NULL DEFAULT '0',
  `rating` int(11) NOT NULL DEFAULT '5',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `category` int(11) NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `location` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'Sqaure Dancing in Jurong East','Sqaure Dancing in Jurong East',2,1,-6,0,'2017-03-28 14:58:11',1,33.3268,53.3268,'Sqaure Dancing in Jurong East'),(2,'Datigng','Datigng',2,0,23,5,'2017-03-28 14:58:11',1,33.3268,53.3268,'Datigng');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Football'),(2,'Dancing'),(3,'Basketball');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL,
  `participant_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant`
--

LOCK TABLES `participant` WRITE;
/*!40000 ALTER TABLE `participant` DISABLE KEYS */;
INSERT INTO `participant` VALUES (12,1,5);
/*!40000 ALTER TABLE `participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `Id` int(11) NOT NULL,
  `IdAct` int(11) NOT NULL,
  `content` varchar(400) NOT NULL,
  `IdSender` int(11) NOT NULL,
  `SendTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `IdAct` (`IdAct`),
  KEY `IdSender` (`IdSender`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`IdAct`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`IdSender`) REFERENCES `user` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,1,'Hello world',2,'2017-03-23 07:08:16');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `rate` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (5,5,1,4),(8,5,2,3);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replay`
--

DROP TABLE IF EXISTS `replay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `replay` (
  `Id` int(11) NOT NULL,
  `IdParent` int(11) NOT NULL,
  `IdReply` int(11) DEFAULT '-1',
  `content` varchar(400) NOT NULL,
  `IdSender` int(11) NOT NULL,
  `SendTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `IdSender` (`IdSender`),
  KEY `IdParent` (`IdParent`),
  CONSTRAINT `replay_ibfk_1` FOREIGN KEY (`IdSender`) REFERENCES `user` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `replay_ibfk_2` FOREIGN KEY (`IdParent`) REFERENCES `post` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replay`
--

LOCK TABLES `replay` WRITE;
/*!40000 ALTER TABLE `replay` DISABLE KEYS */;
INSERT INTO `replay` VALUES (1,1,-1,'<p>w</p>',2,'2017-03-23 07:09:17'),(2,1,-1,'<p>fsdf</p>',2,'2017-03-23 08:19:13');
/*!40000 ALTER TABLE `replay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(127) NOT NULL,
  `Password` char(32) NOT NULL,
  `Fullname` varchar(80) NOT NULL,
  `Phone` char(8) NOT NULL,
  `User_Group` tinyint(1) NOT NULL DEFAULT '3',
  `Last_Login` timestamp(6) NULL DEFAULT NULL,
  `Is_Active` bit(1) NOT NULL DEFAULT b'0',
  `Salt` char(10) NOT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'a@b.c','beeff9b6c62fd652dd0ea1cdd50318cb','12345','12345',3,NULL,'\0','1F1JBpJock'),(2,'b@c.d','a01ba4e46ec5e1e2ea9e666012e6f234','12345','12345',3,'2017-03-03 15:08:59.000000','\0','9MRnotMHuJ'),(3,'admin@sos.com','f21331882606c75d9d5a075bf26f8c63','Administrator','96220394',0,'2017-03-28 14:57:34.000000','\0','U6MNsNwPMq');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `UserProfileId` int(11) NOT NULL AUTO_INCREMENT,
  `Nickname` varchar(127) DEFAULT NULL,
  `Gender` tinyint(1) NOT NULL DEFAULT '0',
  `DOB` date DEFAULT NULL,
  `Nationality` varchar(84) DEFAULT NULL,
  `Address` varchar(127) DEFAULT NULL,
  `UserId` int(11) DEFAULT NULL,
  `Lat` double DEFAULT NULL,
  `Lng` double DEFAULT NULL,
  PRIMARY KEY (`UserProfileId`),
  UNIQUE KEY `Nickname_UNIQUE` (`Nickname`),
  KEY `User_User_Profile_idx` (`UserId`),
  CONSTRAINT `User_User_Profile` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (1,'123',1,'2017-03-01','SG',NULL,1,NULL,NULL),(2,'231',0,NULL,NULL,NULL,2,NULL,NULL);
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sos'
--

--
-- Dumping routines for database 'sos'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-28 23:00:32
