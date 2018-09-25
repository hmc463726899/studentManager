-- MySQL dump 10.13  Distrib 5.7.22, for Win64 (x86_64)
--
-- Host: localhost    Database: stumanager
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Create database stuManager
--

drop database if exists stumanager;
create database stumanager;
use stumanager;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `username` varchar(20) DEFAULT NULL COMMENT '管理员账号',
  `name` varchar(20) DEFAULT NULL COMMENT '管理员姓名',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','管理员','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程Id',
  `name` varchar(20) DEFAULT NULL COMMENT '课程名称',
  `courseType` int(11) DEFAULT NULL COMMENT '课程类型',
  `score` varchar(20) DEFAULT NULL COMMENT '课程学分',
  PRIMARY KEY (`id`),
  KEY `FK_CourseType` (`courseType`),
  CONSTRAINT `FK_CourseType` FOREIGN KEY (`courseType`) REFERENCES `coursetype` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'软件工程',2,'3'),(2,'计算机图形学',2,'4'),(3,'计算机网络',2,'3'),(8,'测试课程',2,'1'),(9,'C语言程序设计',2,'1'),(10,'普通话',3,'2'),(11,'数据结构',2,'2.0'),(12,'高数',1,'2.0');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursetype`
--

DROP TABLE IF EXISTS `coursetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursetype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `name` varchar(20) DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursetype`
--

LOCK TABLES `coursetype` WRITE;
/*!40000 ALTER TABLE `coursetype` DISABLE KEYS */;
INSERT INTO `coursetype` VALUES (1,'工科'),(2,'计算机必修'),(3,'公共课程'),(4,'理科');
/*!40000 ALTER TABLE `coursetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dormi_zone`
--

DROP TABLE IF EXISTS `dormi_zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dormi_zone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zone_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dormi_zone`
--

LOCK TABLES `dormi_zone` WRITE;
/*!40000 ALTER TABLE `dormi_zone` DISABLE KEYS */;
INSERT INTO `dormi_zone` VALUES (1,'芷兰'),(2,'丰泽'),(3,'东湖'),(4,'金岸');
/*!40000 ALTER TABLE `dormi_zone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `punish`
--

DROP TABLE IF EXISTS `punish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `punish` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '惩罚id',
  `student_id` int(11) DEFAULT NULL COMMENT '惩罚学生Id',
  `content` varchar(1000) DEFAULT NULL COMMENT '惩罚内容',
  `time` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_StudentId_punish` (`student_id`),
  CONSTRAINT `FK_StudentId_punish` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `punish`
--

LOCK TABLES `punish` WRITE;
/*!40000 ALTER TABLE `punish` DISABLE KEYS */;
INSERT INTO `punish` VALUES (2,33,'第二节课翘课','2018-06-12'),(3,33,'1','2018-06-04');
/*!40000 ALTER TABLE `punish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality`
--

DROP TABLE IF EXISTS `quality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '素质拓展Id',
  `name` varchar(50) DEFAULT NULL COMMENT '素质拓展活动名称',
  `score` float DEFAULT NULL COMMENT '素质拓展分数',
  `student_id` int(11) DEFAULT NULL COMMENT '选择学生id',
  `time` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_StudentId_quality` (`student_id`),
  CONSTRAINT `FK_StudentId_quality` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quality`
--

LOCK TABLES `quality` WRITE;
/*!40000 ALTER TABLE `quality` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reward`
--

DROP TABLE IF EXISTS `reward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reward` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '奖励Id',
  `student_id` int(11) DEFAULT NULL COMMENT '奖励学生Id',
  `content` varchar(1000) DEFAULT NULL COMMENT '奖励内容',
  `time` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_StudentId_reward` (`student_id`),
  CONSTRAINT `FK_StudentId_reward` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reward`
--

LOCK TABLES `reward` WRITE;
/*!40000 ALTER TABLE `reward` DISABLE KEYS */;
/*!40000 ALTER TABLE `reward` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stu_afl`
--

DROP TABLE IF EXISTS `stu_afl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stu_afl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `approve` int(11) DEFAULT '0',
  `approve_time` varchar(255) DEFAULT NULL,
  `approve_admin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_StudentId_afl` (`student_id`),
  CONSTRAINT `FK_StudentId_afl` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stu_afl`
--

LOCK TABLES `stu_afl` WRITE;
/*!40000 ALTER TABLE `stu_afl` DISABLE KEYS */;
INSERT INTO `stu_afl` VALUES (7,33,'学生会开会','2018-06-03','2018-06-06',1,NULL,NULL),(8,33,'考试','2018-06-03','2018-06-04',1,NULL,NULL),(9,34,'huijia','2018-06-03','2018-06-05',0,NULL,NULL),(10,33,'测试','2018-06-03','2018-06-06',1,NULL,NULL);
/*!40000 ALTER TABLE `stu_afl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stu_course`
--

DROP TABLE IF EXISTS `stu_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stu_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '选课Id',
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程Id',
  `score` int(11) DEFAULT NULL COMMENT '课程成绩',
  PRIMARY KEY (`id`),
  KEY `FK_StudentId_course` (`student_id`),
  KEY `FK_CourseId` (`course_id`),
  CONSTRAINT `FK_CourseId` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_StudentId_course` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stu_course`
--

LOCK TABLES `stu_course` WRITE;
/*!40000 ALTER TABLE `stu_course` DISABLE KEYS */;
INSERT INTO `stu_course` VALUES (1,34,1,12),(3,33,2,60),(7,34,2,NULL),(9,34,8,65),(10,33,8,80),(11,35,8,NULL),(12,35,1,87),(14,35,3,NULL),(18,34,9,90),(19,33,9,86),(20,35,2,NULL),(22,35,9,60),(23,37,9,75),(24,37,8,NULL),(25,37,3,90),(26,37,1,NULL),(27,33,3,NULL),(29,33,10,NULL),(30,35,11,NULL),(31,33,12,NULL);
/*!40000 ALTER TABLE `stu_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stu_dormitory`
--

DROP TABLE IF EXISTS `stu_dormitory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stu_dormitory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) DEFAULT NULL,
  `zone_id` int(11) DEFAULT NULL,
  `building` varchar(64) DEFAULT NULL,
  `room` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_stu_id` (`stu_id`),
  KEY `FK_zone_id` (`zone_id`),
  CONSTRAINT `FK_stu_id` FOREIGN KEY (`stu_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_zone_id` FOREIGN KEY (`zone_id`) REFERENCES `dormi_zone` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stu_dormitory`
--

LOCK TABLES `stu_dormitory` WRITE;
/*!40000 ALTER TABLE `stu_dormitory` DISABLE KEYS */;
INSERT INTO `stu_dormitory` VALUES (4,33,4,'03','334'),(5,37,2,'01','204'),(7,38,3,'10','203');
/*!40000 ALTER TABLE `stu_dormitory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `name` varchar(20) DEFAULT NULL COMMENT '学生姓名',
  `idcard` varchar(20) DEFAULT NULL COMMENT '身份证',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(50) DEFAULT NULL COMMENT '家庭住址',
  `reset` varchar(11) DEFAULT '0' COMMENT '修改密码标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (33,'张三','11111111111111111','男','187xxxxxxxx','123456789','4875772fdf58243@qq.com','湖南省长沙市芙蓉区东湖路','1'),(34,'李四','22222222222222222','女','135XXXXXXX','123456789','184dsa8551111165@qq.com','湖南省长沙市芙蓉区湖南农业大学','1'),(35,'小明','33333333333333333','男','135XXXXXXX','123456789','1485697525281547@qq.com','湖南省长沙市芙蓉区东湖路','0'),(37,'蔡梦婷','430223199712208325','女','187110430821','','','','1'),(38,'颜妃','430223199612048326','女','18711013082','2659231576','1485697525281547@qq.com','','0');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `switch`
--

DROP TABLE IF EXISTS `switch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `switch` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '开关id',
  `name` varchar(20) DEFAULT NULL COMMENT '开关名称',
  `state` int(11) DEFAULT NULL COMMENT '开关状态(1位打开状态)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `switch`
--

LOCK TABLES `switch` WRITE;
/*!40000 ALTER TABLE `switch` DISABLE KEYS */;
INSERT INTO `switch` VALUES (1,'studentSwitch',0),(2,'courseSwitch',1);
/*!40000 ALTER TABLE `switch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `stuId` int(11) DEFAULT NULL COMMENT '学生id',
  PRIMARY KEY (`id`),
  KEY `FK_StudentId_user` (`stuId`),
  CONSTRAINT `FK_StudentId_user` FOREIGN KEY (`stuId`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'123456','123456',34),(7,'123','123',33),(9,'kay','456123',37);
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

-- Dump completed on 2018-07-01 14:20:22
