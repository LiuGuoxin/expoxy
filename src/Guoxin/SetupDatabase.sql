# Host: localhost  (Version: 5.5.34)
# Date: 2019-03-03 19:35:23
# Generator: MySQL-Front 5.3  (Build 4.43)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "staff"
#

DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `staff_num` varchar(255) NOT NULL DEFAULT '',
  `staff_name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`staff_num`),
  UNIQUE KEY `staff_num` (`staff_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "staff"
#

INSERT INTO `staff` VALUES ('10s04929','刘国欣'),('123','测试'),('abc','abc');

#
# Structure for table "expoxy_storage"
#

DROP TABLE IF EXISTS `expoxy_storage`;
CREATE TABLE `expoxy_storage` (
  `expoxy_num` varchar(255) NOT NULL,
  `storage_time` datetime NOT NULL,
  `storage_operator` varchar(255) NOT NULL DEFAULT '0',
  `expoxy_status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`expoxy_num`),
  KEY `staff` (`storage_operator`),
  CONSTRAINT `staff` FOREIGN KEY (`storage_operator`) REFERENCES `staff` (`staff_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "expoxy_storage"
#

INSERT INTO `expoxy_storage` VALUES ('ob789-20190212-l0012321','2019-02-28 21:16:28','10s04929',1),('ob789-20190212-s0012311','2019-02-28 21:10:19','10s04929',3);

#
# Structure for table "expoxy_unfreeze"
#

DROP TABLE IF EXISTS `expoxy_unfreeze`;
CREATE TABLE `expoxy_unfreeze` (
  `expoxy_num` varchar(255) NOT NULL,
  `unfreeze_time` datetime NOT NULL,
  `unfreeze_operator` varchar(255) NOT NULL,
  PRIMARY KEY (`expoxy_num`),
  UNIQUE KEY `number` (`expoxy_num`) USING BTREE,
  KEY `staff1` (`unfreeze_operator`),
  CONSTRAINT `staff1` FOREIGN KEY (`unfreeze_operator`) REFERENCES `staff` (`staff_num`),
  CONSTRAINT `number` FOREIGN KEY (`expoxy_num`) REFERENCES `expoxy_storage` (`expoxy_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "expoxy_unfreeze"
#

INSERT INTO `expoxy_unfreeze` VALUES ('ob789-20190212-s0012311','2019-02-28 22:07:29','10s04929');

#
# Structure for table "expoxy_use"
#

DROP TABLE IF EXISTS `expoxy_use`;
CREATE TABLE `expoxy_use` (
  `expoxy_num` varchar(255) NOT NULL DEFAULT '',
  `use_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user` varchar(255) NOT NULL DEFAULT '0',
  `place` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`expoxy_num`),
  UNIQUE KEY `number` (`expoxy_num`) USING BTREE,
  KEY `staff2` (`user`),
  CONSTRAINT `staff2` FOREIGN KEY (`user`) REFERENCES `staff` (`staff_num`),
  CONSTRAINT `num` FOREIGN KEY (`expoxy_num`) REFERENCES `expoxy_unfreeze` (`expoxy_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "expoxy_use"
#

INSERT INTO `expoxy_use` VALUES ('ob789-20190212-s0012311','2019-03-03 18:58:52','10s04929','CA3');

#
# Structure for table "expoxy_callback"
#

DROP TABLE IF EXISTS `expoxy_callback`;
CREATE TABLE `expoxy_callback` (
  `expoxy_num` varchar(255) NOT NULL DEFAULT '',
  `callbak_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `callbacker` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`expoxy_num`),
  UNIQUE KEY `num` (`expoxy_num`),
  KEY `staff3` (`callbacker`),
  CONSTRAINT `staff3` FOREIGN KEY (`callbacker`) REFERENCES `staff` (`staff_num`),
  CONSTRAINT `nm` FOREIGN KEY (`expoxy_num`) REFERENCES `expoxy_storage` (`expoxy_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "expoxy_callback"
#

