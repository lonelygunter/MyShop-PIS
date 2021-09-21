-- MySQL dump 10.13  Distrib 8.0.25, for macos11 (x86_64)
--
-- Host: localhost    Database: myshopmf
-- ------------------------------------------------------
-- Server version	8.0.25

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

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(61) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Surname` varchar(45) DEFAULT NULL,
  `Age` date DEFAULT NULL,
  `Email` varchar(100) NOT NULL,
  `Telephone` varchar(45) NOT NULL,
  `Street` varchar(100) DEFAULT NULL,
  `Cap` varchar(45) DEFAULT NULL,
  `Role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'gen.gat','$2a$10$1pHER55qAMw7WxgVDnuRbuBuMJJLqUssiyZEmud71Mdb/1h3FFst6','Gennaro','Gatto','1967-05-02','gatto.gennaro@gmail.com','3331967050','Via Cota, 5','73100','A'),(2,'aur.car','$2a$10$bdV2TdPuyjvYsFuDbDr7KexAAW5lXnZy0lITIAqMF6CPxJhx/MO6C','Aurora','Caracciolo','1973-08-18','caracciolo.aurora@gmail.com','3331973081','Viale Rimini, 20','20121','M'),(3,'chi.car','$2a$10$DGTxsNFvAsytu4BnF3e7h.ihHQHYCG0.Cs8Wo9o/mFNE9WMa59vRe','Chiara','Caracciolo','1970-08-18','caracciolo.chiara@gmail.com','3331970081','Via Mentana, 13','44121','M'),(4,'fil.gof','$2a$10$Vqcfzub639Y13ELrlOCyGO5XNTkTqujfhPdaVQp.tXriawARn8gee','Filippo','Goffredo','1963-03-14','goffredo.filippo@gmail.com','3331963031','Via Etna, 10','80121','M'),(5,'mat.apr','$2a$10$Gp06DMGr/PaVdFD2J8i4G.A.lLaBo4RLXBY8VbYq68.G3UJb4oRO.','Matteo','Aprile','1967-01-01','aprile.matteo@gmail.com','3331967010','Via Tasso, 2','00118','M'),(6,'mar.ros','$2a$10$3yJJKU8caF/lQs1D/2U3ROIFuNul2MHAzVahvpYLXtSlO/UmDccha','Mario','Rossi','1969-05-20','rossi.mario@gmail.com','3331969052','Via Pisanello, 3','20121','U'),(7,'lui.ver','$2a$10$LiZsQ7DvOLfHitD5w.1yVOJeZeJi6OLnPGnf1vqKDGAV3UGU9leNK','Luigi','Verdi','1996-02-05','verdi.luigi@gmail.com','3331996020','Via Novara, 5','00118','U');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-10 16:15:19
