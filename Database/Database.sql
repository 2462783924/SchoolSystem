-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: school_system
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `classid` int NOT NULL AUTO_INCREMENT,
  `classname` varchar(45) DEFAULT NULL,
  `static` int DEFAULT '1',
  PRIMARY KEY (`classid`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1001,'计算机科学与技术',1),(1002,'英语',1),(1003,'生信',1);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `courseid` int NOT NULL AUTO_INCREMENT,
  `coursename` varchar(45) DEFAULT NULL,
  `static` int DEFAULT '1',
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=2010 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,NULL,0),(2001,'JAVA',1),(2002,'C++',1),(2003,'大数据',1),(2004,'云计算',1),(2005,'计算机网络',1),(2006,'测试',1),(2009,'测试2',1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `stuno` varchar(45) NOT NULL,
  `courseid` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  KEY `stuno` (`stuno`),
  KEY `courseid` (`courseid`),
  KEY `index_1` (`stuno`,`courseid`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_ibfk_3` FOREIGN KEY (`stuno`) REFERENCES `student` (`stuno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES ('2000502101',2004,75),('2000502101',2004,75),('2000502101',2004,75),('2000502102',2001,84),('2000502102',2002,87),('2000502102',2003,77),('2000502101',2001,89),('2000502101',2002,81),('2000502101',2003,92),('2000502101',2001,89),('2000502101',2002,81),('2000502101',2003,92),('2000502102',2001,84),('2000502102',2002,87),('2000502102',2003,77),('2000502102',2004,93),('2000502103',2001,76),('2000502103',2002,81),('2000502103',2003,69),('2000502103',2004,86),('2000502101',2005,60);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `stuno` varchar(45) NOT NULL,
  `stuname` varchar(45) DEFAULT NULL,
  `stusex` varchar(45) DEFAULT NULL,
  `classid` int DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `stuno_UNIQUE` (`stuno`),
  KEY `class_id` (`classid`),
  CONSTRAINT `class_id` FOREIGN KEY (`classid`) REFERENCES `class` (`classid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `userkey_id` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'2000502101','学生一','男',1001,'18611111111'),(2,'2000502102','学生二','女',1001,'18622222222'),(3,'2000502103','学生三','男',1002,'18633333333'),(7,'2000502107','测试1','女',1001,'18688888888'),(8,'2000502108','测试2','女',1001,'18600000000'),(9,'2000502109','测试3','男',1002,'18600000000'),(10,'2000502110','测试4','男',1003,'18600000000'),(11,'2000502111','测试5','女',1001,'18600000000'),(12,'2000502112','测试6','女',1002,'18600000000'),(13,'2000502113','测试7','女',1002,'18600000000'),(14,'2000502114','测试8','男',1002,'18600000000'),(15,'2000502115','测试9','男',1001,'18600000000'),(16,'2000502116','测试10','女',1003,'18600000000'),(17,'2000502117','测试11','男',1003,'18600000000'),(18,'2000502118','测试12','男',1003,'18600000000'),(19,'2000502119','测试13','女',1002,'18600000000'),(20,'2000502120','测试14','男',1003,'18600000000'),(21,'2000502121','测试15','女',1001,'18600000000'),(22,'2000502122','测试16','女',1002,'18600000000'),(23,'2000502123','测试17','男',1003,'18600000000'),(24,'2000502124','测试18','男',1001,'18600000000'),(25,'2000502125','测试19','女',1002,'18600000000');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `id` bigint NOT NULL,
  `teachno` varchar(45) DEFAULT NULL,
  `teachname` varchar(45) DEFAULT NULL,
  `teachsex` varchar(45) DEFAULT NULL,
  `teachcourse` int NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `teachcourse_idx` (`teachcourse`),
  CONSTRAINT `teachid` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (4,'2001','教师一','男',2001,'13711111111'),(5,'0000','李明华','男',2002,'18688888888'),(42,'1111','li','男',2002,'18688888888');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `userrank` int NOT NULL,
  `status` int NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='用户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'one','111111',0,0),(2,'two','222222',0,1),(3,'123','321',0,1),(4,'test02','123',1,1),(5,'test03','test',1,1),(6,'admin','admin',3,1),(7,'test04','test',0,1),(8,'test05','test',0,1),(9,'test06','test',0,1),(10,'test07','test',0,1),(11,'test08','test',0,1),(12,'test09','test',0,1),(13,'test10','test',0,1),(14,'test11','test',0,1),(15,'test12','test',0,1),(16,'test13','test',0,1),(17,'test14','test',0,1),(18,'test15','test',0,1),(19,'test16','test',0,1),(20,'test17','test',0,1),(21,'test18','test',0,1),(22,'test19','test',0,1),(23,'test20','test',0,1),(24,'test21','test',0,1),(25,'test22','test',0,1),(42,'teacher','teacher',2,1);
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

-- Dump completed on 2023-05-10 13:54:31
