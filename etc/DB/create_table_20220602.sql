-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: with.parkh.xyz    Database: doingdoing
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
-- Table structure for table `tb_auth`
--

DROP TABLE IF EXISTS `tb_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_auth` (
  `USER_ID` varchar(20) NOT NULL COMMENT '사용자 아이디',
  `PASSWORD` varchar(255) NOT NULL COMMENT '비밀번호',
  PRIMARY KEY (`USER_ID`),
  CONSTRAINT `tb_auth_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `tb_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='인증 정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_authkey`
--

DROP TABLE IF EXISTS `tb_authkey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_authkey` (
  `NO` int NOT NULL AUTO_INCREMENT COMMENT '구분 번호',
  `USER_ID` varchar(20) NOT NULL COMMENT '사용자 아이디',
  `EMAIL` varchar(50) NOT NULL COMMENT '이메일',
  `AUTHKEY` varchar(50) NOT NULL COMMENT '인증키',
  `CREATE_TIME` datetime NOT NULL COMMENT '발급시간',
  `TYPE` int NOT NULL,
  PRIMARY KEY (`NO`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_friend`
--

DROP TABLE IF EXISTS `tb_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_friend` (
  `REQUESTER_ID` varchar(20) NOT NULL COMMENT '신청한 사람 아이디',
  `ADDRESSEE_ID` varchar(20) NOT NULL COMMENT '수신 받은 사람 아이디',
  `STATUS` varchar(20) NOT NULL COMMENT '상태',
  PRIMARY KEY (`REQUESTER_ID`,`ADDRESSEE_ID`),
  KEY `ADDRESSEE_ID` (`ADDRESSEE_ID`),
  CONSTRAINT `tb_friend_ibfk_1` FOREIGN KEY (`REQUESTER_ID`) REFERENCES `tb_user` (`USER_ID`),
  CONSTRAINT `tb_friend_ibfk_2` FOREIGN KEY (`ADDRESSEE_ID`) REFERENCES `tb_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_schedule`
--

DROP TABLE IF EXISTS `tb_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_schedule` (
  `NO` int NOT NULL AUTO_INCREMENT COMMENT '구분 번호',
  `USER_ID` varchar(20) NOT NULL COMMENT '사용자 아이디',
  `TITLE` varchar(50) NOT NULL COMMENT '제목',
  `CONTENT` varchar(50) DEFAULT NULL COMMENT '내용',
  `END_TIME` datetime DEFAULT NULL COMMENT '기한',
  `IS_PUBLIC` tinyint(1) NOT NULL DEFAULT '1' COMMENT '공개여부',
  `IS_COMPLETE` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`NO`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `tb_schedule_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `tb_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='일정';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user` (
  `USER_ID` varchar(20) NOT NULL COMMENT '사용자 아이디',
  `NAME` varchar(10) NOT NULL COMMENT '이름',
  `EMAIL` varchar(50) NOT NULL COMMENT '이메일',
  `COMPANY` varchar(50) DEFAULT NULL COMMENT '직장',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='사용자 정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` varchar(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `company` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-03 16:28:21
