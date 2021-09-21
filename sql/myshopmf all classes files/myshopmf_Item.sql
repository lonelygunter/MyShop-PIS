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
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Item` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Price` float NOT NULL,
  `Type` char(1) NOT NULL,
  `Available` tinyint(1) NOT NULL,
  `Wholesaler_Id` int NOT NULL,
  `Category_Id` int NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Item_Wholesaler1_idx` (`Wholesaler_Id`),
  KEY `fk_Item_Category1_idx` (`Category_Id`),
  CONSTRAINT `fk_Item_Category1` FOREIGN KEY (`Category_Id`) REFERENCES `Category` (`Id`),
  CONSTRAINT `fk_Item_Wholesaler1` FOREIGN KEY (`Wholesaler_Id`) REFERENCES `Wholesaler` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES (112,'Sandbacken','Divano, Frillestad grigio chiaro',329,'P',1,1,22),(113,'Kivik','Divano, Orrsta grigio chiaro',499,'P',1,1,22),(114,'Finnala','Divano, Gunnared grigio medio',599,'P',1,1,22),(115,'Lerhamn','Tavolo, macchia anticata chiara/macchia bianca 46 1/2x29 1/8\"',99,'P',1,1,23),(116,'Tarendo','Tavolo, nero 43 1/4x26 3/8\"',39,'P',1,1,23),(117,'Ravaror','Tavolo da pranzo, rovere impiallacciato 51 1/8x30 3/4 \"',199,'P',1,1,23),(118,'Billy','Libreria, bianca 31 1/2x11x79 1/2\"',49,'P',1,1,24),(119,'Kallax','Scaffale, bianco 30 3/8x57 7/8 \"',69.99,'P',1,1,24),(120,'Hemnes','Libreria, marrone-nero 35 3/8x77 1/2 \"',149,'P',1,1,24),(121,'Malm','Struttura letto, alta, marrone-nero/LuröyQueen',199,'P',1,1,25),(122,'Slattum','Giroletto imbottito, Knisa grigio chiaro Queen',149,'P',1,1,25),(123,'Neiden','Struttura letto, pino/LuröyFull',75,'P',1,1,25),(124,'Roenninge','Sedia, verde',95,'P',1,1,26),(125,'Yngvar','Sedia, antracite',95,'P',1,1,26),(126,'Leifarne','Poltrona, verde oliva chiaro/Dietmar cromato',69,'P',1,1,26),(127,'Kleppstand','Armadio 3 ante, bianco 46 1/8x69 1/4\"',129,'P',1,1,27),(128,'Grimsnarl','Armadio a 2 ante, bianco 31 1/4x69 1/4\"',99,'P',1,1,27),(129,'Gursken','Armadio a 2 ante, beige chiaro 23 3/8x21 5/8 x 73 1/8\"',69.99,'P',1,1,27),(130,'Samfalld','Piano cottura a induzione, nero 36\"',899,'P',1,2,29),(131,'Sarklassig','Piano cottura a induzione, nero 30 \"',599,'P',1,2,29),(132,'Brannpunkt','Piano cottura a gas, Acciaio inox 30\"',499,'P',1,2,29),(133,'Undantag','Cappa a soffitto, Acciaio inox/vetro',699,'P',1,2,30),(134,'Luftburen','Cappa a parete, Acciaio inossidabile',549,'P',1,2,30),(135,'Allmanbildad','Cappa a parete, Acciaio inossidabile',699,'P',1,2,30),(136,'Stjarnstatus','Frigorifero con portafinestra, Acciaio inossidabile 21,7 cu.ft',2499,'P',1,2,31),(137,'Farskhet','Frigorifero con congelatore inferiore, colore acciaio inossidabile10,4 cu.ft',1299,'P',1,2,31),(138,'Lagan','Frigorifero con congelatore superiore, bianco13,9 cu.ft',599,'P',1,2,31),(139,'Lauters','Lampada da terra con lampadina LED, cenere/bianco',54.99,'P',1,3,33),(140,'Barlast','Lampada da terra con lampadina LED, nero/bianco 59\"',8.99,'P',1,3,33),(141,'Vicklebly','Lampada da terra con lampadina LED, bianca/fatta a mano 54\"',15.99,'P',1,3,33),(142,'Solviden','Lampada a sospensione LED a energia solare, da esterno/globo bianco 12\"',8.99,'P',1,3,34),(143,'Kannland','Lampada da parete, da esterno nera',49.99,'P',1,3,34),(144,'Solvistan','Luce LED a energia solare, da esterno/globo multicolore',16.99,'P',1,3,34),(145,'Rollsbo','Lampadina LED E26 140 lumen, dimmerabile/globo vetro trasparente grigio 5\"',14.99,'P',1,3,35),(146,'Ledare','Lampadina LED E26 1600 lumen, warm dimming/globo opale',6.99,'P',1,3,35),(147,'Ryet','Lampadina LED E26 100 lumen, globo trasparente',2.99,'P',1,3,35),(148,'Assemblaggio a domicilio','Assemblaggio a domicilio. Tu ci chiami, noi corriamo.',30,'S',1,4,36),(149,'Spedizione','Spedizione',20,'S',1,5,37),(150,'Tarendo + Ronninge','Tavolo, nero, 43 1/4x26 3/8\" + Sedia, verde',128,'P',1,1,1),(151,'Samfalld + Undantag','Piano cottura a induzione, nero, 36\" + Cappa a soffitto, Acciaio inox/vetro',1520,'P',1,2,28),(152,'Solviden + Ryet','Lampada a sospensione LED a energia solare, esterno/globo bianco 12\" + lampadina LED E26 100 lumen, globo trasparente',9.5,'P',1,3,32);
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
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
