# Host: localhost  (Version: 5.5.53)
# Date: 2019-03-15 15:17:45
# Generator: MySQL-Front 5.3  (Build 4.234)

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

INSERT INTO `expoxy_storage` VALUES ('OB786-20181114-S0000061','2019-02-26 12:00:00','123',2),('ob786-20181114-s0000072','2019-03-05 18:02:45','10s04929',3);

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
  CONSTRAINT `number` FOREIGN KEY (`expoxy_num`) REFERENCES `expoxy_storage` (`expoxy_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `staff1` FOREIGN KEY (`unfreeze_operator`) REFERENCES `staff` (`staff_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "expoxy_unfreeze"
#

INSERT INTO `expoxy_unfreeze` VALUES ('OB786-20181114-S0000061','2019-03-14 17:16:18','10s04929'),('ob786-20181114-s0000072','2019-03-05 16:02:54','10s04929');

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
  CONSTRAINT `num` FOREIGN KEY (`expoxy_num`) REFERENCES `expoxy_unfreeze` (`expoxy_num`),
  CONSTRAINT `staff2` FOREIGN KEY (`user`) REFERENCES `staff` (`staff_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "expoxy_use"
#

INSERT INTO `expoxy_use` VALUES ('ob786-20181114-s0000072','2019-03-05 18:14:13','10s04929','CA3');

#
# Structure for table "expoxy_callback"
#

DROP TABLE IF EXISTS `expoxy_callback`;
CREATE TABLE `expoxy_callback` (
  `expoxy_num` varchar(255) NOT NULL DEFAULT '',
  `callbak_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `callbacker` varchar(255) NOT NULL DEFAULT '0',
  `callback_stauts` int(3) NOT NULL DEFAULT '1',
  `callback_failreson` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`expoxy_num`),
  UNIQUE KEY `num` (`expoxy_num`),
  KEY `staff3` (`callbacker`),
  CONSTRAINT `nm` FOREIGN KEY (`expoxy_num`) REFERENCES `expoxy_storage` (`expoxy_num`),
  CONSTRAINT `staff3` FOREIGN KEY (`callbacker`) REFERENCES `staff` (`staff_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "expoxy_callback"
#

