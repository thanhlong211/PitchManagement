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
  `role` tinyint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `account_chk_1` CHECK ((`role` between 0 and 2))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,0,'demo','demo','$2a$10$V/TQL0J9BYI81JnX.Es6auGJMmfAt4p4YZxHqWEqIKfZXhdxn9NSa',NULL,'active'),(2,1,'owner','owner','$2a$10$PU1uXL46OnOH829ZJn6duO9U3VyiMJF1RAnZXhVFK7GepUDlBRWRO',NULL,'active'),(3,2,'admin','admin','$2a$10$7yCSMUU4AKUsuldmmeUyuOp8IgIEfk/9Xirg.hOYVmEEN9jSn3k0S',NULL,'active');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `account_id` int DEFAULT NULL,
  `booking_date` date DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `end_booking` time(6) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `pitch_id` int DEFAULT NULL,
  `price` double NOT NULL,
  `start_booking` time(6) DEFAULT NULL,
  `booking_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7hunottedmjhtdcvhv4sx6x4a` (`account_id`),
  KEY `FK6tamm9q1mpqaoub56q1y0umyu` (`pitch_id`),
  CONSTRAINT `FK6tamm9q1mpqaoub56q1y0umyu` FOREIGN KEY (`pitch_id`) REFERENCES `pitch` (`id`),
  CONSTRAINT `FK7hunottedmjhtdcvhv4sx6x4a` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'2024-07-01','2024-06-30','20:00:00.000000',1,1,150,'18:00:00.000000','onGoing'),(1,'2024-07-02','2024-06-30','22:00:00.000000',2,2,200,'20:00:00.000000','onGoing'),(1,'2024-07-03','2024-06-30','21:00:00.000000',3,3,180,'19:00:00.000000','onGoing'),(1,'2024-07-04','2024-06-30','20:00:00.000000',4,4,250,'18:00:00.000000','onGoing'),(1,'2024-07-05','2024-06-30','22:00:00.000000',5,5,120,'20:00:00.000000','onGoing');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `account_id` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n29vpohy20ym9a2ky4kwg0bmd` (`account_id`),
  KEY `FKgv8o9ylwxq2nmbroq6yf54woa` (`shop_id`),
  CONSTRAINT `FKgv8o9ylwxq2nmbroq6yf54woa` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `FKs8vxfog0lwxdn09g7d71fkuxp` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `capacity` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `price` float NOT NULL,
  `shop_id` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pitch_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr8flyteq7rjnnkwgfqlgg6ic1` (`shop_id`),
  CONSTRAINT `FKr8flyteq7rjnnkwgfqlgg6ic1` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pitch`
--

LOCK TABLES `pitch` WRITE;
/*!40000 ALTER TABLE `pitch` DISABLE KEYS */;
INSERT INTO `pitch` VALUES (10,1,150,1,'Pitch A1','active'),(15,2,200,1,'Pitch A2','active'),(12,3,180,2,'Pitch B1','active'),(20,4,250,3,'Pitch C1','active'),(8,5,120,3,'Pitch C2','active');
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
  `rating` double DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `close_time` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,4.1,'123 Fake Street','https://www.example.com/avatar1.jpg','20:00','Shop A','08:00','555-1234','active'),(2,3.9,'456 Another Rd','https://www.example.com/avatar2.jpg','22:00','Shop B','09:00','555-5678','active'),(3,4.7,'789 Market St','https://www.example.com/avatar3.jpg','21:00','Shop C','10:00','555-9876','active');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_customer_communication`
--

DROP TABLE IF EXISTS `shop_customer_communication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_customer_communication` (
  `customer_id` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoyh253626mvw9f8g9v4dnnyv9` (`customer_id`),
  KEY `FKhth3afauqjjqrw5p4hupy6vdl` (`shop_id`),
  CONSTRAINT `FKhth3afauqjjqrw5p4hupy6vdl` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `FKoyh253626mvw9f8g9v4dnnyv9` FOREIGN KEY (`customer_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_customer_communication`
--

LOCK TABLES `shop_customer_communication` WRITE;
/*!40000 ALTER TABLE `shop_customer_communication` DISABLE KEYS */;
INSERT INTO `shop_customer_communication` VALUES (1,1,1,'2024-07-01 10:00:00.000000','Hello, I have a question about my booking.'),(1,2,2,'2024-07-02 11:00:00.000000','Can I change my booking time?'),(1,3,3,'2024-07-03 12:00:00.000000','I would like to cancel my booking.'),(1,4,1,'2024-07-04 13:00:00.000000','Thank you for your help!'),(1,5,2,'2024-07-05 14:00:00.000000','Is the pitch available tomorrow?');
/*!40000 ALTER TABLE `shop_customer_communication` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-09 16:37:46
