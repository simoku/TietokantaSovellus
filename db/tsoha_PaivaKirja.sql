CREATE DATABASE  IF NOT EXISTS `tsoha` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tsoha`;
-- MySQL dump 10.13  Distrib 5.5.31, for debian-linux-gnu (i686)
--
-- Host: 127.0.0.1    Database: tsoha
-- ------------------------------------------------------
-- Server version	5.5.31-0ubuntu0.12.04.1

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
-- Table structure for table `PaivaKirja`
--

DROP TABLE IF EXISTS `PaivaKirja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaivaKirja` (
  `PaivaKirjaID` int(11) NOT NULL AUTO_INCREMENT,
  `Paivamaara` date NOT NULL,
  `Nimi` int(11) NOT NULL,
  `Harjoitus` varchar(45) NOT NULL,
  `Fiiilis` varchar(45) NOT NULL,
  `Kesto` varchar(45) NOT NULL,
  `KulutetutKalorit` varchar(45) NOT NULL,
  `HarjoitusID` int(11) NOT NULL,
  PRIMARY KEY (`PaivaKirjaID`,`Paivamaara`,`Nimi`,`HarjoitusID`),
  KEY `fk_PaivaKirja_1_idx` (`Nimi`),
  KEY `fk_PaivaKirja_2_idx` (`HarjoitusID`),
  CONSTRAINT `fk_PaivaKirja_1` FOREIGN KEY (`Nimi`) REFERENCES `Kayttajat` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PaivaKirja_2` FOREIGN KEY (`HarjoitusID`) REFERENCES `Harjoitus` (`idHarjoitus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaivaKirja`
--

LOCK TABLES `PaivaKirja` WRITE;
/*!40000 ALTER TABLE `PaivaKirja` DISABLE KEYS */;
/*!40000 ALTER TABLE `PaivaKirja` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-29 17:31:00
