CREATE DATABASE  IF NOT EXISTS `football` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `football`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: football
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` enum('CUSTOMER','OWNER','ADMIN') DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'demo','demo','$2a$10$I7jeZPiMBdDA2WqisbiOOupDnybfB/A1E7qVCQe1KC6MiOlPrZuIu','string','CUSTOMER','active');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_date` date DEFAULT NULL,
  `booking_status` varchar(255) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `end_booking` time(6) DEFAULT NULL,
  `price` double NOT NULL,
  `start_booking` time(6) DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `pitch_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlnnelfsha11xmo2ndjq66fvro` (`customer_id`),
  KEY `FK6tamm9q1mpqaoub56q1y0umyu` (`pitch_id`),
  CONSTRAINT `FK6tamm9q1mpqaoub56q1y0umyu` FOREIGN KEY (`pitch_id`) REFERENCES `pitch` (`id`),
  CONSTRAINT `FKlnnelfsha11xmo2ndjq66fvro` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'2024-07-04','onGoing','2024-07-04','12:30:00.000000',24,'10:30:00.000000',1,1);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dob` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jwt2qo9oj3wd7ribjkymryp8s` (`account_id`),
  CONSTRAINT `FKn9x2k8svpxj3r328iy1rpur83` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'2024-07-04','string',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int DEFAULT NULL,
  `shop_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n29vpohy20ym9a2ky4kwg0bmd` (`account_id`),
  KEY `FKgv8o9ylwxq2nmbroq6yf54woa` (`shop_id`),
  CONSTRAINT `FKgv8o9ylwxq2nmbroq6yf54woa` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `FKs8vxfog0lwxdn09g7d71fkuxp` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pitch`
--

DROP TABLE IF EXISTS `pitch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pitch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `capacity` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pitch_status` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `shop_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr8flyteq7rjnnkwgfqlgg6ic1` (`shop_id`),
  CONSTRAINT `FKr8flyteq7rjnnkwgfqlgg6ic1` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pitch`
--

LOCK TABLES `pitch` WRITE;
/*!40000 ALTER TABLE `pitch` DISABLE KEYS */;
INSERT INTO `pitch` VALUES (1,1,'abc','active',12,1),(2,10,'Pitch 1','active',15,1),(3,20,'Pitch 2','active',20,2),(4,15,'Pitch 3','unavailable',25,3),(5,12,'Pitch 4','active',18,1),(6,18,'Pitch 5','unavailable',22,2);
/*!40000 ALTER TABLE `pitch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `close_time` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,NULL,NULL,NULL,'abc',NULL,NULL,NULL,NULL),(2,'123 Main St','avatar1.png','20:00','Shop 1','08:00','0123456789',4.5,'active'),(3,'456 Central Ave','avatar2.png','21:00','Shop 2','09:00','0987654321',4,'active'),(4,'789 Broadway','avatar3.png','22:00','Shop 3','10:00','0112233445',3.5,'inactive');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_customer_communication`
--

DROP TABLE IF EXISTS `shop_customer_communication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_customer_communication` (
  `id` int NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `shop_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpq4ejc9rsahxh8g1l8u6jiukh` (`customer_id`),
  KEY `FKhth3afauqjjqrw5p4hupy6vdl` (`shop_id`),
  CONSTRAINT `FKhth3afauqjjqrw5p4hupy6vdl` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `FKpq4ejc9rsahxh8g1l8u6jiukh` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_customer_communication`
--

LOCK TABLES `shop_customer_communication` WRITE;
/*!40000 ALTER TABLE `shop_customer_communication` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_customer_communication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKftkstvcfb74ogw02bo5261kno` (`account_id`),
  CONSTRAINT `FKftkstvcfb74ogw02bo5261kno` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,0,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJkZW1vIiwiaWF0IjoxNzIwMDY5ODQ1LCJleHAiOjE3MjAwODA2NDV9.XxV8IOWLSKkpdGaKTeAogDjZJ6b25QBTQcmUdKEbq9QL2Ve2pCqo3DMdjuLCkz0w','access',1),(2,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJkZW1vIiwiaWF0IjoxNzIwMDY5ODQ1LCJleHAiOjE3MjA1MDE4NDV9.loLsYBFy7iolh2OhhcyjbG_Xm9II2ETViPbh5MYhnIznTVsI-fr50g4rVdA2u-Mn','refresh',1),(3,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJkZW1vIiwiaWF0IjoxNzIwMTkzMjcxLCJleHAiOjE3MjAyMDQwNzF9.fZLcIjBU0ktQ7kYCul5XcsUVDkMjKt5R2JGMLfxNvcptcCIOV0Ozr-yVGLxbtCEF','access',1);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-05 23:10:32
