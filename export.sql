-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.16.04.1

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `adminid` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('foo','foo','bhagya kamal jain',234,'a@b.com');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance` (
  `empid` varchar(128) NOT NULL,
  `date` date NOT NULL,
  `value` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`empid`,`date`),
  CONSTRAINT `fk` FOREIGN KEY (`empid`) REFERENCES `employee` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES ('ad1608','2019-10-14','Present'),('bb2211','2019-10-14','Absent'),('mittal21','2019-10-14','Present'),('patwari26','2019-10-14','Absent'),('TheAnshul','2019-10-14','Present');
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chemical`
--

DROP TABLE IF EXISTS `chemical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chemical` (
  `chemid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`chemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chemical`
--

LOCK TABLES `chemical` WRITE;
/*!40000 ALTER TABLE `chemical` DISABLE KEYS */;
INSERT INTO `chemical` VALUES ('cotton','cotton fiber'),('fruc','fructose'),('gluc','glucose'),('mgoh2','magnesium hydroxide'),('nacl','common salt'),('roh','alcohol');
/*!40000 ALTER TABLE `chemical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `companyid` varchar(128) NOT NULL,
  `companyname` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`companyid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('chopra','chopra enterprises'),('cida','companyAAA'),('cidb','companyBBB'),('tata','tata medical ventures');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `containschemical`
--

DROP TABLE IF EXISTS `containschemical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `containschemical` (
  `productid` varchar(128) NOT NULL,
  `chemid` varchar(128) NOT NULL,
  PRIMARY KEY (`productid`,`chemid`),
  KEY `chemid` (`chemid`),
  CONSTRAINT `containschemical_ibfk_1` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`),
  CONSTRAINT `containschemical_ibfk_2` FOREIGN KEY (`chemid`) REFERENCES `chemical` (`chemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `containschemical`
--

LOCK TABLES `containschemical` WRITE;
/*!40000 ALTER TABLE `containschemical` DISABLE KEYS */;
INSERT INTO `containschemical` VALUES ('bandage','cotton'),('paracetamol','fruc'),('didene','gluc'),('paracetamol','gluc'),('brufien','mgoh2'),('liv52','mgoh2'),('paracetamol','mgoh2'),('brufien','nacl'),('paracetamol','nacl'),('bandage','roh'),('didene','roh'),('liv52','roh');
/*!40000 ALTER TABLE `containschemical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `containsprod`
--

DROP TABLE IF EXISTS `containsprod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `containsprod` (
  `orderid` varchar(128) NOT NULL,
  `productid` varchar(128) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderid`,`productid`),
  KEY `productid` (`productid`),
  CONSTRAINT `containsprod_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`),
  CONSTRAINT `containsprod_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `containsprod`
--

LOCK TABLES `containsprod` WRITE;
/*!40000 ALTER TABLE `containsprod` DISABLE KEYS */;
INSERT INTO `containsprod` VALUES ('070dcb06-e2ca-4a10-a38a-8189e44f00901571041992','bandage',3),('070dcb06-e2ca-4a10-a38a-8189e44f00901571041992','brufien',3),('070dcb06-e2ca-4a10-a38a-8189e44f00901571041992','cr',4),('070dcb06-e2ca-4a10-a38a-8189e44f00901571041992','didene',9),('070dcb06-e2ca-4a10-a38a-8189e44f00901571041992','dtl',5),('070dcb06-e2ca-4a10-a38a-8189e44f00901571041992','injection',8),('070dcb06-e2ca-4a10-a38a-8189e44f00901571041992','paracetamol',8),('070dcb06-e2ca-4a10-a38a-8189e44f00901571041992','pill',8),('070dcb06-e2ca-4a10-a38a-8189e44f00901571041992','pm',9),('07ea5fb0-fa78-40a7-b0f6-cb0c34de0b2f1571042549','bandage',1),('07ea5fb0-fa78-40a7-b0f6-cb0c34de0b2f1571042549','brufien',1),('07ea5fb0-fa78-40a7-b0f6-cb0c34de0b2f1571042549','cr',1),('07ea5fb0-fa78-40a7-b0f6-cb0c34de0b2f1571042549','didene',1),('07ea5fb0-fa78-40a7-b0f6-cb0c34de0b2f1571042549','dtl',1),('07ea5fb0-fa78-40a7-b0f6-cb0c34de0b2f1571042549','injection',1),('07ea5fb0-fa78-40a7-b0f6-cb0c34de0b2f1571042549','liv52',1),('07ea5fb0-fa78-40a7-b0f6-cb0c34de0b2f1571042549','paracetamol',1),('07ea5fb0-fa78-40a7-b0f6-cb0c34de0b2f1571042549','pill',1),('07ea5fb0-fa78-40a7-b0f6-cb0c34de0b2f1571042549','pm',1),('20a57a8e-2e2b-4961-98f9-07fd3088763c1571042645','bandage',1),('20a57a8e-2e2b-4961-98f9-07fd3088763c1571042645','didene',2),('20a57a8e-2e2b-4961-98f9-07fd3088763c1571042645','dtl',7),('20a57a8e-2e2b-4961-98f9-07fd3088763c1571042645','injection',4),('20a57a8e-2e2b-4961-98f9-07fd3088763c1571042645','liv52',5),('20a57a8e-2e2b-4961-98f9-07fd3088763c1571042645','paracetamol',1),('90acdc8e-3527-483b-b3e7-8eb48802c2e81571040952','bandage',4),('90acdc8e-3527-483b-b3e7-8eb48802c2e81571040952','cr',6),('90acdc8e-3527-483b-b3e7-8eb48802c2e81571040952','injection',4),('90acdc8e-3527-483b-b3e7-8eb48802c2e81571040952','paracetamol',3),('90acdc8e-3527-483b-b3e7-8eb48802c2e81571040952','pm',4),('9b37aa95-7185-431f-82b2-cf90a782b2d81571042198','cr',1),('9b37aa95-7185-431f-82b2-cf90a782b2d81571042198','paracetamol',3),('9b37aa95-7185-431f-82b2-cf90a782b2d81571042198','pill',10),('9b37aa95-7185-431f-82b2-cf90a782b2d81571042198','pm',2),('a487db7a-30d9-4ae9-b400-bb97424a7d751571041019','bandage',2),('a487db7a-30d9-4ae9-b400-bb97424a7d751571041019','brufien',3),('a487db7a-30d9-4ae9-b400-bb97424a7d751571041019','cr',2),('a487db7a-30d9-4ae9-b400-bb97424a7d751571041019','dtl',5),('a487db7a-30d9-4ae9-b400-bb97424a7d751571041019','injection',2),('a487db7a-30d9-4ae9-b400-bb97424a7d751571041019','pill',2),('a487db7a-30d9-4ae9-b400-bb97424a7d751571041019','pm',3);
/*!40000 ALTER TABLE `containsprod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `empid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `position` varchar(128) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('ad1608','Aditya Mittal',4588,'amittal@c.com','Core Team Head','Room 137 @ visvesaraya hostel','qwerty'),('bb2211','bharat bhushan',9482748,'bb@gmail.com','HR','house 12 hyderabad gate','qwerty'),('mittal21','Abhishek',4568458,'mittal@123.com','cleaning staff','Room 135 ASN Bose Hostel','qwerty'),('patwari26','patwari ayush',56784,'ayush@google.com','Shopkeeper','Room 136 visvesaraya hostel','qwerty'),('TheAnshul','Asawa Anshul',4587476,'asawa@anshul.com','HR Head','Room23 ASN Bose Hostel','qwerty');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `rid` varchar(128) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `feedbacktext` varchar(1024) DEFAULT NULL,
  KEY `rid` (`rid`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `retailer` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES ('ShyamLal','2019-10-14','Quality of products is not up to the mark\r\n'),('ShyamLal','2019-10-14','Packaging is not done properly'),('leela123','2019-10-14','Very good service provided'),('leela123','2019-10-14','Your websites User Interface is good'),('sharma12','2019-10-14','I had ordered a order but it is not completed , till when will it be completed'),('sharma12','2019-10-14','Sorry , got it , nice website btw');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `productid` varchar(128) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`productid`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES ('bandage',2),('brufien',14),('cr',14),('didene',2),('dtl',12),('injection',12),('liv52',1),('paracetamol',12),('pill',13),('pm',12);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manages`
--

DROP TABLE IF EXISTS `manages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manages` (
  `managerid` varchar(128) NOT NULL,
  `empid` varchar(128) NOT NULL,
  PRIMARY KEY (`managerid`,`empid`),
  KEY `empid` (`empid`),
  CONSTRAINT `manages_ibfk_1` FOREIGN KEY (`managerid`) REFERENCES `employee` (`empid`),
  CONSTRAINT `manages_ibfk_2` FOREIGN KEY (`empid`) REFERENCES `employee` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manages`
--

LOCK TABLES `manages` WRITE;
/*!40000 ALTER TABLE `manages` DISABLE KEYS */;
INSERT INTO `manages` VALUES ('bb2211','ad1608'),('mittal21','ad1608'),('patwari26','ad1608'),('ad1608','bb2211'),('bb2211','mittal21'),('TheAnshul','mittal21'),('ad1608','patwari26'),('bb2211','patwari26'),('ad1608','TheAnshul'),('bb2211','TheAnshul');
/*!40000 ALTER TABLE `manages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturedby`
--

DROP TABLE IF EXISTS `manufacturedby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufacturedby` (
  `productid` varchar(128) NOT NULL,
  `companyid` varchar(128) NOT NULL,
  PRIMARY KEY (`productid`,`companyid`),
  KEY `companyid` (`companyid`),
  CONSTRAINT `manufacturedby_ibfk_1` FOREIGN KEY (`companyid`) REFERENCES `company` (`companyid`),
  CONSTRAINT `manufacturedby_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturedby`
--

LOCK TABLES `manufacturedby` WRITE;
/*!40000 ALTER TABLE `manufacturedby` DISABLE KEYS */;
INSERT INTO `manufacturedby` VALUES ('didene','chopra'),('liv52','chopra'),('bandage','cida'),('brufien','cida'),('liv52','cida'),('paracetamol','cida'),('bandage','cidb'),('brufien','cidb'),('paracetamol','cidb'),('didene','tata'),('liv52','tata');
/*!40000 ALTER TABLE `manufacturedby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderid` varchar(128) NOT NULL,
  `retailerid` varchar(128) DEFAULT NULL,
  `status` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `retailerid` (`retailerid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`retailerid`) REFERENCES `retailer` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('070dcb06-e2ca-4a10-a38a-8189e44f00901571041992','leela123','not ready'),('07ea5fb0-fa78-40a7-b0f6-cb0c34de0b2f1571042549','sharma12','not ready'),('20a57a8e-2e2b-4961-98f9-07fd3088763c1571042645','sharma12','not ready'),('90acdc8e-3527-483b-b3e7-8eb48802c2e81571040952','ShyamLal','Done'),('9b37aa95-7185-431f-82b2-cf90a782b2d81571042198','leela123','Done'),('a487db7a-30d9-4ae9-b400-bb97424a7d751571041019','ShyamLal','not ready');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `productid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('bandage','medical bandage',11),('brufien','brufien tablets',23),('cr','crocin',34),('didene','Antacid liquid',45),('dtl','Detol',12),('injection','injection',23),('liv52','Himalaya',23),('paracetamol','paracetamol tablets',34),('pill','sleeping pill',45),('pm','paracetamol',21);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retailer`
--

DROP TABLE IF EXISTS `retailer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `retailer` (
  `rid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `shopaddress` varchar(128) DEFAULT NULL,
  `ownersname` varchar(128) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retailer`
--

LOCK TABLES `retailer` WRITE;
/*!40000 ALTER TABLE `retailer` DISABLE KEYS */;
INSERT INTO `retailer` VALUES ('leela123','Leela Pharmacy Enterprises',578345,'qw@gmail.com','Sadar Bazar Lucknow','Mr Leela lal','qwerty'),('sharma12','Sharma Medical Shop',986689,'as@yahoo.com','Chandigarh Hill Road','dnaN Sharma','qwert'),('ShyamLal','Shyamu Lal Prasad Medical Store',78320,'shyamlal@prasadenterprise.com','shop 23 at Lanka Gate Bhu','Shyam','asd');
/*!40000 ALTER TABLE `retailer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-14 14:42:06
