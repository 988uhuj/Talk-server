# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.21)
# Database: db_talk
# Generation Time: 2014-12-31 10:40:55 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table t_content
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_content`;

CREATE TABLE `t_content` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `title` varchar(100) DEFAULT '',
  `body` varchar(4000) DEFAULT '',
  `url` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `user_id` bigint(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_content` WRITE;
/*!40000 ALTER TABLE `t_content` DISABLE KEYS */;

INSERT INTO `t_content` (`id`, `title`, `body`, `url`, `create_time`, `user_id`, `status`)
VALUES
	('123',NULL,NULL,NULL,NULL,NULL,NULL),
	('12322',NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `t_content` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_file
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_file`;

CREATE TABLE `t_file` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(500) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `thumb_url` varchar(500) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table t_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(100) DEFAULT NULL,
  `account` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `body` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;

INSERT INTO `t_user` (`id`, `name`, `account`, `password`, `body`, `create_time`, `sex`, `status`)
VALUES
	('1c7e3e8d-cd99-424a-a069-32c7d06a3df2','3','123','2',NULL,NULL,NULL,NULL),
	('7cd543ef-43d3-4257-9806-93dc45617fd7','22','123','123',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
