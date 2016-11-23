-- MySQL dump 10.11
--
-- Host: localhost    Database: bac_eeb
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt

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
-- Table structure for table `http_request_attributes`
--

DROP TABLE IF EXISTS `http_request_attributes`;
CREATE TABLE `http_request_attributes` (
  `row_id` varchar(255) NOT NULL,
  `request_id` varchar(255) default NULL,
  `attribute_name` varchar(255) default NULL,
  `attribute_persistence` varchar(255) default NULL,
  PRIMARY KEY  (`row_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `http_request_attributes`
--

LOCK TABLES `http_request_attributes` WRITE;
/*!40000 ALTER TABLE `http_request_attributes` DISABLE KEYS */;
INSERT INTO `http_request_attributes` VALUES ('c5b4d8a419eda2080119eda20b6b000e','c5b4d8a419eda2080119eda20aa00001','param2',NULL),('c5b4d8a419eda2080119eda20b7b000f','c5b4d8a419eda2080119eda20aa00001','param1',NULL),('c5b4d8a419eda2080119eda258910022','c5b4d8a419eda2080119eda258230015','param3',NULL),('c5b4d8a419eda2080119eda258a00023','c5b4d8a419eda2080119eda258230015','param2',NULL),('c5b4d8a419eda2080119eda258b00024','c5b4d8a419eda2080119eda258230015','param1',NULL);
/*!40000 ALTER TABLE `http_request_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `http_request_cookies`
--

DROP TABLE IF EXISTS `http_request_cookies`;
CREATE TABLE `http_request_cookies` (
  `row_id` varchar(255) NOT NULL,
  `request_id` varchar(255) default NULL,
  `comment` varchar(255) default NULL,
  `domain` varchar(255) default NULL,
  `path` varchar(255) default NULL,
  `cookie_name` varchar(255) default NULL,
  `cookie_persistence` varchar(255) default NULL,
  `is_secure` smallint(6) default NULL,
  `max_age` int(11) default NULL,
  `version` int(11) default NULL,
  PRIMARY KEY  (`row_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `http_request_cookies`
--

LOCK TABLES `http_request_cookies` WRITE;
/*!40000 ALTER TABLE `http_request_cookies` DISABLE KEYS */;
INSERT INTO `http_request_cookies` VALUES ('c5b4d8a419ed9d270119ed9d2b42000c','c5b4d8a419ed9d270119ed9d2a280001',NULL,NULL,NULL,'JSESSIONID','D73F375ACDE263589A43BD2430715F10',0,-1,0),('c5b4d8a419ed9d270119ed9d2b90000d','c5b4d8a419ed9d270119ed9d2a280001',NULL,NULL,NULL,'SCREEN_NAME','5434755a70677845576a6d4a49644c585869477253773d3d',0,-1,0),('c5b4d8a419ed9d270119ed9d2baf000e','c5b4d8a419ed9d270119ed9d2a280001',NULL,NULL,NULL,'COOKIE_SUPPORT','true',0,-1,0),('c5b4d8a419ed9d270119ed9d2baf000f','c5b4d8a419ed9d270119ed9d2a280001',NULL,NULL,NULL,'GUEST_LANGUAGE_ID','en_US',0,-1,0),('c5b4d8a419ed9d270119ed9d2bce0010','c5b4d8a419ed9d270119ed9d2a280001',NULL,NULL,NULL,'LOGIN','test@liferay.com',0,-1,0),('c5b4d8a419eda2080119eda20b8a0010','c5b4d8a419eda2080119eda20aa00001',NULL,NULL,NULL,'JSESSIONID','D73F375ACDE263589A43BD2430715F10',0,-1,0),('c5b4d8a419eda2080119eda20b8a0011','c5b4d8a419eda2080119eda20aa00001',NULL,NULL,NULL,'SCREEN_NAME','5434755a70677845576a6d4a49644c585869477253773d3d',0,-1,0),('c5b4d8a419eda2080119eda20b8a0012','c5b4d8a419eda2080119eda20aa00001',NULL,NULL,NULL,'COOKIE_SUPPORT','true',0,-1,0),('c5b4d8a419eda2080119eda20b9a0013','c5b4d8a419eda2080119eda20aa00001',NULL,NULL,NULL,'GUEST_LANGUAGE_ID','en_US',0,-1,0),('c5b4d8a419eda2080119eda20b9a0014','c5b4d8a419eda2080119eda20aa00001',NULL,NULL,NULL,'LOGIN','test@liferay.com',0,-1,0),('c5b4d8a419eda2080119eda258cf0025','c5b4d8a419eda2080119eda258230015',NULL,NULL,NULL,'JSESSIONID','D73F375ACDE263589A43BD2430715F10',0,-1,0),('c5b4d8a419eda2080119eda258cf0026','c5b4d8a419eda2080119eda258230015',NULL,NULL,NULL,'SCREEN_NAME','5434755a70677845576a6d4a49644c585869477253773d3d',0,-1,0),('c5b4d8a419eda2080119eda258df0027','c5b4d8a419eda2080119eda258230015',NULL,NULL,NULL,'COOKIE_SUPPORT','true',0,-1,0),('c5b4d8a419eda2080119eda258df0028','c5b4d8a419eda2080119eda258230015',NULL,NULL,NULL,'GUEST_LANGUAGE_ID','en_US',0,-1,0),('c5b4d8a419eda2080119eda258ef0029','c5b4d8a419eda2080119eda258230015',NULL,NULL,NULL,'LOGIN','test@liferay.com',0,-1,0);
/*!40000 ALTER TABLE `http_request_cookies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `http_request_headers`
--

DROP TABLE IF EXISTS `http_request_headers`;
CREATE TABLE `http_request_headers` (
  `row_id` varchar(255) NOT NULL,
  `request_id` varchar(255) default NULL,
  `header_name` varchar(255) default NULL,
  `header_persistence` varchar(255) default NULL,
  PRIMARY KEY  (`row_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `http_request_headers`
--

LOCK TABLES `http_request_headers` WRITE;
/*!40000 ALTER TABLE `http_request_headers` DISABLE KEYS */;
INSERT INTO `http_request_headers` VALUES ('c5b4d8a419ed9d270119ed9d2a670002','c5b4d8a419ed9d270119ed9d2a280001','host','localhost:8080'),('c5b4d8a419ed9d270119ed9d2a770003','c5b4d8a419ed9d270119ed9d2a280001','user-agent','Mozilla/5.0 (Windows; U; Windows NT 5.1; en-GB; rv:1.8.1.9) Gecko/20071025 Firefox/2.0.0.9'),('c5b4d8a419ed9d270119ed9d2a960004','c5b4d8a419ed9d270119ed9d2a280001','accept','text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5'),('c5b4d8a419ed9d270119ed9d2ab50005','c5b4d8a419ed9d270119ed9d2a280001','accept-language','en-gb,en;q=0.5'),('c5b4d8a419ed9d270119ed9d2ad40006','c5b4d8a419ed9d270119ed9d2a280001','accept-encoding','gzip,deflate'),('c5b4d8a419ed9d270119ed9d2af40007','c5b4d8a419ed9d270119ed9d2a280001','accept-charset','ISO-8859-1,utf-8;q=0.7,*;q=0.7'),('c5b4d8a419ed9d270119ed9d2b130008','c5b4d8a419ed9d270119ed9d2a280001','keep-alive','300'),('c5b4d8a419ed9d270119ed9d2b130009','c5b4d8a419ed9d270119ed9d2a280001','connection','keep-alive'),('c5b4d8a419ed9d270119ed9d2b22000a','c5b4d8a419ed9d270119ed9d2a280001','cookie','JSESSIONID=D73F375ACDE263589A43BD2430715F10; SCREEN_NAME=5434755a70677845576a6d4a49644c585869477253773d3d; COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=en_US; LOGIN=test@liferay.com'),('c5b4d8a419ed9d270119ed9d2b42000b','c5b4d8a419ed9d270119ed9d2a280001','cache-control','max-age=0'),('c5b4d8a419ed9d270119ed9d63880012','c5b4d8a419ed9d270119ed9d63780011','host','localhost:8080'),('c5b4d8a419ed9d270119ed9d63880013','c5b4d8a419ed9d270119ed9d63780011','user-agent','Mozilla/5.0 (Windows; U; Windows NT 5.1; en-GB; rv:1.8.1.9) Gecko/20071025 Firefox/2.0.0.9'),('c5b4d8a419ed9d270119ed9d63a70014','c5b4d8a419ed9d270119ed9d63780011','accept','text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5'),('c5b4d8a419ed9d270119ed9d63b70015','c5b4d8a419ed9d270119ed9d63780011','accept-language','en-gb,en;q=0.5'),('c5b4d8a419ed9d270119ed9d63d60016','c5b4d8a419ed9d270119ed9d63780011','accept-encoding','gzip,deflate'),('c5b4d8a419ed9d270119ed9d63d60017','c5b4d8a419ed9d270119ed9d63780011','accept-charset','ISO-8859-1,utf-8;q=0.7,*;q=0.7'),('c5b4d8a419ed9d270119ed9d63e60018','c5b4d8a419ed9d270119ed9d63780011','keep-alive','300'),('c5b4d8a419ed9d270119ed9d64050019','c5b4d8a419ed9d270119ed9d63780011','connection','keep-alive'),('c5b4d8a419ed9d270119ed9d6405001a','c5b4d8a419ed9d270119ed9d63780011','cookie','JSESSIONID=D73F375ACDE263589A43BD2430715F10; SCREEN_NAME=5434755a70677845576a6d4a49644c585869477253773d3d; COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=en_US; LOGIN=test@liferay.com'),('c5b4d8a419ed9f690119ed9f6c760002','c5b4d8a419ed9f690119ed9f6c380001','host','localhost:8080'),('c5b4d8a419ed9f690119ed9f6c860003','c5b4d8a419ed9f690119ed9f6c380001','user-agent','Mozilla/5.0 (Windows; U; Windows NT 5.1; en-GB; rv:1.8.1.9) Gecko/20071025 Firefox/2.0.0.9'),('c5b4d8a419ed9f690119ed9f6ca50004','c5b4d8a419ed9f690119ed9f6c380001','accept','text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5'),('c5b4d8a419ed9f690119ed9f6cc40005','c5b4d8a419ed9f690119ed9f6c380001','accept-language','en-gb,en;q=0.5'),('c5b4d8a419ed9f690119ed9f6cc40006','c5b4d8a419ed9f690119ed9f6c380001','accept-encoding','gzip,deflate'),('c5b4d8a419ed9f690119ed9f6ce40007','c5b4d8a419ed9f690119ed9f6c380001','accept-charset','ISO-8859-1,utf-8;q=0.7,*;q=0.7'),('c5b4d8a419ed9f690119ed9f6ce40008','c5b4d8a419ed9f690119ed9f6c380001','keep-alive','300'),('c5b4d8a419ed9f690119ed9f6cf30009','c5b4d8a419ed9f690119ed9f6c380001','connection','keep-alive'),('c5b4d8a419ed9f690119ed9f6d03000a','c5b4d8a419ed9f690119ed9f6c380001','cookie','JSESSIONID=D73F375ACDE263589A43BD2430715F10; SCREEN_NAME=5434755a70677845576a6d4a49644c585869477253773d3d; COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=en_US; LOGIN=test@liferay.com'),('c5b4d8a419eda2080119eda20ade0002','c5b4d8a419eda2080119eda20aa00001','host','localhost:8080'),('c5b4d8a419eda2080119eda20ade0003','c5b4d8a419eda2080119eda20aa00001','user-agent','Mozilla/5.0 (Windows; U; Windows NT 5.1; en-GB; rv:1.8.1.9) Gecko/20071025 Firefox/2.0.0.9'),('c5b4d8a419eda2080119eda20aee0004','c5b4d8a419eda2080119eda20aa00001','accept','text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5'),('c5b4d8a419eda2080119eda20afe0005','c5b4d8a419eda2080119eda20aa00001','accept-language','en-gb,en;q=0.5'),('c5b4d8a419eda2080119eda20b0d0006','c5b4d8a419eda2080119eda20aa00001','accept-encoding','gzip,deflate'),('c5b4d8a419eda2080119eda20b0d0007','c5b4d8a419eda2080119eda20aa00001','accept-charset','ISO-8859-1,utf-8;q=0.7,*;q=0.7'),('c5b4d8a419eda2080119eda20b0d0008','c5b4d8a419eda2080119eda20aa00001','keep-alive','300'),('c5b4d8a419eda2080119eda20b1d0009','c5b4d8a419eda2080119eda20aa00001','connection','keep-alive'),('c5b4d8a419eda2080119eda20b1d000a','c5b4d8a419eda2080119eda20aa00001','cookie','JSESSIONID=D73F375ACDE263589A43BD2430715F10; SCREEN_NAME=5434755a70677845576a6d4a49644c585869477253773d3d; COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=en_US; LOGIN=test@liferay.com'),('c5b4d8a419eda2080119eda20b2c000b','c5b4d8a419eda2080119eda20aa00001','cache-control','max-age=0'),('c5b4d8a419eda2080119eda258430016','c5b4d8a419eda2080119eda258230015','host','localhost:8080'),('c5b4d8a419eda2080119eda258430017','c5b4d8a419eda2080119eda258230015','user-agent','Mozilla/5.0 (Windows; U; Windows NT 5.1; en-GB; rv:1.8.1.9) Gecko/20071025 Firefox/2.0.0.9'),('c5b4d8a419eda2080119eda258430018','c5b4d8a419eda2080119eda258230015','accept','text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5'),('c5b4d8a419eda2080119eda258520019','c5b4d8a419eda2080119eda258230015','accept-language','en-gb,en;q=0.5'),('c5b4d8a419eda2080119eda25852001a','c5b4d8a419eda2080119eda258230015','accept-encoding','gzip,deflate'),('c5b4d8a419eda2080119eda25872001b','c5b4d8a419eda2080119eda258230015','accept-charset','ISO-8859-1,utf-8;q=0.7,*;q=0.7'),('c5b4d8a419eda2080119eda25872001c','c5b4d8a419eda2080119eda258230015','keep-alive','300'),('c5b4d8a419eda2080119eda25881001d','c5b4d8a419eda2080119eda258230015','connection','keep-alive'),('c5b4d8a419eda2080119eda25881001e','c5b4d8a419eda2080119eda258230015','cookie','JSESSIONID=D73F375ACDE263589A43BD2430715F10; SCREEN_NAME=5434755a70677845576a6d4a49644c585869477253773d3d; COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=en_US; LOGIN=test@liferay.com');
/*!40000 ALTER TABLE `http_request_headers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `http_request_parameters`
--

DROP TABLE IF EXISTS `http_request_parameters`;
CREATE TABLE `http_request_parameters` (
  `row_id` varchar(255) NOT NULL,
  `request_id` varchar(255) default NULL,
  `parameter_name` varchar(255) default NULL,
  `parameter_persistence` varchar(255) default NULL,
  PRIMARY KEY  (`row_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `http_request_parameters`
--

LOCK TABLES `http_request_parameters` WRITE;
/*!40000 ALTER TABLE `http_request_parameters` DISABLE KEYS */;
INSERT INTO `http_request_parameters` VALUES ('c5b4d8a419ed9d270119ed9d6424001b','c5b4d8a419ed9d270119ed9d63780011','param2','value2'),('c5b4d8a419ed9d270119ed9d6443001c','c5b4d8a419ed9d270119ed9d63780011','param1','value1'),('c5b4d8a419ed9f690119ed9f6d13000b','c5b4d8a419ed9f690119ed9f6c380001','param2','value2'),('c5b4d8a419ed9f690119ed9f6d32000c','c5b4d8a419ed9f690119ed9f6c380001','param1','value1'),('c5b4d8a419eda2080119eda20b2c000c','c5b4d8a419eda2080119eda20aa00001','param2','value2'),('c5b4d8a419eda2080119eda20b4c000d','c5b4d8a419eda2080119eda20aa00001','param1','value1'),('c5b4d8a419eda2080119eda25891001f','c5b4d8a419eda2080119eda258230015','param3','value3'),('c5b4d8a419eda2080119eda258910020','c5b4d8a419eda2080119eda258230015','param2','value2'),('c5b4d8a419eda2080119eda258910021','c5b4d8a419eda2080119eda258230015','param1','value1');
/*!40000 ALTER TABLE `http_request_parameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `http_requests`
--

DROP TABLE IF EXISTS `http_requests`;
CREATE TABLE `http_requests` (
  `request_id` varchar(255) NOT NULL,
  `auth_type` varchar(255) default NULL,
  `context_path` varchar(255) default NULL,
  `method` varchar(255) default NULL,
  `path_info` varchar(255) default NULL,
  `path_info_translated` varchar(255) default NULL,
  `remote_user` varchar(255) default NULL,
  `requested_session_id` varchar(255) default NULL,
  `request_uri` varchar(255) default NULL,
  `request_url` varchar(255) default NULL,
  `servlet_path` varchar(255) default NULL,
  `user_principal` varchar(255) default NULL,
  `character_encoding` varchar(255) default NULL,
  `content_type` varchar(255) default NULL,
  `local_address` varchar(255) default NULL,
  `protocol` varchar(255) default NULL,
  `remote_address` varchar(255) default NULL,
  `remote_host` varchar(255) default NULL,
  `scheme` varchar(255) default NULL,
  `server_name` varchar(255) default NULL,
  `request_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `content_length` int(11) default NULL,
  `local_port` int(11) default NULL,
  `remote_port` int(11) default NULL,
  `server_port` int(11) default NULL,
  PRIMARY KEY  (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `http_requests`
--

LOCK TABLES `http_requests` WRITE;
/*!40000 ALTER TABLE `http_requests` DISABLE KEYS */;
INSERT INTO `http_requests` VALUES ('c5b4d8a419ed9d270119ed9d2a280001',NULL,'/BAC2EEBBridge','GET',NULL,NULL,NULL,'D73F375ACDE263589A43BD2430715F10','/BAC2EEBBridge/RecordHTTPRequest.do','http://localhost:8080/BAC2EEBBridge/RecordHTTPRequest.do','/RecordHTTPRequest.do',NULL,NULL,NULL,'127.0.0.1','HTTP/1.1','127.0.0.1','127.0.0.1','http','localhost','2008-05-15 17:25:08',-1,8080,1996,8080),('c5b4d8a419ed9d270119ed9d63780011',NULL,'/BAC2EEBBridge','GET',NULL,NULL,NULL,'D73F375ACDE263589A43BD2430715F10','/BAC2EEBBridge/RecordHTTPRequest.do','http://localhost:8080/BAC2EEBBridge/RecordHTTPRequest.do','/RecordHTTPRequest.do',NULL,NULL,NULL,'127.0.0.1','HTTP/1.1','127.0.0.1','127.0.0.1','http','localhost','2008-05-15 17:25:24',-1,8080,1996,8080),('c5b4d8a419ed9f690119ed9f6c380001',NULL,'/BAC2EEBBridge','GET',NULL,NULL,NULL,'D73F375ACDE263589A43BD2430715F10','/BAC2EEBBridge/RecordHTTPRequest.do','http://localhost:8080/BAC2EEBBridge/RecordHTTPRequest.do','/RecordHTTPRequest.do',NULL,NULL,NULL,'127.0.0.1','HTTP/1.1','127.0.0.1','127.0.0.1','http','localhost','2008-05-15 17:27:36',-1,8080,2028,8080),('c5b4d8a419eda2080119eda20aa00001',NULL,'/BAC2EEBBridge','GET',NULL,NULL,NULL,'D73F375ACDE263589A43BD2430715F10','/BAC2EEBBridge/RecordHTTPRequest.do','http://localhost:8080/BAC2EEBBridge/RecordHTTPRequest.do','/RecordHTTPRequest.do',NULL,NULL,NULL,'127.0.0.1','HTTP/1.1','127.0.0.1','127.0.0.1','http','localhost','2008-05-15 17:30:28',-1,8080,2055,8080),('c5b4d8a419eda2080119eda258230015',NULL,'/BAC2EEBBridge','GET',NULL,NULL,NULL,'D73F375ACDE263589A43BD2430715F10','/BAC2EEBBridge/RecordHTTPRequest.do','http://localhost:8080/BAC2EEBBridge/RecordHTTPRequest.do','/RecordHTTPRequest.do',NULL,NULL,NULL,'127.0.0.1','HTTP/1.1','127.0.0.1','127.0.0.1','http','localhost','2008-05-15 17:30:49',-1,8080,2055,8080);
/*!40000 ALTER TABLE `http_requests` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-05-15 17:38:16
