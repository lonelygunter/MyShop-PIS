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
-- Table structure for table `Purchase_has_Item`
--

DROP TABLE IF EXISTS `Purchase_has_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Purchase_has_Item` (
  `Purchase_has_Item_Id` int NOT NULL,
  `Purchase_Id` int NOT NULL,
  `Item_Id` int NOT NULL,
  PRIMARY KEY (`Purchase_has_Item_Id`),
  KEY `fk_Purchase_has_Item_Item1_idx` (`Item_Id`),
  KEY `fk_Purchase_has_Item_Purchase1_idx` (`Purchase_Id`),
  CONSTRAINT `fk_Purchase_has_Item_Item1` FOREIGN KEY (`Item_Id`) REFERENCES `Item` (`Id`),
  CONSTRAINT `fk_Purchase_has_Item_Purchase1` FOREIGN KEY (`Purchase_Id`) REFERENCES `Purchase` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Purchase_has_Item`
--

LOCK TABLES `Purchase_has_Item` WRITE;
/*!40000 ALTER TABLE `Purchase_has_Item` DISABLE KEYS */;
INSERT INTO `Purchase_has_Item` VALUES (1,2,131),(2,2,135);
/*!40000 ALTER TABLE `Purchase_has_Item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-10 16:15:20
