# Host: localhost  (Version: 5.5.34)
# Date: 2019-02-25 20:59:18
# Generator: MySQL-Front 5.3  (Build 4.43)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "stff"
#

DROP TABLE IF EXISTS `stff`;
CREATE TABLE `stff` (
  `staff_num` varchar(255) NOT NULL DEFAULT '',
  `staff_name` varchar(255) NOT NULL DEFAULT '',
  KEY `staff_num` (`staff_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "stff"
#


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
  CONSTRAINT `staff` FOREIGN KEY (`storage_operator`) REFERENCES `stff` (`staff_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "expoxy_storage"
#


#
# Structure for table "expxy_callback"
#

DROP TABLE IF EXISTS `expxy_callback`;
CREATE TABLE `expxy_callback` (
  `expoxy_num` varchar(255) NOT NULL DEFAULT '',
  `callbak_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `callbacker` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`expoxy_num`),
  UNIQUE KEY `num` (`expoxy_num`),
  KEY `staff3` (`callbacker`),
  CONSTRAINT `staff3` FOREIGN KEY (`callbacker`) REFERENCES `stff` (`staff_num`),
  CONSTRAINT `nm` FOREIGN KEY (`expoxy_num`) REFERENCES `expoxy_storage` (`expoxy_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "expxy_callback"
#


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
  CONSTRAINT `staff1` FOREIGN KEY (`unfreeze_operator`) REFERENCES `stff` (`staff_num`),
  CONSTRAINT `number` FOREIGN KEY (`expoxy_num`) REFERENCES `expoxy_storage` (`expoxy_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "expoxy_unfreeze"
#


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
  CONSTRAINT `staff2` FOREIGN KEY (`user`) REFERENCES `stff` (`staff_num`),
  CONSTRAINT `num` FOREIGN KEY (`expoxy_num`) REFERENCES `expoxy_unfreeze` (`expoxy_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "expoxy_use"
#

