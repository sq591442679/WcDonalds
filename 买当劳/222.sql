-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: yzqk_database
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `address_information`
--
use yzqk_database;
DROP TABLE IF EXISTS `address_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address_information` (
  `id_ai` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `tele` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `id_customer` int DEFAULT NULL,
  PRIMARY KEY (`id_ai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_information`
--

LOCK TABLES `address_information` WRITE;
/*!40000 ALTER TABLE `address_information` DISABLE KEYS */;
INSERT INTO `address_information` VALUES (2,'shan qian','16631203369','264',2);
/*!40000 ALTER TABLE `address_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `id_administrator` int NOT NULL,
  `name_administrator` varchar(45) DEFAULT NULL,
  `password_administrator` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_administrator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (1,'263yyds','263yyds');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id_customer` int NOT NULL,
  `username_customer` varchar(45) DEFAULT NULL,
  `password_customer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_customer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TRIGGER `customer_BEFORE_DELETE` BEFORE DELETE ON `customer` FOR EACH ROW 
DELETE FROM address_information WHERE address_information.id_customer = customer.id_customer;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (2,'sqsq','sqsq'),(4,'sqsqsq','sqsqsq');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id_employee` int NOT NULL,
  `name_employee` varchar(45) DEFAULT NULL,
  `age_employee` varchar(45) DEFAULT NULL,
  `occupation_employee` varchar(45) DEFAULT NULL,
  `tele_employee` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (2,'王伟','28',NULL,NULL),(3,'王芳','22',NULL,NULL),(4,'李伟','33',NULL,NULL),(5,'王秀英','26',NULL,NULL),(6,'李秀英','24',NULL,NULL),(7,'李娜','31',NULL,NULL),(8,'张敏','32',NULL,NULL),(9,'李静','23',NULL,NULL),(10,'张丽','28',NULL,NULL),(11,'王静','20',NULL,NULL),(12,'李强','27',NULL,NULL),(13,'张静','28',NULL,NULL),(14,'李敏','35',NULL,NULL),(15,'王敏','29',NULL,NULL),(16,'王磊','24',NULL,NULL),(17,'李军','27',NULL,NULL),(18,'刘洋','23',NULL,NULL),(19,'王勇','29',NULL,NULL),(20,'张勇','26',NULL,NULL),(21,'王艳','23',NULL,NULL),(22,'李杰','31',NULL,NULL),(23,'张磊','21',NULL,NULL),(24,'王强','24',NULL,NULL),(25,'王军','25',NULL,NULL),(26,'张杰','25',NULL,NULL),(27,'李娟','25',NULL,NULL),(28,'张艳','31',NULL,NULL),(29,'张涛','25',NULL,NULL),(30,'李霞','25',NULL,NULL),(34,'杨周启珂','21','后勤','1988'),(36,'单乾','20','厨师:4000  rmb/month','16631203369');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_information`
--

DROP TABLE IF EXISTS `employee_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_information` (
  `id_employee` int NOT NULL,
  `id_restaurant` int NOT NULL,
  `in_date` varchar(45) NOT NULL,
  `occupation` varchar(45) DEFAULT NULL,
  `leave_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_employee`,`id_restaurant`,`in_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_information`
--

LOCK TABLES `employee_information` WRITE;
/*!40000 ALTER TABLE `employee_information` DISABLE KEYS */;
INSERT INTO `employee_information` VALUES (34,7,'2020-12-16T16','总经理:55200  rmb/month',NULL);
/*!40000 ALTER TABLE `employee_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id_feedback` int NOT NULL,
  `id_customer` int DEFAULT NULL,
  `id_order` int DEFAULT NULL,
  `time_feedback` varchar(45) DEFAULT NULL,
  `content_feedback` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_feedback`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `id_food` int NOT NULL,
  `name_food` varchar(45) DEFAULT NULL,
  `price_food` float DEFAULT NULL,
  PRIMARY KEY (`id_food`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TRIGGER `food_BEFORE_DELETE` BEFORE DELETE ON `food` FOR EACH ROW 
DELETE FROM set_from WHERE set_from.id_food = food.id_food;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'板烧鸡腿堡',20.5),(2,'安格斯厚牛培根堡',32),(3,'安格斯厚牛芝士堡',29),(4,'双层安格斯厚牛培根堡',44),(5,'双层安格斯厚牛芝士堡',41),(6,'买辣鸡腿堡',20),(8,'不素之霸双层牛堡',21),(9,'双层深海鳕鱼堡',21),(10,'双层脆鸡堡',21),(11,'双层吉士汉堡',19),(13,'吉士汉堡',10.5),(14,'汉堡包',9),(15,'买辣鸡翅',11.5),(16,'买乐鸡',11.5),(17,'那么小鸡排',13),(18,'薯条（中）',12),(19,'薯条（大）',14),(20,'朱古力新地',10),(21,'草莓新地',10),(22,'麦旋风奥利奥',13),(23,'麦旋风奥利奥草莓',13),(24,'可乐（中）',10),(25,'可乐（大）',12),(26,'零度可乐（中）',10),(27,'零度可乐（大）',12),(28,'雪碧（中）',10),(29,'雪碧（大）',12),(30,'纯牛奶',6.333),(32,'大大大薯条',20),(34,'大鸡霸',15);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_order`
--

DROP TABLE IF EXISTS `food_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_order` (
  `id_order` int NOT NULL,
  `id_food` int NOT NULL,
  `num_food` int DEFAULT NULL,
  PRIMARY KEY (`id_order`,`id_food`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_order`
--

LOCK TABLES `food_order` WRITE;
/*!40000 ALTER TABLE `food_order` DISABLE KEYS */;
INSERT INTO `food_order` VALUES (2,1,2);
/*!40000 ALTER TABLE `food_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `name_job` varchar(45) NOT NULL,
  `salary_job` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`name_job`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES ('厨师','4000'),('后勤','3500'),('总经理','55200'),('配送员','2000+2*x（x为当月订单数）');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myorder`
--

DROP TABLE IF EXISTS `myorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myorder` (
  `id_order` int NOT NULL,
  `id_restaurant` int DEFAULT NULL,
  `id_ai` int DEFAULT NULL,
  `tot_price` float DEFAULT NULL,
  `time_order` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myorder`
--

LOCK TABLES `myorder` WRITE;
/*!40000 ALTER TABLE `myorder` DISABLE KEYS */;
INSERT INTO `myorder` VALUES (2,2,2,41,'1607866442270'),(3,2,2,42,'1607866467781');
/*!40000 ALTER TABLE `myorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `id_restaurant` int NOT NULL,
  `loc_restaurant` varchar(45) DEFAULT NULL,
  `accom_restaurant` varchar(45) DEFAULT NULL,
  `tele_restaurant` int DEFAULT NULL,
  PRIMARY KEY (`id_restaurant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (1,'北京市-海淀区-学院路甲38号','1000',101),(2,'北京市-海淀区-学院路29号','45',62329414),(4,'北京市-海淀区-知春路59号海关大厦2层','60',62523612),(5,'北京市-海淀区-双榆树西里7号','28',62522845),(7,'264','20',111111),(9,'合一食堂','600',1652012);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `set_from`
--

DROP TABLE IF EXISTS `set_from`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `set_from` (
  `id_set_meal` int NOT NULL,
  `id_food` int NOT NULL,
  `num` int DEFAULT NULL,
  PRIMARY KEY (`id_set_meal`,`id_food`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `set_from`
--

LOCK TABLES `set_from` WRITE;
/*!40000 ALTER TABLE `set_from` DISABLE KEYS */;
INSERT INTO `set_from` VALUES (1,2,2),(1,8,3),(24,6,6),(24,7,2),(26,6,20),(26,7,1);
/*!40000 ALTER TABLE `set_from` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `set_meal`
--

DROP TABLE IF EXISTS `set_meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `set_meal` (
  `id_set_meal` int NOT NULL,
  `name_set_meal` varchar(45) DEFAULT NULL,
  `price_set_meal` float DEFAULT NULL,
  PRIMARY KEY (`id_set_meal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TRIGGER `set_meal_BEFORE_DELETE` BEFORE DELETE ON `set_meal` FOR EACH ROW 
DELETE FROM set_from WHERE set_from.id_set_meal = set_meal.id_set_meal;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `set_meal`
--

LOCK TABLES `set_meal` WRITE;
/*!40000 ALTER TABLE `set_meal` DISABLE KEYS */;
INSERT INTO `set_meal` VALUES (1,'普通双人餐-不减肥单人餐',100),(2,'丰盛双人餐-吃不下的单人餐',59),(3,'三人分享餐-双人刚好',79),(4,'安格斯厚牛培根堡套餐-除了贵没有缺点',45),(5,'安格斯厚牛芝士堡套餐-少了培根你忍心吗',42),(6,'双层安格斯厚牛培根堡套餐-极致的享受',57),(7,'双层安格斯厚牛芝士堡套餐-同是极致的享受',54),(8,'买辣鸡腿堡套餐',33),(9,'板烧鸡腿堡套餐',33),(10,'巨无霸套餐',36),(11,'不素之霸双层牛堡套餐',35),(12,'双层深海鳕鱼堡套餐',35),(13,'双层吉士汉堡套餐',34),(14,'买乐鸡套餐',27),(15,'买辣鸡翅套餐',38),(16,'巨无霸薯条四件套',39),(17,'小食分享桶A',37),(18,'小食分享桶B',38),(20,'大鸡霸套餐',125),(22,'司马套餐',100),(24,'猎马人套餐',50),(26,'nmsl套餐',152);
/*!40000 ALTER TABLE `set_meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `set_meal_order`
--

DROP TABLE IF EXISTS `set_meal_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `set_meal_order` (
  `id_order` int NOT NULL,
  `id_set_meal` int NOT NULL,
  `num_set_meal` int DEFAULT NULL,
  PRIMARY KEY (`id_order`,`id_set_meal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `set_meal_order`
--

LOCK TABLES `set_meal_order` WRITE;
/*!40000 ALTER TABLE `set_meal_order` DISABLE KEYS */;
INSERT INTO `set_meal_order` VALUES (3,5,1);
/*!40000 ALTER TABLE `set_meal_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-13 23:44:24
