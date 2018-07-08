CREATE DATABASE  IF NOT EXISTS `transport-shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `transport-shop`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: transport-shop
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Electric cars'),(2,'Trucks'),(3,'Sport cars'),(4,'Race cars'),(5,'Planes');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturers`
--

DROP TABLE IF EXISTS `manufacturers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufacturers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturers`
--

LOCK TABLES `manufacturers` WRITE;
/*!40000 ALTER TABLE `manufacturers` DISABLE KEYS */;
INSERT INTO `manufacturers` VALUES (1,'Tesla'),(2,'Ford'),(3,'Mercedes'),(4,'Messerschmitt'),(5,'Nissan'),(6,'Pagani'),(7,'Lamborghini');
/*!40000 ALTER TABLE `manufacturers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `order_id` int(11) NOT NULL,
  `transport_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  KEY `transport_id_idx` (`transport_id`),
  KEY `order_id_idx` (`order_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`transport_id`) REFERENCES `transports` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,30,110000,1),(1,36,700000,1),(1,31,10000000,1),(2,29,25000,1),(3,30,110000,1),(3,36,700000,1),(3,29,25000,1),(4,36,700000,1),(4,29,25000,1),(5,30,110000,1),(5,36,700000,1),(5,29,25000,1),(6,29,25000,1),(7,36,700000,1),(8,36,700000,1),(9,36,700000,1),(9,29,25000,2),(10,29,25000,13),(11,29,25000,1),(11,36,700000,1),(11,31,10000000,1),(11,32,50000,1),(12,36,700000,1),(13,29,25000,1);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` enum('ACCEPTED','CONFIRMED','PROCESSING','SENT','COMPLETED','CANCELED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status_description` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `creation_date` timestamp NOT NULL,
  `user_id` int(11) NOT NULL,
  `delivery_info` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `card` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pay_type` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `delivery` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_order_fk_idx` (`user_id`),
  CONSTRAINT `user_order_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'ACCEPTED','Order was created successfully','2018-06-06 16:46:35',2,'Ya descript','','cash','pickup'),(2,'ACCEPTED','Order was created successfully','2018-06-06 16:56:16',2,'Ya descript1234','','cash','pickup'),(3,'ACCEPTED','Order was created successfully','2018-06-06 17:13:52',2,'','','cash','pickup'),(4,'ACCEPTED','Order was created successfully','2018-06-06 17:15:46',2,'Ya descript1234','','cash','pickup'),(5,'ACCEPTED','Order was created successfully','2018-06-06 17:18:43',2,'adf','','cash','pickup'),(6,'ACCEPTED','Order was created successfully','2018-06-06 17:20:07',2,'f','','cash','pickup'),(7,'ACCEPTED','Order was created successfully','2018-06-06 17:20:38',2,'qwer','','cash','pickup'),(8,'ACCEPTED','Order was created successfully','2018-06-06 17:23:54',2,'Ya descript','','cash','pickup'),(9,'ACCEPTED','Order was created successfully','2018-06-08 11:43:05',2,'jhsf','','cash','pickup'),(10,'ACCEPTED','Order was created successfully','2018-06-08 13:42:01',8,'ffffffff','','cash','pickup'),(11,'ACCEPTED','Order was created successfully','2018-06-08 13:43:58',8,'card','12*********7654','card','pickup'),(12,'ACCEPTED','Order was created successfully','2018-06-11 07:10:38',8,'qwerty','11*********1111','card','pickup'),(13,'ACCEPTED','Order was created successfully','2018-06-11 11:48:18',8,'asdf','','cash','pickup');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transports`
--

DROP TABLE IF EXISTS `transports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `categories_id` int(11) NOT NULL,
  `manufacturers_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_transports_categories1_idx` (`categories_id`),
  KEY `fk_transports_manufacturers1_idx` (`manufacturers_id`),
  CONSTRAINT `fk_transports_categories1` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `fk_transports_manufacturers1` FOREIGN KEY (`manufacturers_id`) REFERENCES `manufacturers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transports`
--

LOCK TABLES `transports` WRITE;
/*!40000 ALTER TABLE `transports` DISABLE KEYS */;
INSERT INTO `transports` VALUES (28,'Tesla Model X',35000,1,1,'Model X is the safest, quickest, most capable sport utility vehicle ever—with standard all-wheel drive, best in class storage and seating for up to seven '),(29,'Ford Mustang GT',25000,3,2,'The sixth generation Mustang was unveiled on December 5, 2013, in Dearborn, Michigan; New York, New York; Los Angeles, California; Barcelona, Spain; Shanghai, China; and Sydney, Australia.[60] The internal project codename is S-550'),(30,'Mercedes AMG',110000,3,3,'Affalterbach.  Mercedes-AMG is supporting the sales release of the new members of the family Mercedes-AMG E 53 4MATIC+ Coupé, Mercedes-AMG E 53 4MATIC+ Cabriolet and Mercedes-AMG CLS 53 4MATIC+ (Combined'),(31,'Mercedes F1 W09',10000000,4,3,'The F1 W09 EQ Power+ is improved in every area over its predecessor and will become the fastest Mercedes Formula One car in history. '),(32,'Mercedes-Benz Actros',50000,2,3,'In July 2011, the manufacturer of Mercedes Benz Trucks, Daimler AG, launched the 2012 Mercedes Benz Actros. This may not be referred to as Actros 4 but it is the New Actros.'),(33,'Messerschmitt bf 109g',5000000,5,4,'The Messerschmitt Bf 109 is a German World War II fighter aircraft that was the backbone of the Luftwaffe\'s fighter force.'),(34,'Nissan GTR r35',100000,3,5,'The Nissan GT-R is a 2-door 2+2 high performance vehicle produced by Nissan unveiled in 2007.'),(35,'Pagani Zonda HP',15000000,4,6,'The Zonda HP Barchetta was unveiled at the 2017 Pebble beach Concours d\'Elegance as a present to the company\'s founder, Horacio Pagani for his 60th birthday as well as to commemorate the 18th anniversary of the Zonda. '),(36,'Lamborghini Aventador',700000,3,7,'The Lamborghini Aventador is a mid-engined sports car produced by the Italian automotive manufacturer Lamborghini.');
/*!40000 ALTER TABLE `transports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mailing` tinyint(4) DEFAULT NULL,
  `role` enum('ADMIN','CLIENT','UNREGISTERED') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'admin','admin','admin@rich.com','D033E22AE348AEB5660FC2140AEC35850C4DA997',1,'CLIENT'),(8,'Asdf','Asdf','asdf@rich.com','3DA541559918A808C2402BBA5012F6C60B27661C',0,'CLIENT'),(9,'No','Avatar','1234@rich.com','D033E22AE348AEB5660FC2140AEC35850C4DA997',1,'CLIENT'),(10,'Danil','Kuliga','qwer@rich.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',0,'CLIENT'),(11,'Lewis','Hamilton','44@rich.com','3DA541559918A808C2402BBA5012F6C60B27661C',1,'CLIENT'),(12,'Sebastian','Vettel','5champ@rich.com','3DA541559918A808C2402BBA5012F6C60B27661C',1,'CLIENT');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-11 18:03:59
