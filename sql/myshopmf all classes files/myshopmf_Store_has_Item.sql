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
-- Table structure for table `Store_has_Item`
--

DROP TABLE IF EXISTS `Store_has_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Store_has_Item` (
  `Store_has_Item_Id` int NOT NULL AUTO_INCREMENT,
  `Store_Id` int NOT NULL,
  `Item_id` int NOT NULL,
  PRIMARY KEY (`Store_has_Item_Id`),
  KEY `fk_Store_has_Item_Item1_idx` (`Item_id`),
  KEY `fk_Store_has_Item_Store1_idx` (`Store_Id`),
  CONSTRAINT `fk_Store_has_Item_Item1` FOREIGN KEY (`Item_id`) REFERENCES `Item` (`Id`),
  CONSTRAINT `fk_Store_has_Item_Store1` FOREIGN KEY (`Store_Id`) REFERENCES `Store` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Store_has_Item`
--

LOCK TABLES `Store_has_Item` WRITE;
/*!40000 ALTER TABLE `Store_has_Item` DISABLE KEYS */;
INSERT INTO `Store_has_Item` VALUES (2,1,112),(3,1,113),(4,1,114),(5,1,115),(6,1,116),(7,1,117),(8,1,118),(9,1,119),(10,1,120),(11,1,121),(12,1,122),(13,1,123),(14,1,124),(15,1,125),(16,1,126),(17,1,127),(18,1,128),(19,1,129),(20,2,130),(21,2,131),(22,2,132),(23,2,133),(24,2,134),(25,2,135),(26,2,136),(27,2,137),(28,2,138),(29,3,139),(30,3,140),(31,3,141),(32,3,142),(33,3,143),(34,3,144),(35,3,145),(36,3,146),(37,3,147),(38,4,112),(39,4,113),(40,4,114),(41,4,115),(42,4,116),(43,4,117),(44,4,118),(45,4,119),(46,4,120),(47,4,121),(48,4,122),(49,4,123),(50,4,124),(51,4,125),(52,4,126),(53,4,127),(54,4,128),(55,4,129),(56,4,130),(57,4,131),(58,4,132),(59,4,133),(60,4,134),(61,4,135),(62,4,136),(63,4,137),(64,4,138),(65,4,139),(66,4,140),(67,4,141),(68,4,142),(69,4,143),(70,4,144),(71,4,145),(72,4,146),(73,4,147);
/*!40000 ALTER TABLE `Store_has_Item` ENABLE KEYS */;
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
