-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: laundrysystem
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `booking_date` datetime(6) DEFAULT NULL,
  `branch_id` bigint DEFAULT NULL,
  `delivery_agent_id` bigint DEFAULT NULL,
  `delivery_days` int NOT NULL,
  `delivery_time` datetime(6) DEFAULT NULL,
  `delivery_type` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `other_items` varchar(255) DEFAULT NULL,
  `pant_count` int NOT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `pickup_time` datetime(6) DEFAULT NULL,
  `service_id` bigint DEFAULT NULL,
  `shirt_count` int NOT NULL,
  `total_price` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9rhmo4eolnjq729eonsvcv4ke` (`delivery_agent_id`),
  KEY `FKougbnvjcp9cv15dhu86gqyjim` (`branch_id`),
  KEY `FK8yane9j6lwhxruc190hgh4dx2` (`service_id`),
  KEY `FKkgseyy7t56x7lkjgu3wah5s3t` (`user_id`),
  CONSTRAINT `FK8yane9j6lwhxruc190hgh4dx2` FOREIGN KEY (`service_id`) REFERENCES `service_entity` (`id`),
  CONSTRAINT `FK9rhmo4eolnjq729eonsvcv4ke` FOREIGN KEY (`delivery_agent_id`) REFERENCES `delivery_agent` (`id`),
  CONSTRAINT `FKkgseyy7t56x7lkjgu3wah5s3t` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKougbnvjcp9cv15dhu86gqyjim` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'38 aundh road,khadki,pune 20','2026-04-03 18:42:40.599636',1,1,2,'2026-04-03 18:45:23.424603','HOME','ORD_1775241760599','DELIVERED','1 curtain',1,'PAID','COD','2026-04-03 18:45:19.595578',3,1,400,13),(2,'pune','2026-04-04 07:07:07.800569',1,1,2,'2026-04-04 07:10:30.341432','SELF','ORD_1775286427800','DELIVERED','2 saree',1,'PAID','ONLINE','2026-04-04 07:10:27.862992',1,1,200,14);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `closing_time` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `opening_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,_binary '','38 aundh road,ambedkar chowk,near kunal crimson,pune-20,khadki','pune','9:00 PM','pune central','9:00 AM'),(2,_binary '','mumbai ,dadar road ,post office no:-123456,mumbai.','mumbai ','8:00 PM','mumbai central','10:00 AM'),(3,_binary '','banjara hills,near hightech city,gachhibowli ,hyderabad city.','hyderabad','7:00 PM','hyderabad central','9:00 AM'),(4,_binary '','thiruvvunantapuram ,near chepauk stadium,lane no:-2,mohan general stores','chennai','5:00 PM','chennai central','11:00 AM'),(5,_binary '','pimple gurav ,near radhakrishna mangal karyalay,near dinasour garden pune-08','pune','7:00 PM','pune clothes washing','8:00 AM'),(6,_binary '','rajasthan near dwarkadhish temple ,rajasthan-411008','rajasthan','6:00 PM','rajasthan central','10:30 AM'),(7,_binary '','gujarat near surat station ,near ahmedabad stadium','gujarat','5:00 PM','gujarat central','8:00 AM'),(8,_binary '','furgusson college,deccan road, deccan gymkhana,pune-20','pune','4:00 PM','pune clothes cleaning centre','7:00 AM'),(9,_binary '','pune city,near wakad bus stand,near shree krishna housing society','pune','10:00 PM','wakad cleaning center','10:00 AM');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_agent`
--

DROP TABLE IF EXISTS `delivery_agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_agent` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKp0fya18nrupn3e4846lm81b5k` (`user_id`),
  CONSTRAINT `FKd4pk0k6vc41s38k5bjxhsbbiq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_agent`
--

LOCK TABLES `delivery_agent` WRITE;
/*!40000 ALTER TABLE `delivery_agent` DISABLE KEYS */;
INSERT INTO `delivery_agent` VALUES (1,_binary '','38 aundh road ,ambedkar chowk,khadki pune 20','pune',9),(2,_binary '','aundh road,kunal crimson ,building no:01','pune',10),(3,_binary '','pimple gurav,near dinosaur garder,pune-20','pune',11),(4,_binary '','38 aundh road,bopodi,near balaji temple','mumbai',12);
/*!40000 ALTER TABLE `delivery_agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_entity`
--

DROP TABLE IF EXISTS `service_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price_per_item` double DEFAULT NULL,
  `branch_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtbhn5fex6d3oid420g6s4314v` (`branch_id`),
  CONSTRAINT `FKtbhn5fex6d3oid420g6s4314v` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_entity`
--

LOCK TABLES `service_entity` WRITE;
/*!40000 ALTER TABLE `service_entity` DISABLE KEYS */;
INSERT INTO `service_entity` VALUES (1,'this is a basic washing service with detergent','1775239577407_drycleaning.jpg','Dry Cleaning',50,1),(2,'this service is remove tough stain','1775239779284_stain-removal.webp','dirty stain removal',70,1),(3,'this is very premium service here you will get clothes washed and iron','1775239941621_ironwash.webp','wash and iron ',100,1),(4,'in this service the clothes are starched eith good quality product','1775240133941_starching.webp','Clothes Starching ',30,2),(5,'in this service it is a special type of service where clothes are washed with warm water','1775240424488_hot-wash.jpg','hot wash ',40,3),(6,'it is service where clothes are ironed .','1775240697210_ironing.jpg','iron clothes',25,4),(7,'this is a service for removing blood stains clothes.','1775240960874_blood.jpg','Blood stain removal',80,6);
/*!40000 ALTER TABLE `service_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `otp` int DEFAULT NULL,
  `otp_generated_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'pune','sameeramarjinta@gmail.com','sameer','admin@123','7028962254','ADMIN',NULL,NULL),(9,NULL,'rutvik@gmail.com','rutvik','123456','1234567890','AGENT',NULL,NULL),(10,NULL,'pratyush@gmail.com','pratyush','123456','0987654321','AGENT',NULL,NULL),(11,NULL,'karan@gmail.com','karan','123456','2345678109','AGENT',NULL,NULL),(12,NULL,'deepak@gmail.com','deepak','123456','7893425171','AGENT',NULL,NULL),(13,'38 aundh road ,khadki,pune 20.','yashamarjinta@gmail.com','yash amarjinta','yash@123','7058490766','USER',NULL,NULL),(14,'pune','rahul@gmail.com','rahul','Rahul@123456','1234567890','USER',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-18 13:53:03
