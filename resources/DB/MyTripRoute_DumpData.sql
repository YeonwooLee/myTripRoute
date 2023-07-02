-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: enjoytrip
-- ------------------------------------------------------
-- Server version	8.0.32

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

alter table user auto_increment=1;
alter table board auto_increment=1;
alter table comment auto_increment=1;
alter table trip auto_increment=1;
alter table plan auto_increment=1;

commit;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'서비스 이용 관련 안내드립니다.','<p>각 탭에 대한 설명입니다.</p><br><h3>관광지 찾기</h3><br><p>한국의 다양한 관광지를 지역별, 분류별, 키워드별로 검색해보세요!</p><br><h3>여행루트 생성기</h3><br><p>다음 여행을 기획해보세요!</p><br><h3>게시판</h3><br><p>여행 계획에 대한 질문을 작성해주세요!</p><br>','admin',2,'2023-05-25 06:29:14','notice'),(2,'한국여행을 계획 중입니다','<p>두근두근 떨려요</p><br>','johndoe10',3,'2023-05-25 06:30:22','free'),(3,'제주도 여행 가보신 분 있나요','<p>맛집 추천해주세요</p><br>','michael',1,'2023-05-25 06:31:40','qna'),(4,'가장 좋았던 여행지는 어디인가요?','<p>저는 서울이요</p><br>','rd7',2,'2023-05-25 06:32:59','free'),(5,'여행 계획에 관광지 추가는 어떻게 하나요?','<p>알려주세요</p><br>','ebrown',5,'2023-05-25 06:35:22','qna'),(6,'강릉 빵집 추천합니다 ❤️','<h1>GAROO</h1><br><p>겁나게 맛잇어용 ❤️</p><br>','goguma',2,'2023-05-25 07:21:56','free'),(7,'순두부젤라또','<p>무슨 맛인가요?????</p><br>','icecream',1,'2023-05-25 07:23:30','free'),(8,'서울맛집!!','<ul><br><li>라면점빵 - 서울 종로구&#39;</li><br><li>정돈 - 대학로</li><br><li>도삭면 - 서울 광진구</li><br><li>맛좋은 청국장 - 청담역 인근</li><br><li>도도미역국 - 청담역 인근</li><br></ul><br>','ramen',2,'2023-05-25 07:25:39','free'),(9,'연어 좋아하시나요?','<p>대구에 <code>도마29</code> 가보세요<br>흐트러진덮밥 추천합니다</p><br>','salmon',1,'2023-05-25 07:27:48','free'),(10,'마크다운은 어떻게 써요?','<p>저도 쓰고 싶어요</p><br>','travel',1,'2023-05-25 07:29:59','qna'),(11,'사이트 개선 예정 사항','<p>다음 업데이트에는 <strong>여행 계획 수정</strong>을 개선할 예정입니다</p><br>','admin',3,'2023-05-25 07:32:28','notice');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'ebrown','<p>저도 가고 싶어요</p><br>','2023-05-25 06:30:45',2),(2,'ssafykim','<p>숙성도 추천합니다</p><br>','2023-05-25 06:33:39',3),(3,'ssafykim','<p>넵</p><br>','2023-05-25 06:33:56',1),(4,'ssafykim','<p>우와 재밌겠어요</p><br>','2023-05-25 06:34:12',2),(5,'ssafykim','<p>강릉 좋아요</p><br>','2023-05-25 06:34:28',4),(6,'rd7','<p>관광지 찾기에서 관광지 조회 후 결과 테이블 맨우측에 있는 버튼을 누르세요!</p><br>','2023-05-25 06:36:44',5),(7,'ebrown','<p>감사합니다</p><br>','2023-05-25 06:37:02',5),(8,'icecream','<p>챗지피티한테 물어보세여</p><br>','2023-05-25 07:30:37',10),(9,'goguma','<p>기대돼요</p><br>','2023-05-25 07:32:47',11),(10,'travel','<p>언제 업데이트되나요???</p><br>','2023-05-25 07:33:20',11),(11,'salmon','<p><em>good~</em></p><br>','2023-05-25 07:34:07',11);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,1,2615254,1,1,100000,'소요산은 종상화산'),(2,1,1114311,2,1,90000,'정상에 천마정이라는 정자가 있다'),(3,1,264590,3,1,200000,'제주도 가면 가야됨'),(4,1,125416,4,1,50000,'덕유산 인근'),(5,1,2752674,5,1,70000,'비슬산'),(11,3,130461,2,2,50000,'제주의 역사를 알 수 있는 곳'),(12,3,2819964,1,2,50000,'중문관광단지 위치'),(13,3,126731,1,1,10000,'가을에 방문'),(14,3,126452,1,1,10000,'큰 동굴'),(15,3,142957,2,3,50000,'공항 10분거리'),(16,3,127861,1,3,10000,'봄에 가면 유채꽃 볼 수 있음'),(17,3,2715653,3,1,10000,'유채꽃!!'),(19,4,2786779,2,1,20000,'두번가야지'),(20,4,987810,1,3,50000,'동백꽃'),(21,4,133262,1,1,50000,'맛있겠당'),(22,4,126081,1,1,50000,NULL),(23,5,125790,2,1,50000,'관동팔경'),(24,5,125789,2,2,50000,'율곡 이이'),(25,5,132774,2,1,50000,'오징어'),(26,5,2670860,1,2,50000,'꼬막 맛있겠다..'),(27,6,2847035,1,1,10000,'강남구청역 2번출구 토지주택공사 서울지역본부 옆'),(28,6,1571359,1,2,50000,'단체손님에 한해 한지 특강 제공'),(29,6,2845175,1,3,50000,'맛있는 도넛');
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (1,'등산 여행','ssafykim','2023-05-25 06:39:09'),(3,'떠나요 제주도','ssafykim','2023-05-25 06:44:31'),(4,'해운대','ssafykim','2023-05-25 06:47:32'),(5,'강릉','ssafykim','2023-05-25 06:49:52'),(6,'강남 카페 투어','ssafykim','2023-05-25 07:43:39');
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'John Doe','johndoe10','$2a$10$QmdMEXYGNFF9br0m102fVOVN7dksHDdhOrBEG0OsWc3DF0msHeaxW','johndoe@ssafy.com','normal',NULL),(2,'Emily Brown','ebrown','$2a$10$P1KwAdEPvc6aX4i0A5hOOuUy6AgcwmcxNVUMALKI2LZzjEGe9lBi2','ebrown@example.com','normal',NULL),(3,'Michael Lee','michael','$2a$10$Mg7e2CEW3sQanTsOZBvjIOKrLmCEI0FYWksm/6s8zIl7bHfDw4cG6','michael@google.com','normal',NULL),(4,'Robert Davis','rd7','$2a$10$KgT5OANPGAO.OcNBjARY3uXjYx95CdWm/IbSBYSYbQOxsSg13aZny','rd7@naver.com','normal',NULL),(5,'관리자','admin','$2a$10$KTj1s9bCMDDn2iU0hffghe2ZucuMj2MQEqU7jZKVZulM95O0vz9Z.','admin@mytriproute.com','admin',NULL),(6,'김싸피','ssafykim','$2a$10$ibfAsuCra6ThAieQZDE47ulX43oq0gyG2cjPpqQRXvJzycrzl5Xfa','kimssafy@ssafy.com','normal',NULL),(7,'김고구마','goguma','$2a$10$IfJh9VM4.HyHxjJY9.U5FugLEoWWhgpr.Txd0hv/5xOde39TqRDKa','goguma@google.com','normal',NULL),(8,'iloveicecream','icecream','$2a$10$xRebJ0Y3MvNf9.cz0l.PputMILEJ/6kgIY8uOXtmbAKs4zjFJySWS','icecream@ssafy.com','normal',NULL),(9,'ramenzzang','ramen','$2a$10$vKVdIpJQ5NGA4rKJSQwzduGwXf01lbjQGpMpRDcTGVEatmGTHwkCS','ramen@google.com','normal',NULL),(10,'연어연어연어','salmon','$2a$10$RJs3Pw1nq7TQs.xoNAcqP.FKVl.ON4CnAygVhqETug3MEnsHZ5r.C','salmon@naver.com','normal',NULL),(11,'김여행','travel','$2a$10$K5QdC2NiDOShmENclEyQHezlEZOOYGNbqOqaKAG63q5FuepH6tFBy','tr@td.com','normal',NULL);
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

-- Dump completed on 2023-05-25 17:28:35
