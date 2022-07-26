-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: doingdoing
-- ------------------------------------------------------
-- Server version	8.0.26


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
-- Dumping data for table `tb_auth`
--

LOCK TABLES `tb_auth` WRITE;
/*!40000 ALTER TABLE `tb_auth` DISABLE KEYS */;
INSERT INTO `tb_auth` VALUES ('par012354','$2a$12$V8wJx6FyZRSHWMMpZx2VIe1iRnQ.RaT28z41UgWOhRYhzilCo45ke'),('park','$2a$12$g4oU1jqK8HtiqaLxwdsPtOsrOsPfiWsRyn6jW964gWL7HiWdqMTWq'),('park123','$2a$12$KvrxLxG7FhUZrF5cEIOWfOQSJlhvrUwmmcbG54eFYWdmcKlhTClhS'),('park1234','$2a$12$4V3zAY.LV83l4gEgC69zk.Z.cjS1gv8esubVU2Pe6keryxfAQy5bm'),('park12354','$2a$12$qOzSwkek9L.SXdZe9/owyO5kJnaSsHaACk0NBU6lT0d09YjhLzDle'),('park2','$2a$12$aAlyuBgofzUJzjoEzxKnz.H4Je1R9RKFCtToUCa4DEWNsklY84Adq');
/*!40000 ALTER TABLE `tb_auth` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`NO`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `tb_authkey_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `tb_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='인증키';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_authkey`
--

LOCK TABLES `tb_authkey` WRITE;
/*!40000 ALTER TABLE `tb_authkey` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_authkey` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_friend`
--

LOCK TABLES `tb_friend` WRITE;
/*!40000 ALTER TABLE `tb_friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_friend` ENABLE KEYS */;
UNLOCK TABLES;

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
  `CONTENT` varchar(50) NOT NULL COMMENT '내용',
  `END_TIME` datetime NOT NULL COMMENT '기한',
  `IS_PUBLIC` tinyint(1) NOT NULL COMMENT '공개여부',
  PRIMARY KEY (`NO`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `tb_schedule_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `tb_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='일정';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_schedule`
--

LOCK TABLES `tb_schedule` WRITE;
/*!40000 ALTER TABLE `tb_schedule` DISABLE KEYS */;
INSERT INTO `tb_schedule` VALUES (2,'park','제목1','글','2022-05-22 00:00:00',1),(3,'park','제목2','글','2022-05-22 00:00:00',1),(4,'park','1','글','2022-05-22 15:38:03',1),(5,'park','1','글','2022-05-22 15:38:07',1),(6,'park','1','글','2022-05-22 15:38:10',0),(7,'park','1','글','2022-05-22 15:38:13',0),(8,'park','1','글','2022-05-22 15:38:13',0),(9,'park','1','글','2022-05-22 15:38:13',0),(10,'park','1','글','2022-05-22 15:38:13',0),(11,'park','1','글','2022-05-22 15:38:29',0),(12,'kQcjWk8UX5','gIytJ','JqsDSdPybF','2022-05-23 06:44:47',1),(13,'pkYnwcIeNb','PZXKc','WPAETZdhlj','2022-05-23 06:48:52',1),(14,'FEOTGSmCID','SCpQT','hxaacmJQKV','2022-05-23 06:50:53',1),(15,'H9uvaXx2pB','vJOaV','WhBPtzfxMN','2022-05-23 06:53:38',1),(16,'eEEtL4PusD','pJIza','QrtDFihcBw','2022-05-23 06:54:43',1),(17,'nQAHquHcpU','mOlPT','dkjSQNFyci','2022-05-23 06:55:18',1),(18,'CxcBdFE5gU','DXnhW','KMlOGYtOiy','2022-05-23 06:55:48',1),(19,'HvqjboeDMP','GqySv','cEIwuwWfRj','2022-05-23 06:56:32',1),(20,'WUTC7Rvy57','Fepsg','TjHIjeKetK','2022-05-23 06:56:34',1),(21,'GYpLg1SlZN','NdbzP','kXgKpWUcsB','2022-05-23 06:57:02',1),(22,'BkvQuDs9vZ','dEEpZ','dETDGxvqOi','2022-05-23 06:57:44',1),(23,'PuVx6G478q','iRIIW','oDDuCgfBbp','2022-05-23 06:59:12',1),(27,'wMpyZW209K','nzhZT','newContent','2022-05-23 07:17:04',1),(28,'1cj4adv102','EIXAJ','newContent','2022-05-23 08:34:26',1),(29,'nzYG900R7Y','tYjwl','newContent','2022-05-23 08:37:02',1),(30,'tlgFCwBXev','XVFaV','newContent','2022-05-23 08:39:01',1),(31,'FJjJrnMCtT','eJUlg','newContent','2022-05-23 08:39:38',1),(32,'tBohl7w0Yk','eDXFS','newContent','2022-05-23 08:40:17',1),(33,'lSUm4VABGJ','peiFB','newContent','2022-05-23 08:40:37',1),(34,'OXwGDS7VQa','ZdhyD','newContent','2022-05-23 08:46:22',1),(35,'gxMvuHMPIy','geLKo','newContent','2022-05-23 08:46:30',1),(36,'DNi1tQ28vG','wYCCz','newContent','2022-05-23 08:46:59',1),(37,'SB0oT4Tdvh','qTvFK','newContent','2022-05-23 08:50:06',1),(38,'S7av1PnoOx','BcDTP','newContent','2022-05-23 08:53:40',1),(39,'V8s9xD16Nx','qhGbB','newContent','2022-05-23 08:56:24',1),(40,'rgZAB6cyox','LwOXO','newContent','2022-05-23 08:58:26',1),(42,'nmVA9OOH88','Vyrjz','newContent','2022-05-24 00:17:17',1),(44,'lDUVNwqxKh','ZGujz','newContent','2022-05-24 00:46:28',1),(46,'qwijoNanQm','sMabv','newContent','2022-05-24 00:55:30',1),(48,'A8flEuinoK','gulLl','newContent','2022-05-24 06:48:59',1),(50,'3zDI0WDVDe','xTzJV','newContent','2022-05-24 06:49:28',1),(52,'Kk51HYPYgt','fIqqO','newContent','2022-05-24 10:54:28',1),(54,'q9GFslEoqS','mRDYA','newContent','2022-05-24 10:54:50',1),(56,'dkUD4swgF2','BMDXo','newContent','2022-05-24 10:55:03',1),(58,'ufbYHvewcL','TFQji','newContent','2022-05-24 10:55:22',1),(60,'Symwl7i6kX','WtFfX','newContent','2022-05-24 11:01:41',1),(62,'JVlHF49CPF','Jxjat','newContent','2022-05-24 11:03:07',1),(64,'sPJeMQjPjE','jBuEk','newContent','2022-05-24 11:06:42',1),(66,'5pgB6g2Q40','hEGTm','newContent','2022-05-24 11:16:20',1),(68,'AhetUQt4ew','tPXXD','newContent','2022-05-24 12:25:27',1);
/*!40000 ALTER TABLE `tb_schedule` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('05iDrnFIOG','DXSby','yMyZC@VleOa.WiN','ZTNczjXFFR'),('0GSmVW16Qx','yGfrg','ZIIDB@dMmyi.aNS','newCompany'),('0MlnRYBulA','XtnbD','dOvav@pbDTK.eve','newCompany'),('0XNXIodazO','xQDSG','chDHL@LOhVn.ixY','LpWIeXUCLn'),('1cj4adv102','oIukt','exOqS@TpTLs.veD','EYOvporHmN'),('1egRzsTpuk','LxzWM','NvekT@BTLXe.YKf','TswMWLZUHc'),('1jC2qT7vvr','pGpQl','yhRHZ@yKDQI.wiv','newCompany'),('1kwOBSgI96','newName','WJObT@mEpUy.YwI','VSdgoHbyLy'),('1KZ7P7cw8P','zkaGI','SIFza@OKoJZ.CEw','dCZSHArKGb'),('1o8W2vz3AL','newName','yZBdD@BUAqH.wXI','dGZaSEYVVY'),('1wenmBfeg7','newName','qGSCR@ByGAb.rpn','pDIvAgRqMi'),('2CkT9gR66F','mTvYV','bnHut@aEIJQ.Lgr','eiauYOwaHG'),('2hh3jV1DHl','QLCns','rZxLI@PjpGV.mOp','CxiwABpKkz'),('2UGfGywPb7','VEzRk','JRdRp@qMpyQ.aIJ','herJEQLRBR'),('3g278Y5j5T','newName','qdNHJ@WTIsd.BEi','pCKFsCCNRF'),('3mit7hnVof','AMHrH','KWiOB@FvLzg.yNf','rxZqLMbIGt'),('3zDI0WDVDe','KEpwK','bagdR@yqVQg.nNy','euqeBLBLbc'),('4cB0aqKtpE','DHPSj','qPLgO@ODdtu.PQK','cKnudVREQB'),('4fE4GsbCwl','tZdSr','jFnWu@PcXPc.lsd','gFAsAprBXR'),('4HwY5joHtT','newName','QQKKh@IkZvA.nlH','VAGVyeyWhw'),('4rEEbnmqdG','jKLPB','JsyAX@qWuKG.Kka','SHzxiuWlKV'),('58HAYtorke','sUCTW','MVvJr@bTIdK.gQy','xcXvzksVRI'),('5mmsEifiFV','culTq','kHrgH@VPEND.weP','zgPUFACscm'),('5pgB6g2Q40','ugIFF','SCCYb@ObVWW.nTL','XhbhHsCLUu'),('5ZCpIFXYAt','newName','soAaF@fIuzx.oZG','gGPbewDoDJ'),('5ZJeUMYrBo','XOINn','bcDDd@CrPoL.BCA','newCompany'),('6FDer2GhVp','hAqKC','UTfVH@JmsqW.Rjf','newCompany'),('6ihCaZqABW','newName','rdLLB@QUcdJ.dIy','LSPFaAUfzz'),('6IPlqoNBGZ','newName','xdurs@WsMdA.xPF','OkdpujvfXc'),('6pTIbTQxQE','newName','PrKZZ@ZmfDL.wyE','HsoikpCsjA'),('700iEubdzR','newName','ghyXx@UjkCI.hNV','IEheEBCpBx'),('7srLpwhoYL','oJoxn','KWwer@QFdnj.CAg','pTqCRUDsyS'),('7U50OhZeC8','cHzxS','dnBFZ@ikXsW.nAb','foVMfBXlIj'),('8V8fJok7af','QnMQd','vMPdm@RMzRd.wxg','ozXiiMrIKQ'),('9HXhlejMjN','dWgSR','yoVbh@tOORd.ncI','newCompany'),('9OKXhETkL2','TYJGo','kGaio@jplfX.Mte','newCompany'),('A0pOwb9Rl4','gdJEm','pGyts@Otate.xjO','zcjRYbeBnv'),('A8flEuinoK','ilCah','PGInZ@GKAjX.bVA','loxGLKKJxE'),('A9Jm7CQsYK','lleJO','meJbP@tXZcw.xVv','newCompany'),('aFhjA7rDyN','GLrMY','rZnNa@TrpdM.oaJ','newCompany'),('AhetUQt4ew','hJRyF','BIMGi@DOikL.ndC','oQyzLJZaKS'),('aIKS1D5FAW','newName','DCDHB@fmCsx.XEi','JtmJDOVSwj'),('Ak1iq5JwRA','newName','VqBGw@HrAqn.iWp','UnvYaXbqGU'),('AvUbc89pNt','hLODT','FyZgK@fnUJs.yde','aXmirSyddJ'),('avZCnrsNHU','dWWjE','jbkJO@nICUy.kBr','DTtlHfSiHx'),('Bjnh9hJyBF','XjCeA','pGzPZ@CHBQn.jmk','HtHeOkQzuk'),('BkvQuDs9vZ','tLuZp','ZKvlC@GVyCM.WOz','lYCwJYTgIU'),('BOy7oXdfV7','newName','abpNO@KKUsw.EvZ','KEZqWcNGiH'),('bv8HZu8l1K','KNrds','VKWHC@houxA.jQe','enOuBKSjZJ'),('c2bvBgfo89','xNMtX','HJnpp@cLiJc.HeZ','bPKOJmDbSm'),('C5MwOFAjhR','newName','HyopP@RpTuf.MsQ','XJMzoKWYif'),('cAkn6t7Sxy','newName','MmRwQ@ucGGO.kRo','HPDaakTZFa'),('cdTfCoaDTq','igdAU','wuhAx@zrxJD.GCp','newCompany'),('CPB6vvaWim','xjGuw','JwlTW@UPDsR.kJo','newCompany'),('cq3jHnGtdz','SEalP','hVMze@PKPgI.med','tKpoEkqadl'),('CxcBdFE5gU','TSpGC','cTbPD@flpLc.pCe','jlxhHMTJyF'),('CxWCjkSXMT','MFTZe','tgYbu@hJCqA.HpR','newCompany'),('cyihNWcTg9','fEhhA','ZKtaG@nfIje.Neh','mdhQOrrBFh'),('d2OTsrzmEM','RTySB','GuRjU@LhkyL.eyq','InAALzavvo'),('d7MtBaWmov','hwMXc','BOcxq@wnPPF.KKB','newCompany'),('dAudckVSiW','hiGzi','gukHS@RHYsA.BeL','rKwSMVPMGb'),('delH3jXICI','newName','NleOY@KRMTF.tiB','vHuLSkSAxM'),('dkb3wYTJvo','PWgdA','zGFjS@zmBcI.uqB','MkIzfGsTYh'),('dkUD4swgF2','kRcbL','owOrs@adXvY.YBq','UWNkTQReqe'),('DNi1tQ28vG','hcwtk','IkmlK@KAPRs.Fqg','DnldpfvESN'),('Do8rUbYUnT','UmzXb','HOyyi@RAvoB.HuZ','tueoiLWqvN'),('dPxGb9QAyW','giLWn','bNyjL@ArFdQ.moa','hoIoCyhXhy'),('DqeY7Wv3ka','newName','obqjk@YNTnY.GtK','crbbfvdRow'),('dUGXPH64RE','ftOur','tNXoJ@qLumW.uiN','zcGuJlykzf'),('Eawq45ot2X','WJAbz','hJnOL@QuTfW.HRl','newCompany'),('EBIDnH5wXK','HJuWC','zyivF@boxPu.Ebi','amwqzsjtVN'),('eEEtL4PusD','KOEHK','eANzQ@ixjrP.mPY','bpevYwXCUT'),('EeiAibiv1G','newName','CMNxI@detcV.CEZ','WYvZOfnrKQ'),('EiHP0DAr2q','newName','Sdshn@MONCD.nQY','dktQEmOYgF'),('EK68WweorH','wnwvw','djVPW@pqUvc.kOa','newCompany'),('em2LSdzNXc','gSDyY','HpNLs@iWvzI.ayC','EQYxUjvbsZ'),('eqThBaJbOm','gQqnb','gOzQB@zMacL.oHL','EQjROCZmEO'),('eW94GwFyyW','newName','DocxJ@PTefS.uga','ALsHKbiIoj'),('eXY1Ujyv2W','WQjQl','FOCyJ@Hdrnu.mks','NDWEHtTHAS'),('exzeEtUlMp','VezrY','Ytrkn@nPHRG.RUP','KZkzcfuzOd'),('FBgdU8t0eb','ZKPrm','wpjHd@hwFGj.TKI','gQvTgRnuEc'),('fcCfvCw8tp','OXwMs','GylNI@AqVMc.pgG','ndndzUGpwf'),('FdbUZmJB4b','nrlSv','RZQKq@DtqbC.Fjc','vfiOEcGIus'),('FEOTGSmCID','jkJEW','wHeUH@qyBcX.osh','YmKiYfXNdQ'),('FG2w4ICjVd','kjHVO','xaYso@dtqhd.ajW','vwRIxrxFFG'),('FJjJrnMCtT','JhVpV','fRwLu@CrAuV.kEM','JDgwlUrZBD'),('fwO7985lu8','GVGJn','neIdW@GKVry.Vjm','JSCQtFPbyJ'),('gFoiiddZhi','QydXF','yVFNk@AQGLP.gWL','newCompany'),('GhgeTezlL5','JhXCy','hAZtH@wrFvB.Rft','yphXQcoXyY'),('glW4fLfkyP','ZHmxV','erpKr@cscCv.Udz','oGYKWIrKdg'),('GndAgy0GnW','LHkHg','Zbjkr@pyIVN.nhJ','CZsrzDZxIB'),('GRCwvzySD3','yaDTn','SBYbx@GdDbZ.hPv','FILbzHhDrt'),('gtT3w04Uhl','newName','CXNHk@XByGg.GCE','JZzAJoNRht'),('gxMvuHMPIy','HORlJ','xxxwy@XJIcH.vrN','GiiLUPTwpv'),('GYpLg1SlZN','AbgIe','aARDY@QGqRT.Qrv','gPRZCyqdvr'),('H7B7hNUtNO','newName','RuOdB@VOVQk.Say','WAPjUIihDF'),('H9uvaXx2pB','jOheb','iuYHi@HzlJl.fPZ','HDLCwhOuto'),('HAGLazDsDL','SuskW','HMeRn@lLKNM.hmN','PiWfEXYNyg'),('hLy2GCj0wP','mJHjs','TeVfH@hgMOh.iFs','cTETkfEkVF'),('HNlFRYRpBw','yzDki','rrHRS@YatJY.ZqO','fOFFRzXwFY'),('Hp4fsjDzAR','JTxBb','liSlx@sSyEf.fnU','vOaieEqjdV'),('hS1DbRsbhU','YPvFG','hLlsD@wvTBF.KUH','AFHXoPQeaM'),('huGe1nyGK4','rPKVd','uUrCw@UPoSA.ZdK','OLCADWeKpP'),('HvqjboeDMP','WvWxF','iyxlS@btdFA.vEz','MbWnHcNuUd'),('HXv6Ml7FMG','CshQc','galKj@hErQB.OOi','JINcuRVBPq'),('iHSXr1zyb4','eoMjN','ttWDP@tWbMn.oKm','laARwzmJzo'),('ijOYsfCs56','CHjEc','QXASW@LqfQj.cWp','newCompany'),('iMYmXW3WT4','Txgop','QDscQ@DVrkn.Ugm','bWLszYmFmd'),('ipq85LidFm','jZwXq','RDsPd@BNpXZ.MkN','uSWFCijZNK'),('IPqrdnymkS','SyaJF','MQHlh@IPoIJ.yWw','ARNWPJHiKy'),('iQ1idQoXOT','newName','VdTtj@ayxtF.qmQ','SdfWguhAUX'),('IvpXQ4ra3c','cUFMZ','gaaeV@VLnEb.Pgh','newCompany'),('JaeeQgZixG','tMJmw','XLRfA@iXksp.nAJ','NucEZvVkuW'),('jdRgoBvtKk','mexhF','rtvTs@WMvEK.Egs','newCompany'),('Jg7rMFAGLy','newName','wrHIg@lofWB.BLh','LbfrQrkjvz'),('jmaJyVGnY3','givcc','FEkKG@sdDCR.uEs','FsNuExJiWP'),('jsyTzLv2vV','FRakp','IBycd@QSttY.JJy','newCompany'),('JVlHF49CPF','HrkCj','RYqlD@MHIJS.YNA','bUeymQubJu'),('JWYfyGZQJ3','newName','nOlTV@UadmU.GiY','OPMYLVTawZ'),('JY8IUOXqXv','nTqKk','AAjAf@rIPQy.ORD','uLCxfXxGEe'),('kcsljrmKAH','newName','nGjsZ@yZjQL.gcM','TPssHrVwxj'),('kIzUvDZWau','JhCIL','HVwNs@kNlZr.YWb','yydRmYMQIY'),('Kk51HYPYgt','HhsxM','URoUr@dEyJX.QLe','UcHPNwgNdv'),('KKvkdLSf9q','ZlyTy','XLUAX@QbrSe.fqc','qRWCOaPqRd'),('kpcFX1e6MB','HAdkF','opXMY@sPZHO.ByX','xaODswlEpC'),('kQcjWk8UX5','YuoEM','OeZtF@PxRnb.bSn','CIlGFWCCKH'),('LddWTRdOc8','fDaEh','rGqIT@fPLbT.ClJ','qlLWTylaOl'),('lDUVNwqxKh','yjvwX','fpoqD@hWYlg.YWh','klekRPtvAS'),('LmUJO1jfsw','rEMHn','wHnCJ@aoljZ.NTD','qLXlpMKksq'),('LOlIwx96sK','EkHPp','JqDgF@whfKA.AEn','aXVZQbZwww'),('LpbWOMiBFL','mqBtV','WqEKy@hOhyV.Dyo','pqnykcSnMk'),('LQ6B86BIho','sMpBp','kFiLT@Mthbo.cEY','PFSXvqXGzj'),('lSUm4VABGJ','mwyUu','PdvCI@tbZAF.wUg','mIMyOWKiof'),('m0IAjB89V5','TNCHu','PwBBv@QOAVb.VLZ','newCompany'),('miSQkarCj7','HfqAw','aULMI@IvrUO.xac','aiGVxFdIdq'),('MOo4qOz8A8','xdjnq','xvPLH@wROyh.Iks','kyNXqYWSYR'),('Mta1Tl9oT1','newName','kMaIV@LVJzc.egV','jyRKwcZKyw'),('mym5fKZaDB','dKvle','hnbfB@DHDKx.oBC','newCompany'),('n134dojFnw','vWnah','JQLit@tKdQr.DJW','huRJCYhmnI'),('Ndrtz3ueNI','RrBWl','bPBoF@mSCcp.Fxd','HtluniJzfp'),('NGnK3LVHkI','ibfcw','rqDuA@kKKki.SDE','YcliKZtATg'),('nkMj851Fe0','MZZCR','UmcJm@AGtFo.FmM','newCompany'),('nLTkKk9kge','CcQim','WqVqq@GZTPV.Rtc','uiwXTzoRYJ'),('nmVA9OOH88','BjdbE','adDpM@PCGKb.qwS','CzdSZeLeGL'),('nQAHquHcpU','UkjRc','teRnd@xVaLo.aUI','cROEMKkxVI'),('nQcYf8S53R','ZvDPb','FuGcl@zjPOd.kZW','eXvKsehmBA'),('NqFWXp7mrK','SQWiw','ianJv@JyLIx.egX','XGgXfhimqs'),('NqWyrB1yo5','EZplm','rWHCW@pIeBs.dAf','newCompany'),('nUaLmydwil','LakEP','qCGQn@eVdor.grm','zOIYaraHSG'),('NvAB49qt4p','UJhJg','bVkUF@puuXI.qSr','newCompany'),('nwjj5YWbQc','cGYmt','KrYoA@SjxTE.jic','lfSVViSSLe'),('nZSaQv6WX2','UDDxo','oBQbJ@aMUQB.dxA','yxrwaoeaih'),('nzYG900R7Y','uomtg','gEGZp@fCPpI.qmV','wjLjBYblwC'),('OHNKAX7D3T','hCZYw','EOZkN@gNwvU.qeg','unGlNtWcRf'),('oJ3RerI6Vu','radTu','pJHWN@BrEYq.wYR','newCompany'),('OLHzdVYKxn','EIKOM','SFbZv@jrwtN.DzS','tmaaZtVaGf'),('OPBUBeYIYl','romdy','KluJX@Nlmiu.Ont','newCompany'),('OsjVFlSEXV','yDvww','FIaeD@imTJC.ses','newCompany'),('OustwD5P5t','VbfSr','ESmUr@Kunuh.ByH','RxydzGEWua'),('OXwGDS7VQa','XJwkL','EShSz@ePnRW.DLm','baaIRGZLUo'),('p3sIduw9Tg','IryXr','nzzfJ@snCRM.jAI','QJBDqLaUBS'),('par012354','phj','park@47parkh',NULL),('park','phj','park@park.com','mjc'),('park123','phj','park@parkh',NULL),('park1234','phj','park@parkh',NULL),('park12354','phj','park@parkh',NULL),('park2','phj','park@47parkh',NULL),('pBUwiWunBY','newName','PGKxP@TQuNY.TPF','BhSwOpEbGG'),('pcTEE69XU1','vwVwd','LOINi@EsofC.Rjq','dmuhsIOVNG'),('pCxhDuYPe8','ioSAN','amISF@fJBbE.brf','USDEBBoyDJ'),('pkYnwcIeNb','PFafa','MlJSO@aviZu.aca','mtwAyAriGl'),('poh3O1PKig','ShflA','kFtGU@DZvYO.mYr','gphiSAboOa'),('PpB6qbNxMZ','tPEcI','nAQJQ@mOPUg.gWU','newCompany'),('ppVoyTa0iD','FBRev','KcyYh@XVNNy.CUw','RJscbPfVGr'),('PtLT1NEReZ','newName','iyJXr@iKBtI.dnS','GovfLTUEOk'),('PuVx6G478q','fhuMG','RfOlO@edtWd.XNn','BeaJlCfZiB'),('pziS4CjxJ2','xGArd','YrvBV@GIfUC.gMU','HbfaXgKhll'),('q06KqfkPht','hIXrZ','ZjBmM@UMCbN.dDA','iCIfhsRqvl'),('q9GFslEoqS','vFszb','Dinlg@QsAyw.uAs','IIeIMjgmUa'),('qaP715UDhf','newName','kTwWi@syBsO.sst','dpsbtkKONM'),('qc8W3NVUz3','newName','UBpqq@gkYvL.ZjP','SxYFgIJVtp'),('qhJrsEhS7l','newName','dPZYB@qxBpC.jPz','VyqckjkRjr'),('Qjw8gVi0q0','UQjOt','YRhTL@IUgBV.bAU','cxopRnUwAM'),('QW5fA2QjKX','AwBHu','JKfxI@igunY.wjN','aZvJVpEoYb'),('qwijoNanQm','ZtUux','vrOSv@hohbB.nNt','zHYGTteeJF'),('r7Hg5JRnQm','czgXF','BWkDb@bvOHc.bJW','bOVdXfAXqI'),('r97hGHxYjP','xKsiV','xObJv@DwcGS.SIx','newCompany'),('RAMk9KIbqU','QiTWQ','oIzgq@LPTrG.PVv','sGpcSvIzxN'),('rbmwzp6L4U','WvFAU','segLo@HcNXI.jlL','gSbTasbHBo'),('rFeyB7DD8l','CsbRH','JTkJp@hqcHz.Bje','sYCdFZdagA'),('rgZAB6cyox','BdeJy','sjIAT@XRvZG.Zyx','YBcJdZucbJ'),('RjTbybHDnl','newName','ZduKd@kFhIL.ebZ','jkyfWSenDa'),('RwUIG4LNY5','newName','QMcfF@NUFJM.hyn','aRDYdxOjvV'),('RZk56LV7F0','zuqEQ','MpjNh@SQxIS.RTh','JoXYmvdjQQ'),('s5650CsnMF','kUxqi','gMHjn@fkBrh.bJS','vdTzeSXWrx'),('s5aHbrSQBa','fKTzP','tlHBn@LIkmf.fTU','newCompany'),('s5h7i9gIO2','RFbvT','Lbceg@hDmYj.uRk','PHeFQfmvAT'),('S7av1PnoOx','Pujwz','LlHIT@eaKhW.Xxk','DhFabReGAf'),('SB0oT4Tdvh','fCdwV','ESCAt@JUqUD.PIV','CBksUNGqiE'),('Sb0qZoMkG7','nfNIN','zQeEV@OyXOm.tnj','poaDEhkuPd'),('SeAFc8iOY9','newName','Eyujz@Zjqtz.GSX','AXSKspLbtP'),('SgZpErLgOC','ZXWJQ','UxRKU@Khtmm.jBE','cvGMcyAvSg'),('sJYg9ZLlCF','RCDvZ','WUuCK@IEyPs.OqV','KOHloWRbRe'),('sPJeMQjPjE','kMYdU','mwisx@vKaxn.xbx','YcaNmOWHef'),('sTYBOhPhiF','IoLeD','iCPPF@icIlV.qeP','eDHZzJeFvI'),('sUWVkEfXMi','newName','WKPmU@trJYc.qXw','xHaBpLnzTt'),('Symwl7i6kX','puial','khSJA@DnWmx.NiZ','KllgterZkp'),('t63vPIMYNq','LWLLL','XAhIH@KfqBA.vhg','PscEdrNUAO'),('TakfBqrfK2','qAlJs','LBEwk@aUjFc.NFa','YRinPCmIYT'),('tBohl7w0Yk','RPtCa','wicDn@gPAGf.mBW','kDKKSTrPqk'),('TbXnAB7WnR','nOkLc','Xtrye@ABOBf.erL','uVZXmkImBg'),('testUserId','newName','newEmail','newCompany'),('tf894inVBX','CpNoo','htgNy@rGSaf.uYU','YIhIWrJIJp'),('thgddFgkMv','Xgpgx','FXntt@jJvOx.mKx','kkCXFeHYwz'),('TjINfQnMsB','newName','IxmHs@mTiht.Xwr','mXhOwEhQtx'),('tlgFCwBXev','avZRU','tUxbi@fmViz.GFH','JsqCsBxAjW'),('ToUGQdwRo5','newName','raxmu@RTTAF.ZmW','mzoEfRTNCv'),('Ucd0Bh6s9K','JTmIJ','mZqNg@Yngzy.HDC','fJjwxaZvPq'),('ufbYHvewcL','iTsDP','ZxIvf@CgPVh.wAv','WhTrTezHSu'),('Ugam9XIhbE','newName','nYUHF@sNdHb.gLS','nymRRSZGHf'),('ujAgFAj26s','FQffS','TJPmj@MNGJB.vUh','pEHoRztNbR'),('umJ0B2l849','newName','kOzgb@KQqLo.wtC','kfzLxWRCGD'),('unB3sveXUJ','upKQP','rrVIu@YYvpP.vZy','newCompany'),('UQW6BhPynk','newName','ifIea@vRnju.THe','YeOrPrJBOo'),('UQZA7zVUPL','newName','LIRVQ@zlNEW.qtW','uChKdXtEYb'),('UrvAaIK3dK','MpxeZ','aFUDE@zNUrY.akU','newCompany'),('UVTDEsef1k','AhXbS','swlGW@ELGFe.HpR','ZkQHdTtVIw'),('v0sVodpkm8','DoVto','TlUQm@XQOCR.lFx','FMGkOvaKIX'),('V2dD93uvoU','newName','uUCKV@yXKZQ.rJP','gjSRqImdOr'),('V8s9xD16Nx','RmqTF','EKysD@nuSMa.GLk','FtfIYcXXtm'),('vcQj5QffvV','YCjnp','YatXM@BohbH.BqR','KgGrAUtEBv'),('vnF5J88SKa','jsMlr','jdbeg@XrpQp.kiU','newCompany'),('vrXdkq2Ro7','DoVsn','CiDjq@mGUbA.SHu','XnOiXxFRVN'),('VsgBjY7Sjb','newName','GyxSs@pwfec.hdG','mAjranajyK'),('vTVGamyJ8w','TYoeK','DCmyH@SwOXZ.WBS','FkppwmETrw'),('vy8crbywG9','gRzFR','KCADV@AXXds.BZy','YvkeOgNkdH'),('wapP0vkGLj','UuRPe','nhmSb@rFLmC.Tnk','MvediovpsF'),('wBQTpK4GKF','QXmLn','SPwey@fGdcV.ces','hjSgqahpMG'),('wG7Eq5EeDW','newName','EdgGC@tJbjd.aeH','AJEzKRMMAc'),('WgicVkjDL7','FPpNu','sTmwf@TUOcY.kQj','newCompany'),('wJtZC2BCUo','ugist','xDhXh@euvcU.HML','FgZStaMYwQ'),('wMpyZW209K','BKGhO','dgDeu@rOKcG.EGA','LPGHIdPewi'),('wp9Bmjs0wY','iMAcU','zVhAz@yTWOh.bua','oNbSxghdWn'),('WQaEAH8jFr','newName','yOexe@LxPBZ.tiE','HgMlmQfDRC'),('WUTC7Rvy57','mSFfK','IytHQ@gspst.nCd','nhARzGUHFw'),('WWmIgZoSp2','jbhEO','kRToR@imBtf.TcO','HusSVijeqR'),('wyCNmf7AYL','ebPmr','yuGVR@FoEQZ.yXg','jdiCgBTRYk'),('xbK2qthJ95','iqDXu','fwdSE@OAIKZ.tEq','VVFUXiCXDG'),('Xl9JPIn6Ua','aeuOE','VjfZI@FBDib.eAl','CfYdQavCuc'),('XMaxQX5cNm','Awwbq','dGuRZ@ceFvk.RNz','eMCPFuJRnF'),('xpPkSNueU5','newName','KFZDE@VhOTo.zVt','dcKDTuuRyD'),('xrxXhs30Za','rlHNJ','zftso@xBzDX.sgk','newCompany'),('xTvpsbh63K','newName','zSSuU@VBLzc.dvM','iBiYxTpnoq'),('XtZx6A4DPp','fxpFB','bkLDL@nzLwN.sqJ','KkoUtkSwxf'),('xuLLytwCXb','ckcVk','aacox@dpISo.GcD','newCompany'),('Xzp7enHMqk','gQFNF','vABSM@lybSv.EOJ','QZgyQLDLAa'),('y6uhPnSZKM','CZLIe','rHJbM@ABNRF.bEv','caFKHiYlRl'),('Ya0hsCnEvx','skKXr','KSPth@pEFFe.zwH','DPKqbTxTWm'),('YHsJBq3jZV','nWCNe','DCbMT@ZTpOn.ZuV','VmdlQUPWqH'),('yjm7OQnAS4','LxOmU','FewtA@QQycI.iqQ','TJUXyPNznY'),('yScTy7BWBk','wtaaN','nVItV@Zeebj.sVv','zTKQsmfpqf'),('YsKPUbRMha','WAAxV','qpinq@yOVwE.yVp','newCompany'),('yXJTZfeVoG','XxZSz','DUIzN@GwZKw.UzZ','newCompany'),('yzzu4ubjlS','Baxts','psgBN@lVbNT.AkK','TbAnvoFGyS'),('z3fWwAFFzm','yMzdS','qbhXM@WAmmM.Nlf','grvYONJkEm'),('zb3zhQ0IIO','jjGts','VoDUR@GFNLu.LoQ','xOQsWLLMGo'),('ZBz9TSF6NP','tjgIm','dvMXz@zBXpg.eFj','pzGAzeUjSK'),('zCTISS4BWo','CuRqB','bCLeD@cmZqQ.llC','yTgSYdMwQj'),('ZMjGoiDfVq','zWHmF','jSOSv@XSBXl.Ovd','UxonGSjfNN'),('zMTsehVjMu','ilvEU','zUtGX@dZMsl.QMO','tAtMSxJkTy'),('ZpfuIdsU80','ZiLVm','rGwpf@LHkVA.iua','newCompany'),('zvSThjBypj','newName','IGtua@rhTKA.tUY','chGOMEWpBk');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-23 21:36:55
